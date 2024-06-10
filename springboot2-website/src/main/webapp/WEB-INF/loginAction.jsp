<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    if ("linzhou".equals(username) && "123456".equals(password)) {
        // 登录成功, 重定向到成功页面或者仪表板
        response.sendRedirect("success.jsp");
    } else {
        // 登录失败, 可以重定向回登录页面并附带错误消息
        response.sendRedirect("login.jsp?error=true");
    }
%>
