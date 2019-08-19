package cn.wangsr.orderInfoDo;

import java.io.Serializable;
import java.util.Date;

public class OrderInfoDo implements Serializable {
	
	private static final long serialVersionUID = 3719384658875322657L;
	private String id;
	private Date orderTime;
	private byte orderStatus;
	private String orderDetail;
	private String userOnlyId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public byte getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(byte orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}
	public String getUserOnlyId() {
		return userOnlyId;
	}
	public void setUserOnlyId(String userOnlyId) {
		this.userOnlyId = userOnlyId;
	}
	@Override
	public String toString() {
		return "OrderInfoDo [id=" + id + ", orderTime=" + orderTime + ", orderStatus=" + orderStatus + ", orderDetail="
				+ orderDetail + ", userOnlyId=" + userOnlyId + "]";
	}
	
	
	
	

}
