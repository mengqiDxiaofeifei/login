package com.mengqid.core.base;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_sys_param")
public class SysParam {

    @Id
    private Integer id;

    private String key;

    private String value;

    private String desc;
}
