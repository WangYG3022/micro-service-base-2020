package micro.service.cloud.mapper;

import micro.service.cloud.entity.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/01 22:47
 * @version: V1.0
 */
@Repository
public interface PaymentMapper {

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
    Payment getPaymentById(@Param("id") Long id);
}
