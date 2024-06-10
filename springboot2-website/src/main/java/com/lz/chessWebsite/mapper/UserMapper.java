package com.lz.chessWebsite.mapper;

import com.lz.chessWebsite.pojo.Admin;
import com.lz.chessWebsite.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    @Insert("INSERT INTO user (username, password) VALUES (#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") // 添加此行以返回自动生成的ID
    int insert(User user); // 修改返回值为int，以表示插入的记录数

    @Select("SELECT * FROM user WHERE id = #{userId}")
    User findById(@Param("userId") int userId);

    //当informations表里username或者password改动时候，更改user表里的数据
    @Update("UPDATE user SET ${field} = #{newValue} WHERE id = #{userId}")
    void updateUserField(@Param("userId") Integer userId, @Param("field") String field, @Param("newValue") String newValue);



    //后台增删改查操作
    @Insert("INSERT INTO user (username, password) VALUES (#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "adminId")
    void addUser(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUser(Integer id);

    @Update("UPDATE user SET username = #{username}, password = #{password} WHERE id = #{id}")
    void updateUser(User user);

    @Select("SELECT * FROM user WHERE username LIKE CONCAT('%', #{username}, '%')")
    List<User> searchUser(String username);


    //后台首页：总注册用户数-->获取
    @Select("SELECT COUNT(*) FROM user")
    int countUsers();
}
