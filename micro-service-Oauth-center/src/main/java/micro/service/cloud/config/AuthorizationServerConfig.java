package micro.service.cloud.config;

import micro.service.cloud.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/09/10 0:25
 * @version: V1.0
 */
@Configuration
@EnableAuthorizationServer // 标识本服务为授权服务
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("test_key");
        return accessTokenConverter;
    }

    /**
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，
     * 你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 在内存中模拟2个客户端
        clients.inMemory()
                .withClient("client-A")
                .secret(passwordEncoder.encode("client-secret-A"))
                .authorizedGrantTypes("refresh_token", "authorization_code")
                .scopes("all")
                .accessTokenValiditySeconds(3600)
                .autoApprove(false) // 关闭自动授权
                .redirectUris("http://127.0.0.1:8081/clientA/login")
            .and()
                .withClient("client-B")
                .secret(passwordEncoder.encode("client-secret-B"))
                .authorizedGrantTypes("refresh_token", "authorization_code")
                .scopes("all")
                .accessTokenValiditySeconds(3600)
                .autoApprove(true)
                .redirectUris("http://127.0.0.1:8082/clientB/login");
        // 实际开发，从DB获取

    }

    /**
     * 用来配置令牌（token）的访问端点和令牌服务(token services)
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(jwtTokenStore())
                .accessTokenConverter(jwtAccessTokenConverter())
                .userDetailsService(userDetailsService);
    }

    /**
     * 用来配置令牌端点的安全约束
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("isAuthenticated()"); // 获取密钥需要身份认证
    }
}
