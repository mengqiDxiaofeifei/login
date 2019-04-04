package com.mengqid.entity.category;


import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_category")
public class Category {

    @Id
    private Integer id;
    private Integer type;
    private String name;
    private Date createTime;
    private Date updateTime;
}
