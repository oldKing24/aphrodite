package com.oldking.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisKeyExpiredEvent;
import org.springframework.stereotype.Component;

/**
 * @author wangzhiyong
 */
@Component
@Slf4j
public class RedisKeyExpiredEventListener implements ApplicationListener<RedisKeyExpiredEvent> {
    @Override
    public void onApplicationEvent(RedisKeyExpiredEvent event) {
        byte[] body = event.getSource();
        log.info("获取到延迟消息：" + new String(body));
    }
}
