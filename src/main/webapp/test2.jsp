<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<script src="js/jquery-1.8.2.js"></script>
<script src="js/ajaxfileupload.js"></script>


<body>
<input type="file" id = "fileId" name = "fileName">
<button id = "uploadImg" onclick = "upImg()">上传</button> 
	
	<img alt="" src="aa.gif" id="gifId" style="display: none">
	
	<script type="text/javascript">
	
	function hideImg(){
		$("#gifId").css("display", "none");
	}
	function showImg(){
		$("#gifId").css("display", "block");
	}
	
	
	function upImg(){
		showImg();
		 $.ajaxFileUpload({
			 	url:'test2.html',
				secureuri:false,
				fileElementId:'upload',
				dataType: 'text/html',
				success: function(data,success){
					
// 					var start = data.indexOf(">");  
// 					if(start != -1) {  
// 						var end = data.indexOf("<", start + 1);  
// 						if(end != -1) {  
// 						   data = data.substring(start + 1, end); 					                 		 
// 						 }  
// 					}	
// 					//显示图片
// 					$("#photos").append('<li  img="'+data+'"><img src="http://images.shopin.net/images/refund/'+data+'"><i>X</i></li>');
// 		           	//3张图片以后不能上传了
// 		           	var num = $("#photos").find('li').length;
// 		           	if (num >= 3){
// 		           		$("#choosePhoto").hide();
// 		           	}	
// 		           	closeLoad();
				},
// 				error: function (data, status, e){
// 			        aler("上传图片失败，请稍后重试。");
// 		           	closeLoad();
// 		        }
			});
		
	}
	
	
	
	$("#fileId").live("change",upload);
	function upload(event){
		if(event.target.files.length == 1 ) {
			if (event.target.files[0].size >= 4096000) {
				alert('您这张图片过大，应小于4M');	
			}else{
				showImg();
		        $.ajaxFileUpload({url:'test2.html',
					secureuri:false,
					fileElementId:'upload',
					dataType: 'text/html',
					success: function(data,success){
// 						var start = data.indexOf(">");  
// 						if(start != -1) {  
// 							var end = data.indexOf("<", start + 1);  
// 							if(end != -1) {  
// 							   data = data.substring(start + 1, end); 					                 		 
// 							 }  
// 						}	
// 						//显示图片
// 						$("#photos").append('<li  img="'+data+'"><img src="http://images.shopin.net/images/refund/'+data+'"><i>X</i></li>');
// 			           	//3张图片以后不能上传了
// 			           	var num = $("#photos").find('li').length;
// 			           	if (num >= 3){
// 			           		$("#choosePhoto").hide();
// 			           	}	
// 			           	closeLoad();
					},
					error: function (data, status, e){
// 				        aler("上传图片失败，请稍后重试。");
// 			           	closeLoad();
			        }
				});
			}
		}else{
			alert('您上传的不是图片，请选择图片上传');
		}
	}
	
	
	
	
	</script>
</body>
</html>