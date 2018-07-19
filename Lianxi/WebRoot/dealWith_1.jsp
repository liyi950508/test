<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
    
<%
	String deal = (String)request.getAttribute("deall");
	if(deal.equals("1")){
		out.print("<script language ='javascript'> alert('您不是提问者！');history.go(-1);</script>");
	}
	else if(deal.equals("2")){
		out.print("<script language ='javascript'> alert('您还没有登录，请登录！');window.location.href='/Lianxi/jsp/userInfo/UserLogin.jsp';</script>");
	}
	else if(deal.equals("3")){
		out.print("<script language ='javascript'> alert('提问成功！');history.go(-1);</script>");
	}
	else if(deal.equals("4")){
		out.print("<script language ='javascript'> alert('视频上传成功！');history.go(-1);</script>");
	}
	else if(deal.equals("5")){
		out.print("<script language ='javascript'> alert('视频已存在！');history.go(-1);</script>");
	}
	else if(deal.equals("6")){
		out.print("<script language ='javascript'> alert('视频类型错误！');history.go(-1);</script>");
	}
	else if(deal.equals("7")){
		out.print("<script language ='javascript'> alert('视频收藏成功！');history.go(-1);</script>");
	}
	else if(deal.equals("8")){
		out.print("<script language ='javascript'> alert('资源上传成功！！！');history.go(-1);</script>");
	}
	else if(deal.equals("9")){
		out.print("<script language ='javascript'> alert('资源已存在');history.go(-1);</script>");
	}
	else if(deal.equals("10")){
		out.print("<script language ='javascript'> alert('资源类型不符，支持“paf/doc/txt/zip/tar/ppt”');history.go(-1);</script>");
	}
	else if(deal.equals("11")){
		out.print("<script language ='javascript'> alert('积分不足');history.go(-1);</script>");
	}
	else if(deal.equals("12")){
		out.print("<script language ='javascript'> alert('资源收藏成功！');history.go(-1);</script>");
	}
	else if(deal.equals("13")){
		out.print("<script language ='javascript'> alert('取消收藏成功！');history.go(-1);</script>");
	}
	else if(deal.equals("14")){
		out.print("<script language ='javascript'> alert('您已经收藏了该课程！');history.go(-1);</script>");
	}
	else if(deal.equals("15")){
		out.print("<script language ='javascript'> alert('您已经收藏了该资源！');history.go(-1);</script>");
	}
	else if(deal.equals("16")){
		out.print("<script language ='javascript'> alert('删除成功！');history.go(-1);</script>");
	}

 %>
