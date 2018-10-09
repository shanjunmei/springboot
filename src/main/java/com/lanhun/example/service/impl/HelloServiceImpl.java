package com.lanhun.example.service.impl;


import com.lanhun.example.RemoteExampleService;
import com.lanhun.example.service.HelloService;
import com.lanhun.system.RemoteClient;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {

    @RemoteClient
    private RemoteExampleService remoteExampleService;

    @Override
    public String hello(String name) {
        remoteExampleService.invoke(name);
        return name +" Hello";
    }
}
