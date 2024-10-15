package com.example.onlineexamsystem.Service.Impl;

import com.example.onlineexamsystem.Mapper.StudentMapper;
import com.example.onlineexamsystem.Service.StudentService;
import com.example.onlineexamsystem.info.CourseInfo;
import com.example.onlineexamsystem.info.ExamInfo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    private final OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public List<CourseInfo> findCourseByUserId(Integer userId) {
        return studentMapper.findCourseByUserId(userId);
    }


    @Override
    public List<ExamInfo> findExamByUserId(Integer userId) {
        return studentMapper.findExamsByUserId(userId);
    }

    @Override
    public String getExamQuestion(String questionUrl) {
        Request request = new Request.Builder().url(questionUrl).build();
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
