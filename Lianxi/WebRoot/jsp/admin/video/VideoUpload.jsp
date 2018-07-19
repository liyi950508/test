<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>视频上传</title>
        <link rel="stylesheet" type="text/css" href="../../../css/admin/videoUpload.css" />
    </head>
    <body>
    	<div class="middle-page">
				<div class="page-header">
					<p class="current">视频上传</p>
				</div>
				<div class="upload">
				    <form class="form" action="videoAction_addVideo" enctype="multipart/form-data" method="post">
						<div class="form-item">
							<div class="form-cont"><p>选择资源</p></div>
							<div class="item-cont">
						    	<div class="note">
						    		<input type="file" class="video-up" name="uploadFile" value="视频上传" />
						    	</div>
						   </div>
						</div> 
						<div class="form-item">
							<div class="form-cont"></div>
							<div class="item-cont">
								<div class="note">
									<input type="text" class="video-input" name="videoAuthor" placeholder="作者" />
								</div>
							</div>
						</div>
						<div class="form-item">
                          <div class="form-cont"><p>所属分类</p></div>
                          <div class="item-cont">
                              <div class="note">
	                                <label for="t_all"><input type="radio" id="radio_1" name="category" value="机器学习" class="type-check" checked="">机器学习</label>
									<label for="t_ppt"><input type="radio" id="radio_2" name="category" value="模式识别" class="type-check">模式识别</label>
                              </div>
                          </div>
                     </div>
                      <div class="form-item" id="select_1" style="display: block;">
                          <div class="form-cont"><p>标签</p></div>
                          <div class="item-cont">
                              <div class="note">
	                              <select name="videoLabel">
	                                  <option value="null">选择分类</option>
	                                  <option value="人工神金网络">神经网络</option>
	                                  <option value="深度学习">深度学习</option>
	                                  <option value="数据挖掘">数据挖掘</option>
	                                  <option value="Python高级">Python高级</option>
	                                  <option value="R语言">R语言</option>
	                              </select>
                              </div>
                          </div>
                      </div>
                      <div class="form-item" id="select_2" style="display: none;">
                          <div class="form-cont"><p>标签</p></div>
                          <div class="item-cont">
                              <div class="note">
	                              <select name="videoLabel" id="classify_1">
	                                  <option value="null">选择分类</option>
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
                      	  <div class="form-cont"><p>简介</p></div>    
                      	  <div class="item-cont"> 
                      	  	  <div class="note">
		                      	<textarea name="intro" class="textarea-input" tabindex="16" placeholder="填写简介，能帮助资源传播更广~~~200字以内"></textarea>
		                      </div>
		                  </div>
                      </div>
                      <div class="form-item">
                      	<div class="form-submit">
                      		<input type="submit" value="确认上传" name="" class="item-submit"/>
                      	</div>  
					  </div>
				</form>
				
			</div>
		</div>
		<script src="../../../js/admin/jquery.min.js"></script>
		<script src="../../../js/jquery.form.js"></script>
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