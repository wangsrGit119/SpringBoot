package cn.itcast.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.itcast.component.LoginHandlerInterceptor;
import cn.itcast.component.MyLocaleResolver;


//使用webmvcconfigureAdapter可以来扩展SpringMvc的功能
//如果要全面接管springmvc  就用--@EnableWebMvc

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
	/*@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//registry.addViewController("/cn.itcast").setViewName("login");
	}*/
	
	
	@SuppressWarnings("deprecation")
	@Bean
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
		WebMvcConfigurerAdapter adapter=new WebMvcConfigurerAdapter() {

			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				
				registry.addViewController("/login.html").setViewName("login");
				registry.addViewController("/").setViewName("login");
				//重定向所需要的结果视图及其映射路径
				registry.addViewController("/main.html").setViewName("dashboard");
			}
			
			/**
			 * 
			 *注册拦截器:一个添加拦截路径  一个排除拦截路径
			 *对于静态资源，springboot已经做好了静态资源路径映射  无需再管
			 *为了防止首页乱码  需要将一些静态文件加载出来 因此需要放行
			 */
			
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				//registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
				//.excludePathPatterns("/login.html","/","/user/login","/**/*.css", "/**/*.js", "/**/*.svg");
			}
		};
		return adapter; 
	}
	
	

	@Bean
	public LocaleResolver localeResolver() {
		return new MyLocaleResolver();
	}
	
}
