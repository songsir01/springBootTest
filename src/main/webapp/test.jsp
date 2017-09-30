<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>


<link href="/plugins/nifty-v2.5/demo/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="/plugins/nifty-v2.5/demo/plugins/bootstrap-table/bootstrap-table.css"
	rel="stylesheet">
<link
	href="/plugins/nifty-v2.5/demo/plugins/gmaps/examples/examples.css"
	rel="stylesheet">
<script src="/plugins/nifty-v2.5/demo/js/jquery-2.2.4.min.js"></script>
<script src="/plugins/nifty-v2.5/demo/js/bootstrap.min.js"></script>
<script src="/plugins/nifty-v2.5/demo/js//bootstrap.js"></script>
<script
	src="/plugins/nifty-v2.5/demo/plugins/bootstrap-table/bootstrap-table.js"></script>
<script
	src="/plugins/nifty-v2.5/demo/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<link
	href="/plugins/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="/plugins/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css">
<script
	src="/plugins/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script
	src="/plugins/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/js/tool.js"></script>
<script type="text/javascript">
	function add() {
		$("#form")[0].reset();
		$("#save").modal("toggle");
	}
</script>
<body>

	<div class="panel panel-default "
		style="width: 80%; margin-left: auto; margin-right: auto; margin-top: 2%;">
		<div class="panel-heading">查询条件</div>
		<div class="panel-body form-group" style="margin-bottom: 0px;">
			<div class="col-sm-3 col-sm-offset-4">
				<input type="text" class="form-control" name="search1" id="search1" />
			</div>
			<div class="col-sm-1 ">
				<button class="btn btn-primary" id="search_btn" onclick="search()">查询</button>
			</div>
			<div class="col-sm-1 ">
				<button class="btn btn-info glyphicon glyphicon-file">统计</button>
			</div>
			<div class="col-sm-1 ">
				<a class="btn btn-primary glyphicon glyphicon-plus" role="button"
					onclick="add()">添加 </a>
			</div>
			<div class="col-sm-1">
				<button class="btn btn-success glyphicon glyphicon-pencil"
					onclick="toupdate()">修改</button>
			</div>
			<div class="col-sm-1">
				<button class="btn btn-danger glyphicon glyphicon-remove"
					onclick="del()">删除</button>
			</div>
			<div style="margin-top: 5%;">
				<table id="table" class="table table-hover">
				</table>
			</div>

		</div>
	</div>

	<!-- 新增弹窗 -->
	<div id="save" class="modal">
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
						<!-- <input type="hidden"   name="id" id="id" > -->
						<div class="form-group">
							<label for="firstname" class="col-sm-5 control-label">派车任务Id</label>
							<div class="col-sm-7">

								<input type="text" class="form-control" name="apply_id"
									id="apply_id" placeholder="请输入派车任务的Id" style="width: 55%">
							</div>
						</div>
						<div class="form-group">
							<label for="lastname" class="col-sm-5 control-label">汽车编号</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="send_car_number"
									id="send_car_number" placeholder="请输入汽车的编号" style="width: 55%">
							</div>



						</div>
						<div class="form-group">
							<label for="lastname" class="col-sm-5 control-label">交通人员的姓名</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="traffic_user_name"
									id="traffic_user_name" placeholder="请输入交通人员的姓名"
									style="width: 55%">
							</div>
						</div>
						<div class="form-group">
							<label for="lastname" class="col-sm-5 control-label">交通过路费</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="traffic_cost"
									id="traffic_cost" placeholder="请准确输入交通过路费的价钱"
									style="width: 55%">
							</div>
						</div>
						<div class="form-group">
							<label for="lastname" class="col-sm-5 control-label">收费地点</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="traffic_address"
									id="traffic_address" placeholder="请输入收费站的地点" style="width: 55%">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-1 col-sm-11">
								<input type="button" class="btn btn-primary" value="添加"
									onclick="addDo()">
							</div>
						</div>
				</div>

				</form>
			</div>
		</div>
	</div>
</body>
</html>