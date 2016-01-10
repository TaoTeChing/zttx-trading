<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>忘记用户名</title>
    <meta name="keywords" content="8637品牌超级代理-品牌市场" />
    <meta name="description" content="8637品牌超级代理-品牌市场" />
    <%--<!--样式-->--%>
    <link href="${res }/styles/common/base.css" rel="stylesheet" />
    <link href="${res }/styles/common/account.css" rel="stylesheet" />
</head>
<body>
    <div class="container findaccount">
       <jsp:include page="login/view_top.jsp"/>
        <div class="main">
            <div class="box">
                <div class="head">
                    <h2>忘记用户名</h2>
                </div>
                <div class="main-left">
                    <div class="content">
                        <div class="ui-form-item" style="padding-left:100px;">
                            <label class="ui-label"><i class="icon i-right-32"></i></label>
                            <h3 class="c-r fs16 yahei lh3">我们会在3个工作日内对您提交的资料进行审核，请耐心等待</h3>
                            <br>
                            <span class="block c-h lh3 fs14">有任何问题，请联系客服：0574-87217777</span>
                            <br>
                            <a class="ui-button ui-button-sred" href="${ctx }/">去品牌超级代理首页</a>
                        </div>

                    </div>
                </div>
                <div class="main-right">
                    <div class="content">
                        <p>
                            <img src="/images/common/findaccount-right.gif">
                        </p>
                        <div class="hr-dashed mt10 mb10"></div>
                        <a class="btn" href="${ctx }/common/register" target="_blank">注册</a>
                        <br>
                        <a class="btn" href="${ctx }/common/login" target="_blank">登录</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="login/view_foot.jsp"/>
    <script src="${res }/scripts/jquery.min.js"></script>
    <script src="${src}/plugin/Validform_v5.3.2_min.js"></script>
</body>
</html>
