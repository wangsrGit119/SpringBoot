package cn.wangsr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.wangsr.entits.Msg;
import cn.wangsr.entits.Role;
import cn.wangsr.entits.Sys_User;



@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserService userService;
	@Autowired
    private SessionRegistry sessionRegistry;
	
    Map<String,Msg> map=new HashMap<String,Msg>();
	
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		if (username == null || username.equals("")) {
			throw new UsernameNotFoundException("用户名不存在");
		}

		Sys_User user = userService.getUserByName(username);
		// 获得所有登录用户的信息
		List<Object> list = sessionRegistry.getAllPrincipals();
		for (Object object : list) {
			if (((User) object).getUsername().equals(user.getUsername())) {
				/*
				Msg mes = new Msg("logininfo", "登录失败", "当前用户已经在线");
				map.put("message", mes);
				throw new SessionAuthenticationException("当前用户已经在线，登录失败");
				*/
			}
			
			System.out.println("getAllPrincipals的遍历" + ((User) object).getUsername());
		}

		// 得到当前登录用户的信息  将得到的角色封装 在后面页面认证成功后会用到
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRolename()));
			System.out.println("拥有的角色:" + role.getRolename());
		}
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

}
