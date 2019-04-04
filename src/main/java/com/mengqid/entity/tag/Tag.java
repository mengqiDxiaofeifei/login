package com.mengqid.entity.tag;


import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_tag")
public class Tag {

    @Id
    private Integer id;
    private String name;
    private Date createTime;
    private Date updateTime;
}
