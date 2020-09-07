package micro.service.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/08 21:22
 * @version: V1.0
 */
@Slf4j
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
//        try {
//            TimeUnit.MILLISECONDS.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "testA-" + LocalDateTime.now();
    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "...testB ");
        return "testB-" + LocalDateTime.now();
    }

    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testD 测试RT");
        return "testD -----";
    }

    private AtomicInteger atom = new AtomicInteger(0);

    @GetMapping("/testExceptionRatio")
    public String testException() {
        log.info("testException 异常比例");
        // 模拟每4次请求，发生1次异常，比例25%
        if (atom.incrementAndGet() % 4 == 0) {
            throw new RuntimeException("num=" + atom.get());
        }
        return "testExceptionRatio - atom=" + atom.get();
    }

    @GetMapping("/testExceptionCount")
    public String testExceptionCount() {
        log.info("testExceptionCount 异常数");
        int age = 10 / 0;
        return "testExceptionCount -----";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "dealTestHotKey") // 不带blockHandler时，如果触发热点限流，会返回ERROR PAGE错误页面
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        int age = 10 / 0;
        return "testHotKey -----";
    }

    public String dealTestHotKey(String p1, String p2, BlockException blockException) {
        return "dealTestHotKey---------";
    }
}
