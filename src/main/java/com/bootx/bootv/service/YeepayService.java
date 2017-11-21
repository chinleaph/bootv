package com.bootx.bootv.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yeepay.g3.sdk.yop.client.YopClient3;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;

import java.util.*;

public class YeepayService {
    static String appKey = "SQKK10015764273";
    static String secretKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3rvNkUgSYNQwE2sw/Z4R6e3wY3A+CQE8QARgBSOr7x6ODIhHvGLzOKPgQb0vyYEzscTWBBnTp8G/IHmSIMg18xWYZDk6Iukt5dyNLjsqV48b9LZUkxb0WYFdi8CPRAYUGK88i60EAzx713FUQSG4hSsQTDwfl2+F9xjACSoSANknK5L3MQ6BQAu0mNuX8cHAR2m85I2nJX+PvdTrJsyh1PGZuB83SE6IImiGDI7Ze0y8fQ4rCxdmqHpQxbaHThDZl3/b5lZYC93uI2FdDuU78EZWNil0QXx7Er2OGBqA08536GFUDyOzDQqQPjGytSkFjKw2ekYqpERDNwyNGoEWJAgMBAAECggEBAKPniWhLh64KJ99tHjS1upFQ/ztvoqcnm2U8lJ8Q4h4p1s586GPiPxf3luOTbMGrDPkvS/8GM+1Mj3KTLEIb580emCxjh9CZ7B9HIEeb80otJrNkqWFW0ZPWBCRVBKDMKleHYB5Yrnh6WbYqFf2hpCQxAyW+k92Yh24kG1LwC6wbPTKnWdR5lwLUxtGlH8jkAbNk3H8XtMTIrryWtd56wd1bNIBbu6k1nIW9r7hon0rN2+rKDiQOACVOx5fGkXWUVtAqevPPf9KJoWwYFReVv3gihflL3s3bekiipDdUNKXsxt2OCpvs5Vq1y854saAJx5s+owMsbx40/IkVOqCnewECgYEA/0vjUnKd3Kt7w7mjSImubCzX3rmupa9UYpNIQoarIYJ0kdvSVGiGf8MhnAVp9EVX2S3TS5eSj9srlODdtbyyUig8mJDUBXQI8QnkevNyLCnZsC9Euc35qfmR3eoZfLN1BKq9zdS+7TcS0gH5jB7Ic8huxwH2sa1bmdEBQz4KEjUCgYEAuDCKJ6OGnaBGHxmYgvtAUqiJLw2w6hRQD2FVuQ/mDkRkJ2EJ/wdrEZ83/V/r8aR0pwNF1TR2cHThkheOjQ9wsL2q4nGs9OsQflqPRscFIGEF9wlxumZ+EzVPnXMONzaLXEJN9HljyQW6rvGbj9THQBl7ftjFxdDBdv7rvlFrkIUCgYB3NW5eb1q7Ue+mwyY1luS1anMac0XT4/zZUsCpH80FBq979FNQSsaTHgCxo+2HvGF67eT+JTawqby99gpQ9F2n81XoP6hhK3+JXf8sUkT7zkbx+AB1anKsJfcw0kE4c2uu1DNwy2ijx9Wn7ngiL7WWKv9fOdGBwdtGBcw7I9r8CQKBgED8h1IzBSUKTpNcgY39PhWr9zMN3pTIc/LpQciYzm/MO5YVS+SaZnGCPsv8ExBgGPRwWCxAZWWKJ6hW/WnRv83sLaXVu9NyOJCMPS8YuztQYD1oLKM61FLMpXmFBcf/S65SipSuW8GqbtKY1m0qAt8f3o3DyAvietAUY3Lz4ULNAoGAQ5AaeOXRCxnArCsxrInOf0cIEUp43TcdnqhIzy7wL+q4pNXM3NN3A582RZTYN84aV+cFNLwLKjx/GS9l2okR4IGju+3jwoKWjwmpk6ztGo5bxDEblfAzYoBlT0m4Yd7OmNgAvsNjpcsYFjteFRqoGyfe41Alxw6Y0mnNRa7mZB8=";
    static String serverRoot= "https://open.yeepay.com/yop-center";


    public static Map<String,String> yeepayYOP(Map<String,String> map, String Uri){

        System.out.println("********"+secretKey);

        YopRequest yoprequest  =  new YopRequest(appKey,secretKey,serverRoot);
        Map<String,String> result  =  new HashMap<String,String>();

        Set<Map.Entry<String, String>> entry = map.entrySet();
        for(Map.Entry<String,String> s:entry){
            yoprequest.addParam(s.getKey(), s.getValue());
        }
        System.out.println("yoprequest:"+yoprequest.getParams());

        //向YOP发请求
        YopResponse yopresponse = YopClient3.postRsa(Uri, yoprequest);
        System.out.println("请求YOP之后结果："+yopresponse.toString());
        System.out.println("请求YOP之后结果："+yopresponse.getStringResult());



//        	对结果进行处理
        if("FAILURE".equals(yopresponse.getState())){
            if(yopresponse.getError() != null)
                result.put("errorcode",yopresponse.getError().getCode());
            result.put("errormsg",yopresponse.getError().getMessage());
            System.out.println("系统处理异常结果："+result);
            return result;
        }
        //成功则进行相关处理
        if (yopresponse.getStringResult() != null) {
            result = parseResponse(yopresponse.getStringResult());

            System.out.println("yopresponse.getStringResult: "+result);

        }

        return result;
    }



    //将获取到的yopresponse转换成json格式
    public static Map<String, String> parseResponse(String yopresponse){

        Map<String,String> jsonMap  = new HashMap<>();
        jsonMap	= JSON.parseObject(yopresponse,
                new TypeReference<TreeMap<String,String>>() {});
        System.out.println("将结果yopresponse转化为map格式之后: "+jsonMap);
        return jsonMap;
    }
}
