package micro.service.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import micro.service.cloud.entity.CommonResult;
import micro.service.cloud.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

//    public static final String PAYMENT_SERVICE_URL = "http://localhost:8001/payment";
    /**
     * 必须使用Ribbon负载均衡，才能使用服务名访问
     */
    public static final String PAYMENT_SERVICE_URL = "http://CLOUD-PAYMENT-SERVICE/payment";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_SERVICE_URL + "/create", payment, CommonResult.class);
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_SERVICE_URL + "/get/" + id, CommonResult.class);
    }

    @GetMapping("/getForEntity/{id}")
    public CommonResult<Payment> getForEntity(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_SERVICE_URL + "/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(500, "ERROR");
        }
    }

    /**
     * 模拟链路跟踪
     * @return
     */
    @GetMapping(value = "/zipkin")
    public String zipkin() {
        return restTemplate.getForObject(PAYMENT_SERVICE_URL + "/zipkin", String.class);
    }
}
