package com.mengqid.mappers;

import com.mengqid.core.base.MyMapper;
import com.mengqid.entity.video.Video;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface VideoMapper extends MyMapper<Video> {

    @Update("UPDATE `tb_video` SET `status`=#{status} where id = #{id}")
    void updateStatusById(@Param("id") Integer id, @Param("status") Integer status);
}
