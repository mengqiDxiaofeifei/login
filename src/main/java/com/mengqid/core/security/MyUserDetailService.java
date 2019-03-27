package com.mengqid.core.security;


import com.mengqid.entity.login.User;
import com.mengqid.mappers.UserMapper;
import com.mengqid.utils.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(MyUserDetailService.class);

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (CheckUtil.isEmpty(user)) {
              throw new AuthenticationServiceException("nouser");
        }
        return user;
    }
}
