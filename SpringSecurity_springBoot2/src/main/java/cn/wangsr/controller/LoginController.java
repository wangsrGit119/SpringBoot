package cn.wangsr.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wangsr.entits.Bar;

import cn.wangsr.entits.Role;
import cn.wangsr.service.BarServices;
import cn.wangsr.service.MyUserDetailService;
import cn.wangsr.service.UserService;





@Controller
public class LoginController {

		@Autowired
	    private SessionRegistry sessionRegistry;
	
		@Autowired
		MyUserDetailService myUserDetailService;
		
		@Autowired
		BarServices BarServices;
		
		@Autowired
		UserService userService;
		
		
		
		
		@RequestMapping("/index")
		public String index(HttpServletRequest request,HttpServletResponse response,Model model) {
			response.setContentType("application/json");
			List<Bar> lists=new ArrayList<>();

			String username=request.getUserPrincipal().getName();
			System.out.println("登录用户:"+username);
			
			Set<Role> roles= userService.getUserByName(username).getRoles();
			for (Role role : roles) {
				int r_id=role.getId();
				Bar bar=BarServices.getBarByRoId(r_id);
				lists.add(bar);
			}
//			System.out.println("所有权限栏目："+lists);
			model.addAttribute("barlist", lists);
			model.addAttribute("username", username);
			return "index";
		}
	
		
		/**
		 * 注销时直接从sessionRegistry中移除请求中储存的sessionid
		 * @param request
		 * @return
		 */
		@RequestMapping("/mylogout")
		public String logout2(HttpServletRequest request) {
			String sessionid=request.getRequestedSessionId();
			sessionRegistry.removeSessionInformation(sessionid);
			return "redirect:/login";
		}
	    
		/*@RequestMapping("/table")
		public String toTable() {
	
			return "table";
		}
	
		@PreAuthorize("hasRole('ROLE_NOUSER')")
		@RequestMapping("/others")
		public String toHello() {
	
			return "other/hello";
		}
	
		@PreAuthorize("hasRole('ROLE_USER')")
		@RequestMapping("/users")
		public String touser() {
	
			return "user/hello";
		}
	
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		@RequestMapping("/admin")
		@ResponseBody
		public String toADMIN() {
	
			return "hello  admin";
		}*/

}
