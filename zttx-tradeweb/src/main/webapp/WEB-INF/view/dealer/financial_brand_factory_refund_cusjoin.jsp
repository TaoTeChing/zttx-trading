<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>管理中心-纠纷处理中</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet"/>

    <link href="${res}/styles/dealer/alreas.css" rel="stylesheet"/>
    <style>
        .refund_detail .detail_box {
            margin-left: 0;
            float: left;
        }
    </style>
</head>
<body>
<!--完成-->
<div class="container">
    <jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
    <div class="em100">
        <div class="main clearfix pr">
            <!--侧边导航-->
            <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
            <!--主体内容-->
            <div class="main-right">
                <!--面包屑-->
                <div class="main-grid mb10">
                    <div class="panel-crumbs">
                        <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a
                            href="${ctx}/dealer/dealerFinancial/financial">品牌财务账</a> > <span class="bb">纠纷处理</span>
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
                                        <label for="">退款金额：</label><strong
                                            class="price">${dealerRefund.refundAmount}</strong>元
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

                                    <p class="explain"><strong class="c-r time" data-endtime="${endTime}"></strong>之后系统会将${dealerRefund.refundAmount}元货款打到您的账户中
                                    </p>
                                </div>
                            </div>
                            <!-- 主要内容  end -->
                        </div>
                    </div>
                </div>

                <!-- 操作记录详情    start -->
                <jsp:include page="/WEB-INF/view/dealer/refund_refReply.jsp"/>
                <!-- 操作记录详情    end   -->
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>

<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp"/>
<script type="text/javascript">
    $(".time").cutDown(function (dhms) {
        return dhms.d + "天" + dhms.h + "时" + dhms.m + "分" + dhms.s + "秒";
    });
</script>
</body>
</html>
