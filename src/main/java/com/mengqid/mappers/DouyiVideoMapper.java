package com.mengqid.mappers;

import com.mengqid.core.base.MyMapper;
import com.mengqid.entity.climb.DouyiVideo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DouyiVideoMapper extends MyMapper<DouyiVideo>{


    List<DouyiVideo> findVideoUrl();


    void insertDouyi(DouyiVideo douyi);
}
