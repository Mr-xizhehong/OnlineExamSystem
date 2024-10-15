package com.example.onlineexamsystem.Service.Impl;

import com.example.onlineexamsystem.Mapper.UserMapper;
import com.example.onlineexamsystem.info.LoginUser;
import com.example.onlineexamsystem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoadUserByUsernameService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUsername(username);
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }
        LoginUser loginUser = new LoginUser(user);
        return loginUser;
    }
}
