package cn.wangsr.api;

/**
 *  用户登录api
 * @author wjl
 *
 */
public interface UserloginApi {
	
	/**
	 * 根据用户登录生成的code 交换登录信息  验证成功 用户信息则存入表中
	 * @param code
	 * @return
	 */
	String  userCheckByCode(String code,String userInfo);
	
	
	/**
	 * 用户信息更改
	 * @param newMyInFo
	 * @return
	 */
	String editMyInfo(String newMyInFo);

}
