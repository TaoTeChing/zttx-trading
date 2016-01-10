<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         import="com.zttx.web.consts.BrandUsermConstant,com.zttx.web.consts.DealerConstant" pageEncoding="UTF-8" %>
<%@ page import="com.zttx.web.module.brand.model.BrandesAuthUserModel" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<c:set var="CARRY_DEALER" value="<%=com.zttx.web.consts.ProductConsts.CARRY_DEALER.getKey()%>"/>
<c:set var="CAN_ORDER" value="<%=com.zttx.web.consts.ProductConsts.CAN_ORDER.getKey()%>"/>
<c:set var="SOLD_OUT" value="<%=com.zttx.web.consts.ProductConsts.SOLD_OUT.getKey()%>"/>
<c:set var="NO_ORDERSTART" value="<%=com.zttx.web.consts.ProductConsts.NO_ORDERSTART.getKey()%>"/>
<c:set var="CAN_NOT_ALL" value="<%=com.zttx.web.consts.ProductConsts.CAN_NOT_ALL.getKey()%>"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>${productInfo.productTitle}-${productInfo.productNo}-${brandesInfo.brandsName}-${brandInfo.comName}产品介绍</title>
    <meta name="keywords"
          content="${productInfo.productTitle}-${brandesInfo.brandsName},${brandesInfo.brandsName}品牌代理,${brandesInfo.brandsName}品牌招商"/>
    <meta name="description" content=""/>
    <link href="${res}/styles/fronts/market/brandviewbase.css" rel="stylesheet" type="text/css"/>
    <link href="${res}/styles/fronts/market/decoration.css" rel="stylesheet" type="text/css"/>
    <jsp:include page="${ctx}/market/header_css">
        <jsp:param name="brandesId" value="${productInfo.brandsId}"/>
    </jsp:include>
</head>
<body>
<!-- top -->
<jsp:include page="${ctx}/common/top"/>
<!-- head -->
<jsp:include page="${ctx}/market/header">
    <jsp:param name="brandesId" value="${productInfo.brandsId}"/>
    <jsp:param name="url" value="/product"/>
</jsp:include>
<!--// header end-->
<div class="do-body">
    <%--产品列表和详情页面不需要背景图--%>
    <style>.do-body {
        background-image: none;
    }</style>
    <div class="listbody clear">
        <div class="sidebar-l">
            <form:form action="${ctx}/dealer/dealerShoper/productToBalance" id="product_addcar_form" method="post">
                <div class="chanpin font">
                    <div class="magnibox f-l ad-gallery clearfix" style="position:relative;">
                        <div class="magnimg" style="position:relative;">
                            <a href="javascript:void(0)" class="jqzoom js-img-center">
                                <c:set value="${productInfo.productImage}" var="url"></c:set>
                                <img src="${res}${url}_300x300.jpg" data-glasses="${res}${url}" title=""/>
                            </a>
                        </div>
                        <div class="magni-all">
                            <a href="javascript:;" class="pagestep-btn prev">&lt;</a> <a href="javascript:;"
                                                                                         class="pagestep-btn next">
                            &gt;</a>

                            <div class="magni-scroll mt10">
                                <ul class="magniul" id="thumblist">
                                    <c:forEach items="${productInfo.imageUrls}" var="url">
                                        <li class="js-img-center">
                                            <a class="zoomThumbActive" href='javascript:void(0);'>
                                                <img src="${res}${url}_60x60.jpg" data-src="${res}${url}"/>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <div class="m-collect">
                            <c:if test="${isFaved}"><span
                                    class="yiguanzhu"></span>已收藏（${empty brandsCount ? 0 : brandsCount.favNum}）</c:if>
                            <c:if test="${not isFaved}">
                                <a href="javascript:addFavor('${productInfo.refrenceId}',this)" class="link">
                                    <i class="icon-collect"></i>收藏商品<span>（${empty brandsCount ? 0 : brandsCount.favNum}）</span></a></c:if>
                        </div>
                        <div class="m-share">
                            <div class="bdsharebuttonbox" data-tag="share_1" style="margin: 10px 0 0 0">
                                <span href="#" style="float: left; margin-top: 6px;"><i
                                        class="icon-share"></i>分享到：</span>
                                <a href="#" class="bds_tsina" data-cmd="tsina"></a>
                                <a href="#" class="bds_qzone" data-cmd="qzone"></a>
                                <a href="#" class="bds_renren" data-cmd="renren"></a>
                                <a href="#" class="bds_more" data-cmd="more"></a>
                            </div>
                            <script>
                                window._bd_share_config = {
                                    "common": {
                                        "bdSnsKey": {},
                                        "bdText": "",
                                        "bdMini": "2",
                                        "bdPic": "",
                                        "bdStyle": "1",
                                        "bdSize": "16"
                                    },
                                    "share": {}
                                };
                                with (document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];
                            </script>
                        </div>
                    </div>
                    <div class="checkbox f-l">
                        <c:set value="<%=BrandesAuthUserModel.INCLUDE_SKU%>" var="state"/>
                        <c:set value="<%=BrandesAuthUserModel.NO_AUTH%>" var="no"/>
                        <c:set value="<%=BrandesAuthUserModel.CASH_AUTH%>" var="cash"/>
                        <c:set value="<%=BrandesAuthUserModel.CREDIT_AUTH%>" var="credit"/>

                        <c:set value="<%=DealerConstant.DealerShoper.PRODUCTTYPE_CASH%>" var="productType_cash"/>
                        <c:set value="<%=DealerConstant.DealerShoper.PRODUCTTYPE_CREDIT%>" var="productType_credit"/>
                        <c:set value="<%=DealerConstant.DealerShoper.PRODUCTTYPE_SAM%>" var="productType_sam"/>
                        <c:set value="${productInfo.refrenceId}" var="refrenceId"/>
                        <c:set value="${fns:getAuthPrice(refrenceId,state)}" var="brandesAuthUserModel"></c:set>
                        <h1 class="title" title="${productInfo.productTitle}"
                            style="word-break: break-all;">${productInfo.productTitle}</h1>
                        <c:choose>
                            <c:when test="${!brandesAuthUserModel.isValidProduct}">
                                <span style="margin-left: 20px;color:#e00;">产品已删除</span>
                            </c:when>
                            <c:when test="${productInfo.stateSet ne 1}">
                                <span style="margin-left: 20px;color:#e00;">产品已下架</span>
                            </c:when>
                        </c:choose>
                        <p style="padding: 0 20px 15px 20px;color: #666;line-height: 18px;border-bottom:1px solid #f2f2f2;">
                                ${productInfo.productExtInfo.patchMark}
                        </p>

                        <div class="m-detail">
                            <dl class="clearfix">
                                <dt>货号</dt>
                                <dd>${productInfo.productNo}</dd>
                            </dl>
                            <dl class="clearfix">
                                <c:choose>
                                    <c:when test="${brandesAuthUserModel.isAuth eq no}">
                                        <dt>吊牌价</dt>
                                        <dd><fmt:formatNumber value="${brandesAuthUserModel.productPrice}"
                                                              pattern="0.00"/></dd>
                                    </c:when>
                                    <%--<c:when test="${brandesAuthUserModel.isAuth eq cash}">
                                        <dt>直供价</dt>
                                        <dd><fmt:formatNumber value="${brandesAuthUserModel.productDirPrice}"
                                                              pattern="0.00"/></dd>
                                    </c:when>
                                    <c:when test="${brandesAuthUserModel.isAuth eq credit}">
                                        <dt>授信价</dt>
                                        <dd><fmt:formatNumber value="${brandesAuthUserModel.productCreditPrice}"
                                                              pattern="0.00"/></dd>
                                    </c:when>
                                --%></c:choose>
                            </dl>

                            <dl class="clearfix">
                                <dt>起批量 &nbsp;&nbsp;&nbsp;</dt>
                                <dd id="startNum">${productInfo.productExtInfo.startNum}<%--（<a href="#" class="link">起批说明</a>）--%></dd>
                            </dl>
                            <dl class="clearfix">
                                <dt>物流运费</dt>
                                <dd>
                                    <c:if test="${productInfo.productCarry==1}">
                                        终端商承担运费
                                    </c:if>
                                    <c:if test="${productInfo.productCarry==2}">
                                        品牌商承担运费
                                    </c:if>
                                        <%--（<a href="#" class="link">运费说明</a>）--%>
                                </dd>
                            </dl>
                        </div>

                        <input type="hidden" name="productId" value="${productInfo.refrenceId}"/>
                        <input type="hidden" name="productType" value=""/>
                        <input type="hidden" name="isClient" value="false"/>

                        <c:if test="${brandesAuthUserModel.isAuth ne no}">
                            <c:if test="${sizeIds}">
                                <div class="m-detail">
                                    <dl class="mt15 clearfix">
                                        <dt>颜色</dt>
                                        <dd>
                                            <ul class="m-colorlist inline-float" id="js-colorlist">
                                                <c:forEach items="${brandesAuthUserModel.productColorSet}" var="item"
                                                           varStatus="status">
                                                    <c:if test="${status.index==0}">
                                                        <li class="selected"><a
                                                                href="javascript:;">${item}</a><span></span></li>
                                                    </c:if>
                                                    <c:if test="${status.index!=0}">
                                                        <li><a href="javascript:;">${item}</a><span></span></li>
                                                    </c:if>
                                                </c:forEach>
                                            </ul>
                                        </dd>
                                    </dl>
                                </div>
                                <div class="mt15">
                                    <table class="table-head">
                                        <colgroup>
                                            <c:if test="${brandesAuthUserModel.point}">
                                                <col width="90"/>
                                                <col width="90"/>
                                                <col width="82"/>
                                                <col width="88"/>
                                                <col width="85"/>
                                                <col width="60"/>
                                                <col width="140"/>
                                            </c:if>
                                            <c:if test="${!brandesAuthUserModel.point}">
                                                <col width="90"/>
                                                <col width="82"/>
                                                <col width="88"/>
                                                <c:if test="${brandesAuthUserModel.isAuth == credit}">
                                                    <col width="85"/>
                                                </c:if>
                                                <col width="60"/>
                                                <col width="140"/>
                                            </c:if>
                                        </colgroup>
                                        <thead>
                                        <c:if test="${brandesAuthUserModel.point}">
                                            <tr>
                                                <th></th>
                                                <th></th>
                                                <th>返点销售价</th>
                                                <th>返点比例</th>
                                                <th>返点成本</th>
                                                <th>库存</th>
                                                <th></th>
                                            </tr>
                                        </c:if>
                                        <c:if test="${!brandesAuthUserModel.point}">
                                            <tr>
                                                <th></th>
                                                <th></th>
                                                <th>现款价</th>
                                                <c:if test="${brandesAuthUserModel.isAuth == credit}">
                                                    <th>授信价</th>
                                                </c:if>
                                                <th>库存</th>
                                                <th></th>
                                            </tr>
                                        </c:if>
                                        </thead>
                                    </table>
                                </div>
                                <div class="m-detail">
                                    <dl class="clearfix">
                                        <dt>尺码</dt>
                                        <dd style="height:200px;overflow: auto;">
                                            <c:forEach items="${brandesAuthUserModel.productSkuMap}" var="item"
                                                       varStatus="status">
                                                <table class="table-list"
                                                       <c:if test="${status.index!=0}">style="display:none;"</c:if>>
                                                    <colgroup>
                                                        <c:if test="${brandesAuthUserModel.point}">
                                                            <col width="60"/>
                                                            <col width="60"/>
                                                            <col width="95"/>
                                                            <col width="85"/>
                                                            <col width="55"/>
                                                            <col width="110"/>
                                                        </c:if>
                                                        <c:if test="${!brandesAuthUserModel.point}">
                                                            <col width="60"/>
                                                            <col width="95"/>
                                                            <c:if test="${brandesAuthUserModel.isAuth == credit}">
                                                                <col width="85"/>
                                                            </c:if>
                                                            <col width="55"/>
                                                            <col width="110"/>
                                                        </c:if>
                                                    </colgroup>
                                                    <tbody id="js-list">
                                                    <c:forEach items="${item.value}" var="item2">
                                                        <input type="hidden" class="js-skuId" name="skuIds"
                                                               value="${item2.productSkuId}"/>
                                                        <c:if test="${brandesAuthUserModel.point}">
                                                            <tr>
                                                                <td style="font-weight: bold;">${item2.sizeName}</td>
                                                                <td><em class="js-p-fd"><fmt:formatNumber
                                                                        value="${item2.productSkuPointPrice}"
                                                                        type="currencySymbol" pattern="0.00"/></em>元
                                                                </td>
                                                                <td><em class="js-p-fdbl"><fmt:formatNumber
                                                                        value="${brandesAuthUserModel.productPointPercent*100}"
                                                                        type="currencySymbol" pattern="#.#"/></em>%
                                                                </td>
                                                                <td>
                                                                    <em class="js-p-fdcb"><fmt:formatNumber
                                                                            value="${(1-brandesAuthUserModel.productPointPercent)*item2.productSkuPointPrice}"
                                                                            type="currencySymbol" pattern="0.00"/></em>元
                                                                </td>
                                                                <td>${item2.quantity}</td>
                                                                <td>
                                                                    <div class="clearfix"><input type="text"
                                                                                                 class="num-amount"
                                                                                                 value="0"
                                                                                                 data-max="${item2.quantity}"
                                                                                                 name="purchaseNum"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                        <c:if test="${!brandesAuthUserModel.point}">
                                                            <tr>
                                                                <td style="font-weight: bold;">${item2.sizeName}</td>
                                                                <td><em class="js-p-xk">${item2.productSkuDirPrice}</em>元
                                                                </td>
                                                                <c:if test="${brandesAuthUserModel.isAuth == credit}">
                                                                    <td>
                                                                        <em class="js-p-sx">${item2.productSkuCreditPrice}元</em>
                                                                    </td>
                                                                </c:if>
                                                                <td>${item2.quantity}</td>
                                                                <td>
                                                                    <div class="clearfix"><input type="text"
                                                                                                 class="num-amount"
                                                                                                 value="0"
                                                                                                 data-max="${item2.quantity}" name="purchaseNum"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </c:forEach>
                                        </dd>
                                    </dl>
                                </div>
                            </c:if>
                            <c:if test="${!sizeIds}">
                                <div class="mt15">
                                    <table class="table-head">
                                        <colgroup>
                                            <c:if test="${brandesAuthUserModel.point}">
                                                <col width="90"/>
                                                <col width="82"/>
                                                <col width="88"/>
                                                <col width="88"/>
                                                <col width="85"/>
                                                <col width="60"/>
                                                <col width="140"/>
                                            </c:if>
                                            <c:if test="${!brandesAuthUserModel.point}">
                                                <col width="90"/>
                                                <col width="82"/>
                                                <col width="88"/>
                                                <c:if test="${productInfo.productExtInfo.credit}">
                                                    <col width="85"/>
                                                </c:if>
                                                <col width="60"/>
                                                <col width="140"/>
                                            </c:if>
                                        </colgroup>
                                        <thead>
                                        <c:if test="${brandesAuthUserModel.point}">
                                            <tr>
                                                <th></th>
                                                <th></th>
                                                <th>返点销售价</th>
                                                <th>返点比例</th>
                                                <th>返点成本</th>
                                                <th>库存</th>
                                                <th></th>
                                            </tr>
                                        </c:if>
                                        <c:if test="${!brandesAuthUserModel.point}">
                                            <tr>
                                                <th></th>
                                                <th></th>
                                                <th>现款价</th>
                                                <c:if test="${productInfo.productExtInfo.credit}">
                                                    <th>授信价</th>
                                                </c:if>
                                                <th>库存</th>
                                                <th></th>
                                            </tr>
                                        </c:if>
                                        </thead>
                                    </table>
                                </div>
                                <div class="m-detail">
                                    <dl class="clearfix">
                                        <dt>颜色</dt>
                                        <dd style="height:200px;overflow: auto;">
                                            <table class="table-list">
                                                <colgroup>
                                                    <c:if test="${brandesAuthUserModel.point}">
                                                        <col width="90"/>
                                                        <col width="95"/>
                                                        <col width="95"/>
                                                        <col width="85"/>
                                                        <col width="55"/>
                                                        <col width="130"/>
                                                    </c:if>
                                                    <c:if test="${!brandesAuthUserModel.point}">
                                                        <col width="90"/>
                                                        <col width="95"/>
                                                        <c:if test="${productInfo.productExtInfo.credit}">
                                                            <col width="85"/>
                                                        </c:if>
                                                        <col width="55"/>
                                                        <col width="130"/>
                                                    </c:if>
                                                </colgroup>
                                                <tbody id="js-list">
                                                <c:forEach items="${brandesAuthUserModel.productSkuMap}" var="item"
                                                           varStatus="status">
                                                    <c:forEach items="${item.value}" var="item2">
                                                        <input type="hidden" class="js-skuId" name="skuIds"
                                                               value="${item2.productSkuId}"/>
                                                        <c:if test="${brandesAuthUserModel.point}">
                                                            <tr>
                                                                <td style="font-weight: bold;">${item2.colorName}
                                                                </td>
                                                                <td><em class="js-p-fd"><fmt:formatNumber
                                                                        value="${item2.productSkuPointPrice}"
                                                                        type="currencySymbol" pattern="0.00"/></em>元
                                                                </td>
                                                                <td><em class="js-p-fdbl"><fmt:formatNumber
                                                                        value="${brandesAuthUserModel.productPointPercent*100}"
                                                                        type="currencySymbol" pattern="#.#"/></em>%
                                                                </td>
                                                                <td>
                                                                    <em class="js-p-fdcb"><fmt:formatNumber
                                                                            value="${(1-brandesAuthUserModel.productPointPercent)*item2.productSkuPointPrice}"
                                                                            type="currencySymbol" pattern="0.00"/></em>元
                                                                </td>
                                                                <td>${item2.quantity}</td>
                                                                <td>
                                                                    <div class="clearfix"><input type="text"
                                                                                                 class="num-amount"
                                                                                                 value="0"
                                                                                                 data-max="${item2.quantity}"
                                                                                                 name="purchaseNum"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                        <c:if test="${!brandesAuthUserModel.point}">
                                                            <tr>
                                                                <td style="font-weight: bold;"><input type="hidden"
                                                                                                      class="js-skuId"
                                                                                                      value="${item2.productSkuId}"/>${item2.colorName}
                                                                </td>
                                                                <td><em class="js-p-xk">${item2.productSkuDirPrice}</em>元
                                                                </td>
                                                                <c:if test="${productInfo.productExtInfo.credit}">
                                                                    <td>
                                                                        <em class="js-p-sx">${item2.productSkuCreditPrice}</em>元
                                                                    </td>
                                                                </c:if>
                                                                <td>${item2.quantity}</td>
                                                                <td>
                                                                    <div class="clearfix"><input type="text"
                                                                                                 class="num-amount"
                                                                                                 value="0"
                                                                                                 data-max="${item2.quantity}" name="purchaseNum"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </dd>
                                    </dl>
                                </div>
                            </c:if>
                            <div class="m-balance">
                                <span><em id="js-count">0</em>件</span>
 
                                <c:if test="${brandesAuthUserModel.point}">
                                    <label><input type="radio" name="balances" value="3" checked/>返点价：<em
                                            id="js-fdprick">0</em>元</label>
                                </c:if>
                                <c:if test="${!brandesAuthUserModel.point}">
                                    <label>
                                        <input type="radio" name="balances" value="0" checked/>现款价：<em
                                            id="js-xkprick">0</em>元
                                    </label>
                                    <label>
                                        <c:if test="${brandesAuthUserModel.isAuth eq credit}">
                                            <input type="radio" name="balances"
                                                   value="1"/>授信价：<em id="js-sxprick">0</em>元
                                        </c:if>
                                    </label>
                                </c:if> 
                            </div>
                        </c:if>

                        <div class="add-settle">
                            <c:set value="<%=BrandUsermConstant.userType.BRAND%>" var="brand_type"/>
                            <c:if test="${brandesAuthUserModel.userType ne brand_type}">
                                <c:choose>
                                    <c:when test="${brandesAuthUserModel.isAuth ne no}">
                                        <%--<a href="javascript:joinShop('${productInfo.refrenceId}');"
                                           id="addcart" class="btn1"><i class="icon-jhds"></i>加入进货单</a>--%>
                                        <!--${brandesAuthUserModel.isValidProduct} ${productInfo.stateSet==1}  有效状态-->
                                        <c:if test="${!brandesAuthUserModel.isValidProduct}">
                                            <%--产品失效--%>
                                            <a href="javascript:;" class="btn1"
                                               style="background-color: #999;">加入进货单</a>
                                            <a href="javascript:;" class="btn2" style="background-color: #999;">去结算</a>
                                        </c:if>
                                        <c:if test="${productInfo.stateSet ne 1}">
                                            <%--产品下架--%>
                                            <a href="javascript:;" class="btn1"
                                               style="background-color: #999;">加入进货单</a>
                                            <a href="javascript:;" class="btn2" style="background-color: #999;">去结算</a>
                                        </c:if>
                                        <c:if test="${brandesAuthUserModel.isValidProduct && productInfo.stateSet==1}">
                                            <%--产品有效--%>
                                            <a href="javascript:;" onclick="joinShop('${productInfo.refrenceId}');"
                                               id="addcart" class="btn1"><i class="icon-jhds"></i>加入进货单</a>
                                            <%--<a href="javascript:toBalance('${productInfo.refrenceId}','${brandesAuthUserModel.isAuth eq cash?productType_cash:productType_credit}')" id="toBalance" class="btn2">去结算</a>--%>
                                            <button type="submit" class="btn2">去结算</button>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="javascript:applyJoin('${productInfo.refrenceId}');" id="" class="btn2">申请加盟</a>
                                    </c:otherwise>
                                </c:choose> 
                            </c:if>
                        </div>
                        <div class="no-product-tip" id="noProductTip">
                            <i class="icon-warning"></i>订购数量必须为大于0的整数
                        </div>
                    </div>
                </div>
            </form:form>
            <c:if test="${sample}">
                <div class="chanpin-fd clearfix">
                    <table>
                        <colgroup>
                            <col width="310"/>
                            <col width="325"/>
                            <col width="210"/>
                        </colgroup>
                        <tbody>
                        <tr>
                            <td class="ta-c"><i class="icon-nayang"></i>拿样价:￥<fmt:formatNumber value="${samplePrice}"
                                                                                               type="currency"
                                                                                               pattern="0.00"/>
                            </td>
                            <td class="ta-l">
                                <p>产品：拿样产品仅支持拿样一件</p>

                                <p>运费：与该产品运费一致</p>

                                <p>退换货：不支持退货</p>
                            </td>
                            <td class="ta-r">

                                <c:if test="${brandesAuthUserModel.isHasTakeSample}">
                                    <a href="#" class="btn1">已拿样</a>
                                </c:if>
                                <c:if test="${brandesAuthUserModel.userType ne brand_type}">
                                    <c:if test="${brandesAuthUserModel.isAuth ne no}">
                                        <c:if test="${not brandesAuthUserModel.isHasTakeSample}">
                                            <a href="javascript:joinShop('${productInfo.refrenceId}','${productType_sam}')"
                                               class="btn1">立即拿样</a>
                                        </c:if>
                                    </c:if>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <c:if test="${not empty productMap}">
                <div class="compare-box mt10 clearfix">
                    <i class="pro-icons pro-icons-bj"></i>

                    <div class="compare-box-col1 fl">
                        <div class="title clearfix">
                            <h2 class="fl">8637进货价与其它平台对比</h2>
                            <c:if test="${!isLogin}">
                                <p class="fr mt5" style="display:none;">
                                    <a href="http://www${zttx}/common/register">注册</a> 或<a
                                        href="http://www${zttx}/common/login">登录</a> 后查看价格
                                </p>
                            </c:if>
                        </div>
                        <ul class="compare-box-list">
                            <li>
                                <div class="fl">
                                    <i class="pro-icons pro-icons-zttx"></i>8637直供价：
                                    <c:set var="parityPrice" value="${productInfo.productPrice}"/>
                                    <c:if test="${!empty userGrantModel.proLowestSkuPrice}">
                                        <c:set var="parityPrice" value="${userGrantModel.proLowestSkuPrice}"/>
                                    </c:if>
                                    <fmt:formatNumber value="${parityPrice}" type="currency" pattern="0.00"/>
                                    元
                                </div>
                                <div class="progress-bar fr" data-price="${parityPrice}" data-color="#56A8E7">
                                    <div class="progress-bar-cover cover-zttx"></div>
                                </div>
                            </li>
                            <c:forEach items="${parityMap.productList}" var="item">
                                <li>
                                    <div class="fl">
                                        <i class="icon ${item.style}"></i>${item.columnName}：
                                        <fmt:formatNumber value="${item.price}" type="currency" pattern="0.00"/>
                                        元
                                    </div>
                                    <div class="progress-bar fr" data-price="${item.price}" data-color="#${item.color}">
                                        <div class="progress-bar-cover cover-zttx"></div>
                                    </div>
                                </li>
                            </c:forEach>
                            <c:if test="${!empty productInfo.provincePrice}">
                                <li>
                                    <div class="fl">
                                        <i class="icon icon-shengdai"></i>省代价格：
                                        <fmt:formatNumber value="${productInfo.provincePrice}" type="currency"
                                                          pattern="0.00"/>
                                        元
                                    </div>
                                    <div class="progress-bar fr" data-price="${productInfo.provincePrice}"
                                         data-color="#77D200">
                                        <div class="progress-bar-cover cover-shengdai"></div>
                                    </div>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                    <div class="compare-box-col2 fl">
                        <div class="title">
                            <h2>利润对比</h2>
                        </div>
                        <div class="chart-area">
                            <p class="chart-profit js-chart-profit">
                                利润<span></span>%
                            </p>

                            <p class="chart-prime js-chart-prime">
                                成本<span></span>%
                            </p>

                            <div id="myfirstchart" style="width: 125px;height: 125px;margin-left: 86px;"></div>
                        </div>
                    </div>
                </div>
            </c:if>
            <%--<!--//产品-->--%>
            <div class="plist-ppro font clear mt10">
                <div class="proinfo-specs js-specs">
                    <ul class="proinfo-specsul js-specsul">
                        <li class="selected"><a href="javascript:;">产品信息</a>
                        </li>
                        <%--<li><a href="javascript:;">产品详细信息</a></li>--%>
                    </ul>
                    <div class="proinfo-specscon js-specscon">
                        <div class="proinfo-specs-items">
                            <ul class="itemsul">
                                <c:set var="result" value="false"/>
                                <c:forEach items="${proAttributes}" var="proAttr">
                                    <c:set var="showAttributeName" value="true"/>
                                    <c:forEach items="${proAttr.valueList}" var="attrValue" varStatus="varStu">
                                        <c:set var="result" value="false"/>
                                        <c:if test="${attrValue.selected}">
                                            <c:set var="result" value="true"/>
                                            <c:choose>
                                                <c:when test="${showAttributeName }">
                                                    <c:set var="showAttributeName" value="false"/>
                                                    <li title="${proAttr.attributeName}">${proAttr.attributeName}：${attrValue.attributeValue}
                                                </c:when>
                                                <c:otherwise>
                                                    ，${attrValue.attributeValue}
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${result }"></li></c:if>
                                </c:forEach>
                            </ul>
                        </div>
                        <%--<div class="proinfo-specs-items hide"></div>--%>
                    </div>
                    <div class="ppro clear"
                         style="border-top:1px dashed #ddd;word-break: break-all;text-align: center;">
                        ${productInfo.productExtInfo.productMark}

                    </div>
                </div>
            </div>
        </div>

        <!---------------------------------   右边栏目      --------------------------------->
        <div class="sidebar-r">
            <div class="m-brand">
                <div class="mb-fore1 js-img-center">
                    <a href="#"><img src="${res}${brandesInfo.brandLogo}" alt="" width="180" height="90"/></a>
                </div>
                <div class="mb-fore2">
                    <a href="#">${brandesInfo.brandsName}</a>
                </div>
                <div class="mb-fore3 clearfix">
                    <a href="javascript:;"><i class="icon-view"></i>浏览量：<em>${brandsCount.viewNum}</em></a>
                    <a href="javascript:;" style="margin-left: 20px;"><i
                            class="icon-follow"></i>关注：<em>${brandsCount.favNum}</em></a>
                </div>
                <div class="mb-fore4">
                    <h3 class="tit">品牌供应商：</h3>

                    <p>${brandInfo.comName}</p>
                </div>
                <div class="mb-fore5 clearfix">
                    <span><i class="icon-ident"></i>认证企业</span>
                    <span style="text-align: center;"><i class="icon-grade"></i>优质品牌</span>
                    <c:if test="${join!=null}">
                        <span style="text-align: right;"><i class="icon-joined"></i>已加盟</span>
                    </c:if>
                </div>
            </div>

            <!--// 品牌及加盟信息 -->
            <div class="m-info">
                <%--未授权 开始--%>
                <c:if test="${null eq dealerJoin}">
                    <c:if test="${null ne brandRecruit && brandRecruit.recruitState == 1}">
                        <c:choose>
                            <c:when test="${btnState == 1}">
                                <div class="mi-fore5">
                                    <a class="join" id="joinbtn" href="javascript:;">提交加盟申请书</a>
                                </div>
                            </c:when>
                            <c:when test="${btnState == 2}">
                                <span>已加盟</span>
                            </c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>
                    </c:if>
                </c:if>
                <%--未授权 结束--%>
                <c:if test="${null ne dealerJoin}">
                    <div class="mi-fore1">
                        <dl class="clearfix">
                            <dt class="pic"><i class="icon-shou"></i></dt>
                            <dd class="tit">您是我们的加盟商户</dd>
                            <dd class="des">加盟关系：<em>${level.levelName}</em></dd>
                        </dl>
                    </div>
                    <div class="mi-fore2 clearfix">
                        <a href="${ctx}/dealer/dealerShoper/grant">我的授权产品库</a>
                        <a href="${ctx}/dealer/dealerShoper/cart" style="margin-left: 10px;">我的进货单</a>
                    </div>
                    <div class="mi-fore3 clearfix">
                        <p class="fl">累计进货<strong>${countAndAmount.count}</strong>款</p>

                        <p class="fr">进货额<strong>${countAndAmount.amount}</strong>元</p>
                    </div>
                </c:if>

                <div class="mi-fore4">
                    <dl class="clearfix">
                        <dt>企业官网</dt>
                        <dd><a href="http://${brandInfo.comWeb}">${brandInfo.comWeb}</a></dd>
                        <dt>联系地址</dt>
                        <dd>${brandInfo.provinceName} ${brandInfo.cityName} ${brandInfo.areaName} ${brandInfo.comAddress}</dd>
                        <dt>所属行业</dt>
                        <dd>
                            <c:forEach items="${businesses}" var="business" varStatus="st">
                                <c:if test="${!st.last}">
                                    ${business}|
                                </c:if>
                                <c:if test="${st.last}">
                                    ${business}
                                </c:if>
                            </c:forEach>

                        </dd>
                        <dt>公司规模</dt>
                        <dd>${emploeeNum}</dd>
                    </dl>
                </div>
            </div>
            <!--// 详细信息 -->
            <div class="m-contact">
                <div class="mc-fore1">
                    <p>${brandInfo.comName}在线咨询服务时间： ${fns:getTimeFromLong(online.onlineBeginTime,"HH:mm")}-${fns:getTimeFromLong(online.onlineEndTime,"HH:mm")}</p>
                </div>
                <%--<div class="mc-fore2">
                    <p class="p1">${brandContact.userTelphone}</p>

                    <p>+86 ${brandContact.userMobile}</p>
                </div>
                --%><%--未授权 开始--%>
                <%--<div class="mc-fore2">
                    <p class="p1">0574-555****888</p>
                    <p class="p2"><a class="link" href="#">登录</a> | 平台查看完整的联系方式</p>
                </div>--%>
                <%--未授权 结束--%>
                <div class="mc-fore3">
                    <ul class="inline-float">
                        <c:forEach items="${online.detailList}" var="detail">
                            <c:if test="${online.showed==1}">
                                <li>${detail.name}：<a
                                        href="http://wpa.qq.com/msgrd?v=3&amp;uin=${detail.qq}&amp;site=qq&amp;menu=yes"><img
                                        src="${res}/images/fronts/market/qq-service.png" alt="" width="75"/></a></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <!--// 联系信息 -->
            <div class="m-down">
                <h3 class="tit">公司旗下品牌：</h3>
                <ul class="inline-float">
                    <c:forEach items="${braList}" var="brandes">
                        <li>

                            <c:set value="${ fns:getBrandsIdSubDomain(brandes.refrenceId)}" var="domain"></c:set>
                            <c:set value="${fns:getImageDomainPath(brandes.brandLogo, 100, 50) }" var="url"></c:set>

                            <a href="http://${domain}${zttx}" class="js-img-center" target="_blank">
                                <img src="${res}${url}" alt="" width="70" height="35"/></a>

                            <p><a href="http://${domain}${zttx}" target="_blank">${brandes.brandsName}</a></p>

                        </li>
                    </c:forEach>
                </ul>
            </div>

            <%--新增了一个浏览记录--%>
            <c:if test="${null!=browseHistroy}">
                <div class="recommend" style="border-left:0;border-right: 0;"> 
                    <div class="clearfix">
                        <h3 class="title fl" style="border-top: 0;font-family: 'Microsoft Yahei';">浏览记录:</h3>
                        <a class="fr" href="http://www${zttx}/dealer/dealerShoper/browseHistroy"
                           style="margin-top: 15px;margin-right: 15px;">更多</a>
                    </div> 
                    <c:forEach items="${browseHistroy}" var="product">
                        <dl class="recommend-combox">
                            <dt class="recommend-img"><a href="${ctx}/market/product/${product.productId}"><img
                                    src="${res}${fns:getImageDomainPath(product.productImage, 45, 45) }" alt=""
                                    width="45" height="45"/></a></dt>
                            <dd class="recommend-title"><a href="${ctx}/market/product/${product.productId}"
                                                           title="产品标题">${product.productTitle}</a></dd>
                            <dd class="recommend-price">
                                <c:choose>
                                    <c:when test="${product.type==3}"><span>采购价：<i>￥</i><em>${product.cash}</em></span></c:when>
                                    <c:when test="${product.type==4}"><span>授信价：<i>￥</i><em>${product.credit}</em></span></c:when>
                                    <c:when test="${product.type==5}"><span>返点价：<i>￥</i><em>${product.point}</em></span></c:when>
                                    <c:otherwise><span>吊牌价：<i>￥</i><em>${product.price}</em></span></c:otherwise>
                                </c:choose>
                                <c:if test="${product.type==3 || product.type==4 || product.type==5}">
                                    <i class="ml5"
                                       style="text-decoration: line-through;color:#999;">${product.price}</i>
                                </c:if>
                            </dd>
                        </dl>
                    </c:forEach>
                </div>
            </c:if>
            <div class="contact clear" style="padding: 20px 15px;position: relative;border:0;">
                <img src="${res}/images/fronts/market/contect.png" alt="">

                <div class="qq-area">
                    <script charset="utf-8" type="text/javascript"
                            src="http://wpa.b.qq.com/cgi/wpa.php?key=XzkzODAwMTU5Nl8xNTE2NjRfNDAwMTExODYzN18"></script>
                </div>
            </div>
        </div>


        <div id="form-area" class="js_login_box js-all-throw" style="display: none; position: relative;">
            <div class="ui-box-tanchuang">
                <div class="ui-head"><h3 class="">申请加盟</h3><i>X</i></div>
                <form:form id="applyForm" method="post" data-widget="validator">
                    <style>
                        #applyForm .ui-form-item {
                            padding-left: 0;
                            padding-bottom: 0;
                        }

                        #applyForm .ui-form-explain {
                            display: inline-block;
                            *display: inline;
                            *zoom: 1;
                            padding-top: 0;
                        }
                    </style>
                    <table class="login_box_table mt15">
                        <colgroup>
                            <col width="70"/>
                            <col width="200"/>
                        </colgroup>
                        <tbody>
                        <input id="brandsId" type="hidden" value="${brandesId}" name="brandsId"/>
                        <tr>
                            <td style="padding: 5px 0;">联 系 人：</td>
                            <td style="padding: 5px 0;">
                                    ${dealerUserm.userName}
                            </td>
                        </tr>
                        <tr>
                            <td style="padding: 5px 0;">联系方式：</td>
                            <td style="padding: 7px 0;">
                                <div class="ui-form-item">
                                    <input type="text" name="telphone" class="ui-input"
                                           value="${dealerUserm.userMobile}" required data-display="手机号码"
                                           maxlength="20"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>申请信息：</td>
                            <td>
                                <div class="ui-form-item">
                                    <textarea class="login_box_textarea" name="inviteText" datatype="*" required
                                              data-display="申请信息" maxlength="512"></textarea>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <input id="applyFormSubmit" type="submit" value="提交申请资料" class="button mt5">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form:form>
            </div>
        </div>
        <!-----右边栏目结束--------->


    </div>
</div>

<form:form id="toBalanceForm" action="${ctx}/dealer/dealerShoper/balance" method="post">
    <input type="hidden" id="shopperId" name="shopperIds"/>
    <input type="hidden" name="isClient" value="false"/>
</form:form>

<%--视频主持人--%>
<%-- <jsp:include page="${ctx}/market/brand/rightFlash">
    <jsp:param name="brandsId" value="${brandesId}" />
</jsp:include> --%>
<%-- 结束 --%>
<!-- footer -->
<jsp:include page="/WEB-INF/view/include/footer.jsp"/>
<%-- <jsp:include page="/jsp/common/loginDialog.jsp"/>--%>
<%-- 弹窗登录 --%>


<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${src}/common/base-init.js"></script>
<script src="${src}/fronts/market/jquery.elastislide.js"></script>
<script src="${src}/common/jquery.cart.js"></script>
<script src="${src}/fronts/market/last.js" type="text/javascript"></script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
<%--前台区域选择--%>
<jsp:include page="/WEB-INF/view/common/select_area.jsp"/>
<!--[if lte IE 8]>
<script type="text/javascript" charset="utf-8" src="${res}/scripts/dealer/json2.js"></script>
<![endif]-->
<%--放大镜--%>
<!--<script src="${res}/scripts/plugin/template-simple.js"></script>
<script src="${res}/scripts/plugin/jquery-dateFormat.min.js"></script>-->
<script type="text/javascript">
    $(function () {
        var $div_li = $("div.js-format .js-formatul li");
        $div_li.click(function () {
            $(this).addClass("selected").siblings().removeClass(
                    "selected");
            var index = $div_li.index(this);
            $("div.pro-format-detail > div.pro-format-item").eq(
                    index).show().siblings().hide();
        });

        var $div_li2 = $("div.js-specs .js-specsul li");
        $div_li2.click(function () {
            $(this).addClass("selected").siblings().removeClass(
                    "selected");
            var index = $div_li2.index(this);
            $("div.js-specscon > div.proinfo-specs-items")
                    .eq(index).show().siblings().hide();
        });

        $('.plist .tit').click(function () {
            $(this).next('.plistul').stop(false, true).slideToggle();
            if ($(this).find('i').hasClass('jian')) {
                $(this).find('i').removeAttr("class");
                $(this).find('i').addClass("jia");
            } else {
                $(this).find('i').removeAttr("class");
                $(this).find('i').addClass("jian");
            }
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
                            + '<img src="' + src + '_300x300.jpg" data-glasses="' + src + '" />'
                            + '<span></span>'
                            + '</a>'
                            + '<div class="mark" style=" position:absolute;top:0;left:0;z-index:2;background:#FFFFFF;filter:alpha(opacity:50);opacity:0.5;border:1px solid #333;display:none;cursor: move;"></div>');
        }

        //选中框的改变
        function changeActive(eq) {
            $(".magniul li").removeClass("active");
            $(".magniul li").eq(eq).addClass("active");
        }

        $(".magni-all").on("click", ".prev", function () {//上一张
            if (Num > 0) {
                Num--;
                changeImg(Num);
            }
        });
        $(".magni-all").on("click", ".next", function () {//下一张
            if (Num < (Len - 1)) {
                Num++;
                changeImg(Num);
            }
        });
        $(".magniul li a").click(function () {//单个点击图片的时候
            var index = $(this).parent().index();
            Num = index;
            changeImg(Num);
        });

        seajs.use(["glasses"], function (Glasses) {
            //放大镜
            new Glasses({
                elem: ".magnimg",
                fixedWidth: 400
            });
        });

        $(".attention")
                .on(
                        'click',
                        '.js-collected-brands',
                        function () {
                            $
                                    .post(
                                            "${ctx}/market/brand/attentionNum/add",
                                            {
                                                brandesId: "${productInfo.brandsId}"
                                            },
                                            function (data) {
                                                switch (data.code) {
                                                    case zttx.SUCCESS:
                                                        remind(
                                                                "success",
                                                                "收藏成功",
                                                                1000,
                                                                function () {
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
                                                        location.href = '${ctx}/common/login?loginType=1&redirect='
                                                                + encodeURIComponent(currentUrl);
                                                        break;
                                                    default:
                                                        remind("error",
                                                                data.message);
                                                }
                                            }, "json");
                        });

        setTimeout(function () {
            $.post('${ctx}/market/user_rtk', {
                id: '${productInfo.brandsId}',
                code: 10,
                productId: '${productInfo.refrenceId}'
            });
        }, 2000);

        cart.load();
    });

    function toBalance(productId)
    {
        var skuIds = [];
        var amounts = [];
        var sum = parseInt(0);
        $(".js-skuId").each(function (i, o) {
            var skuId = $(this).val();
            skuIds.push(skuId);
        });
        $(".num-amount").each(function (i, o) {
            var amount = $(this).val();
            amounts.push(amount);
            sum=sum+parseInt(amount);
        });
        if(sum == 0){remind("error","请选择一款颜色尺码!");return}
        var productType = $("input[name=balances]:checked").val();
        $.ajax({
            url: "${ctx}/dealer/dealerShoper/productToBalance",
            method: "post",
            data: {
                skuIds: skuIds.join(","),
                purchaseNum: amounts.join(","),
                productType: productType,
                productId: productId
            },
            dataType: "json",
            async: false,
            success: function(data){
                //console.log(data);
                if(data.code == 126000){
                    $("#shopperId").val(data.object);
                    $("#toBalanceForm").submit();
                }else{
                    remind("error", data.message);
                }
            }
        });
    }

    $("#product_addcar_form").submit(function () {
        //给type赋值
        $("input[name=productType]").val($("input[name=balances]:checked").val());
        if(parseInt($("#startNum").text()) > parseInt($("#js-count").text())){
            remind("error", "未达到起批量");
            return false;
        }
    });

    function joinShop(productId, productType) {
        var objdata = new Object();
        objdata.title = "提示";
        var skuIds = [];
        var amounts = [];
        var _productType;

        if (productType) {
            //有 productType 表示 立即拿样
            _productType = productType;
        } else {
            //正常加入进货单
            _productType = $("input[name=balances]:checked").val();
            if(parseInt($("#js-count").text()) <= 0){
                remind("error", "请选择数量");
                return false;
            }
        }

        $(".js-skuId").each(function (i, o) {
            var skuId = $(this).val();
            skuIds.push(skuId);
        });
        $(".num-amount").each(function (i, o) {
            var amount = $(this).val();
            amounts.push(amount);
        })
        $.post("${ctx}/dealer/dealerShoper/addCart", {
                    skuIds: skuIds.join(","),
                    purchaseNum: amounts.join(","),
                    productType: _productType,
                    productId: productId
                },
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
                            location.href = '${ctx}/dealer/dealerShoper/cart';
                        }
                    } else if (data.code == 111048) {
                        //尚未购买服务
                        objdata.content = "抱歉~您还未支付平台服务费，暂时无法下单马上支付1200元服务费，享受一手货源";
                        confirmDialog(
                                objdata,
                                function () {
                                    window.open("${ctx}/dealer/service/details?refrenceId=S001");
                                });
                    } else if (data.code == 111040) {
                        //已加入购物车
                        if (status == null) {
                            remind("error", "已加入进货单，请勿重复添加");
                        } else {
                            location.href = '${ctx}/dealer/shopper';
                        }
                    } else {
                        remind("error", data.message);
                        //remind("error","此产品已在进货单,无需重复加入");
                    }
                }, "json");
    }
    function applyJoin(productId) {
        $.ajax({
            url: "${ctx}/dealer/joinManage/apply/join",
            method: "POST",
            data: {productId: productId, inviteText: "申请加盟"},
            dataType: "JSON",
            success: function (data) {
                if (data.code == 126000) {
                    remind("success", "已申请加盟");
                } else if (data.code == 126013) {
                    var parames = "?redirect=" + encodeURIComponent(location.href);
                    jumpToLogin(parames);
                } else {
                    remind("error", data.message);
                }
            }
        });
    }


    function addFavor(productId) {
        $.ajax({
            url: "${ctx}/dealer/dealerFavorite/addFavorite",
            method: "POST",
            data: {productId: productId},
            dataType: "JSON",
            success: function (data) {
                if (data.code == 126000) {
                    remind("success", "产品收藏成功", function () {
                        $(obj).html("已收藏");
                    });
                } else if (data.code == 126013) {
                    var parames = "?redirect=" + encodeURIComponent(location.href);
                    jumpToLogin(parames);
                } else {
                    remind("error", data.message);
                }
            }
        });
    }

    var proInfo = {
        init: function () {
            //this.progressBar();//价格对比进度条
            //从cookies 初始记录
        },
        progressBar: function () {
            if ($(".compare-box").length == 0) {
                return false;
            }
            var _price = parseFloat($(".js-dpj-price").text());//吊牌价
            $(window).scroll(function () {
                //全部加载完成，取消window的scroll事件
                var _scrollTop = $(document).scrollTop();//滚动条到顶部距离
                var _top = $(".compare-box").offset().top, _t_st = _top - _scrollTop, _view = $(window).height();
                if (_t_st <= _view) {
                    $(".compare-box-list li").each(function (i, t) {
                        var thisPrice = parseFloat($(this).find(".progress-bar").data("price"));
                        var _color = $(this).find(".progress-bar").data("color");
                        var moveWid = (thisPrice / _price).toFixed(2) * 100;
                        if (moveWid > 100) {
                            //remind("error","吊牌价不对");
                            return;
                        }
                        $(this).find(".progress-bar-cover").css("background", _color).animate({
                            "width": moveWid + "%"
                        }, 1000);
                        //$(window).off("scroll");//执行完毕解除绑定 此处有冲突，不能解绑
                    });
                }
            });
            seajs.use(["gallery/raphael/2.1.2/raphael"], function (Raphael) {
                var chart = function (_data, _color) {
                    if (_data[0] < _data[1]) {
                        return;
                    }

                    $("#myfirstchart").html("");
                    var r = Raphael("myfirstchart", 124, 124);
                    var whiteBG1 = r.circle(62, 62, 62).attr({stroke: "#fff", fill: "#eaeaea"});
                    //计算svg画图时候的路径并给定颜色
                    r.customAttributes.segment = function (x, y, r, a1, a2) {
                        var flag = (a2 - a1) > 180, a1 = (a1 % 360) * Math.PI / 180;
                        a2 = (a2 % 360) * Math.PI / 180;
                        return {
                            path: [
                                ["M", x, y],
                                ["l", r * Math.cos(a1), r * Math.sin(a1)],
                                ["A", r, r, 0, +flag, 1, x + r * Math.cos(a2), y + r * Math.sin(a2)],
                                ["z"]
                            ],
                            fill: _color
                        };
                    };

                    var data = _data, paths = r.set(), start = 0, val;

                    val = 360 / data[0] * data[1];

                    if (data[0] == data[1]) {
                        var testSmall2 = r.circle(62, 62, 62).attr({stroke: "#fff", fill: _color});
                    } else {
                        var testSmall = r.path().attr({
                            segment: [62, 62, 62, start, start += val],
                            stroke: "#fff",
                            "stroke-width": "0"
                        });
                        paths.push(testSmall);
                        paths[0].animate({
                            segment: [62, 62, 62, start,
                                start += val]
                        }, 618, "backIn");
                    }
                    var whiteBG = r.circle(62, 62, 43).attr({stroke: "#fff", fill: "#fff"});
                    var t = r.text(62, 62, "吊牌价").attr({font: '100 12px Microsoft yahei', fill: "#0082cc"});
                };

                var countForm = function (p, c) { //计算成本和利润并赋值
                    $(".js-chart-profit").find("span").html((((p - c) / c) * 100).toFixed(2));
                    $(".js-chart-prime").find("span").html(((c / p) * 100).toFixed(2));
                };

                $(".progress-bar").mouseover(function () {
                    var _lirun = $(this).data("price");
                    var _color = $(this).data("color");
                    if (!_lirun || !_color) {
                        return;
                    }
                    if (_lirun > _price) {
                        return;
                    }
                    countForm(_price, _lirun);
                    chart([_price, _lirun], _color);
                });

                var initPrice = $(".compare-box-list li:first .progress-bar").data("price");
                var initColor = $(".compare-box-list li:first .progress-bar").data("color");

                if (!initPrice || !initColor) {
                    //remind("error","8637直供价没填");
                    return;
                }

                chart([_price, initPrice], initColor);
                countForm(_price, initPrice);
            });
        }
    };
    proInfo.init();

    /**购物车*/
    var cart = {};
    /**购物车 放入产品 添加 条目*/
    cart.add = function (item) {
        if ($("div.real-time-con").find('#id_' + item.productId).size() == 1) {
            remind("error", "已加入进货单，请勿重复添加");
            return;
        }

        var cartItem = [];
        cartItem.push('<dl class="cart-item" id="id_' + item.productId + '">');
        cartItem.push(' <dt class="pic">');
        cartItem.push('  <a href="#"><img src="' + item.image + '" alt="" width="70" height="70"></a>');
        cartItem.push(' </dt>');
        cartItem.push('<dd class="price">');
        cartItem.push('    <span class="text-yahei text-orange text-lg"><span class="text-md">￥</span>' + item.price + '</span>');
        cartItem.push(' </dd>');
        cartItem.push('<dd class="title">' + item.title + '</dd>');
        cartItem.push('${isLogin==true?"<dd class=\"del\"><a class=\"icon icon-delete\" href=\"javascript:;\"  onclick=removeCart(\"'+item.cartId+'\",\"'+item.productId+'\")></a></dd>":"<dd class=\"del\"><a class=\"icon icon-delete\" href=\"javascript:;\"  onclick=cart.remove(\"'+item.productId+'\")></a></dd>"}');
        cartItem.push('</dl>');
        $("div.real-time-con").append(cartItem.join(""));
        $("#cartCount").html(parseInt($("#cartCount").html()) + 1);
    };

    /**从cookies里载入物品到购物车*/

    cart.load = function () {
        seajs.use(["cookie"], function (Cookie) {
            Cookie.get("cart_items", function (s) {
                s = decodeURIComponent(s);
                if (s == "undefined" || s == "") {
                    return;
                }
                if (${isLogin!=null&&isLogin}) {
                    return;
                }
                var cart_items = jQuery.parseJSON(s);
                for (var i = 0; i < cart_items.length; i++) {
                    cart.add(cart_items[i]);
                }
            });
        });
    };

    /**清空所有*/
    cart.clear = function () {
        $('div.real-time-con').empty();
        $("#cartCount").html(0);
        cart.store();
    };


    cart.remove = function (id) {
        $('#id_' + id).detach();
        $("#cartCount").html(parseInt($("#cartCount").html()) - 1);
        cart.store();
    };

    /**从cookies里载入物品到购物车*/
    cart.store = function () {
        seajs.use(["cookie"], function (Cookie) {
            var str_cart_item = "";
            var cart_items = [];
            $("div.real-time-con dl").each(function (i, obj) {
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
            Cookie.set("cart_items", str_cart_item, {path: '/', domain: document.domain});
        });
    };

    /**直接加入本地购物车*/
    function joinShopCookie(productId, code, status) {
        var item = {};
        item.productId = productId;
        item.image = $('a.jqzoom img').attr('src');
        item.title = $('h1.title ').text();
        item.price = $('span.pro-dt-nomber').text();
        cart.add(item);
        cart.store();
        if (status) {
            location.href = '${ctx}/common/login?redirect=${ctx}/dealer/shopper';
        }
    }

    function joinShopCookie1(productId, code, status) {
        if (status) {
            location.href = '${ctx}/common/login?redirect=/market/product/' + productId + '?code=' + code;
        }
    }

    function removeCart(id, proudctId) {
        $.post("${ctx}/dealer/shoper/delete", {id: id}, function (data) {
            if (data.code == 111000) {
                $('#id_' + proudctId).detach();
                $("#cartCount").html(parseInt($("#cartCount").html()) - 1);
            }
        }, "json");
    }

    function removeAll() {
        $.post("${ctx}/dealer/shoper/deleteAll?ids=", function (data) {
            if (data.code == 111000) {
                $('div.real-time-con').empty();
                $("#cartCount").html(0);
            }
        }, "json");
    }

    //调用城市选择插件
    new selectCity({
        elem: "#select_area",
        ensureFn: function (value, text) {
            $("#select_area").text(text).attr("value", value);
        },
        renderName: function (cityName) {
            //初始化的时候，把当前位置的城市名写到 select_area
            $("#select_area").text(cityName);
        }
    });
    /*新页面调整 新加js*/
    seajs.use(['template', 'underscore'], function (Template, _) {

        var $colorlist_li = $("#js-colorlist li");

        //计算现款价，授信价返点价
        function countTwoPrice() {
            var _c_count = 0, _c_xk = 0, _c_sx = 0, _c_fd = 0;

            $(".table-list tr").each(function () {
                var _p_xk = parseFloat($(this).find(".js-p-xk").text()) == NaN ? 0 : parseFloat($(this).find(".js-p-xk").text());       //现款价
                var _p_sx = parseFloat($(this).find(".js-p-sx").text()) == NaN ? 0 : parseFloat($(this).find(".js-p-sx").text());	      //授信价
                var _p_fd = parseFloat($(this).find(".js-p-fdcb").text()) == NaN ? 0 : parseFloat($(this).find(".js-p-fdcb").text());	  //返点价
                var _count = parseFloat($(this).find(".num-amount").val()) == NaN ? 0 : parseFloat($(this).find(".num-amount").val());  //数量

                _c_count += _count;
                _c_xk += (_p_xk * _count);
                _c_sx += (_p_sx * _count);
                _c_fd += (_p_fd * _count);
            });

            $("#js-count").text(_c_count);
            $("#js-xkprick").text(_c_xk.toFixed(2));
            $("#js-sxprick").text(_c_sx.toFixed(2));
            $("#js-fdprick").text(_c_fd.toFixed(2));
        }


        $(".table-list tr").cartInit({}, function () {
        }, function (val, o) {
            //var _amount = $(o).find(".num-amount");
            countTwoPrice();
        });

        if ($colorlist_li.length > 0) {
            $colorlist_li.click(function () {
                var index = $(this).index();
                $colorlist_li.removeClass("selected").eq(index).addClass("selected").addClass("checked");
                $(".table-list").hide().eq(index).show();
            });
        }

    });

</script>

</body>
</html>
