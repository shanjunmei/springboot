package com.lanhun.system;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * http 请求
 */
public class HttpInvoker {

    /**
     * http post 请求，带返回值类型
     * @param url
     * @param returnType
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T post(String url, Type returnType, Object params) {
        //request
        try {
           URL _url = new URL(url);
            HttpURLConnection connection= (HttpURLConnection)_url.openConnection();
           connection.connect();
            connection.setRequestMethod("post");
          //  connection.addRequestProperty(,"","");

           connection.setDoInput(true);
           connection.setDoOutput(true);

           connection.getOutputStream().write(null);
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
            return buildResponse(returnType, body.toString());
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
