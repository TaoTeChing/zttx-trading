<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>管理中心 &gt; 铺货订单 &gt; 确认付款</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet"/>
    <link href="${res}/styles/dealer/alreas.css" rel="stylesheet"/>
</head>
<body>
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
                        <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <span
                            class="bb">确认付款</span>
                        <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <!--确认付款开始-->
                <div class="main-grid factory-con p10">
                    <c:if test="${fn:length(distriOrders)!=0}">
                        <div class="factory-confirm-order">
                            <div class="title">
                                <input type="checkbox" class="ui-checkbox js-fm-chk"/>授信订单
                            </div>
                            <ul class="factory-ul">
                                <c:forEach items="${distriOrders}" var="order">
                                    <li class="js-item clearfix">
                                        <input type="hidden" value="${order.refrenceId}" name="distriOrderId"/>
                                        <input type="hidden" class="js-part-price" name="distriOrderAmount" value="${order.payCash+order.adjustFreight}">
                                        <div class="brand-name">
                                            <a class="fr link"
                                               href="${ctx}/dealer/dealerOrder/orderDetail/${order.refrenceId}"
                                               target="_blank">查看订单详情</a>
                                            <input type="checkbox" class="js-fs-chk" name="distriChk"/>
                                            <span class="c-h">品牌名称：</span><span>${order.brandsName}</span>
                                        </div>
                                        <div class="order-price">
                                            <div class="order clearfix">
                                                <div class="fl">订单货款:<span class="number"><em class="msyh">￥</em>
                                                    <em class="js-tot-price">${order.productPrice+order.adjustPrice+order.adjustFreight}</em></span>
                                                    <c:if test="${order.frePayType==60 }">(包邮)</c:if>
                                                    <c:if test="${order.frePayType==1 }">(含快递:${order.adjustFreight }元)</c:if>
                                                    <c:if test="${order.frePayType==2 }">(含物流:${order.adjustFreight }元)</c:if>
                                                    <c:if test="${order.frePayType==3 }">(快递到付)</c:if>
                                                    <c:if test="${order.frePayType==4 }">(物流到付)</c:if>
                                                    &nbsp; &nbsp; 剩余可用授信：<span class="number"><em
                                                            class="msyh">￥</em><em>${order.creditCurrent}</em></span>
                                                    &nbsp; &nbsp; 需支付现款：<span class="number"><em
                                                            class="msyh">￥</em><em class="js-all-price">${order.payCash}</em></span>
                                                </div>
                                            </div>

                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                    <c:if test="${fn:length(normalOrders)!=0}">
                        <div class="nomal-confirm-order mt20">
                            <div class="title">
                                <input type="checkbox" class="ui-checkbox js-nm-chk"/>现款订单
                            </div>
                            <ul>
                                <c:forEach items="${normalOrders}" var="order">
                                    <li class="clearfix js-item">
                                        <input type="hidden" value="${order.refrenceId}" name="normalOrderId"/>

                                        <div class="fl">
                                            <input type="checkbox" class="js-ns-chk" name="normalChk"/>
                                                <span class="c-h">品牌名称：</span><span>${order.brandsName}</span>
                                            <span class="ml20">订单货款:</span><span class="number"><em
                                                class="msyh">￥</em><em
                                                class="js-all-price">${order.productPrice+order.adjustPrice+order.adjustFreight}</em></span>
                                            <c:if test="${order.frePayType==60 }">(包邮)</c:if>
                                            <c:if test="${order.frePayType==1 }">(含快递:${order.adjustFreight }元)</c:if>
                                            <c:if test="${order.frePayType==2 }">(含物流:${order.adjustFreight }元)</c:if>
                                            <c:if test="${order.frePayType==3 }">(快递到付)</c:if>
                                            <c:if test="${order.frePayType==4 }">(物流到付)</c:if>
                                        </div>
                                        <div class="fr">
                                            <a class="link"
                                               href="${ctx}/dealer/dealerOrder/orderDetail/${order.refrenceId}"
                                               target="_blank">查看订单详情</a>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>

                    <%--暂时取消掉 --%>
                    <div class="factory-count-box mt10">
                        共计支付现款：<span class="number"><em class="msyh">￥</em><span id="rest_order_money">0.00</span></span> &nbsp;&nbsp;&nbsp;
                        授信货款：<span class="number"><em class="msyh">￥</em><span id="rest_credit_money">0.00</span></span>
                    </div>

                    <%--暂时取消掉
                    <div class="factory-use-price mt10">
                        <div><input type="checkbox" class="ui-checkbox"/> 使用账户余额支付：<span class="number-r">888.00</span>元（当前可用余额 <span class="number-r">79845454.00</span>元）</div>
                        <div><input type="checkbox" class="ui-checkbox"/> 使用网银支付<span class="number-r">888.00</span>元</div>
                    </div>
                     --%>
                    <%--
                    <!--后台原来的-->
                    工厂店订单:<br/>
                    <c:forEach items="${distriOrders}" var="order">
                        <div id="distri_orders">
                            <input type="hidden" value="${order.refrenceId}" name="distriOrderId"/>
                            <div>品牌商名称:${order.brandName} 订单货款:${order.productPrice+order.adjustPrice} &nbsp;<a href="${ctx}/dealer/order/view/${order.refrenceId}" target="_blank">查看订单详情</a><br>
                                <input type="radio" name="distriOrderType" value="2" checked="checked"> 协议付款 <input type="radio" name="distriOrderType" value="1"> 全额付款<br>
                                付款金额:<input type="text" name="distriOrderAmount" value="0">
                            </div>
                        </div>

                    </c:forEach>
                    <hr/>
                    普通订单:
                    <div>
                        <c:forEach items="${normalOrders}" var="order">
                            <div>品牌商名称:${order.brandName} 订单货款:${order.productPrice+order.adjustPrice} &nbsp;<a href="${ctx}/dealer/order/view/${order.refrenceId}" target="_blank">查看订单详情</a></div>
                        </c:forEach>
                    </div>--%>


                    <form:form action="${ctx }/dealer/dealerOrder/payment/confirm" id="confirm_payment_form">
                        <input type="hidden" value="" name="distriOrderIds" id="distriOrderIds"/>
                        <input type="hidden" value="" name="distriOrderTypes" id="distriOrderTypes"/>
                        <input type="hidden" value="" name="distriOrderAmounts" id="distriOrderAmounts"/>
                        <input type="hidden" value="" name="normalOrderIds" id="normalOrderIds"/>
                    </form:form>


                    <div class="factory-agree">
                        <button class="ui-button ui-button-lred mt20" type="button" id="confirem_btn" onclick="payment.confirmPayment()">确定付款</button>
                        <%--bug #4088 去掉 <c:if test="${fn:length(distriOrders)!=0}">
                        	 <div class="mt10"><input type="checkbox" class="ui-checkbox" id="protocol_doc"/> 我同意8637交易平台关于<a href="${ctx}/common/rules/info/E6D7B852340D4891BF878089F52A1055" target="_blank">协议付款保证金协议</a></div>
                        </c:if>--%>
                    </div>
                </div>
                <!--确认付款开始-->
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="${ctx}/WEB-INF/view/dealer/import_javascript.jsp"/>
<script>
    var payment = {};
    /**确认付款事件*/
    payment.confirmPayment = function () {
        var distriOrderIds = [];
        var creditOrderIds = [];
        var distriOrderTypes = [];
        var distriOrderAmounts = [];
        var bAmountChk = false;
        $('input[name=distriChk]:checked').each(function (i, o) {
            distriOrderIds.push($(this).parent().parent().find('input[name=distriOrderId]').val());
            distriOrderTypes.push($(this).parent().parent().find('.js-check-item:checked').val());
            var _amount = $(this).parent().parent().find('input[name=distriOrderAmount]').val();
            if ($(this).parent().parent().find('.js-check-item:checked').val() == 2) {
                if (_amount == null || _amount == '' || _amount == 0) {
                    remind("error", "你有订单未输入货款");
                    $(this).parent().parent().find('input[name=distriOrderAmount]').focus();
                    bAmountChk = true;
                    return;
                }
            }
            //授信订单ID集合
            if($(this).hasClass(".js-fs-chk")){
                creditOrderIds.push($(this).parent().parent().find('input[name=distriOrderId]').val());
            }

            distriOrderAmounts.push(_amount);
        });
        if (bAmountChk) {
            return;
        }

        $('#distriOrderIds').val(distriOrderIds.join(","));
        $('#distriOrderTypes').val(distriOrderTypes.join(","));
        $('#distriOrderAmounts').val(distriOrderAmounts.join(","));
        var normalOrderIds = [];
        $('input[name=normalChk]:checked').each(function (i, o) {
            normalOrderIds.push($(this).parent().parent().find('input[name="normalOrderId"]').val());
        });
        $('#normalOrderIds').val(normalOrderIds.join(","));


        if (distriOrderIds.length == 0 && normalOrderIds.length == 0) {
            remind("error", "至少选择一个订单!");
            return;
        }

        var submitCheck = checkOrder();

        /*$.ajax({
            url: "",
            method: "post",
            data: "",
            dataType: "json",
            success: function(data){*/
                //
                if (submitCheck) {
                    $("#confirem_btn").prop("disabled", true);
                    $('#confirm_payment_form')[0].submit();
                } else {
                    remind("error", "无法支付，请检查您所输入的价格");
                }
            /*}
        });*/
    };


    function checkOrder(){
        var orderCount = 0, creditCount = 0;
        $(".js-item").each(function(i){
            if ($(this).find(".js-fs-chk").prop("checked")){
                var price = parseFloat($(this).find(".js-all-price").text());
                var totPrice = parseFloat($(this).find(".js-tot-price").text());
                orderCount += price;
                creditCount += (totPrice - price);
            }
            if ($(this).find(".js-ns-chk").prop("checked")){
                var price = parseFloat($(this).find(".js-all-price").text());
                orderCount += price;
            }
        });
        $("#rest_order_money").html(orderCount.toFixed(2));
        $("#rest_credit_money").html(creditCount.toFixed(2));
        return true;
    }


    //工厂店订单全选
    checkAll(".js-fm-chk", ".js-fs-chk", checkOrder);
    //普通订单全选
    checkAll(".js-nm-chk", ".js-ns-chk", checkOrder);

    $(".js-check-item").click(function () {
        checkOrder();
    });


</script>
</body>
</html>