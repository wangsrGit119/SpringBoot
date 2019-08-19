package cn.wangsr.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import cn.wangsr.goodsDo.GoodsDo;

public interface ClassifyGoodsInfo {
	
	/**
	 * 根据分类id获取当前列表所有商品
	 * @param itemId
	 * @return
	 */
	
	List<GoodsDo> getGoodsInfoByItemId(@Param("itemId")Integer itemId);
	
	
	/**
	 * 根据id查询商品信息
	 * @param id
	 * @return
	 */
	GoodsDo getGoodsInfoById(@Param("id")Integer id);
	
	/**
	 * 根据关键词查询商品
	 * @param keys
	 * @return
	 */
	
	List<GoodsDo> getGoodsInfoByKeys(@Param("keys")String keys);

}
