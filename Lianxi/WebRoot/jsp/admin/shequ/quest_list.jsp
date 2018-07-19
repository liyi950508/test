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
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>技术问答</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css" href="css/admin/quest_list.css">

	</head>

	<body>
		<div class="wraper">
			<div class="middle-page">
				<div class="container">
					<!-- 课程分类 -->
					<div class="aside">
						<div class="jiqixuexi">
							<div class="a-a">
								<a href="sheQuAction_ShowAdminQuestByPage?category=${help.JQXX }">机器学习</a>
							</div>
							<ul>
								<li>
									<a href="sheQuAction_ShowAdminQuestByPage?category=${help.JQXX }&label=${help.RGSHL }">人工神金网络</a>
								</li>
								<li>
									<a href="sheQuAction_ShowAdminQuestByPage?category=${help.JQXX }&label=${help.SDXX }">深度学习</a>
								</li>
								<li>
									<a href="sheQuAction_ShowAdminQuestByPage?category=${help.JQXX }&label=${help.SJWJ }">数据挖掘</a>
								</li>
								<li>
									<a href="sheQuAction_ShowAdminQuestByPage?category=${help.JQXX }&label=${help.PYTH }">Python高级</a>
								</li>
								<li>
									<a href="sheQuAction_ShowAdminQuestByPage?category=${help.JQXX }&label=${help.RRRR }">R语言</a>
								</li>
							</ul>
						</div>
						<div class="jiqixuexi">
							<div class="a-a">
								<a href="sheQuAction_ShowAdminQuestByPage?category=${help.MSSB }">模式识别</a>
							</div>
							<ul>
								<li>
									<a href="sheQuAction_ShowAdminQuestByPage?category=${help.MSSB }&label=${help.TXCL }">图像处理</a>
								</li>
								<li>
									<a href="sheQuAction_ShowAdminQuestByPage?category=${help.MSSB }&label=${help.HGSF }">回归算法</a>
								</li>
								<li>
									<a href="sheQuAction_ShowAdminQuestByPage?category=${help.MSSB }&label=${help.ZRYY }">自然语言</a>
								</li>
								<li>
									<a href="sheQuAction_ShowAdminQuestByPage?category=${help.MSSB }&label=${help.JHBH }">几何变换</a>
								</li>
								<li>
									<a href="sheQuAction_ShowAdminQuestByPage?category=${help.MSSB }&label=${help.XTX }">形态学</a>
								</li>
							</ul>
						</div>
					</div>
					<div class="main">
						<div class="main-cont">
							<div class="quest">
								<div class="qsort-left">
									<div class="question">
										<p><%String c = (String)request.getAttribute("category");if(c!= null){%><%=c%>&nbsp;/&nbsp;<%}%><%String l = (String)request.getAttribute("label");if(l != null){%><%=l%>&nbsp;/&nbsp;<%}%>
										<%=(String)request.getAttribute("order") %></p>
									</div>
								</div>
								<div class="qsort-right">
									<nav>
										<ul>
											<li><a href="sheQuAction_ShowAdminQuestByPage">全部</a></li>
											<li>
												<a href="sheQuAction_ShowAdminQuestByPage?<%if(c!= null){%>category=<%=c%>&<%}%><%if(l != null){%>label=<%=l%>&<%}%>orderby=questionTime">新问题</a>
											</li>
											<li>
												<a href="sheQuAction_ShowAdminQuestByPage?<%if(c!= null){%>category=<%=c%>&<%}%><%if(l != null){%>label=<%=l%>&<%}%>orderby=answerNum">最热</a>
											</li>
										</ul>
									</nav>
								</div>
							</div>
							<div class="ask-list">
								<s:iterator value="#request.resource" var="st">
									<div class="resource">
										<div class="resource-cont">
											<ul>
												<li id="li-1" class="list-group">
													<p class="p-1"><s:property value="#st.answerNum" /></p>
													<p class="p-2">回答</p>
												</li>
												<li id="li-2">
													<p class="p-3">
														<a href="sheQuAction_getAdminQuestByAnswer?questionId=<s:property value="#st.questionId" />"><s:property value="#st.questionName" /></a>
													</p>
													<p class="p-4">
														<s:property value="#st.questUser" />
														<s:property value="#st.questionTime" />
													</p>
													
												</li>
											</ul>
											<div class="delete"><a href="sheQuAction_deleteQuestion?questionId=<s:property value="#st.questionId" />" style="text-decoration: none;color: #000000;" >删除</a></div>
										</div>
									</div>
								</s:iterator>
							</div>
							<div class="page-FeiYe">
								<div class="feiye-nav">
									<ul class="fenyeul">
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
													<a class="a-cont" href="sheQuAction_ShowAdminQuestByPage?currentPage=1">«首页</a>
												</li>
												<li>
													<a class="a-cont" href="sheQuAction_ShowAdminQuestByPage?currentPage=${pageBean.currentPage-1}">«上一页</a>
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
														<a class="a-cont" href="sheQuAction_ShowAdminQuestByPage?currentPage=${tp.index}">${tp.index}</a>
													</li>
												</c:otherwise>
											</c:choose>
										</c:forEach>

										<c:choose>
											<c:when test="${pageBean.currentPage!=pageBean.totalPage}">
												<li>
													<a class="a-cont" href="sheQuAction_ShowAdminQuestByPage?currentPage=${pageBean.currentPage+1}">下一页»</a>
												</li>
												<li>
													<a class="a-cont" href="sheQuAction_ShowAdminQuestByPage?currentPage=${pageBean.totalPage}">尾页»</a>
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