package cn.itcast;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.itcast.mapper")
@SpringBootApplication
public class SpringbootJdbcMybatisCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJdbcMybatisCrudApplication.class, args);
	}
}
