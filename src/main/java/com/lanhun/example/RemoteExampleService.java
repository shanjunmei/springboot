package com.lanhun.example;

import com.lanhun.system.RemoteClient;
import java.util.Map;

@RemoteClient(gateway ="https://api.open.saofu.cn/gateway/" )
public interface RemoteExampleService {

    @RemoteClient("branch.list")
    String invoke(Map<String,Object> args);
}
