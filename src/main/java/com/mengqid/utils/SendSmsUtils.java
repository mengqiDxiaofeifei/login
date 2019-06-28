package com.mengqid.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName sendSmsUtils
 * @Description TODO
 * @Author K12
 * @Date 2019/6/28 11:08
 **/
public class SendSmsUtils {

    private static String requestUrl = "https://api.apishop.net/communication/sms/send";
    private static String apiKey = "LSsrAGm279f7674a5de69fc3868625aeb3f984ced7ef78a";
    private static String templateID = "10346";


    public static JSONObject sendSms(String mobile,String code) {
        String resp = ClimbDataUtil.sendPost(requestUrl,
                "apiKey="+apiKey+"&phoneNum="+mobile+"&templateID="+templateID+"&params=["+code+"]");
//        if (!CheckUtil.isEmpty(resp)) {
//            JSONObject jsonObject = JSONObject.parseObject(resp);
//            if (!CheckUtil.isEmpty(jsonObject)) {
//                return jsonObject;
//            }
//        }
        return null;
    }

}

