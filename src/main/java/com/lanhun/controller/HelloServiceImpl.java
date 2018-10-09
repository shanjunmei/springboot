package com.lanhun.controller;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return name +" hello";
    }
}
