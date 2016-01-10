<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
    <title>管理中心-交易管理-采购的订单-订单详情</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/brand_trade.css"/>
    <style>
        .detail-contants .detail-contants-dl dd.detail-dlist-des {min-height: 30px;_height: 30px;}
        .payrecord {border: #febb75 solid 2px;padding: 0 6px 10px;border-radius: 3px;margin: 0 15px;}
        .payrecord li {margin-top: 10px;}
        .obvious-line{border-bottom: 1px solid #ddd;}
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
                <a class="link" href="${ctx}/brand/order/purorder">采购的订单</a> <span class="arrow">&gt;</span>
                <span class="current">订单详情</span>
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
            <div class="v2-steps">
                <c:if test="${dealer.orderStatus==1}">
                    <!-- 第一步 -->
                    <span class="text1 current">终端商下单</span>
                    <span class="text2">终端商付款</span>
                    <span class="text3">发货</span>
                    <span class="text4">终端商确认收货</span>

                    <div class="steps v2-steps-1"></div>
                </c:if>
                <c:if test="${dealer.orderStatus==2 || dealer.orderStatus==3}">
                    <!-- 第二步 -->
                    <span class="text1">终端商下单</span>
                    <span class="text2 current">终端商付款</span>
                    <span class="text3">发货</span>
                    <span class="text4">终端商确认收货</span>

                    <div class="steps v2-steps-2"></div>
                </c:if>
                <c:if test="${dealer.orderStatus==4}">
                    <!-- 第三步 -->
                    <span class="text1">终端商下单</span>
                    <span class="text2">终端商付款</span>
                    <span class="text3 current">发货</span>
                    <span class="text4">终端商确认收货</span>

                    <div class="steps v2-steps-3"></div>
                </c:if>
                <c:if test="${dealer.orderStatus==9}">
                    <!-- 第四步 -->
                    <span class="text1">终端商下单</span>
                    <span class="text2">终端商付款</span>
                    <span class="text3">发货</span>
                    <span class="text4 current">终端商确认收货</span>

                    <div class="steps v2-steps-4"></div>
                </c:if>
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
									<span class="logistics-date inline-block">
                                            ${fns:getTimeFromLong(dealerOrderAction.createTime,"yyyy-MM-dd HH:mm:ss")}
                                    </span>
									<span class="logistics-log inline-block"
                                          title="${dealerOrderAction.actName}${dealerOrderAction.actContent}">
										${dealerOrderAction.actName} &nbsp;${dealerOrderAction.actContent}
									</span>
                                        </li>
                                    </c:when>
                                    <c:when test="${status.last==true&&status.first==false}">
                                        <li class="end">
									<span class="logistics-date inline-block">
                                            ${fns:getTimeFromLong(dealerOrderAction.createTime,"yyyy-MM-dd HH:mm:ss")}
                                    </span>
									<span class="logistics-log inline-block"
                                          title="${dealerOrderAction.actName}${dealerOrderAction.actContent}">
										${dealerOrderAction.actName} &nbsp;${dealerOrderAction.actContent}
									</span>
                                        </li>
                                    </c:when>
                                    <c:when test="${status.first}">
                                        <li class="start">
									<span class="logistics-date inline-block">
                                            ${fns:getTimeFromLong(dealerOrderAction.createTime,"yyyy-MM-dd HH:mm:ss")}
                                    </span>
									<span class="logistics-log inline-block"
                                          title="${dealerOrderAction.actName}${dealerOrderAction.actContent}">
										${dealerOrderAction.actName} &nbsp;${dealerOrderAction.actContent}
									</span>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="next">
									<span class="logistics-date inline-block">
                                            ${fns:getTimeFromLong(dealerOrderAction.createTime,"yyyy-MM-dd HH:mm:ss")}
                                    </span>
									<span class="logistics-log inline-block"
                                          title="${dealerOrderAction.actName}${dealerOrderAction.actContent}">
										${dealerOrderAction.actName} &nbsp;${dealerOrderAction.actContent}
									</span>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>

                <c:if test="${dealer.orderStatus!=10}">
                    <div class="orderbox">
                        <input type="hidden" name="refrenceId" value="${dealer.refrenceId}"/>
                        <c:choose>
                            <c:when test="${dealer.orderStatus==2}">
                                <a href="javascript:TradePurorder.sendGoods('${dealer.orderId}','${dealer.refundStatus}');"
                                   class="ui_button ui_button_lorange">发货</a>
                            </c:when>
                            <c:when test="${dealer.orderStatus==3}">
                                <a href="javascript:TradePurorder.sendGoods('${dealer.orderId}','${dealer.refundStatus}');"
                                   class="ui_button ui_button_lorange">继续发货</a>
                                <a href="javascript:TradePurorder.cancelSends('${dealer.orderId}','${dealer.refundStatus}');"
                                   class="ui_button ui_button_lgray">关闭发货</a>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${dealer.orderStatus<=2 && dealer.frePayState==0}">
                            <a href="javascript:;" class="ui_button ui_button_lgreen js_updateAddress"
                               data-areano="${dealer.areaNo}" data-orderid="${dealer.orderId}">修改收货地址</a>
                        </c:if>
                        <a href="javascript:;" class="ui_button simple_button remarks"
                           data-colorindex="${dealer.levelMark}" data-text="${dealer.brandRemark}">备注</a>
                    </div>
                </c:if>
            </div>
            <div class="detail-print mt15">
                <a class="to-look inline-block">订单详情</a>
                <a href="javascript:;" class="to-print inline-block bluefont" style="display:none;">打印</a>
            </div>
            <div class="detail-contants">
                <dl class="detail-contants-dl mt15 clearfix">
                    <dt class="detail-dlist-tit">终端商信息：</dt>
                    <dd class="detail-dlist-des">
                        ${dealer.dealerName}
                        <a href="javascript:;" data-name="${dealer.brandName}" data-dealerid="${dealer.dealerId}"
                           class="bluefont ml15 js_message">发送站内信</a>
                    </dd>
                    <dt class="detail-dlist-tit">留言：</dt>
                    <dd class="detail-dlist-des" title="${dealer.remark}">${fns:trimLongText(dealer.remark, 30)}</dd>

                    <dt class="detail-dlist-tit">收货信息：</dt>
                    <dd class="detail-dlist-des">
                        ${dealer.shipName}，<c:if test="${!empty dealer.dealerMobile}">${dealer.dealerMobile}，</c:if>
                        <c:if test="${!empty dealer.dealerTel}">${dealer.dealerTel}，</c:if>
                        ${dealer.dealerAddrProvince}
                        ${dealer.dealerAddrCity}
                        ${dealer.dealerAddrArea}
                        ${dealer.dealerAddress}
                        <c:if test="${!empty dealer.postCode}">，${dealer.postCode}</c:if>
                    </dd>

                    <dt class="detail-dlist-tit">订单信息：</dt>
                    <dd class="detail-dlist-des">
                        订单编号：${dealer.orderId} <span class="ml15">
							下单时间：${fns:getTimeFromLong(dealer.createTime,"yyyy-MM-dd HH:mm:ss")}</span>
                    </dd>

                    <dt class="detail-dlist-tit">品牌商备注：</dt>
                    <dd class="detail-dlist-des"
                        title="${dealer.brandRemark}">${fns:trimLongText(dealer.brandRemark, 30)}</dd>
                </dl>
                <div class="detail_table">
                    <table class="detailed-list">
                        <colgroup>
                            <col width="135">
                            <col width="105">
                            <c:choose>
                            	<c:when test="${dealer.dealerOrderType==3}">
                            		<col width="80">
                            		<col width="80">
                            		<col width="80">
                            	</c:when>
                            	<c:otherwise>
                            		<col width="100">
                            	</c:otherwise>
                            </c:choose>
                            <col width="90">
                            <col width="100">
                            <%--<col width="130">--%>
                            <c:if test="${dealer.shipCount != 0}">
                                <col width="130">
                            </c:if>
                            <col width="100">
                            <col width="100">
                        </colgroup>
                        <tr>
                            <th>商品信息</th>
                            <th>颜色 | 尺码</th>
                            <c:choose>
                            	<c:when test="${dealer.dealerOrderType==3}">
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
                            <%--<th>优惠后金额（元）</th>--%>
                            <c:if test="${dealer.shipCount != 0}">
                                <th>已发货数量（件）</th>
                            </c:if>
                            <th>数量小计（件）</th>
                            <th>金额小计（元）</th>
                        </tr>
                        <tbody id="product-item">
                        <c:set var="MaxTotalNum" value="0"></c:set>
                        <c:set var="MaxTotalPrice" value="0"></c:set>
                        <c:forEach items="${dealerOrders}" var="d">
                            <tr class="obvious-line">
                                <td class="goods-td cell-1"><br/>
                                    <c:set value="${d.productImage}" var="url"></c:set>
                                    <img src="${res}${fns:getImageDomainPath(url, 160, 160)}"
                                         style="width:90px;height:90px;" title="${d.productTitle}"
                                         alt="${d.productTitle}"/> <br/>
                                    <a href="${ctx}/market/product/${d.productId}" target="_blank">货号:${d.productNo}</a><br/>
                                        <%--工厂店图标--%>
                                    <%--<c:if test="${isFactoryShop}">
                                        <div class="factory-shop-box"><em class="i-factory-shop"></em>工厂店</div>
                                    </c:if>--%>
                                </td>
                                <c:choose>
                                    <c:when test="${dealer.dealerOrderType==3}">
                                        <c:choose>
                                        	<c:when test="${dealer.shipCount != 0}">
                                        		<td class="goods-td" colspan="7" valign="top">
                                        	</c:when>
                                        	<c:otherwise>
                                        		<td class="goods-td" colspan="6" valign="top">
                                        	</c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                    	<c:choose>
                                        	<c:when test="${dealer.shipCount != 0}">
                                        		<td class="goods-td" colspan="5" valign="top">
                                        	</c:when>
                                        	<c:otherwise>
                                        		<td class="goods-td" colspan="4" valign="top">
                                        	</c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                                    <table width="100%">
                                        <colgroup>
                                            <col width="105">
                                            <col width="80">
                                            <col width="80">
                                            <col width="80">
		                                    <c:if test="${dealer.dealerOrderType==3}">
		                                    	<col width="90">
                                            	<col width="90">
		                                    </c:if>
                                            <%--<col width="130">--%>
                                            <c:if test="${dealer.shipCount != 0}">
                                                <col width="130">
                                            </c:if>
                                        </colgroup>
                                        <c:set var="totalNum" value="0"></c:set>
                                        <c:set var="totalPrice" value="0"></c:set>
                                        <c:set var="allTotalPrice" value="0"></c:set>
                                        <c:forEach items="${d.itemsModels}" var="p">
                                            <tr>
                                                <td>${p.productSkuName}</td>
                                                <c:choose>
					                            	<c:when test="${dealer.dealerOrderType==3}">
					                            		<td><fmt:formatNumber value="${p.price}" pattern="0.00"/></td>
					                            		<td><fmt:formatNumber type="percent" value="${p.pointPercent }"/></td>
					                            		<td><fmt:formatNumber value="${p.price*(1-p.pointPercent)}" pattern="0.00"/></td>
					                            	</c:when>
					                            	<c:otherwise>
					                            		<td><!--价格-->
		                                                    <c:choose>
		                                                        <c:when test="${p.adjustPrice != null && p.adjustPrice.doubleValue() >= 0}">
		                                                            <fmt:formatNumber value="${p.adjustPrice/p.quantity}"
		                                                                              pattern="0.00"/>
		                                                            <s style="color: #AAAAAA;margin-left: 5px;"><fmt:formatNumber
		                                                                       value="${p.price}" pattern="0.00"/></s>
		                                                        </c:when>
		                                                        <c:otherwise>
		                                                            <c:choose>
		                                                                <c:when test="${p.discount != null && p.discount.doubleValue() != 0 && p.discount.doubleValue() != 1 }">
		                                                                    <c:choose>
		                                                                        <c:when test="${p.oldPrice != null}">
		                                                                            <fmt:formatNumber
		                                                                                       value="${p.price}"
		                                                                                       pattern="0.00"/>
		                                                                        </c:when>
		                                                                        <c:otherwise>
		                                                                            <fmt:formatNumber
		                                                                                       value="${p.price*p.discount}"
		                                                                                       pattern="0.00"/>
		                                                                            <s style="color: #AAAAAA;margin-left: 5px;"><fmt:formatNumber
		                                                                                       value="${p.price}"
		                                                                                       pattern="0.00"/></s>
		                                                                        </c:otherwise>
		                                                                    </c:choose>
		                                                                </c:when>
		                                                                <c:otherwise>
		                                                                    <fmt:formatNumber value="${p.price}"
		                                                                                      pattern="0.00"/>
		                                                                </c:otherwise>
		                                                            </c:choose>
		                                                        </c:otherwise>
		                                                    </c:choose>
		                                                </td>
					                            	</c:otherwise>
				                            	</c:choose>
                                                <td>${p.quantity}</td>
                                                <td>
                                                	<c:choose>
						                            	<c:when test="${dealer.dealerOrderType==3}">
						                            		<fmt:formatNumber value="${p.price*(1-p.pointPercent)*p.quantity}" pattern="0.00"/>
						                            	</c:when>
						                            	<c:otherwise>
						                            		<c:choose>
		                                                        <c:when test="${p.adjustPrice != null && p.adjustPrice.doubleValue() >= 0}">
		                                                            <fmt:formatNumber value="${p.adjustPrice}"
		                                                                              pattern="0.00"/>
		                                                        </c:when>
		                                                        <c:otherwise>
		                                                            <c:choose>
		                                                                <c:when test="${p.discount != null && p.discount.doubleValue() != 0 && p.discount.doubleValue() != 1 }">
		                                                                    <c:choose>
		                                                                        <c:when test="${p.oldPrice != null}">
		                                                                            <fmt:formatNumber
		                                                                                       value="${p.price * p.quantity}"
		                                                                                       pattern="0.00"/>
		                                                                        </c:when>
		                                                                        <c:otherwise>
		                                                                            <fmt:formatNumber var="discountPrice"
		                                                                                       value="${p.price*p.quantity*(1-p.discount)}"
		                                                                                       pattern="0.00"/>
		                                                                            <fmt:formatNumber
		                                                                                       value="${p.price * p.quantity - discountPrice}"
		                                                                                       pattern="0.00"/>
		                                                                        </c:otherwise>
		                                                                    </c:choose>
		                                                                </c:when>
		                                                                <c:otherwise>
		                                                                    <fmt:formatNumber value="${p.price * p.quantity}"
		                                                                                      pattern="0.00"/>
		                                                                </c:otherwise>
		                                                            </c:choose>
		                                                        </c:otherwise>
		                                                    </c:choose>
						                            	</c:otherwise>
					                            	</c:choose>
                                                </td>
                                                <c:set var="totalNum" value="${totalNum+p.quantity}"></c:set>
                                                
                                                <c:choose>
					                            	<c:when test="${dealer.dealerOrderType==3}">
					                            		<c:set var="totalPrice" value="${totalPrice + p.price*(1-p.pointPercent)*p.quantity}"></c:set>
					                            		<c:set var="allTotalPrice" value="${allTotalPrice + p.price*(1-p.pointPercent)*p.quantity}"></c:set>
					                            	</c:when>
					                            	<c:otherwise>
					                            		<c:choose>
		                                                    <c:when test="${p.adjustPrice != null && p.adjustPrice.doubleValue() >= 0}">
		                                                        <c:set var="totalPrice"
		                                                               value="${totalPrice + p.adjustPrice.doubleValue()}"></c:set>
		                                                    </c:when>
		                                                    <c:otherwise>
		                                                        <c:choose>
		                                                            <c:when test="${p.discount != null && p.discount.doubleValue() != 0 && p.discount.doubleValue() != 1 }">
		                                                                <c:choose>
		                                                                    <c:when test="${p.oldPrice != null}">
		                                                                        <c:set var="totalPrice"
		                                                                               value="${totalPrice + p.price * p.quantity}"></c:set>
		                                                                    </c:when>
		                                                                    <c:otherwise>
		                                                                        <fmt:formatNumber var="discountPrice"
		                                                                                          value="${p.price*p.quantity*(1-p.discount)}"
		                                                                                          pattern="0.00"/>
		                                                                        <c:set var="totalPrice"
		                                                                               value="${totalPrice + p.price * p.quantity - discountPrice}"></c:set>
		                                                                    </c:otherwise>
		                                                                </c:choose>
		                                                            </c:when>
		                                                            <c:otherwise>
		                                                                <c:set var="totalPrice"
		                                                                       value="${totalPrice + p.price * p.quantity}"></c:set>
		                                                            </c:otherwise>
		                                                        </c:choose>
		                                                    </c:otherwise>
		                                                </c:choose>
		                                                <c:choose>
		                                                    <c:when test="${p.adjustPrice != null && p.adjustPrice.doubleValue() >= 0}">
		                                                        <c:set var="allTotalPrice"
		                                                               value="${allTotalPrice + p.adjustPrice}"></c:set>
		                                                    </c:when>
		                                                    <c:otherwise>
		                                                        <c:choose>
		                                                            <c:when test="${p.discount != null && p.discount.doubleValue() != 0 && p.discount.doubleValue() != 1 }">
		                                                                <c:choose>
		                                                                    <c:when test="${p.oldPrice != null}">
		                                                                        <c:set var="allTotalPrice"
		                                                                               value="${allTotalPrice + p.price * p.quantity}"></c:set>
		                                                                    </c:when>
		                                                                    <c:otherwise>
		                                                                        <fmt:formatNumber var="discountPrice"
		                                                                                          value="${p.price*p.quantity*(1-p.discount)}"
		                                                                                          pattern="0.00"/>
		                                                                        <c:set var="allTotalPrice"
		                                                                               value="${allTotalPrice + p.price * p.quantity - discountPrice}"></c:set>
		                                                                    </c:otherwise>
		                                                                </c:choose>
		                                                            </c:when>
		                                                            <c:otherwise>
		                                                                <c:set var="allTotalPrice"
		                                                                       value="${allTotalPrice + p.price * p.quantity}"></c:set>
		                                                            </c:otherwise>
		                                                        </c:choose>
		                                                    </c:otherwise>
		                                                </c:choose>
					                            	</c:otherwise>
					                            </c:choose>
                                                
                                                <c:if test="${dealer.shipCount != 0}">
                                                    <td>${p.shipCount}</td>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                        <c:set var="MaxTotalNum" value="${MaxTotalNum+totalNum}"></c:set>
                                        <c:set var="MaxTotalPrice" value="${MaxTotalPrice+allTotalPrice}"></c:set>
                                    </table>
                                </td>
                                <td class="goods-td">${totalNum}</td>
                                <td class="goods-td last-td"><fmt:formatNumber pattern="0.00" value="${totalPrice}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="price_box">
                    <label>进货总量：</label><strong>${MaxTotalNum}</strong>&nbsp;件 <span
                        class="line">|</span> <label>总价：</label>
                    <fmt:formatNumber value="${MaxTotalPrice}" pattern="0.00"/>元
                    <c:if test="${dealer.adjustPrice>=0}">
                        +<fmt:formatNumber value="${dealer.adjustPrice}" pattern="0.00"/>元
                    </c:if>
                    <c:if test="${dealer.adjustPrice<0}">
                        <fmt:formatNumber value="${dealer.adjustPrice}" pattern="0.00"/>元
                    </c:if>
                    <span>（${dealer.adjustPrice > 0 ? "加价":"优惠"}）</span>+
                    <c:choose>
                        <c:when test="${dealer.dealerOrderType==3}">
                        	0.00元
		                    <span>（运费）</span>
                        </c:when>
                        <c:otherwise>
                        	<c:if test="${!empty dealer.adjustFreight}">
		                    	<fmt:formatNumber value="${dealer.adjustFreight}" pattern="0.00"/>元
		                    </c:if>
		                    <span><c:if test="${empty dealer.adjustFreight}">未设定</c:if>（运费）</span>
                        </c:otherwise>
                    </c:choose>
                    -
                    <c:if test="${!empty dealer.noSendGoodsAmount}">
                        <fmt:formatNumber value="${dealer.noSendGoodsAmount}" pattern="0.00"/>元
                        <span>（关闭发货）</span>-
                    </c:if>
                    <c:choose>
                        <c:when test="${!empty dealer.offSetAmount}">
                            <fmt:formatNumber value="${dealer.offSetAmount}" pattern="0.00"/>元
                        </c:when>
                        <c:otherwise>
                            0.00元
                        </c:otherwise>
                    </c:choose>
                    <%--<c:if test="${!empty dealer.offSetAmount}">
                        <fmt:formatNumber value="${dealer.offSetAmount}" pattern="0.00"/>元
                    </c:if>--%>
                    <span>（抵扣）=</span>
                    <strong>&nbsp;
                        <c:choose>
                            <c:when test="${!empty dealer.noSendGoodsAmount}">
                            	<c:choose>
			                        <c:when test="${dealer.dealerOrderType==3}">
			                        	<fmt:formatNumber
	                                           value="${MaxTotalPrice+dealer.adjustPrice-dealer.noSendGoodsAmount-dealer.offSetAmount}"
	                                           pattern="0.00"/>
			                        </c:when>
			                        <c:otherwise>
			                        	<fmt:formatNumber
	                                           value="${MaxTotalPrice+dealer.adjustPrice+dealer.adjustFreight-dealer.noSendGoodsAmount-dealer.offSetAmount}"
	                                           pattern="0.00"/>
			                        </c:otherwise>
		                        </c:choose>
                            </c:when>
                            <c:otherwise>
                            	<c:choose>
			                        <c:when test="${dealer.dealerOrderType==3}">
			                        	<fmt:formatNumber
                                           value="${MaxTotalPrice+dealer.adjustPrice-dealer.offSetAmount}"
                                           pattern="0.00"/>
			                        </c:when>
			                        <c:otherwise>
			                        	<fmt:formatNumber
                                           value="${MaxTotalPrice+dealer.adjustPrice+dealer.adjustFreight-dealer.offSetAmount}"
                                           pattern="0.00"/>
			                        </c:otherwise>
		                        </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </strong>元
                </div>

                <c:if test="${!empty orderPayList}">
                    <div class="factory-detail mt10 clearfix">
                        <div class="f-d-coll fl">
                            <div class="fl">
                                <span class="f-d-record">支付记录：</span>
                            </div>
                            <div class="fl f-d-detail">
                                <c:forEach items="${orderPayList}" var="orderPay" varStatus="status">
                                    <p>
                                        <span class="f-d-six">${fns:getTimeFromLong(orderPay.payTime,"yyyy-MM-dd HH:mm:ss")}</span>
                                        <span class="f-d-six">支付货款</span>
                                        <span class="f-d-pay"><em class="fn-rmb">￥</em><fmt:formatNumber
                                                value="${orderPay.payAmount}" type="currency" pattern="0.00"/></span>
                                        <c:if test="${orderPay.unpayAmount < 0&&status.index==0}">
                                            <span class="f-d-six">，剩余货款</span>
                                            <span class="f-d-overplus"><em class="fn-rmb">￥</em><fmt:formatNumber
                                                    value="${orderPay.unpayAmount*-1}" type="currency" pattern="0.00"/></span>
                                        </c:if>
                                    </p>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:if>
                <div class="detail-contants-tips">
                    <p>
                        <span class="redfont">*</span>8637品牌超级代理提醒您：
                    </p>

                    <p class="sixfont">1. 终端商已付款，请尽快发货，否则终端商有权申请退款。</p>

                    <p class="sixfont">2. 如果无法发货，请及时与终端商联系并说明情况。</p>

                    <p class="sixfont">3. 终端商申请退款后，须征得同意后再操作发货，否则终端商有权拒收货物。</p>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="bottom.jsp" %>
<script id="cancel_sends" type="text/html">
    <div class="ui-head">
        <h3>关闭发货</h3>
    </div>
    <form class="ui-form cancel_sends_form" action="" method="post">
        <input type="hidden" name="orderId" value="{{orderId}}"/>
        <div class="ui-form-item mt15">
            <label class="ui-label">关闭发货原因：</label>
            <textarea name="reason" placeholder="关闭发货原因..." class="ui-textarea" id="message_text" style="width: 250px;height: 80px;"></textarea>
        </div>
        <div class="ta-c">
            <button type="button" class="simple_button confirm_btn cancel_sends_s">关闭继续发货</button>
            <button type="button" class="simple_button ml5 cancel_sends_c">取消</button>
        </div>
    </form>
</script>
<jsp:include page="/WEB-INF/view/common/dialog/sendMessage_dialog.jsp"/>
<jsp:include page="/WEB-INF/view/common/dialog/remark_dialog.jsp"/>
<jsp:include page="/WEB-INF/view/common/dialog/updateAddress_dialog.jsp">
    <jsp:param value="${dealer.dealerAddress}" name="dealerAddress"/>
    <jsp:param value="${dealer.shipName}" name="shipName"/>
    <jsp:param value="${dealer.dealerTel}" name="dealerTel"/>
    <jsp:param value="${dealer.dealerMobile}" name="dealerMobile"/>
</jsp:include>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script src="${src}/brand/brand_trade.js"></script>
<script>

    //站内信
    TradePurorder.sendMessageFn = function (dealerId, brandName, dialog) {
        var title = "来自" + brandName + "信息";
        var content = $.trim($('#message_text').val());
        if (content == '') {
            remind("error", "留言信息不能为空");
            return;
        }
        if ($.trim(dealerId) == '') {
            return;
        }
        $.ajax({
            type: "post",
            url: "${ctx}/brand/message/sendDealer",
            data: {dealerIds: dealerId, title: title, content: content},
            traditional: true,
            dataType: "json",
            success: function (json) {
                if (json.code == zttx.SUCCESS) {
                    remind("success", "留言成功！");
                    dialog.hide();
                } else {
                    remind("error", json.message);
                }
            }
        });
    };


    //备注
    //dialog当前对话框
    TradePurorder.handleRemarkFn = function (current_obj, orderIds, dialog) {
        var levelMark = $.trim($('#levelMark input[name="leven"]:checked').val());

        if (levelMark < 1 || levelMark > 5) {
            remind("error", "没有该星级");
            return;
        }
        var brandRemark = $.trim($('#remark').val());
        if (brandRemark.length == '') {
            remind("error", "备注的内容不能为空");
            return;
        }

        $.ajax({
            type: "post",
            url: "${ctx}/brand/order/remark",
            data: {orderIds: orderIds, levelMark: levelMark, brandRemark: brandRemark},
            dataType: "json",
            success: function (json) {
                if (json.code == zttx.SUCCESS) {
                    remind("success", "修改成功！");
                    dialog.hide();
                    window.location.href = window.location.href;
                } else {
                    remind("error", json.message);
                }
            }
        });
    };

    //修改地址
    TradePurorder.handleAddressFn = function (dialog, orderId) {
        var dealerAddress = $.trim($('#dealerAddress').val());
        var shipName = $.trim($('#shipName').val());
        var addresscounty = $.trim($('#addresscounty').val());
        var dealerTel = $.trim($('#dealerTel').val());
        var dealerMobile = $.trim($('#dealerMobile').val());
        var checkTel = /^([0-9]{3,4}-[0-9]{7,8}(-[0-9]{2,6})?)$/;
        var checkMobile = /^(1[3-8]\d{9})$/;
        if (dealerTel != '') {
            if (!checkTel.test(dealerTel)) {
                remind("error", "电话号码格式错误");
                return;
            }
        }
        if (dealerMobile != '') {
            if (!checkMobile.test(dealerMobile)) {
                remind("error", "手机号码格式错误");
                return;
            }
        }
        if (dealerAddress == '') {
            remind("error", "街道地址不能为空");
            return;
        }
        if (shipName == '') {
            remind("error", "姓名不能为空");
            return;
        }
        $.ajax({
            type: "post",
            url: "${ctx}/brand/order/modOrderAddr",
            data: {
                dealerAddress: dealerAddress,
                shipName: shipName,
                addresscounty: addresscounty,
                orderId: orderId,
                dealerTel: dealerTel,
                dealerMobile: dealerMobile
            },
            traditional: true,
            dataType: "json",
            success: function (json) {
                if (json.code == zttx.SUCCESS) {
                    remind("success", "修改成功！", function () {
                        dialog.hide();
                        window.location.href = window.location.href;
                    });
                } else {
                    remind("error", json.message);
                }
            }
        });
    };

    TradePurorder.sendGoods = function (orderId, refundStatus) {
        if (1 == refundStatus || 2 == refundStatus || 3 == refundStatus || 4 == refundStatus) {
            confirmDialog("退款申请中，确认继续发货吗？", function () {
                window.location.href = "${ctx}/brand/order/sendGoods/" + orderId;
            });
        } else {
            window.location.href = "${ctx}/brand/order/sendGoods/" + orderId;
        }
    };

    var cancelSendsDialog, v_template;
    seajs.use(["dialog", "template"], function(Dialog, Template){
        v_template = Template;
        cancelSendsDialog = new Dialog({
            "width": 500
        });
        //取消
        $(document).on("click", ".cancel_sends_c", function(){
            cancelSendsDialog.hide();
        });
        //关闭继续发货
        $(document).on("click", ".cancel_sends_s", function(){
            //console.log($(".cancel_sends_form").serialize())
            $.ajax({
                url: "${ctx}/brand/order/closeSendGoods",
                method: "post",
                data: $(".cancel_sends_form").serialize(),
                dataType: "json",
                success: function(data){
                    if(data.code == zttx.SUCCESS){
                        remind("success", "已成功关闭发货");
                        window.location.reload();
                    }else{
                        remind("error", "关闭发货失败");
                        cancelSendsDialog.hide();
                    }
                }
            });
        });
    });

    //关闭发货，后期调整新增内容
    TradePurorder.cancelSends = function(orderId, refundStatus){
        var html = v_template.render('cancel_sends', {orderId: orderId, refundStatus: refundStatus});
        cancelSendsDialog.set("content",html);
        cancelSendsDialog.show();
    };

    TradePurorder.sendMessage();
    TradePurorder.handleRemark();
    TradePurorder.handleAddress();
</script>
</body>
</html>