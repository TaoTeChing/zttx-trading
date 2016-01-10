<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-终端商管理-已终止合作的终端商</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/agencymanag.css"/>
    <style>
        .scrollArea{
            height: 84px;
            overflow: auto;
        }
        .ui-form-item{width: 330px;}
    </style>
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
                <a class="link" href="${ctx}/brand/dealer/listDealerApply">终端商管理</a>
                <span class="arrow">&gt;</span>
                <span class="current">已终止合作的终端商</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <!-- 申请中的终端商 -->
            <div class="agency-teamed-contant">
                <form:form id="search-form" class="ui-form anency-teamed-form clearfix" action="${ctx}/brand/join/list" method="post">
                    <div class="ui-form-item">
                        <label class="ui-label">终端商等级：</label>
                        <div class="inline-block">
                            <select class="ui-select js_select js-setcom-tobe" name="dealerLevel" id="Ateamed-select-class" style="width:196px; height: 35px;">
                                <option value="">全部终端商等级</option>
                                <c:forEach items="${dealerLevels}" var="dealerLvl">
                                    <option value="${dealerLvl.refrenceId}" ${filter.dealerLevel == dealerLvl.refrenceId ? ' selected="selected"':''}>${dealerLvl.levelName}</option>
                                </c:forEach>
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
                    <div class="ui-form-item">
                        <label class="ui-label">终端商名称：</label>
                        <input type="text" name="dealerName" value="${filter.dealerName}" class="ui-input" style="width:190px;"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" style="width: 80px;">
                            授权时间：
                        </label>
                        <input readonly="readonly" value="${filter.startTimeString}" name="startTime" type="text" class="ui-input hasDatepicker" id="start-cal" style="width:48px">
                        -
                        <input readonly="readonly" value="${filter.endTimeString}" name="endTime" type="text" class="ui-input hasDatepicker" id="end-cal" style="width:48px">
                    </div>

                    <div class="ui-form-item">
                        <label class="ui-label">
                            近半年进货总额：
                        </label>
                        <input type="text" value="${filter.min}" name="min" class="ui-input js-price" style="width: 81px;" />
                        -
                        <input type="text" value="${filter.max}" name="max" class="ui-input js-price" style="width: 81px;" />
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" style="width: 92px;">品牌：</label>
                        <div class="inline-block">
                            <select class="ui-select js_select js-setcom-as" name="bid" style="width: 227px;height: 35px;">
                                <option value="" ${empty filter.bid ? 'selected="selected"':''}>全部品牌</option>
                                <c:forEach items="${brandsInfos}" var="brands">
                                    <option value="${brands.refrenceId}" ${brands.refrenceId == filter.bid ? 'selected="selected"':''}>${brands.brandsName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="ui-form-item" style="margin-left: 5px;">
                        <div class="inline-block">
                            <input type="hidden" name="state" value="2">
                            <input type="submit" value="查询" class="ui_button_myourself"/>
                            <input id="btn-export" type="button" value="导出终端商名单" class="ui_button_myourself"/>
                        </div>
                    </div>
                </form:form>
                <div class="js_agency_tabs">
                    <div class="js_agency_menu">
                        <ul class="clearfix">
                            <li><a href="${ctx}/brand/join/list">合作中的</a></li>
                            <li class="selected">
                                <a href="javascript:;">已终止的</a></li>
                        </ul>
                    </div>
                    <div class="js_agency_con mt10">
                        <div class="agency-teamed">
                            <table class="agency-teamed-table">
                                <colgroup>
                                    <col width="250"/>
                                    <col width="140"/>
                                    <col width="190"/>
                                    <!-- <col width="130"/> -->
                                    <col width="140"/>
                                    <col width="140"/>
                                </colgroup>
                                <thead>
                                <tr>
                                    <th><span>终端商名及终止时间</span></th>
                                    <th><span>终端商等级</span></th>
                                    <th><span>品牌名称</span></th>
                                    <th><span>采购金额</span></th>
                                    <th class="teamed-th-tc"><span>操作</span>
                                    </th>
                                </tr>
                                </thead>
                                <tbody id="tbody">
                                <c:choose>
                               	<c:when test="${empty pageResult.list}">
							     <tr>
							         <td colspan="5" style="text-align:center;">暂无数据</td>
							     </tr>
                               	</c:when>
                               	<c:otherwise>
                                <c:forEach items="${pageResult.list}" var="dealerJoin">
                                    <tr>
                                        <input type="hidden" class="js-sendvalue" value="${dealerJoin.refrenceId}">
                                        <td>
                                            <a class="bluefont">${dealerJoin.dealerName}&nbsp;(${fns:getTimeFromLong(dealerJoin.endTime,"yyyy-MM-dd")})</a>

                                            <div>
                                                <a href="javascript:;" class="bluefont js-leave-message" data-dealerid="${dealerJoin.dealerId}">留言</a>
                                            </div>
                                        </td>
                                        <td>${dealerJoin.levelName}</td>
                                        <td>${dealerJoin.brandsName}</td>
                                        <td>
                                            <div>
                                                <strong>${dealerJoin.orderMoney}</strong>
                                            </div>
                                            <div>
                                            </div>
                                        </td>
                                        <td class="teamed-td-tc">
                                            <div>
                                                <a class="ui_button ui_button_morange js-invite-join" data-dealer-id="${dealerJoin.dealerId}" data-brands-id="${dealerJoin.brandsId}">邀请加盟</a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>
                            <div id="pagination" class="pagination"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<div style="display: none">
    <!--留言-->
    <div class="js-leave-messagebox">
        <div class="ui-head">
            <h3>留言</h3>
        </div>
        <form:form id="msg-form" method="post" action="${ctx}/brand/message/sendDealer">
            <input name="dealerIds" type="hidden" class="js-getvalue" value="">
            <div class="content">
                <input id="msg-title" name="title" type="text" value="留言" placeholder="留言" class="ui-input mt10" maxlength="128" style="width: 200px"/>
                <textarea id="msg-content" name="content" class="ui-textarea mt10" style="width:335px;height: 78px;" maxlength="5120" placeholder="请输入留言内容"></textarea>
            </div>
            <div class="ta-c mt10">
                <a href="javascript:;" class="simple_button js-lemsgsure-btn">确认</a>
                <a href="javascript:;" class="simple_button js-lemsgsurecansole-btn">取消</a>
            </div>
        </form:form>
    </div>
</div>

<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script type="text/javascript" src="${src}/brand/agencymanag.js"></script>
<jsp:include page="/WEB-INF/view/brand/dealer_invite_showbox.jsp"/>
<script type="text/html" id="template">
    {{each rows}}
    <tr data-id="">

        <input type="hidden" class="js-sendvalue" value="{{$value.refrenceId}}">
        <input type="hidden" class="js-dealerId" value="{{$value.dealerId}}">

        <td>
            <a class="bluefont">{{$value.dealerName}}&nbsp;({{$formatDate $value.endTime}})</a>
            <div>
                <a href="javascript:;" class="bluefont js-leave-message" data-dealerid="{{$value.dealerId}}">留言</a>
            </div>
        </td>
        <td>{{$value.levelName}}</td>
        <td>{{$value.brandsName}}</td>
        <td>
            <div>
                <strong>{{$value.orderMoney}}</strong>
            </div>
            <div>
            </div>
        </td>
        <td class="teamed-td-tc">
            <div><a class="ui_button ui_button_morange js-invite-join" data-dealer-id="{{$value.dealerId}}" data-brands-id="{{$value.brandsId}}">邀请加盟</a></div>
        </td>
    </tr>
    {{/each}}
	{{ if rows==null||rows.length <= 0 }}
         <tr>
             <td colspan="5" style="text-align:center;">暂无数据</td>
         </tr>
     {{ /if }}
</script>
<script type="text/javascript">
    // 品牌-终端商等级二级联动
    var $selectBrand = $('select[name="bid"]');
    var $selectLevel = $('select[name="dealerLevel"]');
    var $msgForm = $('#msg-form');
    var $msgContent = $('#msg-content');
    if ($selectLevel.children().length <= 1) {
        $selectLevel.prop('disabled', true);
    }

    $selectBrand.change(function (event) {
        var brandsId = $selectBrand.val();
        if (brandsId == '') {
            $selectLevel.empty().append('<option value="">全部终端商等级</option> ').prop('disabled', true);
        } else {
            $selectLevel.empty().append($('#' + brandsId + '-lvls').html()).prop('disabled', false);
        }
    });

    $(document).on('click', '#btn-export', function (event) {
        event.stopPropagation();
        var params = $('#search-form').serialize();
        window.location.href = "${ctx}/brand/join/list.xls?" + params;
    });

    // 获取tr里面的value赋给弹出层
    $(".agency-teamed-table").on("click", ".js-leave-message", function () {
        var dealerid = $(this).data("dealerid");
        $(".js-leave-messagebox .js-getvalue").val(dealerid);
    });

    // 邀请合作按钮点击传值
    $(".agency-teamed-table").on("click", ".js-invite-join", function () {
        var val = $(this).data('dealer-id');
        $('input[name="dealerId"]').val(val);
    })

    var $inviteForm = $('#invite-form');
    agencyteamed.init();
    // 邀请合作

    function inviteJoin(obj, invitedio) {
        $("#dealerId").val($.trim(obj.data("dealer-id")));
        invitEditDiv = invitedio;
    }

    seajs.use(['dialog'], function (Dialog) {
        var target = null;
        var invitedio = null;
        var invitedio = new Dialog({//邀请加盟按钮
            trigger: '.js-invite-join',
            effect: 'fade',
            hasMask: false,
            content: $(".js-invite-show"),
            width: 385
        }).after("show", function () {
                    $("#inviteText").val("");
                    target = this.activeTrigger;
                    if ($.isFunction(inviteJoin)) {
                        inviteJoin(target, invitedio);
                    }
                }).before("show", function () {
                    $("#brandsId").val("");
                    $("#brandsId_div span").html('请选择品牌');
                    $(".joindealer").hide();
                    $("#joinDiv").show();
                });
        $(".js-invite-show").on("click", ".cancel_btn", function () {
            invitedio.hide();
        })
    });
    
    function leaMsg(obj, dialog) {//留言弹窗点击确定后的操作可以写在这里
        // 点击”确定“后出发的方法
        var content = $.trim($msgContent.val());
        if (content == '') {
            remind('error', '请输入内容!');
            return false;
        }
        $.post('${ctx}/brand/message/sendDealer', $msgForm.serialize(), function (data, status, jqXHR) {
            if (data.code === zttx.SUCCESS) {
                remind('success', '留言成功');
                $msgForm[0].reset();
            } else {
                remind('error', data.message);
            }
            dialog.hide();
        }, 'json');
    }

    function scrollArea(){
        seajs.use("jscroll",function(){
            $(".scrollArea").jscrollbar({
                width: "5"
            });
        })
    }

    scrollArea();

    // ajax渲染
    seajs.use(['pagination', 'template', 'moment'], function (Pagination, template, moment) {
        template.helper('$formatDate', function (millsec) {
            return moment(millsec).format("YYYY-MM-DD");
        });
        var $tbody = $('#tbody');
        var pagination = new Pagination({
            url: '${ctx}/brand/join/list.json',
            form: '#search-form',
            currentPage: ${pageResult.page.currentPage},
            total: ${pageResult.page.totalPage},
            elem: '#pagination',
            handleData: function (data) {
                var html = template.render('template', data);
                $tbody.empty().append(html);
                scrollArea();
            }
        });
    });
</script>
</body>
</html>
