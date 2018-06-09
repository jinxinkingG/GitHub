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
  <script type="text/javascript">

/*function hide(){
  document.getElementById("d1").style.display='none';
  }
  function show(){
  document.getElementById("d1").style.display='block';
  } 
  window.onload=function autodivheight(){
  var height=1000;
  height=doucument.getElementById("div_side").style.height;
  doucument.getElementById("body").style.height=height;
  } */
  </script>
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
<div style="height:auto">
<div id="div_side" class="side_column">
<ul>
<li><a href="Choose">数据源选择</a></li>
<li><a href="Analysis">数据分析</a></li>
<li><a href="Show">分析结果</a></li>
</ul>
</div>
 </div>
<div id="body" style="float:left" >
<a href="echarts.jsp">当日热词分析 - 柱</a> <br/>
<a href="echarts_cloud.jsp">当日热词分析 - 文字云</a> <br/>
</div>
  </body>
</html>