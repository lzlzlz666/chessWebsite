// 在 Controller 层添加请求处理方法来处理前端的请求
package com.lz.chessWebsite.controller;

import com.lz.chessWebsite.pojo.Properties;
import com.lz.chessWebsite.pojo.User;
import com.lz.chessWebsite.service.PropertiesService;
import com.lz.chessWebsite.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class PropertiesController {

    @Autowired
    private PropertiesService propertiesService;

    @Autowired
    private UserService userService;

    /**
     * 将登录用户的财产信息存储，渲染至前端
     * @param request
     * @return
     */
    @GetMapping("/getUserProperties")
    @ResponseBody
    public String getUserProperties(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        String username  = (String) session.getAttribute("username");
        User user =  userService.findByUsername(username);
        Integer id = user.getId();
        userId = id;

        if (userId != null) {
            Properties properties = propertiesService.getPropertiesByUserId(userId);
            if (properties != null) {
                return String.valueOf(properties.getBalance());
            }
        }
        return "0";
    }


    /**
     * 后端处理充值的业务逻辑代码
     * @param request
     * @param requestBody
     * @return
     */
    @PostMapping("/addCoins")  // 修改为 @PostMapping
    @ResponseBody
    public String addCoins(HttpServletRequest request,@RequestBody Map<String, Integer> requestBody) {  // 使用 @RequestBody 获取请求中的 JSON 数据
        int amount = requestBody.get("amount");  // 获取 amount 参数
        HttpSession session = request.getSession();

        System.out.println(amount);
        Integer userId = (Integer) session.getAttribute("userId");
        String username  = (String) session.getAttribute("username");
        System.out.println(userId);
        System.out.println(username);

        User user =  userService.findByUsername(username);
        Integer id = user.getId();
        System.out.println(id);
        userId = id;

        if (userId != null) {
            System.out.println("userId不是空");
            boolean success = propertiesService.addCoins(userId, amount);
            if (success) {
                return "success";
            }
        }
        return "fail";
    }



    /**
     * 检查当前登录用户剩余金额的后端逻辑
     */
    @GetMapping("/userBalance")
    @ResponseBody
    public int userBalance(HttpServletRequest request){
        HttpSession session = request.getSession();
        String username  = (String) session.getAttribute("username");
        User user =  userService.findByUsername(username);
        Integer userId = user.getId();

        if(userId != null){
            Properties properties = propertiesService.getPropertiesByUserId(userId);
            if (properties != null) {
                System.out.println("当前用户剩余金额 "+ properties.getBalance());
                return properties.getBalance();
            }
            else{
                return 0;
            }
        }
        else{
            return 0;
        }
    }

}
