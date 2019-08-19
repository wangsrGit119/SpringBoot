package cn.wangsr.controller.adminController;

import cn.wangsr.adminOrderDto.AdminGoodsClassifyDto;
import cn.wangsr.adminOrderDto.AdminGoodsDto;
import cn.wangsr.api.adminApi.GoodsMenuManagerApi;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import resultCode.ResultDto;

import java.util.List;

@RestController
@RequestMapping("/wechatAdmin")
public class AdminMenuManagerController {

    @Reference(version = "1.0.0")
    GoodsMenuManagerApi goodsMenuManagerApi;

    ResultDto<Object> resultDto=null;

    Logger logger= LoggerFactory.getLogger(AdminMenuManagerController.class);

    @GetMapping("/getAllClassify")
    public String getAllGoodsClassify(){
        resultDto=new ResultDto();
        List<AdminGoodsClassifyDto> list=goodsMenuManagerApi.getAllGoodsClassify();
        return JSON.toJSONString(resultDto.successDto(list));
    }

    @PostMapping("/addGoodsClassifyInfo")
    public String addClassifyInfo(@RequestBody AdminGoodsClassifyDto adminGoodsClassifyDto){
        logger.info(adminGoodsClassifyDto.toString());
        resultDto=new ResultDto();
        if(adminGoodsClassifyDto.getClassifyName()==null){
            return JSON.toJSONString(resultDto.failedDto("参数不能为空"));
        }
       String result= goodsMenuManagerApi.insertGoodsClassify(adminGoodsClassifyDto);
        return  JSON.toJSONString(resultDto.successDto("添加成功"+result));
    }


    @PostMapping("/addGoodsInfo")
    public String addGoodsInfo(@RequestBody AdminGoodsDto adminGoodsDto){
        logger.info(adminGoodsDto.toString());
        resultDto=new ResultDto();
        if(adminGoodsDto.getGoodsName()==null || adminGoodsDto.getGoodsPrice()==null || adminGoodsDto.getGoodsImage()==null || adminGoodsDto.getGoodsItemId()==null){
            return JSON.toJSONString(resultDto.failedDto("参数不能为空"));
        }
        String result=goodsMenuManagerApi.insertGoodsInfo(adminGoodsDto);
        return  JSON.toJSONString(resultDto.successDto("添加成功"+result));
    }

    @PostMapping("/delGoodsById")
    public String  delGoodsById(@RequestParam("goodsId") Integer goodsId){
        logger.info("goodsId:"+goodsId);
        resultDto=new ResultDto();
        if(goodsId==null){
            return JSON.toJSONString(resultDto.failedDto("参数不能为空"));
        }
        String result=goodsMenuManagerApi.deleGoodsByGoodsId(goodsId);
        return JSON.toJSONString(resultDto.successDto("删除成功"+result));
    }

}
