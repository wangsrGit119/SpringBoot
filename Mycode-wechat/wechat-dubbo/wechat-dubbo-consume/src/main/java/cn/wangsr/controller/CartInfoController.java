package cn.wangsr.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.wangsr.cartDto.CartInformationDto;
import cn.wangsr.service.MyCartManagerService;
import resultCode.ResultDto;

@RestController
@RequestMapping("/wechatApi")
public class CartInfoController {
	
	 @Autowired
	 MyCartManagerService myCartManagerService;
	
	 ResultDto<Object> resultDto=null;
	
	 Logger logger=LoggerFactory.getLogger(CartInfoController.class);
	 
	 @RequestMapping("/getCartInfoByOnlyId")
	public String getMyCartInfoByOnlyId(@RequestParam("onlyId")String onlyId) {
		 resultDto=new ResultDto<>();
		 if(onlyId==null) {
			return JSON.toJSONString(resultDto.failedDto("onlyId不能为空"));
		 }
		String goodsList= myCartManagerService.getCartInfoByUserOnlyId(onlyId);
		return JSON.toJSONString(resultDto.successDto(goodsList));
	}
	 
	 
	
	 @PostMapping("/addInfoInMyCart")
	 public String insertIntoCartInfo(@RequestParam String myCartInfo) {
		 resultDto=new ResultDto<>();
		 logger.info("consume params:"+myCartInfo);
	
		 CartInformationDto cartInformationDto= JSONObject.parseObject(myCartInfo, CartInformationDto.class);
		
		 if(cartInformationDto.getGoodsId()==null || cartInformationDto.getOnlyId()==null) {
			 return JSON.toJSONString(resultDto.failedDto("goodsId或onlyId不能为空"));
		 }
		String InsertResult= myCartManagerService.insertIntoCartInfo(myCartInfo);
		return   JSON.toJSONString(resultDto.successDto("添加成功"+InsertResult));
	 }
	 
	 
	 @PostMapping("/deletInfoFromCart")
	 public String deleteInfoFromCart(@RequestParam String userOnlyId,@RequestParam Integer goodsId) {
		 resultDto=new ResultDto<>();
		 if(userOnlyId==null || goodsId==null) {
			 return JSON.toJSONString(resultDto.failedDto("userOnlyId或goodsId不能为空"));
		 }
		 myCartManagerService.deleteInfoFromCart(userOnlyId, goodsId);
		 return JSON.toJSONString(resultDto.successDto("删除成功"));
		 
		 
	 }
	

}
