<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新借书</title>
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
						class="fa fa-hand-o-right">&nbsp;</i>&nbsp;更新借书
				</a></li>
			</ol>
		</div>
	</div>
	<div class="container">
		<form id="form1" role="form" method="post"
			action="${base }/permission/lend/update">
			<div class="container" style="width: 600px">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="form-group">
							<label for="no">用户编号</label> <input name="no" value="${updatelend.user.no }"
								class="form-control" type="text" disabled="disabled" />
						</div>
						<div class="form-group">
							<label for="isbn">isbn</label> <input name="isbn"
								class="form-control" value="${updatelend.book.isbn }" type="text"
								readonly="readonly" placeholder="请输入isbn" />
						</div>
						<div class="form-group">
							<label for="count">数量</label> <input value="${updatelend.count }"
								name="count" class="form-control" type="number" />
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
				}

			}

		});

	});
</script>
</html>
