package com.lanhun.system;

/**
 * 开放平台配置
 */
public class OpenPlatformConfig {
    private static String appId="c29d14d06d15053270c65a25fc4a48a8";
    private static String appSecret="89e80649-d8e7-3569-8843-b6ff72dc37f7";
    private static String signType="md5";
    private static String accessToken="1cbbaf60e5b3df6dc84977d5247a7766";
    private static String version="2.0";

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        OpenPlatformConfig.appId = appId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public static void setAppSecret(String appSecret) {
        OpenPlatformConfig.appSecret = appSecret;
    }

    public static String getSignType() {
        return signType;
    }

    public static void setSignType(String signType) {
        OpenPlatformConfig.signType = signType;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        OpenPlatformConfig.accessToken = accessToken;
    }

    public static String getVersion() {
        return version;
    }

    public static void setVersion(String version) {
        OpenPlatformConfig.version = version;
    }
}
