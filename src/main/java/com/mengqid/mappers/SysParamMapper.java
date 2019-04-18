package com.mengqid.mappers;

import com.mengqid.core.base.MyMapper;
import com.mengqid.core.base.SysParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysParamMapper extends MyMapper<SysParam>{

    @Select("select * from tb_sys_param where `key` = #{cacheCursor}")
    SysParam selectByKey(String cacheCursor);

    @Select("update tb_sys_param set `value` = #{value} where `key` = #{cacheCursor}")
    void updateBykey(@Param("cacheCursor") String cacheCursor,@Param("value") String value);
}
