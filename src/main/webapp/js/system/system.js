$(function() {
	$("#submit").click(function() {
        loginFn();
    });
    document.onkeydown = function(event) {
        var e = event || window.event
                || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) { // enter 键
            loginFn();
        }
    };
    var loginFn = function() {
        var accountName = $("#accountName").val();
        var passwd = $("#passwd").val();
        var from = $("#from").val();
        //var imgcode = $("#imgcode").val();
        var param={
        		accountName : accountName,
        		passwd : sha256_digest(passwd),
        		from : from/*,
                 imgcode : imgcode*/
        };
        $.ajax({
			url : basePath+"/dologin.html",
			type : 'POST',
			dataType : 'json',
			data:param,
			success : function(result) {
				if (result.success) {
	                window.location.href = basePath+result.from;
	            } else {
	                $("#success").hide();
	                $("#error").html(result.errorCode.msg).show();
	            }
			},
			complete : function(request, status) {
				$(".menu_main_loading").hide();
				if (status == "timeout"){
					alert("请求菜单超时,请重试");
					return false;
				}else if(status == "error"){
					$("#success").hide();
			           $("#error").html(result.message).show();
				}
			}
		});
    }
	
	
    var $imgHolder 	= $('#demo-bg-list');
    var $bgBtn 		= $imgHolder.find('.demo-chg-bg');
    var $target 	= $('#bg-overlay');
    var url = basePath+"/img/bg-img/bg-img-2.jpg";
    $('<img/>').attr('src' , url).load(function(){
        $target.css('background-image', 'url("' + url + '")').addClass('bg-img');
        $imgHolder.removeClass('disabled');
        $(this).remove();
    });
});
function toResetPasswd(){
	var accountName = $("#accountName").val();
    var mailbox = $("#mailbox").val();
    var passwd = createPasswd();
    var param={
    		accountName : accountName,
    		mailbox : mailbox,
    		passwd:passwd,
    		passwdM:sha256_digest(passwd)
    };
    $.ajax({
		url : basePath+"/toResetPasswd.html",
		type : 'POST',
		dataType : 'json',
		data:param,
		success : function(result) {
			if (result.success) {
				$.niftyNoty({
                    type: 'dark',
                    icon : 'fa fa-check',
                    message : result.businessResult,
                    container : 'floating',
                    timer : 2000
                });
            } else {
            	$.niftyNoty({
                    type: 'warning',
                    icon : 'fa fa-info',
                    message : result.errorCode.msg,
                    container : 'floating',
                    timer : 2000
                });
            }
		},
		complete : function(request, status) {
			if (status == "timeout"){
				alert("请求超时,请重试");
				return false;
			}
		}
	});
}