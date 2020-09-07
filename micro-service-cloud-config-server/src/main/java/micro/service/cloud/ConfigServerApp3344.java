package micro.service.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/06 21:12
 * @version: V1.0
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApp3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApp3344.class, args);
    }
}
