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
    <link rel="stylesheet" href="http://images.8637.com/styles/market/brandviewbase.css"/>
    <link href="http://images.8637.com/styles/market/brandviewbase_edit.css" rel="stylesheet" type="text/css" />
    <link href="http://images.8637.com/styles/market/decoration_edit.css" rel="stylesheet" type="text/css" />
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

    <link rel="stylesheet" href="http://images.8637.com/styles/market/prodisshow.css"/>
    <%--新增的--%>
    <style>
        /*改版后布局变为1190px*/
        .ts-container{width: 1190px;}
        a{color:#666;-webkit-transition: 0.3s;  -moz-transition: 0.3s;  -o-transition: 0.3s;transition: 0.3s;}
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

        /*图标*/
        .icon-shouxin,
        .icon-collect,
        .icon-share,
        .icon-jhds,
        .icon-share,
        .icon-nayang,
        .icon-ident,
        .icon-grade,
        .icon-joined,
        .icon-shou,
        .icon-view,
        .icon-follow,
        .icon-warning{
            display: inline-block;
            *display: inline;
            *zoom: 1;
            margin-right: 5px;;
            background: url("images/product_list/com_icon.png") no-repeat -999em;
            vertical-align: -2px;
            *vertical-align: middle;
        }
        .icon-shouxin{
            width: 16px;
            height: 16px;
            background-position: -109px 0;
        }
        .icon-collect{
            width: 12px;
            height: 11px;
            background-position: -151px 0;
        }
        .icon-share{
            width: 11px;
            height: 11px;
            background-position: -168px 0;
        }
        .icon-jhds{
            width: 16px;
            height: 14px;
            background-position: -130px 0;
        }
        .icon-nayang{
            width: 16px;
            height: 16px;
            background-position: -184px 0;
        }
        .icon-ident,
        .icon-grade,
        .icon-joined{
            vertical-align: -5px;
            *vertical-align: middle;
        }
        .icon-ident{
            width: 19px;
            height: 18px;
            background-position: -39px 0;
        }
        .icon-grade{
            width: 17px;
            height: 18px;
            background-position: -63px 0;
        }
        .icon-joined{
            width: 19px;
            height: 18px;
            background-position: -85px 0;
        }
        .icon-shou{
            width: 31px;
            height: 39px;
            background-position: -205px 0;
        }
        .icon-view{
            width: 16px;
            height: 12px;
            background-position: 0 0;
        }
        .icon-follow{
            width: 13px;
            height: 12px;
            background-position: -21px 0;
        }
        .icon-warning{
            width: 12px;
            height: 12px;
            background-position: -241px 0;
        }
        /*重置样式*/
        .listbody .sidebar-l .chanpin{padding:0;font-family: "Microsoft yahei";}
        .listbody .sidebar-l .chanpin .magnibox {width: 312px;margin:10px;}
        .listbody .sidebar-l .chanpin .magnimg .jqzoom{width: 310px;height: 310px;}
        .listbody .sidebar-l .chanpin .magnimg .jqzoom img {max-width: 310px;max-height: 310px;}
        .listbody .sidebar-l .chanpin .magniul li{width: 40px;height: 40px;}
        .listbody .sidebar-l .chanpin .magniul li img {max-width: 40px;max-height: 40px;}
        .magni-all .magni-scroll{width: 280px;}
        .magni-all .pagestep-btn {height: 42px;line-height: 42px;}
        .listbody .sidebar-l .chanpin .checkbox {width:555px;min-height:458px;_height:458px;margin-left: 0;border-left:1px solid #e5e5e5;}
        .listbody .sidebar-l .chanpin .checkbox h1.title{padding: 15px 20px 0 20px;}
        .listbody .sidebar-r{width: 300px;border: 1px solid #ddd;}

        /*后加*/
        .m-collect{text-align: center;}
        .m-collect .icon-collect{vertical-align: -1px;*vertical-align: middle;}
        .m-collect span{color: #999;}
        .m-share{width: 158px;margin: 0 auto;color:#999;}
        .m-share .icon-share{vertical-align: -1px;*vertical-align: middle;}
        .m-detail{padding: 10px;}
        .m-detail dt,.m-detail dd{float: left;display: inline;line-height: 30px;}
        .m-detail dt{width: 75px;padding-left:10px;color:#666;}
        .m-detail dd{width: 442px;}
        .m-special{padding: 5px 0;background-color: #f8f8f8;}
        .m-special dd em{font-size: 16px;}
        .m-special dd .msp1{color: #e00;}
        .m-special dd .msp2{color: #666;}
        .m-special .not-see{height: 80px;line-height: 86px;text-align: center;font-size: 14px;}
        .m-special .not-see a{margin:0 5px;color: #e00;}
        .m-special .not-see a:hover{color: #a60101;}
        .m-colorlist{}
        .m-colorlist li{height: 34px;line-height: 34px;margin-right:10px;border:1px solid #e5e5e5;position: relative;cursor: pointer;}
        .m-colorlist li span{position: absolute;right: 38%;bottom: -11px;width: 0;height: 0;border-style: solid;border-color: #e00 #fff #fff #fff;_border-color: #e00 tomato tomato tomato;_filter: chroma(color=tomato);border-width: 5px 4px;overflow: hidden;display:none;}
        .m-colorlist li a{padding: 0 5px;}
        .m-colorlist li img{display:block;margin: 1px 1px 2px 1px;}
        .m-colorlist li.selected{border: 2px solid #e00;}
        .m-colorlist li.selected span{display:block;}
        .table-head{width:100%;border-top:1px solid #e5e5e5;background-color: #f8f8f8;}
        .table-head th{padding: 8px 0;color: #666;font-weight: normal;text-align: center;}
        .table-list{width: 100%;}
        .table-list td{padding: 5px 0;border-bottom: 1px dashed #e5e5e5;text-align: center;}
        .m-balance{height:45px;line-height:45px;padding-left:95px;border:1px solid #e5e5e5;border-left:0;border-right:0;background-color: #f8f8f8;color: #e00;}
        .m-balance span{padding-right:10px;border-right: 1px solid #e5e5e5;}
        .m-balance label{margin-right: 15px;margin-left: 5px;font-size: 14px;}
        .m-balance em{font-size: 16px;}
        .m-balance input{margin-right:5px;vertical-align: -1px;*vertical-align: middle;}
        .m-explain{padding:20px;border-top:1px solid #f2f2f2;}
        .m-explain h3{margin-bottom: 5px;font-size: 14px;}
        .m-explain p{color: #666;}
        .m-explain em{color: #f80;}

        .num-minus,
        .num-amount,
        .num-plus{float: left;display: inline;border:1px solid #e5e5e5;color:#666;}
        .num-amount{width: 50px;padding:0 5px;height:24px;line-height:24px;border-left:0;border-right: 0;/*border-left-color:#fff;border-right-color: #fff;*/}
        .num-minus,.num-plus{height: 26px;line-height: 26px;padding: 0 10px;background-color: #fff;font-family: "simsun";}
        .num-minus{border-right-color: #efefef;}
        .num-plus{border-left-color: #efefef;}
        .add-settle{margin-top: 0;padding: 15px 20px;}
        .add-settle .btn1,
        .add-settle .btn2,
        .chanpin-fd .btn1{display: inline-block;*display:inline;*zoom:1;width:160px;height: 50px;line-height: 50px;margin-right:10px;border-radius:3px;text-align:center;font-size: 18px;color: #fff;-webkit-transition: 0.3s;  -moz-transition: 0.3s;  -o-transition: 0.3s;transition: 0.3s;}
        .add-settle .btn1{background-color: #e00;}
        .add-settle .btn2{background-color: #d7a373;}
        .add-settle .btn1 .icon-jhds{margin-right: 10px;vertical-align: -1px;*vertival-align:middle;}
        .add-settle .btn1:hover{background-color: #a60101;}
        .add-settle .btn2:hover{background-color: #ad845e;}
        .no-product-tip{padding: 0 20px 20px;}

        .chanpin-fd{padding: 10px 20px;line-height:30px;border:1px solid #ddd;border-top:0;background: #fff;font-family: 'Microsoft Yahei';color: #666;}
        .chanpin-fd .btn1{width:110px;height:40px;line-height:40px;border:1px solid #e5e5e5;background-color: #f8f8f8;font-size:14px;color: #666;}
        .chanpin-fd .btn1:hover{border-color:#f80;background-color: #f2f2f2;color:#f80;}

        /*右侧*/
        .sidebar-r{font-family: 'Microsoft Yahei';}
        .m-brand,
        .m-info,
        .m-contact,
        .m-down{padding: 20px 20px 15px 20px;border-bottom: 1px solid #ddd;}
        .m-brand h3.tit,
        .m-info h3.tit,
        .m-contact h3.tit,
        .m-down h3.tit{font-size:14px;font-weight: bold;}
        .m-brand .mb-fore1{width: 180px;height: 90px;margin: 20px auto 10px auto;border:1px solid #e5e5e5;background-color: #f2f2f2;}
        .m-brand .mb-fore1 img{max-width: 180px;max-height: 90px;_width: 180px;_height:90px;}
        .m-brand .mb-fore2{width: 180px;margin: 0 auto;text-align: center;color:#666;}
        .m-brand .mb-fore3,
        .m-brand .mb-fore4,
        .m-brand .mb-fore5{margin-top: 15px;}
        .m-brand .mb-fore3 a{float: left;display: inline;width: 118px;height:24px;line-height:26px;border: 1px solid #e5e5e5;border-radius:3px;background-color: #f2f2f2;text-align: center;}
        .m-brand .mb-fore3 em,
        .m-info .mi-fore1 em{color:#f80;}
        .m-brand .mb-fore3 a:hover{background-color: #eee;border-color: #999;color: #666;}
        .m-brand .mb-fore4 p{margin-top: 10px;font-size:14px;}
        .m-brand .mb-fore5 span{float:left;display: inline;width:86px;}

        .m-info .mi-fore1 dt{float:left;display: inline;width:33px;height:44px;margin-right: 10px;}
        .m-info .mi-fore1 .tit{font-weight: bold;}
        .m-info .mi-fore1 .des{margin-top: 6px;color:#666;}
        .m-info .mi-fore2{margin-top: 15px;}
        .m-info .mi-fore2 a{float:left;display: inline;width:123px;height: 36px;line-height: 36px;border:1px solid #f80;border-radius:5px;background-color: #fff;text-align:center;font-size:14px;color:#f80;}
        .m-info .mi-fore2 a:hover{background-color: #f80;color: #fff;}
        .m-info .mi-fore3{line-height:24px;padding-bottom:5px;margin-top:10px;border-bottom:1px dashed #e4e4e4;color:#666;}
        .m-info .mi-fore3 strong{padding:0 5px;}
        .m-info .mi-fore4{margin-top: 10px;}
        .m-info .mi-fore4 dt,.m-info .mi-fore4 dd{float: left;display: inline;line-height: 30px;}
        .m-info .mi-fore4 dt{width: 60px;color: #666;}
        .m-info .mi-fore4 dd{width: 200px;}
        .m-info .mi-fore5{}
        .m-info .mi-fore5 a{display:block;height:40px;line-height:40px;background-color: #ff5654;text-align:center;font-size:16px;color: #fff;}
        .m-info .mi-fore5 a:hover{background-color: #d94847;}

        .m-contact .mc-fore1{}
        .m-contact .mc-fore2{height:50px;margin-top:10px;border:1px solid #e5e5e5;background:url('images/product_list/mc_p.jpg') no-repeat left top;}
        .m-contact .mc-fore2 p{padding-left: 60px;font-size: 14px;}
        .m-contact .mc-fore2 p.p1{font-size: 18px;}
        .m-contact .mc-fore2 p.p2{font-size: 12px;color: #999;}
        .m-contact .mc-fore3 li{width: 130px;line-height: 22px;margin-top: 10px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;word-wrap: normal;}

        .m-down ul{margin-right: -25px;}
        .m-down li{width: 70px;margin-top:10px;margin-right: 25px;text-align: center;}
        .m-down li .js-img-center{display: block; width: 70px;height: 35px;background-color: #f2f2f2;}
        .m-down li .js-img-center img{max-width: 70px;max-height: 35px;_width:70px;_height:35px;}
        .m-down li p{margin-top:5px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;word-wrap: normal;}
    </style>
</head>
<body>
<div class="do-body">
    <div class="listbody clear">
        <div class="sidebar-l">
            <div class="chanpin font">
                <div class="magnibox f-l ad-gallery clearfix" style="position:relative;">
                    <div class="magnimg" style="position:relative;">
                        <a href="javascript:void(0)" class="jqzoom js-img-center">
                            <img src="http://images.8637.com/upload/brand/img/2015/20150727/E740B793B37A40D19834E7F766D045B9.JPG" data-bigimg="" title="" /> </a>
                    </div>
                    <div class="magni-all">
                        <a href="javascript:;" class="pagestep-btn prev">&lt;</a> <a href="javascript:;" class="pagestep-btn next">&gt;</a>
                        <div class="magni-scroll mt10">
                            <ul class="magniul" id="thumblist">
                                <li class="js-img-center"><a class="zoomThumbActive" href='javascript:void(0);'> <img src="http://images.8637.com/upload/brand/img/2015/20150727/E740B793B37A40D19834E7F766D045B9.JPG_60x60.JPG" data-src="http://images.8637.com/upload/brand/img/2015/20150727/E740B793B37A40D19834E7F766D045B9.JPG" />
                                </a></li>
                                <li class="js-img-center"><a class="zoomThumbActive" href='javascript:void(0);'> <img src="http://images.8637.com/upload/brand/img/2015/20150727/6D495C07EEE7470CB2CE3924355C3CD0.JPG_60x60.JPG" data-src="http://images.8637.com/upload/brand/img/2015/20150727/6D495C07EEE7470CB2CE3924355C3CD0.JPG" />
                                </a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="m-collect">
                        <a href="#" class="link"><i class="icon-collect"></i>收藏商品<span>（1234）</span></a>
                    </div>
                    <div class="m-share">
                        <div class="bdsharebuttonbox" data-tag="share_1" style="margin: 10px 0 0 0">
                            <span href="#" style="float: left; margin-top: 6px;"><i class="icon-share"></i>分享到：</span>
                            <a href="#" class="bds_tsina" data-cmd="tsina"></a>
                            <a href="#" class="bds_qzone" data-cmd="qzone"></a>
                            <a href="#" class="bds_renren" data-cmd="renren"></a>
                            <a href="#" class="bds_more" data-cmd="more"></a>
                        </div>
                        <script>
                            window._bd_share_config = {
                                "common": { "bdSnsKey": {}, "bdText": "", "bdMini": "2", "bdPic": "", "bdStyle": "1", "bdSize": "16" },
                                "share": {}
                            };
                            with (document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];
                        </script>
                    </div>
                </div>
                <div class="checkbox f-l">
                    <h1 class="title" title="大熊连帽外套" style="word-break: break-all;">大熊连帽外套</h1>
                    <p style="padding: 0 20px 15px 20px;color: #666;line-height: 18px;border-bottom:1px solid #f2f2f2;">全店混批3000元或100件起批。厂家支持看样。</p>
                    <div class="m-detail">
                        <dl class="clearfix">
                            <dt>货号</dt>
                            <dd>123456789123</dd>
                        </dl>
                        <dl class="clearfix">
                            <dt>吊牌价</dt>
                            <dd>569.00</dd>
                        </dl>
                        <div class="m-special">
                            <dl class="clearfix">
                                <dt>现款价</dt>
                                <dd class="clearfix">
                                    <span class="fl msp1">
                                        <i>￥</i><em>150.00-160.00</em>
                                    </span>
                                    <a href="#" class="link fr">什么叫现款价？</a>
                                </dd>
                            </dl>
                            <dl class="clearfix">
                                <dt>授信价</dt>
                                <dd class="clearfix">
                                    <span class="fl msp2">
                                        <i>￥</i><em>150.00-160.00</em><i class="icon-shouxin" style="margin-left: 25px;"></i>
                                    </span>
                                    <a href="#" class="link fr">什么叫现款价？</a>
                                </dd>
                            </dl>
                        </div>
                        <%--未授权 开始--%>
                        <%--<div class="m-special">
                            <div class="not-see"><a href="#">加盟</a>或<a href="#">登录</a>后查看价格</div>
                        </div>--%>
                        <%--未授权 结束--%>
                        <dl class="clearfix">
                            <dt>起批量</dt>
                            <dd>>10（<a href="#" class="link">起批说明</a>）</dd>
                        </dl>
                        <dl class="clearfix">
                            <dt>物流运费</dt>
                            <dd>买家承担（<a href="#" class="link">运费说明</a>）</dd>
                        </dl>
                    </div>
                    <%--未授权 开始--%>
                    <%--<div class="m-explain">
                        <h3>您如无法查看采购价格，可能存在的原：</h3>
                        <p>1.是否注册/登录8637品牌超级代理</p>
                        <p>2.是否与该品牌<em>达成加盟协议</em></p>
                        <p>2.是否<em>获得</em>该品牌的<em>产品授权</em></p>
                    </div>--%>
                    <%--未授权 结束--%>
                    <div class="m-detail">
                        <dl class="mt15 clearfix">
                            <dt>颜色</dt>
                            <dd>
                                <ul class="m-colorlist inline-float" id="js-colorlist">
                                    <li class="selected" data-item="[{color: '深灰', size: 'S', xk: 120.00, sx: 150, kc: 100},{color: '深灰2', size: 'XL', xk: 20.00, sx: 90, kc: 1000}]"><a href="javascript:;">深灰</a><span></span></li>
                                    <li data-item="[{color: '军绿', size: 'X', xk: 30.00, sx: 50, kc: 102}]"><a href="javascript:;">军绿</a><span></span></li>
                                    <li data-item="[{color: '铁锈红', size: 'XLL', xk: 35.00, sx: 53, kc: 1020}]"><a href="javascript:;">铁锈红</a><span></span></li>
                                    <li data-item="[{color: '其他', size: 'X', xk: 34.00, sx: 86, kc: 1000},{color: '其他2', size: 'X', xk: 34.00, sx: 86, kc: 1000},{color: '其他3', size: 'X', xk: 34.00, sx: 86, kc: 1000}]"><img src="" alt="" width="32" height="32"/><span></span></li>
                                </ul>
                            </dd>
                        </dl>
                    </div>
                    <div class="mt15">
                        <table class="table-head">
                            <colgroup>
                                <col width="90"/>
                                <col width="82"/>
                                <col width="88"/>
                                <col width="85"/>
                                <col width="60"/>
                                <col width="140"/>
                            </colgroup>
                            <thead>
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th>现款价</th>
                                    <th>授信价</th>
                                    <th>库存</th>
                                    <th></th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                    <div class="m-detail">
                        <dl class="clearfix">
                            <dt>尺码</dt>
                            <dd>
                                <table class="table-list">
                                    <colgroup>
                                        <col width="90"/>
                                        <col width="95"/>
                                        <col width="85"/>
                                        <col width="55"/>
                                        <col width="130"/>
                                    </colgroup>
                                    <tbody id="js-list">
                                        <%--<tr>
                                            <td style="font-weight: bold;">s【做工保证】</td>
                                            <td>150.00元</td>
                                            <td>170.00元</td>
                                            <td>1000</td>
                                            <td>
                                                <div class="clearfix"><input type="text" class="num-amount" value="2" data-max="1000"/></div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="font-weight: bold;">M【做工保证】</td>
                                            <td>150.00元</td>
                                            <td>170.00元</td>
                                            <td>1000</td>
                                            <td>
                                                <div class="clearfix"><input type="text" class="num-amount" value="2" data-max="1000"/></td>
                                        </tr>--%>
                                    </tbody>
                                </table>
                            </dd>
                        </dl>
                    </div>
                    <div class="m-balance">
                        <span><em id="js-count">5</em>件</span>
                        <label>
                            <input type="radio" name="balances" checked />现款价：<em id="js-xkprick">910.00</em>元
                        </label>
                        <label>
                            <input type="radio" name="balances"/>授信价：<em id="js-sxprick">920.00</em>元
                        </label>
                    </div>
                    <!-- 判断该产品所属品牌是否过期,如过期就让该产品显示下架  4 为过期-->
                    <div class="add-settle">
                        <a href="javascript:joinShop('12')" id="addcart" class="btn1"><i class="icon-jhds"></i>加入进货单</a>
                        <a href="javascript:joinShop('12',true)" id="toBalance" class="btn2">去结算</a>
                    </div>
                    <div class="no-product-tip" id="noProductTip">
                        <i class="icon-warning"></i>订购数量必须为大于0的整数
                    </div>
                </div>
            </div>
            <div class="chanpin-fd clearfix">
                <table>
                    <colgroup>
                        <col width="310"/>
                        <col width="325"/>
                        <col width="210"/>
                    </colgroup>
                    <tbody>
                        <tr>
                            <td class="ta-c"><i class="icon-nayang"></i>拿样价：￥200.00</td>
                            <td class="ta-l">
                                <p>产品：拿样产品仅支持拿样一件</p>
                                <p>运费：与该产品运费一致</p>
                                <p>退换货：不支持退货</p>
                            </td>
                            <td class="ta-r">
                                <a href="#" class="btn1">立即拿样</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!---------------------------------   右边栏目      --------------------------------->
        <div class="sidebar-r">
            <div class="m-brand">
                <div class="mb-fore1 js-img-center">
                    <a href="#"><img src="" alt="" width="180" height="90"/></a>
                </div>
                <div class="mb-fore2">
                    <a href="#">朵彩</a>
                </div>
                <div class="mb-fore3 clearfix">
                    <a href="javascript:;"><i class="icon-view"></i>浏览量：<em>75699</em></a>
                    <a href="javascript:;" style="margin-left: 20px;"><i class="icon-follow"></i>关注：<em>4590</em></a>
                </div>
                <div class="mb-fore4">
                    <h3 class="tit">品牌供应商：</h3>
                    <p>江阴市飞豪服饰有限公司</p>
                </div>
                <div class="mb-fore5 clearfix">
                    <span><i class="icon-ident"></i>认证企业</span>
                    <span style="text-align: center;"><i class="icon-grade"></i>优质品牌</span>
                    <span style="text-align: right;"><i class="icon-joined"></i>已加盟</span>
                </div>
            </div>
            <!--// 品牌及加盟信息 -->
            <div class="m-info">
                <%--未授权 开始--%>
                <%--<div class="mi-fore5">
                    <a href="#">提交加盟申请书</a>
                </div>--%>
                <%--未授权 结束--%>
                <div class="mi-fore1">
                    <dl class="clearfix">
                        <dt class="pic"><i class="icon-shou"></i></dt>
                        <dd class="tit">您是我们的加盟商户</dd>
                        <dd class="des">加盟关系：<em>金牌终端商</em></dd>
                    </dl>
                </div>
                <div class="mi-fore2 clearfix">
                    <a href="#">我的授权产品库</a>
                    <a href="#" style="margin-left: 10px;">我的进货单</a>
                </div>
                <div class="mi-fore3 clearfix">
                    <p class="fl">累计进货<strong>1025</strong>款</p>
                    <p class="fr">进货额<strong>10251025.00</strong>元</p>
                </div>
                <div class="mi-fore4">
                    <dl class="clearfix">
                        <dt>企业官网</dt>
                        <dd><a href="http://www.docare.com/">www.docare.com</a></dd>
                        <dt>联系地址</dt>
                        <dd>上海市闵行区莘建东路58弄绿地科技岛3号9楼</dd>
                        <dt>所属行业</dt>
                        <dd>服装>内衣</dd>
                        <dt>注册资金</dt>
                        <dd>5000万人民币</dd>
                        <dt>公司规模</dt>
                        <dd>10000人</dd>
                        <dt>年营业额</dt>
                        <dd>1000000万以上</dd>
                    </dl>
                </div>
            </div>
            <!--// 详细信息 -->
            <div class="m-contact">
                <div class="mc-fore1">
                    <p>恒源祥在线咨询服务时间：8：30-17：00</p>
                </div>
                <div class="mc-fore2">
                    <p class="p1">0574-55558888</p>
                    <p>+86 130 2222 3333</p>
                </div>
                <%--未授权 开始--%>
                <%--<div class="mc-fore2">
                    <p class="p1">0574-555****888</p>
                    <p class="p2"><a class="link" href="#">登录</a> | 平台查看完整的联系方式</p>
                </div>--%>
                <%--未授权 结束--%>
                <div class="mc-fore3">
                    <ul class="inline-float">
                        <li>小明：<a href="http://wpa.qq.com/msgrd?v=3&amp;uin=11111111&amp;site=qq&amp;menu=yes"><img src="/images/market/qq-service.png" alt="" width="75"/></a></li>
                        <li>小明：<a href="http://wpa.qq.com/msgrd?v=3&amp;uin=11111111&amp;site=qq&amp;menu=yes"><img src="/images/market/qq-service.png" alt="" width="75"/></a></li>
                    </ul>
                </div>
            </div>
            <!--// 联系信息 -->
            <div class="m-down">
                <h3 class="tit">公司旗下品牌：</h3>
                <ul class="inline-float">
                    <li>
                        <a href="#" class="js-img-center"><img src="" alt="" width="70" height="35"/></a>
                        <p><a href="#">朵彩小羽绒</a></p>
                    </li>
                    <li>
                        <a href="#" class="js-img-center"><img src="" alt="" width="70" height="35"/></a>
                        <p><a href="#">朵彩小羽绒</a></p>
                    </li>
                    <li>
                        <a href="#" class="js-img-center"><img src="" alt="" width="70" height="35"/></a>
                        <p><a href="#">朵彩小羽绒</a></p>
                    </li>
                    <li>
                        <a href="#" class="js-img-center"><img src="" alt="" width="70" height="35"/></a>
                        <p><a href="#">朵彩小羽绒</a></p>
                    </li>
                </ul>
            </div>
            <div class="contact clear" style="padding: 20px 15px;position: relative;border:0;">
                <img src="${res}/images/market/contect.png" alt="">
                <div class="qq-area">
                    <script charset="utf-8" type="text/javascript" src="http://wpa.b.qq.com/cgi/wpa.php?key=XzkzODAwMTU5Nl8xNTE2NjRfNDAwMTExODYzN18"></script>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="http://images.8637.com/scripts/jquery.min.js"></script>
<script src="http://images.8637.com/scripts/market/jquery.elastislide.js"></script>
<script src="http://images.8637.com/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="http://images.8637.com/scripts/seajs_config.js"></script>
<script src="http://images.8637.com/scripts/common.js"></script>
<script src="http://images.8637.com/scripts/common/base-init.js"></script>
<script src="http://images.8637.com/scripts/market/last.js"></script>
<script src="http://172.16.1.8:81/scripts/common/jquery.cart.js"></script>
<script type="text/javascript">
$(function() {
    var $div_li = $("div.js-format .js-formatul li");
    $div_li.click(function() {
        $(this).addClass("selected").siblings().removeClass(
                "selected");
        var index = $div_li.index(this);
        $("div.pro-format-detail > div.pro-format-item").eq(
                index).show().siblings().hide();
    });

    var $div_li2 = $("div.js-specs .js-specsul li");
    $div_li2.click(function() {
        $(this).addClass("selected").siblings().removeClass(
                "selected");
        var index = $div_li2.index(this);
        $("div.js-specscon > div.proinfo-specs-items")
                .eq(index).show().siblings().hide();
    });

    //按钮切换大图效果
    var Num = 0;//图片计数
    var Len = $(".magniul li").length;//图片的个数

    $(".magniul li").eq(0).addClass("active");
    function changeImg(eq) {//上面box图片替换
        var src = $(".magniul li").eq(eq).find("img").data("src");

        $(".magniul li").removeClass("active");
        $(".magniul li").eq(eq).addClass("active");

        $(".magnimg")
                .html(
                        '<a href="javascript:;" class="jqzoom js-img-center">'
                        + '<img src="' + src + '" />'
                        + '<span></span>'
                        + '</a>'
                        + '<div class="mark" style=" position:absolute;top:0;left:0;z-index:2;background:#FFFFFF;filter:alpha(opacity:50);opacity:0.5;border:1px solid #333;display:none;cursor: move;"></div>');
    }
    //选中框的改变
    function changeActive(eq){
        $(".magniul li").removeClass("active");
        $(".magniul li").eq(eq).addClass("active");
    }
    $(".magni-all").on("click", ".prev", function() {//上一张
        if (Num > 0) {
            Num--;
            changeImg(Num);
        }
    });
    $(".magni-all").on("click", ".next", function() {//下一张
        if (Num < (Len - 1)) {
            Num++;
            changeImg(Num);
        }
    });
    $(".magniul li a").click(function() {//单个点击图片的时候
        var index = $(this).parent().index();
        Num = index;
        changeImg(Num);
    });

    seajs.use(["glasses"], function (Glasses) {
        //放大镜
        new Glasses({
            elem:".magnimg img"
        });
    });

    $(".attention")
            .on(
            'click',
            '.js-collected-brands',
            function() {
                $
                        .post(
                        "/market/brand/attentionNum/add",
                        {
                            brandesId : "F80CE4B8EB5F437CA68FA42CCF507C2C"
                        },
                        function(data) {
                            switch (data.code) {
                                case zttx.SUCCESS:
                                    remind(
                                            "success",
                                            "收藏成功",
                                            1000,
                                            function() {
                                                var _value = parseInt($(
                                                        "em.gz-num")
                                                        .text());
                                                $(
                                                        ".attention span.weiguanzhu")
                                                        .addClass(
                                                        "yiguanzhu")
                                                        .removeClass(
                                                        "weiguanzhu")
                                                        .next()
                                                        .text(
                                                        "已收藏")
                                                        .removeClass(
                                                        "js-collected-brands");
                                                $(
                                                        "em.gz-num")
                                                        .text(
                                                                _value + 1);
                                            });
                                    break;
                                case zttx.NOT_LOGIN:
                                    var currentUrl = location.href;
                                    location.href = '/common/login?loginType=1&redirect='
                                            + encodeURIComponent(currentUrl);
                                    break;
                                default:
                                    remind("error",
                                            data.message);
                            }
                        }, "json");
            });

    setTimeout(function() {
        $.post('/market/user_rtk', {
            id : 'F80CE4B8EB5F437CA68FA42CCF507C2C',
            code : 10,
            productId : '624474268448591872'
        });
    }, 2000);

    cart.load();
});

function joinShop(productId,status) {
    var objdata = new Object();
    objdata.title = "提示";
    $.post(
                    "/dealer/shoper/purchase/"
                    + productId
                    + "/default",
            function(data) {
                if (data.code == 126000) {
                    remind("success", "此产品已成功加入进货单");
                    var item = {};
                    item.productId=productId;
                    item.image=$('a.jqzoom img').attr('src');
                    item.title=$('h1.title ').text();
                    item.price=$('span.pro-dt-nomber').text();
                    item.cartId=data.object;
                    cart.add(item);
                    if(status){
                        location.href='/dealer/shopper';
                    }

                } else if (data.code == 111048) {
                    //尚未购买服务
                    objdata.content = "抱歉~您还未支付平台服务费，暂时无法下单马上支付1200元服务费，享受一手货源";
                    confirmDialog(
                            objdata,
                            function() {
                                window
                                        .open("/dealer/service/details?refrenceId=S001");
                            });
                } else if (data.code == 111040) {
                    //已加入购物车
                    if(status==null){
                        remind("error", "已加入进货单，请勿重复添加");
                    }else{
                        location.href='/dealer/shopper';
                    }
                } else {
                    remind("error", data.message);
                    //remind("error","此产品已在进货单,无需重复加入");
                }
            }, "json");
}

/**购物车*/
var cart = {};
/**购物车 放入产品 添加 条目*/
cart.add = function(item) {
    if ($("div.real-time-con").find('#id_' + item.productId).size() == 1) {
        remind("error", "已加入进货单，请勿重复添加");
        return;
    }

    var cartItem = [];
    cartItem.push('<dl class="cart-item" id="id_'+item.productId+'">');
    cartItem.push(' <dt class="pic">');
    cartItem.push('  <a href="#"><img src="'+item.image+'" alt="" width="70" height="70"></a>');
    cartItem.push(' </dt>');
    cartItem.push('<dd class="price">');
    cartItem.push('    <span class="text-yahei text-orange text-lg"><span class="text-md">￥</span>'+ item.price + '</span>');
    cartItem.push(' </dd>');
    cartItem.push('<dd class="title">' + item.title + '</dd>');
    cartItem.push('<dd class="del"><a class="icon icon-delete" href="javascript:;"  onclick=cart.remove("'+item.productId+'")></a></dd>');
    cartItem.push('</dl>');
    $("div.real-time-con").append(cartItem.join(""));
    $("#cartCount").html(parseInt($("#cartCount").html()) + 1);
};

/**从cookies里载入物品到购物车*/
cart.load = function() {
    seajs.use([ "cookie" ], function(Cookie) {
        Cookie.get("cart_items", function(s) {
            s = decodeURIComponent(s);
            if(s == "undefined" || s == ""){
                return;
            }
            if( false){
                return;
            }
            var cart_items = jQuery.parseJSON(s);
            for ( var i = 0; i < cart_items.length; i++) {
                cart.add(cart_items[i]);
            }
        });
    });
};

/**清空所有*/
cart.clear=function(){
    $('div.real-time-con').empty();
    $("#cartCount").html(0);
    cart.store();
};

cart.remove=function(id){
    $('#id_'+id).detach();
    $("#cartCount").html(parseInt($("#cartCount").html()) - 1);
    cart.store();
};

/**从cookies里载入物品到购物车*/
cart.store = function() {
    seajs.use([ "cookie" ], function(Cookie) {
        var str_cart_item = "";
        var cart_items = [];
        $("div.real-time-con dl").each(function(i, obj) {
            var cart_item = {};
            cart_item.productId = $(this).attr('id')
                    .split('_')[1];
            cart_item.title = $(this).find('dd.title')
                    .html();
            cart_item.price = $(this).find(
                    'dd.price span.text-lg').text()
                    .replace("￥", "");
            cart_item.image = $(this).find('img').attr(
                    'src');
            cart_item.code = "A00002";
            cart_items.push(cart_item);
        });
        str_cart_item = JSON.stringify(cart_items);
        Cookie.set("cart_items",str_cart_item,{path:'/',domain:document.domain});
    });
};

/**直接加入本地购物车*/
function joinShopCookie(productId,code,status) {
    var item={};
    item.productId=productId;
    item.image=$('a.jqzoom img').attr('src');
    item.title=$('h1.title ').text();
    item.price=$('span.pro-dt-nomber').text();
    cart.add(item);
    cart.store();
    if(status){
        location.href='/common/login?redirect=/dealer/shopper';
    }
}

function joinShopCookie1(productId,code,status) {
    if(status){
        location.href='/common/login?redirect=/market/product/'+productId+'?code='+code;
    }
}

function removeCart(id,proudctId){
    $.post("/dealer/shoper/delete",{id:id},function(data){
        if(data.code==111000){
            $('#id_'+proudctId).detach();
            $("#cartCount").html(parseInt($("#cartCount").html()) - 1);
        }
    },"json");
}

function removeAll(){
    $.post("/dealer/shoper/deleteAll?ids=",function(data){
        if(data.code==111000){
            $('div.real-time-con').empty();
            $("#cartCount").html(0);
        }
    },"json");
}

/*新页面调整 新加js*/
seajs.use(['template', 'underscore'],function(Template, _){

    var $colorlist_li = $("#js-colorlist li");
    window.tempArr = [];

    $colorlist_li.click(function(){
        var index = $(this).index();
        var data = $.type($(this).data("item")) == "string" ? eval('(' + $(this).data("item") + ')') : $(this).data("item");
        for(var i = 0,ilen = data.length; i < ilen; i++){
            tempArr.push(data[i]);
        }
        renderList(data);
        $colorlist_li.removeClass("selected").eq(index).addClass("selected").addClass("checked");
    });

    function renderList(data){
        var htm = Template.render("tpl_list", data);
        $("#js-list").html(htm);
        $(".table-list tr").cartInit({},function(){},function(val, o){
            var _amount = $(o).find(".num-amount");
            var colorVal =_amount.data("color");
            var sizeVal =_amount.data("size");
            console.log(tempArr);
            var nowData = _.where(tempArr, {color: colorVal, size: sizeVal});
            console.log(nowData);
            if(nowData.length > 0){
                nowData[0].xz = val;
                setXz(tempArr, nowData);
            }
        });
    }

    function setXz(tempArr, singleArr){
        for(var i = 0, len = tempArr; i < len; i++){
            if(tempArr[i].color === singleArr[0].color && tempArr[i].size === singleArr[0].size){
                tempArr[i].xz = singleArr[0].xz;
            }
        }
    }

    //$(".table-list tr").cartInit();
});
</script>
<script id="tpl_list" type="text/html">
    {{each}}
    <tr>
        <td style="font-weight: bold;">{{$value.size}}</td>
        <td>{{$value.xk}}元</td>
        <td>{{$value.sx}}元</td>
        <td>{{$value.kc}}</td>
        <td>
            <div class="clearfix"><input type="text" class="num-amount" data-color="{{$value.color}}" data-color="{{$value.size}}" value="{{if $value.xz}}{{$value.xz}}{{else}}0{{/if}}" data-max="{{$value.kc}}"/></div>
        </td>
    </tr>
    {{/each}}
</script>
</body>
</html>