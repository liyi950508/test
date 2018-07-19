<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>模式识别-机器学习、模式识别</title>
		<link type="text/css" rel="stylesheet" href="css/JiQiXueXi/JIQiXueXi.css" />
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
									<a href="resourceAction_showMSSbByPage">模式识别</a>
								</div>
								<ul>
									<li>
										<a href="resourceAction_showMSSbByPage?category=图像处理">图像处理</a>
									</li>
									<li>
										<a href="resourceAction_showMSSbByPage?category=回归算法">回归算法</a>
									</li>
									<li>
										<a href="resourceAction_showMSSbByPage?category=自然语言">自然语言</a>
									</li>
									<li>
										<a href="resourceAction_showMSSbByPage?category=几何变换">几何变换</a>
									</li>
									<li>
										<a href="resourceAction_showMSSbByPage?category=形态学">形态学</a>
									</li>
								</ul>
							</div>
							
						</div>
						<div class="badge">
							<div class="wrap-item">
								<div class="wrap-cont">
									<ul>
										<li class="lesson-new">
											<a class="type-title" href="resourceAction_showMSSbByPage?<%String c = (String)request.getAttribute("category");if(c!= null){%>category=<%=c%>&<%}%>">全部资源</a>
										</li>
										<li class="lesson-new">
											<a class="type-title" href="resourceAction_showMSSbByPage?<%if(c!= null){%>category=<%=c%>&<%}%>label=课件ppt">课件ppt</a>
										</li>
										<li class="lesson-new">
											<a class="type-title" href="resourceAction_showMSSbByPage?<%if(c!= null){%>category=<%=c%>&<%}%>label=教辅文档">教辅文档</a>
										</li>
										<li class="lesson-new">
											<a class="type-title" href="resourceAction_showMSSbByPage?<%if(c!= null){%>category=<%=c%>&<%}%>label=期刊杂志">期刊杂志</a>
										</li>
										<li class="lesson-new">
											<a class="type-title" href="resourceAction_showMSSbByPage?<%if(c!= null){%>category=<%=c%>&<%}%>label=源码">源码</a>
										</li>
										<li class="lesson-new">
											<a class="type-title" href="resourceAction_showMSSbByPage?<%if(c!= null){%>category=<%=c%>&<%}%>label=其他">其他</a>
										</li>
									</ul>
								</div>
								<div class="wrap-a">
									<div class="orderby">
										<ul>
											<li class="lesson-new">
												<a class="type-title" href="resourceAction_showMSSbByPage?<%if(c!= null){%>category=<%=c%>&<%}%><%String l = (String)request.getAttribute("label");if(l != null){%>label=<%=l%>&<%}%>orderby=uploadTime">最新</a>
											</li>
											<li class="lesson-new">
												<a class="type-title" href="resourceAction_showMSSbByPage?<%if(c!= null){%>category=<%=c%>&<%}%><%if(l != null){%><%=l%>&nbsp;/&nbsp;<%}%>orderby=downloadNumber">最热</a>
											</li>
										</ul>
									</div>
									<p><%if(c != null) {%><%=c%>&nbsp;/&nbsp;<%}%><%if(l != null){%><%=l%>&nbsp;/&nbsp;<%}%><%=(String)request.getAttribute("order") %></p>
								</div>
							</div>
							<!-- 课程展示 -->
							<div class="resource-list">
								<s:iterator value="#request.resource" var="st">
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
													<li>积分：<s:property value="#st.integral" /></li>
												</ul>
											</div>
										</div>
			
										<div class="aside">
											<div class="nav-other">
												<a class="download-item" href="resourceAction_CollentResource?fileName=<s:property value=" #st.resourceName " />">收藏</a>
											</div>
											<div class="nav-other">
												<a class="download-item" href="resourceAction_getResByComment?resourceId=<s:property value=" #st.resourceId " />">评论</a>
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
													<a class="a-cont" href="resourceAction_showMSSbByPage?currentPage=1">«首页</a>
												</li>
												<li>
													<a class="a-cont" href="resourceAction_showMSSbByPage?currentPage=${pageBean.currentPage-1}">«上一页</a>
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
														<a class="a-cont" href="resourceAction_showMSSbByPage?currentPage=${tp.index}">${tp.index}</a>
													</li>
												</c:otherwise>
											</c:choose>
										</c:forEach>

										<c:choose>
											<c:when test="${pageBean.currentPage!=pageBean.totalPage}">
												<li>
													<a class="a-cont" href="resourceAction_showMSSbByPage?currentPage=${pageBean.currentPage+1}">下一页»</a>
												</li>
												<li>
													<a class="a-cont" href="resourceAction_showMSSbByPage?currentPage=${pageBean.totalPage}">尾页»</a>
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