<%@ page import="com.zttx.web.consts.BrandConstant" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>


<c:forEach items="${topProductResult.list}" var="product">
 	<c:set value="${fns:getImageDomainPath(product.productImage,440,440)}" var="productImage"></c:set>
    <li>
        <div class="list-item clearfix">
            <a class="list-item-pic js-img-center" href="${ctx}/market/product/${product.refrenceId}" title="${product.productTitle }" alt="${product.productTitle }" target="_blank">
                <img src="${res}${productImage}" width="250" height="250">
                <div class="list-item-mask">
                    <div class="list-item-logo js-img-center">
                        <img alt="${product.brandsName}" src="${res }${product.brandLogo}"><span></span>
                    </div>
                </div>
                <span></span>
            </a>
            <div class="info">
                <p class="fn-text-overflow yahei fs14" title="${product.productTitle }" style="width:200px;color: #666;">
                    <a href="#">${product.productTitle }</a>
                </p>
                <p class="clearfix">

                		<c:if test="${product.userGrantModel.isAuth}">

                				<c:if test="${product.userGrantModel.priceType}==<%=BrandConstant.productPriceType.PRODUCT_DIR%>">
                					<label>直供价：</label>
				                    <span class="text-yahei text-red" style="font-size: 14px;vertical-align: -1px;">
				                        <em>￥</em><fmt:formatNumber value="${product.userGrantModel.proLowestSkuPrice}" type="currency" pattern="0.00" />
				                    </span>
				                    <del class="text-yahei"><em>￥</em><fmt:formatNumber value="${product.productPrice}" type="currency" pattern="0.00" /></del>
                				</c:if>
                				<c:if test="${product.userGrantModel.priceType}==<%=BrandConstant.productPriceType.PRODUCT_ACTVITY%>">
									<label>爆款价：</label>
				                    <span class="text-yahei text-red" style="font-size: 14px;vertical-align: -1px;">
				                        <em>￥</em><fmt:formatNumber value="${product.userGrantModel.proLowestSkuPrice}" type="currency" pattern="0.00" />
				                    </span>
									<del class="text-yahei"><em>￥</em><fmt:formatNumber value="${product.productPrice}" type="currency" pattern="0.00" /></del>
                				</c:if>
                                 <c:if test="${product.userGrantModel.priceType}==<%=BrandConstant.productPriceType.PRODUCT_ACTVITY%>">
									 <label>吊牌价：</label>
		                            <span class="text-yahei" style="font-size: 14px;vertical-align: -1px;">
		                                <em>￥</em><fmt:formatNumber value="${product.userGrantModel.productPrice}" type="currency" pattern="0.00" />
		                            </span>
								 </c:if>
                		</c:if>
                		<c:if test="${!product.userGrantModel.isAuth}">
                			该产品未开放直供价，请询价
                		</c:if>
                </p>
            </div>
            <div class="operate clearfix">
            	<c:choose>
               		<c:when test="${product.userGrantModel.isAuth}">
               			<a href="${ctx}/market/product/${product.refrenceId}" class="ts-button ts-button-orange fl">加入进货单</a>
               		</c:when>
               		<c:otherwise>
               			<a href="${ctx}/market/product/${product.refrenceId}" class="ts-button btn-query fl">我要询价</a>
               		</c:otherwise>
               	</c:choose>
				<c:set var="domain" value="${fns:getBrandsIdSubDomain(product.brandsId)}"></c:set>
                <a href="http://${domain}${zttx}/index" class="fr mt5 yahei fn-text-overflow">${product.brandsName}</a>
            </div>
        </div>
    </li>
</c:forEach>