package cn.wangsr.controller;


import cn.wangsr.model.po.UserInformation;
import cn.wangsr.service.impl.UserInformationServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author wjl
 * @since 2019-11-11
 */
@RestController
@RequestMapping("/user-information")
public class UserInformationController {
    @Autowired
    UserInformationServiceImpl  userInformationService;

    @Value("${server.port}")
    String port;

    @GetMapping("/getUserInfo")
    public List<UserInformation> getInfo (){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name","aaa");
        List<UserInformation> userInformations = userInformationService.getBaseMapper().selectList(queryWrapper);
        System.out.println("come from: " +port);
        return userInformations;
    }

}
