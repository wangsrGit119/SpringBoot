package cn.wangsr.adminOrderDto;

import java.io.Serializable;
import java.util.Date;

public class AdminOrderInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String orderNum; //订单编号
    private String orderDetail;//订单详情
    private byte orderStatus;//订单状态    0等待接单   1 接单 交易成功  2 拒单 交易失败
    private Date orderTime ;//订单时间
    private String receiptAddress; //收货地址
    private String openId;  //用户id
    private String nickName; //用户名称
    private String userAddress; //用户地址

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    public byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "AdminOrderInfoDto{" +
                "orderNum='" + orderNum + '\'' +
                ", orderDetail='" + orderDetail + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderTime=" + orderTime +
                ", receiptAddress='" + receiptAddress + '\'' +
                ", openId='" + openId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
