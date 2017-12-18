package com.bootx.bootv.controller;

import com.alibaba.fastjson.JSONObject;
import com.bootx.bootv.commen.DateHelper;
import com.bootx.bootv.commen.YeepayEnum;
import com.bootx.bootv.service.YeepayService;
import com.mysql.jdbc.StringUtils;
import com.yeepay.g3.facade.yop.ca.dto.DigitalEnvelopeDTO;
import com.yeepay.g3.facade.yop.ca.enums.CertTypeEnum;
import com.yeepay.g3.frame.yop.ca.DigitalEnvelopeUtils;
import com.yeepay.g3.sdk.yop.utils.InternalConfig;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PUT;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;

@RestController
public class YeepayController {
    private String merchantno = "10015764273";
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
            String requestNo = jsonObject.getString("requestno");     //商户生成的唯一绑卡请求号
            String identityId = "0c41f2d2d1524270927ec95e17f42aa1";     //商户生成的用户唯一标识
            String identityType = YeepayEnum.IdentityType.USER_ID.toString();    //用户标识类型
            String cardNo = "6230941210001953094";     //卡号
            String idCardNo = "412821198708116018";     //证件号
            String idCardType = YeepayEnum.IdCardType.ID.toString();    //证件类型
            System.out.println("ID：" + idCardType);
            String userName = "张军伟";     //用户姓名
            String phone = "18221284735";       //手机号
            boolean isSms = true;     //是否发短信
            System.out.println("isSms的值：" + String.valueOf(isSms));
            String adviceSmsType = "MESSAGE";   //建议短验发送类型
            String avaliableTime = "10";   //绑卡订单有效期,单位：分钟
            String requestTime = DateHelper.getDate();      //请求时间
            String authType = "COMMON_FOUR";        //鉴权类型
            String remark = "接口测试";

            Map<String, String> map = new HashMap<>();
            map.put("merchantno", "10015764273");
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
            System.out.println(JSONObject.toJSONString(map)) ;

            Map<String, String> yeepayResponse = YeepayService.yeepayYOP(map, "/rest/v1.0/paperorder/unified/auth/request");

            return JSONObject.toJSONString(yeepayResponse);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/yeepay/pay",method = RequestMethod.POST)
    public String pay(@RequestBody String jsonPrams) {
        JSONObject jsonObject = JSONObject.parseObject(jsonPrams);
        String unionfirstpayUri = "/rest/v1.0/paperorder/unified/firstpay";
        Map<String,String> map	=	new HashMap<String,String>();
        map.put("merchantno", "10015764273");
        map.put("requestno", jsonObject.getString("requestno"));
        map.put("identityid", "18221284735");
        map.put("identitytype", "PHONE");
        map.put("cardno", "6230941210001953094");
        map.put("idcardno", "412821198708116018");
        map.put("idcardtype", "ID");
        map.put("username", "张军伟");
        map.put("phone", "18221284735");
        map.put("amount", "0.01");
        map.put("authtype", "COMMON_FOUR");
        map.put("issms", "true");
//        map.put("advicesmstype", advicesmstype);
//        map.put("smstempldatemsg", smstempldatemsg);
//        map.put("smstemplateid", smstemplateid);
        map.put("avaliabletime", "60");
        map.put("callbackurl","" );
        map.put("requesttime", "2017-11-14 10:15:30");
        map.put("productname", "接口测试");
        map.put("terminalno","SQKKSCENEPK010" );
//        map.put("dividecallbackurl", dividecallbackurl);
//        map.put("newdividejstr",newdividejstr);

        Map<String,String> yopresponsemap	=	YeepayService.yeepayYOP(map,unionfirstpayUri);
        String jsonResult = JSONObject.toJSONString(yopresponsemap);
        System.out.println("支付结果：" + jsonResult);
        return jsonResult;
    }

    @RequestMapping(value = "/yeepay/batchpay", method = RequestMethod.POST)
    public String batchPay() {
        String batchpayUri = "/rest/v1.0/paperorder/api/pay/batchtempcard/order";
        String batchdetails = "";
        Map<String,String> detail	=	new HashMap<String,String>();
        detail.put("requestno", "2017111417001");
        detail.put("amount", "0.01");
        detail.put("productname", "测试");
        detail.put("productname", "测试");
        detail.put("cardno", "6230941210001953094");
        detail.put("idcardno", "412821198708116018");
        detail.put("idcardtype", "ID");
        detail.put("username", "张军伟");
        List<Map<String,String>> detailList = new ArrayList<Map<String,String>>();
        detailList.add(detail);
        batchdetails = JSONObject.toJSONString(detailList);
        Map<String,String> map	=	new HashMap<String,String>();
        map.put("merchantno", merchantno);
        map.put("merchantbatchno", "201711140001");
        map.put("batchcallbackurl", "");
        map.put("requesttime", DateHelper.getDate());
        map.put("terminalno", "SQKKSCENEPK010");
        map.put("batchdetails", batchdetails);
//        map.put("free1", free1);
//        map.put("free2", free2);
//        map.put("free3", free3);

        Map<String,String> yopresponsemap	=	YeepayService.yeepayYOP(map,batchpayUri);
        String jsonResult = JSONObject.toJSONString(yopresponsemap);
        System.out.println(jsonResult);
        return jsonResult;
    }

    @RequestMapping(value = "/yeepay/bindconfirm",method = RequestMethod.POST)
    public String bindConfirm(@RequestBody String jsonPrams) {
        JSONObject jsonObject = JSONObject.parseObject(jsonPrams);
        String bindConfirmUri = "/rest/v1.0/paperorder/auth/confirm";
        Map<String,String> map	=	new HashMap<String,String>();
        map.put("merchantno", merchantno);
        map.put("requestno", jsonObject.getString("requestno"));
        map.put("validatecode", jsonObject.getString("code"));



        Map<String,String> yopresponsemap	=	YeepayService.yeepayYOP(map,bindConfirmUri);
        String jsonResult = JSONObject.toJSONString(yopresponsemap);
        System.out.println(jsonResult);
        return jsonResult;
    }

    @RequestMapping(value = "/yeepay/quickpay",method = RequestMethod.POST)
    public String bindPay(@RequestBody String jsonPrams) {
        JSONObject jsonObject = JSONObject.parseObject(jsonPrams);
        String unionfirstpayUri = "/rest/v1.0/paperorder/unified/pay";
        Map<String,String> map	=	new HashMap<String,String>();
        map.put("merchantno", "10015764273");
        map.put("requestno", jsonObject.getString("requestno"));
        map.put("identityid", "0c41f2d2d1524270927ec95e17f42aa1");
        map.put("identitytype", YeepayEnum.IdentityType.USER_ID.toString());
        map.put("cardno", "6230941210001953094");
        map.put("idcardno", "412821198708116018");
        map.put("idcardtype", "ID");
        map.put("username", "张军伟");
        map.put("phone", "18221284735");
        map.put("amount", "0.01");
        map.put("authtype", "COMMON_FOUR");
        map.put("issms", "false");
//        map.put("advicesmstype", advicesmstype);
//        map.put("smstempldatemsg", smstempldatemsg);
//        map.put("smstemplateid", smstemplateid);
        map.put("avaliabletime", "60");
        map.put("callbackurl","" );
        map.put("requesttime", "2017-11-14 10:15:30");
        map.put("productname", "接口测试");
        map.put("terminalno","SQKKSCENEPK010" );
        map.put("cardtop", "623094");
        map.put("cardlast", "3094");
        map.put("remark", "quickpay");
//        map.put("dividecallbackurl", dividecallbackurl);
//        map.put("newdividejstr",newdividejstr);

        Map<String,String> yopresponsemap	=	YeepayService.yeepayYOP(map,unionfirstpayUri);
        String jsonResult = JSONObject.toJSONString(yopresponsemap);
        System.out.println("支付结果：" + jsonResult);
        return jsonResult;
    }

    @RequestMapping(value = "/yeepay/notify", method = RequestMethod.POST)
    public String notify(@RequestBody String params) {
        try {
//            String response = params.replaceAll("response=E_", "");
            String response = params;
            //开始解密
            Map<String,String> jsonMap  = new HashMap<>();
            DigitalEnvelopeDTO dto = new DigitalEnvelopeDTO();
            dto.setCipherText(response);
            //InternalConfig internalConfig = InternalConfig.Factory.getInternalConfig();
            PrivateKey privateKey = InternalConfig.getISVPrivateKey(CertTypeEnum.RSA2048);
            System.out.println("privateKey: "+privateKey);
            PublicKey publicKey = InternalConfig.getYopPublicKey(CertTypeEnum.RSA2048);
            System.out.println("publicKey: "+publicKey);

            dto = DigitalEnvelopeUtils.decrypt(dto, privateKey, publicKey);
            System.out.println("-------:"+dto.getPlainText());
            params = dto.getPlainText();
//            jsonMap = parseResponse(dto.getPlainText());
//            System.out.println(jsonMap);
        } catch (Exception e) {
            throw new RuntimeException("回调解密失败！");
        }
        return params;
    }
}
