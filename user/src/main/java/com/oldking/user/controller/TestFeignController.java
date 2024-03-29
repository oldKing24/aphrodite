package com.oldking.user.controller;

import com.oldking.user.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

/**
 * @author wangzhiyong
 */
@RestController
@RequestMapping("/feign")
public class TestFeignController {
    @Autowired
    private TestService testService;
//    @Autowired
//    private TodoFeign todoFeign;

    @GetMapping("/test")
    public void test() {
        int count = 1;
        try {
            for (int i = 0; i < count; i++) {
                testService.doSomething("index=" + i);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("-----");
        }
    }
}
