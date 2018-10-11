package com.lanhun.example;

import com.lanhun.example.model.CancelOrderRequest;
import com.lanhun.system.RemoteClient;
import com.lanhun.system.model.Branch;
import java.util.List;
import java.util.Map;

//@RemoteClient(gateway = "http://api.open.dev-a.saofu.cn/gateway/")
public interface RemoteExampleService {

    @RemoteClient("branch.list")
    List<Branch> queryBranchList(Map<String, Object> _paramsMap);

    /**
     * 撤单
     * @param request
     */
    @RemoteClient("order.retreat")
    void cancelOrder(CancelOrderRequest request);
}
