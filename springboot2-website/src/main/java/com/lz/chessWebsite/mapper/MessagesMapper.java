package com.lz.chessWebsite.mapper;

import com.lz.chessWebsite.pojo.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessagesMapper {
    @Insert("INSERT INTO messages (user_id, text, timestamp) VALUES (#{userId}, #{text}, #{timestamp})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId", keyColumn = "message_id")
    void insert(Message message);

    @Select("SELECT * FROM messages")
    List<Message> findAll();

}
