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
                <span class="current">返点产品调价明细</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方 -->
            <form:form id="" name="" class="ui-form" onsubmit="return false;" style="padding-top:20px;">
                <div class="ui-form-item">
                    <label class="ui-label">
                        终端商名称：
                    </label>
                    <input type="text" class="ui-input" name="dealerName"/>
                    <input type="button" class="ui_button ui_button_morange" style="vertical-align: -1px" value='搜 索' />
                </div>
            </form:form>
            <div>
                <table class="factory-count-table">
                    <colgroup>
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
                        <th>终端商名称</th>
                        <th>总销量</th>
                        <th>总销售额</th>
                        <th>总返点金额</th>
                        <th>总返点应收款</th>
                        <th>总返点已收款</th>
                        <th>返点欠收款</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>XX终端商</td>
                            <td>12</td>
                            <td>100000.00</td>
                            <td>10000.00</td>
                            <td>5000.00</td>
                            <td>5000.00</td>
                            <td>5000.00</td>
                            <td>
                                <a href="#" class="link">查看明细</a><br />
                                <a href="#" class="link">库存明细</a>
                            </td>
                        </tr>
                        <tr>
                            <td>XX终端商</td>
                            <td>12</td>
                            <td>100000.00</td>
                            <td>10000.00</td>
                            <td>5000.00</td>
                            <td>5000.00</td>
                            <td>5000.00</td>
                            <td>
                                <a href="#" class="link">查看明细</a><br />
                                <a href="#" class="link">库存明细</a>
                            </td>
                        </tr>
                        <tr>
                            <td>XX终端商</td>
                            <td>12</td>
                            <td>100000.00</td>
                            <td>10000.00</td>
                            <td>5000.00</td>
                            <td>5000.00</td>
                            <td>5000.00</td>
                            <td>
                                <a href="#" class="link">查看明细</a><br />
                                <a href="#" class="link">库存明细</a>
                            </td>
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