package cn.wangsr.mapper;

import org.apache.ibatis.annotations.Param;

import cn.wangsr.userDo.WechatUserInfoDo;

public interface UserInfoManager {
	/**
	 * 添加  微信用户信息
	 */
	int addUserInfo(WechatUserInfoDo wechatUserInfoDo);
	
	
	
	/**
	 * 根据openId判断用户是否已经存在
	 */
	
	WechatUserInfoDo getUserInfoByOpneId(@Param("openId")String openId); 
	
	/**
	 * 更新用户信息（地址   钱包  红包信息  其余的不更新）
	 * @param wechatUserInfoDo
	 * @return
	 */
	int updateUserInfo(WechatUserInfoDo wechatUserInfoDo);

}
