package micro.service.cloud.service.impl;

import micro.service.cloud.entity.Payment;
import micro.service.cloud.mapper.PaymentMapper;
import micro.service.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/01 23:03
 * @version: V1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public int create(Payment payment) {
        return paymentMapper.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentMapper.getPaymentById(id);
    }
}
