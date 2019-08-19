package cn.wangsr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.wangsr.orderInfoDo.OrderInfoDo;

public interface OrderInfoManager {
	
	/**
	 * 添加订单信息
	 * @param orderInfoDo
	 * @return
	 */
	int insertOrderInfo(OrderInfoDo orderInfoDo);
	
	/**
	 * 获取所有订单信息
	 * @param userOnlyId
	 * @return
	 */
	List<OrderInfoDo> getOrderInfoByOnlyId(@Param("userOnlyId")String userOnlyId);

}
