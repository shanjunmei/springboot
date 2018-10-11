package com.lanhun.example;

import com.lanhun.system.RemoteClient;
import com.lanhun.system.model.Branch;
import java.util.List;
import java.util.Map;

@RemoteClient(gateway = "http://api.open.dev-a.saofu.cn/gateway/")
public interface RemoteExampleService {

    @RemoteClient("branch.list")
    List<Branch> invoke(Map<String, Object> _paramsMap);
}
