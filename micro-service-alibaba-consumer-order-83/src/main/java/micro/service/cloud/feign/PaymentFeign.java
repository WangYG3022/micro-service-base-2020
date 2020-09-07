package micro.service.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/24 18:47
 * @version: V1.0
 */
@FeignClient("nacos-provider-payment") /*此处服务名"nacos-payment-provider"必须和服务提供方的yml配置一致，Nacos大小写敏感*/
public interface PaymentFeign {

    @GetMapping("/payment/nacos/{id}")
    Map<String, String> get(@PathVariable("id") Long id);
}
