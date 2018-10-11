package com.lanhun.system;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 开放平台配置
 */
@Component
@ConfigurationProperties(prefix = "open.platform.config")
public class OpenPlatformConfig {
    private  String appId;//="c29d14d06d15053270c65a25fc4a48a8"
    private  String appSecret;//="89e80649-d8e7-3569-8843-b6ff72dc37f7";
    private  String signType;//="md5";
    private  String accessToken;//="1cbbaf60e5b3df6dc84977d5247a7766";
    private  String version;//="2.0";
    private String gateway;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }
}
