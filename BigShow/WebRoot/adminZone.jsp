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
${username}空间
<input type="button" value="退出登陆" onclick="javascript:window.location.href='login_out!login_out'"/>
</div>
<div class="head">

</div>
<div class="navigation">
<a href="index.jsp">首页</a>&nbsp;&nbsp;
</div>
<div class="side_column">
<ul>
<li><a href="Choose">用户管理</a></li>
<li><a href="Analysis">数据管理</a></li>
<li><a href="Show">登录日志</a></li>
</ul>
</div>
<div style="float:left">
<table width="200%" border="1">
<tr><th>用户数据列表</th></tr>
				<tr>
					<td>jinxin</td><td><a href="">查看数据</a></td><td><a href="">清空数据</a></td>
				</tr>
				<tr>
					<td>zhangsan</td><td><a href="">查看数据</a></td><td><a href="">清空数据</a></td>
				</tr>
				<tr>
					<td>lisi</td><td><a href="">查看数据</a></td><td><a href="">清空数据</a></td>
				</tr>
</table>
</div>
  </body>
</html>