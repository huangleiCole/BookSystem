<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>用户列表</title>
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

	<c:if test="${update2 gt '0' }">
		<script type="text/javascript">
			layer.msg('密码修改成功,请重新登录',{anim:1,offset:'100px',area:'240px',shade:[1,'#e2e2e2'], icon:6, time:2000},function (index){
				window.location.href="/BookSystem/admin/logout";
			});
		</script>
	</c:if>
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

	<form id="form1" method="post" action="${base }/admin/updatepassword">
		<div class="modal fade" id="myModal">
			<div class="modal-dialog">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h3 class="panel-title">您的密码过于简单,请修改密码</h3>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<label for="adminName">管理员名</label> <input type="text"
								id="adminName" name="newname" value="${admin.name }"
								disabled="disabled" class="form-control" />
						</div>
						<div class="form-group">
							<label for="adminPassword">新 密 码</label> <input type="password"
								id="adminPassword" class="form-control" name="newpassword"
								placeholder="请输入密码" />
						</div>
						<div class="form-group">
							<label for="rePassword">确认密码</label><input type="password"
								id="rePassword" class="form-control" name="repassword"
								placeholder="请再次输入密码" />
						</div>
					</div>
					<div class="panel-footer">
						<button type="submit" class="btn btn-info form-control">修改</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<c:if test="${isDefault == 'true' }">
		<script type="text/javascript">
	$('#myModal').modal({
		keyboard : true,
		backdrop : 'static'
	});
	</script>
	</c:if>
	<div style="margin-top: 5px; width: 98%; margin: 5px auto 0 auto">
		<%@ include file="/WEB-INF/pages/common/head.jsp"%>

		<div class="nav">
			<ol class="breadcrumb">
				<li><a class="text-success">当前位置&nbsp;<i
						class="fa fa-hand-o-right">&nbsp;</i>&nbsp;用户列表
				</a></li>
			</ol>
		</div>
		<!--用户查询-->
		<div class="nav navbar-default">
			<div class="navbar-header">
				<a class="navbar-brand" href="${base }/permission/user/all">所有用户</a>
			</div>
			<form class="navbar-form navbar-left" role="form" method="post"
				action="${base }/permission/user/select">
				<input type="text" name="no" class="form-control"
					placeholder="请输入用户编号" />
				<button type="submit" class="btn btn-search">
					查询 <span class="glyphicon glyphicon-search"></span>
				</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li style="padding-right: 15px;"><a data-toggle="collapse"
					data-parent="#accordion" href="#collapse">更多查询条件</a></li>
			</ul>
		</div>
		<!--用户查询end-->
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
				<th>状态</th>
				<th>用户编号</th>
				<th>真实姓名</th>
				<th>联系电话</th>
				<th>创建日期</th>
				<th>创建管理员</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${users }" var="item" varStatus="vs">
				<tr>
					<td style="vertical-align: middle;">${item.id }</td>
					<td
						<c:if test="${item.status == '正常' }" var="normal">style="vertical-align: middle;color:green"</c:if>
						<c:if test="${! normal }">style="vertical-align: middle;color:red"</c:if>>${item.status }</td>
					<td style="vertical-align: middle;">${item.no }</td>
					<td style="vertical-align: middle;">${item.realname }</td>
					<td style="vertical-align: middle;">${item.phone }</td>
					<td style="vertical-align: middle;"><fmt:formatDate
							value="${item.createdate }" pattern="yyyy-MM-dd HH:mm:ss EEE" />
					</td>
					<td style="vertical-align: middle;">${item.admin.name }</td>

					<td>
						<div class="btn-group">
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">
								操作 <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="${base }/permission/user/show?uid=${item.id}">修改</a></li>
								<c:if test="${item.status == '禁用' }">
									<li><a href="${base }/permission/user/able?uid=${item.id}">恢复使用</a></li>
								</c:if>
								<c:if test="${item.status == '正常' }">
									<li><a
										href="${base }/permission/user/disable?uid=${item.id}">禁用</a></li>
								</c:if>
								<li><a href="#" onclick="return del(${item.id})">删除</a></li>
								<c:if test="${item.status == '正常' }">
									<li><a href="${base }/permission/lend/ureadd?uid=${item.id}">借书</a></li>
								</c:if>

								<li><a href="#">还书</a></li>
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
			window.location.href="/BookSystem/permission/user/delete?uid="+id;
			layer.close(index);
		});
	};
	$(document)
	.ready(
			function() {
				$("#form1")
						.bootstrapValidator(
								{
									message : 'This value is not valid',

									feedbackIcons : {
										valid : 'glyphicon glyphicon-ok',
										invalid : 'glyphicon glyphicon-remove',
										validating : 'glyphicon glyphicon-refresh'
									},
									fields : {
										newpassword : {
											message : '密码无效',
											validators : {
												notEmpty : {
													message : '密码不能为空'
												},
												stringLength : {
													min : 6,
													max : 20,
													message : '密码必须在6~20个字符之间'
												}

											}
										},

										repassword : {
											message : '密码无效',
											validators : {
												notEmpty : {
													message : '密码不能为空'
												},

												identical : {
													field : 'newpassword',
													message : '两次密码输入不一致'
												}

											}
										}

									}

								});
			});
</script>

</html>