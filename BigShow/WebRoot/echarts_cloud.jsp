<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<script type="text/javascript" src="echarts/echarts-wordcloud.js"></script>
<script type="text/javascript">
		var xmlHttp ;
		
		var myChart ;
		
		var option ;
		
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
			
			option = {
	                tooltip: {},
	                series: [{
	                    type: 'wordCloud',
	                    gridSize: 2,
	                    sizeRange: [12, 50],
	                    rotationRange: [-90, 90],
	                    shape: 'pentagon',
	                    width: 600,
	                    height: 400,
	                    drawOutOfBound: true,
	                    textStyle: {
	                        normal: {
	                            color: function () {
	                                return 'rgb(' + [
	                                    Math.round(Math.random() * 160),
	                                    Math.round(Math.random() * 160),
	                                    Math.round(Math.random() * 160)
	                                ].join(',') + ')';
	                            }
	                        },
	                        emphasis: {
	                            shadowBlur: 10,
	                            shadowColor: '#333'
	                        }
	                    }
	                }]
			};
			
			// 这里就需要通过AJAX技术，来调用后台数据操作，接收返回的JSON格式数据
			// 1
			createXMLHttp();
			// 2
			xmlHttp.open("get","<%=basePath%>cloud");
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
				option.series[0].data = eval("("+resultStr+")");
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
