<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-品牌财务账</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/factory.css" rel="stylesheet" />
</head>
<body>
<div class="container">
<c:if test="${param.isClient==null||param.isClient==false}">
    <jsp:include page="${ctx}/dealer/mainmenu"/>
    <div class="em100">
        <div class="main clearfix pr">
            <!--侧边导航-->
            <jsp:include page="${ctx}/dealer/dealermenu"/>
            <!--主体内容-->
            <div class="main-right">
                <!--面包屑-->
                <div class="main-grid mb10">
                    <div class="panel-crumbs">
                        <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <span class="bb">品牌财务账</span>
                        <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                </c:if>
                <div class="inner">
                    <div class="main-grid mb10 pl20" style="padding-top: 10px;">
                        <form:form id="financial_brand_factory_form" name="pageForm" class="ui-form" onsubmit="return false;">
                            <input type="hidden" name="isFactoryStore" value="true"/>
                            <div>
                                <label>品牌商名称：</label>
                                <input class="ui-input" type="text" name="brandName"/>
                                <input type="button" class="ui-button ui-button-mwhite event-search" style="vertical-align: -1px" value='搜 索' />
                            </div>
                        </form:form>
                    </div>
                    <div class="panel-tabs">
                        <div class="panel-head">
                            <ul>
                                 <li class="js-order-status"><a  href="${ctx}/dealer/order/financial${param.isClient?'?isClient=true':''}">普通财务账</a></li>
                                <li class="js-order-status"><a class="active" href="${ctx}/dealer/order/financial/factory${param.isClient?'?isClient=true':''}">工厂店财务账</a></li>
                            </ul>
                        </div>
                    </div>
                    <table class="factory-count-table ui-table" id="factory_list">
                        <colgroup>
                            <col width="36"/>
                            <col width="96"/>
                            <col width="76"/>
                            <col width="66"/>
                            <col width="66"/>
                            <col width="76"/>
                            <col width="106"/>
                            <col width="76"/>
                            <col width="66"/>
                            <col width="86"/>
                            <col width="86"/>
                            <col width="86"/>
                        </colgroup>
                        <thead>
                        <tr>
                            <th><input type="checkbox" class="ui-checkbox js-checkall"/></th>
                            <th>品牌商名称</th>
                            <th>已缴押金</th>
                            <th>应付款</th>
                            <th>已付款</th>
                            <th>应收退款</th>
                            <th>应收抵欠付款</th>
                           <%-- <th>已收退款</th> --%>
                            <th>欠付款</th>
                            <th>当期应付款</th>
                            <th>当期已付款</th>
                            <th>当期欠付款</th>
                            <th>操作</th>

                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                    <div id="pagination"></div>
                    <div class="factory-count-box">
                        合计 已缴押金：<em class="fn-rmb">￥</em><span class="consult">0.00</span>&nbsp;|&nbsp;
                        应付货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span>&nbsp;|&nbsp;
                        已付货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span>&nbsp;|&nbsp;
                        欠付货款：<em class="fn-rmb">￥</em><span class="consult-r">0.00</span>
                        当期总欠付款：<em class="fn-rmb">￥</em><span class="consult-r">0.00</span>
                    </div>
                    <div class="ta-r mt10">
                    <c:if test="${param.isClient!=true}">
                    <button type="submit" class="ui-button ui-button-mred js-pay-all">支付当期欠付款</button>
                     </c:if></div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="${ctx}/WEB-INF/view/dealer/import_javascript.jsp" />
<script id="financial_brand_factory_table_template" type="text/html">
    {{each rows}}
    <tr>
        <td>
            {{if $value.needPayAmount < 0 }}
                <input type="checkbox" class="ui-checkbox" disabled />
            {{else}}
                <input type="checkbox" class="ui-checkbox js-sub" data-brandid="{{$value.brandId}}" data-amount="{{$formatPrice($value.needPayAmount-$value.hadPayedAmount)}}" />
            {{/if}}
        </td>
        <td>{{$value.comName}}</td>
        <td>{{$formatPrice $value.amountAry[8]}}</td>
        <td>{{$formatPrice $value.amountAry[0]}}</td>
        <td>{{$formatPrice $value.amountAry[1]}}</td>
        <td>{{$formatPrice $value.amountAry[2]}}</td>
        <td>{{$formatPrice $value.amountAry[6]}}</td>
		<%--
        <td>{{$value.amountAry[3]}}</td>
        --%>
		<td>{{$formatPrice $value.amountAry[4]}}</td>
		<c:if test="${param.isClient==null||param.isClient==false}">
			<td><a class="link" href="${ctx}/dealer/order/financial/factory/payment?brandId={{$value.brandId}}" >{{$formatPrice $value.needPayAmount}}</a></td>
		</c:if>
	   <c:if test="${param.isClient}">
		<td>{{$formatPrice $value.needPayAmount}}</td>
 	   </c:if> 

		 <td>{{$formatPrice $value.hadPayedAmount}}</td>
        <td>{{$formatPrice($value.needPayAmount-$value.hadPayedAmount)}}</td>
<c:if test="${param.isClient==null||param.isClient==false}">
        <td>
            {{if $value.needPayAmount >= 0 }}
                <p><a class="link js-pay" href="javascript:;" data-brandid="{{$value.brandId}}" data-amount="{{$formatPrice($value.needPayAmount-$value.hadPayedAmount)}}" >付款</a></p>
            {{/if}}
			<p><a class="link" href="${ctx}/dealer/order/financial/details/factory?brandId={{$value.brandId}}">查看明细</a></p>
 			<p><a class="link" href="${ctx}/dealer/order/financial/factory/refund?brandId={{$value.brandId}}">退款退货</a></p>
		</td>
</c:if>
		<c:if test="${param.isClient}">
		<td>-</td>
 	   </c:if> 
    </tr>
    {{/each}}
    {{if !rows || rows.length == 0 }}
    <tr>
        <td class="ta-c" colspan="13">暂无数据</td>
    </tr>
    {{/if }}
</script>
<script id="financial_brand_factory_total_template" type="text/html">
    {{if !object || object.length == 0 }}
       合计 已缴押金：<em class="fn-rmb">￥</em><span class="consult">0.00</span>&nbsp;|&nbsp;
            应付货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span>&nbsp;|&nbsp;
            已付货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span>&nbsp;|&nbsp;
            欠付货款：<em class="fn-rmb">￥</em><span class="consult-r">0.00</span>
            当期总欠付款：<em class="fn-rmb">￥</em><span class="consult-r">0.00</span>
    {{else}}
    合计 已缴押金：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.amountTotalAry[8]}}</span>&nbsp;|&nbsp;
	应付货款：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.amountTotalAry[0]}}</span>&nbsp;|&nbsp;
        已付货款：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.amountTotalAry[1]}}</span>&nbsp;|&nbsp;
        欠收退款：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.amountTotalAry[5]}}</span>&nbsp;|&nbsp;
        欠付货款：<em class="fn-rmb">￥</em><span class="consult-r">{{$formatPrice object.amountTotalAry[4]}}</span>   
 	{{/if }}
</script>
<script id="payTpl" type="text/html">
    <div class="ui-head">
        <h3>付款</h3>
    </div>
    <div class="ta-c mt10">
        {{context}}：<strong style="color: #c00;">{{amount}}</strong> 元
    </div>
    <div class="ta-c mt10">
        <a class="ui-button ui-button-morange" id="pay_sure_btn" href="${ctx}/dealer/order/financial/factory/payaction?brandIds={{brandid}}">立即支付</a>
        <a class="ui-button ui-button-mwhite ml5" id="pay_cancel_btn" href="javascript:;">取消</a>
    </div>
</script>
<script>
    $(function () {
        //全选
        checkAll(".js-checkall",".js-sub");
        //付款
        seajs.use(["pagination","dialog", "template"],function(Pagination,Dialog, Template){
        	template.helper('$formatPrice', function (price) {
                 return isNaN(price)?'0.00':price.toFixed(2);
            });
             template.helper('$formatDate', function (millsec) {
 		            return moment(millsec).format("YYYY-MM-DD HH:mm:ss");
 		    });
             
            var payDialog = new Dialog({
                //trigger:".js-pay",
                //content:$("#payTpl").html(),
                width:300
            });
            $("#factory_list").on("click",".js-pay",function(){
                var brandid = $(this).data("brandid"), amount = $(this).data("amount");
                if(amount==0){
                	remind("error","尚无当期应付款，暂无需支付！");
                	return;
                }
                
                payDialog.before("show",function(){
                    var html = template.render('payTpl', {context:"当期欠付款",amount:amount,brandid:brandid});
                    this.element.html(html);
                });
                payDialog.show();
            });

            //计算选中品牌 当期总欠付款
            function countPayPrice(){
                var sum = 0, arr = [];
                $(".js-sub:checked").each(function(){
                    var id = $(this).data("brandid"),price = parseFloat($(this).data("amount"));
                    sum = sum + price;
                    arr.push(id);
                });
                return {
                	brandIds:arr.toString(),
                	amount:sum.toFixed(2)
                };
            }
            $(".main-right").on("click",".js-pay-all",function(){
                if($(".js-sub:checked").length <= 0){
                    remind("error","请选择工厂店品牌");
                    return false;
                }
                var countT = countPayPrice(), brandIds = countT.brandIds, amount = countT.amount;
                if(amount==0){
                	remind("error","尚无当期应付款，暂无需支付！");
                	return;
                }
                payDialog.before("show",function(){
                    var html = template.render('payTpl', {context:"当期总欠付款",amount:amount,brandid:brandIds});
                    this.element.html(html);
                });
                payDialog.show();
            });
            $(document).on("click","#pay_sure_btn",function(){
                var id = $(this).data("id");
                var price = $(this).data("price");
                //alert(id + ":" + price);
                //页面跳转window.location.href = ""
            });
            $(document).on("click","#pay_cancel_btn",function(){
                payDialog.hide();
            });
            
            window['g_pagination']=new Pagination({
                url: "${ctx}/dealer/order/financial/data?factoryStore=true",
                elem: "#pagination",
                form:$("#financial_brand_factory_form"),
                method:"post",
                handleData: function (json) {
                    var html1 = template.render("financial_brand_factory_table_template",json);
                    var html2 = template.render("financial_brand_factory_total_template",json);
                    $(".factory-count-table tbody").html(html1);
                    $(".factory-count-box").html(html2);
                }
            });
        });
        
        /**查询按键*/
        $(".event-search").click(function(){
        	g_pagination.goTo(1);
        });
    });
</script>
</body>
</html>