package com.bootx.bootv.bean;

public class Grade {
    private int id;
    private String gradeNum;
    private int teacherId;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(String num) {
        this.gradeNum = num;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }
}
