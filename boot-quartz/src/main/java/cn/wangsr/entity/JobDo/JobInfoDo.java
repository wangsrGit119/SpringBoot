package cn.wangsr.entity.JobDo;


import org.quartz.Job;
import org.quartz.JobKey;

import java.util.Map;

/**
 * @author: wjl
 * @description:
 * @time: 2019/9/24 0024 14:33
 */
public class JobInfoDo  {
    private JobKey jobKey;

    private String description;

    private String cronExpression;

    /**
     * 定时任务 的 所需数据（包括前端参数）
     */
    private Map<?, ?> jobDataMap;

    /**
     * 定时任务 的 具体执行逻辑类
     */
    private Class<? extends Job> jobClass;

    public JobInfoDo() {
        super();
    }

    public JobInfoDo(JobKey jobKey, String description, String cronExpression, Map<?, ?> jobDataMap, Class<? extends Job> jobClass) {
        super();
        this.jobKey = jobKey;
        this.description = description;
        this.cronExpression = cronExpression;
        this.jobDataMap = jobDataMap;
        this.jobClass = jobClass;
    }

    public JobKey getJobKey() {
        return jobKey;
    }

    public void setJobKey(JobKey jobKey) {
        this.jobKey = jobKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Map<?, ?> getJobDataMap() {
        return jobDataMap;
    }

    public void setJobDataMap(Map<?, ?> jobDataMap) {
        this.jobDataMap = jobDataMap;
    }

    public Class<? extends Job> getJobClass() {
        return jobClass;
    }

    public void setJobClass(Class<? extends Job> jobClass) {
        this.jobClass = jobClass;
    }

    @Override
    public String toString() {
        return "JobInfoDo{" +
                "jobKey=" + jobKey +
                ", description='" + description + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", jobDataMap=" + jobDataMap +
                ", jobClass=" + jobClass +
                '}';
    }
}
