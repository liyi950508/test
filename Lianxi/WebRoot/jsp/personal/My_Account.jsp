<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>个人中心-我的账号</title>
        <link rel="stylesheet" type="text/css" href="css/personal/Personal.My_Account.css">
        <script type="text/javascript">
			function checkNPW(){
				var npw = document.getElementById("pwd").value;
				var npwspan = document.getElementById("newpwd");
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
				var qrspan = document.getElementById("newpwd_1");
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
			function checkForm(){
				if(checkNPW() && checkQRPW()){
					return true;
				}else{
					return false;
				}
			}
			
		</script>
    </head>
    <body>
    	<div class="wraper">
	    	<div class="top-wraper">
	    		<jsp:include page="/jsp/public/public.jsp"></jsp:include>
	    	</div>
				
			<div class="wrapper">
				<div class="directory">
					<ul>
						<li><a href="userAction_UserEdit">我的账号</a></li>
						<li><a href="userVideoAction_showSeclectByPage">我的视频</a></li>
						<li><a href="sheQuAction_getuserNews">我的消息</a></li>
						<li><a href="userSelectAction_showSeclectByPage">我的收藏</a></li>
						<li><a href="userDownAction_showDownByPage">我的下载</a></li>
						<li><a href="resourceAction_showUpUserByPage">我的上传</a></li>
						<li><a href="sheQuAction_showUserQuest?str=isSolve">我的问题</a></li>
						<li><a href="jsp/UpAndDownResource/UpResource.jsp">资源上传</a></li>
					</ul>
				</div>
				<div class="show">
					<div class="show-top">
						<p>我的账号</p>
					</div>
					<div class="show-middle">
						<div id="item" class="show-item">
							<form class="updatepwd" action="userAction_UserUpDate" method="post">
									<div class="div1"><p>个人资料</p></div>
									<input type="hidden" name="userId" value="${user.getId()}" />
									<div class="form-item">
					    				<div class="form-cont"><p>用户名:</p></div>
										<input type="text" name="username" class="input-item" value="${user.getUsername() }"/>
										<span id="userspan" style="color: red"></span>
					    			</div>
					    			<div class="form-item">
					    				<div class="form-cont"><p>积分:</p></div>
										<input type="text" name="integral" class="input-item" readonly="readonly" value="${user.getIntegral() }"/>
										<span id="userspan" style="color: red"></span>
					    			</div>
									<div class="form-item">
					    				<div class="form-cont"><p>职业:</p></div>
										<select name="userJob" id="userJob" class="input-item">
				                            <option value="${user.getUserJob() }">${user.getUserJob() }</option>
				                            <option value="教师">教师</option>
				                            <option value="学生">学生</option>
				                            <option value="研究员">研究员</option>
				                            <option value="程序员">程序员</option>
				                            <option value="其他">其他</option>
			                            </select>
										<span id="Jobspan"></span>
					    			</div>
					    			<div class="form-item">
										<input class="form-submit" id="submit" type="submit" name="button"  value="确认修改"/>
										<span id="submit1"></span>
					    			</div>
							</form>
						</div>
						<div id="item1" class="show-item">
							<form id="updatepwd" class="updatepwd" action="userAction_UserUpDatePassword" method="post">
				    			<div class="div1"><p>修改密码</p></div>
				    			<input type="hidden" name="userId" value="${user.getId()}" />
				    			<div class="form-item">
				    				<div class="form-cont"><p>原密码:</p></div>
									<input type="password" name="password" class="input-item" placeholder="请输入原密码" />
									<span id="pwdspan"></span>
				    			</div>
				    			<div class="form-item">
				    				<div class="form-cont"><p>新密码:</p></div>
									<input id="pwd" type="password" name="newPassword" class="input-item" placeholder="请输入新密码" onblur="checkNPW()"/>
									<span id="newpwd"></span>
				    			</div>
				    			<div class="form-item">
				    				<div id="pwd1" class="form-cont"><p>确认密码:</p></div>
									<input type="password" name="newPassword1" class="input-item" placeholder="请再次输入新密码" onblur="checkQRPW()"/>
									<span id="newpwd_1"></span>
				    			</div>
				    			<div class="form-item">
									<input class="form-submit" id="submit" type="submit" name="button"  value="保存新密码" onclick="checkForm()"/>
									<span id="submit1"></span>
				    			</div>
				    		</form>
						</div>
						
					</div>
					
				</div>
			</div>
		</div>
 		</div>
 	</body>
</html>