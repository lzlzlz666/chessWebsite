package com.lz.chessWebsite.mapper;

import com.lz.chessWebsite.pojo.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {
    @Select("SELECT * FROM admin WHERE adminname = #{adminName} AND password = #{password}")
    Admin findByAdminNameAndPassword(@Param("adminName") String adminName, @Param("password") String password);

    @Insert("INSERT INTO admin (adminname, password) VALUES (#{adminName}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "adminId")
    void addAdmin(Admin admin);

    @Delete("DELETE FROM admin WHERE admin_id = #{id}")
    void deleteAdmin(Integer id);

    @Update("UPDATE admin SET adminname = #{adminName}, password = #{password} WHERE admin_id = #{adminId}")
    void updateAdmin(Admin admin);

    @Select("SELECT * FROM admin WHERE adminname LIKE CONCAT('%', #{username}, '%')")
    List<Admin> searchAdmin(String username);

    @Select("SELECT * FROM admin")
    List<Admin> getAllAdmins();

    //根据adminName 获取对应的admin
    @Select("SELECT * FROM admin WHERE adminname = #{adminName}")
    Admin getAdminByAdminName(String adminName);

}
