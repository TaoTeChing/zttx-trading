<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title> ${meeting.meetName} - 8637品牌超级代理</title>
    <meta name="keywords" content="${meeting.brandName}, ${meeting.brandName}品牌代理, ${meeting.brandName}品牌招商"/>
    <meta name="description" content=""/>
    <jsp:include page="/market/header_css">
        <jsp:param name="brandesId" value="${brandesId}"/>
    </jsp:include>
    <link rel="stylesheet" href="${res}/styles/market/deal.css"/>
</head>
<body>
<div class="container" style="padding-top: 0;">
<div class="header clearfix">
    <%--<!--top-->--%>
    <jsp:include page="${ctx}/common/top"/>

    <%--<!-- head -->--%>
    <jsp:include page="${ctx}/market/header">
        <jsp:param name="brandesId" value="${brandesId}"/>
        <jsp:param name="url" value="/deal"/>
    </jsp:include>

</div>
<div class="do-body" style="padding:15px 0 20px 0;"><!--控制整体样式-->
    <div class="main em100 clearfix">
        <div class="left-part fl">
            <div class="hot-sales" style="display: none;">
                <div class="brand-logo fl">
                    <div class="logo-box">
                    <c:set value="${ fns:getBrandsIdSubDomain(brandesId)}" var="domain"></c:set>
                        <a href="http://${domain}${zttx}" title="${meeting.brandName}">
                            <img src="${res}${meeting.brandsLogo}" alt="${meeting.brandName}"/>
                        </a>
                    </div>

                    <%--<div class="title">${meeting.meetName}</div>--%>
                </div>
                <div class="pic fr">
                    <a href="/deal/${meeting.brandsId}?meetId=${meeting.refrenceId}" title="${meeting.brandName }"><img src="${res}${meeting.meetImage}" alt="${meeting.brandName}"/></a>
                </div>
            </div>
            <div class="product-intro" style="margin-top: 0;">
                <div class="introduction">
                    <h3 class="title" style="display: none;">
                        ${meeting.meetName}
                    </h3>

                    <div class="perfer" style="display: none;">
                        <label class="inline-block">活动优惠政策：</label>
                        <span class="inline-block perfer-span" title="${meeting.meetSlogan}">${meeting.meetSlogan}</span>
                    </div>
                    <div class="content">
                        ${meeting.meetMark}
                    </div>
                </div>
                <%--<div class="brand-product">
                    <div class="headline">
                        <div class="line"></div>
                        <div class="title">
                            <p class="en">Brand Product</p>
                            <p>品牌产品图</p>
                        </div>
                    </div>
                    <div class="content">
                        <img src="/images/deal/product-img1.jpg" alt=""/>
                        <img src="/images/deal/product-img2.jpg" alt=""/>
                    </div>
                </div>--%>
                <%--<div class="active-info">
                    <h3 class="title">活动产品信息</h3>
                    <div class="productshow">
                        <div id="carousel" class="es-carousel-wrapper">
                            <span class="ui-switchable-prev-btn" data-role="prev">1</span>
                            <span class="ui-switchable-next-btn" data-role="next">2</span>
                            <div class="es-carousel">
                                <ul class="ui-switchable-content clearfix" data-role="content">
                                    <li class="ui-switchable-panel"><a href="javascript:void(0);" title="" target="">
                                        <img src="/images/deal/active_1.jpg" alt="" /></a></li>
                                    <li class="ui-switchable-panel"><a href="javascript:void(0);" title="" target="">
                                        <img src="/images/deal/active_1.jpg" /></a></li>
                                    <li class="ui-switchable-panel"><a href="javascript:void(0);" title="" target="">
                                        <img src="/images/deal/active_1.jpg" /></a></li>
                                    <li class="ui-switchable-panel"><a href="javascript:void(0);" title="" target="">
                                        <img src="/images/deal/active_2.jpg" /></a></li>
                                    <li class="ui-switchable-panel"><a href="javascript:void(0);" title="" target="">
                                        <img src="/images/deal/active_2.jpg" /></a></li>
                                    <li class="ui-switchable-panel"><a href="javascript:void(0);" title="" target="">
                                        <img src="/images/deal/active_2.jpg" /></a></li>
                                    <li class="ui-switchable-panel"><a href="javascript:void(0);" title="" target="">
                                        <img src="/images/deal/active_3.jpg" /></a></li>
                                    <li class="ui-switchable-panel"><a href="javascript:void(0);" title="" target="">
                                        <img src="/images/deal/active_3.jpg" /></a></li>
                                    <li class="ui-switchable-panel"><a href="javascript:void(0);" title="" target="">
                                        <img src="/images/deal/active_3.jpg" /></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>--%>
            </div>
        </div>
        <div class="right-part fr">
            <div class="sign-up">
                <div class="headline clearfix">
                    <div class="line fl"></div>
                    <div class="title">
                        <h3>报名参加时间</h3>
                    </div>
                    <div class="line fr"></div>
                </div>
                <div class="content">
                    <div class="cd-box">
                        <c:set var="endTime" value="${fns:getTimeFromLong(meeting.endTime, 'yyyy-MM-dd 23:59:59')}"/>
                        <label for="">倒计时</label>
                        <span class="countdown" data-end="${endTime}" data-cur="${fns:getTimeFromLong(now, 'yyyy-MM-dd HH:mm:ss')}"></span>
                    </div>
                    <div class="deadline">
                        <label for="">截止时间：</label>
                        <span>${endTime}</span>
                    </div>
                    <div class="btns">
                        <c:if test="${not isOwner}">
                            <c:choose>
                                <c:when test="${state == 0}">
                                    <a href="javascript:void(0);" id="sign" class="wait">${tradeMeetJoin == null ? '提交预约信息':'已预约'}</a>
                                </c:when>
                                <c:when test="${state == 1}">
                                    <a href="javascript:void(0);" id="sign" class="starting">${tradeMeetJoin == null or tradeMeetJoin.appointment == 1 ? '我要报名':'已报名'}</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="finished">已结束</a>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                    <div class="info">
                        <%--大于100才显示--%>
                        <c:if test="${meeting.joinNum >= 100}">
                            <div class="field">
                                <label for="">当前意向客户人数：</label>
                                <span>${meeting.joinNum}</span>
                            </div>
                        </c:if>
                        <div class="field">
                            <label for="">总访问人数：</label>
                            <span>${meeting.viewNum}</span>
                        </div>
                        <div style=" margin-top: 10px;">
                            <label style="vertical-align: top;">在线客服：</label>
                            <!-- WPA Button Begin -->
                            <script charset="utf-8" type="text/javascript" src="http://wpa.b.qq.com/cgi/wpa.php?key=XzkzODAwMTU5Nl8xNTE2NjRfNDAwMTExODYzN18"></script>
                            <!-- WPA Button End -->
                        </div>
                        <%--<div class="suppbra-times">
                            <a href="${ctx}/market/brand/viewCompany/${brandesId}/${brandId}">${brandInfo.comName}</a><br />工作时间是：--%>
                            <%--<c:if test="${userOnlineService.onlineDateType==0}">周一 到周五  </c:if>
                            <c:if test="${userOnlineService.onlineDateType==1}">周一 到周六  </c:if>
                            <c:if test="${userOnlineService.onlineDateType==2}">周一 到周日  </c:if>
                            <c:if test="${userOnlineService.onlineDateType==null}">暂时未定 </c:if>--%>
                            <%--${fns:getTimeFromLong(userOnlineService.onlineBeginTime,"HH:mm:ss")}-----%>    <%--时间 --%>
                            <%--${fns:getTimeFromLong(userOnlineService.onlineEndTime,"HH:mm:ss")}--%>
                        <%--</div>--%>
                        <%-- QQ --%>
                        <%--<div class="suppbra-qq-service">--%>
                            <%--<c:forEach items="${userOnlineService.onlineDetailList}" var="obj"  >
                                <div class="inline-block">
                                        ${obj.name}:<a href="http://wpa.qq.com/msgrd?v=3&uin=${obj.qq}&site=qq&menu=yes" class="qq-service"></a>
                                </div>
                            </c:forEach>--%>
                                <%--<div class="inline-block">
                                    小红:<a href="" class="qq-service"></a>
                                </div>
                                <div class="inline-block">
                                    小红:<a href="" class="qq-service"></a>
                                </div>
                                <div class="inline-block">
                                    小红:<a href="" class="qq-service"></a>
                                </div>--%>
                        <%--</div>--%>
                    </div>
                </div>
            </div>
            <div class="dynamic">
                <div class="common-tit">
                    <span class="tit">意向客户报名实时动态</span>
                </div>
                <c:choose>
                    <c:when test="${fn:length(rtkList) >= 20}">
                        <div class="ui-box over" id="on-going">
                            <ul class="on-going">
                                <c:forEach items="${rtkList}" var="item">
                                    <li>
                                        <span class="company">${item.realName}</span><span class="address" title="${item.address}">${fns:trimLongString(item.address, 7)}</span><span class="do">${item.actionMark}</span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>
                <c:if test="${not empty joinList}">
                    <div ${fn:length(joinList) >= 10 ? 'class="ui-box over"':'class="ui-box"'} id="signed">
                        <ul class="signed">
                            <c:forEach items="${joinList}" var="item">
                                <li>
                                    <span class="company">${item.realName}</span>
                                    <span class="state">已报名</span>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
            </div>
            <c:if test="${not empty relatedMeets}">
                <div class="related-fair mt10">
                    <div class="common-tit">
                        <span class="tit">相关交易会</span>
                    </div>
                    <ul>
                        <c:forEach items="${relatedMeets}" var="item">
                            <li>
                                <a href="/deal/${brandesId}?meetId=${item.refrenceId}">
                                    <img src="${res}${item.meetImage}" width="260" height="90"/>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
</div>
<%--视频主持人--%>
<%--<jsp:include page="${ctx}/market/brand/rightFlash">
    <jsp:param name="brandsId" value="${brandesId}"/>
</jsp:include>--%>
<%-- 结束 --%>
<jsp:include page="/WEB-INF/view/common/component/footer.jsp"/>
</div>
<%--<!--我要报名模板-->--%>
<div style="display: none">
    <div id="goSign">
        <div class="ui-head">
            <h3><i>亲爱的用户：</i>请填写您的交易会报名资料<i></i></h3>
        </div>
        <div class="signForm">
            <style>
                .province{ padding: 5px;}
                .city{padding: 5px}
                .county{padding: 5px}
            </style>
            <form:form action="${ctx}/meet/deal/signup" id="signForm" method="post" data-widget="validator">
                <div class="field-content ui-form-item">
                    <label for="mobile">手机号码</label>
                    <input type="text" class="filed-input" value="${userMobile}" id="mobile" name="mobile" required data-rule="mobile" data-errormessage-required="请填写您的电话号码" maxlength="11"/>
                </div>
                <div class="field-content ui-form-item">
                    <label for="name">称呼</label>
                    <input type="text" class="filed-input" id="name" name="name" value="${realName}" required data-errormessage-required="请填写您的称呼" maxlength="32"/>
                </div>
                <div class="field-content ui-form-item field-selet">
                    <label>地区</label>
                        <span class="field">
                           <jsp:include page="${ctx}/client/Regional/searchaAll">
                               <jsp:param value="${province}" name="regProvince"/>
                               <jsp:param value="${city}" name="regCity"/>
                               <jsp:param value="${county}" name="regCounty"/>
                           </jsp:include>
                        </span>
                </div>
                <%--<div class="field-content ui-form-item">
                    <label for="address">详细地址</label>
                    <input type="text" class="filed-input" id="address" name="address" value="${address}"/>
                </div>--%>
                <div class="field-content ui-form-item">
                    <label>用户类型</label>
                    <input type="radio" class="ui-checkbox" name="userType" value="0" ${userType == 0 ? 'checked':''}>实体门店
                    <input type="radio" class="ui-checkbox" name="userType" value="1" ${userType == 1 ? 'checked':''}>个人
                    <input type="radio" class="ui-checkbox" name="userType" value="2" ${userType == 2 ? 'checked':''}>网店
                    <input type="radio" class="ui-checkbox" name="userType" value="3" ${userType == 3 ? 'checked':''}>品牌厂商
                </div>
                <div class="field-content ui-form-item">
                    <label for="remark">备注</label>
                    <textarea class="filed-input" id="remark" name="remark">${tradeMeetJoin == null? '':tradeMeetJoin.remark}</textarea>
                </div>
                <input name="meetId" type="hidden" value="${meeting.refrenceId}"/>

                <div class="btns">
                    <input type="submit" value="提交" class="save"/>
                    <input type="button" value="重新填写" class="cancel"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
<a class="ui-scrolltop" title="返回顶部">返回顶部</a>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js" type="text/javascript"></script>
<script src="${res}/scripts/seajs_config.js" type="text/javascript"></script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
<script src="${res}/scripts/common/base-init.js"></script>
<script src="${res}/scripts/deal/countDown.min.js"></script>
<script>

    var zttx = {
        SUCCESS: 126000, // ajax 成功代码
        VALIDERR: 126047, // 表单提交验证错误
        NOT_LOGIN: 126013 // 未登录
    };

    //弹出提示信息
    function remind(type, message, timeout, fn) {
        if ($.isFunction(timeout)) {
            fn = timeout;
            timeout = 0;
        }

        timeout = timeout || 3000;

        remindfn(type, message, timeout, fn);
    }

    function remindfn(type, message, timeout, fn) {
        seajs.use(["underscore", "dialog", "template"], function (_, Dialog, template) {
            //var types = 'simple,error,warning,success,tip,loading';
            var types = 'success,error';
            if (_.indexOf(types.split(','), type) == -1) {
                return;
            }
            var format = '<div class="remind-border"><div class="remind remind-{{type}}"><i class="remind-icon" style="{{style}}"></i><span class="remind-message">{{message}}</span></div></div>';

            var render = template.compile(format);
            var html = render({
                "type": type,
                "message": message,
                "style": (function () {
                    var str = ""
                    if (type == "success") {
                        str = "background: url(/images/common/icon_warn.png) -100px -0px no-repeat;";
                    } else if (type == "error") {
                        str = "background: url(/images/common/icon_warn.png) -97px -48px no-repeat;";
                    }
                    return str;
                }())
            });
            var d = new Dialog({
                "content": html,
                "hasMask": false,
                "classPrefix": "remind-dialog",
                "closeTpl": "",
                "width": "auto"
            });
            d.after('show', function () {
                setTimeout(function () {
                    d.hide();
                    if ($.isFunction(fn)) {
                        fn()
                    }
                }, timeout);
            });
            d.show();
        });
    }
    var signupForm = $('#signForm');

    seajs.use(['carousel', 'dialog', 'validator', 'widget'], function (Carousel, Dialog, Validator, Widget) {
        //报名倒计时
        var end = $(".countdown").attr("data-end");
        var cur = $(".countdown").attr("data-cur");
        new CountDown($(".countdown"), end, cur, 1, function () {
            //alert("报名结束");
        });
        //我要报名dialog
        var dialog = new Dialog({
            trigger: "#sign",
            width: "450px",
            content: $("#goSign"),
            <c:if test="${showDialog}">
            closeTpl: "",
            </c:if>
            effect: "fade"
        }).after("show", function () {
                    
                    $(this.element[0]).css({"position": "absolute"}); //0618 zxb add
                });

        <c:if test="${showDialog and state != 2}">
           /*$(window).scroll(function(){
               dialog.show();
           });*/
                dialog.show();
                //$(".ui-dialog").css({"position":"absolute"});
        </c:if>
        /*表单重置*/
        /*dialog.before('show', function () {
         $(".ui-form-item").removeClass("ui-form-item-error");
         $(".ui-form-explain").empty();
         $("#signForm")[0].reset();
         });*/

        Widget.autoRenderAll();

        var Core = Validator.query("#signForm");

        Core.set("autoSubmit", false);


        Core.on('formValidated', function (error) {
            if (!error) {
                $.post('${ctx}/meet/deal/signup', signupForm.serialize(), function (data) {
                    if (data.code == zttx.SUCCESS) {
                        remind('success', '资料提交成功');
                        var state = ${state};
                        var text = state == 0 ? '已预约' : '已报名';
                        $('#sign').text(text);
                        dialog.hide();
                    } else {
                        remind('error', data.message);
                    }
                }, 'json');
            }
        });

        /*初始化设值*/
        $("#signForm .filed-input").each(function(){
            $(this).val($(this).attr('value'));
        });
        
        /*重置*/
        $(".btns .cancel").click(function(){
            $("#signForm .filed-input").each(function(){
                $(this).val("");
            });
            $("#signForm select").each(function(){
                $(this).find("option:first").prop("selected","selected");
            });
        });

        /*活动产品信息*/
        //var carousel = new Carousel({
        //    element: "#carousel",
        //    hasTriggers: false,
        //    easing: 'easeOutStrong',
        //    effect: 'scrollx',
        //    step: 3,
        //    viewSize: [825],
        //    circular: false,
        //    autoplay: true
        //}).render();
    });
    /*活动报名实时动态*/
    function AutoScroll(obj) {
        $(obj).find("ul:first").animate({
            marginTop: "30px"
        }, 500, function () {
            $(this).css({marginTop: "0px"}).find("li:last").prependTo($(this));
            //$(this).css({marginTop: "0px"}).find("li:first").appendTo(this);
        });
    }
    if ($("#on-going li").length > 10) {
        setInterval('AutoScroll("#on-going")', 3000);
    }
    if ($("#signed li").length > 10) {
        setInterval('AutoScroll("#signed")', 3000);
    }

    /*function signup() {
     $.post('${ctx}/meet/deal/signup', signupForm.serialize(), function (data) {
     if (data.code == zttx.SUCCESS) {
     remind('success', '资料提交成功');
     } else {
     remind('error', data.message);
     }
     }, 'json');
     }*/
</script>
</body>
</html>
