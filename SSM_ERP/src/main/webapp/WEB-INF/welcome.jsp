<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import = "com.team.erp.framework.model.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- 这行是为了引入EL标签库  -->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>欢迎进入若愚企管系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="css/cutsom-style.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	
<script type="text/javascript">
/* 文档加载事件,是否有指定的cookie*/
//注意:ajax请求记得放行，否者将无法访问
$.ajax({
	type:"POST",
	url:"userController/checkLogin.ajax",
	dataType:"text",//传数据过来就是用json，传字符串过来就是用text
	data:{
		
	},
	success:function(info){
		//将json字符串转换为js对象
		var user = JSON.parse(info);
	    alert("成功到达welcome");
		
		alert(user.userName);
		var username = $("#username").val(user.userName);
		
		
		
	},
	error:function(){
		layer.msg("有关Cookie的ajax请求失败!",{icon:2})
	}
});




</script>
</head>
<body>
<% User user = (User)request.getAttribute("user");%>
<!-- %<user.getUserName();%> 现在获得的userName是空值，所以会报空指针异常 -->
	<div class="x-body">
		<blockquote class="layui-elem-quote">欢迎${userName}进入若愚企官系统v1.0！登录时间:${LOGINTIME}；祝您拥有美好的一天！</blockquote>
		<fieldset class="layui-elem-field">
			<legend>信息总览</legend>
			<div class="layui-field-box">
				<table class="layui-table">
					<thead>
						<tr>
							<th colspan="2" scope="col">您的基本信息</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="30%">您的账号</th>
							<td>${accountId}</td>
						</tr>
						<tr>
							<td>您的姓名</td>
							<td>${userName}</td>
						</tr>
						<!-- <tr>
							<td>您的浏览器信息</td>
							<td id="browserVersion"></td>
						</tr> -->
						<tr>
							<td>您的入职时间</td>
							<td>${joinTime}</td>
						</tr>
						<%-- <tr>
							<td>您的IP地址</td>
							<td>${userIp}</td>
						</tr> --%>
						<tr>
							<td>您的电话号码</td>
							<td>${telNum}</td>
						</tr><tr>
							<td>您的邮箱地址</td>
							<td>${Email}</td>
						</tr>
						<tr>
							<td>您所属的部门</td>
							<td>${department}</td>
						</tr>
						<tr class="spanLinkTr">
							<td>当前时间</td>
							<td><span id="st">加载中...</span>&nbsp; <span id="rstBtn"
								onclick="reServerTime()" class="spanLink"></span>
							</td>
						</tr>
					</tbody>
				</table>
				<%-- <table class="layui-table">
					<thead>
						<tr>
							<th colspan="2" scope="col">服务器信息</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="30%">本系统访问路径</th>
							<td><span id="lbServerName"><%=basePath %></span></td>
						</tr>
						<tr>
							<td>服务器名称</td>
							<td>${serverName}</td>
						</tr>
						<tr>
							<td>服务器ip地址</td>
							<td>${localIp}</td>
						</tr>
						<tr class="spanLinkTr">
							<td>当前在线人数</td>
							<td><span id="sasd">加载中...</span>&nbsp; 
							<span id="ronBtn" onclick="reOnlineNum()" class="spanLink">点击刷新</span>
							</td>
						</tr>
						<tr>
							<td>登录超时时间</td>
							<td>${timeout }秒</td>
						</tr>
						<tr class="spanLinkTr">
							<td>服务器当前时间</td>
							<!-- 点击刷新 -->
							<td><span id="st">加载中...</span>&nbsp; <span id="rstBtn"
								onclick="reServerTime()" class="spanLink"></span>
							</td>
						</tr>
					</tbody>
				</table> --%>
			</div>
		</fieldset>
		<!-- 示例：调用父窗口的某个方法 -->
		<blockquote class="layui-elem-quote layui-quote-nm">
			本系统前端框架支持：X-admin（<a
				onclick="parent.x_admin_show('X-admin主页','http://x.xuebingsi.com/')">http://x.xuebingsi.com/</a>）；
			前端框架调整：liu（liuyulonggz@163.com）； 项目开发：若愚企官系统（<a
				onclick="parent.x_admin_show('若愚企官系统主页','http://localhost:8080/SSM_ERP/userController/index.do')">http://localhost:8080/SSM_ERP/userController/index.do</a>）java
			EE项目组
		</blockquote>
	</div>
</body>
<script type="text/javascript">
	//页面初始化方法
	function showBrowserVersion() {
		var userAgent = window.navigator.userAgent;
		$("#browserVersion").text(userAgent);
	}

	function reServerTime() {
		$("#rstBtn").attr("disabled", "disabled");
		$("#rstBtn").css("display", "none");
		//执行获取服务器时间的方法……
		$.ajax({
			type:"POST",
			url:"userController/getServerTime.ajax",
			data:{
				
			},
			dataType:"text",
			success:function(result){
				$("#st").text(result);
			},
			error:function(){
				$("#st").text("获取失败");
			}
		});
		//回弹按钮
		setTimeout("popRstBtn()", 1000);
	}

	function popRstBtn() {
		$("#rstBtn").removeAttr("disabled");
		$("#rstBtn").css("display", "inline");
	}

	function reOnlineNum() {
		$("#ronBtn").attr("disabled", "disabled");
		$("#ronBtn").css("display", "none");
		//执行获取在线人数的方法……
		$.ajax({
			type:"POST",
			url:"indexController/OnlineNum.ajax",
			data:{
				
			},
			dataType:"text",
			success:function(ifo){
				$("#sasd").text(ifo);
			},
			error:function(){
				$("#sasd").text("获取失败");
			}
		});
		//回弹按钮
		setTimeout("popRonBtn()", 1000);
	}

	function popRonBtn() {
		$("#ronBtn").removeAttr("disabled");
		$("#ronBtn").css("display", "inline");
	}

	$(function() {
		showBrowserVersion();
		reOnlineNum();
		reServerTime();
	});
	
	 /* var T = document.getElementById("st")
	 getFNTime();
	 function getFNTime(){	
			var nd = new Date();
			var ntime = "";
			ntime += nd.getFullYear()+"年";
			if(nd.getMonth()+1 >9){
				ntime += (nd.getMonth()+1)+"月";
			}else{
				ntime += "0"+(nd.getMonth()+1)+"月";
			}
			if(nd.getDate() >9){
				ntime += nd.getDate()+"日";
			}else{
				ntime += "0"+nd.getDate()+"日";
			}
			ntime += nd.getHours()+"时";
			if(nd.getMinutes() >9){
				ntime += nd.getMinutes()+"分";
			}else{
				ntime += "0"+(nd.getMonth()+1)+"分";
			}
			if(nd.getSeconds() >9){
			   ntime += nd.getSeconds()+"秒";
			}else{
			   ntime += "0"+nd.getSeconds()+"秒";
			}
			   T.innerHTML = ntime;
			   setInterval(function(){getFNTime()},1000);
			}    */
</script>


</html>