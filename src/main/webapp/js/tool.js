/**
 *  工具类 on 15-1-9.
 *
 *
 */
/* 去除字符串两边的空格 */
String.prototype.trim = function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
/*替换所有字符*/
String.prototype.replaceAll = function(s1,s2) {

    return this.replace(new RegExp(s1,"gm"),s2);

};
/*
*
* 截取字符串长度
*
* @Parameters
* str  需要传入的字符串
* number 需要的目标字符串位数
* count  不包括点的字符串长度数
* var str = "gfhjjuikoilolili";
* str.part(str,5,5);//gfhjj..
*
* */
String.prototype.part=function(str,number,count){
	 if(str.length>number){
		 return str=str.substring(0,count)+"..";
	 }else{
		 return str;
	 }
};
/*
 * 获取字符串中所有的数字
 * str:字符串
 */
String.prototype.getNum = function(str){
	if(!isNull(str)){
		return str.replace(/[^0-9]/ig,"");
	}else{
		return str;
	}
}
//是不是数字(含正负)
var isDigit2=function (obj){
    var patrn;   
    patrn=/^(-|\+)?\d+$/;
    if (!patrn.exec(obj)) {
        return false;
    }
    return true;
}
//字节转换单位
var  bytesToSize=function (bytes) {
    if (bytes === 0) return '0 B';
    var k = 1024, // or 1000
        sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
        i = Math.floor(Math.log(bytes) / Math.log(k));
   return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
}

//加载数据对json是否会话过期
var initJsonData=function (data){
	if (data['status'] == 1 || data['statusCode'] == "301") {
		alert(data['message']);
		return false;
	}
};
//对请求数据后的异常处理
var completeJsonData=function(status,content){
	if (status == "timeout") {
		alert("请求["+content+"]超时,请重试");
		return false;
	}else if(status == "error"){
		alert("请求["+content+"]系统内部错误");
		return false;
	}
};
var request=function (url,data,isShow,successCallback, failedCallback, errorCallback, isAsynchronous,timeout){
	if(!isNull(isShow)){
		$(isShow).show();
	}
	if(!timeout){
		timeout=20*1000;// 超时20秒
	}
	if(isAsynchronous){
		isAsynchronous = false;
	}else{
		isAsynchronous = true;
	};
	if(!data){
		data="";
	}
	$.ajax({
		type:'POST',
		url:url,
		async:isAsynchronous,
		data:data,
		dataType:'json',
		timeout:timeout*1000,      
		success:function(data){
			if(data){
				if (data['status'] == 1 || data['statusCode'] == "301") {
					alert(data['message']);
					return false;
				}
				if(successCallback){
					if(!isNull(isShow)){
						$(isShow).hide();
					}
					successCallback(data);
				}
			}else{
				if(!isNull(isShow)){
					$(isShow).hide();
				}
				if(failedCallback)
					failedCallback(data);
			}
		},
		complete:function(XMLHttpRequest,status){
			var data = {};
			data.status = status;
			data.message = '系统繁忙,再试一次吧 ';
			if(status=='timeout'){
				if(!isNull(isShow)){
					$(isShow).hide();
				}
				if(errorCallback){
					errorCallback(data);
				}else{
					alert("请求超时,请重试");
					return false;
				}
			}else if(status=='error'){
				if(!isNull(isShow)){
					$(isShow).hide();
				}
				data.message = '系统内部错误';
				if(errorCallback){
					errorCallback(data);
				}else{
					alert("系统内部错误");
					return false;
				}
			
			}
		}
	});
}
var showLoginWaitCurr=function(){
	 $("#loadding").css("left",$("body").width()/2-70); //页面初始化让loadding居中
	 $("#loadding").show();
}
var hideLoginWaitCurr=function (){
	
}
//是不是手机号
function isPhoneNumber(obj) {
	var phoneID = /^((13|14|15|17|18)){1}\d{9}$/;
	if(!phoneID.test(obj)){
	    return false;
	}else{
		return true;
	}
}
//是不是电话号码
var isPhone=function (obj){
    var strPhone=obj;
    var phoneRegWithArea1 = /^(\d){8}$/;
    if (strPhone.length!=0){
        if(phoneRegWithArea1.test(strPhone)){
            return true;
        }else{
            return false;
        }
    }
}
/* 是否满足电信手机号码的要求 */
var isTelecomPhone=function (obj) {
    var strMobile=obj;
    if(strMobile.length!=11){
    	return false;
    }
    var regu =/1[3-9]+\d{9}/;
    var re = new RegExp(regu);
    if (strMobile.length!=0){
        if (strMobile.search(re)!=-1) {
            var s=strMobile.substring(0,3);
            if(s=="133"||s=="153"||s=="189"||s=="180"||s=="181"||s=="177"){
                return true;
            }else{
                return false;
            }
        }else{
            var num = strMobile.substring(0,5);
            if (num =='10649'&&strMobile.length==13){
                return true;
            }else{
                return false;
            }
        }
    }
}
//是不是邮件地址
var isEmail=function (obj){
    var patrn;
    patrn=/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if (!patrn.exec(obj)) {
        return false;
    }
    return true;
}

//是不是数字
var isDigit=function (obj){
    var patrn;
    patrn=/^[0-9]{1,20}$/;
    if (!patrn.exec(obj)) {
        return false;
    }
    return true;
}
//比较两个日期的大小函数 start > end 返回 false
//改用字符串形式比较日期 因有的手机不支持毫秒数比较
var compareDate=function (start,end) {
    start =  formatDateStr(start);
    end =  formatDateStr(end);
    if(start > end){
        return false;
    }else{
        return true;
    }

}
//获得指定日期的字符串，若为空则获取当前日期的字符串  如2012-9-27
var getDateStr=function (myDate){
	if(myDate==null){
		myDate=new Date();
	}
    var str = myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
    return str;
}
//获得指定日期时间的字符串，若为空则获取当前日期的字符串  如2012-9-27 12:12:12
var getDateTimeStr=function (myDateTime){
	if(myDateTime==null){
		myDateTime=new Date();
	}
    var str = myDateTime.getFullYear()+'-'+(myDateTime.getMonth()+1)+"-"+myDateTime.getDate()+" "+
    myDateTime.getHours()+":"+myDateTime.getMinutes()+":"+myDateTime.getSeconds();
    return str;
}
//将2012-9-27格式化成2012-09-27
var formatDateStr=function (str){
    if(isNull(str))
        return "";
    var regEx = new RegExp("\\-","gi");
    str = str.replace(regEx,"/");
    var dependedDate=new Date(Date.parse(str));
    var format = 'yyyy-MM-dd ';
    var o =
    {
        "M+" : dependedDate.getMonth()+1, //month
        "d+" : dependedDate.getDate(),    //day
        "h+" : dependedDate.getHours(),   //hour
        "m+" : dependedDate.getMinutes(), //minute
        "s+" : dependedDate.getSeconds(), //second
        "q+" : Math.floor((dependedDate.getMonth()+3)/3),  //quarter
        "S" : dependedDate.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format))
        format=format.replace(RegExp.$1,(dependedDate.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(format))
            format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}
//将2012-9-27格式化成2012-09-27
var formatDateTimeStr=function (str){
    if(isNull(str))
        return "";
    var regEx = new RegExp("\\-","gi");
    str = str.replace(regEx,"/");
    var dependedDate=new Date(Date.parse(str));
    var format = 'yyyy-MM-dd hh:mm:ss';
    var o =
    {
        "M+" : dependedDate.getMonth()+1, //month
        "d+" : dependedDate.getDate(),    //day
        "h+" : dependedDate.getHours(),   //hour
        "m+" : dependedDate.getMinutes(), //minute
        "s+" : dependedDate.getSeconds(), //second
        "q+" : Math.floor((dependedDate.getMonth()+3)/3),  //quarter
        "S" : dependedDate.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format))
        format=format.replace(RegExp.$1,(dependedDate.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(format))
            format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}

//验证输入的金额是否正确  两位小数 精确到分
var isMoney=function (str){
    str = str.trim();
    var a=/^[0-9]*[1-9][0-9]*$/;///^[0-9]*(\.[0-9]{1,2})?$/;
    //"^[0-9]*[1-9][0-9]*$"
    if(str == ""){return false;}
    if(!a.test(str)){
        return false;
    }else{
        return true;
    }
}
//判断字符串变量是否为空
var isNull=function(str){
    str = $.trim(str);
    if(!str || str=="" || str=="null" || str=="undefined")
        return true;
    return false;
}

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
//判断是否是邮编
var isPostCode=function (postCode){
    var re= /^[0-9]{6}$/;
    if(re.test(postCode)){
        return true;
    }else{
        return false;
    }
}
//判断是不是合法身份证号
var isIDCardNum=function (num){
    num = num.toUpperCase();
    //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
    if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num)))
    {
        return '输入的身份证号长度不对，或者号码不符合规定！<br>15位号码应全为数字，18位号码末位可以为数字或X。';
    }
    //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
    //下面分别分析出生日期和校验位
    var len, re;
    len = num.length;
    if (len == 15)
    {
        re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
        var arrSplit = num.match(re);

        //检查生日日期是否正确
        var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
        var bGoodDay;
        bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay)
        {
            return '输入的身份证号里出生日期不对！';
        }
        else
        {
            //将15位身份证转成18位
            //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var nTemp = 0, i;
            num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
            for(i = 0; i < 17; i ++)
            {
                nTemp += num.substr(i, 1) * arrInt[i];
            }
            num += arrCh[nTemp % 11];
            return true;
        }
    }
    if (len == 18)
    {
        re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
        var arrSplit = num.match(re);

        //检查生日日期是否正确
        var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
        var bGoodDay;
        bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay)
        {
            return '输入的身份证号里出生日期不对！';
        }
        else
        {
            //检验18位身份证的校验码是否正确。
            //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
            var valnum;
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var nTemp = 0, i;
            for(i = 0; i < 17; i ++)
            {
                nTemp += num.substr(i, 1) * arrInt[i];
            }
            valnum = arrCh[nTemp % 11];
            if (valnum != num.substr(17, 1))
            {
                //$("#tip").html('18位身份证的校验码不正确！应该为：' + valnum);
                return '18位身份证的校验码不正确！';
            }
            return true;
        }
    }
    return '18位身份证的校验码不正确！';
}
String.prototype.removeLineEnd = function() {
	return this.replace(/(<.+?\s+?)(?:\n\s*?(.+?=".*?"))/g, '$1 $2')
}
function formatXml(text) {
	//去掉多余的空格
	text = '\n' + text.replace(/(<\w+)(\s.*?>)/g, function($0, name, props) {
		return name + ' ' + props.replace(/\s+(\w+=)/g, " $1");
	}).replace(/>\s*?</g, ">\n<");

	//把注释编码
	text = text.replace(/\n/g, '\r').replace(/<!--(.+?)-->/g,
			function($0, text) {
				var ret = '<!--' + escape(text) + '-->';
				//alert(ret);
				return ret;
			}).replace(/\r/g, '\n');

	//调整格式
	var rgx = /\n(<(([^\?]).+?)(?:\s|\s*?>|\s*?(\/)>)(?:.*?(?:(?:(\/)>)|(?:<(\/)\2>)))?)/mg;
	var nodeStack = [];
	var output = text.replace(rgx, function($0, all, name, isBegin,
			isCloseFull1, isCloseFull2, isFull1, isFull2) {
		var isClosed = (isCloseFull1 == '/') || (isCloseFull2 == '/')
				|| (isFull1 == '/') || (isFull2 == '/');
		//alert([all,isClosed].join('='));
		var prefix = '';
		if (isBegin == '!') {
			prefix = getPrefix(nodeStack.length);
		} else {
			if (isBegin != '/') {
				prefix = getPrefix(nodeStack.length);
				if (!isClosed) {
					nodeStack.push(name);
				}
			} else {
				nodeStack.pop();
				prefix = getPrefix(nodeStack.length);
			}

		}
		var ret = '\n' + prefix + all;
		return ret;
	});

	var prefixSpace = -1;
	var outputText = output.substring(1);
	//alert(outputText);

	//把注释还原并解码，调格式
	outputText = outputText.replace(/\n/g, '\r').replace(
			/(\s*)<!--(.+?)-->/g,
			function($0, prefix, text) {
				if (prefix.charAt(0) == '\r')
					prefix = prefix.substring(1);
				text = unescape(text).replace(/\r/g, '\n');
				var ret = '\n' + prefix + '<!--'
						+ text.replace(/^\s*/mg, prefix) + '-->';
				return ret;
			});

	return outputText.replace(/\s+$/g, '').replace(/\r/g, '\r\n');
}
function getPrefix(prefixIndex) {
	var span = '    ';
	var output = [];
	for ( var i = 0; i < prefixIndex; ++i) {
		output.push(span);
	}
	return output.join('');
}
function btnFormat_click() {
	var $ = document.getElementById;
	$('output').value = formatXml($('input').value);
}
function createPasswd(){
	var aaa = [ 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
			'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9', '+', '/', '*', '.', '!', '@', '#',
			'$', '%', '^', '&', '(', ')', '-'];
	var pass = "";
	for(var i=0;i<12;i++){
		var num = Math.round(Math.random()*75);
		pass+=aaa[num];
		
	}
	return pass;
}
function createImgName(){
    var aaa = [ 'A', 'B', 'C', 'D',
        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
        'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
        'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
        'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
        '4', '5', '6', '7', '8', '9'];
    var name = "";
    for(var i=0;i<20;i++){
        var num = Math.round(Math.random()*61);
        name+=aaa[num];
    }
    return name;
}