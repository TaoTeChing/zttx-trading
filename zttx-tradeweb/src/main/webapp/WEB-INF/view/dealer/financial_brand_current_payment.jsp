<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-当期总应付款明细</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/factory.css" rel="stylesheet" />
    <style>
        .ui-form-item{display: inline-block;*display: inline;*zoom: 1;padding-left: 100px;}
        .ui-label{width: 100px;margin-left: -100px;}
    </style>
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
                        <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="${ctx}/dealer/order/financial/factory">品牌商账务</a> >> <span class="bb">当期总应付款明细</span>
                        <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <div class="main-grid mb10">
                    <form:form id="favorite" name="pageForm" class="ui-form factory-seeking-form clearfix">
                        <input type="hidden" name="brandId" value="${brandInfo.refrenceId}">
                        <div class="ui-form-item">
                            <label for="" class="ui-label">
                                品牌商名称：
                            </label>
                            <input class="ui-input" type="text" disabled value="${brandInfo.brandName }" style="width: 190px;" />
                        </div>
                        <div class="ui-form-item">
                            <label for="" class="ui-label">
                                联系人姓名：
                            </label>
                            <input class="ui-input" type="text" disabled value="${brandInfo.connectUserName }" style="width: 190px;" />
                        </div>
                        <div class="ui-form-item">
                            <label for="" class="ui-label">
                                联系方式：
                            </label>
                            <input class="ui-input" type="text" disabled value="${brandInfo.userMobile }" style="width: 190px;" />
                        </div>
                        <div class="ui-form-item">
                            <label for="" class="ui-label">
                                所在地：
                            </label>
                            <input class="ui-input" type="text" disabled value="${fns:getFullAreaNameByNo(brandInfo.areaNo)}" style="width: 190px;" />
                        </div>
                        <div class="ui-form-item">
                            <label for="" class="ui-label">
                                日期：
                            </label>
                            <input type="text" name="startTime"  class="ui-input date" id="start-cal" readonly style="width: 74px;"/>
                            	 至 
                            <input type="text" name="endTime" class="ui-input date" id="end-cal" readonly style="width: 74px;"/>
                        </div>
                        <div class="ui-form-item" style="margin-left: -69px;">
                            <div class="inline-block">
                                <input type="button" value="搜索" class="ui-button ui-button-mwhite event-query">
                            </div>
                        </div>
                    </form:form>
                </div>
                <table class="factory-count-table ui-table">
                    <colgroup>
                        <col width="150"/>
                        <col width="150"/>
                        <col width="150"/>
                        <col width="150"/>
                        <col width="150"/>
                        <col width="150"/>
                        <col width="155"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <th>时间</th>
                        <th>总销量</th>
                        <th>总销售额（元）</th>
                        <th>应付金额（元）</th>
                        <th>已付金额（元）</th>
                        <th>结算状态状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <div id="pagination"></div>
                <div class="factory-count-box">
                    合计 当期总应付款：<em class="fn-rmb">￥</em><span class="consult-r" id="needPayAmount">0.00</span>&nbsp;|&nbsp;
                    已付款：<em class="fn-rmb">￥</em><span class="consult" id="hadPayedAmount">0.00</span>&nbsp;|&nbsp;
                    欠付款：<em class="fn-rmb">￥</em><span class="consult-r" id="goonPayAmount">0.00</span>
                </div>
                <div class="ta-r mt10">
                    <button type="submit" class="ui-button ui-button-mred js-pay-all" id="goonPayAmountBtn" data-price="123.00" data-id="${param.brandId}">支付当期欠付款</button>
                  <%--  <a href="${ctx}/dealer/order/financial/factory/settle?brandId=${param.brandId}" class="ui-button ui-button-mred ml10" id="settleBtn" data-price="123.00">成本结算</a>--%>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<script id="payTpl" type="text/html">
    <div class="ui-head">
        <h3>付款</h3>
    </div>
    <div class="ta-c mt10">
        总欠付款：<strong style="color: #c00;">{{$formatPrice price}}</strong> 元
    </div>
    <div class="ta-c mt10">
        支付密码：<input type="password" id="payWord" value="" autocomplete="off">
        <input type="hidden" id="brandId" value={{brandid}}>
    </div>
    <div class="ta-c mt10">
        <a class="ui-button ui-button-morange" id="pay_sure_btn" href="javascript:payCurrent();" data-price="{{price}}">立即支付</a>
        <a class="ui-button ui-button-mwhite ml5" id="pay_cancel_btn" href="javascript:;">取消</a>
    </div>
</script>

<script id="feedback-templage" type="text/html">
	{{each rows}}
   	<tr>
     <td>{{$formatDate $value.clearingTime}}</td>
     <td>{{$value.clearingVolume}}</td>
     <td>{{$formatPrice $value.salesAmount}}</td>
	 <td>{{$formatPrice $value.clearingAmount}}</td>
	 <td>{{$formatPrice $value.hasClearingAmount}}</td>
     <td>
		{{if $value.clearingStatus==0}}未结算{{/if}}
		{{if $value.clearingStatus==1}}已结算{{/if}}
	 </td>
     <td>
       <a class="link" href="${ctx }/dealer/dealerFinancial/saleDetail?brandId=${param.brandId}&date={{$formatDate $value.clearingTime}}">查看销售明细</a>
     </td>
    </tr>
	{{/each}}
        {{if !rows || rows.length == 0 }}
    <tr>
        <td class="ta-c" colspan="10">暂无数据</td>
    </tr>
    {{/if }}
</script>

<jsp:include page="${ctx}/WEB-INF/view/dealer/import_javascript.jsp" />
<script>
    $(function () {
        seajs.use(["dialog", "template"],function(Dialog ,Template){
            var payDialog = new Dialog({
                //trigger:".js-pay",
                //content:$("#payTpl").html(),
                width:300
            });

            window.payCurrent = function (){
                var payWord = $.trim($("#payWord").val());
                var brandId = $("#brandId").val();
                if(payWord !== ""){
                    $.ajax({
                        url:"${ctx}/dealer/dealerFinancial/payment",
                        type:"post",
                        data:{brandIds:brandId,payWord:payWord},
                        dataType:"json",
                        success: function(data){
                            if(data.code == 126000){
                                remind("success", "支付成功！");
                                payDialog.hide();
                            }else{
                                remind("error", data.message);
                            }
                        }
                    });
                }else{
                    remind("error", "请输入正确的支付密码");
                }
            }

            $(".main-right").on("click",".js-pay-all",function(){
                //var price = $(this).data("price");
                var price = $("#goonPayAmount").text();
                var _id = $(this).data("id");
                var _html = Template.render("payTpl",{price: price, brandid: _id});
                payDialog.set("content", _html);
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
        });
    });
    
    function formatNumber(amount)
    {
    	if(typeof(amount) != 'number'){
    		return 0.00;
    	}
    	return amount.toFixed(2);
    }
    
    $(function () {
        seajs.use(["pagination","template","moment"], function (Pagination,template,moment) {
            template.helper('$formatPrice', function (price) {
                if($.type(price) == "number"){
                    return price.toFixed(2);
                }else{
                    return price;
                }
            });
            template.helper('$formatDate', function (millsec) {
		            return moment(millsec).format("YYYY-MM-DD");
		    });
            window['g_Pagination'] = new Pagination({
                url: "${ctx}/dealer/dealerFinancial/currentFinancial/detail/data",
                elem: "#pagination",
                form:$("#favorite"),
                method:"post",
                handleData: function (json) {
                    //console.log(json);
                    if(json.code == 126000)
                    {
                        var html1 = template.render("feedback-templage",json);
                        $(".factory-count-table tbody").html(html1);
                        //console.log(json.object.allOwedPrice);
                        $("#needPayAmount").text(json.object.allOwedPrice);
                        $("#hadPayedAmount").text(json.object.allPayPrice);
                        $("#goonPayAmount").text(json.object.allDebtPrice);
                    }else{

                    }


                }
            });
        });
        $(".event-query").click(function(){
        	g_Pagination.goTo(1);
        });
    });

</script>
</body>
</html>