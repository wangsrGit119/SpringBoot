package cn.wangsr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import cn.wangsr.service.MyUserDetailService;
import cn.wangsr.utils.MD5Util;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	
  
	
	/**
	 * 这个地方的注解必须加上@Bean
	 */
	@Bean
	protected UserDetailsService userDetailsService() {

		return new MyUserDetailService();
	}

	//认证
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		
		auth.userDetailsService(userDetailsService()).passwordEncoder(new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				
				return encodedPassword.equals(encode(rawPassword));
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				return MD5Util.encode((String) rawPassword);
			}
		});
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/").permitAll()
        .antMatchers("/login").permitAll();
		
		//开启自动配置的登录功能
		http.formLogin().usernameParameter("username").passwordParameter("password")
        .loginPage("/login").defaultSuccessUrl("/home");
		
		//开启自动配置的注销功能。
        http.logout().logoutSuccessUrl("/");//注销成功以后来到首页
        //1、访问 /logout 表示用户注销，清空session
        //2、注销成功会返回 /login?logout 页面；
		

        //开启记住我功能
        http.rememberMe().rememberMeParameter("rememberme");
        //登陆成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
        //点击注销会删除cookie
		
		
	}

	
	
	
	
}
