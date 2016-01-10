<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-交易管理-采购的订单-退款详情</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/refund_detail.css"/>
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
        <span class="current">退款详情</span>
    </div>
    <div class="fr">
        <%@ include file="common_quick_bar.jsp" %>
    </div>
</div>
<div class="inner">
<!-- 内容都放这个地方  -->
    <!-- 步骤条 -->
    <div class="v2-steps v2-3steps">
        <span class="text1">申请退款</span>
        <span class="text2 current">品牌商处理退款申请</span>
        <span class="text3">退款完成</span>
        <div class="steps v2-steps-2"></div>
    </div>
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
                    <h3>订单信息</h3>
                    <p>
                        <label for="">订单号：</label><a href="${ctx}/brand/order/view/${dealerOrder.refrenceId}" target="_blank" class="link">
                                                <span class="num">${dealerOrder.orderId}</span>
                                            </a>
                    </p>
                    <p>
                        <label for="">订货日期：</label>${fns:getStringTime(dealerOrder.createTime,"yyyy-MM-dd") }
                    </p>
                    <p>
                        <label for=""> 订货数量：</label>${dealerOrder.productCount}
                    </p>
                    <p>
                        <label for="">订货金额：</label><strong class="price">${dealerOrder.payment}</strong>元
                    </p>
                    <p>
                        <label for="">物流费用：</label>${dealerOrder.adjustFreight}元
                    </p>
                    <p>
                        <label for="">终端商：</label>${dealerOrder.dealerName}
                    </p>
                </div>
            </div>
            <div class="detail_box">
              <%-- 已要求客服介入,等待客服介入 --%>
                                	<c:if test="${dealerRefund.cusJoin==true}">

                                        <div class="tips">
                                           <c:if test="${dealerRefund.serProStatus==1}">
                                            <i class="iconfont c-w mr5">&#xe626;</i>客服人员正在介入，请耐心等待...
                                           </c:if>
                                           <c:if test="${dealerRefund.serProStatus==2}">
                                            <i class="iconfont c-w mr5">&#xe626;</i>纠纷处理中…
                                           </c:if>
                                            <c:if test="${dealerRefund.serProStatus==3}">
                                            <i class="iconfont c-w mr5">&#xe626;</i>纠纷处理完毕
                                           </c:if>
                                            <c:if test="${dealerRefund.serProStatus==4}">
                                            <i class="iconfont c-w mr5">&#xe626;</i>纠纷已关闭
                                           </c:if>
                                            <c:if test="${dealerRefund.serProStatus==5}">
                                            <i class="iconfont c-w mr5">&#xe626;</i>等待客服介入中...
                                           </c:if>
                                        </div>

                                	</c:if>
                                	
									<%-- 申请退款等待处理 --%>
                <div class="status_contain">
                    <i class="iconfont agree_icon">&#xe628;</i>
                    <div class="info">
                        <h3>同意退款，等待终端商退货</h3>
                        <p class="explain mt10">终端商还有 <strong class="c-r time" data-endtime="${endTime}"  style=" color:red"></strong> 来处理退货</p>
                        <div class="address mt15">
                            <label for="">退货地址：</label>${dealerRefund.brandRecAddr}
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="bottom">
           
            <div class="info">
               
            </div>
             <!-- 操作记录详情    start -->
                <jsp:include page="/WEB-INF/view/dealer/refund_refReply.jsp" />
                <!-- 操作记录详情    end   -->
        </div>
    </div>
</div>
</div>
</div>
<%@ include file="bottom.jsp" %>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script>
$(".c-r.time").cutDown(function(dhms){
		        return dhms.d + "天"+ dhms.h+"时"+dhms.m+"分"+dhms.s+"秒"
		    });

</script>
</body>
</html>