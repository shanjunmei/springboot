package com.lanhun.system;

/**
 * 开放平台配置
 */
public class OpenPlatformConfig {
    private static String appId="xxx";
    private static String appSecret="xxx";
    private static String signType="md5";
    private static String accessToken="xx";
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
