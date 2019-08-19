package cn.wangsr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"cn.wangsr.mapper","cn.wangsr.mapper.adminMapper"})
@SpringBootApplication
public class WechatDubboProvideApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatDubboProvideApplication.class, args);
    }

}

