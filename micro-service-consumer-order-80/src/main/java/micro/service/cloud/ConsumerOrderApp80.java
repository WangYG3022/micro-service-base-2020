package micro.service.cloud;

import micro.service.rule.LoadBalanceRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/01 23:50
 * @version: V1.0
 */
@EnableEurekaClient
@SpringBootApplication
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = LoadBalanceRule.class) // 为每个服务指定负载均衡算法，或@RibbonClients
public class ConsumerOrderApp80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderApp80.class, args);
    }
}
