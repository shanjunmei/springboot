package com.lanhun.system;

import java.lang.reflect.Type;

/**
 * 远程方法对象
 */
public class RemoteMethod {

    //网关
    private String gateway;

    //远程方法名
    private String commond;
    //参数名
    private String[] paramNames;

    //参数类型
    private Type[] paramTypes;

    //返回值类型
    private Type returnType;

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getCommond() {
        return commond;
    }

    public void setCommond(String commond) {
        this.commond = commond;
    }

    public String[] getParamNames() {
        return paramNames;
    }

    public void setParamNames(String[] paramNames) {
        this.paramNames = paramNames;
    }

    public Type[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Type[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }
}
