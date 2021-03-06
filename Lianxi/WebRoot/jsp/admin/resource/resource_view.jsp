<%@ page language="java" import="java.util.*"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>评论-资源id=${res.getResourceId()}</title>
		<link type="text/css" rel="stylesheet" href="css/admin/res_view.css" />
	</head>

	<body>
		<div class="wraper">
			<div class="middle-page">
				<div class="wrapper">
					<div class="middle-main">
						<div class="div-h">
							<p>${res.getResourceName()}</p>
						</div>
						<div class="resource-cont">
							<nav>
								<ul>
									<li id="li-2">
										<p class="p-1">上传者：</p>
										<p class="p-2">${res.getUploadUser()}</p>
									</li>
									<li id="li-2">
										<p class="p-1">上传时间：</p>
										<p class="p-2">${res.getUploadTime()}</p>
									</li>
									<li id="li-2">
										<p class="p-1">标签：</p>
										<p class="p-2">${res.getLabel()}</p>
									</li>
									<li id="li-2">
										<p class="p-1">类别：</p>
										<p class="p-2">${res.getType()}</p>
									</li>
									<li id="li-2">
										<p class="p-1">所属：</p>
										<p class="p-2">${res.getCategory()}</p>
									</li>
									<li id="li-1" class="list-group">
										<p class="p-1">下载量</p>
										<p class="p-2">${res.getDownloadNumber()}</p>
									</li>
									<li id="li-1">
										<p class="p-1">收藏量</p>
										<p class="p-2">${res.getCollectNumber()}</p>
									</li>
								</ul>
							</nav>
						</div>
					</div>
					<div class="middle-cont">
						<s:iterator value="#request.resource" var="st">
							<div class="resource">
								<div class="resource-cont">
									<ul>
										<li id="li-1" class="list-group">
											<s:property value="#st.commmetsUser" /></li>
										<li id="li-1" class="list-group">
											<s:property value="#st.commentsTime" /></li>
										<li>评论</li>
									</ul>
									<div class="download-item">
										<a href="sheQuAction_deleteComment?comment=<s:property value="#st.commentsId" />">删除</a>
									</div>
								</div>
								<div class="div-s">
									<p><s:property value="#st.commentsName" /></p>
								</div>
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
											<a class="a-cont" href="resourceAction_getAdminResByComment?resourceId=${res.getResourceId()}&currentPage=1">«首页</a>
										</li>
										<li>
											<a class="a-cont" href="resourceAction_getAdminResByComment?resourceId=${res.getResourceId()}&currentPage=${pageBean.currentPage-1}">«上一页</a>
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
												<a class="a-cont" href="resourceAction_getAdminResByComment?resourceId=${res.getResourceId()}&currentPage=${tp.index}">${tp.index}</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${pageBean.currentPage!=pageBean.totalPage}">
										<li>
											<a class="a-cont" href="resourceAction_getAdminResByComment?resourceId=${res.getResourceId()}&currentPage=${pageBean.currentPage+1}">下一页»</a>
										</li>
										<li>
											<a class="a-cont" href="resourceAction_getAdminResByComment?resourceId=${res.getResourceId()}&currentPage=${pageBean.totalPage}">尾页»</a>
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
