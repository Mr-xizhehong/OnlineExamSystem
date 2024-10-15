package com.example.onlineexamsystem.pojo;

import com.example.onlineexamsystem.anno.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class User {
    @NonNull
    private Integer userId;//主键ID

    private String username;//用户名

    @JsonIgnore//让springmvc把当前对象转换成json字符串的时候,忽略password,最终的json字符串中就没有password这个属性了
    private String password;//密码

    @NotEmpty
    @Role
    private String role;//职务

    @NotEmpty
    @Email
    private String email;//邮箱

    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
    private String faceId;

}
