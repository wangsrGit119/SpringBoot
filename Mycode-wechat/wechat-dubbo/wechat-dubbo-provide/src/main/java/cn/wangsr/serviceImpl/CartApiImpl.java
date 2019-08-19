package cn.wangsr.serviceImpl;

import cn.wangsr.api.CartApi;
import cn.wangsr.cartInfoDo.CartInformationDo;
import cn.wangsr.mapper.CartInfoManager;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class CartApiImpl implements CartApi {

	 @Autowired
	 CartInfoManager cartInfoManager;
	 
     Logger logger= LoggerFactory.getLogger(GoodsApiImpl.class);

   
    
	@Override
	public String getCartInfoByUserOnlyId(String userOnlyId) {
		List<CartInformationDo> cartInformationDo=cartInfoManager.getMyCartInfoByOnlyId(userOnlyId);
		return JSON.toJSONString(cartInformationDo);
	}

	@Override
	public String addCartInfo(String cartInformation) {
		CartInformationDo cartInformationDo=JSONObject.parseObject(cartInformation, CartInformationDo.class);
		cartInformationDo.setId(UUID.randomUUID().toString());
		int insertResult=cartInfoManager.addInfoToMyCart(cartInformationDo);
		return String.valueOf(insertResult);
	}

	@Override
	public String deleteCartInfo(String userOnlyId, Integer goodsId) {
		int deleteResult= cartInfoManager.deleteInfoFromCart(userOnlyId, goodsId);
		return String.valueOf(deleteResult);
	}

    
}
