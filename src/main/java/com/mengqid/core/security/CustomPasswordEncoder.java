package com.mengqid.core.security;


import com.mengqid.utils.EncryptUtils;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 密码校验
 *
 * @author liucancan
 * @date 2019/3/20
 */
@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    private static final String KEY = "redstarmacalline";

    @Override
    public String encode(CharSequence charSequence) {
        String pwd = charSequence.toString();
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String md5Pwd = encoder.encodePassword(pwd, "").toUpperCase();
        return md5Pwd;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        String pwd = charSequence.toString();
        StandardPasswordEncoder encoder = new StandardPasswordEncoder();
        try {
            if (encoder.matches(EncryptUtils.aesDecrypt(pwd, KEY), s)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DisabledException("--密码错误--");
        }
        throw new DisabledException("--密码错误--");
    }
}