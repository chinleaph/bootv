package com.bootx.bootv.controller;

import com.alibaba.fastjson.JSONObject;
import com.bootx.bootv.commen.DateHelper;
import com.bootx.bootv.commen.YeepayEnum;
import com.bootx.bootv.service.YeepayService;
import com.mysql.jdbc.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class YeepayController {
    /**
     * 统一鉴权绑卡请求
     * @param jsonPrams
     * @return
     */
    @RequestMapping(value = "/yeepay/auth_request",method = RequestMethod.POST)
    public String yeepayAuthRequest(@RequestBody String jsonPrams){
        if(StringUtils.isNullOrEmpty(jsonPrams)){
            return "error，参数为空";
        }
        try {
            JSONObject jsonObject = JSONObject.parseObject(jsonPrams);
            String requestNo = "041013562ff74a2fb9387";     //商户生成的唯一绑卡请求号
            String identityId = "0c41f2d2d1524270927ec95e17f42aa1";     //商户生成的用户唯一标识
            String identityType = YeepayEnum.IdentityType.USER_ID.toString();    //用户标识类型
            String cardNo = jsonObject.getString("cardno");     //卡号
            String idCardNo = jsonObject.getString("idcardno");     //证件号
            String idCardType = YeepayEnum.IdCardType.ID.toString();    //证件类型
            System.out.println("ID：" + idCardType);
            String userName = jsonObject.getString("username");     //用户姓名
            String phone = jsonObject.getString("phone");       //手机号
            boolean isSms = jsonObject.getBoolean("issms");     //是否发短信
            System.out.println("isSms的值：" + String.valueOf(isSms));
            String adviceSmsType = jsonObject.getString("advicesmstype");   //建议短验发送类型
            String avaliableTime = jsonObject.getString("avaliabletime");   //绑卡订单有效期,单位：分钟
            String requestTime = DateHelper.getDate();      //请求时间
            String authType = "COMMON_FOUR";        //鉴权类型
            String remark = jsonObject.getString("remark");

            Map<String, String> map = new HashMap<>();
            map.put("merchantno", "10000469946");
            map.put("requestno", requestNo);
            map.put("identityid", identityId);
            map.put("identitytype", identityType);
            map.put("cardno", cardNo);
            map.put("idcardno", idCardNo);
            map.put("idcardtype", idCardType);
            map.put("username", userName);
            map.put("phone", phone);
            map.put("issms", String.valueOf(isSms));
            map.put("advicesmstype", adviceSmsType);
            map.put("smstemplateid", "");
            map.put("smstempldatemsg", "");
            map.put("avaliabletime", avaliableTime);
            map.put("callbackurl", "");
            map.put("requesttime", requestTime);
            map.put("authtype", authType);
            map.put("remark", remark);
            map.put("extinfos", "");

            Map<String, String> yeepayResponse = YeepayService.yeepayYOP(map, "https://open.yeepay.com/yop-center/rest/v1.0/paperorder/unified/auth/request");

            return JSONObject.toJSONString(yeepayResponse);
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
