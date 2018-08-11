package cn.wangsr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wangsr.entits.Sys_User;
import cn.wangsr.entits.User_Role;
import cn.wangsr.mapper.UserMapper;
import cn.wangsr.mapper.UserroleMapper;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	UserroleMapper user_RoleMapper;
	/**
	 * 
	 * @param username
	 * @return
	 */
	public  Sys_User getUserByName(String username) {
		
		return userMapper.selectUserByName(username);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Sys_User getUserById(Integer id) {
		
		return userMapper.selectUserById(id);
		
	}
	
	/**
	 * 得到所有的用户信息
	 * @return
	 */
	
	public List<Sys_User> getAllUser() {
		
		return  userMapper.getAllUser();
		
	}
	/**
	 * 分页  无参数 分步查询  解决多对多分页出错
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Sys_User> getAllPageUser() {
		
		return  userMapper.getAllPageUser();
		
	}
	
	

	/**
	 * 删除用户信息
	 * @param id
	 */
	public void deleteUser(int id) {
		userMapper.deleteUserById(id);
	}

	/**
	 * 用户注册
	 * @param user
	 */
	
	public void regesiterUser(Sys_User user) {
		userMapper.insertUser(user);
	}

	/**
	 * 用户数据修改
	 * @param user
	 */
	
	public void updateUser(Sys_User user) {
		userMapper.updateUser(user);
	}

	/**
	 * 为用户添加角色
	 * @param user_Role
	 */
	public void addUser_Role(User_Role user_Role) {
		user_RoleMapper.insertUser_role(user_Role);
		
	}
	
	/**
	 * 根据用户id删除该用户所有的角色
	 * @param user_id
	 */
	public void deleteUser_Role(int u_id) {
		user_RoleMapper.deleteUser_role(u_id);
	}
	
	
}
