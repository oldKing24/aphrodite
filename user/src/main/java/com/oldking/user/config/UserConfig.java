package com.oldking.user.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author wangzhiyong
 */
@Component
@RefreshScope
@Data
public class UserConfig {
    @Value("${test}")
    private String test;
}
