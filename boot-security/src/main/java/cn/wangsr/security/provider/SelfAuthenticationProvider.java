package cn.wangsr.security.provider;

import cn.wangsr.security.service.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * @author: wjl
 * @description: 自定义provider   内置为DaoAuthenticationProvider
 * @time: 2020/1/14 0014 13:37
 */
@Component
public class SelfAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private Logger logger = LoggerFactory.getLogger(SelfAuthenticationProvider.class);

    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        logger.info("retrieveUser...");
        //用户存在查询
        UserDetails loadedUser = this.myUserDetailsService.loadUserByUsername(username);
        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;
    }


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();
        logger.info("additionalAuthenticationChecks...");
        //密码校验处理
        if (authentication.getCredentials() == null) {
            logger.info("Authentication failed: no password provided");
            throw new BadCredentialsException(messages.getMessage(
                    "10001",
                    "密码为空"));
        }
        String presentedPassword = authentication.getCredentials().toString();
        if (!encoder.matches(presentedPassword, userDetails.getPassword())) {
            logger.info("Authentication failed: password does not match stored value");
            throw new BadCredentialsException(messages.getMessage(
                    "10002",
                    "error password"));
        }

    }



}
