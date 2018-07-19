<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page language="java" import="java.net.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>${video.getVideoName()}</title>
		<link rel="stylesheet" href="css/Course/plyr.css" />
		<link rel="stylesheet" href="css/Course/video_watch.css" />
		<script src="js/Course/plyr.js"></script>
	</head>

	<body>
		<div class="wrapper">
			<div class="top-page">
				<jsp:include page="/jsp/public/public.jsp"></jsp:include>
			</div>

			<div class="pager-title">
				<div class="title">
					<p>${video.getVideoName()}</p>
				</div>
			</div>
			<div class="pager-main">
				<div class="pager">
					<div class="pager-cont">
						<form id="formId" class="form1" name="form1" action="videoAction_getVideCurrentTime" method="post">
							<div class="video">
								<video id="video" controls="controls">
									<source src="${pageContext.request.contextPath }/${video.getVideoPath()}"></source>
								</video>
								<script>
									plyr.setup();
								</script>
							</div>
							<input type="hidden" id="playtiem" name="playtime" value="" />
							<input type="hidden" id="playtime1" name="currentTime" value="" />
							<input type="hidden" id="videoId" name="videoId" value="${video.getVideoId() }" />
						</form>
					</div>
					<div class="player">
						<ul>
							<li>
								<input type="submit" class="a" id="question-11" value="提问" />
							</li>
							<li><i></i>
								<div class="aa-a"><a href="videoAction_downloadVideo?videoId=${video.getVideoId() }">下载</a></div>
							</li>
							<li><i></i>
								<!--<div class="aa-a"><a href="videoAction_selectVideo?videoId=${video.getVideoId() }">收藏</a></div>-->
								<form id="form2" class="form" action="" method="post">
									<input type="hidden" name="videoId" value="${video.getVideoId() }">
									<input type="submit" class="input-submit" value="收藏" id="submit2"/>
								</form>
							</li>
						</ul>
					</div>
				</div>
				<div class="question">
					<form id="form1" class="form" action="" method="post">
						<input type="hidden" name="videoId" value="${video.getVideoId() }">
						<div class="textarea-input" style="width: 100%; height: 240px; padding-left: 40px;">
							<textarea name="quest" class="input-item" placeholder="填写回答内容" style="width: 200px; height: 240px; color: #35B558; font-size: 16px; border: 1px solid #333;  border-radius: 7px;outline: none;">
							</textarea>
						</div>
						<div style="width: 100%; height: 40px; margin:5px 0 0 70px; padding: 5px;">
							<input type="text" class="intput-item" name="integral" placeholder="答谢积分" style="width: 150px; height: 35px; border: 1px solid #35B558;  border-radius: 6px; font-size: 14px;" />
						</div>
						<div style="width: 100%; height: 40px;">
							<input type="submit" class="input-submit" id="submit1"/>
						</div>
					</form>
				</div>
			</div>
			<div class="lesson">
				<div class="video-list">
					<div class="course-title">
						<p>课程名：${video.getVideoName()}</p>
					</div>
					<div class="course-list">
						<ul>
							<li>学习人数：${video.getStudyNum()}</li>
							<li>作者：${video.getVideoAuthor()}</li>
							<li>更新时间：${video.getUpdateTime()}</li>
							<li>所属：${video.getVideoLabel()}..${video.getCategory()}</li>
						</ul>
					</div>
					<div class="couser-intro">
						<div class="video-intro">
							<p>介绍：</p>
							<p class="p-1">${video.getCourseIntro()}
								<p>
						</div>
					</div>
				</div>
				<div class="video-question">
					<div class="quest-item">
						<div class="quest-title">
							<ul>
								<li>技术问答</li>
								<li><a id="gengduo" href="videoAction_getVideoByQuest?videoId=${video.getVideoId()}">更多</a></li>
							</ul>
						</div>
						<div class="resource-list" style="display: block;">
							<s:iterator value="#request.question" var="st">
								<div class="resource">
									<div class="quest-name">
										<a class="a-cont" href=""><s:property value="#st.questionName" /></a>
									</div>
									<div class="quest-connect">
										<ul>
											<li>提问人：<s:property value="#st.questUser" /></li>
											<li>回答：<s:property value="#st.answerNum" /></li>
											<li>查看：<s:property value="#st.browseNum" /></li>
											<li>时间：<s:property value="#st.questionTime" /></li>
										</ul>
									</div>
								</div>
							</s:iterator>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="div2" style="width: 0px;height: 0px;display:none;"></div>
	</body>
	<script src="js/Course/function.js"></script>
	<script src="js/Course/videoplay.js"></script>
	<script src="js/admin/jquery.min.js"></script>
	<script src="js/jquery.form.js"></script>
	<!--现实和隐藏提问表单-->
	<script>
		$(document).ready(function(e) {
			$("#question-11").click(function(e) {
				$(".question").toggle();
			});
		});
	</script>
	<!--异步提交提问-->
	<script>
		$(function () {
		    $("#submit1").click(function () {
		        var options = {
		            url: "videoAction_addQuestion",
		            target: "#div2",
		            success: function (data) {
		                return alert("提问成功！");
		            }
		        };
		        $("#form1").ajaxForm(options);
		    });
		});
	</script>
	<!--异步提交收藏-->
	<script>
		$(function () {
		    $("#submit2").click(function () {
		        var options = {
		            url: "videoAction_selectVideo",
		            target: "#div2",
		            success: function (data) {
		                return alert("收藏成功！");
		            }
		        };
		        $("#form2").ajaxForm(options);
		    });
		});
	</script>
	<!--定时提交播放时间-->
	<script>
		window.setInterval("getTimes()", 15000);
		function getTimes(){
			$("#formId").ready(function(){
                var formId = $(this).val();
                //发送post请求
                var url="videoAction_getVideCurrentTime";
                $.post(url,$("#formId").serialize(),function(data){
                     return false;
                });
            });
		}
	</script>
	<script>
		var VP;
		$(function() {
			VP = new VideoPlay();
		});
	</script>

</html>