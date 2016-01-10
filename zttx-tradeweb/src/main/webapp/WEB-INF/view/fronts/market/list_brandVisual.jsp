<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<div class="all-prodisshow">
    <div class="prodisshow clear">
        <ul class="virtual-tit">
			<li<c:if test="${param.m=='1'}"> class="selected"</c:if> _url="${ctx}/market/brand/visionImage?brandId=${brandId}&brandesId=${brandesId}"><a href="javascript:;" title="" target="" class="virtual-tit1 vir">图片展示</a></li>
			<li<c:if test="${param.m=='2'}"> class="selected"</c:if> _url="${ctx}/market/brand/visionVideo?brandId=${brandId}&brandesId=${brandesId}"><a href="javascript:;" title="" target="" class="virtual-tit2 vir">视频展示</a></li>
			<li<c:if test="${param.m=='3'}"> class="selected"</c:if> _url="${ctx}/market/brand/vision3D?brandId=${brandId}&brandesId=${brandesId}"><a href="javascript:;" title="" target="" class="virtual-tit3 vir">3D展厅</a></li>
		</ul>
        <div class="virtual clear">
        </div>
    </div>
</div>