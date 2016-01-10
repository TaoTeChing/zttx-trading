<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>管理中心-账户管理-账户设置</title>
		<link href="${res}/styles/dealer/global.css" rel="stylesheet" />
		<link href="${res}/styles/dealer/account.css" rel="stylesheet" />
		<link href="${res}/styles/dealer/dealerAddr.css" rel="stylesheet" />
		<link href="${res}/styles/common/validform.css" rel="stylesheet" />
		<style type="text/css">
			.ui-form-explain {
			    padding: 0;
			}
		</style>
		</head>
<body>
	<div class="container">
		<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
		<div class="em100">
			<div class="main clearfix pr">
				<jsp:include page="${ctx}/common/menuInfo/sidemenu" >
					<jsp:param name="openId" value="12"/>
				</jsp:include>
				<div class="main-right">
					<jsp:include page="/WEB-INF/view/dealer/agency_header_message.jsp" />
					<div class="main-grid mb10">
						<div class="panel-crumbs">
							<i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> &gt;&gt; <a href="${ctx}/dealer/dealerInfo/account/infor" title="">账户管理</a> &gt; <span
								class="bb">账户设置</span> <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
						</div>
					</div>
					<div class="main-grid mb20 clearfix">
						 <form:form id="account_config_form" >
						确认收货手机验证码:<input class="ui-radio" type="radio" name="rcvSmsVerify" value=1 ${dealerInfo.rcvSmsVerify?'checked':'' }>开启 <input class="ui-radio" type="radio" name="rcvSmsVerify" value=0  ${dealerInfo.rcvSmsVerify?'':'checked' }>关闭
						<input type="button" value="保存" class="ui-button ui-button-morange" id="_rcv_sms_verify">
						</form:form>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp" />
	</div>
	<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
</body>
<script type="text/javascript">
	$(function(){
		$('#_rcv_sms_verify').click(function(){
			$.post("${ctx}/dealer/dealerInfo/account/rcvsms",$('#account_config_form').serialize(),function(data){
				if(data.success=true){
					remind('success','保存成功');
				}else{
					remind('error','保存失败');
				}
			},"json");
		});
	});
</script>
</html>


