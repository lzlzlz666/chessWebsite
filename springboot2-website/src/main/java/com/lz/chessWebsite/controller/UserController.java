package com.lz.chessWebsite.controller;

import com.lz.chessWebsite.pojo.User;
import com.lz.chessWebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {


    @Autowired
    private UserService userService;
    // 新增用户
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return ResponseEntity.ok().body(newUser);
    }

    // 删除用户
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    // 更新用户信息
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok().body(updatedUser);
    }

    // 查询用户信息
    @GetMapping("/searchUser")
    public ResponseEntity<List<User>> searchUser(@RequestParam("username") String username) {
        List<User> users = userService.searchUser(username);
        return ResponseEntity.ok().body(users);
    }

    /**
     * 后台首页获取用户总数
     * @return
     */
    @GetMapping("/users/count")
    @ResponseBody
    public Map<String, Object> getUserCount() {
        Map<String, Object> response = new HashMap<>();
        int count = userService.getUserCount();
        response.put("count", count);
        return response;
    }
}
