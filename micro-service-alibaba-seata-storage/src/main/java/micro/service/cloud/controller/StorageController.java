package micro.service.cloud.controller;

import micro.service.cloud.entity.CommonResult;
import micro.service.cloud.entity.Storage;
import micro.service.cloud.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/10 21:03
 * @version: V1.0
 */
@RestController
@RequestMapping("storage")
public class StorageController {

    @Resource
    private StorageService storageService;

    /**
     * 减库存
     *
     * @param productId
     * @param count
     * @return
     */
    @PostMapping(value = "decrease")
    public CommonResult<Storage> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult<>(200, "扣减库存成功");
    }
}
