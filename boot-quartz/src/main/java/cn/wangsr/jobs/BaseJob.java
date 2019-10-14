package cn.wangsr.jobs;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author: wjl
 * @description:
 * @time: 2019/9/24 0024 13:10
 */

public interface BaseJob extends Job {
    void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException;
}
