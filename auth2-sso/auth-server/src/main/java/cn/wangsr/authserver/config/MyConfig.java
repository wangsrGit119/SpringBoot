package cn.wangsr.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author WJL
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Bean
    public CustomPasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("login");
    }

}
