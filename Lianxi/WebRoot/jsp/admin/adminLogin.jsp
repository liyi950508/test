<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD 	XHTML 4.01 Transitional//EN">
<html>

	<head>
		<base href="<%=basePath%>">

		<title>管理员登录</title>
		<meta charset="utf-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	
		<link type="text/css" rel="stylesheet" href="css/admin/adminLogin.css">
		<script type="text/javascript">
			function checkId() {
				var adminName = document.getElementById("adminName").value;
				var idspan = document.getElementById("idspan");
//				var reg = /^[0-9]{2,11}$/i;

				var flag;
				if(adminName == "") {
					idspan.innerHTML = "* 管理员名称没有填写".fontcolor("red").fontsize("2px");
					flag = false;
				} 
//				else if(!reg.test(uid)) {
//					idspan.innerHTML = "* 管理员编号只能是数字".fontcolor("red").fontsize("2px");
//					flag = false;
//				} 
				else {
					idspan.innerHTML = "";
					flag = true;
				}
				return flag;

			}

			function checkPW() {
				var password = document.getElementById("adminPassword").value;
				var pwspan = document.getElementById("pwspan");
				var flag = false;
				if(password == "") {
					pwspan.innerHTML = "* 密码不能为空".fontcolor("red").fontsize("2px");
					flag = false;
				} else {
					pwspan.innerHTML = "";
					flag = true;
				}
				return flag;
			}

			function checkForm() {
				if(checkId() && checkPW()) {
					return true;
				} else {
					return false;
				}
			}
		</script>
	</head>

	<body>
		<div class="div1">
			<div class="div2_1">
				<div class="div3_1">
					<img src="${pageContext.request.contextPath }/images/bzxylogin.jpg" />
				</div>
				<div class="div3_2">
					<span>机器学习在线学习网站</span>
				</div>
			</div>
			<div class="div2_2">
				<center>

					<form id="form1" action="${pageContext.request.contextPath }/adminAction_adminLogin" method="post" onsubmit="return checkForm()">
						<table border="0px">
							<tr>
								<td class="td-1" colspan="3">管理员登录</td>
							</tr>
							<tr>
								<td class="td-2">用户名：</td>
								<td class="td-3">
									<input class="input-1" id="adminName" type="text" name="adminName" onblur="checkId()" />
								</td>
								<td class="td-4"><span id="idspan"></span></td>
							</tr>
							<tr>
								<td class="td-5">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
								<td class="td-6">
									<input class="input-2" id="adminPassword" type="password" name="adminPassword" onblur="checkPW()" />
								</td>
								<td class="td-7"><span id="pwspan"></span></td>
							</tr>
							<tr>
								<td class="td-8" colspan="3">
									<input class="input-3" type="submit" value="登录" />
									<input class="input-3" type="reset" value="重置" />
								</td>
							</tr>
						</table>
					</form>
				</center>
				<span id="errspan" style="test"><s:actionerror/></span>
			</div>
		</div>
	</body>

</html>