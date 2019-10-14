package cn.wangsr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: wjl
 * @description:
 * @time: 2019/9/27 0027 09:31
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("views/login.html");
        registry.addViewController("/").setViewName("views/login.html");
        registry.addViewController("/index").setViewName("views/index.html");
        registry.addViewController("/jobManager").setViewName("views/jobManager.html");
        registry.addViewController("/systemMonitor").setViewName("views/systemMonitor.html");
        registry.addViewController("/releaseTask").setViewName("views/releaseTask.html");
        registry.addViewController("/logsCenter").setViewName("views/logsCenter.html");
        registry.addViewController("/manageCenter").setViewName("views/manageCenter.html");

    }

}
