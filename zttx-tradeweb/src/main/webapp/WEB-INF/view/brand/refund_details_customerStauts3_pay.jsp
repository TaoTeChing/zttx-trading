<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="com.zttx.web.consts.ZttxConst" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-交易管理-采购的订单-退款详情</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/refund_detail.css"/>
</head>
<body>
	<jsp:include page="${ctx}/brand/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/brand/brandmenu"/>
<div class="main_con">
    <div class="bread_nav">
        <div class="fl">
            <a class="link" href="${ctx}/brand/center">管理中心</a>
            <span class="arrow">&gt;&gt;</span>
            <a class="link" href="${ctx}/brand/order/purorder">交易管理</a>
            <span class="arrow">&gt;</span>
            <a class="link" href="${ctx}/brand/order/purorder">采购的订单</a>
            <span class="arrow">&gt;</span>
            <span class="current">退款详情</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
        </div>
    </div>
<div class="inner">
<!-- 内容都放这个地方  -->
    <!-- 步骤条 -->
    <div class="v2-steps v2-3steps">
        <span class="text1">申请退款</span>
        <span class="text2">品牌商处理退款申请</span>
        <span class="text3 current">退款完成</span>
        <div class="steps v2-steps-3"></div>
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
                <div class="order_info">
                    <h3>订单信息</h3>
                    <p>
                        <label for="">订单号：</label><a href="${ctx}/brand/order/view/${dealerOrder.refrenceId}" target="_blank" class="link">
                                                <span class="num">${dealerOrder.orderId}</span>
                                            </a>
                    </p>
                    <p>
                        <label for="">订货日期：</label>${fns:getStringTime(dealerOrder.createTime,"yyyy-MM-dd") }
                    </p>
                    <p>
                        <label for=""> 订货数量：</label>${dealerOrder.productCount}
                    </p>
                    <p>
                        <label for="">订货金额：</label><strong class="price">${dealerOrder.payment} </strong>元
                    </p>
                    <p>
                        <label for="">物流费用：</label>${dealerOrder.adjustFreight}元
                    </p>
                    <p>
                        <label for="">终端商：</label>${dealerOrder.dealerName}
                    </p>
                </div>
            </div>
           <form:form action="" id="subPass_Form">
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
                <div class="status_contain">
                    <i class="iconfont agree_icon">&#xe628;</i>
                    <div class="info">
                        <h3>纠纷处理中…</h3>

                        <p class="explain mt10">${dealerRefund.serProResult}</p>

                        <p class="explain mt10">请在<strong class="c-r time" data-endtime="${endTime}"  style=" color:red"></strong>之内将${dealerRefund.refundAmount}元货款打到终端商的账户中</p>

                        <div class="item mt15">
                        <input type="hidden" id="refrenceId"
											value="${dealerRefund.refrenceId}"  name="refrenceId"/>
                            <c:if test="${hasBrandPayword==true}"><label style="vertical-align: middle" for=""  >平台支付密码：</label>
                                        <input autocomplete="off" style="width: 230px;" type="password" class="ui-input" id="pwd" name="payWord"/>
                                        <div style="line-height:28px; margin-left:90px;">此密码为8637品牌超级代理平台支付密码</div></c:if>
                                        <c:if test="${hasBrandPayword==false}">
                                        <!-- <button style="width: auto;" id="setPass_btn" class="simple_button">请先设置支付密码</button> -->
                                        <a href="<%=ZttxConst.PAYAPI_WEBURL_RESETPWD%>" class="simple_button" style="width: auto;" target="_blank">请先设置支付密码</a>
                                        <span style="line-height: 28px;">下次支付需要您输入支付密码，请牢记</span></c:if>
                        </div>
                        <div class="operation mt15" style="margin-left:90px;">
                            <c:if test="${hasBrandPayword==true}"><a class="ui_button ui_button_morange"
											 id="agreeRefund">确认</a></c:if>
                            <a class="ui_button ui_button_mgray ml5" href="${ctx}/brand/refund/view/${dealerRefund.orderNumber}">取消</a>
                        </div>
                    </div>
                </div>
            </div>
            </form:form>
        </div>
        <div class="bottom">
            
            <div class="info">
             
            </div>
             <!-- 操作记录详情    start -->
                <jsp:include page="/WEB-INF/view/dealer/refund_refReply.jsp" />
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
                    <input style="width: 200px;" data-display="支付密码" class="ui-input"  id="payWord" name="payWord"  type="password" />
                </div>
                <div class="ui-form-item">
                    <label class="ui-label" for="">重复支付密码</label>
                    <input style="width: 200px;" required data-errormessage-required="请再重复输入一遍密码，不能留空" data-rule="confirmation{target: '#payWord',name:'第二遍'}" data-display="第一遍" class="ui-input" type="password" />
                    <div class="ui-form-explain">下次支付需要您输入支付密码，请牢记</div>
                </div>
                <div class="ui-form-item">
                    <button class="simple_button confirm_btn" type="submit">确定</button>
                    <button class="simple_button cancel_btn ml10" type="button">取消</button>
                </div>
            </form:form>
        </div>
    </div>
<%@ include file="bottom.jsp" %>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
	<script>
		$(function() {
		 if($("#setPass_btn").length > 0){

                var d = null;

                seajs.use(["dialog"],function(Dialog){

                    d = new Dialog({
                        trigger: "#setPass_btn",
                        content: "#setPassword_box",
                        width: 376
                    }).before("show",function(){
                        resetValidatorError("#setPass_Form");
                    });

                });

                $("#setPassword_box .cancel_btn").on("click",function(){
                    d.hide();
                });

                baseFormValidator({
                    selector: "#setPass_Form",
                    isAjax: true,
                    addElemFn: function(Core, Validator){
                        Validator.addRule("specialPassword",function(options){
                            var reg = /^(?:\d*|[a-zA-Z]*|[\x00-\x2f\x3a-\x40\x5b-\x60\x7b-\x7f]*)$/;
                            var str = options.element.val();
                            if(reg.test(str)){
                                return false;
                            }else{
                                return true;
                            }
                        },"密码不能全为数字或字母");
                        Core.addItem({
                            element: '#payWord',
                            required: true,
                            rule: 'minlength{"min":8} maxlength{"max":20} specialPassword',
                            display: '新密码'
                        })
                    },
                    beforeSubmitFn: function(){
                        //alert(1);
                         $.ajax({
				 		  url: '${ctx}/brand/createPayword',
				          type: 'post',
				          data: $("#setPass_Form").serialize(),
				          dataType: 'json',
				          success : function(jsonMessage) {
						if (jsonMessage.code == 126000) {
								remind("success","提交成功",function(){
								window.location.replace("${ctx}/brand/refund/view/customerStauts3_pay/"+${dealerRefund.orderNumber})
							});
						} else {
								remind("error",jsonMessage.message);
						}
					}
			    	});
                    }
                })

            }
		
			$("#agreeRefund").on('click', function() {
				var url = "${ctx}/brand/refund/agreeReturn";
				
				var refrenceId = $("#refrenceId").val();
				
				var pwd = $("#pwd").val();
				$.ajax({
					type : "post",
					dataType : 'json',
					url : url,
					data: $("#subPass_Form").serialize(),
					//contentType : "application/json;charset=UTF-8",
					success : function(jsonMessage) {
						if (jsonMessage.code == 126000) {
						remind("success","提交成功",function(){
							window.location.replace("${ctx}/brand/refund/view/"+${dealerRefund.orderNumber})
							});
							

						} else {
						remind("error",jsonMessage.message);
						}
					}

				});
			});
			$(".c-r.time").cutDown(function(dhms){
		        return dhms.d + "天"+ dhms.h+"时"+dhms.m+"分"+dhms.s+"秒"
		    });

		});
	</script>
</body>
</html>