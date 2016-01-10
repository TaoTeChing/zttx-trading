<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>退款退货</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    
    <link href="${res}/styles/dealer/alreas.css" rel="stylesheet" />
    <style>
        .refund_detail .detail_box{
            float: left;
            display: inline;
            margin-left: 0;
            padding-bottom: 9999px;
            margin-bottom: -9999px;
        }
    </style>

</head>
<body>
    <!--完成-->
    <div class="container">
       <jsp:include page="${ctx}/dealer/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->
                <jsp:include page="${ctx}/dealer/dealermenu" />
                <!--主体内容-->
                <div class="main-right">
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="index.jsp">管理中心</a> >> <a href="${ctx }/dealer/myorder" title="">已进货的订单</a> >> <a href="Purchases-refund-1.jsp" title="">申请退款之后</a> > <span class="bb">退款成功</span>
                            <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="main-grid">
                        <div class="flow-steps flow-4 mt15">
                            <!-- 第三步 -->
                            <ol class="mt10">
                                <li class="done">申请退款</li>
                                <li class="done current-prev"><span>品牌商处理退款申请</span></li>
                                <li class="current"><span>退货给品牌商</span></li>
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
                                            <label for="">退款单号：</label><span class="num">${dealerRefund.orderNumber}</span>
                                        </p>
                                        <p>
                                            <label for="">退款类型：</label>${dealerRefund.needRefund==true ? "退款退货":"仅退款"}
                                        </p>
                                        <p>
                                            <label for="">退款金额：</label><strong class="price">${dealerRefund.refundAmount}</strong>元
                                        </p>
                                    </div>
                                    
                                    <div class="order_info">
										<h3>订单信息</h3>
										<p>
										    <label for="">订单号：</label><span class="num">${dealerOrder.orderId}</span>
										</p>
										<p>
										    <label for="">订货日期：</label>${dealerOrder.createTime}
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
										    <label for="">品牌商：</label>${dealerOrder.brandName}
										</p>
									</div>
                                </div>
                                <div class="detail_box clearfix">

                                    <div class="handle_refund" style="margin-left:40px;">
                                        <h3>请退货并填写物流信息</h3>
                                        
                                        <p class="explain">
                                        您还有 <strong class="c-r time" data-endtime="${endTime}"></strong> 来退货
                                        </p>
                                        
                                        <p class="fs14 bb c-h yahei lh2">未经品牌商同意,请勿使用到付</p>
                                        <p class="mt10">退货地址：${dealerRefund.brandRecAddr}</p>
                                        <form:form id="returnDeliverForm" class="ui-form mt20" style="margin-left:-170px;" 
                                        	action="${ctx}/dealer/refund/returnDeliver" method="post">
                                            <div class="ui-form-item">
                                                <span class="ui-form-text bb c-h">请填写物流信息</span>
                                            </div>
                                            <input type="hidden" name="refrenceId" value="${dealerRefund.refrenceId}" />
                                            <div class="ui-form-item">
                                                <label>物流公司：</label>
                                                <input class="ui-input" style="width:300px" name="logisticsName" dataType="*"/>
                                            </div>
                                            <div class="ui-form-item">
                                                <label>运 单 号：</label>
                                                <input class="ui-input" style="width:300px" name="transNum" dataType="*"/>
                                            </div>
                                            <div class="ui-form-item">
                                                <input class="ui-button ui-button-mred" type="submit" value="提交退货信息" />
                                            </div>
                                        </form:form>

                                    </div>
                                </div>

                            </div>
                        </div>
                        <!-- <div class="main-grid mt20">
                            <ul class="c-h">
                                <li class="lh2">

                                    <span class="c-r">*</span> <span>2012-02-15 12:36</span> <span>您的品牌商【朵彩】发起了退款申请</span> <span class="ml5">退款金额为：5689.00元</span>

                                </li>
                                <li class="lh2">
                                    <span class="c-r">*</span> <span>2012-02-15 12:36</span> <span>您的品牌商【朵彩】发起了退款申请</span> <span class="ml5">退款金额为：5689.00元</span>

                                </li>
                            </ul>
                        </div> -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
    
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <link href="${res}/styles/common/validformStyle.css" rel="stylesheet" type="text/css" />
	<script src="${res}/scripts/plugin/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#returnDeliverForm").Validform({
				tiptype : 4,
				postonce : false,
				ajaxPost : false
			});
		})
	</script>
    <script>
        // 显示提示时间
        $(".time").cutDown(function(dhms){
		        return dhms.d + "天"+ dhms.h+"时"+dhms.m+"分"+dhms.s+"秒"
		    },function(){
		        $(".explain").remove();
	    },function(){
		        $(".explain").remove();
	    });
    </script>
    
</body>
</html>
