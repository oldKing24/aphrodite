package com.oldking.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wangzhiyong
 */
@FeignClient(name = "aphrodite-todo")
public interface TodoFeign {
    @GetMapping("/todo/test")
    String test();
}
