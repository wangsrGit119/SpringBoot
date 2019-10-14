package cn.wangsr.entity.JobDto;



import java.io.Serializable;
import java.util.Map;

/**
 * @author: wjl
 * @description:
 * @time: 2019/9/24 0024 14:33
 */
public class JobInfoDto  implements  Serializable {
    private static final long serialVersionUID =1L;
    private String jobName;
    private String jobGroup;
    private String description;

    private String cronExpression;

    private String interval;  //次数
    private Integer type;  //类型  0---cron  1---simple
    private String startTime; //起时间

    /**
     * 定时任务 的 所需数据（包括前端参数）
     */
    private Map<?, ?> jobDataMap;

    /**
     * 定时任务 的 具体执行逻辑类
     */
    private String jobClass;

    public JobInfoDto() {
        super();
    }

    public JobInfoDto(String jobName, String jobGroup, String description, String cronExpression, String interval, Integer type, String startTime, Map<?, ?> jobDataMap, String jobClass) {
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.description = description;
        this.cronExpression = cronExpression;
        this.interval = interval;
        this.type = type;
        this.startTime = startTime;
        this.jobDataMap = jobDataMap;
        this.jobClass = jobClass;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
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

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "JobInfoDto{" +
                "jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", description='" + description + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", interVal='" + interval + '\'' +
                ", type=" + type +
                ", startTime='" + startTime + '\'' +
                ", jobDataMap=" + jobDataMap +
                ", jobClass='" + jobClass + '\'' +
                '}';
    }
}
