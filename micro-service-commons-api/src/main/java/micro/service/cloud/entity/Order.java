package micro.service.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/09 22:55
 * @version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 数量
     */
    private Integer count;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 订单状态 0:创建中,1:已完结
     */
    private Integer status;
}
