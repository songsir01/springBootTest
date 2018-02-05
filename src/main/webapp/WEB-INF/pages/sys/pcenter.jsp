<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<link href="<%=request.getContextPath() %>/plugins/nifty-v2.5/demo/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/plugins/nifty-v2.5/demo/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath() %>/plugins/nifty-v2.5/demo/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=request.getContextPath() %>/public/style/js/page_common.js"></script>
<link href="<%=request.getContextPath() %>/public/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="<%=request.getContextPath() %>/public/stylesheet" type="text/css" href="style/css/index_1.css" />
</head>
<body style="background-color: #E1FFFF" onload="isLogin()">
	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13"
					src="<%=request.getContextPath()%>/public/style/images/title_arrow.gif" />
				系统菜单-->个人中心
			</div>
		</div>
	</div>
	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
	<form action="updatePcenter" method="post" enctype="multipart/form-data">
		<table class="MainArea_Content" align="center" cellspacing="0"
			cellpadding="0">
				<tr>
					<td><br></td>
				</tr>
				<tr  id="TableTitle" style="height: 30px">
					<td align="center" style="font-size: medium;width: 15%">
						<div id="fileup">
							<img style='max-width:68px;width:68px;width:expression(width>68?"68px":width "px");max-width: 68px;' 
							src="<%=request.getContextPath() %>/style/userimg/${user.img}">
							<input type="hidden" name="image" value="${user.img}">
							<input type="hidden" name="image2" value="<%=request.getContextPath() %>/style/userimg/${user.img}">
						</div>
						<input type="file" name="imageUrl"  onchange="fileClear(this)"/>
					</td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr  id="TableTitle" style="height: 30px">
					<td align="center" style="font-size: medium;width: 15%">用户名：<input id="username" name="username" value="${user.username }" readonly="readonly" style="text-align: center;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr  id="TableTitle" style="height: 30px">
					<td align="center" style="font-size: medium;width: 15%">密&nbsp;码：<input name="password" id="password" style="text-align: center; " placeholder="修改后将退出登录"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr  id="TableTitle" style="height: 30px">
					<td align="center" style="font-size: medium;width: 15%">
					性&nbsp;别:&nbsp;
						<input type="radio" name="sex" value="男"> 男
						&nbsp;
						<input type="radio" name="sex" value="女"> 女
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="hidden" id="sex" value="${user.sex }">
					</td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr  id="TableTitle" style="height: 30px">
					<td align="center" style="font-size: medium;">简&nbsp;介：<input name="description" id="description" value="${user.description }"  style="text-align: center;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr  id="TableTitle" style="height: 30px">
					<td align="center" style="font-size: medium;">角&nbsp;色：<input id="rname" name="rname" style="text-align: center;"readonly="readonly" value="${user.role.rname }"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr  id="TableTitle" style="height: 30px">
					<td align="center" style="font-size: medium;"><input type="submit" value="更新"></input>&nbsp;</td>
				</tr>
		</table>
		</form>
	</div>
	
</body>

<script type="text/javascript">
	//判断是否登录
	function isLogin(){
		$.ajax({
			url:"<%=request.getContextPath()%>/isLogin",
			type:"get",
			dataType:"text",
			success:function(text){
				if(text==1){
					alert("登录信息超时，请重新登录...")
					window.parent.location.href="<%=request.getContextPath()%>/login.jsp";
				}
				
			}
		})
		
		var sex=$("#sex").val();
		var sexs=document.getElementsByName("sex");
		for(var i=0;i<sexs.length;i++){
			if(sexs[i].value==sex){
				sexs[i].checked=true;
			}
		}
	}
	function fileClear(file){
		var prevDiv = document.getElementById('fileup');  
		if (file.files && file.files[0])  
		 {  
		 	var reader = new FileReader();  
		 	reader.onload = function(evt){  
		 	prevDiv.innerHTML = '<img src="' + evt.target.result + '" style="width:68px;height:68px"/>';  
		 }    
		 	reader.readAsDataURL(file.files[0]);  
		 }  
		 else    
		 {  
		 	prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
		 }  
	}
</script>
</html>
