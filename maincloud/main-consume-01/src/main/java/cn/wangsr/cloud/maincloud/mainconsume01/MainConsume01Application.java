package cn.wangsr.cloud.maincloud.mainconsume01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MainConsume01Application {

    public static void main(String[] args) {
        SpringApplication.run(MainConsume01Application.class, args);
    }
}
