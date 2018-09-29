package cn.wangsr.cloud.maincloud.maineureka02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MainEureka02Application {

    public static void main(String[] args) {
        SpringApplication.run(MainEureka02Application.class, args);
    }
}
