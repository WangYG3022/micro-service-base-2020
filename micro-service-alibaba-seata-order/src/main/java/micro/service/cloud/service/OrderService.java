package micro.service.cloud.service;

import micro.service.cloud.entity.Order;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/09 23:07
 * @version: V1.0
 */
public interface OrderService {
    /**
     * 创建订单
     * @param order
     */
    void create(Order order);
}
