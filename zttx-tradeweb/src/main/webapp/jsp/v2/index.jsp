<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include  file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>8637品牌超级代理 - 最低成本招商加盟渠道快建|一手价格一手货源</title>
    <meta name="keywords" content="8637,品牌超级代理,品牌招商,招商加盟，天下商邦"/>
    <meta name="description" content="“8637品牌超级代理”以B2B2C（从品牌商到终端店铺再到消费者）的模式构建零中间商的招商加盟直供平台。利用互联网技术服务品牌商及终端商，改变传统品牌代理供应链（专卖店、批发商、代理商、品牌商），通过互联网直连方式招商加盟，减少中间供应环节，打造从厂家到店铺的最短供应链。"/>
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="v2_index.css" rel="stylesheet"/>
    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/seajs_config.js"></script>
    <script src="v2_index.js"></script>
</head>
<body>
<div class="container">
    <div id="site-nav">
        <div class="site-nav-bd clearfix">
            <ul class="site-nav-bd-l fl">
                <li class="top-menu collect">
                    <div class="menu-hd">
                        <a href="javascript:void(0);" onclick="AddFavorite(document.title,location.href)">收藏本站</a>
                    </div>
                </li>
                <li class="top-line"></li>
                <c:choose>
                    <c:when test="${not empty brandUserm}">
                        <li class="top-menu">
                            <div class="menu-hd">您好！
                                <a class="high-light" href="http://www${zttx}/brand/center">${brandUserm.comName}</a>，
                                <a href="javascript:document.getElementById('logout-form').submit();" class="c_blue">退出登录</a>
                                <form:form id="logout-form" action="http://www${zttx}/common/logout?redirect=false" method="post" cssStyle="display: none;"></form:form>
                            </div>
                        </li>
                    </c:when>
                    <c:when test="${not empty dealerUserm}">
                        <li class="top-menu">
                            <div class="menu-hd">您好！
                                <a class="high-light" href="http://www${zttx}/dealer/center">${dealerUserm.userName}</a>，
                                <a href="javascript:document.getElementById('logout-form').submit();" class="c_blue">退出登录</a>
                                <form:form id="logout-form" action="http://www${zttx}/common/logout?redirect=false" method="post" cssStyle="display: none;"></form:form>
                            </div>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <%-- <li class="top-menu">
                             <div class="menu-hd">您好！欢迎光临8637品牌超级代理</div>
                         </li>--%>
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
                <li class="top-menu order">
                    <div class="menu-hd">
                        <c:choose>
                            <c:when test="${not empty brandUserm}">
                                <a href="http://www${zttx}/brand/order/purorder">我的订单</a>
                            </c:when>
                            <c:when test="${not empty dealerUserm}">
                                <a href="http://www${zttx}/dealer/myorder">我的订单</a>
                            </c:when>
                            <c:otherwise>
                                <a href="http://www${zttx}/common/login">我的订单</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </li>
                <li class="top-line"></li>
                <c:if test="${not empty dealerUserm}">
                    <li class="top-menu order">
                        <div class="menu-hd">
                            <a href="http://www${zttx}/dealer/shopper">我的进货单<span class="high-light">（${shopCount}）</span></a>
                        </div>
                    </li>
                    <li class="top-line"></li>
                </c:if>
                <li class="top-menu favorites">
                    <div class="menu-hd">
                        <span>我的收藏夹</span>
                    </div>
                    <div class="menu-bd" style="width: 85px;">
                        <div class="menu-spacer"></div>
                        <ul>
                            <c:choose>
                                <c:when test="${not empty brandUserm}">
                                    <a target="_blank" href="http://www${zttx}/brand/dealer/favorite">我收藏的终端商</a>
                                </c:when>
                                <c:when test="${not empty dealerUserm}">
                                    <li><a target="_blank" href="http://www${zttx}/dealer/favorite">我收藏的产品</a></li>
                                    <li><a target="_blank" href="http://www${zttx}/dealer/copartner/favorite">我收藏的品牌</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="http://www${zttx}/common/login">我收藏的产品</a></li>
                                    <li><a href="http://www${zttx}/common/login">我收藏的品牌</a></li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                </li>
                <li class="top-line"></li>
                <li class="top-menu down download">
                    <div class="menu-hd">
                        <span>产品下载</span>
                    </div>
                    <div class="menu-bd" style="width: 97px; display: none; ">
                        <ul>
                            <li><a href="http://www${zttx}/soft/erp/" target="_blank">智慧门店管理系统</a></li>
                            <li><a href="http://app${zttx}" target="_blank">约逛-手机APP</a></li>
                            <li><a href="http://www${zttx}/soft/drp/" target="_blank">智慧品牌管理系统</a></li>
                        </ul>
                    </div>
                </li>
                <li class="top-line"></li>
                <li class="top-menu down service">
                    <div class="menu-hd">
                        <span>客户服务</span>
                    </div>
                    <div class="menu-bd">
                        <ul>
                            <li><a target="_blank" href="http://www${zttx}/help/index">帮助中心</a></li>
                            <li><a target="_blank" href="http://www${zttx}/rules/">规则中心</a></li>
                            <li><a target="_blank" href="http://www${zttx}/about/contactus.jsp">联系我们</a></li>
                        </ul>
                    </div>
                </li>
                <li class="top-line"></li>
                <li class="top-menu down website">
                    <div class="menu-hd">
                        <span>网站导航</span>
                    </div>
                    <div class="menu-bd" style="width: 90px;">
                        <ul>
                            <li><a target="_blank" href="http://www${zttx}/about">关于我们</a></li>
                            <li><a target="_blank" href="http://www${zttx}/info">新闻资讯</a></li>
                            <%-- <li><a target="_blank" href="http://www${zttx}/jsp/common/map.jsp">站点地图</a></li> --%>
                            <li><a target="_blank" href="${ctx}/common/dealer/explosionList">爆款产品</a></li>
                            <li><a target="_blank" href="http://www${zttx}/brand/center">品牌商管理中心</a></li>
                            <li><a target="_blank" href="http://www${zttx}/dealer/center">终端商管理中心</a></li>
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
                        <a href="http://www.8637.com/about/superiority.jsp" target="_blank">平台优势介绍</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <%--<jsp:include page="/WEB-INF/view/common/component/header.jsp"/>--%>
    <div class="header-round">
        <%--<div class="sticky">
            <div class="header clearfix" style="z-index: 216;">
                <div class="logo">
                    <a href="/">
                        <h1>8637品牌超级代理</h1>
                    </a>
                </div>
                <div class="search">
                    <div class="clearfix">
                        <div class="search-form" style="width: 525px;">
                            <form id="search_form" action="${ctx}/search" onsubmit="return false;">
                                <div class="search-select">
                                    <input name="keyType" type="hidden" id="key_type" value="${filter.keyType == null ? 0 :  filter.keyType}"/>
                                    <span></span>
                                    <dl>
                                        <dd value="2"><a href="javascript:void(0)">商品</a></dd>
                                        <dd value="0"><a href="javascript:void(0)">品牌</a></dd>
                                        <dd value="1"><a href="javascript:void(0)">资讯</a></dd>
                                    </dl>
                                </div>
                                <div class="search-box" style="width: 345px;">
                                    <input type="text" name="q" id="q" placeholder="商品/品牌/店铺" value="${filter.q}" autocomplete="off" />
                                </div>
                                <div class="search-btn">
                                    <a id="btn_header_search">搜索</a>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="hot-search" id="hostList">

                    </div>
                </div>
            </div>
        </div>--%>
        <div class="header clearfix" style="z-index: 216;">
            <div class="logo">
                <a href="/">
                    <h1>8637品牌超级代理</h1>
                </a>
            </div>
            <div class="fr clearfix" style="margin-top: 28px;">
                <div class="search fl clearfix">
                    <form action="" method="post">
                        <div class="form fl">
                            <input type="text" id="searchTxt" value="搜索您需要的商品" />
                            <span><a href="">短袖T恤</a><a href="">裙子</a><a href="">灯笼裤</a></span>
                        </div>
                        <a id="searchBtn" class="fl" href="javascript:;"><i class="icon-search"></i></a>
                    </form>
                </div>
                <div class="cart fl" id="cart">
                    <div class="cart-hd">
                        <i class="icon-cart"></i>
                        <%--<i class="more1">&gt;</i>--%>
                        <i id="cartCount">3</i>
                        <a href="javascript:;">我的购物车</a>
                    </div>
                    <div class="cart-drop" id="cartDrop">
                        <div class="d1">商品列表</div>
                        <div class="d2">
                            <ul>
                                <li class="clearfix">
                                    <div class="d2-img">
                                        <img src="" alt="" width="50" height="50"/>
                                    </div>
                                    <div class="d2-name">
                                        <a href="#" title="星达Sky-Watcher 130EQ反射高倍高清天文望远镜 深空神器 超大口径 活动款无赠品">星达Sky-Watcher 130EQ反射高倍高清天文望远镜 深空神器 超大口径 活动款无赠品</a>
                                    </div>
                                    <div class="d2-detail">
                                        <span>￥12.00</span><br />
                                        <a href="javascript:;" class="delete">删除</a>
                                    </div>
                                </li>
                                <li class="clearfix">
                                    <div class="d2-img">
                                        <img src="" alt="" width="50" height="50"/>
                                    </div>
                                    <div class="d2-name">
                                        <a href="#" title="星达Sky-Watcher 130EQ反射高倍高清天文望远镜 深空神器 超大口径 活动款无赠品">星达Sky-Watcher 130EQ反射高倍高清天文望远镜 深空神器 超大口径 活动款无赠品</a>
                                    </div>
                                    <div class="d2-detail">
                                        <span>￥12.00</span><br />
                                        <a href="javascript:;" class="delete">删除</a>
                                    </div>
                                </li>
                                <li class="clearfix">
                                    <div class="d2-img">
                                        <img src="" alt="" width="50" height="50"/>
                                    </div>
                                    <div class="d2-name">
                                        <a href="#" title="星达Sky-Watcher 130EQ反射高倍高清天文望远镜 深空神器 超大口径 活动款无赠品">星达Sky-Watcher 130EQ反射高倍高清天文望远镜 深空神器 超大口径 活动款无赠品</a>
                                    </div>
                                    <div class="d2-detail">
                                        <span>￥12.00</span><br />
                                        <a href="javascript:;" class="delete">删除</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div class="d3 clearfix">
                            <span class="fl ta-r" style="word-break: break-all;">共 <strong>9</strong> 件商品 共计 <strong>￥110.00</strong> 元</span>
                            <a href="#" class="fr">去购物车</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%-- header-round end --%>
    <%--<jsp:include page="/WEB-INF/view/common/component/nav.jsp">
        <jsp:param value="1" name="m"/>
    </jsp:include>--%>
    <div class="nav">
        <div class="ts-container clearfix">
            <div class="nav-left fl">
                <h2 class="btn">全部产品分类</h2>
                <div class="float-box <%--<c:if test="${empty param.m || param.m!='1'}">open</c:if>--%>"><%--这里需要判断是否是首页,如果是首页,去掉open样式名--%>
                    <%--<tags:index_staticHtml webDefTemplate="${fns:getDocument(new_index_left_category) }"></tags:index_staticHtml>--%>
                    <style>
                        .nav .nav-left .type-item{padding: 2px 15px;}
                    </style>
                    <div class="type-box">
                        <div class="type-item">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    <a href="/lady?st=">女装</a>
                                </h3>
                                <div class="type-item-c">
                                    <a title="" href="/lady?st=p&mainId=101000000&dealId=101017000" target="_blank">春夏装</a> <a title="" href="/lady?st=p&mainId=101000000&dealId=101008000" target="_blank">连衣裙</a> <a title="" href="/lady?st=p&mainId=101000000&dealId=101022000" target="_blank">小吊带</a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    <a href="/man?st=">男装</a>
                                </h3>
                                <div class="type-item-c">
                                    <a title="" href="/man?st=p&mainId=100000000&dealId=100001000" target="_blank">羽绒服</a> <a title="" href="/man?st=p&mainId=100000000&dealId=100002000" target="_blank">夹克</a> <a title="" href="/man?st=p&mainId=100000000&dealId=100003000" target="_blank">长袖T恤</a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    <a href="/underwear?st=">内衣</a>
                                </h3>
                                <div class="type-item-c">
                                    <a title="" href="/underwear?st=p&mainId=103000000&dealId=103001000" target="_blank">文胸</a> <a title="" href="/underwear?st=p&mainId=103000000&dealId=103002000" target="_blank">女士内裤</a> <a title="" href="/underwear?st=p&mainId=103000000&dealId=103026000" target="_blank">保暖裤</a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    <a href="/textiles?st=p">家纺</a>
                                </h3>
                                <div class="type-item-c">
                                    <a title="" href="/textiles?st=p&mainId=200000000&dealId=200005000" target="_blank">四件套</a> <a title="" href="/textiles?st=p&mainId=200000000&dealId=200011000" target="_blank"> 儿童床品</a> <a title="" href="/textiles?st=p&mainId=200000000&dealId=200017000" target="_blank">凉席</a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    <a href="/children?">童装</a>
                                </h3>
                                <div class="type-item-c">
                                    <a title="" href="/children?st=p&mainId=250000000&dealId=250001000" target="_blank">儿童套装</a> <a title="" href="/children?st=p&mainId=250000000&dealId=250034000" target="_blank">针织衫</a> <a title="" href="/children?st=p&mainId=250000000&dealId=250008000" target="_blank">长裤</a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    <a href="/shoes&hats?">鞋帽</a>
                                </h3>
                                <div class="type-item-c">
                                    <a title="" href="/shoes&hats?st=p&mainId=150000000&dealId=150010000" target="_blank">正装皮鞋</a> <a title="" href="/shoes&hats?st=p&mainId=150000000&dealId=150014000" target="_blank">雪地靴</a> <a title="" href="/shoes&hats?st=p&mainId=150000000&dealId=150007000" target="_blank">板鞋</a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    <a href="/bags?st=p">箱包</a>
                                </h3>
                                <div class="type-item-c">
                                    <a title="" href="/bags?st=p&mainId=252000000&dealId=252004000" target="_blank">旅行箱</a> <a title="" href="/bags?st=p&mainId=252000000&dealId=252003000" target="_blank">双肩包</a> <a title="" href="/bags?st=p&mainId=252000000&dealId=252007000" target="_blank">电脑包</a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    <a href="/search?mainId=201000000">居家日用</a>
                                </h3>
                                <div class="type-item-c">
                                    <a title="" href="/search?mainId=201000000&dealId=201001000" target="_blank">卫浴用具/卫浴配件</a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item" style="padding: 1px 15px;">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    <a href="/search?mainId=104000000">服饰配件</a>
                                </h3>
                                <div class="type-item-c">
                                    <a title="" href="/search?mainId=104000000&dealId=104013000" target="_blank">围巾/手套/帽子</a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item" style="padding: 3px 15px;">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    <a href="/search?mainId=255000000">运动户外</a>
                                </h3>
                                <div class="type-item-c">
                                    <a href="/search?mainId=255000000&dealId=255007000" target="_blank">泳裤</a>
                                    <a title="" href="/search?mainId=255000000&dealId=255010000" target="_blank">泳帽</a>
                                    <a title="" href="/search?mainId=255000000&dealId=255006000" target="_blank">冲锋衣</a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item" style="padding: 3px 15px;">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    <a href="/search?mainId=253000000">珠宝配饰</a>
                                </h3>
                                <div class="type-item-c">
                                    <a title="" href="/search?mainId=253000000&dealId=253001000" target="_blank">珠宝首饰</a> <a title="" href="/search?mainId=253000000&dealId=253003000" target="_blank">品质手表</a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item" style="border-bottom:0; padding: 3px 15px 4px 15px;">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    <a href="/search?mainId=251000000">孕产妇用品</a>
                                </h3>
                                <div class="type-item-c">
                                    <a title="" href="/search?mainId=251000000&dealId=251006000" target="_blank">孕妇裤/托腹裤</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--<div class="type-box">
                        <div class="type-item">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    女装
                                </h3>
                                <a title="" class="type-item-more" href="/common/dealer/productList?dealMain=101000000&amp;q=" target="_blank">更多</a>
                            </div>
                            <div class="type-item-sub">
                                <div class="type-name">女装</div>
                                <div class="type-cate">
                                    <div class="list">
                                        <a href="#">春夏装</a>
                                        <a href="#">连衣裙</a>
                                        <a href="#">T恤 </a>
                                        <a href="#">裤子</a>
                                        <a href="#">羽绒服</a>
                                        <a href="#">羊绒衫</a>
                                        <a href="#">外套 </a>
                                        <a href="#">半身裙</a>
                                        <a href="#">衬衫</a>
                                        <a href="#">春秋季</a>
                                        <a href="#">呢大衣</a>
                                        <a href="#">蕾丝衫/雪纺衫</a>
                                        <a href="#">针织衫</a>
                                        <a href="#">婚纱/旗袍/礼服</a>
                                        <a href="#">牛仔裤</a>
                                        <a href="#">打底衫</a>
                                        <a href="#">卫衣</a>
                                        <a href="#">小背心/小吊带</a>
                                        <a href="#">皮衣</a>
                                        <a href="#">羊毛衫</a>
                                        <a href="#">风衣</a>
                                        <a href="#">马夹</a>
                                        <a href="#">皮草</a>
                                        <a href="#">羽绒背心/羽绒马甲/棉背心</a>
                                        <a href="#">短外套</a>
                                        <a href="#">西装</a>
                                    </div>
                                    &lt;%&ndash;<dl class="clearfix">
                                        <dt><a href="">裙子</a></dt>
                                        <dd><a href="">连衣裙</a><a href="">半身裙</a></dd>
                                    </dl>
                                    <dl class="clearfix">
                                        <dt><a href="">裤子</a></dt>
                                        <dd><a href="">打底裤</a><a href="">休闲裤</a><a href="">牛仔裤</a></dd>
                                    </dl>
                                    <dl class="clearfix">
                                        <dt><a href="">裙子</a></dt>
                                        <dd><a href="">连衣裙</a><a href="">半身裙</a></dd>
                                    </dl>
                                    <dl class="clearfix">
                                        <dt><a href="">裙子</a></dt>
                                        <dd><a href="">连衣裙</a><a href="">半身裙</a></dd>
                                    </dl>&ndash;%&gt;
                                </div>
                                <div class="type-brand">
                                    <a href="" class="js-img-center"><img src="images/temp/duocai.jpg" alt=""></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    男装
                                </h3>
                                <a title="" class="type-item-more" href="/common/dealer/productList?dealMain=100000000&amp;q=" target="_blank">更多</a>
                            </div>
                            <div class="type-item-sub">
                                <div class="type-name">男装</div>
                                <div class="type-cate">
                                    <div class="list">
                                        <a href="#">T恤</a>
                                        <a href="#">衬衫</a>
                                        <a href="#">休闲长裤 </a>
                                        <a href="#">长袖衬衫</a>
                                        <a href="#">毛衣/线衣</a>
                                        <a href="#">短袖衬衫</a>
                                        <a href="#">牛仔裤</a>
                                        <a href="#">长袖T恤</a>
                                        <a href="#">外套</a>
                                        <a href="#">棉衣</a>
                                        <a href="#">夹克</a>
                                        <a href="#">休闲短裤</a>
                                        <a href="#">羽绒服</a>
                                        <a href="#">针织衫</a>
                                        <a href="#">背心</a>
                                        <a href="#">羊绒衫</a>
                                        <a href="#">西服</a>
                                        <a href="#">风衣</a>
                                        <a href="#">呢大衣</a>
                                        <a href="#">皮衣</a>
                                        <a href="#">Polo衫</a>
                                        <a href="#">卫衣</a>
                                        <a href="#">西裤</a>
                                        <a href="#">西服套装</a>
                                        <a href="#">马夹</a>
                                        <a href="#">棉裤</a>
                                        <a href="#">羽绒裤</a>
                                        <a href="#">羽绒背心/羽绒马甲/棉背心</a>
                                    </div>
                                </div>
                                <div class="type-brand">
                                    <a href="" class="js-img-center"><img src="images/temp/duocai.jpg" alt=""></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    内衣/家居服
                                </h3>
                                <a title="" class="type-item-more" href="/common/dealer/productList?dealMain=103000000&amp;q=" target="_blank">更多</a>
                            </div>
                            <div class="type-item-sub">
                                <div class="type-name">内衣/家居服</div>
                                <div class="type-cate">
                                    <div class="list">
                                        <a href="#">长/袜/打底袜/丝袜</a>
                                        <a href="#">文胸</a>
                                        <a href="#">保暖内衣</a>
                                        <a href="#">男士内裤</a>
                                        <a href="#">女士内裤</a>
                                        <a href="#">女士睡衣</a>
                                        <a href="#">男士内衣</a>
                                        <a href="#">塑身连体衣</a>
                                        <a href="#">性感内衣</a>
                                        <a href="#">吊带/内衣配件</a>
                                        <a href="#">保暖裤</a>
                                        <a href="#">塑身美体衣</a>
                                        <a href="#">睡裙</a>
                                        <a href="#">男士睡衣</a>
                                        <a href="#">塑身美体裤</a>
                                        <a href="#">抹胸</a>
                                        <a href="#">家居短袖</a>
                                        <a href="#">无缝羽绒内衣</a>
                                    </div>
                                </div>
                                <div class="type-brand">
                                    <a href="" class="js-img-center"><img src="images/temp/duocai.jpg" alt=""></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    家纺
                                </h3>
                                <a title="" class="type-item-more" href="/common/dealer/productList?dealMain=103000000&amp;q=" target="_blank">更多</a>
                            </div>
                            <div class="type-item-sub">
                                <div class="type-name">家纺</div>
                                <div class="type-cate">
                                    <div class="list">
                                        <a href="#">毛巾/浴巾/浴袍</a>
                                        <a href="#">被子/蚕丝被/纤维被</a>
                                        <a href="#">床单/床笠/床裙/床罩</a>
                                        <a href="#">四件套</a>
                                        <a href="#">枕套/枕巾</a>
                                        <a href="#">枕头/枕芯</a>
                                        <a href="#">保健枕/护颈枕</a>
                                        <a href="#">床品套件</a>
                                        <a href="#">儿童床品</a>
                                        <a href="#">毛毯/绒毯 靠垫/抱枕</a>
                                        <a href="#">床褥/床垫</a>
                                        <a href="#">蚊帐/床幔</a>
                                        <a href="#">蚕丝被</a>
                                        <a href="#">凉席</a>
                                        <a href="#">羊毛被</a>
                                        <a href="#">慢回弹记忆枕</a>
                                        <a href="#">乳胶枕</a>
                                        <a href="#">花边/拉链/布贴/DIY配件</a>
                                        <a href="#">空调被</a>
                                        <a href="#">其他</a>
                                    </div>
                                </div>
                                <div class="type-brand">
                                    <a href="" class="js-img-center"><img src="images/temp/duocai.jpg" alt=""></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    童装
                                </h3>
                                <a title="" class="type-item-more" href="/common/dealer/productList?dealMain=103000000&amp;q=" target="_blank">更多</a>
                            </div>
                            <div class="type-item-sub">
                                <div class="type-name">童装</div>
                                <div class="type-cate">
                                    <div class="list">
                                        <a href="#">儿童套装</a>
                                        <a href="#">儿童外套</a>
                                        <a href="#">儿童卫衣/绒衫 </a>
                                        <a href="#">儿童毛衣</a>
                                        <a href="#">儿童棉袄/羽绒服</a>
                                        <a href="#">儿童连衣裙</a>
                                        <a href="#">儿童马甲/背心</a>
                                        <a href="#">儿童T恤/吊带衫</a>
                                        <a href="#">婴儿连身衣/爬服</a>
                                        <a href="#">儿童衬衫</a>
                                        <a href="#">儿童裤子</a>
                                        <a href="#">羽绒儿童帽子服</a>
                                        <a href="#">儿童羊毛裤</a>
                                        <a href="#">披风/斗篷</a>
                                        <a href="#">儿童夹克/皮衣</a>
                                        <a href="#">童装风衣</a>
                                        <a href="#">内衣裤/睡衣</a>
                                        <a href="#">童装裙装</a>
                                        <a href="#">针织衫</a>
                                        <a href="#">儿童保暖内衣</a>
                                        <a href="#">儿童半身裙</a>
                                        <a href="#">其它童装</a>
                                    </div>
                                </div>
                                <div class="type-brand">
                                    <a href="" class="js-img-center"><img src="images/temp/duocai.jpg" alt=""></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    鞋帽
                                </h3>
                                <a title="" class="type-item-more" href="/common/dealer/productList?dealMain=103000000&amp;q=" target="_blank">更多</a>
                            </div>
                            <div class="type-item-sub">
                                <div class="type-name">鞋帽</div>
                                <div class="type-cate">
                                    <div class="list">
                                        <a href="#">日常休闲鞋</a>
                                        <a href="#">儿童外套</a>
                                        <a href="#">帆布鞋</a>
                                        <a href="#">增高鞋</a>
                                        <a href="#">板鞋</a>
                                        <a href="#">商务休闲鞋</a>
                                        <a href="#">正装皮鞋</a>
                                        <a href="#">低帮皮鞋</a>
                                        <a href="#">懒人鞋</a>
                                        <a href="#">雪地靴</a>
                                        <a href="#">凉鞋</a>
                                        <a href="#">凉拖</a>
                                        <a href="#">运动休闲鞋</a>
                                    </div>
                                </div>
                                <div class="type-brand">
                                    <a href="" class="js-img-center"><img src="images/temp/duocai.jpg" alt=""></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                </div>
                            </div>
                        </div>
                        <div class="type-item" style="padding-bottom:16px;border-bottom: 0;">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    日用家居
                                </h3>
                                <a title="" class="type-item-more" href="" target="_blank">更多</a>
                            </div>
                            <div class="type-item-sub">
                                <div class="type-name">日用家居</div>
                                <div class="type-cate">
                                    &lt;%&ndash;<dl class="clearfix">
                                        <dt><a href="">裙子</a></dt>
                                        <dd><a href="">连衣裙</a><a href="">半身裙</a></dd>
                                    </dl>
                                    <dl class="clearfix">
                                        <dt><a href="">裤子</a></dt>
                                        <dd><a href="">打底裤</a><a href="">休闲裤</a><a href="">牛仔裤</a></dd>
                                    </dl>
                                    <dl class="clearfix">
                                        <dt><a href="">裙子</a></dt>
                                        <dd><a href="">连衣裙</a><a href="">半身裙</a></dd>
                                    </dl>
                                    <dl class="clearfix">
                                        <dt><a href="">裙子</a></dt>
                                        <dd><a href="">连衣裙</a><a href="">半身裙</a></dd>
                                    </dl>&ndash;%&gt;
                                </div>
                                <div class="type-brand">
                                    <a href="" class="js-img-center"><img src="images/temp/duocai.jpg" alt=""></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                    <a href="" class="js-img-center"></a>
                                </div>
                            </div>
                        </div>
                    </div>--%>
                </div>
            </div>
            <ul class="nav-items js-nav-items clearfix">
                <li><a href="/" title="8637品牌超级代理" class="hover">首页</a></li>
                <li><a href="${ctx}/iambrand/index">女装</a></li>
                <li><a href="${ctx}/iambrand/index">男装</a></li>
                <li><a href="${ctx}/iambrand/index">内衣</a></li>
                <li><a href="${ctx}/iambrand/index">家纺</a></li>
                <li><a href="${ctx}/iambrand/index">童装</a></li>
                <li><a href="${ctx}/iambrand/index">鞋帽</a></li>
                <li><a href="${ctx}/iambrand/index">更多</a></li>
                <li><a href="${ctx}/iambrand/index">品牌加盟</a></li>
                <%--<li><a href="${ctx}/iambrand/index">品牌订货会</a></li>--%>
                <li><a href="${ctx}/iambrand/index">工厂店加盟</a></li>
            </ul>
            <div class="nav-move-item"></div>
        </div>
    </div>
    <%-- nav end --%>
    <div class="main">
        <div class="col-1">
            <div class="ts-container clearfix pr">
                <div class="focus">
                    <%--<img src="images/temp/focus.jpg" alt=""/>--%>
                    <div id="focus">
                        <ul class="ui-switchable-content">
                            <li class="focus-list"><a href=""><img src="images/temp/focus.jpg" alt=""/></a></li>
                            <li class="focus-list"><a href=""><img src="images/temp/focus.jpg" alt=""/></a></li>
                            <li class="focus-list"><a href=""><img src="images/temp/focus.jpg" alt=""/></a></li>
                            <li class="focus-list"><a href=""><img src="images/temp/focus.jpg" alt=""/></a></li>
                        </ul>
                    </div>
                </div>
                <div class="card">
                    <div class="card-hd" id="card-hd">
                        <div class="card－hd-view">
                            <ul class="inline-float">
                                <li class="item">
                                    <h3 title="绍兴雅格制衣厂通过验厂"><a href="#">绍兴雅格制衣厂通过验厂1</a></h3>
                                    <span title="桐乡市雅">桐乡市雅</span>
                                </li>
                                <li class="item">
                                    <h3 title="绍兴雅格制衣厂通过验厂"><a href="#">绍兴雅格制衣厂通过验厂2</a></h3>
                                    <span title="桐乡市雅">桐乡市雅</span>
                                </li>
                                <li class="item">
                                    <h3 title="绍兴雅格制衣厂通过验厂"><a href="#">绍兴雅格制衣厂通过验厂3</a></h3>
                                    <span title="桐乡市雅">桐乡市雅</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="card-login">
                        <div class="pic clearfix">
                            <img src="images/defaule_portrait.jpg" alt=""/>
                            <h2>HI你好！请<a href="">登录</a></h2>
                            <span>欢迎来到8637品牌超级代理</span>
                        </div>
                        <div class="btns clearfix">
                            <a href="#">终端商加盟</a>
                            <a class="ml10" href="#">品牌商入驻</a>
                        </div>
                    </div>
                    <div class="card-notice">
                        <div class="card-tab" id="card-tab">
                            <ul class="inline-float">
                                <li class="current">公告</li>
                                <li>品牌商入驻流程</li>
                                <li>终端商加盟流程</li>
                            </ul>
                        </div>
                        <div class="card-tab-content" id="card-tab-content">
                            <div class="card-news">
                                <ul>
                                    <li><a class="type-name" href="">[内衣]</a><a href="#" title="童装行业竞争格局分析">童装行业竞争格局分析</a></li>
                                    <li><a class="type-name" href="">[内衣]</a><a href="#" title="童装行业竞争格局分析">童装行业竞争格局分析</a></li>
                                    <li><a class="type-name" href="">[内衣]</a><a href="#" title="童装行业竞争格局分析">童装行业竞争格局分析</a></li>
                                    <li><a class="type-name" href="">[内衣]</a><a href="#" title="童装行业竞争格局分析">童装行业竞争格局分析</a></li>
                                    <li><a class="type-name" href="">[内衣]</a><a href="#" title="童装行业竞争格局分析">童装行业竞争格局分析</a></li>
                                    <li><a class="type-name" href="">[内衣]</a><a href="#" title="童装行业竞争格局分析">童装行业竞争格局分析</a></li>
                                </ul>
                            </div>
                            <div class="card-news" style="display: none;"></div>
                            <div class="card-news" style="display: none;"></div>
                        </div>
                    </div>
                    <div class="card-other">
                        <a href=""><i class="icon-home"></i>智慧门店管理系统</a>
                        <a href="" class="ml5"><i class="icon-phone"></i>约逛-手机APP</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-2 ts-container clearfix">
            <a href="" class="fl"><img src="images/temp/focus.jpg" alt="" width="210" height="120"/></a>
            <div class="brand-list fl">
                <a class="prev" href="javascript:;">&nbsp;</a>
                <a class="next" href="javascript:;">&nbsp;</a>
                <div class="brand-list-con">
                    <div id="brand-list-con">
                        <ul class="ui-switchable-content">
                            <li class="brand-list-item">
                                <div>
                                    <a class="js-img-center" href=""><img src="images/temp/focus.jpg" alt="" /></a>
                                </div>
                                <div class="brand-name">
                                    <a href="" title="彩羊1">彩羊1</a>
                                </div>
                            </li>
                            <li class="brand-list-item">
                                <div>
                                    <a class="js-img-center" href=""><img src="images/temp/duocai.jpg" alt="" /></a>
                                </div>
                                <div class="brand-name">
                                    <a href="" title="彩羊1">彩羊12</a>
                                </div>
                            </li>
                            <li class="brand-list-item">
                                <div>
                                    <a class="js-img-center" href=""><img src="images/temp/focus.jpg" alt="" /></a>
                                </div>
                                <div class="brand-name">
                                    <a href="" title="彩羊1">彩羊1</a>
                                </div>
                            </li>
                            <li class="brand-list-item">
                                <div>
                                    <a class="js-img-center" href=""><img src="images/temp/duocai.jpg" alt="" /></a>
                                </div>
                                <div class="brand-name">
                                    <a href="" title="彩羊1">彩羊12</a>
                                </div>
                            </li>
                            <li class="brand-list-item">
                                <div>
                                    <a class="js-img-center" href=""><img src="images/temp/focus.jpg" alt="" /></a>
                                </div>
                                <div class="brand-name">
                                    <a href="" title="彩羊1">彩羊1</a>
                                </div>
                            </li>
                            <li class="brand-list-item">
                                <div>
                                    <a class="js-img-center" href=""><img src="images/temp/duocai.jpg" alt="" /></a>
                                </div>
                                <div class="brand-name">
                                    <a href="" title="彩羊1">彩羊12</a>
                                </div>
                            </li>
                            <li class="brand-list-item">
                                <div>
                                    <a class="js-img-center" href=""><img src="images/temp/focus.jpg" alt="" /></a>
                                </div>
                                <div class="brand-name">
                                    <a href="" title="彩羊1">彩羊1</a>
                                </div>
                            </li>
                            <li class="brand-list-item">
                                <div>
                                    <a class="js-img-center" href=""><img src="images/temp/duocai.jpg" alt="" /></a>
                                </div>
                                <div class="brand-name">
                                    <a href="" title="彩羊1">彩羊12</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="brand ts-container">
            <div class="brand-hd clearfix">
                <h4 class="fl">优选品牌</h4>
                <span class="fl">大牌时尚抢先体验，绝对不容错过</span>
                <a href="javascript:;" class="fr js-change-brand"><i class="icon-change"></i>换一批再看看</a>
            </div>
            <div class="brand-bd clearfix" id="change_brand">
                <a href="" style="width: 197px;"><img src="images/temp/brand_01.jpg" alt="" /></a>
                <a href="" style="width: 395px;"><img src="images/temp/brand_02.jpg" alt="" /></a>
                <a href="" style="width: 196px;"><img src="images/temp/brand_03.jpg" alt="" /></a>
                <a href="" style="width: 197px;"><img src="images/temp/brand_01.jpg" alt="" /></a>
                <a href="" style="width: 197px;"><img src="images/temp/brand_03.jpg" alt="" /></a>

                <a href="" style="width: 197px;"><img src="images/temp/brand_01.jpg" alt="" /></a>
                <a href="" style="width: 196px;"><img src="images/temp/brand_03.jpg" alt="" /></a>
                <a href="" style="width: 197px;"><img src="images/temp/brand_01.jpg" alt="" /></a>
                <a href="" style="width: 395px;"><img src="images/temp/brand_02.jpg" alt="" /></a>
                <a href="" style="width: 197px;"><img src="images/temp/brand_03.jpg" alt="" /></a>
            </div>
        </div>
        <%--brand end--%>
        <%--<div></div>--%>
        <%--品牌订货会预留位置 结束--%>
        <div class="ts-container">
            <div class="hall clearfix">
                <div class="fl hall-m">
                    <div class="tit clearfix">
                        <h4 class="fl">女装品牌馆</h4>
                        <a href="" class="more_1 fr"></a>
                    </div>
                    <div class="hall-focus">
                        <a href="javascript:;" class="prev">&lt;</a>
                        <a href="javascript:;" class="next">&gt;</a>
                        <div class="hall-focus-content">
                            <ul>
                                <li class="hall-focus-list"><a href=""><img src="images/temp/hall_big.png" alt="" width="239" height="390" /></a></li>
                                <li class="hall-focus-list"><a href=""><img src="images/temp/hall_big.png" alt="" width="239" height="390" /></a></li>
                                <li class="hall-focus-list"><a href=""><img src="images/temp/hall_big.png" alt="" width="239" height="390" /></a></li>
                            </ul>
                        </div>
                        <div class="hall-focus-bg">
                            <a href="1" class="js-img-center"><img src="images/temp/duocai.jpg" alt=""></a>
                            <a href="2" class="js-img-center ml5"><img src="images/temp/duocai.jpg" alt=""></a>
                            <a href="3" class="js-img-center ml5"><img src="images/temp/duocai.jpg" alt=""></a>
                        </div>
                    </div>
                </div>
                <div class="hall-list fl">
                    <ul>
                        <li>
                            <div class="hall-list-hd" title="丝中娇"><a href="">丝中娇</a></div>
                            <div class="ta-c">
                                <a href=""><img src="images/temp/hall_01.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-bd clearfix">
                                <a href=""><img src="images/temp/hall_sm_01.jpg" alt="" /></a>
                                <a href="" class="ml20"><img src="images/temp/hall_sm_02.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-fd clearfix">
                                <span class="fd_1">99<br>产品数</span>
                                <span class="fd_2"><em>50%</em><br>品牌特惠</span>
                                <a href="javascript:;" class="fd_3">申请<br>加盟</a>
                            </div>
                        </li>
                        <li>
                            <div class="hall-list-hd">
                                丝中娇
                            </div>
                            <div class="ta-c">
                                <a href=""><img src="images/temp/hall_02.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-bd clearfix">
                                <a href=""><img src="images/temp/hall_sm_01.jpg" alt="" /></a>
                                <a href="" class="ml20"><img src="images/temp/hall_sm_02.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-fd clearfix">
                                <span class="fd_1">99<br>产品数</span>
                                <span class="fd_2"><em>50%</em><br>品牌特惠</span>
                                <a href="javascript:;" class="fd_3">申请<br>加盟</a>
                            </div>
                        </li>
                        <li>
                            <div class="hall-list-hd">
                                丝中娇
                            </div>
                            <div class="ta-c">
                                <a href=""><img src="images/temp/hall_03.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-bd clearfix">
                                <a href=""><img src="images/temp/hall_sm_01.jpg" alt="" /></a>
                                <a href="" class="ml20"><img src="images/temp/hall_sm_02.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-fd clearfix">
                                <span class="fd_1">99<br>产品数</span>
                                <span class="fd_2"><em>50%</em><br>品牌特惠</span>
                                <a href="javascript:;" class="fd_3">申请<br>加盟</a>
                            </div>
                        </li>
                        <li>
                            <div class="hall-list-hd">
                                丝中娇
                            </div>
                            <div class="ta-c">
                                <a href=""><img src="images/temp/hall_04.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-bd clearfix">
                                <a href=""><img src="images/temp/hall_sm_01.jpg" alt="" /></a>
                                <a href="" class="ml20"><img src="images/temp/hall_sm_02.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-fd clearfix">
                                <span class="fd_1">99<br>产品数</span>
                                <span class="fd_2"><em>50%</em><br>品牌特惠</span>
                                <a href="javascript:;" class="fd_3">申请<br>加盟</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="hall clearfix">
                <div class="fl hall-m">
                    <div class="tit clearfix">
                        <h4 class="fl">男装品牌馆</h4>
                        <a href="" class="more_1 fr"></a>
                    </div>
                    <div class="hall-focus">
                        <a href="javascript:;" class="prev">&lt;</a>
                        <a href="javascript:;" class="next">&gt;</a>
                        <div class="hall-focus-content">
                            <ul>
                                <li class="hall-focus-list"><a href=""><img src="images/temp/hall_big.png" alt="" width="239" height="390" /></a></li>
                                <li class="hall-focus-list"><a href=""><img src="images/temp/hall_01.jpg" alt="" width="239" height="390" /></a></li>
                                <li class="hall-focus-list"><a href=""><img src="images/temp/hall_big.png" alt="" width="239" height="390" /></a></li>
                            </ul>
                        </div>
                        <div class="hall-focus-bg">
                            <a href="1" class="js-img-center"><img src="images/temp/duocai.jpg" alt=""></a>
                            <a href="2" class="js-img-center ml5"><img src="images/temp/duocai.jpg" alt=""></a>
                            <a href="3" class="js-img-center ml5"><img src="images/temp/duocai.jpg" alt=""></a>
                        </div>
                    </div>
                </div>
                <div class="hall-list fl">
                    <ul>
                        <li>
                            <div class="hall-list-hd" title="丝中娇">丝中娇</div>
                            <div class="ta-c">
                                <a href=""><img src="images/temp/hall_01.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-bd clearfix">
                                <a href=""><img src="images/temp/hall_sm_01.jpg" alt="" /></a>
                                <a href="" class="ml20"><img src="images/temp/hall_sm_02.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-fd clearfix">
                                <span class="fd_1">99<br>产品数</span>
                                <span class="fd_2"><em>50%</em><br>品牌特惠</span>
                                <a href="javascript:;" class="fd_3">申请<br>加盟</a>
                            </div>
                        </li>
                        <li>
                            <div class="hall-list-hd">
                                丝中娇
                            </div>
                            <div class="ta-c">
                                <a href=""><img src="images/temp/hall_02.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-bd clearfix">
                                <a href=""><img src="images/temp/hall_sm_01.jpg" alt="" /></a>
                                <a href="" class="ml20"><img src="images/temp/hall_sm_02.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-fd clearfix">
                                <span class="fd_1">99<br>产品数</span>
                                <span class="fd_2"><em>50%</em><br>品牌特惠</span>
                                <a href="javascript:;" class="fd_3">申请<br>加盟</a>
                            </div>
                        </li>
                        <li>
                            <div class="hall-list-hd">
                                丝中娇
                            </div>
                            <div class="ta-c">
                                <a href=""><img src="images/temp/hall_03.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-bd clearfix">
                                <a href=""><img src="images/temp/hall_sm_01.jpg" alt="" /></a>
                                <a href="" class="ml20"><img src="images/temp/hall_sm_02.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-fd clearfix">
                                <span class="fd_1">99<br>产品数</span>
                                <span class="fd_2"><em>50%</em><br>品牌特惠</span>
                                <a href="javascript:;" class="fd_3">申请<br>加盟</a>
                            </div>
                        </li>
                        <li>
                            <div class="hall-list-hd">
                                丝中娇
                            </div>
                            <div class="ta-c">
                                <a href=""><img src="images/temp/hall_04.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-bd clearfix">
                                <a href=""><img src="images/temp/hall_sm_01.jpg" alt="" /></a>
                                <a href="" class="ml20"><img src="images/temp/hall_sm_02.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-fd clearfix">
                                <span class="fd_1">99<br>产品数</span>
                                <span class="fd_2"><em>50%</em><br>品牌特惠</span>
                                <a href="javascript:;" class="fd_3">申请<br>加盟</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="hall hall-more clearfix">
                <div class="hall-list fl">
                    <ul class="clearfix">
                        <li>
                            <div class="hall-bat">
                                <div class="tit clearfix">
                                    <h4 class="fl"><i class="icon-children"></i>童装母婴</h4>
                                    <a href="" class="more_1 fr"></a>
                                </div>
                                <div class="hall-tag">
                                    <a href="">夏季新品</a><a href="">商场同款</a><a href="">男装</a><a href="">女装</a>
                                </div>
                            </div>
                            <div class="hall-list-hd" title="丝中娇">丝中娇</div>
                            <div class="ta-c">
                                <a href=""><img src="images/temp/hall_01.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-bd clearfix">
                                <a href=""><img src="images/temp/hall_sm_01.jpg" alt="" /></a>
                                <a href="" class="ml20"><img src="images/temp/hall_sm_02.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-fd clearfix">
                                <span class="fd_1">99<br>产品数</span>
                                <span class="fd_2"><em>50%</em><br>品牌特惠</span>
                                <a href="javascript:;" class="fd_3">申请<br>加盟</a>
                            </div>
                        </li>
                        <li>
                            <div class="hall-bat">
                                <div class="tit clearfix">
                                    <h4 class="fl"><i class="icon-daily"></i>童装母婴</h4>
                                    <a href="" class="more_1 fr"></a>
                                </div>
                                <div class="hall-tag">
                                    <a href="">夏季新品</a><a href="">商场同款</a><a href="">男装</a><a href="">女装</a>
                                </div>
                            </div>
                            <div class="hall-list-hd">
                                丝中娇
                            </div>
                            <div class="ta-c">
                                <a href=""><img src="images/temp/hall_02.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-bd clearfix">
                                <a href=""><img src="images/temp/hall_sm_01.jpg" alt="" /></a>
                                <a href="" class="ml20"><img src="images/temp/hall_sm_02.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-fd clearfix">
                                <span class="fd_1">99<br>产品数</span>
                                <span class="fd_2"><em>50%</em><br>品牌特惠</span>
                                <a href="javascript:;" class="fd_3">申请<br>加盟</a>
                            </div>
                        </li>
                        <li>
                            <div class="hall-bat">
                                <div class="tit clearfix">
                                    <h4 class="fl"><i class="icon-parts"></i>童装母婴</h4>
                                    <a href="" class="more_1 fr"></a>
                                </div>
                                <div class="hall-tag">
                                    <a href="">夏季新品</a><a href="">商场同款</a><a href="">男装</a><a href="">女装</a>
                                </div>
                            </div>
                            <div class="hall-list-hd">
                                丝中娇
                            </div>
                            <div class="ta-c">
                                <a href=""><img src="images/temp/hall_03.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-bd clearfix">
                                <a href=""><img src="images/temp/hall_sm_01.jpg" alt="" /></a>
                                <a href="" class="ml20"><img src="images/temp/hall_sm_02.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-fd clearfix">
                                <span class="fd_1">99<br>产品数</span>
                                <span class="fd_2"><em>50%</em><br>品牌特惠</span>
                                <a href="javascript:;" class="fd_3">申请<br>加盟</a>
                            </div>
                        </li>
                        <li>
                            <div class="hall-bat">
                                <div class="tit clearfix">
                                    <h4 class="fl"><i class="icon-shoe"></i>童装母婴</h4>
                                    <a href="" class="more_1 fr"></a>
                                </div>
                                <div class="hall-tag">
                                    <a href="">夏季新品</a><a href="">商场同款</a><a href="">男装</a><a href="">女装</a>
                                </div>
                            </div>
                            <div class="hall-list-hd">
                                丝中娇
                            </div>
                            <div class="ta-c">
                                <a href=""><img src="images/temp/hall_04.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-bd clearfix">
                                <a href=""><img src="images/temp/hall_sm_01.jpg" alt="" /></a>
                                <a href="" class="ml20"><img src="images/temp/hall_sm_02.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-fd clearfix">
                                <span class="fd_1">99<br>产品数</span>
                                <span class="fd_2"><em>50%</em><br>品牌特惠</span>
                                <a href="javascript:;" class="fd_3">申请<br>加盟</a>
                            </div>
                        </li>
                        <li>
                            <div class="hall-bat">
                                <div class="tit clearfix">
                                    <h4 class="fl"><i class="icon-jewel"></i>童装母婴</h4>
                                    <a href="" class="more_1 fr"></a>
                                </div>
                                <div class="hall-tag">
                                    <a href="">夏季新品</a><a href="">商场同款</a><a href="">男装</a><a href="">女装</a>
                                </div>
                            </div>
                            <div class="hall-list-hd">
                                丝中娇
                            </div>
                            <div class="ta-c">
                                <a href=""><img src="images/temp/hall_04.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-bd clearfix">
                                <a href=""><img src="images/temp/hall_sm_01.jpg" alt="" /></a>
                                <a href="" class="ml20"><img src="images/temp/hall_sm_02.jpg" alt="" /></a>
                            </div>
                            <div class="hall-list-fd clearfix">
                                <span class="fd_1">99<br>产品数</span>
                                <span class="fd_2"><em>50%</em><br>品牌特惠</span>
                                <a href="javascript:;" class="fd_3">申请<br>加盟</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="mt20">
                <img src="images/temp/ad.jpg" alt="" width="1190" height="90" />
            </div>
        </div>
        <div class="news mt20">
            <div class="ts-container clearfix">
                <div class="fl news-l">
                    <div class="news-menu">
                        <a href="" class="news-cate" title="平台公告" target="_blank">平台公告</a>
                        <span class="news-line"></span>
                        <a href="" class="news-cate" title="平台资讯" target="_blank">平台资讯</a>
                        <span class="news-line"></span>
                        <a href="" class="news-cate" title="品牌资讯" target="_blank">品牌资讯</a>
                        <span class="news-line"></span>
                        <a href="" class="news-cate" title="电商动态" target="_blank">电商动态</a>
                        <span class="news-line"></span>
                        <a href="" class="news-cate" title="潮流趋势" target="_blank">潮流趋势</a>
                        <span class="news-line"></span>
                        <a href="" class="news-cate" title="产经资讯" target="_blank">产经资讯</a>
                        <span class="news-line"></span>
                        <a href="" class="news-cate" title="展会报道" target="_blank">展会报道</a>
                        <span class="news-line"></span>
                        <a href="" class="news-cate" title="统计报告" target="_blank">统计报告</a>
                        <span class="news-line"></span>
                        <a href="" class="news-cate" title="政策参考" target="_blank">政策参考</a>
                    </div>
                    <div class="news-content clearfix">
                        <div class="news-pic fl">
                            <div id="news-pic">
                                <ul>
                                    <li class="news-pic-item">
                                        <a href=""><img src="images/temp/focus.jpg" alt="" width="365" height="250"/></a>
                                        <span class="title" title="冰丝凉席的作用冰丝凉席的作用冰丝凉席的作用冰丝凉席的作用">1冰丝凉席的作用冰丝凉席的作用冰丝凉席的作用冰丝凉席的作用</span>
                                        <span class="tit"></span>
                                    </li>
                                    <li class="news-pic-item">
                                        <a href=""><img src="images/temp/brand_02.jpg" alt="" width="365" height="250"/></a>
                                        <span class="title" title="冰丝凉席的作用冰丝凉席的作用冰丝凉席的作用冰丝凉席的作用">2冰丝凉席的作用冰丝凉席的作用冰丝凉席的作用冰丝凉席的作用</span>
                                        <span class="tit"></span>
                                    </li>
                                    <li class="news-pic-item">
                                        <a href=""><img src="images/temp/focus.jpg" alt="" width="365" height="250"/></a>
                                        <span class="title" title="冰丝凉席的作用冰丝凉席的作用冰丝凉席的作用冰丝凉席的作用">3冰丝凉席的作用冰丝凉席的作用冰丝凉席的作用冰丝凉席的作用</span>
                                        <span class="tit"></span>
                                    </li>
                                    <li class="news-pic-item">
                                        <a href=""><img src="images/temp/brand_02.jpg" alt="" width="365" height="250"/></a>
                                        <span class="title" title="冰丝凉席的作用冰丝凉席的作用冰丝凉席的作用冰丝凉席的作用">4冰丝凉席的作用冰丝凉席的作用冰丝凉席的作用冰丝凉席的作用</span>
                                        <span class="tit"></span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="news-list fr">
                            <h4>品牌资讯</h4>
                            <ul>
                                <li class="news-hot">
                                    <a href=""><em>[童装]</em> 童装行业竞争格局分析</a>
                                    <div class="news-des">
                                        <a href="">我国童装市场发展历史要晚于正装及休闲装，我国童装市场发展历史要晚于正装及休闲装，我国童装市场发展历史要晚于正装及休闲装</a>
                                    </div>
                                </li>
                                <li><a href=""><em>[童装]</em> 童装行业竞争格局分析</a></li>
                                <li><a href=""><em>[童装]</em> 童装行业竞争格局分析</a></li>
                                <li><a href=""><em>[童装]</em> 童装行业竞争格局分析</a></li>
                                <li><a href=""><em>[童装]</em> 童装行业竞争格局分析</a></li>
                                <li><a href=""><em>[童装]</em> 童装行业竞争格局分析</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="fr news-r">
                    <div class="news-join">
                        <div class="news-join-hd">找不到好的项目，来<em>8637品牌超级代理</em><br />这里有最低的品牌直供价格</div>
                        <div class="news-join-bd clearfix">
                            <a href="" class="news-btn">终端商加盟</a>
                            <span class="news-join-line"></span>
                            <a href="" class="news-btn">品牌商加盟</a>
                        </div>
                        <div class="news-join-fd">
                            <p class="p1">品牌渠道建设慢，效果差，费用高，来8637品牌超级代理，这里有海量的中国门店信息为您的品牌打开更广的市场</p>
                            <p class="p2">加盟服务热线：<em>400-111-8637</em></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <div class="ts-container clearfix">
            <div class="footer-coll fl">
                <img src="images/f-logo.gif" alt=""/>
            </div>
            <div class="footer-colr fl">
                <div class="clearfix footer-sev">
                    <ul class="footer-severs inline-float fl">
                        <li>
                            <dl>
                                <dt>8637保障</dt>
                                <dd><a href="http://www${zttx}/rules/" target="_blank">品牌授权</a></dd>
                                <dd><a href="http://www${zttx}/rules/" target="_blank">规则中心</a></dd>
                                <dd><a href="http://www.8637.com/common/rules/info/2FB444A80857476890C42CFB7A7E6336" target="_blank">退换货保障</a></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt>新手上路</dt>
                                <dd><a href="http://www${zttx}/common/register" target="_blank">注册会员</a></dd>
                                <dd><a href="http://www${zttx}/help/index" target="_blank">常见问题</a></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt>品牌商专区</dt>
                                <dd><a href="http://www${zttx}/apply/brandsSettled.jsp" target="_blank">申请入驻</a></dd>
                                <dd><a href="http://www${zttx}/brand/center" target="_blank">品牌管理</a></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt>终端商专区</dt>
                                <dd><a href="http://www${zttx}/apply/brandsSettled.jsp" target="_blank">申请入驻</a></dd>
                                <dd><a href="http://www${zttx}/dealer/center" target="_blank">加盟管理</a></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt>特色服务</dt>
                                <dd><a href="http://app${zttx}" target="_blank">约逛-手机App</a></dd>
                                <dd><a href="http://www${zttx}/soft/erp/" target="_blank">智慧门店管理系统</a></dd>
                            </dl>
                        </li>
                    </ul>
                    <div class="fl footer-qrcode">
                        <h3>关注8637动态</h3>
                        <img src="images/f-qrcode.gif" alt=""/>
                    </div>
                </div>
                <div class="footer-contact clearfix">
                    <p>
                        <span>全国免费招商热线：400-111-8637</span>
                        <span class="line"></span>
                        <a href="http://www${zttx}/about/" target="_blank">关于8637品牌超级代理</a>
                        <span class="line"></span>
                        <a href="http://www${zttx}/help/index" target="_blank">帮助中心</a>
                        <span class="line"></span>
                        <a href="http://www${zttx}/about/joinus.jsp" target="_blank">诚聘英才</a>
                        <span class="line"></span>
                        <a href="http://www${zttx}/about/contactus.jsp" target="_blank">联系我们</a>
                        <span class="line"></span>
                        <a href="http://www${zttx}/common/rules/list?cateId=7E02825C4B644058BB64E0ABDE23767B&cateType=1" target="_blank">版权说明</a>
                    </p>
                </div>
                <div class="footer-copyright">
                    Copyright&copy;2013-<script language="JavaScript" type="text/javascript">document.write(new Date().getFullYear());</script>，8637品牌超级代理版权所有 浙江天下商邦科技股份有限公司 客服热线：0574-87217777 浙ICP备14007458号-1
                </div>
            </div>
        </div>
    </div>
    <!--[if IE 6]>
    <div class="sorry-ie">
        <div class="alert">
            <a href="javascript:;" class="fr close-alert" style="margin-right: 20px;" >关闭</a>
            亲，您的浏览器版本过低导致图片打开速度过慢，提升打开速度您可以：<a href="http://windows.microsoft.com/zh-cn/windows/upgrade-your-browser" target="_blank">升级浏览器</a> 或者 点击下载 <a href="http://chrome.360.cn/" target="_blank">chrome浏览器</a>
        </div>
    </div>
    <![endif]-->
    <%-- footer end --%>
    <%--<jsp:include page="/WEB-INF/view/common/component/bottom.jsp"></jsp:include>--%>
    <div class="footer-bottom">
        <div class="ts-container clearfix">
            <div class="fl" style="width: 195px;">&nbsp;</div>
            <div class="fl" style="width: 920px;">
                <a target="_blank" href="http://idinfo.zjaic.gov.cn/bscx.do?method=hddoc&amp;id=33020000007109"><img src="/images/common/i_lo2.gif" border="0" height="45"></a>
                <%--<a key ="559b46c6efbfb017b0dcc81c" logo_size="124x47" logo_type="realname" href="http://www.anquan.org"><script src="http://static.anquan.org/static/outer/js/aq_auth.js"></script></a>--%>
            </div>
        </div>
    </div>
    <%-- footer-bottom end --%>
</div>
<%--<script src="${res}/scripts/common/index_uglify.js"></script>--%>
<script src="${res}/scripts/common/base-init.js"></script>
<script>
    var COMMON_ZHUANTI_FLAG = true;
    seajs.use(["gallery/lazyLoad/1.0.0/lazyLoad"],function(lazyLoad) {
        new lazyLoad();
    });
</script>
</body>
</html>
