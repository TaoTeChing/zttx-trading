<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>会员注册-8637品牌超级代理</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <!--样式-->
    <link href="${res}/styles/common/base.css" rel="stylesheet" />
    <link href="${res}/styles/common/account.css" rel="stylesheet" />
</head>
<body>
    <div class="container register">
        <jsp:include page="login/view_top.jsp"/>
        <div class="main">
            <div class="box">
                <div class="head">
                    <h2>会员注册</h2>
                </div>
                <div class="main-left">
                    <div class="tabs clearfix">
                        <a href="javascript:;" <c:if test='${"hide"!=dealerHide}'>class="active"</c:if>><span>我是终端商</span><em class="b-san"></em></a>
                        <a href="javascript:;" <c:if test='${"hide"!=brandHide}'>class="active"</c:if>><span>我是品牌商</span><em class="b-san"></em></a>
                    </div>
                    <div class="content">
                        <div class="form js-verfy-form">
                            <form:form action="${ctx}/common/register/phoneVerify" method="post" autocomplete="off">
                                <dl>
                                    <dt>
                                        <label>会员名:</label></dt>
                                    <dd>
                                        <input type="hidden" name="userType" value="1">
                                        <input type="text" name="userMobile" class="txt userMobile" placeholder="例如:130****1111" value="" datatype="m,isNull" errormsg="请输入手机号码">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>
                                        <label>验证码:</label></dt>
                                    <dd>
                                        <input type="hidden" name="userType" value="1">
                                        <input type="text" name="captcha" class="txt captcha" placeholder="验证码" value="" datatype="ss4" errormsg="请输入验证码" style="width: 57px;">
                                        <img class="captcha_img" src="${ctx}/captcha" alt="" width="150" height="41" style="margin-top: 0;" />
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
                        <%--<div class="tabbody ${dealerHide}">--%>
                        <div class="tabbody" style="display: none;">
                            <div class="form js-person-form">
                                <form:form id="dealerForm" action="${ctx}/common/register/dealerRegister" method="post" autocomplete="off">
                                    <%--<dl>
                                        <dt>
                                            <label>手机号码</label></dt>
                                        <dd>
                                            <input autocomplete="off" type="text" class="txt userMobile" name="userMobile" placeholder="例如:130****111122" nullmsg="手机号码不能为空" errormsg="格式错误，请填写正确的手机号码" value="${dealerUserm.userMobile}" altercss="gray" datatype="m" maxlength="11"/><button type="button" onclick="$REG.phoneVerify('dealerForm .userMobile','1');" class="btn-getcode">免费获取验证码</button>
                                            <!-- <a href="javascript:$REG.phoneVerify('dealerForm #userMobile','0');" class="btn">免费获取验证码</a>-->
                                            <div class="helper">请填写正确的手机号码作为登录账号，并用于找回密码</div>
                                        </dd>
                                    </dl>--%>
                                    <input type="hidden" name="userMobile" value="${dealerUserm.userMobile}" />
                                    <dl>
                                        <dt>
                                            <label>验证码</label></dt>
                                        <dd>
                                            <input autocomplete="off" type="text" class="txt long" value="${dealerUserm.verifyCode}" name="verifyCode" id="verifyCode" nullmsg="验证码不能为空" errormsg="验证码输入错误，请重新获取验证码并输入" placeholder="验证码"  datatype="*,isNull" value="${brandUserm.verifyCode}" maxlength="8"/>
                                            <div class="helper">请输入验证码</div>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>登录密码</label>
                                        </dt>
                                        <dd>
                                            <input type="text" style="display: none;" />
                                            <input autocomplete="off" name="userPwd" id="userPwd" class="txt long" type="password" datatype="ispassword" errormsg="请输入6-16位的密码，支持字母、数字、特殊字符_.@-" nullmsg="登录密码不能为空" placeholder="请输入6-16位的密码，支持字母、数字、特殊字符_.@-" value="" <%--plugin="passwordStrength"--%> maxlength="16" />
                                            <%--<div class="helper">请输入验证码</div>--%>
                                           <%-- <p id="passwordStrength" class="passwordStrength"><span class="first">弱</span><span>中</span><span class="last">强</span></p>--%>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>重复密码</label></dt>
                                        <dd>
                                            <input id="Password2" class="txt long" type="password" value="" datatype="ispassword" maxlength="16" recheck="userPwd" placeholder="请再输入一次密码"  nullmsg="请再输入一次" errormsg="两次密码不一致" />
                                            <div class="helper">请再输入一次</div>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>您的姓名</label></dt>
                                        <dd>
                                            <input class="txt long" name="userName" id="userName" type="text" datatype="*,isNull" value="${dealerUserm.userName}" maxlength="16"  nullmsg="请输入您的姓名" />
                                            <div class="helper">请输入您的姓名</div>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>所在地区</label></dt>
                                        <dd>
                                            <jsp:include page="${ctx}/common/regional/searchAllArea">
    											<jsp:param value="${province}" name="regProvince"/>
    											<jsp:param value="${city}" name="regCity"/>
    											<jsp:param value="${county}" name="regCounty"/>
    											<jsp:param value="test2" name="sale"/>
												<jsp:param value="" name="style"/>
    										</jsp:include>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dd class="confirm">
                                            <div class="clearfix"><input type="checkbox" id="remember" name="remember" class="chk" datatype="*" nullmsg="请确认已阅读" checked/><label for="remember" class="fs14 ml5">我已阅读</label>
                                                <a href="${ctx}/common/rules/info/976D2090BEF9486D939B37BEC5DB4E38" class="fs14" target="_blank" >《8637品牌超级代理服务协议》</a>
                                            </div>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt></dt>
                                        <dd>
                                            <button type="submit" class="btn">点击注册</button>
                                        </dd>
                                    </dl>
                                </form:form>
                            </div>
                        </div>
                        <%--<div class="tabbody ${brandHide}">--%>
                        <div class="tabbody" style="display: none;">
                            <div class="form js-company-form">
                                <form:form id="brandForm" action="${ctx}/common/register/brandRegister" method="post" autocomplete="off">
                                    <input type="hidden" name="userType" id="userType" value="0"/>
                                    <input type="hidden" name="userMobile" value="${brandUserm.userMobile}" />
                                    <%--<dl>
                                        <dt>
                                            <label>手机号码</label></dt>
                                        <dd>
                                            <input autocomplete="off" type="text" name="userMobile" nullmsg="手机号码不能为空" errormsg="格式错误，请填写正确的手机号码" class="txt userMobile" datatype="m" placeholder="例如:130****111122" value="${brandUserm.userMobile}" altercss="gray" maxlength="11"/><button type="button" onclick="javascript:$REG.phoneVerify('brandForm .userMobile','0');" class="btn-getcode">免费获取验证码</button>
                                            <div class="helper">请填写正确的手机号码作为登录账号，并用于找回密码</div>
                                            <!-- <a href="javascript:$REG.phoneVerify('brandForm #userMobile','0');" class="btn">免费获取验证码</a>-->
                                        </dd>
                                    </dl>--%>
                                    <dl>
                                        <dt>
                                            <label>验证码</label></dt>
                                        <dd>
                                            <input autocomplete="off" type="text" class="txt long" nullmsg="验证码不能为空" errormsg="验证码输入错误，请重新获取验证码并输入" name="verifyCode" id="verifyCode" placeholder="验证码" datatype="*,isNull" value="${brandUserm.verifyCode}" maxlength="8"/>
                                            <div class="helper">请输入验证码</div>
                                        </dd>
                                    </dl>
                                    <%-- <dl>
                                        <dt>
                                            <label>电子邮箱:</label></dt>
                                        <dd>
                                            <input autocomplete="off" type="text" name="userMail" id="userMail" tip="" value="${brandUserm.userMail}" altercss="gray" ajaxurl="emailVerify?userType=0" class="txt long" ignore="ignore" datatype="e"/>
                                        </dd>
                                    </dl> --%>
                                    <dl>
                                        <dt>
                                            <label>登录密码</label>
                                        </dt>
                                        <dd>
                                            <input type="text" style="display: none;" />
                                            <input autocomplete="off"  name="userPwd" id="userPwd" class="txt long" type="password" value="" datatype="ispassword" placeholder="请输入6-16位的密码，支持字母、数字、特殊字符_.@-" <%--plugin="passwordStrength"--%> maxlength="16" nullmsg="登录密码不能为空"  errormsg="请输入6-16位的密码，支持字母、数字、特殊字符_.@-" />
                                            <%--<span class="Validform_checktip"></span>--%>
                                            <%--<p id="passwordStrength" class="passwordStrength"><span>弱</span><span>中</span><span class="last">强</span></p>--%>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>重复密码</label></dt>
                                        <dd>
                                            <input class="txt long" type="password" value="" datatype="ispassword" maxlength="16" recheck="userPwd" placeholder="请再输入一次密码" nullmsg="请再输入一次" errormsg="两次密码不一致"  />
                                            <div class="helper">请再输入一次</div>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>企业名称</label></dt>
                                        <dd>
                                            <input class="txt long" name="comName" id="comName" type="text" datatype="*,isNull" value="${brandUserm.comName}" maxlength="50"/>
                                            <div class="helper">请输入您的企业名称</div>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>持有品牌</label></dt>
                                        <dd>
                                            <input class="txt long" name="dealBrands" id="dealBrands" placeholder="如有多个品牌，请用英文逗号隔开"  type="text" datatype="*,/^[^，]*$/,isNull" value="${brandUserm.dealBrands}"  maxlength="100"/>
                                            <div class="helper">请输入您的持有品牌</div>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dd class="confirm">
                                            <input type="checkbox" name="remember" class="chk" datatype="*,isNull" nullmsg="请确认已阅读" checked/><label for="remember" class="fs14 ml5">我已阅读</label>
                                                <a href="${ctx}/common/rules/info/976D2090BEF9486D939B37BEC5DB4E38" class="fs14" target="_blank" >《8637品牌超级代理服务协议》</a>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dd>
                                            <button type="submit" class="btn">点击注册</button>
                                        </dd>
                                    </dl>
                                 </form:form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 栏目：没有8637品牌超级代理账户? -->
                <div class="main-right">
                    <div class="content">
                        <h3 class="mb10">已有8637品牌超级代理账户?</h3>
                        <div class="clearfix"><a class="btn" href="${ctx}/common/login">立即登录</a></div>
                        <a class="mt10" href="${ctx}/about/about.jsp">8637品牌超级代理是什么?</a>
                        <a class="mt10" href="${ctx}/about/superiority.jsp">为何我们需要8637品牌超级代理?</a>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="login/view_foot.jsp"/>
    </div>

	<script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/seajs_config.js"></script>
    <script src="${res}/scripts/common.js"></script>
    <script src="${src}/plugin/passwordStrength-min.js"></script>
    <script src="${src}/plugin/Validform_v5.3.2_min.js"></script>
    <%--<script src="${src}/common/register.js"></script>--%>
    <script type="text/javascript">
        seajs.use(["dialog"], function (Dialog) {
            var activeIndexTemp = 0;   //选中状态缓存  0：经销商， 1：品牌商

            //标签切换
            var tabs = $(".tabs a").click(function () {
                activeIndexTemp = $(this).index();
                if($(".js-verfy-form").is(":hidden")){
                    $(".tabbody").hide().eq($(tabs).removeClass('active').index($(this).addClass('active'))).fadeIn(300);
                }
                if($(".js-verfy-form").is(":visible")){
                    $(tabs).removeClass('active').index($(this).addClass('active'));
                }
            });
            tabs.eq(parseURL(window.location.href).params.t).trigger("click");

            /*$("input.txt,select").focus(function () {
                $(this).parent().find(".helper").show();
            }).blur(function () {
                $(this).parent().find(".helper").hide();
            });*/

            //密码强度验证
            //$(".Validform_checktip").load(function () {
            //    var _left = $(this).parent().find('.txt').outerWidth();
            //    $(this).css({ left: _left + 10 }).animate({ left: _left, }, 300);
            //});

            //$('#userpassword').pwdstr('#passwordStrength');
            //$('input.txt').after('<a class="Validform_checktip">');

            var regForm = $(".js-person-form form,.js-company-form form").Validform({
                tiptype: 4,
                postonce: true,
                datatype:{
                    ispassword:function(gets,obj,curform,regxp){
                        var _reg =/^[\_\@\.\-a-zA-Z0-9]{6,16}$/;
                        return _reg.test(gets);
                    },
                    isNull:function(gets, obj, curform, regxp){
                        $(obj).val($.trim(gets.replace(/\s/g, "")));
                        return $.trim(gets) != "";
                    }
                },
                ajaxPost:true,
                callback:function(data){
                    //console.log(data);
                    if(data.code == "126000"){
                        //remind('success', '账号已成功注册，请重新登陆。');
                        var _htm = '<div><div class="ui-head"><h3>提示</h3></div><div class="confirm_bd ta-c" style="margin-top: 28px;font-size:14px;">账号已成功注册，请<a class="link" href="${ctx}/common/login">重新登陆</a></div></div>';
                        new Dialog({
                            content: _htm,
                            width: 500
                        }).show();
                    }else if(data.code == "126003"){
                        if(activeIndexTemp == 0){
                            setErrMsg("dealerForm", [{errName: 'verifyCode', errMsg: '验证码错误'}]);
                        }else if(activeIndexTemp == 1){
                            setErrMsg("brandForm", [{errName: 'verifyCode', errMsg: '验证码错误'}]);
                        }
                    }else{
                        remind("error", data.message);
                    }
                }
                /* ,usePlugin: {
                 passwordstrength: {
                 minLen: 6,
                 maxLen: 16
                 }
                 }*/
            });

            regForm.addRule([
                {
                    ele:"select.province",
                    datatype:"*",
                    nullmsg:"请选择地区"
                }
            ]);

            //重构完了以后， 页面跳转返回错误不需要，可删除
            /*$(function(){
                if('${message}' != ""){
                    var errList = eval('${message}');
                    setErrMsg("${formName}", errList);
                }
            });*/

            //写错误信息的方法
            function setErrMsg(form, json){
                $.each(json,function(i,item){
                    var inputObj = $("#"+form+" #"+item.errName);
                    $(".js-verfy-form").hide();
                    $(".tabbody").hide();
                    $("#"+form).parents(".tabbody").show();
                    if("province"==item.errName){
                        inputObj = $("#"+form+" select."+item.errName);
                    }
                    var objtip=inputObj.parent().find(".Validform_checktip");
                    objtip.text(item.errMsg);
                    inputObj.addClass("Validform_error");
                    objtip.removeClass("Validform_right").addClass("Validform_wrong");
                });
            }

            //获取验证码新步骤
            $(".js-verfy-form form").Validform({
                tiptype : 4,
                ajaxPost:true,
                datatype:{
                    ss4:function(gets, obj, curform, regxp){
                        var _reg = /^[a-zA-Z0-9]{4}$/;
                        return _reg.test(gets);
                    },
                    isNull:function(gets, obj, curform, regxp){
                        $(obj).val($.trim(gets.replace(/\s/g, "")));
                        return $.trim(gets) != "";
                    }
                },
                callback:function(data){
                    if(data.code == zttx.SUCCESS || data.code == '0'){
                        $(".js-verfy-form").hide();
                        $(".tabbody").find("input[name=userMobile]").val($(".js-verfy-form input[name=userMobile]").val());
                        $(".tabbody").eq(activeIndexTemp).show();
                    }else{
                        remind("error",data.message);
                    }
                }
            });

            //点击验证码图片
            $(document).on("click",".captcha_img", function () {
                var url = "${ctx}/captcha?t=" + Math.random();
                $(this).attr("src", url);
            });
        });

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
    </script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp" />
</body>
</html>