package cn.wangsr.security.config;

import cn.wangsr.security.handler.CustomAccessDeniedHandler;
import cn.wangsr.security.handler.SecurityLoginFailHandler;
import cn.wangsr.security.handler.SecurityLoginSuccessHandler;
import cn.wangsr.security.provider.SelfAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author: wjl
 * @description:
 * @time: 2020/1/10 0010 17:21
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {




    /**
     *   自定义authenticationProvider
     */
     @Autowired
     SelfAuthenticationProvider selfAuthenticationProvider;
    /**
     *   自定义 登录成功处理 securityLoginSuccessHandler
     */
     @Autowired
     SecurityLoginSuccessHandler securityLoginSuccessHandler;
    /**
     *   自定义 登录失败处理 securityLoginSuccessHandler
     */
    @Autowired
    SecurityLoginFailHandler securityLoginFailHandler;
    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;




    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(selfAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll().anyRequest().authenticated();
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(securityLoginSuccessHandler)
                .failureHandler(securityLoginFailHandler)
                 .and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
        http.csrf().disable();
    }
}
