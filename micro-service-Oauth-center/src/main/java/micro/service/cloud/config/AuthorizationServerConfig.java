package micro.service.cloud.config;

import micro.service.cloud.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @description: OAuth2.0配置
 * @author: WANG Y.G.
 * @time: 2020/09/10 0:25
 * @version: V1.0
 */
@Configuration
@EnableAuthorizationServer // 开启授权服务
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    // 密码模式授权模式
    private static final String GRANT_TYPE_PASSWORD = "password";
    //授权码模式
    private static final String AUTHORIZATION_CODE = "authorization_code";
    //refresh token模式
    private static final String REFRESH_TOKEN = "refresh_token";
    //简化授权模式
    private static final String IMPLICIT = "implicit";
    private static final String ALL = "all";

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;
    @Autowired
    private AuthorizationServerTokenServices tokenServices;
    @Autowired
    private TokenStore tokenStore;

    /**
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，
     * 你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 在内存中模拟1个客户端
        clients.inMemory()
                .withClient("client-A")
                .secret(passwordEncoder.encode("client-secret-A"))
                .authorizedGrantTypes(AUTHORIZATION_CODE, REFRESH_TOKEN, GRANT_TYPE_PASSWORD)
                .scopes(ALL)
                .accessTokenValiditySeconds(3600)
                .autoApprove(true) // 自动授权
                .redirectUris("http://localhost:8081/clientA/login")
                .and()
                .withClient("client-B")
                .secret(passwordEncoder.encode("client-secret-B"))
                .authorizedGrantTypes(AUTHORIZATION_CODE, REFRESH_TOKEN, GRANT_TYPE_PASSWORD)
                .scopes(ALL)
                .accessTokenValiditySeconds(3600)
                .autoApprove(true) // 自动授权
                .redirectUris("http://localhost:8082/clientB/login");
        // 实际开发，从DB获取
//        clients.jdbc(dataSource);
    }

    /**
     * 用来配置令牌（token）的访问端点和令牌服务(token services)
     *
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager) // 认证管理器
                .accessTokenConverter(accessTokenConverter)
                .userDetailsService(userDetailsService) //必须注入userDetailsService否则根据refresh_token无法加载用户信息
                .authorizationCodeServices(authorizationCodeServices) // 授权码服务
                .tokenServices(tokenServices) // 令牌管理服务
                .reuseRefreshTokens(true) //刷新token
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET); //支持获取token方式
    }

    /**
     * 用来配置令牌端点的安全约束
     *
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.passwordEncoder(passwordEncoder)
                // 开启/oauth/token_key验证端口认证权限访问
                .tokenKeyAccess("isAuthenticated()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("permitAll()")
                // 允许表单认证 在授权码模式下会导致无法根据code获取token
                .allowFormAuthenticationForClients();
    }
}
