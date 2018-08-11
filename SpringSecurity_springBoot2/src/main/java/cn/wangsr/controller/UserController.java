package cn.wangsr.controller;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.wangsr.entits.Msg;
import cn.wangsr.entits.Sys_User;
import cn.wangsr.service.UserService;
import cn.wangsr.utils.MD5Util;

@Controller
@RequestMapping("/touser")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	/*=================管理员操作========================*/
	
	/**
	 * 得到所有的用户
	 * @param model
	 * @return
	 */
	
	@GetMapping("/alluser_admin")
	public String getAllUser(@RequestParam(value = "pn", defaultValue = "1") Integer pn,Model model) {
		
		PageHelper.startPage(pn, 4);
		List<Sys_User> u_list = userService.getAllPageUser();
		PageInfo<Sys_User> page = new PageInfo<>(u_list, 3);

		model.addAttribute("pageInfo", page);

		/*
		 //不含分页查询
		List<Sys_User> list=userService.getAllUser();
		System.out.println("查询到的user"+list);
		model.addAttribute("pageInfo", list);
		*/
		return "user/users";
	}
	
		/**
		 * 删除用户信息（及其权限）
		 * @param id
		 * @return
		 */
		@DeleteMapping("/user")
		@ResponseBody
		public Msg deleteUser(@RequestParam("id") int id) {
			//System.out.println("要删除的id是:"+id);
			userService.deleteUser(id);
			Msg msg=new Msg("delete", "success", "ojbk");
			return msg;
		}
		
		/**
		 * 来到添加用户页面
		 * @return
		 */
		
		@RequestMapping("/adduser")
		public String touser() {
			return "user/register";
		}
		
		/**
		 * 注册用户信息
		 * @param user
		 * @return
		 */
		
		@PostMapping("/user")
		@ResponseBody
		public Msg addUser(Sys_User user) {
			//密码加密  以及初始状态设置
			String password=user.getPassword();
			user.setPassword(MD5Util.encode(password));
			user.setActive("活跃");//初始注册人员状态全部为活跃状态
			user.setCreatedate(new Date());
			System.out.println("要添加的用户"+user);
			
			try {
				userService.regesiterUser(user);
				Msg msg=new Msg("post", "success", "ojbk");
				return msg;
			} catch (Exception e) {
				System.out.println("后台校验数据失败");
				throw new RuntimeException(e);
			}
			
			
		}
		
	/*=================用户操作========================*/
	
	/**
	 * 得到用户个人信息
	 * @param request
	 * @param username
	 * @param model
	 * @return
	 */
	@GetMapping("/user")
	public String getUser(@RequestParam("username")String username,Model model) {
		//System.out.println("用户名："+username);
		Sys_User user=userService.getUserByName(username);
		//System.out.println("得到："+user);
		model.addAttribute("user", user);
		return "user/myInfo";  
	}
	
	/**
	 * 来到修改界面  并回显个人信息
	 * @param username
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/toUpdatepage")
	public String toUpdatePage(@RequestParam("username")String username,Model model) {
		Sys_User user=userService.getUserByName(username);
		System.out.println("回显："+user);
		model.addAttribute("user", user);
		return "user/editPage";
	}
	
	/**
	 * 用户数据更新操作
	 * @param user
	 * @return
	 */
	
	@PutMapping("/user")
	@ResponseBody
	public Msg updateUser( Sys_User user) {
		System.out.println("修改后的数据："+user);
		userService.updateUser(user);
		Msg msg=new Msg("put", "success", "ojbk");
		return msg;
	}
	
	

}
