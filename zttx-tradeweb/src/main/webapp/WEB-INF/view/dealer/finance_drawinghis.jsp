<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-财务管理-提现记录</title>
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
                <!--侧边导航-->
                
                    <jsp:include page="${ctx}/dealer/dealermenu">
					<jsp:param value="8" name="openId"/>
				</jsp:include>
                
                <!--主体内容-->
                <div class="main-right">
                    
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="${ctx }/dealer/finance" title="">财务管理</a> > <span class="bb">交易记录</span>
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
                                            <a href="${ctx}/dealer/finance/charge" target="_blank"  class="ui-button ui-button-mred">充 值</a>
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
                                   	<a href="${ctx}/dealer/finance/income" class="yahei fs14"><li>收入交易</li></a>
                                    <a href="${ctx}/dealer/finance/history" class="yahei fs14"><li>交易记录</li></a>
                                    <a href="${ctx}/dealer/finance/drawinghis" class="yahei fs14"><li  class="on">提现记录</li></a>
                                    <a href="${ctx}/dealer/finance/bankcard" class="yahei fs14"><li>银行卡设置</li></a>
                                    <a href="${ctx}/dealer/finance/drawing" class="yahei fs14"><li>提现</li></a>
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
                     								<input id="end-cal" class="end_time ui-input date" type="text" name="end"/>
                                                   </div>
                                                <div class="ui-form-item">
                                                    <label class="">交易状态：</label>
						    <div class="inline-block pr">
                                                    <select name="drawState" class="sel js-select"  id="select_status">
                                                    	<option value="-1">全部</option>
                                                        <option value="0">申请等待处理</option>
                                                        <option value="1">提现成功</option>
                                                        <option value="3">财务处理中</option>
                                                        <option value="5">已提交到银行</option>
                                                        <option value="7">提现失败</option>
                                                        <option value="9">取消提现</option>
                                                    </select>
                                                </div>
						</div>
                                                <div class="ui-form-item">
                                                	<input type="button"id="doSearch" value="搜索" class="ui-button-lwhite ui-button"/>
                                                </div>
                                        </div>
                                        <div class="panel-table-content">
                                             <table class="ui-table">
                                                <thead>
                                                    <tr>
                                                        <th class="cell-1">提现流水号</th>
                                                        <th class="cell-1">开户行</th>
                                                        <th class="cell-1">开户名</th>
                                                        <th class="cell-1">卡号</th>
                                                        <th class="cell-1">提现金额</th>
                                                        <th class="cell-1">申请时间</th>
                                                        <th class="cell-1">状态</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="datas" name="datas">
                                                </tbody>
                                            </table>
                                            <div class="mt10">
                                                <div class="pagination" id="pagination"></div>
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
    <script src="${res}/scripts/common/page-init.js"></script>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script src="${res}/scripts/brand/myFinancial.js"></script>
    <script id="feedback-templage" type="text/html">
	{{each rows}}
            <tr>
              <td class="cell-1"><span>{{$value.drawNumber}}</span></td>
     		  <td class="cell-1"><span>{{$value.bankExName}}</span></td>
              <td class="cell-1"><span>{{$value.acountName}}</span></td>
     		  <td class="cell-1"><span class="c-r">{{$value.lastBankNo}}</span></td>
   			  <td class="cell-1"><span class="c-g">{{$formatNumber $value.drawAmount}}</span></td>
     		  <td class="cell-1"><span class="c-r">{{ $formatDate $value.createTime}}</span></td>
<td class="cell-1"><span class="c-r">
				{{if $value.drawState==0}}
				<span>申请等待处理</span>
				{{/if}}
				{{if $value.drawState==1}}
				<span>提现成功</span>
				{{/if}}
				{{if $value.drawState==3}}
				<span>财务处理中</span>
				{{/if}}
				{{if $value.drawState==5}}
				<span>已提交到银行</span>
				{{/if}}
				{{if $value.drawState==7}}
				<span>提现失败</span>
				{{/if}}
				{{if $value.drawState==9}}
				<span>取消提现</span>
				{{/if}}
				</span></td>
            </tr>
	{{/each}}
{{ if rows.length == 0 }}
				<tr>
					<td colspan="6" style="text-align: center;">暂无数据</td>
				</tr>
			{{ /if }}
	</script>
	
    <script>
        seajs.use(["$", 'tip', 'calendar_style'], function ($,  Tip) {
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
		                    url: "${ctx}/dealer/finance/drawinghis.json",
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
