﻿<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>资料管理 &gt; 基本资料</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link rel="stylesheet" href="${res}/styles/dealer/account.css"/>
    <link rel="stylesheet" href="${res}/styles/common/through.css"/>
</head>
<body>
    <!--完成-->
    <div class="container">
        <jsp:include page="${ctx}/dealer/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <jsp:include page="${ctx}/dealer/dealermenu" />
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
                            <i class="icon i-setting"></i><a href="${ctx }/dealer/center">管理中心</a> &gt;&gt; <a href="${ctx}/dealer/account/infor" title="" class="c-h">账户管理</a> > <span class="bb">资料审核</span>
                        </div>
                    </div>

                    <div class="main-grid mb20 clearfix">
	                        <div class="main-grid mb10">
		                            <div class="flow-steps flow-4 mt15">
		                                <ol class="mt10">
		                                    <li class="done">注册成功</li>
		                                    <li class="done current-prev"><span>填写资料</span></li>
		                                    <li class="last-current"><span>确认审核</span></li>
		                                </ol>
		                            </div>
	                        </div>
                       
                            <div class="panel-content mt20">
                                <div class="mycount-havehrough">
								        <dl class="clearfix">
								            <dt class="imgbox"></dt>
								            <dd class="lager">您好！您的资料正在<em>审核中</em></dd>
								            <dd class="another">我们将会在1-3个工作日内对您提交的信息进行审核，请您耐心等待！</dd>
								            <dd><a class="ui_button ui_button_morange" href="${ctx }/dealer/account/infor">修改资料</a></dd>
								        	<dd class="another">
								        		<div style="margin-top: 10px;" class="mycount-through-content">
										           	 您也可以通过以下方式 <i class="through-icon"></i>联系我们 电话：<em>400-111-8637</em>   邮箱：<em>services@8637.com</em>
										        </div>
								        	</dd>
								        </dl>
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
