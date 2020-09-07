package micro.service.cloud.service.impl;

import lombok.extern.slf4j.Slf4j;
import micro.service.cloud.mapper.StorageMapper;
import micro.service.cloud.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/10 21:02
 * @version: V1.0
 */
@Slf4j
@Service
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageMapper storageMapper;

    /**
     * 减库存
     *
     * @param productId
     * @param count
     * @return
     */
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("seata-storage-service------>扣减库存---->START");
        storageMapper.decrease(productId, count);
        log.info("seata-storage-service------>扣减库存---->END");
    }
}
