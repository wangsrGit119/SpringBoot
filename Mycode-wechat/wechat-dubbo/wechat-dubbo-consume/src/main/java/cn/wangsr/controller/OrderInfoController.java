package cn.wangsr.controller;



import cn.wangsr.socket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.wangsr.api.OrderInfoApi;
import cn.wangsr.orderInfoDto.OrderInfoDto;
import resultCode.ResultDto;

@RestController
@RequestMapping("/wechatApi")
public class OrderInfoController {
	
	
	
	@Reference(version="1.0.0")
	OrderInfoApi orderInfoApi;

	@Autowired
	WebSocket webSocket;
	
	Logger logger=LoggerFactory.getLogger(OrderInfoController.class);
	ResultDto<Object> resultDto=null;
	

	@PostMapping("/addOrderInfo")
	public String addOrderInfo(@RequestParam("orderInfo") String orderInfo) {
		resultDto=new ResultDto<>();
		logger.info("addOrderInfo"+orderInfo);
		OrderInfoDto orderInfoDto=JSONObject.parseObject(orderInfo,OrderInfoDto.class);
		if(orderInfoDto.getUserOnlyId()==null || orderInfoDto.getOrderDetail()==null) {
			return JSON.toJSONString(resultDto.failedDto("参数不能为空"));
		}
		String addResult=orderInfoApi.addOrderInfo(orderInfo);
		webSocket.sendMessage("您有新的订单");
		return JSON.toJSONString(resultDto.successDto("添加成功"+addResult));
		
	}
	
	@GetMapping("/getOrderInfo")
	public String getMyOrderInfoByOnlyId(@RequestParam("userOnlyId") String userOnlyId) {
		resultDto=new ResultDto<>();
		if(userOnlyId==null) {
			return JSON.toJSONString(resultDto.failedDto("参数不能为空"));
		}
		
		String orderInfo= orderInfoApi.getOrderInfoByUserOnlyId(userOnlyId);
		logger.info("getOrderInfo:"+orderInfo);
		return JSON.toJSONString(resultDto.successDto(orderInfo));
	}

}
