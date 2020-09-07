package micro.service.cloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/01 22:29
 * @version: V1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker // 启动服务熔断降级
public class ProviderHystrixPaymentApp8001 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderHystrixPaymentApp8001.class, args);
    }

    /**
     * 此配置是为了服务监控而配置，与服务容错本身无关，Spring Cloud升级后的坑
     * ServletRegistrationBean因为SpringBoot的默认路径不是 “/hystrix.stream"
     * 只要在自己的项目里配置上下的servlet就可以了
     */
    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet() ;
        ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return  registrationBean;
    }
}
