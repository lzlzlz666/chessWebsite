package com.lz.chessWebsite.mapper;

import com.lz.chessWebsite.pojo.Information;
import org.apache.ibatis.annotations.*;

@Mapper
public interface InformationMapper {

    @Update("UPDATE informations SET ${field} = #{newValue} WHERE user_id = #{userId}")
    void updateInformationField(@Param("userId") Integer userId, @Param("field") String field, @Param("newValue") String newValue);

    /**
     * 向informations表中插入数据
     * @param userId 用户ID
     * @param username 用户名
     * @param password 密码
     */
    @Insert("INSERT INTO informations (user_id, username, password) VALUES (#{userId}, #{username}, #{password})")
    void addInformation(Integer userId, String username, String password);


    @Select("SELECT * FROM informations WHERE user_id = #{userId}")
    Information getInformationByUserId(Integer userId);
}
