package com.mengqid.mappers;

import com.mengqid.core.base.MyMapper;
import com.mengqid.entity.climb.DouyiVideo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DouyiVideoMapper extends MyMapper<DouyiVideo>{


    List<DouyiVideo> findVideoUrl();


    void insertDouyi(DouyiVideo douyi);

    @Select("SELECT * from tb_douyi_video WHERE DATE_SUB(CURDATE(), INTERVAL 2 DAY) >= date(create_time)")
    List<DouyiVideo> findYesterDay();
}
