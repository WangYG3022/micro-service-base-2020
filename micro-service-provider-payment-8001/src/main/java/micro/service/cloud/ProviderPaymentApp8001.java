package micro.service.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/01 22:29
 * @version: V1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan("micro.service.cloud.mapper")
public class ProviderPaymentApp8001 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderPaymentApp8001.class, args);
    }
}
