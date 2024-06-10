package com.lz.chessWebsite.mapper;

import com.lz.chessWebsite.pojo.Admin;
import com.lz.chessWebsite.pojo.AdminGrade;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AdminGradeMapper {

    //根据admin表里的admin_id，得到admingrade表里的grade数据
    @Select("SELECT grade FROM admingrade WHERE admin_id = #{adminId}")
    int getAdminGrade(int adminId);

    //更新admingrade表里grade值
    @Update("UPDATE admingrade SET grade = #{grade} WHERE admin_id = #{adminId}")
    void updateAdminGrade(int adminId, int grade);

    //插入admingrade表新增数据
    @Insert("INSERT INTO admingrade (admin_id, grade) VALUES (#{AdminId}, #{grade})")
    @Options(useGeneratedKeys = true, keyProperty = "AdminGradeId")
    void addAdminGrade(AdminGrade admingrade);

    @Delete("DELETE FROM admingrade WHERE admin_id = #{id}")
    void deleteAdminGradeByAdminId(Integer id);

}
