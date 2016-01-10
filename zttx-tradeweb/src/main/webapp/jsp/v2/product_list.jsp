<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include  file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>产品展示 朵彩-上海朵彩棉服饰有限公司 8637品牌超级代理</title>
    <meta name="keywords" content="朵彩,朵彩品牌代理,朵彩品牌招商"/>
    <meta name="description" content=""/>
    <link href="http://images.8637.com/styles/fronts/market/brandviewbase.css" rel="stylesheet" type="text/css"/>
    <link href="http://images.8637.com/styles/fronts/market/decoration.css" rel="stylesheet" type="text/css"/>
    <link href="http://images.8637.com/styles/fronts/market/brandviewbase_edit.css" rel="stylesheet" type="text/css" />
    <link href="http://images.8637.com/styles/fronts/market/decoration_edit.css" rel="stylesheet" type="text/css" />

    <style>
        /*中间整体背景链接等*/
        .do-body{
            /*全局字体颜色*/
            background-color:#f1f1e9;background-image: url('http://images.8637.com/upload/brand/img/2014/20140530/3C5DAA0F42C84F1A82A0B3A1558AE3F7.jpg');  background-repeat: no-repeat ;/*全局背景颜色*/
            background-position: top center;
        }
        .do-body a{

        }/*全局链接字体颜色*/
        .do-body a:hover{

        }

        /*店名样式*/
        .logo{
            display: none;

        }

        .logo h2{
            font-size:12px; display: none;
        }
        /*招牌顶部背景图*/
        .header-nav{
            background:#BA2926 url('http://images.8637.com/upload/brand/img/2014/20140701/9EA54E45311F4F9BA3F180EEF59C2D99.jpg')
        }

        /* 通栏内背景图*/
        .header-navcen{
            background:url('http://images.8637.com/upload/brand/img/2014/20140701/57F512B211094296B41D61918EA1810B.jpg')
        }

        /*导航背景样式*/
        .nav-cen{
            background:#e50278;
        }

        /*导航文字颜色*/
        a.menu-link,.menu-link:hover {
            color:#FFFFFF
        }
        a.menu-link:hover{
            color: #800444
        }


        /*选中的导航样式*/
        .menu-link.selected{
            background:#b20b62
        }

        /*导航移动变化*/
        .menu:hover {
            background: #e50278
        }

    </style>

    <link rel="stylesheet" href="http://images.8637.com/styles/fronts/market/prodisshow.css"/>
    <%--新增的--%>
    <style>
        /*增加一种显示模式*/
        .change-layout-list{}
        .change-layout-list ul{
            width: 1156px;
            margin-right: 0px;
        }
        .change-layout-list li{
            width: 100%;
            height: auto;
        }
        .change-layout-list .pic,.change-layout-list .detail{
            float:left;
        }
        .change-layout-list .detail{}
    </style>
</head>
<body>
    <div class="ts-container">
        <div class="m-product">
            <div class="m-cate clearfix">
                <div class="cate-tit fl">
                    全部分类：
                </div>
                <div class="cate-list fr">
                    <ul class="inline-float">
                        <li><a href="">九分女裤</a><em>（55）</em></li>
                        <li><a href="">九分女裤</a><em>（55）</em></li>
                        <li><a href="">九分女裤</a><em>（55）</em></li>
                        <li><a href="">九分女裤</a><em>（55）</em></li>
                        <li><a href="">九分女裤</a><em>（55）</em></li>
                        <li><a href="">九分女裤</a><em>（55）</em></li>
                        <li><a href="">九分女裤</a><em>（55）</em></li>
                        <li><a href="">九分女裤</a><em>（55）</em></li>
                        <li><a href="">九分女裤</a><em>（55）</em></li>
                        <li><a href="">九分女裤</a><em>（55）</em></li>
                        <li><a href="">九分女裤</a><em>（55）</em></li>
                    </ul>
                </div>
            </div>
            <div class="m-search clearfix">
                <div class="form fl">
                    <input type="text" id="searchTxts" placeholder="输入商品名或货号" />
                    <button type="button" id="searchbtns"><i class="icon-search"></i></button>
                </div>
                <div class="count fl">共搜索到 <strong>34</strong> 个符合条件的商品</div>
            </div>
            <div class="m-filter clearfix">
                <ul class="inline-float fl">
                    <li class="m-f-1 current"><a href="javascript:;">综合排序</a></li>
                    <li class="m-f-1"><a href="javascript:;">人气</a></li>
                    <li class="m-f-1"><a href="javascript:;">销量</a></li>
                    <li class="m-f-1"><a href="javascript:;">时间</a></li>
                    <li class="m-f-1"><a href="javascript:;">价格</a></li>
                    <li class="m-f-2"><input type="text" class="fl"/><span class="fl">-</span><input type="text" class="fl"/><button type="button" class="fl">确定</button></li>
                    <li class="m-f-3"><label><input type="checkbox"/><span>支持拿样</span></label><i class="icon-nayang"></i></li>
                    <li class="m-f-3"><label><input type="checkbox"/><span>我的授权商品</span></label><i class="icon-shouxin"></i></li>
                    <li class="m-f-3"><label><input type="checkbox"/><span>我的授信商品</span></label><i class="icon-yuding"></i></li>
                </ul>
                <div class="m-f-4 fr">
                    <a href="javascript:;" class="prev"><i class="icon-d-prev"></i></a>
                    <span><em>1</em>/100</span>
                    <a href="javascript:;" class="next"><i class="icon-f-next"></i></a>
                </div>
            </div>
            <%--老板<div class="m-list">
                <ul class="inline-float">
                    <li>
                        <div class="pic">
                            <a href=""><img src="" alt="" width="270" height="270"/></a>
                        </div>
                        <div class="operate">
                            <a class="operate-collect" href="javascript:;"><i class="icon-collect"></i>收藏</a>
                            <a class="operate-jhds" href="javascript:;"><i class="icon-jhds"></i>加入进货单</a>
                        </div>
                        <div class="detail">
                            <div class="price">直供价：<em>￥</em><strong>150.00</strong><i>750.00</i></div>
                            <div class="title"><a href="">女式羽绒立领绣花上衣 YKC118</a></div>
                            <div class="number">货号：YKC118</div>
                            <div class="clearfix">
                                <span class="fl"><i class="icon-nayang" title="支持拿样"></i><i class="icon-shouxin ml5" title="我的授权商品"></i><i class="icon-yuding ml5" title="我的授信商品"></i></span>
                                <a class="fr" href="javascript:;"><i class="icon-cat"></i></a>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>--%>
            <div class="m-list change-layout-list">
                <ul class="inline-float">
                    <li>
                        <div class="pic">
                            <a href="/market/product/634259102104555520?code=default" title="hehehei冬季中长款宽松翻领普通长袖常规袖" class="js-img-center" target="_blank">
                                <img src="http://images.8637.com/upload/brand/img/2015/20151030/7FCB94F0E29245DAA9CC2F144F01E44D.JPG_220x220.JPG" alt="hehehei冬季中长款宽松翻领普通长袖常规袖">
                                <span></span><span></span></a>
                            <div class="operate">
                                <a class="operate-collect" href="javascript:addFavor('634259102104555520');"><i class="icon-collect"></i>收藏</a>
                                <a class="operate-jhds" href="javascript:joinShop('634259102104555520','0');"><i class="icon-jhds"></i>加入进货单</a>
                            </div>
                        </div>
                        <div class="detail">
                            <div class="price">

                                直供价：<em>￥</em>
                                <strong>318.25</strong>
                                <i>1299.00</i>
                            </div>
                            <div class="title">
                                <a href="/market/product/634259102104555520?code=default">hehehei冬季中长款宽松翻领普通长袖常规袖</a>
                            </div>
                            <div class="number">货号：15D081</div>
                            <div class="address clearfix">
                                <a class="fl" href="#" title="呵呵嘿">呵呵嘿</a>
                                <a class="fr" href="javascript:;" title="浙江省">浙江省</a>
                            </div>
                            <div class="clearfix">
                                <span class="fl"><i class="icon-nayang" title="支持拿样"></i><i class="icon-shouxin ml5" title="我的授权商品"></i><i class="icon-yuding ml5" title="我的授信商品"></i></span>
                                <a class="fr" href="javascript:;"><i class="icon-cat"></i></a>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="pic">
                            <a href="/market/product/634256813075730432?code=default" title="hehehei冬季中长款宽松翻领普通长袖常规袖" class="js-img-center" target="_blank">
                                <img src="http://images.8637.com/upload/brand/img/2015/20151030/BC65589623E14FF18DDFE7B666BF66CC.JPG_220x220.JPG" alt="hehehei冬季中长款宽松翻领普通长袖常规袖">
                                <span></span><span></span></a>
                            <div class="operate">
                                <a class="operate-collect" href="javascript:addFavor('634256813075730432');"><i class="icon-collect"></i>收藏</a>
                                <a class="operate-jhds" href="javascript:joinShop('634256813075730432','0');"><i class="icon-jhds"></i>加入进货单</a>
                            </div>
                        </div>
                        <div class="detail">
                            <div class="price">

                                直供价：<em>￥</em>
                                <strong>312.63</strong>
                                <i>1839.00</i>
                            </div>
                            <div class="title">
                                <a href="/market/product/634256813075730432?code=default">hehehei冬季中长款宽松翻领普通长袖常规袖</a>
                            </div>
                            <div class="number">货号：15D022</div>
                            <div class="address clearfix">
                                <a class="fl" href="#" title="呵呵嘿">呵呵嘿</a>
                                <a class="fr" href="javascript:;" title="浙江省">浙江省</a>
                            </div>
                            <div class="clearfix">
                                <span class="fl"><i class="icon-nayang" title="支持拿样"></i><i class="icon-shouxin ml5" title="我的授权商品"></i><i class="icon-yuding ml5" title="我的授信商品"></i></span>
                                <a class="fr" href="javascript:;"><i class="icon-cat"></i></a>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="m-page">
                <div class="pagination">
                    <a class="prev" href="javascript:;">上一页</a>
                    <a class="page current" href="javascript:;">1</a>
                    <a class="page" href="javascript:;">2</a>
                    <a class="page" href="javascript:;">3</a>
                    <a class="page" href="javascript:;">4</a>
                    <a class="ellipsis" href="">...</a>
                    <a class="page" href="javascript:;">5</a>
                    <a class="page" href="javascript:;">6</a>
                    <a class="next" href="javascript:;">下一页</a>
                </div>
            </div>
        </div>
    </div>
    <script src="http://images.8637.com/scripts/jquery.min.js"></script>
    <script src="http://images.8637.com/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="http://images.8637.com/scripts/seajs_config.js"></script>
    <script src="http://images.8637.com/scripts/src/fronts/market/last.js" type="text/javascript"></script>
    <script src="http://images.8637.com/scripts/common.js"></script>

    <script>
        $(".m-list .pic,.m-list .operate").hover(function(){
            $(this).parent().addClass("hover");
        }, function(){
            $(this).parent().removeClass("hover");
        });
    </script>
</body>
</html>