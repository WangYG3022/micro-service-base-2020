package micro.service.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import micro.service.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/01 23:06
 * @version: V1.0
 */
@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/ok/{id}")
    public String ok(@PathVariable("id") Long id) {
        return paymentService.paymentOK(id);
    }

    @GetMapping("/timeout/{id}")
    public String paymentTimeout(@PathVariable("id") Long id) {
        return paymentService.paymentTimeout(id);
    }

    /**
     * 服务熔断示例
     * @param id
     * @return
     */
    @GetMapping("/circuit/{id}")
    public String circuit(@PathVariable("id") Long id) {
        return paymentService.paymentCircuitBreaker(id);
    }

}
