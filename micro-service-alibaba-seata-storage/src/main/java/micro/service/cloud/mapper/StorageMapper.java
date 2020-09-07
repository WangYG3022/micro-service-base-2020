package micro.service.cloud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/10 21:01
 * @version: V1.0
 */
@Mapper
public interface StorageMapper {
    /**
     * 减库存
     *
     * @param productId
     * @param count
     * @return
     */
    int decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
