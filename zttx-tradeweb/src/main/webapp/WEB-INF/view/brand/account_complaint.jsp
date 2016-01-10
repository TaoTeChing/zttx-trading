<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-我的账户-账户信息-人工受理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/myAccount.css"/>
    <style>
        .safe_verification .artificial .item{
            padding-left: 0;
            padding-bottom: 0;
        }
        .safe_verification .artificial .item label{
            float: left;
            display: inline;
            color: #666;
            line-height: 28px;
            margin-right: 10px;
        }
        .safe_verification .artificial .item strong{
            color: #cc0000;
        }
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
            <span class="current">人工审核</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
        </div>
    </div>
<div class="inner">
    <div class="safe_verification">
        <div class="artificial">
            <form:form id="js-form-account" action=""  data-widget="validator">
            	<c:if test="${empty securityCert}">
                	<div class="item ui-form-item clearfix">
                    	<label for="">请输入您的新手机号</label>
                    	<input data-display="新手机号" data-rule="mobile" style="width: 180px;" class="ui-input" type="text" name="userMobile" required />
                	</div>
                </c:if>
                <c:if test="${not empty securityCert}">
                	<div class="item clearfix">已提交，请耐心等待...</div>
                </c:if>
                <div class="item mt10">
                    <p>客服人员将在1天之内联系您，请保持电话畅通。</p>
                    <p class="mt5">届时客服人员将会向您核对身份信息，保证您的账户安全，请准备好相关资料，如：<strong>身份证号</strong> <strong>营业执照编号</strong></p>
                </div>
                <c:if test="${empty securityCert}">
                	<div class="item mt20">
                    	<button class="ui_button ui_button_lblue" type="submit">确定提交</button>
                	</div>
                </c:if>
            </form:form>
        </div>
    </div>
</div>
</div>
</div>
	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script>


    baseFormValidator({
        isAjax: true,
        selector: "#js-form-account",
        beforeSubmitFn: function(){
            // 当表单验证通过，ajax提交表单
            $.post("${ctx}/common/securityCert/brand/securityCert/save", $('#js-form-account').serialize(), function(data){
                if(data.code == zttx.SUCCESS)
                {
                    remind("success", "提交成功", function(){
                        window.location.reload();
                    });
                }
                else
                {
                    remind("error", data.message);
                }
            }, "json");
        }
    })

</script>
</body>
</html>