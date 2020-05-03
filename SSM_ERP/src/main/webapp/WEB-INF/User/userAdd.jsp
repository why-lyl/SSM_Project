<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<meta charset="UTF-8">
<title>企业资产管理系统</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js"
	charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
</head>
<body>
    <div class="x-body">
        <form id="form1" class="layui-form">
          <div class="layui-form-item">
              <label for="staffname" class="layui-form-label">
                  <span class="x-red">*</span>姓名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="staffname" name="staffName" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
           <div class="layui-form-item">
              <label for="staffsex" class="layui-form-label">
                  <span class="x-red">*</span>性别
              </label>
              <div class="layui-input-inline">
                  <input name="staffSex" checked="" type="radio" value="男" lay-skin="primary" title="男">
                  <input name="staffSex" type="radio" value="女" lay-skin="primary" title="女">
              </div>
          </div>
         <div class="layui-form-item">
              <label for="phone" class="layui-form-label">
                  <span class="x-red">*</span>手机
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="phone" name="staffTel" required="" lay-verify="phone"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
         <div class="layui-form-item">
              <label for="phone" class="layui-form-label">
                  <span class="x-red">*</span>电子邮箱
              </label>
              <div class="layui-input-inline">
                  <input type="text"  name="staffEmail" required="" lay-verify="email"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
         <%--   <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>家庭地址
              </label>
              <div class="layui-input-inline">
                  <input type="text"  name="userAddress" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>--%>
          <div class="layui-form-item">
              <label for="staffBirthday" class="layui-form-label">
                  <span class="x-red">*</span>出生日期
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="date1" name="staffBirthday" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="staffjoin" class="layui-form-label">
                  <span class="x-red">*</span>入职时间
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="date2" name="staffJoin" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <<!-- div class="layui-form-item">
              <label for="staffage" class="layui-form-label">
                  <span class="x-red">*</span>年龄  
              </label>
              <div class="layui-input-inline">
                  <input type="number" id="staffAge" name="staffAge" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">  
              </div>
          </div> -->
         <%--  <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>学历
              </label>
              <div class="layui-input-inline">
                  <select id="shipping" name="userDiploma" class="valid">
                    <option value="大专">大专</option>
                    <option value="本科">本科</option>
                    <option value="硕士">硕士</option>
                    <option value="博士">博士</option>
                  </select>
              </div>
          </div>--%>
          <div class="layui-form-item">
              <label for="staffApart" class="layui-form-label">
                  <span class="x-red">*</span>部门安排
              </label>
              <div class="layui-input-inline">
                  <select name="staffDepart">
                    <c:forEach items="${departs}" var="department">
              		<option value="${department.departmentName }">${department.departmentName} </option>
              </c:forEach>
                  </select>
              </div>
          </div>
          <%-- <div class="layui-form-item">
              <label for="L_email" class="layui-form-label">
                  <span class="x-red">*</span>职位
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_email" name="userPosition" required="" lay-verify=""
                  autocomplete="off" class="layui-input">
              </div>
          </div> --%>
            <div class="layui-form-item">
              <label for="staffAccountId" class="layui-form-label">
                  <span class="x-red">*</span>账号
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="staffAccountId" name="accountId" required="" onchange="checkAccountId(this)" lay-verify="nikename"
                  autocomplete="off" class="layui-input">
              </div>
              <div id="account" class="layui-form-mid layui-word-aux">
                 	至少3个字符
              </div>
          </div>
            <div class="layui-form-item">
              <label for="AccountPassword" class="layui-form-label">
                  <span class="x-red">*</span>密码
              </label>
              <div class="layui-input-inline">
                  <input onchange="safePW()" type="password" id="AccountPassword" name="password" required="" lay-verify="pass"
                  autocomplete="off" class="layui-input">
              </div>
              <div id="password" class="layui-form-mid layui-word-aux ">
                  至少6个字符
              </div>
          </div>
          <div class="layui-form-item">
              <label for="ReAccountPassword" class="layui-form-label">
                  <span class="x-red">*</span>确认密码
              </label>
              <div class="layui-input-inline">
                  <input onchange="confirm()" type="password" id="ReAccountPassword" name="repassword" required="" lay-verify="repass"
                  autocomplete="off" class="layui-input">
              </div>
               <div id="confire" class="layui-form-mid layui-word-aux ">
                  	两次密码必须一致
              </div> 
          </div>  
          
          <div class="layui-form-item">
              <label for="ReAccountPassword" class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-filter="add" lay-submit="">
                  增加
              </button>
          </div>
      </form>
    </div>
    <script>
	    /* function checkAge(){
	    	if($("#staffAge").val()<0){
    			$("#staffAge").html("年龄不能小于0");
    		}
	    } */
    	function checkAccountId(doms){
    		if($("#staffAccountId").val().length<2){
    			$("#staffAccountId").select();
    			$("#account").html("至少3个字符");
    		}else{
    			AccountAjax();
    		}			
		};
		function safePW(){
			if($('#AccountPassword').val().length>5){
				$("#password").html("<i class=\"layui-icon layui-anim layui-anim-scale \" style=\"color: #1E9FFF;\">&#xe618;</i>")
			}
		}
		function confirm(){
			if($('#AccountPassword').val()==$('#ReAccountPassword').val()){
                  $("#confire").html("<i class=\"layui-icon layui-anim layui-anim-scale \" style=\"color: #1E9FFF;\">&#xe618;</i>") 
                }
		}
		function AccountAjax(){
			$.ajax({//这个ajax是验证账号是否重复
				type : "POST",
				async:false,
				data : {
					account:$("#staffAccountId").val()
				},
				dataType : "text",
				url : "staffController/isRepeat.ajax",
				success : function(result) {
					console.log(result);
					if(result=="NO"){
						$("#account").html("<i class=\"layui-icon layui-anim  layui-anim-scale\" style=\"color: #1E9FFF;\">&#xe618;</i>  ")
					}else{
						$("#staffAccountId").select();
						$("#account").html("<span style=\"color: #FF5722\" class=\"layui-anim layui-anim-scale\">账号已存在!</span>")
					}
					
				},
				error : function() {
					$("#staffAccountId").select();
					$("#account").html("<span style=\"color: #FF5722\" class=\"layui-anim layui-anim-scale\">无法连接服务器!</span>")
					layer.msg('无法连接服务器', {icon: 2});
				}
			});
		}
		layui.use(['form','layer','laydate'], function(){
            $ = layui.jquery;
          var form = layui.form
          ,layer = layui.layer
          ,laydate=layui.laydate;
        	
        	
          //日期
          laydate.render({
    		elem: '#date1'
  		});
        laydate.render({
    		elem: '#date2'
  		});
          //自定义验证规则
          form.verify({
            nikename: function(value){
              if(value.length < 3){
                return '请至少添加3个字符';
              }
            }
            ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,repass: function(value){
                if($('#AccountPassword').val()!=$('#ReAccountPassword').val()){
                    return '两次密码不一致';
                }
            }
          }); 
          //监听提交
          form.on('submit(add)', function(data){
            console.log(data);
            //发异步，把数据提交给php
            console.log(data);
            $.ajax({
        		type : "POST",
        		data : $('#form1').serialize(),
        		dataType : "text",
        		url :"staffController/staffAdd.ajax",
        		success : function(result) {
        		 //发异步，把数据提交给php
                 layer.msg("添加成功",{
              		icon : 1,
               		time: 500,
               	},function () {
                     // 获得frame索引
                     var index = parent.layer.getFrameIndex(window.name);
                     //关闭当前frame
                     parent.location.reload();
                     parent.layer.close(index);
                 });
        		},
        		error : function() {
        			layer.msg('无法连接服务器', {icon: 2});
        		}
        	});   
            return false;
          });
          });
          
          
        
    </script>
    <script>var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
      })();</script>
  </body>

</html>
