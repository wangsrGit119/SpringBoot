package cn.wangsr.api;

public interface OrderInfoApi {
	
	/**
	 * 添加订单信息
	 * @param orderInfo
	 * @return
	 */
	String  addOrderInfo(String orderInfo);
	
	
	/**
	 * 获取订单信息
	 * @param userOnlyId
	 * @return
	 */
	String getOrderInfoByUserOnlyId(String userOnlyId);

}
