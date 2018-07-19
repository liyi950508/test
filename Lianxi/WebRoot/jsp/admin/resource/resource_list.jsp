<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

	<head>
		<title>资源下载</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="css/admin/resource_list.css" />

	</head>

	<body>
		<div class="wrapper">
			<div class="middle-page">
				<div class="sort">
					<div class="sort-item">
						<ul>
							<li>
								<a class="sort-item-cont" href="resourceAction_showAdminAllByPage">全部资源</a>
							</li>
							<li>
								<a class="sort-item-cont" href="resourceAction_showAdminAllByPage?label=课件ppt">课件ppt</a>
							</li>
							<li>
								<a class="sort-item-cont" href="resourceAction_showAdminAllByPage?label=教辅文档">教辅文档</a>
							</li>
							<li>
								<a class="sort-item-cont" href="resourceAction_showAdminAllByPage?label=期刊杂志">期刊杂志</a>
							</li>
							<li>
								<a class="sort-item-cont" href="resourceAction_showAdminAllByPage?label=源码">源码</a>
							</li>
							<li>
								<a class="sort-item-cont" href="resourceAction_showAdminAllByPage?label=其他">其他</a>
							</li>
						</ul>
					</div>
					<div class="wrap-a">
						<div class="orderby">
							<ul>
								<li class="lesson-new">
									<a class="type-title" href="resourceAction_showAdminAllByPage?<%String l = (String)request.getAttribute(" label ");if(l != null){%>label=<%=l%>&<%}%>orderby=uploadTime">最新</a>
								</li>
								<li class="lesson-new">
									<a class="type-title" href="resourceAction_showAdminAllByPage?<%if(l != null){%>label=<%=l%>&<%}%>&orderby=downloadNumber">最热</a>
								</li>
							</ul>
						</div>
						<p>
							<%if(l != null){%>
							<%=l%>&nbsp;/&nbsp;
							<%}%>
							<%=(String)request.getAttribute("order") %>
						</p>
					</div>
				</div>
				<div class="resource-list">
					<s:iterator value="#request.resource" var="st">
						<div class="resource">
							<div class="resource-cont">
								<dl>
									<dt><a class="a-cont" href="resourceAction_getAdminResByComment?adminId=${admin.getAdminId()}&resourceId=<s:property value="#st.resourceId"/>"><s:property value="#st.resourceName"/></a></dt>
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
									<a class="download-item" href="resourceAction_AdminDeleteResource?resourceId=<s:property value=" #st.resourceId " />">删除</a>
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
										<a class="a-cont" href="resourceAction_showAdminAllByPage?currentPage=1">«首页</a>
									</li>
									<li>
										<a class="a-cont" href="resourceAction_showAdminAllByPage?currentPage=${pageBean.currentPage-1}">«上一页</a>
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
											<a class="a-cont" href="resourceAction_showAdminAllByPage?currentPage=${tp.index}">${tp.index}</a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:choose>
								<c:when test="${pageBean.currentPage!=pageBean.totalPage}">
									<li>
										<a class="a-cont" href="resourceAction_showAdminAllByPage?currentPage=${pageBean.currentPage+1}">下一页»</a>
									</li>
									<li>
										<a class="a-cont" href="resourceAction_showAdminAllByPage?currentPage=${pageBean.totalPage}">尾页»</a>
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