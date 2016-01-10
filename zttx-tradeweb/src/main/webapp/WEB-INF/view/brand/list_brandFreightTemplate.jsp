<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-运费模板管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/productmanag.css"/>
</head>
<body>
<input type="hidden" id="isRecommendHide" name="isRecommendHide" value="${isRecommend}">
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
<div class="main layout">
    <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
    <div class="main_con">
        <!-- 面包导航，并不是每个页面都有 -->
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <span class="current">运费模板管理</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <div class="fare-template">
            	<c:if test="${empty productIds}">
                <!-- tabs 切换 -->
                <ul class="inner-tabs inline-float">
                    <li <c:if test="${isRecommend == 0}"> class="selected"</c:if>><a href="${ctx}/brand/freightTemplate/list?isRecommend=0">自定义模板</a></li>
                    <li <c:if test="${isRecommend == 1}"> class="selected"</c:if>><a href="${ctx}/brand/freightTemplate/list?isRecommend=1">推荐模板</a></li>
                </ul>
                </c:if>
                <!-- 内容 -->
                <div class="mt15" id="fareTplList">
                    <p class="tips">
                        <i class="v2-icon-explain"></i>
                        提示：平台暂时只支持一套运费模板，如需调整，请在原模板进行修改；
                    </p>

            		<c:if test="${empty productIds}">
                    <c:if test="${isRecommend == 0 && templateNumber<1}">
                    <!-- 新建运费模板 按钮 -->
                    <a class="add-new-fare mt5" id="addNewFare" href="${ctx}/brand/freightTemplate/get">
                        <i class="iconfont"></i>
                        <span class="newclasspan">新建运费模板</span>
                    </a>
                    </c:if>
                    </c:if>
                    <!-- 运费模板 列表 -->
                    <div id="diyTplCon"></div>
                    <!-- 分页 -->
                    <div id="pagination" class="mt5"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<c:if test="${isRecommend == 0}">
    <script type="text/html" id="diyTplTemplate">
        {{each rows}}
        <div class="fare-tpl-list">
            <div class="fare-tpl-hd clearfix">
                <div class="fl">
                    <span>{{$value.name}}</span>
                    {{if $value.isDefault == 1}}
                    <span>
                        （<em class="default">默认模板</em>）
                    </span>
                    {{/if}}
                    <span class="ml5">
                        发货地区：{{$value.fullAreaName}}
                    </span>
                </div>
                <div class="fr" data-id="{{$value.refrenceId}}">
                    <a class="link js-edit-fare" href="${ctx}/brand/freightTemplate/get?templateId={{$value.refrenceId}}&productIds=${productIds}">修改</a>
                    <a class="link js-delete-fare" href="javascript:;">删除</a>
                </div>
            </div>
            <div style="overflow-x: hidden;overflow-y: auto;max-height: 209px;_height:209px;">
                <table class="fare-table" style="width: 100%;">
                    <colgroup>
                        <col width="120"/>
                        <col width="360"/>
                        <col width="120"/>
                        <col width="120"/>
                        <col width="120"/>
                        <col width="120"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <th>运送方式</th>
                        <th>运送到</th>
                        <th>首重（kg）</th>
                        <th>首重价格（元）</th>
                        <th>续重（kg）</th>
                        <th>续重价格（元）</th>
                    </tr>
                    </thead>
                    <tbody>
                    {{if $value.listMapDetail.length > 0}}
                        {{each $value.listMapDetail}}
                        <tr>
                            <td>{{$value.carryTypeName}}</td>
                            <td>
                                {{if $value.arriveAddress}}
                                    {{$value.arriveAddress}}
                                {{else}}
                                    默认运费
                                {{/if}}
                            </td>
                            <td>{{formatNumberThree $value.firstWeight}}</td>
                            <td>{{formatNumberTwo $value.firstPrice}}</td>
                            <td>{{formatNumberThree $value.extendWeight}}</td>
                            <td>{{formatNumberTwo $value.extendPrice}}</td>
                        </tr>
                        {{/each}}
                    {{else}}
                    <tr>
                        <td class="ta-c" colspan="6">暂无数据</td>
                    </tr>
                    {{/if}}
                    {{if $value.expressCollect || $value.logisticsCollect}}
                    <tr>
                        <td colspan="6">
                            {{if $value.expressCollect}}
                                <span>
                                    支持{{$value.expressCollect}} &nbsp;
                                </span>
                            {{/if}}
                            {{if $value.logisticsCollect}}
                                <span>
                                     支持{{$value.logisticsCollect}}
                                </span>
                            {{/if}}
                        </td>
                    </tr>
                    {{/if}}
                    </tbody>
                </table>
            </div>
			<c:if test="${not empty productIds}">
            <div class="fare-tpl-bd" data-id="{{$value.refrenceId}}">
                <input type="hidden" value="${productIds}" name="productIds" id="productIds">
                <a class="ui_button ui_button_sorange fr mt5 mr5 js-use-fare" href="javascript:;">应用该模板</a>
            </div>
			</c:if>
        </div>
        {{/each}}
    </script>
</c:if>
<c:if test="${isRecommend == 1}">
    <script type="text/html" id="diyTplTemplate">
        {{each rows}}
        <div class="fare-tpl-list">
            <div class="fare-tpl-hd clearfix">
                <div class="fl">
                    <span>{{$value.name}}</span>
                </div>
                <div class="fr" data-id="{{$value.refrenceId}}">
                    <c:if test="${templateNumber < 1}">
                    <a class="link js-edit-fare" href="${ctx}/brand/freightTemplate/get?templateId={{$value.refrenceId}}&isRecommend=1">复制模板</a>
                    </c:if>
                </div>
            </div>
            <div style="overflow-x: hidden;overflow-y: auto;max-height: 209px;_height:209px;">
                <table class="fare-table" style="width: 100%;">
                    <colgroup>
                        <col width="120"/>
                        <col width="360"/>
                        <col width="120"/>
                        <col width="120"/>
                        <col width="120"/>
                        <col width="120"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <th>运送方式</th>
                        <th>运送到</th>
                        <th>首重（kg）</th>
                        <th>首重价格（元）</th>
                        <th>续重（kg）</th>
                        <th>续重价格（元）</th>
                    </tr>
                    </thead>
                    <tbody>
                    {{if $value.listMapDetail.length > 0}}
                        {{each $value.listMapDetail}}
                        <tr>
                            <td>{{$value.carryTypeName}}</td>
                            <td>
                                {{if $value.arriveAddress}}
                                    {{$value.arriveAddress}}
                                {{else}}
                                    默认运费
                                {{/if}}
                            </td>
                            <td>{{formatNumberThree $value.firstWeight}}</td>
                            <td>{{formatNumberTwo $value.firstPrice}}</td>
                            <td>{{formatNumberThree $value.extendWeight}}</td>
                            <td>{{formatNumberTwo $value.extendPrice}}</td>
                        </tr>
                        {{/each}}
                    {{else}}
                    <tr>
                        <td class="ta-c" colspan="6">暂无数据</td>
                    </tr>
                    {{/if}}
                    {{if $value.expressCollect || $value.logisticsCollect}}
                    <tr>
                        <td colspan="6">
                            {{if $value.expressCollect}}
                                <span>
                                    支持{{$value.expressCollect}} &nbsp;
                                </span>
                            {{/if}}
                            {{if $value.logisticsCollect}}
                                <span>
                                     支持{{$value.logisticsCollect}}
                                </span>
                            {{/if}}
                        </td>
                    </tr>
                    {{/if}}
                    </tbody>
                </table>
            </div>
            <c:if test="${not empty productIds}">
            <div class="fare-tpl-bd" data-id="{{$value.refrenceId}}">
                <a class="ui_button ui_button_sorange fr mt5 mr5 js-use-fare" href="javascript:;">应用该模板</a>
            </div>
            </c:if>
        </div>
        {{/each}}
    </script>
</c:if>
<script type="text/html" id="useThisTpl">
    <div class="ui-head">
        <h3>提示</h3>
    </div>
    <div class="js-setArea ta-c mt20" style="font-size: 14px;">
        <span>成功应用此模版，</span>
        <a href="${ctx}/brand/product/list" style="color: #0082cc;">关闭页面</a>
    </div>
</script>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/productmanage.js"></script>
<script src="${src}/common/area.js"></script>
<script src="${src}/brand/list_brandFreightTemplate.js"></script>
</body>
</html>