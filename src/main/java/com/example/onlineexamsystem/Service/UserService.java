package com.example.onlineexamsystem.Service;

import com.example.onlineexamsystem.info.LoginUser;
import com.example.onlineexamsystem.pojo.Result;
import com.example.onlineexamsystem.pojo.User;

public interface UserService {
    User findUserByUsername(String username);

    void register(String username, String password, String role);

    LoginUser login(String username, String password);

    String searchFace(String faceBase64);

    User findUserByFaceId(String userId);

    void updateUserInfo(User user);
}
