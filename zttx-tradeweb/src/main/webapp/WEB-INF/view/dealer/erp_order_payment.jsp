<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>管理中心  &gt; 铺货订单 &gt; 确认付款</title>
    <link href="${ctx}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${ctx}/styles/dealer/alreas.css" rel="stylesheet" />
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/dealer/mainmenu"/>
    <div class="em100">
        <div class="main clearfix pr">
            <!--侧边导航-->
                <!--确认付款开始-->
                <div class="main-grid factory-con">
                <c:if test="${fn:length(distriOrders)!=0}">
                    <div class="factory-confirm-order">
                        <div class="title">
                            <input type="checkbox" class="ui-checkbox js-fm-chk" />工厂店订单
                        </div>
                        <ul class="factory-ul">
                            <c:forEach items="${distriOrders}" var="order">
                            <li class="js-item clearfix">
                            	<input type="hidden" value="${order.refrenceId}" name="distriOrderId"/>
                                <div class="brand-name">
                                    <a class="fr link" href="${ctx}/dealer/order/view/${order.refrenceId}" target="_blank">查看订单详情</a>
                                    <input type="checkbox" class="js-fs-chk" name="distriChk"/>
                                    <span class="c-h">品牌商名称：</span><span>${order.brandName}</span>
                                </div>
                                <div class="order-price">
                                    <div class="order clearfix">
                                        <div class="fl">订单货款:<span class="number"><em class="msyh">￥</em><em class="js-all-price">${order.productPrice+order.adjustPrice+order.adjustFreight}</em></span>
                                        <c:if test="${order.adjustFreight==null}">(物流未设定)</c:if><c:if test="${order.adjustFreight!=null }"><c:choose><c:when test="${order.adjustFreight =='0.00' }">(包邮)</c:when><c:otherwise>(含物流:${order.adjustFreight }元)</c:otherwise> </c:choose> </c:if>
                                        </div>
                                        <div class="fr">
                                            <label class="js-more"><input class="ui-radio js-check-item" type="radio" name="distriOrderType_${order.refrenceId}" value="2" disabled="disabled"  onclick="payment.showPriceDiv('price_div_${order.refrenceId}')"> 协议付款</label>
                                            <label><input class="ui-radio js-check-item" type="radio" name="distriOrderType_${order.refrenceId}" value="1" checked onclick="payment.hidePriceDiv('price_div_${order.refrenceId}')"> 全额付款</label>
                                        </div>
                                    </div>
                                    <div class="price" id="price_div_${order.refrenceId}" style="display: none;">
                                        <label class="fr">
                                            付款金额:
                                            <input class="ui-input js-part-price js-price self-ui-input" data-min="${order.frePayState==0?order.adjustFreight:0.0}" type="text" name="distriOrderAmount" value="${order.frePayState==0?order.adjustFreight:0.0}">
                                        </label>
                                    </div>
                                    <div class="clearfix">
                                        <span class="js-tips" style="color: #ff5243;display: none;float: right;margin-right: 50px;margin-bottom: 8px;margin-top: -10px;">请输入正确的价格</span>
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
                            <input type="checkbox" class="ui-checkbox js-nm-chk" />普通订单
                        </div>
                        <ul>
                            <c:forEach items="${normalOrders}" var="order">
                            <li class="clearfix js-item">
                            	<input type="hidden" value="${order.refrenceId}" name="normalOrderId"/>
                                <div class="fl">
                                    <input type="checkbox" class="js-ns-chk" name="normalChk"/>
                                    <span class="c-h">品牌商名称：</span><span>${order.brandName}</span>
                                    <span class="ml20">订单货款:</span><span class="number"><em class="msyh">￥</em><em class="js-all-price">${order.productPrice+order.adjustPrice+order.adjustFreight}</em></span>
                                </div>
                                <div class="fr">
                                    <a class="link" href="${ctx}/dealer/order/view/${order.refrenceId}" target="_blank">查看订单详情</a>
                                </div>
                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                    </c:if>
                    
                    <%--暂时取消掉 --%>
                    <div class="factory-count-box mt10">
                        支付金额：<span class="number"><em class="msyh">￥</em><span id="rest_order_money">0.00</span></span>
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


                    <form:form action="${ctx }/dealer/order/payment/confirm" id="confirm_payment_form">
                        <input type="hidden" value="" name="distriOrderIds" id="distriOrderIds"/>
                        <input type="hidden" value="" name="distriOrderTypes" id="distriOrderTypes"/>
                        <input type="hidden" value="" name="distriOrderAmounts" id="distriOrderAmounts"/>
                        <input type="hidden" value="" name="normalOrderIds" id="normalOrderIds"/>
                    </form:form>


                    <div class="factory-agree">
                        <button class="ui-button ui-button-lred mt20" onclick="payment.confirmPayment()">确定付款</button>
                        <c:if test="${fn:length(distriOrders)!=0}">
                        	 <div class="mt10"><input type="checkbox" class="ui-checkbox" id="protocol_doc"/> 我同意8637交易平台关于<a href="${ctx}/common/rules/info/E6D7B852340D4891BF878089F52A1055" target="_blank">协议付款保证金协议</a></div>
                        </c:if>
                    </div>
                </div>
                <!--确认付款开始-->
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="${ctx}/WEB-INF/view/dealer/import_javascript.jsp" />
	<script>
		var payment={};
		/**确认付款事件*/
		payment.confirmPayment=function(){
			var distriOrderIds=[];
			var distriOrderTypes=[];
			var distriOrderAmounts=[];
			var bAmountChk=false;
			$('input[name=distriChk]:checked').each(function(i,o){
				distriOrderIds.push($(this).parent().parent().find('input[name=distriOrderId]').val());
				distriOrderTypes.push($(this).parent().parent().find('.js-check-item:checked').val());
				var _amount=$(this).parent().parent().find('input[name=distriOrderAmount]').val();
                if($(this).parent().parent().find('.js-check-item:checked').val() == 2){
                    if(_amount==null||_amount==''||_amount==0){
                        remind("error","你有订单未输入货款");
                        $(this).parent().parent().find('input[name=distriOrderAmount]').focus();
                        bAmountChk=true;
                        return;
                    }
                }

				distriOrderAmounts.push(_amount);
			});
			if(bAmountChk){
				return;
			}
			
			$('#distriOrderIds').val(distriOrderIds.join(","));
			$('#distriOrderTypes').val(distriOrderTypes.join(","));
			$('#distriOrderAmounts').val(distriOrderAmounts.join(","));
			var normalOrderIds=[];
			$('input[name=normalChk]:checked').each(function(i,o){
				normalOrderIds.push($(this).parent().parent().find('input[name="normalOrderId"]').val());
			});
			$('#normalOrderIds').val(normalOrderIds.join(","));
			
			//有协议付款 且协议未选中
			if(jQuery.inArray("2", $.unique(distriOrderTypes))!=-1&&$('#protocol_doc:checked').size()==0){
				remind("error","您尚未同意8637交易平台关于协议付款保证金协议，无法支付!");
				return;
			}
			if(distriOrderIds.length==0&&normalOrderIds.length==0){
				remind("error","至少选择一个订单!");
				return;
			}
			
			
			$('#confirm_payment_form')[0].submit();
		};
		
		payment.showPriceDiv=function(orderDivId){
			$('#'+orderDivId).show();
		};
		payment.hidePriceDiv=function(orderDivId){
			$('#'+orderDivId).hide();
		};

        //处理价格变化的方法
        function checkOrder(){
            var count = 0;
            var chkLen = $(".js-fs-chk:checked").length;
            var chkXs = $(".js-check-item:checked").val();

            //设置 我同意8637交易平台关于协议付款保证金协议 前面 checkbox 的值
            if(chkXs == 1){
                $("#protocol_doc").prop("checked",false);
            }
            if(chkLen <= 0){
                $("#protocol_doc").prop("checked",false);
            }

            $(".js-item").each(function(i){
                var price = 0;
                var tips = $(this).find(".js-tips");
                //工厂店订单计算价格
                if($(this).find(".js-fs-chk").prop("checked")){
                    var checkItem = $(this).find(".js-check-item:checked").val();
                    if (checkItem == 1) {
                        price = parseFloat($(this).find(".js-all-price").text());
                        tips.hide();
                    }else if (checkItem == 2) {
                        var num = $(this).find(".js-part-price").val();

                        if(isNaN(num)){
                            num = 0;
                            tips.show();
                        }else if(num == ""){
                            num = 0;
                            tips.show();
                        }else{
                            tips.hide();
                        }

                        price = parseFloat(num);

                        if(price == 0){
                            tips.show();
                        }else{
                            tips.hide();
                        }

                        $("#protocol_doc").prop("checked",true);
                    }
                }else{
                    tips.hide();
                }

                //普通订单计算价格
                if($(this).find(".js-ns-chk").prop("checked")){
                    //设置 我同意8637交易平台关于协议付款保证金协议 前面 checkbox 的值
                    price = parseFloat($(this).find(".js-all-price").text());
                }

                count += price;
            });
            //把计算的结果给 “支付金额”
            $("#rest_order_money").html(count.toFixed(2));
        }
        //工厂店订单全选
        checkAll(".js-fm-chk", ".js-fs-chk",checkOrder);
        //普通订单全选
        checkAll(".js-nm-chk", ".js-ns-chk",checkOrder);

        $(".js-check-item").click(function(){
            checkOrder();
        });

        $(".js-part-price").on("change keyup",function(){
            checkOrder();
        });

        //加入提示框
        seajs.use(["tip"],function(Tip){
            new Tip({
                trigger: '.js-more',
                content: '此货款直接支付至品牌商账户',
                arrowPosition: 11
            });
        });
	</script>
</body>
</html>