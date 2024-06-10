package com.lz.chessWebsite.service;

import com.lz.chessWebsite.mapper.PropertiesMapper;
import com.lz.chessWebsite.pojo.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertiesService {

    @Autowired
    private PropertiesMapper propertiesMapper;

    public Properties getPropertiesByUserId(Integer userId) {
        return propertiesMapper.findByUserId(userId);
    }

    // 给properties数据库插入初始化数据
    public void insertDefault(Integer userId) {
        propertiesMapper.insertDefault(userId);
    }

    //增加金币
    public boolean addCoins(Integer userId, int amount) {
        Properties properties = propertiesMapper.findByUserId(userId);
        if (properties != null) {
            properties.setBalance(properties.getBalance() + amount);
            propertiesMapper.update(properties);
            return true;
        }
        return false;
    }

    //购买商品的时候，消费金币
    public boolean deleteCoins(Integer userId,int amount){
        Properties properties = propertiesMapper.findByUserId(userId);
        if (properties != null) {
            properties.setBalance(properties.getBalance() - amount);
            propertiesMapper.update(properties);
            return true;
        }
        return false;
    }

}
