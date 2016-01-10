<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-加盟管理-已加盟品牌库</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/brandjoin.css" rel="stylesheet" />
    <style>
        .ui-form{*height:52px;}
        .ui-form .ui-form-item{padding: 0;}
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
    <div class="em100">
        <div class="main clearfix pr">
            <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
            <div class="main-right">
                <!--面包屑-->
                <div class="main-grid mb10">
                    <div class="panel-crumbs">
                        <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="${ctx}/dealer/copartner/brandes" title="">加盟管理</a> > <span class="bb">已加盟品牌库</span>
                        <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <div class="inner">
                    <div class="panel-joined" style="">
                        <form class="ui-form" id="search-form" action="${ctx}/dealer/dealerJoinManage/copartner/brandes/data" method="post">
                            <div class="ui-form-item inline-block">
                                <label>
                                    公司名称：
                                </label>
                                <input type="text" class="ui-input" name="brandName" value="${dealerJoinView.brandName}">
                            </div>
                            <div class="ui-form-item inline-block">
                                <label>
                                    品牌名称：
                                </label>
                                <input type="text" class="ui-input" name="brandsName" value="${dealerJoinView.brandsName}">
                            </div>
                            <div class="ui-form-item inline-block">
                                <input type="button" class="ui-button ui-button-lwhite" value="搜索" onclick="doCopartnerSearch();" name="">
                                <input type="button" class="ui-button ui-button-lwhite" value="导出EXCEL" id="btn-export" >
                            </div>
                        </form>
                        <div class="mt10 ">
                        <div class="mt10" id="pagination"></div>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<script id="feedback-templage" type="text/html">
    <table  id="tempTable" class="ui-table">
        <colgroup>
            <col width="130"/>
            <col width="110"/>
            <col width="100"/>
            <col width="70"/>
            <col width="70"/>
            <col width="70"/>
            <col width="60"/>
            <col width="70"/>
            <col width="100"/>
            <col width="70"/>
            <col width="110"/>
        </colgroup>
        <thead>
        <tr>
            <th>品牌</th>
            <th>公司名称</th>
            <th>合作开始时间</th>
            <th>合作模式</th>
            <th>授信额度</th>
            <th>剩余授信</th>
            <th>押金</th>
            <th>已缴押金</th>
            <th>待缴/待退押金</th>
            <th>进货累计</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>

        {{each rows}}
        <tr>
            <td class="ta-c">
                <a target="_blank" href='http://{{$value.domain}}${zttx}'>{{$value.brandsName}}</a>
            </td>
            <td class="ta-c">{{$value.brandName}}</td>
            <td class="ta-c"> {{$value.startTimeStr}} </td>
            <td class="ta-c"> {{$value.joinFormCn}} </td>
            <td class="ta-c"> {{$value.creditAmount}} </td>
            <td class="ta-c"> {{$value.wantCurrent}}</td>
            <td class="ta-c"> {{$value.depositTotalAmount}} </td>
            <td class="ta-c"> {{$value.paidAmount}} </td>
            <td class="ta-c"> {{$value.wantMoney}} </td>
            <td class="ta-c"> {{$value.orderTime}} </td>
            <td class="ta-c">
                {{ if $value.joinForm==1&&($value.paidAmount==null || $value.paidAmount< $value.depositTotalAmount) }}
                <a class="ui-button ui-button-mred js-pay-deposit" href="javascript:;" id="paydeposit" data-tot="{{$value.depositTotalAmount}}"
                   data-id="{{$value.refrenceId}}" data-pa="{{$value.paidAmount}}" data-name="{{$value.brandsName}}"
                   data-money="{{$value.wantMoney}}" style="width:75px;padding: 0;">缴纳押金</a>
                {{ else}}
                <a class="ui-button ui-button-mred" style="width:75px;padding: 0;" target="_blank" href='http://{{$value.domain}}${zttx}'>去进货</a>
                {{ /if}}
            </td>
        </tr>
        {{/each}}
        {{ if rows==null||rows.length == 0 }}
        <tr>
            <td colspan="11">暂无加盟品牌</td>
        </tr>
        {{ /if }}
        </tbody>
    </table>
</script>
<script id="payDepositTpl" type="text/html">
    <div class="ui-head">
        <h3>缴纳押金</h3>
    </div>
    <div class="ta-c mt10 ml10 mr10">
        品牌：<span>{{name}}</span>，
        押金：<span>{{tot}}</span>，
        已缴纳：<span>{{paidAmount}}</span>，
        还需缴纳押金：<span>{{money}}</span>
    </div>
    <div class="ta-c mt10">
        <a class="ui-button ui-button-morange pay_deposit_btn" href="javascript:;" data-id="{{id}}" data-price="{{money}}">立即缴纳</a>
        <a class="ui-button ui-button-mwhite ml5 pay_cancel_btn" href="javascript:;">稍后缴纳</a>
    </div>
</script>

<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
<script>

    function doCopartnerSearch() {
        g_pagination.goTo(1);
    }

    $(function(){

        $("a[name=stopButton]").click(function(){
            var uuid = $(this).data("uuid");
            confirmDialog("确定终止合同吗？",function(){
                $.post("/dealer/joined/stop",{brandsId:uuid},function(data){
                    if(data.code == 126000){
                        $("#pageForm").submit();
                    }else{
                        remind("error",data.message);
                    }
                },"json");
            });
        });

        $(document).on('click', '#btn-export', function (event) {
            var params = $('#search-form').serialize();
            window.location.href = "${ctx}/dealer/dealerJoinManage/copartner/brandes/list.xls?" + params;
        });

        seajs.use(["dialog","pagination", "template"],function(Dialog,Pagination, Template){
            var payDeposit = new Dialog({
                width:300
            });
            $(".panel-joined").on("click",".js-pay-deposit",function(){
                var name = $(this).data("name"), money = $(this).data("money"), paidAmount = $(this).data("pa"), tot = $(this).data("tot"),id = $(this).data("id");

                var data = {name:name,tot:tot,paidAmount:paidAmount,money:money,id:id};
//                    var data = {name:name,tot:tot,paidAmount:paidAmount,money:money,id:id};
                payDeposit.before("show",function(){
                    var html = Template.render("payDepositTpl",data);
                    this.element.html(html);
                });
                payDeposit.show();
            });
            $(document).on("click",".pay_deposit_btn",function(){

                var refrenceId = $(this).data("id");
                dialogLoading(function(_d){
                   location.href = "${ctx}/dealer/join/redirect?orderIdArr="+refrenceId+"&orderType="+3;
                }, "系统处理中...");
            });
            $(document).on("click",".pay_cancel_btn",function(){
                payDeposit.hide();
            });


            window['g_pagination'] = new Pagination({
                url: "${ctx}/dealer/dealerJoinManage/copartner/brandes/data",
                elem: "#pagination",
                form:$("#search-form"),
                method:"post",
                handleData: function (data) {
                    var html = template.render("feedback-templage", data);
                    $("#tempTable").remove();
                    $("#pagination").before($(html));
                }
            });
        });


    });
</script>

</body>
</html>