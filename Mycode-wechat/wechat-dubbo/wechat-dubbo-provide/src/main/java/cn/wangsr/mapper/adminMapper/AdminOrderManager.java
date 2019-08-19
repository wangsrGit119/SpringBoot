package cn.wangsr.mapper.adminMapper;

import cn.wangsr.adminOrderDo.AdminOrderInfoDo;
import cn.wangsr.adminOrderDo.AdminQueryDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminOrderManager {

   /**
    * 关键字 订单状态 及其时间范围查询
    * @param adminQueryDo
    * @return
    */
   List<AdminOrderInfoDo> getOrderInfoByKeys(AdminQueryDo adminQueryDo);

   /**
    * 处理订单  接单与否
    * @param orderNums  批量更新状态
    * @param status
    * @return
    */
   int updateOrderStatus (@Param("orderNums") List<String> orderNums,@Param("status") byte status);

}
