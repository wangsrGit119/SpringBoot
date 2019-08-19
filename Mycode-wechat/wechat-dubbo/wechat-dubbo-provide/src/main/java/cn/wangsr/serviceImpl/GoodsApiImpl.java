package cn.wangsr.serviceImpl;

import cn.wangsr.api.GoodsApi;
import cn.wangsr.goodsDo.GoodsDo;
import cn.wangsr.goodsDo.GoodsItemsDo;
import cn.wangsr.mapper.ClassifyGoodsInfo;
import cn.wangsr.mapper.ClassifyGoodsItems;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;

@Service(version = "1.0.0")
public class GoodsApiImpl implements GoodsApi {

	
	@Autowired
	ClassifyGoodsInfo classifyGoodsInfo;
	@Autowired
	ClassifyGoodsItems classifyGoodsItems;
	
	Logger logger=LoggerFactory.getLogger(GoodsApiImpl.class);

	@Override
	public String getAllItemsInfo() {
		List<GoodsItemsDo> list_items= classifyGoodsItems.getAllGoodsItems();
		return JSON.toJSONString(list_items);
	}

	@Override
	public String getAllGoodsByItemId(Integer itemId) {
		List<GoodsDo> list_goods= classifyGoodsInfo.getGoodsInfoByItemId(itemId);
		return JSON.toJSONString(list_goods);
	}

	@Override
	public String getGoodsInfoByGoodsId(Integer goodsId) {
		GoodsDo goodsDo=classifyGoodsInfo.getGoodsInfoById(goodsId);
		return JSON.toJSONString(goodsDo);
	}

	@Override
	public String getGoodsListByKeyWords(String keyWords) {
	 List<GoodsDo>	 listGoods=classifyGoodsInfo.getGoodsInfoByKeys(keyWords);
		return JSON.toJSONString(listGoods);
	}
	
	

  
	
	
	
}
