package com.lz.chessWebsite.mapper;

import com.lz.chessWebsite.pojo.Properties;
import com.lz.chessWebsite.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PropertiesMapper {

    @Select("SELECT * FROM properties WHERE user_id = #{userId}")
    Properties findByUserId(@Param("userId") Integer userId);

    //更新properties表里balance的值
    @Update("UPDATE properties SET balance = #{balance} WHERE user_id = #{userId}")
    void update(Properties properties);

    @Insert("INSERT INTO properties (user_id) VALUES (#{userId})")
    void insertDefault(Integer userId);
}
