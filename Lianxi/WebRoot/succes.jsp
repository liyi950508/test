<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>My JSP 'succes.jsp' starting page</title>
  </head>
  
  <body>
    文件名：<s:property value="uploadFileFileName"/><br/>
    文件类型：<s:property value="uploadFileContentType"/><br/>
     视频名：<s:property value="uploadVideoName"/><br/>
    视频类型：<s:property value="uploadVideoContentType"/>
  </body>
</html>
