<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="my-login-box">
    <h2>Register</h2>
    <form id="loginForm" action="loginAction.jsp" method="post">
        <div class="user-box">
            <input type="text" name="username" required="">
            <label>Username</label>
        </div>
        <div class="user-box">
            <input type="password" name="password" required="">
            <label>Password</label>
        </div>
        <a href="#" onclick="document.getElementById('loginForm').submit();">
            <span></span>
            <span></span>
            <span></span>
            <span></span> Submit
        </a>
    </form>
</div>
<script>
    // 如果需要，这里可以添加额外的JavaScript代码
</script>
</body>
</html>
