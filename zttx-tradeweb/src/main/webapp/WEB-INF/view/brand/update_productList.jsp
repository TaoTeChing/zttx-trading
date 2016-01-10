<%--
  @(#)create_ProductList.jsp 14-3-18 上午9:42
  Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--%>
<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-产品线管理-修改产品线</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/prolinemaneg.css"/>
</head>
<body>
	<br><jsp:include page="${ctx}/brand/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/brand/brandmenu"/>
    <div class="main_con">
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <div class="bread_nav">
                <div class="fl">
                    <i class="icon"></i>
                    <a class="link" href="${ctx}/brand/center">管理中心</a>
                    <span class="arrow">&gt;&gt;</span>
                    <a class="link" href="${ctx}/brand/line/execute">产品线管理</a>
                    <span class="arrow">&gt;</span>
                    <span class="current">修改产品线</span>
                </div>
                <div class="fr">
                    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
                </div>
            </div>
            <form:form id="add-form" class="ui-form addLine_form" action="${ctx}/brand/line/update"
                       method="post">
                <input type="hidden" name="id" value="${line.refrenceId}"/>

                <div class="ui-form-item">
                    <label class="ui-label">
                        选择品牌：
                        <span class="ui-form-required">*</span>
                    </label>

                    <div class="inline-block">
                        <select class="ui-select js_select" name="brandesId" disabled="disabled" style="width: 121px;height: 30px;">
                            <option value="${line.brandesInfo.refrenceId}">${line.brandesInfo.brandName}</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-item">
                    <label class="ui-label">
                        产品线名称：
                        <span class="ui-form-required">*</span>
                    </label>
                    <input type="text" name="lineName" class="ui-input proline-name" value="${line.lineName}"/>
                </div>
                <div class="ui-form-item">
                    <label class="ui-label">产品线折扣率：<span class="ui-form-required">*</span></label>
                    <span>吊牌价X</span>
                    <input id="agio" name="lineAgio" type="text" class="ui-input proline-sellzhe  js-price" placeholder="4.5" value="${agio}"/>
                    <span>折</span>
                    <span>（0-10折）</span>
                </div>
                <div class="ui-form-item">
                    <label class="ui-label">产品线采购时间：<span class="ui-form-required">*</span></label>

                    <div class="radio_box inline-block">
                        <input class="ui-radio" name="buyTimeType" type="radio" value="0" id="owner1" ${line.buyType == 0 ? 'checked="checked"':""} />
                        <label for="owner1">不限时间</label>
                        <input ${line.buyType == 0 ? '':"checked='checked'"} class="ui-radio ml5" name="buyTimeType" type="radio" id="owner2" value="1"/>
                        <label for="owner2">设定时间</label>

                        <div class="inline-block proline-getimehide" style="display: none;">
                            <input value="${fns:getTimeFromLong(line.buyStart, 'yyyy-MM-dd')}" name="buyStartTime" type="text" class="ui-input date" id="start-cal" readonly="readonly"/>
                            -
                            <input value="${fns:getTimeFromLong(line.buyEnd, 'yyyy-MM-dd')}" name="buyEndTime" type="text" class="ui-input date" id="end-cal" readonly="readonly"/>
                        </div>
                    </div>
                </div>
                <!--div class="ui-form-item">
                    <label class="ui-label">
                        该产品线起批量：
                        <span class="ui-form-required">*</span>
                    </label>

                    <div class="inline-block proline-N-che">
                        <input value="${line.buyNum}" maxlength="4" name="buyNum" type="text" class="ui-input proline-number js-price"/>
                        件
                        <input value="${line.buyMoney}" maxlength="11" name="buyMoney" type="text" class="ui-input proline-number js-price" data-max="99999999.99"/>
                        元
                    </div>
                    <span class="ui-form-other">(请至少填写一个)</span>
                </div-->
                <div class="ui-form-item">
                    <label class="ui-label">
                        指定终端商：<span class="ui-form-required">*</span>
                    </label>

                    <div class="dealers_layer" style="margin-left: 70px;">
                        <div class="search_condition">
                            <span class="fl check_box"><input id="chk_all" class="ui-checkbox mr5" type="checkbox"/>全选</span>

                            <div class="level fl">
                                <label style="vertical-align: 2px;">终端商等级:</label>

                                <div class="inline-block">
                                    <select class="js_select" name="level_select" id="level_select">
                                        <option value="">全部终端商</option>
                                    </select>
                                    <c:forEach items="${brandLevels}" var="brandLevel">
                                        <script id="${brandLevel.refrenceId}-lvls" type="text/html">
                                            <option value="">全部终端商等级</option>
                                                <c:forEach items="${brandLevel.levels}" var="level">
                                                    <option value="${level.refrenceId}">${level.levelName}</option>
                                                </c:forEach>
                                        </script>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="area fl">
                                <label style="vertical-align: 2px;">终端商区域:</label>

                                <div class="inline-block">
                                    <select class="js_select" name="area_select" id="area_select">
                                        <option value="">全国</option>
                                        <option value="110000">北京市</option>
                                        <option value="120000">天津市</option>
                                        <option value="130000">河北省</option>
                                        <option value="140000">山西省</option>
                                        <option value="150000">内蒙古自治区</option>
                                        <option value="210000">辽宁省</option>
                                        <option value="220000">吉林省</option>
                                        <option value="230000">黑龙江省</option>
                                        <option value="310000">上海市</option>
                                        <option value="320000">江苏省</option>
                                        <option value="330000">浙江省</option>
                                        <option value="340000">安徽省</option>
                                        <option value="350000">福建省</option>
                                        <option value="360000">江西省</option>
                                        <option value="370000">山东省</option>
                                        <option value="410000">河南省</option>
                                        <option value="420000">湖北省</option>
                                        <option value="430000">湖南省</option>
                                        <option value="440000">广东省</option>
                                        <option value="450000">广西壮族自治区</option>
                                        <option value="460000">海南省</option>
                                        <option value="500000">重庆市</option>
                                        <option value="510000">四川省</option>
                                        <option value="520000">贵州省</option>
                                        <option value="530000">云南省</option>
                                        <option value="540000">西藏自治区</option>
                                        <option value="610000">陕西省</option>
                                        <option value="620000">甘肃省</option>
                                        <option value="630000">青海省</option>
                                        <option value="640000">宁夏回族自治区</option>
                                        <option value="650000">新疆维吾尔自治区</option>
                                        <option value="710000">台湾省</option>
                                        <option value="810000">香港特别行政区</option>
                                        <option value="820000">澳门特别行政区</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="data_list">
                            <table id="list-table">
                                <colgroup>
                                    <col width="192"/>
                                    <col width="192"/>
                                    <col width="192"/>
                                    <col width="194"/>
                                </colgroup>
                                <c:forEach items="${joinDealers}" var="dealer" varStatus="status">
                                    <c:if test="${status.index % 4 == 0}"><tr></c:if>
                                    <input type="checkbox" ${dealer.joined == true ? 'chcked="true"':''}  data-area="${dealer.areaNo}" data-level="${dealer.levelId}" name="dealerIds" value="${dealer.dealerId}" class="ui-checkbox mr5"/>${dealer.dealerName}
                                    <c:if test="${status.index % 4 == 3 or status.last}"></tr></c:if>
                                </c:forEach>
                                <tr></tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="ui-form-item">
                    <label class="ui-label">&nbsp;</label>
                    <button class="ui_button ui_button_morange proline-next-btn fr" type="submit">下一步</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script src="${res}/scripts/brand/prolinemanage.js"></script>
<script type="text/javascript" src="${res}/scripts/plugin/Validform_v5.3.2_min.js"></script>
<script>
    $(function () {
        rangeCalendar('start-cal', 'end-cal');
        addlineONE.init();
        $('#add-form').Validform({
            tiptype: 4,
            postonce: true,
            beforeSubmit: function (curform) {
                var $agio = $('#agio');
                var agio = $agio.val();
                if (!$.isNumeric(agio) || agio < 0 || agio > 10) {
                    remind('error', "折扣不正确");
                    return false;
                }
                var $timeCheck = $('[name="buyTimeType"]:checked').val();
                if ($timeCheck == '1') {
                    var $start = $('input[name="buyStartTime"]').val();
                    var $end = $('input[name="buyEndTime"]').val();
                    if ($start == '' || $end == '' || $end < $start) {
                        remind('error', '起止日期不正确');
                        return false;
                    }
                }
                var buyNum = $('input[name="buyNum"]').val();
                var buyMoney = $('input[name="buyMoney"]').val();
                /*
                if (!$.isNumeric(buyNum) && !$.isNumeric(buyMoney)) {
                    remind('error', "产品线起批量不正确");
                    return false;
                }
                if ($.isNumeric(buyNum) && buyNum < 0) {
                    remind('error', "产品线起批量不正确");
                    return false;
                }
                if ($.isNumeric(buyMoney) && buyMoney < 0) {
                    remind('error', "产品线起批量不正确");
                    return false;
                }
                */
                return true;
            }
        });
        addlineONE.init();
    });
</script>
<!--
    另外加载当前页面需要的js路径，或者使用
    seajs.use("./xxx.js")
 -->
</body>
</html>
