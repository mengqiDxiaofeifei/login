package com.mengqid.entity.banner;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_banner")
public class Banner {

    @Id
    private Integer id;
    private String name;
    private String title;
    private String adviceWord;
    private String imgUrl;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
