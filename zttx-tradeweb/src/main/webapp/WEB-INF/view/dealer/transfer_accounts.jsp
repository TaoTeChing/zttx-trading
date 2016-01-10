<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-转账</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/alreas.css" rel="stylesheet" />
    <style>
        .transfer-table{width: 100%;border: 1px solid #eee;}
        .transfer-table td,th{
            padding: 8px;
            font-size: 14px;
            font-family: "microsoft yahei";
        }
        .transfer-table td{
            border-bottom: #eee solid 1px;
            border-right: #eee solid 1px;
            line-height: 24px;
            font-size: 12px;
        }
        .transfer-table th{
            background: #f7e8f7;
            font-weight: normal;
            color: #333;
            text-align: left;
        }
        .transfer-table tr.selected{background: #e1edf7;}
    </style>
</head>
<body>
    <!--完成-->
    <div class="container">
        <jsp:include page="/common/menuInfo/mainmenu"/> 
        <div class="em100">
            <div class="main clearfix pr">
                <%--<!--侧边导航-->--%>
                <jsp:include page="/common/menuInfo/sidemenu"/>
                <!--主体内容-->
                <div class="main-right">
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <span class="bb">转账</span>
                            <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="main-grid mb10">
                        <form:form id="transfer-form" class="ui-form transfer-form mt15" style="margin-left: 55px;" action="${ctx}/brand/payApi/transfer" method="post" data-widget="validator">
			                <div class="ui-form-item" style="padding-top: 20px;">
			                    <label class="ui-label">
			                        出款方公司名称：
			                    </label>
			                    <input class="ui-input" type="text" disabled value="${dealerInfo.dealerName }" />
			                </div>
			                <div class="ui-form-item">
			                    <label class="ui-label">
			                        可用账户余额：
			                    </label>
			                    <input class="ui-input" type="text" disabled value="${balance }" /> 元
			                </div>
			                <div class="ui-form-item">
			                    <label class="ui-label">
			                        收款方名称：
			                        <span class="ui-form-required">*</span>
			                    </label>
			                    <input id="gathering_id" name="toUserId" class="ui-input" type="hidden"/>
			                    <input id="toUserMobile" name="toUserMobile" class="ui-input" type="hidden"/>
			                    <input id="dealerJoinId" name="dealerJoinId" class="ui-input" type="hidden"/>
			                    <input id="gathering_name" name="toUserName" class="ui-input" type="text" readonly="readonly" required data-display="收款方名称" />
			                    <input id="select_gathering" class="ui-button ui-button-lblue" type="button" value="选择" />
			                </div>
			                <div class="ui-form-item">
			                    <label class="ui-label">
			                        转账金额：
			                        <span class="ui-form-required">*</span>
			                    </label>
			                    <input id="gathering_price" name="amount" class="ui-input js-price" maxlength="8" type="text" required data-display="转账金额" data-rule="min{min:0.01} max{max:${balance }}" /> 元 <span style="color: #999;">（转账金额不得超出账户余额）</span>
			                </div>
			                <div class="ui-form-item">
			                    <label class="ui-label">
			                        转账说明：
			                    </label>
			                    <textarea name="title" id="title" class="ui-textarea" style="width: 669px; height: 120px;"></textarea>
			                </div>
			                <div class="ui-form-item">
			                    <label class="ui-label">
			                        支付密码：
			                        <span class="ui-form-required">*</span>
			                    </label>
			                    <input class="ui-input" id="payPwd" name="payPwd" type="password" required data-display="支付密码" />
			                </div>
			                <div class="ui-form-item">
			                    <button class="ui-button ui-button-lblue" type="submit">确定转账</button>
			                </div>
			            </form:form>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp" />
    </div>

    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
	<script id="transfer_dealer_d" type="text/html">
	<div class="ui-head">
	        <h3>选择品牌商</h3>
	    </div>
	    <div class="confirm_bd" style="padding: 10px;">
	        <form id="searchTermForm" action="" style="padding-bottom: 10px;">
	            <div class="inline-block">
	                <label>品牌商名称：</label>
	                <input type="text" name="comName" class="ui-input"  />
	            </div>
	            <div class="inline-block">
	                <label>品牌名称：</label>
	                <input type="text" name="brandsName" class="ui-input" />
	            </div>
	            <div class="inline-block">
	                <button id="transfer_dealer_filter" class="ui-button ui-button-lblue" type="button">搜索</button>
	            </div>
	        </form>
	        <table class="transfer-table">
	            <thead>
	                <tr>
	                    <th>品牌商名称</th>
	                    <th>品牌名称</th>
	                    <th>联系人名称</th>
	                </tr>
	            </thead>
	            <tbody id="transfer_dealer_list">
	            </tbody>
	        </table>
	        <div id="pagination" class="pagination mt10"></div>
	        <div class="operate"><a class="simple_button confirm_btn" id="transfer_confirm" href="javascript:;">确定</a><a class="simple_button cancel_btn" id="transfer_cancel" style="" href="javascript:;">取消</a></div>
	    </div>
	</script>
	<script id="transfer_dealer_tr" type="text/html">
	{{each rows}}
	<tr data-id="{{$value.brandId}}" data-name="{{$value.comName}}" data-mobile="{{$value.userMobile}}" data-dealerjoinid="{{$value.dealerJoinId}}">
		 <td>{{$value.comName}}</td>
		 <td>{{$value.brandsName}}</td>
		 <td>{{$value.userMobile}}</td>
	</tr>
	{{/each}}
	</script>
	<script>
	    $(function(){
	        seajs.use(["dialog", "template", "pagination"],function(Dialog, Template, Pagination){
	            /*表单校验*/
	            baseFormValidator({
	                selector:"#transfer-form",
	                isAjax: true,
	                beforeSubmitFn: function(){
	                    var name = $("#gathering_name").val();
	                    var price = $("#gathering_price").val();
	                    confirmDialog("是否确认向“" + name + "”转账" + price + "元",function(){
	                        $.ajax({
	                            url: "${ctx}/dealer/payApi/transfer",
	                            method: "post",
	                            data: $("#transfer-form").serialize(),
	                            dataType: "json",
	                            success: function(jsonMessage){
	                            	if (jsonMessage.code == zttx.SUCCESS) {
	                                    remind("success", "转账成功", function () {
	                                        window.location.reload();
	                                    });
	                                } else {
	                                    remind("error", jsonMessage.message);
	                                }
	                            }
	                        });
	                    });
	                }
	            });
	            //选择终端商弹窗逻辑
	            var gathering_temp = false, d, page;
	            d = new Dialog({
	                content: $("#transfer_dealer_d").html(),
	                width: 700
	            }).show().hide();
	            page = new Pagination({
	                url: "${ctx}/payApi/listDealerJoin",
	                elem: "#pagination",
	                form:$("#searchTermForm"),
	                pageSize:10,
	                method:"post",
	                handleData: function (json) {
	                    var html = template.render("transfer_dealer_tr", json);
	                    $("#transfer_dealer_list").html(html);
	                    if(gathering_temp === true){
	                        d.show();
	                    }
	                }
	            });
	            //选择
	            $("#select_gathering").click(function(){
	                gathering_temp = true;
	                page.goTo(1);
	            });
	            //筛选
	            $(document).on("click", ".ui-dialog #transfer_dealer_filter", function(){
	                page.goTo(1);
	            });
	            $(document).on("click", "#transfer_dealer_list tr", function(){
	                $("#transfer_dealer_list tr").removeClass("selected");
	                $(this).addClass("selected");
	            });
	            //确定
	            $(document).on("click", "#transfer_confirm", function(){
	                var id = $("#transfer_dealer_list tr.selected").data("id");
	                var name =  $("#transfer_dealer_list tr.selected").data("name");
	                var mobile =  $("#transfer_dealer_list tr.selected").data("mobile");
	                var dealerJoinId = $("#transfer_dealer_list tr.selected").data("dealerjoinid");
	                if(!id){
	                	remind("error","请选择品牌商");
	                }else{
		                $("#gathering_name").val(name);
		                $("#gathering_id").val(id);
		                $("#toUserMobile").val(mobile);
		                $("#dealerJoinId").val(dealerJoinId);
		                d.hide();
	                }
	            });
	            //取消
	            $(document).on("click", "#transfer_cancel", function(){
	                d.hide();
	            });
	        });
	    });
	</script>
</body>
</html>
