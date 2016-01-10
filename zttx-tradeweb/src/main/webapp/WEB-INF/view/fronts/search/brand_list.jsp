<%@ page import="com.zttx.web.consts.BrandConstant" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>品牌搜索 - 8637品牌超级代理</title>
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
        </div>
        <%--<div class="m-filter clearfix">
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
            </ul>
        </div>--%>
        <div class="m-recom clearfix" style="border-top: 1px solid #F2F2F2;">
            <%--<div class="fl">
                <label>
                    <input type="checkbox" ${filter.recom == true ? "checked" : ""} data-type="0"/>
                    <span>推荐品牌</span>
                </label>
            </div>--%>
            <div class="fr" id="change_st">
                <a class="active" href="javascript:;" data-type="b"><i class="icon-g-brand"></i>找品牌商</a>
                <a href="javascript:;" data-type="p"><i class="icon-g-goods"></i>商品</a>
            </div>
        </div>
        <form id="searchForm" action="${filter.url}" method="get">
            <input type="hidden" name="q" id="searchFormQ" value="${filter.q != null ? filter.q : ""}"/>
            <input type="hidden" name="st" value="${filter.st != null ? filter.st : ""}"/>
            <input type="hidden" name="brandsId" value="${filter.brandsId != null ? filter.brandsId : ""}"/>
            <input type="hidden" name="mainId" value="${filter.mainId != null ? filter.mainId : ""}"/>
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
            <div class="m-brandlist">
                <ul class="itemul inline-float">
                    <c:set var="userPrincipal" value="${fns:getUserPrincipal()}"/>
                    <c:set var="brandType" value="<%=BrandConstant.userType.BRAND_TYPE%>"/>
                    <c:forEach items="${result.list}" var="item">
                        <li class="item">
                            <div class="clearfix">
                                <div class="i1">
                                    <div class="item-logo">
                                        <c:set value="${fns:getBrandsIdSubDomain(item.refrenceId)}" var="domain" />
                                        <a href="http://${domain}${zttx}" class="js-img-center">
                                            <img src="${res}${fns:getImageDomainPath(item.brandLogo,140 ,70)}"
                                                 alt="${item.brandName}"/>
                                        </a>
                                    </div>
                                    <div class="item-name"><a href="http://${domain}${zttx}">${item.brandName}</a></div>
                                   <c:if test="${userPrincipal.userType ne brandType}">
                                    <c:choose>
                                    	<c:when test="${item.isCollected }">
                                    		<div class="item-follow">已收藏</div>
                                    	</c:when>
                                    	<c:otherwise>
                                    		<div class="item-follow" id="collect_${item.refrenceId }"><a href="javascript:addCollection('${item.refrenceId }');">添加收藏</a></div>
                                    	</c:otherwise>
                                    </c:choose>
                                   </c:if>
                                </div>
                                <div class="i2">
                                    <a href="http://${domain}${zttx}"><img src="${res}${fns:getImageDomainPath(item.recommendImage,370 ,200)}"
                                         alt="${item.brandName}" width="370" height="200"/></a>
                                </div>
                                <div class="i3">
                                    <h3><a href="http://${domain}${zttx}">${item.comName}</a></h3>
                                    <div class="item-address">${item.provinceName}&nbsp;&nbsp;${item.cityName}</div>
                                    <div class="item-com"><span>主营产品：</span>
                                        <c:forEach items="${item.dealName}" var="dealName" varStatus="status">
                                            ${dealName}<c:if test="${!status.last}"> | </c:if>
                                        </c:forEach>
                                    </div>
                                    <div class="item-com"><span>商品数量：</span><em>${item.productNum}</em> 个</div>
                                    <div class="item-com"><span>进货店铺：</span><em>${item.joinNum}</em> 家</div>
                                    <div class="item-btns">

                                        <a href="javascript:applyJoin('','${item.refrenceId}')"  class= "${userPrincipal.userType ne brandType ? 'btn1' : 'disabled'}">申请加盟</a>
                                        <a href="http://${domain}${zttx}/product" target="_blank"  class= "${userPrincipal.userType ne brandType ? 'btn2' : 'disabled'}">进货</a>
                                    </div>
                                </div>
                                <div class="i4">
                                    <div>
                                        <ul class="inline-float">
                                            <c:set var="products" value="${item.productList}"/>
                                            <c:forEach items="${products}" var="pro" varStatus="status">
                                                <li class="item-other">
                                                    <div>
                                                        <a href="${ctx}/market/product/${pro.refrenceId}" class="js-img-center" target="_blank">
                                                            <img src="${res}${fns:getImageDomainPath(pro.productImage,130 ,130)} "
                                                                 alt="${pro.productTitle}"/>
                                                        </a>
                                                    </div>
                                                    <span><fmt:formatNumber value="${pro.productPrice}" pattern="0.00"/></span>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <div class="ta-r mt15" style="margin-right: 19px;">
                                        <a href="http://${domain}${zttx}/index" target="_blank" class="item-more">了解详情&gt;</a>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>

            </div>
            <tags:page form="searchForm" page="${result.page}"/>
        </form>
        <div class="m-related">
           <%-- <div class="tit clearfix">
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
            </div>--%>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/include/bottom.jsp"></jsp:include>
</div>
<script src="${src}/common/base-init.js"></script>
<script>
    seajs.use(["${jsrel}/fronts/index/index"], function (Index) {
        Index.index_product_list.init();
    });
</script>
</body>
</html>