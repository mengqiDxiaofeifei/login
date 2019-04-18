package com.mengqid.entity.Author;


import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Table(name = "tb_douyi_video_author")
public class Author {

    @Id
    private Integer id;

    private String headImgUrl;

    private Integer gender;

    private String nickname;

    private String uid;
    //抖音id
    private String uniqueId;
}
