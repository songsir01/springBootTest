<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>注册界面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/style/js/jquery-1.4.2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/style/images/login.js"></script>
<link href="<%=request.getContextPath()%>/style/css/login2.css"
	rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">
	function blrnoname(t){
		   if($(t).val()==""||$(t).val()==null){
		     $("#susername").css("display","block");
		   }
		   var username=$(t).val();
		   var strRegex = "^[0-9a-zA-Z]{3,16}$";
		   var re=new RegExp(strRegex);
		   if(!re.test(username)){
			   $("#username").focus();
			   $("#guize").css("display","block");
			  
		   }
		   
		   else{
			   $("#guize").css("display","none");
		   $.ajax({
			   url:"<%=request.getContextPath()%>/userunique?username="+username,
			   dateType:"text",
			   type:"get",
			   success:function(text){
				   if(text==1){
					   $("#exsistuser").css("display","block");
					   $("#username").focus();
				   }
				   else{
					   $("#exsistuser").css("display","none");  
				   }
			   }
		   })
		   }
		}
	function focnoname(t){
		   if($(t).val()==""||$(t).val()==null){
		     $("#susername").css("display","none");
		   }
		}
	function blrnopass(t){
		   if($(t).val()==""||$(t).val()==null){
			 //$("#password").focus();
		     $("#spassword").css("display","block");
		   }
		}
	function focnopass(t){
		   if($(t).val()==""||$(t).val()==null){
		     $("#spassword").css("display","none");
		   }
		}
	function blrnorepass(t){
		   if($("#password").val()!=$("#repassword").val()){
			   //alert("两次输入不一致！请重新输入...");
			   //$("#reepassword").text("两次密码输入不一致！重新输入...");
			  // $("#repassword").val("");
			   $("#reepassword").css("display","block");
			  // $("#repassword").focus();
		   }else{
			   $("#reepassword").css("display","none");
		   }
		}
	function focnorepass(t){
		if(!($("#password").val()=="" || $("#password").val()==null)){
			$("#spassword").css("display","none");
		}
		
		   if($(t).val()==""||$(t).val()==null){
		     $("#reepassword").css("display","none");
		    
		   }
		}
	function focdesc(){
		if($("#password").val()==$("#repassword").val()){
			$("#reepassword").css("display","none");
		}else{
			$("#reepassword").css("display","block");
		}
		
	}
	
	function passkeydown(){
		$("#repassword").val("");
	}
	
	function repasskeyup(){
		if($("#password").val()!=$("#repassword").val()){
			$("#reepassword").css("display","block");
		}
		else{
			
			$("#reepassword").css("display","none");
		}
		
	}
	
	function nextRegist(){
		var uname=$("#username").val();
		var username=$.trim(uname);
		var password=$("#password").val();
		var repassword=$("#repassword").val();
		var description=$("#description").val();
		var sexs=document.getElementsByName("sex");
		for(var i in sexs){
			if(sexs[i].checked){
				var sex=sexs[i].value;
			}
		}
		//alert(username+" "+password+" "+repassword+" "+description+" "+sex);
		if(password!=repassword ){
			alert("两次密码输入不一致！请重新输入...");
		}else if(password==null || password=="" || repassword==null ||repassword==""){
			alert("密码不能为空！请重新输入...");
		}else if(username==null || username==""){
			alert("用户名不能为空！")
		}
		else{
			$.ajax({
				url:"<%=request.getContextPath()%>/regNext",
				type:"post",
				dataType:"text",
				data:{"username":username,"password":password,"description":description,"sex":sex},
				success:function(text){
					if(text==1){
						alert("注册成功！");
						location.href="<%=request.getContextPath()%>/login.jsp";
					}
					else if(text==3){
						alert("用户名已存在，请重新输入...")
					}else if(text==4){
						alert("用户名不合规则，请重新注册...")
					}
					else{
						alert("Sorry!服务器出错，注册失败!请刷新页面重试...")
					}
				}
			})
		}
	}
</script>
<body>
	<h1>
		练习一下注册页面<sup>2017</sup>
	</h1>
	<div class="login" style="margin-top: 50px;">
		<div class="web_qr_login" id="web_qr_login"
			style="display: block; height: 450px; width: 450px">
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
								<span style="display: none; color: red;" id="exsistuser">用户名已存在...</span>
								<span style="display: none; color: red;" id="guize">长度3-16位英文或数字</span>
							</div>
						</div>
						<div class="pwdArea" id="pwdArea">
							<label class="input-tips" for="p">请输入密码：</label>
							<div class="inputOuter" id="pArea">
								<input type="password" id="password" name="password"
									class="inputstyle" onblur="blrnopass(this)"
									onfocus="focnopass(this)"  onkeydown="passkeydown()"/> <span
									style="display: none; color: red;" id="spassword">密码不能为空!</span>
							</div>
						</div>
						<div class="uinArea" id="repwdArea">
							<label class="input-tips" for="p">请重新输入密码：</label>
							<div class="inputOuter" id="repArea">
								<input type="password" id="repassword" name="repassword"
									class="inputstyle" onblur="blrnorepass(this)"
									onfocus="focnorepass(this)" onkeyup="repasskeyup()" /> <span
									style="display: none; color: red;" id="reepassword">两次密码输入不一致..</span>
							</div>
						</div>
						<div class="uinArea" id="desArea">
							<label class="input-tips" for="p">请输入简介：</label>
							<div class="inputOuter" id="d4">
								<input type="text" id="description" name="description"
									class="inputstyle11" onfocus="focdesc()"/>
							</div>
						</div>
						<div class="uinArea" id="sexArea">
							<label class="input-tips" for="p">请选择性别：</label>
							<div
								style="width: 130px; height: 42px; margin-top: 20px; float: left;"
								id="sArea">
								<input type="radio" name="sex" value="男" checked="checked" />男 <input
									type="radio" name="sex" value="女" />女
							</div>
						</div>
						<div style="padding-left: 40px; margin-top: 70px;">
							<input type="button"  value="立即注册" onclick="nextRegist()"
								style="width: 100px;" class="button_blue" />
								<input
								type="button" onclick="window.history.go(-1)" value="返回登录"
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