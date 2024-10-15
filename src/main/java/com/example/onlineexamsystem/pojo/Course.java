package com.example.onlineexamsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Integer courseId;
    private String courseName;
    private Integer teacherId;
    private Integer examId;
    private Timestamp createTime;
    private Timestamp updateTime;
}
