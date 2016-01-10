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
    <script src="${res}/scripts/common.js"></script>
    <script src="v2_index.js"></script>
    <style>
        body,.main{background: #fff;}
        button, input, select, textarea, .main{
            font-family: Arial,"宋体";
        }
        .icon-del,
        .icon-close,
        .icon-close-down,
        .icon-nayang,
        .icon-shouxin,
        .icon-yuding,
        .icon-cat,
        .icon-collect,
        .icon-jhds,
        .icon-d-prev,
        .icon-d-next,
        .icon-f-prev,
        .icon-f-next,
        .icon-g-brand,
        .icon-r-brand,
        .icon-g-goods,
        .icon-r-goods{
            display: inline-block;
            *display: inline;
            *zoom: 1;
            background: url("images/index_product_list/icon.png") no-repeat -999em;
            vertical-align: -3px;
            *vertical-align: middle;
        }
        .icon-del{
            width: 7px;
            height: 7px;
            background-position: 0 0;
            vertical-align: 0px;
            *vertical-align: 3px;
        }
        .icon-close{
            width: 7px;
            height: 4px;
            margin-left: 5px;
            background-position: -24px 0;
            vertical-align: 2px;
            *vertical-align: 5px;
        }
        .icon-close-down{
            width: 7px;
            height: 4px;
            margin-left: 5px;
            background-position: -36px 0;
            vertical-align: 2px;
            *vertical-align: 5px;
        }
        .icon-nayang{
            width: 16px;
            height: 16px;
            background-position: -92px 0;
            vertical-align: -4px;
            *vertical-align: middle;
        }
        .icon-shouxin{
            width: 16px;
            height: 16px;
            background-position: -113px 0;
            vertical-align: -4px;
            *vertical-align: middle;
        }
        .icon-yuding{
            width: 16px;
            height: 16px;
            background-position: -134px 0;
            vertical-align: -4px;
            *vertical-align: middle;
        }
        .icon-cat{
            width: 16px;
            height: 16px;
            background-position: -155px 0;
            vertical-align: -4px;
            *vertical-align: middle;
        }
        .icon-collect{
            width: 11px;
            height: 10px;
            margin-right: 6px;
            background-position: -176px 0;
            vertical-align: 0px;
            *vertical-align: 3px;
        }
        .icon-jhds{
            width: 10px;
            height: 10px;
            margin-right: 6px;
            background-position: -190px 0;
            vertical-align: 0px;
            *vertical-align: 3px;
        }
        .icon-d-prev{
            width: 6px;
            height: 9px;
            background-position: -48px 0;
            vertical-align: 0;
            *vertical-align: 3px;
        }
        .icon-d-next{
            width: 6px;
            height: 9px;
            background-position: -81px 0;
            vertical-align: 0;
            *vertical-align: 3px;
        }
        .icon-f-prev{
            width: 6px;
            height: 9px;
            background-position: -59px 0;
            vertical-align: 0;
            *vertical-align: 3px;
        }
        .icon-f-next{
            width: 6px;
            height: 9px;
            background-position: -70px 0;
            vertical-align: 0;
            *vertical-align: 3px;
        }
        .icon-g-brand{
            width: 11px;
            height: 10px;
            margin-right: 5px;
            background-position: -205px 0;
            vertical-align: -1px;
            *vertical-align: 2px;
        }
        .icon-r-brand{
            width: 11px;
            height: 10px;
            margin-right: 5px;
            background-position: -221px 0;
            vertical-align: -1px;
            *vertical-align: 2px;
        }
        .icon-g-goods{
            width: 11px;
            height: 11px;
            margin-right: 5px;
            background-position: -238px 0;
            vertical-align: -2px;
            *vertical-align: 1px;
        }
        .icon-r-goods{
            width: 11px;
            height: 11px;
            margin-right: 5px;
            background-position: -254px 0;
            vertical-align: -2px;
            *vertical-align: 1px;
        }

        .nav .ts-container{border-bottom: 2px solid #ed0100;}
        .m-bread{padding: 10px 0;font-family: "宋体";}
        .m-bread a:hover{color: #e00;}
        .m-bread .m-b-1,
        .m-bread .m-b-2,
        .m-bread .m-b-3{float: left;display: inline;margin-top: 4px;color: #666;}
        .m-bread .m-b-4{float: right;display: inline;height: 24px;line-height: 24px;padding: 0 9px;border: 1px solid #e5e5e5;background-color: #f2f2f2;color: #666;}
        .m-bread .m-b-2{margin: 4px 8px 0 8px;color: #999;}
        .m-bread .m-b-3{margin-right: 5px;padding: 0 5px;border: 1px solid #e5e5e5;}
        .m-bread .icon-del{margin-left: 6px;}
        .m-bread .m-b-3:hover{border-color: #ed0100;}
        .m-bread .m-b-3:hover .icon-del{background-position: -12px 0;}
        .m-bread .m-b-4:hover .icon-close{background-position: -36px 0;}
        .m-brand{border: 1px solid #e5e5e5;}
        .m-brand .tit,
        .m-cates .tit,
        .m-guess .tit{width: 60px;padding: 10px 0 10px 15px;color: #999;}
        .m-brand .list,
        .m-cates .list,
        .m-guess .list{width: 870px;margin-left: 10px;}
        .m-brand .list ul{max-height: 204px;padding: 1px;overflow-y: auto;}
        .m-brand .list li{width: 100px;height: 50px;margin-top: -1px;margin-left: -1px;border: 1px solid #f2f2f2;position: relative;cursor: pointer;}
        .m-brand .list .js-img-center{width: 100px;height: 50px;}
        .m-brand .list .js-img-center img{max-width: 100px;max-height: 50px;}
        .m-brand .list .hover{position: absolute;top: -1px;left: -1px;width: 100px;height: 50px;line-height: 50px;border: 1px solid #ed0100;background-color: #fff;text-align: center;color: #e00;z-index: 1;}
        .m-brand .list .selected{position: absolute;top: -1px;left: -1px;width: 100px;height: 50px;border: 1px solid #666;background: url("images/index_product_list/del.png") right -120px no-repeat;z-index: 2;}
        .m-brand .list li:hover .hover{display: block;z-index: 3;}
        .m-brand .list li:hover .selected{border: 1px solid #ed0100;background: url("images/index_product_list/del.png") right 0 no-repeat;z-index: 4;}
        #brand_search{padding: 10px 0;}
        #brand_search input{height: 18px;line-height: 18px;padding: 1px 5px;border: 1px solid #e5e5e5;color: #666;}
        #brand_btns{padding: 7px 0;text-align: center;}
        #brand_btns a{display: inline-block;*display: inline;*zoom: 1;padding: 2px 10px;background: #f2f2f2;border: 1px solid #e5e5e5;}
        #brand_btns .brand_btns_confirm{margin-right: 5px;border-color: #ed0100;background-color: #ed0100;color: #fff;}
        #brand_btns .disabled{background: #f2f2f2;border: 1px solid #e5e5e5;color: #999;}
        #select_duo{padding: 0 5px;margin-right: 10px;border: 1px solid #e5e5e5;}
        .m-brand .more,
        .m-cates .more{width: 160px;padding: 10px 15px;}
        .m-cates{ margin-bottom:10px;border: 1px solid #e5e5e5;}
        .m-cates .list a,
        .m-guess a{margin-right: 30px;line-height: 40px;}
        .m-cates .list-filter{position: relative;}
        .m-cates .list-filter .drop{position: absolute;top: 40px;left: -76px;width: 1158px;line-height: 40px;padding: 0 15px;border: 1px solid #e5e5e5;background-color: #fff;display: none;}
        .m-cates .list-filter .drop a{margin-right: 30px;}
        .m-cates .list-filter li{padding: 0 20px 0 10px;line-height: 40px;}
        .m-cates .list-filter li:hover{background-color: #e5e5e5;}
        .m-cates .list-filter li:hover .drop{display: block;}
        .m-mod{margin-bottom: -1px;border-bottom: 1px dotted #ccc;}
        .mod-height{height: 40px;overflow: hidden;}
        .m-guess{border: 1px solid #e5e5e5;}

        .m-filter{height: 40px;margin-top:10px;background: #f2f2f2;}
        .m-filter .m-f-1{line-height: 40px;}
        .m-filter .m-f-1 a{display: block;padding: 0 23px;color:#666;-webkit-transition: 0.3s;-moz-transition: 0.3s;-o-transition: 0.3s;transition: 0.3s;}
        .m-filter .m-f-1 a:hover{background-color: #e4e4e4;}
        .m-filter .m-f-1.current{line-height: 38px;border: 1px solid #e5e5e5; background: #fff;}
        .m-filter .m-f-1.current a{color: #ed0100;}
        .m-filter .m-f-4{height: 10px;line-height:10px;margin-right:20px;padding: 15px 0;}
        .m-filter .m-f-4 span{color: #666;}
        .m-filter .m-f-4 em{color: #ef0000;}
        .m-filter .m-f-4 .prev{margin-right: 20px;}
        .m-filter .m-f-4 .next{margin-left: 20px;}

        .m-recom{border: 1px solid #f2f2f2;border-top:0;padding: 10px 10px 10px 25px;}
        .m-recom label{margin-right: 30px;color:#666;}
        .m-recom input{margin-right:5px;vertical-align: -3px;*vertical-align: middle;}
        .m-recom span{margin-right:5px;}
        .m-recom a{padding: 4px 10px;margin-left: 5px;border:1px solid #e5e5e5;}

        .m-list ul{margin-top: 20px;margin-right: -20px;}
        .m-list li{width:220px;margin-right: 20px;margin-bottom:20px;border: 1px solid #f3f3f3;font-family: "Microsoft yahei";position: relative;-webkit-transition: 0.3s;-moz-transition: 0.3s;-o-transition: 0.3s;transition: 0.3s;}
        .m-list .pic{}
        .m-list .pic .js-img-center{display:block;width: 220px;height: 220px;}
        .m-list .pic .js-img-center img{max-width: 220px;max-height: 220px;_width:220px;_height:220px;}
        .m-list .detail{ padding: 10px;color: #999;}
        .m-list .detail .price,
        .m-list .detail .title,
        .m-list .detail .number{overflow: hidden;text-overflow: ellipsis;white-space: nowrap;word-wrap: normal;}
        .m-list .detail .price em,.m-list .detail .price strong{color: #e00;font-size: 14px;}
        .m-list .detail .price strong{font-size: 14px;font-weight: normal;}
        .m-list .detail .price i{margin-left:10px;text-decoration:line-through;}
        .m-list .detail .title{padding: 5px 0;}
        .m-list .detail .number{}
        .m-list .detail .address{margin-bottom: 10px;}
        .m-list .detail .address a{color: #999;}
        .m-list .detail .address .fl{width: 150px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;word-wrap: normal;}
        .m-list .detail .address .fr{}
        .m-list .operate{position: absolute;top:190px;left: 0;width:220px;height: 30px;line-height: 30px;background-color: #eb0000;background-color: rgba(235, 0, 0, 0.8);display: none;-webkit-transition: 0.3s;-moz-transition: 0.3s;-o-transition: 0.3s;transition: 0.3s;}
        .m-list .operate .operate-collect,
        .m-list .operate .operate-jhds{float:left;display:inline;text-align: center;color: #fff;}
        .m-list .operate .operate-collect{width: 100px;}
        .m-list .operate .operate-jhds{width: 118px;border-left: 1px solid rgba(255, 255, 255, 0.3);}
        .m-list li:hover,
        .m-list li.hover{border-color: #eb0000;}
        .m-list li:hover .operate,
        .m-list li.hover .operate{display: block;}

        /*当前页面分页样式重置*/
        .pagination {background: #fff;text-align: center;font-size: 0;}
        .pagination a{-webkit-transition: 0.3s;-moz-transition: 0.3s;-o-transition: 0.3s;transition: 0.3s;}
        .pagination a:hover{ text-decoration: none;}
        .pagination .prev, .pagination .next, .pagination .page,.pagination .ellipsis{display: inline-block;*display:inline;*zoom:1;padding: 0;width: 33px;height: 33px;margin-left: -1px;line-height: 33px;border: #e5e5e5 solid 1px;background: #fff;font-size: 12px;font-family: "宋体";color: #666;}
        .pagination .prev:hover, .pagination .next:hover, .pagination .page:hover,.pagination .ellipsis:hover{background-color: #e4e4e4;}
        .pagination .current:hover{background-color: #ed0100;}
        .pagination .prev,.pagination .next{border-radius: 0; color: #999;}
        .pagination .prev{margin-right: 10px;}
        .pagination .next{margin-left: 10px;}
        .pagination .current {border-color:#ed0100;background-color: #ed0100;color: #fff;}
        .pagination .total {margin-left: 10px;font-size: 12px;}

        .m-related{margin-top: 30px;margin-bottom: 30px;font-family: 'Microsoft yahei';}
        .m-related .tit{height:40px;line-height:40px;padding: 0 20px;background-color: #f2f2f2;}
        .m-related .tit h3{font-size: 16px;}
        .m-related .list{width: 1188px;border: 1px solid #f2f2f2;overflow: hidden;}
        .m-related ul{margin-right: -18px;}
        .m-related li{ width: 201px; padding: 18px; border-right: 1px solid #f2f2f2;}
        .m-related li h4{margin-bottom:10px;text-align: center;font-size: 16px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;word-wrap: normal;}
        .m-related li h4 a{color:#333;}
        .m-related li .js-img-center{display:block;width: 201px;height: 201px;}
        .m-related li .js-img-center img{max-width: 201px;max-height: 201px;_width: 201px;_height:201px;}

        .m-brandlist{font-family: "Microsoft yahei";}
        .m-brandlist .itemul{margin-bottom: 30px;}
        .m-brandlist .item{width:1190px;padding: 20px 0;border-bottom:1px solid #e5e5e5;}
        .m-brandlist .item .i1,
        .m-brandlist .item .i2,
        .m-brandlist .item .i3,
        .m-brandlist .item .i4{
            float: left;
            display:inline;
            margin-right: 20px;
        }
        .m-brandlist .item .i1{width: 200px;text-align: center;}
        .m-brandlist .item .i2{width: 400px;}
        .m-brandlist .item .i3{width: 255px;}
        .m-brandlist .item .i4{margin-right: 0;}
        .m-brandlist .item .item-logo{}
        .m-brandlist .item .item-logo .js-img-center{display: inline-block;*display: inline;*zoom: 1;width:140px;height:70px;}
        .m-brandlist .item .item-logo .js-img-center img{max-width:140px;max-height:70px;_width:140px;_height:70px;}
        .m-brandlist .item .item-name{margin-top: 10px;margin-bottom: 20px;}
        .m-brandlist .item .item-follow{display: inline-block;*display: inline;*zoom: 1;padding:0 10px;;border: 1px solid #e5e5e5;border-radius: 3px;cursor: pointer;}
        .m-brandlist .item h3{margin-bottom:5px;font-size: 14px;}
        .m-brandlist .item .item-address{margin-bottom:20px;color:#666;}
        .m-brandlist .item .item-com{line-height: 24px;}
        .m-brandlist .item .item-com span{color: #666;}
        .m-brandlist .item .item-com em{color: #e00;}
        .m-brandlist .item .item-btns{margin-top:30px;}
        .m-brandlist .item .item-btns a{display: inline-block;*display: inline;*zoom: 1;width:98px;padding: 5px 0;background-color:#dcdbdb;text-align: center;color:#666;-webkit-transition: 0.3s;-moz-transition: 0.3s;-o-transition: 0.3s;transition: 0.3s;}
        .m-brandlist .item .item-btns a:hover{background-color: #b6b6b6;}
        .m-brandlist .item .item-btns .btn1{margin-right:10px;background-color:#e00;color:#fff;}
        .m-brandlist .item .item-btns .btn1:hover{background-color:#a60101;}
        .m-brandlist .item .item-other{border:1px solid #f2f2f2;}
        .m-brandlist .item .item-other span{line-height: 30px;padding:0 5px;color:#666;}
        .m-brandlist .item .item-more{color:#1d7ad9;}
    </style>
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
    <div class="nav">
        <div class="ts-container clearfix">
            <div class="nav-left fl">
                <h2 class="btn">全部产品分类</h2>
                <div class="float-box <%--<c:if test="${empty param.m || param.m!='1'}">open</c:if>--%>" style="display:none;"><%--这里需要判断是否是首页,如果是首页,去掉open样式名--%>
                    <%--<tags:index_staticHtml webDefTemplate="${fns:getDocument(new_index_left_category) }"></tags:index_staticHtml>--%>
                    <div class="type-box">
                        <div class="type-item">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    女装
                                </h3>
                                <a title="" class="type-item-more" href="/common/dealer/productList?dealMain=101000000&amp;q=" target="_blank">更多</a>
                            </div>
                            <div class="type-item-content">
                                <a title="春夏装" href="/common/dealer/productList?dealMain=101000000&amp;dealNo=101017000&amp;q==" target="_blank">春夏装</a>
                                <a title="连衣裙" href="/common/dealer/productList?dealMain=101000000&amp;dealNo=101008000&amp;q=" target="_blank">连衣裙</a>
                                <a title="T恤" href="/common/dealer/productList?dealMain=101000000&amp;dealNo=101004000&amp;q=" target="_blank">T恤</a>
                                <a title="小吊带" href="/common/dealer/productList?dealMain=101000000&amp;dealNo=101022000&amp;q=" target="_blank">小吊带</a>
                                <a title="春季女装" href="/common/dealer/productList?dealMain=101000000&amp;dealNo=101043000&amp;q=" target="_blank">春季女装</a>
                                <a title="裤子" href="/common/dealer/productList?dealMain=101000000&amp;dealNo=101018000&amp;q=" target="_blank">裤子</a>
                                <a title="半身裙" href="/common/dealer/productList?dealMain=101000000&amp;dealNo=101019000&amp;q=" target="_blank">半身裙</a>
                                <a title="衬衫" href="/common/dealer/productList?dealMain=101000000&amp;dealNo=101005000&amp;q=" target="_blank">衬衫</a>
                            </div>
                            <div class="type-item-sub">
                                <div class="type-name">女装</div>
                                <div class="type-cate">
                                    <dl class="clearfix">
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
                                    </dl>
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
                            <div class="type-item-content">
                                <a title="T恤" href="/common/dealer/productList?dealMain=100000000&amp;dealNo=100016000&amp;q=" target="_blank">T恤</a>
                                <a title="夹克" href="/common/dealer/productList?dealMain=100000000&amp;dealNo=100002000&amp;q=" target="_blank">夹克</a>
                                <a title="线衣" href="/common/dealer/productList?dealMain=100000000&amp;dealNo=100008000&amp;q=" target="_blank">线衣</a>
                                <a title="风衣" href="/common/dealer/productList?dealMain=100000000&amp;dealNo=100007000&amp;q=" target="_blank">风衣</a>
                                <a title="POLO衫" href="/common/dealer/productList?dealMain=100000000&amp;dealNo=100013000&amp;q=" target="_blank">POLO衫</a>
                                <a title="长袖衬衫" href="/common/dealer/productList?dealMain=100000000&amp;dealNo=100004000&amp;q=&amp;minPrice=&amp;maxPrice=&amp;descType=&amp;attrType=&amp;productGroom=false&amp;currentPage=3&amp;csrfToken=D5B43356DCBF4D8D988869786C173A39" target="_blank">长袖衬衫</a>
                                <a title="西服" href="/common/dealer/productList?dealMain=100000000&amp;dealNo=100009000&amp;q=" target="_blank">西服</a>
                            </div>
                            <div class="type-item-sub">
                                <div class="type-name">男装</div>
                                <div class="type-cate">
                                    <dl class="clearfix">
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
                                    </dl>
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
                            <div class="type-item-content">
                                <a title="春夏袜" href="/common/dealer/productList?dealMain=103000000&amp;dealNo=103023000&amp;q=" target="_blank">春夏袜</a>
                                <a title="" href="/common/dealer/productList?dealMain=103000000&amp;dealNo=103001000&amp;q=" target="_blank">文胸</a>
                                <a title="性感内衣" href="/common/dealer/productList?dealMain=103000000&amp;dealNo=103005000&amp;q=" target="_blank">性感内衣</a>
                                <a title="" href="/common/dealer/productList?q=睡衣" target="_blank">睡衣</a>
                                <a title="" href="/common/dealer/productList?dealMain=103000000&amp;dealNo=103002000&amp;q=" target="_blank">女内裤</a>
                                <a title="" href="/common/dealer/productList?dealMain=103000000&amp;dealNo=103007000&amp;q=" target="_blank">男内裤</a>
                                <a title="" href="/common/dealer/productList?q=美体" target="_blank">美体</a>
                            </div>
                            <div class="type-item-sub">
                                <div class="type-name">内衣/家居服</div>
                                <div class="type-cate">
                                    <dl class="clearfix">
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
                                    </dl>
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
                        <div class="type-item" style="border-bottom: 0;">
                            <div class="type-item-head">
                                <h3 class="type-item-title">
                                    日用家居
                                </h3>
                                <a title="" class="type-item-more" href="" target="_blank">更多</a>
                            </div>
                            <div class="type-item-content">
                                <a title="" href="" target="_blank">裤子</a>
                                <a title="" href="" target="_blank">半身裙</a>
                                <a title="" href="" target="_blank">衬衫</a>
                                <a title="" href="" target="_blank">卫衣</a>
                            </div>
                            <div class="type-item-sub">
                                <div class="type-name">日用家居</div>
                                <div class="type-cate">
                                    <dl class="clearfix">
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
                                    </dl>
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
                    </div>
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
                <li><a href="${ctx}/iambrand/index">品牌订货会</a></li>
                <li><a href="${ctx}/iambrand/index">工厂店加盟</a></li>
            </ul>
            <div class="nav-move-item"></div>
        </div>
    </div>
    <div class="main ts-container">
        <div class="m-bread clearfix">
            <a href="#" class="m-b-1">首页</a>
            <span class="m-b-2">&gt;</span>
            <a href="#" class="m-b-1">品牌女装</a>
            <span class="m-b-2">&gt;</span>
            <a href="#" class="m-b-3">连衣裙<i class="icon-del"></i></a>
            <a href="#" class="m-b-3">连衣裙<i class="icon-del"></i></a>
            <a href="#" class="m-b-3">连衣裙<i class="icon-del"></i></a>
            <a href="javascript:;" class="m-b-4 js-slide-filter">收起筛选<i class="icon-close"></i></a>
        </div>
        <div class="m-brand clearfix">
            <div class="tit fl">选择品牌：</div>
            <div class="list fl" id="brand_list">
                <div style="display: none;" id="brand_search">
                    <form action="">
                        <input type="text" placeholder="搜索品牌名称" />
                    </form>
                </div>
                <div class="clearfix">
                    <ul class="inline-float">
                        <li>
                            <a href="#" class="js-img-center">
                                <img src="images/temp/duocai.jpg" alt="" title="朵彩">
                            </a>
                        </li>
                        <li>
                            <a href="#" class="js-img-center">
                                <img src="images/temp/duocai.jpg" alt="" title="朵彩">
                            </a>
                        </li>
                        <li>
                            <a href="#" class="js-img-center">
                                <img src="images/temp/duocai.jpg" alt="" title="朵彩">
                            </a>
                        </li>
                        <li>
                            <a href="#" class="js-img-center">
                                <img src="images/temp/duocai.jpg" alt="" title="朵彩">
                            </a>
                        </li>
                        <li>
                            <a href="#" class="js-img-center">
                                <img src="images/temp/duocai.jpg" alt="" title="朵彩">
                            </a>
                        </li>
                        <li>
                            <a href="#" class="js-img-center">
                                <img src="images/temp/duocai.jpg" alt="" title="朵彩">
                            </a>
                        </li>
                        <li>
                            <a href="#" class="js-img-center">
                                <img src="images/temp/duocai.jpg" alt="" title="朵彩">
                            </a>
                        </li>
                        <li>
                            <a href="#" class="js-img-center">
                                <img src="images/temp/duocai.jpg" alt="" title="朵彩">
                            </a>
                        </li>
                        <li>
                            <a href="#" class="js-img-center">
                                <img src="images/temp/duocai.jpg" alt="" title="朵彩">
                            </a>
                        </li>
                        <li>
                            <a href="#" class="js-img-center">
                                <img src="images/temp/duocai.jpg" alt="" title="朵彩">
                            </a>
                        </li>
                    </ul>
                </div>
                <div style="display: none;" id="brand_btns">
                    <a class="brand_btns_confirm disabled" href="javascript:;">确定</a>
                    <a class="brand_btns_cancel" href="javascript:;">取消</a>
                </div>
            </div>
            <div class="more fr ta-r">
                <a href="#" id="select_duo">多选</a>
                <a href="javascript:;" id="select_more">更多<i class="icon-close-down"></i></a>
            </div>
        </div>
        <div class="m-cates">
            <div class="m-mod mod-height clearfix">
                <div class="tit fl">女装：</div>
                <div class="list fl">
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                </div>
                <div class="more fr ta-r">
                    <a class="js-cates-more" href="javascript:;">更多<i class="icon-close-down"></i></a>
                </div>
            </div>
            <div class="m-mod mod-height clearfix">
                <div class="tit fl">选购热点：</div>
                <div class="list fl">
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                    <a href="#">雪纺连衣裙</a>
                </div>
                <div class="more fr ta-r">
                    <a class="js-cates-more" href="javascript:;">更多<i class="icon-close-down"></i></a>
                </div>
            </div>
            <div class="m-mod clearfix">
                <div class="tit fl">筛选条件：</div>
                <div class="list-filter fl">
                    <ul class="inline-float">
                        <li>
                            <a href="javascript:;">风格<i class="icon-close-down"></i></a>
                            <div class="drop">
                                <a href="#">1X</a>
                                <a href="#">S</a>
                                <a href="#">M</a>
                                <a href="#">XL</a>
                                <a href="#">均码</a>
                            </div>
                        </li>
                        <li>
                            <a href="javascript:;">腰型<i class="icon-close-down"></i></a>
                            <div class="drop">
                                <a href="#">2X</a>
                                <a href="#">S</a>
                                <a href="#">M</a>
                                <a href="#">XL</a>
                                <a href="#">均码</a>
                            </div>
                        </li>
                        <li>
                            <a href="javascript:;">裙型<i class="icon-close-down"></i></a>
                            <div class="drop">
                                <a href="#">3X</a>
                                <a href="#">S</a>
                                <a href="#">M</a>
                                <a href="#">XL</a>
                                <a href="#">均码</a>
                            </div>
                        </li>
                        <li>
                            <a href="javascript:;">尺码<i class="icon-close-down"></i></a>
                            <div class="drop">
                                <a href="#">4X</a>
                                <a href="#">S</a>
                                <a href="#">M</a>
                                <a href="#">XL</a>
                                <a href="#">均码</a>
                            </div>
                        </li>
                        <li>
                            <a href="javascript:;">相关分类<i class="icon-close-down"></i></a>
                            <div class="drop">
                                <a href="#">5X</a>
                                <a href="#">S</a>
                                <a href="#">M</a>
                                <a href="#">XL</a>
                                <a href="#">均码</a>
                            </div>
                        </li>
                    </ul>
                </div>
                <%--<div class="more fr ta-r">
                    <a href="javascript:;">更多<i class="icon-close-down"></i></a>
                </div>--%>
            </div>
        </div>
        <div class="m-guess clearfix">
            <div class="tit fl">猜您想找：</div>
            <div class="list fl">
                <a href="#">雪纺连衣裙</a>
                <a href="#">雪纺连衣裙</a>
                <a href="#">雪纺连衣裙</a>
                <a href="#">雪纺连衣裙</a>
                <a href="#">雪纺连衣裙</a>
                <a href="#">雪纺连衣裙</a>
            </div>
        </div>
        <div class="m-filter clearfix">
            <ul class="inline-float fl">
                <li class="m-f-1 current"><a href="javascript:;">综合排序</a></li>
                <li class="m-f-1"><a href="javascript:;">人气</a></li>
                <li class="m-f-1"><a href="javascript:;">销量</a></li>
                <li class="m-f-1"><a href="javascript:;">时间</a></li>
                <li class="m-f-1"><a href="javascript:;">价格</a></li>
            </ul>
            <div class="m-f-4 fr">
                <a href="javascript:;" class="prev"><i class="icon-d-prev"></i></a>
                <span><em>1</em>/100</span>
                <a href="javascript:;" class="next"><i class="icon-f-next"></i></a>
            </div>
        </div>
        <div class="m-recom clearfix">
            <div class="fl">
                <label><input type="checkbox"/>推荐产品</label>
                <label><input type="checkbox"/><span>拿样</span><i class="icon-nayang"></i></label>
                <label><input type="checkbox"/><span>授信</span><i class="icon-shouxin"></i></label>
                <label><input type="checkbox"/><span>预定</span><i class="icon-yuding"></i></label>
            </div>
            <div class="fr">
                <a href=""><i class="icon-g-brand"></i>找品牌商</a>
                <a href=""><i class="icon-r-goods"></i>商品</a>
            </div>
        </div>
        <div class="m-brandlist">
            <ul class="itemul inline-float">
                <li class="item">
                    <div class="clearfix">
                        <div class="i1">
                            <div class="item-logo">
                                <a href="#" class="js-img-center"><img src="" alt="" width="140" height="70"/></a>
                            </div>
                            <div class="item-name"><a href="">艾谛菲</a></div>
                            <div class="item-follow">关注</div>
                        </div>
                        <div class="i2">
                            <img src="" alt="" width="400" height="200"/>
                        </div>
                        <div class="i3">
                            <h3><a href="#">东莞市雄成立亿服饰有限公司</a></h3>
                            <div class="item-address">广东省 东莞市</div>
                            <div class="item-com"><span>主营产品：</span>内衣/家居服 | 女装 | 男装</div>
                            <div class="item-com"><span>商品数量：</span><em>200</em> 个</div>
                            <div class="item-com"><span>进货店铺：</span><em>200</em> 家</div>
                            <div class="item-btns"><a href="#" class="btn1">申请加盟</a><a href="#" class="btn2">进货</a></div>
                        </div>
                        <div class="i4">
                            <div>
                                <ul class="inline-float">
                                    <li class="item-other">
                                        <div><img src="" alt="" width="130" height="130"/></div>
                                        <span>￥79.00</span>
                                    </li>
                                    <li class="item-other ml10">
                                        <div><img src="" alt="" width="130" height="130"/></div>
                                        <span>￥79.00</span>
                                    </li>
                                </ul>
                            </div>
                            <div class="ta-r mt15">
                                <a class="item-more" href="#">了解详情&gt;</a>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <div class="m-page">
            <div class="pagination">
                <a class="prev" href="javascript:;">&lt;</a>
                <a class="page current" href="javascript:;">1</a>
                <a class="page" href="javascript:;">2</a>
                <a class="page" href="javascript:;">3</a>
                <a class="page" href="javascript:;">4</a>
                <a class="ellipsis" href="">...</a>
                <a class="page" href="javascript:;">5</a>
                <a class="page" href="javascript:;">6</a>
                <a class="next" href="javascript:;">&gt;</a>
            </div>
        </div>
        <div class="m-related">
            <div class="tit clearfix">
                <h3 class="fl">相关品牌推荐</h3>
                <a href="#" class="fr">我也要出现在这里</a>
            </div>
            <div class="list">
                <ul class="inline-float">
                    <li>
                        <h4><a href="">丝中娇</a></h4>
                        <div>
                            <a href="#" class="js-img-center"><img src="" alt="" width="201" height="201"/></a>
                        </div>
                    </li>
                </ul>
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
<script src="${res}/scripts/common/base-init.js"></script>
<script>
    $(function(){
        var select_duo_flag = false;  //多选开关
        var select_more_flag = true; //更多开关
        var tpl_up = '收起<i class="icon-close"></i>';
        var tpl_down = '更多<i class="icon-close-down"></i>';
        var tpl_show = '显示筛选<i class="icon-close-down"></i>';
        var tpl_hide = '收起筛选<i class="icon-close"></i>';

        //收起筛选
        $(".js-slide-filter").click(function(){
            if($(this).find(".icon-close").length > 0){
                $(".m-brand").stop().fadeOut("fast");
                $(".m-cates").stop().fadeOut("fast");
                $(".js-slide-filter").html(tpl_show);
            }else{
                $(".m-brand").stop().fadeIn("fast");
                $(".m-cates").stop().fadeIn("fast");
                $(".js-slide-filter").html(tpl_hide);
            }
        });

        //多选
        $("#select_duo").click(function(){
            $(this).hide();
            $("#brand_btns").show();
            select_duo_flag = true;
        });

        //更多
        $("#select_more").click(function(){
            if(select_more_flag){
                $("#brand_search").show();
                $(this).html(tpl_up);
                select_more_flag = false;
            }else{
                $("#brand_search").hide();
                $(this).html(tpl_down);
                select_more_flag = true;
            }
        });

        //取消
        $(".brand_btns_cancel").click(function(){
            $("#select_duo").show();
            $("#brand_search").hide();
            $("#brand_btns").hide();
            $("#select_more").html(tpl_down);
            $("#brand_list li .hover").remove();
            $("#brand_list li .selected").remove();
            $(".brand_btns_confirm").addClass("disabled");
            select_duo_flag = false;
            select_more_flag = true;
        });
        //确定
        $(".brand_btns_confirm").click(function(){
            if(!$(this).hasClass("disabled")){
                alert("弹出品牌列表");
            }
        });

        $("#brand_list li").hover(function(){
            var $hover = $(this).find(".hover");
            var len = $hover.length;
            if(len <= 0){
                var title = trimLongString($(this).find('img').attr('title'), 5) || "null"; //title 必须加
                var $tpl = $('<span class="hover">' + title + '</span>');
                $(this).append($tpl);
            }else{
                $hover.show();
            }
        },function(){
            //$(this).find(".hover").stop().fadeOut();
            $(this).find(".hover").hide();
        });

        $("#brand_list li").click(function(){
            if(select_duo_flag){
                var $hover = $(this).find(".hover");
                var $selected = $(this).find(".selected");
                var $tpl = $('<span class="selected"></span>');
                if($selected.length <= 0){
                    $(this).append($tpl);
                }else{
                    $selected.remove();
                }
                if($("#brand_list .selected").length > 0){
                    $(".brand_btns_confirm").removeClass("disabled");
                }else{
                    $(".brand_btns_confirm").addClass("disabled");
                }
            }else{
                var href = $(this).find(".js-img-center").attr("href");
                window.location.href = href;
            }
        });

        //缺一个选中品牌的方法


        //
        $(".js-cates-more").click(function(){
            var $parents = $(this).parents(".m-mod");
            if($parents.hasClass("mod-height")){
                $parents.removeClass("mod-height");
                $(this).html(tpl_up);
            }else{
                $parents.addClass("mod-height");
                $(this).html(tpl_down);
            }
        });
    });
</script>
</body>
</html>