package micro.service.cloud.service.impl;

import lombok.extern.slf4j.Slf4j;
import micro.service.cloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/08/07 19:33
 * @version: V1.0
 */
@Slf4j
@EnableBinding(Source.class) // 定义消息的推送管道，即向消息队列输入消息
public class MessageProviderImpl implements IMessageProvider {

    @Autowired
    private MessageChannel output; // 消息发送通道

    @Override
    public String send() {
        String message = UUID.randomUUID().toString();
        log.info("发送消息：{}", message);
        output.send(MessageBuilder.withPayload(message).build());
        return message;
    }
}
