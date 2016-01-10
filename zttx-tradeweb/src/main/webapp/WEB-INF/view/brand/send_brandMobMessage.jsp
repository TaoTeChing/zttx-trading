<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/include/taglib.jsp"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
    <title>管理中心-我的消息-发送新消息</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/myMessage.css"/>
</head>
<body>
	<jsp:include page="${ctx}/brand/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/brand/brandmenu"/>
<div class="main_con">
    <div class="inner">
        <!-- 内容都放这个地方  -->

        <!-- 面包导航，并不是每个页面都有 -->
        <div class="bread_nav">
            <div class="fl">
                <i class="icon"></i>
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
        <div class="mymessage">
            <div class="js_grademanage_menu clearfix">
                <ul>
                    <li><a href="${ctx}/brand/message/send">发送消息</a></li>
                    <li class="selected"><a href="${ctx}/brand/message/sendMob">发送手机短信</a></li>
                </ul>
            </div>
            <div class="js_grademanage_con">
                <div class="mymessage-send mymessage-mobilemsg">
                    <form:form id="js-msg-form" class="ui-form mt15" action="${ctx}/brand/message/sendMobSubmit" method="post">
                        <input type="hidden" id="checkResult" />
                        <div class="ui-form-item">
                            <label class="ui-label">
                                短信内容：&nbsp;&nbsp;&nbsp;
                            </label>
                            <textarea class="ui-textarea" name="mobContent" style="width: 770px; height: 50px"></textarea>
                        </div>
                        <jsp:include page="/WEB-INF/view/brand/list_dealer_level.jsp">
                    		<jsp:param value="" name="brandsName"/>
                    		<jsp:param value="3" name="addLevelType"/>
                    		<jsp:param value="" name="lineId"/>
                    	</jsp:include>
                        <div class="ui-form-item">
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
<script src="${res}/scripts/brand/myMessage.js"></script>
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
            		$.post("${ctx}/brand/message/sendDealerMob", $form.serializeArray(), function(data){
            			if(data.code == zttx.SUCCESS){
            				remind("success", "发送成功");
            				$form.get(0).reset();
            			}else{
        					remind("error", data.object);
        				}
            		}, "json");
            	}
            }
        }).addItem({
            element: ':input[name="mobContent"]',
            required: true,
            errormessage: '请输入短信内容'
        }).addItem({
            element: '#checkResult',
            required: true,
            onItemValidate: function(error){
            	$('#checkResult').val('true');
            }
        });
	});
   
</script>
</body>
</html>