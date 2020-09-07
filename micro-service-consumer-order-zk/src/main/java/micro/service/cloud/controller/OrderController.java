package micro.service.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

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

    /**
     * 必须使用Ribbon负载均衡，才能使用服务名访问
     */
    public static final String PAYMENT_ZK_URL = "http://provider-payment-zk" + "/payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/zk")
    public Map<String, Object> zk() {
        return restTemplate.getForObject(PAYMENT_ZK_URL + "/zk", Map.class);
    }
}
