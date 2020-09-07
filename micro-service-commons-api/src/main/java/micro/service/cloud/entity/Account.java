package micro.service.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/10 21:10
 * @version: V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 总额度
     */
    private Integer total;
    /**
     * 已用额度
     */
    private Integer used;
    /**
     * 剩余额度
     */
    private Integer residue;
}
