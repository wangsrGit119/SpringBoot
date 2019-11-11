package cn.wangsr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WechatServerProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatServerProviderApplication.class, args);
    }

}
