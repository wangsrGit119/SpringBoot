package cn.wangsr.cloud.maincloud.maineureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MainEureka01Application {

    public static void main(String[] args) {
        SpringApplication.run(MainEureka01Application.class, args);
    }
}
