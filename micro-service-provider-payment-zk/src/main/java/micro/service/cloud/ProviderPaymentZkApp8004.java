package micro.service.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/01 22:29
 * @version: V1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderPaymentZkApp8004 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderPaymentZkApp8004.class, args);
    }
}
