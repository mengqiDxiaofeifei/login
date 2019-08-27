package com.mengqid.entity.climb;

import lombok.Data;

/**
 * 评论数，分享数，点赞数
 */
@Data
public class Statistics {
    //评论
    private Integer comment;
    //点赞
    private Integer zan;
    //分享数
    private Integer share;
    //播放数
    private Integer play;
}
