package com.mengqid.mappers;

import com.mengqid.core.base.MyMapper;
import com.mengqid.entity.video.Video;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface VideoMapper extends MyMapper<Video> {

    @Update("UPDATE `tb_video` SET `status`=#{status} where id = #{id}")
    void updateStatusById(@Param("id") Integer id, @Param("status") Integer status);


    @Select("select `id`, `uuid`, `bg_img_url`, `name`, `video_url`, `year`, `status`, `star`, `story`, `category`, `create_time`, `update_time` from  `tb_video` where status = 1 order by create_time DESC")
    List<Video> findVideoListNews();

    @Select("select `id`, `uuid`, `bg_img_url`, `name`, `video_url`, `year`, `status`, `star`, `story`, `category`, `create_time`, `update_time` from  `tb_video` where status = 1")
    List<Video> findVideoListHot();
}
