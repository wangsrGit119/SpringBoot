package cn.wangsr.cloud.maincloud.mainprovide01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MainProvide01Application {

    public static void main(String[] args) {
        SpringApplication.run(MainProvide01Application.class, args);
    }
}
