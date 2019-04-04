package com.mengqid.utils;

import com.github.pagehelper.Page;
import com.mengqid.entity.common.PageResult;

import java.util.List;

/**
 * @Author: Yenan
 * @Description:
 * @Date: Created in 11:53 2018/2/5
 */
public class CommonUtil {


    public static PageResult backPageResult(List<?> list, Page<?> page) {
        PageResult pageResult = new PageResult();
        pageResult.setData(list);
        if(!CheckUtil.isEmpty(list)){
            pageResult.setCode(0);
            pageResult.setMsg("success");
        }else {
            pageResult.setCode(-1);
            pageResult.setMsg("暂无数据");
        }
        pageResult.setCount(page.getTotal());
        return pageResult;
    }
}
