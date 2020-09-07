package micro.service.cloud.mapper;

import micro.service.cloud.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/09 23:06
 * @version: V1.0
 */
@Mapper
public interface OrderMapper {
    /**
     * 1 新建订单
     * @param order
     * @return
     */
    int create(Order order);

    /**
     * 2 修改订单状态,从0改为1
     * @param userId
     * @param status
     * @return
     */
    int update(@Param("userId") Long userId, @Param("status") Integer status);
}
