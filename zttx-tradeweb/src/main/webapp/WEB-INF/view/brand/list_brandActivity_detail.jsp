<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>管理中心-活动报名列表</title>
<link rel="stylesheet" href="${res}/styles/brand/global.css" />
<link rel="stylesheet" href="${res}/styles/brand/sign.css" />
</head>
<body>
	<jsp:include page="${ctx}/brand/mainmenu">
		<jsp:param value="1" name="headType"/>
	</jsp:include>
	<div class="main layout">
	    <div class="sign-box">
	        <div class="detail-hd clearfix">
	            <div class="img_contain">
	            	<c:choose>
	            		<c:when test="${!empty brandActivity.image}">
	            		<img src="${res}${brandActivity.image}" width="220" height="220" alt=""/>
	            		</c:when>
	            		<c:otherwise>
	            		<img src="${res}/images/common/nopic-400.gif" width="220" height="220" alt=""/>
	            		</c:otherwise>
	            	</c:choose>
	                
	            </div>
	            <div class="info_contain">
	                <h3 class="name">${brandActivity.name}</h3>
	                <div>
	                <c:choose>
	                	<c:when test="${brandActivity.chargeType==1}">
	                	<p class="price toll">
		                                        收费
		                </p>
	                	</c:when>
	                	<c:otherwise>
	                	<p class="price free">
		                                        免费
		                </p>
	                	</c:otherwise>
	                </c:choose>
	                </div>
	                <p class="time mt15">活动时间：${fns:getStringTime(brandActivity.startTime,"yyyy-MM-dd")} ～${fns:getStringTime(brandActivity.endTime,"yyyy-MM-dd")}</p>
	                <p class="sign-person mt15">
	                    已有<span class="num">${brandActivity.productCount}</span>产品报名<span class="ml15" style="display:none;">剩余可报名人数：<span class="remind">4533</span>人</span>
	                </p>
	                <p class="time mt15">
	                    报名截止时间：${fns:getStringTime(brandActivity.regEndTime,"yyyy-MM-dd HH:mm")}
	                </p>
	                <div class="mt20">
	                <c:choose>
	                	<c:when test="${brandActivity.regStateType==1}">
	                	<!-- <button class="ui_button ui_button_mred">我要报名</button> -->
	                	<a href="${ctx}/brand/activity/products?id=${brandActivity.refrenceId}" class="ui_button ui_button_mred">我要报名</a>
	                	</c:when>
	                	<c:otherwise>
	                	<button disabled="disabled" class="ui_button ui_button_mgray">我要报名</button>
	                	</c:otherwise>
	                </c:choose>
	                </div>
	            </div>
	        </div>
	        <div class="detail-bd">
	            <h3>活动介绍</h3>
	            <div class="con">
	                ${brandActivity.description}
	            </div>
	        </div>
	    </div>
	</div>
	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
</body>
</html>