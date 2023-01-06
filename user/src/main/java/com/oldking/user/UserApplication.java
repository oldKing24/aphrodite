package com.oldking.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = "com.oldking.user.mapper")
public class UserApplication {

    public static void main(String[] args) {
        //在main方法里插入下面的代码以解决es的netty冲突问题
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(UserApplication.class, args);
    }
}
