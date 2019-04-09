package com.mengqid.entity.climb;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_douyi_video")
public class DouyiVideo {

    @Id
    private Integer id;

    private String uuid;

//    private String desc;

    private String videoUrl;

    private Date createTime;

    private Date updateTime;
}
