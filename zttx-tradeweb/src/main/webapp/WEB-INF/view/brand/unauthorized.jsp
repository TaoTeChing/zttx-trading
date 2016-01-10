<%--
  @(#)create_ProductLine.jsp 14-3-18 上午9:42
  Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--%>
<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <c:set value="新增产品线" var="pageName"/>
    <c:if test="${!empty id}">
        <c:set value="修改产品线" var="pageName"/>
    </c:if>
    <title>管理中心-产品线管理-${pageName}</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/common/through.css"/>
    <style>
        .flow-steps li{
            width: 320px;
        }
    </style>
</head>
<body>
<jsp:include page="${ctx}/brand/mainmenu"/>
<div class="main layout">
    <jsp:include page="${ctx}/brand/brandmenu"/>
    <div class="main_con">
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
            </div>
            <div class="fr">
                <c:choose>
                    <c:when test="${empty id}">
                        <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp"/>
                    </c:when>
                    <c:otherwise>
                        <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
                            <jsp:param value="0" name="isShow"/>
                        </jsp:include>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <div class="mycount-havehrough" style="margin-top: 0;padding-top: 165px;">
                <dl class="clearfix">
                    <dt class="imgbox"></dt>
                    <dd class="lager">您好！您<em>没有权限</em>访问该页面！</dd>
                    <dd class="another">请联系您的账号管理员！</dd>
                </dl>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp"/>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
</body>
</html>
