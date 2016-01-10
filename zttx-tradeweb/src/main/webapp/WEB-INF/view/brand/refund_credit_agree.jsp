<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.zttx.web.consts.ZttxConst" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-交易管理-采购订单-退款详情</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/refund_detail.css"/>
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
<div class="main layout">
    <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
    <div class="main_con">
        <!-- 面包导航，并不是每个页面都有 -->
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <a class="link" href="${ctx}/brand/order/purorder">交易管理</a>
                <span class="arrow">&gt;</span>
                <a class="link" href="${ctx}/brand/order/purorder">采购订单</a>
                <span class="arrow">&gt;</span>
                <span class="current">退款详情</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp"/>
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <%--第二版步骤条--%>
            <div class="v2-steps" style="width:745px;margin-left: 100px;">
                <span class="text1">申请退款</span>
                <span class="text2 current">品牌商处理退款申请</span>
                <span class="text3">退款完成</span>

                <div class="steps v2-steps-2" style="width: 510px;"></div>
            </div>
            <div class="refund_detail">
                <div class="top clearfix">
                    <div class="left">
                        <div class="refund_info">
                            <h3>退款信息</h3>

                            <p>
                                <label for="">退款单号：</label><span class="num">${dealerRefund.refundId}</span>
                            </p>

                            <p>
                                <label for="">退款类型：</label>${dealerRefund.needRefund==true ?"退款退货":"仅退款"}
                            </p>

                            <p>
                                <label for="">退款金额：</label><strong class="price">${dealerRefund.refundAmount}</strong>元
                            </p>
                        </div>
                        <div class="refund_dealer">
                            <h3>终端商信息</h3>

                            <p>
                                <label for="">终端商名称：</label><span class="inline-block"
                                                                  style="width: 110px;">${dealerInfo.dealerName}</span>
                            </p>

                            <p>
                                <label for="">联系人名称：</label>${dealerInfo.dealerUser}
                            </p>

                            <p>
                                <label for="">联系方式：</label>
                                <c:choose>
                                    <c:when test="dealerInfo.dealerTel != null">
                                        ${dealerInfo.dealerTel}
                                    </c:when>
                                    <c:otherwise>
                                        ${dealerInfo.userMobile}
                                    </c:otherwise>
                                </c:choose>
                            </p>

                            <p>
                                <label for="">所在地：</label>${dealerInfo.provinceName} ${dealerInfo.cityName} ${dealerInfo.areaName}
                            </p>
                        </div>
                    </div>
                    <div class="detail_box">
                        <%-- 已要求客服介入,等待客服介入 --%>
                        <c:if test="${dealerRefund.cusJoin==true}">

                            <div class="tips">
                                <c:if test="${dealerRefund.serProStatus==1}">
                                    <i class="iconfont c-w mr5">&#xe626;</i>客服人员正在介入，请耐心等待...
                                </c:if>
                                <c:if test="${dealerRefund.serProStatus==2}">
                                    <i class="iconfont c-w mr5">&#xe626;</i>纠纷处理中…
                                </c:if>
                                <c:if test="${dealerRefund.serProStatus==3}">
                                    <i class="iconfont c-w mr5">&#xe626;</i>纠纷处理完毕
                                </c:if>
                                <c:if test="${dealerRefund.serProStatus==4}">
                                    <i class="iconfont c-w mr5">&#xe626;</i>纠纷已关闭
                                </c:if>
                                <c:if test="${dealerRefund.serProStatus==5}">
                                    <i class="iconfont c-w mr5">&#xe626;</i>等待客服介入中...
                                </c:if>
                            </div>

                        </c:if>

                        <%-- 申请退款等待处理 --%>
                        <div class="handle_refund">
                            <h3>终端商请求退款</h3>

                            <p class="explain">
                                您还有 <strong class="c-r time" data-endtime="${endTime}" style=" color:red"></strong>
                                来处理终端商的退款申请
                            </p>
                            <form:form action="" id="subPass_Form">
                            <div class="detail">
                                <p class="item">
                                    <label for="">退款原因：</label>${dealerRefund.refundReason}
                                </p>

                                <p class="item">
                                    <label for="">退款金额：</label><strong class="price"
                                                                       id="refundAmount">${dealerRefund.refundAmount}</strong>
                                </p>

                                <p class="item">
                                    <label for="">退款说明：</label>${dealerRefund.refundMark}
                                </p>

                                <div class="item">
                                    <label for="">凭证：</label>

                                    <div class="inline-block">
                                        <c:forEach items="${dealerRefAttachts}" var="dealerRefAttacht">
                                            <a href="${dealerRefAttacht.domainName}${dealerRefAttacht.attachtName}"
                                               data-lightbox="arraylist_${status.index}">
                                                <img width="100" height="100"
                                                     src="${res}${dealerRefAttacht.domainName}${dealerRefAttacht.attachtName}"/>
                                            </a>
                                        </c:forEach>
                                    </div>
                                </div>

                                    <%--<div class="item" id="payState">
                                        <label>退款支付方式：</label>&lt;%&ndash;欠收抵应付 当前终端商欠收<strong class="price">${reportInfos.debtMoney}</strong>元&ndash;%&gt;
                                        <div class="inline-block">
                                            <p>
                                                <label><input type="checkbox" class="ui-checkbox" name="payType"
                                                              value="1" id="payTypeOne"/> 欠收抵应收</label>
                                                当前终端商欠收 <strong id="debtMoney">${debtMoney}</strong> 元
                                            </p>
                                            <p>
                                                <label><input type="checkbox" class="ui-checkbox" name="payType"
                                                              value="2" id="payTypeTwo"/> 余额支付</label>
                                                当前账户余额 <strong id="accountBalance">${accountBalance}</strong> 元
                                            </p>
                                        </div>
                                    </div>--%>

                            </div>

                            <div class="item">
                                <input type="hidden" id="refrenceId" value="${dealerRefund.refrenceId}"
                                       name="refrenceId"/>
                                <input type="hidden" value="${debtMoney}" name="debtMoney"/>
                                <input type="hidden" value="${accountBalance}" name="accountBalance"/>
                                <c:choose>
                                    <c:when test="${hasBrandPayword==true}">
                                        <label style="vertical-align: middle" for="">平台支付密码：</label>
                                        <input autocomplete="off" style="width: 230px;" type="password" class="ui-input"
                                               id="pwd" name="payWord"/>

                                        <div class="item-remind">此密码为8637品牌超级代理平台支付密码</div>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="<%=ZttxConst.PAYAPI_WEBURL_RESETPWD%>"
                                           style="width: auto;margin-left: 65px;"
                                           class="simple_button" target="_blank">请先设置支付密码</a>
                                        <span style="line-height: 28px;">下次支付需要您输入支付密码，请牢记</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="operation">
                                <c:if test="${hasBrandPayword == true}">
                                    <a class="ui_button ui_button_morange" id="agreeRefund">确认</a>
                                </c:if>
                                <a class="ui_button ui_button_mgray ml5"
                                   href="${ctx}/brand/refund/factory/view/${dealerRefund.refundId}">取消</a>
                            </div>
                        </div>
                        </form:form>
                    </div>
                </div>
            </div>
            <div class="bottom">

                <div class="info">

                </div>
                <!-- 操作记录详情    start -->
                <jsp:include page="/WEB-INF/view/dealer/refund_refReply.jsp"/>
                <!-- 操作记录详情    end   -->
            </div>
        </div>
    </div>
</div>
</div>
<div style="display: none;">
    <div id="setPassword_box" class="setPassword_box">
        <div class="ui-head">
            <h3>设置支付密码</h3>
        </div>
        <form:form data-widget="validator" class="ui-form setPass_Form mt15" id="setPass_Form">
            <div class="ui-form-item">
                <label class="ui-label" for="">支付密码</label>
                <input style="width: 200px;" data-display="支付密码" class="ui-input" id="payWord" name="payWord"
                       type="password"/>
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">重复支付密码</label>
                <input style="width: 200px;" required data-errormessage-required="请再重复输入一遍密码，不能留空"
                       data-rule="confirmation{target: '#payWord',name:'第二遍'}" data-display="第一遍" class="ui-input"
                       type="password"/>

                <div class="ui-form-explain">下次支付需要您输入支付密码，请牢记</div>
            </div>
            <div class="ui-form-item">
                <button class="simple_button confirm_btn" type="submit">确定</button>
                <button class="simple_button cancel_btn ml10" type="button">取消</button>
            </div>
        </form:form>
    </div>
</div>
<form:form id="order_payment" action="${ctx}/brand/refund/factory/agree/${dealerRefund.refundId}"
           method="post"></form:form>
<%@ include file="bottom.jsp" %>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script>
    $(function () {
        //支付方式多选
        /*var refundAmount = $("#refundAmount").html();
         refundAmount = refundAmount==""?0: refundAmount*1;
         var debtMoney = $("#debtMoney").html();
         debtMoney = debtMoney == "" ? 0 : debtMoney*1;
         var accountBalance = $("#accountBalance").html();
         accountBalance = accountBalance == "" ? 0 : accountBalance*1;
         if (refundAmount <= debtMoney){
         $("#payTypeOne").attr("checked","true");
         $("#payTypeTwo").attr("onclick", "this.checked=!this.checked");
         }else if(refundAmount > debtMoney && refundAmount <= debtMoney+ accountBalance){
         $("#payTypeOne").attr("checked", "true");
         $("#payTypeTwo").attr("checked", "true");
         $("#payTypeOne").attr("onclick", "this.checked=!this.checked");
         $("#payTypeTwo").attr("onclick", "this.checked=!this.checked");
         }*/

        if ($("#setPass_btn").length > 0) {
            var d = null;
            seajs.use(["dialog"], function (Dialog) {
                d = new Dialog({
                    trigger: "#setPass_btn",
                    content: "#setPassword_box",
                    width: 376
                }).before("show", function () {
                               resetValidatorError("#setPass_Form");
                           });
            });

            $("#setPassword_box .cancel_btn").on("click", function () {
                d.hide();
            });

            baseFormValidator({
                selector: "#setPass_Form",
                isAjax: true,
                addElemFn: function (Core, Validator) {
                    Validator.addRule("specialPassword", function (options) {
                        var reg = /^(?:\d*|[a-zA-Z]*|[\x00-\x2f\x3a-\x40\x5b-\x60\x7b-\x7f]*)$/;
                        var str = options.element.val();
                        if (reg.test(str)) {
                            return false;
                        } else {
                            return true;
                        }
                    }, "密码不能全为数字或字母");
                    Core.addItem({
                        element: '#payWord',
                        required: true,
                        rule: 'minlength{"min":8} maxlength{"max":20} specialPassword',
                        display: '新密码'
                    })
                },
                beforeSubmitFn: function () {
                    //alert(1);
                    $.ajax({
                        url: '${ctx}/brand/createPayword',
                        type: 'post',
                        data: $("#setPass_Form").serialize(),
                        dataType: 'json',
                        success: function (jsonMessage) {
                            if (jsonMessage.code == 126000) {
                                remind("success", "提交成功", function () {
                                    $("#order_payment")[0].submit();
                                });
                            } else {
                                remind("error", jsonMessage.message);
                            }
                        }
                    });
                }
            })

        }

        $("#agreeRefund").on('click', function () {
            /*if(refundAmount > debtMoney + accountBalance){
             remind("error", "退款金额大于欠付款和账号余额之和");
             return;
             }*/
            var url = "${ctx}/brand/refund/factory/agreeReturn";
            $.ajax({
                type: "post",
                dataType: 'json',
                url: url,
                data: $("#subPass_Form").serialize(),
                //contentType : "application/json;charset=UTF-8",
                success: function (jsonMessage) {
                    if (jsonMessage.code == 126000) {
                        remind("success", "提交成功", function () {
                            window.location.replace("${ctx}/brand/refund/factory/view/" +${dealerRefund.refundId})
                        });
                    } else {
                        remind("error", jsonMessage.message);
                    }
                }
            });
        });
        $(".c-r.time").cutDown(function (dhms) {
            return dhms.d + "天" + dhms.h + "时" + dhms.m + "分" + dhms.s + "秒"
        });
    });
</script>
</body>
</html>