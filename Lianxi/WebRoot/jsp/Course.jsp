<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
		<title>课程</title>
		<link rel="stylesheet" type="text/css" href="css/public.css">
		<link rel="stylesheet" type="text/css" href="css/Course/course_main.css" />
		<script type="text/javascript" src="js/Course/course.js"></script>
	</head>

	<body>
		<div class="wrapper">
			<div class="top-page">
				<jsp:include page="/jsp/public/public.jsp"></jsp:include>
			</div>
			<div class="pager">
				<div class="container">
					<div class="wraper">
						<!-- 课程分类 -->
						<div class="aside">
							<div class="jiqixuexi">
								<div class="a-a">
									<a href="videoAction_ShowAllVideoByPage?category=${help.JQXX }">机器学习</a>
								</div>
								<ul>
									<li>
										<a href="videoAction_ShowAllVideoByPage?category=${help.JQXX }&label=${help.RGSHL }">神经网络</a>
									</li>
									<li>
										<a href="videoAction_ShowAllVideoByPage?category=${help.JQXX }&label=${help.SDXX }">深度学习</a>
									</li>
									<li>
										<a href="videoAction_ShowAllVideoByPage?category=${help.JQXX }&label=${help.SJWJ }">数据挖掘</a>
									</li>
									<li>
										<a href="videoAction_ShowAllVideoByPage?category=${help.JQXX }&label=${help.PYTH }">Python高级</a>
									</li>
									<li>
										<a href="videoAction_ShowAllVideoByPage?category=${help.JQXX }&label=${help.RRRR }">R语言</a>
									</li>
								</ul>
							</div>
							<div class="jiqixuexi">
								<div class="a-a">
									<a href="videoAction_ShowAllVideoByPage?category=${help.MSSB }">模式识别</a>
								</div>
								<ul>
									<li>
										<a href="videoAction_ShowAllVideoByPage?category=${help.MSSB }&label=${help.TXCL }">图像处理</a>
									</li>
									<li>
										<a href="videoAction_ShowAllVideoByPage?category=${help.MSSB }&label=${help.HGSF }">回归算法</a>
									</li>
									<li>
										<a href="videoAction_ShowAllVideoByPage?category=${help.MSSB }&label=${help.ZRYY }">自然语言</a>
									</li>
									<li>
										<a href="videoAction_ShowAllVideoByPage?category=${help.MSSB }&label=${help.JHBH }">几何变换</a>
									</li>
									<li>
										<a href="videoAction_ShowAllVideoByPage?category=${help.MSSB }&label=${help.XTX }">形态学</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="badge">
							<div class="wrap-item">
								<div class="wrap-cont">
									<ul>
										<li class="lesson-new">
											<a class="type-title" href="videoAction_ShowAllVideoByPage">全部课程</a>
										</li>
										<li class="lesson-new">
											<a class="type-title" href="videoAction_ShowAllVideoByPage?<%String c = (String)request.getAttribute("category");if(c!= null){%>category=<%=c%>&<%}%><%String l = (String)request.getAttribute("label");if(l != null){%>label=<%=l%>&<%}%>orderby=updateTime">最新</a>
										</li>
										<li class="lesson-new">
											<a class="type-title" href="videoAction_ShowAllVideoByPage?<%if(c != null){%>category=<%=c%>&<%}%><%if(l != null){%>label=<%=l%>&<%}%>orderby=studyNum">最热</a>
										</li>
									</ul>
								</div>
								<div class="wrap-a">
									<p><%if(c != null) {%><%=c%>&nbsp;/&nbsp;<%}%><%if(l != null){%><%=l%>&nbsp;/&nbsp;<%}%><%=(String)request.getAttribute("order") %></p>
								</div>
							</div>
							<!-- 课程展示 -->
							<div class="video-list" id="video-list">
								<s:iterator value="#request.resource" var="st">
									<div class="lesson-list">
										<div class="lesson-item">
											<div class="v-item">
												<div class="pic">
													<div class="video-pic">
														<a href="videoAction_getVideoByName?videoName=<s:property value=" #st.videoName "/>">
															<video style="width: 250px; height: 180px">
																<source src="${pageContext.request.contextPath }/${st.getVideoPath()}" type="video/mp4"></source>
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
					</div>
				</div>
			</div>
		</div>
	</body>

</html>