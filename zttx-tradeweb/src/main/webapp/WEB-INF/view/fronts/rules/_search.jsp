<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<div class="search-help">
    <div class="search-help-box">
        <form id="searchForm" action="${ctx}/rules/list">
            <input name="q" placeholder="请输入您要搜索的关键字" value="${filter.q}"><span class="span-select">所有</span><button type="submit" id="searchButton">搜 索</button>
            <input type="hidden" name="cateId" value="${filter.cateId}">
            <div class="span-select-item hide">
                <a data-id="" href="javascript:;">所有</a>
                <a data-id="A6CFFF0106F2469A8CBEC37B3F5DAF79" href="javascript:;">管理总则</a>
                <a data-id="0749E8A0625345CB9689FCE0D6887DA3" href="javascript:;">服务协议</a>
                <a data-id="EA73FFBDCD6243B4ADFDD24B128D0664" href="javascript:;">交易规则</a>
            </div>
        </form>
    </div>
</div>
