<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-产品管理-返点产品调价明细</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/factory.css"/>
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
                <span class="current">库存明细</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方 -->
            <form:form id="" name="" class="ui-form factory-seeking-form clearfix">
                <div class="ui-form-item">
                    <label for="" class="ui-label">品牌名称： </label>
                    <input class="ui-input" type="text" disabled value="七休" style="width: 210px;"/>
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">产品名称：</label>
                    <input class="ui-input" type="text" disabled value="A产品" style="width: 210px;"/>
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">产品货号：</label>
                    <input class="ui-input" type="text" disabled value="001" style="width: 210px;"/>
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">颜色/尺码：</label>
                    <input class="ui-input" type="text" disabled value="红_XXL" style="width: 210px;"/>
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label"> 类型：</label>
                    <div class="inline-block pr">
                        <select class="js_select" name="type" style="width:220px;height: 30px;">
                            <option value="">请选择</option>
                            <c:forEach var="type" items="${tradeType}">
                                <option value="${type.key}">${type.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="ta-r fr mt10">
                    <input type="button" value="搜索"
                           class="ui_button_myourself event-query" style="margin-right: 35px;">
                </div>
            </form:form>
            <div>
                <table class="factory-count-table mt10">
                    <colgroup>
                        <col width="256"/>
                        <col width="251"/>
                        <col width="251"/>
                        <col width="251"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <th>时间</th>
                        <th>单号</th>
                        <th>类型</th>
                        <th>数量</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>2015-11-17 15:00:00</td>
                            <td>001_1</td>
                            <td>销售</td>
                            <td>2</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp"/>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
</body>
</html>