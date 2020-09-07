package micro.service.cloud.feign;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/05 18:30
 * @version: V1.0
 */

/*
 * 这里要重点强调的是@RequestMapping URL路径映射的问题，
 * 如果@RequestMapping的值和PaymentFeign的接口相同，会导致同一个URL映射到两个方法上，
 * 因此需要保证Fallback类的@RequestMapping的值与interface不同。
 */

@RequestMapping("/fallback/payment")
@Component
public class PaymentFallback implements PaymentFeign {
    @Override
    public String ok(Long id) {
        return "PaymentFallback-ok-fallback";
    }

    @Override
    public String paymentTimeout(Long id) {
        return "PaymentFallback-paymentTimeout-fallback";
    }
}
