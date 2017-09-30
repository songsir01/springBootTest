<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>登录界面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/style/js/jquery-1.4.2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/style/images/login.js"></script>
<link href="<%=request.getContextPath()%>/style/css/login2.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript">
	function blrnoname(t){
		   if($(t).val()==""||$(t).val()==null){
		     $("#susername").css("display","block");
		   }
		}
	function focnoname(t){
		   if($(t).val()==""||$(t).val()==null){
		     $("#susername").css("display","none");
		   }
		}
	function blrnopass(t){
		   if($(t).val()==""||$(t).val()==null){
		     $("#spassword").css("display","block");
		   }
		}
	function focnopass(t){
		   if($(t).val()==""||$(t).val()==null){
		     $("#spassword").css("display","none");
		   }
		}
	function regist(){
		location.href="<%=request.getContextPath()%>/regist";
	}
	function login(){
		var username=$("#username").val();
		var password=$("#password").val();
		$.ajax({
			url:"<%=request.getContextPath()%>/login",
			type:"post",
			dataType:"text",
			data:{"username":username,"password":password},
			success:function(text){
				if(text==0){
					alert("用户名不存在或者密码错误！");
				}else{
					location.href="<%=request.getContextPath()%>/zhuye";
				}
			}
		})
	}

   	//清空session
  $(document).ready(function(){
	  var uid="<%=request.getSession().getAttribute("uid")%>";
	  //alert(uid>0);
	  if(uid>0){
		  
	  	location.href="<%=request.getContextPath()%>/logOut";
		}
	});
</script>
</head>
<body>
	<h1>
		练习一下登录页面<sup>2017</sup>
	</h1>
	<div class="login" style="margin-top: 50px;">
		<div class="header"></div>
		<div class="web_qr_login" id="web_qr_login"
			style="display: block; height: 300px;">
			<!--登录-->
			<div class="web_login" id="web_login">
				<div class="login-box">
					<div class="login_form">
						<div class="uinArea" id="uinArea">
							<label class="input-tips" for="u">请输入帐号：</label>
							<div class="inputOuter" id="uArea">
								<input type="text" id="username" name="username"
									class="inputstyle" onblur="blrnoname(this)"
									onfocus="focnoname(this)" /> <span
									style="display: none; color: red;" id="susername">用户名不能为空!</span>
							</div>
						</div>
						<div class="pwdArea" id="pwdArea">
							<label class="input-tips" for="p">请输入密码：</label>
							<div class="inputOuter" id="pArea">
								<input type="password" id="password" name="password"
									class="inputstyle" onblur="blrnopass(this)"
									onfocus="focnopass(this)" /> <span
									style="display: none; color: red;" id="spassword">密码不能为空!</span>
							</div>
						</div>
						<div style="padding-left: 40px; margin-top: 70px;">
							<input type="button" onclick="login()" value="登 录"
								style="width: 100px;" class="button_blue" /> <input
								type="button" onclick="regist()" value="注册"
								style="width: 100px; margin-left: 30px" class="button_blue" />
						</div>
					</div>
				</div>
			</div>
			<!--登录end-->
		</div>
		<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>
		</div>
</body>
</html>