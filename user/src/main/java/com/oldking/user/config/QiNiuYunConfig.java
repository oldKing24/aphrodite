package com.oldking.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangzhiyong
 */
@Component
@ConfigurationProperties("qiniuyun")
@Data
public class QiNiuYunConfig {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String tmpDir;
    private String previewUrl;
}
