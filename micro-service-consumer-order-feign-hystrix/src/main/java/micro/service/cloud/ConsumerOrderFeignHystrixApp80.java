package micro.service.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/01 23:50
 * @version: V1.0
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients // 启用Feign
@EnableHystrix
public class ConsumerOrderFeignHystrixApp80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderFeignHystrixApp80.class, args);
    }

}
