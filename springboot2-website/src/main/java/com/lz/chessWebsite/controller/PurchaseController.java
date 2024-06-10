package com.lz.chessWebsite.controller;

import com.lz.chessWebsite.pojo.Purchase;
import com.lz.chessWebsite.pojo.User;
import com.lz.chessWebsite.service.PropertiesService;
import com.lz.chessWebsite.service.PurchaseService;
import com.lz.chessWebsite.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private PropertiesService propertiesService;


    /**
     * 将购买商品插入数据库中
     * @param requestBody
     * @param request
     * @return
     */
    @PostMapping("/checkout")
    @ResponseBody
    public String checkout(@RequestBody Map<String, List<String>> requestBody, HttpServletRequest request) {
        System.out.println("666");
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");

        User user =  userService.findByUsername(username);
        Integer userId = user.getId();


        if (userId == null || username == null) {
            return "fail"; // 用户未登录
        }

        // 从请求体中获取购物车商品描述数组
        List<String> products = requestBody.get("products");
        System.out.println(products);
        if (products == null || products.isEmpty()) {
            return "fail"; // 购物车为空
        }

        // 将购买记录插入数据库
        for (String productDescription : products) {
            Purchase purchase = new Purchase();

            System.out.println(userId);
            System.out.println(productDescription);

            purchase.setUserId(userId);
            purchase.setProductsDescription(productDescription);
            purchase.setPurchaseDate(LocalDateTime.now());
            purchaseService.save(purchase);
        }

        return "success";
    }

    /**
     * 当登录的用户，在购物车里面点击购买按钮的时候
     将uer_id对应的Properties表里balance（财产信息）-> 进行扣除
     * @param request
     * @param amount
     * @return
     */
    @GetMapping("/deleteCoins")
    @ResponseBody
    public String addCoins(HttpServletRequest request, int amount) {
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
            boolean success = propertiesService.deleteCoins(userId, amount);
            if (success) {
                return "success";
            }
        }
        return "fail";
    }

    /**
     * 登录时候，在show.html里面渲染我的财富（根据purchase表里的购买信息）
     * @param request
     * @return
     */
    @GetMapping("/getUserGoods")
    @ResponseBody
    public List<Purchase> getUserGoods(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String username  = (String) session.getAttribute("username");
        User user =  userService.findByUsername(username);
        Integer userId = user.getId();

        if (userId != null) {
            return purchaseService.getPurchasesByUserId(userId);
        }
        return null;
    }

    @GetMapping("/purchases/total")
    public ResponseEntity<Double> getTotalPurchaseAmount() {
        double totalAmount = purchaseService.getTotalPurchaseAmount();
        return ResponseEntity.ok().body(totalAmount);
    }

}
