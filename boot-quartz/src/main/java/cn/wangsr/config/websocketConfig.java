package cn.wangsr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author: wjl
 * @description:
 * @time: 2019/9/27 0027 14:52
 */
@Configuration
public class websocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){

        return  new ServerEndpointExporter();
    }
}
