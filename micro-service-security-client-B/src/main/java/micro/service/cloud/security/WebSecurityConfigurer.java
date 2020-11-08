package micro.service.cloud.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/09/10 0:21
 * @version: V1.0
 */
@Configuration
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // 开启基于方法的安全拦截，可在任意配置类上加此注解
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    /**
     * 密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 拦截机制
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/r/r1").hasAnyAuthority("p1")
                .antMatchers("/r/r2").hasAnyAuthority("p2")
                .antMatchers("/r/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .successForwardUrl("/login_success")
                // security session 相关设置
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);*/
        http.logout()
                .logoutUrl("/myLogout")
                .logoutSuccessUrl("http://localhost:8080/sso/logout")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

}
