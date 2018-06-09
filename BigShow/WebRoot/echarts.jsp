<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>热词</title>
<script type="text/javascript" src="echarts/echarts.js"></script>
	<script type="text/javascript">
		var xmlHttp ;
		
		var myChart ;
		
		function createXMLHttp() {
			if (window.XMLHttpRequest != null) {
				xmlHttp = new XMLHttpRequest();
			} else {
				xmlHttp = new ActiveXObject("Microsoft.xmlhttp");
			}
		}
	
		function initChart() {
			// 初始化要显示的图标div
			myChart = echarts.init(document.getElementById('my_chart'));
			
			// 这里就需要通过AJAX技术，来调用后台数据操作，接收返回的JSON格式数据
			// 1
			createXMLHttp();
			// 2
			xmlHttp.open("get","<%=basePath%>ajax");
			// 3
			xmlHttp.onreadystatechange = chartCallback;
			// 4
			xmlHttp.send();
		}
		
		function chartCallback() {
			// 判断结果是否真正返回
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var resultStr = xmlHttp.responseText;
				// 转换为对象
				var option = eval("("+resultStr+")");
				myChart.setOption(option);
			}
		}

	</script>
</head>

<body onload="initChart();">
	<center>
		<div id="my_chart" style="width:1000px;height:400px;"></div>
	</center>
</body>
</html>
