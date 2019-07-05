package com.mengqid.site.login;

import com.mengqid.entity.Response;
import com.mengqid.entity.common.SmsCode.SmsCode;
import com.mengqid.entity.login.User;
import com.mengqid.mappers.SmsCodeMapper;
import com.mengqid.mappers.UserMapper;
import com.mengqid.site.account.AccountService;
import com.mengqid.utils.CheckUtil;
import com.mengqid.utils.PasswordEncoderUtil;
import com.mengqid.utils.ShortUUID;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @ClassName LoginService
 * @Description TODO
 * @Author K12
 * @Date 2019/6/28 14:14
 **/
@Service
public class LoginService {


    @Resource
    private SmsCodeMapper smsCodeMapper;
    @Resource
    private UserMapper userMapper;

    public boolean login_m(String mobile, String code, Integer type, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean flag = checkCode(mobile, code);
        if (!flag) {
            response.sendRedirect("/login?type=" + type + "&msg=error_code");
        }else {
            Example example = new Example(User.class);
            example.createCriteria().andEqualTo("telephone",mobile);
            User existUser = userMapper.selectOneByExample(example);
            if(CheckUtil.isEmpty(existUser)){
                //新增
                User user = new User();
                user.setUsername("u"+mobile);
                user.setPassword(PasswordEncoderUtil.passwordEncoder(mobile));
                user.setUser_uuid(ShortUUID.generate());
                user.setTelephone(mobile);
                user.setStatus(1);
                user.setType(0);
                user.setCreateTime(new Date());
                user.setUpdateTime(new Date());
                userMapper.insertUseGeneratedKeys(user);
                existUser = userMapper.selectByPrimaryKey(user.getId());
            }
            User user = userMapper.findByUsername(existUser.getUsername());
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            response.sendRedirect("/home");
        }
        return true;
    }



    /**
     * 校验验证码是否正确,如果正确将验证码设为失效
     *
     * @return
     */
    public boolean checkCode(String mobile, String code) {
        //设置过期时间
        long outTime = 10 * 60 * 1000;
        SmsCode smsCode = smsCodeMapper.findByMobile(mobile);
        if (CheckUtil.isEmpty(smsCode)) {
            return false;
        }
        long smsCodeTime = smsCode.getSendTime().getTime();
        long nowTime = System.currentTimeMillis();
        if ((nowTime - smsCodeTime) > outTime) {
            return false;
        }
        if (!CheckUtil.isEmpty(smsCode) && smsCode.getCode().equals(code)) {
            return true;
        }
        return false;
    }


    public Response registerUser(User user) {
        if(!CheckUtil.isEmpty(user.getCode()) && !CheckUtil.isEmpty(user.getTelephone())){
            boolean flag = checkCode(user.getTelephone(), user.getCode());
            if(!flag){
                return Response.buildErrorResponse("短信验证码有误或验证码已过期！");
            }
        }
        user.setPassword(PasswordEncoderUtil.passwordEncoder(user.getPassword()));
        user.setUser_uuid(ShortUUID.generate());
        user.setStatus(1);
        user.setType(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insert(user);
        return Response.buildSuccessResponse();
    }
}
