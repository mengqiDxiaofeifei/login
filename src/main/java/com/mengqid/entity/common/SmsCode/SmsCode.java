package com.mengqid.entity.common.SmsCode;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName SmsCode
 * @Description TODO
 * @Author K12
 * @Date 2019/6/28 13:47
 **/
@Data
@Table(name = "tb_sms_code")
public class SmsCode {

    /**
     * id
     */
    private Integer id;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 验证码
     */
    private String code;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 类型
     * 1:注册 2:修改密码
     */
    private  Integer type;

    /**
     * 是否验证
     * 0:未验证 1：已验证
     */
    private  Integer status;


}
