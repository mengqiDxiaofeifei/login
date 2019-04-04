package com.mengqid.mappers;


import com.mengqid.core.base.MyMapper;
import com.mengqid.entity.category.Category;
import com.mengqid.entity.common.Response;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper extends MyMapper<Category> {
    @Select("select id ,name from tb_category where type = #{type}")
    List<Category> findCategoryByType(String type);
}
