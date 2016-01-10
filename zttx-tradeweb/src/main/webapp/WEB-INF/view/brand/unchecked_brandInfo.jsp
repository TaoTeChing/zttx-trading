<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-用户信息</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/common/through.css"/>
    <style>
        .flow-steps li{
            width: 320px;
        }
    </style>
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
<div class="main layout">
    <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
    <div class="main_con">
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <span>账户信息</span>
            </div>
            <%--<div class="fr">
                <%@ include file="common_quick_bar.jsp" %>
            </div>--%>
        </div>
        <div class="inner">
            <!-- 步骤条 -->
            <div class="flow-steps mt15">
                <ol class="mt10">
                    <li class="done"><span>注册成功</span></li>
                    <li class="done current-prev"><span>填写企业资料</span></li>
                    <li class="last-current"><span>确认审核</span></li>
                </ol>
            </div>

            <div class="mycount-havehrough">
                <dl class="clearfix">
                    <dt class="imgbox"></dt>
                    <dd class="lager">您好！您的资料正在<em>审核中</em></dd>
                    <dd class="another">我们将会在1-3个工作日内对您提交的信息进行审核，请您耐心等待！</dd>
                    <dd class="another">
                        <div style="margin-top: 10px;" class="mycount-through-content">
                            您也可以通过以下方式
                            <a class="link" href="/about/contactus.jsp" target="_blank"><i class="through-icon"></i>联系我们</a> 电话：<em>400-111-8637</em> 邮箱：<em>services@8637.com</em>
                        </div>
                    </dd>
                </dl>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp"/>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
</body>
</html>