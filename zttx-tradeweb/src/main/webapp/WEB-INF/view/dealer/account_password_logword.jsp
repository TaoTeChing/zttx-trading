<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zttx.web.consts.ZttxConst" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<c:set var="DCAPTCHA_SEND_SUCCESS" value="<%=com.zttx.web.consts.DealerConst.CAPTCHA_SEND_SUCCESS.getCode()%>"></c:set>
<c:set var="DCAPTCHA_EXIST" value="<%=com.zttx.web.consts.DealerConst.CAPTCHA_EXIST.getCode()%>"></c:set>
<c:set var="DVERIFY_SUCCESS" value="<%=com.zttx.web.consts.DealerConst.VERIFY_SUCCESS.getCode()%>"></c:set>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理中心-账户管理-登录密码管理</title>
<link href="${res}/styles/dealer/global.css" rel="stylesheet" />
<link href="${res}/styles/dealer/icons.css" rel="stylesheet" />
<link href="${res}/styles/dealer/account.css" rel="stylesheet" />
<style>
    .hqyzm {height:30px; border:1px #ddd solid; background-color:#EFEFEF; color:#666666; margin-left:5px; width:120px; cursor: pointer;}
    .gray {color:#CDCDCD;}
    .verify-tips-sus{
        padding-left: 20px;
        background: url("/images/common/right.png") left center no-repeat;
    }
    .verify-tips-err{
        padding-left: 20px;
        background: url("/images/common/error.png") left center no-repeat;
    }
</style>
</head>
<body>
	<!--完成-->
	<div class="container">
		<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
		<div class="em100">
			<div class="main clearfix pr">
				<jsp:include page="${ctx}/common/menuInfo/sidemenu">
					<jsp:param name="currSubMenu" value="/dealer/account/password"/>
					<jsp:param name="openId" value="12"/>
				</jsp:include>
				<div class="main-right">

					<!--面包屑-->
					<div class="main-grid mb10">
						<div class="panel-crumbs">
							<i class="icon i-setting"></i><a href="${ctx }/dealer/center">管理中心</a>
							>> <a href="${ctx}/dealer/dealerInfo/account/infor" title="">账户管理</a> > <span class="bb">密码管理</span> <a
								class="panel-crumbs-help" href="${ctx}/help/index"><i
								class="icon i-help"></i>帮助中心</a>
						</div>
					</div>
					<div class="inner">
						<div class="panel-tabs">
							<div class="panel-head">
								<ul class="clearfix">
									<li class="on"><a class="yahei fs14">登录密码管理</a></li>
									<li><span class="yahei fs14"><a href="<%=ZttxConst.PAYAPI_WEBURL_RESETPWD%>">平台支付密码管理</a></span></li>
									<!-- <li><span class="yahei fs14"><a href="${ctx}/dealer/account/erpword">招财猫密码管理</a></span></li> -->
								</ul>
							</div>
									<div class="passchange-login">
										<form:form id='loginpassManageForm' method="post">
											<input type="hidden" name="userType" value="${userType}" />
											<dl class="ui-form-item">
												<dt class="ui-label">
													<span class="c-r-on ui-form-required">*</span> 手 机 号:
												</dt>
												<dd>
													<input disabled="disabled" class="text yahei ui-input" type="text" name="userMobile" value=" ${userMobile}" />
													<%--<input type="button" value="获取验证码" onclick="getPhoneCaptcha('loginpassManageForm input[name=userMobile]','${userType}');" class="yahei ui-button ui-button-mwhite hqyzm"/>--%>
                                                    <button type="button" onclick="getPhoneCaptcha('loginpassManageForm input[name=userMobile]','${userType}');" class="yahei ui-button ui-button-mwhite hqyzm">获取验证码</button>
													<div id="getCaptchaInfo" class="mt10"></div>
                                                    <div class="ui-form-explain">手机号码</div>
												</dd>
											</dl>
											<dl class="ui-form-item">
												<dt class="ui-label">
													<span class="c-r-on  ui-form-required">*</span> 验 证 码:
												</dt>
												<dd>
													<input autocomplete="off" class="text yahei short ui-input" type="text" name="telCode" value=""/>
													<span id="verifyResultInfo" style="color: red;"></span>
													<div class="ui-form-explain">请输入您的收到的验证码</div>
												</dd>
											</dl>
											<dl class="ui-form-item">
												<dt class="ui-label">
													<span class="c-r-on  ui-form-required">*</span> 新 密 码:
												</dt>
												<dd>
                                                    <input type="text" style="display: none;" />
													<input autocomplete="off" class="text yahei long ui-input" id="password" type="password" name="newPwd" value="" />
													<div class="ui-form-explain">请输入您的新密码</div>
													<!--<i class="c-r-on ml10">*格式有误</i></dd>-->
												</dd>
											</dl>
											<dl class="ui-form-item">
												<dt class="ui-label">
													<span class="c-r-on  ui-form-required">*</span> 重复密码:
												</dt>
												<dd>
													<input class="text yahei long ui-input" id="password-confirmation" type="password" value="" />
													<div class="ui-form-explain">请再输入一次您的新密码</div>
												</dd>
											</dl>
											<dl class="ui-form-item">

												<dd>
													<input type="submit" class="ui-button-lorange ui-button yahei fs14" value="保存修改"/>
												</dd>
											</dl>
										</form:form>
									</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
	<div id="newCard" class="newCard hide"></div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
	<script>
		seajs.use(['validator', '$'], function (Validator, $) {
		   //$(".hqyzm").removeAttr("disabled");
           //非异步
            Validator.addRule("ispassword",function(option){
                var _reg =/^[\_\@\.\-a-zA-Z0-9]{6,16}$/;
                return _reg.test(option.element.val());
            },"请输入6-16位的{{display}}，支持字母、数字、特殊字符_.@-");
           var validator = new Validator({
               element: '#loginpassManageForm',
               autoSubmit: false,// 当验证通过后不自动提交
               onFormValidated: function(error,results,element){
               		if(!error){
	               		// 当表单验证通过，ajax提交表单
	               		$.ajax({
							type : "POST",
							url : "${ctx}/password/center/updateHasLogin",  /*原路径 ${ctx}/dealer/password/update  */
							data : $('#loginpassManageForm').serialize(),
							dataType: "json",
							success : function(json) {
								//json.code==parseInt('${DCAPTCHA_SEND_SUCCESS}')|json.code==parseInt('${DCAPTCHA_EXIST}')|json.code==parseInt('${DVERIFY_SUCCESS}')
								if(json.code == 126000)
								{
									remind("success","登录密码修改成功");
									$('#getCaptchaInfo').html('');
                                    $('#loginpassManageForm')[0].reset();//0618 zxb add BUG#2277
                                    resetValidatorError('#loginpassManageForm');//0618 zxb add BUG#2277
								}
								else 
								{
							    	remind("error",json.message);
								}
							},
							error:function(XMLHttpRequest, textStatus, errorThrown){
								alert(errorThrown);
							}
						});
               		}
               }
           });
           
           validator.addItem({
               element: '[name=userMobile]',
               required: true,
               rule: 'mobile',
               display: '手机号码'
           }).addItem({
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
           })

        });
        
        // 发送手机验证码
        function getPhoneCaptcha(name, userType){
			var obj = $("#"+name);
			var userMobile = $.trim(obj.val());
			$.ajax({
				type : "POST",
				url : "${ctx}/common/password/mobile/getCaptcha",   //${ctx}/common/getCaptcha
				data : {userMobile:userMobile,userType:userType},
				dataType: "json",
				success : function(json) {
                    $('#getCaptchaInfo').removeClass("verify-tips-sus");
                    $('#getCaptchaInfo').removeClass("verify-tips-err");
					var validObj = obj.next(".Validform_checktip");
					if(json.code==${DCAPTCHA_SEND_SUCCESS} || json.code==${DCAPTCHA_EXIST} || json.code==${DVERIFY_SUCCESS} || json.code == 121000 || json.code == 126000){
						$('#getCaptchaInfo').text("验证码已发送到您的手机，30分钟内输入有效，请勿泄露");
				    	$('#getCaptchaInfo').attr("style", "color:#71b83d").addClass("verify-tips-sus");
				    	var waitTiem = 60;
                        getCodeFn($(".hqyzm"),waitTiem);
					}else{
						$('#getCaptchaInfo').text(json.message);
				    	$('#getCaptchaInfo').attr("style", "color:red").addClass("verify-tips-err");
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					console.log(errorThrown);
				}
			});
		}
    </script>
</body>
</html>
