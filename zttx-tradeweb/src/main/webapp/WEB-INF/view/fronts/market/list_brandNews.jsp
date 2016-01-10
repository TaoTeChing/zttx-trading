<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<h3 class="title">品牌新闻</h3>
<div class="comnews font">
    <ul class="comnewsul">
        <li class="selected"><a href="javascript:;" _newsType="0">最新发布</a></li>
        <li><a href="javascript:;" _newsType="1">热点资讯</a></li>
    </ul>
    <div class="comnewsbodyall"></div>
    <div class="pages f-r clear"></div>
    <div class="clear"></div>
</div>
<script id="ajax-templage-comnews" class="ajax-templage" _url="${ctx}/market/brand/listNews?brandesId=${brandesId}" _page=".comnews .pages" _pid=".comnews .comnewsbodyall" type="text/html">
{{each rows}}
<div class="comnewsbody clear">
	<div class="timecla f-l">
		<p class="time">{{$formatDate $value.createTime}}</p>
	    <p class="classify">
			<a href="javascript:;">{{$value.cateName}}</a>
		</p>
	</div>
	<dl class="comnewsbodydl">
		<dd>
			<!--
			<div class="imgbox f-l">
				<a href="${ctx}/market/viewBrandNewsInfo/${brandesId}/${brandId}/{{$value.refrenceId}}" target="_blank">
					<img src="/images/market/temp/comnews.jpg" />
				</a>
			</div>
			-->
		</dd>
		<dt class="title">
			<a href="${ctx}/market/viewBrandNewsInfo/${brandesId}/${brandId}/{{$value.refrenceId}}" target="_blank">{{$value.newsTitle}}</a>
		</dt>
		<dd class="des fontcolor6">{{$value.newsSummary}}</dd>
	    <dd class="details">
			<a href="/viewBrandNewsInfo/${brandesId}/${brandId}/{{$value.refrenceId}}" target="_blank">查看详情 ></a>
		</dd>
	</dl>
</div>
{{/each}}
</script>