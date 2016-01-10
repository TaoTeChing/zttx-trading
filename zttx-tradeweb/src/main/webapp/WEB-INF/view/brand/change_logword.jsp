<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zttx.web.consts.ZttxConst" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-我的账户-账户信息-登录密码管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/myAccount.css"/>
    <style>
        .explain{line-height: 24px;padding: 5px 0 5px 20px;color: #71B83D;}
        .explainError{color: #e00;padding-left: 20px;background: url("/images/common/error.png") left center no-repeat;}
        .explainSuccess{padding-left: 20px;background: url("/images/common/right.png") left center no-repeat;}
        .code_btn_loading{padding-left: 26px;background: url("/images/common/loading-16-16.gif") no-repeat 5px center;}
    </style>
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
            <a class="link" href="${ctx}/brand/contact/list">我的账户</a>
            <span class="arrow">&gt;</span>
            <a class="link" href="${ctx}/brand/contact/list">账户信息</a>
            <span class="arrow">&gt;</span>
            <span class="current">登录密码管理</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
        </div>
    </div>
<div class="inner">
    <div class="password_manage">
        <div class="ui_tab">
            <ul class="ui_tab_items clearfix">
                <li class="ui_tab_item current"><a href="javascript:;">登录密码管理</a></li>
                <li class="ui_tab_item"><a href="<%=ZttxConst.PAYAPI_WEBURL_RESETPWD%>">平台支付密码管理</a></li>
            </ul>
        </div>
        <form:form id="password_form" class="password_form ui-form" method="post">
            <div class="ui-form-item">
                <label class="ui-label" for=""><span class="ui-form-required">*</span>手机号：</label>
                <input id="mobile" class="ui-input" style="width: 160px;" type="text" readonly="readonly" disabled value="${userm.userMobile}"  />
                <div class="inline-block">
                    <button class="code_btn" type="button" id="telcode-btn">获取验证码</button>
                    <span class="explain ml10"></span>
                </div>
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for=""><span class="ui-form-required">*</span>验证码：</label>
                <input autocomplete="off" style="width: 80px;" type="text" class="ui-input" name="telCode">
                <span style="color: red;"></span>
            </div>
            <div class="ui-form-item">
                <input type="text" style="display: none;" />
                <label class="ui-label" for=""><span class="ui-form-required">*</span>新密码：</label>
                <input autocomplete="off" id="password" style="width: 258px;" type="password" class="ui-input" name="newPwd">
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for=""><span class="ui-form-required">*</span>重复密码：</label>
                <input id="password-confirmation" style="width: 258px;" type="password" class="ui-input">
            </div>
            <div class="ui-form-item">
                <button type="submit" class="ui_button ui_button_lblue">保存修改</button>
            </div>
        </form:form>
    </div>
</div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script>
	seajs.use(['validator'], function(Validator){
		
		$('#telcode-btn').click(function(){
			var _this = $(this);
            _this.addClass("code_btn_loading");
			$.post("${ctx}/common/password/mobile/getCaptcha", function(data){
                _this.removeClass("code_btn_loading");
            	if(data.code == zttx.SUCCESS){
                    $(".explain").removeClass("explainError").addClass("explainSuccess").html("验证码已发送到您的手机，30分钟内输入有效，请勿泄露");
                    getCodeFn(_this,60);
            	}else if(data.code == '126050'){
                    $(".explain").removeClass("explainError").addClass("explainSuccess").html(data.message);
            	}else{
                    $(".explain").addClass("explainError").removeClass("explainSuccess").html(data.message);
            	}
            }, "json");
			
		});
        Validator.addRule("ispassword",function(option){
            var _reg =/^[\_\@\.\-a-zA-Z0-9]{6,16}$/;
            return _reg.test(option.element.val());
        },"请输入6-16位的{{display}}，支持字母、数字、特殊字符_.@-");
        var v1 = new Validator({
			element: '#password_form',
			autoSubmit: false,// 当验证通过后不自动提交
			onFormValidated: function(error,results,element)
			{
				if(!error){
					// 当表单验证通过，ajax提交表单
					//原密码更新路径 ${ctx}/brand/password/update
					$.post("${ctx}/common/password/center/updateHasLogin", $('#password_form').serialize(), function(data){
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
        });
        v1.addItem({
            element: '[name=telCode]',
            required: true,
            rule: 'minlength{min:6} maxlength{max:6}',
            display: '验证码'
        }).addItem({
            element: '#password',
            required: true,
            rule: 'minlength{"min":6} maxlength{"max":16} ispassword',
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