package micro.service.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/08 21:21
 * @version: V1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelApp8401 {
    public static void main(String[] args) {
        SpringApplication.run(SentinelApp8401.class, args);
    }
}
