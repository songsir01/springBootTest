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
<body style="background-color: #E1FFFF" onload="isLogin()">
	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13"
					src="<%=request.getContextPath()%>/public/style/images/title_arrow.gif" />
				系统菜单-->角色管理
			</div>
		</div>
	</div>
	<div id="QueryArea">
		<button style="float: right;margin-left: 30px" onclick="toAdd()">添加</button>
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
					<td align="center" style="font-size: medium;width: 15%">角色ID</td>
					<td align="center" style="font-size: medium;width: 15%">角色名</td>
					<td align="center" style="font-size: medium;">拥有权限</td>
				</tr>
			</thead>
			<!--显示数据列表 -->
			<tbody id="TableData">
				<c:forEach items="${roleList }" var="list">
					<tr class="TableDetail1" style="background: #DCDCDC"
						onclick="xuan(${list.rid })">
						<td align="center"><input type="checkbox" id="${list.rid }"
							onclick="xuan(${list.rid })" name="ck" value="${list.rid }" /></td>
						<td align="center" style="font-size: small;">${list.rid }</td>
						<td align="center" style="font-size: small;">${list.rname }</td>
						<td align="center" style="font-size: small;">
						<c:forEach items="${list.permissiones }" var="plist">
							<input type="checkbox" value="${plist.pid }" checked="checked" disabled="disabled"/>${plist.pname } &nbsp;
						</c:forEach>
						
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
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
				<div class="modal-body ">
					<form class="form-horizontal" role="form" id="form">
						<div style="margin-left: 150px">
							<span style="font-size: medium;">角色名：</span>
							<input type="text" name="up_rname" id="up_rname" style="width: 180px;height: 30px">
							<input type="hidden" name="up_rid" id="up_rid" style="width: 180px;height: 30px">
						</div>
						<br>
						<div style="margin-left: 150px">
							<span style="font-size: medium;">权&nbsp;限：</span>
							<input type="checkbox" name="up_pname" value="2" ><span style="font-size: medium;">用户管理</span>&nbsp;
							<input type="checkbox" name="up_pname" value="1" id="rmanager"  ><span style="font-size: medium; " >角色管理</span><br><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="up_pname" value="3"><span style="font-size: medium;">查看列表</span>&nbsp;
							<input type="checkbox" name="up_pname"  value="5"><span style="font-size: medium;">操作1</span><br><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="up_pname" value="6" ><span style="font-size: medium;">操作2</span>&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="up_pname" value="7" ><span style="font-size: medium;" >操作3</span><br><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="up_pname"  value="4"><span style="font-size: medium;">个人中心</span>
							&nbsp;<input type="checkbox" name="up_pname"  value="8"><span style="font-size: medium;">测试一下</span>
						</div>
						<br>
						<div class="form-group text-center">
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
	
	
	
	<!-- 添加弹窗 -->
	<div id="addm" class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span class='glyphicon glyphicon-plus'>&nbsp;</span>添加
					<button type='button' class='close' data-dismiss='modal'>
						<span style='font-size: 20px;' class='glyphicon glyphicon-remove'></span>
					</button>
				</div>
				<div class="modal-body ">
					<form class="form-horizontal" role="form" id="form1">
						<div style="margin-left: 150px">
							<span style="font-size: medium;">角色名：</span>
							<input type="text" name="add_rname" id="add_rname" style="width: 180px;height: 30px" required="required" onkeyup="rnameKeyUp()">
							<span style="color: red;display: none" id="tishi"></span>
						</div>
						<br>
						<div style="margin-left: 150px">
							<span style="font-size: medium;">权&nbsp;限：</span>
							<input type="checkbox" name="add_pname" value="2" ><span style="font-size: medium;">用户管理</span>&nbsp;
							<input type="checkbox" name="add_pname" value="1"   ><span style="font-size: medium; " >角色管理</span><br><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="add_pname" value="3"><span style="font-size: medium;">查看列表</span>&nbsp;
							<input type="checkbox" name="add_pname"  value="5"><span style="font-size: medium;">操作1</span><br><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="add_pname" value="6" ><span style="font-size: medium;">操作2</span>&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="add_pname" value="7" ><span style="font-size: medium;" >操作3</span><br><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="add_pname"  value="4" checked="checked" disabled="disabled"><span style="font-size: medium;">个人中心</span>
							&nbsp;<input type="checkbox" name="add_pname"  value="8" ><span style="font-size: medium;">测试一下</span>
						</div>
						<br>
						<div class="form-group text-center">
							<div class="col-sm-offset-1 col-sm-11">
								<input type="button" class="btn btn-primary" value="添加" onclick="addRole()" id="addStart">
								<input type="button" class="btn btn-default" data-dismiss="modal" value="取消" onclick="up_cancle()">
							</div>
						</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	
</body>

<script type="text/javascript">
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
	//更新回显数据
	function toupd(){
		document.getElementById("rmanager").disabled=false;
		var cks=document.getElementsByName("ck");
		var len=0;
		for(var i in cks){
			if(cks[i].checked){
				len+=1;
			}
		}
		if(len==0){
			alert("请选择需要修改的角色...");
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
					var rid=ck[i].value;
				}
			}
			if(rid==1){
				 document.getElementById("rmanager").disabled=true;
			}
			var up_pnames=document.getElementsByName("up_pname");
			$.ajax({
				url:"<%=request.getContextPath()%>/roleToupd",
				type:"post",
				dataType:"json",
				data:{"rid":rid},
				success:function(json){
					$("#form")[0].reset();
					var jsonPs=json[0].permissiones;
					for(var i in up_pnames){
						for(var j in jsonPs){
							if(up_pnames[i].value==jsonPs[j].pid){
								up_pnames[i].checked=true;
							}
						}
					}
					$("#up_rname").val(json[0].rname);
					$("#up_rid").val(json[0].rid);
					$("#update").modal("toggle");
				}
			})
		}
	}
	//修改执行 
	function upStart(){
		var cks=document.getElementsByName("ck");
		var rid=$("#up_rid").val();
		var rname=$("#up_rname").val();
		var pnames=document.getElementsByName("up_pname");
		var pids='';
		for (var i in pnames){
			if(pnames[i].checked){
				pids+=pnames[i].value+",";
			}
		}
		$.ajax({
			url:"<%=request.getContextPath()%>/roleUpStart",
			type:"post",
			dataType:"text",
			data:{"pids":pids,"rid":rid,"rname":rname},
			success:function(text){
				if(text>0){
					alert("修改成功！重新登录后权限生效...");
					for(var i in cks){
						if(cks[i].checked){
							cks[i].checked=false;
						}
					}
					location.reload();
				}else{
					alert("修改出错,请刷新后重试...")
				}
			}
		})
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
			alert("Warming!超级管理员角色不可删除...");
		}else{
			if(confirm("确认删除吗")){
				$.ajax({
					url:"<%=request.getContextPath()%>/roleDel",
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
		function toAdd(){
			document.getElementById("addStart").disabled=false;
			var rname=$("#add_rname").val();
			if(rname=="" || rname==null){
				document.getElementById("addStart").disabled=true;
			}
			$("#form1")[0].reset();
			$("#addm").modal("toggle");
		}
		function rnameKeyUp(){
			document.getElementById("addStart").disabled=false;
			var rname=$("#add_rname").val();
			var tishi=document.getElementById("tishi");
			$.ajax({
				url:"<%=request.getContextPath()%>/roleYanZ",
				type:"post",
				dataType:"text",
				data:{"rname":rname},
				success:function(text){
					if(text==1){
						$("#tishi").text("用户名已存在...");
						tishi.style.display="inline";
						document.getElementById("addStart").disabled=true;
					}else if(text==0){
						$("#tishi").text("");
						tishi.style.display="none";
						document.getElementById("addStart").disabled=false;
					}else{
						$("#tishi").text("用户名不能为空...");
						tishi.style.display="inline";
						document.getElementById("addStart").disabled=true;
					}
				}
			})
		}
		
		function addRole(){
			var rname=$("#add_rname").val();
			var pids=document.getElementsByName("add_pname");
			var pidss='';
			for(var i in pids){
				if(pids[i].checked){
					pidss+=pids[i].value+",";
				}
			}
			$.ajax({
				url:"<%=request.getContextPath()%>/addRole",
				type:"post",
				dataType:"text",
				data:{"rname":rname,"pidss":pidss},
				success:function(text){
					if(text>0){
						alert("添加成功！");
						location.reload();
					}else{
						alert("添加失败，请刷新页面重试...")
					}
				}
			})
		}
		
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
