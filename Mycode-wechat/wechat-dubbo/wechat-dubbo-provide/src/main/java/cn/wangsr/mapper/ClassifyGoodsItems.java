package cn.wangsr.mapper;

import java.util.List;

import cn.wangsr.goodsDo.GoodsItemsDo;

public interface ClassifyGoodsItems {
	
	/**
	 * 获取所有的分类列表
	 * @return
	 */
	List<GoodsItemsDo> getAllGoodsItems(); 
	
	/**
	 * 根据id获取当前分类
	 * @param itemId
	 * @return
	 */
	GoodsItemsDo getGoodsItesDoById(Integer itemId);

}
