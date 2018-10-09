package com.lanhun.example;

import com.lanhun.system.RemoteClient;

public interface RemoteExampleService {

    @RemoteClient("hello")
    public String invoke(String args);
}
