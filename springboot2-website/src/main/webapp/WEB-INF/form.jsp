<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>留言板</title>
</head>
<body>
<form action="process.jsp" method=post>
    请输入您的姓名：<input type="text" name="name"><br><br>
    请输入标题：<input type="text" name="title"><br><br>
    请输入留言内容：<br>
    &nbsp&nbsp&nbsp&nbsp
    <textarea rows="10" cols="30" name="content"></textarea><br><br>
    <input type="submit" value="submit">
</form>
<br>
<form action="show.jsp">
    <input type="submit" value="查看留言板">
</form>
</body>
</html>
