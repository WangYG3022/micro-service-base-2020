package micro.service.cloud.service;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/01 23:03
 * @version: V1.0
 */
public interface PaymentService {
    /**
     * 正常访问
     * @param id
     * @return
     */
    String paymentOK(Long id);

    /**
     * 模拟超时
      * @param id
     * @return
     */
    String paymentTimeout(Long id);

    String paymentCircuitBreaker(Long id);
}
