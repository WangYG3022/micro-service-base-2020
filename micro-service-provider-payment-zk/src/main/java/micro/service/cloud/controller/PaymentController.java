package micro.service.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/02 23:31
 * @version: V1.0
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/zk")
    public Map<String, Object> zk() {
        Map<String, Object> map = new HashMap<>();
        map.put("port", port);
        map.put("request-time", LocalDateTime.now());
        return map;
    }
}
