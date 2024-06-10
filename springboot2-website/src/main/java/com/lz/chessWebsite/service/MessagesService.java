package com.lz.chessWebsite.service;


import com.lz.chessWebsite.mapper.MessagesMapper;
import com.lz.chessWebsite.pojo.Message;
import com.lz.chessWebsite.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class  MessagesService {

    @Autowired
    private MessagesMapper messagesMapper;

    @Autowired
    private UserService userService;


    public void save(Message message) {
        // 根据用户ID获取用户名
        User user = userService.findById(message.getUserId());
        if (user != null) {
            message.setUsername(user.getUsername());
            message.setTimestamp(LocalDateTime.now()); // 设置留言时间
            messagesMapper.insert(message);
        }
    }

    public List<Message> getAllMessages() {
        List<Message> messages = messagesMapper.findAll();
        for (Message message : messages) {
            User user = userService.findById(message.getUserId());
            if (user != null) {
                message.setUsername(user.getUsername());
            }
        }
        return messages;
    }
}
