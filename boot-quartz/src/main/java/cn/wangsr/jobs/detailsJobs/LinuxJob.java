package cn.wangsr.jobs.detailsJobs;

import cn.wangsr.jobs.BaseJob;
import cn.wangsr.socket.WebSocket;
import cn.wangsr.utils.SocketUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: wjl
 * @description:
 * @time: 2019/9/25 0025 10:56
 */
public class LinuxJob implements BaseJob {

    private static Logger logger= LoggerFactory.getLogger(LinuxJob.class);

    @Autowired
    WebSocket webSocket;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalDateTime localDateTime=LocalDateTime.now();

        JobDataMap map = jobExecutionContext.getMergedJobDataMap();
        logger.info("Running Job details : {} ", JSON.toJSONString(map));

        //执行参数获取
        Object shellJson = map.get("shells");
        List<String> shells=JSON.parseObject(JSON.toJSONString(shellJson),new TypeReference<List<String>>(){});
        String executePath = map.getString("executePath");
        String userName = map.getString("userName");

        //执行文件夹    日志文件路径设置
        File executePathDir = new File(executePath);
        String preFile=localDateTime.getYear()+"-"+localDateTime.getMonth().getValue()+"-"+localDateTime.getDayOfMonth()+"-"+System.currentTimeMillis();
        String trueFileLogPath=executePath+"//"+preFile+"-"+userName+"-execute.log";
        File executePathLog = new File(trueFileLogPath);

        //后期加权限 指定非法目录
        if(!executePathDir.exists()) {
            throw new JobExecutionException("路径不存在  " );
        }

        ProcessBuilder processBuilder = new ProcessBuilder();
        //环境变量
        Map mapEn= processBuilder.environment();
        logger.info("environment()  {}",JSON.toJSONString(mapEn));
        //工作目录
        processBuilder.directory(executePathDir);
        List<String> commands = new ArrayList<>();

        if (shells != null)
            shells.forEach(shell->commands.add(shell));
        processBuilder.redirectErrorStream(true);//如果将值设置为 true，标准错误将与标准输出合并
        processBuilder.redirectOutput(ProcessBuilder.Redirect.appendTo(executePathLog));//日志重定向
        try {
            logger.info("执行命令 : {} ,日志输出文件夹:   {} ", commands,executePath);
            processBuilder.command(commands);
            Process process = processBuilder.start();
            process.waitFor();//一直挂起直至子进程结束
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }finally {
            //socket 日志名称及其路径同步至前段
            Map mapLog=new HashMap();
            mapLog.put("trueFileLogPath",trueFileLogPath);
            mapLog.put("fileName",executePathLog.getName());
            webSocket.sendMessageOneToOne(JSON.toJSONString(mapLog),userName);
        }

    }

}
