package com.lz.chessWebsite.service;

import com.lz.chessWebsite.mapper.InformationMapper;
import com.lz.chessWebsite.pojo.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformationService {

    @Autowired
    private InformationMapper informationMapper;

    public void updateInformationField(Integer userId, String field, String newValue) {
        informationMapper.updateInformationField(userId, field, newValue);
    }

    /**
     * 在informations表中插入数据
     * @param userId 用户ID
     * @param username 用户名
     * @param password 密码
     */
    public void addInformation(Integer userId, String username, String password) {
        informationMapper.addInformation(userId, username, password);
    }

    public Information getInformationByUserId(Integer userId) {
        return informationMapper.getInformationByUserId(userId);
    }
}
