<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*,java.text.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%//获取到用户输入得信息,并且将用户得信息处理成一个字符串
    request.setCharacterEncoding("UTF-8");
    String name=request.getParameter("name");
    String title=request.getParameter("title");
    String content=request.getParameter("content");
//out.println(name+"<br>");
//out.println(title+"<br>");
//out.println(content+"<br>");
    if(name=="")
    {
        name="游客"+(int)(Math.random()*1000);
    }
    if(title=="")
    {
        title="无标题";
    }
    if(content==""){
        content="无内容";
    }
    Date time=new Date();
    DateFormat bf = new SimpleDateFormat("MM.dd  HH:mm");
    String format = bf.format(time);
    out.println(format);
    String s=name+"#"+title+"#"+format+"#"+content+"#";
    addMessage(s);
%>
<%!
    //定义向量vector装入用户输入得信息
    Vector v=new Vector();
    ServletContext application;
    synchronized void addMessage(String s){
        application=getServletContext();
        v.add(s);
        application.setAttribute("Message", v);
    }
%>
<a href="form.jsp"> 返回首页</a>
</body>
</html>
