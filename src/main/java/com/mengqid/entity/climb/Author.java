package com.mengqid.entity.climb;

import lombok.Data;


@Data
public class Author {


    private AvatarThumb avatarThumb;

    private Integer gender;

    private String nickname;

    private String uid;
    //抖音id
    private String uniqueId;
}
