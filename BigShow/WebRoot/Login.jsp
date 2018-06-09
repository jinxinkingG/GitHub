<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>Login Page</title>
  <link rel="stylesheet" type="text/css" href="CSS/common_form.css">
  </head>
  	
  <body>
  <script>
  function onSubmit()
  {
  var a = document.getElementsByTagName("input")[0].value;
  var b = document.getElementsByTagName("input")[1].value;
  if(!a||!b){
  document.getElementById("pp").innerHTML="请输入登录信息";
  return false;
  }else if(a&&b){
  document.getElementById("pp").innerHTML="";
  return true;
  }
  }
  </script>
   <header>
  <div class="head-line"></div>
  </header>
 <div class="content">
 <img class="content-logo" src="img/1.png" alt="logo">
 <h1 class="content-title">登录账户</h1>
<div class="content-form">
<form action="login!login" onsubmit="return onSubmit()">
	<c:choose>
		<c:when test="${username}=='false'">
		用户名不存在的
		</c:when>
		<c:when test="${password}=='false'">密码错误</c:when>
		</c:choose>
<div>&nbsp;用户名：<input type="text" name="username" placeholder="请输入用户名"></div><br>
<div>&nbsp;密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" placeholder="请输入密码"></div>
<p style="color:#f00" id="pp"></p>
<input class="content-form-signup" type="submit" value="登录"/>
</form>
</div>
<div class="content-login-description">没有账户?</div>
<div> <a class="content-login-link" href="Register.jsp">注册</a></div>
</div>
  </body>
</html>
