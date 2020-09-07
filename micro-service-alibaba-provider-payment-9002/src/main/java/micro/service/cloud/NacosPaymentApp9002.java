package micro.service.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/08 17:24
 * @version: V1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosPaymentApp9002 {
    public static void main(String[] args) {
        SpringApplication.run(NacosPaymentApp9002.class, args);
    }
}
