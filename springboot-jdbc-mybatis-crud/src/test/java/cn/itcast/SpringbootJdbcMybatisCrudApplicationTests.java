package cn.itcast;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.itcast.entities.Employee;
import cn.itcast.mapper.EmplMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJdbcMybatisCrudApplicationTests {

	
	@Autowired
	EmplMapper emplMapper;
	
	
	@Test
	public void contextLoads() {
		List<Employee> employee=emplMapper.getAllEmp();
		System.out.println(employee);
	}

}
