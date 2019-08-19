package cn.wangsr.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.wangsr.api.OrderInfoApi;
import cn.wangsr.mapper.OrderInfoManager;
import cn.wangsr.orderInfoDo.OrderInfoDo;
import cn.wangsr.utils.GenerateOrderSn;

@Service(version="1.0.0")
public class OrderInfoApiImpl implements OrderInfoApi {

	@Autowired
	OrderInfoManager orderInfoManager;
	
	@Override
	public String addOrderInfo(String orderInfo) {
		
		OrderInfoDo orderInfoDo=JSONObject.parseObject(orderInfo, OrderInfoDo.class);
		orderInfoDo.setId(GenerateOrderSn.generateOrderSnByTime());
		orderInfoDo.setOrderStatus((byte) 0);//初始状态都为0  
		int inserResult=orderInfoManager.insertOrderInfo(orderInfoDo);
		return String.valueOf(inserResult);
	}

	@Override
	public String getOrderInfoByUserOnlyId(String userOnlyId) {
		List<OrderInfoDo> lInfoDos= orderInfoManager.getOrderInfoByOnlyId(userOnlyId);
		return JSON.toJSONString(lInfoDos);
	}

}
