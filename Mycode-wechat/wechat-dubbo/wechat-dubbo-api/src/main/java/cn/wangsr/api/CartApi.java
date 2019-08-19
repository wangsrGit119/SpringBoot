package cn.wangsr.api;


/**
 *
 * @author wjl
 *  获取购物车信息
 * @param <T>
 */
public interface CartApi {

	/**
	 * 获取购物车信息
	 * @param userId
	 * @return
	 */
     String getCartInfoByUserOnlyId(String userOnlyId);

    /**
     * 购物车添加信息  
     * @param cartInformation
     * @return
     */
    String addCartInfo(String  cartInformation);
    
    
    /**
     * 删除当前用户的购物车某个商品
     * @param userOnlyId
     * @param goodsId
     * @return
     */
    
    String deleteCartInfo(String userOnlyId,Integer goodsId);
    
}
