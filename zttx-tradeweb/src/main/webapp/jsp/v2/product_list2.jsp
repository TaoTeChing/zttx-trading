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
        /*.change-layout-list{}
        .change-layout-list ul{
            width: 1156px;
            margin-right: 0px;
        }
        .change-layout-list li{
            width: 1116px;
            height: auto;
            padding: 20px;
        }
        .change-layout-list .pic,.change-layout-list .detail{
            float:left;
        }
        .change-layout-list .pic .js-img-center{
            display: block;
            width: 90px;
            height: 90px;
        }
        .change-layout-list .pic .js-img-center img{
            max-width: 90px;
            max-height: 90px;
            _width: 90px;
            _height: 90px;
        }
        .change-layout-list .detail{}*/

        /*列表模式重置样式*/
        .entry-list{
            margin-bottom: 20px;
        }
        .entry-list li{
            width: 1116px;
            height: auto;
            padding: 20px;
            margin-top: -1px;
            margin-right: 0;
            margin-bottom: 0;
            background-color: #fcfcfc;
        }
        .entry-list li:hover{
            border-color: #f3f3f3;
            background-color: #fefce8;
        }
        .entry-list .entry-pic,
        .entry-list .entry-detail,
        .entry-list .entry-price,
        .entry-list .entry-operate{
            float: left;
            display: inline;
            height: 90px;
            margin-top: -5px;
            margin-left: 20px;
        }
        .entry-list .entry-pic{
            margin-left: 0;
            margin-top: 0;
        }
        .entry-list .entry-pic .js-img-center{
            display:inline-block;
            *display:inline;
            *zoom:1;
            width: 90px;
            height: 90px;
            border:1px solid #f3f3f3;
        }
        .entry-list .entry-pic .js-img-center img{
            max-width: 90px;
            max-height: 90px;
        }
        .entry-list .entry-detail{
            width: 290px;
            height: 90px;
            position: relative;
        }
        .entry-list .entry-detail .title{
            /*margin-top: -5px;*/
            font-size: 14px;
        }
        .entry-list .entry-detail .title a{
            color: #666;
        }
        .entry-list .entry-detail .title a:hover{
            color: #f80;
        }
        .entry-list .entry-detail .number{
            margin-top: 5px;
            color: #999;
        }
        .entry-list .entry-detail .signs{
            position: absolute;
            left:0;
            bottom: -6px;
        }
        .entry-list .entry-operate{
            margin-top: 0;
        }
        .entry-list .entry-operate .btns{
            display: inline-block;
            *display:inline;
            *zoom:1;
            width: 80px;
            height: 26px;
            line-height: 26px;
            padding: 0 10px;
            margin-bottom: 10px;
            border-radius: 3px;
            border: 1px solid #e5e5e5;
            background-color: #fff;
            text-align: center;
            color: #666;
            -webkit-transition: 0.3s;
            -moz-transition: 0.3s;
            -o-transition: 0.3s;
            transition: 0.3s;
        }
        .entry-list .entry-operate .btns:hover{
            border-color: #d24848;
            background: #f55;
            color: #fff;
        }
        .entry-list .entry-price{
            width: 270px;
            margin-left: 20px;
            font-size: 14px;
            color: #999;
        }
        .entry-list .entry-price span{
            color: #e00;
        }
        .entry-list .entry-price strong{
            font-weight: normal;
        }
        /*扩展两个图标，icon-window,icon-entry*/
        .m-filter .m-f-4{
            height: auto;
            margin-top: 8px;
            margin-right: 10px;
            padding: 0;
        }
        .m-filter .m-f-4 .icon-window,
        .m-filter .m-f-4 .icon-entry{
            float: left;
            border: 1px solid #ccc;
            width: 30px;
            height:24px;
            background:#fff url("/images/fronts/market/list-type.png") no-repeat;
        }
        .m-filter .m-f-4 .icon-window{
            border-right: 0;
            background-position: left top;
        }
        .m-filter .m-f-4 .icon-entry{
            border-left: 0;
            background-position: left bottom;
        }
        .m-filter .m-f-4 .active{
            border-color: #ddd;
            background-color: #ddd;
        }

        .order-table{ border: #ccc solid 1px;}
        .order-table td,th{ padding: 8px 0;}
        .order-table td{
            border-bottom: #d9d9d9 solid 1px;
            padding-left: 8px;
            padding-right: 8px;
        }
        .order-table th{
            padding-left: 8px;
            padding-right: 8px;
            text-align: left;
            border-bottom: 1px solid #d9d9d9;
            color: #666;
            background-color: #f6f6f6;
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f8f8f8', endColorstr='#F2F2F2');
            background: -webkit-gradient(linear, left top, left bottom, from(#f8f8f8), to(#f2f2f2));
            background: -moz-linear-gradient(top, #f8f8f8, #f2f2f2);
            background: -o-linear-gradient(top, #f8f8f8, #f2f2f2);
            background: linear-gradient(top, #f8f8f8, #f2f2f2);
        }

        .set_comfirm{
            display: inline-block;
            *display:inline;
            *zoom:1;
            width: 80px;
            height: 26px;
            line-height: 26px;
            padding: 0 10px;
            margin-bottom: 10px;
            border-radius: 3px;
            border: 1px solid #e5e5e5;
            background-color: #fff;
            text-align: center;
            color: #666;
            -webkit-transition: 0.3s;
            -moz-transition: 0.3s;
            -o-transition: 0.3s;
            transition: 0.3s;
        }
        .set_comfirm:hover{
            border-color: #d24848;
            background: #f55;
            color: #fff;
        }

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
                    <%--<li class="m-f-3"><label><input type="checkbox"/><span>支持拿样</span></label><i class="icon-nayang"></i></li>
                    <li class="m-f-3"><label><input type="checkbox"/><span>我的授权商品</span></label><i class="icon-shouxin"></i></li>
                    <li class="m-f-3"><label><input type="checkbox"/><span>我的授信商品</span></label><i class="icon-yuding"></i></li>--%>
                </ul>
                <div class="m-f-4 fr">
                    <%--<a href="javascript:;" class="prev"><i class="icon-d-prev"></i></a>
                    <span><em>1</em>/100</span>
                    <a href="javascript:;" class="next"><i class="icon-f-next"></i></a>--%>
                    <a href="javascript:;"><i class="icon-window active"></i></a>
                    <a href="javascript:;"><i class="icon-entry"></i></a>
                </div>
            </div>
            <%--<div class="m-list window-list" id="windowList">
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
                </ul>
            </div>--%>
            <div class="m-list entry-list" id="entryList">
                <%--放十五条数据--%>
                <ul class="inline-float">
                    <li class="clearfix">
                        <div class="entry-pic">
                            <a href="/market/product/634259102104555520?code=default" title="hehehei冬季中长款宽松翻领普通长袖常规袖" class="js-img-center" target="_blank">
                                <img src="http://images.8637.com/upload/brand/img/2015/20151030/7FCB94F0E29245DAA9CC2F144F01E44D.JPG_220x220.JPG" alt="hehehei冬季中长款宽松翻领普通长袖常规袖">
                                <span></span><span></span></a>
                        </div>
                        <div class="entry-detail">
                            <div class="title">
                                <a href="/market/product/634259102104555520?code=default">hehehei冬季中长款宽松翻领普通长袖常规袖阿斯打扫打扫阿斯打扫的阿斯达啊啊去</a>
                            </div>
                            <div class="number">货号：15D081</div>
                            <div class="signs">
                                <span class="fl"><i class="icon-nayang" title="支持拿样"></i><i class="icon-shouxin ml5" title="我的授权商品"></i><i class="icon-yuding ml5" title="我的授信商品"></i></span>
                            </div>
                        </div>
                        <div class="entry-price">
                            吊牌价：<em>￥</em>
                            <strong>318.25</strong>
                        </div>
                        <div class="entry-price">
                            其他价格（名称要改）：
                            <span><em>￥</em><strong>318.25</strong></span>
                        </div>
                        <div class="entry-operate">
                            <%--<a class="btns" href="javascript:addFavor('634259102104555520');"><i class="icon-collect"></i>收藏</a><br />--%>
                            <a class="btns" href="javascript:joinShop('634259102104555520','0');">加入进货单</a>
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="entry-pic">
                            <a href="/market/product/634259102104555520?code=default" title="hehehei冬季中长款宽松翻领普通长袖常规袖" class="js-img-center" target="_blank">
                                <img src="http://images.8637.com/upload/brand/img/2015/20151030/7FCB94F0E29245DAA9CC2F144F01E44D.JPG_220x220.JPG" alt="hehehei冬季中长款宽松翻领普通长袖常规袖">
                                <span></span><span></span></a>
                        </div>
                        <div class="entry-detail">
                            <div class="title">
                                <a href="/market/product/634259102104555520?code=default">hehehei冬季中长款宽松翻领普通长袖常规袖阿斯打扫打扫阿斯打扫的阿斯达啊啊去</a>
                            </div>
                            <div class="number">货号：15D081</div>
                            <div class="signs">
                                <span class="fl"><i class="icon-nayang" title="支持拿样"></i><i class="icon-shouxin ml5" title="我的授权商品"></i><i class="icon-yuding ml5" title="我的授信商品"></i></span>
                            </div>
                        </div>
                        <div class="entry-price">
                            吊牌价：<em>￥</em>
                            <strong>318.25</strong>
                        </div>
                        <div class="entry-price">
                            其他价格（名称要改）：
                            <span><em>￥</em><strong>318.25</strong></span>
                        </div>
                        <div class="entry-operate">
                            <%--<a class="btns" href="javascript:addFavor('634259102104555520');"><i class="icon-collect"></i>收藏</a><br />--%>
                            <a class="btns" href="javascript:joinShop('634259102104555520','0');">加入进货单</a>
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="entry-pic">
                            <a href="/market/product/634259102104555520?code=default" title="hehehei冬季中长款宽松翻领普通长袖常规袖" class="js-img-center" target="_blank">
                                <img src="http://images.8637.com/upload/brand/img/2015/20151030/7FCB94F0E29245DAA9CC2F144F01E44D.JPG_220x220.JPG" alt="hehehei冬季中长款宽松翻领普通长袖常规袖">
                                <span></span><span></span></a>
                        </div>
                        <div class="entry-detail">
                            <div class="title">
                                <a href="/market/product/634259102104555520?code=default">hehehei冬季中长款宽松翻领普通长袖常规袖阿斯打扫打扫阿斯打扫的阿斯达啊啊去</a>
                            </div>
                            <div class="number">货号：15D081</div>
                            <div class="signs">
                                <span class="fl"><i class="icon-nayang" title="支持拿样"></i><i class="icon-shouxin ml5" title="我的授权商品"></i><i class="icon-yuding ml5" title="我的授信商品"></i></span>
                            </div>
                        </div>
                        <div class="entry-price">
                            吊牌价：<em>￥</em>
                            <strong>318.25</strong>
                        </div>
                        <div class="entry-price">
                            其他价格（名称要改）：
                            <span><em>￥</em><strong>318.25</strong></span>
                        </div>
                        <div class="entry-operate">
                            <%--<a class="btns" href="javascript:addFavor('634259102104555520');"><i class="icon-collect"></i>收藏</a><br />--%>
                            <a class="btns" href="javascript:joinShop('634259102104555520','0');">加入进货单</a>
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
    <script id="joinOrderTpl" type="text/html">
        <div>
            <div class="ui-head">
                <h3>请选择加入进货单产品的数量</h3>
            </div>
            <form id="joinOrderForm" action="">
                <input type="hidden" id="productId"/>
                <table class="order-table" style="margin-top: 20px;">
                    <thead>
                        <tr>
                            <th width="80">颜色/尺码</th>
                            <th width="90">吊牌价</th>
                            <th width="85">现款价</th>
                            <th width="85">授信价</th>
                            <th width="90">库存</th>
                            <th width="152">数量</th>
                        </tr>
                    </thead>
                </table>
                <div class="" style="max-height: 300px;margin-top:-1px;margin-bottom:20px;overflow-y: auto;">
                    <table class="order-table">
                        <thead>
                            <tr>
                                <th width="80" style="padding: 0;"></th>
                                <th width="90" style="padding: 0;"></th>
                                <th width="85" style="padding: 0;"></th>
                                <th width="85" style="padding: 0;"></th>
                                <th width="90" style="padding: 0;"></th>
                                <th width="152" style="padding: 0;"></th>
                            </tr>
                        </thead>
                        <tbody id="orderTableBody">
                            <tr>
                                <td>红_XL</td>
                                <td>123.00</td>
                                <td><span class="js-xk-price">8.00</span></td>
                                <td><span class="js-sx-price">5.00</span></td>
                                <td>15</td>
                                <td>
                                    <button type="button" class="num-minus">-</button>
                                    <input type="text" class="num-amount js-price" value="" data-max="15"/>
                                    <button type="button" class="num-plus">+</button>
                                </td>
                            </tr>
                            <tr>
                                <td>红_XL</td>
                                <td>123.00</td>
                                <td><span class="js-xk-price">6.00</span></td>
                                <td><span class="js-sx-price">3.00</span></td>
                                <td>25</td>
                                <td>
                                    <button type="button" class="num-minus">-</button>
                                    <input type="text" class="num-amount js-price" value="" data-max="25"/>
                                    <button type="button" class="num-plus">+</button>
                                </td>
                            </tr>
                            <tr>
                                <td>红_XL</td>
                                <td>123.00</td>
                                <td><span class="js-xk-price">12.00</span></td>
                                <td><span class="js-sx-price">9.00</span></td>
                                <td>80</td>
                                <td>
                                    <button type="button" class="num-minus">-</button>
                                    <input type="text" class="num-amount js-price" value="" data-max="80"/>
                                    <button type="button" class="num-plus">+</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div style="margin-bottom: 20px;">
                    <label><input type="radio"/> 现款价：<strong id="xk_price">0.00</strong></label>
                    <label class="ml10"><input type="radio"/> 授信价：<strong id="sx_price">0.00</strong></label>
                </div>
                <div class="ta-r">
                    <input type="submit" class="set_comfirm" value="确定"/>
                </div>
            </form>

        </div>
    </script>
    <script>
        $(".m-list li").hover(function(){
            $(this).addClass("hover");
        }, function(){
            $(this).removeClass("hover");
        });

        seajs.use(["dialog", "template"], function(Dialog, Template){
            var joinOrderDialog = new Dialog({
                width: 500,
                content: $("#joinOrderTpl").html()
            });

            /*加进货单方法作修改*/
            window.joinShop = function(productId, type){
                joinOrderDialog.show();

                /*发请求取得 sku 列表*/
                /*$.ajax({
                    url: "",
                    type: "",
                    data: "",
                    dataType: "json",
                    success: function(data){
                        var _html = Template.render("joinOrderTpl", data);
                        joinOrderDialog.set("content", _html).show();
                    }
                });*/
            };

            /*确定*/
            $(document).on("click", ".set_comfirm", function(){
                var params = $("#joinOrderForm").serialize();
                /*$.ajax({
                     url: "",
                     type: "",
                     data: "",
                     dataType: "json",
                     success: function(data){
                        if(data.code == 126000){
                            remind("success", "成功加入进货单");
                            joinOrderDialog.hide();
                        }else{
                            remind("error", data.message);
                        }
                     }
                 });*/
            });

            //计算现款价和授信价
            function countAllPrice(){
                var _xk = 0, _sx = 0;

                $("#orderTableBody tr").each(function(){
                    var sinXk = parseFloat($(this).find(".js-xk-price").text());
                    var sinSx = parseFloat($(this).find(".js-sx-price").text());
                    var _num = parseInt($(this).find(".num-amount").val()) || 0;

                    _xk += (sinXk * _num);
                    _sx += (sinSx * _num);
                });

                $("#xk_price").text(_xk.toFixed(2));
                $("#sx_price").text(_sx.toFixed(2));
            }

            /*数量输入*/
            var setNumberVal = function(options){
                var setting = {
                    numMinus: ".num-minus",
                    numAmount: ".num-amount",
                    numPlus: ".num-plus",
                    maxerror: "数量超过最大值",
                    callback: null,
                    reg: /[^\d]/g
                };
                var configs = $.extend({}, setting, options);
                for(var key in configs){
                    this[key] = configs[key];
                }
                this.init();
            };

            setNumberVal.prototype.init = function(){
                this.events();
            };

            setNumberVal.prototype.events = function(){
                var that = this;
                $(document).on("click", this.numMinus, function(){
                    var _val = $(this).next().val();
                    var amount = parseInt($(this).next().val()) || 0;
                    amount--;
                    that.changeValue($(this).next(), amount);
                });
                $(document).on("keyup change", this.numAmount, function(){
                    var amount = parseInt($(this).val());
                    that.amount($(this), amount);
                });
                $(document).on("click", this.numPlus, function(){
                    var amount = parseInt($(this).prev().val()) || 0;
                    amount++;
                    that.changeValue($(this).prev(), amount);
                });
            };

            setNumberVal.prototype.changeValue = function($amount, amount){
                var _max = $amount.data("max");
                if(amount < 0){
                    $amount.val("0");
                }
                if(amount > _max){
                    $amount.val(_max);
                    remind("error", this.maxerror);
                }
                if(amount <= _max && amount >= 0){
                    $amount.val(amount);
                }
                this.callback && this.callback();
            };

            setNumberVal.prototype.amount = function($amount, amount){
                $amount.val($amount.val().replace(this.reg, ''));
                if($amount.val() == ""){
                    amount = 0;
                }
                this.changeValue($amount, amount);
            };

            new setNumberVal({
                callback: countAllPrice
            });

        });


    </script>
</body>
</html>