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
  </body>
</html>