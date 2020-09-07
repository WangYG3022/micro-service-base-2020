package micro.service.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: Ribbon负载均衡策略配置类，该类不能放在@ComponentScan所扫描的当前包及子包下，否则会被所有客户端所共享
 * @author: WANG Y.G.
 * @time: 2020/08/04 21:35
 * @version: V1.0
 */
@Configuration
public class LoadBalanceRule {

    @Bean
    public IRule iRule() {
        return new RandomRule();
    }
}
