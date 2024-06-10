package com.lz.chessWebsite.service;

import com.lz.chessWebsite.mapper.PurchaseMapper;
import com.lz.chessWebsite.pojo.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseMapper purchaseMapper;

    public void save(Purchase purchase) {
        purchaseMapper.insert(purchase);
    }

    public List<Purchase> getPurchasesByUserId(Integer userId) {
        return purchaseMapper.findByUserId(userId);
    }

    public double getTotalPurchaseAmount() {
        return purchaseMapper.getTotalPurchaseAmount();
    }
}
