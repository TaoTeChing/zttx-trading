<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<title>管理中心-我的财务</title>
<link rel="stylesheet" href="${res}/styles/brand/global.css" />
<link rel="stylesheet" href="${res}/styles/brand/myFinancial.css" />
</head>
<body>
	<jsp:include page="${ctx}/brand/mainmenu"/>
	<div class="main myFinancial layout">
		<div class="main_con">
			<div class="inner">
				<!-- 内容都放这个地方  -->

				
    <jsp:include page="${ctx}/WEB-INF/view/brand/show_myFinance_bar.jsp">
    	<jsp:param name="m" value="${styleNum}" />
    </jsp:include>

				<div class="tab_con_box">
					<!-- 财务明细开始 -->
					<div class="tab_con">
						<form:form class="search_form" action="" id="ui_form">
						<input type="hidden" value="${styleNum}" id="hidd"/>
							<div class="clearfix">
<!-- 							<div class="item">
								<label for="">品牌：</label> 
								<div class="inline-block">
									<select class="js-select"
										name="select1" style="width:122px;" id="select1">
										<option value="0">不限</option>
									</select>
								</div>
							</div>
							<div class="item">
								<label for="">资金流向：</label> 
								<div class="inline-block">
									<select class="js-select"
										name="select2" id="select2">
										<option value="0">不限</option>
									</select>
								</div>
							</div> -->
							<div class="item">
								<label for="">时间：</label> 
								<input id="start-cal" class="start_time" type="text" name="start"/>
                    				-
                     			<input id="end-cal" class="end_time" type="text" name="end"/>
							</div>
							
							
							
							<c:choose>
								<c:when test="${styleNum==7}">
								<div class="item">
									<label for="">提现类型：</label> 
									<div class="inline-block">
										<select class="js-select"
											style="width: 112px;" name="tradeType" id="select4">
											<option value="">全部</option>
	                                        <option value="0">申请等待处理</option>
	                                        <option value="1">提现成功</option>
	                                        <option value="3">财务处理中</option>
	                                        <option value="7">提现失败</option>
										</select>
									</div>
								</div>
								</c:when>
								<c:when test="${styleNum==3}">
								<div class="item">
									<label for="">交易类型：</label> 
									<div class="inline-block">
										<select class="js-select"
											style="width: 112px;" name="tradeType" id="select4">
											<option value="0">全部</option>
	                                        <option value="10">网银充值</option>
	                                        <option value="11">手工充值</option>
	                                        <option value="2">支付</option>
	                                        <option value="3">收入</option>
	                                        <option value="4">退款</option>
	                                        <option value="5">提现</option>
										</select>
									</div>
								</div>
								</c:when>
								<c:otherwise>
								<div class="item">
									<label for="">交易类型：</label> 
									<div class="inline-block">
										<select class="js-select"
											style="width: 112px;" name="tradeType" id="select4">
											<option value="0">全部</option>
	                                        <option value="1">订单支付</option>
	                                        <option value="2">短信支付</option>
										</select>
									</div>
								</div>
								</c:otherwise>
							</c:choose>
							<c:if test="${styleNum!=3 && styleNum!=7}">
							<div class="item">
								<label for="">交易状态：</label>
								<div class="inline-block"> 
									<select class="js-select"
										name="tradeState" id="select3">
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
							<div class="item">
								<a class="simple_button" id="doSearch">搜&nbsp;索</a>
							</div>
							</div>
                        <table class="detail_table">
							<colgroup>
							<c:choose>
								<c:when test="${styleNum==1}">
								<col width="210">
								<col width="170">
                                <col width="100">
								<col width="306">
								<col width="160">
								<col width="150">
                                <col width="150">
                                <col width="150">
								</c:when>
								<c:when test="${styleNum==3}">
								<col width="260">
								<col width="240">
								<col width="100">
								<col width="230">
								<col width="220">
                                <col width="200">
								</c:when>
								<c:when test="${styleNum==7}">
								<col width="160">
								<col width="170">
                                <col width="200">
								<col width="200">
								<col width="100">
								<col width="150">
                                <col width="200">
								</c:when>
								<c:otherwise>
								<col width="210">
								<col width="170">
                                <col width="100">
								<col width="306">
								<col width="160">
								<col width="150">
                                <col width="150">
								</c:otherwise>
							</c:choose>
							</colgroup>
							<thead>
							<c:choose>
								<c:when test="${styleNum==7 }">
								<tr>
									<th>时间</th>
									<th>流水号</th>
									<th>开户行</th>
									<th>开户名</th>
									<th>卡号</th>
									<th>金额</th>
									<th>提现状态</th>
								</tr>
								</c:when>
								<c:otherwise>
								<tr>
									<th>时间</th>
									<th>流水号</th>
									<th>类型</th>
									<c:if test="${styleNum!=3}">
                                        <th>行为概述</th>
                                     </c:if>
									<c:if test="${styleNum!=3}">
                                      <th>交易对方</th>
                                     </c:if>
									 <c:if test="${styleNum==1||styleNum==3}">
                                        <th>收入</th>
                                        <c:if test="${styleNum==1}">
                                        <th>佣金</th>
                                        </c:if>
                                     </c:if>
									<c:if test="${styleNum==2||styleNum==3}">
                                        <th>支出</th>
                                     </c:if>
                                     <c:if test="${styleNum!=3}">
                                        <th>交易状态</th>
                                     </c:if>
									<c:if test="${styleNum==3}">
                                        <th>账户余额</th>
                                     </c:if>
									<!--<th>结算状态</th>-->
								</tr>
								</c:otherwise>
							</c:choose>
							</thead>
                            <tbody id="datas" class="datas"></tbody>
						</table>
						<div class="pagination mt15">
							<div class="pagination" id="pagination">
							</div>
						</div>
						</form:form>
					</div>
					<!-- 财务明细结束 -->
				</div>

			</div>
		</div>
	</div>
	<%@ include file="bottom.jsp"%>
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
    <script src="${res}/scripts/brand/myFinancial.js"></script>
	<script>
		myFinancial.init();
	</script>
	<script id="feedback-templage" type="text/html">
	<c:if test="${styleNum==1||styleNum==2}">
	{{each rows}}
            <tr>
             <td class="cell-1"><span>{{$formatDate $value.createTime}}</span></td>
     		 <td class="cell-2"><span>{{$value.serialNumber}}</span></td>
             <td class="cell-3">
			 {{if $value.tradeType==1}}
				<span>订单支付</span>
			 {{/if}}
			 {{if $value.tradeType==2}}
				<span>短信支付</span>
			 {{/if}}
			 </td>
     		 <td class="cell-4"><a href="${ctx}/brand/order/view/{{$value.orderId}}" target="_blank">{{$value.title}}</a></td>
   			 <td class="cell-5"><a>{{$value.sendName}}</a></td>
     		 <td class="cell-2"><span
     		 <c:if test="${styleNum==1}">class="c-r"</c:if>
     		 <c:if test="${styleNum==2}">class="c-g"</c:if>
     		 >{{$formatMoney $value.balance}}</span></td>
			 <c:if test="${styleNum==1}">
			 <td class="cell-1"><span>{{$formatMoney $value.pointBalance}}</span>
			 </c:if>
    		 <td class="cell-7">
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
            <td class="ta-c" colspan="8">暂无数据</td>
        </tr>
        {{ /if }}
	</c:if>

	<c:if test="${styleNum==3}">
	{{each rows}}
            <tr>
              <td class="cell-1"><span>{{$formatDate $value.createtime}}</span></td>
     		  <td class="cell-2"><span>{{$value.serialNumber}}</span></td>
              <td class="cell-3">				
				{{if $value.tradetype==10}}
				<span>网银充值</span>
				{{/if}}
				{{if $value.tradetype==11}}
				<span>手工充值</span>
				{{/if}}
				{{if $value.tradetype==2}}
				<span>支付</span>
				{{/if}}
				{{if $value.tradetype==3}}
				<span>收入</span>
				{{/if}}
				{{if $value.tradetype==4}}
				<span>退款</span>
				{{/if}}
				{{if $value.tradetype==5}}
				<span>提现</span>
				{{/if}}
			  </td>
     		  <td class="cell-2"><span class="c-r">{{$formatMoney $value.inCome}}</span></td>
   			  <td class="cell-2"><span class="c-g">{{$formatMoney $value.outAmount}}</span></td>
     		  <td class="cell-6"><span class="c-r">{{$formatMoney $value.balance}}</span></td>
            </tr>
	{{/each}}
        {{ if rows.length == 0 }}
        <tr>
            <td class="ta-c" colspan="7">暂无数据</td>
        </tr>
        {{ /if }}
	</c:if>

	<c:if test="${styleNum==7}">
	{{each rows}}
            <tr>
                <td class="cell-1"><span>{{$formatDate $value.createTime}}</span></td>
                <td class="cell-2"><span>{{$value.drawNumber}}</span></td>
                <td class="cell-3"><span>{{$value.bankName}}</span></td>
                <td class="cell-2"><span class="c-r">{{$value.acountName}}</span></td>
                <td class="cell-2"><span class="c-g">{{$value.bankAccount}}</span></td>
                <td class="cell-6"><span class="c-r">{{$formatMoney $value.drawAmount}}</span></td>
                <td class="cell-3">
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
                    <span>提交到银行</span>
                    {{/if}}
                    {{if $value.drawState==7}}
                    <span>提现失败</span>
                    {{/if}}
					{{if $value.drawState==9}}
                    <span>取消提现</span>
                    {{/if}}
                </td>
            </tr>
	{{/each}}
        {{ if rows.length == 0 }}
        <tr>
            <td class="ta-c" colspan="7">暂无数据</td>
        </tr>
        {{ /if }}
	</c:if>
	</script>
	
	<script>
	  var page;
	  $(function () {
	  var styleNum = $("#hidd").val();
	    seajs.use(["pagination","template","moment"], function (Pagination,template,moment) {
	    	template.helper('$formatDate', function (millsec) {
				return moment(millsec).format("YYYY-MM-DD HH:mm:ss");
			});
			template.helper('$formatMoney', function (num) {
				num = num == null ? 0 : num;
		        return num.toFixed(2);
		    });

            var renderPagination = function(){
        	if(styleNum==1){
        	page = new Pagination({
              url: "${ctx}/brand/tradedetails/list/in.json",
              elem: "#pagination",
              form:$("#ui_form"),
              method:"post",
              handleData: function (json) {
                  var html = template.render("feedback-templage", json);
                  $("#datas").html(html);
              }
          });
          }
          if(styleNum==2){
        	page = new Pagination({
              url: "${ctx}/brand/tradedetails/list/out.json",
              elem: "#pagination",
              form:$("#ui_form"),
              method:"post",
              handleData: function (json) {
                  var html = template.render("feedback-templage", json);
                  $("#datas").html(html);
              }
          });
          }
          
          if(styleNum==3){
        	page = new Pagination({
              url: "${ctx}/brand/tradedetails/list/business.json",
              elem: "#pagination",
              form:$("#ui_form"),
              method:"post",
              handleData: function (json) {
                  var html = template.render("feedback-templage", json);
                  $("#datas").html(html);
              }
          });
          }
          
          if(styleNum==7){
        	page = new Pagination({
              url: "${ctx}/brand/tradedetails/list/drawals.json",
              elem: "#pagination",
              form:$("#ui_form"),
              method:"post",
              handleData: function (json) {
                  var html = template.render("feedback-templage", json);
                  $("#datas").html(html);
              }
          });
          }
          
          };
          renderPagination(); 
        });
        
        $("#doSearch").click(function(){
        	page.goTo(1);
        });
        
      });
      
	</script>
</body>
</html>