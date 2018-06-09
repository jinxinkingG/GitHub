<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>My JSP 'Regisiter.jsp' starting page</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">	
	<link rel="stylesheet" type="text/css" href="CSS/common_form.css">
  </head>
  <body>
  <header>
  <div class="head-line"></div>
  </header>
 <div class="content">
 <img class="content-logo" src="img/1.png" alt="logo">
 <h1 class="content-title">创建账户</h1>
<div class="content-form">
<form action="register.action" onsubmit="return submitTest()">
<div id="change_margin_1">&nbsp;&nbsp;&nbsp;用户名：<input type="text" name="username" placeholder="请输入用户名" onblur="oBlur_1()" onfocus="cFocus_1()"></div><p id="remind_1"></p>
<div id="change_margin_2">&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" placeholder="请输入密码" onblur="oBlur_2()" onfocus="oFocus_2()"></div><p id="remind_2"></p>
<div id="change_margin_4">确认密码：<input type="password" name="cfmPsw" onblur="oBlur_3()" onfocus="oFocus_3()"></div><p id="remind_3"></p>
<div id="chang_margin_3">
<input class="content-form-signup" type="submit" value="注册"/>
</div>
</form>
</div>
<div class="content-login-description">已有账户?</div>
<div> <a class="content-login-link" href="Login.jsp">登录</a></div>
</div>
<script type="text/javascript" src="JS/Register_from.js"></script>
  </body>
</html>
