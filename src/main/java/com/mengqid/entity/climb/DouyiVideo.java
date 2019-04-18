package com.mengqid.entity.climb;

import com.mengqid.entity.Author.Author;
import lombok.Data;
import org.springframework.data.annotation.Persistent;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_douyi_video")
public class DouyiVideo {

    @Id
    private Integer id;

    private String uuid;

    private String desc;

    private String videoImg;

    private String videoUrl;

    private String authorUid;

    private String userId;

    //评论
    private Integer commentCount;
    //点赞
    private Integer diggCount;
    //分享数
    private Integer shareCount;

    private Date createTime;

    private Date updateTime;


    @Persistent
    private Author author;
}
