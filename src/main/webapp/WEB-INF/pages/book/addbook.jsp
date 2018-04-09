<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加图书</title>
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
						class="fa fa-hand-o-right">&nbsp;</i>&nbsp;添加图书
				</a></li>
			</ol>
		</div>
	</div>
	<div class="container">
		<form id="form1" role="form" method="post"
			action="${base }/permission/book/add">
			<div class="container" style="width: 600px">
				<div class="panel panel-default">

					<div class="panel-body">
						<div class="form-group">
							<label for="isbn">isbn</label> <input name="isbn"
								class="form-control" type="text" placeholder="请输入isbn" />

						</div>
						<div class="form-group">
							<label for="name">图书名称</label> <input name="name"
								class="form-control" type="text" placeholder="请输入图书名称" />
						</div>
						<div class="form-group">
							<label for="author">作者</label> <input name="author"
								class="form-control" type="text" placeholder="请输入作者" />
						</div>
						<div class="form-group">
							<label for="publishhouse">出版社</label> <input name="publishhouse"
								class="form-control" type="text" placeholder="请输入出版社" />
						</div>
						<div class="form-group">
							<label for="count">数量</label> <input value="1" name="count"
								class="form-control" type="number" />
						</div>
						<div class="form-group">
							<label for="category">类别</label> <select class="form-control"
								name="category">
								<c:forEach items="${categories }" var="item" varStatus="vs">
									<option value="${item.id }">${item.name }
								</c:forEach>
							</select>
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
				isbn : {
					message : 'isbn无效',
					validators : {
						notEmpty : {
							message : 'isbn不能为空'
						}

					}
				},

				name : {
					message : '图书名称无效',
					validators : {
						notEmpty : {
							message : '图书名称不能为空'
						}
					}
				},

				author : {
					message : '作者无效',
					validators : {
						notEmpty : {
							message : '密码不能为空'
						}

					}
				},
				publishhouse : {
					message : '出版社无效',
					validators : {
						notEmpty : {
							message : '出版社不能为空'
						}

					}
				},
				count : {
					message : '数量无效',
					validators : {
						notEmpty : {
							message : '数量不能为空'
						},
						regexp : {
							regexp : /^[1-9]\d{0,1}$/,
							message : '数量不在范围内'
						}

					}
				},
				category : {
					message : '类别无效',
					validators : {
						notEmpty : {
							message : '类别不能为空'
						}
					}
				}

			}

		});

	});
</script>
</html>
