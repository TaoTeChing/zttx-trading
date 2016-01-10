<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>资讯管理-会议</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_trade.css"/>
    <style>
        .file_wrap{
            width: 350px;
            height: 200px;
            border: #cccccc dashed 1px;
            background: #f9f9f9;
        }
        .file_wrap .replace_text{
            line-height: 200px;
            color: #ccc;
            font-size: 14px;
        }
        .file_wrap .input_file{
            width: 350px;
            height: 200px;
        }
        .explain {
            color: #999;
        }
    </style>
</head>
<body>
	<jsp:include page="${ctx}/brand/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/brand/brandmenu"/>
<div class="main_con">
    <!-- 面包导航，并不是每个页面都有 -->
    <div class="bread_nav">
        <div class="fl">
            <a class="link" href="${ctx}/brand/center">管理中心</a>
            <c:choose>
                <c:when test="${param.m=='1'}">
                    <span class="arrow">&gt;&gt;</span>
                    <a class="link" href="${ctx}/brand/dealer/listDealerApply">终端商管理</a>
                    <span class="arrow">&gt;</span>
                    <span class="current">交易会管理</span>
                </c:when>
                <c:otherwise>
                    <span class="arrow">&gt;&gt;</span>
                    <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
                    <span class="arrow">&gt;</span>
                    <span class="current">资讯管理</span>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
        </div>
    </div>
    <jsp:include page="${ctx}/WEB-INF/view/brand/show_brandNews_bar.jsp">
        <jsp:param name="m" value="1" />
    </jsp:include>
    <div class="inner">
        <!-- 内容都放这个地方  -->
        <form:form id="meetingForm" class="ui-form mt20 info-form" action="${ctx}/brand/tradeMeeting/save" method="post">
            <input type="hidden" name="refrenceId" value="${tradeMeeting.refrenceId}" />
            <div class="ui-form-item">
                <label class="ui-label" for="">选择品牌：<span class="ui-form-required">*</span></label>
            	<div class="inline-block">
	                <select id="js-select-brands" style="width: 210px;" name="brandsId" class="js_select ui-select">
	                    <option value="">请选择品牌...</option>
	                    <c:forEach items="${brandesInfoList}" var="brandesInfo">
	                        <option value="${brandesInfo.refrenceId}"<c:if test="${brandesInfo.refrenceId == tradeMeeting.brandsId}"> selected</c:if>>${brandesInfo.brandName}</option>
	                    </c:forEach>
	                </select>
	            </div>
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">会议名称：<span class="ui-form-required">*</span></label>
                <input style="width: 392px;" type="text" class="ui-input" name="meetName" value="${tradeMeeting.meetName}" />
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">举办时间：<span class="ui-form-required">*</span></label>
                <input style="width: 116px;" readonly="readonly" type="text" class="ui-input Wdate" id="startDate" name="beginTimeD"
                	value="${not empty tradeMeeting.beginTime ? fns:getTimeFromLong(tradeMeeting.beginTime,'yyyy-MM-dd') : ''}"/>
                -
                <input style="width: 116px;" readonly="readonly" type="text" class="ui-input Wdate" id="endDate" name="endTimeD"
                	value="${not empty tradeMeeting.endTime ? fns:getTimeFromLong(tradeMeeting.endTime,'yyyy-MM-dd') : ''}"/>
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">配图：<span class="ui-form-required">*</span></label>
                <div class="file_wrap">
                	<div id="meetImageDiv">
                		<c:choose>
	                		<c:when test="${empty tradeMeeting}">
	                			<p class="replace_text">点击上传</p>
	                		</c:when>
	                		<c:otherwise>
								<c:set value="${tradeMeeting.domainName}${tradeMeeting.meetImage}" var="url"></c:set>
	                    		<img src="${res}${url}" style="width:350px; height:200px;" alt="">
	                    		<input type="hidden" name="meetImage" value="${tradeMeeting.meetImage}" />
	                		</c:otherwise>
	                	</c:choose>
                	</div>
                    <input class="input_file" type="file" name="photo" id="attachtNames"/>
                    <input type="hidden" id="attach_img_hidden" value="${tradeMeeting.meetImage}" />
                </div>
                <p class="mt5 explain">（图片尺寸350*200px，仅支持JPG、GIF、PNG图片文件，且文件小于2M）</p>
            </div>
            <div class="ui-form-item vertical-tip">
                <label class="ui-label" for="">活动政策：<span class="ui-form-required">*</span></label>
                <textarea class="ui-textarea guide_textarea" name="meetSlogan" >${tradeMeeting.meetSlogan}</textarea>
            </div>
            <div class="ui-form-item vertical-tip">
                <label class="ui-label" for="">详细介绍：<span class="ui-form-required">*</span></label>
                <div class="inline-block">
                    <textarea id="myEditor" type="text/plain" name="meetMark" >${tradeMeeting.meetMark}</textarea>
                </div>
            </div>
            <div class="ui-form-item">
                <button id="js-saveButton" class="ui_button ui_button_morange">提交审核</button>
                <a id="js-cancelButton" class="ui_button ui_button_mgray ml10" href="javascript:void(0);">取&nbsp;消</a>
            </div>
        </form:form>

    </div>
</div>
</div>
<!-- footer(品牌商管理中心) -->
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />

<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${res}/scripts/brand/brand_manage.js"></script>
<script>
	// remindState 是否提醒用户(用户修改过表单就提醒)
	var remindState = false, tradeMeeting, validator;
    seajs.use(["validator", "ajaxFileUpload"], function (Validator) {
    	
    	tradeMeeting = {
    		// 初始化
    		init: function(){
    			add_meeting.init();
    		    rangeCalendar("startDate","endDate",true);
    		 	// 表单修改，维护 remindState 变量
    	    	$("#meetingForm").each(function(index, obj){
    	    		$(obj).change(function(){
    	    			remindState = true;
    	    		});
    	    	});
    		 	
    		 	//增加验证
    	    	validator = new Validator({
                    element: '#meetingForm',
        		    autoSubmit: false
                }).addItem({
                    element: '#js-select-brands',
                    required: true,
                    display: '品牌'
                }).addItem({
                    element: '[name=meetName]',
                    required: true,
                    display: '会议名称'
                }).addItem({
                    element: '[name=beginTimeD]',
                    required: true,
                    display: '举办时间'
                }).addItem({
                    element: '[name=meetSlogan]',
                    required: true,
                    display: '活动政策'
                }).addItem({
                    element: '#attach_img_hidden',
                    required: "true",
                    errormessageRequired: "请上传配图"
                }).addItem({
                    element: '#myEditor',
                    required: true,
                    display: '详细介绍'
                });
    		 	
    		 	//绑定图片上传事件
    	    	$('input[name="photo"]').bind('change', function(){
    	    		tradeMeeting.uploadImage($(this).attr('id'));
    	    	});
    		 	
    		 	// 保存
    		 	$(document).on('click','#js-saveButton',function(){
					validator.execute(function(hasError, results, element){
						if(!hasError){
							$("#js-saveButton").attr("disabled", true);
							tradeMeeting.save();
						}
					});
				});

    		 	// 取消
				$(document).on('click','#js-cancelButton',function(){
					tradeMeeting.cancel();
				});
    		},
    		// 上传图片
    		uploadImage: function(uploadId){
    			$.ajaxFileUpload({
    				url: '${ctx}/common/showImg',
           			secureuri: false,
           			fileElementId: uploadId,
           			dataType: 'json',
           			success: function(data)
           			{
           				//发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                       	$('#' + uploadId).bind('change', function(){
                       		tradeMeeting.uploadImage(uploadId);
                   		});
                       	if(data.code == zttx.SUCCESS)
                    	{
                            $("#attach_img_hidden").val("1");
                       		tradeMeeting.showImage(data.message);
                    	}
                    	else
                    	{
                    		remind("error", data.message);
                    	}
                	}
           		});
    		},
    		// 显示图片
    		showImage: function(url){
    			$("#meetImageDiv").html("");
    			$('<img style="width:350px; height:200px;"/>').attr("src", "${res}"+url).appendTo("#meetImageDiv");
    			$('<input type="hidden" name="meetImagePath"/>').attr("value", url).appendTo("#meetImageDiv");
    		},
    		// 保存
    		save: function(){
    			$.post("${ctx}/brand/tradeMeeting/save", $('#meetingForm').serialize(), function(data) {
                    $("#js-saveButton").attr("disabled", false);
    				if(data.code == zttx.SUCCESS)
    				{
    					remind("success", "保存成功", 1000, function(){
    						location.href = "${ctx}/brand/tradeMeeting/list";
    					});
    				}
    				else
    				{
    					remind("error", data.message);
    				}
    			}, "json");
    		},
    		// 取消
    		cancel: function(){
    			if(remindState && !confirm("确定取消保存？")) {
    				
    	   		} else {
					location.href = "${ctx}/brand/tradeMeeting/list";
    	   		}
    		}
    	}
    	tradeMeeting.init();
    });
</script>

</body>
</html>