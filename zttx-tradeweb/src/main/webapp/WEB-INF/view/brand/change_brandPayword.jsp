<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-我的账户-账户信息-支付密码管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/myAccount.css"/>
    <style>
        .explain{
            line-height: 24px;
            color: #71B83D;
        }
        .explainError{
            color: #E00;
        }
    </style>
</head>
<body>
	<jsp:include page="${ctx}/brand/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/brand/brandmenu"/>
<div class="main_con">
<div class="inner">
    <div class="bread_nav">
        <div class="fl">
            <i class="icon"></i>
            <a class="link" href="${ctx}/brand/center">管理中心</a>
            <span class="arrow">&gt;&gt;</span>
            <a class="link" href="${ctx}/brand/contact/list">我的账户</a>
            <span class="arrow">&gt;</span>
            <a class="link" href="${ctx}/brand/contact/list">账户信息</a>
            <span class="arrow">&gt;</span>
            <span class="current">支付密码管理</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
        </div>
    </div>
    <div class="password_manage">
        <div class="ui_tab">
            <ul class="ui_tab_items">
                <li class="ui_tab_item"><a href="${ctx}/brand/password">登录密码管理</a></li>
                <li class="ui_tab_item current"><a href="javascript:;">平台支付密码管理</a></li>
            </ul>
        </div>
        <form:form id="payword-form" class="password_form ui-form" method="post">
            <div class="ui-form-item">
                <label class="ui-label" for=""><span class="ui-form-required" >*</span>手机号：</label>
                <input id="mobile" style="width: 160px;" type="text" class="ui-input" readonly="readonly" disabled value="${userm.userMobile}">
                <div class="inline-block" style="height: 31px;">
                    <button class="code_btn" type="button" id="telcode-btn">获取验证码</button>
                    <span class="ml10 explain"></span>
                </div>
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for=""><span class="ui-form-required">*</span>验证码：</label>
                <input autocomplete="off" style="width: 80px;" type="text" class="ui-input" name="telCode">
                <div class="ui-form-explain"></div>
            </div>
            <div class="ui-form-item">
                             此密码为8637品牌超级代理平台支付密码
            </div>
            <div class="ui-form-item">
                <input type="text" style="display: none;" />
                <label class="ui-label" for=""><span class="ui-form-required">*</span>新密码：</label>
                <input autocomplete="off" id="password" name="newPassowrd" style="width: 258px;" type="password" class="ui-input">
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for=""><span class="ui-form-required" >*</span>重复密码：</label>
                <input id="password-confirmation" style="width: 258px;" type="password" class="ui-input">
            </div>
            <div class="ui-form-item">
               <input type="submit" class="ui_button ui_button_morange" value="保存修改"/>
            </div>
        </form:form>
    </div>
</div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${res}/scripts/plugin/jquery.countdown.js"></script>
<script type="text/javascript">
var validator;
seajs.use(['validator'], function(Validator){
	$('#telcode-btn').countdown({
        callback: function () {
            $.post("${ctx}/brand/center/getCaptcha", function(data){
            	if(data.code == zttx.SUCCESS){
            		$(".explain").removeClass("explainError").html("验证码已发送");
            	}else{
                    $(".explain").addClass("explainError").html(data.message);
            	}
            }, "json");
        }
    });
     Validator.addRule("specialPassword",function(options){
        var reg = /^(?:\d*|[a-zA-Z]*|[\x00-\x2f\x3a-\x40\x5b-\x60\x7b-\x7f]*)$/;
        var str = options.element.val();
        if(reg.test(str)){
            return false;
        }else{
            return true;
        }
    },"密码不能全为数字或字母")
	validator = new Validator({
		element: '#payword-form',
		autoSubmit: false,// 当验证通过后不自动提交
		onFormValidated: function(error,results,element)
		{
			if(!error){
				// 当表单验证通过，ajax提交表单
				$.post("${ctx}/brand/payword/update", $('#payword-form').serialize(), function(data){
					if(data.code == zttx.SUCCESS)
					{
						$(':input[name="telCode"]').val("");
						$('#password').val("");
						$('#password-confirmation').val("");
						remind("success", "修改成功", function(){
							window.location.reload();
						});
					}
					else 
					{
						remind("error", data.message);
					}
				}, "json");
           }
       }
    }).addItem({
        element: '[name=telCode]',
        required: true,
        rule: 'minlength{min:6} maxlength{max:6}',
        display: '验证码'
    }).addItem({
        element: '#password',
        required: true,
        rule: 'minlength{"min":8} maxlength{"max":20} specialPassword',
        display: '新密码'
    }).addItem({
        element: '#password-confirmation',
        required: true,
        rule: 'confirmation{target: "#password", name: "第一遍"}',
        display: '密码',
        errormessageRequired: '请再重复输入一遍密码，不能留空'
    });
});
</script>
</body>
</html>