package micro.service.cloud.service.impl;

import lombok.extern.slf4j.Slf4j;
import micro.service.cloud.mapper.AccountMapper;
import micro.service.cloud.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/10 21:13
 * @version: V1.0
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("seata-account-service---->扣减账户余额---->START");
        // 模拟超时异常,全局事务回滚
        /*try {
            // 暂停20秒钟
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        accountMapper.decrease(userId, money);
        log.info("seata-account-service---->扣减账户余额---->END");
    }
}
