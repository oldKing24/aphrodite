package com.oldking.user.client;

import com.alibaba.fastjson.JSONObject;
import com.oldking.user.request.export.BaseExportRequest;
import com.oldking.user.service.export.ExportComposite;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangzhiyong
 */
@Component
@RocketMQMessageListener(topic = "test", consumerGroup = "${rocketmq.consumer.group}", selectorExpression = "user||course||test")
@Slf4j
public class ExportConsumer implements RocketMQListener<String> {
    @Autowired
    private ExportComposite exportComposite;

    @Override
    public void onMessage(String s) {
        log.info("接收到rocketmq消息：{}", s);
        BaseExportRequest request = JSONObject.parseObject(s, BaseExportRequest.class);
        if (request == null || StringUtils.isBlank(request.getType())) {
            log.info("消息类型不正确，忽略消息");
            return;
        }
        exportComposite.doJob(request.getType(), request.getTaskId(), s);
    }
}
