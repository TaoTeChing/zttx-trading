﻿<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>账户申诉</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    
    <link href="${res}/styles/dealer/account.css" rel="stylesheet" />
    <style>
        .panel-verify .item{
            padding-left: 0;
            padding-bottom: 0;
        }
        .panel-verify .item .ui-form-explain{
            display: inline-block;
            *display: inline;
            *zoom: 1;
            vertical-align: top;
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

                    <jsp:include page="${ctx}/common/menuInfo/sidemenu" />

                <!--主体内容-->
                <div class="main-right">
                    <!--面包屑-->
                    <div class="main-grid">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> 
                            <a href="${ctx}/dealer/dealerInfo/account/infor" title="">账户管理</a> > <span class="bb">账户申诉</span>
                            <a class="panel-crumbs-help" href="${ctx}/help/index">
                            <i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="main-grid mb10">
                        <div class="panel">
                            <div class="panel-head">
                                <i class="verify-icon i-user"></i>
                                <h3 class="yahei fs14 fl ml5">账户认证/保障</h3>
                            </div>
                            <div class="panel-content">
                                <div class="panel-verify">
             <form:form id="js-form-account" action="" data-widget="validator">
            	<c:if test="${empty securityCert}">
                	<div class="item ui-form-item clearfix">
                    	<label for="">请输入您的新手机号</label>
                    	<input data-display="新手机号" style="width: 180px;" class="ui-input" type="text" name="userMobile" required />
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
                    	<button style="line-height: 1;" class="ui-button ui-button-morange" type="submit">确定提交</button>
                	</div>
                </c:if>
            </form:form>
                                </div>
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

        baseFormValidator({
            isAjax: true,
            selector: "#js-form-account",
            beforeSubmitFn: function(){
                // 当表单验证通过，ajax提交表单
                $.post("${ctx}/common/securityCert/dealer/securityCert/save", $('#js-form-account').serialize(), function(data){
                    if(data.code == zttx.SUCCESS){
                        remind("success", "提交成功", function(){
                            window.location.reload();
                        });
                    }else {
                        remind("error", data.message);
                    }
                }, "json");
            }
        })

    </script>
</body>
</html>
