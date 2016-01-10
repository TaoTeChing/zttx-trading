<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>填补用户信息</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <!--样式-->
    <link href="${res}/styles/common/base.css" rel="stylesheet" />
    <link href="${res}/styles/common/account.css" rel="stylesheet" />
</head>
<body>
<div class="container completion">
    <jsp:include page="login/view_top.jsp"/>
    <div class="main">
        <div class="box">
            <div class="head">
                <h2>完善账号信息</h2>
            </div>
            <div class="main-left">
                <div class="tabs clearfix">
                    <a href="javascript:;" class="active"><span>我是终端商</span><em class="b-san"></em></a>
                    <a href="javascript:;"><span>我是品牌商</span><em class="b-san"></em></a>
                </div>
                <div class="content">
                    <div class="form js-verfy-form">
                        <form:form action="${ctx}/common/register/phoneVerify" method="post" autocomplete="off">
                            <dl>
                                <dt>
                                    <label>会员名:</label></dt>
                                <dd>
                                    <input type="hidden" name="userType" value="1" />
                                    <input type="hidden" name="checkLoacl" value="true" />
                                    <input type="text" name="userMobile" class="txt userMobile" value="${centerUser.userName}" datatype="m" errormsg="请输入手机号码" readonly>
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
                    <div class="tabbody hide">
                        <div class="form">
                            <form:form action="${ctx}/common/register/submit" id="dealerForm" method="post">
                                <input type="hidden" name="key" value="${key}" />
                                <input type="hidden" name="type" value="1" />
                                <input type="hidden" name="userMobile" value="${centerUser.userName}" />
                                <input type="hidden" name="userPwd" value="${centerUser.password}" />
                                <%--<dl>
                                    <dt>
                                        <label>手机号码</label></dt>
                                    <dd>
                                        <input autocomplete="off" type="text" class="txt userMobile" readonly="readonly" name="userMobile" value="${centerUser.userName}" altercss="gray" datatype="m" maxlength="11"/><button type="button" onclick="phoneVerify('1');" class="btn-getcode">免费获取验证码</button>
                                        <div class="helper">请填写正确的手机号码作为登录账号，并用于找回密码</div>
                                    </dd>
                                </dl>--%>
                                <dl>
                                    <dt>
                                        <label>验证码</label></dt>
                                    <dd>
                                        <input autocomplete="off" type="text" class="txt long" value="" name="verifyCode" nullmsg="验证码不能为空" errormsg="验证码输入错误，请重新获取验证码并输入" placeholder="验证码"  datatype="*,isNull"  maxlength="8"/>
                                        <div class="helper">请输入验证码</div>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>
                                        <label>您的姓名</label></dt>
                                    <dd>
                                        <input class="txt long" name="userName" id="userName" type="text" datatype="*,isNull" value="${centerUser.trueName}" maxlength="16"  nullmsg="请输入您的姓名" />
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
                                    <dt></dt>
                                    <dd>
                                            <%--<button type="submit" class="btn">提交账号信息</button>--%>
                                        <button type="submit" class="btn">提交账号信息</button>
                                    </dd>
                                </dl>
                            </form:form>
                        </div>
                    </div>
                    <div class="tabbody hide">
                        <div class="form" >
                            <form:form id="brandForm" action="${ctx}/common/register/submit" method="post">
                                <%--<input type="hidden" name="userType" id="userType" value="0"/>--%>
                                <input type="hidden" name="key" value="${key}" />
                                <input type="hidden" name="type" value="0" />
                                <input type="hidden" name="userMobile" value="${centerUser.userName}" />
                                <input type="hidden" name="userPwd" value="${centerUser.password}" />
                                <%--<dl>
                                    <dt>
                                        <label>手机号码</label></dt>
                                    <dd>
                                        <input autocomplete="off" type="text" name="userMobile" readonly="readonly"  nullmsg="手机号码不能为空" errormsg="格式错误，请填写正确的手机号码" class="txt userMobile" datatype="m" placeholder="例如:130****111122" value="${centerUser.userName}" altercss="gray" maxlength="11"/><button type="button" onclick="phoneVerify('1');" class="btn-getcode">免费获取验证码</button>
                                        <div class="helper">请填写正确的手机号码作为登录账号，并用于找回密码</div>
                                        <!-- <a href="javascript:$REG.phoneVerify('brandForm #userMobile','0');" class="btn">免费获取验证码</a>-->
                                    </dd>
                                </dl>--%>
                                <dl>
                                    <dt>
                                        <label>验证码</label></dt>
                                    <dd>
                                        <input autocomplete="off" type="text" class="txt long" nullmsg="验证码不能为空" errormsg="验证码输入错误，请重新获取验证码并输入" name="verifyCode" id="verifyCode" datatype="*,isNull" value="${brandUserm.verifyCode}" maxlength="8" placeholder="验证码"/>
                                        <div class="helper">请输入验证码</div>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>
                                        <label>企业名称</label></dt>
                                    <dd>
                                        <input class="txt long" name="comName" id="comName" type="text" nullmsg="请输入您的企业名称" datatype="*,isNull" value="${centerUser.trueName}" maxlength="50"/>
                                        <div class="helper">请输入您的企业名称</div>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>
                                        <label>持有品牌</label></dt>
                                    <dd>
                                        <input class="txt long" name="dealBrands" id="dealBrands" nullmsg="请输入您的持有品牌" placeholder="如有多个品牌，请用英文逗号隔开"  type="text" datatype="*,/^[^，]*$/,isNull" value="${brandUserm.dealBrands}"  maxlength="100"/>
                                        <div class="helper">请输入您的持有品牌</div>
                                    </dd>
                                </dl>
                                <dl>
                                    <dd>
                                        <button type="submit" class="btn">提交账号信息</button>
                                    </dd>
                                </dl>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="main-right">
                <!-- 栏目：没有8637品牌超级代理账户? -->
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
<script src="${src}/plugin/Validform_v5.3.2_min.js"></script>
<script>
    var tabs = $(".tabs a").click(function () {
        //$(".tabbody").hide().eq($(tabs).removeClass('active').index($(this).addClass('active'))).fadeIn(300);
        if($(".js-verfy-form").is(":hidden")){
            $(".tabbody").hide().eq($(tabs).removeClass('active').index($(this).addClass('active'))).fadeIn(300);
        }
        if($(".js-verfy-form").is(":visible")){
            $(tabs).removeClass('active').index($(this).addClass('active'));
        }
    });
    tabs.eq(parseURL(window.location.href).params.t).trigger("click");

    var regForm = $(".tabbody .form form").Validform({
        tiptype: 4,
        postonce: true,
        ajaxPost: true,
        /* beforeSubmit:function(){
            $('button[type="submit"]').text("登录中...");
        },*/
        datatype:{
            isNull:function(gets, obj, curform, regxp){
                $(obj).val($.trim(gets.replace(/\s/g, "")));
                return $.trim(gets) != "";
            }
        },
        callback: function (data) {
            $('button[type="submit"]').text("提交账号信息");
            if (data.code === 126000) {
                window.location.href= "${ctx}/"+data.object;
            }else{
                remind("error",data.message);
            }
        }
    });

    regForm.addRule([
        {
            ele:"select.province",
            datatype:"*",
            nullmsg:"请选择地区"
        }]);

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

    //获取验证码新步骤
    $(".js-verfy-form form").Validform({
        tiptype : 4,
        ajaxPost:true,
        datatype:{
            ss4:function(gets, obj, curform, regxp){
                var _reg = /^[a-zA-Z0-9]{4}$/;
                return _reg.test(gets);
            }
        },
        callback:function(data){
            if(data.code == zttx.SUCCESS || data.code == '0'){
                var i = $(".tabs a").eq(0).hasClass("active") ? 0 : 1;
                $(".js-verfy-form").hide();
                $(".tabbody").find("input[name=userMobile]").val($(".js-verfy-form input[name=userMobile]").val());
                $(".tabbody").eq(i).show();
            }else{
                remind("error",data.message);
            }
        },
        isNull:function(gets, obj, curform, regxp){
            $(obj).val($.trim(gets.replace(/\s/g, "")));
            return $.trim(gets) != "";
        }
    });
    //点击验证码图片
    $(document).on("click",".captcha_img", function () {
        var url = "${ctx}/captcha?t=" + Math.random();
        $(this).attr("src", url);
    });

    <%--var phoneVerify = function(userType)
    {
        /*var obj = $("#"+name);
        var isCheck = regForm.check(false,$("#"+name));
        if(!isCheck) return;
        var userMobile = $.trim(obj.val());*/
        //console.log({userMobile:'${centerUser.userName}',userType:userType});
        $.ajax({
            type : "get",
            url : "${ctx}/common/phoneVerify",
            data : {userMobile:13522225555,userType:userType, checkLoacl:true},
            dataType: "json",
            success : function(json) {
                console.log(json);
                //var validObj = obj.parent().find(".Validform_checktip");
                if(json.code!=0){
                    remind("error",json.message+",请稍后再试.");
                    //validObj.text(json.message);
                    //obj.addClass("Validform_error");
                    //validObj.attr("class","Validform_checktip Validform_wrong");
                }else{
                    getCodeFn($(".btn-getcode"),60);
                    remind("success","短信发送成功,请查收.");
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                      alert(errorThrown);
            }
        });
    };--%>
</script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp" />
</body>
</html>