package cn.wangsr.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author: wjl
 * @description: DB操作
 * @time: 2020/1/13 0013 10:10
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.equals("")) {
            return null;
        }
        if(!"wjl".equals(username)){
            return null;
        }
        return User.builder().username("wjl").password("123456").authorities(new ArrayList<>()).build();
    }
}
