<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-财务管理-收入交易</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/finance.css" rel="stylesheet" />
</head>
<body>
    <!--完成-->
    <div class="container">
       <jsp:include page="${ctx}/dealer/mainmenu">
       <jsp:param value="0" name="isShow"/>
       </jsp:include>
        <div class="em100">
            <div class="main clearfix pr">
                
                 <jsp:include page="${ctx}/dealer/dealermenu">
					<jsp:param value="8" name="openId"/>
				</jsp:include>
                <div class="main-right">
                    
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="${ctx }/dealer/finance" >财务管理</a> > <span class="bb">收入交易</span>
                            <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="inner">
                        <div class="panel-account clearfix">
                            <dl class="mb10">
                                <dt class="yahei fs16 fl lh2">当前账户余额:</dt>
                                <dd>
                                    <ul>
                                        <li><span class="fs18 bb yahei c-r lh2">${empty dealerBalance.balance?0.00: dealerBalance.balance}元</span></li>
                                        <li class="mb40">
                                            <span class="lh2">
                                            当前可用余额:<span id="id_accountBalance">${empty dealerBalance.accountBalance?0.00: dealerBalance.accountBalance }</span>元 ( 
                                            <span  class="freeze_cash" style="color: #0082CC;">
	                                            冻结金额:<span id="id_frozeMoney">${empty dealerBalance.frozeMoney?0.00: dealerBalance.frozeMoney}</span>元</a> )</span>
	                                       <input type="hidden" id="id_amount" value="${amount}" />
                                            </span>
                                        </li>
                                        <li>
                                             <a href="${ctx}/dealer/finance/charge" target="_blank" class="ui-button ui-button-mred">充 值</a>
                                            <a href="${ctx}/dealer/finance/drawing" class="ui-button ui-button-mred">提 现</a>
                                        </li>
                                    </ul>
                                </dd>
                            </dl>
                        </div>
                        <div class="panel-tabs">
                            <div class="panel-head">
                                <ul>
                                    <a href="${ctx}/dealer/finance" class="yahei fs14"><li>支出交易 </li></a>
                                    <a href="${ctx}/dealer/finance/income" class="yahei fs14"><li class="on">收入交易</li></a>
                                    <a href="${ctx}/dealer/finance/history" class="yahei fs14"><li >交易记录</li></a>
                                    <a href="${ctx}/dealer/finance/drawinghis" class="yahei fs14"><li>提现记录</li></a>
                                    <a href="${ctx}/dealer/finance/bankcard" class="yahei fs14"><li>银行卡设置</li></a>
                                    <a href="${ctx}/dealer/finance/drawing" class="yahei fs14"><li >提现</li></a>
                                </ul>
                            </div>
                            <div class="panel-content">
                                <div class="tab-item">
                                    <div class="panel-table">
                                       <form:form class="ui-form Detail-form clearfix" id="ui_form">
                                       <input type="hidden" value="${styleNum}" id="hidd"/>
                                        <div class="panel-table-title clearfix">
                                                <div class="ui-form-item">
                                                    <label class="">时间：</label>
                                                   	 <input id="start-cal" class="start_time ui-input date" type="text" name="start"/>
                    								 - 
                     								<input id="end-cal" class="end_time ui-input date"  type="text" name="end"/>
                                                   </div>
                                                <c:if test="${styleNum!=3}">
                                                	<div class="ui-form-item">
                                                    <label class="">交易类型：</label>
                                                    <div class="inline-block pr">
                                                        <select name="tradeType" class="sel js-select" id="select_type">
                                                            <option value="0">全部</option>
                                                            <option value="1">订单支付</option>
                                                            <option value="2">短信支付</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                </c:if>
                                                <c:if test="${styleNum==3 }">
                                                	<div class="ui-form-item">
                                                    <label class="">交易类型：</label>
                                                    <div class="inline-block pr">
                                                        <select name="tradeType" class="sel js-select" id="select_type">
                                                            <option value="0">全部</option>
                                                            <option value="1">充值</option>
                                                            <option value="2">支付</option>
                                                            <option value="3">收入</option>
                                                            <option value="4">退款</option>
                                                            <option value="5">提现</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                </c:if>
                                                <c:if test="${styleNum!=3}">
                                                <div class="ui-form-item">
                                                    <label class="">交易状态：</label>
                                                    <div class="inline-block pr">
                                                        <select name="tradeState" class="sel js-select"  id="select_status">
                                                            <option value="0">全部</option>
                                                            <option value="1">等待付款</option>
                                                            <option value="2">等待发货</option>
                                                            <option value="3">部分发货</option>
                                                            <option value="4">等待确认收货</option>
                                                            <option value="9">交易成功</option>
                                                            <option value="10">交易关闭</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                </c:if>
                                                <div class="ui-form-item">
                                                	<input type="button"id="doSearch" value="搜索" class="ui-button-lwhite ui-button"/>
                                                </div>
                                        </div>
                                        <div class="panel-table-content">
                                             <table class="ui-table">
                                                <thead>
                                                    <tr>
                                                        <th class="cell-1">时间</th>
                                                        <th class="cell-1">流水号</th>
                                                        <th class="cell-1">类型</th>
                                                        <th class="cell-1">行为概述</th>
                                                        <th class="cell-1">交易对方</th>
                                                        <th class="cell-1">收入</th>
                                                        <th class="cell-1">交易状态</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="datas" name="datas">
                                                </tbody>
                                            </table>
                                            <div class="mt10">
                                                <div class="pagination" id="pagination">
												</div>
                                            </div>
                                        </div>
                                        </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    
    <script id="feedback-templage" type="text/html">
	{{each rows}}
            <tr>
             <td class="cell-1"><span>{{$formatDate $value.createTime}}</span></td>
     		 <td class="cell-1"><span>{{$value.serialNumber}}</span></td>
             <td class="cell-1">
			 {{if $value.tradeType==1}}
				<span>订单支付</span>
			 {{/if}}
			 {{if $value.tradeType==2}}
				<span>短信支付</span>
			 {{/if}}
			 </td>
     		 <td class="cell-1"><a>{{$value.title}}</a></td>
   			 <td class="cell-1"><a>{{$value.sendName}}</a></td>
     		 <td class="cell-1"><span
     		 <c:if test="${styleNum==1}">class="c-r"</c:if>
     		 <c:if test="${styleNum==2}">class="c-g"</c:if>
     		 >{{$formatNumber $value.balance}}</span></td>
    		 <td class="cell-1">
			 {{if $value.tradeState==1}}
				<span>等待付款</span>
			 {{/if}}
			 {{if $value.tradeState==2}}
				<span>等待发货</span>
			 {{/if}}
			 {{if $value.tradeState==3}}
				<span>部分发货</span>
			 {{/if}}
			 {{if $value.tradeState==4}}
				<span>等待确认收货</span>
			 {{/if}}
			 {{if $value.tradeState==9}}
				<span>交易成功</span>
			 {{/if}}
			 {{if $value.tradeState==10}}
				<span>交易关闭</span>
			 {{/if}}
		     </td>
            </tr>
	{{/each}}
		{{ if rows.length == 0 }}
				<tr>
					<td colspan="7" style="text-align: center;">暂无数据</td>
				</tr>
			{{ /if }}
	</script>
	<script src="${res}/scripts/brand/myFinancial.js"></script>
    <script>
        seajs.use(["$", 'tip'], function ($,  Tip) {
            new Tip({
                trigger: '.tooltip',
                theme: 'yellow',
                arrowPosition: 11
            }).before('show', function () {
                this.set('content', this.activeTrigger.data().tip);
            });
            //表格样式
            $(".panel-table-content tbody tr:odd").css("background-color", "#f9f9f9");
        });
        
	  var page;
	  $(function () {
		    var styleNum = $("#hidd").val();
		    seajs.use(["pagination", "template", "moment"], function (Pagination, template, moment) {
		        template.helper('$formatDate', function (millsec) {
		            return moment(millsec).format("YYYY-MM-DD HH:mm:ss");
		        });
                template.helper('$formatNumber', function (price) {
                    if(isNaN(price)){
                        return price;
                    }else{
                        return parseFloat(price).toFixed(2);
                    }
                });
                page = new Pagination({
                    url: "${ctx}/dealer/finance/income.json",
                    elem: "#pagination",
                    form: $("#ui_form"),
                    method: "post",
                    handleData: function (json) {
                        var html = template.render("feedback-templage", json);
                        $("#datas").html(html);
                    }
                });
		          
		    });

		    $("#doSearch").click(function () {
		        page.goTo(1);
		    });

		});
		
		myFinancial.showTip();
	</script>
</body>
</html>
