package com.hjt.web;

import com.hjt.web.dto.HelloResponseDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(String name, int amount) {
        return new HelloResponseDto(name, amount);
    }
}
