package micro.service.cloud.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/09/10 0:19
 * @version: V1.0
 */
@RestController
public class UserController {

    @PreAuthorize("hasAuthority('p1')")
    @GetMapping("/r/r1")
    public String r1() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal + "正在访问r1";
    }

    @PreAuthorize("hasAuthority('p2')")
    @GetMapping("/r/r2")
    public String r2() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal + "正在访问r2";
    }

    @PostMapping("/login_success")
    public Object success() {
        // 用户登录信息可以从SecurityContextHolder中获得
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = ((UserDetails) principal);
            String username = userDetails.getUsername();
            String password = userDetails.getPassword();
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            System.out.println("username = " + username);
            System.out.println("password = " + password);
            System.out.println("authorities = " + authorities);
        }
        return principal;
    }

    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/myLogout")
    public void logout() {
        SecurityContext context = SecurityContextHolder.getContext();
        SecurityContextHolder.clearContext();
    }
}
