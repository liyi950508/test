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
	 	<title>用户：登录</title>
	 	
	 	<link type="text/css" rel="stylesheet" href="css/UserInfo/UserLogin.css" />
     	<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
     	<link type="text/css" href="user_js/bootstrap.min.css" rel="stylesheet">
      	<link type="text/css" rel="stylesheet" href="user_js/UserLogin.css">
	</head>
	<body>
	
	    <div class="container">
	    	<div class="a-a">
	    		<jsp:include page="/jsp/public/public.jsp"></jsp:include>
	    	</div>
	        <div class="bigbg">
	            <div class="content">
	                <div class="content-left">
	                    <div class="main">
	                        <p style="font-size: 20px; color: #32B757">登录</p>
	                        <div class="line"></div>
	                    </div>
	                    <form class="zhuce-form" action="userAction_userLogin" method="post">
							<div class="form-item">
								<div class="form-cont">
									<input id="username" type="text" name="username" class="input-item" tabindex="1" placeholder="请输入用户名" />
								</div>
							</div>
							<div class="form-item">
								<div class="form-cont">
									<input id="password" type="password" name="password" class="input-item" tabindex="2" placeholder="请输入密码" />
								</div>
							</div>
	                        <div class="jizhumima">
	                            <label class="checkbox">
	                                <input type="checkbox"  class="chexbox"> 记住密码
	                            </label>
	                            <a href="" class="forget">忘记密码？</a>
	                        </div>
	                        <input type="submit" value="登录" class="btn " style="background-color:#32B757;width:90%;color:#fff;font-size:17px;;">
	                    </form>
	                </div>
	                <div class="register-right">
						<div class="right-main">
							<div class="right-cont">
								"没有账号"：
								<a class="a-cont" href="jsp/userInfo/UserRegister.jsp">点击注册</a>
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