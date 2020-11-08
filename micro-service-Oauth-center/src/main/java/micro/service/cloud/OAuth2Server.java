package micro.service.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/09/09 22:38
 * @version: V1.0
 */
//@EnableOAuth2Sso // 开启SSO支持
@EnableDiscoveryClient
@EnableFeignClients
@EnableResourceServer
@SpringBootApplication
public class OAuth2Server {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2Server.class, args);
    }
}
