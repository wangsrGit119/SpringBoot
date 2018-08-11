package cn.wangsr.mapper;



import java.util.List;

import cn.wangsr.entits.Sys_User;



public interface UserMapper {
	
	public Sys_User selectUserByName(String username);
	
	public Sys_User selectUserById(Integer id);
	
	public List<Sys_User> getAllUser();
	
	public List<Sys_User> getAllPageUser();
	
	public int insertUser(Sys_User user);
	
	public int deleteUserById(Integer id);
	
	public int updateUser(Sys_User user);
	

}
