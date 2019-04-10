package com.mengqid.mappers;

import com.mengqid.core.base.MyMapper;
import com.mengqid.entity.climb.DouyiVideo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DouyiVideoMapper extends MyMapper<DouyiVideo>{

    @Select("select * from tb_douyi_video limit 12,6")
    List<DouyiVideo> findVideoUrl();
}
