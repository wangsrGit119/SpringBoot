package cn.wangsr.bootquartz;

import cn.wangsr.service.impl.JobServiceImpl;
import cn.wangsr.service.sys.SystemInfoService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BootQuartzApplicationTests {
    @Autowired
    JobServiceImpl jobService;

    @Autowired
    SystemInfoService systemInfoService;

    @Test
    public void contextLoads() {


    }

    @Test
    public void contextLoadsJob01() {

        JobKey jobKey = JobKey.jobKey("HelloWorld", "1");
        Map map = new HashMap();
        map.put("jarPath", "D:\\myProject\\monitor-data-serve\\target\\monitor-data-serve-0.0.1-SNAPSHOT.jar");
//        map.put("parameter","-javaagent:D:\\迅雷下载\\xrebel-4.0.1\\xrebel\\xrebel.jar");
//        map.put("vmParam","-XX:+PrintGCDetails");
        JobDataMap jobDataMap = new JobDataMap(map);


    }

    @Test
    public void getSsh() {
        systemInfoService.getSSHLinuxInfo();


    }

}
