package micro.service.cloud.feign;

import micro.service.cloud.entity.CommonResult;
import micro.service.cloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 封装服务提供方接口
 * @author: WANG Y.G.
 * @time: 2020/08/04 23:09
 * @version: V1.0
 */
//@Component // 实际运行不需要该注解
@FeignClient("CLOUD-PAYMENT-SERVICE") // 声明为Feign客户端，并指明对应的微服务名
@RequestMapping("/payment")
public interface PaymentOpenFeign {

    @PostMapping("/create")
    CommonResult<Payment> create(@RequestBody Payment payment);

    @GetMapping("/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/timeout")
    String timeout();
}
