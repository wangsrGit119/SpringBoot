package cn.itcast.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import cn.itcast.entities.Department;
import cn.itcast.entities.Employee;
import cn.itcast.mapper.DeptMapper;
import cn.itcast.mapper.EmplMapper;

@Controller
public class EmployeeController {

	@Autowired
	DeptMapper deptMapper;

	
	@Autowired
	EmplMapper emplMapper;
	
	//员工列表查询
	@GetMapping("/emps")
	public String list(Model model) {
		Collection<Employee> employees=emplMapper.getAllEmp();
		System.out.println(employees);
		model.addAttribute("emps",employees);
		return "emp/list";
	}
	
	//员工添加之前来到添加页面  get请求
	@GetMapping("/emp")
	public String toaddPage(Model model) {
		
		Collection<Department> departments=deptMapper.getAllDept();
		model.addAttribute("depts", departments);
		return "emp/add";
	}
	
	//添加员工
	@PostMapping("/emp")
	public String addEmp(Employee employee) {
		System.out.println("添加员工的信息："+employee);
		emplMapper.insertEmpl(employee);
		return "redirect:/emps";//重定向到员工列表页面
	}
	//来到修改员工页面(重点：回显数据信息)
	@GetMapping("/emp/{id}")
	public String toEditPage(@PathVariable("id")Integer id,Model model) {
		
		Employee employee=emplMapper.getEmpById(id);
		System.out.println("要修改的员工数据："+employee);
		Collection<Department> departments=deptMapper.getAllDept();
		model.addAttribute("depts", departments);
		model.addAttribute("emp", employee);
		//
		return "emp/edit";
	}
	
	
	//提交修改数据
	@PutMapping("/emp")
	public String updateEditInf(Employee employee) {
		System.out.println("修改后的员工数据："+employee);
		emplMapper.updateEmpById(employee);//这个地方在实际操作时调用数据库更新  并不是替换map集合的值
		return "redirect:/emps";
	}
	
	//删除用户数据
	@DeleteMapping("/emp/{id}")
	public String deleteEmp(@PathVariable("id")Integer id) {
		System.out.println("要删除的："+id);
		emplMapper.deleteEmpById(id);
		
		return "redirect:/emps";
	}

}
