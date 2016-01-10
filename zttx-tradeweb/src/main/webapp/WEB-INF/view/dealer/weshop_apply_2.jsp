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
                                        <h4>已有约逛号激活微店</h4>
                                        <span class="notepad"></span>
                                        <p>
                                            使用您已经在使用的约逛账户进行绑定和激活，绑定后即可开通使用约逛里的微店功能。
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
                                                绑定的约逛账号:
                                            </label>
                                            <input class="ui-input" placeholder="约逛注册时的手机号" id='userAccount'>
                                            <button class="ui-button ui-button-lorange" type="button" id="sndCodeBtn">获取验证码</button><span style="color: red;" id="errorInfo">&nbsp;</span>
                                            <span class="block" id="userCodeTip">&nbsp;</span>
                                        </div>
                                        <div class="ui-form-item">
                                            <label class="ui-label">
                                                验证码:
                                            </label>
                                            <input class="ui-input" style="width:50px;" id="vcode">  <span id="vcodeInfo" style="color: red;"></span>
                                        </div>
                                        <%--
                                        <div class="ui-form-item">
                                            <label class="ui-label">
                                                约逛服务套餐:
                                            </label>
                                            <span class="ui-form-text"><input class="ui-checkbox" type="radio" name="servType"> 基础服务(免费)</span>
                                            <span class="ui-form-text"><input class="ui-checkbox" type="radio" name="servType"> 加强版服务 </span>
                                            <a class="ui-form-text link">查看服务区别</a>
                                        </div>
                                         --%>
                                        <div class="ui-form-item">
                                            <label class="ui-label">
                                                约逛服务时长:
                                            </label>
                                            <span class="ui-form-text">您在8637品牌超级代理的终端服务时间为${fns:getStringTime(dealerBuyService.beginTime,'yyyy-MM-dd')}至${fns:getStringTime(dealerBuyService.endTime,'yyyy-MM-dd')} <br>约逛的服务时间与此相同</span>
                                        </div>
                                        <div class="ui-form-item">
                                            <button class="ui-button ui-button-mred" type="button" id="submitBtn">立即绑定激活</button>
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
   <script type="text/javascript">
   
   $('#sndCodeBtn').click(function(){
	    var userAccount=$('#userAccount').val();
	    $('#errorInfo').html('');
	    if(userAccount==null||userAccount==""){
	    	$('#errorInfo').html('手机号不能为空');
	    	return;
	    }
	   	$.post("${ctx }/dealer/weshop/verifycode",{userAccount:userAccount},function(data){
	   		if(data.code==3){
	   			remind('error','验证码已发送,请稍后再发送!');
	   		}
	   		if(data.code==121201){
	   			remind('error','您所输入的约逛账号不存在!');
	   		}
	   		if(data.code==126000){
	   			$('#userCodeTip').html('你输入的手机号对应的约逛号为:'+data.object.object.userCode);
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
		
		$('#errorInfo').html('');
		var userAccount=$('#userAccount').val();
		var vcode=$('#vcode').val();
		
		 $('#errorInfo').html('');
	    if(userAccount==null||userAccount==""){
	    	$('#errorInfo').html('手机号不能为空');
	    	return;
	    }
	    $('#vcodeInfo').html('');
	    if(vcode==null||vcode==""){
	    	$('#vcodeInfo').html('验证不能为空');
	    	return;
	    }
	    
	    $('#submitBtn').html("处理中...");
		$.post("${ctx }/dealer/weshop/apply2",{userAccount:userAccount,code:vcode},function(data){
			$('#submitBtn').html("立即绑定激活");
			if(data.code==126000){
				remind('success','微店开通成功',function(){
                   window.location.href="${ctx}/dealer/weshop/success?type=2&refrenceId="+data.object.refrenceId;
               });
			}else if(data.code==126107){
				remind('error','验证码错误!');
			}else{
				
				remind('error','微店开通失败,请联系客服!');
			}
		},"json");
	});
   </script>

</body>
</html>
