package cn.wangsr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import cn.wangsr.entits.Role;
import cn.wangsr.entits.Sys_User;



@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		if (username==null || username.equals("")) {
			System.out.println("用户名为空"+username);
			return null;
		}
		Sys_User user=userService.getUserByName(username);
		 List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
         for (Role role : user.getRoles()) {
         	  authorities.add(new SimpleGrantedAuthority(role.getRolename()));
         	  System.out.println("拥有的角色:"+role.getRolename());
			}
		return  new User(user.getUsername(), user.getPassword(), authorities);
	}

}
