package cn.wangsr.mapper;

import java.util.List;


import cn.wangsr.entits.Permission;



public interface PermissionMapper {

	public int  insertPer(Permission permission);
	public int deletePerById(Integer id);
	public int updateper(Permission permission);
	public List<Permission> selectAllper();
	public List<Permission> getPerByUserId(int id);
	
	
}
