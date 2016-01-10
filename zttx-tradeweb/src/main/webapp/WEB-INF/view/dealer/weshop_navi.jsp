<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<div class="weshop-nav">
	<ul>
		<li><a href="${ctx }/dealer/weshop/product/store?shopId=${shopId}"    class="i-store ${param.tab==1?'active':''}" > 约逛仓库 </a></li>
		<li><a href="${ctx }/dealer/weshop/product/index?shopId=${shopId}" class="i-publish-goods ${param.tab==2?'active':''}">发布商品</a></li>
		<li><a href="${ctx }/dealer/weshop/product/buyed?shopId=${shopId}" class="i-leading-in ${param.tab==3?'active':''}">导入商品</a></li>
		<li><a href="${ctx }/dealer/weshop/trends?shopId=${shopId}"  class="i-trends ${param.tab==4?'active':''}">约逛动态管理</a></li>
		<li><a href="${ctx }/dealer/weshop/config?shopId=${shopId}"  class="i-manage ${param.tab==5?'active':''}">微店设置</a></li>
	</ul>
</div>