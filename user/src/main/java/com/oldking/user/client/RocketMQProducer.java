package com.oldking.user.client;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangzhiyong
 */
@Component
public class RocketMQProducer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMsg(String topic, String msg) {
        System.out.println("发送报文：" + msg);
        this.rocketMQTemplate.convertAndSend(topic, msg);
    }
}
