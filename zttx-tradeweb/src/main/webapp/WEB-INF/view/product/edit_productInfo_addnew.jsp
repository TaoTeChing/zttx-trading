<!DOCTYPE html>
<%@ page import="com.zttx.web.consts.ProductConsts" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<c:set var="productcate_stock" value="<%=ProductConsts.CATE_STOCK.getKey()%>"></c:set>
<c:set var="productcate_order" value="<%=ProductConsts.CATE_ORDER.getKey()%>"></c:set>
<c:set var="orderSelect_need" value="<%=ProductConsts.ORDER_SELECT_NEED.getKey()%>"></c:set>
<c:set var="orderSelect_notNeed" value="<%=ProductConsts.ORDER_SELECT_NOT_NEED.getKey()%>"></c:set>
<c:set var="carry_dealer" value="<%=ProductConsts.CARRY_DEALER.getKey()%>"></c:set>
<c:set var="carry_brand" value="<%=ProductConsts.CARRY_BRAND.getKey()%>"></c:set>
<c:set var="beginType_first" value="<%=ProductConsts.BEGIN_TYPE_FIRST.getKey()%>"></c:set>
<c:set var="beginType_set" value="<%=ProductConsts.BEGIN_TYPE_SET.getKey()%>"></c:set>
<c:set var="beginType_store" value="<%=ProductConsts.BEGIN_TYPE_STORE.getKey()%>"></c:set>
<c:set var="groom_false" value="<%=ProductConsts.GROOM_FALSE.getKey()%>"></c:set>
<c:set var="groom_true" value="<%=ProductConsts.GROOM_TRUE.getKey()%>"></c:set>
<c:set var="title" value="发布新产品"></c:set>
<c:if test="${productInfo.operType!='copy'}">
	<c:if test="${!empty productInfo.refrenceId}">
	    <c:set var="title" value="编辑产品"></c:set>
	</c:if>
</c:if>
<c:if test="${productInfo.operType=='copy'}">
	<c:set var="title" value="复制产品"></c:set>
</c:if>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-产品管理-${title}</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/productmanag.css"/>
    <style>
        .ui-form-item-success .ui-form-othertiop {
            line-height: 30px;
            padding-left: 20px;
            background: url("${res}/images/common/right.png") left center no-repeat;
            color: #85c749;
            display: inline-block;
            *display: inline;
            *zoom: 1;
        }
        .ui-form-othertiop, .ui-form-item-error .ui-form-othertiop {
            display: none;
        }


        .ui-form-item-loading .ui-form-explain {
            line-height: 30px;
            padding-left: 20px;
            background: url("${res}/images/common/loading-16-16.gif") left center no-repeat;
        }
    </style>
    <!--[if lte IE 8]>
    <style>
        .edui-body-container {
            height: 500px;
        }
    </style>
    <![endif]-->
    <jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
</head>
<body>
<jsp:include page="/common/menuInfo/mainmenu"/> 
	<div class="main layout">
		 <jsp:include page="/common/menuInfo/sidemenu"/>
    <div class="main_con">
        <!-- 面包导航，并不是每个页面都有 -->
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <a class="link" href="${ctx}/brand/product/execute">产品管理</a>
                <span class="arrow">&gt;</span>
                <span class="current">${title}</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
                    <jsp:param value="0" name="isShow"/>
                </jsp:include>
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <div class="productadd">
                <%--第二版步骤条 第二步--%>
                <div class="v2-steps v2-3steps" style="margin-left: 94px;">
                    <span class="text1 current">选择类目</span>
                    <span class="text2 current">添加资料</span>
                    <span class="text3">发布成功</span>
                    <%--<span class="text4">加入产品线</span>--%>
                    <div class="steps v2-steps-2"></div>
                </div>
                <form:form cssClass="ui-form productadd-form" data-widget="validator" action="" method="post">
                    <c:if test="${productInfo.operType!='copy'}">
                    	<input type="hidden" name="refrenceId" value="${productInfo.refrenceId}">
                    </c:if>
                    <input type="hidden" name="dealNo" value="${productInfo.dealNo}">
                    <input type="hidden" name="cateIds" value="${cateIds}" id="cateIds">
                    <input type="hidden" name="brandsName" value="${productInfo.brandsName}" id="brandsName">
                    <input type="hidden" name="opType" id="opType"/>
                    <div class="ui-form-item">
                        <label class="ui-label"> 当前产品资料完善度： </label>
                        <div class="jindutiao">
                            <div class="comple"></div>
                        </div>
                    </div>
                    <div class="impoBar">
                        <i class="productmanege_icon PD_o"></i>产品基本资料
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label"> 类目信息： </label>
                        <div class="ui-form-noEdit">${dealNames}
                        	<c:if test="${empty hasValidOrder or !hasValidOrder}">
                            	<a class="bluefont ml10" href="${ctx}/brand/product/create/choose/dealdic?dealNo=${productInfo.dealNo}&id=${productInfo.refrenceId}&operType=${productInfo.operType}">修改</a>
                            </c:if>
                        </div>
                        <input type="hidden" name="dicError" id="dicEror" value="1"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label"> 品牌选择：<span class="ui-form-required">*</span> </label>
                        <div class="inline-block">
                           <c:if test="${!empty productInfo.refrenceId}"> 
                           		<select class="ui-select js-select to-omple ppchoice" id="ppchoice"  disabled>
	                                <c:forEach items="${brandesInfoList}" var="brandes">
	                                    <option value="${brandes.refrenceId}" <c:if test="${brandes.refrenceId == productInfo.brandsId}">selected="selected" </c:if>>${brandes.brandsName}</option>
	                                </c:forEach>
	                            </select>
	                            <input type="hidden" name="brandsId" value="${productInfo.brandsId}"/>
                            </c:if>
                            <c:if test="${empty productInfo.refrenceId}"> 
                           		<select class="ui-select js-select to-omple ppchoice" name="brandsId" id="ppchoice" >
	                                <c:forEach items="${brandesInfoList}" var="brandes">
	                                    <option value="${brandes.refrenceId}" <c:if test="${brandes.refrenceId == productInfo.brandsId}">selected="selected" </c:if>>${brandes.brandsName}</option>
	                                </c:forEach>
	                            </select>
                            </c:if>
                        </div>
                    </div>
                    <input value="${productcate_stock}" class="ui-radio now-pro owner" name="productCate" type="hidden"/>
                    <%--<div class="ui-form-item">
                        <label class="ui-label"> 产品类型：<span class="ui-form-required">*</span> </label>
                        <div class="radio_box inline-block">
                            <label>
                                <input <c:if test="${empty productInfo.productCate || productInfo.productCate==productcate_stock}">checked="checked"</c:if>
                                    value="${productcate_stock}" class="ui-radio now-pro owner" name="productCate" type="radio"/>现货产品
                            </label>
                            <label>
                                <input <c:if test="${productInfo.productCate==productcate_order}">checked="checked"</c:if>
                                value="${productcate_order}" class="ui-radio ml5 yuding-pro owner" name="productCate" type="radio" id="productCate"/>预订产品
                            </label>
                        </div>
                    </div>
                    <div class="proman-templetedit" <c:if test="${productInfo.productCate==null || productInfo.productCate==productcate_stock}">style="display: none;"</c:if>>
                        <div class="ui-form-item">
                            <label class="ui-label"> 预定模板： </label>
                            <div class="inline-block">
                                <select class="ui-select js_select pretemplate" name="tempId" id="ppchoice1"></select>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label"> 预定名称：<span class="ui-form-required">*</span> </label>
                            <input type="text" class="ui-input textWsize210 presetname" name="orderName"
                                   id="orderName" maxlength="64" value="${productInfo.productExtInfo.orderName}"/>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label"> 订货时间：<span class="ui-form-required">*</span> </label>
                            <input style="width: 90px;" readonly="readonly" type="text" class="ui-input ordertime" id="start-cal" name="orderStartStr"
                            <c:if test="${!empty productInfo.productExtInfo.orderStart}"> value="${fns:getTimeFromLong(productInfo.productExtInfo.orderStart,'yyyy-MM-dd')}"</c:if>>
                            - <input style="width: 90px;" readonly="readonly" type="text" class="ui-input ordertime" id="end-cal" name="orderEndStr"
                            <c:if test="${!empty productInfo.productExtInfo.orderEnd}"> value="${fns:getTimeFromLong(productInfo.productExtInfo.orderEnd, 'yyyy-MM-dd')}"</c:if>>
                        </div>

                        <div class="ui-form-item">
                            <label class="ui-label"> 订货量：<span class="ui-form-required">*</span> </label>
                            <input type="text" class="ui-input textWsize110 orderamount js-number" value="${productInfo.productExtInfo.orderNum}" id="orderNum" name="orderNum">
                            <span class="ui-form-other">件</span>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label"> 出货时间：<span class="ui-form-required">*</span> </label>
                            <input style="width: 90px;" readonly="readonly" type="text" class="ui-input shippingtime" id="start-cal2" name="outStartStr"
                            <c:if test="${productInfo.productExtInfo.outStart!=null}"> value="${fns:getTimeFromLong(productInfo.productExtInfo.outStart, 'yyyy-MM-dd')}"</c:if>>
                            - <input style="width: 90px;" readonly="readonly" type="text" class="ui-input shippingtime" id="end-cal2" name="outEndStr"
                            <c:if test="${productInfo.productExtInfo.outEnd!=null}"> value="${fns:getTimeFromLong(productInfo.productExtInfo.outEnd, 'yyyy-MM-dd')}"</c:if>>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label"> 订金选择：<span class="ui-form-required">*</span></label>
                            <div class="inline-block">
                                <label>
                                    <input id="no_deposit" <c:if test="${empty productInfo.productExtInfo.orderSelect || productInfo.productExtInfo.orderSelect==orderSelect_notNeed}"> checked="checked"</c:if>
                                           value="${orderSelect_notNeed}" type="radio" name="orderSelect" class="ui-radio needeposit"/>无订金
                                </label>
                                <label>
                                    <input id="need_deposit" <c:if test="${productInfo.productExtInfo.orderSelect==orderSelect_need}">checked="checked"</c:if>
                                           value="${orderSelect_need}" type="radio" name="orderSelect" class="ui-radio needeposit"/>需要订金
                                </label>

                                <div style="height: 30px;margin-top: -3px;" class="inline-block">
                                    <span id="order_amount" style="<c:if test="${productInfo.productExtInfo.orderSelect==orderSelect_notNeed || empty productInfo.productExtInfo.orderSelect}">display:none;</c:if>">
                                        <label>订单金额的 </label>
                                        <input type="text" class="ui-input hasdeposit js-price" name="orderMoney" data-max="100" data-min="0" id="orderMoney" value="${productInfo.productExtInfo.orderMoney}"/>%
                                    </span>
                                </div>

                            </div>
                        </div>
                        <div class="savetotmp">
                            <input type="checkbox" class="ui-checkbox" name="saveToTmp"/>存为订单模板，方便发布其他订货产品
                        </div>
                        <div class="top-jiantou"></div>
                    </div>
                    --%><div class="ui-form-item">
                        <label class="ui-label">产品货号：<span class="ui-form-required">*</span> </label>
                        <input type="hidden" id="copy" value="${productInfo.operType}" />
                        <input type="text" class="ui-input textWsize210 to-omple articleno" name="productNo" id="productNo" data-display="产品货号"
                               value="${productInfo.productNo}" <c:if test="${not empty hasValidOrder && hasValidOrder}">disabled</c:if>>
                        <c:if test="${not empty hasValidOrder && hasValidOrder}">
                            <i class="iconfont edit-iconfont ml5">&#xe618;</i>
                        </c:if>
                            <div class="ui-form-othertiop" >货号可以使用</div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label"> 产品标题：<span class="ui-form-required">*</span> </label>
                        <input type="text" class="ui-input textWsize505 to-omple productitle" name="productTitle" id="productTitle" value="${productInfo.productTitle}" data-display="产品标题">
                        <span class="ui-form-other">还能输入<em class="bluefont" id="count">60</em>个字符</span>
                    </div>
                    <%--<div class="ui-form-item">
                        <label class="ui-label"> 产品吊牌价：<span class="ui-form-required">*</span> </label>
                        <input type="text" class="ui-input textWsize210 to-omple productprice js-price" data-display="产品价格" name="productPrice" id="productPrice" value="${productInfo.productPrice}"> 元
                    </div>
                   
                    <c:if test="${ directPriceRequired}">
	                    <div class="ui-form-item js-no-directprice">
	                        <label class="ui-label"> 产品直供价：<span class="ui-form-required">*</span> </label>
	                        <input type="text" class="ui-input textWsize210 to-omple directprice js-price" data-display="产品直供价" name="directPrice" id="directPrice" value="${productInfo.directPrice}"> 元
	                    </div>
                    </c:if>
                    --%><%--有颜色尺码--%>
                    <%--<c:if test="${ !directPriceRequired}">
                        <div class="ui-form-item js-no-directprice">
                            <label class="ui-label"> 产品直供价：<span class="ui-form-required">*</span> </label>
                            <input type="text" class="ui-input textWsize210 to-omple directprice js-price" data-display="产品直供价" name="directPrice" id="directPrice" value="${productInfo.directPrice}"> 元
                        </div>
                    </c:if>
                    --%><div class="ui-form-item">
                        <label class="ui-label"> 产品单位：<span class="ui-form-required">*</span> </label>
                        <div class="inline-block">
                            <select class="ui-select js-select to-omple" id="unitNo" name="unitNo" style="width: 220px;height: 30px;">
                                <option value="">请选择单位</option>
                                <c:forEach items="${webUnitList}" var="webUnit">
                                    <option value="${webUnit.refrenceId}" <c:if test="${webUnit.refrenceId == productInfo.unitNo}">selected="selected" </c:if>>${webUnit.unitName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--<div class="ui-form-item">
                        <label class="ui-label"> 产品库存：<span class="ui-form-required">*</span> </label>
                        <input type="text" class="ui-input textWsize210 to-omple productinventory" name="productStore" id="productStore"
                               value="${productInfo.productExtInfo.productStore}" data-display="产品库存">
                        <span class="js-unitNo">件</span>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label"> 产品企业编码：<span class="ui-form-required"></span> </label>
                        <input type="text" class="ui-input textWsize210" name="companyCode" id="companyCode" value="${productInfo.productExtInfo.companyCode}">
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label"> 产品条形码：<span class="ui-form-required"></span> </label>
                        <input type="text" class="ui-input textWsize210" name="barCode" id="barCode" value="${productInfo.productExtInfo.barCode}">
                    </div>
                    --%><div class="ui-form-item">
                        <label class="ui-label"> 起批量：<span class="ui-form-required">*</span> </label>
                        <input type="text" class="ui-input textWsize110 bulkclass to-omple" value="${productInfo.productExtInfo.startNum}" id="startNum"
                               name="startNum" data-display="起批量">
                        <span class="js-unitNo">件</span>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label"> 批量说明：&nbsp;&nbsp; </label>
                        <textarea class="ui-textarea" name="patchMark" id="patchMark">${productInfo.productExtInfo.patchMark}</textarea>
                    </div>
                    <%--属性页--%>
                    <div class="" style="position:relative;">
                        <jsp:include page="edit_productInfo_attr.jsp"/>
                    </div>
                    <div class="ui-form-item" style="padding-bottom:0;">
                        <input type="hidden" name="attrError" id="attrError" value="1"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label"> 产品主图：&nbsp;&nbsp;<span class="ui-form-required">*</span> </label>
                        <div>
                            <jsp:include page="/WEB-INF/view/brand/common_teps.jsp"/>
                            <input type="hidden" id="imgValidate">
                        </div>
                    </div>
                    <div class="ui-form-item vertical-tip">
                        <label class="ui-label"> 产品描述：&nbsp;&nbsp;<span class="ui-form-required">*</span> </label>

                        <div class="inline-block">
                            <div class="jstabsmenu_t clearfix">
                                <ul>
                                    <li class="selected"><span>电脑端</span></li>
                                </ul>
                            </div>
                            <div class="tabsbox_t" style="border: 0;">
                                <div class="">
                                    <textarea id="myEditor" class="to-omple" data-display="产品描述" name="productMark">${productInfo.productExtInfo.productMark}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="float-form-T clearfix">
                        <div class="ui-form-item">
                            <label class="ui-label"> 产品分类：&nbsp;&nbsp; </label>
                            <div class="inline-block js_scllobox" id="catalog">
                                <ul>

                                </ul>
                            </div>
                        </div>
                        <%--<div class="ui-form-item">
                            <label class="ui-label"> 产品线：&nbsp;&nbsp; </label>
                            <div class="inline-block pro_class js_scllobox">
                                <ul class="onefenl" id="proLine_ul">

                                </ul>
                            </div>
                        </div>--%>
                    </div>
                    <div class="impoBar"><i class="productmanege_icon PD_h"></i>包装信息和物流</div>
                    <div class="ui-form-item">
                        <label class="ui-label"> 产品包装后的重量：<span class="ui-form-required">*</span> </label>

                        <div class="inline-block">
                            <label><input class="ui-input" type="text" name="productWeight" value="${productInfo.productExtInfo.productWeight}"/> kg/件</label>
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label"> 运费物流：<span class="ui-form-required">*</span> </label>

                        <div class="radio_box inline-block">
                            <label><input <c:if test="${productInfo.productCarry==carry_dealer || empty productInfo.productCarry}">checked</c:if>
                                    value="${carry_dealer}" class="ui-radio chengdanwul" name="productCarry" type="radio"/>终端商承担物流</label>
                            <label><input <c:if test="${productInfo.productCarry==carry_brand }">checked</c:if> value="${carry_brand}" class="ui-radio ml5 chengdanwul"
                                    name="productCarry" id="productCarry" type="radio"/>品牌商承担物流</label>
                        </div>
                    </div>
                    <div class="ui-form-item" id="fareSwitchItem">
                        <label class="ui-label"> 运费模版：<span class="ui-form-required">*</span> </label>

                        <div class="inline-block pr">
                            <select class="js-select ui-select" name="freTemplateId" id="freTemplate" style="height: 28px;width: 137px;">
                                <option value="">请选择运费模版</option>
                                <c:forEach items="${templateList}" var="template">
                                    <option value="${template.refrenceId}" <c:if test="${template.refrenceId==productInfo.productExtInfo.freTemplateId}">selected</c:if>>${template.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!--运费模版需变化的模块 开始-->
                    <div class="ui-form-item fare-change">

                    </div>
                    <!--运费模版需变化的模块 结束-->
                    <div class="impoBar">
                        <i class="productmanege_icon PD_h"></i>服务保障
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label"> 退换货承诺：&nbsp;&nbsp; </label>
                        <textarea class="ui-textarea" name="productBear" id="productExtInfo.productBear">${productInfo.productExtInfo.productBear}</textarea>
                    </div>
                    <div class="impoBar">
                        <i class="productmanege_icon PD_t"></i>其他信息
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label"> 开始时间：<span class="ui-form-required">&nbsp;</span> </label>

                        <div class="radio_box inline-block">
                            <div class="staradio">
                                <label>
                                    <input <c:if test="${productInfo.stateSet eq beginType_first || empty productInfo.stateSet}"> checked="checked"</c:if>
                                        value="${beginType_first}" class="ui-radio" name="stateSet"
                                        type="radio"/>立刻</label>
                            </div>
                            <div class="staradio">
                                <label>
                                    <input <c:if test="${productInfo.stateSet eq beginType_set}"> checked="checked"</c:if>
                                        value="${beginType_set}" class="ui-radio" name="stateSet"
                                        type="radio"/>设定</label>
                                    <!-- 
                                    <input type="text" class="ui-input" id="datatimemm" name="beginTime"
                                           <c:if test="${!empty productInfo.productExtInfo.beginTime}">value="${fns:getTimeFromLong(productInfo.productExtInfo.beginTime,'yyyy-MM-dd HH:mm:ss')}"</c:if>>
                                    -->
                                    <input type="text" class="ui-input" id="datatimemm" name="beginTimeStr"
                                           <c:if test="${!empty productInfo.productExtInfo.beginTime}">value="${fns:getTimeFromLong(productInfo.productExtInfo.beginTime,'yyyy-MM-dd HH:mm:ss')}"</c:if>>
                                     

                            </div>
                            <div class="staradio">
                                <label><input
                                        <c:if test="${productInfo.stateSet eq beginType_store}"> checked="checked"</c:if>
                                        value="${beginType_store}" class="ui-radio" name="stateSet"
                                        id="beginType" type="radio"/>放入仓库</label>
                            </div>
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label"> 前台可见：<span class="ui-form-required">*</span> </label>

                        <div class="radio_box inline-block">
                            <label>
                                <input <c:if test="${(empty productInfo.productExtInfo.show) || productInfo.productExtInfo.show}">checked</c:if>
                                    value="true" class="ui-radio" name="isShow" type="radio"/>可见
                            </label>
                            <label>
                                <input <c:if test="${(not empty productInfo.productExtInfo.show) && (not productInfo.productExtInfo.show)}">checked</c:if>
                                value="false" class="ui-radio ml5" name="isShow" type="radio"/>不可见</label>
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label"> 推荐设置：<span class="ui-form-required">&nbsp;</span> </label>

                        <div class="inline-block recomstyle">
                            <label>
                                <input type="checkbox" class="ui-checkbox"
                                    <c:if test="${productInfo.productExtInfo.productGroom==true}"> checked="checked"</c:if>
                                       name="productGroom" id="productGroom"/>
                                <span class="recomPro">设为推荐产品</span> </label>
                                <span id="groomTotal" data-groom="${groomTotal}">共有${groomTotal}个推荐位 </span>
                                <span id="groomUsed" data-groom="
                                    <c:choose>
                                        <c:when test="${productInfo.productExtInfo.productGroom==true}">
                                            ${groomUsed-1}
                                        </c:when>
                                        <c:otherwise>${groomUsed}</c:otherwise>
                                    </c:choose>">已用${groomUsed}位</span>
                                <input type="hidden" id="groomCheck"
                                    <c:choose>
                                        <c:when test="${productInfo.refrenceId==null}">value="${groomUsed}"</c:when>
                                        <c:when test="${productInfo.productExtInfo.productGroom==true && groomTotal>groomUsed}">value="${groomUsed}"</c:when>
                                        <c:when test="${productInfo.productExtInfo.productGroom==null || productInfo.productExtInfo.productGroom==false}"> value="${groomUsed}" </c:when>
                                    </c:choose> />
                        </div>
                    </div>
                    <div class="fixed_bar">
                        <div class="fixed_content">
                            <div class="fixed_main">
                                <label class="ui-label"> <span class="ui-form-required">&nbsp;</span></label>
                                <input type="button" class="ui_button ui_button_morange Csure" value="立即发布" onclick="setOpType('0')" id="productSave">
                                <a href="javascript:setOpType('1');" class="ui_button ui_button_mgray Csure">预览</a>
                            </div>
                        </div>
                    </div>
                </form:form>
                <form:form id="viewForm" action="${ctx}/brand/product/previewNew" method="post" target="viewFrame" style="display:none;"></form:form>
            </div>
        </div>
    </div>
</div>
<div id="viewPanel">
    <iframe id="viewFrame" name="viewFrame" frameborder="0" scrolling="yes" width="100%" height="100%"></iframe>
    <a id="closeView" href="javascript:;">×</a>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp"/>
<script src="${src}/brand/productmanage.js" type="text/javascript"></script>
<script src="${src}/brand/productinfo_edit.js" type="text/javascript"></script>
<script src="${res}/scripts/gallery/editor/1.0.0/expand.js"></script>

<script id="shopenv-template" type="text/html">
    <label class="ui-label"></label>
    <div class="inline-block fare-change-body">
        <div class="clearfix">
            <a class="fr" href="${ctx}/brand/freightTemplate/get?templateId={{object.template.refrenceId}}" target="_blank">查看详情</a>
            <strong>运费模版</strong>
        </div>
        <div class="mt10 fare-change-tabs">
            <ul class="inline-float">
                {{if object.isExpressUsed}}
                <li class="fare-item selected" style="cursor: pointer;">快递</li>
                {{/if}}
                {{if object.isLogisticsUsed}}
                <li class="fare-item {{if !object.isExpressUsed}}selected{{/if}}" style="cursor: pointer;">物流</li>
                {{/if}}
                {{if object.isExpressCollectUsed}}
                <li>快递到付</li>
                {{/if}}
                {{if object.isLogisticsCollectUsed}}
                <li>物流到付</li>
                {{/if}}
            </ul>
            {{if object.isExpressUsed}}
            <div>
                {{if object.defaultExpress!=null}}
                <div class="mt5 fare-tabs-con">
                    <div class="con-hd">
                        默认运费：{{object.defaultExpress.firstWeight}}千克以内{{object.defaultExpress.firstPrice}}元，每增加{{object.defaultExpress.extendWeight}}千克，加{{object.defaultExpress.extendPrice}}元
                    </div>
                    {{/if}}
                    {{if object.expressList!=null && object.expressList.length>0}}
                    <div class="mt5">
                        <h5><strong>指定区域运费</strong></h5>

                        <div class="city-list">
                            {{each object.expressList}}
                            {{if $index==0}}
                            {{$trimAreaNum $value.arriveAddress}}
                            {{$value.firstWeight}}千克内{{$value.firstPrice}}元，每增加{{$value.extendWeight}}千克，加{{$value.extendPrice}}元
                            {{/if}}
                            {{/each}}
                        </div>
                    </div>
                    {{/if}}
                </div>
                {{/if}}
                {{if object.isLogisticsUsed}}
                <div class="mt5 fare-tabs-con" {{if object.isExpressUsed}}style="display:none;" {{/if}}>
                {{if object.defaultLogistics!=null}}
                <div class="con-hd">
                    默认运费：{{object.defaultLogistics.firstWeight}}千克以内{{object.defaultLogistics.firstPrice}}元，每增加{{object.defaultLogistics.extendWeight}}千克，加{{object.defaultLogistics.extendPrice}}元
                </div>
                {{/if}}
                {{if object.logisticsList!=null && object.logisticsList.length>0}}
                <div class="mt5">
                    <h5><strong>指定区域运费</strong></h5>

                    <div class="city-list">
                        {{each object.logisticsList}}
                        {{if $index==0}}
                        {{$trimAreaNum $value.arriveAddress}}
                        {{$value.firstWeight}}千克内{{$value.firstPrice}}元，每增加{{$value.extendWeight}}千克，加{{$value.extendPrice}}元
                        {{/if}}
                        {{/each}}
                    </div>
                </div>
                {{/if}}
            </div>
            {{/if}}
        </div>
    </div>
    </div>
    <div class="mt5">
        发货地：{{object.template.fullAreaName}}
    </div>
</script>
<script id="commonEditTpl" type="text/html">
    <div class="ui-head">
        <h3>{{title}}</h3>
    </div>
    <form id="productNoEditForm" class="mt15" action="" data-widget="validator" style="{{display1}}">
        <div class="ui-form-item" style="padding-left: 105px;">
            <label class="ui-label">
                现{{title}}：
            </label>

            <div class="inline-block radio_box" style="margin-top: 7px;">
                {{oldValue}}
            </div>
        </div>
        <div class="ui-form-item" style="padding-left: 105px;">
            <label class="ui-label">
                新{{title}}：
            </label>
            <input class="ui-input" type="text" style="width: 176px;" required data-display="新{{title}}" {{if rule==
                   1}}data-rule="lengthTW checkProductNo" {{else if rule== 2}}data-rule="lengthTW" {{/if}} />
        </div>
        <div class="ta-c">
            <button type="submit" class="simple_button productNoEditSure">提交审核</button>
            <button type="button" href="javascript:;" class="simple_button productNoEditCancel ml10">取消</button>
        </div>
    </form>
    <div id="productNoEditTip" class="mt15" style="{{display2}} margin:0 10px;">
        <div style="word-break: break-all;">
            您已提交待审核的新{{title}}：<span>{{newValue}}</span>，请耐心等待审核结果。
        </div>
        <div class="ta-c mt10">
            <button type="button" href="javascript:;" class="simple_button productNoEditCancel ml10">确定</button>
        </div>
    </div>
</script>
<script id="remindFdTpl" type="text/html">
    <div class="ui-head">
        <h3>提示</h3>
    </div>
    <div>
        <p style="padding: 10px;">未生效的返点价智慧门店是否仍继续生效？</p>
    </div>
    <div class="operate ta-c">
        <button type="submit" class="simple_button confirm js-fdcome-sure">是</button>
        <button type="button" class="simple_button ml5 cancel js-fdcome-cancel">否</button>
    </div>
</script>
<script type="text/javascript">
    
    
    $(function () {
        $("#count").html(60 - getCharLength($(".productitle").val()));

        addNew.init();
        brandChange();
        templateChange();
        $("#ppchoice").on("change", function () {
            brandChange();
        });

        //有无定金的处理 order_amount
        $(document).on("click", "#no_deposit", function () {
            $("#order_amount").hide();
        });
        $(document).on("click", "#need_deposit", function () {
            $("#order_amount").show();
        });
        //产品单位的联动处理
        $("#unitNo").change(function () {
            var unitNoText = $("#unitNo option:selected").text()
            $(".js-unitNo").text(unitNoText);
        });

        $("#freTemplate").on("change", function () {
            templateChange();
        });
    });

    function brandChange() {
        var refrenceId = "${productInfo.refrenceId}";
        var brandsId = $.trim($("#ppchoice").val());
        addNew_changeBrandTmp(brandsId);
        addNew_setProCate("${cateIds}", brandsId);
        //addNew_setProLine(refrenceId, brandsId);
    }

    function templateChange() {
        var value = $.trim($("#freTemplate").val());
        if ("" != value) {
            seajs.use(["template"], function (template) {

                template.helper("$trimAreaNum", function (str) {
                    return trimAreaNum(str, 10);
                });

                $.post("/brand/product/selTemplate", {templateId: value}, function (data) {
                    if (data.code == zttx.SUCCESS) {
                        var html = template.render("shopenv-template", data);
                        $('.fare-change').html(html);
                    } else {
                        remind("error", data.message);
                    }
                }, 'json');
            });
        } else {
            $('.fare-change').html("");
        }
    }

    var productCate = $("input[name='productCate']:checked").val();
    if (productCate == "${productcate_order}") {$(".proman-templetedit").show();}

    $.fn.countNum = function () {//以后扩展，最大数字通过dada-maxnum来自定义，文字通过data-defult来自定义

        return this.each(function () {
            var maxCount = 60;//最大数
            $(this).on("keyup paste copy", function () {//输入监听
                var len = getCharLength(this.value);
                $("#count").html(maxCount - len);
            });
        });

    };
    $(".productitle").countNum();

    function setOpType(t) {
        $("#opType").val(t);
        $(".productadd-form").submit();
    }
    
  //工厂店第二期
    seajs.use(["dialog", "template"],function(Dialog, Template){
        var productId = $("input[name=refrenceId]").val();
        var commonDialog = new Dialog({
            width:300,
            content:$("#commonEditTpl").html()
        });

        function editFirstFn(self, changeType, content, vid){
            if($("#productNoEditForm").length > 0){
                baseFormValidator({
                    selector:"#productNoEditForm",
                    isAjax:true,
                    beforeSubmitFn:function(){
                    	var newValue = $("#productNoEditForm .ui-input").val();
                        var data = {productId: productId, changeType: changeType, newValue: newValue, vid: vid};
                        if(changeType == 1){
                            data.attributeIcon = self.parent().find(".colorchk").data("icon");
                        }
                        $.ajax({
                            url:"/brand/product/changeSubmit",
                            method:"post",
                            dataType : "json",
                            data:data,
                            success:function(json){
                                //console.log(json);
                                if(json.code == zttx.SUCCESS){
                                    remind("success",content.title + "修改请求成功提交，请耐心等待结果。");
                                    commonDialog.hide();
                                }else{
                                    remind("error",json.message);
                                }
                            }
                        });
                    }
                });
            }
        }

        function verifyCurrentState(self, url, changeType, content, vid){
            changeType = changeType || 0;
            var data = {
                productId: productId,
                changeType: changeType,
                vid: vid
            };
            $.ajax({
                url:url,
                method:"post",
                dataType : "json",
                data:data,
                success:function(json){
                    if(json.code == zttx.SUCCESS){
                        if(json.object && json.object.state === 0){
                            content.newValue = json.object.newValue;
                            content.display1 = "display:none;";
                            content.display2 = "display:block;";
                        }else{
                        	content.oldValue = self.prev().val();
                            content.display1 = "display:block;";
                            content.display2 = "display:none;";
                        }
                        commonDialog.before("show",function(){
                            var html = Template.render("commonEditTpl",content);
                            this.element.html(html);
                        });
                        commonDialog.show();
                        editFirstFn(self, changeType, content, vid);
                    }else{
                        remind("error",json.message);
                    }
                }
            });
        }
        //产品货号 单击
        $(".productadd").on("click",".edit-iconfont",function(){
            var data = {
                title: "产品货号",
                rule: 1
            };
            //先验证状态
            verifyCurrentState($(this), "/brand/product/selectSubmit", "0", data);
        });
        //颜色尺码 单击
        $(".productadd").on("click",".edit-attr-iconfont",function(){
            var title = $(this).parents(".coloreditul").data("name");
            var data = {
                title: title,
                rule: 2
            };
            var vid = $(this).parent().find("input[name=salerRefrenceId]").val();
            var isColor = $(this).parents(".coloreditul").data("color");
            var changeType = 1;
            if(isColor){
                changeType = 1;
            }else{
                changeType = 2;
            }
            verifyCurrentState($(this), "/brand/product/selectSubmit", changeType, data, vid);
        });
        $(document).on("click",".productNoEditCancel",function(){
            commonDialog.hide();
        });
    });
</script>

</body>
</html>