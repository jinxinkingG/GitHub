<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="CSS/Index.css">
    <title><%=session.getAttribute("username") %>的空间</title>  
  </head>
<%--   <script type="text/javascript">
  function hide(){
  document.getElementById("d1").style.display='none';
  }
  function show(){
  document.getElementById("d1").style.display='block';
  }
  </script> --%>
<body>
<div class="header">
${username}的空间
<input type="button" value="退出登陆" onclick="javascript:window.location.href='login_out!login_out'"/>

</div>
<div class="head">

</div>
<div class="navigation">
<a href="index.jsp">首页</a>&nbsp;&nbsp;
</div>
<div class="side_column">
<ul>
<li><a href="Choose">数据源选择</a></li>
<li><a href="Analysis">数据分析</a></li>
<li><a href="Show">分析结果</a></li>
</ul>
</div>
<div style="float:left">
<form action="Collect">
<div id="d1">
输入要爬取的网页源地址：<input type="text" name="PathAdd">
爬取最大深度：<input type="text" name="maxDepth">
</div>
<%-- <select name="maxDepth">
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
</select> --%>
<input type="submit" value="提交"><br><br>
<p>${result}</p>
<input type="button" value="refresh" onclick="javascript:window.location.href='Collect!refresh'">
<input type="button" value="clear" onclick="javascript:window.location.href='Collect!Clear'"> 
</form>
</div>
  </body>
</html>