package com.mengqid.entity.login;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.Date;

@Data
@Table(name = "tb_user")
public class User implements UserDetails {

    private static final long serialVersionUID = 4125096758372084309L;
    @Id
    private Integer id;
    private String userUuid;   //用户UUID
    private Integer type;       //账号类型  1： 门户  0 ： 后台
    private String username;    //用户名
    private String password;    //用户密码
    private String email;       //用户邮箱
    private String telephone;   //电话号码
    private String role;        //用户角色
    private Integer status;     //用户状态  1： 启用  0 ：禁用
    private String image;       //用户头像
    private String lastIp;     //上次登录IP
    private Date lastTime;   //上次登录时间
    private Date createTime;   //创建时间
    private Date updateTime;    //更新时间


    @Transient
    private String code;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
