<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>

<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>管理中心-终端商管理-终端商详情</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/agencymanag.css"/>
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
            <span class="current">终端商详情</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" >
                <jsp:param value="0" name="isShow"/>
            </jsp:include>
        </div>
    </div>
<div class="inner">
<!-- 内容都放这个地方  -->
<!-- 终端商详情 -->
<div class="agency-teamed-info-contant">
    <div class="teamed-info-title">
        <h1 class="title inline-block">${dealerJoin.dealerName}</h1>
        <span class="account inline-block">账号（${dealerUserm.userMobile}）</span>
        <span class="account inline-block">授权品牌：${brandesInfo.brandsName}</span>
        <a javascript=":;" class="export simple_button js-leave-message">留言站内信</a>
    </div>
    <div class="js_teamedinfo_tabs">
        <div class="js_teamedinfo_menu clearfix">
            <ul class="clearfix
            ">
                <li><a href="${ctx}/brand/join/dealerInfo/${joinId}">基本资料</a></li>
                <li><a href="${ctx}/brand/join/dealerLevel/${joinId}">等级授权</a></li>
                <li class="selected"><a href="${ctx}/brand/join/dealerDeal/${joinId}">进货明细</a></li>
            </ul>
        </div>
        <div class="js_teamedinfo_con np">
            <div class="agency-info-recom"><!--进货明细-->
                <table class="teamed-info-table mt10">
                    <colgroup>
                        <col width="204" />
                        <col width="100" />
                        <col width="215" />
                        <col width="110" />
                        <col width="110" />
                        <col width="100" />
                        <col width="100" />
                    </colgroup>
                    <thead>
                        <tr>
                            <th>订单编号</th>
                            <th>进货时间</th>
                            <th>采购数/实发数/收货数</th>
                            <th>采购金额（元）</th>
                            <th>最终金额（元）</th>
                            <th>欠款（元）</th>
                            <th>状态</th>
                        </tr>
                    </thead>
                    <tbody></tbody>
                </table>
                <div class="pagination">
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>
	<div style="display: none;">
    <!--留言-->
    <div class="js-leave-messagebox">
        <div class="ui-head">
            <h3>留言</h3>
        </div>
        <form:form id="msg-form" method="post" action="${ctx}/brand/message/sendDealer">
            <input name="dealerIds" type="hidden" class="js-getvalue" value="${dealerJoin.dealerId}">
            <div class="content">
                <input id="msg-title" name="title" type="text" placeholder="留言" class="ui-input mt10" style="width: 200px"/>
                <textarea id="msg-content" name="content" class="ui-textarea mt10" style="width:335px;height: 78px;" placeholder="请输入留言内容"></textarea>
            </div>
            <div class="ta-c mt10">
                <a href="javascript:;" class="simple_button js-lemsgsure-btn">确认</a>
                <a href="javascript:;" class="simple_button js-lemsgsurecansole-btn">取消</a>
            </div>
        </form:form>
    </div>
    <!--留言结束-->
	</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/agencymanag.js"></script>
<script src="${src}/plugin/template-simple.js" type="text/javascript"></script>
<script src="${src}/plugin/jquery-dateFormat.min.js" type="text/javascript"></script>
<script class="ajax-templage" _url="${ctx}/brand/join/proDetList/${joinId}" _page=".pagination" _pid=".teamed-info-table tbody" type="text/html">
{{each rows}}
<tr>
	<td>
		<a href="${ctx}/brand/order/view/{{$value.refrenceId}}">{{$value.orderId}}</a>
	</td>
	<td>{{$formatDate $value.createTime}}</td>
	<td>{{$value.productCount}}件/{{$value.shipCount}}件/{{$value.receiveCount}}件</td>
	<td>{{$formatMoney $value.productPrice}}</td>
	<td>{{$formatMoney $value.sumMoney}}</td>
	<td>
		{{if $value.orderStatus == 1}}
		
		{{else}}
			<span {{if $value.debtMoney>0}}class="hongfont"{{/if}}>
				<strong>{{$formatMoney $value.debtMoney}}</strong>
			</span>
		{{/if}}
	</td>
	<td>
		{{if $value.orderStatus == 1}}
			<span>等待付款</span>
		{{else if $value.orderStatus == 2}}
			<span>等待发货</span>
		{{else if $value.orderStatus == 3}}
			<span>部分发货</span>
		{{else if $value.orderStatus == 4}}
			<span>等待确认收货</span>
		{{else if $value.orderStatus == 9}}
			<span>交易成功</span>
		{{else if $value.orderStatus == 10}}
			<span>交易关闭</span>
		{{/if}}
	</td>
</tr>
{{/each}}
{{ if rows.length <= 0 }}
<tr>
	<td colspan="7" style="text-align: center;">暂无数据</td>
</tr>
{{ /if }}
</script>
<script>
agencyteamedinfo.init();

var $msgForm = $('#msg-form');
var $msgContent = $('#msg-content');

function leaMsg(obj, dialog) {//留言弹窗点击确定后的操作可以写在这里
    //点击”确定“后出发的方法
    var content = $.trim($msgContent.val());
    if (content == '') {
        remind('error', '请输入内容!');
        return false;
    }
    var title = $.trim($('#msg-title').val());
    if (title == '') {
    	$('#msg-title').val("留言");
    }
    $.post('${ctx}/brand/message/sendDealer', $msgForm.serialize(), function (data, status, jqXHR) {
        if (data.code === zttx.SUCCESS) {
            remind('success', '留言成功');
            $msgForm[0].reset();
            dialog.hide();
        } else {
            remind('error', data.message);
        }
    }, 'json');
}

var pageList = {};
$(function(){
	seajs.use(["pagination"], function (Pagination){
	
		template.helper('$formatMoney', function (num) {
			num = num == null ? 0 : num;
	        return num.toFixed(2);
	    });
		
		template.helper('$formatDate', function (millsec) {
	        return $.format.date(new Date(millsec), 'yyyy-MM-dd');
	    });
		
		$(".ajax-templage").each(function(index){
			var $this = $(this);
			var tempId = $this.attr("id");
			if(tempId == null || tempId == ""){
				tempId = "templage" + index;
				$this.attr("id", tempId);
			}
			pageList[tempId] = new Pagination({
	            url: $this.attr("_url"),
	            elem: $this.attr("_page"),
	            method: "post",
	            handleData: function (data) {
	            	var html = template.render(tempId, data);
	                $($this.attr("_pid")).html(html);
	            }
	        });    
		});
	});
});
</script>
<!--
    另外加载当前页面需要的js路径，或者使用
    seajs.use("./xxx.js")
 -->
</body>
</html>