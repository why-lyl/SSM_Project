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
<title>医药管理系统</title>
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
    /* 文档加载事件,是否有指定的cookie*/
    //注意:ajax请求记得放行，否者将无法访问
    $.ajax({
    	type:"POST",
    	url:"userController/queryCookie.ajax",
    	dataType:"text",//传数据过来就是用json，传字符串过来就是用text
    	data:{
    		
    	},
    	success:function(info){
    		//将json字符串转换为js对象
    		var user = JSON.parse(info);
    		/*var username = $("#username").val(user.userName);
        	  var password = $("#password").val(user.password);
    		此处的user是UserServiceImpl层也就是user的实现层中定义的，
    		.username 相当于就是取的它的属性（有待理解）(理解有误)
    		此处的user应该就是var user = JSON.parse(info);所定义的user
    		user.后面的属性就是将字符串转换为json格式里面的属性，
    		转换为json格式后为{"userId":1,"userName":"cpa","password":"123"}
    		所以这里应该写user.userName而非user.username（老师的写法）
    		(其实这里还要加强理解)*/
    		
    		/* if ($("#memoryuser").prop("checked",true)) {	
			   var username = $("#username").val(user.userName);
    		   var password = $("#password").val(user.password);
			   }else if($("#memoryuser").prop("checked",false)){
			   var username = $("#username").val(user.userName);
			   };
    		   $("#memoryuser").prop("checked",true) 就相当于一个方法执行了，
    		     这里的意思是代码执行时将复选框选中, 所以无法满足我记住密码展示的要求
    		     更换思路为使用一个变量来获取复选框的值，这样复选框状态就会变化了，就能
    		     根据相应变化写出记住密码的展示了
    		*/
    		
    		 //只要有默认的cookie就默认选中复选框
    		/* if (user.username != "") {
    			$("#memoryuser").prop("checked",true);
			};  */
			
    		// 取得复选框（checkbox）的状态(true 或者 false)
    		var checkObj = $("#memoryuser").prop("checked");
			//alert(checkObj);
			//alert(typeof(checkObj));
    		// 记住密码功能展示
    		//看来还需要将checkObj保存在cookie里面啊，否者页面一刷新就什么也没有了
    		//alert(user.selectionBox);
    		if (user.selectionBox == "YES") {
    			$("#memoryuser").prop("checked",true);
    		   var username = $("#username").val(user.userName);
     		   var password = $("#password").val(user.password);
     		  // alert(checkObj);
			}else {
			   var username = $("#username").val(user.userName);
			};
			
    	},
    	error:function(){
    		layer.msg("有关Cookie的ajax请求失败!",{icon:2})
    	}
    });
	function checkLogin() {
		// 1.)取得输入框的值 $("#username")中的username是input的id名（1号）
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
		};
		
		//前端验证
		if (username == null || username == "") {
			layer.msg("请输入用户名",{icon:2});
			/*现在我发现alert()，是没有定义的,正确的阻碍线程的弹窗是alert(),所以这里真的是意想不到的效果，
			我用一个错误的没有定义的方法完成了展示的效果*/
			alter();//此处我原本只是想阻碍一下线程的运行，没想到直接达到了效果
		}else if (password == null || password == ""){
			layer.msg("请输入用户密码",{icon:2});
			alter();
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
				if (info=="ERROR"){
					layer.msg("用户名或者密码错误",{icon:2})
				}else{
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
		<div class="message">医药管理系统-用户登录</div>
		
		<font  id="error" size="15" class="text-align:center" color="red"></font>
		<div id="darkbannerwrap"></div>
		<form id = "user" class="layui-form">
			<input id="username" placeholder="用户名" type="text" 
				class="layui-input" >
			<hr class="hr15">
			<input id="password" placeholder="密码" type="password"
				class="layui-input">
			<hr class="hr15">
			<input lay-submit lay-filter="login" style="width: 100%;"
				type="button" value="登录" onclick="checkLogin()" />
			<hr class="hr20">
		</form>
		<input type="checkbox" id="memoryuser" >&nbsp;记住用户密码
		<div style="float:right;"><a href="userController/goRegister.do" style="color:blue ">注册</a></div>
	</div>

</body>
</html>