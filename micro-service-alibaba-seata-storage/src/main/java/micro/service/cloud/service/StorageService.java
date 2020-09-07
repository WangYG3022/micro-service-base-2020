package micro.service.cloud.service;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/10 21:01
 * @version: V1.0
 */
public interface StorageService {
    /**
     * 减库存
     *
     * @param productId
     * @param count
     * @return
     */
    void decrease(Long productId, Integer count);
}
