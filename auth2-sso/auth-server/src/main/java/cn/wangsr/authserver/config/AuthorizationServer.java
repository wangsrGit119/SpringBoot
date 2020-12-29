package cn.wangsr.authserver.config;

import cn.wangsr.authserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;

    @Resource
    private DataSource dataSource;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // 获取密钥需要身份认证，使用单点登录时必须配置
        security.tokenKeyAccess("isAuthenticated()");
    }

    @Bean
    public ClientDetailsService clientDetails() throws SQLException {
        return new JdbcClientDetailsService(dataSource);
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        //数据库储存客户端信息
        clients.withClientDetails(clientDetails());
//        clients.inMemory()
//                //配置client_id
//                .withClient("cdjcy")
//                //配置client_secret
//                .secret(customPasswordEncoder.encode("123456"))
//                //配置访问token的有效期
//                .accessTokenValiditySeconds(3600)
//                //配置刷新token的有效期
//                .refreshTokenValiditySeconds(864000)
//                //配置redirect_uri，用于授权成功后跳转
//                .redirectUris("http://localhost:8081/cdjcy/login")
//                //自动授权配置
//                .autoApprove(true)
//                //配置申请的权限范围
//                .scopes("all")
//                //配置grant_type，表示授权类型
//                .authorizedGrantTypes("authorization_code", "refresh_token");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.accessTokenConverter(jwtAccessTokenConverter())
                .tokenStore(jwtTokenStore());
    }

    @Bean
    public JwtTokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("123456");
        return jwtAccessTokenConverter;
    }

}
