package micro.service.cloud.service;

import micro.service.cloud.entity.Account;
import micro.service.cloud.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/10 20:12
 * @version: V1.0
 */
@FeignClient("seata-account-service")
@RequestMapping("account")
public interface AccountFeign {
    /**
     * 减账户余额
     *
     * @param userId
     * @param money
     * @return
     */
    @PostMapping(value = "decrease")
    CommonResult<Account> decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
