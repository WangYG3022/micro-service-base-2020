package micro.service.cloud.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/09/12 18:08
 * @version: V1.0
 */
@RestController
public class UserController {

    @GetMapping("/getCurrentUser")
    public Authentication getCurrentUser(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/login")
    public String login() {
        return "登录成功！";
    }
    @PreAuthorize("hasAuthority('p1')")
    @GetMapping("/r/r1")
    public String r1() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal + "正在访问r1";
    }

    @PreAuthorize("hasAuthority('p3')")
    @GetMapping("/r/r3")
    public String r2() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal + "正在访问r3";
    }

    @GetMapping("/logout")
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
