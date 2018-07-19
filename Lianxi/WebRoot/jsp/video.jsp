<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>课程</title>
		<link rel="stylesheet" type="text/css" href="../css/public.css">
		<link rel="stylesheet" type="text/css" href="../css/Course/course_main.css" />
		<script type="text/javascript" src="../js/Course/video.js"></script>
    </head>
    <body>
    	<div class="wrapper">
    		<div class="top-page">
				<jsp:include page="/jsp/public/public.jsp"></jsp:include>
			</div>
			<div class="pager">
				<div class="crumbs">
					<div class="w-1000">

					</div>
				</div>
				<div class="wrapper-main">
					<div class="wrapper-list">
						<div class="sort">
							<ul class="wrap-ul" id="box">
								<li class="ul-li"><a href="">全部课程</a></li>
								<li class="lesson-new">
									<a class="type-title" href="">最新</a>
								</li>
								<li class="lesson-new">
									<a class="type-title" href="">最热</a>
								</li>
							</ul>
						</div>
					</div>
					<!-- 课程展示 -->
					<div class="lesson-list" id="changid">
						<s:iterator value="request.resource" var="st">
							<div class="video-pic">
								<video src="${pageContext.request.contextPath }/${video.getVideoPath()}"></video>
							</div>
							<div class="video-mesage">
								<ul class="cf">
									<li>名称：<s:property value="#st.videoName" /></li>
									<li>上传者：<s:property value="#st.uploadUser" /></li>
									<li>上传时间：<s:property value="#st.uploadTime" /></li>
									<li>所属分类：<s:property value="#st.category" /></li>
									<li>下载量：<s:property value="#st.downloadNumber" /></li>
									<li>标签：<s:property value="#st.label" /></li>
								</ul>
							</div>
							<div class="aside">
								<div class="nav-other">
									<a class="download-item" href="resourceAction_CollentResource?fileName=<s:property value=" #st.resourceName " />">收藏</a>
								</div>
								<div class="nav-other">
									<a class="download-item" href="http://localhost:8080/Lianxi/download?">评论</a>
								</div>
								<div class="nav-other">
									<a class="download-item" href="resourceAction_DownloadResource?fileName=<s:property value=" #st.resourceName " />">下载</a>
								</div>
							</div>
						</s:iterator>
					</div>
					<div class="page-FeiYe">
						<ul>
							<c:choose>
								<c:when test="${pageBean.currentPage<=1}">
									<li><a class="a-cont" href="javascript:void(0);">«首页</a></li>
									<li><a class="a-cont" href="javascript:void(0);">«上一页</a></li>
								</c:when>
								<c:otherwise>
									<li><a class="a-cont" href="resourceAction_showAllByPage?currentPage=1">«首页</a></li>
									<li><a class="a-cont" href="resourceAction_showAllByPage?currentPage=${pageBean.currentPage-1}">«上一页</a></li>
								</c:otherwise>
							</c:choose>
		
							<c:forEach begin="${pageBean.forEachBegin }" end="${pageBean.forEachEnd}" varStatus="tp">
								<c:choose>
									<c:when test="${pageBean.currentPage==tp.index}">
										<li><a class="a-cont" href="javascript:void(0);">${tp.index}</a></li>
									</c:when>
									<c:otherwise>
										<li><a class="a-cont" href="resourceAction_showAllByPage?currentPage=${tp.index}">${tp.index}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
		
							<c:choose>
								<c:when test="${pageBean.currentPage!=pageBean.totalPage}">
									<li><a class="a-cont" href="resourceAction_showAllByPage?currentPage=${pageBean.currentPage+1}">下一页»</a></li>								
									<li><a class="a-cont" href="resourceAction_showAllByPage?currentPage=${pageBean.totalPage}">尾页»</a></li>
								</c:when>
								<c:otherwise>
									<li><a class="a-cont" href="javascript:void(0);">下一页»</a></li>
									<li><a class="a-cont" href="javascript:void(0);">»尾页»</a></li>		
								</c:otherwise>
							</c:choose>
						</ul>
						<span>当前是第${pageBean.currentPage}页,共有${pageBean.totalPage}页  </span>
					</div>
				</div>
			</div>
    	</div>
 	</body>
</html>