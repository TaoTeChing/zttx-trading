<%@ page import="com.zttx.web.consts.DealerConstant" %>
<%@ page import="com.zttx.web.module.brand.model.BrandesAuthUserModel" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心 &gt;&gt; 我的授权产品库</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/purchases.css" rel="stylesheet" />
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
    <div class="em100">
        <div class="main clearfix">
            <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
            <div class="main-right">
                <jsp:include page="/WEB-INF/view/dealer/agency_header_message.jsp" />
                <div class="main-grid mb10">
                    <div class="panel-crumbs">
                        <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> &gt;&gt;
                        <span class="bb">我的授权产品库</span>
                        <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <div class="inner">
                    <div class="main-grid mb20 clearfix">
                        <div class="panel-tablink clearfix">
                            <ul class="clearfix">
                                <li><a class="bgc-rd c-w nl first" href="${ctx}/dealer/dealerShoper/cart"  target="_blank"><i class="icon i-cart"></i>我的进货单</a></li>
                                <li><a href="${ctx}/dealer/dealerShoper/grant" class="on">我的授权产品库</a></li>
                                <li><a href="${ctx}/dealer/dealerShoper/orderc">我的常进款式</a></li>
                                <li><a href="${ctx}/dealer/dealerShoper/browseHistroy">我的浏览记录</a></li>
                                <li><a href="${ctx}/dealer/dealerFavorite/productFavorite">我的产品收藏夹</a></li>
                            </ul>
                        </div>
                        <div class="panel-tbar clearfix">
                            <div class="search-bar">
                                <div class="search-box">
                                    <form:form id="filter-form" action="${ctx}/dealer/dealerShoper/grant" onsubmit="return false;">

                                        <ul class="searchul">
                                           <%-- <li>
                                                <label>快速下单:</label><input type="text" id="key" autocomplete="off"  data-complete="#" class="ui-input ml10 i-search keyword" placeholder="可以直接输入货号快速下单" value=""/>
                                            </li>--%>
                                            <li class="select_li">
                                                <select class="ui-select js-select" data-height="30" name="brandsId" id="sel-brands">
                                                    <option value="" >全部品牌</option>
                                                    <c:forEach items="${brandsCataList}" var="brands">
                                                        <option value="${brands.brandsId}"  ${brandsId == brands.brandsId ? 'selected="selected"':''}  >${brands.brandsName}</option>
                                                    </c:forEach>
                                                </select>
                                            </li>
                                            <li class="select_li">
                                                <select class="ui-select js-select" name="sort" id="sel-sort">
                                                    <c:forEach items="${sortTypeList}" var="sortType">
                                                        <option value="${sortType.dictValue}" >${sortType.dictValueName}</option>
                                                    </c:forEach>
                                                </select>
                                            </li>
                                            <li class="select_li">
                                               货号：<input type="text" class="ui-input" name="productNo"  style="padding-left:5px;background:none;width: 280px;">
                                            </li>
                                            <li>
                                                <input type="button" class="ui-button ui-button-lwhite yahei fs14 ml10" value="搜 索" onclick="g_pagination.goTo(1);"/>
                                            </li>
                                        </ul>
                                    </form:form>
                                </div>
                                <div class="search-result hide"></div>
                            </div>
                            <div class="order-bar">
                                <ul>
                                    <li class="on" data-order="grid"><a class="btn i-grid"></a></li>
                                    <li data-order="list"><a class="btn i-list"></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="main-grid clearfix">
                        <div class="panel-order panel-grid" id="product-item">
                        </div>
                    </div>
                    <div id="pagination" class="pagination mb10"></div>
                    <div class="main-grid ">
                       <%-- <div class="panel-btns">
                            <input id="btn-addlist" class="ui-button ui-button-lred fs16 msyh" type="button" style="cursor: pointer; width: 200px" value="加入进货单" />
                        </div>--%>
                        <form:form id="addlist-form" method="post" action="${ctx}/dealer/dealerShoper/addCart">
                        </form:form>
                        <form:form id="addlist-form-quick" method="post" action="${ctx}/dealer/dealerShoper/addCart">
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp" />
</div>

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
            <a href="${ctx}/dealer/service/details?refrenceId=S001" target="_blank" class="ui-button ui-button-morange">立即前往支付</a> <a class="ui-button ui-button-morange js-cancel">取消</a>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
<script id="category-template" type="text/html">
    <option value="" selected="selected">全部类别</option>
    {{each rows}}
    {{if $value!=null}}
    <option value="{{$value.dealNo}}">{{$value.dealName}}</option>
    {{/if}}
    {{/each}}
</script>
<script id="products-template" type="text/html">
    <ul class="product-item">
        {{each rows}}
        <li class="pr {{if $value.productCate==2}}preordered{{/if}} {{$index%4==0?'np':''}}" >
            <dl>
                <dt class="item-pic js-img-center {{if $value.type == 0}}illegal{{else if $value.type == 1}}down{{else if $value.type == 2}}illegal{{/if}}">
                    <!--图片-->
                    <img src="${res}{{$getImageDomainPathFn $value.productImage 220 220}}"/>
                    <%--<a class="btn-select {{if $value.type == 0 || $value.type == 1 || $value.type == 2}}cantuse{{/if}}" data-product-id="{{$value.productId}}" data-product-type="{{if isCredit}}1{{else}}0{{/if}}" href="javascript:;"></a>--%>
                    <a class="btn-joinorder {{if $value.type == 0 || $value.type == 1 || $value.type == 2}}cantuse{{/if}}" data-product-id="{{$value.productId}}" data-product-type="{{if isCredit}}1{{else}}0{{/if}}" href="javascript:;">加入进货单</a>
                    <%--<i></i>--%>
                </dt>

                <dd class="item-des" title="{{$value.productTitle}}" style="text-align: center;">
                    <a href="${ctx}/market/product/{{$value.productId}}" target="_blank" class="link">{{$handelBate $value.productTitle}}</a>
                </dd>
                <dd class="item-brand">
                <span>{{$value.brandsName}}</span>
                 </dd>
                <dd class="item-price">
                    {{if $value.type===5}}
                    返点价:<i>￥</i><span>{{$formatPrice $value.point}}</span>
                    {{else if $value.type===4}}
                    授信价:<i>￥</i><span>{{$formatPrice $value.credit}}</span>
                    {{else if $value.type===3}}
                    直供价:<i>￥</i><span>{{$formatPrice $value.cash}}</span>
                    {{/if}}
                    <em>{{$formatPrice $value.price}}</em>
                </dd>
                <dd class="item-name">
                    <h3 class="fn-text-overflow" title="{{$value.productNo}}"><a href="${ctx}/market/product/{{$value.productId}}" target="_blank" class="link">货号:{{$value.productNo}}</a></h3>
                </dd>
                <dd class="item-quantity">
                    <span>销量{{$formatNumber $value.saleNum}}件</span>
                </dd>
            </dl>
        </li>
        {{/each}}
    </ul>
</script>
<jsp:include page="/WEB-INF/view/common/join_order_tpl.jsp"></jsp:include>
<script>
    seajs.use(["${jsrel}/dealer/purchases"], function(Purchase){
        Purchase.init({
            url: "${ctx}/dealer/dealerShoper/grant/data"
        });
    });
</script>
</body>
</html>