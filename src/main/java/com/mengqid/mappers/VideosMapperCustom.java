package com.mengqid.mappers;

import com.mengqid.core.base.MyMapper;
import com.mengqid.entity.api.pojo.Videos;
import com.mengqid.entity.api.vo.VideosVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideosMapperCustom extends MyMapper<Videos> {

    /**
     * @Description: 条件查询所有视频列表
     */
    public List<VideosVo> queryAllVideos(@Param("videoDesc") String videoDesc,
                                         @Param("userId") String userId);



}

