<%@ page language="java" contentType="text/html;  charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<%-- <base href="<%=basePath%>" /> --%>
<meta charset="UTF-8">
<title>xxx管理系统</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="./css/xadmin.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<script src="lib/layui/layui.all.js" charset="utf-8"></script>
<script type="text/javascript">
	//如果该页面在iframe中出现，那么会自动调用最外层窗口刷新本链接
	if (window != top) {
		top.location.href = location.href;
	}
</script>

<script type="text/javascript">
	function checkLogin() {
		// 1.)取得输入框的值 $(#"username")中的username是input的id名（1号）
		var username = $("#username").val();
		var password = $("#password").val();
		// 2.)取得复选框（checkbox）的状态(true 或者 false)
		var checkObj = $("#memoryuser").prop("checked");
		/* alert(username);
		alert(checkObj);//alert()方法在页面上弹出一个窗口（对话框），阻碍程序的运行
		alert(typeof(checkObj));//typeof(checkObj)查看类型  */
		
		// 3.)给定一个变量区分是否被选中
		var selectionBox = "NO";
		if (checkObj) { //if()，()中为true则会执行{}中的语句
			selectionBox = "YES";//表示被选中
		}
		
		// 4.)使用ajax来完成访问后台登录验证 此处使用jQuery封装的ajax（不同公司可能使用不同的js框架来封装ajax，如vue）
		$.ajax({//键值对来表示设置的属性
			//请求方式
			type:"POST",
			//设置请求后台的相应的url，根据规定或喜好可自行设置路径名，但前后要相一致
			url:"userController/checkLogin.ajax",
			//设置返回值类型
			dataType:"text",
			//设置发送到后台的值（通过键值对的形式发送到后台）
			//前面的键名可以自行修改，后面的键值则要和方法中定义的变量名一致，如（1号）的 var username
			data:{
				username:username,
				password:password,
				selectionBox:selectionBox
			},
			
			//成功的回调函数，此处的info有待理解
			success:function(info){
				
			},
			
			//失败的回调函数
			error:function(){
				//使用layui的打印信息(必须引入相应的框架才能使用) {icon:2}表示一个图标
				layer.msg("ajax请求失败!",{icon:2})
				
			}
			
			
		});
		
	}

</script>

</head>
<body class="login-bg">

	<div class="login" >
		<div class="message">XXX管理系统-用户登录</div>
		
		<font  id="error" size="15" class="text-align:center" color="red"></font>
		<div id="darkbannerwrap"></div>
		<form  class="layui-form">
			<input id="username" placeholder="用户名" type="text" 
				class="layui-input">
			<hr class="hr15">
			<input id="password" placeholder="密码" type="password"
				class="layui-input">
			<hr class="hr15">
			<input lay-submit lay-filter="login" style="width: 100%;"
				type="button" value="登录" onclick="checkLogin()" />
			<hr class="hr20">
		</form>
		<input type="checkbox" id="memoryuser" >&nbsp;30天内自动登录
	</div>

</body>
</html>