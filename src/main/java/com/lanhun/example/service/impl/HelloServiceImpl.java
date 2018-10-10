package com.lanhun.example.service.impl;


import com.lanhun.example.RemoteExampleService;
import com.lanhun.example.service.HelloService;
import com.lanhun.system.RemoteClient;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {

    @RemoteClient
    private RemoteExampleService remoteExampleService;

    @Override
    public String hello(String name) {
        Map<String,Object> param=new HashMap<>();
        param.put("name","深圳");
        param.put("page_no",1);
        param.put("page_size",10);
       String remoteResult= remoteExampleService.invoke(param);
        return name +" Hello ,remote result:"+remoteResult;
    }
}
