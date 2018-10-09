package com.lanhun.example.service.impl;


import com.lanhun.example.service.HelloService;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return name +" hello";
    }
}
