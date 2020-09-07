package micro.service.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/01 23:56
 * @version: V1.0
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced // 开启负载均衡，可通过微服务名访问
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
