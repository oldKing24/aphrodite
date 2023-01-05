package com.oldking.user.controller;

import com.oldking.user.config.UserConfig;
import com.oldking.user.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhiyong
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class TestConfigController {
    @Autowired
    private UserConfig userConfig;

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public String test() {
        return userConfig.getTest();
    }
}
