<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-新增运费模板</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/productmanag.css"/>
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
<div class="main layout">
    <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
    <div class="main_con">
        <!-- 面包导航，并不是每个页面都有 -->
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <span class="current">
                <c:choose><c:when test="${empty paramModel.template.refrenceId}">新增</c:when><c:otherwise>修改</c:otherwise></c:choose>运费模板</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方 -->
            <div class="fare-template">
                <form class="ui-form fare-add-form" id="fare-add-form" data-widget="validator" action="${ctx}/brand/freightTemplate/saveBrandFreight">
                    <input type="hidden" value="${paramModel.template.refrenceId}" name="template.refrenceId">
                    <input type="hidden" value="${isRecommend}" name="isRecommend" id="isRecommend">
                    <input type="hidden" value="${productIds}" name="productIds" id="productIds">
                    <div class="ui-form-item">
                        <label class="ui-label">
                            模板名称：
                            <span class="ui-form-required">*</span>
                        </label>
                        <input type="text" class="ui-input" required maxlength="50" data-rule="maxlength{max:50}" data-display="模版名称" name="template.name" value="${paramModel.template.name}" />
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            发货地区：
                            <span class="ui-form-required">*</span>
                        </label>
                        <div class="inline-block">
                            <style>
                                .js-select{width: 100px;}
                            </style>
                            <jsp:include page="${ctx}/common/regional/searchAllArea">
                                <jsp:param value="${paramModel.template.areaNo}" name="regAreaNo"/>
                                <jsp:param value="ui-select js-select" name="style"/>
                            </jsp:include>
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            运送方式：
                            <span class="ui-form-required">*</span>
                        </label>
                        <div class="inline-block mt5">
                            <span class="cnine">除指定地区外，其余地区的运费采用“默认运费”,价格重量请在0-10000000范围之间(不包含临界值),超出将按最大值计算;</span>
                            <div class="mt10 csix js-edit-main">
                                <div>
                                    <input type="checkbox" class="ui-checkbox mr10 js-edit-item" name="isExpressUsed" id="isExpressUsed" value="1" <c:if test="${paramModel.isExpressUsed}"> checked </c:if> />快递
                                </div>
                                <div class="edit-area mt10 js-edit-area" style="display: none;">
                                    <div class="hd cnine">
                                        默认运费：<input class="ui-input js-weight" name="defaultExpress.firstWeight" type="text" data-min="0.001" data-max="9999999.999" style="width: 70px;" value="${paramModel.defaultExpress.firstWeight}" disabled /> kg内，
                                        <input class="ui-input js-input-price" name="defaultExpress.firstPrice" type="text" data-min="0.01" data-max="9999999.99" style="width: 70px;" value="${paramModel.defaultExpress.firstPrice }" disabled /> 元，
                                        每增加 <input class="ui-input js-weight" name="defaultExpress.extendWeight" type="text" data-min="0.001" data-max="9999999.999" style="width: 70px;" value="${paramModel.defaultExpress.extendWeight }" disabled /> kg，
                                        增加运费 <input class="ui-input js-input-price" name="defaultExpress.extendPrice" type="text" data-min="0.01" data-max="9999999.99" style="width: 70px;" value="${paramModel.defaultExpress.extendPrice }" disabled /> 元
                                    </div>
                                    <div class="bd mt10 js-edit-areafare">
                                        <table class="fare-table" style="width: 100%;">
                                            <colgroup>
                                                <col width="250"/>
                                                <col width="110"/>
                                                <col width="110"/>
                                                <col width="110"/>
                                                <col width="110"/>
                                                <col width="70"/>
                                            </colgroup>
                                            <thead>
                                            <tr>
                                                <th>运送到</th>
                                                <th>首重（kg）</th>
                                                <th>首重价格（元）</th>
                                                <th>续重（kg）</th>
                                                <th>续重价格（元）</th>
                                                <th class="last">操作</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${paramModel.expressList}" var="expressList" varStatus="status">
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" class="ui-checkbox js-setarea-checkbox mr5" data-index="${status.index}" style="display: none;"/>
                                                        <input type="hidden" name="expressList[${status.index}].areaNos" value="${expressList.areaNos }">
                                                        <a class="link fr js-edit" href="javascript:;" data-value="${expressList.areaNos }">编辑</a>
                                                        <span style="display: inline-block;width: 207px;vertical-align: middle">${expressList.arriveAddress}</span>
                                                    </td>
                                                    <td><input class="ui-input js-first-w" type="text" data-min="0.001" data-max="9999999.999" name="expressList[${status.index}].firstWeight" value="${expressList.firstWeight }" style="width: 85px;" /></td>
                                                    <td><input class="ui-input js-first-p" type="text" data-min="0.01" data-max="9999999.99" name="expressList[${status.index}].firstPrice" value="${expressList.firstPrice }" style="width: 85px;" /></td>
                                                    <td><input class="ui-input js-zen-w" type="text" data-min="0.001" data-max="9999999.999" name="expressList[${status.index}].extendWeight" value="${expressList.extendWeight }" style="width: 85px;" /></td>
                                                    <td><input class="ui-input js-zen-p" type="text" data-min="0.01" data-max="9999999.99" name="expressList[${status.index}].extendPrice" value="${expressList.extendPrice }" style="width: 85px;" /></td>
                                                    <td class="ta-c last">
                                                        <a class="link js-setarea-delete" href="javascript:;">删除</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="mt10 js-edit-batch" style="display: none;">
                                            <input type="checkbox" class="ui-checkbox mr10 js-edit-checkall"/> 全选
                                            <a class="link ml10 js-set-all" href="javascript:;">批量设置</a>
                                            <a class="link ml10 js-delete-all" href="javascript:;">批量删除</a>
                                        </div>
                                    </div>
                                    <div class="mt10">
                                        <a class="link js-setarea-fare" href="javascript:;">为指定地区城市设置运费</a>
                                        <a class="link ml10 js-batch-operation" href="javascript:;">批量操作</a>
                                    </div>
                                </div>
                            </div>
                            <div class="csix js-edit-main" style="margin-top: 30px;">
                                <div>
                                    <input type="checkbox" class="ui-checkbox mr10 js-edit-item" name="isLogisticsUsed" id="isLogisticsUsed"  <c:if test="${paramModel.isLogisticsUsed}"> checked </c:if> />物流
                                </div>
                                <div class="edit-area mt10 js-edit-area" style="display: none;">
                                    <div class="hd cnine">
                                        默认运费：<input class="ui-input js-weight" type="text" data-min="0.001" data-max="9999999.999" name="defaultLogistics.firstWeight" style="width: 70px;" value="${paramModel.defaultLogistics.firstWeight}" disabled /> kg内，
                                        <input class="ui-input js-input-price" type="text" data-min="0.01" data-max="9999999.99" name="defaultLogistics.firstPrice" style="width: 70px;" value="${paramModel.defaultLogistics.firstPrice}" disabled /> 元，
                                        每增加 <input class="ui-input js-weight" type="text" data-min="0.001" data-max="9999999.999" name="defaultLogistics.extendWeight" style="width: 70px;" value="${paramModel.defaultLogistics.extendWeight }" disabled /> kg，
                                        增加运费 <input class="ui-input js-input-price" type="text" data-min="0.01" data-max="9999999.99" name="defaultLogistics.extendPrice" style="width: 70px;" value="${paramModel.defaultLogistics.extendPrice}" disabled /> 元
                                    </div>
                                    <div class="bd mt10 js-edit-areafare">
                                        <table class="fare-table" style="width: 100%;">
                                            <colgroup>
                                                <col width="250"/>
                                                <col width="110"/>
                                                <col width="110"/>
                                                <col width="110"/>
                                                <col width="110"/>
                                                <col width="70"/>
                                            </colgroup>
                                            <thead>
                                            <tr>
                                                <th>运送到</th>
                                                <th>首重（kg）</th>
                                                <th>首重价格（元）</th>
                                                <th>续重（kg）</th>
                                                <th>续重价格（元）</th>
                                                <th class="last">操作</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${paramModel.logisticsList}" var="logisticsList" varStatus="status">
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" class="ui-checkbox js-setarea-checkbox mr5" data-index="${status.index}" style="display: none;"/>
                                                        <input type="hidden" name="logisticsList[${status.index}].areaNos" value="${logisticsList.areaNos }">
                                                        <a class="link fr js-edit" href="javascript:;" data-value="${logisticsList.areaNos }">编辑</a>
                                                        <span style="display: inline-block;width: 207px;vertical-align: middle">${logisticsList.arriveAddress}</span>
                                                    </td>
                                                    <td><input class="ui-input js-first-w" type="text" data-min="0.001" data-max="9999999.999" name="logisticsList[${status.index}].firstWeight" value="${logisticsList.firstWeight }" style="width: 85px;" /></td>
                                                    <td><input class="ui-input js-first-p" type="text" data-min="0.01" data-max="9999999.99" name="logisticsList[${status.index}].firstPrice" value="${logisticsList.firstPrice }" style="width: 85px;" /></td>
                                                    <td><input class="ui-input js-zen-w" type="text" data-min="0.001" data-max="9999999.999" name="logisticsList[${status.index}].extendWeight" value="${logisticsList.extendWeight }" style="width: 85px;" /></td>
                                                    <td><input class="ui-input js-zen-p" type="text" data-min="0.01" data-max="9999999.99" name="logisticsList[${status.index}].extendPrice" value="${logisticsList.extendPrice }" style="width: 85px;" /></td>
                                                    <td class="ta-c last">
                                                        <a class="link js-setarea-delete" href="javascript:;">删除</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="mt10 js-edit-batch" style="display: none;">
                                            <input type="checkbox" class="ui-checkbox mr10 js-edit-checkall"/> 全选
                                            <a class="link ml10 js-set-all" href="javascript:;">批量设置</a>
                                            <a class="link ml10 js-delete-all" href="javascript:;">批量删除</a>
                                        </div>
                                    </div>
                                    <div class="mt10">
                                        <a class="link js-setarea-fare" href="javascript:;">为指定地区城市设置运费</a>
                                        <a class="link ml10 js-batch-operation" href="javascript:;">批量操作</a>
                                    </div>
                                </div>
                            </div>
                            <div class="csix" style="margin-top: 30px;">
                                <input type="checkbox" class="ui-checkbox mr10" name="isExpressCollectUsed" id="isExpressCollectUsed" <c:if test="${paramModel.isExpressCollectUsed}"> checked </c:if> />允许快递到付
                            </div>
                            <div class="csix" style="margin-top: 30px;">
                                <input type="checkbox" class="ui-checkbox mr10" name="isLogisticsCollectUsed" id="isLogisticsCollectUsed" <c:if test="${paramModel.isLogisticsCollectUsed}"> checked </c:if> />允许物流到付
                            </div>
                        </div>
                    </div>
                    <%--运送方式未输入提示--%>
                    <%--<div class="ui-form-item ui-form-item-error" id="fareTypeInput" style="display: none;margin-top: -28px;">
                        <label class="ui-label"></label>
                        <div class="ui-form-explain">请确认运送方式各项值填写完毕</div>
                    </div>--%>
                    <div class="ui-form-item">
                        <label class="ui-label"></label>
                        <input type="button" class="ui_button ui_button_morange" id="saveTemple" onclick="submitButton()" value="保存"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<script type="text/html" id="setAllTpl">
    <div class="ui-head">
        <h3>设置运费</h3>
    </div>
    <div class="mt10 js-setArea">
        设置运费：<input class="ui-input js-f-w" type="text" data-min="0.001" data-max="9999999.999" style="width: 70px;" value="1.000" /> kg内，
        <input class="ui-input js-f-p" type="text" data-min="0.01" data-max="9999999.99" style="width: 70px;" /> 元，
        每增加 <input class="ui-input js-z-w" type="text" data-min="0.001" data-max="9999999.999" style="width: 70px;" value="1.000" /> kg，
        增加运费 <input class="ui-input js-z-p" type="text" data-min="0.01" data-max="9999999.99" style="width: 70px;" /> 元
        <div class="operate ta-c mt10">
            <a class="simple_button js-sure" href="javascript:;">确定</a>
            <a class="simple_button ml10 js-cancel" style="" href="javascript:;">取消</a>
        </div>
    </div>
</script>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${res}/scripts/brand/productmanage.js"></script>
<!--地区-->
<%@ include file="set_area.jsp" %>

<script src="${src}/brand/edit_brandFreightTemplate.js"></script>

</body>
</html>