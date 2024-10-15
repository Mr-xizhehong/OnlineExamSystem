package com.example.onlineexamsystem.Mapper;

import com.example.onlineexamsystem.info.CourseInfo;
import com.example.onlineexamsystem.info.ExamInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("SELECT c.course_id, c.course_name, t.teacher_name, c.create_time, c.update_time " +
            "FROM student s " +
            "JOIN course c ON s.class_id = c.course_id " +
            "JOIN teacher t ON c.teacher_id = t.teacher_id " +
            "WHERE s.user_id = #{userId}")
    List<CourseInfo> findCourseByUserId(Integer userId);

    @Select("SELECT e.exam_id, e.create_time, e.update_time, e.exam_time, c.course_name, eq.question_url " +
            "FROM student s " +
            "JOIN exam e ON s.class_id = e.course_id " +
            "JOIN course c ON e.course_id = c.course_id " +
            "JOIN exam_question eq ON e.exam_question_id = eq.exam_question_id " +
            "WHERE s.user_id = #{userId}")
    List<ExamInfo> findExamsByUserId(Integer userId);


}
