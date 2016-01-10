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
    <style>.factory-seeking-form .ui_button_myourself{padding: 3px 10px;}</style>
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
                <span>我的报表</span>
                <span class="arrow">&gt;</span>
                <span class="current">工厂店库存报表</span>
            </div>
            <div class="fr">
                <%--<jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />--%>
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方 -->
            <form:form id="" name="" class="ui-form factory-seeking-form clearfix">
                <div class="ui-form-item">
                    <label for="" class="ui-label">品牌： </label>
                    <input class="ui-input" type="text"  id="brandName" style="width: 210px;" value=""/>
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">产品名称：</label>
                    <input class="ui-input" type="text" id="productName" style="width: 210px;" value=""/>
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">产品货号：</label>
                    <input class="ui-input" type="text" id="productNo" style="width: 210px;" value="" />
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">终端商名称：</label>
                    <input class="ui-input" type="text" id="dealerName" style="width: 210px;" value="" />
                </div>
                <div class="ui-form-item" style="width: 200px; line-height: 30px;">
                    <label><input type="checkbox"/>包含现款库存</label>
                    <label><input type="checkbox"/>按SKU查看</label>
                </div>
                <div class="ta-r fr mt10">
                    <input type="button" value="搜索" class="ui_button_myourself event-query">
                    <input type="button" value="导出EXCEL表格" class="ui_button_myourself">
                    <input type="button" value="导出EXCEL表格（含SKU）" class="ui_button_myourself" style="margin-right: 35px;">
                </div>
            </form:form>
            <%-- 按产品查看 --%>
            <div>
                <table class="factory-count-table mt10">
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
                        <tr>
                            <td>001</td>
                            <td>A产品</td>
                            <td>A品牌</td>
                            <td>红色/XL</td>
                            <td>A品牌商</td>
                            <td>5</td>
                        </tr>
                        <tr>
                            <td>001</td>
                            <td>A产品</td>
                            <td>A品牌</td>
                            <td>红色/XL</td>
                            <td>A品牌商</td>
                            <td>5</td>
                        </tr>
                        <tr>
                            <td>001</td>
                            <td>A产品</td>
                            <td>A品牌</td>
                            <td>红色/XL</td>
                            <td>A品牌商</td>
                            <td>5</td>
                        </tr>
                        <tr>
                            <td>001</td>
                            <td>A产品</td>
                            <td>A品牌</td>
                            <td>红色/XL</td>
                            <td>A品牌商</td>
                            <td>5</td>
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