package micro.service.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/06 22:16
 * @version: V1.0
 */
@RestController
@RefreshScope
public class ConfigController {

    @Value("${server.port}")
    private String port;
    @Value("${config.info}")
    private String info;

    @RequestMapping("/config")
    public Map<String, String> getConfig() {
        Map<String, String> config = new HashMap<>(4);
        config.put("port", port);
        config.put("info", info);
        return config;
    }
}
