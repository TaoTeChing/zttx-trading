<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
<link rel="stylesheet" href="${res}/styles/common/login-dialog.css" />
<!-- 公用弹窗登录 -->
<div class="login-dialog" style="width: 610px;">
    <a class="login-dialog-close" href="javascript:;">关闭</a>
    <div class="ui-head">
        <h2><span>8637品牌超级代理</span> 欢迎您！</h2>
    </div>
    <div class="login-dialog-content clearfix">
        <div class="login-dialog-tit">
            <span class="dialog-tit-ch">会员登录</span>
            <span class="dialog-tit-en">User login</span>
        </div>
        <div class="login-dialog-left">
            <%--<ul class="login-dialog-tabmenu clearfix">
                <li class="selected"><a href="javascript:;">我是经销商</a><i class="login-dialog-arrow"></i></li>
                <li><a href="javascript:;">我是品牌商</a><i class="login-dialog-arrow"></i></li>
            </ul>--%>
            <div class="login-dialog-tabcon" style="background: none;">
                <div class="form login-dialog-ul">
                    <input type="hidden" name="redirect" value="${redirect}"/>
                    <form:form class="js-login-form" action="${ctx}/common/submitlogin" method="post">
                        <dl>
                            <dd>
                                <input autocomplete="off" value="${account}" type="text" name="account" class="login-dialog-text login-dialog-username" placeholder='手机号' value="手机号" maxlength="11"/>
                            </dd>

                        </dl>
                        <dl>
                            <dd>
                                <input ${empty brandAccount ? '':'checked="true"'} type="password" name="pwd" class="login-dialog-text login-dialog-password"/>
                                <input autocomplete="off" id="userType" type="hidden" name="userType" value="1" />
                            </dd>
                        </dl>
                        <%--<dl ${showCapcha == true ? 'class="captcha_dl clearfix"':'class="captcha_dl clearfix hide"'}>--%>
                            <%--<dd>--%>
                                <%--<input type="text" name="captcha" datatype="*" nullmsg="请填写验证码！" class="login-dialog-text fl" style="padding-left: 5px;width: 167px;"/>--%>
                                <%--<img class="captcha_img fl" src="${ctx}/jcaptcha" alt=""/>--%>
                            <%--</dd>--%>
                        <%--</dl>--%>
                        <dl class="login-dialog-nextlogin">
                            <dd>
                                <%--<label class="fl">--%>
                                    <%--<input type="checkbox" class="login-dialog-checkbox" name="remember" value="true" id="Checkbox2" ${empty account ? '':'checked'} />--%>
                                    <%--下次自动登录--%>
                                <%--</label>--%>
                                <a href="${ctx}/common/forgotpass" class="link login-dialog-fogt">忘记密码?</a>
                            </dd>
                        </dl>
                        <dl>
                            <dt></dt>
                            <dd>
                                <input id="login-btn" type="submit" class="login-dialog-button" value="登录"/>
                            </dd>
                        </dl>
                        <dl>
                            <dt></dt>
                        </dl>
                    </form:form>
                </div>
            </div>
            <div class="login-dialog-tip">
                了解品牌超级代理 | 客服热线：400-800-8637
            </div>
        </div>
        <div class="login-dialog-right">
            <span>提示：</span>还不是品牌超级代理会员？
            点击这里
            <a target="_blank" class="login-dialog-join" href="${ctx }/apply/index.jsp">入驻加盟</a>
        </div>
    </div>
</div>
