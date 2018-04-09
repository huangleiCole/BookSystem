<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>分类列表</title>
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
						class="fa fa-hand-o-right">&nbsp;</i>&nbsp;分类列表
				</a></li>
			</ol>
		</div>
		<!--分类查询-->
		<div class="nav navbar-default">
			<div class="navbar-header">
				<a class="navbar-brand" href="${base }/permission/category/all">所有分类</a>
			</div>
			<form class="navbar-form navbar-left" role="form" method="post"
				action="${base }/permission/category/select">
				<input type="text" name="no" class="form-control"
					placeholder="请输入分类名称" />
				<button type="submit" class="btn btn-search">
					查询 <span class="glyphicon glyphicon-search"></span>
				</button>
			</form>

		</div>
		<!--分类查询end-->

		<table class="table table-hover table-striped">
			<tr class="active">
				<th>id</th>
				<th>分类名称</th>
				<th>书架</th>
				<th>创建管理员</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${categories }" var="item" varStatus="vs">
				<tr>
					<td style="vertical-align: middle;">${item.id }</td>
					<td style="vertical-align: middle;">${item.name }</td>
					<td style="vertical-align: middle;">${item.location }</td>
					<td style="vertical-align: middle;">${item.admin.name }</td>
					<td><a type="button"
						href="${base }/permission/category/show?cid=${item.id}"
						class="btn btn-primary">修改</a> <a type="button"
						onclick="return del(${item.id})" class="btn btn-danger">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>

<script type="text/javascript">
	function del(id) {
		layer.confirm('确认要删除吗',{icon:3,title:'提示',offset:'100px'},function (index){
			window.location.href = "/BookSystem/permission/category/delete?cid="
				+ id;
			layer.close(index);
		});
	};
</script>

</html>