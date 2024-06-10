<%--
  Created by IntelliJ IDEA.
  User: linz
  Date: 2024/5/1
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%--  head.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/head.css">
<base href="${pageContext.request.contextPath}/">
<div class="top">
    <div class="user">
        <li><a href="register.jsp">注册账号</a></li>
    </div>
    <div class="icon"></div>
    <header>
        <div class="navbar">
            <li><a href="index.jsp">首页</a></li>
            <li><a href="login.jsp">登录账号</a></li>
            <li><a href="chess-manuals.jsp">棋谱欣赏</a></li>
            <li><a href="game.jsp">围棋对弈</a></li>
            <li><a href="shopping-mall.jsp">购物商场</a></li>
            <li><a href="my-wealth.jsp">我的财富</a></li>
        </div>
    </header>
</div>


