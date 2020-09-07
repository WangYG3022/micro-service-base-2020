package micro.service.cloud.controller;

import micro.service.cloud.feign.PaymentFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/01 23:57
 * @version: V1.0
 */
@RestController
@RequestMapping("/c/payment")
//@DefaultProperties(defaultFallback = "globalFallback")
public class OrderController {

    @Resource
    private PaymentFeign paymentFeign;

    @GetMapping("/ok/{id}")
    public String ok(@PathVariable("id") Long id) {
        return paymentFeign.ok(id);
    }

//    @HystrixCommand // 未指定具体fallback方法，使用默认fallback
    /*@HystrixCommand(
            fallbackMethod = "consumerPaymentTimeoutFallback", // fallback方法签名必须与本方法一致，否则报异常：com.netflix.hystrix.contrib.javanica.exception.FallbackDefinitionException
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
            })*/
    @GetMapping("/timeout/{id}")
    public String paymentTimeout(@PathVariable("id") Long id) {
//        int a = 10 / 0; //模拟本方法异常
        return paymentFeign.paymentTimeout(id);
    }

    public String consumerPaymentTimeoutFallback(Long id) {
        return "消费端80调用服务端8001超时/异常，或自身服务异常，请稍后再试！";
    }

    /**
     * 全局的fallback方法，注意全局方法不能带形参
     * @return
     */
    public String globalFallback() {
        return "全局异常处理信息，请稍后再试！";
    }
}
