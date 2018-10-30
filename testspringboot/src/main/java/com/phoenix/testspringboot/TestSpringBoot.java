package com.phoenix.testspringboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSpringBoot {

    @RequestMapping(value = "/say")
    public String say(){
        String hello = "你好 Spring Boot";
        return hello;
    }
}
