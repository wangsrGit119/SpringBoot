package cn.wangsr.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wangsr.entits.Msg;





@Controller
public class LoginController {

	

	
    @RequestMapping("/home")
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","欢迎管理员来到HOME页面");
        model.addAttribute("msg", msg);
        return "home";
    }

    
  
   @PreAuthorize("hasRole('ROLE_NOUSER')")
    @RequestMapping("/others")
    public String toHello() {
    	
    	return "other/hello";
    }
    
   
   @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/users")
    public String touser(){
    	
        return "user/hello";
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin")
    @ResponseBody
    public String toADMIN(){
    	
        return "hello  admin";
    }
	
}
