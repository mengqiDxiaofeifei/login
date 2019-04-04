package com.mengqid.entity.video;


import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_video")
public class Video {

    @Id
    private Integer id;
    private String uuid;
    private String bgImgUrl;
    private String name;
    private String videoUrl;
    private String year;
    private Integer star;
    private String story;
    private Integer status;
    private String category;
    private Date createTime;
    private Date updateTime;


}
