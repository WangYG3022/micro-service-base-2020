package micro.service.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/09/09 22:38
 * @version: V1.0
 */
@EnableOAuth2Sso // 开启SSO支持
@SpringBootApplication
public class OAuth2ServerApp {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2ServerApp.class, args);
    }
}
