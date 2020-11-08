package micro.service.cloud.mapper;

import micro.service.cloud.entity.SsoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/09/11 21:22
 * @version: V1.0
 */
@Repository
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SsoUser getUserByUserName(String userName) {
        SsoUser ssoUser = new SsoUser();
        ssoUser.setUserName(userName);
        ssoUser.setPassword(passwordEncoder.encode("123"));
        List<String> permissions = Arrays.asList("p1", /*"p2",*/ "p3");
        ssoUser.setPermissions(permissions);
        return ssoUser;
    }
}
