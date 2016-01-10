<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-交易管理-采购的订单-发货</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/brand_trade.css"/>
    <style>
        .payrecord {
            border: #febb75 solid 2px;
            padding: 0 6px 10px;
            border-radius: 3px;
            margin: 0 15px;
        }

        .payrecord li {
            margin-top: 10px;
        }

        .detail-status {
            margin-top: 0;
            margin-bottom: 15px;
            padding-bottom: 10px;
        }

        .delivery_box {
            margin-bottom: 0;
        }
    </style>
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
<div class="main layout">
    <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
    <div class="main_con">
        <!-- 面包导航，并不是每个页面都有 -->
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <a class="link" href="${ctx}/brand/order/purorder">交易管理</a>
                <span class="arrow">&gt;</span>
                <a class="link" href="${ctx}/brand/order/purorder">采购的订单</a>
                <span class="arrow">&gt;</span>
                <span class="current">发货</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
                    <jsp:param value="0" name="isShow"/>
                </jsp:include>
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <!-- 步骤条 -->
            <div class="v2-steps mt10">
                <!-- 第三步 -->
                <span class="text1">终端商下单</span>
                <span class="text2">终端商付款</span>
                <span class="text4">终端商确认收货</span>
                <span class="text3 current">发货</span>

                <div class="steps v2-steps-3"></div>
            </div>
            <div class="detail-dtime">
                <div class="inline-block"></div>
                <div class="inline-block"></div>
                <div class="inline-block"></div>
                <div class="inline-block"></div>
                <div class="inline-block"></div>
            </div>

            <div class="detail-status">
                <c:if test="${!empty shipRecordList}">
                    <div class="delivery_box">
                        <h3>订单状态：</h3>
                        <ul>
                            <c:forEach items="${shipRecordList}" var="dealerOrderAction" varStatus="status">
                                <c:choose>
                                    <c:when test="${status.last==true&&status.first==true}">
                                        <li class="first">
                                            <span class="logistics-date">${fns:getTimeFromLong(dealerOrderAction.createTime,"yyyy-MM-dd HH:mm:ss")} </span>
                                            <span class="logistics-log">${dealerOrderAction.actName}&nbsp;${dealerOrderAction.actContent}</span>
                                        </li>
                                    </c:when>
                                    <c:when test="${status.last==true&&status.first==false}">
                                        <li class="end">
                                            <span class="logistics-date">${fns:getTimeFromLong(dealerOrderAction.createTime,"yyyy-MM-dd HH:mm:ss")} </span>
                                            <span class="logistics-log">${dealerOrderAction.actName}&nbsp;${dealerOrderAction.actContent}</span>
                                        </li>
                                    </c:when>
                                    <c:when test="${status.first}">
                                        <li class="start">
                                            <span class="logistics-date">${fns:getTimeFromLong(dealerOrderAction.createTime,"yyyy-MM-dd HH:mm:ss")} </span>
                                            <span class="logistics-log">${dealerOrderAction.actName}&nbsp;${dealerOrderAction.actContent}</span>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="next">
                                            <span class="logistics-date">${fns:getTimeFromLong(dealerOrderAction.createTime,"yyyy-MM-dd HH:mm:ss")} </span>
                                            <span class="logistics-log">${dealerOrderAction.actName}&nbsp;${dealerOrderAction.actContent}</span>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
            </div>

            <form:form method="post" id="sendForm" name="sendForm" action="${ctx}/brand/order/confirmSend">
                <div class="detail-contants">
                    <div class="send-steps">
                        <div class="detail-print">
                            <span class="to-look inline-block">第一步</span>
                            <span href="javascript:;" class="inline-block to-print">确认发货及交易详情信息</span>
                        </div>
                        <div class="send-ordertime">
                            <span>交易订单号：${order.orderId}</span>
                            <input type="hidden" name="orderId" value="${order.orderId}"/>
                            <input type="hidden" name="orderRefrenceId" value="${order.refrenceId}"/>
                            <input type="hidden" name="brandId" value="${order.brandId}"/>
                            <input type="hidden" name="brandName" value="${order.brandName}"/>
                            <span class="sixfont ml15">下单时间：${fns:getStringTime(order.createTime,"yyyy-MM-dd") }</span>
                        </div>
                        <table class="detail_table sendall-table">
                            <colgroup>
                                <col width="135"/>
                                <col width="245"/>
                                <col width="120"/>
                                <col width="170"/>
                                <col width="325"/>
                            </colgroup>
                            <thead>
                            <tr>
                                <th>品牌</th>
                                <th>货号</th>
                                <th>数量（件）</th>
                                <th>金额（元）</th>
                                <th>备忘</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${order.brandsName}</td>
                                <td class="tdtextl">
                                    <c:set var="lenPro" value="${fn:length(orderModels)}"/>
                                    <c:forEach var="product" items="${orderModels}" varStatus="st">
                                        ${product.productNo}<c:if test="${st.index<lenPro-1}">、</c:if>
                                    </c:forEach>
                                </td>
                                <td>${order.productCount}</td>
                                <td>
                                    <c:set var="yhjg" value="${order.adjustPrice < 0 ? order.adjustPrice*-1:order.adjustPrice}"/>
                                    <strong style="display:block">${order.productPrice}
                                        <c:if test="${order.adjustPrice < 0 || order.adjustPrice > 0}"><br/>
                                        (${order.adjustPrice >= 0 ? "加价":"优惠"}:${yhjg})</c:if>
                                    </strong>
                                    <span class="sixfont">
                                    	<c:choose>
				                            <c:when test="${order.dealerOrderType==3}">
				                            	（品牌商承担运费）
				                            </c:when>
				                            <c:otherwise>
				                            	（物流：${empty order.adjustFreight ? "物流未设定":order.adjustFreight}）
				                            </c:otherwise>
				                        </c:choose>
                                    </span>
                                    <c:if test="${order.dealerOrderType!=3}">
                                    <c:set value="${order.payment}" var="payment"/>
                                    <c:if test="${order.frePayState==1}">
                                        <c:set value="${order.payment+order.adjustFreight}" var="payment"/>
                                    </c:if>
                                    <strong style="display:block">已付款金额:${payment}</strong>
                                    </c:if>
                                </td>
                                <td class="tdtextl orderbox">
                                    <input type="hidden" name="refrenceId" value="${order.refrenceId}"/>
                                    <p>终端商留言：<span title="${order.remark}">${fns:trimLongText(order.remark, 30)}</span></p>
                                    <p>品牌商备注：<span title="${order.brandRemark}" id="bMess">${fns:trimLongText(order.brandRemark, 30)}</span>
                                        <a style="color: #0082CC;" class="remarks" href="javascript:;"
                                            data-colorindex="${order.levelMark}" data-text="${order.brandRemark}">修改</a>
                                    </p>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="send-buyerinfo">
                            终端商信息：
                            <span id="dealerMessage">
                             ${fns:getFullArea(order.dealerAddrProvince,order.dealerAddrCity,order.dealerAddrArea,"/")} ${order.dealerAddress}，${order.postCode}，${order.shipName}，
                             <c:if test="${not empty order.dealerTel&& not empty order.dealerMobile}">${order.dealerTel}/${order.dealerMobile}</c:if>
                             <c:if test="${not empty order.dealerTel&& empty order.dealerMobile}">${order.dealerTel}</c:if>
                             <c:if test="${not empty order.dealerMobile&& empty order.dealerTel}">${order.dealerMobile}</c:if>
                            </span>
                            <c:if test="${order.orderStatus<=2 && order.frePayState==0}">
                                <a style="color: #0082CC;" class="js_updateAddress" href="javascript:;"
                                   data-areano="${order.areaNo}" data-orderid="${order.orderId}">修改</a>
                            </c:if>
                        </div>
                    </div>
                    <div class="send-steps mt20">
                        <div class="detail-print">
                            <span class="to-look inline-block">第二步</span>
                            <span href="javascript:;" class="inline-block to-print">确认发货数量</span>
                        </div>
                        <c:if test="${not empty listPay}">
                            <div class="payrecord mt10">
                                <ul>
                                    <c:set var="recordAmount" value="${pay[0].payAmount}"/>
                                    <c:forEach var="pay" items="${listPay}">
                                        <c:set var="recordAmount" value="${recordAmount+pay.payAmount}"/>
                                        <li>
                                            ${fns:getStringTime(pay.createTime,"yyyy-MM-dd HH:MM:ss") }
                                            支付 ${pay.payAmount} 元 共 <span>${recordAmount}</span> 元
                                        </li>
                                    </c:forEach>

                                </ul>

                            </div>
                        </c:if>

                        <div class="send-tosure mt20 ml15">
                            发货数量确认：
                            <label>
                                <input type="radio" name="sendAll" class="ui-radio sendallradio js-sendall-radio" checked="checked" value="true"/>
                                全部发出
                            </label>
                            <label>
                                <input type="radio" name="sendAll" class="ui-radio senpartradio js-sendpart-radio" value="false"/>
                                部分发出
                            </label>
                        </div>
                    </div>
                    <div class="detail_table sendall-products js-sendall">
                        <table class="detailed-list">
                            <colgroup>
                                <col width="135"/>
                                <col width="135"/>
                                <c:choose>
	                            	<c:when test="${order.dealerOrderType==3}">
	                            		<col width="85">
	                            		<col width="85">
	                            		<col width="85">
	                            	</c:when>
	                            	<c:otherwise>
	                            		<col width="85">
	                            	</c:otherwise>
	                            </c:choose>
                                <col width="85"/>
                                <col width="85"/>
                                <col width="100"/>
                                <col width="110"/>
                                <col width="140"/>
                            </colgroup>
                            <tr>
                                <th>商品信息</th>
                                <th style="text-align: left;">颜色 | 尺码</th>
                                <c:choose>
	                            	<c:when test="${order.dealerOrderType==3}">
	                            		<th>返点价</th>
	                            		<th>返点比例</th>
	                            		<th>成本价</th>
	                            	</c:when>
	                            	<c:otherwise>
	                            		<th>单价</th>
	                            	</c:otherwise>
	                            </c:choose>
                                <th>购买数量（件）</th>
                                <th>已发数量（件）</th>
                                <th>金额（元）</th>
                                <th style="text-align: left;">数量小计（件）</th>
                                <th style="text-align: left;">金额小计（元）</th>
                            </tr>
                            <c:set var="maxIndex" value="0"/>
                            <c:forEach var="product" items="${orderModels}" varStatus="sta">
                                <tr class="js-census-sum">
                                    <td class="goods-td cell-1">
                                        <br/><c:set var="url" value="${product.productImage}"/>
                                        <img src="${res}${fns:getImageDomainPath(url, 160, 160)}" width="90" height="90"/><br/>
                                        <a href="${ctx}/market/product/${product.productId}" target="_blank">货号:${product.productNo}</a>
                                    </td>
                                    <td class="goods-td" ${order.dealerOrderType==3?'colspan="7"':'colspan="5"'}>
                                        <table style="width: 100%;">
                                            <c:forEach var="attr" items="${product.itemsModels}">
                                                <c:choose>
                                                    <c:when test="${not empty attr.quantity}">
                                                        <c:set var="buyCount" value="${attr.quantity}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set var="buyCount" value="0"/>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${not empty attr.shipCount}">
                                                        <c:set var="sendCount" value="${attr.shipCount}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set var="sendCount" value="0"/>
                                                    </c:otherwise>
                                                </c:choose>
                                                <tr class="attributes">
                                                    <input type="hidden" name="sendAtts[${maxIndex}].sendNum" value="${buyCount-sendCount}" class="hidDiv"/>
                                                    <td ${order.dealerOrderType==3?'style="text-align: left;width: 140px;"':'style="text-align: left;width: 156px;"' }>${attr.productSkuName}</td>
                                                    <c:choose>
						                            	<c:when test="${order.dealerOrderType==3}">
						                            		<td style="width: 80px;"><fmt:formatNumber value="${attr.price}" pattern="0.00"/></td>
						                            		<td style="width: 80px;"><fmt:formatNumber type="percent" value="${attr.pointPercent }"/></td>
						                            		<td class="js-buyprice" style="width: 80px;"><span><fmt:formatNumber value="${attr.price*(1-attr.pointPercent)}" pattern="0.00"/></span></td>
						                            	</c:when>
						                            	<c:otherwise>
						                            		<td class="js-buyprice" style="width: 98px;"><!--单价-->
		                                                        <c:choose>
		                                                            <c:when test="${attr.adjustPrice != null && attr.adjustPrice.doubleValue() >= 0}">
		                                                                <span><fmt:formatNumber
		                                                                           value="${attr.adjustPrice/attr.quantity}"
		                                                                           type="currency"
		                                                                           pattern="0.00"/></span>
		                                                                <s style="color: #AAAAAA;margin-left: 5px;"><fmt:formatNumber
		                                                                           value="${attr.price}"
		                                                                           type="currency"
		                                                                           pattern="0.00"/></s>
		                                                            </c:when>
		                                                            <c:otherwise>
		                                                                <c:choose>
		                                                                    <c:when test="${attr.discount != null && attr.discount.doubleValue() != 0 && attr.discount.doubleValue() != 1 }">
		                                                                        <c:choose>
		                                                                            <c:when test="${attr.oldPrice != null}">
		                                                                                <span><fmt:formatNumber
		                                                                                           value="${attr.price}"
		                                                                                           pattern="0.00"/></span>
		                                                                            </c:when>
		                                                                            <c:otherwise>
		                                                                                <span><fmt:formatNumber
		                                                                                           value="${attr.price*attr.discount}"
		                                                                                           pattern="0.00"/></span>
		                                                                                <s style="color: #AAAAAA;margin-left: 5px;"><fmt:formatNumber
		                                                                                           value="${attr.price}"
		                                                                                           pattern="0.00"/></s>
		                                                                            </c:otherwise>
		                                                                        </c:choose>
		                                                                    </c:when>
		                                                                    <c:otherwise>
		                                                                        <span><fmt:formatNumber value="${attr.price}"
		                                                                                          pattern="0.00"/></span>
		                                                                    </c:otherwise>
		                                                                </c:choose>
		                                                            </c:otherwise>
		                                                        </c:choose>
		                                                    </td>
						                            	</c:otherwise>
					                            	</c:choose>
                                                    <td class="js-buynumber" style="width: 98px;">${buyCount}</td>
                                                    <td class="js-alrnumber" style="width: 98px;">${sendCount}</td>
                                                    <td class="last-td js-summoney" style="width: 114px;"><!--金额-->
                                                    	<c:choose>
							                            	<c:when test="${order.dealerOrderType==3}">
							                            		<fmt:formatNumber value="${attr.price*(1-attr.pointPercent)*attr.quantity}" pattern="0.00"/>
							                            	</c:when>
							                            	<c:otherwise>
							                            		<c:choose>
		                                                            <c:when test="${attr.adjustPrice != null && attr.adjustPrice.doubleValue() >= 0}">
		                                                                <fmt:formatNumber
		                                                                           value="${attr.adjustPrice}"
		                                                                           pattern="0.00"/>
		                                                            </c:when>
		                                                            <c:otherwise>
		                                                                <c:choose>
		                                                                    <c:when test="${attr.discount != null && attr.discount.doubleValue() != 0 && attr.discount.doubleValue() != 1 }">
		                                                                        <c:choose>
		                                                                            <c:when test="${attr.oldPrice != null}">
		                                                                                <fmt:formatNumber
		                                                                                           value="${attr.price * attr.quantity}"
		                                                                                           pattern="0.00"/>
		                                                                            </c:when>
		                                                                            <c:otherwise>
		                                                                                <fmt:formatNumber
		                                                                                           var="discountPrice"
		                                                                                           value="${attr.price*attr.quantity*(1-attr.discount)}"
		                                                                                           pattern="0.00"/>
		                                                                                <fmt:formatNumber
		                                                                                           value="${attr.price * attr.quantity - discountPrice}"
		                                                                                           pattern="0.00"/>
		                                                                            </c:otherwise>
		                                                                        </c:choose>
		                                                                    </c:when>
		                                                                    <c:otherwise>
		                                                                        <fmt:formatNumber
		                                                                                   value="${attr.price * attr.quantity}"
		                                                                                   pattern="0.00"/>
		                                                                    </c:otherwise>
		                                                                </c:choose>
		                                                            </c:otherwise>
		                                                        </c:choose>
							                            	</c:otherwise>
						                            	</c:choose>
                                                    </td>
                                                </tr>
                                                <c:set var="maxIndex" value="${maxIndex+1}"/>
                                            </c:forEach>
                                        </table>
                                    </td>
                                    <td class="goods-td js-count-total" style="vertical-align: middle;text-align: left;">

                                    </td>
                                    <td class="goods-td last-td js-price-total" style="vertical-align: middle;text-align: left;">

                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

                    <div class="detail_table senpart-products js-sendpart" style="display: none;">
                        <table class="detailed-list">
                            <colgroup>
                                <col width="135"/>
                                <col width="135"/>
                                <c:choose>
	                            	<c:when test="${order.dealerOrderType==3}">
	                            		<col width="85">
	                            		<col width="85">
	                            		<col width="85">
	                            	</c:when>
	                            	<c:otherwise>
	                            		<col width="85">
	                            	</c:otherwise>
	                            </c:choose>
                                <col width="130"/>
                                <col width="100"/>
                                <col width="110"/>
                                <col width="140"/>
                            </colgroup>
                            <tr>
                                <th>商品信息</th>
                                <th style="text-align: left;">颜色 | 尺码</th>
                                <c:choose>
	                            	<c:when test="${order.dealerOrderType==3}">
	                            		<th>返点价</th>
	                            		<th>返点比例</th>
	                            		<th>成本价</th>
	                            	</c:when>
	                            	<c:otherwise>
	                            		<th>单价</th>
	                            	</c:otherwise>
	                            </c:choose>
                                <th>数量（件）</th>
                                <th>金额（元）</th>
                                <th style="text-align: left;">数量小计（件）</th>
                                <th style="text-align: left;">金额小计（元）</th>
                            </tr>
                            <c:set var="maxIndex" value="0"/>
                            <c:forEach var="product" items="${orderModels}" varStatus="sta">
                                <tr class="js-census-sum">
                                    <td class="goods-td cell-1">
                                        <br/><input type="checkbox" class="ui-checkbox js-mainchk"/>
                                        <c:set var="url" value="${product.productImage}"/>
                                        <img src="${res}${fns:getImageDomainPath(url, 160, 160)}" width="90" height="90"/><br/>
                                        <a href="${ctx}/market/product/${product.productId}">货号:${product.productNo}</a>
                                    </td>
                                    <td class="goods-td" ${order.dealerOrderType==3?'colspan="6"':'colspan="4"'}>
                                        <table style="width: 100%;">
                                            <c:forEach var="attr" items="${product.itemsModels}">
                                                <c:choose>
                                                    <c:when test="${not empty attr.quantity}">
                                                        <c:set var="buyCount" value="${attr.quantity}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set var="buyCount" value="0"/>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${not empty attr.shipCount}">
                                                        <c:set var="sendCount" value="${attr.shipCount}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set var="sendCount" value="0"/>
                                                    </c:otherwise>
                                                </c:choose>
                                                <tr class="attTr attributes">
                                                    <td ${order.dealerOrderType==3?'style="width: 140px;text-align: left;text-indent: 10px;"':'style="width: 163px;text-align: left;text-indent: 10px;"' }>
                                                        <input type="checkbox" class="ui-checkbox mr5 js-subchk" name="sendAtts[${maxIndex}].isSelect" value="1"/>
                                                        <input type="hidden" name="sendAtts[${maxIndex}].orderItemId" value="${attr.orderItemId}" disabled />
                                                        ${attr.productSkuName}
                                                    </td>
                                                    <c:choose>
						                            	<c:when test="${order.dealerOrderType==3}">
						                            		<td style="width: 80px;"><fmt:formatNumber value="${attr.price}" pattern="0.00"/></td>
						                            		<td style="width: 80px;"><fmt:formatNumber type="percent" value="${attr.pointPercent }"/></td>
						                            		<td class="js-buyprice" style="width: 80px;"><span><fmt:formatNumber value="${attr.price*(1-attr.pointPercent)}" pattern="0.00"/></span></td>
						                            	</c:when>
						                            	<c:otherwise>
						                            		<td class="js-buyprice" style="width: 103px;"><!--价格-部分发货-->
		                                                        <c:choose>
		                                                            <c:when test="${attr.adjustPrice != null && attr.adjustPrice.doubleValue() >= 0}">
		                                                                <span><fmt:formatNumber
		                                                                           value="${attr.adjustPrice/attr.quantity}"
		                                                                           pattern="0.00"/></span>
		                                                                <s style="color: #AAAAAA;margin-left: 5px;"><fmt:formatNumber
		                                                                           value="${attr.price}"
		                                                                           pattern="0.00"/></s>
		                                                            </c:when>
		                                                            <c:otherwise>
		                                                                <c:choose>
		                                                                    <c:when test="${attr.discount != null && attr.discount.doubleValue() != 0 && attr.discount.doubleValue() != 1 }">
		                                                                        <c:choose>
		                                                                            <c:when test="${attr.oldPrice != null}">
		                                                                                <span><fmt:formatNumber
		                                                                                           value="${attr.price}"
		                                                                                           pattern="0.00"/></span>
		                                                                            </c:when>
		                                                                            <c:otherwise>
		                                                                                <span><fmt:formatNumber
		                                                                                           value="${attr.price * attr.discount}"
		                                                                                           pattern="0.00"/></span>
		                                                                                <s style="color: #AAAAAA;margin-left: 5px;"><fmt:formatNumber
		                                                                                           value="${attr.price}"
		                                                                                           pattern="0.00"/></s>
		                                                                            </c:otherwise>
		                                                                        </c:choose>
		                                                                    </c:when>
		                                                                    <c:otherwise>
		                                                                        </span><fmt:formatNumber
		                                                                                   value="${attr.price}"
		                                                                                   pattern="0.00"/></span>
		                                                                    </c:otherwise>
		                                                                </c:choose>
		                                                            </c:otherwise>
		                                                        </c:choose>
		                                                    </td>
						                            	</c:otherwise>
						                            </c:choose>
                                                    <td class="js-buynumber" style="width: 157px;">
                                                        <input type="hidden" name="sendAtts[${maxIndex}].sendNum" value="${buyCount-sendCount}" class="hidDiv" id="sendNum" disabled/>
                                                        <input class="ui-input pronumber canSendNum js-number" data-max="${buyCount-sendCount}" data-already="${sendCount}"
                                                               value="${buyCount-sendCount}" disabled>
                                                    </td>
                                                    <td class="last-td js-summoney" style="width: 120px;">
                                                        <%--<fmt:formatNumber value="${attr.price * buyCount}" pattern="0.00"/>--%>
                                                        0.00
                                                    </td>
                                                </tr>
                                                <c:set var="maxIndex" value="${maxIndex+1}"/>
                                            </c:forEach>
                                        </table>
                                    </td>

                                    <td class="goods-td js-count-total" style="vertical-align: middle;text-align: left;">
                                        0
                                    </td>
                                    <td class="goods-td last-td js-price-total" style="vertical-align: middle;text-align: left;">
                                        0
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

                    <div class="price_box">
                        <label>进货总量：</label><strong class="js-all-amount"></strong>&nbsp;件
                        <span class="line">|</span>
                        <label>总价：</label>
                        <span class="js-all-total">
                            ${order.productPrice}</span>元 ${order.adjustPrice >= 0 ? "+":"-"}
                        <span class="js-all-discount">${yhjg}</span>元
                        <span>（${order.adjustPrice > 0 ? "加价":"优惠"}）</span>+
                        	<c:choose>
		                        <c:when test="${order.dealerOrderType==3}">
		                        	0.00元
		                        </c:when>
		                        <c:otherwise>
		                        	<c:if test="${!empty order.adjustFreight}">
		                                <span class="js-all-expennum">${order.adjustFreight}</span>元
		                            </c:if>
		                            <c:if test="${empty order.adjustFreight}">未设定</c:if>
		                        </c:otherwise>
		                    </c:choose>
                        <span>（运费）</span>=<strong class="js-all-result">&nbsp;</strong>元
                    </div>
                    <div class="send-steps mt20">
                        <div class="detail-print">
                            <span class="to-look inline-block">第三步</span>
                            <span href="javascript:;" class="inline-block to-print">选择物流服务</span>
                        </div>
                        <table class="detail_table sendall-log-table mt10">
                            <colgroup>
                                <col width="135"/>
                                <col width="245"/>
                                <col width="160"/>
                            </colgroup>
                            <thead>
                            <tr>
                                <th class="tdtextl">物流公司名</th>
                                <th>单号</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <input type="hidden" name="logisticName" id="logisticName"/>
                            <input type="hidden" name="shipNumber" id="shipNumber"/>
                            <c:forEach items="${brandLogisticsList}" var="brandLogistics">
                                <tr class="wlTr">
                                    <td class="tdtextl" style="line-height: 32px;">${brandLogistics.logisticName} </td>
                                    <td><input type="text" class="ui-input logisNum"></td>
                                    <td><a href="javascript:void(0)" class="ui_button ui_button_lblue js-sendgoods">发货</a></td>
                                </tr>
                            </c:forEach>
                            <tr class="wlTr">
                                <td class="tdtextl" style="line-height: 32px;">其他物流：
                                    <input type="text" class="ui-input" style="width:130px" name="customLogisName" id="cusLogis">
                                </td>
                                <td><input type="text" class="ui-input logisNum"></td>
                                <td>
                                    <a href="javascript:void(0)" class="ui_button ui_button_lblue js-sendgoods">发货</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
</div>
</div>
<%@ include file="bottom.jsp" %>

<jsp:include page="/WEB-INF/view/common/dialog/remark_dialog.jsp"/>
<jsp:include page="/WEB-INF/view/common/dialog/updateAddress_dialog.jsp">
    <jsp:param value="${order.dealerAddress}" name="dealerAddress"/>
    <jsp:param value="${order.shipName}" name="shipName"/>
    <jsp:param value="${order.dealerTel}" name="dealerTel"/>
    <jsp:param value="${order.dealerMobile}" name="dealerMobile"/>
</jsp:include>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script src="${src}/brand/brand_trade.js"></script>
<script>
    $(document).ready(function () {
        var sendAll = $(".send-tosure").find("input[name=sendAll]").val();
        $(".send-tosure").on("click", ".js-sendall-radio", function () {
            $(".js-sendall").show();
            $(".js-sendpart").hide();

            $(".js-sendpart").find("input[type=hidden]").each(function () {
                $(this).attr("disabled", true);
            });
            $(".js-sendall").find("input[type=hidden]").each(function () {
                $(this).removeAttr("disabled");
            });
            sendAll = true;
        });
        $(".send-tosure").on("click", ".js-sendpart-radio", function () {
            $(".js-sendall").hide();

            $(".js-sendall").find("input[type=hidden]").each(function () {
                $(this).attr("disabled", true);
            });
            $(".js-sendpart").find("input[type=hidden]").each(function () {
                $(this).removeAttr("disabled");
            });
            $(".js-sendpart").show();
            sendAll = false;
        });
        //判断订单合法状态
        if ('${status}' != '') {
            remind("error", '${status}');
            window.location.href = "/brand/order/purorder";
        }

        var chkPrice = 0;//勾选和输入的价格

        function partClick(my) {
            var priceall = 0;
            var countall = 0;

            my.parents(".js-census-sum").find(".js-subchk:checked").each(function () {
                var _input = $(this).parent().parent().find(".canSendNum");
                var price = parseFloat($(this).parent().parent().find(".js-buyprice span").text());
                var count = parseFloat(_input.val());
                var _max = _input.data("max");
                var chengyi = parseFloat(price * count);

                if (count > _max) {
                    countall = _max;
                    chengyi = parseFloat(price * countall);
                }

                priceall += chengyi;//总价格
                countall += count;//总数量

                if (count > _max) {
                    countall = _max;
                    chengyi = parseFloat(price * countall);
                }

                $(this).parents(".js-census-sum").find(".js-count-total").text(countall);//数量小计
                $(this).parents(".js-census-sum").find(".js-price-total").text(priceall.toFixed(2));//金额小计

                $(this).parent().parent().find(".js-summoney").text(chengyi.toFixed(2));

                _input.prop("disabled", false);
                if (_max == 0) {
                    _input.prop("disabled", true);
                }
            });

            if (my.parents(".js-census-sum").find(".js-subchk:checked").size() == 0) {//没有选中的时候数量初始化
                my.parents(".js-census-sum").find(".js-count-total").text(0);//数量小计
                my.parents(".js-census-sum").find(".js-price-total").text(0);//金额小计
            }
            chkPrice = partPrice();
        }

        function partPrice() {//计算勾选、以及输入的金额
            var partPriceNum = 0;
            if ($(".js-sendpart").is(":visible")) {
                $(".senpart-products .js-census-sum").each(function () {
                    var partText = parseFloat($(this).find(".js-price-total").text());
                    partPriceNum += partText;
                });
            }
            return partPriceNum;
        }

        /*全部发货-计算总数量总价格以及结果*/
        function alltotal() {
            var priceall = 0;
            var countall = 0;
            //var priceall = parseFloat($(".js-all-total").text() == "" ? 0 : $(".js-all-total").text());
            var discount = parseFloat($(".js-all-discount").text() == "" ? 0 : $(".js-all-discount").text());//优惠价格
            var expennum = parseFloat($(".js-all-expennum").text() == "" ? 0 : $(".js-all-expennum").text());//运费

            $(".js-sendall .attributes").each(function () {
                var price = parseFloat($(this).find(".js-buyprice span").text());
                var count = parseFloat($(this).find(".js-buynumber").text());
                var countnext = parseFloat($(this).find(".js-alrnumber").text());
                var rellcount = parseFloat($(this).find(".js-buynumber").text());
                count -= countnext;
                var chengyi = parseFloat(price * count);

                priceall += chengyi;//总价格
                countall += rellcount;//总数量

                $(this).find(".js-summoney").text(chengyi.toFixed(2));

            });

            $(".js-all-amount").text(countall);//进货总量
            $(".js-all-total").text(priceall.toFixed(2));//总价
            $(".js-all-result").text((priceall + ${order.adjustPrice} + expennum).toFixed(2));//结果
        }

        alltotal();

        chkPrice = $(".js-all-total").text();//初始的发货金额，全部发货的最大值,选择部分发货，并且会修改此值
        //console.log(chkPrice);
        $(".canSendNum").on("keyup", function (ev) {
            var _this = this;
            var max = $(this).data("max");
            var _val = $(this).val();
            var _max = $(this).data("max");
            var hidSendNum = $(this).parents(".attTr").find("#sendNum");

            if (_val == "") {
                return false;
            }

            hidSendNum.val(_val);

            if (parseInt($(this).val()) > max) {
                remind("error", "发货的产品数量不能多于需要发货的总数量", function () {
                    $(_this).val(max);
                });
                hidSendNum.val(max);
            }

            partClick($(this));
        });
        //全部发货数量、金额小计 20140419
        $(".js-sendall .js-census-sum").each(function () {

            var inx = $(this).find(".js-buynumber").length;
            var item = $(this).find(".js-buynumber");
            var prc = $(this).find(".js-summoney");
            var alritem = $(this).find(".js-alrnumber");

            var count = 0;
            var price = 0;
            for (i = 0; i < inx; i++) {
                var retcount = parseFloat(item.eq(i).text()) - parseFloat(alritem.eq(i).text());

                count += retcount;
                price += parseFloat(prc.eq(i).text());
            }
            $(this).find(".js-count-total").text(count);
            $(this).find(".js-price-total").text(price.toFixed(2));

        });

        function chk_main_show() {//主复选框
            $(".js-census-sum").each(function () {
                var sublen = $(this).find(".js-subchk").length;
                var childlen = $(this).find(".js-childchk").length;
                var subchklen = $(this).find(".js-subchk:checked").length;
                var childchklen = $(this).find(".js-childchk:checked").length;

                if (sublen == subchklen && childlen == childchklen) {
                    $(this).find(".js-mainchk").prop("checked", true);
                }
                if (sublen !== subchklen || childlen !== childchklen) {
                    $(this).find(".js-mainchk").prop("checked", false);
                }
            });
        }

        function chk_sub_show() {//次复选框
            $(".js-census-sum tr").each(function () {
                var childlen = $(this).find(".js-childchk").length;
                var childchklen = $(this).find(".js-childchk:checked").length;
                if (childlen == childchklen) {
                    $(this).find(".js-subchk").prop("checked", true);
                }
                if (childlen !== childchklen) {
                    $(this).find(".js-subchk").prop("checked", false);
                }
            });
            chk_main_show();
        }

        //总全选
        $(".js-mainchk").click(function () {

            var _input = $(this).parents(".js-census-sum").find(".js-number");
            _input.prop("disabled", true);

            $(this).parents(".js-census-sum").find("input[type=checkbox]").prop("checked", this.checked);
            //partotal();
            partClick($(this));
        });
        //部分全选
        $(".js-subchk").click(function () {

            var _input = $(this).parent().parent().find(".canSendNum");
            var _max = _input.data("max");

            _input.prop("disabled", true);
            $(this).parents("tr").eq(0).find(".size-chk").prop("checked", this.checked);
            chk_main_show();
            //partotal();
            partClick($(this));
            if (_max == 0) {
                remind("success", "此货物已发完");
            }

        });

        //提交表单
        $(".js-sendgoods").each(function () {
            $(this).click(function () {
                if (!sendAll) {//部分发货
                    if ($(".mr5:checked").length < 1) {
                        remind("error", '至少勾选一个产品');
                        return;
                    }

                    $(".mr5").each(function () {
                        var attTr = $(this).parents(".attTr");
                        if ($(this).prop("checked")) {
                            attTr.find("input[type=hidden]").each(function () {
                                $(this).removeAttr("disabled");
                            });
                        } else {
                            attTr.find("input[type=hidden]").each(function () {
                                $(this).attr("disabled", false);
                            });
                        }
                    });
                }
                //判断物流信息是否完整
                $("#shipNumber").val("");
                $("#logisticName").val("");
                var _logisNum = $(this).parents(".wlTr").find(".logisNum").val();
                var _logisticName = $(this).parents(".wlTr").find(".tdtextl").html();
                if ($(this).parents(".wlTr").find("#cusLogis").length > 0 && $(this).parents(".wlTr").find("#cusLogis").val() != "") {
                    _logisticName = $("#cusLogis").val();
                }
                $("#shipNumber").val(_logisNum);
                $("#logisticName").val(_logisticName);
                var logisticName = $("#logisticName").val();
                var logisNum = $("#shipNumber").val();
                var temp = $(this).parents(".wlTr").find(".ui-input");
                if (temp.attr("id") == "cusLogis") {
                    if ($.trim($("#cusLogis").val()) == "") {
                        remind("error", "请填写物流公司名称");
                        $("#cusLogis").focus();
                        return false;
                    }
                }
                if ($.trim(logisNum) != "" && $.trim(logisticName) != "") {
                    var amount = '${order.productPrice}';//总价
                    var yhjg = '${yhjg}';//优惠或加价
                    var payment =${order.payment};//已付金额
                    var payState =${order.payState};
                    var frePayState =${order.frePayState};
                    var refundStatus = '${order.refundStatus}';
                    if (1 == refundStatus || 2 == refundStatus || 3 == refundStatus) {
                        confirmDialog("退款申请中，确认继续发货吗？", function () {
                            TradePurorder.send(frePayState, payState, chkPrice, payment);
                        });
                    } else if (4 == refundStatus) {
                        confirmDialog("已拒绝退款，确认继续发货吗？", function () {
                            TradePurorder.send(frePayState, payState, chkPrice, payment);
                        });
                    } else
                        TradePurorder.send(frePayState, payState, chkPrice, payment);
                } else {
                    remind("error", "请填写发货单号");
                    return false;
                }

            });
        });

        TradePurorder.send = function (frePayState, payState, chkPrice, payment) {

            /* bug #2775 修复发货数量为零也能发货的情况*/
            if (chkPrice === 0) {
                remind("error", "请输入至少一件产品");
                return;
            }

            var obj = new Object();
            obj.title = "发货提示";
            if ((2 == payState && 1 == frePayState) || chkPrice <= payment) {
                obj.content = "是否确认发货？";
                if (2 == payState && 0 == frePayState) {
                    obj.content = "终端商的运费还没支付，是否确认发货？";
                }
                confirmDialog(obj, function () {
                    $("#sendForm").submit();
                });
            } else {
                if (chkPrice > payment) {
                    obj.content = "您的发货金额超过终端商已支付金额，是否确认发货？";
                    confirmDialog(obj, function () {
                        $("#sendForm").submit();//提交表单
                    });
                }
            }
        }
        //修改备注
        TradePurorder.handleRemarkFn = function (current_obj, orderIds, dialog) {

            var levelMark = $.trim($('#levelMark input[name="leven"]:checked').val());
            if (levelMark < 1 || levelMark > 5) {
                alert("没有该星级");
                return;
            }
            var brandRemark = $.trim($('#remark').val());
            if (brandRemark.length == '') {
                remind("error", "备注的内容不能为空!");
                return;
            }

            $.ajax({
                type: "post",
                url: "${ctx}/brand/order/remark",
                data: {orderIds: orderIds, levelMark: levelMark, brandRemark: brandRemark},
                dataType: "json",
                success: function (json) {
                    if (json.code == zttx.SUCCESS) {
                        remind("success", "备注成功！");
                        $("#bMess").attr("title", brandRemark);
                        $("#bMess").html(trimLongString(brandRemark, 30));
                        dialog.hide();
                    } else {
                        remind("error", json.message);
                    }
                }
            });
        }

        TradePurorder.handleRemark();

        //修改地址
        TradePurorder.handleAddressFn = function (dialog, orderId) {
            var dealerAddress = $.trim($('#dealerAddress').val());
            var shipName = $.trim($('#shipName').val());
            var addressprovince = $.trim($('#addressprovince').val());
            var addresscity = $.trim($('#addresscity').val());
            var addresscounty = $.trim($('#addresscounty').val());
            var dealerTel = $.trim($('#dealerTel').val());
            var dealerMobile = $.trim($('#dealerMobile').val());
            var checkTel = /^[0-9]{3,4}-[0-9]{7,8}(-[0-9]{2,6})?$/;
            var checkmobile = /^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$/;
            if (addressprovince == '') {
                remind("error", "所在地不能为空");
                return;
            }
            if (dealerAddress == '') {
                remind("error", "街道地址不能为空");
                return;
            }
            if (shipName == '') {
                remind("error", "姓名不能为空");
                return;
            }
            if (dealerTel != '' && !checkTel.test(dealerTel)) {
                remind("error", "电话号码格式不正确");
                return;
            }
            if (dealerMobile != '' && !checkmobile.test(dealerMobile)) {
                remind("error", "手机格式不正确");
                return;
            }
            $.ajax({
                type: "post",
                url: "${ctx}/brand/order/modOrderAddr",
                data: {
                    dealerAddress: dealerAddress,
                    shipName: shipName,
                    addressprovince: addressprovince,
                    addresscity: addresscity,
                    addresscounty: addresscounty,
                    orderId: orderId,
                    dealerTel: dealerTel,
                    dealerMobile: dealerMobile
                },
                traditional: true,
                dataType: "json",
                success: function (json) {
                    if (json.code == zttx.SUCCESS) {
                        remind("success", "修改成功！", 1000, function () {
                            //dialog.hide();
                            window.location.reload();
                        });
                    } else {
                        remind("error", json.message);
                    }
                    ;
                }
            });
        }
        TradePurorder.handleAddress();
    })
</script>
</body>
</html>