<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%--和询价有关的弹窗 开始--%>
    <div style="display: none;">
        
    <%--step = 1  未登录 点击“我要询价” --%>
    <div class="enquiry-login-dialog">
            <style>
                .Validform_wrong{
                    padding-left: 0;
                }
            </style>
            <div class="e-l-d-tabs">
                <ul class="e-l-d-menu inline-float">
                    <li><a href="javascript:;">访客咨询</a></li>
                    <li class="active"><a href="javascript:;">会员登录</a></li>
                </ul>
            </div>
            <div class="e-l-d-tabs-con" style="display:none;margin-top: 45px;">
                <form class="ui-form fk-enquiry-form" data-widget="validator" id="dealerRegist">
                    <div class="enquiry-form" style="border-top: 0;">
                        <div class="ui-form-item">
                            <label class="ui-label"><span class="ui-form-required">*</span>您的手机号：</label>
                            <input type="hidden" name="productId" value="${productId}">
                            <div class="clearfix">
                                <input type="text" class="ui-input fl" id="userMobile" data-rule="mobile" name="userMobile"  required data-display="手机号码" value="${brandUserm.userMobile }"/>
                                <input type="button" class="fl" id="sendMsg" style="font-size: 12px;height: 28px;line-height:24px;text-align:center;border: 1px #ddd solid;background-color: #EFEFEF;color: #666666;margin-left: 5px;width: 120px;" value="免费获取验证码" />
                            </div>
                            <!--存在用户的时候，显示下面的就好-->
                            <div style="color: #f80; display: none;" class="existMobile">当前用户已存在，您可以选择 <a href="javascript:;" class="js-hydl">会员登录</a></div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label"><span class="ui-form-required">*</span>验证码：</label>
                            <input type="text" class="ui-input fl" required data-display="验证码" name="verifyCode"/>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label"><span class="ui-form-required">*</span>姓名：</label>
                            <input type="text" class="ui-input fl" name="userName" required data-display="姓名" />
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label"><span class="ui-form-required">*</span>收货地址：</label>
                            <div>
                                <style>
                                    .chosen-select{padding: 5px;width: 100px;}
                                </style>

                                <input type="hidden" name="provinceName" value="${dealerInfo.provinceName}" >
                                <input type="hidden" name="cityName" value="${dealerInfo.cityName}"  />
                                <input type="hidden" name="areaName" value="${dealerInfo.areaName}"  />
                                <input type="hidden" name="areaNo" value="${dealerInfo.areaNo}" />
                                <jsp:include page="${ctx}/client/Regional/searchaAll">
                                    <jsp:param value="${dealerInfo.provinceName}" name="regProvince" />
                                    <jsp:param value="${dealerInfo.cityName}"    name="regCity" />
                                    <jsp:param value="${dealerInfo.areaName}"    name="regCounty" />
                                 </jsp:include>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">详细地址：</label>
                            <textarea class="ui-textarea" style="width: 425px;color: #666;" name=""></textarea>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">
                                <span class="ui-form-required">*</span>采购量：
                            </label>
                            <div class="fl">
                                <input type="text"  name="purchaseNum"  class="ui-input js-number-text js-number" value="1" style="width: 50px;"  required data-display="采购量"/>
                            </div>
                            <div class="e-f-mixbox fl">
                                <div class="e-f-mixbox-item js-umber-plus" style="border-bottom: 1px solid #c1c1c1;">
                                    <a class="e-f-plus" href="javascript:;"></a>
                                </div>
                                <div class="e-f-mixbox-item js-umber-minus">
                                    <a class="e-f-minus" href="javascript:;"></a>
                                </div>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">采购说明：</label>
                            <textarea class="ui-textarea" name="purchaseMark" style="width: 425px;height:90px;color: #666;"></textarea>
                        </div>
                        <div class="ui-form-item">
                            <div class="file_wrap inline-block">
                                <a class="e-f-add" href="javascript:;">添加附件</a>
                                <input class="input_file" id="fujian_nologin" name="file" type="file" style="width: 56px;">
                            </div>
                            <input type="hidden" class="ui-input" name="attachment" readonly style="width: 156px;"/>
                            <input type="text" class="js-fj-name" readonly />
                            <span class="e-f-explain">（色彩 尺码 布料等问题说明）</span>
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <input class="e-f-submit" type="submit" value="发送询价单并申请加盟"/>
                    </div>
                </form>
            </div>
            <div class="content login e-l-d-tabs-con">
                <div class="tab-item">
                    <div class="form">
                        <input type="hidden" name="redirect" value="${redirect}"/>
                          <form:form class="js-login-form" action="${ctx}/common/submitlogin" method="post">
                            <dl>
                                <dt>
                                    <label>会员名:</label>
                                </dt>
                                <dd>
                                    <input maxlength="11" value="${account}" type="text" name="account" class="txt long" datatype="m|e" nullmsg="请填写会员名！" errormsg="会员名必须是手机号码！" sucmsg="" placeholder='手机号' value="手机号"/>
                                </dd>
                            </dl>
                            <dl id="password_box">
                                <dt>
                                    <label>密 码:</label>
                                </dt>
                                <dd>
                                    <input type="text" style="display: none;" />
                                    <input ${empty brandAccount ? '':'checked="true"'} type="password" name="pwd" datatype="*" nullmsg="请填写密码！" autocomplete="off" sucmsg="" class="txt long"/>
                                    <input id="userType" type="hidden" name="userType" value="1"/>
                                    <span class="Validform_checktip"></span>
                                    <a href="${ctx}/common/forgotpass" class="link" target="_blank">忘记密码?${showCapcha}</a>
                                </dd>
                            </dl>
                             <c:if test="${showCapcha}">
                                    <dl class="captcha_dl">
                                        <dt>
                                            <label>验证码:</label>
                                        </dt>
                                        <dd>
                                            <input type="text" name="captcha" datatype="*" nullmsg="请填写验证码！" class="txt long"/>
                                            <img class="captcha_img" src="${ctx}/captcha" alt=""/>
                                        </dd>
                                    </dl>
                             </c:if>
                            <dl>
                                <dt></dt>
                                <dd>
                                    <input type="checkbox" name="remember" value="true" id="Checkbox2" ${empty account ? '':'checked'} />
                                    <label for="Checkbox2" class="fs14 ml5">记住用户名</label>
                                    <span class="c-r-on fs12 ml10">使用公用电脑勿勾选</span>
                                </dd>
                            </dl>
                            <dl>
                                <dt></dt>
                                <dd>
                                    <input type="submit" class="btn yahei fs16 bgc-r c-w" value="点击登录"/>
                                </dd>
                            </dl>
                            <dl class="yahei mb40">
                                <dt></dt>
                                <dd>
                                    <span class="fl fs14 ">如找回密码遇到困难,请</span><a class="fs14" href="${ctx}/about/contactus.jsp" style="color: #f80;" target="_blank">联系我们</a>
                                </dd>
                            </dl>
                        </form:form>
                        
                    </div>
                </div>
            </div>
        </div>

        <%--step 2 已登录 未授权 --%>
        <div class="js-enquiry-show">
            <div class="enquiry-head">
                <h3>询价</h3>
            </div>
            <div>
                <div class="enquiry-steps mt15">
                    <!-- 第一步 -->
                    <ol>
                        <li class="current first">发送询价</li>
                        <li><span>同意加盟</span></li>
                        <li><span>下单并付款</span></li>
                        <li><span>买家发货</span></li>
                        <li class="last"><span>确认收货</span></li>
                    </ol>
             
                    <form class="ui-form already-login-form" data-widget="validator" >
                        <div class="enquiry-form mt15">
                            <div class="ui-form-item e-f-fitem">
                                <label class="ui-label e-f-label">您的信息：</label>
                                <div class="e-f-textstyle">
                                           ${dealerUserm.userName}.${dealerUserm.userMobile}.
                                           ${dealerInfo.provinceName}${dealerInfo.cityName}${dealerInfo.areaName}${dealerInfo.dealerAddress }
                                </div>
                            </div>
                            <div class="ui-form-item e-f-fitem">
                                <label class="ui-label e-f-label">询价产品：</label>
                                <div class="e-f-textstyle">
                                       ${productInfo.brandsName}.${productInfo.productTitle}
                                       <input type="hidden" name="productId" value="${productId}">
                                </div>
                            </div>
                            <div class="ui-form-item">
                                <label class="ui-label">
                                    <span class="ui-form-required">*</span>采购量：
                                </label>
                                <div class="fl">
                                    <input type="text" name="purchaseNum" class="ui-input js-number-text js-number" value="1" style="width: 50px;" required  data-display="采购量"/>
                                </div>
                                <div class="e-f-mixbox fl">
                                    <div class="e-f-mixbox-item js-umber-plus" style="border-bottom: 1px solid #c1c1c1;">
                                        <a class="e-f-plus" href="javascript:;"></a>
                                    </div>
                                    <div class="e-f-mixbox-item js-umber-minus">
                                        <a class="e-f-minus" href="javascript:;"></a>
                                    </div>
                                </div>
                            </div>
                            <div class="ui-form-item">
                                <label class="ui-label">采购说明:</label>
                                <textarea class="ui-textarea" name="purchaseMark" style="width: 425px;height:90px;color: #666;"></textarea>
                            </div>
                            <div class="ui-form-item">
                                <div class="file_wrap inline-block">
                                    <a class="e-f-add" href="javascript:;">添加附件</a>
                                    <input class="input_file" id="fujian_login" name="file" type="file" style="width: 56px;">
                                </div>
                                <input type="hidden" class="ui-input" name="attachment" readonly style="width: 156px;"/>
                                <input type="text" class="js-fj-name" readonly/>
                                <span class="e-f-explain">（色彩 尺码 布料等问题说明）</span>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <input class="e-f-submit" type="submit" value="发送询价单" name="send_submit"/>
                            <!-- 已发送过询价单的给下面的提示 最好给“发送询价单”按钮加一个 disabled 阻止提交 -->
                           <!--  <span style="color: #f80;font-size: 14px;font-family: 'Microsoft yahei';">（该产品已发送询价单）</span> -->
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        <%-- step 3 已登录 未授权 已发送询价申请 --%>
       <div class="js-enquiry-already">
            <div class="enquiry-head">
                <h3>询价</h3>
            </div>
            <div>
                <div class="enquiry-steps mt15">
                    <!-- 第二步 -->
                    <ol>
                        <li class="done current-prev first">发送询价</li>
                        <li class="current"><span>同意加盟</span></li>
                        <li><span>下单并付款</span></li>
                        <li><span>买家发货</span></li>
                        <li class="last"><span>确认收货</span></li>
                    </ol>
                    <form class="ui-form">
                        <div class="enquiry-form mt15">
                            <div class="ui-form-item e-f-fitem">
                                <label class="ui-label e-f-label">关于您对：</label>
                                <div class="e-f-textstyle">
                                ${brandesInfo.brandCompanyName}的询价已经成功发送给8637品牌超级代理
                                </div>
                            </div>
                            <div style="padding: 10px 0 0 20px;">
                                我们将会在<span style="color: #f80;">1-3</span>个工作日内对您的询价单与品牌商对接沟通。
                            </div>
                            <div style="padding: 10px 0 10px 20px;">
                                我们的客服热线：<span style="color: #f80;">0574-87217777-2</span>
                            </div>
                        </div>
                        <div style="padding-left: 20px;">
                            <a href="${ctx }/common/dealer/productList" style="color: #0082cc;font-size: 14px;font-family: 'Microsoft yahei';">继续浏览其他产品进行询价</a><!--跳到产品页  -->
                            &nbsp;&nbsp;&nbsp;
                            <a href="${ctx }/dealer/inquiry" style="color: #0082cc;font-size: 14px;font-family: 'Microsoft yahei';">进入我的询价管理中心</a> <!--  终端商后台-->
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- step 4 已加盟,但未报价或未加入产品线 -->
        <div  class="js-join-already">
            <div class="enquiry-head">
                <h3>温馨提示</h3>
            </div>
            <div class="enquiry-join-already">
                <div class="e-j-a-tit"> 您已对此产品发起询价，请耐心等待报价或联系我们：0574-87217777-2</div>
                <div class="e-j-a-detail">
                    <a class="e-f-submit mr10" href="${ctx }/common/dealer/productList" target="_blank">继续浏览其他产品</a>
                    <a class="e-f-submit" href="${ctx }/dealer/inquiry" target="_blank">进入我的询价管理中心</a>
                </div>
            </div>
        </div>
        <!-- step 5 已加盟    并   报价或加入产品线 -->
        <div class="js-price-already">
            <div class="enquiry-head">
                <h3>温馨提示</h3>
            </div>
            <div class="enquiry-join-already" style="padding-top: 24px;">
                <div class="e-j-a-tit"> 产品已报价，立即前往 <a href="${ctx }/dealer/inquiry" target="_blank">我的询价管理中心</a>购买</div>
            </div>
        </div>
        
                   

    </div>
    
    
    <%--和询价有关的弹窗 结束--%>
<%-- 增加的 登录验证框架 --%>
<script src="${res}/scripts/jquery.min.js"></script> 
<script  src="${res}/scripts/plugin/Validform_v5.3.2_min.js"></script>
<script type="text/html" id="captcha_tpl">
    <dl class="captcha_dl">
        <dt>
            <label>验证码:</label>
        </dt>
        <dd>
            <input type="text" name="captcha" datatype="*" nullmsg="请填写验证码！" class="txt long"/>
            <img class="captcha_img" src="${ctx}/captcha" alt=""/>
        </dd>
    </dl>
</script>
<script type="text/javascript">
    	function sendMsg(){
    		var userMobile = $("#userMobile").val();
		    $.post("${ctx}/common/queryPhoneVerify", {userMobile: userMobile, userType:1}, function(data){
                if(data.code==126002){
                    //用户存在
                    $(".existMobile").show();
                }else if(data.code==0){
                    //发送成功
                    $("#sendMsg").val("重新发送(60)").attr("disabled",true);
                    $(".existMobile").hide();
                    var _time = 60,timer = null;
                    timer = setInterval(function(){
                        _time--;
                        $("#sendMsg").val("重新发送("+_time+")");
                        if(_time == 0){
                            $("#sendMsg").val("免费获取验证码").attr("disabled",false);
                            clearInterval(timer);
                        }
                    },1000);
                }else if(data.code==126013){
                    //错误的手机号格式
                    remind("error","手机号格式错误");
                }else if(data.code==3){
                    //请一分钟后再发
                    remind("error","请于一分钟后再发送");
                }else if(data.code == 126016){
                         remind()
                }
		    }, "json");
    	}

        $("#sendMsg").click(function(){
            //此处无法用校验框架获取手机号码校验是否出错，因此重新验证一下。
            var mobileNum = $("#userMobile").val();

            if(!mobileNumReg(mobileNum)){
                return;
            }

            sendMsg();
            return;
        });

        <%-- 和询价有关的脚本 开始 --%>
        var step = ${step};
       
        seajs.use(['dialog','ajaxFileUpload'], function(Dialog) {
            //已登录 未授权
            if(step=="2"){
                var invitedio = new Dialog({
                    //trigger: '.to-enquiry',
                    effect: 'fade',
                    classPrefix:"enquiry-dialog",
                    content: $(".js-enquiry-show"),
                    width:535
                });
            }
            //已登录 未授权 已发送询价请求
            /*if(step=="3"){
                var already = new Dialog({
                    //trigger: '.to-enquiry',
                    effect: 'fade',
                    classPrefix:"enquiry-dialog",
                    content: $(".js-enquiry-already"),
                    width:535
                }).show();
            }*/


               //加盟 但未报价或未加入产品线
            /*if(step=="4"){
                    var join_already = new Dialog({
                        effect:'fade',
                        classPrefix:"enquiry-dialog",
                        content: $(".js-join-already"),
                        width:535
                    }).show();
                }*/
                    
                    //已加盟    并报价或加入产品线
               /*if(step=="5"){
                    var price_already = new Dialog({
                        effect:'fade',
                        classPrefix:"enquiry-dialog",
                        content: $(".js-price-already"),
                        width:535
                    }).show();
               }*/
                
                    

            //未登陆未授权
            if(step=="1"){
                var login = new Dialog({
                    //trigger: '.to-enquiry',
                    effect: 'fade',
                    classPrefix: "enquiry-dialog",
                    content:$(".enquiry-login-dialog"),
                    width:535
                });
            }

            var already2 = new Dialog({
                //trigger: '.to-enquiry',
                effect: 'fade',
                classPrefix:"enquiry-dialog",
                content: $(".js-enquiry-already"),
                width:535
            });
            $(".to-enquiry").click(function(){
                if(step == "1"){
                    login.show();
                }
                if(step == "2"){
                    invitedio.show();
                }
                if(step == "3"){
                    already2.show();
                }
                /*if(step == "4"){
                    join_already.show();
                }
                if(step == "5"){
                    price_already.show();
                }*/
            });

            /*已登录 未授权 表单验证*/
            baseFormValidator({
                selector:".already-login-form",
                isAjax:true,
                beforeSubmitFn:function(){
                    $.get("${ctx}/common/queryPrice/submit",$("form.already-login-form").serialize(),function(data){
                        if(data.code==zttx.SUCCESS){
                            invitedio.hide();
                            already2.show();
                            step = 3;
                        }else{
                            remind("error",data.message);
                            if(invitedio){
                                //出错了关闭掉询价的弹窗
                                invitedio.hide();
                            }
                        }
                    },"JSON");
                }
            });
            /*访客 提交表单 验证*/
            baseFormValidator({
                selector:".fk-enquiry-form",
                isAjax:true,
                addElemFn:function(Core,Validator){
                    var _self = this;
                    Core.addItem({
                        element: $(_self.selector).find("#province"),
                        required: true,
                        rule: 'minlength{min:0}',
                        errormessageRequired: "请选择省份"
                    })
                },
                beforeSubmitFn:function(){
                    $.get("${ctx}/common/queryPrice/submit",$("#dealerRegist").serialize(),function(data){
                        if(data.code==126005){
                            //用户存在
                            remind("error","用户存在")
                        }else if(data.code==zttx.SUCCESS){
                            login.hide();
                            already2.show();
                            step = 3;
                        }else if(data.code==126016){
                            remind("error",data.object[0].errMsg); //验证码不正确
                        }else{//品牌加盟书关闭
                            remind("error",data.message);
                        }
                    },"JSON");
                }
            });

            //上传附件
            $("#fujian_nologin").change(function(){
                uploadImage("fujian_nologin");
            });
            //上传附件
            $("#fujian_login").change(function(){
                uploadImage("fujian_login");
            });
            function uploadImage(uploadId){
                $.ajaxFileUpload({
                    url: '/common/showFile',
                    secureuri: false,
                    fileElementId: uploadId,
                    dataType: 'json',
                    success: function(data){
                        //发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                        $('#'+uploadId).bind('change', function(){
                            uploadImage(uploadId);
                        });
                        if(data.code == zttx.SUCCESS){
                            $("#"+uploadId).parents(".ui-form-item").find("input[name=attachment]").val(data.message);
                            $("#"+uploadId).parents(".ui-form-item").find(".js-fj-name").val(data.object);
                        }else{
                            remind("error",data.message);
                        }
                    }
                });
            }

        });

        $(".js-number-text").on("keyup change",function(){
            $(this).val($(this).val().replace(/[^\d]/g, ''));
            if($(this).val() == 0){
                $(".js-number-text").val("1");
                return;
            }
            if($(this).val() >= 10000000){
                $(".js-number-text").val("10000000");
                remind("error","数值过高");
                return;
            }
            $(".js-number-text").val($(this).val());
        });
        $(".js-umber-plus").click(function(){
            var num = $(".js-number-text").val();
            if(num >= 10000000){
                $(".js-number-text").val("10000000");
                remind("error","数值过高");
                return;
            }
            num++;
            $(".js-number-text").val(num);
        });
        $(".js-umber-minus").click(function(){
            var num = $(".js-number-text").val();
            if(num <= 1){
                $(".js-number-text").val("1");
                return;
            }
            num--;
            $(".js-number-text").val(num);
        });

        //登录弹窗tabs切换
        $(".e-l-d-tabs .e-l-d-menu li").click(function(){
            var index = $(this).index();
            $(".e-l-d-tabs .e-l-d-menu li").removeClass("active").eq(index).addClass("active");
            $(".e-l-d-tabs-con").hide().eq(index).show();
        });
        //检测到号码已存在跳到会员登录
        $(".js-hydl").click(function(){
            $(".e-l-d-tabs .e-l-d-menu li").removeClass("active").eq(1).addClass("active");
            $(".e-l-d-tabs-con").hide().eq(1).show();
        });
        /*以下为登录验证*/
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
            tiptype: 4,
            postonce: true,
            ajaxPost: true,
            callback: function (data) {
                if ($.type(data) === 'string') {
                    data = $.parseJSON(data);
                }

                if (data.code === 126000) {
                    var redirect = $.trim($('input[name="redirect"]').val());
                    /*if (redirect != '') {

                     } else {
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
                     */
                    window.location.reload();
                } else {
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

        // 服务器端返回的登陆类型   '${loginType}'
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

        <%-- 和询价有关的脚本 结束 --%>
</script>