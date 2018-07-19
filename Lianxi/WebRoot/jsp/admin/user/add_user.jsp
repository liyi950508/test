<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>添加新用户</title>
        <link rel="stylesheet" type="text/css" href="../../../css/admin/videoUpload.css" />
    </head>
    <body>
    	<div class="middle-page">
				<div class="page-header">
					<p class="current">添加新用户</p>
				</div>
				<div class="upload">
				    <form id="form1" class="form" action="" method="post">
						<div class="form-item">
							<div class="form-cont"><p>用户名：</p></div>
							<div class="item-cont">
								<div class="note">
									<input type="text" class="video-input" name="user.username" placeholder="请输入用户名" style="width: 200px;"/>
								</div>
							</div>
						</div>
						<div class="form-item">
                          <div class="form-cont"><p>密码：</p></div>
                          <div class="item-cont">
                              <div class="note">
	                               <input type="text" class="video-input" name="user.password" placeholder="请输入密码" style="width: 200px;"/>
                              </div>
                          </div>
                       </div>
                        <div class="form-item" id="select_1">
                          <div class="form-cont"><p>职业：</p></div>
                          <div class="item-cont">
                              <div class="note">
	                              <select name="user.userJob" style="width: 200px; height: 45px; border-radius: 7px;">
	                                  <option value="null">选择职业</option>
	                                  <option value="教师">教师</option>
		                              <option value="学生">学生</option>
		                              <option value="研究员">研究员</option>
		                              <option value="程序员">程序员</option>
		                              <option value="其他">其他</option>
	                              </select>
                              </div>
                          </div>
                      </div>
                      <div class="form-item">
                      	<div class="form-submit">
                      		<input id="submit1" type="submit" value="提交" name="" class="item-submit" style="margin-left: -65px;"/>
                      	</div>  
					  </div>
				</form>
			</div>
		</div>
		<div id="div2" style="width: 0px;height: 0px;display:none;"></div>
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery.form.js"></script>
		<!--异步提交提问-->
		<script>
			$(function () {
			    $("#submit1").click(function () {
			        var options = {
			            url: "userAction_addUser",
			            target: "#div2",
			            success: function (data) {
			                return alert("用户添加成功！");
			            }
			        };
			        $("#form1").ajaxForm(options);
			    });
			});
		</script>
 	</body>
 	
</html>