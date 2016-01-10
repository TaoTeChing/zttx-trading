<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<%--
  @(#)change_payword 2014/3/21 14:14
  Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>管理中心-账户管理-平台支付密码管理</title>
	<link href="${res}/styles/dealer/global.css" rel="stylesheet" />
	
	<link href="${res}/styles/dealer/account.css" rel="stylesheet" />
    <style type="text/css">
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
</jsp:include>
<!--主体内容-->
<div class="main-right">
    <!--提示框-->
    <!--<div class="main-grid mb10">
        <div class="alert c-w warning">
            <b class="icon i-close">X</b><i class="icon"></i><a href="#">预警消息:您有一笔订单超过15天未付款! 2013-10-16</a>
        </div>
    </div>-->
    <!--面包屑-->
    <div class="main-grid mb10">
        <div class="panel-crumbs">
            <i class="icon i-setting"></i><a href="${ctx }/dealer/center">管理中心</a>
            >> <a href="${ctx}/dealer/account/infor" title="">账户管理</a> > <span class="bb">密码管理</span> <a
                class="panel-crumbs-help" href="${ctx}/help/index"><i
                class="icon i-help"></i>帮助中心</a>
        </div>
    </div>
    <div class="inner">
        <div class="panel-tabs">
            <div class="panel-head">
                <ul>
                    <li><span class="yahei fs14"><a href="${ctx}/dealer/account/password">登录密码管理</a></span></li>
                    <li class="on"><a class="yahei fs14">平台支付密码管理</a></li>
                    <li><span class="yahei fs14"><a  href="${ctx}/dealer/account/erpword">招财猫密码管理</a></span></li>
                </ul>
            </div>
                    <div class="passchange-login">
                        <form:form id="payword-form" class="ui-form" method="post">
                             <c:if test="${payword!=null}">
                            <dl class="ui-form-item">
                                <dt class="ui-label">
	                                <span class="ui-form-required ui-form-required">*</span> 手 机 号:
                                </dt>
                                <dd>
                                    <input id="mobile" name="userMobile" class="text yahei ui-input" type="text" name="mobile"
                                           data-display="手机号" value="${userm.userMobile}" disabled="disabled" />
                                    <input id="telcode-btn" type="button" value="获取验证码" onclick="sendValidatorCode()" class="yahei ui-button ui-button-mwhite"/><span id="getCaptchaInfo" class="ml5"></span>
                                    <div class="ui-form-explain">手机号码</div>
                                </dd>
                            </dl>
                            <dl class="ui-form-item">
                                <dt class="ui-label">
                                <span class="ui-form-required">*</span>  验 证 码:
                                </dt>
                                <dd>
                                    <input autocomplete="off" id="tel-code"
                                           class="text yahei short ui-input"
                                           type="text" value="" name="telCode"/>

                                    <div class="ui-form-explain">请输入您的收到的验证码
                                    </div>
                                </dd>
                            </dl>
                             </c:if>
                             <dl class="ui-form-item">
                             此密码为8637品牌超级代理平台支付密码
                             </dl>
                            <dl class="ui-form-item">
                                <dt class="ui-label"><span class="ui-form-required">*</span>
                                    新 密 码:
                                </dt>
                                <dd>
                                    <input type="text" style="display: none;" />
                                    <input autocomplete="off" id="password" name="newPassowrd"
                                           class="text yahei long ui-input"
                                           type="password"
                                           value="" data-display="新密码"
                                           data-explain="请输入您的新密码"/>

                                    <div class="ui-form-explain">请输入您的新密码</div>
                                </dd>
                            </dl>
                            <dl class="ui-form-item">
                                <dt class="ui-label"><span class="ui-form-required">*</span>
                                    重复密码:
                                </dt>
                                <dd>
                                    <input id="confirm-pwd" name="cfmPassword"
                                           class="text yahei long ui-input"
                                           type="password"
                                           value="" data-display="新密码"/>

                                    <div class="ui-form-explain">请再输入一次您的新密码
                                    </div>
                                </dd>
                            </dl>
                            <dl class="ui-form-item">

                                <dd>
                                    <input type="submit"  class="ui-button-lorange ui-button yahei fs14" value="保存修改"/>
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
<div id="newCard" class="newCard hide"></div>
<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
<script type="text/javascript" src="${res}/scripts/plugin/jquery.countdown.js"></script>
<script>
seajs.use(['validator', '$'], function (Validator, $) {
	   $(".hqyzm").removeAttr("disabled");
    //非异步
    var validator = new Validator({
        element: '#payword-form',
        autoSubmit: false,// 当验证通过后不自动提交
        onFormValidated: function(error,results,element){
        		if(!error){
        		     $.post("${ctx}/dealer/payword/update", $('#payword-form').serialize(), function(data){
        					if(data.code == zttx.SUCCESS)
        					{
        						remind("success", "支付密码修改成功");
        						$('#getCaptchaInfo').html('');
        						$('#payword-form')[0].reset();//0618 zxb add BUG#2277
                                resetValidatorError('#payword-form');//0618 zxb add BUG#2277
        					}else{
        						remind("error",data.message);
        					}
        				}, "json");
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
        required: ${payword!=null},
        rule: 'minlength{min:6} maxlength{max:6}',
        display: '验证码'
    }).addItem({
        element: '#password',
        required: true,
        rule: 'minlength{"min":6} maxlength{"max":20}',
        display: '新密码'
    }).addItem({
        element: '#confirm-pwd',
        required: true,
        rule: 'confirmation{target: "#password", name: "第一遍"}',
        display: '密码',
        errormessageRequired: '请再重复输入一遍密码，不能留空'
    });

 });
 function sendValidatorCode(){
	 var mobile = $('#mobile').val();
     $.post('${ctx}/common/getCaptcha', {cusType: 1,userType: '001',userMobile: mobile},function(data){
			if(data.code==111004)
			{
				$('#getCaptchaInfo').text(data.message);
		    	$('#getCaptchaInfo').attr("style", "color:#71b83d");
			} else  {
				$('#getCaptchaInfo').text(data.message);
		    	$('#getCaptchaInfo').attr("style", "color:red");
			}
     },"json");
 }

</script>
</body>
</html>

