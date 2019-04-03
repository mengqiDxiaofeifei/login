package com.mengqid.mappers;

import com.mengqid.core.base.MyMapper;
import com.mengqid.entity.banner.Banner;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface BannerMapper extends MyMapper<Banner>{

    @Update("UPDATE `tb_banner` SET `status`=#{status} where id = #{id}")
    void updateStatusById(@Param("id") Integer id, @Param("status") Integer status);
}
