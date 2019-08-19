package cn.wangsr.api;

/**
 * 
 * @author wjl
 *  获取商品页显示信息
 */


public interface GoodsApi {

	/**
	 * 获取所有分类
	 * @return
	 */
    String getAllItemsInfo();
    
    /**
     * 根据itemId获取该类下面的所有商品
     * @param itemId
     * @return
     */
    String getAllGoodsByItemId(Integer itemId);
    
    /**
     * 根据goodsid获取商品信息
     * @param goodsId
     * @return
     */
    
    String getGoodsInfoByGoodsId(Integer goodsId);
    
    
    /**
     * 根据关键词搜索商品
     * @param keyWords
     * @return
     */
    String getGoodsListByKeyWords(String keyWords);

}
