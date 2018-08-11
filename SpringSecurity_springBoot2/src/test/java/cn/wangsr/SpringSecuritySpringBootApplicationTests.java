package cn.wangsr;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import cn.wangsr.mapper.BarMapper;
import cn.wangsr.mapper.UserroleMapper;
import cn.wangsr.service.RoleService;
import cn.wangsr.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSecuritySpringBootApplicationTests {

	@Autowired
	RoleService RoleService;
	
	@Autowired
	UserroleMapper UserroleMapper;
	
	@Autowired
	BarMapper barMapper;
	
	@Autowired 
	UserService UserService;
	@Test
	public void contextLoads() {
		
		//System.out.println(barMapper.getAllBarByRoId(1));
		
		/*Sys_User user=UserService.getUserByName("aaa");
		
	    Object data=JSONArray.toJSON(user);
		
		String js=Jsonutils.getJSONData(0, "helllo", 1l, data);
		
		System.out.println(js);*/
	//	UserroleMapper.deleteUser_role(5);
		UserService.deleteUser_Role(5);
//		User_Role user_Role=new User_Role();
//		user_Role.setUser_id(1);
//		user_Role.setRole_id(4);
//		UserService.addUser_Role(user_Role);
//		
	}

}
