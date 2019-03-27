package com.mengqid.site.login;


import com.mengqid.utils.Captcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
@Controller
@RequestMapping("/itnl")
public class CheckCodeController {

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
     * 校验二维码
     *
     * @param request
     * @return java.lang.String
     *
     *
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
}
