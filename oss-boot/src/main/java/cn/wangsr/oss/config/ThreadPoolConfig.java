package cn.wangsr.oss.config;


import cn.wangsr.oss.exception.AsyncException;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wjl
 */
@Configuration
@EnableAsync
@Slf4j
public class ThreadPoolConfig implements AsyncConfigurer {
    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {

        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setThreadNamePrefix("cebon-scjg-");
        //阻塞队列大小设置
        pool.setQueueCapacity(300);
        pool.setKeepAliveSeconds(60);
        // 线程池维护线程的最少数量
        pool.setCorePoolSize(6);
        // 线程池维护线程的最大数量
        pool.setMaxPoolSize(200);
        // 当调度器shutdown被调用时等待当前被调度的任务完成
        pool.setWaitForTasksToCompleteOnShutdown(true);
        //对拒绝task的处理策略
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SpringAsyncExceptionHandler();
    }

    class SpringAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
            log.info("异步任务发生异常, 请求参数 :  " + JSONArray.toJSONString(obj));

            if (throwable instanceof AsyncException) {
                AsyncException asyncException = (AsyncException) throwable;
                log.info("asyncException:"  + asyncException.getMsg());
            }
            log.error("写入任务异常Exception :", throwable);
        }
    }


}

