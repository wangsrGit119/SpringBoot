package cn.wangsr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wangsr.entits.Role;
import cn.wangsr.mapper.RoleMapper;


@Service
public class RoleService {
	@Autowired
	RoleMapper roleMapper;
	
	
	public List<Role> getAllRoles() {
		return roleMapper.selectAllRole();
	}
	
	public Role getAllPsByRoname(String rolename) {
		return roleMapper.selectAllPsByRole(rolename);
	}
	
}
