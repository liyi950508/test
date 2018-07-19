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
		<title>回答-问题id=${question.getQuestionId()}</title>
		<link type="text/css" rel="stylesheet" href="css/SheQu/answer.css" />
	</head>

	<body>
		<div class="wraper">
			<div class="top-page">
				<jsp:include page="/jsp/public/public.jsp"></jsp:include>
			</div>
			<div class="middle-page">
				<div class="wrapper">
					<div class="middle-main">
						<div class="div-h">
							<p>${question.getQuestionName()}</p>
						</div>
						<div class="resource-cont">
							<nav>
								<ul>
									<li id="li-2">
										<p class="p-1">提问人：</p>
										<p class="p-2">${question.getQuestUser()}</p>
									</li>
									<li id="li-2">
										<p class="p-1">提问时间：</p>
										<p class="p-2">${question.getQuestionTime()}</p>
									</li>
									<li id="li-2">
										<p class="p-1">标签：</p>
										<p class="p-2">${question.getLabel()}</p>
									</li>
									<li id="li-2">
										<p class="p-1">所属：</p>
										<p class="p-2">${question.getCategory()}</p>
									</li>
									<li id="li-1" class="list-group">
										<p class="p-1">回答</p>
										<p class="p-2">${question.getAnswerNum()}</p>
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
											<s:property value="#st.answerUser" /></li>
										<li id="li-1" class="list-group">
											<s:property value="#st.answerTime" /></li>
										<li>回答</li>
									</ul>
									<div class="a-a">
										<form class="form-cont" id="form2" action="" method="post">
											<input type="hidden" name="questId" value="${question.getQuestionId()}" />
											<input type="hidden" name="answerId" value="<s:property value="#st.answerId" />" />
											<input type="submit" name="submit1" value="有用" id="submit2" />
										</form>
									</div>
								</div>
								<div class="div-s">
									<p><s:property value="#st.answerName" /></p>
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
											<a class="a-cont" href="sheQuAction_getQuestByAnswer?questionId=${question.getQuestionId()}&currentPage=1">«首页</a>
										</li>
										<li>
											<a class="a-cont" href="sheQuAction_getQuestByAnswer?questionId=${question.getQuestionId()}&currentPage=${pageBean.currentPage-1}">«上一页</a>
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
												<a class="a-cont" href="sheQuAction_getQuestByAnswer?questionId=${question.getQuestionId()}&currentPage=${tp.index}">${tp.index}</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${pageBean.currentPage!=pageBean.totalPage}">
										<li>
											<a class="a-cont" href="sheQuAction_getQuestByAnswer?questionId=${question.getQuestionId()}&currentPage=${pageBean.currentPage+1}">下一页»</a>
										</li>
										<li>
											<a class="a-cont" href="sheQuAction_getQuestByAnswer?questionId=${question.getQuestionId()}&currentPage=${pageBean.totalPage}">尾页»</a>
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
				<div class="a-cont">
					<div class="AAA">
						<p>回答问题:</p>
					</div>
					<form id="form1" class="form" action="sheQuAction_addAnswer" method="post">
						<input type="hidden" name="questionId" value="${question.getQuestionId()}">
						<div class="textarea-input">
							<textarea name="answer_1" class="input-item">
							</textarea>
						</div>
						<input type="submit" class="input-submit" id="submit1"/>
					</form>
				</div>
			</div>
		</div>
		<div id="div2" style="width: 0px;height: 0px;display:none;"></div>
	</body>
		<script src="js/admin/jquery.min.js"></script>
		<script src="js/jquery.form.js"></script>
		<!--异步提交回答-->
		<script>
			$(function () {
			    $("#submit2").click(function () {
			        var options = {
			            url: "sheQuAction_AnswerIntegral",
			            target: "#div2",
			            success: function (data) {
			                return alert("问题已解决！");
			            }
			        };
			        $("#form2").ajaxForm(options);
			    });
			});
		</script>
</html>
