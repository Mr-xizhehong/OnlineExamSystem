package com.example.onlineexamsystem.info;

import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@Data
public class ExamInfo {

    private Integer examId;
    private String courseName;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Timestamp examTime;
    private String questionUrl;

}
