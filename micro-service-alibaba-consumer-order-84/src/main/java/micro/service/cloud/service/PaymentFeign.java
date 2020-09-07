package micro.service.cloud.service;

import micro.service.cloud.entity.CommonResult;
import micro.service.cloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/09 20:37
 * @version: V1.0
 */
@FeignClient(value = "nacos-provider-payment", fallback = PaymentFallback.class)
public interface PaymentFeign {

    @GetMapping("/paymentSQL/{id}")
    CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
