package cn.wangsr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wangsr.entits.Msg;
import cn.wangsr.entits.Role;
import cn.wangsr.service.RoleService;

@Controller 
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	
	
	/**
	 * 得到所有的角色
	 * @param model
	 * @return
	 */
	@GetMapping("/getRole")
	@ResponseBody
	public Model getAll(Model model) {
		List<Role> roles=	roleService.getAllRoles();
		model.addAttribute("Roles",roles);
		return model;
	}
	
	
	/**
	 * 添加新的角色
	 * @param role
	 * @return
	 */
	@PostMapping("/role")
	@ResponseBody
	public Msg addRole(Role role) {
		
		
		if (role.getRolename().equals("") || role.getRolename()==null) {
			Msg msg = new Msg("post", "fail", "noojbk");
			return msg;
		}
		System.out.println("要添加的角色："+role);
		try {
			roleService.addRole(role);
			Msg msg = new Msg("post", "success", "ojbk");
			return msg;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	

}
