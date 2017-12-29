<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isELIgnored="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>练习一下管理系统</title>
</head>
	<frameset rows="100px,*" framespacing="0" border="0" frameborder="0">
		<frame src="public/top.jsp" scrolling="no" noresize /> 
		<frameset cols="178px,*">
			<frame noresize src="public/left.jsp" scrolling="yes" /> 
			<frame noresize name="right" src="public/right.jsp" scrolling="yes" /> 
		</frameset>
	</frameset>
	<noframes>
		<body onload="isLogin()">
			你的浏览器不支持框架布局，推荐你使用<a href="http://www.firefox.com.cn/download/" style="text-decoration: none;">火狐浏览器</a>,
			<a href="http://www.google.cn/intl/zh-CN/chrome/" style="text-decoration: none;">谷歌浏览器</a>
		</body>
	</noframes>
	<script type="text/javascript">
	function isLogin(){
		$.ajax({
			url:"<%=request.getContextPath()%>/isLogin",
			type:"get",
			dataType:"text",
			success:function(text){
				if(text==1){
					alert("您还未登录...")
					window.parent.location.href="<%=request.getContextPath()%>/login.jsp";
				}
				
			}
		})
	}
	</script>
</html>