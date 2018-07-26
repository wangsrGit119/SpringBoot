package cn.wangsr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wangsr.entits.Sys_User;
import cn.wangsr.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;
	
	public  Sys_User getUserByName(String username) {
		
		return userMapper.selectUserByName(username);
	}
	
}
