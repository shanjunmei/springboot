package com.lanhun.system;

import com.lanhun.system.model.Request;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * http 请求
 */
public class HttpInvoker {

    /**
     * 请求参数转换
     * @param paramNames
     * @param args
     * @return
     */
    public static byte[] convertParamter(String method,String[]  paramNames,Object[] args){
        Map<String,Object> paramsMap=new HashMap<>();
        for(int i=0;i<paramNames.length;i++){
            String key=paramNames[i];
            Object val=args[i];
            paramsMap.put(key,val);
        }
        Request request=new Request();
        /*
        request.setAccessToken("");
        request.setAppId("");
        request.setBody("");
        request.setMethod("");
        request.setSignType("");
        */
        request.setTimestamp(new Date().getTime());
        request.setVersion("2.0");
        String sign=SignUtils.sign(request);
        request.setSign(sign);
        return null;
    }

    /**
     * 远程方法调用
     * @param method
     * @param args
     * @param <T>
     * @return
     */
    public static <T> T invoke(RemoteMethod method,Object[] args){
        String response= post(method.getCommond(),convertParamter(method.getCommond(),method.getParamNames(),args));
        return buildResponse(method.getReturnType(), response);
    }

    /**
     * http post 请求，返回String
     * @param url
     * @param params
     * @return
     */
    public static String  post(String url, byte[] params) {
        //request
        try {
           URL _url = new URL(url);
            HttpURLConnection connection= (HttpURLConnection)_url.openConnection();
           connection.connect();
            connection.setRequestMethod("post");
          //  connection.addRequestProperty(,"","");

           connection.setDoInput(true);
           connection.setDoOutput(true);

           connection.getOutputStream().write(params);
           StringBuilder body=null;
           BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));

           while(bufferedReader.ready()){
               if(body==null){
                   body=new StringBuilder();
               }
               String line=bufferedReader.readLine();
               body.append(line);
           }

            if(body==null){
               body=new StringBuilder();
            }
            return body.toString();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    /**
     * 构建响应对象
     * @param type
     * @param body
     * @param <T>
     * @return
     */
    public static <T> T buildResponse(Type type,String body){
        if(type==void.class){
            return null;
        }else if(type==String.class){
            return (T)body;
        }else{
            //TODO convert
            return null;
        }

    }
}
