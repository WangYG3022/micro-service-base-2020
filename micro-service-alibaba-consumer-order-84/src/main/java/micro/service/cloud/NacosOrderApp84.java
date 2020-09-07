package micro.service.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/08 17:24
 * @version: V1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NacosOrderApp84 {
    public static void main(String[] args) {
        SpringApplication.run(NacosOrderApp84.class, args);
    }
}
