package micro.service.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/08 18:26
 * @version: V1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigClientApp3377 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClientApp3377.class, args);
    }
}
