<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>搜索</title>
		<link rel="stylesheet" type="text/css" href="css/sreach.css" />
	</head>

	<body>
		<div class="wrapper">
			<div class="top-page">
				<jsp:include page="/jsp/public/public.jsp"></jsp:include>
			</div>
			<div class="middle-page">
				<div class="pater-title">
					<ul>
						<li>
							<p id="video-aa">课程</p>
						</li>
						<li>
							<p id="res-aa">资源</p>
						</li>
						<li>
							<p id="quest-aa">问答</p>
						</li>
					</ul>
				</div>
				<div class="badge">
					<div class="sreach" id="video-a" style="display: block;">
						<div class="video-list" id="video-list">
							<s:iterator value="#request.video" var="st">
								<div class="lesson-list">
									<div class="lesson-item">
										<div class="v-item">
											<div class="pic">
												<div class="video-pic">
													<a href="videoAction_getVideoByName?videoName=<s:property value=" #st.videoName "/>">
														<video style="width: 250px; height: 180px">
															<source src="${pageContext.request.contextPath }/<s:property value=" #st.videoPath "/>" type="video/mp4"></source>
														</video>
													</a>
												</div>
											</div>
										</div>
										<div class="video-mesage">
											<div class="video-title">
												<p>名称：</p>
												<a href="videoAction_getVideoByName?videoId=<s:property value=" #st.videoId "/>"><s:property value="#st.videoName" /></a>
											</div>
											<div class="video-study">
												<p>学习人数：<s:property value="#st.studyNum" /></p>
											</div>
											<div class="video-1">
												<ul>
													<li>作者：<s:property value="#st.videoAuthor" /></li>
													<li>更新：<s:property value="#st.updateTime" /></li>
												</ul>
											</div>
											<div class="video-1">
												<ul>
													<li>分类：<s:property value="#st.category" /></li>
													<li>标签：<s:property value="#st.videoLabel" /></li>
												</ul>
											</div>
											<div class="video-intro">
												<p>介绍：</p>
												<p class="p-1"><s:property value="#st.courseIntro" />
													<p>
											</div>
										</div>

									</div>

									<div class="aside-a">
										<div class="nav-other">
											<a class="download-item" href="videoAction_selectVideo?videoId=<s:property value=" #st.videoId " />">收藏</a>
										</div>
										<div class="nav-other">
											<a class="download-item" href="http://localhost:8080/Lianxi/download?">评论</a>
										</div>
										<div class="nav-other">
											<a class="download-item" href="videoAction_downloadVideo?videoId=<s:property value=" #st.videoId " />">下载</a>
										</div>
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
												<a class="a-cont" href="sheQuAction_ShowQuestionByPage?currentPage=1">«首页</a>
											</li>
											<li>
												<a class="a-cont" href="sheQuAction_ShowQuestionByPage?currentPage=${pageBean.currentPage-1}">«上一页</a>
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
													<a class="a-cont" href="sheQuAction_ShowQuestionByPage?currentPage=${tp.index}">${tp.index}</a>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>

									<c:choose>
										<c:when test="${pageBean.currentPage!=pageBean.totalPage}">
											<li>
												<a class="a-cont" href="sheQuAction_ShowQuestionByPage?currentPage=${pageBean.currentPage+1}">下一页»</a>
											</li>
											<li>
												<a class="a-cont" href="sheQuAction_ShowQuestionByPage?currentPage=${pageBean.totalPage}">尾页»</a>
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
					<div class="sreach" id="resource-a" style="display: none;">
						<!-- 课程展示 -->
						<div class="resource-list">
							<s:iterator value="#request.res" var="st">
								<div class="resource">
									<div class="resource-cont">
										<dl>
											<dt><a class="a-cont" href=""><s:property value="#st.resourceName"/></a></dt>
											<dd>简介：<s:property value="#st.intro" /></dd>
										</dl>
										<div class="list-item">
											<ul>
												<li>上传者：<s:property value="#st.uploadUser" /></li>
												<li>上传时间：<s:property value="#st.uploadTime" /></li>
												<li>所属分类：<s:property value="#st.category" /></li>
												<li>下载量：<s:property value="#st.downloadNumber" /></li>
												<li>标签：<s:property value="#st.label" /></li>
											</ul>
										</div>
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
												<a class="a-cont" href="sheQuAction_ShowQuestionByPage?currentPage2=1">«首页</a>
											</li>
											<li>
												<a class="a-cont" href="sheQuAction_ShowQuestionByPage?currentPage2=${pageBean.currentPage-1}">«上一页</a>
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
													<a class="a-cont" href="sheQuAction_ShowQuestionByPage?currentPage2=${tp.index}">${tp.index}</a>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>

									<c:choose>
										<c:when test="${pageBean.currentPage!=pageBean.totalPage}">
											<li>
												<a class="a-cont" href="sheQuAction_ShowQuestionByPage?currentPage2=${pageBean.currentPage+1}">下一页»</a>
											</li>
											<li>
												<a class="a-cont" href="sheQuAction_ShowQuestionByPage?currentPage2=${pageBean.totalPage}">尾页»</a>
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
					<div class="sreach" id="question-a" style="display: none;">
						<div class="ask-list">
							<s:iterator value="#request.quest" var="st">
								<div class="resource">
									<div class="resource-cont">
										<ul>
											<li id="li-1" class="list-group">
												<p class="p-1"><s:property value="#st.answerNum" /></p>
												<p class="p-2">回答</p>
											</li>
											<li id="li-2">
												<p class="p-3">
													<a href="sheQuAction_getQuestByAnswer?questionId=<s:property value=" #st.questionId " />"><s:property value="#st.questionName" /></a>
												</p>
												<p class="p-4">
													<s:property value="#st.questUser" />
													<i></i>
													<s:property value="#st.questionTime" />
												</p>
											</li>
										</ul>
									</div>
								</div>
							</s:iterator>
						</div>
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
												<a class="a-cont" href="sheQuAction_ShowQuestionByPage?currentPage1=1">«首页</a>
											</li>
											<li>
												<a class="a-cont" href="sheQuAction_ShowQuestionByPage?currentPage1=${pageBean.currentPage-1}">«上一页</a>
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
													<a class="a-cont" href="sheQuAction_ShowQuestionByPage?currentPage1=${tp.index}">${tp.index}</a>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>

									<c:choose>
										<c:when test="${pageBean.currentPage!=pageBean.totalPage}">
											<li>
												<a class="a-cont" href="sheQuAction_ShowQuestionByPage?currentPage1=${pageBean.currentPage+1}">下一页»</a>
											</li>
											<li>
												<a class="a-cont" href="sheQuAction_ShowQuestionByPage?currentPage1=${pageBean.totalPage}">尾页»</a>
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
		</div>
		<script src="js/admin/jquery.min.js"></script>
		<script src="js/jquery.form.js"></script>
		<script>
			$(document).ready(function(e) {
				$("#video-aa").click(function(e) {
					$("#video-a").toggle();
					if($('#resource-a').css('display') == 'block'){
					   document.getElementById("resource-a").style.display = "none";
					}
					if($('#question-a').css('display') == 'block'){
					   document.getElementById("question-a").style.display = "none";
					}
				});
			});
			$(document).ready(function(e) {
				$("#res-aa").click(function(e) {
					$("#resource-a").toggle();
					if($('#video-a').css('display') == 'block'){
					   document.getElementById("video-a").style.display = "none";
					}
					if($('#question-a').css('display') == 'block'){
					   document.getElementById("question-a").style.display = "none";
					}
				});
			});
			$(document).ready(function(e) {
				$("#quest-aa").click(function(e) {
					$("#question-a").toggle();
					if($('#resource-a').css('display') == 'block'){
					   document.getElementById("resource-a").style.display = "none";
					}
					if($('#video-a').css('display') == 'block'){
					   document.getElementById("video-a").style.display = "none";
					}
				});
			});
		</script>
	</body>

</html>