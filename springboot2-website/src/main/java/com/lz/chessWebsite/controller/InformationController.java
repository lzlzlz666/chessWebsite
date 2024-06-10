package com.lz.chessWebsite.controller;

import com.lz.chessWebsite.pojo.Information;
import com.lz.chessWebsite.pojo.User;
import com.lz.chessWebsite.service.InformationService;
import com.lz.chessWebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class InformationController {

    @Autowired
    private InformationService informationService;

    @Autowired
    private UserService userService;

    @PostMapping("/updateInformationField")
    @ResponseBody
    public String updateInformationField(@RequestParam("field") String field, @RequestParam("newValue") String newValue, HttpServletRequest request) {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");

        // 检查用户名是否存在于数据库中
        User user = userService.findByUsername(newValue);
        if (user != null) {
            return "fail";
        }

        // 检查用户名是否存在于数据库中
        user = userService.findByUsername(username);

        Integer userId = user.getId();

        informationService.updateInformationField(userId, field, newValue);
        // 如果 username 或者 password 被改变了，需要同时改变 user 表里的 username 以及 password
        if ("username".equals(field) || "password".equals(field)) {
            userService.updateUserField(userId, field, newValue);
            // 更新会话中的用户名
            if ("username".equals(field)) {
                session.setAttribute("username", newValue);
            }
        }

        return "success";
    }

    @GetMapping("/getUserInformation")
    @ResponseBody
    public Information getUserInformation(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        User user = userService.findByUsername(username);

        Integer userId = user.getId();
        if (userId == null) {
            // 用户 ID 为空
            return null;
        }

        // 根据用户 ID 从数据库中获取用户信息
        Information information = informationService.getInformationByUserId(userId);
        return information;
    }
}
