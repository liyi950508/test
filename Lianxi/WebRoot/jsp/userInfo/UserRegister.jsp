<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>用户注册</title>
 
        <link type="text/css" rel="stylesheet" href="user_js/UserRegister.css">
        <script type="text/javascript">
			function checkPW(pw1){
				var pw = document.getElementById("pw").value;
				var pwspan = document.getElementById("pwspan");
				var flag;
				if(pw1 != pw){
					pwspan.innerHTML = "* 密码错误".fontcolor("red").fontsize("2px");
					flag = false;
				}else{
					pwspan.innerHTML = "";
					flag = true;
				}
				return flag;
			}
		function checkNPW(){
			var npw = document.getElementById("pwd").value;
			var npwspan = document.getElementById("pwdspan");
			var flag;
			var reg = /^[0-9]{6,11}$/i;
			if(npw == ""){
				npwspan.innerHTML = "* 密码不能为空".fontcolor("red").fontsize("2px");
				flag = false;
			}
			else if(!reg.test(npw)){
				npwspan.innerHTML = "* 密码只能是数字共6到11位".fontcolor("red").fontsize("2px");
				flag = false;
			}
			else{
				npwspan.innerHTML = "";
				flag = true;
			}
			return flag;
		}
		function checkQRPW(){
			var qrpw = document.getElementById("pwd").value;
			var qrspan = document.getElementById("pwd1span");
			var npw = document.getElementById("pwd1").value;
			var flag;
			if((npw == qrpw) && qrpw != ""){
				qrspan.innerHTML = "";
				flag = true;
			}else{
				qrspan.innerHTML = "* 两次密码不一致".fontcolor("red").fontsize("2px");
				flag = false;
			}
			return flag;
		}
		function checkForm(pw1){
			if(checkPW(pw1) && checkNPW() && checkQRPW()){
				return true;
			}else{
				return false;
			}
		}
		
	</script>
	</head>
	<body>
		<div class="register">
			<header class="register-header">
				<jsp:include page="/jsp/public/public.jsp"></jsp:include>
			</header>
			<div class="container">
				<div class="container-sign">
					<div class="register-left">
						<div class="zhuce" style="color: #32B757;">新用户注册</div>
						<div class="line"></div>
						<form class="zhuce-form" action="userAction_UserRegister" method="post">
							<div class="form-item">
								<div class="form-cont">
									<input type="text" name="user.username" class="input-item" tabindex="1" placeholder="请输入用户名" />
								</div>
							</div>
							<div class="form-item">
								<div class="form-cont">
									<input type="password" id="pwd" name="user.password" class="input-item" tabindex="2" placeholder="请输入密码" onclick="checkNPW()" />
									<span id="pwdspan"></span>
								</div>
							</div>
							<div class="form-item">
								<div class="form-cont">
									<input type="text" id="pwd1" name="passworg1" class="input-item" tabindex="3" placeholder="请再次输入密码" onblur="checkQRPW()" />
									<span id="pwd1span"></span>
								</div>
							</div>
							<div class="form-item">
								<div class="form-cont">
									<select name="user.userJob" id="userJob" class="input-item">
		                                <option value="null">请选择职业</option>
		                                <option value="教师">教师</option>
		                                <option value="学生">学生</option>
		                                <option value="研究员">研究员</option>
		                                <option value="程序员">程序员</option>
		                                <option value="其他">其他</option>
	                                </select>
								    <span id="Jobspan"></span>
								</div>
							</div>
							<div class="form-item">
								<div class="form-cont">
                        			<input id="checkbox" type="checkbox" name="treaty" checked="checked" value="treaty">
                        			<span id="checkboxSpan">同意</span><a href="http://www.jikexueyuan.com/help/service.html" target="_blank" class="treaty">网站用户协议</a>
                    			</div>
                    		</div>
							<div class="form-item">
								<div class="form-cont">
									<input id="submit" type="submit" name="button"  value="注册" class="btn" tabindex="5" style="background-color:#32B757;width:100%;color:#fff;font-size:17px;border: 1px solid #35B558; border-radius: 7px;" onclick="checkForm(pw1)">
									<span id="submit1"></span>
								</div>
							</div>
						</form>
					</div>
					<div class="register-right">
						<div class="right-main">
							<div class="right-cont">
								"已有账号"：
								<a class="a-cont" href="jsp/userInfo/UserLogin.jsp">点击登录</a>
							</div>
						</div>
						<div class="go-home">
							<div class="go">
								<a class="go-cont" href="jsp/shouye.jsp">返回首页</a>
							</div>
                        </div>
					</div>
				</div>	
			</div>
		</div>
	</body>
</html>