package micro.service.cloud.controller;

import micro.service.cloud.entity.Account;
import micro.service.cloud.entity.CommonResult;
import micro.service.cloud.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/10 21:14
 * @version: V1.0
 */
@RestController
@RequestMapping("account")
public class AccountController {
    @Resource
    private AccountService accountService;

    @PostMapping(value = "decrease")
    public CommonResult<Account> decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        accountService.decrease(userId, money);
        return new CommonResult<>(200, "扣减账户余额成功");
    }
}
