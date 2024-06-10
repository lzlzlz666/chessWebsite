package com.lz.chessWebsite.pojo;

public class AdminGrade {
    private Integer AdminGradeId;
    private Integer AdminId;
    private Integer grade;

    public Integer getAdminGradeId() {
        return AdminGradeId;
    }

    public void setAdminGradeId(Integer adminGradeId) {
        AdminGradeId = adminGradeId;
    }

    public Integer getAdminId() {
        return AdminId;
    }

    public void setAdminId(Integer adminId) {
        AdminId = adminId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
