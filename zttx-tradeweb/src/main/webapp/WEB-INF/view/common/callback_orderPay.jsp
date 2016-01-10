<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%@ page import="com.zttx.web.consts.ZttxConst" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>付款成功</title>
    <meta name="keywords" content="8637,品牌超级代理,品牌招商,招商加盟，天下商邦"/>
    <meta name="description" content="“8637品牌超级代理”以B2B2C（从品牌商到终端店铺再到消费者）的模式构建零中间商的招商加盟直供平台。利用互联网技术服务品牌商及终端商，改变传统品牌代理供应链（专卖店、批发商、代理商、品牌商），通过互联网直连方式招商加盟，减少中间供应环节，打造从厂家到店铺的最短供应链。"/>
    <link href="${res}/styles/common/base.css" rel="stylesheet" />
    <link href="${res}/styles/fronts/market/pay-state.css" rel="stylesheet" />
</head>
<body>
<%--     回调成功${orderPayCallbackModel.id},金额：${orderPayCallbackModel.amount}
    <c:forEach var="obj" items="${dealerOrderPayList}" varStatus="status">
		<c:if test="${status.index == 0}">
			<c:if test="${obj.orderType == 0}">
    			<div><a href="/dealer/myorder">我的订单</a></div>
			</c:if>
			<c:if test="${obj.orderType == 1}">
				<div><a href="/dealer/service">我的服务</a></div>
			</c:if>
		</c:if>
	</c:forEach> --%>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <jsp:include page="/WEB-INF/view/include/search.jsp"/>
    <jsp:include page="/WEB-INF/view/include/nav.jsp">
        <jsp:param value="1" name="m"/>
    </jsp:include>
    <%-- nav end --%>
    <div class="main ts-container">
       <!--  <div style="height:20px;line-height:20px;font-family: '宋体';">
            <a href="/">首页</a>
            <span style="color: #999;">&gt;&gt;</span>
            <span>我的进货单</span>
        </div> -->
        <div class="pay-state-con">
            <div class="clearfix mt10">
                <div class="con1 fl">
                    <div class="state-box">
                        
                        <%--成功--%>
                        <i class="icon-success"></i>
                        <p class="p-success">您已成功支付${orderPayCallbackModel.amount}元</p>
                        
                        
                        <c:forEach var="obj" items="${dealerOrderPayList}" varStatus="status">
                        	<c:if test="${obj.orderType == 0}">
				    			<p class="c-h mt5">订单号：${obj.orderNo}&nbsp;<a class="link" href="${ctx}/dealer/dealerOrder/orderDetail/${obj.orderId}" target="_blank">查看详情</a></p>
				    			<c:if test="${status.index == 0}">
				    				<%--<p class="mt5 c-h"><a class="link" href="${ctx}/common/dealer/productList" target="_blank">继续采购</a> |--%> <a class="link" href="${ctx}/dealer/dealerOrder/order" target="_blank">查看我的进货记录</a></p>
								</c:if>
							</c:if>
                        </c:forEach>
                        <%--失败--%>
                        <%--  
                        <i class="icon-error"></i>
                        <p class="p-error">对不起，支付未成功</p>
                        <p class="mt5"><a class="link" href="#" target="_blank">查看原因并继续付款</a></p> --%>
                    </div>
                    <div class="state-box-bottom">
                        <span class="c-h">您可能还需要：</span>
                        <a class="link" href="<%=ZttxConst.PAYAPI_WEBURL_PORTAL%>" target="_blank">查看余额</a> | <a class="link" href="<%=ZttxConst.PAYAPI_WEBURL_TRADE%>" target="_blank">消费记录</a> | <a class="link" href="<%=ZttxConst.PAYAPI_WEBURL_RECHARGE%>" target="_blank">我要充值</a>
                    </div>
                </div>
                <div class="con2">
                    <h3 class="title">
                        客服咨询
                    </h3>
                    <div class="phone-number c-h">
                        <span>如果您有任何问题，可以联系客服</span>
                        <div class="phone mt10 mb10"></div>
                        <span>工作时间：周一至周五 （8:30--20:30）</span>
                    </div>
                </div>
            </div>
            <%--<div class="recomed mt10">
                相关产品推荐
            </div>
            <ul class="inline-float product-item mt5">
                <li>
                    <div class="js-img-center">
                        <img src="" alt=""/>
                    </div>
                    <div class="price">
                        <span class="p-sing">价格</span>
                        <span class="p-num">45.00</span>
                        <span class="p-del">￥398.00</span>
                    </div>
                    <div class="title mt5" title="2014夏季新款女装文艺复古清新印花雪纺连衣裙送项链">
                        2014夏季新款女装文艺复古清新印花雪纺连衣裙送项链
                    </div>
                    <div class="operate" style="">
                        <a href="javascript:;" class="ts-button btn-addcart">加入进货单</a>
                        <a href="javascript:;" class="ts-button btn-favorites">收藏</a>
                    </div>
                </li>
                <li>
                    <div class="js-img-center">
                        <img src="" alt=""/>
                    </div>
                    <div class="price">
                        <span class="p-sing">价格</span>
                        <span class="p-num">45.00</span>
                        <span class="p-del">￥398.00</span>
                    </div>
                    <div class="title mt5" title="2014夏季新款女装文艺复古清新印花雪纺连衣裙送项链">
                        2014夏季新款女装文艺复古清新印花雪纺连衣裙送项链
                    </div>
                    <div class="operate" style="">
                        <a href="javascript:;" class="ts-button btn-addcart">加入进货单</a>
                        <a href="javascript:;" class="ts-button btn-favorites">收藏</a>
                    </div>
                </li>
                <li>
                    <div class="js-img-center">
                        <img src="" alt=""/>
                    </div>
                    <div class="price">
                        <span class="p-sing">价格</span>
                        <span class="p-num">45.00</span>
                        <span class="p-del">￥398.00</span>
                    </div>
                    <div class="title mt5" title="2014夏季新款女装文艺复古清新印花雪纺连衣裙送项链">
                        2014夏季新款女装文艺复古清新印花雪纺连衣裙送项链
                    </div>
                    <div class="operate" style="">
                        <a href="javascript:;" class="ts-button btn-addcart">加入进货单</a>
                        <a href="javascript:;" class="ts-button btn-favorites">收藏</a>
                    </div>
                </li>
                <li>
                    <div class="js-img-center">
                        <img src="" alt=""/>
                    </div>
                    <div class="price">
                        <span class="p-sing">价格</span>
                        <span class="p-num">45.00</span>
                        <span class="p-del">￥398.00</span>
                    </div>
                    <div class="title mt5" title="2014夏季新款女装文艺复古清新印花雪纺连衣裙送项链">
                        2014夏季新款女装文艺复古清新印花雪纺连衣裙送项链
                    </div>
                    <div class="operate" style="">
                        <a href="javascript:;" class="ts-button btn-addcart">加入进货单</a>
                        <a href="javascript:;" class="ts-button btn-favorites">收藏</a>
                    </div>
                </li>
                <li>
                    <div class="js-img-center">
                        <img src="" alt=""/>
                    </div>
                    <div class="price">
                        <span class="p-sing">价格</span>
                        <span class="p-num">45.00</span>
                        <span class="p-del">￥398.00</span>
                    </div>
                    <div class="title mt5" title="2014夏季新款女装文艺复古清新印花雪纺连衣裙送项链">
                        2014夏季新款女装文艺复古清新印花雪纺连衣裙送项链
                    </div>
                    <div class="operate" style="">
                        <a href="javascript:;" class="ts-button btn-addcart">加入进货单</a>
                        <a href="javascript:;" class="ts-button btn-favorites">收藏</a>
                    </div>
                </li>
                <li>
                    <div class="js-img-center">
                        <img src="" alt=""/>
                    </div>
                    <div class="price">
                        <span class="p-sing">价格</span>
                        <span class="p-num">45.00</span>
                        <span class="p-del">￥398.00</span>
                    </div>
                    <div class="title mt5" title="2014夏季新款女装文艺复古清新印花雪纺连衣裙送项链">
                        2014夏季新款女装文艺复古清新印花雪纺连衣裙送项链
                    </div>
                    <div class="operate" style="">
                        <a href="javascript:;" class="ts-button btn-addcart">加入进货单</a>
                        <a href="javascript:;" class="ts-button btn-favorites">收藏</a>
                    </div>
                </li>
            </ul>--%>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
    <%-- footer end --%>
    <jsp:include page="/WEB-INF/view/include/bottom.jsp"></jsp:include>
</div>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js" type="text/javascript"></script>
<script src="${res}/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script src="${res}/scripts/seajs_config.js" type="text/javascript"></script>
<script src="${src}/common/base-init.js"></script>
<script></script>
</body>
</html>