package cn.wangsr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.wangsr.cartInfoDo.CartInformationDo;

public interface CartInfoManager {
	
	/**
	 * 获取我的购物车信息
	 * @param onlyId 用户唯一id
	 * @return
	 */
	List<CartInformationDo> getMyCartInfoByOnlyId(String onlyId);
	
	/**
	 * 购物车中 添加信息
	 * @param cartInformationDo
	 * @return
	 */
	int addInfoToMyCart(CartInformationDo cartInformationDo);
	
	
	/**
	 * 删除商品
	 * @param userOnlyId
	 * @param goodsId
	 * @return
	 */
	int deleteInfoFromCart(@Param("userOnlyId") String userOnlyId,@Param("goodsId") Integer goodsId);
	
}
