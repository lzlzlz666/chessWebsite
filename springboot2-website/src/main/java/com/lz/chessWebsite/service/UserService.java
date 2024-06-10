package com.lz.chessWebsite.service;

import com.lz.chessWebsite.mapper.PropertiesMapper;
import com.lz.chessWebsite.mapper.UserMapper;
import com.lz.chessWebsite.pojo.Admin;
import com.lz.chessWebsite.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User authenticate(String username, String password) {
        return userMapper.findByUsernameAndPassword(username, password);
    }


    public User register(User user) {
        // 检查用户名是否已存在
        User existingUser = userMapper.findByUsername(user.getUsername());
        if (existingUser != null) {
            return null; // 用户名已存在，注册失败
        }

        // 用户名不存在，可以进行注册
        int affectedRows = userMapper.insert(user);
        if (affectedRows > 0) {
            return user; // 返回插入的用户对象
        } else {
            return null; // 插入失败
        }
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public User findById(int userId) {
        return userMapper.findById(userId);
    }

    public void updateUserField(Integer userId, String field, String newValue) {
        userMapper.updateUserField(userId, field, newValue);
    }

    public User addUser(User user) {
        userMapper.addUser(user);
        return user;
    }

    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    public User updateUser(Integer id, User userDetails) {
        userDetails.setId(id);
        userMapper.updateUser(userDetails);
        return userDetails;
    }

    public List<User> searchUser(String username) {
        return userMapper.searchUser(username);
    }

    public int getUserCount() {
        return userMapper.countUsers();
    }
}
