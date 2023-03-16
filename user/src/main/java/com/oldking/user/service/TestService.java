package com.oldking.user.service;

import com.oldking.user.client.TodoFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author wangzhiyong
 */
@Service
@Slf4j
public class TestService {
    @Autowired
    private TodoFeign todoFeign;

    // 指定使用beanname为doSomethingExecutor的线程池
    @Async("doSomethingExecutor")
    public String doSomething(String message) {
//        String test = todoFeign.test();
//        log.info("do something, message={},test={}", message, test);
        throw new IllegalArgumentException("111");
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            log.error("do something error: ", e);
//        }
//        return message;
    }
}
