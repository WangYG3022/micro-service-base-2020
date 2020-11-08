package micro.service.cloud.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/09/10 0:21
 * @version: V1.0
 */
@EnableOAuth2Sso // 单点登录必须要加上
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    /**
     * 拦截机制
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .logout().logoutSuccessUrl("http://localhost:8080/sso/logout")
                .and()
                .authorizeRequests()
//                .antMatchers("/r/r1").hasAuthority("p1")
//                .antMatchers("/r/r2").hasAuthority("p2")
                .anyRequest().authenticated();
    }

}
