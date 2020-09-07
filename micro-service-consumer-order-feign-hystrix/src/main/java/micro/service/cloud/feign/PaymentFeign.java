package micro.service.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 封装服务提供方接口
 * @author: WANG Y.G.
 * @time: 2020/08/04 23:09
 * @version: V1.0
 */
@Component
@FeignClient(value = "HYSTRIX-PAYMENT-SERVICE", fallback = PaymentFallback.class)
@RequestMapping("/payment")
public interface PaymentFeign {

    @GetMapping("/ok/{id}")
    String ok(@PathVariable("id") Long id);

    @GetMapping("/timeout/{id}")
    String paymentTimeout(@PathVariable("id") Long id);

}
