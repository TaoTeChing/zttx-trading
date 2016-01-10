<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <title>管理中心-产品管理-返点产品调价明细</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/productmanag.css"/>
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
                <a class="link" href="${ctx}/brand/product/execute">产品管理</a>
                <span class="arrow">&gt;</span>
                <span class="current">返点产品调价明细</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方 -->
            <div class="selectbar_list clearfix">
                <form:form cssClass="ui-form" action="" method="post" id="search-form">
                    <div class="ui-form-item">
                        <label class="ui-label">
                            选择品牌：
                        </label>
                        <div class="fl">
                            <select class="ui-select js_select list-select-width" name="brandsId" id="111">
                                <option value="">全部品牌</option>
                                <c:if test="${brandesList!=null}">
                                    <c:forEach items="${brandesList}" var="brandes">
                                        <option value="${brandes.refrenceId}" <c:if test="${brandes.refrenceId==infoModel.list_brandsId}"> selected="selected" </c:if> >${brandes.brandsName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            产品货号：
                        </label>
                        <input class="ui-input" type="text" name="productNo" value="" style="width: 235px;">
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            产品名称：
                        </label>
                        <input class="ui-input" type="text" name="productName" value="" style="width: 235px;">
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            颜色：
                        </label>
                        <input class="ui-input" type="text" name="color" value="" style="width: 87px;">
                        <label style="font-size: 14px;">
                            尺码：
                        </label>
                        <input class="ui-input" type="text" name="size" value="" style="width: 88px;">
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            调价时间：
                        </label>
                        <input class="ui-input hasDatepicker" id="startDate1" type="text" name="createTimeStart" value="" style="width: 85px;" readonly>
                        -
                        <input class="ui-input hasDatepicker" id="endDate1" type="text" name="createTimeEnd" value="" style="width: 86px;" readonly>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" style="width: 100px;">
                            调价生效时间：
                        </label>
                        <input class="ui-input hasDatepicker" id="startDate2" type="text" name="effectiveTimeStart" value="" style="width: 70px;"readonly >
                        -
                        <input class="ui-input hasDatepicker" id="endDate2" type="text" name="effectiveTimeEnd" value="" style="width: 70px;" readonly>
                    </div>
                    <div class="ui-form-item" style="float: right;text-align: right;margin-right: 15px;">
                        <input type="button" class="ui_button_myourself" value="搜索"  onclick="page.goTo(1);" >
                    </div>
                </form:form>
            </div>
            <div>
                <table class="rebate-table" id="list-table">
                    <colgroup>
                        <col width="94"/>
                        <col width="92"/>
                        <col width="92"/>
                        <col width="92"/>
                        <col width="91"/>
                        <col width="91"/>
                        <col width="91"/>
                        <col width="91"/>
                        <col width="91"/>
                        <col width="91"/>
                        <col width="91"/>
                    </colgroup>
                    <thead>
                        <tr>
                            <th>货号</th>
                            <th>产品名称</th>
                            <th>品牌</th>
                            <th>颜色</th>
                            <th>尺码</th>
                            <th>原返点价</th>
                            <th>原返点比例</th>
                            <th>现返点价</th>
                            <th>现返点比例</th>
                            <th>调价时间</th>
                            <th>调价生效时间</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                    </tbody>
                </table>
                <div id="pagination" class="pagination"></div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp"/>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script src="${res}/scripts/src/brand/productmanage.js"></script>
<script id="line-template" type="text/html">
    {{each rows}}
    <tr>
        <td>{{$value.productNo}}</td>
        <td>{{$value.productTitle}}</td>
        <td>{{$value.brandsName}}</td>
        <td>{{$value.color}}</td>
        <td>{{$value.size}}</td>
        <td>{{if $value.beforePrice!=0}}{{$value.beforePrice}}{{/if}}</td>
        <td>{{if $value.beforePercent!=0}}{{$value.beforePercent}}{{/if}}</td>
        <td>{{$value.afterPrice}}</td>
        <td>{{$value.afterPercent}}</td>
        <td>{{$formatDate $value.createTime}}</td>
        <td>{{if $value.effTime==null}}{{else}}{{$formatDate $value.effTime}}{{/if}}</td>
    </tr>
    {{ /each }}
    {{ if rows.length == 0 }}
    <tr>
        <td colspan="7">暂无数据</td>
    </tr>
    {{ /if }}
</script>
<script>
    rebateDetail.init();
    var page;
    $(function () {
    	seajs.use(["pagination", "moment", "template", "dialog"], function (Pagination, moment, template, Dialog) {
    		template.helper('formatNumber',function(price){
                if(isNaN(price)){
                    return price;
                }else{
                    return parseFloat(price).toFixed(2);
                }
            });
    		template.helper('$formatDate', function (millsec) {
                return moment(millsec).format("YYYY-MM-DD");
            });
    		var $tbody = $('#list-table').find('tbody');
    		var renderPagination = function(){
                page = new Pagination({
                    url: "${ctx}/common/productAdjustDetail/ajax_list",
                    elem: "#pagination",
                    form:$("#search-form"),
                    pageSize: 20,
                    method:"post",
                    handleData: function (json) {
                        var html = template.render("line-template", json);
                        $tbody.empty().append(html);
                        
                        seajs.use("jscroll",function(){
                            $(".scrollArea").jscrollbar({
                                width: "5"
                            });
                        });
                    }
                });
            };
            renderPagination();
    	});
    	
    });
</script>
</body>
</html>