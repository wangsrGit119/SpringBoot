package cn.wangsr.controller;


import cn.wangsr.api.GoodsApi;
import resultCode.ResultDto;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wechatApi")
public class GoodsController {

    @Reference(version = "1.0.0")
    GoodsApi goodsApi;
    
    
    ResultDto<Object> resultDto=null;

    @GetMapping("/goodsItemsInfo")
    public String getItemsInfo(){
    	resultDto=new ResultDto<>();
        String goodsItem=goodsApi.getAllItemsInfo();
        String resultItem=JSON.toJSONString(resultDto.successDto(goodsItem));
        return resultItem;
    }
    
    @GetMapping("/goodsInfoByItemId")
    public String getGoodsInfoById(@RequestParam("itemId")Integer itemId) {
    	resultDto=new ResultDto<>();
    	if(itemId==null) {
    		return JSON.toJSONString(resultDto.failedDto(null)) ;
    	}
    	String goodsInfo=goodsApi.getAllGoodsByItemId(itemId);
    	return JSON.toJSONString(resultDto.successDto(goodsInfo));
    }
    
    @GetMapping("/getGoodsListByKeys")
    public String getGoodsListByKeys(@RequestParam("keys")String keys) {
    	resultDto=new ResultDto<>();
    	String userList= goodsApi.getGoodsListByKeyWords(keys);
    	return JSON.toJSONString(resultDto.successDto(userList));
    }
}
