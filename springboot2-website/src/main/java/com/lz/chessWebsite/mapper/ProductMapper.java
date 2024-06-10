package com.lz.chessWebsite.mapper;

import com.lz.chessWebsite.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    // 根据类别查找商品
    @Select("SELECT * FROM products WHERE category = #{category}")
    List<Product> findByCategory(@Param("category") int category);


    //后台管理商品-> 增删改查
//    @Select("SELECT * FROM products")
//    List<Product> getAllProducts();

    @Select("SELECT * FROM products")
    @Results({
            @Result(property = "productId", column = "products_id"),
            @Result(property = "productImage", column = "product_image")
    })
    List<Product> getAllProducts();

    @Select("SELECT * FROM products WHERE products_id = #{productId}")
    Product getProductById(int productId);

    @Insert("INSERT INTO products (category, name, price, description, stock, product_image) " +
            "VALUES (#{category}, #{name}, #{price}, #{description}, #{stock}, #{productImage})")
    void addProduct(Product product);

    @Update("UPDATE products SET category = #{category}, name = #{name}, price = #{price}, " +
            "description = #{description}, stock = #{stock}, product_image = #{productImage} " +
            "WHERE products_id = #{productId}")
    void updateProduct(Product product);

    @Delete("DELETE FROM products WHERE products_id = #{productId}")
    void deleteProduct(int productId);

    //后台首页：总道具数 --> 获取
    @Select("SELECT COUNT(*) FROM products")
    int countProducts();

}
