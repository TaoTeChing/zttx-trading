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
                <form:form cssClass="ui-form" action="" method="post" id="">
                    <div class="ui-form-item">
                        <label class="ui-label">
                            选择品牌：
                        </label>
                        <div class="fl">
                            <select class="ui-select js_select list-select-width" name="" id="111">
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            产品货号：
                        </label>
                        <input class="ui-input" type="text" name="" value="" style="width: 235px;">
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            产品名称：
                        </label>
                        <input class="ui-input" type="text" name="" value="" style="width: 235px;">
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            颜色：
                        </label>
                        <input class="ui-input" type="text" name="" value="" style="width: 87px;">
                        <label style="font-size: 14px;">
                            尺码：
                        </label>
                        <input class="ui-input" type="text" name="" value="" style="width: 88px;">
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            调价时间：
                        </label>
                        <input class="ui-input" id="dateRebate1" type="text" name="" value="" style="width: 214px;" readonly>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" style="width: 103px;">
                            调价生效时间：
                        </label>
                        <input class="ui-input" id="dateRebate2" type="text" name="" value="" style="width: 181px;" readonly>
                    </div>
                    <div class="ui-form-item" style="float: right;text-align: right;margin-right: 15px;">
                        <input type="button" class="ui_button_myourself" value="搜索">
                    </div>
                </form:form>
            </div>
            <div>
                <table class="rebate-table">
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
                        <tr>
                            <td>001_1</td>
                            <td>001产品</td>
                            <td>朵彩</td>
                            <td>红色</td>
                            <td>XXL</td>
                            <td>90.00</td>
                            <td>10%</td>
                            <td>50.00</td>
                            <td>10%</td>
                            <td>2015-11-17</td>
                            <td>2015-11-18</td>
                        </tr>
                        <tr>
                            <td>001_1</td>
                            <td>001产品</td>
                            <td>朵彩</td>
                            <td>红色</td>
                            <td>XXL</td>
                            <td>90.00</td>
                            <td>10%</td>
                            <td>50.00</td>
                            <td>10%</td>
                            <td>2015-11-17</td>
                            <td>2015-11-18</td>
                        </tr>
                        <tr>
                            <td>001_1</td>
                            <td>001产品</td>
                            <td>朵彩</td>
                            <td>红色</td>
                            <td>XXL</td>
                            <td>90.00</td>
                            <td>10%</td>
                            <td>50.00</td>
                            <td>10%</td>
                            <td>2015-11-17</td>
                            <td>2015-11-18</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp"/>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script src="${res}/scripts/src/brand/productmanage.js"></script>
<script>
    rebateDetail.init();
</script>
</body>
</html>