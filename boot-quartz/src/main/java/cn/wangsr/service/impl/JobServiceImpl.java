package cn.wangsr.service.impl;

import cn.wangsr.entity.JobDo.JobInfoDo;
import cn.wangsr.service.JobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.quartz.DateBuilder.futureDate;


/**
 * @author: wjl
 * @description:
 * @time: 2019/9/24 0024 14:27
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private Scheduler scheduler;

    /**
     * @Description  启动创建任务  带有corn表达式
     * @author wjl
     * @date 2019/9/24 0024
     * @param [jobInfoDo]
     * @return void
     */
    public void startScheduleJob(JobInfoDo jobInfoDo) throws Exception {
        scheduler.start();

        JobKey jobKey = jobInfoDo.getJobKey(); //定时任务 的 名字和组名
        String description = jobInfoDo.getDescription();
        Class<? extends Job> jobClass = jobInfoDo.getJobClass(); //定时任务 的 逻辑实现类

        //构建job信息
        JobDetail jobDetail =JobBuilder.newJob(jobClass)
                .withIdentity(jobKey)
                .usingJobData(new JobDataMap(jobInfoDo.getJobDataMap()))
                .withDescription(description)
                .build();

        //执行规则
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobInfoDo.getCronExpression());
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobKey.getName(), jobKey.getGroup())
                .withDescription(description)
                .usingJobData(new JobDataMap(jobInfoDo.getJobDataMap()))
                .withSchedule(scheduleBuilder)
                .build();

            scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * @Description   非 cron表达式执行  interval 分钟之后执行
     * @author wjl
     * @date 2019/9/25 0025
     * @param [jobInfoDo interval   ]
     * @return void
     */
    public void startSimpleScheduleJob(JobInfoDo jobInfoDo,Integer interval) throws Exception {
        scheduler.start();

        JobKey jobKey = jobInfoDo.getJobKey(); //定时任务 的 名字和组名
        String description = jobInfoDo.getDescription();
        Class<? extends Job> jobClass = jobInfoDo.getJobClass(); //定时任务 的 逻辑实现类

        //构建job信息
        JobDetail jobDetail =JobBuilder.newJob(jobClass)
                .withIdentity(jobKey)
                .usingJobData(new JobDataMap(jobInfoDo.getJobDataMap()))
                .withDescription(description)
                .build();

        //执行规则
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity(jobKey.getName(), jobKey.getGroup())
                .startAt(DateBuilder.futureDate(interval, DateBuilder.IntervalUnit.MINUTE))  //开始时间  多久之后  按分钟
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(1).withRepeatCount(0)) // 重复间隔  重复次数 指的是除首次之外运行次数
                .withDescription(description)
                .usingJobData(new JobDataMap(jobInfoDo.getJobDataMap()))
                .build();
        scheduler.scheduleJob(jobDetail, simpleTrigger);
    }

    /**
     * 暂停Job
     */
    public void pauseJob(JobKey jobKey) throws SchedulerException {
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复Job
     */
    public void resumeJob(JobKey jobKey) throws SchedulerException {
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除Job
     */
    public void deleteJob(JobKey jobKey) throws SchedulerException {
        scheduler.deleteJob(jobKey);
    }

    /**
     * 修改Job 的cron表达式
     */
    public boolean modifyJobCron(JobInfoDo jobInfoDo) {
        String cronExpression = jobInfoDo.getCronExpression();
        if (!CronExpression.isValidExpression(cronExpression)) return false; //如果cron表达式的格式不正确,则返回修改失败
        JobKey jobKey = jobInfoDo.getJobKey();
        TriggerKey triggerKey = new TriggerKey(jobKey.getName(), jobKey.getGroup());
        try {
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (!cronTrigger.getCronExpression().equalsIgnoreCase(cronExpression)) { //如果cron发生变化了,则按新cron触发 进行重新启动定时任务
                CronTrigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey)
                        .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                        .usingJobData(new JobDataMap(jobInfoDo.getJobDataMap()))
                        .build();
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
