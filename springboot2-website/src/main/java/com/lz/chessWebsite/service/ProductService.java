package com.lz.chessWebsite.service;

import com.lz.chessWebsite.mapper.ProductMapper;
import com.lz.chessWebsite.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    // 根据类别获取商品信息
    public List<Product> getProductsByCategory(int category) {
        return productMapper.findByCategory(category);
    }

    //调用Mapper层，查询全部（为了后续渲染前端） 和 增删改查 操作
    public List<Product> getAllProducts() {
        return productMapper.getAllProducts();
    }

    public Product getProductById(int productId) {
        return productMapper.getProductById(productId);
    }

    public void addProduct(Product product) {
        productMapper.addProduct(product);
    }

    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    public void deleteProduct(int productId) {
        productMapper.deleteProduct(productId);
    }
    public void deleteProducts(List<Integer> productIds) {
        for (Integer productId : productIds) {
            productMapper.deleteProduct(productId);
        }
    }

    public int getProductCount() {
        return productMapper.countProducts();
    }

}
