package com.example.onlineexamsystem.Service.Impl;


import com.example.onlineexamsystem.Mapper.TeacherMapper;
import com.example.onlineexamsystem.Service.TeacherService;
import com.example.onlineexamsystem.pojo.Course;
import com.example.onlineexamsystem.pojo.Grade;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    private final OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public List<Course> getCourse(Integer userId) {
        return teacherMapper.getCourse(userId);
    }

    @Override
    public List<Grade> getGrade(Integer userId, Integer course_id) {
        return teacherMapper.getGrade(userId,course_id);
    }

    @Override
    public String getStudentExam(Integer examId, Integer studentId) {
        String studentExamUrl = teacherMapper.getStudentUrl(examId, studentId);
        //向url发送okhttp请求
        Request request = new Request.Builder().url(studentExamUrl).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
