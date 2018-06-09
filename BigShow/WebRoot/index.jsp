<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="view">
    <link rel="stylesheet" type="text/css" href="CSS/Index.css">
  </head>
<body>
<div class="header">
<c:choose>
<c:when test="${sessionScope.username==null}">
<a href="Login.jsp">登陆</a>
<a href="Register.jsp">注册</a>
</c:when>
<c:when test="${sessionScope.username!=null&&sessionScope.userType.equals('user')}">
${username}<a href="Zone">个人空间</a>

<input type="button" value="退出登陆" onclick="javascript:window.location.href='login_out!login_out'"/>
</c:when>
<c:when test="${sessionScope.username!=null&&sessionScope.userType.equals('admin')}">
${username}<a href="adminZone.jsp">管理员空间</a>

<input type="button" value="退出登陆" onclick="javascript:window.location.href='login_out!login_out'"/>
</c:when>
</c:choose>
</div>
<div class="head">

</div>
<div class="navigation">
<a href="index.jsp">首页</a>&nbsp;&nbsp;
<div style="float:right">
<input type="text" name="search"><input type="button" value="找找看"></div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="bootsrap/js/bootstrap.min.js"></script>
  </body>
</html>