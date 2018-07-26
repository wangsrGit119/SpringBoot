package cn.wangsr.mapper;

import java.util.List;


import cn.wangsr.entits.Role;



public interface RoleMapper {
	
	public int insertRole(Role role);
	
	public int deleteRoById(Integer id);
	
	public int updateRole(Role role);
	
	public List<Role> selectAllRole();
	
	public Role selectAllPsByRole(String rolename);
	

}
