<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>个人中心-我的消息</title>
		<link rel="stylesheet" href="css/personal/Personal.My_news.css" />
	</head>

	<body>
		<div class="wraper">
			<div class="top-wraper">
				<jsp:include page="/jsp/public/public.jsp"></jsp:include>
			</div>

			<div class="wrapper">
				<div class="directory">
					<ul>
						<li>
							<a href="userAction_UserEdit">我的账号</a>
						</li>
						<li>
							<a href="userVideoAction_showSeclectByPage">我的视频</a>
						</li>
						<li>
							<a href="sheQuAction_getuserNews">我的消息</a>
						</li>
						<li>
							<a href="userSelectAction_showSeclectByPage">我的收藏</a>
						</li>
						<li>
							<a href="userDownAction_showDownByPage">我的下载</a>
						</li>
						<li>
							<a href="resourceAction_showUpUserByPage">我的上传</a>
						</li>
						<li>
							<a href="sheQuAction_showUserQuest?str=isSolve">我的问题</a>
						</li>
						<li>
							<a href="jsp/UpAndDownResource/UpResource.jsp">资源上传</a>
						</li>
					</ul>
				</div>
				<div class="show">
					<div class="show-top">
						<ul>
							<li>我的消息:</li>
							<li id="li-22"><%=(String)request.getAttribute("mesage")%></li>
						</ul>
					</div>
					<!-- 课程展示 -->
					<div class="video-list">
						<s:iterator value="#request.resource" var="st">
							<div class="lesson-list">
									<p style=" margin:0 0 0 0;padding: 15px 0 0 40px;"><s:property value="#st.newsName" /></p>
									<a href="sheQuAction_deleteUserNews?newsId=<s:property value=" #st.newsId " />">删除</a>
							</div>
						</s:iterator>
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
											<a class="a-cont" href="sheQuAction_getuserNews?currentPage=1">«首页</a>
										</li>
										<li>
											<a class="a-cont" href="sheQuAction_getuserNews?currentPage=${pageBean.currentPage-1}">«上一页</a>
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
												<a class="a-cont" href="sheQuAction_getuserNews?currentPage=${tp.index}">${tp.index}</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${pageBean.currentPage!=pageBean.totalPage}">
										<li>
											<a class="a-cont" href="sheQuAction_getuserNews?currentPage=${pageBean.currentPage+1}">下一页»</a>
										</li>
										<li>
											<a class="a-cont" href="sheQuAction_getuserNews?currentPage=${pageBean.totalPage}">尾页»</a>
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
			</div>
		</div>
	</body>

</html>