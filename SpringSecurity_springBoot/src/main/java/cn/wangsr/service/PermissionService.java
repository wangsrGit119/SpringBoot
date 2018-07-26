package cn.wangsr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wangsr.entits.Permission;
import cn.wangsr.mapper.PermissionMapper;

@Service
public class PermissionService {
	@Autowired
	PermissionMapper permissionMapper;
	
	public  List<Permission>  getPerByUserId(int id) {
		
		return permissionMapper.getPerByUserId(id);
	}
	
	

}
