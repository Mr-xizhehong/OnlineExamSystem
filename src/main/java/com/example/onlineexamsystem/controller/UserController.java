package com.example.onlineexamsystem.controller;

import com.example.onlineexamsystem.Service.UserService;
import com.example.onlineexamsystem.anno.Role;
import com.example.onlineexamsystem.info.LoginUser;
import com.example.onlineexamsystem.pojo.Result;
import com.example.onlineexamsystem.pojo.User;
//import com.example.onlineexamsystem.utils.FaceCaptureUtil;
import com.example.onlineexamsystem.utils.FaceCaptureUtil;
import com.example.onlineexamsystem.utils.JwtUtil;
import com.example.onlineexamsystem.utils.Md5Util;
import com.example.onlineexamsystem.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = ("^\\S{3,15}$")) String username , @Pattern(regexp = ("^\\S{3,16}$")) String password , @Role String role){
        User user = userService.findUserByUsername(username);
        //用户不存在,进行注册
        if (user == null){
            userService.register(username,password,role);
            Result.success();
        }
        //用户存在，报错
        return Result.error("用户已存在");
    }

    @GetMapping("/test")
    public String test(){
        return "asdsa";
    }
    //常规写法的登录校验
//    @PostMapping("/login")
//    public Result<String> login(@Pattern(regexp = ("^\\S{3,15}$")) String username , @Pattern(regexp = ("^\\S{3,16}$")) String password){
//        User user = userService.findUserByUsername(username);
//        //判断用户是否存在
//        if (user == null) {return Result.error("用户不存在");}
//
//        //判断密码是否正确
//        String md5Password = Md5Util.getMD5String(password);
//        if (md5Password.equals(user.getPassword())) {
//            //密码正确生成jwt令牌
//            Map<String,Object> claim = new HashMap<>();
//            claim.put("user_id",user.getUserId());
//            claim.put("username",user.getUsername());
//            claim.put("role",user.getRole());
//            String token = JwtUtil.genToken(claim);
//            //将令牌放入redis
//            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//            operations.append(token,token);
//            Result.success(token);
//        }
//        //密码错误
//        return Result.error("密码错误");
//    }

    //基于sping security的登录校验
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = ("^\\S{3,15}$")) String username , @Pattern(regexp = ("^\\S{3,16}$")) String password){
        LoginUser loginUser = userService.login(username, password);
        Map<String,Object> claim = new HashMap<>();
        claim.put("user_id",loginUser.getUser().getUserId());
        claim.put("username",loginUser.getUser().getUsername());
        claim.put("role",loginUser.getUser().getRole());
        String jwt = JwtUtil.genToken(claim);
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(jwt,jwt);
        System.out.println("登录成功");
        return Result.success(jwt);
    }

    @PostMapping("/facelogin")
    public Result<String> facelogin(){
        String faceBase64 = FaceCaptureUtil.captureImageAsBase64();
        String result = userService.searchFace(faceBase64);
        JSONObject jsonObject = new JSONObject(result);
        JSONObject face_result = jsonObject.getJSONObject("result");
        JSONArray userList = face_result.getJSONArray("user_list");
        JSONObject user = userList.getJSONObject(0);
        String user_id = user.getString("user_id");
        Double score = user.getDouble("score");

        //找到人脸
        if (score > 85){
            User login_user = userService.findUserByFaceId(user_id);
            Map<String,Object> claim = new HashMap<>();
            claim.put("user_id",login_user.getUserId());
            claim.put("username",login_user.getUsername());
            claim.put("role",login_user.getRole());
            String token = JwtUtil.genToken(claim);
            //将令牌放入redis
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token);
            System.out.println(login_user);
            Result.success(token);
        }
        //找不到人脸
        return Result.error("匹配失败，请重新尝试");
    }

    @PostMapping("/userInfo")
    public Result<User> userInfo(){
        Map<String,Object> claim = ThreadLocalUtil.get();
        String user_name = (String) claim.get("username");
        User user = userService.findUserByUsername(user_name);
        return Result.success(user);
    }

    @PostMapping("/updateUserInfo")
    public Result<String> updateUserInfo(User user){
        userService.updateUserInfo(user);
        return Result.success("修改成功");
    }


}
