<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>首页</title>
		<link rel="stylesheet" href="css/UserInfo/shouye.css" />
	</head>

	<body id="bod">
		<div class="wrapper">
			<div class="top-wraper">
				<jsp:include page="/jsp/public/public.jsp"></jsp:include>
			</div>

			<div class="middle-page">
				<div id="line"></div>
				<div class="video" style="height: 360px;">
					<div class="video-top" id="top">
						<div id="video-left" class="video-cont" style="float: left;">
							<p>最新课程</p>
						</div>
						<div id="video-right" class="video-cont" style="float: right;">
							<a href="videoAction_ShowAllVideoByPage?orderBy=updateTime">更多</a>
						</div>
					</div>
					<div class="video-main">
						<ul>
							<s:iterator value="#request.video" var="st">
									<li>
										<div class="lesson-list">
											<div class="lesson-item">
												<div class="v-item">
													<div class="pic">
														<div class="video-pic">
															<a id="showVideo" href="videoAction_getVideoByName?videoName=<s:property value=" #st.videoName "/>">
																<video style="width: 250px; height: 180px">
																	<source src="${pageContext.request.contextPath }/${st.getVideoPath()}" type="video/mp4"></source>
																</video>
																<div class="video-2">
																	<ul>
																		<li>名称：<s:property value="#st.videoName" /></li>
																		<li>学习人数：<s:property value="#st.studyNum" /></li>
																		<li>作者：<s:property value="#st.videoAuthor" /></li>
																		<li>更新：<s:property value="#st.updateTime" /></li>
																		<li>标签：<s:property value="#st.videoLabel" /></li>
																	</ul>
																</div>
															</a>
														</div>
													</div>
												</div>
											</div>
										</div>
									</li>
							</s:iterator>
						</ul>
					</div>
				</div>
				<div id="line"></div>
				<div class="res">
					<div class="res-top" id="top">
						<div class="res-cont" id="video-left" style="float: left;">
							<p>最新资源</p>
						</div>
						<div class="res-cont" id="video-right" style="float: right;">
							<a href="resourceAction_showAllByPage?orderBy=uploadTime">更多</a>
						</div>
					</div>
					<div class="res-main">
						<div class="resource-list">
							<s:iterator value="#request.res" var="st">
								<div class="resource">
									<div class="resource-cont">
										<dl>
											<dt><a class="a-cont" href="resourceAction_getResByComment?resourceId=<s:property value=" #st.resourceId " />"><s:property value="#st.resourceName"/></a></dt>
											<dd>简介：<s:property value="#st.intro" /></dd>
										</dl>
										<div class="list-item">
											<ul>
												<li>上传者：<s:property value="#st.uploadUser" /></li>
												<li>上传时间：<s:property value="#st.uploadTime" /></li>
												<li>所属分类：<s:property value="#st.category" /></li>
												<li>下载量：<s:property value="#st.downloadNumber" /></li>
												<li>标签：<s:property value="#st.label" /></li>
												<li>标签：<s:property value="#st.integral" /></li>
											</ul>
										</div>
									</div>
								</div>
							</s:iterator>
						</div>
					</div>
				</div>
				<div id="line"></div>
				<div class="shuqu">
					<div class="shequ-top" id="top">
						<div class="shequ-cont" id="video-left" style="float: left;">
							<p>技术问答</p>
						</div>
						<div class="shequ-cont" id="video-right" style="float: right;">
							<a href="sheQuAction_ShowQuestionByPage">更多</a>
						</div>
					</div>
					<div class="shequ-main">
						<div class="ask-list">
							<s:iterator value="#request.quest" var="st">
								<div class="quest">
									<div class="quest-cont">
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
					</div>
				</div>
			</div>
		</div>
	</body>
	<!--<script src="js/admin/jquery.min.js"></script>
	<script src="js/jquery.form.js"></script>
	异步请求首页展示方法
	<script>
		$(function () {
		    $("#bod").ready(function () {
		        url: "sheQuAction_getShouYe",
		        $.post(url,null,function(data){
                     return false;
                });
		    });
		});
	</script>-->
	-->
</html>