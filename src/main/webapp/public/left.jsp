<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Frame left</title>
	
	<script type="text/javascript" src="/public/style/js/jquery.js"></script>
	<script type="text/javascript" src="/public/style/js/page_common.js"></script>
    <link href="/public/style/css/common_style_blue.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		// 显示或隐藏二级菜单 
		function menuClick( menuDiv ){
			$(".MenuLevel2").not( $(menuDiv).next() ).hide();
			//$(".MenuLevel3").hide();
			$(menuDiv).next().toggle();
		}
		function menuClick2( menuDiv ){
			$(".MenuLevel2").not( $(menuDiv).next() ).hide();
			//$(".MenuLevel2").hide();
			$(menuDiv).next().toggle();
		}
		
		/* $(function(){
			// 默认只显示第1个二级菜单
			$(".MenuLevel2").hide();
			$(".MenuLevel2:first").show();
		}); */
		/* $(function(){
			// 默认只显示第1个二级菜单
			$(".MenuLevel3").hide();
			$(".MenuLevel3:first").show();
		}); */
	</script>
	<!-- 内容总宽度为 3px边框 * 2 + 155px内容 = 161px; -->
	<style type="text/css">

html{
height: 100%;
}
body {
	background: none repeat scroll 0 0 #D8EDFC;
	margin: 0;
	padding: 0;
}
#Menu {
    margin: 0;
    padding: 0;
    width: 155px;
	background: none repeat scroll 0 0 #D8EBF7;
    list-style: none outside none;
	
	margin-left: 3px;
	border-top: 3px solid #4891C6;
}
#Menu .level1 {
 color: #005790;
    font-weight: bold;
    padding-bottom: 1px;
	  cursor: pointer;
}
#Menu .level1 .level1Style {
  background: url("/public/style/images/img/menu_btn_bg.gif") no-repeat scroll 0 0 transparent;
    height: 23px;
    padding-left: 20px;
    padding-top: 5px;
    width: 135px;
	margin-bottom: -4px
}
#Menu .level1 .level1Style .Icon {
	margin-top: -2px;
}
#Menu .level1 .MenuLevel2 {
 background: none repeat scroll 0 0 #D8EBF7;
    list-style: none outside none;
    margin: 0;
    padding: 0;
}
#Menu .level1 .MenuLevel2 .level2Style{
	color: #005790;
    font-weight: normal;
	border-top: 1px solid #EFF6FB;
	height: 18px;
	padding-left: 43px;
	padding-top: 5px;
	width: 112px;
	background-image:url(/public/style/images/img/menu_arrow_single.gif);
	background-color: #8EC4E9;
	background-repeat: no-repeat;
	background-position: 29px center;
}

	</style>
</head>
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
	function roleList(){
		
		isLogin();
		
		var rid=${sessionScope.rid};
		var menuLi=document.getElementsByName("menuLi");
		
		$.ajax({
			url:"<%=request.getContextPath()%>/roleByRid",
			type:"post",
			dataType:"json",
			data:{"rid":rid},
			success:function(json){
				for(var i in json){
					var per=json[i].permissiones;
					for(var j in per){
						//alert(per[j].pid);
						for(var k=0;k<menuLi.length;k++){
							if(menuLi[k].value==per[j].pid){
								//alert(1);
								//$("#k").show();
								/* alert(k);
								alert(menuLi[k].value)
								alert(per[j].pid) */
								$("#"+k).show();
							}
						}
					}
				}
				
			}
		})
	}
</script>
<body onload="roleList()">
	
    <ul id="Menu">
	    <li class="level1">
            <div onClick="menuClick(this);" class="level1Style">
				<img src="/public/style/images/func20001.gif" class="Icon" /> 
				系统菜单
			</div>
            <ul class="MenuLevel2">
            	<li class="level2 level2Style" id="0" style="display: none" name="menuLi" value="1">
                    <a target="right" href="<%=request.getContextPath()%>/roleManager">角色管理</a>
				</li>
                <li class="level2 level2Style" id="1" style="display: none" name="menuLi" value="2">
                	<a target="right" href="<%=request.getContextPath()%>/userManager">用户管理</a>
				</li>
                <li class="level2 level2Style" id="2" style="display: none" name="menuLi" value="3">
                	<a target="right" href="<%=request.getContextPath()%>/listManager">查看列表</a>
				</li>
                
                <li class="level2 level2Style" id="3" style="display: none" name="menuLi" value="4">
                	<a target="right" href="<%=request.getContextPath()%>/pcenter">个人中心</a>
				</li>
            </ul>
            
			
        </li>
        
        <li class="level1">
         	 <div onClick="menuClick2(this);" class="level1Style">
				<img src="/public/style/images/1.png" class="Icon" /> 
				测试菜单
			</div>
			 <ul class="MenuLevel2">
			 
			 	<li class="level2 level2Style" id="4" style="display: none" name="menuLi" value="5">
                	<a target="right" href="<%=request.getContextPath()%>/caozuo1">操作1</a>
				</li>
                <li class="level2 level2Style" id="5" style="display: none" name="menuLi" value="6">
                	<a target="right" href="<%=request.getContextPath()%>/caozuo2">操作2</a>
				</li>
                <li class="level2 level2Style" id="6" style="display: none" name="menuLi" value="7">
                	<a target="right" href="<%=request.getContextPath()%>/caozuo3">操作3</a>
				</li>
			 	
			 	<li class="level2 level2Style" id="7" style="display: none" name="menuLi" value="8">
                	<a target="right" href="<%=request.getContextPath()%>/ceshiyixia">测试一下</a>
				</li>
			 </ul>
         </li>
         
    </ul>
</body>
</html>