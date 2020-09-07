package micro.service.cloud.controller;

import micro.service.cloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/07 19:41
 * @version: V1.0
 */
@RestController
@RequestMapping("/msg")
public class MsgSendController {

    @Autowired
    private IMessageProvider provider;

    @GetMapping("/send")
    public String send() {
        return provider.send();
    }
}
