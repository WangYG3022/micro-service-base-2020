package micro.service.cloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import micro.service.cloud.entity.CommonResult;
import micro.service.cloud.entity.Payment;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/09 18:46
 * @version: V1.0
 */
public class CustomerBlockHandler {
    /**
     * 方法只能用静态
     */
    public static CommonResult<Payment> handlerException(BlockException exception) {
        return new CommonResult<>(444, "客户自定义，global handlerException---1");
    }

    public static CommonResult<Payment> handlerException2(BlockException exception) {
        return new CommonResult<>(444, "客户自定义，global handlerException---2");
    }
}
