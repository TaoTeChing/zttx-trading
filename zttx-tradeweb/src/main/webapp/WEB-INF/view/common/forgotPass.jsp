<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<c:set var="bsendSuccess" value="<%=com.zttx.web.consts.BrandConst.CAPTCHA_SEND_SUCCESS.getCode()%>"></c:set>
<c:set var="bcaptchaExist" value="<%=com.zttx.web.consts.BrandConst.CAPTCHA_EXIST.getCode()%>"></c:set>
<c:set var="dsendSuccess" value="<%=com.zttx.web.consts.DealerConst.CAPTCHA_SEND_SUCCESS.getCode()%>"></c:set>
<c:set var="dcaptchaExist" value="<%=com.zttx.web.consts.DealerConst.CAPTCHA_EXIST.getCode()%>"></c:set>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>忘记密码</title>
    <meta name="keywords" content="8637品牌超级代理-品牌市场"/>
    <meta name="description" content="8637品牌超级代理-品牌市场"/>
    <%--<!--样式-->--%>
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="${res}/styles/common/account.css" rel="stylesheet"/>
</head>
<body>
<div class="container getpass">
    <jsp:include page="login/view_top.jsp"/>
    <div class="main">
        <div class="box">
            <div class="head">
                <h2>重置密码</h2>
            </div>
            <div class="main-left">
                <div class="content">
                    <div class="form js-verfy-form">
                        <form:form action="${ctx}/common/forgotpass/getCaptcha" method="post" autocomplete="off">
                            <dl>
                                <dt>
                                    <label>会员名:</label></dt>
                                <dd>
                                    <input type="hidden" name="userType" value="1">
                                    <input type="text" name="userMobile" class="txt userMobile"
                                           placeholder="例如:130****1111" value="" datatype="m" errormsg="请输入手机号码">
                                </dd>
                            </dl>
                            <dl>
                                <dt>
                                    <label>验证码:</label></dt>
                                <dd>
                                    <input type="hidden" name="userType" value="1">
                                    <input type="text" name="captcha" class="txt captcha" placeholder="验证码" value=""
                                           datatype="ss4" errormsg="请输入验证码" style="width: 57px;">
                                    <img class="captcha_img" src="${ctx}/captcha" alt="" width="150" height="41"
                                         style="margin-top: 0;"/>
                                </dd>
                            </dl>
                            <dl>
                                <dt></dt>
                                <dd>
                                    <button class="btn" type="submit" style="width: 232px;">获取手机验证码</button>
                                </dd>
                            </dl>
                        </form:form>
                    </div>
                    <div class="form js-reset-form" style="display: none;">
                        <form:form action="${ctx}/common/forgotpass/updatePassword" method="post"
                                   autocomplete="off"> <!-- 原提交 ${ctx}/common/editPassWord -->
                            <input type="hidden" name="userType" value="1">
                            <input type="hidden" name="userMobile" value="">
                            <dl>
                                <dt>
                                    <label>验证码:</label></dt>
                                <dd>
                                    <input autocomplete="off" name="telCode" type="text" class="txt long" dataType="*"
                                           placeholder="请再输入验证码"/></dd>
                            </dl>
                            <dl>
                                <dt>
                                    <label>新的密码:</label></dt>
                                <dd>
                                    <input type="text" style="display: none;"/>
                                    <input autocomplete="off" name="newPwd" class="txt long" type="password"
                                           datatype="ispassword" maxlength="16"
                                           placeholder="请输入6-16位的密码，支持字母、数字、特殊字符_.@-"
                                           errormsg="请输入6-16位的密码，支持字母、数字、特殊字符_.@-" nullmsg="新的密码不能为空"/>
                                        <%--<span class="Validform_checktip"></span>--%>
                                        <%--<p id='passwordStrength' class='passwordStrength'>
                                            <span>弱</span><span>中</span><span class="last">强</span>
                                        </p>--%>
                                </dd>
                            </dl>
                            <dl>
                                <dt>
                                    <label>重复密码:</label></dt>
                                <dd>
                                    <input type="password" class="txt long" datatype="ispassword" maxlength="16"
                                           recheck="newPwd" placeholder="请再输入一次密码" nullmsg="请再输入一次" errormsg="两次密码不一致"/>
                                </dd>
                            </dl>
                            <dl>
                                <dt></dt>
                                <dd>
                                    <input class="btn" type="submit" value="点击修改"/>
                                </dd>
                            </dl>
                            <dl>
                                <dt></dt>
                                <dd>
                                    忘记用户名？<a class="link-r" href="${ctx }/common/forgotpass/findAccount" target="_blank">提交申请单</a>由客服帮助处理。
                                </dd>
                            </dl>
                            <jsp:include page="login/view_forgotPassContact.jsp"/>
                        </form:form>
                    </div>
                </div>
            </div>
            <%--<!-- 栏目：没有8637品牌超级代理账户? -->--%>
            <jsp:include page="login/view_forgotPassRight.jsp"/>
        </div>
    </div>
    <jsp:include page="login/view_foot.jsp"/>
</div>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${src}/plugin/Validform_v5.3.2_min.js"></script>
<script src="${src}/plugin/passwordStrength-min.js"></script>
<tags:message content="${message}" width="300"></tags:message>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"></jsp:include>
<script>
    $(function () {
        // ==============================================================
        /*var BCAPTCHA_SEND_SUCCESS =
        ${bsendSuccess};
         var BCAPTCHA_EXIST =
        ${bcaptchaExist};
         var DCAPTCHA_SEND_SUCCESS =
        ${dsendSuccess};
         var DCAPTCHA_EXIST =
        ${dcaptchaExist};*/

        $("input.txt,select").focus(function () {
            $(this).parent().find(".helper").show();
        }).blur(function () {
            $(this).parent().find(".helper").hide();
        });

        //获取验证码新步骤
        $(".js-verfy-form form").Validform({
            tiptype: 4,
            ajaxPost: true,
            datatype: {
                ss4: function (gets, obj, curform, regxp) {
                    var _reg = /^[a-zA-Z0-9]{4}$/;
                    return _reg.test(gets);
                }
            },
            callback: function (data) {
                if (data.code == zttx.SUCCESS || data.code == 121000) {
                    $(".js-reset-form input[name=userMobile]").val($(".js-verfy-form input[name=userMobile]").val());
                    $(".js-verfy-form").hide();
                    $(".js-reset-form").show();
                } else {
                    remind("error", data.message);
                }
            }
        });

        //点击验证码图片
        $(document).on("click", ".captcha_img", function () {
            var url = "${ctx}/captcha?t=" + Math.random();
            $(this).attr("src", url);
        });

        // 开启前台验证
        $(".js-reset-form form").Validform({
            tiptype: 4,
            ajaxPost: true,
            datatype: {
                ispassword: function (gets, obj, curform, regxp) {
                    var _reg = /^[\_\@\.\-a-zA-Z0-9]{6,16}$/;
                    return _reg.test(gets);
                }
            },
            callback: function (data) {
                if (data.code == zttx.SUCCESS || data.code == 121000) {
                    window.location.href = '${ctx}/common/forgotpass/resetPassOK';
                } else {
                    remind("error", data.message);
                }
            }
            /*,usePlugin: {
             passwordstrength: {
             minLen: 6,
             maxLen: 16
             }
             }*/
        });
    });
</script>
</body>
</html>