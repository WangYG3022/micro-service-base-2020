package micro.service.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import micro.service.cloud.entity.CommonResult;
import micro.service.cloud.entity.Payment;
import micro.service.cloud.feign.PaymentOpenFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/01 23:57
 * @version: V1.0
 */
@RestController
@Slf4j
@RequestMapping("/c/payment")
public class OrderController {

    @Autowired
    private PaymentOpenFeign paymentFeign;

    @PostMapping("/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        return paymentFeign.create(payment);
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeign.getPaymentById(id);
    }

    /**
     * 模拟服务端长流程，服务超时场景。Feign默认等待超时为1秒
     * 解决方法：yml开启超时时间设置
     * @return
     */
    @GetMapping("/timeout")
    public String timeout() {
        return paymentFeign.timeout();
    }

}
