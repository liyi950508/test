<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
		<link rel="stylesheet" type="text/css" href="css/admin/user/user_list.css" />
	</head>

	<body>
		<div class="user">
			<div style="width: 70%; height: 50px; margin: auto auto;padding-top: 20px;">
				<form action="userAction_showAllUserByPage" method="post" style="float: left;">
						用户名:<input type="text" class="input-item" name="username" value="" />
						&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="submit" value="查询"  class="item-submit" />
				</form>
				<form action="userAction_showAllUserByPage" method="post" style="float: right;">
					   职    业:<input type="text" class="input-item" name="userJob" value=""/>
					 &nbsp;&nbsp;&nbsp;&nbsp;	
					 <input type="submit" value="查询"  class="item-submit" />
				</form>
			</div>
			<div class="user-top">
				<div class="user-title">用戶列表:</div>
			</div>
			<div class="user_list">
				<nav>
					<ul class="nav">
						<li>
							<p>用户id</p>
						</li>
						<li>
							<p>用户名称</p>
						</li>
						<li>
							<p>用户密码</p>
						</li>
						<li>
							<p>职业</p>
						</li>
						<li>
							<p>用户积分</p>
						</li>
						<li>
							<p>操作</p>
						</li>
					</ul>

					<s:iterator value="#request.resource" var="st">
						<ul class="nav">
							<li>
								<s:property value="#st.id" />
							</li>
							<li>
								<s:property value="#st.username" />
							</li>
							<li>
								<s:property value="#st.password" />
							</li>
							<li>
								<s:property value="#st.userJob" />
							</li>
							<li>
								<s:property value="#st.integral" />
							</li>
							<li>
								<a href="userAction_AdminUserDelete?userId=<s:property value =" #st.id " />" onclick="return confirm('确定要删除吗？')">删除 </a>
								<i></i>
								<a href="userAction_adminUserEdit?userId=<s:property value =" #st.id " />">修改</a>
							</li>
						</ul>
					</s:iterator>
				</nav>
			</div>

			<!-- 分页展示功能块  -->
			<div class="page-FeiYe">
				<div class="feiye-nav">
					<ul>
						<c:choose>
							<c:when test="${pageBean.currentPage<=1}">
								<li>
									<a class="a-cont" href="javascript:void(0);">«首页</a>
								</li>
								<li>
									<a class="a-cont" href="javascript:void(0);">«上一页</a>
								</li>
							</c:when>
							<c:otherwise>
								<li>
									<a class="a-cont" href="videoAction_ShowAllVideoByPage?currentPage=1">«首页</a>
								</li>
								<li>
									<a class="a-cont" href="videoAction_ShowAllVideoByPage?currentPage=${pageBean.currentPage-1}">«上一页</a>
								</li>
							</c:otherwise>
						</c:choose>

						<c:forEach begin="${pageBean.forEachBegin }" end="${pageBean.forEachEnd}" varStatus="tp">
							<c:choose>
								<c:when test="${pageBean.currentPage==tp.index}">
									<li>
										<a class="a-cont" href="javascript:void(0);">${tp.index}</a>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<a class="a-cont" href="videoAction_ShowAllVideoByPage?currentPage=${tp.index}">${tp.index}</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:choose>
							<c:when test="${pageBean.currentPage!=pageBean.totalPage}">
								<li>
									<a class="a-cont" href="videoAction_ShowAllVideoByPage?currentPage=${pageBean.currentPage+1}">下一页»</a>
								</li>
								<li>
									<a class="a-cont" href="videoAction_ShowAllVideoByPage?currentPage=${pageBean.totalPage}">尾页»</a>
								</li>
							</c:when>
							<c:otherwise>
								<li>
									<a class="a-cont" href="javascript:void(0);">下一页»</a>
								</li>
								<li>
									<a class="a-cont" href="javascript:void(0);">»尾页»</a>
								</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<div class="feiye-nav">
					<p>当前是第<span>${pageBean.currentPage}</span>页,共有<span>${pageBean.totalPage}</span>页 </p>
				</div>
			</div>
		</div>
	</body>

</html>