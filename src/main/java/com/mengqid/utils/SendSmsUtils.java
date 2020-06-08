package com.mengqid.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName sendSmsUtils
 * @Description TODO
 * @Author K12
 * @Date 2019/6/28 11:08
 **/
public class SendSmsUtils {

    private static String requestUrl = " http://apis.haoservice.com/sms/sendv2";
    private static String apiKey = "64257c1d0b944531a23e2c5f3343b891";
    private static String templateID = "1945";


    public static JSONObject sendSms(String mobile,String code) {
        String resp = ClimbDataUtil.sendPost(requestUrl,
                "key="+apiKey+"&mobile="+mobile+"&tpl_id="+templateID+"&content="+code+"");
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
                return jsonObject;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        sendSms("17320406595","4444");
    }

}

