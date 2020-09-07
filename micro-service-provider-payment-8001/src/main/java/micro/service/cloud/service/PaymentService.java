package micro.service.cloud.service;

import micro.service.cloud.entity.Payment;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/01 23:03
 * @version: V1.0
 */
public interface PaymentService {
    /**
     * 新增记录
     * @param payment
     * @return
     */
    int create(Payment payment);

    /**
     * 按主键查询
     * @param id
     * @return
     */
    Payment getPaymentById(Long id);
}
