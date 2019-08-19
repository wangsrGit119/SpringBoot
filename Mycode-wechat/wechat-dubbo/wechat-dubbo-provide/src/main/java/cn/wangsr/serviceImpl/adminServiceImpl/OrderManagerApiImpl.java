package cn.wangsr.serviceImpl.adminServiceImpl;


import cn.wangsr.adminOrderDo.AdminOrderInfoDo;
import cn.wangsr.adminOrderDo.AdminQueryDo;
import cn.wangsr.api.adminApi.OrderManagerApi;
import cn.wangsr.mapper.adminMapper.AdminOrderManager;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class OrderManagerApiImpl implements OrderManagerApi {


    @Autowired
    AdminOrderManager adminOrderManager;

   Logger logger= LoggerFactory.getLogger(OrderManagerApiImpl.class);
    @Override
    public String getOrderByKeys(String queryInfo) {
        AdminQueryDo adminQueryDo= JSONObject.parseObject(queryInfo, AdminQueryDo.class);
        logger.info(adminQueryDo.toString());
        PageHelper.startPage(adminQueryDo.getPageNum(),adminQueryDo.getPageSize());
         List<AdminOrderInfoDo> adminOrderInfoDoList= adminOrderManager.getOrderInfoByKeys(adminQueryDo);
        PageInfo<AdminOrderInfoDo> pageInfoResult=new PageInfo<>(adminOrderInfoDoList);
        return JSON.toJSONString(pageInfoResult);
    }

    @Override
    public String reviewedOrder(List orderNums,byte status) {
      int updateResult=  adminOrderManager.updateOrderStatus(orderNums,status);
        return String.valueOf(updateResult);
    }
}
