package com.lz.chessWebsite.controller;

import com.lz.chessWebsite.pojo.Message;
import com.lz.chessWebsite.pojo.User;
import com.lz.chessWebsite.service.MessagesService;
import com.lz.chessWebsite.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MessagesController {

    @Autowired
    private MessagesService messagesService;

    @Autowired
    private UserService userService;

    @PostMapping("/postMessage")
    @ResponseBody
    public Map<String, Object> postMessage(@RequestBody Map<String, String> requestBody, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return Collections.singletonMap("success", false);
        }
        String text = requestBody.get("text");
        if (text == null || text.isEmpty()) {
            return Collections.singletonMap("success", false);
        }

        // 获取当前时间
        LocalDateTime timestamp = LocalDateTime.now();

        // 获取用户ID
        User user = userService.findByUsername(username);
        if (user == null) {
            return Collections.singletonMap("success", false);
        }
        int userId = user.getId();

        // 保存留言到数据库
        Message message = new Message();
        message.setUserId(userId);
        message.setText(text);
        message.setTimestamp(timestamp);
        messagesService.save(message);

        // 返回成功响应
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        return response;
    }

    @GetMapping("/getAllMessages")
    @ResponseBody
    public List<Message> getAllMessages() {
        return messagesService.getAllMessages();
    }

}
