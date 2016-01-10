<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
    <title>管理中心-我的消息-发送新消息</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/myMessage.css"/>
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
	<div class="main layout">
        <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
<div class="main_con">
    <div class="bread_nav">
        <div class="fl">
            <a class="link" href="${ctx}/brand/center">管理中心</a>
            <span class="arrow">&gt;&gt;</span>
            <a class="link" href="${ctx}/brand/message/list">我的消息</a>
            <span class="arrow">&gt;</span>
            <span class="current">发送新消息</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
        </div>
    </div>
    <div class="inner">
        <!-- 内容都放这个地方  -->
        <div class="mymessage">
            <div class="js_grademanage_menu clearfix">
                <ul class="clearfix">
                    <li class="selected"><a href="${ctx}/brand/message/send">发送消息</a></li>
                    <%--<li><a href="${ctx}/brand/message/sendMob">发送手机短信</a></li>
                --%></ul>
            </div>
            <div class="js_grademanage_con">
                <div class="mymessage-send">
                    <div class="tips">
                        <i class="v2-icon-explain"></i>
                        说明：选择所有终端商，将会把信息发送给所有与您之间达成合作关系的终端商；选择指定终端商，您可以选择性的将消息发送给指定的终端商
                    </div>
                    <form:form id="js-msg-form" class="ui-form mt15" action="${ctx}/brand/message/sendSubmit" method="post">
                        <input type="hidden" id="checkResult" />
                        <div class="ui-form-item">
                            <label class="ui-label">
                                消息标题：
                                <span class="ui-form-required">*</span>
                            </label>
                            <input type="text" name="title" class="ui-input" style="width: 470px" />
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">
                                短信内容：&nbsp;&nbsp;&nbsp;
                            </label>
                            <div class="inline-block">
                                <script id="myEditor" name="content" type="text/plain"></script>
                            </div>
                        </div>
                        <jsp:include page="/WEB-INF/view/brand/list_dealer_level.jsp">
                    		<jsp:param value="" name="brandsName"/>
                    		<jsp:param value="3" name="addLevelType"/>
                    		<jsp:param value="" name="lineId"/>
                    		<jsp:param value="所有终端商" name="title1"/>
                    		<jsp:param value="指定终端商" name="title2"/>
                    	</jsp:include>

                        <%--<div class="ui-form-item">
                            <label class="ui-label">
                                手机短信：&nbsp;&nbsp;&nbsp;
                            </label>
                            <div class="radio-group inline-block">
                                <label>
                                <input type="checkbox" name="sendMobNote" class="ui-checkbox"/>
                                一起发送到终端商手机上
                                </label>
                            </div>
                        </div>
                        --%><div class="ui-form-item">
                            <label class="ui-label">
                            </label>
                            <button id="js-sendMsg" type="submit" class="ui_button ui_button_sorange">发送消息</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<tags:message content="${message}" width="300"></tags:message>
<script src="${src}/brand/myMessage.js"></script>
<script>
	myMessagesend.init();

	seajs.use("validator",function(Validator){
		var validator = new Validator({
            element: '#js-msg-form',
            failSilently: true,
            stopOnError: true,
            autoSubmit: false,
            onFormValidated: function(error){
            	if(!error){
            		var $form = $("#js-msg-form");
            		$.post("${ctx}/brand/message/sendDealer", $form.serializeArray(), function(data){
            			if(data.code == zttx.SUCCESS){
            				remind("success", "发送成功");
            				$form.get(0).reset();
            				$("#myEditor").html("");
                            $(".dealers_layer").hide();
            			}else{
        					remind("error", data.rows[0]);
        				}
            		}, "json");
            	}
            }
        }).addItem({
            element: ':input[name="title"]',
            required: true,
            errormessage: '请输入消息标题'
        }).addItem({
            element: '#checkResult',
            required: true,
            onItemValidate: function(){
            	$('#checkResult').val('true');
            }
        });
	});


</script>
</body>
</html>