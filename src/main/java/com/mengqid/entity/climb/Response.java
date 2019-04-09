package com.mengqid.entity.climb;

import lombok.Data;

import java.util.List;

@Data
public class Response {

    private Integer status;

    private Integer count;

    private String cursor;

    private List<Temp> data;
}
