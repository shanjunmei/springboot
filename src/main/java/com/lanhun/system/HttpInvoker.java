package com.lanhun.system;


import com.lanhun.system.model.BusinessResponse;
import com.lanhun.system.model.Request;
import com.lanhun.system.model.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * http 请求
 */
@Component
@SuppressWarnings("unchecked")
public class HttpInvoker {

    private static Logger logger = LoggerFactory.getLogger(HttpInvoker.class);

    @Autowired
    private OpenPlatformConfig openPlatformConfig;

    /**
     * post 请求，默认请求头
     */
    public static String post(String url, byte[] params) {
        return request(url, params, null, "POST");
    }

    /**
     * http post 请求，返回String
     */
    public static String request(String url, byte[] params, Map<String, String> header, String method) {
        //request
        try {
            URL _url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            if (header != null) {
                for (Entry<String, String> entry : header.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());//请求头覆盖
                }
            }
            if (params != null) {
                connection.setDoOutput(true);
            }
            connection.connect();
            if (params != null) {
                connection.getOutputStream().write(params);
            }
            StringBuilder body = null;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                while (bufferedReader.ready()) {
                    if (body == null) {
                        body = new StringBuilder();
                    }
                    String line = bufferedReader.readLine();
                    body.append(line);
                }
                if (body == null) {
                    body = new StringBuilder();
                }
                return body.toString();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 构建公共响应体
     */
    public static <T> Response<T> buildCommonResponse(String body) {
        Type type = Response.class;
        return JsonMapper.from(type, body);
    }

    /**
     * 构建响应对象
     */
    public static <T> T buildResponse(Type type, String body) {
        if (type == void.class) {
            return null;
        } else if (type == String.class) {
            return (T) body;
        } else {
            return JsonMapper.from(type, body);
        }

    }

    /**
     * 请求参数转换
     */
    public byte[] convertParamter(String method, String[] paramNames, Object[] args) {
        String paramBody;
        if (args.length == 1) {
            paramBody = JsonMapper.toJsonString(args[0]);
        } else if (args.length > 1) {
            Map<String, Object> paramsMap = new HashMap<>();
            for (int i = 0; i < paramNames.length; i++) {
                String key = paramNames[i];
                Object val = args[i];
                paramsMap.put(key, val);
            }
            paramBody = JsonMapper.toJsonString(paramsMap);
        } else {
            paramBody = null;
        }

        Request request = new Request();
        request.setMethod(method);

        request.setAccessToken(openPlatformConfig.getAccessToken());
        request.setAppId(openPlatformConfig.getAppId());
        request.setBody(paramBody);
        request.setSignType(openPlatformConfig.getSignType());
        request.setVersion(openPlatformConfig.getVersion());
        request.setTimestamp(new Date().getTime());
        String sign = SignUtils.sign(request, openPlatformConfig.getAppSecret());
        request.setSign(sign);
        logger.info("request body :"+JsonMapper.toJsonString(request));
        return JsonMapper.toJson(request);
    }

    /**
     * 远程方法调用
     */

    public <T> T invoke(RemoteMethod method, Object[] args) {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("zb-shop-id",openPlatformConfig.getShopId());
        header.put("zb-branch-id",openPlatformConfig.getBranchId());
        String gateway = method.getGateway();
        if (gateway == null || gateway.trim().length() == 0) {
            gateway = openPlatformConfig.getGateway();
        }
        String response = request(gateway, convertParamter(method.getCommond(), method.getParamNames(), args), header, "POST");
        logger.info("invoke result:"+response);
        Response<String> stringResponse = buildCommonResponse(response);
        BusinessResponse<Object> businessResponse = JsonMapper.from(BusinessResponse.class, stringResponse.getBody());
        if (businessResponse.getData() instanceof String) {
            return JsonMapper.from(method.getReturnType(), (String) businessResponse.getData());
        } else {
            return (T) buildResponse(method.getReturnType(), JsonMapper.toJsonString(businessResponse.getData()));
        }
    }
}
