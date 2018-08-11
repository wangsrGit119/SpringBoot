package cn.wangsr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("cn.wangsr.mapper")
@SpringBootApplication
public class SpringSecuritySpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecuritySpringBootApplication.class, args);
	}
}
