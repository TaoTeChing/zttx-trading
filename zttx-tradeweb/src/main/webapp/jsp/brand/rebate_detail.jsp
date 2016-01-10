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
<%--<jsp:include page="/common/menuInfo/mainmenu"/>--%>
<div class="main layout">
    <%--<jsp:include page="/common/menuInfo/sidemenu"/>--%>
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
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方 -->
            <form:form id="" name="" class="ui-form" onsubmit="return false;" style="padding-top:20px;">
                <div class="ui-form-item inline-block">
                    <label class="ui-label">
                        产品名称：
                    </label>
                    <input type="text" class="ui-input" name=""/>
                </div>
                <div class="ui-form-item inline-block">
                    <label class="ui-label">
                        产品货号：
                    </label>
                    <input type="text" class="ui-input" name=""/>
                </div>
                <div class="ui-form-item inline-block" style="padding-left: 20px;">
                    <input type="button" class="ui_button ui_button_morange" style="vertical-align: -1px" value='搜 索' />
                </div>
            </form:form>
            <div>
                <table class="factory-count-table">
                    <colgroup>
                        <col width="91"/>
                        <col width="91"/>
                        <col width="91"/>
                        <col width="91"/>
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
                        <th>产品货号</th>
                        <th>产品名称</th>
                        <th>颜色/尺码</th>
                        <th>销量</th>
                        <th>销售额</th>
                        <th>退货数</th>
                        <th>退货金额</th>
                        <th>盘亏数量</th>
                        <th>盘亏成本</th>
                        <th>销售成本</th>
                        <th>合计成本</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>001_1</td>
                            <td>名称</td>
                            <td>红色/XL</td>
                            <td>100</td>
                            <td>10.00</td>
                            <td>100</td>
                            <td>10.00</td>
                            <td>3</td>
                            <td>200.00</td>
                            <td>300.00</td>
                            <td>200.00</td>
                        </tr>
                        <tr>
                            <td>001_1</td>
                            <td>名称</td>
                            <td>红色/XL</td>
                            <td>100</td>
                            <td>10.00</td>
                            <td>100</td>
                            <td>10.00</td>
                            <td>3</td>
                            <td>200.00</td>
                            <td>300.00</td>
                            <td>200.00</td>
                        </tr>
                        <tr>
                            <td>001_1</td>
                            <td>名称</td>
                            <td>红色/XL</td>
                            <td>100</td>
                            <td>10.00</td>
                            <td>100</td>
                            <td>10.00</td>
                            <td>3</td>
                            <td>200.00</td>
                            <td>300.00</td>
                            <td>200.00</td>
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