package cn.wangsr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WechatDubboConsumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatDubboConsumeApplication.class, args);
    }

}

