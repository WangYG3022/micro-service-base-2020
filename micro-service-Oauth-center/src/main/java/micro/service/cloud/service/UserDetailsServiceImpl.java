package micro.service.cloud.service;

import lombok.extern.slf4j.Slf4j;
import micro.service.cloud.entity.SsoUser;
import micro.service.cloud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/09/10 0:28
 * @version: V1.0
 */
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SsoUser ssoUser = userMapper.getUserByUserName(userName);
        if (ssoUser == null) {
            log.info("用户名 [{}] 尚未注册！", userName);
            return null;
        }
        List<java.lang.String> list = ssoUser.getPermissions();
        String[] arr = new String[list.size()];
        list.toArray(arr);
        return User.withUsername(userName)
                .password(ssoUser.getPassword())
                .authorities(arr)
                .build();
    }
}
