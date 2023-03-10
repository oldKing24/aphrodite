package com.oldking.user.controller;

import com.oldking.response.AphroditeResponse;
import com.oldking.user.client.RocketMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhiyong
 */
@RestController
@RequestMapping("/mq")
@Slf4j
public class TestRocketMQController {
    @Autowired
    private RocketMQProducer rocketMQProducer;

    @GetMapping("/send")
    public AphroditeResponse<Long> sendMsg(@RequestParam("msg") String msg) {
        rocketMQProducer.sendExportMsg("test", "test", msg);
        return AphroditeResponse.success();
    }
}
