<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
		<link rel="stylesheet" type="text/css" href="css/admin/videoEdit.css" />
	</head>

	<body>
		<div class="middle-page">
			<div class="page-header">
				<p class="current">修改视频资料</p>
			</div>
			<div class="upload">
				<form class="form" action="videoAction_videoUpDate" enctype="multipart/form-data" method="post">
					<div class="form-item">
						<div class="form-cont">
							<p>视频名称</p>
						</div>
						<div class="item-cont">
							<div class="note">
								<input type="text" class="input-item" name="videoName" value="${video1.getVideoName()}" />
								<input type="hidden" name="videoId" value="${video1.getVideoId()}" />
							</div>
						</div>
					</div>
					<div class="form-item">
						<div class="form-cont">
							<p>视频作者</p>
						</div>
						<div class="item-cont">
							<div class="note">
								<input type="text" class="input-item" name="videoAuthor" value="${video1.getVideoAuthor()}" />
							</div>
						</div>
					</div>
					<div class="form-item">
						<div class="form-cont">
							<p>学习人数</p>
						</div>
						<div class="item-cont">
							<div class="note">
								<input type="text" class="input-item" name="studyNum_1" value="${video1.getStudyNum()}" />
							</div>
						</div>
					</div>
					
					<div class="form-item">
						<div class="form-cont">
							<p>视频所属</p>
						</div>
						<div class="item-cont">
							<div class="note">
									 <label for="t_all"><input type="radio" id="radio_1" name="category" value="${video1.getCategory()}" class="type-check" checked="">机器学习</label>
									<label for="t_ppt"><input type="radio" id="radio_2" name="category" value="${video1.getCategory()}" class="type-check">模式识别</label>
							</div>
						</div>
					</div>

					<div class="form-item" id="select_1" style="display: block;">
                          <div class="form-cont"><p>视频标签</p></div>
                          <div class="item-cont">
                              <div class="note">
	                              <select name="videoLabel"  class="input-item">
	                                  <option value=" value="null">${video1.getVideoLabel()}</option>
	                                  <option value="人工神金网络">人工神金网络</option>
	                                  <option value="深度学习">深度学习</option>
	                                  <option value="数据挖掘">数据挖掘</option>
	                                  <option value="Python高级">Python高级</option>
	                                  <option value="R语言">R语言</option>
	                              </select>
                              </div>
                          </div>
                      </div>
                      <div class="form-item" id="select_2" style="display: none;">
                          <div class="form-cont"><p>视频标签</p></div>
                          <div class="item-cont">
                              <div class="note">
	                              <select name="videoLabel" class="input-item" id="classify_1">
	                                  <option value=" value="mi;;">${video1.getVideoLabel()}</option>
	                                  <option value="图像处理">图像处理</option>
	                                  <option value="回归算法">回归算法</option>
	                                  <option value="自然语言">自然语言</option>
	                                  <option value="几何变换">几何变换</option>
	                                  <option value="形态学">形态学</option>
	                              </select>
                              </div>
                          </div>
                      </div>
					<div class="form-intro">
						<div class="form-cont">
							<p>视频简介</p>
						</div>
						<div class="item-cont">
							<div class="note">
								<textarea name="intro" class="textarea-input" value="${video1.getCourseIntro()}" style="width: 300px; height: 80px; border: 1px solid #737373; border-radius: 4px;"></textarea>
							</div>
						</div>
					</div>
					<div class="form-item">
						<div class="form-submit">
							<input type="submit" value="提交修改" name="" class="item-submit" />
						</div>
					</div>
				</form>

			</div>
		</div>
		<script src="js/admin/jquery.min.js"></script>
		<script src="js/jquery.form.js"></script>
		<script>
			$(document).ready(function(e) {
				$("#radio_2").click(function(e) {
					$("#select_1").toggle();
					$("#select_2").toggle();
				});
			});
			$(document).ready(function(e) {
				$("#radio_1").click(function(e) {
					$("#select_1").toggle();
					$("#select_2").toggle();
				});
			});
		</script>
	</body>

</html>