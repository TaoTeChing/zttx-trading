<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>搜索结果</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="${res}/styles/search/index.css" rel="stylesheet"/>
    <%--
         <link rel="stylesheet" href="${res}/styles/market/prodisshow.css"/>
          <link rel="stylesheet" href="${res}/styles/market/brandviewbase.css"/>--%>
    <style>
        .classify {
            margin-top: -1px;
        }

        .classify .classify-ul li a:hover {
            text-decoration: none;
        }

        .classify .classify-ul li a.active {
            color: #ff8800;
            font-weight: normal;
        }

        .filter .filter-tag a {
            margin-left: -1px;
        }

        .filter .filter-tag a:hover, .filter .filter-tag .current {
            background: #fff;
            color: #d8271c !important;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <jsp:include page="/WEB-INF/view/common/component/header.jsp"/>
    <jsp:include page="/WEB-INF/view/common/component/nav.jsp">
        <jsp:param value="3" name="m"/>
    </jsp:include>


    <!--     <div class="ts-container">
            <div class="info-bread-nav" style="height: 30px;">
                <span>所有分类</span>
                <span style="font-family:'宋体';color: #999;">&gt;&gt;</span>
                <a href="/search/">女士内衣/男士内衣</a>
                <span style="font-family:'宋体';color: #999;">&gt;</span>
                <a href="/search/">女士内衣/男士内衣</a>
                <span style="font-family:'宋体';color: #999;">&gt;</span>
                <form class="nav-search-box"><input class="ui-input" value="{当前搜索关键字}"><button><img src="/images/market/clearance/isearch.png"></button></form>
            </div>
        </div> -->
    <div class="ts-container clearfix mb20">
        <div class="brand-col-l fl">
            <div class="classify classify-main clearfix">
                <div class="classify-tit fl">行业</div>
                <!-- 一级目录 -->
                <div class="classify-sel fl">
                    <ul class="classify-ul inline-block">
                        <li>
                            <a href="/common/dealer/productList?q="
                               class="${empty dealerProductListModel.dealMain?'active':''}">全部<i class="brandicons"></i></a>
                        </li>
                        <li>
                            <a href="/common/dealer/productList?dealMain=1&q="
                               class="${dealerProductListModel.dealMain==1?'active':''}">服装服饰<i class="brandicons"></i></a>
                        </li>
                        <li>
                            <a href="/common/dealer/productList?dealMain=2&q="
                               class="${dealerProductListModel.dealMain==2?'active':''}">鞋靴箱包<i class="brandicons"></i></a>
                        </li>
                        <li>
                            <a href="/common/dealer/productList?dealMain=3&q="
                               class="${dealerProductListModel.dealMain==3?'active':''}">家纺<i
                                    class="brandicons"></i></a>
                        </li>
                        <li>
                            <a href="/common/dealer/productList?dealMain=4&q="
                               class="${dealerProductListModel.dealMain==4?'active':''}">童装/母婴类<i
                                    class="brandicons"></i></a>
                        </li>
                    </ul>
                </div>
            </div>

            <!-- 二级目录 -->
            <c:if test="${not empty dealerProductListModel.dealMain}">
                <div class="classify classify-main clearfix">
                    <div class="classify-tit fl">分类</div>
                    <div class="classify-sel fl">
                        <ul class="classify-ul inline-block">
                            <c:forEach items="${dealDicList}" var="obj">
                                <li>
                                    <a href="/common/dealer/productList?dealNo=${obj.dictValue}&q=${dealerProductListModel.q}&dealMain=${dealerProductListModel.dealMain}"
                                       class="${obj.dictValue==dealerProductListModel.dealNo?'active':''}">${obj.dictValueName}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </c:if>

            <div class="filter clearfix mb10">
                <div class="fl">
                    <c:set var="descType" value="${dealerProductListModel.descType == 0 ? '1':'0'}"/>
                    <c:set var="url"
                           value="${ctx }/common/dealer/productList?q=${dealerProductListModel.q}&minPrice=${dealerProductListModel.minPrice}&maxPrice=${dealerProductListModel.maxPrice}&descType=${descType}&dealNo=${dealerProductListModel.dealNo}&dealMain=${dealerProductListModel.dealMain } "/>

                    <div class="filter-tag">
                        <!-- attrType 空：默认排序，0：销量，1：时间，2：点击数量，3：价格
                           descType 0：升序，1：降序
                           minPrice 最小价格
                           maxPrice 最大价格 -->

                        <a href="${url}" ${dealerProductListModel.attrType == null ? 'class="current"':"" } >默认排序 <span
                                class="icon-arr"><i></i><em></em></span></a>
                        <a href="${url}&attrType=0"  ${dealerProductListModel.attrType == 0 ? 'class="current"':"" }>销量 ${dealerProductListModel.attrType == 0 and dealerProductListModel.descType== 0 ? '↑':'↓'}<span
                                class="icon-arr"><i></i><em></em></span></a>
                        <a href="${url}&attrType=1"   ${dealerProductListModel.attrType == 1 ? 'class="current"':"" }>时间 ${dealerProductListModel.attrType == 1  and dealerProductListModel.descType == 0 ? '↑':'↓'}
                            <span class="icon-arr"><i></i><em></em></span></a>
                        <a href="${url}&attrType=2"  ${dealerProductListModel.attrType == 2 ? 'class="current"':"" }>点击数量 ${dealerProductListModel.attrType== 2 and dealerProductListModel.descType == 0 ? '↑':'↓'}
                            <span class="icon-arr"><i></i><em></em></span></a>
                        <a href="${url}&attrType=3"    ${dealerProductListModel.attrType == 3 ? 'class="current"':"" }>价格${dealerProductListModel.attrType == 3 and dealerProductListModel.descType == 0 ? '↑':'↓'}
                            <span class="icon-arr"><i></i><em></em></span></a>


                    </div>
                </div>
                <div class="fl ml20">
                    <div class="ts-input-filter">
                        <form>
                            <input class="input-price" name="minPrice"
                                   value="${dealerProductListModel.minPrice }"><b>-</b><input class="input-price"
                                                                                              name="maxPrice"
                                                                                              value="${dealerProductListModel.maxPrice }">
                            <button class="btn-search">筛选</button>
                        </form>
                    </div>
                </div>
            </div>
            <c:if test="${not empty paginateResult.list}">
                <form:form id="dealerShopForm" method="get">
                    <input type="hidden" name="dealMain" value="${dealerProductListModel.dealMain}">
                    <input type="hidden" name="dealNo" value="${dealerProductListModel.dealNo}">
                    <input type="hidden" name="q" value="${dealerProductListModel.q}">
                    <input type="hidden" name="minPrice" value="${dealerProductListModel.minPrice}">
                    <input type="hidden" name="maxPrice" value="${dealerProductListModel.maxPrice}">
                    <input type="hidden" name="descType" value="${dealerProductListModel.descType}">
                    <input type="hidden" name="attrType" value="${dealerProductListModel.attrType}">
                    <ul class="order-grid">
                        <c:forEach items="${paginateResult.list}" var="brands" varStatus="status">

                            <li class="goods-sale">
                                <div class="list-item clearfix">
                                    <a class="list-item-pic js-img-center" href="${ctx}/market/product/${brands.id}"
                                       title="${brands.productTitle}" target="_blank">
                                        <img src="${res}${fns:getImageDomainPath(brands.productImage,440,440)}"
                                             width="220" height="220"><!-- 440X440 -->
                                        <div class="list-item-mask">

                                            <div class="list-item-logo js-img-center">
                                                <img alt="${brands.brandsName}"
                                                     src="${res }${brands.brandLogo}"> <%--//如果显示品牌商名 需要前端控制--%>
                                            </div>

                                        </div>
                                        <c:if test="${brands.priceMap.isActivity==1}">
                                            <div class="goods-sale-img">
                                                <img src="/images/search/search-sale.png">
                                            </div>
                                        </c:if>
                                    </a>

                                    <div class="pic-nav">
                                        <div class="pic-thumbs">
                                            <ul class="pic-thumb-list">

                                                <c:forEach items="${brands.images}" var="imageName"  begin="0" end="4">
                                                    <li>
                                                            <%--<!-- 160X160,40X40 -->--%>
                                                            <img  src="${res}${fns:getImageDomainPath(imageName,160,160)}" data-src="${res}${imageName}" class="image0">
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="info">
                                        <p class="clearfix">
                                            <c:choose>
                                                <c:when test="${brands.priceMap!=null||brands.priceMap.isAuth==1}">
                                                    <label>采购价：</label>
                                                    <span class="text-yahei text-red" style="font-size: 18px;"><em
                                                            class="text-md">￥</em><fmt:formatNumber
                                                            pattern="0.00">${brands.priceMap.price}</fmt:formatNumber></span>
                                                    <del class="text-yahei">${brands.productPrice}</del>
                                                </c:when>
                                                <c:otherwise>
                                                    <label>吊牌价：</label>
                                                    <span class="text-yahei text-red" style="font-size: 18px;"><em
                                                            class="text-md">￥</em>${brands.productPrice}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                        <p class="clearfix">
                                            <label>所属品牌：</label>
                                            <span class="text-bold text-grey">${brands.brandsName}</span>
                                        </p>

                                        <p class="clearfix">
                                            <label>所在地：</label>
                                            <span>${brands.provinceName }.${brands.cityName}</span>
                                        </p>
                                    </div>

                                    <div class="operate">

                                        <c:if test="${not isBrand}">

                                            <c:choose>
                                                <c:when test="${brands.priceMap != null}">
                                                    <a href="javascript:joinShop('${brands.id}')"
                                                       class="ts-button btn-addcart">加入进货单</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="${ctx}/market/product/${brands.id}" target="_blank"
                                                       class="ts-button btn-query">我要询价</a>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:if test="${!brands.tag}">
                                                <a href="javascript:;" class="ts-button btn-favorites"
                                                   onclick="javascript:addFavor('${brands.id}',this)">加入收藏夹</a>
                                            </c:if>
                                            <c:if test="${brands.tag}">
                                                <a href="javascript:;" class="ts-button btn-favorites "
                                                   style="color: green"
                                                   onclick="javascript:addFavor('${brands.id}',this)">已加入收藏夹</a>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${isBrand }">
                                            <a href="${ctx}/market/product/${brands.id}" target="_blank"
                                               class="ts-button btn-query btn-disabled">我要询价</a>
                                            <a href="javascript:;" class="ts-button btn-favorites btn-disabled"
                                               onclick="javascript:addFavor('${brands.id}',this)">加入收藏夹</a>
                                        </c:if>
                                        <!-- <a href="javascript:;" class="ts-button btn-favorites">加入收藏夹</a> -->
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                    <tags:page form="dealerShopForm" page="${paginateResult.page}"/>
                </form:form>
            </c:if>

            <c:if test="${empty paginateResult.list}">

                <div class="no-result">
                    <div class="n-r-say">
                        <p>抱歉！我们花了九牛二虎之力还是没找到您要找的</p>

                        <p style="font-size: 14px;"><span>${dealerProductListModel.q}</span>搜索结果</p>
                    </div>
                    <!-- <div class="n-r-recomed">
                        <span>你还可能喜欢：</span><a href="#">饰品衣服</a> <a href="#"> 2014衣服</a> <a href="#">收纳衣服</a> <a href="#">女装衣服</a> <a href="#">2013衣服</a> <a href="#">配饰衣</a> <a href="#">服秋冬衣服</a>
                    </div> -->
                    <div class="n-r-search">
                        <form action="${ctx}/common/dealer/productList" method="get">
                            <input type="text" name="q" class="fl n-r-text" placeholder="换个关键词试试呗">
                            <input type="submit" value="" class="fl n-r-btn">
                        </form>
                    </div>
                </div>
            </c:if>


            <!-- 品牌推荐模块 -->
            <div class="hr-dashed mt20 mb20"></div>

            <h3 class="text-md text-yahei mb10">品牌推荐</h3>

            <ul class="brand-grid">
                <c:set value="${fns:getBrandesInfo(20, 9)}" var="brandsList"></c:set>
                <c:if test="${ brandsList != null }">

                    <c:forEach items="${brandsList}" var="brandes" varStatus="status" begin="0" end="3">
                        <c:set value="${fns:getBrandsIdSubDomain(brandes.refrenceId)}" var="domain"></c:set>
                        <c:set value="${fns:getImageDomainPath(brandes.image, 440, 440)}" var="brandesImage"></c:set>
                        <li>

                            <div class="list-item clearfix">
                                <a class="list-item-pic js-img-center" href="http://${domain}${zttx}"
                                   title="${brandes.brandName }" target="_blank">
                                    <img src="${res}${brandes.image}">
                                </a>
                                <h4>${brandes.brandName }</h4>

                                <div class="list-item-logo">
                                    <a class="js-img-center" href="http://${domain}${zttx}" target="_blank">
                                        <img alt="${brandes.brandName }" src="${res}${brandes.brandLogo}">
                                    </a>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
                <%-- <c:if test="${status.index==0 }">
                    <div class="show-hd js-img-center" style="width:299px; height:299px;"><a href="http://${domain}${zttx}" target="_blank" title="${brandes.brandName }">
                     <c:choose>
                         <c:when test="${empty brandes.image}">
                         <img data-logo="${res}${brandes.brandLogo}" src="${res}${brandes.proLogo}" data-pic="${res}${brandes.proLogo}" alt="" />
                         </c:when>
                         <c:otherwise>
                         <img data-logo="${res}${brandes.brandLogo}" src="${res}${brandesImage}" data-pic="${res}${brandes.image}" alt="" />
                         </c:otherwise>
                     </c:choose>
                     </a></div>
                </c:if>
                <c:if test="${status.index>0&&status.index<7 }">
                    <div class="js-img-center" style="width:149px; height:149px;"><a href="http://${domain}${zttx}" target="_blank" title="${brandes.brandName }" >
                    <c:choose>
                         <c:when test="${empty brandes.image}">
                         <img data-logo="${res}${brandes.brandLogo}" src="${res}${brandes.proLogo}" data-pic="${res}${brandes.proLogo}" alt="" />
                         </c:when>
                         <c:otherwise>
                         <img data-logo="${res}${brandes.brandLogo}" src="${res}${brandesImage}" data-pic="${res}${brandes.image}" alt="" />
                         </c:otherwise>
                     </c:choose>
                    </a></div>
                </c:if>
            </c:forEach> --%>


            </ul>
        </div>

        <!-- 入驻模块 -->
        <div class="brand-col-r fr">
            <ul>
                <li>
                    <div class="imbrand-list">
                        <div class="head">
                            <h3><i class="icon icon-brand"></i> 我是品牌商</h3>

                            <p>全国优质终端商E网打尽</p>
                        </div>
                        <div class="content">
                            <p>筹备入驻品牌：<span
                                    class="text-orange text-yahei text-md">${fns:getWebInfoCount("CBRZPP")}</span> 家</p>

                            <p>达成合作意向：<span
                                    class="text-orange text-yahei text-md">${fns:getWebInfoCount("DCJMGX")}</span> 次</p>
                        </div>
                        <a class="btn-apply" href="${ctx }/common/register?t=1" target="_blank">品牌商入驻</a>
                    </div>
                </li>
                <li>
                    <div class="imdealer-list">
                        <div class="head">
                            <h3><i class="icon icon-dealer"></i> 我是终端商</h3>

                            <p>一手价格一手货源，最低成本渠道快建</p>
                        </div>
                        <div class="content">
                            <p>入驻店铺：<span class="text-orange text-yahei text-md">${fns:getWebInfoCount("RZZDS")}</span>
                                家</p>

                            <p>累计节约：<span class="text-orange text-yahei text-md">${fns:getWebInfoCount("JSHK")}</span> 元
                            </p>
                        </div>
                        <a class="btn-apply" href="${ctx }/common/register" target="_blank">终端商加盟</a>
                    </div>
                </li>
            </ul>

            <!-- 广告位1 -->
            <div class="gg-230x110 mt10">
                <c:set value="${fns:getAdvertPosit(product_list_1)}" var="posit"></c:set>
                <c:if test="${posit != null && posit.viewNum > 0}">
                    <c:forEach items="${posit.subList}" var="list" begin="0" end="${posit.viewNum}">
                        <a href="${ctx}${list.urlAddress}" target="_blank">
                            <img src="${res}${list.adLogo}" width="${posit.imgWidth}" height="${posit.imgHeight}">
                        </a>
                    </c:forEach>
                </c:if>
            </div>

            <!-- <div class="gg-230x110 mt10">
                <a href="#" target="_blank">
                    <img src="" width="230" height="110">
                </a>
            </div>
            <div class="explosion-col mt10">
             -->


            <%--             <div class="explosion-col-content">
                            <div class="real-time-area" style="height: 534px; overflow: hidden;">  <!-- real-time-area -->
                            <c:set value="${fns:getTradeMeetRtkList(10, 12) }" var="meetRtkList"></c:set>
                            <c:if test="${!empty meetRtkList}">
                                <div class="real-time-con">
                                <c:forEach items="${meetRtkList}" var="meetRtk">
                                <c:set value="${fns:getImageDomainPath(meetRtk.productImg,160,160)}" var="productImg"></c:set>
                                    <dl class="real-time">
                                        <a href="${ctx}/market/product/${meetRtk.productId}" target="_blank"><dt class="pic"><img src="${res}${productImg}" alt="" width="70" height="70"/></dt></a>
                                        <dd class="title">${meetRtk.realName}</dd>
                                        <dd class="des"><a href="${ctx}/market/product/${meetRtk.productId}" title="${meetRtk.actionMark}" target="_blank">${fns:trimLongStringWithEllipsis(meetRtk.actionMark, 20, '...') }</a></dd>
                                    </dl>
                                </c:forEach>
                                </div>
                            </c:if>
                            </div>
                        </div> --%>


            <div class="explosion-col-head">
                <h3>实时动态</h3>
            </div>
            <div class="explosion-col-content">
                <div class="real-time-area" style="height: 534px; overflow: hidden;">
                    <div class="real-time-con">
                        <c:set value="${fns:getTradeMeetRtkList(10, 12) }" var="meetRtkList"></c:set>
                        <c:if test="${!empty meetRtkList}">
                            <c:forEach items="${meetRtkList}" var="meetRtk">
                                <c:set value="${fns:getImageDomainPath(meetRtk.productImg,160,160)}"
                                       var="productImg"></c:set>
                                <dl class="real-time">
                                    <a href="${ctx}/market/product/${meetRtk.productId}" target="_blank">
                                        <dt class="pic"><img src="${res}${productImg }" alt="" width="70" height="70"/>
                                        </dt>
                                    </a>
                                    <dd class="title">${meetRtk.realName}</dd>
                                    <dd class="des"><a href="${ctx}/market/product/${meetRtk.productId}"
                                                       title="${meetRtk.actionMark}"
                                                       target="_blank">${fns:trimLongStringWithEllipsis(meetRtk.actionMark, 20, '...') }</a>
                                    </dd>
                                </dl>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>


        <!--广告位2 -->

        <div class="gg-230x110 mt10">
            <c:set value="${fns:getAdvertPosit(product_list_right_2)}" var="posit"></c:set>
            <c:if test="${posit != null && posit.viewNum > 0}">
                <c:forEach items="${posit.subList}" var="list" begin="0" end="${posit.viewNum}">
                    <a href="${ctx}${list.urlAddress}" target="_blank">
                        <img src="${res}${list.adLogo}" width="${posit.imgWidth}" height="${posit.imgHeight}">
                    </a>
                </c:forEach>
            </c:if>
        </div>

    </div>
</div>


<jsp:include page="/WEB-INF/view/common/component/footer.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/component/bottom.jsp"></jsp:include>


<script src="/scripts/jquery.min.js"></script>
<script src="/scripts/seajs/seajs/2.1.1/sea.js" type="text/javascript"></script>
<script src="/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script src="/scripts/seajs_config.js" type="text/javascript"></script>
<script src="/scripts/common.js"></script>
<script src="/scripts/common/base-init.js"></script>
<script src="${res}/scripts/market/last.js" type="text/javascript"></script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>


<%--  <jsp:include page="/WEB-INF/view/product/enquiry_dialog.jsp"></jsp:include> 
 <jsp:include page="/WEB-INF/view/product/enquiry_script.jsp"></jsp:include> --%>

<script>
    $.realMoveFn = function (configs) {
        //parentID,childID,_len,pad,time,styles
        //styles加入css3样式用到
        var len = $(configs.childID).length;
        var hei = $(configs.childID).height() + configs.pad;//pad为 padding 和 margin
        var timer = null;
        var moveTime = configs.time || 6000;

        if (len > configs._len) {
            function realMove() {
                timer = setInterval(function () {
                    $(configs.parentID).stop().animate({
                        marginTop: hei
                    }, function () {

                        //if(configs.styles){
                        //    $(configs.childID).addClass("animated").last().prependTo($(configs.parentID)).addClass(configs.styles);
                        //}else{
                        $(configs.childID).last().css("opacity", "0").prependTo($(configs.parentID)).animate({"opacity": "1"});
                        //}


                        $(configs.parentID).css({
                            marginTop: 0
                        });
                    });
                }, moveTime);
            }

            realMove();

            $(configs.parentID).hover(function () {
                clearInterval(timer);
            }, function () {
                realMove();
            });
        }
    };
    $.realMoveFn({
        parentID: ".real-time-con", childID: ".real-time", _len: 6, pad: 20
    });


    function joinShop(productId, status) {
        var objdata = new Object();
        objdata.title = "提示";
        $.post(
                "/dealer/shoper/purchase/"
                + productId
                + "/default",
                function (data) {
                    if (data.code == 126000) {
                        remind("success", "此产品已成功加入进货单");
                        var item = {};
                        item.productId = productId;
                        item.image = $('a.jqzoom img').attr('src');
                        item.title = $('h1.title ').text();
                        item.price = $('span.pro-dt-nomber').text();
                        item.cartId = data.object;
                        cart.add(item);
                        if (status) {
                            location.href = '/dealer/shopper';
                        }

                    } else if (data.code == 111048) {
                        //尚未购买服务
                        objdata.content = "抱歉~您还未支付平台服务费，暂时无法下单马上支付1200元服务费，享受一手货源";
                        confirmDialog(
                                objdata,
                                function () {
                                    window
                                            .open("/dealer/service/details?refrenceId=S001");
                                });
                    } else if (data.code == 111040) {
                        //已加入购物车
                        if (status == null) {
                            remind("error", "已加入进货单，请勿重复添加");
                        } else {
                            location.href = '/dealer/shopper';
                        }
                    } else {
                        remind("error", data.message);
                        //remind("error","此产品已在进货单,无需重复加入");
                    }
                }, "json");
    }


    //加入收藏夹
    function addFavor(productId, obj) {
        $.post("/dealer/favorite/save", {"productId": productId}, function (data) {
            if (data.code == 126000) {
                remind("success", "产品收藏成功", function () {
                    $(obj).html("已加入收藏夹");
                    $(obj).css("color", "green");
                });
            } else {
                remind("error", data.message);
            }
        }, "json");
    }


</script>
</body>
</html>
