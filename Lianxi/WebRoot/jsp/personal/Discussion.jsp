<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>个人中心-问题讨论</title>
        <link type="text/css" rel="stylesheet" href="../../css/personal/Personal.public.css" />
    </head>
    <body>
    	<div class="wraper">
	    	<div class="top-wraper">
	    		<jsp:include page="/jsp/public/public.jsp"></jsp:include>
	    	</div>
				
			<div class="wrapper">
				<div class="directory">
					<ul>
						<li><a href="jsp/personal/My_Account.jsp">我的账号</a></li>
						<li><a href="jsp/personal/My_Course.jsp">我的课程</a></li>
						<li><a href="jsp/personal/My_Notes.jsp">我的笔记</a></li>
						<li><a href="jsp/personal/My_News.jsp">我的消息</a></li>
						<li><a href="userSelectAction_showSeclectByPage">我的收藏</a></li>
						<li><a href="userDownAction_showDownByPage">我的下载</a></li>
						<li><a href="resourceAction_showUpUserByPage">我的上传</a></li>
						<li><a href="sheQuAction_showUserQuest?str=isSolve">我的问题</a></li>
						<li><a href="jsp/UpAndDownResource/UpResource.jsp">资源上传</a></li>
						<!--<li><a href="../jsp/personal/Discussion.jsp">问题讨论</a></li>-->
						<!--<li><a href="../jsp/personal/My_Integral.jsp">我的积分</a></li>-->
					</ul>
				</div>
				<div class="show">
					<div class="show-top">
						
					</div>
				</div>
			</div>
		</div>
 	</body>
</html>