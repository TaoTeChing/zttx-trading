<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-我的报表-工厂店库存报表</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/factory.css"/>
    <style>.factory-seeking-form .ui_button_myourself {
        padding: 3px 10px;
    }</style>
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
                <span>我的报表</span>
                <span class="arrow">&gt;</span>
                <span class="current">工厂店库存报表</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp"/>
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方 -->
            <form:form id="search-form" name="search-form" class="ui-form factory-seeking-form clearfix">
                <div class="ui-form-item">
                    <label class="ui-label">品牌： </label>

                    <div class="inline-block pr">
                        <select class="ui-select js_select list-select-width" name="brandId" id="brandId">
                            <option value="">全部品牌</option>
                            <c:if test="${brandesList!=null}">
                                <c:forEach items="${brandesList}" var="brandes">
                                    <option value="${brandes.refrenceId}">${brandes.brandsName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div class="ui-form-item">
                    <label class="ui-label">产品名称：</label>
                    <input class="ui-input" type="text" id="productName" name="productName" style="width: 210px;"
                           value=""/>
                </div>
                <div class="ui-form-item">
                    <label class="ui-label">产品货号：</label>
                    <input class="ui-input" type="text" id="productNo" name="productNo" style="width: 210px;" value=""/>
                </div>
                <div class="ui-form-item">
                    <label class="ui-label">终端商名称：</label>
                    <input class="ui-input" type="text" id="dealerName" name="dealerName" style="width: 210px;"
                           value=""/>
                </div>
                <div class="ui-form-item" style="width: 200px; line-height: 30px;">
                    <label><input id="isSku" name="isSku" type="checkbox"/>按SKU查看</label>
                </div>
                <div class="ta-r fr mt10">
                    <input type="button" value="搜索" class="ui_button_myourself event-query" onclick="page.goTo(1);">
                    <input type="button" value="导出EXCEL表格" class="ui_button_myourself js_export">
                    <input type="button" value="导出EXCEL表格（含SKU）" class="ui_button_myourself js_export_sku"
                           style="margin-right: 35px;">
                </div>
            </form:form>
            <table class="factory-count-table mt10" id="list-table">

            </table>
            <div id="pagination" class="pagination"></div>
        </div>
    </div>
</div>
<%-- 按产品查看 --%>
<script id="product_tempalte" type="text/html">
    <colgroup>
        <col width="200"/>
        <col width="200"/>
        <col width="210"/>
        <col width="210"/>
        <col width="187"/>
    </colgroup>
    <thead>
    <tr>
        <th>货号</th>
        <th>产品名称</th>
        <th>品牌</th>
        <th>所属终端商</th>
        <th>库存量</th>
    </tr>
    </thead>
    <tbody>
    {{each rows}}
    <tr>
        <td>{{$value.productNo}}</td>
        <td>{{$value.productName}}</td>
        <td>{{$value.brandName}}</td>
        <td>{{$value.dealerName}}</td>
        <td>{{$value.realStorage}}</td>
    </tr>
    {{/each}}
    {{ if rows == null || rows.length == 0 }}
    <tr>
        <td colspan="5">暂无数据</td>
    </tr>
    {{ /if }}
    </tbody>
</script>
<%-- 按sku查看 --%>
<script id="sku_tempalte" type="text/html">
    <colgroup>
        <col width="170"/>
        <col width="167"/>
        <col width="167"/>
        <col width="167"/>
        <col width="167"/>
        <col width="167"/>
    </colgroup>
    <thead>
    <tr>
        <th>货号</th>
        <th>产品名称</th>
        <th>品牌</th>
        <th>颜色/尺码</th>
        <th>所属终端商</th>
        <th>库存量</th>
    </tr>
    </thead>
    <tbody>
    {{each rows}}
    <tr>
        <td>{{$value.productNo}}</td>
        <td>{{$value.productName}}</td>
        <td>{{$value.brandName}}</td>
        <td>{{$value.colorSize}}</td>
        <td>{{$value.dealerName}}</td>
        <td>{{$value.realStorage}}</td>
    </tr>
    {{/each}}
    {{ if rows == null || rows.length == 0 }}
    <tr>
        <td colspan="6">暂无数据</td>
    </tr>
    {{ /if }}
    </tbody>
</script>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp"/>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script>
    seajs.use(["pagination", "moment", "template", "dialog"], function (Pagination, moment, template, Dialog) {
        template.helper('$formatDate', function (millsec) {
            return moment(millsec).format("YYYY-MM-DD");
        });
        template.helper('formatNumber', function (price) {
            if (isNaN(price)) {
                return price;
            } else {
                return parseFloat(price).toFixed(2);
            }
        });
        $(".js_export").click(function () {
            var url = "${ctx}/brand/product/report/quantityExcel?_isSku=0&" + $("#search-form").serialize();
            window.open(url);
        });
        $(".js_export_sku").click(function () {
            var url = "${ctx}/brand/product/report/quantityExcel?_isSku=1&" + $("#search-form").serialize();
            window.open(url);
        });
        var renderPagination = function () {
            page = new Pagination({
                url: "${ctx}/brand/product/report/data",
                elem: "#pagination",
                form: $("#search-form"),
                pageSize: 20,
                method: "post",
                handleData: function (json) {
                    var tempalteId = $('#isSku:checked').length > 0
                            ? "sku_tempalte" : "product_tempalte";
                    var html = template.render(tempalteId, json);
                    $('#list-table').empty().append(html);
                }
            });
        };
        renderPagination();
    });
</script>
</body>
</html>