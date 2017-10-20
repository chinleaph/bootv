package com.bootx.bootv.dao;

import com.bootx.bootv.bean.Grade;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GradeMapper {
    @Select("select * from grade where grade_num=#{num}")
    List<Grade> getByGradeNum(String num);
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "grade_num",property = "gradeNum"),
            @Result(column = "teacher_id",property = "teacherId")
    })

    @Select("select * from grade where grade_num=#{num} limit 1")
    Grade getByNum(String num);
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "grade_num",property = "gradeNum"),
            @Result(column = "teacher_id",property = "teacherId")
    })

    @Insert("insert into grade(grade_num,teacher_id) values(#{gradeNum},#{teacherId})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void save(Grade grade);
}
