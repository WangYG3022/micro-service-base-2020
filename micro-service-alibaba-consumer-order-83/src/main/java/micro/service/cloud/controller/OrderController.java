package micro.service.cloud.controller;

import micro.service.cloud.feign.PaymentFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/08 17:58
 * @version: V1.0
 */
@RestController
@RequestMapping("/c/payment")
public class OrderController {

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/nacos/{id}")
    public Map<String, String> get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(serverURL + "/payment/nacos/" + id, Map.class);
    }

    @Autowired
    private PaymentFeign paymentFeign;
    @GetMapping("/nacos/feign/{id}")
    public Map<String, String> feignGet(@PathVariable("id") Long id) {
        return paymentFeign.get(id);
    }
}
