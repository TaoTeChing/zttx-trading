<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<h3 class="title">资料下载</h3>
<div class="datadownload font">
	<c:choose>
		<c:when test="${isLogin}">
			<ul class="datadownloadul">
		    	<li class="selected">
		    		<a href="javascript:;">
		    			<span>全部资料</span>
		    		</a>
		    	</li>
		    	<c:forEach var="obj" items="${doccateList}">
		    		<li>
		    			<a href="javascript:;" _cateId="${obj.refrenceId}">
		    				<span>${obj.cateName}</span>
		    			</a>
		    		</li>
		    	</c:forEach>
		    </ul>
		    <div class="downbodyall"></div>
		    <div class="pages f-r clear"></div>
		</c:when>
		<c:otherwise>
			<h3 style="padding-top: 20px;"><a href="${ctx}/common/login">请先登录！</a></h3>
		</c:otherwise>
	</c:choose>
</div>
<script id="ajax-templage-datadownload" class="ajax-templage" _url="${ctx}/market/brand/listDocument?brandsId=${brandesId}&brandId=${brandId}" _page=".datadownload .pages" _pid=".datadownload .downbodyall" type="text/html">
{{each rows}}
<div class="downbody clear">
	<h2 class="title">
		{{$value.docName}}
		{{if $value.docOpen == 0}}
			<span class="auth">授权下载</span>
		{{else}}
			<span class="whole">全网开放</span>
		{{/if}}
	</h2>
	<p class="time fontcolor9">
		<%--<span>大小：10.0M</span> --%>
		<span>上传时间：{{$formatDate $value.createTime}}</span>
		<span>下载次数：{{$value.downNum}}次</span>
	</p>
	<p class="des">{{$value.docMark}}</p>
	<p>
		{{if $value.docnFile != null && $value.docnFile != ""}}
			<a class="button download" data-id="{{$value.refrenceId}}" href="${res}{{$value.domainName + $value.docnFile}}" target="_blank">本地下载</a>
		{{/if}}
		{{if $value.webAddress != null && $value.webAddress != ""}}
			<a class="button download" data-id="{{$value.refrenceId}}" href="{{$value.webAddress}}" target="_blank">网盘下载</a>
		{{/if}}
		{{if $value.docPass != null && $value.docPass != ""}}
			<span class="fontcolor9">解压包密码：{{$value.docPass}}</span>
		{{/if}}
	</p>
</div>
{{/each}}
</script>