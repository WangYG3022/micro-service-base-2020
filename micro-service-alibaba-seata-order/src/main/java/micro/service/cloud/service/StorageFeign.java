package micro.service.cloud.service;

import micro.service.cloud.entity.CommonResult;
import micro.service.cloud.entity.Storage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/09 23:08
 * @version: V1.0
 */
@FeignClient("seata-storage-service")
@RequestMapping("storage")
public interface StorageFeign {
    /**
     * 减库存
     *
     * @param productId
     * @param count
     * @return
     */
    @PostMapping(value = "decrease")
    CommonResult<Storage> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
