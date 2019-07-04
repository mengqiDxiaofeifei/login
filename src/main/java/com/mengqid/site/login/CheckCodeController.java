package com.mengqid.site.login;


import com.alibaba.fastjson.JSONObject;
import com.mengqid.entity.Response;
import com.mengqid.entity.common.SmsCode.SmsCode;
import com.mengqid.mappers.SmsCodeMapper;
import com.mengqid.site.account.AccountService;
import com.mengqid.utils.Captcha;
import com.mengqid.utils.CheckUtil;
import com.mengqid.utils.SendSmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;
import java.util.Random;

@Slf4j
@Controller
@RequestMapping("/itnl")
public class CheckCodeController {

    /**
     * 数字
     */
    private static final String SYMBOLS = "0123456789";
    private static final Random RANDOM = new SecureRandom();

    @Resource
    private SmsCodeMapper smsCodeMapper;
    @Resource
    private AccountService accountService;

    /**
     * 用于生成带四位数字验证码的图片
     */
    @GetMapping(value = "/validateCode")
    public void imagecode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        OutputStream os = response.getOutputStream();
        Map<String, Object> map = Captcha.getImageCode(86, 37, os);
        request.getSession().setAttribute("CHECK_CODE", map.get("strEnsure").toString().toLowerCase());
        request.getSession().setAttribute("codeTime", System.currentTimeMillis());
        try {
            ImageIO.write((BufferedImage) map.get("image"), "jpg", os);
        } catch (IOException e) {
        } finally {
            if (os != null) {
                os.flush();
                os.close();
            }
        }
    }


    /**
     * 查询手机号
     */
    @ResponseBody
    @RequestMapping("/findUserByMobile")
    public com.mengqid.entity.Response findUserByMobile(String mobile){
        return accountService.findUserByMobile(mobile);
    }


    /**
     * 查询用户名
     */
    @ResponseBody
    @RequestMapping("/findUserByUsername")
    public com.mengqid.entity.Response findUserByUsername(String username){
        return accountService.findUserByUsername(username);
    }


    /**
     * 校验二维码
     *
     * @param request
     * @return java.lang.String
     */
    @GetMapping("/checkCode")
    @ResponseBody
    public String checkCode(HttpServletRequest request) {
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute("CHECK_CODE");
        if (!org.thymeleaf.util.StringUtils.equalsIgnoreCase(sessionCode, code)) {
            return "errs";
        } else {
            return "success";
        }
    }


    /**
     * 发送手机验证码
     *
     * @param
     * @return java.lang.String
     */
    @GetMapping("/sendCode")
    @ResponseBody
    public Response sendCode(String mobile) {
        if (CheckUtil.isEmpty(mobile)) {
            log.info("/sendCode  msg : {} ,param : {} , time :{}", "手机号不能为空！", "mobile :" + mobile, new Date());
            return Response.buildErrorResponse("手机号不能为空！");
        }
        //获取随机验证码
       String code = getNonceStr();
        //测试专用
        //String code = "0000";
        //发送验证码
        JSONObject returnMsg = SendSmsUtils.sendSms(mobile, code);
//        if (CheckUtil.isEmpty(returnMsg)) {
//            log.info("/sendCode  msg : {} ,param : {} , time :{}", "调用短信接口失败！", "mobile :" + mobile, new Date());
//            return Response.buildErrorResponse("调用短信接口失败");
//        }
//        String valiCode = returnMsg.getString("statusCode");
//        if (CheckUtil.isEmpty(valiCode) || !"000000".equals(valiCode)) {
//            log.info("/sendCode  msg : {} ,param : {} , time :{}", "调用短信接口失败！", "mobile :" + mobile, new Date());
//            return Response.buildErrorResponse("调用短信接口失败");
//        }
        //记录验证码
        addCode(mobile, code);
        return Response.buildSuccessResponse();
    }


    /**
     * 获取长度为 4 的随机数字
     *
     * @return 随机数字
     * @date
     */
    public String getNonceStr() {
        char[] nonceChars = new char[4];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }


    /**
     * 记录验证码
     *
     * @return
     */
    public void addCode(String mobile, String code) {
        SmsCode smsCode = new SmsCode();
        smsCode.setMobile(mobile);
        smsCode.setCode(code);
        smsCode.setSendTime(new Date());
        smsCode.setType(1);
        smsCode.setStatus(-1);
        smsCodeMapper.insert(smsCode);
    }
}
