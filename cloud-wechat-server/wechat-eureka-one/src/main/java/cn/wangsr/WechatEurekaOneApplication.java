package cn.wangsr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class WechatEurekaOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatEurekaOneApplication.class, args);
    }

}
