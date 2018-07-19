<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>个人中心-我的视频</title>
		<link rel="stylesheet" href="css/personal/Personal.My_Course.css" />
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
						<p>我的视频:</p>
						
						<div class="video-statu">
							<ul>
								<li>
									<a id="study-1" href="userVideoAction_showSeclectByPage?fileType=在学">学习中</a>
								</li>
								<li>
									<a id="study-1" href="userVideoAction_showSeclectByPage?fileType=已学完">已学完</a>
								</li>
								<li>
									<a id="select-1" href="userVideoAction_showSeclectByPage?fileType=收藏">收藏</a>
								</li>
							</ul>
							<div class="video-d">
								<p>当前：<%=(String)request.getAttribute("type")%></p>
							</div>
						</div>
						
					</div>
					<!-- 课程展示 -->
					<div class="video-list">
						<s:iterator value="#request.resource" var="st">
							<div class="lesson-list">
								<div class="lesson-item">
									<div class="v-item">
										<div class="pic">
											<div class="video-pic">
												<a href="videoAction_getVideoByName?videoName=<s:property value="#st.videoName"/>">
													<video style="width: 250px; height: 180px">
														<source src="${pageContext.request.contextPath }/${st.getVideoPath()}" type="video/mp4"></source>
													</video>
												</a>
											</div>
										</div>
									</div>
									<div class="video-mesage">
										<div class="video-title" style="height:45px">
											<p>名称：</p>
											<a href="videoAction_getVideoByName?videoId=<s:property value=" #st.videoId "/>"><s:property value="#st.videoName" /></a>
										</div>
										<div class="video-1">
											<ul>
												<li>作者：<s:property value="#st.videoAuthor" /></li>
											</ul>
										</div>
										<div class="video-1">
											<ul>
												<li>所属：<s:property value="#st.category" /></li>
												<li>标签：<s:property value="#st.label" /></li>
											</ul>
										</div>
										<div class="video-study">
											<p>观看时间：<s:property value="#st.currentTime" /><p>	
										</div>
									</div>
								</div>
								<div id="no-study" class="lesson-right">
									<a href="userVideoAction_DeleteStudyVideo?userVideoId=<s:property value ="#st.userVideoId"/>&type=<%=(String)request.getAttribute("type")%>">删除 </a>
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
											<a class="a-cont" href="userVideoAction_showSeclectByPage?currentPage=1">«首页</a>
										</li>
										<li>
											<a class="a-cont" href="userVideoAction_showSeclectByPage?currentPage=${pageBean.currentPage-1}">«上一页</a>
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
												<a class="a-cont" href="userVideoAction_showSeclectByPage?currentPage=${tp.index}">${tp.index}</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${pageBean.currentPage!=pageBean.totalPage}">
										<li>
											<a class="a-cont" href="userVideoAction_showSeclectByPage?currentPage=${pageBean.currentPage+1}">下一页»</a>
										</li>
										<li>
											<a class="a-cont" href="userVideoAction_showSeclectByPage?currentPage=${pageBean.totalPage}">尾页»</a>
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