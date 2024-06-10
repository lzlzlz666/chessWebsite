package com.lz.chessWebsite.controller;

import com.lz.chessWebsite.pojo.User;
import com.lz.chessWebsite.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 校验用户登录后端，并且通过session保存登录的username，为后面前端渲染做准备
     * @param username
     * @param password
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpServletRequest request) {
        User user = userService.authenticate(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return new ModelAndView("redirect:/index.html");
        } else {
            //return new ModelAndView("login").addObject("error", "Invalid username or password");
            return new ModelAndView("redirect:/loginFail.html");
        }
    }

    /**
     * 与index.html里的script进行联系，完成渲染前端登录后username的显示
     * @param request
     * @return
     */
    @GetMapping("/getUsername")
    @ResponseBody
    public String getUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 检查是否存在用户名
        if (session.getAttribute("username") != null) {
            return session.getAttribute("username").toString();
        } else {
            return "您还未登录"; // 如果没有用户登录，返回一个默认值
        }
    }

    /**
     * 检查用户是否已经登录
     * @param request
     * @return
     */
    @GetMapping("/checkLogin")
    @ResponseBody
    public String checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 检查是否存在用户名
        if (session.getAttribute("username") != null) {
            return "true"; // 用户已登录
        } else {
            return "false"; // 用户未登录
        }
    }

}
