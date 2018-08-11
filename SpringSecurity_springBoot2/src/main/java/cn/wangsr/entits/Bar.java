package cn.wangsr.entits;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Bar implements Serializable {

	/**
	 * 栏目信息
	 * 
	 * 在初始登录加载出来  及其权限控制
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	private  long  id;
	private String barName;
	private String description;
	private Role role;
	private Set<Bar_list> lists=new HashSet<>();

	
	
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBarName() {
		return barName;
	}

	public void setBarName(String barName) {
		this.barName = barName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public Set<Bar_list> getLists() {
		return lists;
	}

	public void setLists(Set<Bar_list> lists) {
		this.lists = lists;
	}

	@Override
	public String toString() {
		return "Bar [id=" + id + ", barName=" + barName + ", description=" + description + ", role=" + role + ", lists="
				+ lists + "]";
	}

	
	
	
	
	
	
	

}
