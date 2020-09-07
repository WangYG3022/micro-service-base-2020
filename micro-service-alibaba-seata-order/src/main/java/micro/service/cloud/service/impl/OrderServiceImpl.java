package micro.service.cloud.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import micro.service.cloud.entity.Order;
import micro.service.cloud.mapper.OrderMapper;
import micro.service.cloud.service.AccountFeign;
import micro.service.cloud.service.OrderService;
import micro.service.cloud.service.StorageFeign;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/09 23:08
 * @version: V1.0
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private StorageFeign storageFeign;
    @Resource
    private AccountFeign accountFeign;

    @GlobalTransactional(name = "global-create-order", rollbackFor = Exception.class)
    @Override
    public void create(Order order) {
        // 1 新建订单
        log.info("----->开始新建订单");
        orderMapper.create(order);

        // 2 扣减库存
        log.info("----->订单微服务开始调用库存,做扣减Count");
        storageFeign.decrease(order.getProductId(), order.getCount());
        log.info("----->订单微服务开始调用库存,做扣减End");

        // 3 扣减账户
        log.info("----->订单微服务开始调用账户,做扣减Money");
        accountFeign.decrease(order.getUserId(), order.getMoney());
        log.info("----->订单微服务开始调用账户,做扣减End");

        // 4 修改订单状态,从0到1,1代表已完成
        log.info("----->修改订单状态开始");
        orderMapper.update(order.getUserId(), 0);

        log.info("----->下订单结束了,O(∩_∩)O哈哈~");
    }
}
