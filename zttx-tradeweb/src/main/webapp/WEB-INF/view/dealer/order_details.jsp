<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理中心 >> 已进货的订单 > 订单状态</title>
<link href="${res}/styles/dealer/global.css" rel="stylesheet" />

<link href="${res}/styles/dealer/alreas.css" rel="stylesheet" />
</head>
<body>
	<!--完成-->
	<div class="container">
		<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
		<div class="em100">
			<div class="main clearfix pr">
					<jsp:include page="${ctx}/common/menuInfo/sidemenu" >
						<jsp:param value="${dealerOrder.dealerOrderType==1?2:222 }" name="openId"/>
					</jsp:include>
				<!--主体内容-->
				<div class="main-right">
					<jsp:include page="/WEB-INF/view/dealer/agency_header_message.jsp" />
					<div class="main-grid mb10">
						<div class="panel-crumbs">
							<i class="icon i-setting"></i><a href="${ctx }/dealer/center">管理中心</a> >> <a href="${ctx}/dealer/dealerOrder/order" title="">已进货的订单</a> > <span class="bb">订单状态</span>
							<a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
						</div>
					</div>
					<div class="inner" style="padding-top:5px;padding-left: 10px;padding-right: 10px;">
					<div class="main-grid mb10" >
						<div class="panel-step">
							<h3 class="fl yahei fs14 lh2">订单流程:</h3>
							<!--流程样式 step1-4 -->
							<c:if test="${(dealerOrder.orderStatus!=9&&dealerOrder.orderStatus!=10)&&(dealerOrder.payState==0||dealerOrder.payState==1)}"><div class="step step1"></div></c:if>
							<c:if test="${dealerOrder.payState==2&&dealerOrder.orderStatus==1}"><div class="step step1"></div></c:if>
							<c:if test="${dealerOrder.payState==2&&(dealerOrder.orderStatus==2||dealerOrder.orderStatus==3)}"><div class="step step2"></div></c:if>
							<c:if test="${dealerOrder.payState==2&&dealerOrder.orderStatus==4}"><div class="step step3"></div></c:if>
							<c:if test="${dealerOrder.orderStatus==9||dealerOrder.orderStatus==10}"><div class="step step4"></div></c:if>
						</div>
					</div>
					<div class="main-grid mb10">
						<div class="panel-purchases">
							<div class="panel-title">
								<h3 class="yahei fs16">订单状态</h3>
							</div>
							<div class="panel-content clearfix" style="width: 978px;">
								<div class="panel-left fl">
									<div class="panel-logistics">
										<ul class="bar">
											<c:forEach items="${dealerOrderAction}" varStatus="status"
												var="dealerOrderAction">
												<c:choose>
													<c:when test="${status.last==true&&status.first==true}">
														<li class="first"><span class="logistics-date">
																${fns:getTimeFromLong(dealerOrderAction.createTime,"yyyy-MM-dd
																HH:mm:ss")} </span> <span class="logistics-log" title="${dealerOrderAction.actName} ${dealerOrderAction.actContent}">${dealerOrderAction.actName}
																&nbsp;${dealerOrderAction.actContent}</span></li>
													</c:when>
													<c:when test="${status.last==true&&status.first==false}">
														<li class="end"><span class="logistics-date">
																${fns:getTimeFromLong(dealerOrderAction.createTime,"yyyy-MM-dd
																HH:mm:ss")} </span> <span class="logistics-log" title="${dealerOrderAction.actName} ${dealerOrderAction.actContent}">${dealerOrderAction.actName}
																&nbsp;${dealerOrderAction.actContent}</span></li>
													</c:when>
													<c:when test="${status.first}">
														<li class="start"><span class="logistics-date">
																${fns:getTimeFromLong(dealerOrderAction.createTime,"yyyy-MM-dd
																HH:mm:ss")} </span> <span class="logistics-log" title="${dealerOrderAction.actName} ${dealerOrderAction.actContent}">${dealerOrderAction.actName}
																&nbsp;${dealerOrderAction.actContent}</span></li>
													</c:when>
													<c:otherwise>
														<li class="next"><span class="logistics-date">
																${fns:getTimeFromLong(dealerOrderAction.createTime,"yyyy-MM-dd
																HH:mm:ss")} </span> <span class="logistics-log" title="${dealerOrderAction.actName} ${dealerOrderAction.actContent}">${dealerOrderAction.actName}
																&nbsp;${dealerOrderAction.actContent}</span></li>
													</c:otherwise>

												</c:choose>

												<%-- 												<li class="end next"><span class="logistics-date">2013-12-17
														12:12:12</span><span class="logistics-log">生成订单 订单编号：
														${dealer.orderId}</span></li> --%>
											</c:forEach>
										</ul>
									</div>
								</div>
								<div class="panel-right">
									<div class="panel-right-btns ta-c">
									<%--工厂店不支持继续付款 --%>
											<c:if test="${dealerOrder.dealerOrderType==1 }">
										<c:if test="${(dealerOrder.orderStatus!=9&&dealerOrder.orderStatus!=10)&&(dealerOrder.payState==0||dealerOrder.payState==1)}">
											<a href="<c:if test="${dealerOrder.payState==0 }">${ctx}/dealer/dealerOrder/payment/${dealerOrder.refrenceId}</c:if><c:if test="${dealerOrder.payState==1 }">javascript:void()</c:if>" class="ui-button ui-button-lred yahei fs16 mb5 ${dealerOrder.payState==1?'fun-goon-pay':'' }"
											data-order-id='${dealerOrder.refrenceId}'  data-order-payment='${dealerOrder.payment}' data-order-frepayment='${dealerOrder.frePayState==1?dealerOrder.adjustFreight:0.0}' data-order-adjustfreight='${dealerOrder.frePayState==0?dealerOrder.adjustFreight:0.0}'   data-order-total="${dealerOrder.productPrice + dealerOrder.adjustPrice + dealerOrder.adjustFreight}"
											>
											<c:if test="${dealerOrder.payState==0 }">付 款 </c:if>
											<c:if test="${dealerOrder.payState==1 }">继续付款</c:if>
											
											</a>
										</c:if>
									     <c:if test="${dealerOrder.payState==2&&dealerOrder.frePayState != 1&&dealerOrder.adjustFreight!=null}">
                                               <a href="${ctx}/dealer/order/freight/${dealerOrder.refrenceId}" class="ui-button ui-button-mred" target="_blank">支付运费 </a>
                                         </c:if>
                                         </c:if>
                                         <%-- 未退款流程的时候 --%>
										<c:if test="${empty dealerOrder.refundStatus&&dealerOrder.payState==2&&dealerOrder.orderStatus==4&&dealerOrder.frePayState==1}">
											   <a href="${ctx }/dealer/dealerOrder/receive/${dealerOrder.refrenceId}" class="ui-button ui-button-mblue">确认收货</a>
										</c:if>
										
									    <c:if test="${not empty dealerOrder.refundStatus &&dealerOrder.payState==2&&dealerOrder.orderStatus==4&&dealerOrder.frePayState==1 }">
											<%-- 退款流程的 走完后4：拒绝退款--%>
											<c:if test="${dealerOrder.refundStatus == 4&&dealerOrder.serProStatus==null}">
												   <a href="${ctx }/dealer/dealerOrder/receive/${dealerOrder.refrenceId}" class="ui-button ui-button-mblue">确认收货</a>
											</c:if>
											<%-- 退款流程的 走完后 5：拒绝退货 --%>
											<c:if test="${dealerOrder.refundStatus == 5&&dealerOrder.serProStatus==null}">
												   <a href="${ctx }/dealer/dealerOrder/receive/${dealerOrder.refrenceId}" class="ui-button ui-button-mblue">确认收货</a>
											</c:if>
											<%-- 退款流程的 走完后 6：退款关闭 --%>
											<c:if test="${dealerOrder.refundStatus == 6&&dealerOrder.serProStatus==null}">
												   <a href="${ctx }/dealer/dealerOrder/receive/${dealerOrder.refrenceId}" class="ui-button ui-button-mblue">确认收货</a>
											</c:if>
											<%-- 退款流程的 走完后  7: 取消退款 --%>
											<c:if test="${dealerOrder.refundStatus == 7&&dealerOrder.serProStatus==null}">
												   <a href="${ctx }/dealer/dealerOrder/receive/${dealerOrder.refrenceId}" class="ui-button ui-button-mblue">确认收货</a>
											</c:if>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="main-grid mb10" id="print_Region">
						<!--startprint-->
						<div class="panel-table">
							<div class="panel-table-title">
								<div class="panel-table-top clearfix ">
									<!--附加样式名: debt:欠款 advance:预付 -->
									<div class="fl">
										<span class="yahei fs16 ml10">订单信息：</span>
									</div>
									<div class="tools" style="margin-left: 766px;_margin-left: 763px;">
										<span class="mr5">有疑问可咨询</span> <a href="javascript:orderdetails.showCustomService();"
											class="service link mr5"><i class="icon i-user-blue"></i>联系客服</a>
										<a href="javascript:orderdetails.printTable()" class="print link ml5">打印</a>
									</div>
								</div>
								<div class="panel-table-bottom">
									<ul class="clearfix inline-float">
										<li><span>订单号:${dealerOrder.orderId}</span></li>
										<c:if test="${dealerOrder.activitiesNo=='A00002'}">
                                        <li><span>品牌:清仓品牌</span></li>
                                    	</c:if>
                                    	<c:if test="${dealerOrder.activitiesNo!='A00002'}">
                                    	<c:set value="${fns:getBrandsIdSubDomain(dealerOrder.brandsId)}" var="domain"></c:set> 
                                       	<li><span>品牌:<a target="_blank" href='http://${domain}${zttx}'>${dealerOrder.brandsName}</a></span></li>
										</c:if>
										<li><span>联系人:${brandContact.userName}</span></li>
										<li><span>电话:${brandContact.userTelphone}</span></li>
										<li><span>手机:${brandContact.userMobile}</span></li>
										<li><span>所在地:${brandInfo.provinceName} ${brandInfo.cityName}</span></li>
									</ul>
								</div>
							</div>
							<div class="panel-table-content clearfix">
								<table class="goods ui-table">
									<thead>
										<tr>
											<th width="170">商品信息</th>
											<th width="130">颜色/尺码</th>
											<c:choose>
			                        			<c:when test="${dealerOrder.dealerOrderType==3}">
			                        				<th width="130">返点价</th>
			                        				<th width="130">返点比例</th>
			                        				<th width="130">成本价</th>
			                        			</c:when>
			                        			<c:otherwise>
			                        				<th width="130">单价</th>
			                        			</c:otherwise>
			                        		</c:choose>
											<th width="130">数量</th>
											<th width="130">金额</th>
											<c:if test="${dealerOrder.shipCount != 0}">
												<th width="155">已发货数量（件）</th>
											</c:if>
											<th width="90">数量小计</th>
											<th width="90">金额小计</th>
										</tr>
									</thead>
									<tbody id="product-item">
										<!--商品1-->
										<c:set var="MaxTotalNum" value="0"></c:set>
										<c:set var="MaxTotalPrice" value="0"></c:set>
										<c:forEach items="${orderModels}" var="orderModel">
											<tr class="goods-item">
												<td class="goods-td goods-main-info cell-1">
                                                    <div class="js-img-center" style="width:90px;height:90px;margin-left: 40px;*margin-left: 15px;overflow:hidden"><a target="_blank" href="${ctx}/market/product/${orderModel.productId}"><img style="width:100%" src="${res}${orderModel.productImage}" /></a></div>
                                                    <br />
                                                    <a  target="_blank" href="${ctx}/market/product/${orderModel.productId}">
													货号: ${orderModel.productNo} </a>
												</td>
												<c:choose>
													<c:when test="${dealerOrder.shipCount != 0}">
														<td ${dealerOrder.dealerOrderType==3?'colspan="7"':'colspan="5"' }
														  	 class="goods-td goods-attribute">
													</c:when>
													<c:otherwise>
														<td ${dealerOrder.dealerOrderType==3?'colspan="6"':'colspan="4"' }
													   		class="goods-td goods-attribute">
													</c:otherwise>
												</c:choose>
													<table>
														<tbody>
															<c:set var="totalNum" value="0"></c:set>
															<c:set var="totalPrice" value="0"></c:set>
															<c:set var="allTotalPrice" value="0"></c:set>
															<c:forEach items="${orderModel.itemsModels}" var="itemsModel">
																<tr class="backf9">
																	<c:choose>
																		<c:when test="${dealerOrder.shipCount != 0}">
																			<td class="goods-other-attribute" ${dealerOrder.dealerOrderType==3?'colspan="7"':'colspan="5"' }>
																		</c:when>
																		<c:otherwise>
																			<td class="goods-other-attribute" ${dealerOrder.dealerOrderType==3?'colspan="6"':'colspan="4"' }>
																		</c:otherwise>
																	</c:choose>
																		<div class="attributes">
																			<table id="id1">
																				<tbody>
																					<tr>
																						<td class="widthtit" style="width:127px;">${itemsModel.productSkuName}</td>
																						<c:choose>
			                        														<c:when test="${dealerOrder.dealerOrderType==3}">
			                        															<td class="widthtit" style="width:127px;"><fmt:formatNumber value="${itemsModel.price}" pattern="0.00"/></td>
			                        															<td class="widthtit" style="width:127px;"><fmt:formatNumber type="percent" value="${itemsModel.pointPercent }"/></td>
			                        															<td class="widthtit" style="width:127px;"><fmt:formatNumber value="${itemsModel.price*(1-itemsModel.pointPercent)}" pattern="0.00"/></td>
			                        														</c:when>
			                        														<c:otherwise>
			                        															<td class="widthtit" style="width:127px;"><!--价格-->
																									<c:choose>
																										<c:when test="${itemsModel.adjustPrice != null && itemsModel.adjustPrice.doubleValue() >= 0}">
																											<fmt:formatNumber
																													   value="${itemsModel.adjustPrice/itemsModel.quantity}"
																													   type="currency"
																													   pattern="0.00"/>
																											<s style="color: #AAAAAA;margin-left: 5px;"><fmt:formatNumber
																													   value="${itemsModel.price}"
																													   type="currency"
																													   pattern="0.00"/></s>
																										</c:when>
																										<c:otherwise>
																											<c:choose>
																												<c:when test="${itemsModel.discount != null && itemsModel.discount.doubleValue() != 0 && itemsModel.discount.doubleValue() != 1 }">
																													<c:choose>
																														<c:when test="${itemsModel.oldPrice != null}"><!--有调价,则单价不用再计算折扣-->
																															<fmt:formatNumber
																																	   value="${itemsModel.price}"
																																	   type="currency"
																																	   pattern="0.00"/>
																														</c:when>
																														<c:otherwise>
																															<fmt:formatNumber
																																	   value="${itemsModel.price*itemsModel.discount}"
																																	   type="currency"
																																	   pattern="0.00"/>
																															<s style="color: #AAAAAA;margin-left: 5px;"><fmt:formatNumber
																																	   value="${itemsModel.price}"
																																	   type="currency"
																																	   pattern="0.00"/></s>
																														</c:otherwise>
																													</c:choose>
																												</c:when>
																												<c:otherwise>
																													<fmt:formatNumber
																															   value="${itemsModel.price}"
																															   type="currency"
																															   pattern="0.00"/>
																												</c:otherwise>
																											</c:choose>
																										</c:otherwise>
																									</c:choose>
																								</td>
			                        														</c:otherwise>
			                        													</c:choose>
																						<td class="widthtit" style="width:127px;">${itemsModel.quantity}</td>
																						<td class="widthtit" style="width:127px;"><!--金额-->
																							<c:choose>
				                        														<c:when test="${dealerOrder.dealerOrderType==3}">
				                        															<fmt:formatNumber value="${itemsModel.price*(1-itemsModel.pointPercent)*itemsModel.quantity}" type="currency" pattern="0.00"/>
				                        														</c:when>
				                        														<c:otherwise>
				                        															<c:choose>
																										<c:when test="${itemsModel.adjustPrice != null && itemsModel.adjustPrice.doubleValue() >= 0}">
																											<fmt:formatNumber
																													   value="${itemsModel.adjustPrice}"
																													   type="currency"
																													   pattern="0.00"/>
																										</c:when>
																										<c:otherwise>
																											<c:choose>
																												<c:when test="${itemsModel.discount != null && itemsModel.discount.doubleValue() != 0 && itemsModel.discount.doubleValue() != 1 }">
																													<c:choose>
																														<c:when test="${itemsModel.oldPrice != null}">
																															<fmt:formatNumber
																																	   value="${itemsModel.price * itemsModel.quantity}"
																																	   type="currency"
																																	   pattern="0.00"/>
																														</c:when>
																														<c:otherwise>
																															<fmt:formatNumber var="discountPrice" value="${itemsModel.price*itemsModel.quantity*(1-itemsModel.discount)}" pattern="0.00"/>
																															<fmt:formatNumber
																																	   value="${itemsModel.price * itemsModel.quantity - discountPrice}"
																																	   type="currency"
																																	   pattern="0.00"/>
																														</c:otherwise>
																													</c:choose>
																												</c:when>
																												<c:otherwise>
																													<fmt:formatNumber
																															   value="${itemsModel.price * itemsModel.quantity}"
																															   type="currency"
																															   pattern="0.00"/>
																												</c:otherwise>
																											</c:choose>
																										</c:otherwise>
																									</c:choose>
				                        														</c:otherwise>
			                        														</c:choose>
																						</td>
																						<c:set var="totalNum" value="${totalNum+itemsModel.quantity}"></c:set>
																						<c:choose>
															                            	<c:when test="${dealerOrder.dealerOrderType==3}">
															                            		<c:set var="totalPrice" value="${totalPrice + itemsModel.price*(1-itemsModel.pointPercent)*itemsModel.quantity}"></c:set>
															                            		<c:set var="allTotalPrice" value="${allTotalPrice + itemsModel.price*(1-itemsModel.pointPercent)*itemsModel.quantity}"></c:set>
															                            	</c:when>
															                            	<c:otherwise>
															                            		<c:choose>
																									<c:when test="${itemsModel.adjustPrice != null && itemsModel.adjustPrice.doubleValue() >= 0}">
																										<c:set var="totalPrice"
																											   value="${totalPrice + itemsModel.adjustPrice.doubleValue()}"></c:set>
																									</c:when>
																									<c:otherwise>
																										<c:choose>
																											<c:when test="${itemsModel.discount != null && itemsModel.discount.doubleValue() != 0 && itemsModel.discount.doubleValue() != 1 }">
																												<c:choose>
																													<c:when test="${itemsModel.oldPrice != null}">
																														<c:set var="totalPrice"
																															   value="${totalPrice + itemsModel.price  * itemsModel.quantity}"></c:set>
																													</c:when>
																													<c:otherwise>
																														<fmt:formatNumber
																																   var="discountPrice"
																																   value="${itemsModel.price*itemsModel.quantity*(1-itemsModel.discount)}"
																																   pattern="0.00"/>
																														<c:set var="totalPrice"
																															   value="${totalPrice + itemsModel.price * itemsModel.quantity - discountPrice}"></c:set>
																													</c:otherwise>
																												</c:choose>
																											</c:when>
																											<c:otherwise>
																												<c:set var="totalPrice"
																													   value="${totalPrice + itemsModel.price * itemsModel.quantity}"></c:set>
																											</c:otherwise>
																										</c:choose>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${itemsModel.adjustPrice != null && itemsModel.adjustPrice.doubleValue() >= 0}">
																										<c:set var="allTotalPrice"
																											   value="${allTotalPrice + itemsModel.adjustPrice}"></c:set>
																									</c:when>
																									<c:otherwise>
																										<c:choose>
																											<c:when test="${itemsModel.discount != null && itemsModel.discount.doubleValue() != 0 && itemsModel.discount.doubleValue() != 1 }">
																												<c:choose>
																													<c:when test="${itemsModel.oldPrice != null}">
																														<c:set var="allTotalPrice"
																															   value="${allTotalPrice + itemsModel.price * itemsModel.quantity}"></c:set>
																													</c:when>
																													<c:otherwise>
																														<fmt:formatNumber
																																   var="discountPrice"
																																   value="${itemsModel.price*itemsModel.quantity*(1-itemsModel.discount)}"
																																   pattern="0.00"/>
																														<c:set var="allTotalPrice"
																															   value="${allTotalPrice + itemsModel.price * itemsModel.quantity-discountPrice}"></c:set>
																													</c:otherwise>
																												</c:choose>
																											</c:when>
																											<c:otherwise>
																												<c:set var="allTotalPrice"
																													   value="${allTotalPrice + itemsModel.price * itemsModel.quantity}"></c:set>
																											</c:otherwise>
																										</c:choose>
																									</c:otherwise>
																								</c:choose>
															                            	</c:otherwise>
														                            	</c:choose>
																						<c:set var = "discount" value="${itemsModel.discount}"/>
																						<c:set var="oldPrice"
																							   value="${itemsModel.oldPrice}"/>
																					</tr>
																				</tbody>
																			</table>
																		</div>
																	</td>
																	<c:if test="${dealerOrder.shipCount != 0}">
																		<td class="widthtit"
																			style="width:127px;">${itemsModel.shipCount}</td>
																	</c:if>
																</tr>
															</c:forEach>
															<c:set var="MaxTotalNum" value="${MaxTotalNum+totalNum}"></c:set>
															<c:set var="MaxTotalPrice" value="${MaxTotalPrice+allTotalPrice}"></c:set>
														</tbody>
													</table>
												</td>
												<td class="goods-td goods-main-count cell-8">${totalNum}</td>
												<td class="goods-td goods-main-amount cell-9"><fmt:formatNumber value="${totalPrice}" type="currency" pattern="0.00" /><br/>
												<%--<c:if test="${oldPrice == null}">
													<c:if test="${discount lt 1}">
														折扣:${discount}
													</c:if>
												</c:if>--%>
												</td>
												
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr>
											<td colspan="2" class="ta-l">
												<p>
													<span class="bb">寄送至：</span>
													<span>${dealerOrder.dealerAddrProvince} ${dealerOrder.dealerAddrCity} ${dealerOrder.dealerAddrArea} ${dealerOrder.dealerAddress} </span>
												</p>
												<p>
													<span class="bb">收货人：</span><span>${dealerOrder.shipName} ${dealerOrder.dealerMobile}</span>
												</p></td>
											<td ${dealerOrder.dealerOrderType==3?'colspan="7"':'colspan="6"'} class="total"><span class="fs14 yahei">进货总量：<span
													class="fs18 c-r">${MaxTotalNum}</span> 件
													&nbsp;&nbsp;|&nbsp;&nbsp; </span><span class="fs14 yahei">总价:<span>
												<fmt:formatNumber value="${MaxTotalPrice-dealerOrder.offSetAmount}" type="currency" pattern="0.00" />元 
												<c:if test="${dealerOrder.adjustPrice>=0}">
													+ <fmt:formatNumber value="${dealerOrder.adjustPrice}" type="currency" pattern="0.00" />元  
												</c:if> 
												<c:if test="${dealerOrder.adjustPrice<0}">
													 <fmt:formatNumber value="${dealerOrder.adjustPrice}" type="currency" pattern="0.00" />元 
												</c:if>
												<span> ${dealerOrder.adjustPrice > 0 ? "(加价)":"(优惠)"}</span>+
												<c:choose>
							                        <c:when test="${dealerOrder.dealerOrderType==3}">
							                        	0.00元
									                    <c:if test="${dealerOrder.frePayType==60 }">(包邮)</c:if>
				                                        <c:if test="${dealerOrder.frePayType==1 }">(快递)</c:if>
				                                        <c:if test="${dealerOrder.frePayType==2 }">(物流)</c:if>
				                                        <c:if test="${dealerOrder.frePayType==3 }">(快递到付)</c:if>
				                                        <c:if test="${dealerOrder.frePayType==4 }">(物流到付)</c:if>
							                        </c:when>
							                        <c:otherwise>
									                    <c:if test="${dealerOrder.adjustFreight==null}">
															(未设定)
														</c:if>
														<c:if test="${dealerOrder.adjustFreight!=null}">
															<fmt:formatNumber value="${dealerOrder.adjustFreight}" type="currency" pattern="0.00" />元
															<c:if test="${dealerOrder.frePayType==60 }">(包邮)</c:if>
					                                        <c:if test="${dealerOrder.frePayType==1 }">(快递)</c:if>
					                                        <c:if test="${dealerOrder.frePayType==2 }">(物流)</c:if>
					                                        <c:if test="${dealerOrder.frePayType==3 }">(快递到付)</c:if>
					                                        <c:if test="${dealerOrder.frePayType==4 }">(物流到付)</c:if>
														</c:if>
							                        </c:otherwise>
							                    </c:choose>
												<c:if test="${!empty dealerOrder.noSendGoodsAmount}">
													-<fmt:formatNumber value="${dealerOrder.noSendGoodsAmount}"
																	   pattern="0.00"/>元
													<span>（关闭发货）</span>
												</c:if>
												<c:if test="${!empty dealerOrder.offSetAmount}">
													-<fmt:formatNumber value="${dealerOrder.offSetAmount}" pattern="0.00"/>元（抵扣）
												</c:if>
												<span>=</span>
												<strong class="fs18 c-r">&nbsp;<fmt:formatNumber value="${MaxTotalPrice+dealerOrder.adjustPrice+dealerOrder.adjustFreight-dealerOrder.noSendGoodsAmount-dealerOrder.offSetAmount}" type="currency" pattern="0.00" /></strong>元</span>
											</span></td>
										</tr>
									</tfoot>
								</table>

                                <%--工厂店单子需要用到下面的东西--%>
                                
                               <c:if test="${fn:length(orderPayList)>0}">
                               <div class="factory-detail mt10 clearfix">
                                <div class="fl">
                                    <span class="f-d-record">支付记录：</span>
                                </div>
                                <div class="fl f-d-detail">
                                <c:forEach items="${orderPayList}" var="orderPay" varStatus="status">
                                	<p>
                                        <span class="f-d-six">${fns:getTimeFromLong(orderPay.payTime,"yyyy-MM-dd HH:mm:ss")}</span>
                                        <span class="f-d-six">支付货款</span>
                                        <span class="f-d-pay"><em class="fn-rmb">￥</em><fmt:formatNumber value="${orderPay.payAmount}" type="currency" pattern="0.00" /></span>
                                      	<c:if test="${orderPay.unpayAmount < 0&&status.index==0}">
											<span class="f-d-six">,剩余货款</span>
                                        	<span class="f-d-overplus"><em class="fn-rmb">￥</em><fmt:formatNumber value="${orderPay.unpayAmount*-1}" type="currency" pattern="0.00" /></span>
                                      	</c:if>
                                       <!-- 
                                        <span class="f-d-six">,剩余可用保证金</span>
                                        <span class="f-d-overplus"><em class="fn-rmb">￥</em><fmt:formatNumber value="${orderPay.unpayAmount}" type="currency" pattern="0.00" /></span>
                                  		 -->
                                    </p>
                                </c:forEach>
                                 </div>
                                 </div>
                                <%--
                                    <div class="f-d-colr fr mt10">
                                        <a class="ui-button ui-button-lred" href="#">支付尾款</a>
                                    </div>
                                     --%>
                                </c:if>
                                <%--<div class="factory-detail mt10 clearfix">
                                    <div class="f-d-coll fl">
                                        <div class="fl">
                                            <span class="f-d-record">支付记录：</span>
                                        </div>
                                        <div class="fl f-d-detail">
                                            <p>
                                                <span class="f-d-six">2014-11-25 14:09:09</span>
                                                <span class="f-d-six">支付保证金</span>
                                                <span class="f-d-pay"><em class="fn-rmb">￥</em>2000.00</span>
                                                <span class="f-d-six">,剩余可用保证金</span>
                                                <span class="f-d-overplus"><em class="fn-rmb">￥</em>9000.00</span>
                                            </p>
                                            <p>
                                                <span class="f-d-six">2014-11-25 14:09:09</span>
                                                <span class="f-d-six">支付保证金</span>
                                                <span class="f-d-pay"><em class="fn-rmb">￥</em>2000.00</span>
                                                <span class="f-d-six">,剩余可用保证金</span>
                                                <span class="f-d-overplus"><em class="fn-rmb">￥</em>9000.00</span>
                                            </p>
                                            <p>
                                                <span class="f-d-six">2014-11-25 14:09:09</span>
                                                <span class="f-d-six">支付保证金</span>
                                                <span class="f-d-pay"><em class="fn-rmb">￥</em>2000.00</span>
                                                <span class="f-d-six">,剩余可用保证金</span>
                                                <span class="f-d-overplus"><em class="fn-rmb">￥</em>9000.00</span>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="f-d-colr fr mt10">
                                        <a class="ui-button ui-button-lred" href="#">支付尾款</a>
                                    </div>
                                </div>--%>


							</div>
						</div>
						<!--endprint-->
					</div>
				</div>
			</div>
		</div>
		</div>
		<div class="hide">
			<div id="payArrears" class="clearfix" style="">
                <div class="ui-head">
                    <h3>支付欠款</h3>
                </div>
                <form:form id="payArrearsForm" data-widget="validator" class="ui-form mt10" action="${ctx}/dealer/order/payment/confirm">
                    <fieldset>
                        <legend>支付欠款</legend>
                        <div class="ui-form-item" style="padding-left: 120px;">
                            <label class="ui-label" style="font-size: 12px;">订单剩余货款：</label>
                            <div class="inline-block fs16 msyh" style="vertical-align: middle;"><span>￥</span><span id="dlg_payment_rest">0.0</span></div>
                        </div>
                        <div class="ui-form-item" style="padding-left: 120px;padding-bottom:5px;font-size: 12px;">
                            <label class="ui-label" style="font-size: 12px;">支付金额：</label>
                            <input type="text" class="ui-input js-price self-ui-input" name="distriOrderAmounts" id="distriOrderAmountsId" /> 元
                        </div>
                        <div class="ui-form-item" style="padding-left: 120px;">
                            <label class="ui-label"></label>
                            <span class="c-hh">此货款即时到账至品牌商账户</span>
                        </div>
                        <input type="hidden" name="distriOrderIds" id="distriOrderIds"/>
                        <input type="hidden" name="distriOrderTypes" value="2" />
                        <div style="padding-left: 115px;" class="ui-form-item close-operate">
                            <input type="submit" class="ui-button ui-button-mred" value="提 交"/>
                            <input type="button" class="ui-button ui-button-mwhite ml20 js-pay-close" value="取 消"/>
                        </div>
                    </fieldset>
                </form:form>
            </div>
		</div>
		  <%----%>
		<jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
	</div>
	<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
	<script src="${res}/scripts/plugin/jquery-dateFormat.min.js"></script>
	<script>
		var orderdetails={};
        orderdetails.showCustomService=function(){
            confirmDialog({'title':"提示",'content':'<div style="text-align: left;font-size:12px;">客户服务热线: 0574-87217777</div><div style="text-align: left;font-size:12px;">客户服务邮箱: services@8637.com</div>'},function(){},false,function(){},true);
        };
        orderdetails.printTable = function(){
           document.getElementById("printFrame").src="${ctx}/dealer/order/print/${dealerOrder.refrenceId}";
        };
        $(function(){
        	 //继续付款
        	 seajs.use(['dialog'], function (Dialog) {
        		 var d3 = new Dialog({
                     trigger: '.fun-goon-pay',
                     content: "#payArrears",
                     width: 415,
                     hasMask: false
                 }).after('show', function (o) {
                             var totalAmount=parseFloat($(o.activeTrigger).data('order-total'));
                             var payedAmount=parseFloat($(o.activeTrigger).data('order-payment'));
                             var frePyAmount=parseFloat($(o.activeTrigger).data('order-frepayment'));
                             $('#distriOrderAmountsId').val($(o.activeTrigger).data('order-adjustfreight'));

                             $('#dlg_payment_rest').html(totalAmount-payedAmount-frePyAmount);
                             $('#distriOrderIds').val($(o.activeTrigger).data('order-id'));

                             $('#distriOrderAmountsId').data({
                                 "max":totalAmount-payedAmount-frePyAmount
                             }).attr({
                                 "data-price":$(o.activeTrigger).data('order-adjustfreight')
                             });
                             $(".js-pay-close").click(function(){
                                 d3.hide();
                             })
                        });
                 //继续付款表单校验
                 baseFormValidator({
                     selector:"#payArrearsForm",
                     addElemFn:function(Core, Validator){

                         Validator.addRule('iPrice', function (a) {
                             var inputPrice = $(".self-ui-input").val();

                             if(isNaN(inputPrice)){
                                 return false;
                             }else if(parseFloat(inputPrice) <=0){
                                 return false;
                             }else{
                                 return true;
                             }
                         }, '请输入正确的{{display}}');

                         Validator.addRule('minFare', function (a) {
                             var inputPrice = parseFloat($(".self-ui-input").val());
                             var minPrice = parseFloat($(".self-ui-input").attr("data-price"));

                             if(inputPrice < minPrice){
                                 return false;
                             }else{
                                 return true;
                             }
                         }, '{{display}}必须大于或等于运费');

                         Core.addItem({
                             element: 'input[name=distriOrderAmounts]',
                             rule: "min{target: 'input[name=distriOrderAmounts]', min: '0.01'} iPrice minFare",
                             required:true,
                             display: "支付金额"
                         });

                     }
                 });
        	 });
        });
	</script>
    <div class="hide">
        <iframe id="printFrame"></iframe>
    </div>
</body>
</html>
