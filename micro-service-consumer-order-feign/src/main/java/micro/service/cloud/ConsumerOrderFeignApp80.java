package micro.service.cloud;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @description: 使用OpenFeign来取代Ribbon+RestTemplate的远程服务调用方式
 * @author: WANG Y.G.
 * @time: 2020/08/01 23:50
 * @version: V1.0
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients // 启用Feign
public class ConsumerOrderFeignApp80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderFeignApp80.class, args);
    }

    /**
     * 设置OpenFeign的日志级别
     * @return
     */
    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }
}
