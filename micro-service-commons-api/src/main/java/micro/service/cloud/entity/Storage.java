package micro.service.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/10 20:59
 * @version: V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 总库存
     */
    private Integer total;
    /**
     * 已用库存
     */
    private Integer used;
    /**
     * 剩余库存
     */
    private Integer residue;
}
