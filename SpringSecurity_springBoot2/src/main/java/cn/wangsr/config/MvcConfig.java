package cn.wangsr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer  {
	
	 public void addViewControllers(ViewControllerRegistry registry) {
	        
	       	registry.addViewController("/login").setViewName("login");
	        registry.addViewController("/").setViewName("login");
	        registry.addViewController("/welcome").setViewName("welcome");
	        //registry.addViewController("/register").setViewName("register");//暂时拦截未开放
	   }
}
