package cn.wangsr.userDo;

import java.io.Serializable;

public class WechatUserCheckResultDo implements Serializable {
	
	
	private static final long serialVersionUID = 4380947258269595277L;
	private String session_key;
	private String openid;
	public String getSession_key() {
		return session_key;
	}
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	
	

}
