<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html"%>
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
		<title></title>
		<meta charset="utf-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link rel="stylesheet" type="text/css" href="css/public.css" />

	</head>

	<body>
		<div class="public">
			<div class="top-page">
				<div class="top-page-left">
					<nav>
						<ul>
							<li>
								<a href="sheQuAction_getShouYe" class="nav-item">首页</a>
							</li>
							<li>
								<a href="videoAction_ShowAllVideoByPage" class="nav-item">课程</a>
							</li>
							<li>
								<a href="resourceAction_showJQXxByPage" class="nav-item">机器学习</a>
							</li>
							<li>
								<a href="resourceAction_showMSSbByPage" class="nav-item">模式识别</a>
							</li>
							<li>
								<a href="sheQuAction_ShowQuestionByPage" class="nav-item">社区：讨论</a>
							</li>
							<li>
								<a href="resourceAction_showAllByPage" class="nav-item">资源下载</a>
							</li>
						</ul>
					</nav>
				</div>
				<div class="top-page-right">
					<form action="sheQuAction_sreachQuestionByPage" method="post">
						<div class="search">
							<input type="text" id="se" name="search" class="search-input" value="<%=(String)request.getAttribute("search")%>" placeholder="请输入搜索内容" />
							<span class="search-icon" id="search-bottom"></span>
						</div>
					</form>
					<div class="nav-other">
						<input type="hidden" id="username" name="username" value="${user.getUsername()}" />
						<div class="centerbox" id="userLogin" style=" margin-left: 10px;">
							<div>
								<a href="userVideoAction_showSeclectByPage" style="margin: 0 0 5px 0;color: #35B558; font-size: 18px;">
								个人中心：${user.getUsername()}</a>
							</div>
							<div>
								<span style="margin: 0 0 0 0;">
								<a href="userAction_userExit" style="color: #35B558; font-size: 18px;">
									退出登录</a></span>
							</div>
						</div>
						<div class="user-info" id="userNotLogin" >
							<a href="jsp/userInfo/UserLogin.jsp" class="user-info-item">登录</a>
							<i></i>
							<a href="jsp/userInfo/UserRegister.jsp" class="user-info-item">注册</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="js/admin/jquery.min.js"></script>
		<script src="js/jquery.form.js"></script>
		<script>
			$("#username").ready(function() {
				var username = document.getElementById("username").value;
				if(username.length == "" || username.length == undefined || username.length == null) {
					document.getElementById("userNotLogin").style.display = "block";
					document.getElementById("userLogin").style.display = "none";
				} else {
					document.getElementById("userNotLogin").style.display = "none";
					document.getElementById("userLogin").style.display = "block";
//					$.ajax({
//						type: "post",
//						url: "sheQuAction_getuserNewsNum",
//						success: function(data) {
//							return alert("<%=(String)request.getAttribute("mesage1") %>");
//						},
//					});
				};
			});
		</script>
		<!--提交搜索-->
		<script>
			function getclick() {
				$("#form1").click(function(){
	                var form1 = $(this).val();
	                //发送post请求
	                var url="videoAction_addQuestion";
	                $.post(url,$("#form1").serialize(),function(data){
	                    return false;
	                });
	            });
			};
		</script>

		<!--页面自动刷新-->
		<!--<script>
			function reurl(){
				url = location.href; //把当前页面的地址赋给变量 url
				var times = url.split("?"); //分切变量 url 分隔符号为 "?"
				if(times[1] != 1){ //如果?后的值不等于1表示没有刷新
					url += "?1"; //把变量 url 的值加入 ?1
					self.location.replace(url); //刷新页面
				}
			}
			onload=reurl
		</script>-->
		
	</body>

</html>