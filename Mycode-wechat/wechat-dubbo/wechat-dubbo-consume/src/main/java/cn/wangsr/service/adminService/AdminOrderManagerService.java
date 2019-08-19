package cn.wangsr.service.adminService;


import cn.wangsr.adminOrderDto.AdminOrderInfoDto;
import cn.wangsr.api.adminApi.OrderManagerApi;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminOrderManagerService {

    @Reference(version = "1.0.0")
    OrderManagerApi orderManagerApi;
    Logger logger= LoggerFactory.getLogger(AdminOrderManagerService.class);


    public String getOrderInfoBykeys(String queryInfo){
        PageInfo<AdminOrderInfoDto> jsonObject=JSONObject.parseObject(orderManagerApi.getOrderByKeys(queryInfo), PageInfo.class);
        logger.info(""+jsonObject.toString());
        return JSON.toJSONString(jsonObject);
    }



    public String updataorderStatus(List orderNums,byte status){
         String result=  orderManagerApi.reviewedOrder(orderNums,status);
        return  JSON.toJSONString(result);
    }

}
