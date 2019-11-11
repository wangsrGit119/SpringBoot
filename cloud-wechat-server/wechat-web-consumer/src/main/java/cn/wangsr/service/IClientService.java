package cn.wangsr.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("wechat-client-provider")
public interface IClientService {

    @RequestMapping(value = "/user-information/getUserInfo",method = RequestMethod.GET)
    List getUserInfo();

}
