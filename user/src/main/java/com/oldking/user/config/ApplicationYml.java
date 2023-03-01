package com.oldking.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangzhiyong
 */
@Component
@ConfigurationProperties("ignore")
@Data
public class ApplicationYml {
    private List<String> paths;
}
