<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
<title>管理中心-我的进货单</title>
<link href="${res}/styles/dealer/global.css" rel="stylesheet" />

<link href="${res}/styles/dealer/purchases.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<div class="main-grid clearfix" style="background: #fff;min-width:1000px;/*_width:1000px;*/">
			<c:choose>
				<c:when test="${null == shopperMaps}">
					<div style="text-align: center">
						<img src="/images/common/null.png">
					</div>
				</c:when>
				<c:otherwise>
					<form:form name="myForm" id="myForm" method="post" action="${ctx}/dealer/shopper/balance?isClient=true">
						<input type="hidden" name="productIds" id="_productIds"/>
					</form:form>
						<div class="main-grid mb10" id="settle_accounts" style="width:100%;background-color:#fff;z-index:999">
							<div class="panel-bbar clearfix">
								<div class="bar-btns fl">
									<div class="fs14">
										<input type="checkbox" id="CheckAll" class="ui-checkbox"> <label class="lab yahei">全选</label> <a
											href="javascript:;" id="DeleteAll" class="btn btn-n yahei fs14">移除</a> <a href="javascript:void(0);"
											class="btn btn-n yahei fs14" id="clearCart">清空进货单</a>
									</div>
								</div>
								<div class="bar-btns-right">
									<div class="fs14 yahei">
										<span>进货总量: <i id="AllCount" class="c-r">-</i> 件 </span><em>|</em> <span>总货款(不含运费): ￥<i id="AllAmount"
											class="total-amount c-r">-</i> </span><a data-issubmit='true' class="js-clearing ui-button ui-button-mred fs16">结
											算</a>
									</div>
								</div>
							</div>
						</div>

						<div class="main-grid clearfix" style="_margin-bottom:0;">
							<div class="shopping-cart">
								<div class="brand-list">

									<c:forEach var="map" items="${shopperMaps}" varStatus="sta">
										<ul>
											<li class="brand-item">
												<div class="detailed-list">
													<div class="detailed-list-head clearfix">
                                                        <span class="fr yahei fs14 c-sh">商品总数: ${fn:length(map.value)}</span>
                                                        <input type="checkbox" class="ui-checkbox b-chk" style="_margin-top:5px;"><span class="yahei fs14 c-sh marfu" style="_margin-top:5px;">品牌名称:${map.key}</span>

													</div>
													<div class="detailed-list-body">
														<table class="goods" style="width:100%;">
															<thead>
																<tr>
																	<th class="cell-1">商品信息</th>
																	<th class="cell-2">颜色</th>
																	<th class="cell-3">尺码</th>
																	<th class="cell-4">单价</th>
																	<th class="cell-5">库存</th>
																	<th class="cell-6">数量</th>
																	<th class="cell-7">金额</th>
																	<th class="cell-8">数量小计</th>
																	<th class="cell-9">金额小计</th>
																	<th class="cell-10">操作</th>
																</tr>
															</thead>
															<c:forEach var="product" items="${map.value}">
																<c:if test="${product.delState==false}">
																	<tbody>
																		<!--商品1-->
																		<tr class="goods-item ${product.illegal||product.beginType==3?'disabled':''} ${product.beginType==3?'offline':''} ${product.preOrdered?'preordered':''} ${product.enough==false?'notenough':''}">
																			<td class="goods-td goods-main-info cell-1">
																				<div style="position:relative">
																					<input type="hidden" name="productIds" value="${product.cartId}" class="_hidDiv" disabled="disabled" /> <input type="hidden" value="${product.startNum}" class="stNumClass" />
                                                                                    <input type="checkbox" class="ui-checkbox ${product.illegal||product.beginType==3?'':'s-chk'}" pid="${product.cartId}">

                                                                                    <i class="m-disabled" style="left: 69px;">已失效</i>
                                                                                    <i class="m-offline" style="left: 69px;">已下架</i>
                                                                                    <i class="m-notenough" style="left: 69px;">库存不足</i>

                                                                                    <img class="l-100x100" src="${res}${product.domainName}${fns:getFormateURL(product.image)}" title="${product.productTitle}" /><br />
                                                                                    <a href="${ctx}/market/product/${product.id}" target="_blank">货号:<span class="pNoClass">${product.pNo}</span></a> <br>
                                                                                    <span class="c-r">${product.preOrdered?'预定产品':'' }</span>
																				</div>
                                                                            </td>
																			<td colspan="6" class="goods-td goods-attribute">
																				<table>
																					<tbody>
																						<c:forEach var="att" items="${product.attrList}">
																							<tr class="goods-color">
																								<td class="goods-main-attribute cell-2">
																									<div style="padding-left:35px">
																										<c:if test="${att.logo!=null&&att.logo!='' }">
																											<img src="${res}${product.domainName}${fns:getFormateURL(att.logo)}"
																												style=" width:20px; height:20px;margin-left:-25px;vertical-align: -5px" class="goods-color" />
																										</c:if>
																										<span title="${att.attributeValue}">${fns:trimLongString(att.attributeValue, 5)}</span>
																									</div></td>
																								<td class="goods-other-attribute" colspan="5">
																									<div class="attributes">
																										<table>
																											<colgroup>
																												<col class="cell-3" />
																												<col class="cell-4" />
																												<col class="cell-5" />
																												<col class="cell-6" />
																												<col class="cell-7" />
																											</colgroup>
																											<tbody>
																												<c:forEach var="att_son" items="${att.listShoperAtt}" varStatus="sta3">

																													<tr class='hidTr ${att_son.buyCount>0?"js-notnull":""}'>
																														<td><span title="${att_son.attributeValue}">${fns:trimLongString(att_son.attributeValue,15)}</span></td>
																														<td class="price">${att_son.price}
                                                                                                                            <input type="hidden" name="bigImage" value="${product.image}" class="hidDiv" id="bigImage" />
                                                                                                                            <input type="hidden" name="productId" value="${product.id}" class="hidDiv" id="productId" />
                                                                                                                            <input type="hidden" name="price" value="${att_son.price}" class="hidDiv" id="price" />
                                                                                                                            <input type="hidden" name="vid" value="${att.vid}" class="hidDiv" id="vid" />
                                                                                                                            <input type="hidden" name="vidSon" value="${att_son.vid}" class="hidDiv" id="vidSon" />
                                                                                                                        </td>
																														<td>${att_son.storeCount}</td>
																														<td>
																															<div class="item-amount hidDiv">
																																<input type="text" value="${att_son.buyCount}" ${product.illegal||product.beginType==3? "disabled=disabled ":""} class="txt txt-amount hidDiv txt-buyNum" data-max="${att_son.storeCount}" data-min="10" name="buyNum" id="buyNum" />
																															</div>
                                                                                                                        </td>
																														<td class="total">0</td>
																													</tr>
																												</c:forEach>
																											</tbody>
																										</table>
																									</div>
																								</td>
																							</tr>
																						</c:forEach>
																					</tbody>
																				</table>
																			</td>
																			<td class="goods-td goods-main-count cell-8" style="text-align:center; "><span
																				class="buycountXj">0</span><br />
																			<span>${product.startNum} 件起</span></td>
																			<td class="goods-td goods-main-amount cell-9">0.00</td>
																			<td style="text-align: left;" class="goods-td goods-main-operate cell-10"><c:if
																					test="${product.illegal==false&&product.beginType!=3}">
																					<a style="padding-left: 8px;" href="javascript:void(0)" class="link addColl" pid="${product.id}"><i
																						style="color: #666;font-size: 10px;margin-right: 5px;" class="iconfont">&#xe611;</i>加入收藏夹</a>
																					<br />
																				</c:if> <a style="padding-left: 8px;" href="javascript:void(0)" class="link delOne" pid="${product.cartId}"><i
																					class="icon i-delete"></i>移除</a></td>
																		</tr>

																	</tbody>

																</c:if>
															</c:forEach>
														</table>
													</div>
												</div>
											</li>
											<li class="brand-item"></li>
										</ul>
									</c:forEach>
								</div>
							</div>
						</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
	<!--[if lte IE 8]>
	<script type="text/javascript" charset="utf-8" src="${res}/scripts/dealer/json2.js" ></script>
	<![endif]-->
	<script src="${res}/scripts/dealer/jquery.cart.js"></script>
	<script src="${res}/scripts/dealer/dealer_shopper.js"></script>
</body>
</html>
