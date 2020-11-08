package micro.service.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.concurrent.TimeUnit;

/**
 * @description: 相关bean配置
 * @author: WANG Y.G.
 * @time: 2020/09/11 21:21
 * @version: V1.0
 */
@Configuration
public class SecurityBeanConfig {
    /**
     * 加密公钥
     */
    private static final String SIGNING_KEY = "bcrypt";

    /**
     * 密码加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 令牌存储方式
     * @return
     */
    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter accessTokenConverter) {
        // JwtTokenStore
        return new JwtTokenStore(accessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY); //对称秘钥，资源服务器使用该秘钥来验证
        return converter;
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        // 设置授权码模式的授权码如何存取，暂时采用内存方式
        return new InMemoryAuthorizationCodeServices();
    }

    /**
     * 令牌服务
     *
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices(
            ClientDetailsService clientDetailsService,
            JwtAccessTokenConverter accessTokenConverter,
            TokenStore tokenStore) {
        final DefaultTokenServices service = new DefaultTokenServices();
        service.setClientDetailsService(clientDetailsService);
        service.setSupportRefreshToken(true); // 支持刷新令牌
        service.setTokenStore(tokenStore); // 令牌存储
        service.setTokenEnhancer(accessTokenConverter);

        service.setAccessTokenValiditySeconds(((int) TimeUnit.HOURS.toSeconds(2))); // 令牌默认有效期2小时
        service.setRefreshTokenValiditySeconds(((int) TimeUnit.DAYS.toSeconds(3))); // 刷新令牌默认有效期3天
        return service;
    }
}
