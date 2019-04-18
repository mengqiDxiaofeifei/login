package com.mengqid.entity.climb;

import lombok.Data;

import java.util.List;

@Data
public class Cover {

    private Integer height;

    private String  uri;

    private List<String> urlList;

    private Integer width;
}
