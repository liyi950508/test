<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${video.getVideoName()}-相关问题</title>
		<link rel="stylesheet" href="css/admin/quest_view.css" />
	</head>

	<body>
		<div class="wrapper">
		<!-- 课程展示 -->
		<div class="video-list" id="video-list">
			<s:iterator value="#request.video" var="st">
				<div class="lesson-list">
					<div class="lesson-item">
						<div class="v-item">
							<div class="pic">
								<div class="video-pic">
										<video style="width: 250px; height: 180px">
											<source src="${pageContext.request.contextPath }/${st.getVideoPath()}" type="video/mp4"></source>
										</video>
								</div>
							</div>
						</div>
						<div class="video-mesage">
							<div class="video-study">
								<p>名称：<s:property value="#st.videoName" /></p>
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
							<a class="download-item" href="videoAction_AdminVideoEdit?videoId=<s:property value=" #st.videoId " />">修改</a>
						</div>
						<div class="nav-other">
							<a class="download-item" href="videoAction_AdminDeleteVideo?videoId=<s:property value=" #st.videoId " />" onclick="return confirm('确定要删除吗？')">删除</a>
						</div>
					</div>
				</div>
			</s:iterator>
		</div>
		</div>
		<div class="quest-wraper">
			<div class="question-list">
				<s:iterator value="#request.resource" var="st">
					<div class="resource">
						<div class="quest-name">
							<a class="a-cont" href="sheQuAction_getQuestByAnswer?adminId=${admin.getAdminId()}&questionId=<s:property value="#st.questionId"/>"><s:property value="#st.questionName" /></a>
						</div>
						<div class="quest-connect">
							<ul>
								<li>提问人：<s:property value="#st.questUser" /></li>
								<li>回答：<s:property value="#st.answerNum" /></li>
								<li>查看：<s:property value="#st.browseNum" /></li>
								<li>时间：<s:property value="#st.questionTime" /></li>
								<li><a href="sheQuAction_deleteQuestion?comment=<s:property value="#st.questionId" />">删除</a></li>
							</ul>
						</div>
					</div>
				</s:iterator>
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
									<a class="a-cont" href="videoAction_getAdminVideoByQuest?videoId=${video.getVideoId()}&currentPage=1">«首页</a>
								</li>
								<li>
									<a class="a-cont" href="videoAction_getAdminVideoByQuest?videoId=${video.getVideoId()}&currentPage=${pageBean.currentPage-1}">«上一页</a>
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
										<a class="a-cont" href="videoAction_getAdminVideoByQuest?videoId=${video.getVideoId()}&currentPage=${tp.index}">${tp.index}</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:choose>
							<c:when test="${pageBean.currentPage!=pageBean.totalPage}">
								<li>
									<a class="a-cont" href="videoAction_getAdminVideoByQuest?videoId=${video.getVideoId()}&currentPage=${pageBean.currentPage+1}">下一页»</a>
								</li>
								<li>
									<a class="a-cont" href="videoAction_getAdminVideoByQuest?videoId=${video.getVideoId()}&currentPage=${pageBean.totalPage}">尾页»</a>
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
	</body>

</html>