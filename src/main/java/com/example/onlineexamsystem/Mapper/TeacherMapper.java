package com.example.onlineexamsystem.Mapper;

import com.example.onlineexamsystem.pojo.Course;
import com.example.onlineexamsystem.pojo.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeacherMapper {

    @Select("SELECT c.course_id, c.course_name, c.teacher_id, c.exam_id, c.create_time, c.update_time " +
            "FROM teacher t " +
            "JOIN course c ON t.teacher_id = c.teacher_id " +
            "WHERE t.user_id = #{userId}")
    List<Course> getCourse(Integer userId);

    @Select("SELECT g.grade_id, g.student_id, g.course_id, g.grade, g.teacher_id, g.exam_id " +
            "FROM grade g " +
            "JOIN teacher t ON g.teacher_id = t.teacher_id " +
            "WHERE t.user_id = #{userId} AND g.course_id = #{courseId}")
    List<Grade> getGrade(Integer userId, Integer course_id);

    String getStudentExam(Integer examId, Integer studentId);

    @Select("select sa.student_question_url from student_answer sa where sa.exam_id = #{examId} and student_id = #{studentId}")
    String getStudentUrl(Integer examId, Integer studentId);
}
