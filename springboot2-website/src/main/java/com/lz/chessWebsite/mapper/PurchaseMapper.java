package com.lz.chessWebsite.mapper;

import com.lz.chessWebsite.pojo.Purchase;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PurchaseMapper {

    @Insert("INSERT INTO purchases (user_id, products_description, purchase_date) " +
            "VALUES (#{userId}, #{productsDescription}, #{purchaseDate})")
    void insert(Purchase purchase);

    @Select("SELECT * FROM purchases WHERE user_id = #{userId}")
    List<Purchase> findByUserId(Integer userId);

    //后台首页:总购买道具金额数 --> 获取
    @Select("SELECT SUM(pr.price) FROM purchases p JOIN products pr ON p.products_description = pr.description")
    double getTotalPurchaseAmount();
}
