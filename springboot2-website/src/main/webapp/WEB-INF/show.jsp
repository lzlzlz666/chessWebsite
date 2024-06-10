<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%//从application获取输入得内容
    Vector v=(Vector)application.getAttribute("Message");
//out.println(v);
%>
<h1>这是一个留言板</h1>

<%
    for(int i=0;i<v.size();i++)
    {
        String a=(String)v.elementAt(i);
        //out.println(a+"<br>");
        String[] a1=a.split("#");//切割分离出信息
%>

姓名：<%=a1[0] %><br>
主题：<%=a1[1] %><br>
留言时间：<%=a1[2] %><br>
留言内容：<%=a1[3] %><br>
<hr>
<%
    }
%>
</table>
</body>
</html>
