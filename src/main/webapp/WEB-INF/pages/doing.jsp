<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="/plugins/nifty-v2.5/demo/css/bootstrap.min.css" rel="stylesheet">
<script src="/plugins/nifty-v2.5/demo/js/jquery-2.2.4.min.js"></script>
<script src="/plugins/nifty-v2.5/demo/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/public/style/js/page_common.js"></script>
<link href="/public/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="/public/stylesheet" type="text/css" href="style/css/index_1.css" />
</head>
<body style="background-color: #E1FFFF" onload="skip()">
	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13"
					src="<%=request.getContextPath()%>/public/style/images/title_arrow.gif" />
				处理页面
			</div>
		</div>
	</div>
	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea" style="margin-top: 200px">
	<table class="MainArea_Content" align="center" cellspacing="0"
			cellpadding="0">
				<tr  id="TableTitle" style="height: 30px">
					<td align="center" style="font-size: medium;width: 15%">	处理中,请稍后...</td>
				</tr>
		</table>
	</div>
	
</body>
<script type="text/javascript">
	function skip(){
		setTimeout(b,100);
	}
	function b(){
		location.href="<%=request.getContextPath()%>/pcenter";
	}
</script>
	
</html>
