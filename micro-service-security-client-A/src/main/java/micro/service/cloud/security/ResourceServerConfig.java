package micro.service.cloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/09/11 23:32
 * @version: V1.0
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final String resourceId = "client-A";
    private final String secret = "client-secret-A";
//    @Autowired
//    private ResourceServerTokenServices tokenServices;
    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceId) // 资源ID
                .tokenStore(tokenStore)
//                .tokenServices(tokenServices) // 验证令牌的服务，使用jwt令牌后可本地校验
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasScope('all')")
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /*@Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setClientId("client-A");
        tokenServices.setClientSecret("client-secret-A");
        tokenServices.setCheckTokenEndpointUrl("http://localhost:8080/sso/oauth/check_token");
        return tokenServices;
    }*/

}
