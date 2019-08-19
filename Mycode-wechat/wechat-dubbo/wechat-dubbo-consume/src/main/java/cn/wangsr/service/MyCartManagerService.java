package cn.wangsr.service;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.wangsr.api.CartApi;
import cn.wangsr.api.GoodsApi;
import cn.wangsr.cartDto.CartInformationDto;
import cn.wangsr.goodsDto.GoodsDto;

@Service
public class MyCartManagerService {
	
	@Reference(version="1.0.0")
	CartApi cartApi;
	
	@Reference(version="1.0.0")
	GoodsApi goodsApi;
	
	Logger logger=LoggerFactory.getLogger(MyCartManagerService.class);
	
	/**
	 * 查看当前用户的购物车信息
	 * @param userOnlyId
	 * @return
	 */
	
	public String getCartInfoByUserOnlyId(String userOnlyId) {
		List<GoodsDto> goodsList=new ArrayList<>();
		String cartInfo=  cartApi.getCartInfoByUserOnlyId(userOnlyId);
		List<CartInformationDto> cartInformationDtos= JSONObject.parseArray(cartInfo, CartInformationDto.class);
		
		cartInformationDtos.forEach(x->{
		  Integer goodsId=x.getGoodsId();
		  String goodsInfo=goodsApi.getGoodsInfoByGoodsId(goodsId);
		  String  replacement=",'goodsCount':"+x.getGoodsCount()+"}"; //结果集里面追加单个商品的购物数量
		  goodsInfo= goodsInfo.replace("}", replacement);
		  logger.info(goodsInfo);
		  GoodsDto goodsDto=JSONObject.parseObject(goodsInfo, GoodsDto.class);
		  goodsList.add(goodsDto);
		});
		
		return JSON.toJSONString(goodsList);
	}
	
	
	/**
	 * 购物车插入信息
	 * @param cartInfo
	 * @return
	 */
	public String insertIntoCartInfo(String cartInfo) {
		String insertResult=cartApi.addCartInfo(cartInfo);
		return String.valueOf(insertResult);
	}
	
	/**
	 * 删除信息
	 * @param userOnlyId
	 * @param goodsId
	 * @return
	 */
	public String deleteInfoFromCart(String userOnlyId,Integer goodsId) {
		String deleteResult=cartApi.deleteCartInfo(userOnlyId, goodsId);
		return deleteResult;
	}

}
