package com.example.onlineexamsystem.Service.Impl;

import com.example.onlineexamsystem.Mapper.UserMapper;
import com.example.onlineexamsystem.Service.UserService;
import com.example.onlineexamsystem.info.LoginUser;
import com.example.onlineexamsystem.pojo.Result;
import com.example.onlineexamsystem.pojo.User;
import com.example.onlineexamsystem.utils.GetTokenUtil;
import com.example.onlineexamsystem.utils.JwtUtil;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GetTokenUtil getTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoadUserByUsernameService loadUserByUsernameService;

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public void register(String username, String password, String role) {
        String encodePassword= passwordEncoder.encode(password);
        userMapper.register(username,encodePassword,role);
    }

    public LoginUser login(String username, String password) {
        //进行验证
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(token);
        //验证失败，摆出异常
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户未认证");
        }
        //验证成功，允许登录,生成jwt并存入
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        return loginUser;
    }


    public String searchFace(String imageBase64) {
        String token = getTokenUtil.getAuth();
        // API URL
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search?access_token=" + token;

        // 创建请求体
        MediaType mediaType = MediaType.parse("application/json");
        String json = "{\"image\":\"" + imageBase64 + "\",\"image_type\":\"BASE64\",\"group_id_list\":\"face_user\",\"quality_control\":\"LOW\",\"liveness_control\":\"NONE\"}";
        RequestBody body = RequestBody.create(mediaType, json);

        // 创建请求
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        // 执行请求并获取响应
        try (Response response = HTTP_CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findUserByFaceId(String userId) {
        return userMapper.findUserByFaceId(userId);
    }

    @Override
    public void updateUserInfo(User user) {
        userMapper.updateUserInfo(user);
        return;
    }


}
