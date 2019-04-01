package com.mengqid.entity.common;


import lombok.Data;

@Data
public class PageRequestVo {

    /**
     * 当前页
     */
    private int page;
    /**
     * 每页大小
     */
    private int limit;

}
