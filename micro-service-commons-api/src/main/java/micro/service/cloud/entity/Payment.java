package micro.service.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: 支付实体类
 * @author: WANG Y.G.
 * @time: 2020/08/01 22:39
 * @version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 支付流水号
     */
    private String serial;
}
