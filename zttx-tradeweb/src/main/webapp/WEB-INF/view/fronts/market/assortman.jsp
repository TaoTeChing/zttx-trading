<!DOCTYPE html><%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/include/taglib.jsp"  %>
<html>
<jsp:include page="/WEB-INF/view/market/brandmarket/brand_edit/brand_edit_head.jsp" />
<body>
<jsp:include page="/WEB-INF/view/market/brandmarket/brand_edit/brand_edit_k_top.jsp" />
<div class="k-body">
    <div class="k-left f-l">
    		<h1 class="k-title">分类管理</h1>
            <h2 class="k-lefth2"><i class="k-hico"></i>基础页</h2>
            <ul class="k-leftul">
                <li class="selected"><a href="assortman.jsp" title="" target="">分类管理</a></li>
                <li><a href="#" title="" target="">宝贝管理</a></li>
            </ul>
    </div>
    <div class="k-right f-l">
    	<div class="shousuo"></div>
    	<div class="k-method"><span class="k-addmaintree">添加分类</span></div>
    	<iframe id="testIframe" name="mainframe" marginwidth="0" marginheight="0" src="sortmanage" frameborder="0" width="100%" height="100%" scrolling="auto"></iframe>
    </div>
    <div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/view/market/brandmarket/brand_edit/brand_edit_foot.jsp" />
</body>
</html>