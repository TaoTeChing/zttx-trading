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
                <span class="current">库存明细</span>
            </div>
            <div class="fr">
                <%--<jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />--%>
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方 -->
            <form:form id="" name="" class="ui-form factory-seeking-form clearfix">
                <div class="ui-form-item">
                    <label for="" class="ui-label">终端商名称： </label>
                    <input class="ui-input" type="text" disabled  id="brandName" style="width: 210px;" value=""/>
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">联系人姓名：</label>
                    <input class="ui-input" type="text" disabled id="brandUser" style="width: 210px;" value=""/>
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">联系方式：</label>
                    <input class="ui-input" type="text" disabled id="brandMobile" style="width: 210px;" value="" />
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">所在地：</label>
                    <input class="ui-input" type="text" disabled id="address" style="width: 210px;" value="" />
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">日期：</label>
                    <input type="text"
                           class="ui-input date" id="start-cal" readonly
                           style="width: 90px;"  name="startTime"/> 至
                    <input type="text"
                           class="ui-input date" id="end-cal" readonly
                           style="width: 90px;"  name="endTimer"/>
                </div>
                <div class="ta-r fr mt10">
                    <input type="button" value="搜索"
                           class="ui_button_myourself event-query" style="margin-right: 35px;">
                </div>
            </form:form>
            <div class="js_grademanage_menu mt10">
                <ul class="inline-float">
                    <li class="selected"><a href="#">按日期查看</a></li>
                    <li><a href="#">按产品查看</a></li>
                </ul>
            </div>
            <%-- 按日期查看 --%>
            <div>
                <table class="factory-count-table">
                    <colgroup>
                        <col width="256"/>
                        <col width="251"/>
                        <col width="252"/>
                        <col width="251"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <th>时间</th>
                        <th>单号</th>
                        <th>类型</th>
                        <th>发货数/退货数/库存量/销售量</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>2015-11-5 15:00:00</td>
                            <td><a href="#" class="link">123456789</a></td>
                            <td>销售</td>
                            <td><a href="#" class="link">-10</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <%-- 按产品查看 --%>
            <div>
                <table class="factory-count-table">
                    <colgroup>
                        <col width="120"/>
                        <col width="111"/>
                        <col width="111"/>
                        <col width="111"/>
                        <col width="111"/>
                        <col width="111"/>
                        <col width="111"/>
                        <col width="111"/>
                        <col width="111"/>
                    </colgroup>
                    <thead>
                        <tr>
                            <th>品牌</th>
                            <th>产品名称</th>
                            <th>产品货号</th>
                            <th>颜色/尺码</th>
                            <th>产品类型变更数</th>
                            <th>发货数</th>
                            <th>销售数</th>
                            <th>退货数</th>
                            <th>库存数</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>朵彩</td>
                            <td>A产品</td>
                            <td>001</td>
                            <td>红色/XL</td>
                            <td>10</td>
                            <td>5</td>
                            <td>2</td>
                            <td>2</td>
                            <td><a href="" class="link">5</a></td>
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