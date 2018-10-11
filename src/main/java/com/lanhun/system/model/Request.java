package com.lanhun.system.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 开放平台请求体
 */
public class Request {

    //访问令牌
    @JsonProperty("access_token")
    private String accessToken;
    //应用标识
    @JsonProperty("app_id")
    private String appId;
    //业务参数
    private String body;
    //业务方法
    private String method;
    //签名
    private String sign;
    //签名类型(默认md5)
    @JsonProperty("sign_type")
    private String signType = "md5";
    //时间戳
    private Long timestamp;
    //版本（默认2.0）
    private String version = "2.0";

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
