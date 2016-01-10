<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-品牌管理-资讯管理</title>
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
    <div class="inner">
        <!-- 内容都放这个地方  -->
	    <jsp:include page="${ctx}/WEB-INF/view/brand/show_brandNews_bar.jsp">
    		<jsp:param name="m" value="2" />
    	</jsp:include>

        <form:form id="meetingForm" class="ui-form mt20  info-form" action="${brandMeeting.refrenceId==null ? 'sava' : 'update'}" method="post">
            <input type="hidden" name="refrenceId" value="${brandMeeting.refrenceId}" />
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    选择品牌：<span class="ui-form-required">*</span>
                </label>
                <%-- <input style="width: 222px;" type="text" class="ui-input" name="brandesId" value="${brandMeeting.brandesId}" /> --%>
            	<div class="inline-block">
	                <select id="js-select-brands" style="width: 210px;" name="brandsId" class="js_select ui-select">
                        <c:if test="${brandesInfos.size() == 0 || brandesInfos.size() > 1}">
	                        <option value="">请选择品牌...</option>
                        </c:if>
	                    <c:forEach items="${brandesInfos}" var="brandesInfo">
	                        <option value="${brandesInfo.refrenceId}" 
	                        	${brandesInfo.refrenceId== brandMeeting.brandsId ? "selected" : ""}>${brandesInfo.brandName}</option>
	                    </c:forEach>
	                </select>
	            </div>
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    会议名称：<span class="ui-form-required">*</span>
                </label>
                <input style="width: 392px;" type="text" class="ui-input" name="meetName" value="${brandMeeting.meetName}" />
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    举办时间：<span class="ui-form-required">*</span>
                </label>
                <input style="width: 116px;" readonly="readonly" type="text" class="ui-input Wdate" id="startDate" name="beginTimeD"
                	value="${brandMeeting.beginTime!=null ? fns:getTimeFromLong(brandMeeting.beginTime,'yyyy-MM-dd') : ''}"/>
                -
                <input style="width: 116px;" readonly="readonly" type="text" class="ui-input Wdate" id="endDate" name="endTimeD"
                	value="${brandMeeting.endTime!=null ? fns:getTimeFromLong(brandMeeting.endTime,'yyyy-MM-dd') : ''}"/>
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    举办地点：<span class="ui-form-required">*</span>
                </label>
                <input style="width: 646px;" type="text" class="ui-input" name="meetAddress" value="${brandMeeting.meetAddress}" />
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    联系方式：<span class="ui-form-required">*</span>
                </label>
                <input style="width: 220px;" type="text" class="ui-input" name="meetContact" value="${empty brandMeeting ? brandUserm.userMobile : brandMeeting.meetContact}" />
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    邮箱地址：<span class="ui-form-required">*</span>
                </label>
                <input style="width: 220px;" type="text" class="ui-input" name="meetMail"  value="${empty brandMeeting ? brandUserm.userMail : brandMeeting.meetMail}" />
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    配图：<span class="ui-form-required">*</span>
                </label>
                <div class="file_wrap">
                	<div id="meetImageDiv">
                		<c:choose>
	                		<c:when test="${brandMeeting.meetImage==null}">
	                			<p class="replace_text">点击上传</p>
	                		</c:when>
	                		<c:otherwise>
								<c:set value="${brandMeeting.domainName}${brandMeeting.meetImage}" var="url"></c:set>
	                    		<img src="${res}${url}" style="width:350px;height:200px;" alt="">
	                    		<input type="hidden" name="attachtNames" value="${brandMeeting.meetImage}" />
	                		</c:otherwise>
	                	</c:choose>
                	</div>
                    <input class="input_file" type="file" name="photo" id="attachtNames"/>
                    <input type="hidden" id="attach_img_hidden" value="${brandMeeting.meetImage}" />
                </div>
                <p class="mt5 explain">（图片尺寸350*200px，仅支持JPG、GIF、PNG图片文件，且文件小于2M）</p>
            </div>
            <div class="ui-form-item vertical-tip">
                <label class="ui-label" for="">
                    导语：<span class="ui-form-required">*</span>
                </label>
                <textarea class="ui-textarea guide_textarea" name="meetSlogan" >${brandMeeting.meetSlogan}</textarea>
            </div>
            <div class="ui-form-item vertical-tip">
                <label class="ui-label" for="">
                    详细介绍：<span class="ui-form-required">*</span>
                </label>
                <div class="inline-block">
                    <textarea id="myEditor" type="text/plain" name="meetMark" >${brandMeeting.meetMark}</textarea>
                </div>
            </div>
            <div class="ui-form-item">
                <button class="ui_button ui_button_morange" type="submit">提交审核</button>
                <a href="javascript: cancel();" class="ui_button ui_button_mgray ml10" type="button">取&nbsp;消</a>
            </div>
        </form:form>

    </div>
</div>
</div>
<!-- footer(品牌商管理中心) -->
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />

<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${res}/scripts/brand/brand_manage.js"></script>
<tags:message content="${message}" width="300"></tags:message>
<script>
	add_meeting.init();

    seajs.use(["my97DatePicker"], function () {
        $("#startDate").on("focus", function () {
              WdatePicker({ maxDate: '#F{$dp.$D(\'endDate\')}', minDate: '%y-%M-%d' });
        })

        $("#endDate").on("focus", function () {
            WdatePicker({ minDate: '#F{$dp.$D(\'startDate\',{d:1});}'});
        })
    });

    rangeCalendar("startDate","endDate",true);
	
	var remind = false;// 是否提醒用户(用户修改过表单就提醒)
	
    seajs.use(['validator', '$'], function (Validator, $) {
    	
    	// 表单修改，维护 remind 变量
    	$("#meetingForm").each(function(index, obj){
    		$(obj).change(function(){
    			remind = true;
    		});
    	});
    	
    	$('input[name="photo"]').bind('change', function(){uploadImage($(this).attr('id'));});
    
        $(function () {
        	
        	Validator.addRule('tel', /^((\+?[0-9]{2,4}\-[0-9]{3,4}\-)|([0-9]{3,4}\-))?([0-9]{7,8})(\-[0-9]+)?$/);
        	
        	var tel = Validator.getRule("tel");
        	
        	var mobileOrTel = tel.or("mobile");
        	
        	Validator.addRule("mobileOrTel",mobileOrTel,'请输入正确的手机号或电话号码（区号-电话）')
        	
            var validator = new Validator({
                element: '#meetingForm'
            });
            
            

            validator.addItem({
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
                display: '举办开始时间'
            }).addItem({
                element: '[name=endTimeD]',
                required: true,
                display: '举办结束时间'
            }).addItem({
                element: '[name=meetAddress]',
                required: true,
                display: '举办地点'
            }).addItem({
                element: '[name=meetContact]',
                required: true,
                rule: "mobileOrTel",
                display: '联系方式'
            }).addItem({
                element: '[name=meetMail]',
                required: true,
                rule: "email",
                display: '邮箱地址'
            }).addItem({
                element: '[name=meetSlogan]',
                required: true,
                display: '导语'
            }).addItem({
                element: '#attach_img_hidden',
                required: "true",
                errormessageRequired: "请上传配图"
            }).addItem({
                element: '#myEditor',
                required: true,
                display: '详细介绍'
            });
        });
    });
    
    function showImage(uploadId, url){
    		var html = '<img style="width:350px;height:200px;" src="${res}'+ url +'">' +
    				   '<input type="hidden" name="'+ uploadId +'" value="' + url + '" />';
        	$("#meetImageDiv").html(html);
    	}
    	
   	function uploadImage(uploadId){
   		seajs.use(["$","ajaxFileUpload"],function($){
   			$.ajaxFileUpload({
       			url: '${ctx}/common/showImg',
       			secureuri: false,
                   fileElementId: uploadId,
                   dataType: 'json',
                   success: function(data)
                   {
                   	//发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                   	$('#' + uploadId).bind('change', function(){
               			uploadImage(uploadId);
               		});
                   	if(data.code != zttx.SUCCESS)
                   	{
       					alert(data.message);
                   	}
                   	else
                   	{
                        $("#attach_img_hidden").val("1");
                   		showImage(uploadId,data.message);
                   	}
            	}
       		});
   	    });
   	} 
   	
   	// 取消
   	function cancel(){
   		if(remind==true) {
   			if(confirm("确定取消保存？")==true){
   				window.history.back();
   			}
   		} else {
   			window.history.back();
   		}
   	}
   	
</script>

</body>
</html>