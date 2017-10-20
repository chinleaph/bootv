package com.bootx.bootv.service;

import com.bootx.bootv.bean.Grade;
import com.bootx.bootv.dao.GradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    public List<Grade> getByGradeNum(String num) {
        return gradeMapper.getByGradeNum(num);
    }

    public Grade getByNum(String num) {
        return gradeMapper.getByNum(num);
    }

    public void defaultRemark(Grade grade) {
        if (null==grade.getRemark() || grade.getRemark().isEmpty()) {
            grade.setRemark("一个很普通的班级");
        }
    }
}
