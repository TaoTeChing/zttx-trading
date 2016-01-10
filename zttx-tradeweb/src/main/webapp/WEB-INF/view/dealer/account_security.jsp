<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-账户管理-安全验证</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/account.css" rel="stylesheet" />
    <style>
    	.code_btn{
		    height: 28px;
		    padding: 0 10px;
		    border: #b8b5b4 solid 1px;
		    border-radius: 3px;
		    background: #f5f5f5;
		    font: 14px/26px "微软雅黑";
		    display: inline-block;
		    *display: inline;
		    *zoom: 1;
		    *overflow: visible;
		    vertical-align: top;
		    cursor: pointer;
		}
        .code_btn_loading{
            padding-left: 26px;
            background: url("/images/common/loading-16-16.gif") no-repeat 5px center;
        }
		.code_btn:hover{
			background-color: #eee;
		    color: #333;
		}
        .verify-tips{
            padding: 5px 0 5px 20px;
            background: url("/images/common/right.png") left center no-repeat;
            color: #85c749;
        }
    </style>
</head>
<body>
    <!--完成-->
    <div class="container">
        <jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->
                <jsp:include page="${ctx}/common/menuInfo/sidemenu">
                    <jsp:param name="openId" value="12"/>
                </jsp:include>
                <!--主体内容-->
                <div class="main-right">
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> 
                            <a href="${ctx}/dealer/dealerInfo/account/infor" title="">账户管理</a> > <span class="bb">安全验证</span>
                            <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="inner">
                        <div class="panel">
                            <div class="panel-head">
                                <h3 class="yahei fs14"><i class="verify-icon i-user"></i>账户认证/保障</h3>
                            </div>
                            <div class="panel-content">
                                <div class="panel-verify">
                                    <div class="verify verify-phone clearfix">
                                        <ul class="clearfix">
                                            <li class="item1">
                                            <c:choose>
	                                            <c:when test="${dealerUserm.mobileVerify == false}">
		                                            <i class="verify-icon no"></i>
	                                            </c:when>
	                                            <c:otherwise>
	                                            	<i class="verify-icon yes"></i>
	                                            </c:otherwise>
                                            </c:choose>
                                            </li>
                                            <li class="item2">
                                                <div class="clearfix">
                                                    <i class="verify-icon phone"></i>
                                                    <dl class="ml20">
                                                        <dt class="yahei fs14 lh2">
                                                        	    手机认证
                                                        </dt>
                                                        <dd class="lh1-5">
                                                            快速个人移动通信认证<br />方便快捷更可靠
                                                        </dd>
                                                    </dl>
                                                </div>
                                            </li>
                                            <li class="item3">
                                       	    您的手机号码[<span id="id_dealerMobile" class="c-r-on">${dealerUserm.userMobile}</span>]
                                                           <c:choose>
	                                            <c:when test="${dealerUserm.mobileVerify == false}">
	                                           		 <span class="c-r-on ml10">
	                                       			    未通过验证
	                                       			  </span>
	                                            </c:when>
	                                            <c:otherwise>
	                                           	 <span class="c-g">
	                                            	     已通过验证
	                                            	     </span>
	                                            </c:otherwise>
                                            </c:choose>
                                               <a href="javascript:;" id="Btn_ChangeMobile" class="ui-button ui-button-lorange js_mobile_change">更换手机号</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <%-- 隐藏 邮件认证
                                    <div class="verify verify-email clearfix">
                                        <ul>
                                            <li class="item1">
                                                <c:choose>
		                                            <c:when test="${dealerUserm.mailVerify == false||dealerUserm.mailVerify==null}">
			                                            <i class="verify-icon no"></i>
		                                            </c:when>
		                                            <c:otherwise>
		                                            	<i class="verify-icon yes"></i>
		                                            </c:otherwise>
	                                            </c:choose>
                                            </li>
                                            <li class="item2">
                                                <div class="clearfix">
                                            
                                                    <i class="verify-icon email"></i>
                                                    <dl class="ml20">
                                                        <dt class="yahei fs14 lh2">
                                                            邮箱认证
                                                        </dt>
                                                        <dd class="lh1-5">
                                                            账户密码更安全<br />
                                                            邮箱提醒账户实时动态
                                                        </dd>
                                                    </dl>
                                                </div>
                                            </li>
                                            <li class="item3">
                                                <div>
                                                        <div class="ui-form-item">
                                                            您的邮箱地址[<span class="c-r-on">${dealerUserm.userMail}</span>] 
                                                            <c:choose>
                                                             <c:when test="${dealerUserm.mailVerify == false||dealerUserm.mailVerify==null}">
						                                           		 <span class="c-r-on ml10">
						                                       			    未通过验证
						                                       			  </span>
                                                            <a class="js_email_change ui-button ui-button-sred ">邮箱认证</a>
						                                            </c:when>
						                                            <c:otherwise>
						                                           	 <span class="c-g">
						                                            	     已通过验证
						                                            	     </span>
						                                            </c:otherwise>
					                                            </c:choose>
                                                        </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                     --%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
         <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>

    <div class="hide">
        <div id="ChangeMobile">
            <div class="ui-head">
                <h3>更换手机号</h3>
            </div>
            <div class="mt5" style="padding: 10px 0;margin-left: 55px;">已向您的手机<span style="color: red">${dealerUserm.userMobile}</span>发送验证码,请注意查收</div>
            <form:form id="change_mobile_form" data-widget="validator" class="ui-form" action="#">
                <div class="ui-form-item" style="padding-bottom:5px;">
                    <label class="ui-label" style="font-size: 12px;">
                        <span class="ui-form-required c-r">*</span> 验证码：
                    </label>
                    <input id="old_verifyCode" required data-display="验证码"  minlength="6"  maxlength="6" type="text" class="ui-input js-price" style="width: 100px;" value="" autocomplete="off">
                    <p class="mt5">
                        <a class="link" href="${ctx}/common/securityCert/dealer/securityCert">旧号码已经停用了,收不到验证码?</a>
                    </p>
                </div>
                <div class="ui-form-item" style="padding-bottom:5px;">
                    <label class="ui-label" style="font-size: 12px;">
                        <span class="ui-form-required c-r">*</span> 新的手机号码：
                    </label>
                    <input id="id_dealerMobile_s" required data-display="手机号" data-rule="mobile"  type="text" class="ui-input js-price" style="width: 200px;" value=""  autocomplete="off"/>
                    <button class="code_btn ml5"  type="button" id="telcode-btn-s">获取验证码</button>
                </div>
                <div id="verify-tips" class="ui-form-item" style="display: none;">
                    <span class="verify-tips">验证码已发送到您手机,5分钟内输入有效,请勿泄露</span>
                </div>
                <div class="ui-form-item" style="padding-bottom:5px;">
                    <label class="ui-label" style="font-size: 12px;">
                        <span class="ui-form-required c-r">*</span> 验证码：
                    </label>
                    <input id="new_verifyCode" required data-display="验证码"  minlength="6"  maxlength="6"  type="text" class="ui-input js-price" style="width: 100px;" value=""  autocomplete="off"/>
                </div>
                <div class="ui-form-item" style="padding-bottom:5px;">
                    <button type="submit" class="ui-button ui-button-mred" id="verifySub">提交</button>
                </div>
            </form:form>
        </div>
        
        <div id="change_email_pop" class="change_pop">
                        <div class="ui-head">
                      ${dealerUserm.mailVerify == false?'<h3>邮箱认证</h3>':'<h3>更换邮箱</h3>'}
                        </div>
                        <p class="mt5 tip">
                        </p>
                        <form:form id="change_email_form" data-widget="validator" class="ui-form" action="#">
                            <div class="ui-form-item">
                                <label class="ui-label" for="">
                                    <span class="ui-form-required">*</span>邮箱：
                                </label>
                                <input id="id_email" required data-display="邮箱" data-rule="email" value="${dealerUserm.userMail}" 
                                style="width: 162px;" type="text" class="ui-input" />
                                <br/>
                               <span style="color: red;">向该邮箱发送验证邮件</span> 
                            </div>
                            <div class="ui-form-item">
                                <button class="ui-button ui-button-mred" type="submit">提交</button>
                            </div>
                        </form:form>
                    </div>
  				</div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script>
        $(function () {
            //发送短信
            $("#telcode-btn-s").on("click",function(){
                var $this = $(this);
                var mobile = $.trim($('#id_dealerMobile_s').val());
                if(!mobileNumReg(mobile)){
                    remind("error","请输入正确的手机号码！");
                    return ;
                }
                $this.addClass("code_btn_loading");
                $.post('${ctx}/common/forgotpass/sendvcode?isNew=true&userMobile='+mobile, function(data){
                    $this.removeClass("code_btn_loading");
                    if(data.code != 126000){
                        remind("error", data.message);
                    }else{
                        getCodeFn($this);
                        $("#verify-tips").show();
                    }
                }, "json");
            })
        });
        seajs.use(["dialog"],function(Dialog){
            var d1 = new Dialog({
                content: "#ChangeMobile",
                width: 508
            });
            var d2 = new Dialog({
                content: "#change_email_pop",
                width: 508
            });
            d1.after('show', function() {
                clearInterval(getCodeFn.timer);
                $("#telcode-btn-s").html("获取验证码");
                $("#telcode-btn-s").removeAttr("disabled");
                $('#ChangeMobile').show();
            });
            d2.after('show', function() {
                $('#change_email_pop').show();
            });
            //更改手机号
            $(document).on("click",".js_mobile_change",function(){
                //弹出更新手机号之前发送短信给原手机号
                var mobile = $('#id_dealerMobile').text();
                $.post('${ctx}/common/forgotpass/sendvcode?userMobile='+mobile);
                d1.show();
                seajs.use(['validator', 'widget'], function(Validator, Widget){
                    Widget.autoRenderAll();
                    var Core = Validator.query("#change_mobile_form");
                    Core.set("autoSubmit",false);
                    Core.on('formValidated', function(error, message, elem) {
                        if(!error){
                            var old_verifyCode = $("#old_verifyCode").val();
                            var old_mobile = $('#id_dealerMobile').text();
                            var new_verifyCode = $("#new_verifyCode").val();
                            var new_mobile = $('#id_dealerMobile_s').val();
                            var csrfToken = $("input[name='csrfToken']").val();
                            var old_values = 'oldUserMobile=' +old_mobile+'&oldVerifyCode='+ old_verifyCode+'&newUserMobile='+ new_mobile+'&newVerifyCode='+new_verifyCode +'&csrfToken='+ csrfToken;
                            $("#verifySub").attr("disabled",true);
                            $.ajax({
                                url:"${ctx}/dealer/dealerInfo/info/mobile",
                                type: 'post',
                                data: old_values,
                                dataType: 'json',
                                success: function(data, textStatus)
                                {
                                    if(data.code == 126000)
                                    {
                                        remind("success","手机认证成功！", 1000,function(){
                                            window.location.reload();
                                        });
                                    }
                                    else
                                    {
                                        remind("error",data.message);
                                    }
                                    $("#verifySub").attr("disabled", false);
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
                            var new_values = 'userCate=1&emailAddr='+ id_email +'&csrfToken='+ csrfToken;
                            $.ajax({
                                url:"${ctx}/common/emailValidate/create",
                                type: 'post',
                                data: new_values,
                                dataType: 'json',
                                success: function(data, textStatus)
                                {
                                    if(data.code == zttx.SUCCESS){
                                        remind("success","认证邮件发送成功！");
                                    }else{
                                        remind("error",data.message);
                                    }
                                }
                            });
                        }
                    });
                });
            });

        });
    </script>

</body>
</html>
