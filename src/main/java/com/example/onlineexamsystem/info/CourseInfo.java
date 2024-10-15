package com.example.onlineexamsystem.info;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CourseInfo {

    private Integer courseId;
    private String CourseName;
    private String teacherName;
    private Timestamp createTime;
    private Timestamp updateTime;
}
