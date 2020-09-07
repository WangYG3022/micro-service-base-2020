package micro.service.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/08 17:25
 * @version: V1.0
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/nacos/{id}")
    public Map<String, String> get(@PathVariable("id") Long id) {
        Map<String, String> map = new HashMap<>();
        map.put("port", port);
        map.put("id", id.toString());
        return map;
    }
}
