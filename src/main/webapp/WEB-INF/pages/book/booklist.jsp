<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>图书列表</title>
<link rel="stylesheet" type="text/css"
	href="${base }/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${base }/css/superadmin.css" />
<link rel="stylesheet" type="text/css"
	href="${base }/css/bootstrapValidator.min.css">
<script type="text/javascript" src="${base }/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript"
	src="${base }/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${base }/bootstrap/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${base }/js/bootstrapValidator.min.js"></script>
<script type="text/javascript"
	src="${base }/layer-v3.1.1/layer/layer.js"></script>
</head>
<body>
	<c:if test="${update1 gt '0' }">
		<script type="text/javascript">
		layer.msg('修改成功',{anim:1,offset:'100px',area:'100px', icon:1, time:1000});
		</script>
	</c:if>
	<c:if test="${delete1 gt '0' }">
		<script type="text/javascript">
		layer.msg('删除成功',{anim:1,offset:'100px',area:'100px', icon:1, time:1000});
		</script>
	</c:if>
	<c:if test="${add1 gt '0' }">
		<script type="text/javascript">
		layer.msg('添加成功',{anim:1,offset:'100px',area:'100px', icon:1, time:1000});
		</script>
	</c:if>

	<div style="margin-top: 5px; width: 98%; margin: 5px auto 0 auto">
		<%@ include file="/WEB-INF/pages/common/head.jsp"%>

		<div class="nav">
			<ol class="breadcrumb">
				<li><a class="text-success">当前位置&nbsp;<i
						class="fa fa-hand-o-right">&nbsp;</i>&nbsp;图书列表
				</a></li>
			</ol>
		</div>
		<!--图书查询-->
		<div class="nav navbar-default">
			<div class="navbar-header">
				<a class="navbar-brand" href="${base }/permission/book/all">所有图书</a>
			</div>
			<form class="navbar-form navbar-left" role="form" method="post"
				action="${base }/permission/user/select">
				<input type="text" name="no" class="form-control"
					placeholder="请输入图书isbn" />
				<button type="submit" class="btn btn-search">
					查询 <span class="glyphicon glyphicon-search"></span>
				</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li style="padding-right: 15px;"><a data-toggle="collapse"
					data-parent="#accordion" href="#collapse">更多查询条件</a></li>
			</ul>
		</div>
		<!--图书查询end-->
		<!--更多查询条件-->
		<div id="collapse" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="form-group">
					<div>
						<form class="form-horizontal" method="post"
							action="${base }/permission/user/selectbycondition">
							<div class="form-group has-feedback">

								<div class="col-md-offset-1 col-md-1" style="padding-top: 5px;">
									创建时间:</div>
								<div class="col-md-3">
									<input type="text" name="startdate" class="form-control"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:false})"
										placeholder="开始时间"> <span
										class="glyphicon glyphicon-time form-control-feedback"></span>
								</div>
								<div class="col-md-1"
									style="padding-top: 5px; text-align: center;">至</div>
								<div class="col-md-3">
									<input type="text" name="enddate" class="form-control"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:false})"
										placeholder="结束日期"> <span
										class="glyphicon glyphicon-time form-control-feedback"></span>
								</div>

							</div>
							<div class="form-group has-feedback">
								<div class="col-md-offset-1 col-md-1" style="padding-top: 5px;">
									手机号码:</div>
								<div class="col-md-3">
									<input type="text" name="phone" class="form-control"
										placeholder="请输入手机号码" />
								</div>
								<div class="col-md-1"
									style="padding-top: 5px; text-align: center;">状态</div>
								<div class="col-md-3">
									<select class="form-control" name="status">
										<option value="正常">正常</option>
										<option value="禁用">禁用</option>
									</select>
								</div>

							</div>
							<div class="form-group has-feedback">
								<div class="col-md-offset-1 col-md-1" style="padding-top: 5px;">
									真实姓名:</div>
								<div class="col-md-3">
									<input type="text" name="realname" class="form-control"
										placeholder="请输入真实姓名" />
								</div>

								<div class="col-md-1"
									style="padding-top: 5px; text-align: center; font-size: small">创建管理员</div>
								<div class="col-md-3">
									<input type="text" name="adminname" class="form-control"
										placeholder="请输入管理员名" />
								</div>

								<div class="col-md-3">
									<button type="submit" class="btn btn-default">
										查询&nbsp;&nbsp;<span class="glyphicon glyphicon-search"></span>
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--更多查询条件end-->
		<table class="table table-hover table-striped">
			<tr class="active">
				<th>id</th>
				<th>图书状态</th>
				<th>isbn</th>
				<th>图书名称</th>
				<th>作者</th>
				<th>出版社</th>
				<th>数量</th>
				<th>剩余数量</th>
				<th>上架日期</th>
				<th>类别</th>
				<th>书架</th>
				<th>上架管理员</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${books }" var="item" varStatus="vs">
				<tr>
					<td style="vertical-align: middle;">${item.id }</td>
					<td
						<c:if test="${item.bookstatus == '正常' }" var="normal">style="vertical-align: middle;color:green"</c:if>
						<c:if test="${! normal }">style="vertical-align: middle;color:red"</c:if>>${item.bookstatus }</td>
					<td style="vertical-align: middle;">${item.isbn }</td>
					<td style="vertical-align: middle;">${item.name }</td>
					<td style="vertical-align: middle;">${item.author }</td>
					<td style="vertical-align: middle;">${item.publishhouse }</td>
					<td style="vertical-align: middle;">${item.count }</td>
					<td style="vertical-align: middle;">${item.restcount }</td>
					<td style="vertical-align: middle;"><fmt:formatDate
							value="${item.ondate }" pattern="yyyy-MM-dd" /></td>
					<td style="vertical-align: middle;">${item.category.name }</td>
					<td style="vertical-align: middle;">${item.category.location }</td>
					<td style="vertical-align: middle;">${item.admin.name }</td>
					<td>
						<div class="btn-group">
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">
								操作 <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="${base }/permission/book/show?bid=${item.id}">修改</a></li>
								<li><a href="#" onclick="return del(${item.id})">删除</a></li>
								<li class="divider"></li>
								<c:if
									test="${item.bookstatus == '正常' || item.bookstatus == '已借完' }">
									<li><a href="#" onclick="return down(${item.id})">下架</a></li>
								</c:if>
								<c:if test="${item.bookstatus == '正常' }">
									<li><a href="${base }/permission/lend/readd?bid=${item.id}">借出</a></li>
								</c:if>
							</ul>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>

<script type="text/javascript">
	function del(id){
		layer.confirm('确认要删除吗',{icon:3,title:'提示',offset:'100px'},function (index){
			window.location.href="/BookSystem/permission/book/delete?bid="+id;
			layer.close(index);
		});
	};
	function down(id){
		layer.confirm('确定要下架吗',{icon:3,title:'提示',offset:'100px'},function (index){
			layer.confirm('下架之后不能上架，还要继续下架吗',{icon:3,title:'提示',offset:'100px'},function (index1){
				window.location.href="/BookSystem/permission/book/down?bid="+id;
				layer.close(index1);
			});
			layer.close(index);
		});
	};
	
</script>

</html>