package cn.wangsr.entits;

import java.util.HashSet;
import java.util.Set;

public class Role {
	private Integer id;
	private String rolename;
	
	Set<Permission> permissions=new HashSet<>();
	

	

	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", permissions=" + permissions + "]";
	}
	
	
	
	
	
	
	
	

}
