package micro.service.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/06 18:20
 * @version: V1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayApp9527 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApp9527.class, args);
    }
}
