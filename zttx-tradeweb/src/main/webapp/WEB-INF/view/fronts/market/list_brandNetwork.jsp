<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<h3 class="title">门店展示</h3>
<div class="network">
    <ul class="networkul"></ul>
    <div class="pages f-r clear"></div>
</div>                                              
<script class="ajax-templage" _url="${ctx}/market/brand/listNetwork?brandesId=${brandesId}" _page=".network .pages" _pid=".networkul" type="text/html">
{{each rows}}
<li>
	<div class="imgbox">
		<a href="{{$value.domain + $value.path}}" title="{{$value.name}}" data-lightbox="netpicgroup">
			<img src="{{$value.domain + $value.path}}" class="f-l" width="100%" height="100%" />
		</a>
	</div>
	<div class="title">{{$value.name}}</div>
	<div class="address">地址：{{$value.addr}}</div>
</li>
{{/each}}
</script>
<div class="network-bigimgshow" style="">
    <ul class="network-bigimgshow-ul"></ul>
    <div class="js-network-close"></div>
    <img src="" alt="">
    <a href="javascript:;" class="network-bigimgshow-move js-network-prev">prev</a>
    <a href="javascript:;" class="network-bigimgshow-move js-network-next">next</a>
</div>