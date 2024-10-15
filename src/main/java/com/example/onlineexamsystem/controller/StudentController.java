package com.example.onlineexamsystem.controller;

import com.example.onlineexamsystem.Service.StudentService;
import com.example.onlineexamsystem.info.CourseInfo;
import com.example.onlineexamsystem.info.ExamInfo;
import com.example.onlineexamsystem.pojo.Result;
import com.example.onlineexamsystem.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    //得到课程信息
    @GetMapping("/courseMessage")
    public Result<List<CourseInfo>> courseMessage(){
        Map<String,Object> claim = ThreadLocalUtil.get();
        Integer user_id = (Integer) claim.get("user_id");
        List<CourseInfo> courseInfoList = studentService.findCourseByUserId(user_id);
        return Result.success(courseInfoList);
    }

    //得到考试信息
    @PostMapping("/examMessage")
    public Result<List<ExamInfo>> examMessage(){
        Map<String,Object> claim = ThreadLocalUtil.get();
        String user_id = (String) claim.get("user_id");
        List<ExamInfo> examInfoList = studentService.findExamByUserId(Integer.parseInt(user_id));
        return Result.success(examInfoList);
    }


    //得到试卷信息
    @PostMapping("/getExamQuestion")
    public Result<String> getExamQuestion(String questionUrl) throws IOException {
        String examQuestion = studentService.getExamQuestion(questionUrl);
        return Result.success(examQuestion);
    }


}
