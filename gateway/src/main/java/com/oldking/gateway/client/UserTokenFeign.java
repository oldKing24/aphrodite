package com.oldking.gateway.client;

import com.oldking.response.AphroditeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangzhiyong
 */
@FeignClient(name = "aphrodite-user")
public interface UserTokenFeign {
    @GetMapping("/token/get")
    AphroditeResponse<String> test();

    @PostMapping("/token/valid")
    AphroditeResponse<Void> valid(@RequestParam("token") String token);

}
