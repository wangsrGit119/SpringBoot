package cn.wangsr.controller;

import cn.wangsr.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: wjl
 * @description:
 * @time: 2019/11/11 0011 14:23
 */
@RestController
@RequestMapping("/consumer/client/")
public class ClientController {
    @Autowired
    IClientService iclientService;
    @GetMapping("/getInfo")
    public List getUserInfo(){
       return iclientService.getUserInfo();
    }
}
