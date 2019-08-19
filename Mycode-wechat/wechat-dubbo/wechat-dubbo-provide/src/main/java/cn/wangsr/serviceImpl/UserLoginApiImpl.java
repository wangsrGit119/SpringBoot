package cn.wangsr.serviceImpl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.wangsr.api.UserloginApi;
import cn.wangsr.mapper.UserInfoManager;
import cn.wangsr.userDo.WechatUserCheckResultDo;
import cn.wangsr.userDo.WechatUserInfoDo;

@Service(version="1.0.0")
public class UserLoginApiImpl implements UserloginApi {

   

	private static final String APPID="wx98e9bd4331328790";
	private static final String SECRET="ed247ab3badac7073b4dae53ae316114";
	private static final String GRANT_TYPE="authorization_code";
	private RestTemplate restTemplate=null;
	private Logger logger=LoggerFactory.getLogger(UserLoginApiImpl.class);
	
	@Autowired
	UserInfoManager userInfoManager;
	
	WechatUserInfoDo wechatUserInfoDo=null;
	/**
	 * 微信接口信息交换
	 */
	@Override
	public String userCheckByCode(String code,String userInfo) {
		
		ResponseEntity<String> responseEntity=null;
		String responseInfo=null;
		wechatUserInfoDo=JSONObject.parseObject(userInfo, WechatUserInfoDo.class);
		String url="https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+"&secret="+SECRET+"&js_code="+code+"&grant_type="+GRANT_TYPE;
		restTemplate=new RestTemplate();
		//校验接口可能超时 
		try {
			responseEntity=restTemplate.exchange(url, HttpMethod.GET, null, String.class);
			 responseInfo=responseEntity.getBody();
		} catch (Exception e) {
			return e.getMessage();
		}
		
		//检验失败  直接返回失败结果
		if(responseInfo.contains("errcode")) {
			return responseInfo;
		}
		
		//成功  储存用户信息  
		
		//将校验成功结果转为对象
		WechatUserCheckResultDo wechatUserCheckResultDo=JSONObject.parseObject(responseInfo,WechatUserCheckResultDo.class);
		//判断是否是老用户
		WechatUserInfoDo queryUserInfoDo=userInfoManager.getUserInfoByOpneId(wechatUserCheckResultDo.getOpenid());
		//老用户直接返回用户信息
		if(queryUserInfoDo!=null) {
			logger.info("老用户queryUserInfoDo："+JSON.toJSONString(queryUserInfoDo));
			return JSON.toJSONString(queryUserInfoDo);
			
		}
		
		//新用户添加到表中
		wechatUserInfoDo.setOpenId(wechatUserCheckResultDo.getOpenid());
		userInfoManager.addUserInfo(wechatUserInfoDo);
		logger.info("新用户wechatUserInfoDo："+wechatUserInfoDo.toString());
			return JSON.toJSONString(wechatUserInfoDo);
		}
	
	@Override
	public String editMyInfo(String newMyInFo) {
		WechatUserInfoDo wechatUserInfoDo=JSONObject.parseObject(newMyInFo, WechatUserInfoDo.class);
		int upResult=userInfoManager.updateUserInfo(wechatUserInfoDo);
		return String.valueOf(upResult);
	}
	
	

}
