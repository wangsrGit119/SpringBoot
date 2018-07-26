package cn.wangsr.entits;

public class Permission {
	private Integer id;
	private String permissionname ;
	private String description;
	
	private String url;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPermissionname() {
		return permissionname;
	}
	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", permissionname=" + permissionname + ", description=" + description + ", url="
				+ url + "]";
	}
	
	
	

}
