package com.example.onlineexamsystem.Mapper;

import com.example.onlineexamsystem.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User findUserByUsername(String username);

    @Insert("insert into user(username,password,role) values(#{username},#{md5Password},#{role})")
    void register(String username, String md5Password, String role);

    @Select("select * from user where face_id = #{userId}")
    User findUserByFaceId(String userId);

    @Select("select * from user where username = #{username}")
    User test(String username);

    @Update("update user set username = #{username} , password = #{password} , email = #{email} where user_id = #{userId}")
    void updateUserInfo(User user);
}
