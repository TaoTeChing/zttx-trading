<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-我的账户-账户信息-安全验证</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/myAccount.css"/>
    <style>
        .code_btn_loading{padding-left: 26px;background: url("/images/common/loading-16-16.gif") no-repeat 5px center;}
        .verify-tips{padding: 5px 0 5px 20px;background: url("/images/common/right.png") left center no-repeat;color: #85c749;}
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
            <span class="current">安全验证</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
        </div>
    </div>
<div class="inner">
    <div class="safe_verification">
        <ul class="method_list">
            <li class="item">
                <div class="current_status">
                    <c:choose>
                    	<c:when test="${brandUserm.mobileVerify == false}">
		                    <div class="fail">
		                        <i class="iconfont">&#xe627;</i>
		                        <p>未认证</p>
		                    </div>
                    	</c:when>
                    	<c:otherwise>
		                    <div class="success">
		                        <i class="iconfont">&#xe628;</i>
		                        <p>已认证</p>
	                        </div>
                    	</c:otherwise>
                    </c:choose>
                </div>
                <div class="intro">
                    <i class="icon mobile_icon"></i>
                    <div class="text">
                        <h3>手机认证</h3>
                        <p>
                            快速个人移动通信认证<br />
                            方便快捷更可靠
                        </p>
                    </div>
                </div>
                <div class="certification">
                    <!-- 手机未认证 -->
                    <!--form data-widget="validator" id="mobile_certification_form" class="ui-form" action="#">
                        <div class="ui-form-item">
                            <label class="ui-label" for="">
                                <span class="ui-form-required">*</span>手机号：
                            </label>
                            <input required data-display="手机号"  style="width: 162px;" type="text" class="ui-input" value="15852625240" disabled>
                            <button class="code_btn" type="button">获取验证码</button>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label" for="">
                                <span class="ui-form-required">*</span>验证码：
                            </label>
                            <input required data-display="验证码" style="width: 82px;" type="text" class="ui-input" />
                        </div>
                        <div class="ui-form-item">
                            <button class="ui_button ui_button_morange" type="submit">提交</button>
                        </div>
                    </form-->
                    <!-- 手机已认证 -->
                    <div class="has_certification">
                        您的手机号：<span id="id_dealerMobile">${brandUserm.userMobile}</span>
                        <c:choose>
                        	<c:when test="${brandUserm.mobileVerify == false}">
                        	 	<span class=" fail">未通过认证</span>
                        	</c:when>
                        	<c:otherwise>
		                        <span class="explain">已通过认证</span>
                        	</c:otherwise>
                        </c:choose>
                        <a class="js_mobile_change change_btn" href="javascript:;">更换手机号</a>
                    </div>
                    <div id="change_mobile_pop" class="change_pop" style="display: none;">
                        <div class="ui-head">
                            <h3>更换手机号</h3>
                        </div>
                        <p class="mt5 tip">
                            已向您的手机<span class="forget_link">${brandUserm.userMobile}</span>发送验证码，请注意查收
                        </p>
                        <form:form id="change_mobile_form" data-widget="validator" class="ui-form" action="#">
                            <div class="ui-form-item">
                                <label class="ui-label" for="">
                                    <span class="ui-form-required">*</span>验证码：
                                </label>
                                <input id="old_verifyCode" required data-display="验证码" style="width: 82px;" type="text" class="ui-input">
                                <a class="forget_link" href="${ctx}/common/securityCert/brand/securityCert" target="_blank">旧号码已经停用了，收不到验证码？</a>
                            </div>
                            <div class="ui-form-item">
                                <label class="ui-label" for="">
                                    <span class="ui-form-required">*</span>新的手机号：
                                </label>
                                <input id="new_mobile" required data-display="手机号" data-rule="mobile" style="width: 162px;" type="text" class="ui-input">
                                <button type="button" id="get_mobile_code" class="code_btn">获取验证码</button>
                            </div>
                            <div id="verify-tips" class="ui-form-item" style="display: none;">
                                <div class="verify-tips">验证码已发送到您的手机，30分钟内输入有效，请勿泄露</div>
                            </div>
                            <div class="ui-form-item">
                                <label class="ui-label" for="">
                                    <span class="ui-form-required">*</span>验证码：
                                </label>
                                <input id="new_verifyCode" required data-display="验证码" style="width: 82px;" type="text" class="ui-input">
                            </div>
                            <div class="ui-form-item">
                                <button class="ui_button ui_button_lblue" type="submit">提交</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </li>
            <li class="item">
                <div class="current_status">
                    <c:choose>
                    	<c:when test="${brandUserm.mailVerify == false}">
		                    <div  style="color: red">
		                        <i class="iconfont">&#xe627;</i>
		                        <p>未认证</p>
		                    </div>
                    	</c:when>
                    	<c:otherwise>
		                    <div class="success">
		                        <i class="iconfont">&#xe628;</i>
		                        <p>已认证</p>
	                        </div>
                    	</c:otherwise>
                    </c:choose>
                </div>
                <div class="intro">
                    <i class="icon email_icon"></i>
                    <div class="text">
                        <h3>邮箱认证</h3>
                        <p>
                            账户密码更安全<br />
                            邮箱提醒账户实时动态
                        </p>
                    </div>
                </div>
                <div class="certification">
                    <!-- 邮箱未认证 -->
                    <!--form data-widget="validator" id="email_certification_form" class="ui-form" action="#">
                        <div class="ui-form-item">
                            <label class="ui-label" for="">
                                <span class="ui-form-required">*</span>邮箱：
                            </label>
                            <input required data-display="邮箱" data-rule="email" style="width: 162px;" type="text" class="ui-input">
                            <button class="code_btn" type="button">获取验证码</button>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label" for="">
                                <span class="ui-form-required">*</span>验证码：
                            </label>
                            <input required data-display="验证码" style="width: 82px;" type="text" class="ui-input" />
                        </div>
                        <div class="ui-form-item">
                            <button class="ui_button ui_button_morange" type="submit">提交</button>
                        </div>
                    </form-->
                    <!-- 邮箱已认证 -->
                    <div class="has_certification">
                        您的邮箱：${brandUserm.userMail }
                        <c:choose>
                        	<c:when test="${brandUserm.mailVerify == false}">
                        	 	<span style="color: red">未通过认证</span>
		                        <a class="js_email_change change_btn" href="javascript:;">更换邮箱</a>
                        	</c:when>
                        	<c:otherwise>
		                        <span class="explain">已通过认证</span>
                        	</c:otherwise>
                        </c:choose>
                    </div>
                    <div id="change_email_pop" class="change_pop" style="display: none;">
                        <div class="ui-head">
                            <h3>更换邮箱</h3>
                        </div>
                        <p class="mt5 tip">
                        </p>
                        <form:form id="change_email_form" data-widget="validator" class="ui-form" action="#">
                            <div class="ui-form-item">
                                <label class="ui-label" for="">
                                    <span class="ui-form-required">*</span>新的邮箱：
                                </label>
                                <input id="id_email" required data-display="邮箱" data-rule="email" style="width: 162px;" type="text" class="ui-input">
                            </div>
                            <div class="ui-form-item">
                                <button class="ui_button ui_button_lblue" type="submit">提交</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
</div>
</div>
	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script>
    var verification = {
        init: function(){
            this.certification();
            this.showPop();
        },
        sendTelCode:function () {
            $.post("${ctx}/brand/info/center/getCaptcha", function(data){
            	if(data.code == zttx.SUCCESS){
            		//remind("success", "发送成功");
            	}else{
            		//remind("error", data.message);
            	}
            }, "json");
        },
        certification: function(){
            seajs.use(['validator', 'widget'], function(Validator, Widget){
                Widget.autoRenderAll();
                if($("#mobile_certification_form").length > 0){
                    var Core = Validator.query("#mobile_certification_form");
                    Core.set("autoSubmit",false);
                    Core.on('formValidated', function(error, message, elem) {
                        if(!error){
                            alert("手机进行提交");
                        }
                    });
                }
                if($("#email_certification_form").length > 0){
                    var Core = Validator.query("#email_certification_form");
                    Core.set("autoSubmit",false);
                    Core.on('formValidated', function(error, message, elem) {
                        if(!error){
                            alert("邮箱进行提交");
                        }
                    });
                }
            });
        },
        showPop: function(){
            //js_mobile_change
            seajs.use(["dialog"],function(Dialog){
                var d1 = new Dialog({
                    content: "#change_mobile_pop",
                    width: 508
                });
                var d2 = new Dialog({
                    content: "#change_email_pop",
                    width: 508
                });
                d1.before("show",function(){
                    $("#change_mobile_form")[0].reset();
                    resetValidatorError("#change_mobile_form");
                })
                d1.after('show', function() {
                    $('#change_mobile_pop').show();
                });
                d2.after('show', function() {
                    $('#change_email_pop').show();
                });
                //更改手机号
                $(document).on("click",".js_mobile_change",function(){
                	//弹出更新手机号之前发送短信给原手机号
                    verification.sendTelCode();
	                
                    d1.show();
                    seajs.use(['validator', 'widget'], function(Validator, Widget){
                        Widget.autoRenderAll();
                        var Core = Validator.query("#change_mobile_form");
                        Core.set("autoSubmit",false);
                        Core.on('formValidated', function(error, message, elem) {
                            if(!error){
	                            var old_verifyCode = $("#old_verifyCode").val();
	                            var new_moblie = $("#new_mobile").val();
	                            var new_verifyCode = $("#new_verifyCode").val();
	                            $.ajax({
						 		  url:"${ctx}/brand/info/mobile",
						          type: 'post',
						          data: {oldVerifyCode: old_verifyCode, newUserMobile: new_moblie, newVerifyCode: new_verifyCode},
						          dataType: 'json',
						          success: function(data, textStatus)
						          {
						          	if(data.code == zttx.SUCCESS)
						          	{
								        remind("success","手机认证成功！", 1000,function(){
							          		window.location.reload();
								        });
						          	}
						          	else
						          	{
						          		remind("error",data.message);
						          	}
						          }
					          });
                            }
                        });
                    });
                });
                //更改邮箱
                $(document).on("click",".js_email_change",function(){
                    d2.show();
                    seajs.use(['validator', 'widget'], function(Validator, Widget){
                        Widget.autoRenderAll();
                        var Core = Validator.query("#change_email_form");
                        Core.set("autoSubmit",false);
                        Core.on('formValidated', function(error, message, elem) {
                            if(!error){
                                var id_email = $("#id_email").val();
	                            var csrfToken = $("input[name='csrfToken']").val();
				                var new_values = 'userCate=0&emailAddr='+ id_email +'&csrfToken='+ csrfToken;
				          		$.ajax({
						 		  url:"${ctx}/common/emailValidate/create",
						          type: 'post',
						          data: new_values,
						          dataType: 'json',
						          success: function(data, textStatus)
						          {
						          	if(data.code == zttx.SUCCESS)
						          	{
						          		remind("success","认证邮件发送成功！");
						          		d2.hide();
						          	}
						          	else
						          	{
						          		remind("error",data.message);
						          	}
						          }
					          });
                            }
                        });
                    });
                });
            });
        }
    }
    verification.init();
    
     $(function () {
         //发送短信
         $("#get_mobile_code").on("click",function(){
             var $this = $(this);
             var mobile = $("#new_mobile").val();
             if(!mobileNumReg(mobile)){
                 remind("error","新手机号码不正确");
                 return ;
             }
             $this.addClass("code_btn_loading");
             $.post("${ctx}/common/register/phoneVerify", {userType: 0, userMobile: mobile},function(data){
                 $this.removeClass("code_btn_loading");
                 if(data.code == 0){
                     //remind("success", "发送成功");
                     $("#verify-tips").show();
                     getCodeFn($this);
                 }else{
                     $("#verify-tips").hide();
                     remind("error", data.message);
                 }
             }, "json");
         })
      });
</script>
</body>
</html>