package cn.wangsr.cartDto;

import java.io.Serializable;

public class CartInformationDto implements Serializable {

	private static final long serialVersionUID = 5891526646812063928L;

	private String id;
	
	private String onlyId;
	
	private Integer goodsId;
	
	private Integer goodsCount;

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOnlyId() {
		return onlyId;
	}

	public void setOnlyId(String onlyId) {
		this.onlyId = onlyId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	@Override
	public String toString() {
		return "CartInformationDo [id=" + id + ", onlyId=" + onlyId + ", goodsId=" + goodsId + ", goodsCount="
				+ goodsCount + "]";
	}
	
	
	
	
}
