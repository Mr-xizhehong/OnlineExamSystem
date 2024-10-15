package com.example.onlineexamsystem.Service;


import com.example.onlineexamsystem.pojo.Course;
import com.example.onlineexamsystem.pojo.Grade;

import java.util.List;

public interface TeacherService {

    List<Course> getCourse(Integer userId);

    List<Grade> getGrade(Integer userId, Integer course_id);

    String getStudentExam(Integer examId, Integer studentId);
}
