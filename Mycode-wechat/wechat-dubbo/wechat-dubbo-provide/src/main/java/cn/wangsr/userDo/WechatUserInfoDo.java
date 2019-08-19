package cn.wangsr.userDo;

import java.io.Serializable;
import java.math.BigDecimal;

public class WechatUserInfoDo implements Serializable {
	

	private static final long serialVersionUID = 3498559743858853367L;
	private  int id;
	private String nickname;
	private String country;
	private String province;
	private String city;
	private byte gender;
	private BigDecimal account;  //个人账户
	private BigDecimal redPackets;//红包 
	private String receiptAddress; //收货地址
	private String openId; //用户唯一id
	private String avatarUrl;//头像url
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public byte getGender() {
		return gender;
	}
	public void setGender(byte gender) {
		this.gender = gender;
	}
	public BigDecimal getAccount() {
		return account;
	}
	public void setAccount(BigDecimal account) {
		this.account = account;
	}
	public BigDecimal getRedPackets() {
		return redPackets;
	}
	public void setRedPackets(BigDecimal redPackets) {
		this.redPackets = redPackets;
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
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	@Override
	public String toString() {
		return "WechatUserInfoDo [id=" + id + ", nickname=" + nickname + ", country=" + country + ", province="
				+ province + ", city=" + city + ", gender=" + gender + ", account=" + account + ", redPackets="
				+ redPackets + ", receiptAddress=" + receiptAddress + ", openId=" + openId + ", avatarUrl=" + avatarUrl
				+ "]";
	}


	
	
	
	
	

}
