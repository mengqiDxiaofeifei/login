package com.mengqid.core.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用继承MyMapper
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
