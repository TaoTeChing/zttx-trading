<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>注册成功</title>
    <meta name="keywords" content="8637品牌超级代理-品牌市场" />
    <meta name="description" content="8637品牌超级代理-品牌市场" />
    <!--样式-->
	<link href="${res}/styles/common/global.css" rel="stylesheet" />
    <link href="${res}/styles/common/account.css" rel="stylesheet" />
</head>
<body>
    <div class="container success">
        <jsp:include page="login/view_top.jsp" />
        <div class="main">
            <div class="box">
                <div class="head">
                    <h2>会员注册</h2>
                </div>
                <div class="main-left">
                    <div class="content">
                        <div class="form">
                            <dl class="mb40">
                                <dt>
                                    <img src="${ctx}/images/common/icon-right-50.png" /></dt>
                                <dd>
                                    <h3 class="yahei c-g fs18 mb10">恭喜您在8637品牌超级代理的账号注册成功！</h3>
                                    <span>您的登陆账号是：</span><span class="c-r">${userMobile}</span>
                                    <span>登陆密码已发送到您的邮箱,如以后更换密码,请在找回密码里设置。</span>
                                </dd>
                            </dl>
                            <c:choose>
                            <c:when test="${userType==0}">
                            <dl class="yahei mb40">
                                <dt>
                                    <label>您是品牌商</label></dt>
                                <dd>
                                    <a class="box bgc-g" href="${ctx}/brand/info/index">提交入驻资料</a>
                                    <a class="box bgc-yl" href="${ctx}/brand/center">进入管理后台</a>
                                    <a class="box bgc-cf" href="${ctx}/brand/brandes">发布我的品牌资料</a>
                                </dd>
                            </dl>
                            </c:when>
                            <c:when test="${userType==1}">
                            <dl class="yahei mb40">
                                <!--如果是终端商 去掉hide样式 -->
                                <dt>
                                    <label>您是终端商</label></dt>
                                <dd>
                                    <a class="box bgc-g" href="${ctx}/dealer/info">完善我的信息</a>
                                    <a class="box bgc-yl" href="${ctx}/dealer/center">进入管理后台</a>
                                    <a class="box bgc-cf" href="#">找品牌合作</a>
                                </dd>
                            </dl>
                            </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="login/view_foot.jsp" />
    </div>

</body>
</html>
