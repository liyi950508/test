<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<%
	String deal = (String)request.getAttribute("deal");
	if(deal.equals("1")){
		out.print("<script language ='javascript'> alert('用户名或密码不能为空！');history.go(-1);</script>");
	}
	else if(deal.equals("2")){
		out.print("<script language ='javascript'> alert('用户名长度为2~~15个字符！');history.go(-1);</script>");
	}
	else if(deal.equals("3")){
		out.print("<script language ='javascript'> alert('密码长度为6~~12个字符！');history.go(-1);</script>");
	}
	else if(deal.equals("4")){
		out.print("<script language ='javascript'> alert('用户名已经存在！');history.go(-1);</script>");
	}
	else if(deal.equals("5")){
		out.print("<script language ='javascript'> alert('用户注册成功！');window.location.href='/Lianxi/jsp/userInfo/UserLogin.jsp';</script>");
	}
	else if(deal.equals("6")){
		out.print("<script language ='javascript'> alert('登陆成功！');history.go(-2);</script>");
	}
	else if(deal.equals("7")){
		out.print("<script language ='javascript'> alert('用户名不存在！');history.go(-1);</script>");
	}
	else if(deal.equals("8")){
		out.print("<script language ='javascript'> alert('登录失败：用户名或密码错误！！！');history.go(-1);</script>");
	}
	else if(deal.equals("9")){
		out.print("<script language ='javascript'> alert('您已经登录！');history.go(-1)</script>");
	}
	else if(deal.equals("10")){
		out.print("<script language ='javascript'> alert('密码修改成');history.go(-1);</script>");
	}
	else if(deal.equals("11")){
		out.print("<script language ='javascript'> alert('原密码错误');history.go(-1);</script>");
	}
 
 %>
    
    
