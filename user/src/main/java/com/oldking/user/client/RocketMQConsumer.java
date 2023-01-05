package com.oldking.user.client;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author wangzhiyong
 */
@Component
@RocketMQMessageListener(topic = "test", consumerGroup = "${rocketmq.consumer.group}",selectorExpression = "*")
public class RocketMQConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("接收到rocketmq消息：" + s);
    }
}
