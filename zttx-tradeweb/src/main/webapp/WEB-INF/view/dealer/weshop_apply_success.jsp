<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <title>服务管理-开通微店成功</title>
    <link href="/styles/dealer/global.css" rel="stylesheet" />
    <link href="/styles/dealer/weshop.css" rel="stylesheet" />
</head>
<body>
<div class="container">
            <jsp:include page="${ctx}/dealer/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->

                    <jsp:include page="${ctx}/dealer/dealermenu"  />

                <div class="main-right">
                <div class="main-grid mb10">
                    <div class="panel-crumbs">
                        <i class="icon i-setting"></i><a href="/dealer/center">管理中心</a> &gt;&gt; <span class="bb">服务管理</span>
                        <a class="panel-crumbs-help" href="/help/index"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <div class="main-grid mb40 clearfix">
                    <div class="weshop-apply-success">
                        <h2>开通我的微店</h2>
                        <div class="hr-dashed">
                        </div>
                        <div class="clearfix apply-box">
                        <c:if test="${type==1 }">
                            <div class="apply-flog">
                                    <div class="apply-btn">
                                        <h4>一步开通约逛及微店</h4>
                                        <span  class="lock"></span>
                                        <p>
                                           	使用您现在登录的终端商账号开通相同手机号的约逛登录账户，并一步开通激活微店功能，两者密码可相同也可独立自行修改。
                                        </p>
                                    </div>
                                </div>
                             </c:if>
                             <c:if test="${type==2 }">
                            <div class="apply-flog">
                                    <div class="apply-btn">
                                        <h4>已有约逛号激活微店</h4>
                                        <span  class="notepad"></span>
                                        <p>
                                           	使用您已经在使用的约逛账户进行绑定和激活，绑定后即可开通使用约逛里的微店功能。
                                        </p>
                                    </div>
                                </div>
                             </c:if>
                             <c:if test="${type==3 }">
                            <div class="apply-flog">
                                    <div class="apply-btn">
                                        <h4>使用其他新约逛号</h4>
                                        <span  class="iphone"></span>
                                        <p>
                                           	还没有约逛账户或想使用其它手机号码作为约逛的登录号请注册新的约逛号。
                                        </p>
                                    </div>
                                </div>
                             </c:if>
                            <div class="apply-form">
                                <h3>
                                    <i class="icon i-right-32"></i> <span>已绑定,并授权开通服务</span>
                                </h3>
                                <div class="ui-form-item">
                                    <label class="ui-label">
                                        终端商名:
                                    </label>
                                    <span class="ui-form-text">${dealerInfo.dealerName }</span>
                                </div>
                                <div class="ui-form-item">
                                    <label class="ui-label">
                                        终端商账户:
                                    </label>
                                    <span class="ui-form-text">${dealerUserm.userMobile}</span>
                                </div>
                                <div class="ui-form-item">
                                    <label class="ui-label">
                                        绑定的约逛号:
                                    </label>
                                    <span class="ui-form-text">${dealerShopInfo.userCode } 登录号(${dealerUserm.userMobile})</span>
                                </div>
                                <div class="ui-form-item">
                                    <a class="ui-button ui-button-mred" href="${ctx}/dealer/weshop/config?shopId=${dealerShopInfo.shopId }">设置微店</a> <%--地址--%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
       <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />

</body>
</html>
