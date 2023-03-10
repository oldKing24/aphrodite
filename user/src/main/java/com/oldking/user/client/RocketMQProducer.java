package com.oldking.user.client;

import com.oldking.user.request.export.BaseExportRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangzhiyong
 */
@Component
@Slf4j
public class RocketMQProducer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendExportMsg(String topic, String tag, String extra) {
        log.info("rocketMQ product topic:{},tag:{},extra:{}", topic, tag, extra);
        String topicAndTags = topic + ":" + tag;
        this.rocketMQTemplate.convertAndSend(topicAndTags, extra);
    }
}
