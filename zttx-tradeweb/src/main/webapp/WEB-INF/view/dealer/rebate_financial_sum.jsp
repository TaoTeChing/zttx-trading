<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-产品管理-返点产品调价明细</title>
    <link rel="stylesheet" href="${res}/styles/dealer/global.css"/>
    <link rel="stylesheet" href="${res}/styles/dealer/factory.css"/>
</head>
<body>
<div class="container">
    <jsp:include page="/common/menuInfo/mainmenu"/>
    <div class="em100">
        <div class="main clearfix">
            <jsp:include page="/common/menuInfo/sidemenu"/>
            <div class="main-right">
                <!--面包屑-->
                <div class="main-grid mb10">
                    <div class="panel-crumbs">
                        <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <span class="bb">返点产品调价明细</span>
                        <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <div class="inner">
                    <!-- 内容都放这个地方 -->
                    <div class="main-grid mb10 pl20" style="padding-top: 10px;">
                        <form:form id="financial_brand_normal_form" name="" class="ui-form" onsubmit="return false;">
                            <div>
                                <label>
                                    品牌商名称：
                                </label>
                                <input type="text" class="ui-input" name="brandName"/>
                                <input type="button" class="ui-button ui-button-mwhite event-query"
                                       style="vertical-align: -1px" value='搜 索'/>
                            </div>
                        </form:form>
                    </div>
                    <div>
                        <table class="factory-count-table ui-table">
                            <colgroup>
                                <col width="25"/>
                                <col width="165"/>
                                <col width="115"/>
                                <col width="125"/>
                                <col width="125"/>
                                <col width="125"/>
                                <col width="125"/>
                                <col width="125"/>
                                <col width="100"/>
                            </colgroup>
                            <thead>
                            <tr>
                                <th><input type="checkbox" class="ui-checkbox js-checkall"></th>
                                <th>品牌商名称</th>
                                <th>总销量</th>
                                <th>总销售额</th>
                                <th>总返点金额</th>
                                <th>总返点应付款</th>
                                <th>总返点已付款</th>
                                <th>返点欠付款</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                        <div id="pagination"></div>
                        <div class="factory-count-box clearfix" style="font-size: 12px;"></div>
                        <div class="p10 ta-r">
                            <button type="button" class="ui-button ui-button-mred js-pay-all">支付当期欠付款</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>

<jsp:include page="${ctx}/WEB-INF/view/dealer/import_javascript.jsp"/>
<script id="data-template" type="text/html">
    {{if null||length==0}}
    <tr>
        <td colspan="9">暂无数据！</td>
    </tr>
    {{else}}
    {{each}}
    <tr>
        <th><input type="checkbox" class="ui-checkbox js-subchk" data-brandid="{{$value.brandId}}"
                   data-amount="{{$value.allDebtPriceSum}}"></th>
        <td>{{$value.brandName}}</td>
        <td>{{ $value.allSaleNumSum}}</td>
        <td>{{ $value.allSalePriceSum}}</td>
        <td>{{ $value.allPointPriceSum}}</td>
        <td>{{ $value.allCostPriceSum}}</td>
        <td>{{ $value.allPaidPriceSum}}</td>
        <td>{{ $value.allDebtPriceSum}}</td>
        <td>
            <a class="link js-pay" href="javascript:;" data-brandid="{{$value.brandId}}"
               data-amount="{{$value.allDebtPriceSum}}">付款</a><br/>
            <a href="${crx}/dealer/dealerFinancialPoint/saleSum?brandId={{$value.brandId}}" class="link">查看明细</a><br/>
            <a href="${crx}/dealer/dealerFinancialPoint/stockCountByDate?brandId={{$value.brandId}}"
               class="link">库存明细</a>
        </td>
    </tr>
    {{/each}}
    {{/if}}
</script>

<script id="count-template" type="text/html">
    <div class="fr">
        合计&nbsp;应收款:
        <span class="consult" style="color: #f00;">
        {{if count!=0}}{{ $formatPrice sumCostPriceSum}}{{else}}0.00{{/if}}
        </span>&nbsp; 已收款： <span class="consult" style="color: #f00;">
        {{if count!=0}}{{ $formatPrice sumPaidPriceSum}}{{else}}0.00{{/if}}
        </span>&nbsp; 欠收款：<span class="consult" style="color: #f00;">
        {{if count!=0}}{{ $formatPrice sumDebtPriceSum}}{{else}}0.00{{/if}}
        </span>&nbsp;
        共 <span class="consult" style="color: #f00;">{{count}}</span> 条
    </div>
</script>
<script id="payTpl" type="text/html">
    <div class="ui-head">
        <h3>付款</h3>
    </div>
    <div class="ta-c mt10">
        {{context}}：<strong style="color: #c00;">{{amount}}</strong> 元
        {{if paying>0}}
        {{paycontext}}:<strong style="color: #c00;">{{paying}}</strong> 元<br>
        本次支付：<strong style="color: #c00;">{{amount-paying }}</strong>元
        {{/if}}
        <br>
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
    $(function () {
        seajs.use(["pagination", "template", "moment", "dialog"], function (Pagination, template, moment, Dialog) {

            checkAll(".js-checkall", ".js-subchk");

            template.helper('$formatPrice', function (price) {
                if (isNaN(price)) {
                    return price;
                } else {
                    return price.toFixed(2);
                }
            });
            template.helper('$formatDate', function (millsec) {
                return moment(millsec).format("YYYY-MM-DD HH:mm:00");
            });
            window['g_pagination'] = new Pagination({
                url: "${ctx}/dealer/dealerFinancialPoint/priceSum/data",
                elem: "#pagination",
                form: "#financial_brand_normal_form",
                method: "post",
                handleData: function (json) {
                    if (json.code == 126000) {
                        var html1 = template.render("data-template", json.rows);
                        var html2 = template.render("count-template", json.object);
                        $(".factory-count-table tbody").html(html1);
                        $(".factory-count-box").html(html2);
                    } else {
                        remind("error", json.message);
                    }
                }
            });

            var payDialog = new Dialog({
                width: 300
            });

            //立即支付
            window.payCurrent = function () {
                var payWord = $.trim($("#payWord").val());
                var brandId = $("#brandId").val();
                if (payWord !== "") {
                    $.ajax({
                        url: "/dealer/dealerFinancialPoint/payment",
                        type: "post",
                        data: {brandIds: brandId, payWord: payWord},
                        dataType: "json",
                        success: function (data) {
                            if (data.code == 126000) {
                                remind("success", "支付成功！");
                                payDialog.hide();
                            } else {
                                remind("error", data.message);
                            }
                        }
                    });
                } else {
                    remind("error", "请输入正确的支付密码");
                }
            }

            //单个支付
            $(".factory-count-table").on("click", ".js-pay", function () {

                var brandid = $(this).data("brandid"), amount = $(this).data("amount"), paying = 0;
                if (amount == 0) {
                    remind("error", "尚无当期应付款，暂无需支付！");
                    return;
                }
                //获取真在支付中的款额
                $.post("${ctx}/dealer/dealerFinancialPoint/payIng", {brandIds: brandid}, function (data) {

                    if (data.code == 126000) {
                        paying = data.object.payIngSum;
                        var html = template.render('payTpl', {
                            context: "当期欠付款",
                            amount: amount,
                            brandid: brandid,
                            paycontext: "结算中",
                            paying: paying
                        });
                        payDialog.set("content", html);
                        payDialog.show();
                    } else {
                        remind("error", data.message);
                    }
                }, "json")

                //批量支付
                $(document).on("click", ".js-pay-all", function () {
                    var brandids = "", amounts = 0;

                    if ($(".js-subchk:checked").length <= 0) {
                        remind("error", "请选择需要支付的品牌商");
                        return;
                    }

                    $(".js-subchk:checked").each(function () {
                        var brandid = $(this).data("brandid"), amount = parseFloat($(this).data("amount"));
                        if (brandids == "") {
                            brandids += brandid;
                        } else {
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
                $(".event-query").click(function () {
                    g_pagination.goTo(1);
                });
            });
        });
    });

</script>
</body>
</html>