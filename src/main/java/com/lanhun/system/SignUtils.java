package com.lanhun.system;

import com.lanhun.system.model.Request;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 签名工具类
 */
public class SignUtils {

    private static Logger logger=LoggerFactory.getLogger(SignUtils.class);
    /**
     * 对象转map
     * @param o
     * @return
     */
    public static Map<String, Object> toMap(Object o) {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean accessible = field.isAccessible();
            try {
                field.setAccessible(true);
                map.put(field.getName(), field.get(o));
                field.setAccessible(accessible);
            } catch (Exception e) {
                //IGNORE
            }

        }

        return map;
    }


    /**
     * 驼峰命名转为下划线命名
     * @param para 下划线命名的字符串
     * @return 驼峰命名的字符串
     */
    public static String humpToUnderline(String para){
        StringBuilder sb=new StringBuilder(para);
        int temp=0;//定位
        for(int i=0;i<para.length();i++){
            if(Character.isUpperCase(para.charAt(i))){
                sb.insert(i+temp, "_");
                temp+=1;
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * key命名样式更新
     * @param map
     */
    public static Map<String,Object> prepare(Map<String,Object> map){
        Map<String,Object> result=new HashMap<>();
        for(Entry<String,Object> entry:map.entrySet()){
          String key=   humpToUnderline(entry.getKey());
          result.put(key,entry.getValue());
        }
        return result;
    }
    /**
     * 签名
     */
    public static String sign(Request request) {
        List<Entry<String, Object>> sorted = new LinkedList<>();
        Map<String,Object> map=toMap(request);
        map=prepare(map);
        map.remove("sign");
        map.put("app_secret",OpenPlatformConfig.getAppSecret());
        map.entrySet().stream().sorted(Comparator.comparing(e -> e.getKey())).forEachOrdered(e -> sorted.add(e));
        StringBuilder paramStr=null;
        for(Entry<String,Object> e:sorted){
            if(paramStr==null){
                paramStr=new StringBuilder();
            }else{
                paramStr.append("&");
            }
            paramStr.append(e.getKey());
            paramStr.append("=");
            try{
                String val=  URLEncoder.encode(e.getValue().toString(),"utf-8");
                paramStr.append(val);
            }catch (Exception ex){
                //IGNORE
            }

        }
        logger.info("sign source: "+paramStr.toString());
        return md5(paramStr.toString()).toUpperCase();
    }

    /**
     * md5
     */
    public static String md5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());

            byte[] hash = md.digest();
            StringBuilder secpwd = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                int v = hash[i] & 0xFF;
                if (v < 16) {
                    secpwd.append(0);
                }
                secpwd.append(Integer.toString(v, 16));
            }
            return secpwd.toString();
        } catch (Exception e) {
            throw new RuntimeException("MD5加密出现错误");
        }

    }

}
