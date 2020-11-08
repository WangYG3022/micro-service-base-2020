package micro.service.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/09/12 16:30
 * @version: V1.0
 */
@Slf4j
@RestController
public class UserController {

    @GetMapping("/user")
    public Object getCurrentUser(Authentication authentication) {
        log.info("查询当前用户信息：{}", authentication);
        return authentication;
    }
}
