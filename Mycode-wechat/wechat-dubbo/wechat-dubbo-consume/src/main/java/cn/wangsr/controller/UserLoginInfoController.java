package cn.wangsr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;

import cn.wangsr.api.UserloginApi;
import resultCode.ResultDto;

@RestController
@RequestMapping("wechatApi/user")
public class UserLoginInfoController {
	
	@Reference(version="1.0.0")
	UserloginApi userloginApi;
	
	ResultDto<Object> resultDto=null;
	
	private Logger logger=LoggerFactory.getLogger(UserLoginInfoController.class);

	@PostMapping("/getCodeWithUserInfo")
	public String userCheckByCode(@RequestParam String code,@RequestParam String userInfo) {
		resultDto=new ResultDto<>();
		if(code ==null || userInfo==null) {
			return JSON.toJSONString(resultDto.failedDto(("参数不能为空")));
		}
		String resultInfo=userloginApi.userCheckByCode(code,userInfo);
		logger.info("consume:"+resultInfo);
		return JSON.toJSONString(resultDto.successDto(resultInfo));
	}
	
	@PostMapping("/editMyInfo")
	public String updataMyInfo(@RequestParam String userInfo) {
		resultDto=new ResultDto<>();
		String result=userloginApi.editMyInfo(userInfo);
		return JSON.toJSONString(resultDto.successDto("修改成功:"+result));
	}
	

}
