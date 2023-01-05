package com.oldking.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangzhiyong
 */
@Data
@Component
@ConfigurationProperties("send.email")
public class SendEmailConfig {
    private String host;
    private int port;
    private String from;
    private String user;
    private String pass;
    private Integer expireTime = 1;
}
