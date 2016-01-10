<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>纠纷处理中</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    
    <link href="${res}/styles/dealer/alreas.css" rel="stylesheet" />
    <style>
        .refund_detail .detail_box { margin-left: 0; float: left; }
    </style>
</head>
<body>
    <!--完成-->
    <div class="container">
        <jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->
                <jsp:include page="${ctx}/common/menuInfo/sidemenu"  >
						<jsp:param value="2" name="openId"/>
						</jsp:include>
                <!--主体内容-->
                <div class="main-right">
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="${ctx}/dealer/dealerOrder/order" title="">已进货的订单</a> > <span class="bb">纠纷处理完毕</span>
                            <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="main-grid">
                        <div class="flow-steps mt15">
                            <ol class="mt10">
                                <li class="done current-prev">申请退款</li>
                                <li class="current"><span>品牌商处理退款申请</span></li>
                                <li class="last"><span>退款完成</span></li>
                            </ol>
                        </div>
                    </div>
                    <div class="main-grid mb10">
                        <div class="refund_detail">
                            <div class="top clearfix">
                                <div class="left">
                                    <div class="refund_info">
                                        <h3>退款信息</h3>
                                        <p>
                                            <label for="">退款单号：</label><span class="num">${dealerRefund.refundId}</span>
                                        </p>
                                        <p>
                                            <label for="">退款类型：</label>${dealerRefund.needRefund==true ? "退款退货":"仅退款"}
                                        </p>
                                        <p>
                                            <label for="">退款金额：</label><strong class="price">${dealerRefund.refundAmount}</strong>元
                                        </p>
                                    </div>

                                    <div class="order_info">
                                        ${dealerRefund.needRefund==true ? "<h3>退款/退货申请</h3>
                                        ":"<h3>订单信息</h3>
                                        "}
										<p>
                                            <label for="">订单号：</label>
                                            <a href="${ctx}/dealer/dealerOrder/orderDetail/${dealerOrder.refrenceId}" target="_blank" class="link">
                                                <span class="num">${dealerOrder.orderId}</span>
                                            </a>
                                        </p>
                                        <p>
                                            <label for="">订货日期：</label>${fns:getTimeFromLong(dealerOrder.createTime,"yyyy-MM-dd HH:mm:ss")}
                                        </p>
                                        <p>
                                            <label for="">订货数量：</label>${dealerOrder.productCount}
                                        </p>
                                        <p>
                                            <label for="">订货金额：</label><strong class="price">${dealerOrder.payment}</strong>元
                                        </p>
                                        <p>
                                            <label for="">物流费用：</label>${dealerOrder.adjustFreight}元
                                        </p>
                                        <p>
                                            <label class="fl">品牌商：</label><span class="fl" style="width: 154px;">${dealerOrder.brandName}</span>
                                        </p>
                                    </div>
                                </div>
                                <!-- 主要内容  start -->
                                <div class="detail_box">
                                    <!--处理结束-->
                                    <div class="handle_refund pt40">
                                        <div><i class="icon i-clock-big"></i></div>
                                        <h3>纠纷处理中</h3>
                                        <p class="explain">处理结果：<%--${dealerRefund.serProResult}</p> --%>
                                        <p class="explain"><strong class="c-r time" data-endtime="${endTime}"></strong>之后系统会将${dealerRefund.refundAmount}元货款打到您的账户中</p>
                                    </div>
                                    
                                    
                                </div>
                                <!-- 主要内容  end -->
                            </div>
                        </div>
                    </div>

                    <!-- 操作记录详情    start -->
                    <jsp:include page="/WEB-INF/view/dealer/refund_refReply.jsp" />
                    <!-- 操作记录详情    end   -->
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp" />
    </div>

    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
	<script type="text/javascript">
	 $(".time").cutDown(function(dhms){
	        return dhms.d + "天"+ dhms.h+"时"+dhms.m+"分"+dhms.s+"秒";
	    });
	</script>
</body>
</html>
