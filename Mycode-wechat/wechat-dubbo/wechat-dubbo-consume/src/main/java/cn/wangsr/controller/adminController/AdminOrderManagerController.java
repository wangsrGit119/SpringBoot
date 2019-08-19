package cn.wangsr.controller.adminController;



import cn.wangsr.service.adminService.AdminOrderManagerService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import resultCode.ResultDto;

import java.util.List;

@RestController
@RequestMapping("/wechatAdmin")
public class AdminOrderManagerController {


     @Autowired
    AdminOrderManagerService adminOrderManagerService;
    Logger logger= LoggerFactory.getLogger(AdminOrderManagerController.class);
     ResultDto<Object> resultDto;

     @PostMapping("/getOrderInfoBykeys")
     public String getAdminOrderByKeys(@RequestParam  String queryInfo){
         logger.info(""+queryInfo);
         resultDto=new ResultDto<>();
         String getResult= adminOrderManagerService.getOrderInfoBykeys(queryInfo);
         return JSON.toJSONString(resultDto.successDto(getResult));
     }

     @PostMapping("/batchUpdateStatus")
     public String updateStatus(@RequestParam("orderNums") List orderNums,@RequestParam("status") byte status){
         System.out.println(orderNums+":"+status);
         resultDto=new ResultDto<>();
         if(orderNums.size()==0 ){
          return   JSON.toJSONString( resultDto.failedDto("参数不能为空"));
         }
        String res= adminOrderManagerService.updataorderStatus(orderNums,status);
         return  JSON.toJSONString(resultDto.successDto("更新成功"+res));
     }

}
