package cn.wangsr.mapper;



import cn.wangsr.entits.Sys_User;



public interface UserMapper {
	
	public Sys_User selectUserByName(String username);
	
	public int insertUser(Sys_User user);
	
	public int deleteUserById(Integer id);
	
	public int updateUser(Sys_User user);

}
