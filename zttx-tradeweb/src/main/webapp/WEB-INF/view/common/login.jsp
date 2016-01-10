<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>会员登录-8637品牌超级代理</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <!--样式-->
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="${res}/styles/common/account.css" rel="stylesheet"/>
</head>
<body>
<div class="container login">
    <jsp:include page="login/view_top.jsp"/>
    <div class="main">
        <div class="box">
            <div class="head">
                <h2>会员登录</h2>
            </div>
            <div class="main-left">
                <div class="content">
                    <div class="error-box">

                    </div>
                    <div class="form">
                        <input type="hidden" name="redirect" value="${redirect}"/>
                        <form:form class="js-login-form" action="${ctx}/common/login/submitlogin" method="post">
                            <dl>
                                <dt>
                                    <label>会员名</label>
                                </dt>
                                <dd>
                                    <input maxlength="11" value="" type="text" name="userMobile" class="txt long" datatype="m|e" nullmsg="请填写会员名！" errormsg="会员名必须是手机号码！" sucmsg="" placeholder='手机号' value="手机号"/>
                                    <%--<a href="${ctx}/common/forgotaccount" class="link">忘记用户名?</a>--%>
                                </dd>
                            </dl>
                            <dl id="password_box">
                                <dt>
                                    <label>密 码</label>
                                </dt>
                                <dd>
                                    <input type="text" style="display: none;" />
                                    <input ${empty brandAccount ? '':'checked="true"'} type="password" name="userPwd" datatype="*" nullmsg="请填写密码！" autocomplete="off" sucmsg="" class="txt long"/>
                                    <input id="userType" type="hidden" name="userType" value="1"/>
                                    <span class="Validform_checktip"></span>
                                </dd>
                            </dl>
                            <c:if test="${showCapcha}">
                                <dl class="captcha_dl">
                                    <dt>
                                        <label>验证码</label>
                                    </dt>
                                    <dd>
                                        <input type="text" name="captcha" datatype="*" nullmsg="请填写验证码！" class="txt code"/>
                                        <img class="captcha_img" src="${ctx}/captcha" alt=""/>
                                    </dd>
                                </dl>
                            </c:if>
                            <dl>
                                <dd>
                                    <div class="clearfix mb10">
                                        <label for="Checkbox2" class="fs14 ml5"><input type="checkbox" name="rememberMe" value="true" id="Checkbox2" ${empty account ? '':'checked'} /> 记住用户名</label>
                                        <span class="link-r ml10 fs14">使用公用电脑勿勾选</span>
                                        <a href="${ctx}/common/forgotpass/index" class="link fs14 fr">忘记密码?</a>
                                    </div>
                                    <div class="mb10">
                                        <button type="submit" id="btn_sigin" class="btn">点击登录</button>
                                    </div>
                                    <div>
                                        <span class="fl fs14 ">如找回密码遇到困难,请</span><a class="fs14 link-r" href="${ctx}/about/contactus">联系我们</a>
                                    </div>
                                </dd>
                            </dl>

                            <%--6-12 add --%>
                            <!-- 栏目：如找回密码遇到困难,请联系我们  -->

                            <%--6-12 add --%>
                            <%--6-12 remove --%>
                            <%--<jsp:include page="view_forgotPassContact.jsp"/>--%>
                            <%--6-12 remove --%>
                        </form:form>
                    </div>
                </div>
            </div>
            <!-- 栏目：没有8637品牌超级代理账户? -->
            <jsp:include page="login/view_forgotPassRight.jsp"/>
        </div>
    </div>
    <jsp:include page="login/view_foot.jsp"/>
</div>
<jsp:include page="/WEB-INF/view/common/component/import_common_js.jsp"/>
<script type="text/javascript" src="${src}/plugin/Validform_v5.3.2_min.js"></script>
<script type="text/html" id="captcha_tpl">
    <dl class="captcha_dl">
        <dt>
            <label>验证码:</label>
        </dt>
        <dd>
            <input type="text" name="captcha" datatype="*" nullmsg="请填写验证码！" class="txt code"/>
            <img class="captcha_img" src="${ctx}/captcha" alt=""/>
        </dd>
    </dl>
</script>
<script type="text/javascript">
    $(function () {

        // 路径解析
        function parseURL(url) {
            var a = document.createElement('a');
            a.href = url;
            return {
                source: url,
                protocol: a.protocol.replace(':', ''),
                host: a.hostname,
                port: a.port,
                query: a.search,
                params: (function () {
                    var ret = {},
                            seg = a.search.replace(/^\?/, '').split('&'),
                            len = seg.length, i = 0, s;
                    for (; i < len; i++) {
                        if (!seg[i]) {
                            continue;
                        }
                        s = seg[i].split('=');
                        ret[s[0]] = s[1];
                    }
                    return ret;
                })(),
                file: (a.pathname.match(/\/([^\/?#]+)$/i) || [, ''])[1],
                hash: a.hash.replace('#', ''),
                path: a.pathname.replace(/^([^\/])/, '/$1'),
                relative: (a.href.match(/tps?:\/\/[^\/]+(.+)/) || [, ''])[1],
                segments: a.pathname.replace(/^\//, '').split('/')
            };
        }

        //点击验证码图片
        $(document).on("click",".captcha_img", function () {
            var url = "${ctx}/captcha?t=" + Math.random();
            $(this).attr("src", url);
        });

        //错误登录次数
        var loginErrorCount = 0;

        var isShow = false;

        var myValidForm = $('form.js-login-form').Validform({
            tiptype:4,
            postonce: true,
            ajaxPost: true,
            beforeSubmit:function(){
                $("#btn_sigin").text("登录中...");
            },
            callback: function (data) {
                $("#btn_sigin").text("点击登录");
                if ($.type(data) === 'string') {
                    data = $.parseJSON(data);
                }
				
                if (data.code === 126000) {
                    var redirect = $.trim($('input[name="redirect"]').val());
                    if (null == redirect || redirect == '') {
                    	redirect = data.object.redirect;
                        var link = parseURL(redirect);
                        var current = parseURL(window.location.href);
                        if (link.host == current.host) { // 相同域名
                            var userType = parseInt($('#userType').val());
                            switch (userType) {
                                case 0:
                                    if (link.path.indexOf('/dealer') == 0) {
                                        redirect = data.object.redirect;
                                    }
                                    break;
                                case 1:
                                    if (link.path.indexOf('/brand') == 0) {
                                        redirect = data.object.redirect;
                                    }
                                    break;
                                default :
                                    break;
                            }
                        } else {
                            redirect = data.object.redirect;
                        }
                    }
                    window.location.href = redirect;
                } 
                else if(data.code === 126137)
                {
                    var key = data.object.refrenceId || '';
                    var pwd = data.object.password || '';
                	window.location.href = '${ctx}/common/login/completion?key=' + key + '&value=' + pwd;
                }
                else {

                    //console.log("message: " + data.message);

                    if(!data.message || data.message == ""){
                        //csrfToken 失效
                        window.location.href = '${ctx}/common/login/';
                    }else{
                        remind('error', data.message);
                        $(".captcha_img").click();
                        if (data.object && data.object.showCaptcha) {
                            if($(".captcha_dl").length > 0){
                                $(".captcha_dl").show();
                            }else{
                                createCaptcha();
                            }

                            $(".captcha_img").trigger("click");
                            myValidForm.unignore('[name="captcha"]');
                            isShow = true;
                        }
                        loginErrorCount++;
                    }
                }

                if (loginErrorCount == 3) {
                    if (isShow) {
                        return;
                    }

                    if($(".captcha_tpl").length > 0){
                        $(".captcha_dl").show();
                    }else{
                        createCaptcha();
                    }


                    $(".captcha_img").trigger("click");

                    myValidForm.unignore('[name="captcha"]');

                }

            }
        }).ignore('[name="captcha"]');

        function createCaptcha(){
            $("#password_box").after($($("#captcha_tpl").html()));
            myValidForm.addRule([{
                ele: "[name=captcha]",
                datatype: "*",
                nullmsg: "请填写验证码！"
            }])
        }

        $('[name="account"],[name="pwd"]').on("change", function () {
            //$(".captcha_img").trigger("click");
        });

        $(".tabs").on("click", "li a", function () {
            var index = $(this).parents("li").index();
            $(".tabs li a").removeClass("on").eq(index).addClass("on");
            if(index == 0){
                $("#userType").val(1);
            }else if(index == 1){
                $("#userType").val(0);
            }

        });

        // 服务器端返回的登陆类型
        var loginType = '${loginType}';

        if (loginType != "") {
            if (loginType == "1") {
                $(".tabs li a").removeClass("on").eq(0).addClass("on");
                $("#userType").val(1);
            }
            if (loginType == "0") {
                $(".tabs li a").removeClass("on").eq(1).addClass("on");
                $("#userType").val(0);
            }
        }
    })
</script>
</body>
</html>
