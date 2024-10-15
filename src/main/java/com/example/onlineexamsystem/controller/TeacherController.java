package com.example.onlineexamsystem.controller;

import com.example.onlineexamsystem.Service.TeacherService;
import com.example.onlineexamsystem.pojo.Course;
import com.example.onlineexamsystem.pojo.Grade;
import com.example.onlineexamsystem.pojo.Result;
import com.example.onlineexamsystem.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teahcer")
@Validated
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //得到教师所教的课程信息
    @GetMapping("/getCourse")
    public Result<List<Course>> getCourse() {
        Map<String,Object> claims = ThreadLocalUtil.get();
        Integer user_id = (Integer) claims.get("user_id");
        List<Course> courseList = teacherService.getCourse(user_id);
        return Result.success(courseList);
    }

    //得到课程对应的考试信息及学生成绩
    @PostMapping("/getStudentGrade")
    public Result<List<Grade>> getStudentGrade(Integer course_id) {
        Map<String,Object> claims = ThreadLocalUtil.get();
        Integer user_id = (Integer) claims.get("user_id");
        List<Grade> gradeList = teacherService.getGrade(user_id,course_id);
        return Result.success(gradeList);
    }

    @PostMapping("/getStudentExam")
    public Result<String> getStudentExam(Integer exam_id, Integer student_id) {
        String studentExam = teacherService.getStudentExam(exam_id,student_id);
        return Result.success(studentExam);
    }
}
