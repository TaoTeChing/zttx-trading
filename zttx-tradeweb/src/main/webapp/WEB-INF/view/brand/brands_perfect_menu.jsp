<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="select_brand_area ui-form-item" style="margin-left: -69px;">
	<label class="ui-label">选择品牌：</label>
	<div class="inline-block">
		<select class="js_select" id="brandsId" name="brandsId"
			required data-errormessage-required="请选择品牌...">
			<option value="">请选择品牌...</option>
			<c:forEach items="${brandes}" var="brand">
				<option value="${brand.refrenceId}" <c:if test="${brand.refrenceId == id}">selected</c:if>>${brand.brandsName}</option>
			</c:forEach>
		</select>
	</div>
</div>
<div class="simple_tab">
    <ul class="js_tab_box inline-float">
        <li class="item <c:if test="${param.m==1}">current</c:if>">
            <a href="${ctx}/brand/brandRoom/execute?id=${id}">品牌基本资料</a>
        </li>
        <li class="item <c:if test="${param.m==2}">current</c:if>">
            <a href="${ctx}/brand/brandRecruit/execute?id=${id}">品牌招募书</a>
        </li>
        <li class="item <c:if test="${param.m==3}">current</c:if>">
            <a href="${ctx}/brand/brandNetwork/execute?id=${id}">门店展示</a>
        </li>
        <li class="item <c:if test="${param.m==4}">current</c:if>">
            <a href="${ctx}/brand/brandVisual/visual?id=${id}">陈列视觉</a>
        </li>
        <li class="item <c:if test="${param.m==5}">current</c:if>">
            <a href="${ctx}/brand/brandesInfoRegional/view?id=${id}">区域设置</a>
        </li>
    </ul>
</div>