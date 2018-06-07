package cn.itcast.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@PostMapping(value="/user/login")
	public String login(@RequestParam("username")String username,
			            @RequestParam("password")String password,
			            Map<String, Object> map,
			            HttpSession session
			) {
		
		if(!StringUtils.isEmpty(username) && "123456".equals(password)) {
			
			session.setAttribute("loginuser", username);;
			//为防止表单的重复提交，需要重定向结果视图
			//下面的 main.html是自定义结果视图时的映射路径 ---见MyMvcConfig
			return "redirect:/main.html";
		}else {
			//登录失败
			map.put("msg", "用户名或密码错误");
			return "login";
		}
		
		
		
	}

}
