package cn.wangsr.controller;

import cn.wangsr.entity.JobDo.JobAndTriggerInfoDo;
import cn.wangsr.entity.JobDo.JobInfoDo;
import cn.wangsr.entity.JobDto.JobInfoDto;
import cn.wangsr.resultCode.ResultDto;
import cn.wangsr.service.impl.JobServiceImpl;
import cn.wangsr.service.impl.ManagerJobServiceImpl;
import cn.wangsr.socket.WebSocket;
import cn.wangsr.utils.BeanUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author: wjl
 * @description:
 * @time: 2019/9/24 0024 15:33
 */
@RestController
public class JobsController {

    @Autowired
    JobServiceImpl jobServiceImpl;
    @Autowired
    ManagerJobServiceImpl managerJobServiceImpl;
    ResultDto resultDto;
    @Autowired
    WebSocket webSocket;
    @PostMapping("/start")
    public String createScheduler(@RequestBody JobInfoDto jobInfoDto) {
        resultDto=new ResultDto();
        //参数校验
        if( StringUtils.isEmpty(jobInfoDto.getJobClass()) || StringUtils.isEmpty(jobInfoDto.getJobName()) || StringUtils.isEmpty(jobInfoDto.getJobGroup())){
            return JSON.toJSONString(resultDto.failedDto("参数不能为空"));
        }
        JobInfoDo jobInfoDo=new JobInfoDo(new JobKey(jobInfoDto.getJobName(),jobInfoDto.getJobGroup()),jobInfoDto.getDescription(),jobInfoDto.getCronExpression(),jobInfoDto.getJobDataMap(),BeanUtils.getClass(jobInfoDto.getJobClass()).getClass());
        //className存在性校验
        if(!BeanUtils.existCurrentPackage("cn.wangsr.jobs.detailsJobs",jobInfoDto.getJobClass())){
            return JSON.toJSONString(resultDto.failedDto("jobClass不存在"));
        }
        if(managerJobServiceImpl.getJobInfoByJobName(jobInfoDto.getJobName())){
            return JSON.toJSONString(resultDto.failedDto("jobName已存在"));
        }
        try {
            if(jobInfoDto.getType() == 0){
                jobServiceImpl.startScheduleJob(jobInfoDo);
            }else {
                if(!StringUtils.isEmpty(jobInfoDto.getInterval())){
                    jobServiceImpl.startSimpleScheduleJob(jobInfoDo,Integer.parseInt(jobInfoDto.getInterval()));
                }else {
                    return  JSON.toJSONString(resultDto.failedDto("开始时间不能为空"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(resultDto.failedDto(e));
        }
        return JSON.toJSONString(resultDto.successDto("任务开始"));
    }

    @PostMapping("/delete")
    public String deleteScheduler(@RequestBody JobInfoDto jobInfoDto) {
        resultDto=new ResultDto();
        if( StringUtils.isEmpty(jobInfoDto.getJobName()) || StringUtils.isEmpty(jobInfoDto.getJobGroup())){
            return JSON.toJSONString(resultDto.failedDto("参数不能为空"));
        }
        JobKey jobKey=new JobKey(jobInfoDto.getJobName(),jobInfoDto.getJobGroup());
        try {
            jobServiceImpl.deleteJob(jobKey);
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(resultDto.failedDto(e));
        }
        return JSON.toJSONString(resultDto.successDto("任务删除"));
    }


    @GetMapping(value = "/loadAllJobAndTrigger")
    public String getAllJobAndTrigger(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize,@RequestParam("userInfo")String userInfo, HttpServletRequest request) {
        resultDto=new ResultDto();
        PageInfo<JobAndTriggerInfoDo> pageInfo = managerJobServiceImpl.getAllJobAndTrigger(pageNum, pageSize);
        return JSON.toJSONString(resultDto.successDto(pageInfo));
    }
    @PostMapping("/deleteByKey")
    public String deleteJobByJobKey( @RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup){
        resultDto=new ResultDto();
        try {
            jobServiceImpl.deleteJob(new JobKey(jobName,jobGroup));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(resultDto.successDto("ok"));
    }

    @PostMapping("/pauseJobByKey")
    public String pauseJobByJobKey( @RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup){
        resultDto=new ResultDto();
        try {
            jobServiceImpl.pauseJob(new JobKey(jobName,jobGroup));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(resultDto.successDto("ok"));
    }

    @PostMapping("/resumeJobByKey")
    public String resumeJobByJobKey(  @RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup){
        resultDto=new ResultDto();
        try {
            jobServiceImpl.resumeJob(new JobKey(jobName,jobGroup));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(resultDto.successDto("ok"));
    }


}
