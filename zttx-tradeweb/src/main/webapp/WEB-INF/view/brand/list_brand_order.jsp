<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zttx.web.consts.DealerConstant" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<% String[] arr = {"#ff0000","#d603f1","#0082cc","#009944","#996c33"}; %>
<c:if test="${1!=orderType && 2!=orderType && 3!=orderType && 4!=orderType && 5!=orderType && 6!=orderType && 7!=orderType}"><c:set var="title" value="采购订单"/></c:if>
<c:if test="${1==orderType}"><c:set var="title" value="待付款"/></c:if>
<c:if test="${2==orderType}"><c:set var="title" value="待发货订单"/></c:if>
<c:if test="${3==orderType}"><c:set var="title" value="已发货"/></c:if>
<c:if test="${4==orderType}"><c:set var="title" value="成功的"/></c:if>
<c:if test="${5==orderType}"><c:set var="title" value="关闭的"/></c:if>
<%--<c:if test="${6==orderType}"><c:set var="title" value="预订订单"/></c:if>--%>
<c:if test="${7==orderType}"><c:set var="title" value="退款中"/></c:if>
    <title>管理中心-交易管理-${title}</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_trade.css"/>
    <style>
    	#privilege_select_div{
    		border: none;
    		background: none;
    	}
    	.free_shipping{
            color: #0082CC;
            margin-right: 5px;
            vertical-align: bottom;
        }
        .free_shipping:hover{
            color: #ff8800;
        }
   	</style>
</head>
<body>
	<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
    <div class="main_con">
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <a class="link" href="${ctx}/brand/order/purorder">交易管理</a>
                <span class="arrow">&gt;</span>
                <span class="current">${title}</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <!-- 面包屑结束 -->
    <div class="inner">
    <!-- 内容都放这个地方  -->
        <form:form class="ui-form tradorder-form clearfix" id="orderForm">
        	<input type="hidden" name="orderType" value="${orderType}"/>
        	<input type="hidden" name="isShowClose" id="isShowClose"/>
            <input type="hidden" name="isSampleOrder" id="isSampleOrder"/>
            <div class="ui-form-item">
                <label class="">
                    品牌：
                </label>
                <div class="inline-block">
                    <select class="ui-select js_select tradorder-select140" name="brandsId">
                    	<option value="" >全部品牌</option>
                    	<c:if test="${infoList != null }">
                    		<c:forEach var="info" items="${infoList }">
                    			<option value="${info.refrenceId }">${info.brandsName }</option>
                    		</c:forEach>
                    	</c:if>
                    </select>
                </div>
            </div>
            <div class="ui-form-item">
                <label class="">
                    订单号：
                </label>
                <input type="text" class="ui-input tradorder-text-number" name="orderIdStr"/>
            </div>
            <div class="ui-form-item">
                <label class="">
                    终端商名：
                </label>
                <input type="text" class="ui-input tradorder-text-name" name="dealerName"/>
            </div>
            <div class="ui-form-item" style="display:none;">
                <label class="">
                    售后服务：
                </label>
                <div class="inline-block">
                    <select class="ui-select js_select tradorder-select115" name="list-pro-line2">
                        <option value="0">无</option>
                        <option value="1">有</option>
                    </select>
                </div>
            </div>
            <div class="ui-form-item">
                <label class="">
                    下单时间：
                </label>
                <input style="width: 101px;" readonly type="text" class="ui-input Wdate" id="start-cal" name="startTimeStr">
                -
                <input style="width: 102px;" readonly type="text" class="ui-input Wdate" id="end-cal" name="endTimeStr">
            </div>
            <div class="ui-form-item">
                <label class="">
                    订单状态：
                </label>
                <div class="inline-block">
                    <select class="ui-select js_select tradorder-select197" name="orderStatusStr">
                    	 <option value="">全部订单</option>
                    	 <c:if test="${orderStatus != null }">
		                    <c:forEach items="${orderStatus }" var="status">
		                    	 <option value="${status.dictValue }">${status.dictValueName }</option>
		                    </c:forEach>
	                    </c:if>
                    </select>
                </div>
            </div>
            <div class="ui-form-item">
                <label>订单类型：</label>
                <div class="pr inline-block">
                    <c:set var="cash" value="<%=DealerConstant.DealerOrder.ORDER_TYPE_CASH%>"/>
                    <c:set var="credit" value="<%=DealerConstant.DealerOrder.ORDER_TYPE_CREDIT%>"/>
                    <c:set var="point" value="<%=DealerConstant.DealerOrder.ORDER_TYPE_POINT%>"/>
                    <select class="ui-select js_select" name="dealerOrderType">
                        <option value="0">请选择</option>
                        <option value="${cash}">
                            现款
                        </option>
                        <option value="${credit}">
                            授信
                        </option>
                        <option value="${point}">
                            返点
                        </option>
                    </select>
                </div>
            </div>
            <div class="ui-form-item fr" style="margin-left:30px;">
                <a class="simple_button tradorder-btn-sousuo" href="javascript:page.goTo(1);">搜索</a>
                <a class="simple_button" style="display:none;">批量导出订单</a>
            </div>
        </form:form>
        <div class="purorder">
            <div class="js_trador_tabs"><!-- 交易管理tabs开始 -->
                <div class="js_trador_menu"><!-- 交易管理tabs菜单切换 -->
                    <ul class="clearfix">
                        <li <c:if test="${1!=orderType && 2!=orderType && 3!=orderType && 4!=orderType && 5!=orderType && 6!=orderType && 7!=orderType}">class="selected"</c:if>>
                            <a href="/brand/order/purorder">所有订单</a>
                        </li>
                        <li <c:if test="${1==orderType}">class="selected"</c:if>>
                            <a href="/brand/order/purorder/1">待付款</a>
                        </li>
                        <li <c:if test="${2==orderType}">class="selected"</c:if>>
                            <a href="/brand/order/purorder/2">待发货</a>
                        </li>
                        <li <c:if test="${3==orderType}">class="selected"</c:if>>
                            <a href="/brand/order/purorder/3">已发货</a>
                        </li>
                        <li <c:if test="${4==orderType}">class="selected"</c:if>>
                            <a href="/brand/order/purorder/4">成功的</a>
                        </li>
                        <li <c:if test="${5==orderType}">class="selected"</c:if>>
                            <a href="/brand/order/purorder/5">关闭的</a>
                        </li>
                        <%--<li <c:if test="${6==orderType}">class="selected"</c:if>>--%>
                            <%--<a href="/brand/order/purorder/6">预订订单</a>--%>
                        <%--</li>--%>
                        <li <c:if test="${7==orderType}">class="selected"</c:if>>
                            <a href="/brand/order/purorder/7">退款中</a>
                        </li>
                    </ul>
                </div>
                <div class="js_trador_con"><!-- 交易管理tabs内容 -->
                    <!-- tab 里面的 div 开始 -->
                    <div class="js_trador_conbox">
                        <table class="orderbox-table" style="margin-bottom: 0;">
                            <colgroup>
                                <col width="105" />
                                <col width="245" />
                                <col width="110" />
                                <col width="175" />
                                <col width="120" />
                                <col width="90" />
                                <col width="140" />
                            </colgroup>
                            <thead>
                                <tr>
                                    <th>品牌</th>
                                    <th>货号</th>
                                    <th>数量（件）</th>
                                    <th>终端商</th>
                                    <th>金额（元）</th>
                                    <th>交易状态</th>
                                    <th>交易操作</th>
                                </tr>
                            </thead>
                        </table>
                        <div class="checkallbox clearfix">
                            <div class="checkallsel inline-block">
                                <input type="checkbox" id="checkall"/>
                                <label for="checkall">全选</label>
                                <a href="javascript:;" class="checkall-hover hide">批量发货</a>
                                <a href="javascript:;" class="checkall-hover batch_remark">批量备注</a>
                                <c:if test="${1==orderType || 2==orderType || 6==orderType}"><a href="javascript:;" class="checkall-hover betch_freeshipping">批量免邮</a></c:if>
                            </div>
                            <div class="close-order inline-block">
                                <input type="checkbox" id="nosee-order" />
                                <label for="nosee-order">不显示关闭的订单</label>
                            </div>
                            <div class="close-order inline-block">
                                <input type="checkbox" id="sample-order"/>
                                <label for="sample-order">只看拿样订单</label>
                            </div>
                            <div class="page-top-down inline-block">
                                <a href="javascript:;" class="page-style page-top">上一页</a>
                                <a href="javascript:;" class="page-style page-down">下一页</a>
                            </div>
                        </div>
                        <div class="orderbox_contain" >
                        </div>
                        <div class="mt10" id="pagination"></div>
                    </div>
                    <!-- 结束 -->
                </div>
            </div><!-- tabs结束 -->

        </div>
    </div>
</div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<div id="popup"></div>
<script id="feedback-templage" type="text/html">
{{each rows}}
	<div class="orderbox">
		<input type="hidden" name="adjustFreight" value="{{$value.adjustFreight}}">
		<input type="hidden" name="adjustPrice" value="{{$value.adjustPrice}}">
		<input type="hidden" name="dealerId" value="{{$value.dealerId}}">
		<input type="hidden" name="refrenceId" value="{{$value.refrenceId}}">
		<input type="hidden" name="outActTime" value="{{$value.outActTime}}">
		<input type="hidden" name="address" value="{{$value.dealerAddrProvince}}{{$value.dealerAddrCity}}{{$value.dealerAddrArea}}{{$value.dealerAddress}}">

		<div class="check">
			<div class="inline-block">
				<input type="checkbox" class="js_chk" />
				<label>交易订单号：{{$value.orderId}}</label>
			</div>
			<div class="datatime inline-block">
                 下单时间：{{$formatDate $value.createTime}}
			</div>
            <div class="datatime inline-block">
                <label>终端商：{{$value.dealerName}}</label>
            </div>

            <div class="datatime inline-block">
                {{if $value.dealerOrderType == 1 }}
                    <%--{{if $value.isSampleOrder != null && $value.isSampleOrder }}
                        <label>采购类型：那样</label>
                    {{else}}--%>
                        <label>采购类型：现款</label>
                    <%--{{/if}}--%>
                {{else if $value.dealerOrderType == 2}}
                    <label>采购类型：授信</label>
				{{else if $value.dealerOrderType == 3}}
                    <label>采购类型：返点</label>
                {{/if}}
            </div>

			<div data-color="{{$hanleColor $value.levelMark}}" data-colorindex="{{$value.levelMark}}" data-text="{{$value.brandRemark}}" data-id="2" class="remarks inline-block bluefont">
				{{ if $value.levelMark != null }}
				<em class="red-star" style="color: {{$hanleColor $value.levelMark}}">★</em>备注
				{{else}}
				<em class="red-star" style="display:none;">★</em>备注
				{{/if}}
				<input type="hidden" name="brandRemark" value="{{$value.brandRemark}}">
			</div>
		</div>
		<table class="orderbox-table">
			<colgroup>
				<col width="105" />
				<col width="245" />
				<col width="110" />
				<col width="175" />
				<col width="120" />
				<col width="90" />
				<col width="140" />
			</colgroup>
			<tbody>
			<tr>
				<td class="brand_name">{{$value.brandsName}}</td>
				<td class="product_info">
				{{each $value.items}}
                    {{if $index < 10}}
                        {{$value.productNo}}
                    {{/if}}
				{{/each}} {{if $value.items.length>3}}等产品{{/if}}
				</td>
				<td class="orderbox-td-tc">
					<span class="product_num">{{$value.productCount}}</span>
					<p><a target="_blank" href="${ctx}/brand/order/view/{{$value.refrenceId}}" class="simple_button bluefont"><i class="icon-orderlist"></i>订单详情</a></p>
				</td>
				<td>
					{{$value.dealerName}}
					<p class="sixfont mt5" title="{{$value.remark}}">{{$trimLongString($value.remark,50)}}</p>
				</td>
				<td class="orderbox-td-tc">
					<span class="product_price hide" >{{$value.productPrice}}</span>
					<strong class="product_price">{{$formatMoney $value.productPrice+$value.adjustPrice}}</strong>
					<p class="sixfont">
                        {{if $value.dealerOrderType==3}}
							（品牌商承担运费）
                        {{else}}
							{{if $value.adjustFreight == null}}（物流未设定）{{else}}（物流：{{$formatMoney $value.adjustFreight}}）{{/if}}
						{{/if}}
					</p>
				</td>
				<td class="orderbox-td-tc">
					{{if $value.orderStatus == 1}}
						<span class="redfont">待付款</span>　
					{{/if}}
					{{if $value.orderStatus == 2}}
						{{if $value.frePayState == 1 && $value.payState==2}}
						<span class="greenfont">已付款</span>
						{{else}}
						<span class="greenfont">已部分付款</span>
						{{/if}}
					{{/if}}
					{{if $value.orderStatus == 3}}
						<span class="greenfont">已部分发货</span>
					{{/if}}
					{{if $value.orderStatus == 4}}
						<span class="greenfont">已发货</span>
					{{/if}}
					{{if $value.orderStatus == 9}}
						<span class="greenfont">交易成功</span>
					{{/if}}
					{{if $value.orderStatus == 10}}
						<span style="color: #999;">交易关闭</span>
					{{/if}}
					{{if $value.serProStatus != null && $value.serProStatus == 2}}
						<br/><span class="bluefont">纠纷处理中</span>
					{{else}}
						{{if $value.refundStatus == 1}}
						<p class="status status_1">
							<i class="iconfont"></i>申请退款中
						</p>
						{{/if}}
						{{if $value.refundStatus == 2}}
						<p class="status status_4">
							<i class="iconfont"></i>等待退货
						</p>
						{{/if}}
						{{if $value.refundStatus == 3}}
						<p class="status status_4">
							<i class="iconfont"></i>已退货
						</p>
						{{/if}}
						{{if $value.refundStatus == 4 || $value.refundStatus == 5}}
						<p class="status status_3">
							<i class="iconfont"></i>拒绝退款
						</p>
						{{/if}}
						{{if $value.refundStatus == 6}}
						<p class="status status_5">
							<i class="iconfont"></i>退款关闭
						</p>
						{{/if}}
						{{if $value.refundStatus == 9 || $value.refundStatus == 10}}
						<p class="status status_2">
							<i class="iconfont"></i>退款成功
						</p>
						{{/if}}
					{{/if}}
				</td>
				<td class="orderbox-td-tc">
					{{if $value.orderStatus == 1}}
						<div><a href="javascript:;" class="ui_button ui_button_sblue js_update_cash" data-orderid="{{$value.refrenceId}}" data-brandsid="{{$value.brandsId}}" data-type="price" data-ordertype="{{$value.dealerOrderType}}">修改金额</a></div>
                       <div><a href="javascript:;" class="ui_button ui_button_sgray js_close_order" data-orderid="{{$value.refrenceId}}" data-brandsid="{{$value.brandsId}}" data-brandsname="{{$value.brandsName}}">关闭订单</a></div>
					{{/if}}
					{{if $value.orderStatus == 10}}
						<div><a class="ui_button ui_button_sgray js_message" href="javascript:;"  data-dealerid="{{$value.dealerId}}" data-name="{{$value.brandName}}">站内信</a></div>
					{{/if}}
					{{if $value.orderStatus > 1 && $value.orderStatus<=4 && $value.frePayState == 0}}
						<div><a class="ui_button ui_button_sblue js_update_cash" href="javascript:;"  data-orderid="{{$value.refrenceId}}" data-payState="{{$value.payState}}" data-brandsid="{{$value.brandsId}}" data-type="fare" >修改运费</a></div>
					{{/if}}
					{{if $value.orderStatus == 2}}
						<div><a class="ui_button ui_button_sorange" href="javascript:TradePurorder.sendGoods('{{$value.orderId}}','{{$value.refundStatus}}')">发货</a></div>
						{{if $value.dealerOrderType == 3 }}
							<div><a href="javascript:;" class="ui_button ui_button_sgray js_close_order" data-orderid="{{$value.refrenceId}}" data-brandsid="{{$value.brandsId}}" data-brandsname="{{$value.brandsName}}">关闭订单</a></div>
						{{/if}}	
					{{/if}}
					{{if $value.orderStatus == 3}}
						<div><a class="ui_button ui_button_sorange" href="javascript:TradePurorder.sendGoods('{{$value.orderId}}','{{$value.refundStatus}}')">继续发货</a></div>
					{{/if}}
					{{if $value.orderStatus == 4}}
						<div><a href="javascript:;" class="ui_button ui_button_sorange js_extension_time" tabindex="-1" data-orderid="{{$value.refrenceId}}">延长收货期限</a></div>
					{{/if}}
					{{if $value.refundStatus != null || $value.serProStatus != null}}
                        {{if $value.dealerOrderType == 1 }}
                            <div><a class="ui_button ui_button_sblue" target="_blank" href="${ctx}/brand/refund/view/{{$value.orderId}}" target="_blank">退款详情</a></div>
                        {{else}}
                            <div><a class="ui_button ui_button_sblue" target="_blank" href="${ctx}/brand/refund/factory/view/{{$value.refundId}}" target="_blank">退款详情</a></div>
                        {{/if}}
                       {{if 1!=1}}
						<div><a class="bluefont" target="_blank" href="1.Trade_delivery.jsp" target="_blank">查看物流信息</a></div>
						{{/if}}
					{{/if}}
				</td>
			</tr>
			</tbody>
		</table>
	</div>
{{/each}}
{{ if rows.length == 0 }}
    <div class="ta-c mt15">暂无数据</div>
{{ /if }}
</script>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/brand_trade.js"></script>
<!-- 鼠标移入备注时的有数据的模板 -->
<script type="text/html" id="showRemark">
    <div id="remark_pop_{{id}}" class="remark_pop">
        <div class="hd">
            <label for="">
                <em style="color: {{color}}">★</em>
            </label>
        </div>
        <div class="bd">
            <div class="info">
               {{$trimLongString(text,50)}}
            </div>
            <div class="operate">
                <a data-id="{{id}}" data-colorindex="{{colorIndex}}" data-text="{{text}}" href="javascript:;" class="simple_button update_btn">修改</a>
            </div>
        </div>
    </div>
</script>
<!-- 鼠标点击备注时的模板 -->
<script type="text/html" id="dialogRemark">
    <div class="ui-head">
        <h3>备注</h3>
    </div>
    <div id="remark_dialog_{{id}}" class="remark_dialog">
        <div class="hd" id="levelMark">
			<label for="">
                <input name="leven" value="1" type="radio" {{ if colorIndex == 1 }} checked {{/if}}  /><em style="color: #ff0000">★</em>
            </label>
            <label for="">
                <input name="leven" value="2" type="radio" {{ if colorIndex == 2 }} checked {{/if}}   /><em style="color: #d603f1">★</em>
            </label>
            <label for="">
                <input name="leven" value="3" type="radio" {{ if colorIndex == 3 }} checked {{/if}}  /><em style="color: #0082cc">★</em>
            </label>
            <label for="">
                <input name="leven" value="4" type="radio" {{ if colorIndex == 4 }} checked {{/if}}  /><em style="color: #009944">★</em>
            </label>
            <label for="">
                <input name="leven" value="5" type="radio" {{ if colorIndex == 5 }} checked {{/if}}  /><em style="color: #996c33">★</em>
            </label>
        </div>
        <div class="bd">
            <div class="info">
                <textarea id="remark">{{text}}</textarea>
            </div>
            <div class="operate">
                <button class="confirm_btn simple_button" type="button">确定</button>
                <button class="cancel_btn simple_button ml5" type="button">取消</button>
            </div>
        </div>
    </div>
</script>

<!-- 延长收货期限的模板 -->
<script type="text/html" id="extension_tpl">
    <div class="ui-head">
        <h3>延长收货期限</h3>
	</div>
	<div class="qixian_text mt15 ta-c"></div>
	 <form:form action="" id="outActTime">
<input type="hidden" name="refrenceId" value="" />
    <div class="confirm_bd extension_dialog">
        <div class="ta-c mt20">
            延长：
            <div class="inline-block ta-l">
                <select class="js_select extension_select" name="actTime">
                    <option value="3">3天</option>
                    <option value="5">5天</option>
                    <option value="10">10天</option>
                    <option value="15">15天</option>
                </select>
            </div>
        </div>
        <div class="operate">
            <a class="confirm_btn" href="javascript:;">确定</a>
            <a class="cancel_btn" href="javascript:;">取消</a>
        </div>
</form:form>
    </div>
</script>
<!-- 修改金额 -->
<script type="text/html" id="update_cash_tpl">
    <div class="ui-head">
        <h3>{{title}}</h3>
    </div>
      <form:form action="#" id="updatePrice">
		<input type="hidden" name="refrenceId" value="" />
        <input type="hidden" name="brandsId" value=""/>
        <table class="common_table">
            <colgroup>
                <col width="96" />
                <col width="246" />
                <col width="72" />
                <col width="92" />
                <col width="76" />
                <col width="120" />
                <col width="120" />
            </colgroup>
            <tr>
                <th>品牌</th>
                <th>产品</th>
                <th>数量(件）</th>
                <th>货款（元）</th>
                <th>折扣</th>
				 {{ if type == "price" }}
                <th>
					<div class="inline-block">
						<select style="width: 50px;height: 20px;" id="privilege_select" name="privilege_select">
							<option  value="0" {{if oldAdjustPrice<0}}selected{{/if}}>优惠</option>
							<option value="1" {{if oldAdjustPrice>0}}selected{{/if}}>加价</option>
						</select>
					</div>
				</th>
				{{ /if }}
				{{ if type == "fare" }}
				<th>
					{{if oldAdjustPrice<=0}}优惠{{else}}加价{{/if}}
				</th>
				{{ /if }}
                <th class="last">物流费</th>
            </tr>
            <tr>
                <td>{{brand}}</td>
                <td>{{info}}</td>
                <td>{{num}}</td>
                <td class="yprice">{{$formatNumber price}}</td>
                {{ if type == "price" }}
                <td>
                    <input {{if oldAdjustPrice>0}} readonly="readonly" {{/if}} class="discount" data-min="1" data-max="10" name="discount" style="width: 36px;" type="text" value=""
                    {{if dealerOrderType == 2}}disabled{{/if}} />
                    折
                </td>
                <td>
                    <input class="nprice" style="width: 70px;" type="text" value="{{if adjustPrice>0}}{{adjustPrice}}{{/if}}" data-min="0.00" data-max="{{price - 0.01}}" name="adjustPrice" {{if dealerOrderType == 2}}disabled {{/if}} />
                    元
                </td>
                {{ /if }}
                {{ if type == "fare" }}
                <td>
                   {{discount}}
				<input class="discount" name="discount" type="hidden" value="{{discount}}" />
                    {{if discount!=null && discount!=""}}折{{/if}}
                </td>
                <td>
                   {{adjustPrice}}
				 <input class="nprice" type="hidden" value="{{adjustPrice}}" data-min="0.00" data-max="{{price - 0.01}}" name="adjustPrice" />
                    元
                </td>
                {{ /if }}
                <td class="last">
                    <input class="wuliuprice" style="width: 70px;"  type="text" value='{{if oldAdjustFreight !=null &&  oldAdjustFreight!="" && oldAdjustFreight>=0}}{{adjustFreight}}{{/if}}'  name="adjustFreight"/>
                    元
                </td>
            </tr>
        </table>
        <div class="cash_info">

            <div class="item">
                <label for="">收货地址：</label>
                <span title="{{address}}" class="fn-text-overflow" style="display: inline-block; vertical-align: -5px; width: 420px;">{{address}}</span>
            </div>
            <div class="item">
                <label for="">实付款：</label>
                <strong class="price">{{price}}</strong><span class="price_explain">（货款）</span><span class="symbol">{{if oldAdjustPrice>0}}+{{else}}-{{/if}}</span><strong class="price prefer_price total_discount">{{adjustPrice || 0.00 }}</strong>
				<span class="price_explain">（<span class="symbol_text">{{if oldAdjustPrice>0}}加价{{else}}优惠{{/if}}</span>）</span>+<strong class="price total_wuliu">{{adjustFreight || 0.00 }}</strong><span class="price_explain">（物流费）</span>=
                <strong class="price total_price">{{totalprice}}</strong>元
                <a class="free_shipping" href="javascript:;">免运费</a>
            </div>
            <div class="operate">
                <button class="simple_button confirm" type="button" id="updatePriceBtn">确定</button>
                <button class="simple_button ml5 cancel" type="button">取消</button>
            </div>
        </div>
    </form:form>

</script>
<!-- 关闭订单 -->
<script type="text/html" id="closePanel_tpl">
    <div class="clearfix closePanel">
        <div class="ui-head">
            <h3>我要关闭交易</h3>
        </div>
        <form:form class="ui-form"  id="closeOrder">
			<input type="hidden" name="orderId" value="" />
			<input type="hidden" name="brandsId" value="" />
			<input type="hidden" name="brandsName" value="" />
            <fieldset>
                <legend>我要关闭交易</legend>
                <div class="ui-form-item mt15">
                    <label for="" class="ui-label">申请关闭交易的原因：</label>
                    <div class="inline-block">
                        <select class="ui-select reason-select" name="code">
                            <option value="4">长时间联系不到终端商</option>
							<option value="5">终端商不想买了</option>
							<option value="6">没有货了，交易无法完成</option>
							<option value="3">其他原因</option>
                        </select>
                    </div>
                </div>
                <div class="ta-c">
                    <button type="button" class="simple_button confirm_btn" id="closeOrderBtn">提&nbsp;交</button>
                    <button type="button" class="simple_button ml5 cancel_btn">取消</button>
                </div>
            </fieldset>
        </form:form>
    </div>
</script>
<!-- 发站内信 -->
<script type="text/html" id="message_tpl">
    <div class="message_dialog">
        <div class="ui-head">
            <h3>发站内信</h3>
        </div>
        <form:form class="ui-form message-form" action="#">
            <div class="ui-form-item mt15">
                <textarea placeholder="请输入内容..." class="ui-textarea" id="message_text"></textarea>
            </div>
            <div class="ta-c">
                <button type="button" class="simple_button confirm_btn">提&nbsp;交</button>
                <button type="button" class="simple_button ml5 cancel_btn">取消</button>
            </div>
        </form:form>
    </div>
</script>



<script>
   var page;
   var colorArr = ["#ff0000","#d603f1","#0082cc","#009944","#996c33"];

   function mouseRemarks(){
   	   seajs.use(["popup"],function(Popup){
   			$(".remarks").each(function(){
           var id = $(this).data("id");
           var color = $(this).data("color");
           if(color == ""){
               return;
           }
           new Popup({
               trigger: $(this),
               element: '#popup',
               align: {
                   selfXY: ["right", 0],
                   baseXY: [63, 35]
               }
           }).before('show',function(){

           	 var current = this.activeTrigger;
          	     var color = current.data("color");
          	     var text = current.data("text");
          	     var colorIndex = current.data("colorindex");
          	     var data = {
	           	 	id: id,
	           	 	color: color,
	           	 	text: text,
	           	 	colorIndex: colorIndex
	           	 }
                 template.helper('$trimLongString', function (string,num) {
                      return trimLongString(string,num);
                 });
           	 	var html = template.render('showRemark', data);
                this.element.html(html);
           });

       	 })
   	   })
   }

  $(function () {

    seajs.use(["pagination","moment","template"], function (Pagination,moment,template) {
    	template.helper('$formatDate', function (millsec) {
           	return moment(millsec).format("YYYY-MM-DD HH:mm");
       	});
       	template.helper('$hanleColor',function(colorIndex){
       		return colorArr[colorIndex-1];
       	});
        template.helper('$trimLongString', function (string,num) {
            return trimLongString(string,num);
        });
		template.helper('$formatMoney', function (num) {
    		num = num == null ? 0 : num;
            return num.toFixed(2);
        });
        page = new Pagination({
             url: "${ctx}/brand/order/purorder/data",
             elem: "#pagination",
             form:$("#orderForm"),
             method:"post",
             handleData: function (json) {
                 var html = template.render("feedback-templage", json);
                 $(".orderbox_contain").html(html);
//                 if(json.rows.length == 0){
//                     $(".checkallbox").hide();
//                 }
                 mouseRemarks();
             }
         });

        $(".page-top").on("click",function(ev){
            ev.preventDefault();
            page.goPrev();
        });

        $(".page-down").on("click",function(ev){
            ev.preventDefault();
            page.goNext();
        });

        $("#nosee-order").on("click",function(){
        	if($(this)[0].checked){
        		$("#isShowClose").val("1");
        	}else{
        		$("#isShowClose").val("");
        	}
        	page.render();
        });

        $("#sample-order").on("click", function () {
            if ($(this)[0].checked) {
                $("#isSampleOrder").val("true");
            } else {
                $("#isSampleOrder").val("");
            }
            page.render();
        });

       });
     });


     TradePurorder.confirmUpdatePop = function(orderID,type,dailog,discount,payState,brandsId){
      	if( discount > 10){
     		remind("error","折扣率不正确, 请重新输入");
     		return ;
     	}
        if(type == 'price'){			//修改金额
			$('#updatePrice input[name="refrenceId"]').val(orderID);
			$('#updatePrice input[name="brandsId"]').val(brandsId);
			      $.ajax({
					type:"post",
					url:"${ctx}/brand/order/modPrice",
					data : $('#updatePrice').serialize(),
					traditional:true,
					dataType: "json",
					success:function(json){
						if(json.code == zttx.SUCCESS){
							remind("success","修改成功！");
							dailog.hide();
							page.render();
						}else{
							remind("error",json.message);
						}
					}
				});
        }else if(type == 'fare'){			//修改运费
            var _val = $(".wuliuprice").val();
            if(_val == "" ){
            	remind("error","请输入物流费");
                return false;
            }
            if(_val == 0 && payState==2){
                confirmDialog("终端商已全部付款，将运费设为0，终端商将无需支付运费，是否继续？",function(){
                	TradePurorder.updatePop(orderID, dailog);
                });
            }
            else
            	TradePurorder.updatePop(orderID, dailog);
        	}
       };

       TradePurorder.updatePop = function(orderID,dialog,brandsId){
       		$('#updatePrice input[name="refrenceId"]').val(orderID);
       		$('#updatePrice input[name="brandsId"]').val(brandsId)
            $.ajax({
                type:"post",
                url:"${ctx}/brand/order/modFare",
                data : $('#updatePrice').serialize(),
                traditional:true,
                dataType: "json",
                success:function(json){
                    if(json.code == zttx.SUCCESS){
                        remind("success","修改成功！");
                        dialog.hide();
                        page.render();
                    }else{
                        remind("error",json.message);
                    }
                }
            });
       };

      //关闭订单
     TradePurorder.closeTradeFn = function(orderId,dialog,brandsId,brandsName){
     	$('#closeOrder input[name="orderId"]').val(orderId);
     	$('#closeOrder input[name="brandsId"]').val(brandsId);
     	$('#closeOrder input[name="brandsName"]').val(brandsName);
     	  $.ajax({
				type:"post",
				url:"${ctx}/brand/order/closeOrder",
				data : $('#closeOrder').serialize(),
				traditional:true,
				dataType: "json",
				success:function(json){
					if(json.code == zttx.SUCCESS){
						remind("success","修改成功！");
						dialog.hide();
						page.render();
					}else{
						remind("error",json.message);
					}
				}
			});
		}

         //站内信
         TradePurorder.sendMessageFn = function (dealerId, brandName, dialog ){
         	var title = "来自"+brandName+"信息";
          	var content = $.trim($('#message_text').val());
          	if(content == ''){
          		alert("内容不能为空");
          		return ;
          	}
          	if($.trim(dealerId) == ''){
          		return ;
          	}
       		  $.ajax({
				type:"post",
				url:"${ctx}/brand/message/sendDealer",
				data :{dealerIds : dealerId, title : title, content : content},
				traditional:true,
				dataType: "json",
				success:function(json){
					if(json.code == zttx.SUCCESS){
						remind("success","发送成功！");
						dialog.hide();
					}else{
						remind("error",json.message);
					}
				}
			});
       }

          //延长收货期限
        TradePurorder.extensionTimeFn = function (orderId, dialog){
        	$('#outActTime input[name="refrenceId"]').val(orderId);
       		 $.ajax({
				type:"post",
				url:"${ctx}/brand/order/outActTime",
				data : $('#outActTime').serialize(),
				traditional:true,
				dataType: "json",
				success:function(json){
					if(json.code == zttx.SUCCESS){
						remind("success","修改成功！");
						dialog.hide();
						page.render();
					}else{
						remind("error",json.message);
					}
				}
			});
       }

      //备注
       TradePurorder.handleRemarkFn = function (current_obj,orderIds,dialog){
       		var levelMark = $.trim($('#levelMark input[name="leven"]:checked').val());

       		if(levelMark < 1 || levelMark > 5){
       			alert("没有该星级");
       			return ;
       		}
       		var brandRemark = $.trim($('#remark').val());
       		if(brandRemark.length == ''){
       			alert("备注的内容不能为空!");
       			return ;
       		}



       		$.ajax({
				type:"post",
				url:"${ctx}/brand/order/remark",
				data : {orderIds : orderIds,levelMark : levelMark, brandRemark : brandRemark },
				dataType: "json",
				success:function(json){
					if(json.code == zttx.SUCCESS){
						remind("success","备注成功！");


						if(!current_obj){

							dialog.hide();

							page.render();

							return;
						}

						var star = current_obj.find(".red-star");

						star.css({
							color: colorArr[levelMark-1]
						})
						star.show();
						current_obj.data({
							"color": colorArr[levelMark-1],
							"text": brandRemark,
							"colorindex": levelMark
						})
						mouseRemarks();
						dialog.hide();
					}else{
						remind("error",json.message);
					}
				}
			});
       }

     //批量免邮
     TradePurorder.handleShippingFn = function (orderIds,dialog){
     		$.ajax({
				type:"post",
				url:"${ctx}/brand/order/avoidMail",
				data : {orderIds : orderIds},
				dataType: "json",
				success:function(json){
					if(json.code == zttx.SUCCESS){
						remind("success","操作完成！");
						dialog.hide();
						page.render();
					}else{
						remind("error",json.message);
					}
				}
			});
     }

     TradePurorder.sendGoods = function(orderId, refundStatus){

     	if(1==refundStatus || 2==refundStatus || 3==refundStatus)
     	{
	     	confirmDialog("退款申请中，确认继续发货吗？",function(){
	            window.location.href="${ctx}/brand/order/sendGoods/"+orderId;
	        });
        }else if(4==refundStatus){
            confirmDialog("已拒绝退款，确认继续发货吗？",function(){
                window.location.href="${ctx}/brand/order/sendGoods/"+orderId;
            });
        }else{
        	window.location.href="${ctx}/brand/order/sendGoods/"+orderId;
        }
     }

     TradePurorder.init();

	$('#checkall').on('click', function (){
        var checked = $(this).prop("checked");
        $('.orderbox input[type="checkbox"]').each(function(){
            $(this).prop("checked",checked);
        })
	});

   $('.orderbox_contain').on("click",'input[type="checkbox"]',function(){

       if(!$(this).prop("checked")){
           $('#checkall').prop("checked",false);
       }

   })

</script>
</body>
</html>