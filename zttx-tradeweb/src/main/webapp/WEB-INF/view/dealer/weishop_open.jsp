<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心 &gt; 微店管理</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/weshop.css" rel="stylesheet" />
</head>
<body>
<!--完成-->
    <div class="container">
        <jsp:include page="/common/menuInfo/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->
                <jsp:include page="/common/menuInfo/sidemenu"/>
            <!--主体内容-->
            <div class="main-right">
                <!--面包屑-->
                <div class="main-grid mb10">
                    <div class="panel-crumbs">
                        <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <span class="bb">微店管理</span>
                        <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <div class="main-grid clearfix">
                    <div class="weshop-apply">
                        <div class="clearfix apply-box">
                            <div class="apply-flog">
                                <div class="apply-btn">
                                    <h4 class="fs18">没有约逛?赶紧扫一扫</h4>
                                    <div class="qrcode">

                                    </div>
                                    <p>
                                        请使用您在约逛注册的手机号来开通微店,一个手机号只能开通一个微店哦
                                    </p>
                                </div>
                            </div>
                            <div class="apply-form mt40">
                                <form id="applyFrom" class="ui-form" data-widget="validator">
                                    <div class="ui-form-item">
                                        <label class="ui-label">
                                            手机号:
                                        </label>
                                        <input class="ui-input js-number" required data-display="手机号" data-rule="mobile" id="userMoblie" placeholder="请输入您在约逛注册的手机号">
                                        <button id="vcodeBtn" type="button" class="ui-button ui-button-lwhite" style="*vertical-align: -6px;*margin-left: 5px;/*IE7*/">获取验证码</button>
                                    </div>
                                    <div class="ui-form-item">
                                        <label class="ui-label">
                                            验证码:
                                        </label>
                                        <input class="ui-input" required  data-display="验证码" style="width:50px;" id="validCode">
                                    </div>
                                    <div class="ui-form-item">
                                        <button class="ui-button ui-button-mred" type="submit">开通微店</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
<script type="text/javascript">
$(function(){

     $("#vcodeBtn").click(function(){
         var userMobile = $("#userMoblie").val();
         if(mobileNumReg(userMobile)){
             var me = this;
             $.post("${ctx}/dealer/weishop/sendValidCode",{userMobile:userMobile},function(data){
                 if(data.code==121201){
                     //返回了不成功的信息
                     err = true;
                     remind("error","该手机号未注册约逛，请下载约逛app并注册");
                     return false;
                 }else if(data.code==121511){
                     //返回了不成功的信息
                     err = true;
                     remind("error","该手机号已开通其他微店，请更换其他手机号");
                     return false;
                 }else if(data.code==121000){
                     err = false;
                     remind("success","验证码已经发送,请查收!");
                     getCodeFn(me);
                 }else{
                     err = true;
                     remind("error","验证码发送失败");
                     return false;
                 }
             },"json");
         }else{
             remind("error","手机号码格式错误");
         }
    }); 
        
    seajs.use(['$','qrcode'], function ($,Qrcode) {
        var msg = "",err = false;
        baseFormValidator({
            selector:"#applyFrom",
            isAjax:true,
            beforeSubmitFn:function(){
                if(err)
                {
                    remind("error",msg);
                    return;
                }
                var userMobile = $("#userMoblie").val();
                var validCode = $("#validCode").val();
				$.post("${ctx}/dealer/weishop/openWeiShop",{userMobile:userMobile,validCode:validCode},function(data){
		            if(data.code==126000){
		                remind("success","开通成功",function(){
		                	location.href = "${ctx}/dealer/weishop";
		                });
		            }else{
		            	remind("error","开通失败");
		            }
		            
		   	    },"json");
            }
        });
        //发送验证码,并验证是否存在手机号等事务
       
       	 var qrnode = new Qrcode({
	         text: "http://app.8637.com/soft/app/download",
	         width:120,
	         height:120
	     });
	     $('.qrcode').append(qrnode);
    });

});
</script>
</body>
</html>
