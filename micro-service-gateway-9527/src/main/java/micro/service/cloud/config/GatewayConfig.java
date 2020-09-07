package micro.service.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/06 18:42
 * @version: V1.0
 */
@Configuration
public class GatewayConfig {

    /**
     * 自定义路由规则，通过gateway，访问百度新闻
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customBaiduLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("baidu_news_guonei", p -> p.path("/guonei").uri("http://news.baidu.com/guonei"))
                .route("baidu_news_guoji", p -> p.path("/guoji").uri("http://news.baidu.com/guoji"))
                .build();
    }
}
