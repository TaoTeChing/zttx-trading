<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!-- 面包导航，并不是每个页面都有 -->
	<div class="bread_nav">
        <div class="fl">
            <i class="icon"></i>
            <a class="link" href="${ctx}/brand/center">管理中心</a>
            <span class="arrow">&gt;&gt;</span>
            <span class="current">我的财务</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
        </div>
    </div>

    <div class="account_area">
        <div class="clearfix">
            <label for="">当前账户余额：</label>
            <div class="info">
                <div class="clearfix">
                    <span class="price fl">
                        <span class="font_arial">${empty brandBalance.balance?0.00: brandBalance.balance}</span>元
                    </span>
                    <a class="recharge_btn fl" href="${ctx}/brand/center/charge">充&nbsp;值</a>
                    <a class="withdrawal_btn fl" href="${ctx}/brand/brandDrawing/execute">提&nbsp;现</a>
                </div>
                <p class="current_balance">
                    当前可用余额：<span id="id_accountBalance">${empty brandBalance.accountBalance?0.00: brandBalance.accountBalance }</span>元（
                    <span class="freeze_cash">冻结金额：<span id="id_frozeMoney">${empty brandBalance.frozeMoney?0.00: brandBalance.frozeMoney}</span>元</span>）
                    <input type="hidden" id="id_amount" value="${amount}" />
                </p>
            </div>
        </div>
    </div>

    <div class="ui_tab main_ui_tab">
        <ul class="ui_tab_items">
        	<li class="ui_tab_item<c:if test="${param.m=='1'}"> current</c:if>"><a href="${ctx}/brand/tradedetails/list/in">收入交易</a></li>
        	<li class="ui_tab_item<c:if test="${param.m=='2'}"> current</c:if>"><a href="${ctx}/brand/tradedetails/list/out">支出交易</a></li>
        	<li class="ui_tab_item<c:if test="${param.m=='3'}"> current</c:if>"><a href="${ctx}/brand/tradedetails/list/business">交易记录</a></li>
        	<li class="ui_tab_item<c:if test="${param.m=='7'}"> current</c:if>"><a href="${ctx}/brand/tradedetails/list/drawals">提现记录</a></li>
            <li class="ui_tab_item<c:if test="${param.m=='4'}"> current</c:if>"><a href="${ctx}/brand/bank/list">银行卡设置</a></li>
            <li class="ui_tab_item<c:if test="${param.m=='6'}"> current</c:if>"><a href="${ctx}/brand/brandDrawing/execute">提现</a></li>
        </ul>
        
        <%--<div class="download">
            <i class="iconfont">&#xe620;</i>下载账单：
            <a class="link" href="javascript:;">Excel格式</a>
            <a class="link" href="javascript:;">Txt格式</a>
        </div>--%>
    </div>