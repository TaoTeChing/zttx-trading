<%@ page import="com.zttx.web.consts.DealerConstant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include  file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的进货单</title>
    <link href="${res}/styles/common/base.css" rel="stylesheet" />
    <link href="${res}/styles/fronts/market/shop-cart.css" rel="stylesheet" />
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top" />
    <div class="header">
        <div class="ts-container">
            <div class="logo">
                <a href="/"><img src="${res}/images/common/logo-old.png"></a> 我的进货单
            </div>
            <div class="step step1 animated slideInDown">
            </div>
        </div>
    </div>
    <div class="main">
        <div class="ts-container">
            <div class="search">
                <form>
                    <label>快速下单：</label>
                    <input id="key" name="key" autocomplete="off" type="text" placeholder="可以直接输入货号快速下单" /><%--<button>搜索</button>--%>
                </form>
                <div class="search-result hide" style="display: none;"></div>
            </div>
            <div class="operation">
                <label><input class="ui-checkbox  js-check-all" type="checkbox"> 全选</label>
                <a class="link js-product-remove-multi" href="javascript:;">移除</a>
                <a class="link js-product-remove-all" href="javascript:;">清空进货单</a>
                <a class="link " href="${ctx}/search" target="_blank">继续进货</a>
            </div>

            <div class="order-all">

                <c:set var="invalid" value="<%=DealerConstant.productState.PRODUCT_INVALID%>" />
                <c:set var="down" value="<%=DealerConstant.productState.PRODUCT_DOWN%>" />
                <c:set var="noright" value="<%=DealerConstant.productState.PRODUCT_NORIGHT%>" />
                <c:set var="credit" value="<%=DealerConstant.productState.PRODUCT_CREDIT%>" />
                <c:set var="point" value="<%=DealerConstant.productState.PRODUCT_POINT%>" /><!--返点-->

                <c:forEach var="shopersModel" items="${shopersModelList}" varStatus="status1">
                    <div class="order-item" style="padding-bottom: 10px;">
                        <div class="m-brand">
                            <label>
                                <input type="checkbox" class="ui-checkbox js-check-sub"/>
                            </label>
                            <span class="">${shopersModel.brandName}</span>
                            <%--<a href=""><i class="icon-cat"></i></a>--%>
                            <span>品牌名称：</span>
                            <a href="http://${fns:getBrandsIdSubDomain(shopersModel.brandesId)}${zttx}">${shopersModel.brandesName}</a>
                        </div>
                        <c:forEach var="dealerShoper" items="${shopersModel.dealerShoperList}" varStatus="status2">
                            <div class="m-con clearfix<c:if test="${dealerShoper.type eq invalid || dealerShoper.type eq down || dealerShoper.type eq noright}"> product-disable</c:if>">
                                <div class="m-pic fl clearfix">
                                    <div class="pic-box clearfix">
                                        <input class="fl js-check" type="checkbox" data-shopper-id="${dealerShoper.refrenceId}" autocomplete="off">
                                        <p class="fl">
                                            <a target="_blank" href="/market/product/${dealerShoper.productId}">
                                                <img src="${res}${fns:getImageDomainPath(dealerShoper.productImage,90, 90)}"  title=""/></a>
                                            <a target="_blank" class="huohao js-huohao" href="/market/product/${dealerShoper.productId}">货号：${dealerShoper.productNo}</a>
                                        </p>
                                    </div>
                                </div>
                                <div class="m-detail fr" data-z="${dealerShoper.discount}">
                                    <div class="o-hd clearfix">
                                        <div class="tit fl">
                                            <span>${dealerShoper.productTitle}</span>
                                            <c:if test="${dealerShoper.type eq invalid}">
                                                <span class="warning"><i class="icon-warning"></i>产品已失效</span>
                                            </c:if>
                                            <c:if test="${dealerShoper.type eq down}">
                                                <span class="warning"><i class="icon-warning"></i>产品已下架</span>
                                            </c:if>
                                            <c:if test="${dealerShoper.type eq noright}">
                                                <span class="warning"><i class="icon-warning"></i>产品已无权限</span>
                                            </c:if>
                                        </div>
                                        <div class="operate fr clearfix">
                                            <c:choose>
                                                <c:when test="${dealerShoper.isPoint}">
                                                    <a href="javascript:;"  class="selected"  data-type="3" data-id="${dealerShoper.refrenceId}" ><i class="icon-fd"></i>返点</a>
                                                    <span class="tips" id="tips1" href="javascript:;"><i class="iconfont">&#xe610;</i></span>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${dealerShoper.isSample}">
                                                        <a href="javascript:;" class="js-nayang ${dealerShoper.productType eq 2 ?'selected':''}" data-type="2" data-id="${dealerShoper.refrenceId}" data-z="${dealerShoper.discount}"><i class="icon-ny"></i>拿样</a>
                                                    </c:if>
                                                    <a href="javascript:;"  class="${dealerShoper.productType eq 0 ?'selected':''}"  data-type="0" data-id="${dealerShoper.refrenceId}" data-z="${dealerShoper.discount}"><i class="icon-yuan"></i>现款</a>
                                                    <c:if test="${dealerShoper.type eq credit}">
                                                        <a href="javascript:;"  class="${dealerShoper.productType eq 1 ?'selected':''}"  data-type="1" data-id="${dealerShoper.refrenceId}" data-z="${dealerShoper.discount}"><i class="icon-sf"></i>授信</a>
                                                    </c:if>
                                                </c:otherwise>
                                            </c:choose>

                                        </div>
                                    </div>
                                    <div class="o-bd attr-item">
                                        <%--<a class="edit-btn" href="javascript:;"><i class="icon-edit"></i>修改</a>--%>
                                        <div class="simple-edit" style="display: none;"></div>
                                        <%--<div class="simple-tips"><i class="iconfont">&#xe630;</i>已无更多产品</div>--%>
                                        <div class="simple-function"
                                             data-toggle="0"
                                             data-isajax="0"
                                             data-productid="${dealerShoper.productId}"
                                             data-shopid="${dealerShoper.refrenceId}"
                                             data-producttype="${dealerShoper.productType}"
                                             data-discount="${dealerShoper.discount eq null?1:dealerShoper.discount}"
                                             data-fdbl="<fmt:formatNumber value="${dealerShoper.pointPercent*100}" type="currencySymbol" pattern="#.#"/>">选择更多<i class="css-arrow"></i></div>
                                        <table class="">
                                            <colgroup>
                                                <col width="320"/>
                                                <col width="140"/>
                                                <col width="60"/>
                                                <col width="150"/>
                                                <col width="200"/>
                                                <col width="140"/>
                                            </colgroup>
                                            <tbody>
                                            <c:forEach var="dealerShopers" items="${dealerShoper.dealerShopersList}" varStatus="state" >
                                                <tr style="${empty dealerShopers.purchaseNum || 0 == dealerShopers.purchaseNum && state.index != 0 ? 'display:none;': ''}">
                                                    <td class="p-colorsize">${dealerShopers.productSkuName}</td>
                                                    <td class="p-price" id="che_price_${dealerShopers.productSkuId}"><fmt:formatNumber value="${dealerShopers.productSkuPrice}" type="currency" pattern="0.00"/><c:if test="${dealerShoper.isPoint}">(<fmt:formatNumber value="${dealerShoper.pointPercent*100}" type="currencySymbol" pattern="#.#"/>%)</c:if></td>
                                                    <td class="p-stock" id="che_stock_${dealerShopers.productSkuId}">${dealerShopers.productStock}</td>
                                                    <td class="p-number">
                                                        <div class="num-operate js-count-1">
                                                            <button href="javascript:void(0)" class="num-minus disable">-</button>
                                                            <input type="text"
                                                                   id="che_amount_${dealerShopers.productSkuId}"
                                                                   value="${empty dealerShopers.purchaseNum ? '0': dealerShopers.purchaseNum}"
                                                                   data-discount="${dealerShoper.discount eq null?1:dealerShoper.discount}"
                                                                   data-price="<fmt:formatNumber value="${dealerShopers.productSkuPrice}" type="currency" pattern="0.00"/>"
                                                                   data-shopsid="${dealerShopers.refrenceId}"
                                                                   data-shopid="${dealerShoper.refrenceId}"
                                                                   data-skuid="${dealerShopers.productSkuId}"
                                                                   data-max="${dealerShopers.productStock}"
                                                                   data-z="${dealerShoper.discount}"
                                                                   data-type="${dealerShoper.productType}"
                                                                   data-fdbl="<fmt:formatNumber value="${dealerShoper.pointPercent*100}" type="currencySymbol" pattern="#.#"/>"
                                                                   class="num-amount"
                                                                   autocomplete="off" />
                                                            <button href="javascript:void(0)" class="num-plus">+</button>
                                                        </div>
                                                        <div class="js-count-2" style="display: none;">
                                                            <a href="javascript:;"><i class="icon-del js-del-single"></i></a>
                                                        </div>
                                                    </td>
                                                    <%--<c:if test="${state.first}">
                                                        <td class="p-xiaoji" rowspan="${fn:length(dealerShoper.dealerShopersList)}">
                                                            <span>数量小计：<em class="js-number" id="number_${dealerShoper.refrenceId}"></em>件<br /></span>
                                                            <span>起批量：<em class="js-min-number">${dealerShoper.startNum}</em>件</span>
                                                        </td>
                                                    </c:if>--%>
                                                    <td class="p-count js-price-single" id="single_${dealerShopers.productSkuId}">0.00</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="o-tatel">
                                        <span>数量小计：<strong class="js-number" id="number_${dealerShoper.refrenceId}"></strong>件</span>
                                        <span>起批量：<strong class="js-min-number">${dealerShoper.startNum}</strong>件</span>
                                    </div>
                                    <div class="o-fd clearfix">
                                        <%--<div class="more fl">
                                            <a href="javascript:;">选择更多</a>
                                        </div>--%>
                                        <div class="count fr">
                                            <span>金额小计：<em class="js-price" id="price_${dealerShoper.refrenceId}">00.00</em></span>
                                            <c:if test="${dealerShoper.productType eq 0 || dealerShoper.productType eq 1}">
                                            <span>折扣优惠: <em class="js-zkj" id="zkj_${dealerShoper.refrenceId}">00.00</em></span>
                                            </c:if>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="m-bottom mt10">
                                <a class="js-product-add-fav" data-productid="${dealerShoper.productId}" href="javascript:;"><i class="icon-star"></i></a>
                                <a class="js-product-remove-single" data-shoper-id="${dealerShoper.refrenceId}" href="javascript:;"><i class="icon-del"></i></a>
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>

            </div>

            <div class="bbar">
                <div class="bbar-left">
                    <label><input class="ui-checkbox js-check-all" type="checkbox"> 全选</label>
                    <a class="link js-product-remove-multi" href="javascript:;">移除</a>
                    <a class="link js-product-remove-all" href="javascript:;">清空进货单</a>
                    <a class="link" href="${ctx}/search" target="_blank"> 继续进货</a>
                    <a class="link js-product-remove-off" href="javascript:;">批量移除失效产品</a>
                </div>
                <div class="bbar-right clearfix" style="width: 700px;">
                    <div class="fl">
                        已选商品
                        <span class="js-allNumber">0</span>件 | 总货款（不含运费）：
                        <span class="js-allPrice">0.00</span><br />（其中：授信产品货款&nbsp;
                        <span class="js-dispribute normols">0.00</span>&nbsp;现款现货产品货款&nbsp;
                        <span class="js-nowproductprice normols">0.00</span>&nbsp;返点产品货款&nbsp;
                        <span class="js-rebateprice normols">0.00</span>）
                    </div>
                    <div class="fr">
                        <button class="ui-button ui-button-lred js-clearing">结 算</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">

        </div>
    </div>
    <form:form id="addlist-form-quick" method="post" action="${ctx}/dealer/dealerShoper/addCart2"/>
    <form:form id="submit-form-balance"  action="${ctx}/dealer/dealerShoper/balance" method="post"></form:form>
    <script id="list_tempalte" type="text/html">
        {{each rows}}
        <tr>
            <td class="p-colorsize">{{$value.productSkuName}}</td>
            <td class="p-price" id="che_price_{{$value.productSkuId}}">{{$formatPrice $value.productSkuPrice}}{{if $value.fdbl != 0}}({{$value.fdbl}}%){{/if}}</td>
            <td class="p-stock" id="che_stock_{{$value.productSkuId}}">{{$value.productStock}}</td>
            <td class="p-number">
                <div class="num-operate js-count-1">
                    <button href="javascript:void(0)" class="num-minus disable">-</button>
                    <input type="text" id="che_amount_{{$value.productSkuId}}" value="0"
                           data-discount="{{$value.discount}}"
                           data-price="{{$value.productSkuPrice}}"
                           data-shopsid=""
                           data-shopid="{{$value.shoperId}}"
                           data-skuid="{{$value.productSkuId}}"
                           data-max="{{$value.productStock}}"
                           data-z="{{$value.discount}}"
                           data-type="{{$value.productType}}"
                           data-fdbl="{{$value.fdbl}}"
                           class="num-amount"
                           autocomplete="off">
                    <button href="javascript:void(0)" class="num-plus">+</button>
                </div>
                <div class="js-count-2" style="display: none;">
                    <a href="javascript:;"><i class="icon-del js-del-single"></i></a>
                </div>
            </td>
            <td class="p-count js-price-single" id="single_{{$value.productSkuId}}">0.00</td>
        </tr>
        {{/each}}
    </script>

    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
    <script src="${res}/scripts/seajs_config.js"></script>
    <script src="${res}/scripts/common.js"></script>
    <script src="${src}/dealer/json2.js"></script>
    <script src="${src}/common/base-init.js"></script>
    <jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
    <script>
        seajs.use(['${jsrel}/dealer/shopper'], function(Shopper){

        });
    </script>
</body>
</html>