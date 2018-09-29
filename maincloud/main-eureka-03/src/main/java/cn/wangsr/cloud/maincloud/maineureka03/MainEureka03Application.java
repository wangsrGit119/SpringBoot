package cn.wangsr.cloud.maincloud.maineureka03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MainEureka03Application {

    public static void main(String[] args) {
        SpringApplication.run(MainEureka03Application.class, args);
    }
}
