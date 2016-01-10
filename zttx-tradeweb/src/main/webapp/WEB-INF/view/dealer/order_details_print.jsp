<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单号:${dealerOrder.orderId}</title>
<link href="${res}/styles/dealer/global.css" rel="stylesheet" />

<link href="${res}/styles/dealer/alreas.css" rel="stylesheet" />
</head>
<body>
	<div class="panel-table">
							<div class="panel-table-title">
								<div class="panel-table-top clearfix ">
									<!--附加样式名: debt:欠款 advance:预付 -->
									<div class="fl">
										<span class="yahei fs16 ml10">订单信息：</span>
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
											<th>商品信息</th>
											<th width="110">颜色</th>
											<th width="110">尺码</th>
											<th width="110">单价</th>
											<th width="110">数量</th>
											<th width="110">金额</th>
											<th width="80">数量小计</th>
											<th width="80">金额小计</th>
										</tr>
									</thead>
									<tbody id="product-item">
										<!--商品1-->
										<c:set var="MaxTotalNum" value="0"></c:set>
										<c:set var="MaxTotalPrice" value="0"></c:set>
										<c:forEach items="${dealerOrders}" var="d">
											<tr class="goods-item">
												<td class="goods-td goods-main-info cell-1">
												<a  target="_blank" href="${ctx}/market/product/${d.productId}"><img
													src="${res}${d.productImage}" width="90" height="90" /> <br />
													货号: ${d.productNo} </a>
												</td>
												<td colspan="5" class="goods-td goods-attribute">
													<table>
														<tbody>
															<c:set var="totalNum" value="0"></c:set>
															<c:set var="totalPrice" value="0"></c:set>
															<c:forEach items="${d.attrs}" var="p">
																<tr class="backf9">
																	<td class="goods-other-attribute" colspan="4">
																		<div class="attributes">
																			<table id="id1">
																				<tbody>
																					<tr>
																						<c:if test="${fn:length(p.z)==1}">
																							<td class="widthtit">默认</td>
																						</c:if>
																						<c:forEach items="${p.z}" var="zz">
																							<td class="widthtit">
																								<c:if test="${zz.icon!=null&&zz.icon!=''}">
																									<img src="${res}${zz.icon}" class="goods-color mr10" style="width:15px;height:15px; vertical-align: -6px;">
																								</c:if> ${zz.v=="0"?'':zz.v}</td>
																						</c:forEach>
																						<td class="widthtit">${p.p}</td>
																						<td class="widthtit">${p.b}</td>
																						<td class="widthtit">${p.b*p.p}</td>
																						<c:set var="totalNum" value="${totalNum+p.b}"></c:set>
																						<c:set var="totalPrice"
																							value="${totalPrice+p.b*p.p}"></c:set>
																					</tr>
																				</tbody>
																			</table>
																		</div>
																	</td>
																</tr>
															</c:forEach>
															<c:set var="MaxTotalNum" value="${MaxTotalNum+totalNum}"></c:set>
															<c:set var="MaxTotalPrice"
																value="${MaxTotalPrice+totalPrice}"></c:set>
														</tbody>
													</table>
												</td>
												<td class="goods-td goods-main-count cell-8">${totalNum}</td>
												<td class="goods-td goods-main-amount cell-9">${totalPrice}</td>
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
											<td colspan="6" class="total"><span class="fs14 yahei">进货总量：<span
													class="fs18 c-r">${MaxTotalNum}</span> 件
													&nbsp;&nbsp;|&nbsp;&nbsp; </span><span class="fs14 yahei">总价:<span>
														${MaxTotalPrice}元 
												<c:if test="${dealerOrder.adjustPrice>=0}">
													+ ${dealerOrder.adjustPrice}元  
												</c:if> 
												<c:if test="${dealerOrder.adjustPrice<0}">
													 ${dealerOrder.adjustPrice}元 
												</c:if>
												<span> ${dealerOrder.adjustPrice > 0 ? "(加价)":"(优惠)"}</span>
												<c:if test="${dealerOrder.adjustFreight==null}">
													+ (未设定)
												</c:if>
												<c:if test="${dealerOrder.adjustFreight!=null}">
													+ ${dealerOrder.adjustFreight}(运费)
												</c:if>
												<span>=</span>
														<strong class="fs18 c-r">&nbsp;${MaxTotalPrice+dealerOrder.adjustPrice+dealerOrder.adjustFreight}</strong>元</span>
											</span></td>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
	 <script>
        window.print();
    </script>
</body>
</html>
