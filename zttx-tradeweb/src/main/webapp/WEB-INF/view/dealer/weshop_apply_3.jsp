<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <title>服务管理-开通微店</title>
    <link href="/styles/dealer/global.css" rel="stylesheet" />
    <link href="/styles/dealer/weshop.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
               <jsp:include page="${ctx}/dealer/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->

                    <jsp:include page="${ctx}/dealer/dealermenu"  />

                <div class="main-right">
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="/dealer/center">管理中心</a> &gt;&gt; <span class="bb">服务管理</span>
                            <a class="panel-crumbs-help" href="/help/index"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="main-grid mb40 clearfix">
                        <div class="weshop-apply">
                            <h2>开通我的微店</h2>
                            <div class="hr-dashed">
                            </div>
                            <div class="clearfix apply-box">
                                <div class="apply-flog">
                                    <div class="apply-btn">
                                        <h4>使用其他新约逛号</h4>
                                        <span  class="iphone"></span>
                                        <p>
                                            还没有约逛账户或想使用其它手机号码作为约逛的登录号请注册新的约逛号
                                        </p>
                                    </div>
                                </div>
                                <div class="apply-form">
                                    <form class="ui-form" action="weshop-apply-success.jsp">
                                        <div class="ui-form-item">
                                            <label class="ui-label">
                                                终端商名:
                                            </label>
                                            <span class="ui-form-text">${dealerInfo.dealerName}</span>
                                        </div>
                                        <div class="ui-form-item">
                                            <label class="ui-label">
                                                终端商登录账号:
                                            </label>
                                            <span class="ui-form-text">${dealerUserm.userMobile}</span>
                                        </div>
                                        <div class="ui-form-item">
                                            <label class="ui-label">
                                                输入手机号码:
                                            </label>
                                            <input class="ui-input" id="userAccount" >
                                            <button class="ui-button ui-button-lorange" type="button" id="sndCodeBtn">获取验证码</button><span id="verifyInfo" style="color: red;margin-left: 10px;"></span>
                                        </div>
                                        <div class="ui-form-item">
                                            <label class="ui-label">
                                                验证码:
                                            </label>
                                            <input class="ui-input" style="width:50px;" id="vcode"> <span id="vcodeInfo" style="color: red;"></span>
                                        </div>
                                        <div class="ui-form-item">
                                            <label class="ui-label">
                                                性别:
                                            </label>
                                            <span class="ui-form-text"><input class="ui-checkbox" type="radio" name="typeGender" value='1' checked="checked"> 男</span>
                                            <span class="ui-form-text"><input class="ui-checkbox" type="radio" name="typeGender" value='2'> 女</span>
                                        </div>
                                        <div class="ui-form-item">
                                            <label class="ui-label">
                                                约逛密码:
                                            </label>
                                            <input class="ui-input" type="password" id="password1">
                                        </div>
                                        <div class="ui-form-item">
                                            <label class="ui-label">
                                                重复密码:
                                            </label>
                                            <input class="ui-input" type="password" id="password2"><span id='pswdErrorInfo' style="color: red;margin-left: 10px;"></span>
                                        </div>
                                        <div class="ui-form-item">
                                            <button class="ui-button ui-button-mred mb5" type="button" id="submitBtn">立即注册并绑定</button>
                                            <span class="block">点击上面的“注册”按钮，即表示您同意《<a class="link" target="_blank">逛逛使用条例</a>》</span>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="hr mt10 mb10">
                            </div>
                            <div class="m020">
                                <a class="ui-button ui-button-lred mr10" href="" target="_blank">前往下载约逛</a>
                                <a class="link" href="" target="_blank">了解约逛</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
          <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>

    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
	<script>
	 $('#sndCodeBtn').click(function(){
		    var userAccount=$('#userAccount').val();
		    $('#verifyInfo').html('');
		    if(userAccount==null||userAccount==""){
		    	$('#verifyInfo').html('手机号不能为空');
		    	return;
		    }
		   	$.post("${ctx }/common/sendvcode",{userMobile:userAccount},function(data){
		   		if(data.code==3){
		   			remind('error','验证码已发送,请稍后再发送!');
		   		}
		   		if(data.code==126000){
		   		   $('#sndCodeBtn').attr('disabled','disabled');
		   			 var i=0;
	       		     window['t']= setInterval(function(){
	       			 if(i++<60){
	       				 $('#sndCodeBtn').html('倒计时('+(60-i)+'秒)!');
	       			 }else{
	       				 $('#sndCodeBtn').html('发送手机验证码');
	       				 $('#sndCodeBtn').removeAttr('disabled','disabled');
	       				 clearInterval(t);
	       			 }
	       		 },1000);
		   		}
		   		
		   	},"json");
	   });
	 
	 $('#submitBtn').click(function(){
			var password1=$('#password1').val();
			var password2=$('#password2').val();
			var userAccount=$('#userAccount').val();
			var vcode=$('#vcode').val();
			$('#pswdErrorInfo').html('');
			var userGender=$("input[name=typeGender]:checked").val();
			if(password1==null||password1==''||password1.length<6){
				$('#pswdErrorInfo').html('密码长度不能小于6位');
				return;
			}
			$('#pswdErrorInfo').html('');
			if(password1!=password2){
				$('#pswdErrorInfo').html('两次输入密码不一样');
				return;
			}
			 $('#vcodeInfo').html('');
			if(vcode==null||vcode==""){
		    	$('#vcodeInfo').html('验证不能为空');
		    	return;
		    }
			$('#submitBtn').html("处理中...");
			
			var params={userAccount:userAccount,password1:password1,password2:password2,userGender:userGender,code:vcode};
			$.post("${ctx}/dealer/weshop/apply3",params,function(data){
				$('#submitBtn').html("立即开通");
				if(data.code==126000){
					remind('success','微店开通成功',function(){
                     window.location.href="${ctx}/dealer/weshop/success?type=3&refrenceId="+data.object.refrenceId;
                    });
				}else{
					remind('success','微店开通失败,请联系客服!');
				}
			},"json");
		});
	</script>
</body>
</html>
