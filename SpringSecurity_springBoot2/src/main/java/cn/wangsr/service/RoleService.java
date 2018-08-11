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
	
	/**
	 * 得到所有的角色
	 * @return
	 */
	public List<Role> getAllRoles() {
		return roleMapper.selectAllRole();
	}
	
	public Role getAllPsByRoname(String rolename) {
		return roleMapper.selectAllPsByRole(rolename);
	}

	
	/**
	 * 添加角色
	 * @param role
	 */
	public void addRole(Role role) {
		// TODO Auto-generated method stub
		roleMapper.insertRole(role);
		
	}
	
}
