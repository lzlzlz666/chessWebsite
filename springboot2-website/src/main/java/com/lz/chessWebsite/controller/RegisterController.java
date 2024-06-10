package com.lz.chessWebsite.controller;

import com.lz.chessWebsite.mapper.PropertiesMapper;
import com.lz.chessWebsite.pojo.User;
import com.lz.chessWebsite.service.InformationService;
import com.lz.chessWebsite.service.PropertiesService;
import com.lz.chessWebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private PropertiesService propertiesService;

    @Autowired
    private InformationService informationService;

    /**
     * 后端注册业务逻辑代码
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ModelAndView register(User user) {
        User registeredUser = userService.register(user);
        if (registeredUser != null) {
            Integer user_id = registeredUser.getId();

            //注册成功的同时，将财产信息存储到properties数据库中（通过user_id作为外键关联）
            propertiesService.insertDefault(user_id);

            Integer userId = user_id;
            // 在informations表中插入对应的数据
            informationService.addInformation(userId, user.getUsername(), user.getPassword());

            return new ModelAndView("redirect:/registerSuccess.html");
        } else {
            return new ModelAndView("redirect:/registerFail.html");
        }
    }
}
