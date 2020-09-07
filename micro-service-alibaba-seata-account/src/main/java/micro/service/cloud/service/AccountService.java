package micro.service.cloud.service;

import java.math.BigDecimal;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/10 21:13
 * @version: V1.0
 */
public interface AccountService {
    /**
     * 减库存
     *
     * @param userId 用户id
     * @param money  金额
     * @return
     */
    void decrease(Long userId, BigDecimal money);
}
