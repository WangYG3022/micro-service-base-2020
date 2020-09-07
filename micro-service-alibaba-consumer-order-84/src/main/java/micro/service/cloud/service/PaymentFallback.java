package micro.service.cloud.service;

import micro.service.cloud.entity.CommonResult;
import micro.service.cloud.entity.Payment;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/09 20:38
 * @version: V1.0
 */
@Component
public class PaymentFallback implements PaymentFeign {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "fallback");
    }
}
