<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
		<link rel="stylesheet" type="text/css" href="css/admin/videoEdit.css" />
	</head>

	<body>
		<div class="middle-page">
			<div class="page-header">
				<p class="current">修改视频资料</p>
			</div>
			<div class="upload">
				<form class="form" action="userAction_adminUserUpDate" enctype="multipart/form-data" method="post">
					<div class="form-item">
						<div class="form-cont">
							<p>用户名</p>
						</div>
						<div class="item-cont">
							<div class="note">
								<input type="text" class="input-item" name="username" value="${user.getUsername()}" />
								<input type="hidden" name="userId" value="${user.getId()}" />
							</div>
						</div>
					</div>
					<div class="form-item">
						<div class="form-cont">
							<p>密码</p>
						</div>
						<div class="item-cont">
							<div class="note">
								<input type="text" class="input-item" name="password" value="${user.getPassword()}" />
							</div>
						</div>
					</div>
					<div class="form-item">
						<div class="form-cont">
							<p>积分</p>
						</div>
						<div class="item-cont">
							<div class="note">
								<input type="text" class="input-item" name="integral" value="${user.getIntegral()}" />
							</div>
						</div>
					</div>
					<div class="form-item">
						<div class="form-cont">
							<p>职业:</p>
						</div>
						<div class="item-cont">
							<div class="note">
								<select name="userJob" id="userJob" class="input-item">
									<option value="null">${user.getUserJob() }</option>
									<option value="教师">教师</option>
									<option value="学生">学生</option>
									<option value="研究员">研究员</option>
									<option value="程序员">程序员</option>
									<option value="其他">其他</option>
								</select>
								<span id="Jobspan"></span>
							</div>
						</div>
					</div>
					<div class="form-item">
						<div class="form-submit">
							<input type="submit" value="提交修改" name="" class="item-submit" />
						</div>
					</div>
				</form>

			</div>
		</div>
	</body>

</html>