<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!-- 面包导航，并不是每个页面都有 -->
<style>
	.anency-seeking-form .ui-form-item{
		width: auto;
	}
    .anency-seeking-form .ui-form-item .ui-label{
        float: none;
    }
    .anency-seeking-form .ui-form-item .ui-select{
        width: 113px;
    }
</style>
<div class="bread_nav">
	<div class="fl">
		<a class="link" href="${ctx}/brand/center">管理中心</a>
		<span class="arrow">&gt;&gt;</span>
		<a class="link" href="${ctx}/brand/dealer/listDealerApply">终端商管理</a>
		<span class="arrow">&gt;</span>
		<span class="current">
			<%--<c:if test='${param.m==0}'>平台推荐的终端商</c:if>--%>
			<c:if test='${param.m==1}'>邀请中的终端商</c:if>
			<c:if test='${param.m==2}'>申请中的终端商</c:if>
			<c:if test='${param.m==3}'>收藏的终端商</c:if>
			<c:if test='${param.m==4}'>我浏览过的终端商</c:if>
			<c:if test='${param.m==5}'>浏览过我的终端商</c:if>
			<c:if test='${param.m==6}'>我拒绝的终端商</c:if>
			<c:if test='${param.m==7}'>寻找终端商</c:if>
		</span>
	</div>
	<div class="fr">
		<jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
	</div>
</div>
<form:form action="${ctx}/brand/dealer/listDealerGroom" id="dealerSearchForm">

<div class="ui-form anency-seeking-form clearfix">
    <input type="hidden" name="attrType" id="attrType" value="">
    <input type="hidden" name="descType" id="descType" value="">
	<div class="ui-form-item">
		<label for="" class="ui-label"> 实体店经验： </label>
		<div class="inline-block">
			<select  class=" js_select seeking-select-width" name="shopExperi" id="Aseeking-select-exp">
				<option value="0" >请选择</option>
				 <c:if test="${shopSuffer != null }">
                   <c:forEach items="${shopSuffer }" var="suffer">
                   	 <option value="${suffer.dictValue }" ${searchModel.shopExperi == suffer.dictValue ? 'selected' : '' }>${suffer.dictValueName }</option>
                   </c:forEach>
                 </c:if>

			</select>
		</div>
	</div>
    <div class="ui-form-item">
        <label for="" class="ui-label"> 员工数量： </label>
        <div class="inline-block">
            <select  class=" js_select seeking-select-width" name="employeeNum">
                <option value="0"  >请选择</option>
                <c:if test="${employeeNum != null }">
                    <c:forEach items="${employeeNum }" var="num">
                        <option value="${num.dictValue }" ${searchModel.employeeNum == num.dictValue ? 'selected' : '' }>${num.dictValueName }</option>
                    </c:forEach>
                </c:if>

            </select>
        </div>
    </div>
    <div class="ui-form-item">
        <label for="" class="ui-label"> 月销售额：</label>
        <div class="inline-block">
            <select class=" js_select seeking-select-width" name="saleTotal"
                    id="Aseeking-select-class">
                <option value="0"  >请选择</option>
                <c:if test="${monthMoney != null }">
                    <c:forEach items="${monthMoney }" var="money">
                        <option value="${money.dictValue }" ${searchModel.saleTotal == money.dictValue ? 'selected' : '' }>${money.dictValueName }</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
    </div>
	<div class="ui-form-item" style="width: 326px;">
		<label for="" class="ui-label"> 实体店有无： </label>
		<div class="inline-block">
			<select  class=" js_select seeking-select-width" name="shopNumByFind">
				 <option value="0"  >请选择</option>
				<c:if test="${shopNum != null }">
                   <c:forEach items="${shopNum }" var="num">
                   	 <option value="${num.dictValue }" ${searchModel.shopNumByFind == num.dictValue ? 'selected' : '' }>${num.dictValueName }</option>
                   </c:forEach>
                 </c:if>
			</select>
		</div>
	</div>
	<div class="ui-form-item">
		<label class="ui-label"> 所在地区： </label>
		<jsp:include page="${ctx}/common/regional/searchAllArea">
			<jsp:param value="${searchModel.province}" name="regProvince"/>
			<jsp:param value="${searchModel.city}" name="regCity"/>
			<jsp:param value="${searchModel.county}" name="regCounty"/>
			<jsp:param value="ui-select js_select seeking-select-width" name="style" />
			<jsp:param value="test1" name="sale" />
		</jsp:include>
	</div>
	<div class="ui-form-item" style="width: 326px;">
		<label for="" class="ui-label"> 经营品类： </label>
		<div class="inline-block">
			<select  class=" js_select seeking-select-width" name="dictValue">
				 <option value="">请选择</option>
				<c:if test="${not empty dealList}">
                   <c:forEach items="${dealList}" var="dealDic">
                   	 <option value="${dealDic.dealNo}">${dealDic.dealName}</option>
                   </c:forEach>
                 </c:if>
			</select>
		</div>
	</div>
	<c:if test="${param.m==2}">
	<div class="ui-form-item" style="width: 59px;line-height: 30px;">
        <input type="checkbox" value="true" name="isExitsInquiry"> 询价单
	</div>
	</c:if>
	<c:if test="${param.m==7}">
    <div style="width: auto" class="ui-form-item">
        <label for="" class="ui-label"> 终端商名称： </label>
        <input style="width: 120px;" name="dealerName" class="ui-input" type="text"/>
    </div>
	</c:if>
	<div class="ui-form-item" style="width: 100px;">
		<div class="inline-block">
			<input type="button" value="查询" class="ui_button_myourself" onclick="dealerSearchBut();" />
		</div>
	</div>
</div>
</form:form>
<script>
function dealerSearchBut(){
	page.goTo(1);
}
</script>
