package cn.wangsr.entits;

import java.io.Serializable;

public class Bar_list implements Serializable {

	/**
	 * 子栏目信息
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private long id;
	private String barListName;
	private String description;
	private String url;
	private Integer bar_id;
	
	
	public Integer getBar_id() {
		return bar_id;
	}
	public void setBar_id(Integer bar_id) {
		this.bar_id = bar_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBarListName() {
		return barListName;
	}
	public void setBarListName(String barListName) {
		this.barListName = barListName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Bar_list [id=" + id + ", barListName=" + barListName + ", description=" + description + ", url=" + url
				+ ", bar_id=" + bar_id + "]";
	}
	
	
	
	
	
	
	

}
