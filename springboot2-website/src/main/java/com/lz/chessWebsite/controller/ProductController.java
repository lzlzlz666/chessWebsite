package com.lz.chessWebsite.controller;

import com.lz.chessWebsite.pojo.Product;
import com.lz.chessWebsite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // 根据类别获取商品信息
    @GetMapping("/productsByCategory")
    public List<Product> getProductsByCategory(int category) {
        return productService.getProductsByCategory(category);
    }

    //  查询全部products数据  +  增删改查

    //查询全部products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    //根据products_id查询products
    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }
    //增加一条商品
    @PostMapping("/products")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }
    //更改商品信息
    @PutMapping("/products/{productId}")
    public void updateProduct(@PathVariable int productId, @RequestBody Product product) {
        product.setProductId(productId);
        productService.updateProduct(product);
    }
    //删除指定products_id的商品
    @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
    }

    //批量删除
    @DeleteMapping("/products")
    public void deleteProducts(@RequestBody List<Integer> productIds) {
        productService.deleteProducts(productIds);
    }

    /**
     * 后台首页获取用户总数
     * @return
     */
    @GetMapping("/products/count")
    @ResponseBody
    public Map<String, Object> getUserCount() {
        Map<String, Object> response = new HashMap<>();
        int count = productService.getProductCount();
        response.put("count", count);
        return response;
    }
}
