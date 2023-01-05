package com.oldking.todo.controller;

import com.oldking.todo.utils.ConverterFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wangzhiyong
 */
@RestController
@RequestMapping("/todo")
public class TodoController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/test")
    public String test() {
        return "当前应用端口号：" + port;
    }

    @GetMapping("/test1")
    public String test1() {
        Map<String, String> converter = ConverterFactory.converter;
        System.out.println(converter.get("1"));
        return "当前应用端口号：" + port;
    }

    public static void main(String[] args) {

    }
}
