package com.mengqid.mappers;

import com.mengqid.core.base.MyMapper;
import com.mengqid.entity.common.SmsCode.SmsCode;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SmsCodeMapper extends MyMapper<SmsCode>{


    @Select("SELECT * FROM tb_sms_code WHERE mobile = #{mobile} AND `status` = -1 ORDER BY send_time DESC LIMIT 1")
    SmsCode findByMobile(@Param("mobile") String mobile);
}
