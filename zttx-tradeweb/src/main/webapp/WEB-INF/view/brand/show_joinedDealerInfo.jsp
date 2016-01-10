<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>


<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
<%@ page import="com.zttx.web.utils.CalendarUtils" %>
    <title>管理中心-终端商管理-终端商详情</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/agencymanag.css"/>
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
<div class="main layout">
    <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
<div class="main_con">
<!-- 面包导航，并不是每个页面都有 -->
<div class="bread_nav">
    <div class="fl">
        <a class="link" href="${ctx}/brand/center">管理中心</a>
        <span class="arrow">&gt;&gt;</span>
        <a class="link" href="${ctx}/brand/dealer/listDealerApply">终端商管理</a>
        <span class="arrow">&gt;</span>
        <span class="current">终端商详情</span>
    </div>
    <div class="fr">
        <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" >
            <jsp:param value="0" name="isShow"/>
        </jsp:include>
    </div>
</div>
<div class="inner">
<!-- 内容都放这个地方  -->
<!-- 终端商详情 -->
<div class="agency-teamed-info-contant">
    <div class="teamed-info-title">
        <h1 class="title inline-block">${dealerJoin.dealerName}</h1>
        <span class="account inline-block">账号（${dealerUserm.userMobile}）</span>
        <span class="account inline-block">授权品牌：${brandesInfo.brandsName}</span>
        <a javascript=":;" class="export simple_button js-leave-message">留言站内信</a>
    </div>
    <div class="js_teamedinfo_tabs">
        <div class="js_teamedinfo_menu clearfix">
            <ul>
                <li class="selected"><a href="${ctx}/brand/join/dealerInfo/${joinId}">基本资料</a></li>
                <li><a href="${ctx}/brand/join/dealerLevel/${joinId}">等级授权</a></li>
                <li><a href="${ctx}/brand/join/dealerDeal/${joinId}">进货明细</a></li>
            </ul>
        </div>
        <div class="js_teamedinfo_con">
            <div class="agency-info-recom agency-teamedinfo-recom"><!--基本资料-->
                <div class="agency-info-titbar agency-info-titbarone">
                    <i class="inline-block info-titbar-icon titbar-company"></i>
                    <span>加盟信息</span>
                </div>
                <div class="ui-form">
                    <div class="ui-form-item">
                        <label class="ui-label">
                            加盟来源：
                        </label>
                        <div class="ui-form-self inline-block">
                        	<c:choose>
                        		<c:when test="${dealerJoin.joinSource eq 1}">平台线上申请加盟</c:when>
								<c:when test="${dealerJoin.joinSource eq 2}">平台线上邀请加盟</c:when>
                                <c:when test="${dealerJoin.joinSource eq 3}">平台线下加盟</c:when>
                                <c:when test="${dealerJoin.joinSource eq 4}">关系户加盟</c:when>
                                <c:otherwise>未定义${obj.joinSource}</c:otherwise>
                            </c:choose>
                        </div>
                        <div class="teamedinfo-pawz"><input type="checkbox" class="js_chk" <c:if test="${showFlag}">checked</c:if>/>在门店展示中显示该终端商</div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            加盟时间：
                        </label>
                        <div class="ui-form-self inline-block">
                        ${fns:getTimeFromLong(dealerJoin.joinTime,"yyyy-MM-dd")}
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            所在地：
                        </label>
                        <div class="ui-form-self inline-block">${dealerInfo.provinceName} ${dealerInfo.cityName} ${dealerInfo.areaName}</div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            经营品类：
                        </label>
                        <div class="ui-form-self inline-block">
                        	<c:forEach var="obj" items="${dealerClassList}" varStatus="status"><c:if test="${status.index > 0}">、</c:if>${obj.dealName}</c:forEach>
                        </div>
                    </div>
                    <div class="ui-form-item">
                    	<label class="ui-label" for=""> 开店经验： </label>
                    	<div class="ui-form-self inline-block"><c:set var="now" value="<%=CalendarUtils.getCurrentLong() %>"/>${dealerInfo.foundTime<now?"有":"无"}</div>
                    </div>
                    <div class="ui-form-item" style="display:none;">
                    	<label class="ui-label" for=""> 实体店： </label>
                    	<div class="ui-form-self inline-block">正在经营</div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            开店日期：
                        </label>
                        <c:if test="${(not empty dealerInfo.foundTime) && (dealerInfo.foundTime != 0)}">
	                        <div class="ui-form-self inline-block">
	                        	${fns:getTimeFromLong(dealerInfo.foundTime,"yyyy年MM月dd日")}
	                        </div>
                        </c:if>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            分店数量：
                        </label>
                        <div class="ui-form-self inline-block">${dealerInfo.shopNum}家</div>
                    </div>
                </div>
                <div class="agency-info-titbar">
                    <i class="inline-block info-titbar-icon titbar-contact"></i>
                    <span>联系方式</span>
                </div>
                <div class="ui-form titbar-contact-form">
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            联系人：
                        </label>
                        <div class="ui-form-self inline-block">${dealerInfo.dealerUser}</div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            手机号码：
                        </label>
                        <div class="ui-form-self inline-block">${dealerUserm.userMobile}</div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            联系电话：
                        </label>
                        <div class="ui-form-self inline-block">${dealerInfo.dealerTel}</div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            传真号码：
                        </label>
                        <div class="ui-form-self inline-block">${dealerInfo.dealerFax}</div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            公司地址：
                        </label>
                        <div class="ui-form-self inline-block">${dealerInfo.dealerAddress}</div>
                    </div>
                </div>
                <div class="agency-info-titbar">
                    <i class="inline-block info-titbar-icon titbar-intrdus"></i>
                    <span>公司介绍</span>
                </div>
                <div class="ui-form">
                    <div class="ui-form-item">
                        <label class="ui-label">
                            公司网站：
                        </label>
                        <div class="ui-form-self inline-block"><a href="${dealerInfo.dealerWeb}" target="_blank">${dealerInfo.dealerWeb}</a></div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            网上店铺：
                        </label>
                        <div class="ui-form-self inline-block"><a href="${dealerInfo.dealerShop}">${dealerInfo.dealerShop}</a></div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            店铺形象：
                        </label>
                        <div class="ui-form-self inline-block mendianpic">
							<c:set value="${dealerInfo.domainName}${dealerInfo.dealerLogo}" var="url"></c:set>
                        	<img style="width:600px;height:260px;" src="${res}${url}" />
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            详细介绍：
                        </label>
                        <div class="ui-form-self inline-block xiangxijs">${dealerInfo.dealerMark}</div>
                    </div>
                </div>
            </div>
            <c:if test="${not empty queryPriceList}">
            <!-- 询价内容开始 -->
            <div class="detail_contain">
                <div class="inquiry_con" style="margin: 0;">
                    <div class="tit">
                        <i class="iconfont add_icon">&#xe611;</i><span>询价内容</span>
                    </div>
                    <div class="bd">
                        <ul style="margin-top: -10px;">
                            <c:forEach var="queryPrice" items="${queryPriceList}" varStatus="status">
                                <li>
                                    <div class="i-hd">
                                        单号：${queryPrice.queryNo}
                                        <span class="date">日期：
                                        ${fns:getTimeFromLong(queryPrice.createTime,"yyyy-MM-dd HH:mm:ss")}
                                        </span>
                                        <c:if test="${queryPrice.lockState ==1 || (queryPrice.queryState==0 && empty queryPrice.lineId)}">
                                        <a class="ui_button ui_button_sorange fr js-join-pline" href="javascript:;" data-id="${queryPrice.refrenceId}">加入产品线</a>
                                        </c:if>
                                    </div>
                                    <div class="i-bd clearfix">
                                        <div class="img-contain">
                                            <img src="${res}${fns:getImageDomainPath(queryPrice.productImage, 160, 160)}" width="78" height="78" alt=""/>
                                        </div>
                                        <div class="info">
                                            <p>
                                                <label for="">产品名称：</label>
                                                <a style="width: 224px;" class="inline-block fn-text-overflow link" href="${ctx}/market/product/${queryPrice.productId}">${queryPrice.productName}</a>
                                                <label for="">货号：</label>
                                                <span style="width: 232px;" class="inline-block fn-text-overflow">${queryPrice.productNo}</span>
                                                <label for="">询价数量：</label>
                                                <span class="num">${queryPrice.purchaseNum}${empty queryPrice.unit ? '件':queryPrice.unit}</span>
                                            </p>
                                            <p>
                                                <label for="">采购说明：</label>
                                                            <span style="width: 650px;" class="inline-block fn-text-overflow">
                                                                    ${queryPrice.purchaseMark}
                                                            </span>
                                            </p>
                                        </div>
                                        <c:if test="${!empty queryPrice.attachment}">
                                            <span class="download">
                                                <i></i><a href="${ctx}/brand/line/queryDownFile/${queryPrice.refrenceId}" target="_blank">下载附件</a>
                                            </span>
                                        </c:if>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- 询价内容结束-->
            </c:if>
        </div>
    </div>
</div>
</div>
</div>
</div>
	<div style="display: none;">
        <!--留言-->
        <div class="js-leave-messagebox">
            <div class="ui-head">
                <h3>留言</h3>
            </div>
            <form:form id="msg-form" method="post" action="${ctx}/brand/message/sendDealer">
                <input name="dealerIds" type="hidden" class="js-getvalue" value="${dealerJoin.dealerId}">
                <div class="content">
                    <input id="msg-title" name="title" type="text" placeholder="留言" class="ui-input mt10" style="width: 200px"/>
                    <textarea id="msg-content" name="content" class="ui-textarea mt10" style="width:335px;height: 78px;" placeholder="请输入留言内容"></textarea>
                </div>
                <div class="ta-c mt10">
                    <a href="javascript:;" class="simple_button js-lemsgsure-btn">确认</a>
                    <a href="javascript:;" class="simple_button js-lemsgsurecansole-btn">取消</a>
                </div>
            </form:form>
        </div>
        <!--留言结束-->
        <!--询价加入产品线-->
        <div class="js-join-plinebox">
            <div class="ui-head">
                <h3>加入产品线</h3>
            </div>
            <input type="hidden" name="queryId" id="queryId"/>
            <%--<form:form id="msg-form" method="post" action="${ctx}/brand/message/sendDealer">
                <input name="dealerId" type="hidden" class="js-getvalue" value="${dealerJoin.dealerId}">

                <div>
                    <input id="msg-title" name="title" type="text" placeholder="留言" class="ui-input mt10" style="width: 200px"/>
                    <textarea id="msg-content" name="content" class="ui-textarea mt10" style="width:376px;height: 78px;" placeholder="请输入留言内容"></textarea>
                </div>
                <div class="ta-c mt10">
                    <a href="javascript:;" class="simple_button js-lemsgsure-btn">确认</a>
                    <a href="javascript:;" class="simple_button js-lemsgsurecansole-btn">取消</a>
                </div>
            </form:form>--%>
            <div>
                <ul id="query-pline">
                </ul>
            </div>
        </div>
        <!--询价加入产品线 结束-->
	</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/agencymanag.js"></script>
<script id="line-template" type="text/html">
{{each rows}}
<li class="clearfix mt10 query-join-pline" style="line-height: 30px;" data-id="{{$value.refrenceId}}">{{$value.lineName}} <a href="javascript:;" class="simple_button fr">加入</a></li>
{{/each}}
</script>
<script>
	agencyteamedinfo.init();

    var $msgForm = $('#msg-form');
    var $msgContent = $('#msg-content');
    
    function leaMsg(obj, dialog) {//留言弹窗点击确定后的操作可以写在这里
        //点击”确定“后出发的方法
        var content = $.trim($msgContent.val());
        if (content == '') {
            remind('error', '请输入内容!');
            return false;
        }
        var title = $.trim($('#msg-title').val());
        if (title == '') {
        	$('#msg-title').val("留言");
        }
        $.post('${ctx}/brand/message/sendDealer', $msgForm.serialize(), function (data, status, jqXHR) {
            if (data.code === zttx.SUCCESS) {
                remind('success', '留言成功');
                $msgForm[0].reset();
                dialog.hide();
            } else {
                remind('error', data.message);
            }
        }, 'json');
    }
    
    //是否在展厅中展示
    $(".teamedinfo-pawz").on("click",".js_chk",function(){
    	var url = "/brand/join/addNetwork/${joinId}";
    	if($(".teamedinfo-pawz input[type='checkbox']:checked").size() == 0){
    		url = "/brand/join/delNetwork/${joinId}";
    	}
    	$.getJSON(url, function(data){
    		if(data.code == zttx.SUCCESS)
    		{
    			remind("success", "操作成功");
    		}
    		else
    		{
    			remind("error", data.message);
    		}
    	});
	});

    //询价加入产品线弹出窗
    seajs.use(['dialog','template'],function(Dialog, template){
        //询价单加入产品线
        var joinPlineBox = new Dialog({
            //trigger:".js-join-pline",
            content: $(".js-join-plinebox"),
            width:385,
            hasMask:false
        });
        //点击触发弹窗
        $(".js-join-pline").click(function(){
        	var refrenceId = $.trim($(this).data("id"));
        	$.post('${ctx}/brand/line/queryLine', {"refrenceId":refrenceId}, function (data, status, jqXHR) {
	            if (data.code === zttx.SUCCESS) {
	            	$("#queryId").val(refrenceId);
	            	var html = template.render("line-template", data);
	            	$("#query-pline").html(html);
	            	joinPlineBox.show();
	            } else {
	                remind('error', data.message);
	            }
        	}, 'json');
        });
        //加入产品线
        $(".js-join-plinebox").on("click",".query-join-pline",function(){
        	var queryId = $.trim($("#queryId").val());
        	var lineId = $(this).data("id");
        	$.post('${ctx}/brand/line/queryJoinLine', {"queryId":queryId,"lineId":lineId}, function (data, status, jqXHR) {
	            if (data.code === zttx.SUCCESS) {
	            	joinPlineBox.hide();
	            	remind('success', "已成功加入产品线",function(){
	            		location.href = location.href;
	            	});
	            } else {
	                remind('error', data.message);
	            }
        	}, 'json');
        });
    });
</script>
<!--
    另外加载当前页面需要的js路径，或者使用
    seajs.use("./xxx.js")
 -->
</body>
</html>