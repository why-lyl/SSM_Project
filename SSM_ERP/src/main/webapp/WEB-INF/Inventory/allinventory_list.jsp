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
<title>库存资产</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/font.css">
<link rel="stylesheet" href="css/xadmin.css">
<link rel="stylesheet" href="lib/layui/css/layui.css">
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/xadmin.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<script type="text/javascript">
var userDepart=null;
var EntryStart=null;
var EntryEnd=null;
var birthRange=null;
var productName=null;
var total=0;
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
				productName:productName,
				currentPage:n
			},
			dataType : "text",
			url : "inventoryController/selectProduct.ajax",
			success : function(result) {
				console.log("从后台拿到的值"+result);
				//alert(result);
				var tl = eval("(" + result + ")");
				if(n==-1){
					total=tl.total;
					$("#total").text(total);
				}
				$("#products").html("");
				//json遍历
				if(tl.list.length>0){
					$.each(tl.list, function(n,val){
						/* console.log(val.userEntrytime);
						console.log(typeof(val.userEntrytime)); 
						
					   
					    */
						var str="";
					    str+="<tr>";
					    str+="<td>"+val.productName+"</td>";
					    str+="<td>"+val.productType+"</td>";
					    str+="<td>"+val.productSort+"</td>";
					    str+="<td>"+val.productAmount+"</td>";
					    str+="<td>"+val.productPrice+"</td>";
					    str+="<td>"+val.productTotal+"</td>";
					   	str+="<td><a class=\"layui-btn  layui-btn-mini\" onclick=\"x_admin_show('职工信息修改','productController/goUserEdit.do?productId="+val.productId+"')\" ><i class=\"layui-icon\">&#xe642;</i>编辑</a> <button onclick=\"delStaff('"+val.productId+"')\" class=\"layui-btn  layui-btn-mini layui-btn-danger\"><i class=\"layui-icon\">&#xe640;</i>删除</button></td>"
					    str+="</tr>";
						$("#products").append(str);
					})
				}else{
					$("#products").append("<tr><td colspan=8 align=\"center\">暂时没有数据哦，快去添加一条吧</td></tr>");
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
<body >
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a>库存管理</a> <a> <cite>资产情况</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="inventoryController/goAllInventory.do" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<form class="layui-form layui-col-md12 x-so" method="post"
				action="inventoryController/goAllInventory.do">
					<span>快速查询：</span>
					<div class="layui-input-inline">
						<select name="bigtypeName" id="quiz3" lay-filter="myselect2">
							<option value="" selected="selected">请选择类型</option>
							<option value="固定资产">固定资产</option>
							<option value="非实物资产">非实物资产</option>
							<option value="低值易耗损品">低值易耗损品</option>
						</select>
					</div>
					<div class="layui-input-inline">
						<select name="malltypeName" id="quiz4" lay-filter="myselect3">
							<option value="" selected="selected">请选择分类</option>
							<option value="固定资产">固定资产</option>
							<option value="非实物资产">非实物资产</option>
							<option value="低值易耗损品">低值易耗损品</option>
						</select>
					</div>
					<div class="layui-input-inline">
						<select name="propertyName" id="quiz1" lay-filter="myselect">
							<option value="" selected="selected">请选择资产名字</option>
						</select>
					</div>
					<div class="layui-input-inline">
						<select name="type" id="quiz2">
							<option value="">请选择型号</option>
							<option value="杭州">杭州</option>
							<option value="宁波" disabled="">宁波</option>
							<option value="温州">温州</option>
							<option value="温州">台州</option>
							<option value="温州">绍兴</option>
						</select>
					</div>
						<button type="submit" class="layui-btn" lay-submit="sreach()"
							lay-filter="sreach">
							<i class="layui-icon">&#xe615;</i>
						</button>

			</form>
		</div>

		<xblock>
		<button class="layui-btn" onclick="addProduct();">
			<i class="layui-icon">&#xe608;</i>新增资产
		</button>
		<span class="x-right" style="line-height: 40px">共有数据：<a id="total">88</a> 
			条</span> </xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th>资产名称</th>
					<!-- <th>型号</th> -->
					<th>类型</th>
					<th>分类</th>
					<th>资产数量</th>
					<th>资产单价</th>
					<th>资产总价</th>
					<th>操作</th>
				</tr>
			</thead>
			
			<tbody id ="products">
			
			
				
			</tbody>
		</table>
		<div id="test1" style="text-align:center"></div>

        </div>
	<script>
		/*一級 联动下拉框 */
		layui.use(['layer', 'form'], function(){
			  var layer = layui.layer
			  ,form = layui.form;
			  form.on('select(myselect2)', function(data){
				 var bigtypeName=$("#quiz3").val();
			  $.ajax({
	                 type: 'POST',
	                 url: 'propertyController/returnMalltypeName.ajax',
	                 data: {
	                	 "bigtypeName" : bigtypeName
	                 },
	                 dataType:  'json',
	                 success: function(allproperty){
	                		var str = "";
	    					$("#quiz4").empty();
	    					str += "<option>请选择分类</option>"
	    					$("#quiz4").html(str);

	    					for (var i = 0; i < allproperty.length; i++) {
	    						var property = allproperty[i];
	    						$("#quiz4").append("<option >" + property.malltypeName + "</option>");
	    						form.render('select');
	    						 $("#quiz4").get(0).selectedIndex=0;
	    					}
	                 },
	                 error : function(msg) {
		    				alert("无法连接服务器");
		    			}   
	         }); 
	});
		});
		/*二級 联动下拉框 */
		layui.use(['layer', 'form'], function(){
			  var layer = layui.layer
			  ,form = layui.form;
			  form.on('select(myselect3)', function(data){
				 var malltypeName=$("#quiz4").val();
			  $.ajax({
	                 type: 'POST',
	                 url: 'propertyController/returnPropertyName.ajax',
	                 data: {
	                	 "malltypeName" : malltypeName
	                 },
	                 dataType:  'json',
	                 success: function(allproperty){
	                		var str = "";
	    					$("#quiz1").empty();
	    					str += "<option>请选择资产名字</option>"
	    					$("#quiz1").html(str);

	    					for (var i = 0; i < allproperty.length; i++) {
	    						var property = allproperty[i];
	    						$("#quiz1").append("<option >" + property.propertyName + "</option>");
	    						form.render('select');
	    						 $("#quiz1").get(0).selectedIndex=0;
	    					}
	                 },
	                 error : function(msg) {
		    				alert("无法连接服务器");
		    			}   
	         }); 
	});
	
		});
		/*三級 联动下拉框 */
		layui.use(['layer', 'form'], function(){
			  var layer = layui.layer
			  ,form = layui.form;
			  form.on('select(myselect)', function(data){
				 var propertyName=$("#quiz1").val();
			  $.ajax({
	                 type: 'POST',
	                 url: 'applyController/returnType.do',
	                 data: {
	                	 "propertyName" : propertyName
	                 },
	                 dataType:  'json',
	                 success: function(allproperty){
	                		var str = "";
	    					$("#quiz2").empty();
	    					str += "<option>请选择型号</option>"
	    					$("#quiz2").html(str);

	    					for (var i = 0; i < allproperty.length; i++) {
	    						var property = allproperty[i];
	    						$("#quiz2").append("<option >" + property.type + "</option>");
	    						form.render('select');
	    						 $("#quiz2").get(0).selectedIndex=0;
	    					}
	                 },
	                 error : function(msg) {
		    				alert("无法连接服务器");
		    			}   
	         }); 
	});
	
		});


		/*资产-删除*/
		function member_del(obj, productId) {
			layer.confirm('确认要删除吗？', function(index) {
				//发异步删除数据
				layer.msg('已删除!', {
					icon : 1,
					time : 500
				});
				window.location.href = "propertyController/deleteProperty.do?productId="+productId+"";
				
			});
		}
		function addProduct() {
				window.location.href = "inventoryController/goAddProduct.do";
		}

	</script>
	
</body>

</html>