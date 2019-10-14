package cn.wangsr.schedule;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @Description
 * @author wjl
 * @date 2019/9/27 0027
 * @param
 * @return
 */
@Component
public class MapReduceFileSchedule {


    private static Logger logger= LoggerFactory.getLogger(MapReduceFileSchedule.class);





    /**
     * @Description  发送系统信息
     * @author wjl
     * @date 2019/9/27 0027
     * @param []
     * @return void
     */
//    @Scheduled(cron = "10 * * * * ?")
    public void sendSystemInfo() {


    }

}
