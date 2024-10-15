package com.example.onlineexamsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    private Integer gradeId;
    private Integer studentId;
    private Integer courseId;
    private Integer grade;
    private Integer teacherId;
    private Integer examId;
}
