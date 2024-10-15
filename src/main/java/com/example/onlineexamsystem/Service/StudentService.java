package com.example.onlineexamsystem.Service;

import com.example.onlineexamsystem.info.CourseInfo;
import com.example.onlineexamsystem.info.ExamInfo;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    List<CourseInfo> findCourseByUserId(Integer userId);

    List<ExamInfo> findExamByUserId(Integer userId);

    String getExamQuestion(String questionUrl) throws IOException;

}
