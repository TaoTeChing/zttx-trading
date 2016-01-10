<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>资料管理 &gt; 基本资料</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/account.css" rel="stylesheet" />
        <link rel="stylesheet" href="/styles/common/through.css"/>
</head>
<body>
    <!--完成-->
    <div class="container">
        <jsp:include page="${ctx}/dealer/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->
                <jsp:include page="${ctx}/dealer/dealermenu" />
                
                <!--主体内容-->
                <div class="main-right">
                    <!--提示框-->
                    <!--<div class="main-grid mb10">
                        <div class="alert c-w warning">
                            <b class="icon i-close">X</b><i class="icon"></i><a href="#">预警消息:您有一笔订单超过15天未付款! 2013-10-16</a>
                        </div>
                    </div>-->
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx }/dealer/center">管理中心</a> &gt;&gt; <a href="${ctx}/dealer/account/infor" title="" class="c-h">账户管理</a> > <span class="bb">审核结果</span>

                        </div>
                    </div>
                    <div class="main-grid mb20 clearfix">
	                        <div class="main-grid mb10">
		                            <div class="flow-steps mt15">
		                                <ol class="mt10">
		                                    <li class="done">注册成功</li>
		                                    <li class="last done current-prev"><span>填写资料</span></li>
		                                    <li class="last current"><span>审核结果</span></li>
		                                </ol>
		                            </div>
	                        </div>
                        <div class="panel-tabs">
                           <div class="mycount-nothrough">
						        <dl class="clearfix">
						            <dt class="imgbox"></dt>
						            <dd class="tips">温馨提示:</dd>
						            <dd class="lager">请修改您的申请资料</dd>
						            <dd class="another">您提交的资料中，<em>${errorinfo}</em>，请重新提交</dd>
						            <dd><a class="ui_button ui_button_morange" href="${ctx }/dealer/account/infor">修改资料</a></dd>
						        </dl>
						        <div class="mycount-through-content">
						            	您也可以通过以下方式 <i class="through-icon"></i>联系我们 电话：<em>400-111-8637</em>   邮箱：<em>services@8637.com</em>
						        </div>
						    </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp" />
    </div>
</body>
</html>
