<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看列表</title>
</head>

<link href="plugins/nifty-v2.5/demo/css/bootstrap.min.css" rel="stylesheet">

<link href="plugins/nifty-v2.5/demo/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">

<link href="plugins/nifty-v2.5/demo/plugins/gmaps/examples/examples.css" rel="stylesheet">

<link href="plugins/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

<link href="plugins/nifty-v2.5/demo/css/nifty.min.css" rel="stylesheet">

<link href="plugins/nifty-v2.5/demo/plugins/themify-icons/themify-icons.min.css" rel="stylesheet">

<script src="plugins/nifty-v2.5/demo/js/jquery-2.2.4.min.js"></script>

<script src="js/tool.js"></script>

<script src="plugins/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<script src="plugins/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>

<script src="plugins/nifty-v2.5/demo/js/bootstrap.js"></script>

<script src="plugins/nifty-v2.5/demo/plugins/bootstrap-table/bootstrap-table.js"></script>

<script src="plugins/nifty-v2.5/demo/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
	
<link href="plugins/nifty-v2.5/demo/css/demo/nifty-demo-icons.min.css" rel="stylesheet">

<script type="text/javascript">
//弹出框初始化代码
$(function () { 
	$("[data-toggle='popover']").popover(); 
	
		goodsPage();
	
	});

//数据分页
function goodsPage(){
	
	 //初始化表格,动态从服务器加载数据
	  $("#goodsTable").bootstrapTable('destroy').bootstrapTable({  
      	striped: true,   //是否显示行间隔色
        method:'post',//服务器数据的请求方式 'get' or 'post'
        contentType: "application/x-www-form-urlencoded",
		url:'<%=request.getContextPath()%>/catList',//服务器数据的加载地址
		dataType: "json",//服务器返回的数据类型
		toggle:'table',
  	    cache: true,   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
  	  	pagination: true,//是否显示分页
  	    paginationLoop:false,//启用分页条无限循环的功能
  	    sortable: true,   //是否启用排序
  	    sortOrder: "desc",   //排序方式
  	    sidePagination: "server", //设置在哪里进行分页，可选值为 'client' 或者 'server'。
  	                           //设置 'server'时，必须设置 服务器数据地址（url）或者重写ajax方法 
  	    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder 
  	    //设置为limit可以获取limit, offset, search, sort, order 
  	    queryParamsType: "limit", 
  	    queryParams:queryParams, //设置查询参数 
  	    showColumns: false, //不显示下拉框（选择显示的列）  
  	    // pageNumber:1,   //初始化加载第一页，默认第一页
        pageSize: 10,   //每页的记录行数（*）
  	    pageList: [ 10, 50, 100], //可供选择的每页的行数
  	    strictSearch: false,//启用 全匹配搜索
  	    minimumCountColumns:1,// 设置最少显示列个数
  	    clickToSelect: true,  //是否启用点击选中行
  	 
  	   columns:[// 列设置
  	        {
  	        title:'全选',
	            field:'select',
	            //复选框
	            checkbox:true,
	            width:25,
	            align:'center',
	            valign:'middle'
  	        },
  	        {
  	        field:'gid',
  	        title:"商品标号",
  	        align: 'center',
              valign: 'middle',
  	        sortable:'true'//开启排序功能
  	        },{
  	        field:'gname',
  	        title:"商品名",
  	        align: 'center',
              valign: 'middle',
  	        sortable:'true'
  	        },{
  	        field:'gprice',
  	        title:"价格",
  	        align: 'center',
            valign: 'middle',
  	        sortable:'true',
  	        },{
  	        field:'jijie',
  	        title:"季节",
  	        align: 'center',
            valign: 'middle',
  	        sortable:'true',
  	  	    },{
  	        field:'size',
  	        title:"型号",
  	        align: 'center',
            valign: 'middle',
  	        sortable:'true',
  	  	  	},{
  	        field:'descript',
  	        title:"描述",
  	        align: 'center',
            valign: 'middle',
  	        sortable:'true',
  	  	    },{
  	        field:'brand',
  	        title:"品牌",
  	        align: 'center',
            valign: 'middle',
  	        sortable:'true',
  	  	    }
  	       ]
  }); 
	
}

//请求服务数据时所传参数
function queryParams(params){
    return{
        //每页多少条数据
        limit: params.limit,
        offset:params.offset,
        order:params.order,
        search:$("#tname").val(),
    }
}
//模糊查询
function goodsSearch(){
	goodsPage();
}

/*删除  */
function deleteGoods(){
	var allTableData = $("#goodsTable").bootstrapTable('getAllSelections');//获取表格的所有内容行  
	if(allTableData==''){
		$("#mymodal").modal("toggle");
	}else{
		$("#mymodal1").modal("toggle");
	     }
} 

//删除日志
function sureDel(){
	var rows = $("#goodsTable").bootstrapTable('getAllSelections');//获取表格的所有内容行  
		var ids='';
		for(var i in rows){
			ids+=rows[i].gid+",";
			}
		 $.ajax({
			url:"<%=request.getContextPath()%>/delGoods",
			datType : "json",
			data : {
				gid : ids
			},
			type : "post",
			success : function(json) {
				$("#mymodal2").modal("toggle");
				$("#tiShi").html(json.businessResult);
			}

		});
	}

	function GoodsReset() {
		$("#tname").val("");
	}
	//刷新页面
	function reFresh() {
		location.reload();
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

<body onload="isLogin()">

	<div>
		<div class="panel">
			<div class="panel-heading">
				<div class="panel-control" style="margin-top: 12px">
					<button type="button" class="btn btn-primary ti-plus"
						onclick="addGoods()">新增</button>
					<button type="button" onclick="updateById()"
						class=" btn btn-success ti-pencil">修改</button>
					<button class=" btn btn-danger ti-trash" onclick="deleteGoods()">删除</button>
					<button class=" btn btn-warning ti-reload" onclick="GoodsReset()">重置</button>
					<button type="button" class=" btn btn-success ti-import" onclick="ExportExcel()" >导出</button>
				</div>
				
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13"
					src="<%=request.getContextPath()%>/public/style/images/title_arrow.gif" />
				<span style="font-size: 16px">系统菜单-->查看列表</span>
				
			</div>
			<div class="row ">

					<div class="col-sm-3 input-group" style="float: right;">
						<input id="tname" name="tname" class="form-control"
							placeholder="Search" style="font-size: 10px;" type="text">
						<span class="input-group-btn">
							<button class="btn btn-mint ti-search" type="button"
								id="search_btn" onclick="goodsSearch()">查询</button>
						</span>
					</div>
				</div>
				
			</div>
			<div class="panel-body form-group">
				
				<div class="row">
					<div class="col-sm-12" style="margin-top: 1%;">
						<table id="goodsTable" class="table table-hover"></table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 新增 或者 修改后 处理结果弹窗-->
	<div class="modal" id="jieguo">
 		<div class="modal-dialog">
 			<div class="modal-content">
 				<div class="modal-header">
 					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" style="font-size: xx-large;color: red;">×</span><span class="sr-only">Close</span></button>
 					<!-- 标题 -->
 					<h4 class="modal-title">处理结果</h4>
 				</div>
 				<div class="modal-body text-center">
					<p id="mJieGuo"></p>
				</div>
 				<div class="modal-footer">
 					<button type="button" class="btn btn-default" data-dismiss="modal" onclick="reFresh()">关闭</button>
 					<!-- <button type="button" class="btn btn-primary">保存</button> -->
 				</div>
 			</div><!-- /.modal-content -->
 		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<!-- 删除弹窗 -->
	<div id="mymodal" class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span class='glyphicon glyphicon-envelope'>&nbsp;</span>信息！
					<button type='button' class='close' data-dismiss='modal'>
						<span style='font-size: 20px;' class='glyphicon glyphicon-remove'></span>
					</button>
				</div>
				<div class="modal-body text-center">
					<p>请选择需要操作的数据！</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger " data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	<div id="mymodal1" class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span class='glyphicon glyphicon-envelope'>&nbsp;</span>信息！
					<button type='button' class='close' data-dismiss='modal'>
						<span style='font-size: 20px;' class='glyphicon glyphicon-remove'></span>
					</button>
				</div>
				<div class="modal-body text-center">
					<p>确定删除这些数据吗？</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger " onclick="sureDel()"
						data-dismiss="modal">确定</button>
					<button type="button" class="btn btn-default " data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 删除弹窗 -->


	<!-- 删除成功弹窗 -->
	<div id="mymodal2" class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span class='glyphicon glyphicon-envelope'>&nbsp;</span>信息！
					<button type='button' class='close' data-dismiss='modal'>
						<span style='font-size: 20px;' class='glyphicon glyphicon-remove'></span>
					</button>
				</div>
				<div class="modal-body text-center">
					<p id="tiShi"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger " data-dismiss="modal"
						onclick="reFresh()">确定</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 弹窗提示 -->
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true" style="font-size: xx-large; color: red;">×</span><span
							class="sr-only">Close</span>
					</button>
					<!-- 标题 -->
					<h4 class="modal-title">提示</h4>
				</div>
				<div class="modal-body">
					<!-- 内容 -->
					<p id="message"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">关闭</button>
					<!-- <button type="button" class="btn btn-primary">保存</button> -->
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<!-- 新增窗口 -->
	<div id="xinzeng" class="modal" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<span class='glyphicon glyphicon-plus'>&nbsp;</span>新增！
				<button type='button' class='close' data-dismiss='modal'>
                		<span style='font-size:20px; ' class='glyphicon glyphicon-remove'></span>
                </button>
			</div>
			<div class="modal-body text-center">
				<form  class="form-horizontal" role="form" id="form1" >
				  <div class="form-group">
				    <label for="firstname" class="col-sm-5 control-label">商品名称</label>
				    <div class="col-sm-7">
				      <input type="text"  class="form-control" name="gname" id="gname" placeholder="请输入商品名称" style="width: 55%;text-align: center;" onkeyup="gnameKeyUp()">
				      
				    </div>
				  </div>
				    <span style="display: none; color: red; margin-left: 60px" id="tiGname" ></span>  
				  <div class="form-group">
				    <label for="lastname" class="col-sm-5 control-label">商品价格</label>
				    <div class="col-sm-7">
				      <input  class="form-control" name="gprice" id="gprice" placeholder="请输入商品价格" style="width: 55%;text-align: center;"></input>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="lastname" class="col-sm-5 control-label">季节</label>
				    <div class="col-sm-7">
				     <select  class="selectpicker form-control  bootstrap-select_dropdown-toggle"   id="checkJijie" name="checkJijie" style="width: 55%;text-align: center;">
				       <option value="春天" name="chjijie" style="text-align: center;">春天</option>
	                   <option value="夏天" name="chjijie" style="text-align: center;">夏天</option>
	                   <option value="秋天" name="chjijie" style="text-align: center;">秋天</option>
	                   <option value="冬天" name="chjijie" style="text-align: center;">冬天</option>
				      </select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="lastname" class="col-sm-5 control-label">型号</label>
				    <div class="col-sm-7">
				      <input  class="form-control" name="size" id="size" placeholder="请输入商品型号" style="width: 55%;text-align: center;"></input>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="lastname" class="col-sm-5 control-label">商品描述</label>
				    <div class="col-sm-7">
				      <textarea rows="1" cols="20" class="form-control" name="descript" id="descript" placeholder="请输入商品描述" style="width: 55%;text-align: center;"></textarea>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="lastname" class="col-sm-5 control-label">商品品牌</label>
				    <div class="col-sm-7">
				      <input  class="form-control" name="brand" id="brand" placeholder="请输入商品品牌" style="width: 55%;text-align: center;"></input>
				    </div>
				  </div>
				  
<!-- 				  <div id="demo-dp-component"> -->
<!-- 		        	<label class="col-sm-5 control-label" for="demo-text-input">消息发送时间</label> -->
<!-- 		           	<div class="input-group date" data-date-format="yyyy/mm/dd"> -->
<!-- 		               	<input type="text" class="form-control" id="checkNextTime" name="checkNextTime" placeholder="请选择消息发送时间" style="width: 65%;float: left;"> -->
<!-- 		               	<span class="input-group-addon" style="float: left;height: 33px"><i class="demo-pli-calendar-4"></i></span> -->
<!-- 		           	</div> -->
<!-- 		        </div> -->
		        
		        
		        <br>
				  <div class="form-group">
				    <div class="col-sm-offset-1 col-sm-11">
				      <input type="button" class="btn btn-danger" onclick="add()" value="添加" id="addStart">
				      <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				      
				    </div>
				  </div>
				</form>
			</div>
			<div class="modal-footer " >
<!-- 				<button type="button" class="btn btn-primary"  onclick="add()">添加</button> -->
			</div>
		</div>
	</div>
</div>

<!-- 修改窗口 -->
<div id="xiugai" class="modal" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<span class='glyphicon glyphicon-plus'>&nbsp;</span>修改！
				<button type='button' class='close' data-dismiss='modal'>
                		<span style='font-size:20px; ' class='glyphicon glyphicon-remove'></span>
                </button>
			</div>
			<div class="modal-body text-center">
				<form  class="form-horizontal" role="form" id="form2" >
				  <div class="form-group">
				    <label for="firstname" class="col-sm-5 control-label">商品名称</label>
				    <div class="col-sm-7">
				      <input type="hidden"  name="up_gid" id="up_gid" >
				      <input type="text"  class="form-control" name="up_gname" id="up_gname" style="width: 55%;text-align: center;" onkeyup="gnameKeyUp2()">
				    </div>
				  </div>
				    <span style="display: none; color: red; margin-left: 60px" id="tiGname2" ></span>  
				  <div class="form-group">
				    <label for="lastname" class="col-sm-5 control-label">商品价格</label>
				    <div class="col-sm-7">
				      <input  class="form-control" name="up_gprice" id="up_gprice"  style="width: 55%;text-align: center;"></input>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="lastname" class="col-sm-5 control-label">季节</label>
				    <div class="col-sm-7">
				     <select  class="selectpicker form-control  bootstrap-select_dropdown-toggle"   id="up_checkJijie" name="up_checkJijie" style="width: 55%;text-align: center;">
				       <option value="春天" name="up_jijie" style="text-align: center;">春天</option>
	                   <option value="夏天" name="up_jijie" style="text-align: center;">夏天</option>
	                   <option value="秋天" name="up_jijie" style="text-align: center;">秋天</option>
	                   <option value="冬天" name="up_jijie" style="text-align: center;">冬天</option>
				      </select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="lastname" class="col-sm-5 control-label">型号</label>
				    <div class="col-sm-7">
				      <input  class="form-control" name="up_size" id="up_size"  style="width: 55%;text-align: center;"></input>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="lastname" class="col-sm-5 control-label">商品描述</label>
				    <div class="col-sm-7">
				      <textarea rows="" cols="" class="form-control" name="up_descript" id="up_descript" style="width: 55%;text-align: center;"></textarea>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="lastname" class="col-sm-5 control-label">商品品牌</label>
				    <div class="col-sm-7">
				      <input  class="form-control" name="up_brand" id="up_brand" placeholder="请输入商品品牌" style="width: 55%;text-align: center;"></input>
				    </div>
				  </div>
				  
<!-- 				  <div id="demo-dp-component"> -->
<!-- 		        	<label class="col-sm-5 control-label" for="demo-text-input">消息发送时间</label> -->
<!-- 		           	<div class="input-group date" data-date-format="yyyy/mm/dd"> -->
<!-- 		               	<input type="text" class="form-control" id="checkNextTime" name="checkNextTime" placeholder="请选择消息发送时间" style="width: 65%;float: left;"> -->
<!-- 		               	<span class="input-group-addon" style="float: left;height: 33px"><i class="demo-pli-calendar-4"></i></span> -->
<!-- 		           	</div> -->
<!-- 		        </div> -->
		        
		        
		        <br>
				  <div class="form-group">
				    <div class="col-sm-offset-1 col-sm-11">
				      <input type="button" class="btn btn-danger" onclick="goodsUpdate()" value="修改" id="upStart">
				      <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				      
				    </div>
				  </div>
				</form>
			</div>
			<div class="modal-footer " >
<!-- 				<button type="button" class="btn btn-primary"  onclick="add()">添加</button> -->
			</div>
		</div>
	</div>
</div>

	<script type="text/javascript">
		function addGoods() {
			document.getElementById("tiGname").style.display="none";
			$("#form1")[0].reset();
//	       $("#form1").form("clear");
			document.getElementById("addStart").disabled=true;
	  		$("#xinzeng").modal("toggle");//手动切换模态窗
		}
		function gnameKeyUp(){
			document.getElementById("tiGname").style.display="none";
			document.getElementById("addStart").disabled=false;
			var gname=$("#gname").val();
			var gnamel=gname.trim();
			if(gnamel=="" || gnamel==null){
				document.getElementById("addStart").disabled=true;
				$("#tiGname").text("商品名不能为空...");
				document.getElementById("tiGname").style.display="inline";
			}
			
		}
		function gnameKeyUp2(){
			document.getElementById("tiGname2").style.display="none";
			document.getElementById("upStart").disabled=false;
			var gname=$("#up_gname").val();
			var gnamel=gname.trim();
			if(gnamel=="" || gnamel==null){
				document.getElementById("upStart").disabled=true;
				$("#tiGname2").text("商品名不能为空...");
				document.getElementById("tiGname2").style.display="inline";
			}
			
		}
		
		function add(){
			var gname=$("#gname").val();
			var gprice=$("#gprice").val();
			var size=$("#size").val();
			var descript=$("#descript").val();
			var brand=$("#brand").val();
			var sel=document.getElementsByName("chjijie");
			for(var i in sel){
				if(sel[i].selected){
					var jijie=sel[i].value;
				}
			}
			$.ajax({
				url:"<%=request.getContextPath()%>/addGoods",
				type:"post",
				dataType:"json",
				data:{"gname":gname,"gprice":gprice,"size":size,"descript":descript,"brand":brand,"jijie":jijie},
				success:function(obj){
					$("#xinzeng").modal("hide");
					$("#mJieGuo").html(obj.businessResult);
					$('#jieguo').modal('show');
					
				}
			})
		}
	
		
		function updateById(){
			var allTableData = $("#goodsTable").bootstrapTable('getAllSelections');//获取表格的所有内容行 
			
			if(allTableData==''){
				$("#mymodal").modal("toggle");
			}else if(allTableData.length>1){
				$("#mymodal2").modal("toggle");
				$("#tiShi").html("请选择一条数据进行修改...");
			}else{
				for(var j in allTableData){
					var gid=allTableData[j].gid;
				}
				var jijies=document.getElementsByName("up_jijie");
				
				$("#form2")[0].reset();
				$("#xiugai").modal("show");
				
				$.ajax({
					url:"<%=request.getContextPath()%>/goodsByGid",
					type:"post",
					dataType:"json",
					data:{"gid":gid},
					success:function(json){
						$("#up_gid").val(json.goodsByGid.gid);
						$("#up_gname").val(json.goodsByGid.gname);
						$("#up_gprice").val(json.goodsByGid.gprice);
						$("#up_size").val(json.goodsByGid.size);
						$("#up_descript").val(json.goodsByGid.descript);
						$("#up_brand").val(json.goodsByGid.brand);
						for(var i=0;i<jijies.length;i++){
							if(jijies[i].value==json.goodsByGid.jijie){
								jijies[i].selected=true;
							}
						}
					}
				})
				
			}
		}
		function goodsUpdate(){
			var gid=$("#up_gid").val();
			var gname=$("#up_gname").val();
			var gprice=$("#up_gprice").val();
			var size=$("#up_size").val();
			var descript=$("#up_descript").val();
			var brand=$("#up_brand").val();
			var jijies=document.getElementsByName("up_jijie");
			for(var i=0;i<jijies.length;i++){
				if(jijies[i].selected){
					var jijie=jijies[i].value;
				}
			} 
			$.ajax({
				url:"<%=request.getContextPath()%>/addGoods",
				type:"post",
				dataType:"json",
				data:{"gid":gid,"gname":gname,"gprice":gprice,"size":size,"descript":descript,"brand":brand,"jijie":jijie},
				success:function(json){
					$("#xinzeng").modal('hide');
		   			$("#xiugai").modal('hide');
		   			$("#mymodal2").modal("toggle");
					$("#tiShi").html(json.businessResult);
				}
			})
			
		}
		
		//报表导出
		function ExportExcel(){
			
			location.href="<%=request.getContextPath()%>/ExportExcel";
		}
	</script>
</body>


</html>