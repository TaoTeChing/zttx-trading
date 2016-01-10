<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>管理中心-产品管理-发布新产品</title>
<link rel="stylesheet"
	href="${res}/styles/brand/global.css" />
<link rel="stylesheet" href="${res}/styles/brand/productmanag.css" />
</head>
<body>
	<jsp:include page="/common/menuInfo/mainmenu" />
	<div class="main layout">
		<jsp:include page="/common/menuInfo/sidemenu" />
		<div class="main_con">
			<!-- 面包导航，并不是每个页面都有 -->
			<div class="bread_nav">
				<div class="fl">
					<a class="link" href="/brand/center">管理中心</a> <span class="arrow">&gt;&gt;</span>
					<a class="link" href="${ctx}/brand/product/execute">产品管理</a> <span
						class="arrow">&gt;</span> <span class="current">发布新产品</span>
				</div>
				<div class="fr">
					<jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
				</div>
			</div>
			<div class="inner">
				<!-- 内容都放这个地方  -->
				<div class="productadd">
					<div class="v2-steps v2-3steps" style="margin-left: 94px;">
						<span class="text1 current">选择类目</span> <span class="text2">添加资料</span>
						<span class="text3">发布成功</span>
						<%--<span class="text4">加入产品线</span>--%>
						<div class="steps v2-steps-1"></div>
					</div>
					<div class="productadd-selectcate">
						<div class="tips">
							<i class="v2-icon-explain"></i>说明：您可根据产品的实际情况，选择产品分类，单个产品可加入多个分类
						</div>
						<form:form cssClass="ui-form" action="/brand/product/addnew"
							method="post" id="selectDicFrm">
							<input type="hidden" name="id" id="id" value="${param.id}" />
							<input type="hidden" name="operType" value="${param.operType}"/>
							<div class="ui-form-item">
								<label class="ui-label"> 您最近使用的类目：<span
									class="ui-form-required">&nbsp;</span> </label>
								<div class="inline-block">
									<select class="ui-select js_select" name="selectadd"
										onchange="dicSelectChange(this);" id="selectadd"
										style="width: 410px; height: 30px">
										<option value="0">请选择</option>
										<c:if test="${!empty dicList}">
											<c:forEach items="${dicList}" var="dic">
												<option value="${dic.dealNo}">${dic.dealName}</option>
											</c:forEach>
										</c:if>
									</select>
								</div>
							</div>
							<div class="ui-form-item">
								<label class="ui-label"> 产品分类：<span
									class="ui-form-required">*</span> </label>
								<div class="inline-block product_area">
									<ul class="inline-float">
										<li id="select_category" class="select_category">
											<ul class="select_inner">

											</ul></li>
										<li id="select_class" class="select_class">
											<ul class="select_inner">

											</ul></li>
										<li id="select_product" class="select_product">
											<ul class="select_inner">
												<li class="item"></li>
											</ul></li>
									</ul>
								</div>
							</div>
							<div class="ui-form-item">
								您当前选择的是： <a href="javascript:;" class="selectcatemain"></a> <a
									class="arrow-select" style="display: none">&gt;</a> <a
									href="javascript:;" class="selectcatesub"></a> <a
									href="javascript:;" class="selectcateproduct"></a>
							</div>
							<div class="ui-form-item">
								<label class="ui-label"> </label> <a
									href="javascript:formSubmit();"
									class="ui_button ui_button_lblue">我已熟悉产品发布的相关规则，现在发布产品</a> <a
									href="${ctx}/help/index" class="bluefont ml15" target="_blank"
									style="display:none;">产品发布规则</a>
							</div>
							<input name="dealNo" id="dealNo" type="hidden">
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
	<script src="${src}/brand/productmanage.js"
		type="text/javascript"></script>
	<script type="text/javascript">
	
    var _dealList = ${dealList};
    selectcate.init();
    function formSubmit(sel){
        var dealno = null;
        if(sel){
            dealno = sel;
        }else{
            dealno = $("#select_product li").data("dealno");
        }
        if(dealno != null && dealno != undefined){
            $("#dealNo").val(dealno);//逻辑判断等
            $("#selectDicFrm").submit();
        }else{
            remind("error","请选择类目");
            return;
        }
    }
    function dicSelectChange(sel){
        var dealNo = $(sel).val();
        if(dealNo != 0){
            formSubmit(dealNo);
        }
    }

    function selectDealNo(dealNo){
        if(dealNo == ""){
            return;
        }
        var no1 = dealNo.substring(0, 3) + "000000";
        $("#" + no1).click();
        $("#" + dealNo).click();
    }

    $(function(){
        var dealNo = "${param.dealNo}";
        selectDealNo(dealNo);
    });
</script>
</body>
</html>