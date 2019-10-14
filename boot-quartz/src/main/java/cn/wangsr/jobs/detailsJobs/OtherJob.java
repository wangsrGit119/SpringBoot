package cn.wangsr.jobs.detailsJobs;

import cn.wangsr.jobs.BaseJob;
import com.alibaba.fastjson.JSON;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: wjl
 * @description:
 * @time: 2019/9/24 0024 14:25
 */
@DisallowConcurrentExecution
public class OtherJob implements BaseJob {
    private static Logger logger= LoggerFactory.getLogger(OtherJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getMergedJobDataMap();
        logger.info("Running Job details : {} ", JSON.toJSONString(map));

    }
}
