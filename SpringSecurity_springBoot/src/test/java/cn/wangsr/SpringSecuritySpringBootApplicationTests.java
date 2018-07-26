package cn.wangsr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.wangsr.service.RoleService;
import cn.wangsr.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSecuritySpringBootApplicationTests {

	@Autowired
	RoleService RoleService;
	
	@Autowired
	UserService UserService;
	
	@Test
	public void contextLoads() {
	System.out.println(	UserService.getUserByName("aaa"));
	}

}
