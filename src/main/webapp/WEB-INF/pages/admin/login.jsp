<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>图书管理系统</title>
<link href="${base }/bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="${base }/font-awesome-4.7.0/css/font-awesome.css"
	rel="stylesheet" type="text/css" />
<link href="${base }/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${base }/css/bootstrapValidator.min.css">
<script src="${base }/js/jquery-3.1.1.min.js"></script>
<script src="${base }/bootstrap/js/bootstrap.min.js"></script>
<script src="${base }/js/login.js"></script>
<script type="text/javascript"
	src="${base }/js/bootstrapValidator.min.js"></script>
<script type="text/javascript"
	src="${base }/layer-v3.1.1/layer/layer.js"></script>
</head>

<body style="background-color: #E2E2E2;">
	<form id="form1" method="post" action="${base }/admin/addsuper">
		<div class="modal fade" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">初次使用，请注册超级管理员账号</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="adminName">管理员名</label> <input type="text"
								id="adminName" name="newname" class="form-control"
								placeholder="请输入管理员名" />
						</div>
						<div class="form-group">
							<label for="adminPassword">密 码</label> <input type="password"
								id="adminPassword" class="form-control" name="newpassword"
								placeholder="请输入密码" />
						</div>
						<div class="form-group">
							<label for="rePassword">确认密码</label><input type="password"
								id="rePassword" class="form-control" name="repassword"
								placeholder="请再次输入密码" />
						</div>
						<div class="form-group">
							<label for="real">真实姓名</label> <input type="text" id="real"
								class="form-control" name="realname" placeholder="请输入真实姓名" />
						</div>
						<div class="form-group">
							<label for="tel">手机号码</label><input type="text" id="tel"
								class="form-control" name="phone" placeholder="请输入手机号码" />
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-info form-control">注册</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<c:if test="${hasSuper != 'true' }">
	<script type="text/javascript">
		$('#myModal').modal({
			keyboard : true,
			backdrop : 'static'
		});
	</script>
</c:if>
	<c:if test="${admin == '0' }">
		<script type="text/javascript">
			layer.alert("密码错误或被禁用,请联系超级管理员!",{icon:2,shade:[1,'#e2e2e2'], offset:'100px'},function (index){
				window.location.href = "/BookSystem/admin/logout";
			});
			
		</script>
	</c:if>
	<div class="container">
		<div class="row text-center " style="padding-top: 100px;">
			<div class="col-md-12">
				<h2>图书管理系统</h2>
			</div>
		</div>
		<div class="row ">
			<div
				class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
				<div class="panel-body">
					<form id="form2" role="form" method="post"
						action="${base }/admin/login">
						<hr />
						<h5>请登录</h5>
						<br />
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-user"></i></span>
							<input type="text" id="name" class="form-control" name="name"
								onblur="checkname()" placeholder="管理员名" />
						</div>
						<span id="unHint1"></span>
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-lock"></i></span>
							<input type="password" class="form-control" name="password"
								placeholder="密码" />
						</div>
						<button type="submit" id="login" class="btn btn-primary">登录</button>
						<hr />
						忘记密码 ? 请联系超级管理员
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
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
												newname : {
													message : '用户名无效',
													validators : {
														notEmpty : {
															message : '用户名不能为空'
														},
														stringLength : {
															min : 6,
															max : 30,
															message : '用户名必须在6~30个字符之间'
														},
														regexp : {
															regexp : /^[a-z][a-zA-Z0-9]+$/,
															message : '用户名只能是小写字母开头,不能有中文'
														}

													}
												},

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
														},
														different : {
															field : 'newname',
															message : '密码不能和用户名一样'
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
														},
														different : {
															field : 'newname',
															message : '密码不能和用户名一样'
														}

													}
												},
												realname : {
													message : '真实姓名无效',
													validators : {
														notEmpty : {
															message : '真实姓名不能为空'
														},
														regexp : {
															regexp : /^([\u4e00-\u9fa5]{1,20}|[a-zA-Z\.\s]{1,20})$/,
															message : '名字格式不正确'
														}
													}
												},
												phone : {
													message : '电话无效',
													validators : {
														notEmpty : {
															message : '电话不能为空'
														},
														regexp : {
															regexp : /^[1][3,4,5,7,8,9][0-9]{9}$/,
															message : '电话格式不正确'
														}

													}
												}

											}

										});
						$("#form2").bootstrapValidator({
							message : 'This value is not valid',

							feedbackIcons : {
								valid : 'glyphicon glyphicon-ok',
								invalid : 'glyphicon glyphicon-remove',
								validating : 'glyphicon glyphicon-refresh'
							},
							fields : {

								name : {
									message : '用户名无效',
									validators : {
										notEmpty : {
											message : '用户名不能为空'
										}
									}
								}
							}
						});
					});
</script>
</html>
