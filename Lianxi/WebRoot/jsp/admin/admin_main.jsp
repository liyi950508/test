<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>网站后台管理</title>
    <link type="text/css" rel="stylesheet" href="css/admin/index.css" />
    <link type="text/css" rel="stylesheet" href="css/admin/easyui.css" />
    <link type="text/css" rel="stylesheet" href="css/admin/demo.css" />
    <!--<link type="text/css" rel="stylesheet" href="css/admin/index.min.css" />-->
    <script src="js/admin/jquery.min.js"></script>
    <script src="js/admin/jquery.easyui.min.js"></script>
    <script>
        $(function () {
            bindEvent();
        });
        function bindEvent() {
            $(".btn_menu").click(function () {
                var title = $(this).text();
                var url = $(this).attr("url");
                var isSelect = $("#container").tabs('exists', title);
                if (isSelect) {
                    $("#container").tabs('select', title);
                    return;
                }
                $("#container").tabs('add', {
                    title: title,
                    content: CreateContent(url),
                    closable: true
                });
            });
        }

        function CreateContent(url) {
            var strHtml = '<iframe src="' + url + '" scrolling="no" frameborder="0" fit="true" style="height:110%;width:100%;min-height:600px;"></iframe>';
            return strHtml;
        }
    </script>    
</head>
<body>
    <div id="layout_div" class="easyui-layout">
        <div data-options="region:'north',border:false" class="admin" style="overflow:hidden; height:70px;background:#338FCC;padding:10px;padding-left:30px;">
            <div class="admin-1" style="color:#fff  ;font-size:3em; float:left;margin-left:30px;">
                                            网站后台管理
            </div>
            <div class="admin-2" style="float:right;height:70px; margin-right:50px;">
                <span>管理员:${admin.getAdminName()}</span>
                <span><a href="adminAction_userExit">注销</a></span>
            </div>
        </div>
        <div data-options="region:'west',split:false,title:'菜单',collapsible:false" style="width:170px; height: 900px">
            <div id="menu" class="easyui-accordion" fit="true">
                <!-- a标签url属性中填写（/控制器名称/视图名称） -->
                <div title="视频管理" data-options="iconCls:'icon-print'" style=" overflow:auto;padding:10px;">
                    <ul class="easyui-tree">
                        <li><a href="javascript:;" class="btn_menu" url="jsp/admin/video/VideoUpload.jsp">视频上传</a></li>
                        <li><a href="javascript:;" class="btn_menu" url="videoAction_ShowAdminVideo">视频操作</a></li>
                    </ul>
                </div>
               <div title="用户管理" data-options="iconCls:'icon-man'" style="overflow:auto;padding:10px;">
                    <ul class="easyui-tree">
                        <li><a href="javascript:;" class="btn_menu" url="userAction_showAllUserByPage">用户管理</a></li>
                        <li><a href="javascript:;" class="btn_menu" url="jsp/admin/user/add_user.jsp">添加用户</a></li>
                    </ul>
                </div>
                <div title="资源管理" data-options="iconCls:'icon-redo'" style="overflow:auto;padding:10px;">
                    <ul class="easyui-tree">
                        <li><a href="javascript:;" class="btn_menu" url="resourceAction_showAdminAllByPage">资源管理</a></li>
                        <li><a href="javascript:;" class="btn_menu" url="resourceAction_showAdminNoByPage">文档审核</a></li>
                    </ul>
                </div>
                <div title="社区管理" data-options="iconCls:'icon-tip'" style="overflow:auto;padding:10px;">
                    <ul class="easyui-tree">
                        <li><a href="javascript:;" class="btn_menu" url="sheQuAction_ShowAdminQuestByPage">问答管理</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div data-options="region:'center'" style="overflow:hidden">
            <div class="easyui-tabs" fit="true" id="container">
                <div title="主页" style="padding:10px">
                    <span>欢迎您，管理员:${admin.getAdminName()}</span>
                </div>
            </div>

        </div>
    </div>
</body>
</html>