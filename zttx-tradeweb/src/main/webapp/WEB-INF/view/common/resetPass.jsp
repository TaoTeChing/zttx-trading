<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>忘记密码</title>
    <meta name="keywords" content="8637品牌超级代理-品牌市场" />
    <meta name="description" content="8637品牌超级代理-品牌市场" />
    <!--样式-->
    <link href="${res}/styles/common/global.css" rel="stylesheet" />
    <link href="${res}/styles/common/account.css" rel="stylesheet" />
</head>
<body>
    <div class="container getpass">
        <jsp:include page="login/view_top.jsp" />
        <div class="main">
            <div class="box">
                <div class="head">
                    <h2 class="c-r-on">重置密码</h2>
                </div>
                <div class="main-left">
                    <div class="content">
                        <div class="form">
                            <form:form action="${ctx}/common/editPassWord" method="post">
                            	<input type="hidden" name="userMobile" value="${userMobile}" /> 
                            	<input type="hidden" name="userType" value="${userType}" /> 
                                <dl class="mb20">
                                    <dt>
                                        <label>新的密码:</label>
                                    </dt>
                                    <dd>
                                        <input name="userpassword" class="txt long" type="password" plugin="passwordStrength" datatype="*6-18" />
                                        <div class="helper">6-18位数字、字母和符号，区分大小写</div>
                                        <p id="passwordStrength" class="passwordStrength"><span>弱</span><span>中</span><span class="last">强</span></p>
                                    </dd>
                                </dl>
                                <dl class=" mb20">
                                    <dt>
                                        <label>重复密码:</label></dt>
                                    <dd>
                                        <input type="password" class="txt long" datatype="*6-15" recheck="userpassword" /></dd>
                                </dl>
                                <dl class=" mb40">
                                    <dt></dt>
                                    <dd>
                                        <input class="btn" type="submit" value="点击修改" />
                                    </dd>
                                </dl>
                                <!-- 栏目：如找回密码遇到困难,请联系我们  -->
                                <jsp:include page="login/view_forgotPassContact.jsp" />
                            </form:form>
                        </div>
                    </div>
                </div>
                <!-- 没有8637品牌超级代理账户?栏目 -->
				<jsp:include page="login/view_forgotPassRight.jsp" />
            </div>
        </div>
        <jsp:include page="login/view_foot.jsp" />
    </div>
    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/plugin/passwordStrength-min.js"></script>
	<script src="${res}/scripts/plugin/Validform_v5.3.2_min.js"></script>
    <script>

        $("input.txt,select").focus(function () {
            $(this).parent().find(".helper").show();
        }).blur(function () {
            $(this).parent().find(".helper").hide();
        });

        $('input.txt').after('<a class="Validform_checktip">');
        $(".form form").Validform({
            tiptype: 4,
            usePlugin: {
                passwordstrength: {
                    minLen: 6,
                    maxLen: 18
                }
            }
        });
    </script>
</body>
</html>
