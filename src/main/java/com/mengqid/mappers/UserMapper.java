package com.mengqid.mappers;

import com.mengqid.core.base.MyMapper;
import com.mengqid.entity.login.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper extends MyMapper<User> {

    @Select("SELECT `id`, `user_uuid`, type,`username`, `password`, `email`, `telephone`, `role`, `image`, `last_ip`, `last_time` from `tb_user`  where username = #{username} and status  = 1")
    User findByUsername(@Param("username") String username);


    @Update("UPDATE `tb_user` SET `status`=#{status} where id = #{id}")
    void updateStatusById(@Param("id") Integer id, @Param("status") Integer status);


    List<User> accountPage(User user);
}
