<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head> 
  <title>安全设置界面</title> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <script type="text/javascript" src="<%=request.getContextPath() %>/style/js/jquery-1.4.2.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/style/images/login.js"></script> 
  <link href="<%=request.getContextPath() %>/style/css/login2.css" rel="stylesheet" type="text/css" /> 
</head>
<body>
<h1>练习一下安全设置页面<sup>2017</sup></h1>
  <div class="login" style="margin-top:50px;"> 
   <div class="web_qr_login" id="web_qr_login" style="display: block; height: 450px;"> 
    <!--登录--> 
    <div class="web_login" id="web_login"> 
     <div class="login-box"> 
      <div class="login_form"> 
       <div class="uinArea" id="uinArea"> 
        <label class="input-tips" for="u">请设置安全问题：</label> 
        <div class="inputOuter" id="uArea"> 
         <select style="width:200px;
						height:38px;
						padding-left:5px;
						line-height:30px;line-height:38px;
						border:1px solid #D7D7D7;
						background:#fff;
						color:#333;border-radius:2px;
						font-family:Verdana, Tahoma, Arial;
						font-size:16px;
						ime-mode:disabled;">
         	<c:forEach items="${questionList }" var="questionList">
         		<option value="${questionList.qid }">${questionList.qname }</option>
         	</c:forEach>
         </select>
        </div> 
       </div> 
       
       <div class="pwdArea" id="pwdArea"> 
        <label class="input-tips" for="p">请输入答案：</label> 
        <div class="inputOuter" id="pArea"> 
         <input type="text" id="password" name="password" class="inputstyle" onblur="kongpwd(this)" />
         <span style="display: none;color: red;" id="spassword">问题不能为空!</span> 
        </div> 
       </div> 
       
       <div class="uinArea" id="uinArea"> 
        <label class="input-tips" for="u">请设置安全问题：</label> 
        <div class="inputOuter" id="uArea"> 
         <select style="width:200px;
						height:38px;
						padding-left:5px;
						line-height:30px;line-height:38px;
						border:1px solid #D7D7D7;
						background:#fff;
						color:#333;border-radius:2px;
						font-family:Verdana, Tahoma, Arial;
						font-size:16px;
						ime-mode:disabled;">
         	<c:forEach items="${questionList }" var="questionList">
         		<option value="${questionList.qid }">${questionList.qname }</option>
         	</c:forEach>
         </select>
        </div> 
       </div> 
       <div class="pwdArea" id="pwdArea"> 
        <label class="input-tips" for="p">请输入答案：</label> 
        <div class="inputOuter" id="pArea"> 
         <input type="password" id="password" name="password" class="inputstyle" onblur="kongpwd(this)" />
         <span style="display: none;color: red;" id="spassword">问题不能为空!</span> 
        </div> 
       </div> 
       
       
       <div style="padding-left:80px;margin-top:70px;">
        <input type="button" onclick="kong()" value="立即注册" style="width:100px;" class="button_blue" />
       </div> 
      </div> 
     </div> 
    </div>
    <!--登录end--> 
   </div>
  <div class="jianyi">
   *推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站
  </div> 
</body>
</html>