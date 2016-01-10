<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-产品管理-返点产品调价明细</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/factory.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="/common/menuInfo/mainmenu"/>
<div class="main layout">
    <jsp:include page="/common/menuInfo/sidemenu"/>
    <div class="main_con">
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <span>终端商财务账</span>
                <span class="arrow">&gt;</span>
                <span class="current">返点明细</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp"/>
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方 -->
            <form:form id="favorite" name="" class="ui-form" onsubmit="return false;" style="padding-top:20px;">
                <div class="ui-form-item inline-block">
                    <label class="ui-label">
                        品牌：
                    </label>
                    <input type="text" class="ui-input" name="brandsName"/>
                </div>
                <div class="ui-form-item inline-block">
                    <label class="ui-label">
                        产品名称：
                    </label>
                    <input type="text" class="ui-input" name="productTitle"/>
                </div>
                <div class="ui-form-item inline-block">
                    <label class="ui-label">
                        产品货号：
                    </label>
                    <input type="text" class="ui-input" name="productNo"/>
                </div>
                <div class="ui-form-item inline-block" style="padding-left: 20px;">
                    <input type="button" class="ui_button ui_button_morange event-query" style="vertical-align: -1px" value='搜 索'/>
                </div>
            </form:form>
            <div style="padding-left: 40px;">
                <table class="factory-count-table">
                    <colgroup>
                        <col width="145"/>
                        <col width="101"/>
                        <col width="101"/>
                        <col width="111"/>
                        <col width="111"/>
                        <col width="111"/>
                        <col width="111"/>
                        <col width="111"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <th>时间</th>
                        <th>品牌</th>
                        <th>产品名称</th>
                        <th>货号</th>
                        <th>颜色/尺码</th>

                        <th>成本价</th>
                        <th>库存量</th>
                        <th>成本合计</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <!-- 分页 -->
                <div id="pagination"></div>
                <div class="factory-count-box clearfix" style="font-size: 12px;"></div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${ctx}/WEB-INF/view/dealer/agency_footer.jsp"/>
<jsp:include page="${ctx}/WEB-INF/view/dealer/import_javascript.jsp"/>

<script id="data-template" type="text/html">
    {{if null||length==0}}
    <tr>
        <td colspan="8">暂无数据</td>
    </tr>
    {{else}}
    {{each}}
    <tr>
        <td>{{$formatDate $value.createTime}}</td>
        <td>{{$value.brandsName}}</td>
        <td>{{$value.productTitle}}</td>
        <td>{{$value.productNo}}</td>
        <td>{{$value.productSkuName}}</td>
        <td>{{$value.costPrice}}</td>
        <td>{{$value.baseStock}}</td>
        <td>{{$value.sumCost}}</td>
    </tr>
    {{/each}}
    {{/if}}


</script>

<script id="countTpl" type="text/html">

    <div class="fr">
        合计金额：<span class="consult" style="color: #f00;">{{if count!=null}}{{ $formatPrice allSumCost}}{{else}}0.00{{/if}}</span>&nbsp;
        共 <span class="consult" style="color: #f00;">{{if count == null }}0{{else}}{{count}}{{/if}}</span> 条
    </div>
</script>

<script>
    $(function () {
        seajs.use(["pagination", "template", "moment"], function (Pagination, template, moment) {
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
            template.helper('$getLocalTime', function (millsec) {
                return moment(millsec).format("YYYY-MM-DD HH:mm:00");
            });

            window['g_pagination'] = new Pagination({
                url: "${ctx}/dealer/dealerFinancial/creditToPoint/data",
                elem: "#pagination",
                form: "#favorite",
                data: {brandId: "${brandId}"},
                method: "post",
                pageSize: 20,
                handleData: function (json) {
                    console.log(json);
                    var html1 = template.render("data-template", json.rows);
                    var html2 = template.render("countTpl", json.object);
                    $(".factory-count-table tbody").html(html1);
                    $(".factory-count-box").html(html2);
                }
            });
        });
        $(".event-query").click(function () {
            g_pagination.goTo(1);
        });
    });

</script>


</body>
</html>