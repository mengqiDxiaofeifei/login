package com.mengqid.entity.common;


import lombok.Data;

import java.util.List;

@Data
public class PageResult {

    /**
     * 当前页
     */
    private String  msg;
    /**
     * 每页大小
     */
    private int code;
    /**
     * 总条数
     */
    private Long count;
    /**
     * 数据集合
     */
    private List data;
}
