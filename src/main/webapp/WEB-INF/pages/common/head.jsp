<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${base }/font-awesome-4.7.0/css/font-awesome.css"
	rel="stylesheet" type="text/css" />
<title>head</title>
</head>
<body>

	<nav class="navbar navbar-default well-sm" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#example-navbar-collapse">
					<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">图书管理系统</a>
			</div>
			<div class="collapse navbar-collapse" id="example-navbar-collapse">
				<ul class="nav navbar-nav">
					<li
						<c:if test="${admin.flag == '普通管理员' }" var="normal">
							 class="hidden"
						</c:if>>
						<ul class="nav navbar-nav navbar-left">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <span
									class="glyphicon glyphicon-star"></span> 管理员 <b class="caret"></b>
							</a>
								<ul class="dropdown-menu">
									<li><a href="${base }/admin/all">管理员列表</a></li>
									<li class="divider"></li>
									<li><a href="${base }/admin/jsp/addadmin">添加管理员</a></li>
									<li class="divider"></li>
								</ul></li>
						</ul>
					</li>
					<li><ul class="nav navbar-nav navbar-left">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <span
									class="glyphicon glyphicon-user"></span> 用户管理 <b class="caret"></b>
							</a>
								<ul class="dropdown-menu">
									<li><a href="${base }/permission/user/all">用户列表</a></li>
									<li class="divider"></li>
									<li><a href="${base }/permission/user/jsp/adduser">添加用户</a></li>
									<li class="divider"></li>
								</ul></li>
						</ul></li>
					<li><ul class="nav navbar-nav navbar-left">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <span
									class="glyphicon glyphicon-book"></span> 图书管理 <b class="caret"></b>
							</a>
								<ul class="dropdown-menu">
									<li><a href="${base }/permission/book/all">图书列表</a></li>
									<li class="divider"></li>
									<li><a href="${base }/permission/book/jsp/addbook">添加图书</a></li>
									<li class="divider"></li>
								</ul></li>
						</ul></li>
					<li><ul class="nav navbar-nav navbar-left">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <span
									class="glyphicon glyphicon-tags"></span> 分类管理 <b class="caret"></b>
							</a>
								<ul class="dropdown-menu">
									<li><a href="${base }/permission/category/all">分类列表</a></li>
									<li class="divider"></li>
									<li><a href="${base }/permission/category/jsp/addcategory">添加分类</a></li>
									<li class="divider"></li>
								</ul></li>
						</ul></li>
					<li><a href="${base }/permission/lend/all"><i
							class="fa fa-pencil-square-o"></i> 借书列表 </a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><span class="badge s1">${admin.name },你好</span></li>
					<li><a id="a1" type="button" href="${base }/admin/logout"
						class="btn btn-danger"> 退出系统 <span
							class="glyphicon glyphicon-log-out"></span>
					</a></li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>