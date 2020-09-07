package micro.service.cloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import micro.service.cloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/01 23:03
 * @version: V1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    /*
     * ====================================================================================
     * *                                   服务降级                                        *
     * ====================================================================================
     */

    @Override
    public String paymentOK(Long id) {
        return "线程池：" + Thread.currentThread().getName()
                + ", method=paymentOK, id: " + id + "正常访问！";
    }

    /*@HystrixCommand(
            fallbackMethod = "paymentTimeoutFallback", // 指定超时或异常后处理方法，fallback方法签名必须与本方法一致，否则报异常：com.netflix.hystrix.contrib.javanica.exception.FallbackDefinitionException
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") // 指定方法执行超时时间，此处设置为3秒，实际方法耗时5秒
            })*/
    @Override
    public String paymentTimeout(Long id) {
        long sleep = 5000L;
//        int a = 10 / 0; // 模拟抛出异常
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName()
                + ", method=paymentTimeout, id: " + id + "正常访问！";
    }

    public String paymentTimeoutFallback(Long id) {
        return "线程池资源繁忙，请稍后再试！" + Thread.currentThread().getName()
                + ", method=paymentTimeoutFallback, id: " + id;
    }

    /*
     * ====================================================================================
     * *                                   服务熔断                                        *
     * ====================================================================================
     */

    @HystrixCommand(
            fallbackMethod = "paymentCircuitBreakerFallback",
            commandProperties = { // com.netflix.hystrix.HystrixCommandProperties
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启断路器
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求数达到后才计算
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // //错误率达到多少跳闸
            })
    @Override
    public String paymentCircuitBreaker(Long id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        String serialNum = UUID.randomUUID().toString();
        return Thread.currentThread().getName() + "调用成功，流水号：" + serialNum;
    }

    public String paymentCircuitBreakerFallback(Long id) {
        return "paymentCircuitBreakerFallback，id不能为负数";
    }
}
