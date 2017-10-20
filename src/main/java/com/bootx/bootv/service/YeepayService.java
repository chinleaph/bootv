package com.bootx.bootv.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yeepay.g3.sdk.yop.client.YopClient3;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;

import java.util.*;

public class YeepayService {
    static String appKey = "SQKK10000469946";
    static String secretKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCxvbcO80BpbiirZUOtTOUfTQroEpbT6cSpG1EJ+wfjzc/N85FiMfIpnUFPdsZOT+tiMljVdV8JWjz/QtvhxWY4wwkReymXzxaIusxfslWH7h32qnJ0UP7TgjvDRmewKJrOSmHUS1ImWp4qb1X6DU4wxdP7c/OYaqkljBpJpVqJ4UU9DcDCzsyWwdq8i7IJmMbP6OYt8KGEfeQdkbdxUqNZnxvi25uCRgniUpcApfXFptONNCF/1/7qU3X0Vb4RG6KleZo9YnqP2YSwrRm9TgZ7MfxjDKyZbTP9Pe31bwNf7gfLLgUu4r4OGyNssR1aD648HfM3rDjclJepC1AiT+n7AgMBAAECggEBAIra+mr8kGKNQ8p6pv95Zjoo1w9sjlZpd1DhXVMdwREv1VtBIGAFQvlNuBsbYFsHxo0FZi8ErcVBsQt+MQdVTsGjZK86d8j4aNNSk51jVbyGwvPUPuwt5pZYVNX+Z7zQS/hDVeAjI7+A5bWjGxjpUh96PVxhPnnhQdlqdZL5Uh0KCP7BmFQ9NSW6EotSrbWVDbWW6Bzke9xnEYa7h2QkdwrzitF+HjkuwQsAgAAsN2bMzkn65fpYTI9LH18KBID1763WBlKC5UYB7FtyAr/xEZM6IW1V2S5VPQjmsQLqDKUQAvpJYtwQIUqrFSCYaluB1FwRncrgiQFp3mKYmClxGJECgYEA5XDlR5nuoOr+lMVWSYi+hRbUGgoxWmx2EBHchTRdLmDIW5CwuKOtGD3tq84OXjmZq2q28j2rZdOICeYwuYysGQkVoSPs6KqjoX8hDeBwYYOlni0zvvzajZ2Zs1Ws620X23u+/rodMXv403PrL1PSMa1z8XMQ3T3G6TpvGwXzzJMCgYEAxlDHGdf4rdvbjWuKL0kxUmzSWP0HR8T6tBIz4MsNZDbxMWNUA3BLnwv5OLxb18sSU5EPauxmsdJR4uqxJIXnTTsZoDR9GyskELez7RenIXkSgynqb5kmk3FJyaTRZqtoLP0ioMRUCkTwzrSy+YKOgkC+IMQhVgpcdc27beWOtfkCgYEAgrqBmPtnEL70URXjJUDJtREdxrx6BFh6KIvUxvzf/tXcMvfNCVcRABgA/HwKibtuCFJkbL+gkgMlpuZGauJxCE65yTer+6GGXDUQQ1TXE2isC+Ubb72oVTov4hN11CozhrYKTB8FFTXav7hzj2LGB9IZlsAGItjZJfhKZ+5LTAsCgYB3NzYJVih7M3CvehdOx7wrpZlpv2nx/fsL5uli2A3L0a96lhB6JLaA/Oyr66d2ePAiZlCTYVt2yE1LkPQ+VXSvm7iS8xrGC1AZ8KTsAU0KNUMosDjrL3DeL7tAyaDMDHyKlv2LEZrHtZvhVwVEMvTCXnMtNVoo4/+jHbLvpJD+2QKBgQCvoKXxCapsov1gpBpYANIUsdeoBqvhn/RwGHZj+7CMJPr6CDv2dGm6mh4F11tiwmwOO2wVwaO65LR4KOPiRoYOL+nGBAuPAQqz3jhfAAcBMKGXKIq+XWskb/ZrZgvc4hYUyt3WoAWoK1zfao3C9MA3lNh+ojpN6B0qmPMOwjSQnw==";
    static String serverRoot= "https://open.yeepay.com/yop-center";


    public static Map<String,String> yeepayYOP(Map<String,String> map, String Uri){

        System.out.println("********"+secretKey);

        YopRequest yoprequest  =  new YopRequest(appKey,secretKey,serverRoot);
        Map<String,String> result  =  new HashMap<String,String>();

        Set<Map.Entry<String,String> > entry		=	map.entrySet();
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
