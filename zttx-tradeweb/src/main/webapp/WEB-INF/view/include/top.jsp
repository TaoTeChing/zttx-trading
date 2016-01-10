<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<div id="site-nav">
    <div class="site-nav-bd clearfix">
        <ul class="site-nav-bd-l fl">
            <%--<li class="top-menu">
                <div class="menu-hd">
                    <i class="t-icon-collect"></i><a href="javascript:void(0);" onclick="AddFavorite(document.title,location.href)">收藏本站</a>
                </div>
            </li>
            <li class="top-line"></li>--%>
            <c:choose>
                <c:when test="${not empty userPrincipal}">
                    <li class="top-menu">
                        <div class="menu-hd clearfix">
                            <span class="fl">您好！</span>
                            <c:choose>
                                <c:when test="${userPrincipal.userType eq 0}">
                                    <a class="high-light fl"
                                       href="http://www${zttx}/brand/center">${userPrincipal.userName != "" ? userPrincipal.userName : userPrincipal.userMobile}</a><span class="fl">，</span>
                                </c:when>
                                <c:otherwise>
                                    <a class="high-light fl"
                                       href="http://www${zttx}/dealer/center">${userPrincipal.userName != "" ? userPrincipal.userName : userPrincipal.userMobile}</a><span class="fl">，</span>
                                </c:otherwise>
                            </c:choose>
                            <a href="javascript:document.getElementById('logout-form').submit();"
                               class="c_blue fl">退出登录</a>
                            <form:form id="logout-form" action="http://www${zttx}/common/logout?redirect=false" method="post" cssStyle="display: none;"></form:form>
                        </div>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="top-menu">
                        <div class="menu-hd">
                            <a class="high-light" href="http://www${zttx}/common/login">请登录</a>
                            <a class="high-light" href="http://www${zttx}/common/register">加盟入驻</a>
                        </div>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
        <ul class="site-nav-bd-r fr">
            <li class="top-menu">
                <div class="menu-hd">
                    <a href="http://www${zttx}">8637品牌超级代理首页</a>
                </div>
            </li>
            <li class="top-line"></li>
            <li class="top-menu">
                <div class="menu-hd">
                    <c:choose>
                        <c:when test="${not empty userPrincipal}">
                            <c:choose>
                                <c:when test="${userPrincipal.userType eq 1}">
                                    <i class="t-icon-order"></i><a href="http://www${zttx}/dealer/dealerOrder/order">我的订单</a>
                                </c:when>
                                <c:otherwise>
                                    <i class="t-icon-order"></i><a href="http://www${zttx}/brand/order/purorder">我的订单</a>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <i class="t-icon-order"></i><a href="http://www${zttx}/common/login">我的订单</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </li>
            <li class="top-line"></li>
            <c:if test="${not empty userPrincipal && userPrincipal.userType eq 1}">
                <li class="top-menu">
                    <div class="menu-hd">
                        <i class="t-icon-order"></i>
                        <a href="http://www${zttx}/dealer/dealerShoper/cart">
                            我的进货单<span class="high-light">（${fns:countShoperNum()}）</span>
                        </a>
                    </div>
                </li>
                <li class="top-line"></li>
            </c:if>
            <li class="top-menu">
                <div class="menu-hd">
                    <i class="t-icon-fav"></i><span>我的收藏夹</span>
                </div>
                <div class="menu-bd" style="width: 85px;padding: 0 25px;">
                    <div class="menu-spacer" style="width: 45px;"></div>
                    <ul>
                        <c:choose>
                            <c:when test="${not empty userPrincipal}">
                                <c:choose>
                                    <c:when test="${userPrincipal.userType eq 0}">
                                        <li><a target="_blank" href="http://www${zttx}/brand/dealer/favorite">我收藏的终端商</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a target="_blank" href="http://www${zttx}/dealer/dealerFavorite/productFavorite">我收藏的产品</a></li>
                                        <li><a target="_blank" href="http://www${zttx}/dealer/dealerJoinManage/brandsCollect">我收藏的品牌</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <li><a target="_blank" href="http://www${zttx}/common/login">我收藏的品牌</a></li>
                                <li><a target="_blank" href="http://www${zttx}/common/login">我收藏的终端商</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </li>
            <li class="top-line"></li>
            <li class="top-menu">
                <div class="menu-hd">
                    <span>产品下载</span><i class="t-icon-down"></i>
                </div>
                <div class="menu-bd" style="width: 97px;padding: 0 10px;">
                    <div class="menu-spacer" style="width: 44px;"></div>
                    <ul>
                        <li><a href="http://www${zttx}/soft/erp/" target="_blank">智慧门店管理系统</a></li>
                        <li><a href="http://app${zttx}" target="_blank">约逛-手机APP</a></li>
                        <li><a href="http://www${zttx}/soft/drp/" target="_blank">智慧品牌管理系统</a></li>
                    </ul>
                </div>
            </li>
            <li class="top-line"></li>
            <li class="top-menu">
                <div class="menu-hd">
                    <span>客户服务</span><i class="t-icon-down"></i>
                </div>
                <div class="menu-bd" style="width: 50px;padding: 0 12px;">
                    <div class="menu-spacer" style="width: 1px;"></div>
                    <ul>
                        <li><a target="_blank" href="http://www${zttx}/help">帮助中心</a></li>
                        <li><a target="_blank" href="http://www${zttx}/rules">规则中心</a></li>
                        <li><a target="_blank" href="http://www${zttx}/about/contactus">联系我们</a></li>
                    </ul>
                </div>
            </li>
            <li class="top-line"></li>
            <li class="top-menu down website">
                <div class="menu-hd">
                    <i class="t-icon-site"></i><span>网站导航</span><i class="t-icon-down"></i>
                </div>
                <div class="menu-bd" style="width: 90px;padding: 0 25px;">
                    <div class="menu-spacer" style="width: 50px;"></div>
                    <ul>
                        <li><a target="_blank" href="http://www${zttx}/about">关于我们</a></li>
                        <li><a target="_blank" href="http://www${zttx}/info">新闻资讯</a></li>
                        <c:choose>
                            <c:when test="${not empty userPrincipal && userPrincipal.userType == 0}">
                                <li><a target="_blank" href="http://www${zttx}/brand/center">后台管理</a></li>
                            </c:when>
                            <c:when test="${not empty userPrincipal && userPrincipal.userType == 1}">
                                <li><a target="_blank" href="http://www${zttx}/dealer/center">后台管理</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a target="_blank" href="http://www${zttx}/common/login">后台管理</a></li>
                            </c:otherwise>
                        </c:choose>
                        <li><a target="_blank" href="http://www${zttx}/search?q=&st=b">品牌列表</a></li>
                        <li><a target="_blank" href="http://www${zttx}/shop">终端商名录</a></li>
                    </ul>
                </div>
            </li>
            <li class="top-line"></li>
            <li class="top-menu">
                <div class="menu-hd">
                    <a href="http://wpa.b.qq.com/cgi/wpa.php?ln=1&amp;key=XzkzODAwMTU5Nl8xOTE0MjNfNDAwMTExODYzN18yXw" target="_blank">加盟入驻咨询</a>
                </div>
            </li>
            <li class="top-line"></li>
            <li class="top-menu">
                <div class="menu-hd">
                    <a href="${ctx}/about/superiority" target="_blank">平台优势介绍</a>
                </div>
            </li>
        </ul>
    </div>
</div>