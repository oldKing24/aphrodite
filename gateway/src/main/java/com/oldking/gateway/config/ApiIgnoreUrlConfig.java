package com.oldking.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;

/**
 * @author wangzhiyong
 */
@Data
@Component
@ConfigurationProperties("api")
public class ApiIgnoreUrlConfig {
    private LinkedHashSet<String> ignoreUrl;
}
