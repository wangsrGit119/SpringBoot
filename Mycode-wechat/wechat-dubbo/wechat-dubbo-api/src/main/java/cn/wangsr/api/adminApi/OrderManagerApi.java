package cn.wangsr.api.adminApi;

import java.util.List;

public interface OrderManagerApi {


    /**
     * 关键词获取订单信息  关键词包含时间范围查询   用户名关键字查询    以及订单状态
     * @param queryInfo
     * @return
     */
    String getOrderByKeys(String queryInfo);

    /**
     * 处理订单 接单 或者 拒单
     * @param orderNums
     *  @param status
     * @return
     */
    String reviewedOrder(List orderNums,byte status);


}
