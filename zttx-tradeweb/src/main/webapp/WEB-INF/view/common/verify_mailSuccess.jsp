<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>邮箱验证-8637品牌超级代理</title>
    <meta name="keywords" content="8637品牌超级代理-品牌市场" />
    <meta name="description" content="8637品牌超级代理-品牌市场" />
    <!--样式-->
    <link href="${res}/styles/common/base.css" rel="stylesheet" />
    <link href="${res}/styles/common/account.css" rel="stylesheet" />
</head>
<body>
<div class="container getpass">
    <jsp:include page="login/view_top.jsp" />
    <div class="main">
        <div class="box">
            <div class="head">
                <h2>邮箱验证</h2>
            </div>
            <div class="main-left">
                <div class="content">
                    <div class="form">
                        <dl class="mb20">
                            <dt>
                                <img alt="" src="${ctx}/images/common/icon-right-50.png" /></dt>
                            <dd>
                                <h3 class="yahei c-g fs18 mb10">恭喜您,邮箱验证成功！</h3>
                                <%--<span>新的密码已发送到您的邮箱,如果以后更换密码,请在重置密码里设置</span>--%>
                            </dd>
                        </dl>
                       <%-- <!-- 栏目：如找回密码遇到困难,请联系我们  -->
                        <jsp:include page="view_forgotPassContact.jsp" />--%>
                        <dl class="yahei mb40">
                            <dt></dt>
                            <dd>
                                <a title="" class="btn yahei bgc-h c-h alpha" href="${ctx}/common/login">登录我的管理中心</a>
                            </dd>
                        </dl>
                    </div>
                </div>
            </div>
            <div class="main-right">
                <div class="content">
                    <h3>已有品牌超级代理账户?</h3>
                    <a class="btn" href="${ctx}/common/login">立即登录</a><br />
                    <a href="${ctx}/about/about.jsp">8637品牌超级代理是什么?</a><br />
                    <a href="${ctx}/about/superiority.jsp">为何我们需要8637品牌超级代理?</a><br />
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="login/view_foot.jsp" />
</div>
</body>
</html>