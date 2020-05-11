<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>采购信息</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="lib/layui/css/layui.css">
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
</head>
<script type="text/javascript">
var userDepart=null;
var EntryStart=null;
var EntryEnd=null;
var birthRange=null;
var purchaseName=null;
var total=0;
function disagree(applyStatus){//这里applyStatus是通过遍历时产生的删除按钮那里得到的值，可以进行名字的更改
	//alert(applyStatus);
	layer.msg('确实不采纳申请吗？', {
		  time: 0 //不自动关闭
		  ,btn: ['确定','取消']
		  ,yes: function(index){
			  $.ajax({
					type : "POST",
					async:false,
					data : {
						applyStatus:applyStatus
					},
					dataType : "text",
					url : "purchaseController/disagreeApply.ajax",
					success : function(result) {
						console.log(result);
						layer.msg("操作成功",{
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
function agree(applyStatus){//这里applyStatus是通过遍历时产生的删除按钮那里得到的值，可以进行名字的更改
	//alert(purchaseId);
	//alert(applyStatus);
	layer.msg('确实同意申请吗？', {
		  time: 0 //不自动关闭
		  ,btn: ['确定','取消']
		  ,yes: function(index){
			  $.ajax({
					type : "POST",
					async:false,
					data : {
						applyStatus:applyStatus,
					},
					dataType : "text",
					url : "purchaseController/agreeApply.ajax",
					success : function(result) {
						console.log(result);
						layer.msg("操作成功",{
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
	 //分页的ajax
	  function showPage(n) {
		$.ajax({
			type : "POST",
			async:false,
			data : {
				userDepart:userDepart,
				EntryStart:EntryStart,
				EntryEnd:EntryEnd,
				birthRange:birthRange,
				purchaseName:purchaseName,
				currentPage:n
			},
			dataType : "text",
			url : "purchaseController/selectPurchase.ajax",
			success : function(result) {
				console.log("从后台拿到的值"+result);
				//alert(result);
				var tl = eval("(" + result + ")");
				if(n==-1){
					total=tl.total;
					$("#total").text(total);
				}
				$("#purchases").html("");
				//json遍历
				if(tl.list.length>0){
					$.each(tl.list, function(n,val){
						/*  <td><i class=\"layui-icon\"></i>不采纳</td>"
						 <span class="layui-btn layui-btn-normal layui-btn-mini">  </span> */
						var str="";
					    var applyTime = new Date(val.applyTime).format("yyyy-MM-dd");
						var purchaseTime = new Date(val.purchaseTime).format("yyyy-MM-dd");
						var applyStatus = "<td></td>";
						var applyStatusValue = val.applyStatus;
						 if (applyStatusValue == "同意") {
							applyStatus = "<td class='td-status'><span class='layui-btn layui-btn-normal layui-btn-mini'>同意申请</span></td>"
						} 
						if (applyStatusValue == null) {
								applyStatus = "<td class='td-status'><span class='layui-btn layui-btn-normal layui-btn-mini'>审核中</span></td>"
						} 
						if (applyStatusValue == "不采纳") {
							applyStatus = "<td class='td-status'><span class='layui-btn layui-btn-normal layui-btn-mini layui-btn-disabled'>不采纳</span></td>"
					} 
					    str+="<tr>";
					    str+="<td>"+val.purchaseName+"</td>";
					    str+="<td>"+val.purchaseSort+"</td>";
					    str+="<td>"+val.purchaseAmount+"</td>";
					    str+="<td>"+val.purchasePrice+"</td>";
					    str+="<td>"+val.purchaseTotal+"</td>";
					    str+="<td>"+val.purchaseStaff+"</td>";
					    /* str+="<td>"+applyStatus+"</td>"; */
					    //layui-btn layui-btn-normal layui-btn-mini layui-btn-disabled
					    //layui-btn layui-btn-normal layui-btn-mini
						str+= applyStatus;
					    str+="<td>"+applyTime+"</td>";
					    str+="<td>"+purchaseTime+"</td>";
					   	str+="<td><a class=\"layui-btn  layui-btn-mini\" onclick=\"agree('"+val.applyStatus+','+val.purchaseId+"')\" class=\"layui-btn  layui-btn-mini layui-btn-danger\"><i class=\"layui-icon\"></i>同意</button></a><button onclick=\"disagree('"+val.applyStatus+','+val.purchaseId+"')\" class=\"layui-btn  layui-btn-mini layui-btn-danger\"><i class=\"layui-icon\"></i>不采纳</button></td>"
					    str+="</tr>";
						$("#purchases").append(str);
					})
				}else{
					$("#purchases").append("<tr><td colspan=8 align=\"center\">暂时没有数据哦，快去添加一条吧</td></tr>");
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
	</script>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>采购管理</a> <a> <cite>采购信息</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="purchaseController/goPurchase.do" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<form class="layui-form layui-col-md12 x-so" method="post" action="porchaseController/showPorchase.do">
				<span>快速查询：</span> <input class="layui-input" placeholder="请输入名称"
					name="propertyName"> <input type="text" name="userName"
					placeholder="请输入申请人姓名" autocomplete="off" class="layui-input">
				<button type="submit" class="layui-btn" lay-submit="sreach()" lay-filter="sreach">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</form>
		</div>
		<xblock>
		<button class="layui-btn" onclick="x_admin_show('采购申请','purchaseController/goPurchaseAdd.do')"><i class="layui-icon"></i>采购申请</button>
		<span class="x-right" style="line-height: 40px">共有数据：<a id="total">88</a>条</span> </xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th>资产名称</th>
					<th>物品类型</th>
					<th>物品数量</th>
					<th>物品单价</th>
					<th>申请总资金</th>
					<th>申请人</th>
					<th>审核状态</th>
					<th>申请时间</th>
					<th>采购时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id = "purchases">
				
			</tbody>
		</table>
	<div id="test1" style="text-align:center"></div>

    </div>
	<script>
		/*用户-控制*/
		function member_stop(obj,porchaseId) {
			layer.confirm('确认要停用/启用该采购吗？', function(index) {
				if ($(obj).attr('title') == '启用') {
					//发异步把用户状态进行更改
					$(obj).attr('title', '未批准')
					$(obj).find('i').html('&#xe626;');
					$(obj).parents("tr").find(".td-status").find('span')
							.addClass('layui-btn-disabled').html('未批准');
					layer.msg('停用成功!', {
						icon : 1,
						time : 500
					});
					
					$.ajax({
						type:"post",
						url:"porchaseController/updateState.ajax",
						data:{
							porchaseId:porchaseId
						},
						datatype:"text",
						success:function(result){
							
						},
						error:function(){
							alert("无法连接服务器");
						}
					});
				} else {
					$(obj).attr('title', '启用')
					$(obj).find('i').html('&#xe627;');
					$(obj).parents("tr").find(".td-status").find('span')
							.removeClass('layui-btn-disabled').html('已启用');
					layer.msg('启用成功!', {
						icon : 1,
						time : 500
					});
					
					$.ajax({
						type:"post",
						url:"porchaseController/updateStateDo.ajax",
						data:{
							porchaseId:porchaseId
						},
						datatype:"text",
						success:function(result){
							
						},
						error:function(){
							alert("无法连接服务器");
						}
					});
				}

			});
		} 

		/*用户-删除*/
		function member_del(obj, porchaseId) {
			layer.confirm('确认要删除吗？', function(index) {
				//发异步删除数据
				layer.msg('已删除!', {
					icon : 1,
					time : 500
				});
				window.location.href = "porchaseController/deletePorchase.do?porchaseId="+porchaseId+"";
				
			});
		}

		
		
	
	</script>
</body>
</html>