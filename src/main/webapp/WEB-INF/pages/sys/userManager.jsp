<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="/plugins/nifty-v2.5/demo/css/bootstrap.min.css"
	rel="stylesheet">
<!-- <link -->
<!-- 	href="/plugins/nifty-v2.5/demo/plugins/bootstrap-table/bootstrap-table.css" -->
<!-- 	rel="stylesheet"> -->
<!-- <link -->
<!-- 	href="/plugins/nifty-v2.5/demo/plugins/gmaps/examples/examples.css" -->
<!-- 	rel="stylesheet"> -->
<script src="/plugins/nifty-v2.5/demo/js/jquery-2.2.4.min.js"></script>
<script src="/plugins/nifty-v2.5/demo/js/bootstrap.min.js"></script>
<!-- <script src="/plugins/nifty-v2.5/demo/js/bootstrap.js"></script> -->
<!-- <script -->
<!-- 	src="/plugins/nifty-v2.5/demo/plugins/bootstrap-table/bootstrap-table.js"></script> -->
<!-- <script -->
<!-- 	src="/plugins/nifty-v2.5/demo/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script> -->
<!-- <link -->
<!-- 	href="/plugins/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" -->
<!-- 	rel="stylesheet" /> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="/plugins/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css"> -->
<!-- <script -->
<!-- 	src="/plugins/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script> -->
<!-- <script -->
<!-- 	src="/plugins/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script> -->
<!-- <script src="/js/tool.js"></script> -->

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/public/style/js/page_common.js"></script>
<link
	href="<%=request.getContextPath()%>/public/style/css/common_style_blue.css"
	rel="stylesheet" type="text/css">
<link rel="<%=request.getContextPath()%>/public/stylesheet"
	type="text/css" href="style/css/index_1.css" />
</head>
<body onload="onLoad()" style="background-color: #E1FFFF">
	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13"
					src="<%=request.getContextPath()%>/public/style/images/title_arrow.gif" />
				系统菜单-->用户管理
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>
	<div id="QueryArea">
		<input type="text" name="keyWord" id="keyWord" onblur="onb()"
			onfocus="onf()" value="${keyWord }">
		<button onclick="search()">搜索</button>
<!-- 		<button style="float: right;margin-left: 30px" onclick="toAdd()">添加</button> -->
		<button style="float: right;margin-left: 30px" onclick="del()">删除</button>
		<button style="float: right;" onclick="toupd()">修改</button>
	</div>
	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<table class="MainArea_Content" align="center" cellspacing="0"
			cellpadding="0">
			<!-- 表头-->
			<thead>
				<tr  id="TableTitle" style="height: 30px">
					<td align="center"><input type="checkbox" onclick="qxuan()" id="qxuan" /></td>
					<td align="center" style="font-size: medium;">用户ID</td>
					<td align="center" style="font-size: medium;">用户名</td>
					<td align="center" style="font-size: medium;">性别</td>
					<td align="center" style="font-size: medium;">简介</td>
					<td align="center" style="font-size: medium;">角色</td>
				</tr>
			</thead>
			<!--显示数据列表 -->
			<tbody id="TableData">
				<c:forEach items="${userList }" var="list">
					<tr class="TableDetail1" style="background: #DCDCDC"
						onclick="xuan(${list.uid })">
						<td align="center"><input type="checkbox" id="${list.uid }"
							onclick="xuan(${list.uid })" name="ck" value="${list.uid }" /></td>
						<td align="center" style="font-size: small;">${list.uid }</td>
						<td align="center" style="font-size: small;">${list.username }</td>
						<td align="center" style="font-size: small;">${list.sex }</td>
						<td align="center" style="font-size: small;"><textarea rows="1" cols="20" readonly="readonly" style="background-color:#DCDCDC;text-align: center;">${list.description }</textarea></td>
						<td align="center" style="font-size: small;">${list.role.rname }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 其他功能超链接,分页 -->
		<div id="TableTail" align="center">
			<button onclick="fy(1)">首页</button>
			<button onclick="fy(${prevPage })">上一页</button>
			<button onclick="fy(${nextPage })">下一页</button>
			<button onclick="fy(${lastPage })">尾页</button>
			&nbsp; <input type="hidden" value="${lastPage }" id="allPage" />
			每页显示 <select onchange="changePage()">
				<option value="10" name="sel">10</option>
				<option value="50" name="sel">50</option>
				<option value="100" name="sel">100</option>
			</select> <input type="hidden" id="pSize" value="${pageSize }" /> 条&nbsp; 当前第<input
				name="thisPage" id="thisPage"
				style="width: 30px; text-align: center;" value="${page }" />页&nbsp;
			共<input name="allPage" id="allPage"
				style="width: 30px; text-align: center;" value="${lastPage }" />页
			&nbsp; 跳到第<input name="dingPage" id="dingPage"
				style="width: 30px; text-align: center;" value="${page }" />页
			<button style="width: 40px" onclick="goPage()" id="go">go</button>
		</div>
	
	<!-- 修改弹窗 -->
	<div id="update" class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span class='glyphicon glyphicon-plus'>&nbsp;</span>修改
					<button type='button' class='close' data-dismiss='modal'>
						<span style='font-size: 20px;' class='glyphicon glyphicon-remove'></span>
					</button>
				</div>
				<div class="modal-body text-center">
					<form class="form-horizontal" role="form" id="form">
						<input type="hidden"   name="up_uid" id="up_uid" >
						<div class="form-group">
							<label for="lastname" class="col-sm-5 control-label">用户名</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="up_name"
									id="up_name" placeholder="" style="width: 55%" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="lastname" class="col-sm-5 control-label">性别</label>
							<div class="col-sm-7">
<!-- 								<input type="" class="form-control" name="traffic_address" -->
<!-- 									id="traffic_address" placeholder="请输入收费站的地点" style="width: 55%"> -->
									<select class="form-control" data-style="btn-info" style="width: 50%;text-align: center;" id="sex_sel">
											<option value="男" style="text-align: center;" name="up_sex">男</option>
											<option value="女" style="text-align: center;" name="up_sex">女</option>
									</select>
							</div>
						</div>
						<div class="form-group">
							<label for="lastname" class="col-sm-5 control-label">简介</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="up_description"
									id="up_description" placeholder=""
									style="width: 55%">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-5 control-label">重置新密码</label>
							<div class="col-sm-7">

								<input type="text" class="form-control" name="up_password"
									id="up_password" placeholder="默认不修改" style="width: 55%;ime-mode:disabled">
							</div>
						</div> 
						<div class="form-group">
							<label for="lastname" class="col-sm-5 control-label">角色</label>
							<div class="col-sm-7">
<!-- 								<input type="" class="form-control" name="traffic_address" -->
<!-- 									id="traffic_address" placeholder="请输入收费站的地点" style="width: 55%"> -->
									<select class="form-control" data-style="btn-info" style="width: 50%;text-align: center;" id="sel">
										<c:forEach items="${roleList }" var="roleList">
											<option value="${roleList.rid }" style="text-align: center;" name="role">${roleList.rname }</option>
										</c:forEach>
									</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-1 col-sm-11">
								<input type="button" class="btn btn-primary" value="修改" onclick="upStart()">
								<input type="button" class="btn btn-default" data-dismiss="modal" value="取消" onclick="up_cancle()">
							</div>
						</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	
	
	
	<!-- 新增弹窗 -->
	<div id="add" class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span class='glyphicon glyphicon-plus'>&nbsp;</span>添加
					<button type='button' class='close' data-dismiss='modal'>
						<span style='font-size: 20px;' class='glyphicon glyphicon-remove'></span>
					</button>
				</div>
				<div class="modal-body text-center">
					<form class="form-horizontal" role="form" id="form2">
						<div class="form-group">
							<label for="lastname" class="col-sm-5 control-label">用户名</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="up_name"
									id="add_name" placeholder="请输入用户名" style="width: 55%" >
							</div>
						</div>
						<div class="form-group">
							<label for="lastname" class="col-sm-5 control-label">性别</label>
							<div class="col-sm-7">
									<select class="form-control" data-style="btn-info" style="width: 50%;text-align: center;" id="sex_sel">
											<option value="男" style="text-align: center;" name="add_sex">男</option>
											<option value="女" style="text-align: center;" name="add_sex">女</option>
									</select>
							</div>
						</div>
						<div class="form-group">
							<label for="lastname" class="col-sm-5 control-label">简介</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="add_description"
									id="add_description" placeholder="请输入简介"
									style="width: 55%">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-5 control-label">设置密码</label>
							<div class="col-sm-7">

								<input type="text" class="form-control" name="add_password"
									id="add_password" placeholder="请输入密码" style="width: 55%;ime-mode:disabled">
							</div>
						</div> 
						<div class="form-group">
							<label for="lastname" class="col-sm-5 control-label">角色</label>
							<div class="col-sm-7">
									<select class="form-control" data-style="btn-info" style="width: 50%;text-align: center;" id="add_sel">
										<c:forEach items="${roleList }" var="roleList">
											<option value="${roleList.rid }" style="text-align: center;" name="add_role">${roleList.rname }</option>
										</c:forEach>
									</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-1 col-sm-11">
								<input type="button" class="btn btn-primary" value="添加" onclick="addStart()">
								<input type="button" class="btn btn-default" data-dismiss="modal" value="取消"">
							</div>
						</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	
</body>

<script type="text/javascript">
	//每页数量改变事件
	function changePage(){
		var sel=document.getElementsByName("sel");
		for (var i in sel){
			if(sel[i].selected){
				var pageSize=sel[i].value;
			}
		}
		var keyWord=$("#keyWord").val();
		location.href="<%=request.getContextPath()%>/userManager?pageSize="+pageSize+"&keyWord="+keyWord;
	}
	//加载 每页数量
	function onLoad(){
		isLogin();
		var sel=document.getElementsByName("sel");
		var pSize=$("#pSize").val();
		for(var i in sel){
			if(sel[i].value==pSize){
				sel[i].selected=true;
			}
		}
	}
	//分页
	function fy(page){
		var keyWord=$("#keyWord").val();
		var pageSize=$("#pSize").val();
		location.href="<%=request.getContextPath()%>/userManager?page="+page+"&keyWord="+keyWord+"&pageSize="+pageSize;
	}
	//模糊查询
	function search(){
		var keyWord=$("#keyWord").val();
		var pageSize=$("#pSize").val();
		location.href="<%=request.getContextPath()%>/userManager?keyWord="+keyWord+"&pageSize="+pageSize;
	}
	//跳转指定页面
	function goPage(){
		var keyWord=$("#keyWord").val();
		var pageSize=$("#pSize").val();
		var goPage=$("#dingPage").val();
		var allPage=$("#allPage").val();
		if(goPage>0 && goPage<=allPage){
			location.href="<%=request.getContextPath()%>/userManager?page="+goPage+"&keyWord="+keyWord+"&pageSize="+pageSize;
		}
	}
	//选择
	function xuan(id){
		var a=document.getElementById(id);
		if(a.checked){
			a.checked=false;
		}else{
			a.checked=true;
		}
	}
	//全选
/* 	function qxuan() {  
		var zong=document.getElementById("qxuan");
		var code_Values = document.getElementsByTagName("input");  
		for (i = 0; i < code_Values.length; i++) {  
		    if (code_Values[i].type == "checkbox") {  
				if(zong.checked){
		  			code_Values[i].checked = true;  
		  		}else{
		  			code_Values[i].checked = false;  
		  		}
		    }  
		  }  
		}  */
		function qxuan() {  
			var zong=document.getElementById("qxuan");
			var cks = document.getElementsByName("ck"); 
			for(var i in cks){
				if(zong.checked){
					cks[i].checked=true;
				}else{
					cks[i].checked=false;
				}
			}
		} 
	//删除
	function del(){
		
		var qxuan=document.getElementById("qxuan");
		var ck=document.getElementsByName("ck");
		var admId=document.getElementById("1");
		var ids='';
		for(var i=0;i<ck.length;i++){
			if(ck[i].checked){
				ids+=ck[i].value+",";
			}
		}
		if(ids==""){
			alert("请选择需要删除的用户...");
		}else if(admId!=null && admId.checked){
			alert("Warming!超级管理员账户不可删除...");
		}
		else{
			if(confirm("确认删除吗")){
			$.ajax({
				url:"<%=request.getContextPath()%>/userDel",
				type:"post",
				dataType:"text",
				data:{"ids":ids},
				success:function(text){
					if(text>0){
						alert("删除成功！");
						location.reload();
						for(var i=0;i<ck.length;i++){
							if(ck[i].checked){
								ck[i].checked=false;
							}
						}
						if(qxuan.checked){
							qxuan.checked=false;
						}
					}
					else{
						alert("删除失败！")
					}
				}
			})
		}
	}
	}
	//更新回显数据
	function toupd(){
		$("#sel").removeAttr("disabled");
		var len=$(':checkbox:checked').length;
		if(len==0){
			alert("请选择需要修改的用户名...");
		}else if(len>1){
			alert("请选择一项进行修改...")
			var ck=document.getElementsByName("ck");
			var qxuan=document.getElementById("qxuan");
			if(qxuan.checked){
				qxuan.checked=false;
			}
			for(var i in ck){
				if(ck[i].checked){
					ck[i].checked=false;
				}
			}
		}else{
			var ck=document.getElementsByName("ck");
			for(var i in ck){
				if(ck[i].checked){
					var uid=ck[i].value;
				}
			}
			var role=document.getElementsByName("role");
			var sex=document.getElementsByName("up_sex");
			$.ajax({
				type:"get",
				dataType:"json",
				url:"<%=request.getContextPath()%>/toupd?uid="+uid,
				success:function(json){
					$("#form")[0].reset();
					$("#update").modal("toggle");
					for(var i in role){
						if(role[i].value==json.role.rid){
							role[i].selected=true;
						}
						if(json.uid==1){
							$("#sel").attr("disabled","disabled");
						}
					}
					for(var j in sex){
						if(sex[j].value==json.sex){
							sex[j].selected=true;
						}
					}
					$("#up_name").val(json.username);
					$("#up_uid").val(json.uid);
					$("#up_description").val(json.description);
				}
			})
			
		}
	}
	//更新
	function upStart(){
		var uid=$("#up_uid").val();
		var username=$("#up_name").val();
		var password=$("#up_password").val();
		var description=$("#up_description").val();
		var sexn=document.getElementsByName("up_sex");
		var rolen=document.getElementsByName("role");
		for(var i in sexn){
			if(sexn[i].selected){
				var sex=sexn[i].value;
			}
		}
		for(var j in rolen){
			if(rolen[j].selected){
				var rid=rolen[j].value;
			}
		}
		$.ajax({
			url:"<%=request.getContextPath()%>/upStart",
			type:"post",
			dataType:"text",
			data:{"uid":uid,"username":username,"password":password,"sex":sex,"description":description,"rid":rid},
			success:function(text){
				if(text>0){
					alert("修改成功！");
					location.reload();
				}else{
					alert("修改出错了，请刷新页面重试...")
				}
			}
		})
	}
	function up_cancle(){
		var ck=document.getElementsByName("ck");
		for(var i in ck){
			if(ck[i].checked){
				ck[i].checked=false;
			}
		}
	}
	/* function toAdd(){
		$("#form2")[0].reset();
		$("#add").modal("toggle");
		
	} */
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
	}
</script>
</html>
