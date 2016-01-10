<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-品牌商财务账</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/factory.css" rel="stylesheet" />
</head>
<body>
<div class="container">
    <c:if test="${param.isClient==null||param.isClient==false}">
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
                        <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <span class="bb">品牌商财务账</span>
                        <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                </c:if>
                <div class="inner">
                    <div class="main-grid mb10 pl20" style="padding-top: 10px;">
                        <form:form id="financial_brand_normal_form" name="pageForm" class="ui-form" onsubmit="return false;">
                            <input type="hidden" name="isFactoryStore" value="false"/>
                            <div>
                                <label>品牌商名称：</label>
                                <input class="ui-input" type="text" name="brandName"/>
                                <input type="button" class="ui-button ui-button-mwhite" style="vertical-align: -1px" value='搜 索' />
                            </div>
                        </form:form>
                    </div>
                    <div class="panel-tabs">
                        <div class="panel-head">
                            <ul class="clearfix">
                                <li class="js-order-status"><a class="active" href="${ctx}/dealer/order/financial${param.isClient?'?isClient=true':''}">财务账</a></li>
                            </ul>
                        </div>
                    </div>
                    <table class="factory-count-table ui-table mt20" id="factory-list">
                        <colgroup>
                            <col width="25"/>
                            <col width="100"/>
                            <col width="125"/>
                            <col width="125"/>
                            <col width="125"/>
                            <col width="60"/>
                            <col width="100"/>
                            <col width="80"/>
                            <col width="75"/>
                            <col width="95"/>
                            <col width="105"/>
                            <col width="85"/>
                        </colgroup>
                        <thead>
                        <tr>
                            <th><input type="checkbox" class="ui-checkbox js-checkall"/></th>
                            <th>品牌商名称</th>
                            <th>品牌名称</th>
                            <th colspan="4">订单/退款/变更 统计</th>
                            <th colspan="3">财务数据</th>
                            <th>当期欠付款</th>
                            <th>操作</th>
                        </tr>
                        <tr>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th>进行中订单金额</th>
                            <th>已完成订单金额</th>
                            <th>退款</th>
                            <th>产品类型变更</th>
                            <th>应付款</th>
                            <th>已付款</th>
                            <th>欠付款</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <!--financial_brand_normal_table_template-->
                        </tbody>
                    </table>
                    <div id="pagination"></div>
                    <div class="factory-count-box mt10">
                        合计 应付货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span>&nbsp;|&nbsp;
                        已付货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span>&nbsp;|&nbsp;
                        欠付货款：<em class="fn-rmb">￥</em><span class="consult-r">0.00</span>
                    </div>
                    <div class="p10 ta-r">
                        <button type="button" class="ui-button ui-button-mred js-pay-all">支付当期欠付款</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="${ctx}/WEB-INF/view/dealer/import_javascript.jsp" />
<script id="financial_brand_normal_table_template" type="text/html">
    {{each rows}}
    <tr>
        <td><input type="checkbox" class="ui-checkbox js-subchk" data-brandid="{{$value.brandId}}" data-amount="{{$value.allDebtPrice}}"/></td>
        <td>{{$value.brandName}}</td>
        <td>
            {{each $value.brandesMap}}
              <ul>{{$value.brandesName}}</ul>
            {{/each}}
        </td>
        <td>{{$formatPrice $value.totalInIngOrderPrice}} </td>
        <td>{{$formatPrice $value.totalFinishOrderPrice}}</td>
        <td>{{$formatPrice $value.totalRefundOrderPrice}}</td>
        <td>{{$value.totalCreditToPointPrice}}</td>
        <td>{{$formatPrice $value.totalProductPrice}}</td>
        <td>{{$formatPrice $value.totalPayPrice}}</td>
        <td>{{$formatPrice $value.totalDebtPrice}}</td>
        <td><a class="link" href="${ctx}/dealer/dealerFinancial/currentFinancial/detail?brandId={{$value.brandId}}">{{$value.allDebtPrice}}</a></td>
        <td>
            <%--{{if $value.allDebtPrice > 0 }}--%>
            <p><a class="link js-pay" href="javascript:;" data-brandid="{{$value.brandId}}" data-amount="{{$value.allDebtPrice}}" >付款</a></p>
          <%--  {{/if}}--%>
            <a class="link" href='${ctx}/dealer/dealerFinancial/financial/detail?brandId={{$value.brandId}}'>查看详情</a><br />
            <a class="link" href="${ctx}/dealer/dealerFinancial/refund?brandId={{$value.brandId}}">退货退款</a><br />
        </td>
    </tr>
    {{/each}}
    {{if !rows || rows.length == 0 }}
    <tr>
        <td class="ta-c" colspan="10">暂无数据</td>
    </tr>
    {{/if }}
</script>
<script id="financial_brand_normal_total_template" type="text/html">
    {{if object && object.sumProductPrice}}
        合计 应付货款：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.sumProductPrice}}</span>&nbsp;|&nbsp;
        已付货款：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.sumPayPrice}}</span>&nbsp;|&nbsp;
        欠付货款：<em class="fn-rmb">￥</em><span class="consult-r">{{$formatPrice object.sumDebtPrice}}</span>
    {{else}}
        合计 应付货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span>&nbsp;|&nbsp;
        已付货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span>&nbsp;|&nbsp;
        欠付货款：<em class="fn-rmb">￥</em><span class="consult-r">0.00</span>
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
        支付密码：<input type="password" id="payWord" value="" autocomplete="off">
        <input type="hidden" id="brandId" value={{brandid}}>
    </div>
    <div class="ta-c mt10">
        <a class="ui-button ui-button-morange" id="pay_sure_btn" href="javascript:payCurrent();">立即支付</a>
        <a class="ui-button ui-button-mwhite ml5" id="pay_cancel_btn" href="javascript:;">取消</a>
    </div>
</script>
<script>
    seajs.use(["pagination","dialog", "template"],function(Pagination,Dialog, Template) {

        checkAll(".js-checkall", ".js-subchk");

        var payDialog = new Dialog({
            width: 300
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

        //单个支付
        $("#factory-list").on("click", ".js-pay", function () {
            var brandid = $(this).data("brandid"), amount = $(this).data("amount");
            if (amount == 0) {
                remind("error", "尚无当期应付款，暂无需支付！");
                return;
            }
            var html = template.render('payTpl', {context: "当期欠付款", amount: amount, brandid: brandid});
            payDialog.set("content", html);
            payDialog.show();
        });
        //批量支付
        $(document).on("click", ".js-pay-all", function(){
            var brandids = "", amounts = 0;
            $(".js-subchk:checked").each(function(){
                var brandid = $(this).data("brandid"), amount = parseFloat($(this).data("amount"));
                if(brandids == ""){
                    brandids += brandid;
                }else{
                    brandids += "," + brandid;
                }
                amounts += amount;
            });
            if (amounts == 0) {
                remind("error", "尚无当期应付款，暂无需支付！");
                return;
            }
            var html = template.render('payTpl', {context: "当期欠付款", amount: amounts, brandid: brandids});
            payDialog.set("content", html);
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

        template.helper('$formatPrice', function (price) {
            return price.toFixed(2);
        });

        window['g_pagination']=new Pagination({
            url: "${ctx}/dealer/dealerFinancial/financial/data",                   /* ${ctx}/dealer/order/financial/data*/
            elem: "#pagination",
            form:$("#financial_brand_normal_form"),
            method:"post",
            handleData: function (json) {
                //console.log(json);
                var html1 = template.render("financial_brand_normal_table_template",json);
                var html2 = template.render("financial_brand_normal_total_template",json);
                $(".factory-count-table tbody").html(html1);
                $(".factory-count-box").html(html2);
            }
        });

        $(".ui-button-mwhite").click(function(){
            g_pagination.goTo(1);
        });
    });
</script>
</body>
</html>