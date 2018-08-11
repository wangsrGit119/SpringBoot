package cn.wangsr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.wangsr.entits.Bar;
import cn.wangsr.entits.Bar_list;
import cn.wangsr.entits.Msg;
import cn.wangsr.entits.Role;
import cn.wangsr.entits.Sys_User;
import cn.wangsr.entits.User_Role;
import cn.wangsr.service.BarServices;
import cn.wangsr.service.RoleService;
import cn.wangsr.service.UserService;

@Controller
@RequestMapping("/touser")
public class ResourceController {

	@Autowired
	BarServices barServices;
	
	@Autowired
	RoleService roleService;
	
	
	@Autowired
	UserService userService;
	/*
	 * ********资源路径设置模块*******************
	 */
	
	/**
	 * 得到所有的资源及其url
	 * @param pn
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/tosetresource_admin")
	public String  getAllResource(@RequestParam(value = "pn", defaultValue = "1") Integer pn,Model model) {
		PageHelper.startPage(pn, 4);
		
		List<Bar_list> lists=barServices.getAllBar_List();
		
		PageInfo<Bar_list> page = new PageInfo<>(lists,3);
		
		model.addAttribute("pageInfo", page);
		return "resource/resource";
	}
	
	
	/**
	 * 删除资源及其URL
	 * @param id
	 * @return
	 */
	@DeleteMapping("/resource")
	@ResponseBody
	public Msg deleteBar_List(@RequestParam("id")Integer id) {
		barServices.deleteBLById(id);
		Msg msg = new Msg("delete", "success", "ojbk");
		return msg;
	}
	
	
	
	
	
	
	/**
	 * 前往添加资源页面
	 * @return
	 */
	@RequestMapping("/addResource")
	public String toaddResourePage(Model model) {
		List<Bar> bars=barServices.getAllBar();
		model.addAttribute("Bars",bars);
		return "resource/addResource";
	}
	
	
	
	@PostMapping("/resource")
	@ResponseBody
	public Msg addResource(Bar_list bar_list) {
		
		//System.out.println("添加的资源及其URL："+bar_list);
		barServices.addbarList(bar_list);
		Msg msg=new Msg("post", "success", "ojbk");
		
		return msg;
	}
	
	
	/*
	 * ********权限赋予设置模块*******************
	 */
	
	
	/**
	 * 前往设置页面
	 * @param model
	 * @return
	 */
	@GetMapping("/tosetpermission_admin")
	public String toSetPermissionPage( Model model) {
		List<Sys_User> u_list=userService.getAllUser();
		List<Role> r_list=roleService.getAllRoles();
		List<Bar> b_List=barServices.getAllBar();
		model.addAttribute("u_list", u_list);
		model.addAttribute("r_list", r_list);
		model.addAttribute("b_list", b_List);
		return "resource/setPermission";
	}
	
	
	/**
	 * 得到某个用户的id及其为期赋予权限的id
	 * @param id
	 * @param ids
	 * @return
	 */
	
	@PostMapping("/user_roles")
	@ResponseBody
	public Msg addUser_Roles(Integer user_id, String  ids) {
		
		if (ids.equals("") || ids==null || user_id.equals("") || user_id==null) {
			Msg msg = new Msg("post", "failed", "noojbk");
			return msg;
		}
		
		System.out.println("用户id："+user_id+"角色json_ids："+ids);
		JSONObject json_ids=JSONObject.parseObject(ids);
		JSONArray arr_ids=json_ids.getJSONArray("ids");
		//System.out.println("arr_ids:"+arr_ids);
		
		//首先清空当前用户拥有的角色
		userService.deleteUser_Role(user_id);
	
		//然后添加前台传过来的角色
		
		User_Role user_Role=new User_Role();
		for (Object  r_id : arr_ids) {
			user_Role.setUser_id(user_id);
			user_Role.setRole_id(Integer.parseInt( (String) r_id));
			userService.addUser_Role(user_Role);
			//System.out.println("要插入的角色："+user_Role);
		}
		
		Msg msg = new Msg("post", "success", "ojbk");
		return msg;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 角色栏目对应权限关系
	 * @param role
	 * @param bar
	 * @return
	 */
	@PostMapping("/permission")
	@ResponseBody
	public Msg addRole_Bar(Bar bar) {
		if (bar.getBarName().equals("") || bar.getBarName()==null) {
			Msg msg=new Msg("post", "failed", "noojbk");
			return msg;
		}
		
		
		System.out.println("bar:"+bar);
		
		try {
			barServices.addBar(bar);
			Msg msg=new Msg("post", "success", "ojbk");
			return msg;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	
	
}
