<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	 <head>  
		  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		   <title>资源上传</title>
		   <link type="text/css" rel="stylesheet" href="../../css/public.css" />
		   <link type="text/css" rel="stylesheet" href="../../css/UpandDownResource/UpResource.css" />
	 </head>
  	 <body>
	  	 <div class="public">
		  	<div class="top-page">
				<jsp:include page="/jsp/public/public.jsp"></jsp:include>
			</div>
			<div class="middle-page">
				<div class="page-header">
					<p>资源上传</p>
				</div>
				<div class="upload">
				    <form class="form" action="resourceAction_uploadResource" enctype="multipart/form-data" method="post">
						<div class="form-item">
							<div class="form-cont"><p>选择资源</p></div>
							<div class="item-cont">
						    	<div class="note">
						    		<input type="file" name="uploadFile" value="资源上传" />
<!--						    		<span>（资源大小不超过50M）</span>-->
						    	</div>
								
						   </div>
						</div> 
						<div class="form-item">
							<div class="form-cont"></div>
							<div class="item-cont">
								<div class="note">
									<label for="t_all"><input type="radio" id="radio_1" name="resource.type" value="机器学习" class="type-check" checked="">机器学习</label>
									<!--<label for="t_doc"><input type="radio" name="resource.resourceType" value="视频" class="type-check">视频</label>-->
									<label for="t_ppt"><input type="radio" id="radio_2" name="resource.type" value="模式识别" class="type-check">模式识别</label>
								</div>
							</div>
						</div>
						<div class="form-item" id="select_1" style="display: block;">
                          <div class="form-cont"><p>所属分类</p></div>
                          <div class="item-cont">
                              <div class="note">
	                              <select name="resource.category">
	                                  <option value="null">选择分类</option>
	                                  <option value="神经网络">神经网络</option>
	                                  <option value="深度学习">深度学习</option>
	                                  <option value="数据挖掘">数据挖掘</option>
	                                  <option value="Python高级">Python高级</option>
	                                  <option value="R语言">R语言</option>
	                              </select>
                              </div>
                          </div>
                      </div>
                      <div class="form-item" id="select_2" style="display: none;">
                          <div class="form-cont"><p>所属分类</p></div>
                          <div class="item-cont">
                              <div class="note">
	                              <select name="resource.category" id="classify_1">
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
                      <div class="form-item">
                          <div class="form-cont"><p>标签</p></div>
                          <div class="item-cont" id="tags">
                          	  <div class="note">
                          	  		<select name="resource.label" id="classify_1">
	                                  <option value="null">选择标签</option>
	                                  <option value="课件ppt">课件ppt</option>
	                                  <option value="教辅文档">教辅文档</option>
	                                  <option value="笔记">笔记</option>
	                                  <option value="期刊杂志">期刊杂志</option>
	                                  <option value="源码">源码</option>
	                                  <option value="其他">其他</option>
	                              </select>
	                      	  </div>
                          </div>
                      </div>
                      <div class="form-item">
                          <div class="form-cont"><p>积分</p></div>
                          <div class="item-cont" id="tags">
                          	  <div class="note">
                          	  		<select name="integral">
	                                  <option value="0">0</option>
	                                  <option value="2">2</option>
	                                  <option value="3">3</option>
	                                  <option value="5">5</option>
	                                  <option value="7">7</option>
	                                  <option value="10">10</option>
	                                  <option value="15">15</option>
	                              </select>
	                      	  </div>
                          </div>
                      </div>
                      <div class="form-intro">    
                      	  <div class="form-cont"><p>简介</p></div>    
                      	  <div class="item-cont"> 
                      	  	  <div class="note">
		                      	<textarea name="resource.intro" class="textarea-input" tabindex="16" placeholder="填写简介，能帮助资源传播更广~~~200字以内"></textarea>
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
		<div class="JiangLiTiShi">
			<div class="JiangLiTiShi-header">
				<h4>上传资源奖励</h4>			
			    </div>
					<div class="JiangLiTiShi-aside">
						<dl>
							<dt>文档</dt>
							<ol>
								<li>您可以上传觉得不错的文档、课件ppt，以及压缩文件等，文件大小≤200MB</li>
								<li>上传文件名的重复的资源不会审核成功,上传内容重复可能不会审核通过</li>
							</ol>
						</dl>	
						<dl>
							<dt>积分获取</dt>
							<ol>
								<li>成功上传一个资源获得5积分</li>
								<li>为资源设置积分数，当有人下载时获取相应积分数，下载者扣除相应积分数</li>
								<li>回答问题获取奖励积分积分</li>
								<li>评论资源奖励1积分</li>
							</ol>
						</dl>				
					</div>
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
