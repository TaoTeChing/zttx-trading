<%@ page import="com.zttx.web.module.brand.model.BrandesAuthUserModel" %>
<%@ page import="com.zttx.web.consts.DealerConstant" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>产品搜索 - 8637品牌超级代理</title>
    <meta name="keywords" content="8637,品牌超级代理,品牌招商,招商加盟，天下商邦"/>
    <meta name="description" content="“8637品牌超级代理”以B2B2C（从品牌商到终端店铺再到消费者）的模式构建零中间商的招商加盟直供平台。
    利用互联网技术服务品牌商及终端商，改变传统品牌代理供应链（专卖店、批发商、代理商、品牌商），通过互联网直连方式招商加盟，减少中间供应环节，
    打造从厂家到店铺的较短供应链。"/>
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="${res}/styles/fronts/index/index.css" rel="stylesheet"/>
    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/seajs_config.js"></script>
    <script src="${res}/scripts/common.js"></script>
    <style>
        body, .main {
            background: #fff;
        }

        button, input, select, textarea, .main {
            font-family: Arial, "宋体";
        }

        .nav .ts-container {
            border-bottom: 2px solid #ed0100;
        }
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <jsp:include page="/WEB-INF/view/include/search.jsp">
        <jsp:param value="${filter.q}" name="q"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/view/include/nav.jsp">
        <jsp:param value="${nav}" name="m"/>
        <jsp:param value="${filter.st}" name="st"/>
    </jsp:include>
    <div class="main ts-container">
        <div class="m-bread clearfix">
            <a href="${ctx}/" class="m-b-1">首页</a>
            <c:choose>
                <c:when test="${null !=showName}">
                    <span class="m-b-2">&gt;</span><a href="" class="m-b-1">${showName}</a>
                </c:when>
                <c:otherwise>
                    <span class="m-b-2">&gt;</span><a href="" class="m-b-1">全部分类</a>
                </c:otherwise>
            </c:choose>
            <%-- 搜索标签 --%>
            <c:if test="${not empty filter}">
                <c:if test="${not empty filter.q}">
                    <span class="m-b-2">&gt;</span>
                    <a href="javascript:;" class="m-b-3 remove_q">${filter.q}<i class="icon-del"></i></a>
                </c:if>
                <c:if test="${not empty filter.brandsId}">
                    <span class="m-b-2">&gt;</span>
                    <a href="javascript:;" class="m-b-3 remove_brandsId">
                        <c:forEach items="${brandesList}" var="item" varStatus="status">${item.brandsName}
                            <c:if test="${!status.last}"> &nbsp;</c:if> </c:forEach><i class="icon-del"></i></a>
                </c:if>
                <c:if test="${not empty filter.dealId}">
                    <span class="m-b-2">&gt;</span>
                    <a href="javascript:;" class="m-b-3 remove_dealId">${dealDic.dealName}<i class="icon-del"></i></a>
                </c:if>

            </c:if>
            <a href="javascript:;" class="m-b-4 js-slide-filter">收起筛选<i class="icon-close"></i></a>
        </div>
        <c:set var="brandesList" value="${result.facets['brandesList']}"/>
        <c:if test="${null != brandesList}">
            <div class="m-brand clearfix">
                <div class="tit fl">选择品牌：</div>
                <div class="list fl" id="brand_list">
                    <div style="display: none;" id="brand_search">
                        <form action="">
                            <input type="text" placeholder="搜索品牌名称"/>
                        </form>
                    </div>
                    <div class="clearfix" id="selected_brand" style="height: 52px;margin: 5px 0;overflow: hidden;position:relative;">
                        <ul class="inline-float">
                            <c:forEach items="${brandesList}" var="item" varStatus="status">
                                <li>

                                    <c:choose>
                                        <c:when test="${status.index < 9}">
                                            <a href="" title="${item.brandsName}" class="js-img-center"
                                               data-cache="${item.refrenceId}">
                                                <img src="${res}${fns:getImageDomainPath(item.brandLogo,150 ,75)}"
                                                     alt="${item.brandsName}" title="${item.brandsName}">
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="" title="${item.brandsName}" class="img-text"
                                               data-cache="${item.refrenceId}">${item.brandsName}</a>
                                        </c:otherwise>
                                    </c:choose>

                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div style="display: none;" id="brand_btns">
                        <a class="brand_btns_confirm disabled" href="javascript:;">确定</a>
                        <a class="brand_btns_cancel" href="javascript:;">取消</a>
                    </div>
                </div>
                <c:if test="${null == filter.brandsId || '' == filter.brandsId}">
                    <div class="more fr ta-r">
                        <a href="javascript:;" id="select_duo">多选</a>

                        <span id="more1"><a  href="javascript:;" id="select_more">更多<i class="icon-close-down"></i></a></span>
                    </div>
                </c:if>
            </div>
        </c:if>
        <c:set var="dealDicList" value="${result.facets['dealDicList']}"/>
        <c:if test="${null != dealDicList}">
            <div class="m-cates mt10">
                <div class="m-mod mod-height clearfix" id="selected_cate">
                    <div class="tit fl">
                        <c:choose>
                            <c:when test="${null !=showName}">
                                <span>${showName}</span>
                            </c:when>
                            <c:otherwise>
                                <span>全部</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="list fl">
                        <c:forEach items="${dealDicList}" var="item">
                            <a href="" data-cache="${item.dealNo}"> ${item.dealName}</a>
                        </c:forEach>
                    </div>
                    <c:if test="${null == filter.dealId || '' == filter.dealId}">
                        <div id="more2" class="more fr ta-r">
                            <a class="js-cates-more" href="javascript:; ">更多<i class="icon-close-down"></i></a>
                        </div>
                    </c:if>
                </div>
            </div>
        </c:if>
        <div class="m-filter clearfix">
            <ul class="inline-float fl">
                <li class="m-f-1 ${filter.sorts == null || filter.sorts == "defaut" ? "current" : ""}">
                    <a href="javascript:;" title="综合排序" data-type="defaut">
                        综合排序
                    </a>
                </li>
                <li class="m-f-1 ${filter.sorts == "popular" ? "current" : ""}">
                    <a href="javascript:;" title="人气从高到低" data-type="popular">
                        人气从高到低
                    </a>
                </li>
                <li class="m-f-1 ${filter.sorts == "saleNum" ? "current" : ""}">
                    <a href="javascript:;" title="销量从高到低" data-type="saleNum">
                        销量从高到低
                    </a>
                </li>
                <li class="m-f-1 ${filter.sorts == "date" ? "current" : ""}">
                    <a href="javascript:;" title="时间从近到远" data-type="date">
                        时间从近到远
                    </a>
                </li>
                <%--<li class="m-f-1" id="sort_price">
                    <a href="javascript:;" data-type="price">价格</a>

                    <div id="sort_price_pop" style="display: none;">
                        <a href="javascript:;" title="价格从高到低" data-type="desc">价格从高到低</a>
                        <a href="javascript:;" title="价格从低到高" data-type="asc">价格从低到高</a>
                    </div>
                </li>--%>
            </ul>
        </div>
        <div class="m-recom clearfix">
            <div class="fl">
                <label>
                    <input type="checkbox" ${filter.recom == true ? "checked" : ""} data-type="0"/>
                    <span>推荐产品</span>
                </label>
                <label>
                    <input type="checkbox" ${filter.samples == true ? "checked" : ""} data-type="1"/>
                    <span>支持拿样</span><i class="icon-nayang"></i>
                </label>
                <label>
                    <input type="checkbox" ${filter.credit == true ? "checked" : ""} data-type="3"/>
                    <span>授信商品</span><i class="icon-yuding"></i>
                </label>
                <label>
                    <input type="checkbox" ${filter.point == true ? "checked" : ""} data-type="4"/>
                    <span>返点商品</span><i class="icon-fd"></i>
                </label>
            </div>
            <div class="fr" id="change_st">
                <a href="javascript:;" data-type="b"><i class="icon-g-brand"></i>找品牌商</a>
                <a class="active" href="javascript:;" data-type="p"><i class="icon-g-goods"></i>商品</a>
            </div>
        </div>
        <form id="searchForm" action="${filter.url}" method="get">
            <input type="hidden" id="searchFormQ" name="q" value="${filter.q != null ? filter.q : ""}"/>
            <input type="hidden" name="st" value="${filter.st != null ? filter.st : ""}"/>
            <input type="hidden" name="brandsId" value="${filter.brandsId != null ? filter.brandsId : ""}"/>
            <input type="hidden" name="mainId" value="${filter.mainId != null ? filter.mainId : ""}" id="mainId4AutoComplete"/>
            <input type="hidden" name="dealId" value="${filter.dealId != null ? filter.dealId : ""}"/>
            <input type="hidden" name="skuColor" value="${filter.skuColor != null ? filter.skuColor : ""}"/>
            <input type="hidden" name="skuSize" value="${filter.skuSize != null ? filter.skuSize : ""}"/>
            <input type="hidden" name="otherAttr" value="${filter.otherAttr != null ? filter.otherAttr : ""}"/>
            <input type="hidden" name="sorts" value="${filter.sorts != null ? filter.sorts : ""}"/>
            <input type="hidden" name="orderBy" value="${filter.orderBy != null ? filter.orderBy : "asc"}"/>
            <input type="hidden" name="recom" value="${filter.recom != null ? filter.recom : "false"}"/>
            <input type="hidden" name="samples" value="${filter.samples != null ? filter.samples : "false"}"/>
            <input type="hidden" name="authorize" value="${filter.authorize != null ? filter.authorize : "false"}"/>
            <input type="hidden" name="credit" value="${filter.credit != null ? filter.credit : "false"}"/>
			<input type="hidden" name="point" value="${filter.point != null ? filter.point : "false"}"/>
			
            <div class="m-list">
                <c:choose>
                    <c:when test="${not empty result.list}">
                        <c:set value="<%=BrandesAuthUserModel.NINCLUDE_SKU%>" var="state"/>
                        <c:set value="<%=BrandesAuthUserModel.NO_AUTH%>" var="no"/>
                        <c:set value="<%=BrandesAuthUserModel.CASH_AUTH%>" var="cash"/>
                        <c:set value="<%=BrandesAuthUserModel.CREDIT_AUTH%>" var="credit"/>
                        <c:set value="<%=DealerConstant.DealerShoper.PRODUCTTYPE_CASH%>" var="productType_cash"/>
                        <c:set value="<%=DealerConstant.DealerShoper.PRODUCTTYPE_CREDIT%>" var="productType_credit"/>
                        <ul class="inline-float" id="product_list">
                            <c:forEach items="${result.list}" var="item">
                                <%--<c:set value="${fns:getProductAuthPrice(item,state)}" var="brandesAuthUserModel"></c:set>--%>
                                <li data-refrenceid="${item.refrenceId}">
                                    <div class="pic">
                                        <a href="${ctx}/market/product/${item.refrenceId}?code=default"
                                           title="${item.productTitle}" class="js-img-center" target="_blank">
                                            <img src="${res}/images/common/loading.gif"
                                                 alt="${item.productTitle}" data-ks-lazyload="${res}${fns:getImageDomainPath(item.productImage,220 ,220)}"/>
                                            <span></span>
                                        </a>
                                        <div class="operate" id="operate_${item.refrenceId}">
                                           <%-- <a class="operate-collect" href="javascript:addFavorite('${item.refrenceId}');"><i class="icon-collect"></i>收藏</a>--%>
                                        </div>
                                    </div>
                                    <div class="detail">
                                        <div class="title">
                                            <a href="${ctx}/market/product/${item.refrenceId}?code=default">${item.productTitle}</a>
                                        </div>
                                        <div class="number">货号：${item.productNo}</div>
                                        <div class="address clearfix">
                                            <c:set value="${fns:getBrandsIdSubDomain(item.brandsId)}" var="domain" />
                                            <a class="fl" href="http://${domain}${zttx}/" title="${item.brandsName}">${item.brandsName}</a>
                                            <a class="fr" href="javascript:;"
                                               title="${item.provinceName}">${item.provinceName}</a>
                                        </div>
                                        <div class="price" id="price_${item.refrenceId}">
                                           <%--
                                            <c:choose>
                                                <c:when test="${brandesAuthUserModel.isAuth eq no}">
                                                    吊牌价：<em>￥</em>
                                                    <strong><fmt:formatNumber  value="${brandesAuthUserModel.productPrice}" pattern="0.00"/></strong>
                                                </c:when>
                                                <c:when test="${brandesAuthUserModel.isAuth eq cash}">
                                                    直供价：<em>￥</em>
                                                    <strong><fmt:formatNumber value="${brandesAuthUserModel.productDirPrice}" pattern="0.00"/></strong>
                                                    <i><fmt:formatNumber value="${brandesAuthUserModel.productPrice}" pattern="0.00"/></i>
                                                </c:when>
                                                <c:when test="${brandesAuthUserModel.isAuth eq credit}">
                                                    授信价：<em>￥</em>
                                                    <strong><fmt:formatNumber  value="${brandesAuthUserModel.productCreditPrice}" pattern="0.00"/></strong>
                                                    <i><fmt:formatNumber  value="${brandesAuthUserModel.productPrice}" pattern="0.00"/></i>
                                                </c:when>
                                            </c:choose>
                                            --%>
                                        </div>

                                        <div class="clearfix">
                                        <span class="fl">
                                            <c:if test="${item.isSample eq true}">
                                                <i class="icon-nayang" title="支持拿样" style="margin-right: 5px"></i>
                                            </c:if>
                                            <c:if test="${item.isCredit eq true}">
                                                <i class="icon-yuding" title="我的授信商品"></i>
                                            </c:if>
                                            <c:if test="${item.isPoint}">
                                                <i class="icon-fd" title="我的返点商品"></i>
                                            </c:if>
                                        </span>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <div class="no-data">
                            <img src="${res}/images/fronts/index/null.png">
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <tags:page form="searchForm" page="${result.page}"/>
        </form>
        <div class="m-related">
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/include/bottom.jsp"></jsp:include>
</div>
<jsp:include page="/WEB-INF/view/common/join_order_tpl.jsp"></jsp:include>
<script id="join_tpl" type="text/html">
    {{if userType != 2 }}
        <a class="operate-collect" href="javascript:addFavorite('{{productId}}');"><i class="icon-collect"></i>收藏</a>
        {{if isAuth > 0}}
        <a class="operate-jhds" href="javascript:joinShop('{{productId}}','{{isAuth == 1 ? 0 : 1 }}');"><i class="icon-jhds"></i>加入进货单 </a>
        {{else if isAuth == 0}}
        <a class="operate-jhds" href="javascript:applyJoin('{{productId}}','');"><i class="icon-jhds"></i>申请加盟</a>
        {{/if}}
    {{/if}}
</script>
<script id="price_tpl" type="text/html">
  {{if isPoint }}
           返点价：<em>￥</em>
        <strong>{{$formatPrice pointPrice}}</strong>
        <i>{{$formatPrice productPrice}}</i>
    {{else if isAuth == 0}}
        吊牌价：<em>￥</em>
        <strong>{{$formatPrice productPrice}}</strong>
    {{else if isAuth == 1}}
        直供价：<em>￥</em>
        <strong>{{$formatPrice productDirPrice}}</strong>
        <i>{{$formatPrice productPrice}}</i>
    {{else if isAuth == 2}}
        授信价：<em>￥</em>
        <strong>{{$formatPrice productCreditPrice}}</strong>
        <i>{{$formatPrice productPrice}}</i>
    {{/if}}
</script>
<script src="${src}/common/base-init.js"></script>
<script>
    seajs.use(["${jsrel}/fronts/index/index"], function (Index) {
        Index.index_product_list.init();
    });
</script>
</body>
</html>