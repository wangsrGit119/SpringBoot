package cn.wangsr.authclient.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @GetMapping("/test")
    public Object test01(Authentication authentication){

        return authentication.getDetails();
    }

}
