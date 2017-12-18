package com.bootx.bootv.controller;

import javax.annotation.Resource;

import com.bootx.bootv.bean.Grade;
import com.bootx.bootv.service.GradeService;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GradeController {
    @Resource
    private GradeService gradeService;

    @RequestMapping("/getByGradeNum")
    public List<Grade> getByGradeNum(String num){
        return gradeService.getByGradeNum(num);
    }

    @RequestMapping("/getByGradeNum2")
    public List<Grade> getByGradeNum2(String num){
        PageHelper.startPage(1, 2);
        List<Grade> list = gradeService.getByGradeNum(num);
        for (Grade grade : list) {
            gradeService.defaultRemark(grade);
        }
        return list;
    }

    @RequestMapping("/getOneByNum")
    public Grade getByNum(String num){
        Grade grade = gradeService.getByNum(num);
        gradeService.defaultRemark(grade);
        return grade;
    }

    @RequestMapping("/showaop")
    public String showAop() {
        return "month moon moo";
    }
}
