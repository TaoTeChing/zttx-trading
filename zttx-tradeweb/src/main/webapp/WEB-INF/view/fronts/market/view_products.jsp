<%@ page import="com.zttx.web.consts.DealerConstant" %>
<%@ page import="com.zttx.web.module.brand.model.BrandesAuthUserModel" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>产品展示 ${brandesInfo.brandsName}-${brandInfo.comName } 8637品牌超级代理</title>
    <meta name="keywords"
          content="${brandesInfo.brandsName},${brandesInfo.brandsName}品牌代理,${brandesInfo.brandsName}品牌招商"/>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="${res}/styles/fronts/market/brandviewbase.css"/>
    <jsp:include page="${ctx}/market/header_css">
        <jsp:param name="brandesId" value="${brandesId}"/>
    </jsp:include>
    <link rel="stylesheet" href="${res}/styles/fronts/market/prodisshow.css"/>
</head>
<body>
<%--<!-- top -->--%>
<jsp:include page="${ctx}/common/top"/>

<%--<!-- head -->--%>
<jsp:include page="${ctx}/market/header">
    <jsp:param name="brandesId" value="${brandesId}"/>
    <jsp:param name="url" value="/product"/>
</jsp:include>

<div class="do-body">
    <%--产品列表和详情页面不需要背景图--%>
    <style>.do-body {
        background-image: none;
    }</style>
    <div class="all-prodisshow">
        <div class="prodisshow clear">
            <%--<c:if test="${showBtn}">
                <div class="comsite" style="margin-top: 15px;">
                    <div class="purchase" style="top:0;">
                        <a href="${ctx}/market/brand/viewProductInfo/${brandesId}/${brandId}?view=1" class="return-pic font"><i class="seticon return-pic-icon mr5"></i>返回图册</a>
                    </div>
                </div>
            </c:if>--%>
            <%--<!-- 品牌信息栏结束 -->--%>
            <div class="ts-container">
                <div class="m-product">
                    <div class="m-cate clearfix">
                        <div class="cate-tit fl">
                            店内分类：
                        </div>
                        <div class="cate-list fr">
                            <ul class="inline-float">
                                <li ${empty filter.cataId ? 'class="active"':''}>
                                    <a href="/product"><span>全部分类</span></a>
                                </li>
                                <c:forEach items="${primaryList}" var="item">
                                    <li ${primaryCate == item.refrenceId ? 'class="active"':''}>
                                        <a href="/product?cataId=${item.refrenceId}">${item.cateName}</a>
                                    </li>
                                </c:forEach>
                                <%--<li><a href="">九分女裤</a><em>（55）</em></li>--%>
                            </ul>
                        </div>
                    </div>
                    <form:form method="get" id="searchForm" action="/product">
                    <input type="hidden" name="displayMode" id="displayMode" value="${filter.displayMode }"/>
                    <input type="hidden" name="sort" id="sort" value="${filter.sort }"/>
                    <input type="hidden" name="order" id="order" value="${filter.order }"/>

                    <div class="m-search clearfix">
                        <div class="form fl">
                            <input type="text" name="keyWord" value="${filter.keyWord}" id="searchTxts"
                                   placeholder="输入商品名或货号" autocomplete="off"/>
                            <button type="submit" id="searchbtns"><i class="icon-search"></i></button>
                        </div>
                            <%--<div class="count fl">共搜索到 <strong>34</strong> 个符合条件的商品</div>--%>
                    </div>
                    <div class="m-filter clearfix">
                        <ul class="inline-float fl">
                            <li class="m-f-1 ${empty filter.sort? 'current':''}">
                                <a href="javascript:sortMethod('1','1');">默认排序</a></li>
                            <li class="m-f-1 ${filter.sort == 2 ? 'current':''}">
                                <a href="javascript:sortMethod('2','${(filter.sort == 2 and filter.order == 1)?2:1 }');">上架时间${filter.sort == 2 and filter.order == 2 ? '↑':'↓'}</a>
                            </li>
                            <li class="m-f-1 ${filter.sort == 3 ? 'current':''}">
                                <a href="javascript:sortMethod('3','${(filter.sort == 3 and filter.order == 1)?2:1 }');">产品价格${filter.sort == 3 and filter.order == 2 ? '↑':'↓'}</a>
                            </li>
                                <%--<li class="m-f-1"><a href="javascript:;">人气</a></li>
                                <li class="m-f-1"><a href="javascript:;">销量</a></li>
                                <li class="m-f-1"><a href="javascript:;">时间</a></li>
                                <li class="m-f-1"><a href="javascript:;">价格</a></li>
                                <li class="m-f-2">
                                    <input type="text" name="min" value="${filter.min}" class="fl"/>
                                    <span class="fl">-</span>
                                    <input type="text" name="max" value="${filter.max}" class="fl"/>
                                    <button type="submit" class="fl">确定</button></li>
                                <li class="m-f-3"><label>
                                    <input type="checkbox"/><span>支持拿样</span></label><i class="icon-nayang"></i>
                                </li>
                                <li class="m-f-3"><label>
                                    <input type="checkbox"/><span>我的授权商品</span></label><i class="icon-shouxin"></i>
                                </li>
                                <li class="m-f-3"><label>
                                    <input type="checkbox"/><span>我的授信商品</span></label><i class="icon-yuding"></i>
                                </li>--%>
                        </ul>
                        <div class="m-f-4 fr">
                                <%--<a href="javascript:;" class="prev"><i class="icon-d-prev"></i></a>
                                <span><em>1</em>/100</span>
                                <a href="javascript:;" class="next"><i class="icon-f-next"></i></a>--%>
                            <a href="javascript:displayMethod('1');"><i
                                    class="icon-window ${filter.displayMode==1?'active':'' }"></i></a>
                            <a href="javascript:displayMethod('0');"><i
                                    class="icon-entry ${filter.displayMode==0?'active':'' }"></i></a>
                        </div>
                    </div>

                    <c:choose>
                        <c:when test="${filter.displayMode ==0 }">
                            <div class="m-list entry-list" id="entryList">
                                <c:choose>
                                <c:when test="${pageResult.page.totalCount == 0}">
                                    <div class="no-data">
                                        <img src="${res}/images/fronts/index/null.png">
                                    </div>
                                </c:when>
                                <c:otherwise>
                                <c:set value="<%=BrandesAuthUserModel.NINCLUDE_SKU%>" var="state"/>
                                <c:set value="<%=BrandesAuthUserModel.NO_AUTH%>" var="no"/>
                                <c:set value="<%=BrandesAuthUserModel.CASH_AUTH%>" var="cash"/>
                                <c:set value="<%=BrandesAuthUserModel.CREDIT_AUTH%>" var="credit"/>

                                <c:set value="<%=DealerConstant.DealerShoper.PRODUCTTYPE_CASH%>"
                                       var="productType_cash"/>
                                <c:set value="<%=DealerConstant.DealerShoper.PRODUCTTYPE_CREDIT%>"
                                       var="productType_credit"/>
                                <ul class="inline-float">
                                    <c:forEach items="${pageResult.list}" var="item">
                                        <c:set value="${fns:getProductAuthPrice(item,state)}"
                                               var="brandesAuthUserModel"></c:set>
                                    <li class="clearfix">
                                        <div class="entry-pic">
                                            <a href="${ctx}/market/product/${item.refrenceId}?code=default"
                                               title="${item.productTitle}" class="js-img-center" target="_blank">
                                                <img src="${res}${fns:getImageDomainPath(item.productImage,220 ,220)}"
                                                     alt="${item.productTitle}">
                                                <span></span><span></span>
                                            </a>
                                        </div>
                                        <div class="entry-detail">
                                            <div class="title">
                                                <a href="${ctx}/market/product/${item.refrenceId}?code=default"
                                                   target="_blank">${item.productTitle}</a>
                                            </div>
                                            <div class="number">货号：${item.productNo}</div>
                                            <div class="signs">
	                                <span class="fl">
	                                	<c:if test="${item.isSample eq true}">
                                            <i class="icon-nayang" title="支持拿样"></i>
                                        </c:if>
		                                <c:if test="${item.isCredit eq true}">
                                            <i class="icon-shouxin ml5" title="我的授权商品"></i>
                                        </c:if>
	                                </span>
                                            </div>
                                        </div>
                                        <c:choose>
                                            <c:when test="${brandesAuthUserModel.isAuth eq no}">
                                                <div class="entry-price">
                                                    吊牌价：<em>￥</em>
                                                    <strong><fmt:formatNumber
                                                            value="${brandesAuthUserModel.productPrice}"
                                                            pattern="0.00"/></strong>
                                                </div>
                                                <div class="entry-price">
                                                </div>
                                            </c:when>
                                            <c:when test="${brandesAuthUserModel.isPoint}">
                                                <div class="entry-price">
                                                    返点价：<em>￥</em>
                                                    <span><strong><fmt:formatNumber
                                                            value="${brandesAuthUserModel.pointPrice}"
                                                            pattern="0.00"/></strong></span>
                                                </div>
                                                <div class="entry-price">
                                                    吊牌价：
                                                    <em>￥</em><strong><fmt:formatNumber
                                                        value="${brandesAuthUserModel.productPrice}"
                                                        pattern="0.00"/></strong>
                                                </div>
                                            </c:when>
                                            <c:when test="${brandesAuthUserModel.isAuth eq cash}">
                                                <div class="entry-price">
                                                    现款价：<em>￥</em>
                                                    <span><strong><fmt:formatNumber
                                                            value="${brandesAuthUserModel.productDirPrice}"
                                                            pattern="0.00"/></strong></span>
                                                </div>
                                                <div class="entry-price">
                                                    吊牌价：
                                                    <em>￥</em><strong><fmt:formatNumber
                                                        value="${brandesAuthUserModel.productPrice}"
                                                        pattern="0.00"/></strong>
                                                </div>
                                            </c:when>
                                            <c:when test="${brandesAuthUserModel.isAuth eq credit}">
                                                <div class="entry-price">
                                                    授信价：<em>￥</em>
                                                    <span><strong><fmt:formatNumber
                                                            value="${brandesAuthUserModel.productCreditPrice}"
                                                            pattern="0.00"/></strong></span>
                                                </div>
                                                <div class="entry-price">
                                                    吊牌价：
                                                    <em>￥</em><strong><fmt:formatNumber
                                                        value="${brandesAuthUserModel.productPrice}"
                                                        pattern="0.00"/></strong>
                                                </div>
                                            </c:when>
                                        </c:choose>
                                        <div class="entry-operate">
                                                <%--<a class="btns" href="javascript:addFavor('634259102104555520');"><i class="icon-collect"></i>收藏</a><br />--%>
                                            <c:if test="${not isBrand }">
                                                <a class="btns" href="javascript:joinShop('${item.refrenceId}','0');">加入进货单</a>
                                            </c:if>
                                        </div>
                                    </li>
                                    </c:forEach>
                                    </c:otherwise>
                                    </c:choose>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="m-list">
                                <c:choose>
                                    <c:when test="${pageResult.page.totalCount == 0}">
                                        <div class="no-data">
                                            <img src="${res}/images/fronts/index/null.png">
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set value="<%=BrandesAuthUserModel.NINCLUDE_SKU%>" var="state"/>
                                        <c:set value="<%=BrandesAuthUserModel.NO_AUTH%>" var="no"/>
                                        <c:set value="<%=BrandesAuthUserModel.CASH_AUTH%>" var="cash"/>
                                        <c:set value="<%=BrandesAuthUserModel.CREDIT_AUTH%>" var="credit"/>

                                        <c:set value="<%=DealerConstant.DealerShoper.PRODUCTTYPE_CASH%>"
                                               var="productType_cash"/>
                                        <c:set value="<%=DealerConstant.DealerShoper.PRODUCTTYPE_CREDIT%>"
                                               var="productType_credit"/>
                                        <ul class="inline-float">
                                            <c:forEach items="${pageResult.list}" var="item">
                                                <c:set value="${fns:getProductAuthPrice(item,state)}"
                                                       var="brandesAuthUserModel"></c:set>
                                                <li>
                                                    <div class="pic">
                                                        <a href="${ctx}/market/product/${item.refrenceId}?code=default"
                                                           title="${item.productTitle}" class="js-img-center"
                                                           target="_blank">
                                                            <img src="${res}${fns:getImageDomainPath(item.productImage,220 ,220)}"
                                                                 alt="${item.productTitle}"/>
                                                        </a>

                                                        <div class="operate">
                                                            <a class="operate-collect"
                                                               href="javascript:addFavor('${item.refrenceId}');"><i
                                                                    class="icon-collect"></i>收藏</a>
                                                            <c:if test="${brandesAuthUserModel.isAuth gt no}">
                                                                <a class="operate-jhds"
                                                                   href="javascript:joinShop('${item.refrenceId}','${brandesAuthUserModel.isAuth eq cash?productType_cash:productType_credit}');"><i
                                                                        class="icon-jhds"></i>加入进货单</a>
                                                            </c:if>
                                                            <c:if test="${brandesAuthUserModel.isAuth eq no}">
                                                                <a class="operate-jhds"
                                                                   href="javascript:applyBrandJoin('${item.refrenceId}','');"><i
                                                                        class="icon-jhds"></i>申请加盟</a>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                    <div class="detail">
                                                        <div class="price">
                                                            <c:choose>
                                                                <c:when test="${brandesAuthUserModel.isAuth eq no}">
                                                                    吊牌价：<em>￥</em>
                                                                    <strong><fmt:formatNumber
                                                                            value="${brandesAuthUserModel.productPrice}"
                                                                            pattern="0.00"/></strong>
                                                                </c:when>
                                                                <c:when test="${brandesAuthUserModel.isPoint}">
                                                                    返点价：<em>￥</em>
                                                                    <strong><fmt:formatNumber
                                                                            value="${brandesAuthUserModel.pointPrice}"
                                                                            pattern="0.00"/></strong>
                                                                    <i><fmt:formatNumber
                                                                            value="${brandesAuthUserModel.productPrice}"
                                                                            pattern="0.00"/></i>
                                                                </c:when>
                                                                <c:when test="${brandesAuthUserModel.isAuth eq cash}">
                                                                    直供价：<em>￥</em>
                                                                    <strong><fmt:formatNumber
                                                                            value="${brandesAuthUserModel.productDirPrice}"
                                                                            pattern="0.00"/></strong>
                                                                    <i><fmt:formatNumber
                                                                            value="${brandesAuthUserModel.productPrice}"
                                                                            pattern="0.00"/></i>
                                                                </c:when>
                                                                <c:when test="${brandesAuthUserModel.isAuth eq credit}">
                                                                    授信价：<em>￥</em>
                                                                    <strong><fmt:formatNumber
                                                                            value="${brandesAuthUserModel.productCreditPrice}"
                                                                            pattern="0.00"/></strong>
                                                                    <i><fmt:formatNumber
                                                                            value="${brandesAuthUserModel.productPrice}"
                                                                            pattern="0.00"/></i>
                                                                </c:when>
                                                            </c:choose>
                                                        </div>
                                                        <div class="title">
                                                            <a href="${ctx}/market/product/${item.refrenceId}?code=default">${item.productTitle}</a>
                                                        </div>
                                                        <div class="number">货号：${item.productNo}</div>
                                                        <div class="address clearfix">
                                                            <a class="fl" href="#"
                                                               title="${item.brandsName}">${item.brandsName}</a>
                                                            <a class="fr" href="javascript:;"
                                                               title="${item.provinceName}">${item.provinceName}</a>
                                                        </div>
                                                        <div class="clearfix">
                                                    <span class="fl">
                                                        <c:if test="${item.isSample eq true}">
                                                            <i class="icon-nayang" title="支持拿样"></i>
                                                        </c:if>
                                                        <c:if test="${item.isCredit eq true}">
                                                            <i class="icon-yuding ml5" title="我的授信商品"></i>
                                                        </c:if>
                                                    </span>
                                                        </div>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:otherwise>
                    </c:choose>

                        <%--  --%>
                    <div class="m-page">
                        <tags:page form="searchForm" page="${pageResult.page}"/>
                    </div>
                </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%--视频主持人 开始--%>
<%--<jsp:include page="${ctx}/market/brand/rightFlash">
    <jsp:param name="brandsId" value="${brandesId}"/>
</jsp:include>--%>
<%--视频主持人 结束 --%>
<%--footer--%>
<div class="hide">
    <div id="serviceCost">
        <div class="ui-head">
            <h3>提示</h3>
        </div>
        <div style="padding:20px" class="fs14 yahei lh2">
            抱歉~您还未支付平台服务费,暂时无法下单<br>
            马上支付<span class="c-o">1200元</span>服务费,享受一手货源!
        </div>
        <div class="ta-c">
            <a href="${ctx}/dealer/service/details?refrenceId=S001" target="_blank" class="ui-button ui-button-morange">立即前往支付</a>
            <a class="ui-button ui-button-mwhite js-cancel">取消</a>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/include/footer.jsp"/>

<form:form id="favoriteForm" action="" method="post">
    <input type="hidden" name="productId" id="productId"/>
</form:form>
<jsp:include page="/WEB-INF/view/common/join_order_tpl.jsp"></jsp:include>
<script src="${res}/scripts/jquery.min.js"></script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<%-- 此时未用到，暂时注释 <script src="${res}/scripts/market/jquery.elastislide.js" type="text/javascript"></script>--%>
<script src="${src}/fronts/market/last.js" type="text/javascript"></script>
<%-- 此时未用到，暂时注释 <script src="${res}/scripts/market/ZeroClipboard.min.js" type="text/javascript"></script>--%>
<script src="${res}/scripts/common.js"></script>
<%--<!-- 点击复制插件-->--%>
<script type="text/javascript">
    //处理价格
    $(".js_price,.js-price").isPrice();
    function productCollect(productId) {
        $("#productId").val(productId);
        $.post("/addFavorite", {productId: productId}, function (data) {
            if (data.code == 126000) {
                alert("产品收藏成功");
                remind("success", "产品收藏成功");
            } else {
                alert(data.message);
                remind("error", data.message);
            }
        }, "json");
    }

    /**直接加入后台购物车*/
    /* function joinShop(productId,productType) {

     var objdata ={
     "title": "提示"
     };
     //<!--nginx 请求转发：/dealer/dealerShoper/productAddCar-->
    $.ajax({
        url: "/addCar",
        method: "post",
        data: {productId: productId, productType: productType, skuIds: '', amounts: ''},
        dataType: "json",
        async: false,
        success: function (data) {
            if (data.code == 126000) {
                remind("success", "此产品已成功加入进货单!");
            } else if (data.code == 111048) {
                //尚未购买服务
                objdata.content = "抱歉~您还未支付平台服务费，暂时无法下单马上支付1200元服务费，享受一手货源";
                confirmDialog(objdata, function () {
                    window.open("${ctx}/dealer/service/details?refrenceId=S001");
                });
            } else if (data.code == 111040) {
                //已加入购物车
                remind("error", "已加入进货单，请勿重复添加");
            } else if (data.code == 126013) {
                //跳转到登录页
                location.href = "${ctx}/common/login?redirect=" + encodeURIComponent(location.href);
            } else {
                remind("error", data.message);
            }
        }
    });
    } */
    //
    <!--nginx 请求转发：/dealer/dealerFavorite/addFavorite-->
    function addFavor(productId, obj) {
        $.ajax({
            url: "/addFavorite",
            method: "post",
            data: {"productId": productId},
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.code == 126000) {
                    remind("success", "产品收藏成功", function () {
                        $(obj).html("已加入收藏夹");
                        $(obj).css("color", "green");
                    });
                } else if (data.code == 126013) {
                    //跳转到登录页
                    location.href = "${ctx}/common/login?redirect=" + encodeURIComponent(location.href);
                } else {
                    remind("error", data.message);
                }
            }
        });
    }
    //
    <!--nginx 请求转发：/dealer/dealerApply/dealer/joinManage/apply/join-->
    function applyBrandJoin(productId, brandsId) {
        $.ajax({
            url: "/addJoin",
            method: "POST",
            data: {productId: productId, inviteText: "申请加盟", brandsId: brandsId},
            dataType: "json",
            async: false,
            beforeSend: function (request) {
                request.setRequestHeader("Access-Control-Allow-Origin", "*");
            },
            success: function (data) {
                if (data.code == 126000) {
                    remind("success", "已提交加盟申请");
                } else if (data.code == 126013) {
                    location.href = "${ctx}/common/login?redirect=" + encodeURIComponent(location.href);
                } else {
                    remind("error", data.message);
                }
            }
        });
    }

    function parseURL(url) {
        var a = document.createElement('a');
        a.href = url;
        return {
            source: url,
            protocol: a.protocol.replace(':', ''),
            host: a.hostname,
            port: a.port,
            query: a.search,
            params: (function () {
                var ret = {},
                        seg = a.search.replace(/^\?/, '').split('&'),
                        len = seg.length, i = 0, s;
                for (; i < len; i++) {
                    if (!seg[i]) {
                        continue;
                    }
                    s = seg[i].split('=');
                    ret[s[0]] = s[1];
                }
                return ret;
            })(),
            file: (a.pathname.match(/\/([^\/?#]+)$/i) || [, ''])[1],
            hash: a.hash.replace('#', ''),
            path: a.pathname.replace(/^([^\/])/, '/$1'),
            relative: (a.href.match(/tps?:\/\/[^\/]+(.+)/) || [, ''])[1],
            segments: a.pathname.replace(/^\//, '').split('/')
        };
    }

    $(function () {
        setTimeout(function () {
            $.post('${ctx}/market/user_rtk', {
                id: '${brandesId}',
                code: 4
            });
        }, 2000);
        <%--
        // 定义一个新的复制对象
        var clip = new ZeroClipboard(document.getElementById("d_clip_button"), {
            moviePath: "${res}/images/market/zd.jpg"
        });
        // 复制内容到剪贴板成功后的操作
        clip.on('complete', function (client, args) {
            alert("复制成功，复制内容为：" + args.text);
        });
        --%>
        seajs.use(["carousel"], function (Carousel) {//产品展示图
            $(".ad-thumbs").each(function () {
                var _item = $(this).find(".ad-thumb-list li");
                var carousel = new Carousel({
                    "element": $(this),
                    "panels": _item,
                    "effect": "scrollx",
                    "step": 1,
                    "viewSize": [36],
                    "circular": false,
                    "autoplay": false,
                    hasTriggers: false,
                    prevBtn: $(this).parent().find(".ad-back"),
                    nextBtn: $(this).parent().find(".ad-forward")
                });
            });
            var _item = $(".ad-thumbs").find(".ad-thumb-list li");
            <%--//获取小图的src给大图--%>
            _item.click(function () {
                var _src = $(this).find("img").data("src");
                var _parents = $(this).parents(".box").find(".ad-image-wrapper");
                if (_src) {
                    var _html = '<img src=" ' + _src + ' " />';
                    var _width = $(this).find("img").width();
                    var _height = $(this).find("img").height();
                    _parents.html(_html);
                    imgCenter();
                }
            });
            <%--//让图片在一个容器里面垂直居中--%>
            function imgCenter() {
                $(".js_img_center,.js-img-center").each(function () {
                    $("<span></span>").appendTo($(this));
                });
            }

            imgCenter();
        });

        $(".m-list li").hover(function () {
            $(this).addClass("hover");
        }, function () {
            $(this).removeClass("hover");
        });
    });

    seajs.use(["dialog", "template", "setNumber"], function (Dialog, Template, SetNumber) {

        Template.helper('$formatPrice', function (price) {
            if (price == null) {
                return 0.00;
            } else {
                return price.toFixed(2);
            }
        });

        var joinOrderDialog = new Dialog({
            width: 500
        });

        /*加进货单方法作修改*/
        window.joinShop = function (productId, type) {
            /*发请求取得 sku 列表*/
            $.ajax({
                url: "/listSkuIds",
                method: "post",
                data: {productId: productId},
                dataType: "json",
                success: function (data) {
                    if (data.code == 126013) {
                        location.href = "${ctx}/common/login?redirect=" + encodeURIComponent(location.href);
                    } else if (data.code == 126000) {
                        var _html = Template.render("joinOrderTpl", data.object);
                        joinOrderDialog.set("content", _html).show();
                        joinOrderDialog.show();
                    } else {
                        remind("error", data.message);
                    }
                }
            });
        };

        /*确定*/
        $(document).on("click", ".set_comfirm", function () {
            var params = $("#joinOrderForm").serialize();
            //console.log(params);
            $.ajax({
                url: "/shoperAddCart",
                method: "post",
                data: params,
                dataType: "json",
                success: function (data) {
                    if (data.code == 126000) {
                        remind("success", "成功加入进货单");
                        joinOrderDialog.hide();
                    } else {
                        remind("error", data.message);
                    }
                }
            });
        });

        //计算现款价和授信价
        function countAllPrice() {
            var _xk = 0, _sx = 0, _fd = 0;

            $("#orderTableBody tr").each(function () {
                var sinXk = parseFloat($(this).find(".js-xk-price").text());
                var sinSx = parseFloat($(this).find(".js-sx-price").text());
                var sinFd = parseFloat($(this).find(".js-fd-price").text());
                var _num = parseInt($(this).find(".num-amount").val()) || 0;

                _xk += (sinXk * _num);
                _sx += (sinSx * _num);
                _fd += (sinFd * _num);
            });

            $("#xk_price").text(_xk.toFixed(2));
            $("#sx_price").text(_sx.toFixed(2));
            $("#fd_price").text(_fd.toFixed(2));
        }

        new SetNumber({
            callback: countAllPrice
        });

        window.displayMethod = function (type) {
            $("#displayMode").val(type);
            $("#searchForm").submit();
        }

        window.sortMethod = function (sort, order) {
            if (sort == 1) {
                $("#sort").val("");
            } else {
                $("#sort").val(sort);
            }
            $("#order").val(order);
            $("#searchForm").submit();
        }
    });
</script>
</body>
</html>
