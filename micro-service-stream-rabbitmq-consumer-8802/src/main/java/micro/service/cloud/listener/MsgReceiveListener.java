package micro.service.cloud.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/07 20:35
 * @version: V1.0
 */
@Slf4j
@Component
@EnableBinding(Sink.class) // 消息消费方，即从队列输出消息
public class MsgReceiveListener {

    @Value("${server.port}")
    private String port;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        log.info("端口{}接收到消息：{}", port, message.getPayload());
    }
}
