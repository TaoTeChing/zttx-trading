<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-终端商管理-已合作的终端商</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/agencymanag.css"/>
    <style>
        .scrollArea{height: 84px;overflow: auto;}
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
                <span class="current">已合作的终端商</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <div class="agency-teamed-contant">
                <form:form id="search-form" class="ui-form anency-teamed-form clearfix" action="${ctx}/brand/join/list" method="post">
                    <div class="ui-form-item">
                        <label class="ui-label">终端商等级：</label>
                        <div class="inline-block">
                            <select class="ui-select js_select js-setcom-tobe" name="dealerLevel" id="Ateamed-select-class" style="width:196px; height: 35px ">
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
                        <label class="ui-label">
                            授权时间：
                        </label>
                        <input readonly="readonly" value="${filter.startTimeString}" name="startTime" type="text" class="ui-input hasDatepicker" id="start-cal" style="width: 48px">
                        -
                        <input readonly="readonly" value="${filter.endTimeString}" name="endTime" type="text" class="ui-input hasDatepicker" id="end-cal" style="width: 48px">
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
                            <select class="ui-select js_select js-setcom-as" name="bid" style="width:227px; height: 35px ">
                                <option value="" ${empty filter.bid ? 'selected="selected"':''}>全部品牌</option>
                                <c:forEach items="${brandsInfos}" var="brands">
                                    <option value="${brands.refrenceId}" ${brands.refrenceId == filter.bid ? 'selected="selected"':''}>${brands.brandsName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <!--查询价单 结束-->
                        <div class="inline-block">
                            <input type="submit" value="查询" class="ui_button_myourself"/>
                            <input id="btn-export" type="button" value="导出终端商名单" class="ui_button_myourself"/>
                        </div>
                    </div>
                </form:form>
                <div class="js_agency_tabs">
                    <div class="js_agency_menu">
                        <ul class="clearfix">
                            <li class="selected">
                                <a href="javascript:;">合作中的</a></li>
                            <li>
                                <a href="${ctx}/brand/join/list?state=2">已终止的</a>
                            </li>
                        </ul>
                    </div>
                    <div class="js_agency_con mt10">
                        <div class="agency-teamed">
                            <table class="agency-teamed-table" style="font-size:12px;">
                                <colgroup>
                                    <col width="130"/>
                                    <col width="80"/>
                                    <col width="60"/>
                                    <col width="60"/>
                                    <col width="90"/>
                                    <col width="60"/>
                                    <col width="60"/>
                                    <col width="90"/>
                                    <col width="40"/>
                                    <col width="70"/>
                                    <col width="100"/>
                                </colgroup>
                                <thead>
                                <tr>
                                    <th><span>终端商名及授权时间</span></th>
                                    <th><span>品牌名称</span></th>
                                    <th><span>交易模式</span></th>
                                    <th><span>授信额度</span></th>
                                    <th><span>剩余可用授信</span></th>
                                    <th><span>押金比例</span></th>
                                    <th><span>已缴押金</span></th>
                                    <th><span>待缴/待退押金</span></th>
                                    <th><span>折扣</span></th>
                                    <th><span>进货总金额</span></th>
                                    <th class="teamed-th-tc"><span>操作</span></th>
                                </tr>
                                </thead>
                                <tbody id="tbody">
                                </tbody>
                            </table>
                            <div id="pagination"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<div style="display: none;">
    <!--终止合作-->
    <div class="js-ending-teamboxshow">
        <form:form id="terminate-form" method="post" action="${ctx}/brand/join/terminate">
            <input name="id" type="hidden" class="js-idvalue" value="">
            <input name="brandsId" type="hidden" class="js-brandsId" value="">

            <div class="ui-head">
                <h3>终止合作</h3>
            </div>
            <div>
                <textarea id="endMark" name="endMark" class="ui-textarea mt5" style="width: 216px;" placeholder="请输入终止原因"></textarea>
            </div>
            <div class="ta-c mt5">
                <a href="javascript:;" class="simple_button js-tosure-btn">确认</a>
                <a href="javascript:;" class="simple_button js-tocansole-btn">取消</a>
            </div>
        </form:form>
    </div>
    <!--终止合作结束-->
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
    <!--留言结束-->
</div>

<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script type="text/javascript" src="${src}/brand/agencymanag.js"></script>
<script id="template" type="text/html">
    {{each rows}}
    <tr data-id="">
        <input type="hidden" class="js-idvalue" value="{{$value.refrenceId}}">
        <input type="hidden" class="js-dealerId" value="{{$value.dealerId}}">
        <input type="hidden" class="js-brandsId" value="{{$value.brandsId}}">
        <td>
            <a class="bluefont">{{$value.dealerName}}&nbsp;({{$formatDate $value.startTime}})</a>

            <div>
                <a href="javascript:;" class="bluefont js-leave-message">留言</a>
                <a href="${ctx}/brand/join/dealerInfo/{{$value.refrenceId}}" target="_blank" class="bluefont">查看详细资料</a>
            </div>
        </td>
        <td>{{$value.brandsName}}</td>
        <td>{{$formatName $value.joinFormName}}</td>
        <td>
            {{if $value.creditAmount != undefined }}
                {{if $value.creditAmount == ""}}
                    ---
                {{else}}
                    {{$formatPrice $value.creditAmount}}
                {{/if}}
            {{/if}}
        </td>
        <td>
            {{if $value.availableCurrent != undefined }}
                {{if $value.availableCurrent == ""}}
                    ---
                {{else}}
                    {{$formatPrice $value.availableCurrent}}
                {{/if}}
            {{/if}}
        </td>
        <td>{{$value.creditPaidPercent}} %</td>
        <td>
            {{if $value.paidAmount != undefined }}
                {{if $value.paidAmount == ""}}
                    ---
                {{else}}
                    {{$formatPrice $value.paidAmount}}
                {{/if}}
            {{else}}
                ---
            {{/if}}
        </td>
        <td>
            {{if $value.wantPaid != undefined }}
                {{if $value.wantPaid == ""}}
                    ---
                {{else}}
                    {{$value.wantPaid}}
                {{/if}}
            {{else}}
                ---
            {{/if}}
        </td>
        <td>{{$value.discount*10}}</td>
        <td>
            <div>
                <strong>{{$formatPrice $value.orderMoney}}</strong>
            </div>
            <div>
                <a href="${ctx}/brand/join/dealerDeal/{{$value.refrenceId}}" target="_blank" class="bluefont">进货详细</a>
            </div>
        </td>
        <td class="teamed-td-tc">
            <div>
                <a class="ui_button ui_button_mblue ending-team js-ending-team" dealerJoinId="{{$value.refrenceId}}" style="width: 80px;">终止合作</a>
            </div>
            <div>
                <a href="javascript:;" data-refrenceid="{{$value.refrenceId}}" data-point="{{$value.point?'true':'false'}}"  data-joinform="{{$value.joinForm}}" data-deposittotalamount="{{$value.depositTotalAmount}}" data-creditamount="{{$value.creditAmount}}" data-creditpaidpercent="{{$value.creditPaidPercent}}" data-discount="{{$value.discount}}"   class="ui_button ui_button_mgreen js-edit-deposit" style="width:80px;">设置交易类型</a>
            </div>
            <div>
                {{if $value.wantButton  == "true"}}
                    <a href="javascript:;" data-refrenceid="{{$value.refrenceId}}" data-deposittotalamount="{{$value.depositTotalAmount}}" data-creditamount="{{$value.creditAmount}}" data-paidamount="{{$value.paidAmount}}"  class="ui_button ui_button_morange js-back-deposit" style="margin-bottom: 8px;width:80px;">退还押金</a>
                {{/if}}
            </div>
        </td>
    </tr>
    {{/each}}
    {{ if rows==null||rows.length <= 0 }}
    <tr>
        <td colspan="11" style="text-align:center;">暂无数据</td>
    </tr>
    {{ /if }}
</script>
<script id="depositEditTpl" type="text/html">
    <div class="ui-head">
        <h3>{{title}}</h3>
    </div>
    <form:form id="depositForm" class="ui-form mt15" method="post" action="" data-widget="validator">
        <input name="joinId" type="hidden" value="{{joinId}}">
        <div class="ui-form-item">
            <label class="ui-label" style="font-size:12px;">
                交易模式：
            </label>
            <div class="inline-block" style="margin-top: 6px;">
                <input type="radio" class="ui-radio" name="joinForm" value="1" onclick="changeModel(this)" id="credit" {{creditCheck}} autocomplete="off"/><label for="credit"> 授信</label>
                <input type="radio" class="ui-radio" name="joinForm" value="0" onclick="changeModel(this)" id="cash" {{cashCheck}} autocomplete="off"/><label for="cash"> 现款现货</label>
            </div>
        </div>
        <div style="{{style}}" id="creditDiv">
            <div style="border-top: 1px solid #DDD;border-bottom: 1px solid #DDD;padding-top: 15px;margin-bottom: 15px;">
                <div class="ui-form-item">
                    <label class="ui-label" style="font-size:12px;">
                        授信额度：
                    </label>
                    <input type="text" class="ui-input" id="creditAmount" name="creditAmount"
                           value="{{$formatPrice creditAmount}}" onchange="seteposit()" maxlength="9" style="width: 176px;" data-display="授信额度"  autocomplete="off"/>元
                </div>
                <div class="ui-form-item">
                    <label class="ui-label" style="font-size:12px;">
                        押金比例：
                    </label>
                    <div class="inline-block" id="creditPaidPercent" style="margin-top: 6px;">{{creditPaidPercent}}</div>
                </div>
                <div class="ui-form-item">
                    <label class="ui-label" style="font-size:12px;">
                        押金金额：
                    </label>
                    <input type="text" class="ui-input" name="depositTotalAmount" id="depositTotalAmount"
                           value="{{$formatPrice depositTotalAmount}}" onchange="seteposit()" required data-display="押金"  data-rule="isamount" maxlength="9" style="width: 176px;" autocomplete="off" />元
                </div>
            </div>
        </div>
        <div class="ui-form-item">
            <label class="ui-label" style="font-size:12px;">
                用户折扣：
            </label>
            <input type="text" class="ui-input" name="discount" id="discount" value="{{discount}}" required data-display="折扣" style="width: 176px;" autocomplete="off" />折
        </div>
        <div class="ui-form-item">
            <label class="ui-label" style="font-size:12px;">
                支持返点：
            </label>
            <div class="inline-block mt5">
                <label>
                    <input type="radio" name="point" value="true" class="ui-radio" {{if point==true}} checked {{/if}} autocomplete="off" /> 支持
                </label>
                <label>
                    <input type="radio" name="point" value="false" class="ui-radio" {{if point==false}} checked {{/if}} autocomplete="off" /> 不支持
                </label>
            </div>
        </div>
        <div class="ta-c mt10">
            <button type="submit" class="simple_button">确认</button>
            <button type="button" href="javascript:;" class="simple_button ml10 js-depositcansole-btn">取消</button>
        </div>
    </form:form>
</script>
<script id="beforeEndTpl" type="text/html">
    <div class="ui-head">
        <h3>终止合作</h3>
    </div>
    <form class="ui-form" id="beforeEndForm" method="post" action="" data-widget="validator">
        <input name="dealerJoinId" type="hidden" value="{{id}}">
        <p style="padding: 10px 40px;">终端商已缴纳押金 <strong>{{max}}</strong> 元，如需终止合作，需将押金退还至终端商</p>
        <div class="ui-form-item">
            <label class="ui-label" style="font-size:12px;">
                退还押金:
            </label>
            <input class="ui-input js-price" name="amount" required data-display="退还押金" data-rule="min{min:'0.01'} max{max:'{{max}}'}" />
        </div>
        <div class="ui-form-item">
            <label class="ui-label" style="font-size:12px;">
                支付密码:
            </label>
            <input class="ui-input" id="payPwd" name="payPwd" type="password" required data-display="支付密码" data-widget-cid="widget-8" data-explain="">
        </div>
        <div class="ui-form-item">
            <label class="ui-label" style="font-size:12px;">
                终止原因:
            </label>
            <textarea id="desc" name="desc" class="ui-textarea mt5" required data-display="终止原因" style="width: 132px;"></textarea>
        </div>
        <div class="ta-c mt10 operate">
            <button type="submit" class="simple_button">立即退还押金</button>
            <button type="button" class="simple_button ml10 js-beforeEndcansole-btn">取消</button>
        </div>
    </form>
</script>
<script id="depositBackTpl" type="text/html">
    <div class="ui-head">
        <h3>退押金</h3>
    </div>
    <form:form id="depositBackForm" class="ui-form mt15" method="post" action="" data-widget="validator">
        <input name="dealerJoinId" type="hidden" value="{{joinId}}">
        <div class="ui-form-item">
                押金金额为{{deposittotalamount}}元，终端商已缴押金{{paidamount}}元，
                <br>可退还<font color="red"> {{backmount}}</font> 元至终端商账户。
        </div>
        <div class="ui-form-item">
            <label class="ui-label" style="font-size:12px;">
                退还押金:
            </label>
            <input class="ui-input js-price" name="amount" required data-display="退还押金" data-rule="min{min:'0.01'} max{max:'{{max}}'}" />
        </div>
        <div class="ui-form-item">
            <label class="ui-label" style="font-size:12px;">
                支付密码:
            </label>
            <input class="ui-input" id="payPwd" name="payPwd" type="password" required data-display="支付密码" data-widget-cid="widget-8" data-explain="">
        </div>
        <div class="ta-c mt10">
            <button type="submit" class="simple_button">立即退还</button>
            <button type="button" href="javascript:;" class="simple_button ml10 js-depositBackCansole-btn">取消</button>
        </div>
    </form:form>
</script>
<script type="text/javascript">
    var $msgForm = $('#msg-form');
    var $msgContent = $('#msg-content');
    var $terminateForm = $('#terminate-form');
    var $endMark = $('#endMark');

    // 品牌-终端商等级二级联动
    var $selectBrand = $('select[name="bid"]');
    var $selectLevel = $('select[name="dealerLevel"]');

    $selectBrand.change(function (event) {
        var brandsId = $selectBrand.val();
        if (brandsId == '') {
            $selectLevel.empty().append('<option value="">全部终端商等级</option> ').prop('disabled', true);
        } else {
            $selectLevel.empty().append($('#' + brandsId + '-lvls').html()).prop('disabled', false);
        }
        renderSelect('select[name="dealerLevel"]');
    });

    //交易模式   变更
    function changeModel(obj){
        if(obj.value==1){//现款
            $("#creditDiv").show();
        }else{//授信
            $("#creditDiv").hide();
        }
    }

    //计算押金比例
    function seteposit(){
        var depositAmount = $("#depositTotalAmount").val();
        var creditAmount = $("#creditAmount").val();
        if(creditAmount !=null &&creditAmount !="" && (creditAmount*1) > 0 && depositAmount != null && depositAmount != "" && isFinite(depositAmount) && isFinite(creditAmount)){
            $("#creditPaidPercent").html(((depositAmount/creditAmount)*100).toFixed(2)+" %<input type='hidden' name='creditPaidPercent' value='"+((depositAmount/creditAmount)*100).toFixed(2)+"' id='creditPaidPercent'> ");
        }else{
            $("#creditPaidPercent").html("0.00%<input type='hidden' name='creditPaidPercent' value='0.00' id='creditPaidPercent'>");
        }
    }
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

    function stopTemd(obj, dialog) {//留言弹窗点击确定后的操作可以写在这里
        var endMark = $.trim($endMark.val());
        if (endMark == '') {
            remind('error', '请填写终止原因!');
            return false;
        }
        $.ajax('${ctx}/brand/join/terminate', {
            method: 'post',
            data: $terminateForm.serialize(),
            dataType: 'json',
            success: function (data, status, jqXHR) {
                if (data.code === 126000) {
                    window.location.reload();
                }
            }
        });
    }

    // 获取tr里面的value赋给终止合同
    $(".agency-teamed-table").on("click", ".js-ending-team", function () {
//        var val = $(this).parents("tr").find(".js-idvalue").val();
        var dealerId = $(this).parents("tr").find(".js-dealerId").val();
        var brandsId = $(this).parents("tr").find(".js-brandsId").val();
        $(".js-ending-teamboxshow .js-idvalue").val(dealerId);
        $(".js-ending-teamboxshow .js-brandsId").val(brandsId);
    });


    // 获取tr里面的value赋给弹出层
    $(".agency-teamed-table").on("click", ".js-leave-message", function () {
        var val = $(this).parents("tr").find(".js-dealerId").val();
        $(".js-leave-messagebox .js-getvalue").val(val);
    });

    $(document).on('click', '#btn-export', function (event) {
        event.stopPropagation();
        var params = $('#search-form').serialize();
        window.location.href = "${ctx}/brand/join/list.xls?" + params;
    });

    agencyteamed.init();

    function scrollArea(){
        seajs.use("jscroll",function(){
            $(".scrollArea").jscrollbar({
                width: "5"
            });
        })
    }

    scrollArea();
    seajs.use(['pagination', 'template', 'moment', 'dialog'], function (Pagination, template, moment, Dialog) {
        template.helper('$formatDate', function (millsec) {
            return moment(millsec).format("YYYY-MM-DD HH:mm:ss");
        });
        template.helper('$formatPrice', function (price) {
            if(isNaN(price)){
                return price;
            }else{
                return parseFloat(price).toFixed(2);
            }
        });
        template.helper('$formatName',function(name){
        	var result = name.split("<br/>");
        	if(result.length==2){
        		return result[0] + '<br/>' + result[1];
        	}else{
        		return name;
        	}
        	
        });

        var $tbody = $('#tbody');
        var pagination = new Pagination({
            url: '${ctx}/brand/join/list.json',
            form: '#search-form',
            <%--currentPage: ${pageResult.page.currentPage},--%>
            <%--total: ${pageResult.page.totalPage},--%>
            elem: '#pagination',
            handleData: function (data) {
                //console.log(data);
                var html = template.render('template', data);
                $tbody.empty().append(html);
                scrollArea();
            }
        });

        //押金编辑
        var depositDialog = new Dialog({
            content: $("#depositEditTpl").html(),
            width: 400
        }).after("render",function(){
                    baseFormValidator({
                        selector: "#depositForm",
                        isAjax: true,
                        addElemFn:function(Core, Validator){

                            Validator.addRule("isamount", function(options) {
                                var element = options.element;
                                return isAmount(element.val());
                            }, "押金格式错误");
                        },
                        beforeSubmitFn:function(){
                            $.ajax({
                                url: '${ctx}/brand/join/setDeposit',
                                method: 'post',
                                data: $("#depositForm").serialize(),
                                dataType: 'json',
                                success: function (data) {
                                    if (data.code === 126000) {
                                        window.location.reload();
                                    }else{
                                        remind("error",data.message);
                                        depositDialog.hide();
                                    }
                                }
                            });
                        }
                    });
                });
        $("#tbody").on("click",".js-edit-deposit",function(){
            var editObj = {};
            editObj.joinId = $(this).data("refrenceid");
            editObj.dealername = $(this).data("dealername");
            editObj.point=$(this).data("point");

            var joinform = $(this).data("joinform");
            if(joinform=="0"){
                editObj.style = "display:none;";
                editObj.creditCheck="";
                editObj.cashCheck="checked";
            }else{
                editObj.style = "display:block;";
                editObj.creditCheck="checked";
                editObj.cashCheck="";
            }

            editObj.title = "设置交易类型";
            editObj.creditAmount = $.trim($(this).data("creditamount")) != "" ? $(this).data("creditamount") : 0.00;
            editObj.depositTotalAmount = $.trim($(this).data("deposittotalamount")) != "" ? $(this).data("deposittotalamount") : 0.00;
            editObj.discount = $.trim($(this).data("discount")) != "" ? $(this).data("discount")*10 : 0.00;


            var html = template.render("depositEditTpl",editObj);
            depositDialog.set("content", html);
            /*depositDialog.element.html(html);*/
            depositDialog.show();

            $.trim($(this).data("creditpaidpercent")) != ""
                    ?
                    $("#creditPaidPercent").html($(this).data("creditpaidpercent")+"%<input type='hidden' name='creditPaidPercent' value='"+$(this).data("creditpaidpercent")+"' id='creditPaidPercent'>")
                    : $("#creditPaidPercent").html("0.00%");



        });

        //取消
        $(document).on("click",".js-depositcansole-btn",function(){
            depositDialog.hide();
        });



        //退押金窗口
        var depositBackDialog = new Dialog({
            content: $("#depositBackTpl").html(),
            width: 430
        }).after("render",function(){
            baseFormValidator({
                selector: "#depositBackForm",
                isAjax: true,
                beforeSubmitFn:function(){
                    dialogLoading(function(_d){
                        $.ajax({
                            url: '/brand/payApi/depositTransferNotStop',
                            method: 'post',
                            data: $("#depositBackForm").serialize(),
                            dataType: 'json',
                            success: function (data) {
                                if (data.code === zttx.SUCCESS) {
                                    depositBackDialog.hide();
                                    window.location.reload();
                                }else{
                                    remind("error",data.message);
                                    depositBackDialog.hide();
                                }
                                _d.hide();
                            }
                        });
                    }, "系统处理中...");
                }
            });
        });


        //取消退押金窗口
        $(document).on("click",".js-depositBackCansole-btn",function(){
            depositBackDialog.hide();
        });

        //退押金
        $("#tbody").on("click",".js-back-deposit",function() {

            var editObj = {};
            editObj.dealerid = $(this).data("dealerid");
            editObj.deposittotalamount = $.trim($(this).data("deposittotalamount")) != "" ? $(this).data("deposittotalamount") : 0.00;
            editObj.paidamount = $.trim($(this).data("paidamount")) != "" ? $(this).data("paidamount") : 0.00;
            editObj.backmount = editObj.paidamount-editObj.deposittotalamount;
            editObj.max = editObj.backmount;
            editObj.joinId = $(this).data("refrenceid");
            var html = template.render("depositBackTpl",editObj);

            depositBackDialog.set("content", html);
            depositBackDialog.show();

        });

    });
</script>
</body>
</html>
