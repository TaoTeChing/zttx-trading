<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%@ page  language="java"  pageEncoding="UTF-8" %>
<div class="search-help">
    <div class="search-help-box">
        <form id="searchForm" action="${ctx}/help/list">
        	<input name="q" placeholder="请输入您要搜索的关键字" value="${filter.q}"><span class="span-select">所有</span><button type="submit" id="searchButton">搜 索</button>
            <input type="hidden" name="cateId" value="${filter.cateId }">
            <div class="span-select-item hide">
                <a data-id="" href="javascript:;">所有</a>
                <a data-id="42110AB01FAE4E28B90AFB061A63ABF3" href="javascript:;">品牌商专区</a>
                <a data-id="CB54FA04ECE94139B045E72CA0DFE2AB" href="javascript:;">终端商专区</a>
                <a data-id="1162836017D541B9883BD74A4F2A6723" href="javascript:;">支付流程</a>
            </div>
        </form>
    </div>
</div>