package cn.wangsr.config;





import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
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
		auth.userDetailsService(userDetailsService()).passwordEncoder(new  PasswordEncoder() {
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				//匹配
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
		
		http.authorizeRequests()
		.antMatchers("/").permitAll()
        .antMatchers("/login").permitAll()
        //.antMatchers("/register").permitAll()
        .anyRequest().authenticated(); //任何请求,登录后可以访问
		
		//开启自动配置的登录功能
		http.formLogin()
		.usernameParameter("username")
		.passwordParameter("password")
        .loginPage("/login")
        .defaultSuccessUrl("/index", true);
        //.successHandler(new ForwardAuthenticationSuccessHandler("/index?status=true"));// 强制指定登陆成功后跳转的路径
       
		//开启自动配置的注销功能。
        http.logout().permitAll();
        //session管理
        http 
        .sessionManagement()
        .maximumSessions(-1).maxSessionsPreventsLogin(true)
        .sessionRegistry(getSessionRegistry());
        //开启X-Frame-Options  DENY、SAMEORIGIN、ALLOW-FROM http://。。。。/
        http.headers().frameOptions().sameOrigin();
        http.rememberMe().rememberMeParameter("remember");
        //登陆成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
        //点击注销会删除cookie
		http.csrf().disable();//关闭  ----和restful风格实现有冲突=注意这个如果关闭的话自己的注销功能会不起作用
		
	}

	
	
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/lib/**");
	}

	@Bean
	public SessionRegistry getSessionRegistry() {
		
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
	}

	
	
	
	
}
