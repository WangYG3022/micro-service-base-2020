package micro.service.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import micro.service.cloud.entity.CommonResult;
import micro.service.cloud.entity.Payment;
import micro.service.cloud.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/09 18:42
 * @version: V1.0
 */
@RestController
public class RateLimitController {

    /*
     * **********************************************
     * * 使用blockHandler兜底
     * **********************************************
     */

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult<Payment> byResource() {
        return new CommonResult<>(200, "按资源名称限流测试OK", new Payment(2020L, UUID.randomUUID().toString()));
    }

    public CommonResult<Payment> handleException(BlockException blockException) {
        return new CommonResult<>(444, blockException.getClass().getCanonicalName() + "\t服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult<Payment> byUrl() {
        return new CommonResult<>(200, "by url限流测试OK", new Payment(2020L, UUID.randomUUID().toString()));
    }

    /*
     * **********************************************
     * * 使用CustomerBlockHandler兜底
     * **********************************************
     */

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(
            value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2"
    )
    public CommonResult<Payment> customerBlockHandler(){
        return new CommonResult<>(200, "客户自定义 限流测试OK", new Payment(2020L, UUID.randomUUID().toString()));
    }
}
