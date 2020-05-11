<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
</head>
<body>
   <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">职工管理</a>
        <a>
          <cite>用户管理</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
          <div class="layui-input-inline">
            <select id="userDepart">
              <option value="">员工所在部门</option>
              <c:forEach items="${departs}" var="department">
              		<option value="${department.departmentName}">${department.departmentName} </option>
              </c:forEach>
              
            </select>
          </div>
          <div class="layui-input-inline">
            <input id="entry" type="text" name="staffJoin"  placeholder="入职时间" autocomplete="off" class="layui-input">
          </div>
          <div class="layui-input-inline">
            <select id="birthRange">
            	<option value="">员工生日</option>
            	<option value="30">最近一个月</option>
            	<option value="90">最近三个月 </option>
            	<option value="180">最近半年</option>
            </select>
          </div>
          <div class="layui-input-inline">
           <input type="text" id="staffName"  placeholder="员工姓名" autocomplete="off" class="layui-input">
          </div>
          
          <a class="layui-btn"  lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></a>
        </form>
      </div>
      <xblock>
        <button class="layui-btn" onclick="x_admin_show('添加职工','staffController/goUserAdd.do')"><i class="layui-icon"></i>入职添加</button>
        <span class="x-right" style="line-height:40px">共有数据：<a id="total">88</a> 条</span>
      </xblock>
      <table class="layui-table">
        <thead>
          <tr>
            <th>姓名</th>
            <th>性别</th>
            <th>所属部门</th>
            <!-- <th>职位</th> -->
            <th>入职时间</th>
            <th>生日</th>
           <!--  <th>年龄</th> -->
            <th>登录账号</th>
            <th>电话号码</th>
            <th>邮箱地址</th>
            <th >操作</th>
            </tr>
        </thead>
        <tbody id="staffs">
          <tr>
          
            <td colspan=8> 加载中<i class="layui-icon" >&#xe63e;</i></td>
          </tr>
        </tbody>
      </table>
      <div id="test1" style="text-align:center"></div>

    </div>

	</body>
<script type="text/javascript">

var userDepart=null;
var EntryStart=null;
var EntryEnd=null;
var birthRange=null;
var staffName=null;
var total=0;
//删除操作
function delStaff(staffId){//这里staffId是通过遍历时产生的删除按钮那里得到的值，可以进行名字的更改
	//alert(staffId);
	layer.msg('确定删除吗？', {
		  time: 0 //不自动关闭
		  ,btn: ['确定删除','取消返回']
		  ,yes: function(index){
			  $.ajax({
					type : "POST",
					async:false,
					data : {
						staffId:staffId
					},
					dataType : "text",
					url : "staffController/staffDel.ajax",
					success : function(result) {
						console.log(result);
						layer.msg("删除成功",{
		              		icon : 1,
		               		time: 500,
		               	},function () {
		               		location.replace(location.href)
		                    
		                 });
						
					},
					error : function() {
						
						layer.msg('无法连接服务器', {icon: 2});
					}
				});
		  }
		});
}
layui.use(['form', 'laypage', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
 ,laypage = layui.laypage
  ,laydate = layui.laydate;
  
  //日期
 var start=null;
 var end=null;
 laydate.render({
  elem: '#entry'
  ,range:true
  ,done: function(value, startDate, endDate){
    console.log(value); //得到日期生成的值，如：2017-08-18
    start=startDate.year+"-"+startDate.month+"-"+startDate.date;
    end=endDate.year+"-"+endDate.month+"-"+endDate.date;
    console.log(start) //得到间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
    console.log(end); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
  }
});

 
//自定义验证规则
form.verify({
    title: function(value){
      if(value.length < 5){
        return '标题至少得5个字符啊';
      }
    }
    ,pass: [/(.+){6,12}$/, '密码必须6到12位']
    ,content: function(value){
      layedit.sync(editIndex);
    }
}); 
//查询分页（功能暂不完善,暂时使用姓名查询）
  form.on('submit(search)', function(list){
      console.log("search数据"+list);
      userDepart=$("#userDepart").val();
      birthRange=$("#birthRange").val();
      staffName=$("#staffName").val();
    	EntryStart=start;
    	EntryEnd=end;
       showStaffPage(-1);
       laypage.render({
    		  elem: 'test1'
    		  ,count: total //数据总数，从服务端得到
    		  ,limit:8
    		  ,jump: function(obj, first){
    		    //obj包含了当前分页的所有参数，比如：
    		    console.log("search当前页"+obj.curr); //得到当前页，以便向服务端请求对应页的数据。
    		    console.log("search每页显示的条数"+obj.limit); //得到每页显示的条数
    		    //首次不执行
    		    if(!first){
    		    	showStaffPage(obj.curr);
    		    }
    		  }
    		});
            function showStaffPage(n) {
    		$.ajax({
    			type : "POST",
    			async:false,
    			data : {
    				userDepart:userDepart,
    				EntryStart:EntryStart,
    				EntryEnd:EntryEnd,
    				birthRange:birthRange,
    				staffName:staffName,
    				currentPage:n
    			},
    			dataType : "text",
    			url : "staffController/selectStaffByStaffName.ajax",
    			success : function(result) {
    				console.log("从后台拿到的值"+result);
    				//alert(result);
    				var tl = eval("(" + result + ")");
    				if(n==-1){
    					total=tl.total;
    					$("#total").text(total);
    				}
    				$("#staffs").html("");
    				//json遍历
    				if(tl.list.length>0){
    					$.each(tl.list, function(n,val){
    						var str="";
    						var jionTime = new Date(val.staffJoin).format("yyyy-MM-dd");
    						var birthday = new Date(val.staffBirthday).format("yyyy-MM-dd");
    						var staffName = val.staffName;
    						var staffSex = val.staffSex;
    						var staffDepart = val.staffDepart;
    						var staffTel = val.staffTel;
    						var staffEmail = val.staffEmail;
    						//alert(jionTime);
    						if (jionTime == "NaN-aN-aN") {
    							jionTime = "未入职";
    						}
    						//alert(jionTime);
    						if (birthday == "NaN-aN-aN") {
    							birthday = "未填写生日";
    						}
    						if (staffName == null) {
    							   staffName = "未入职";
    							}
    						//alert(staffName);
    						if (staffSex == null) {
    							staffSex = "未入职";
    							}
    						if (staffDepart == null) {
    							staffDepart = "未入职";
    							}
    						if (staffTel == null) {
    							staffTel = "未入职";
    							}
    						if (staffEmail == null) {
    							staffEmail = "未入职";
    							}
    						//alert(typeof(jionTime));
    					    str+="<tr>";
    					    str+="<td>"+staffName+"</td>";
    					    str+="<td>"+staffSex+"</td>";
    					    str+="<td>"+staffDepart+"</td>";
    					    str+="<td>"+jionTime+"</td>";
    					    str+="<td>"+birthday+"</td>";
    					   /*  str+="<td>"+val.staffAge+"</td>"; */
    					    str+="<td>"+val.accountId+"</td>";
    					    str+="<td>"+staffTel+"</td>";
    					    str+="<td>"+staffEmail+"</td>";
    					    str+="<td><a class=\"layui-btn  layui-btn-mini\" onclick=\"x_admin_show('职工信息修改','staffController/goUserEdit.do?staffId="+val.staffId+"')\" ><i class=\"layui-icon\">&#xe642;</i>编辑</a> <button onclick=\"delStaff('"+val.staffId+"')\" class=\"layui-btn  layui-btn-mini layui-btn-danger\"><i class=\"layui-icon\">&#xe640;</i>删除</button></td>"
    					    str+="</tr>";
    						$("#staffs").append(str);
    					})
    				}else{
    					$("#staffs").append("<tr><td colspan=8 align=\"center\">暂时没有数据哦，快去添加一条吧</td></tr>");
    				}
    				
    			},
    			error : function() {
    				layer.msg('无法连接服务器', {icon: 2});
    			}
    		});
    	}
      return false;
    });
  
  //监听提交
  form.on('submit(demo1)', function(data){
    layer.alert(JSON.stringify(data.field), {
      title: '最终的提交信息'
    })
    return false;
  });
  //分页的ajax
  function showPage(n) {
	$.ajax({
		type : "POST",
		async:false,
		data : {
			currentPage:n
		},
		dataType : "text",
		url : "staffController/selectStaff.ajax",
		success : function(result) {
			console.log("从后台拿到的值"+result);
			//alert(result);
			var tl = eval("(" + result + ")");
			if(n==-1){
				total=tl.total;
				$("#total").text(total);
			}
			$("#staffs").html("");
			//json遍历
			if(tl.list.length>0){
				$.each(tl.list, function(n,val){
					var str="";
					var jionTime = new Date(val.staffJoin).format("yyyy-MM-dd");
					var birthday = new Date(val.staffBirthday).format("yyyy-MM-dd");
					var staffName = val.staffName;
					var staffSex = val.staffSex;
					var staffDepart = val.staffDepart;
					var staffTel = val.staffTel;
					var staffEmail = val.staffEmail;
					//alert(staffName);
					if (jionTime == "NaN-aN-aN") {
						jionTime = "未入职";
					}
					if (birthday == "NaN-aN-aN") {
						birthday = "未填写生日";
					}
					if (staffName == null) {
						   staffName = "未入职";
						}
					//alert(staffName);
					if (staffSex == null) {
						staffSex = "未入职";
						}
					if (staffDepart == null) {
						staffDepart = "未入职";
						}
					if (staffTel == null) {
						staffTel = "未入职";
						}
					if (staffEmail == null) {
						staffEmail = "未入职";
						}
					//alert(typeof(jionTime));
				    str+="<tr>";
				    str+="<td>"+staffName+"</td>";
				    str+="<td>"+staffSex+"</td>";
				    str+="<td>"+staffDepart+"</td>";
				    str+="<td>"+jionTime+"</td>";
				    str+="<td>"+birthday+"</td>";
				   /*  str+="<td>"+val.staffAge+"</td>"; */
				    str+="<td>"+val.accountId+"</td>";
				    str+="<td>"+staffTel+"</td>";
				    str+="<td>"+staffEmail+"</td>";
				    str+="<td><a class=\"layui-btn  layui-btn-mini\" onclick=\"x_admin_show('职工信息修改','staffController/goUserEdit.do?staffId="+val.staffId+"')\" ><i class=\"layui-icon\">&#xe642;</i>编辑</a> <button onclick=\"delStaff('"+val.staffId+"')\" class=\"layui-btn  layui-btn-mini layui-btn-danger\"><i class=\"layui-icon\">&#xe640;</i>删除</button></td>"
				    str+="</tr>";
					$("#staffs").append(str);
				})
			}else{
				$("#staffs").append("<tr><td colspan=8 align=\"center\">暂时没有数据哦，快去添加一条吧</td></tr>");
			}
			
		},
		error : function() {
			layer.msg('无法连接服务器', {icon: 2});
		}
	});
}
  //分页,展示的时候分页
  showPage(-1);
  laypage.render({
	  elem: 'test1'
	  ,count: total //数据总数，从服务端得到
	  ,limit: 8 //这是理论上每页展示的条数，前后端要一致，否者，会多出空白页，或者展示不全
	  ,jump: function(obj, first){
	    //obj包含了当前分页的所有参数，比如：
	    console.log("分页展示当前页"+obj.curr); //得到当前页，以便向服务端请求对应页的数据。
	    console.log("分页展示显示条数"+obj.limit); //得到每页显示的条数
	    
	    //首次不执行
	    if(!first){
	    	showPage(obj.curr);
	    }
	  }
	});
});
function test() {
	$.ajax({
		type : "POST",
		async:false,
		data : {
			index:null
		},
		dataType : "text",
		url : "roleController/getUserRole.ajax",
		success : function(result) {
			alert(result);
			console.log(result);
		},
		error : function() {
			layer.msg('无法连接服务器', {icon: 2});
		}
	});
}
</script>
</html>