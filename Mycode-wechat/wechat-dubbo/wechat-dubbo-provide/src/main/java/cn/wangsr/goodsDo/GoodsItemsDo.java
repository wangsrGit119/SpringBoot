package cn.wangsr.goodsDo;

import java.io.Serializable;

public class GoodsItemsDo implements Serializable {

	private static final long serialVersionUID = -1629829204682699068L;
	private Integer id;
	private String name;//栏目名称
	private  Integer totalNum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	@Override
	public String toString() {
		return "GoodsItemsDto{" +
				"id=" + id +
				", name='" + name + '\'' +
				", totalNum=" + totalNum +
				'}';
	}
}
