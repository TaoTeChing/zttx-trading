<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-退款退货申请</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/alreas.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
        <jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->
                <jsp:include page="${ctx}/common/menuInfo/sidemenu"   >
						<jsp:param value="2" name="openId"/>
				</jsp:include>
                <!--主体内容-->
                <div class="main-right">
                    <!--提示框-->
                    <!--<div class="main-grid mb10">
                        <div class="alert c-w warning">
                            <b class="icon i-close">X</b><i class="icon"></i><a href="#">预警消息:您有一笔订单超过15天未付款! 2013-10-16</a>
                        </div>
                    </div>-->
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="${ctx}/dealer/dealerOrder/order" title="">已进货的订单</a> > <span class="bb">退款/退货申请</span>
                            <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
					
                    <div class="main-grid mb10">
                        <div class="refund_detail">
                            <div class="top clearfix">
                            	<!---- 退款/退货申请：订单信息栏目  ---->
                                <div class="left">
								    <div class="order_info">
									<h3>订单信息</h3>
									<p>
									    <label for="">订单号：</label><span class="num">${dealerOrder.orderId}</span>
									</p>
									<p>
									    <label for="">订货日期：</label>${fns:getTimeFromLong(dealerOrder.createTime,"yyyy-MM-dd HH:mm:ss")}
									</p>
									<p>
									    <label for="">订货数量：</label>${dealerOrder.productCount}
									</p>
									<p>
									    <label for="">订货金额：</label><strong class="price">${dealerOrder.productPrice + dealerOrder.adjustPrice}</strong>元
									</p>
									<p>
									    <label for="">物流费用：</label>${dealerOrder.adjustFreight}元
									</p>
									<p>
									    <label class="fl">品牌商：</label><span class="fl" style="width: 154px;">${dealerOrder.brandName}</span>
									</p>
								    </div>
								</div>    
                                <div class="detail_box ta-c">
                                    <a style="color: #666;" class="ui-button ui-button-lorange ui-button-refund yahei fs18" href="${ctx}/dealer/refund/choice?needRefund=false&orderNumber=${orderNumber}">仅退款不退货</a>
                                    <a style="color: #666;" class="ui-button ui-button-lorange ui-button-refund yahei fs18" href="${ctx}/dealer/refund/choice?&needRefund=true&orderNumber=${orderNumber}"> 退款退货</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
    
    <div class="hide">
        <div id="close_refund">
            <div class="ui-head">
                <h3>撤销退款申请</h3>
            </div>

            <p class="fs14 yahei" style="margin:20px;">
                确定撤销退款申请吗?
            </p>
            <div class="ta-c mb10">
                <a href="Purchases-refund-2-close.jsp" class="ui-button ui-button-morange">确定</a><a class="ui-button ui-button-morange ml20">取消</a>
            </div>

        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <tags:message content="${message}" width="300"></tags:message>
    <script>
        seajs.use(["$", 'dialog'], function ($, Dialog) {
            var d1 = new Dialog({
                trigger: '#btn_close_refund',
                content: "#close_refund",
                width: 360
            });
        });
    </script>
</body>
</html>
