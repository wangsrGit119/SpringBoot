package cn.wangsr.jobs.detailsJobs;

import cn.wangsr.jobs.BaseJob;
import com.alibaba.fastjson.JSON;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: wjl
 * @description:
 * @time: 2019/9/24 0024 14:22
 */
@DisallowConcurrentExecution //禁止并发执行
public class JarJob implements BaseJob {

    private static Logger logger= LoggerFactory.getLogger(BaseJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getMergedJobDataMap();
        logger.info("Running Job details : {} ", JSON.toJSONString(map));
        String jarPath = map.getString("jarPath");
        String parameter = map.getString("parameter");
        String vmParam = map.getString("vmParam");
        long startTime = System.currentTimeMillis();
        if (!StringUtils.isEmpty(jarPath)) {
            File jar = new File(jarPath);
            if (jar.exists()) {
                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.directory(jar.getParentFile());
                List<String> commands = new ArrayList<>();
                commands.add("nohup");
                commands.add("java");
                if (!StringUtils.isEmpty(vmParam)) commands.add(vmParam);
                commands.add("-jar");
                commands.add(jarPath);
                if (!StringUtils.isEmpty(parameter)) commands.add(parameter);
                processBuilder.command(commands);
                logger.info("执行命令 : {}  ", commands);
                try {
                    Process process = processBuilder.start();
                    InputStreamReader inputStreamReader =new InputStreamReader(process.getInputStream(),"utf-8");
                    BufferedReader br =new BufferedReader(inputStreamReader);
                    while ((br.read())!=-1) {
                        logger.info(br.readLine());
                    }
                    process.waitFor();
                } catch (Exception e) {
                    throw new JobExecutionException(e);
                }
            } else throw new JobExecutionException("jar包不存在  " + jarPath);
        }
        long endTime = System.currentTimeMillis();
        logger.info("总耗时 : {}ms ", (endTime - startTime));
    }
}
