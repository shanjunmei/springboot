package com.lanhun.example.service.impl;


import com.lanhun.example.RemoteExampleService;
import com.lanhun.example.dao.DataFlowMapper;
import com.lanhun.example.model.DataFlow;
import com.lanhun.example.service.HelloService;
import com.lanhun.system.JsonMapper;
import com.lanhun.system.RemoteClient;
import com.lanhun.system.model.Branch;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {

    private Logger logger=LoggerFactory.getLogger(getClass());

    @RemoteClient
    private RemoteExampleService remoteExampleService;

    @Autowired
    private DataFlowMapper dataFlowMapper;

    @Override
    public String hello(String name) {

      List<DataFlow> data=  dataFlowMapper.list();

        logger.info("data.size:"+data.size());

        Map<String,Object> param=new HashMap<>();
/*
        param.put("name","深圳");
*/
        param.put("page_no",1);
        param.put("page_size",10);
        List<Branch> remoteResult= remoteExampleService.invoke(param);
        return name +" Hello ,Remote Result:"+JsonMapper.toJsonString(remoteResult);
    }
}
