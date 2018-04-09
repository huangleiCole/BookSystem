<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加分类</title>
<link rel="stylesheet" type="text/css"
	href="${base }/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${base }/css/addadmin.css" />
<link rel="stylesheet" type="text/css"
	href="${base }/css/bootstrapValidator.min.css">
<script type="text/javascript" src="${base }/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript"
	src="${base }/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${base }/js/bootstrapValidator.min.js"></script>
</head>
<body>
	<div style="margin-top: 5px; width: 98%; margin: 5px auto 0 auto">
		<%@ include file="/WEB-INF/pages/common/head.jsp"%>
		<div class="nav">
			<ol class="breadcrumb">
				<li><a class="text-success">当前位置&nbsp;<i
						class="fa fa-hand-o-right">&nbsp;</i>&nbsp;添加分类
				</a></li>
			</ol>
		</div>
	</div>
	<div class="container">
		<form id="form1" role="form" method="post"
			action="${base }/permission/category/add">
			<div class="container" style="width: 600px">
				<div class="panel panel-default">

					<div class="panel-body">

						<div class="form-group">
							<label for="categoryname">分类名称</label> <input name="categoryname"
								class="form-control" type="text" placeholder="请输入名称" />
						</div>
						<div class="form-group">
							<label for="location">位置</label> <input name="location"
								class="form-control" type="text" placeholder="请输入位置" />
						</div>

						<button type="submit" class="btn btn-info form-control">添加</button>
					</div>
				</div>
			</div>


		</form>
	</div>

</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#form1").bootstrapValidator({
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {

				categoryname : {
					message : '分类名称无效',
					validators : {
						notEmpty : {
							message : '分类名称不能为空'
						}
					}
				},
				location : {
					message : '位置无效',
					validators : {
						notEmpty : {
							message : '位置不能为空'
						}
					}
				}

			}

		});

	});
</script>
</html>
