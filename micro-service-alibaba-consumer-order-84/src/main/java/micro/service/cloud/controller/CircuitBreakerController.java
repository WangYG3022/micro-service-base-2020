package micro.service.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import micro.service.cloud.entity.CommonResult;
import micro.service.cloud.entity.Payment;
import micro.service.cloud.service.PaymentFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/09 20:44
 * @version: V1.0
 */
@RestController
@RequestMapping("/c/payment")
public class CircuitBreakerController {

    private static final String SERVICE_URL = "http://nacos-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/fallback/{id}")
//    @SentinelResource(value = "fallback") //没有配置，返回error page
//    @SentinelResource(value = "fallback", fallback = "handlerFallback") //配置了fallback的，fallback只负责业务异常
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler") // 配置了blockHandler，只负责sentinel控制台配置违规
    @SentinelResource(
            value = "fallback",
            fallback = "handlerFallback",
            blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class}
    ) // 配置了blockHandler和fallback，两个规则都违背时，以blockHandler为主
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult<Payment> commonResult = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class);
        if(id == 4){
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常");
        }else if(commonResult.getData() == null){
            throw new NullPointerException("NullPointerException,该ID没有记录，空指针异常");
        }
        return commonResult;
    }
    // 本例是fallback
    public CommonResult<Payment> handlerFallback(Long id, Throwable e){
        Payment payment = new Payment(id, null);
        return new CommonResult<>(444, "兜底异常handler，exception内容"+e.getMessage(), payment);
    }

    public CommonResult<Payment> blockHandler(Long id, BlockException exception){
        Payment payment = new Payment(id, null);
        return new CommonResult<>(445, "blockHandler-sentinel 限流，无此流水号：blockException" + exception.getMessage(), payment);
    }

    /*
     * ************************************************
     * 使用OpenFeign
     * ************************************************
     */

    @Resource
    private PaymentFeign paymentFeign;

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentFeign.paymentSQL(id);
    }
}
