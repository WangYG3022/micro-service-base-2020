package micro.service.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import micro.service.cloud.entity.CommonResult;
import micro.service.cloud.entity.Payment;
import micro.service.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;

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

    @Value("${server.port}")
    private String port;

    @PostMapping("/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("result : {}", result);
        if (result > 0) {
            return new CommonResult<>(200, "数据插入成功！port=" + port, payment);
        } else {
            return new CommonResult<>(500, "数据插入失败。port=" + port);
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("payment : {}", payment);
        if (payment != null) {
            return new CommonResult<>(200, "OK! port=" + port, payment);
        } else {
            return new CommonResult<>(500, "ID(" + id + ")查无数据。port=" + port);
        }
    }

    @GetMapping("/lb")
    public String lb() {
        return port;
    }

    /**
     * 模拟服务端长流程，服务超时场景
     * @return
     */
    @GetMapping("/timeout")
    public String timeout() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/discovery")
    public DiscoveryClient discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service:{}", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(serviceInstance -> log.info("{}\t{}", serviceInstance.getInstanceId(), serviceInstance.getUri()));
        return discoveryClient;
    }

    /**
     * Sleuth + zipkin 链路跟踪示例
     * @return
     */
    @GetMapping(value = "/zipkin")
        public String zipkin() {
        return "hi, I'am paymentZipkin server fallback, welcome, O(∩_∩)O哈哈~";
    }
}
