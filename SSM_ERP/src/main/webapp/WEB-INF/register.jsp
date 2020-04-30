<%@ page language="java" contentType="text/html;  charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
    + request.getServerPort()+ path + "/";%>
<!DOCTYPE html>
<html lang="en">
<head>

<base href="<%=basePath%>" />  
<meta charset="UTF-8">
<title>企业管理系统用户注册</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="./css/xadmin.css">
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<script src="lib/layui/layui.all.js" charset="utf-8"></script>
<script type="text/javascript">
	//如果该页面在iframe中出现，那么会自动调用最外层窗口刷新本链接
	if (window != top) {
		top.location.href = location.href;
	}
</script>
	
<script type="text/javascript">

		//利用循环延时
		function sleep(delay) {
		    var start = (new Date()).getTime();
		    while((new Date()).getTime() - start < delay) {
		        continue;
		    }
		}

        function checkRegister() {
        	//取得输入框的值 $("#username")中的username是input的id名
    		var username = $("#username").val();
    		var password = $("#password").val();
    		var repassword = $("#repassword").val();
    		//alert("iooasdiff");
    		//前端验证
    		if (username == null || username == "") {
    			layer.msg("请输入用户名",{icon:2});
    			/*现在我发现alert()，是没有定义的,正确的阻碍线程的弹窗是alert(),所以这里真的是意想不到的效果，
    			我用一个错误的没有定义的方法完成了展示的效果*/
    			alter();//此处我原本只是想阻碍一下线程的运行，没想到直接达到了效果
    		}else if (password == null || password == ""){
    			layer.msg("请输入用户密码",{icon:2});
    			alter();
    		}else if (repassword == null || repassword == ""){
    			layer.msg("请再次输入用户密码",{icon:2});
    			alter();
    			}
    		// 4.)使用ajax来完成访问后台注册验证 此处使用jQuery封装的ajax（不同公司可能使用不同的js框架来封装ajax，如vue）
    		$.ajax({//键值对来表示设置的属性
    			//请求方式
    			type:"POST",
    			//设置请求后台的相应的url，根据规定或喜好可自行设置路径名，但前后要相一致
    			url:"userController/checkRegister.ajax",
    			//设置返回值类型
    			dataType:"text",
    			//设置发送到后台的值（通过键值对的形式发送到后台）
    			//前面的键名可以自行修改，后面的键值则要和方法中定义的变量名一致，如（1号）的 var username
    			data:{
    				username:username,
    				password:password,
    				repassword:repassword
    				
    			},
    			
    			//成功的回调函数，此处的info有待理解
    			success:function(info){
    				if (info=="REPEATNAME"){
    					layer.msg("用户名已存在",{icon:2})
    				}else if (info=="DIFFERENTPASSWARD"){
    					layer.msg("两次密码不一致",{icon:2})
    				}else if(info=="SUCCESS"){
    					//alert("注册成功");
    					layer.msg("注册成功",{icon:1})
    					sleep(1000);
    					//跳转到主页面(最好后台跳转)
    					window.location.href = "userController/index.do"
    				}
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
		<div class="message">医药管理系统-用户注册</div>
		
		<font  id="error" size="15" class="text-align:center" color="red"></font>
		<div id="darkbannerwrap"></div>
		<form id = "user" class="layui-form">
			<input id="username" placeholder="用户名" type="text" 
				class="layui-input" >
			<hr class="hr15">
			<input id="password" placeholder="密码" type="password"
				class="layui-input">
			<hr class="hr15">
			<input id="repassword" placeholder="确认密码" type="password"
				class="layui-input">
			<hr class="hr15">
			<input id = registerButton lay-submit lay-filter="login" style="width: 100%;"
				type="button" value="注册" onclick="checkRegister()" />
			<hr class="hr20">
		</form>
		<div style="float:right;"><a href="userController/goLogin.do" style="color:blue ">登录</a></div>
	</div>

</body>
</html>