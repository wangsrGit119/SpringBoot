package cn.wangsr.authserver.service;

import cn.wangsr.authserver.config.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;

    private List<User> userList;


    @PostConstruct
    public void initData() {
        String password = customPasswordEncoder.encode("123456");
        userList = new ArrayList<>();
        userList.add(new User("admin", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
        userList.add(new User("wjl", password, AuthorityUtils.commaSeparatedStringToAuthorityList("all")));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> findUserList = userList.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(findUserList)) {
            return findUserList.get(0);
        } else {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

    }
}
