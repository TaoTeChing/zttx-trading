<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zttx.web.consts.ZttxConst" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-品牌商处理退款申请</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/alreas.css" rel="stylesheet" />
</head>
<body>
    <!--完成-->
    <div class="container">
		<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->
				<jsp:include page="${ctx}/common/menuInfo/sidemenu" >
						<jsp:param value="2" name="openId"/>
				</jsp:include>
                <!--主体内容-->
                <div class="main-right">
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <c:choose>
                                <c:when test="${dealerRefund != null && dealerRefund.orderId != null && dealerRefund.orderId != ''}">
                                    <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a
                                           href="${ctx}/dealer/dealerOrder/order" title="">已进货的订单</a> > <span
                                           class="bb">品牌商处理退款申请</span>
                                    <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
                                </c:when>
                                <c:otherwise>
                                    <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a
                                           href="${ctx}/dealer/dealerFinancial/financial">品牌财务账</a> > <span class="bb">品牌商处理退款申请</span>
                                    <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="inner">
                                <c:if test="${dealerRefund.refundState==1}">
                                	<c:if test="${dealerRefund.needRefund==false }">
                                   <div class="flow-steps mt15">
			                            <ol class="mt10">
			                                <li class="done current-prev">申请退款</li>
			                                <li class="current"><span>等待品牌商处理退款申请</span></li>
			                                <li class="last"><span>退款完成</span></li>
			                            </ol>
			                        </div>
			                        </c:if>
			                        <c:if test="${dealerRefund.needRefund==true }">
			                            <div class="flow-steps flow-4 mt15">
			                            <ol class="mt10">
			                                <li class="done current-prev">申请退款</li>
			                                <li class="current"><span>品牌商处理退款申请</span></li>
			                                <li class=""><span>退货给品牌商</span></li>
			                                <li class="last"><span>退款完成</span></li>
			                            </ol>
			                        	</div>
			                        </c:if>
                                </c:if>
                                <c:if test="${dealerRefund.refundState==2}">
                                	<div class="flow-steps flow-4 mt15">
			                            <ol class="mt10">
			                                <li class="done">申请退款</li>
			                                <li class="done current-prev"><span>品牌商处理退款申请</span></li>
			                                <li class="current"><span>退货给品牌商</span></li>
			                                <li class="last"><span>退款完成</span></li>
			                            </ol>
			                        </div>
                                </c:if>
                                <c:if test="${dealerRefund.refundState==3}">
                                <div class="flow-steps flow-4 mt15">
		                            <ol class="mt10">
		                                <li class="done">申请退款</li>
		                                <li class="done current-prev"><span>品牌商处理退款申请</span></li>
		                                <li class="current"><span>退货给品牌商</span></li>
		                                <li class="last"><span>退款完成</span></li>
		                            </ol>
		                        </div>
		                        </c:if>
                        
                                <c:if test="${dealerRefund.refundState==4}">
                                	 <div class="flow-steps mt15">
				                        <ol class="mt10">
				                            <li class="done">申请退款</li>
				                            <li class="done current-prev"><span>品牌商处理退款申请</span></li>
				                            <li class="last-current"><span>退款失败</span></li>
				                        </ol>
				                    </div>
                                </c:if>
                                <c:if test="${dealerRefund.refundState==5}">
                                	<div class="flow-steps flow-4 mt15">
		                            <ol class="mt10">
		                                <li class="done">申请退款</li>
		                                <li class="done"><span>品牌商处理退款申请</span></li>
		                                <li class="done current-prev" ><span>退货给品牌商</span></li>
		                                <li class="last-current"><span>退款失败</span></li>
		                            </ol>
		                        </div>
                                </c:if>
                                <c:if test="${dealerRefund.refundState==9}">
                                	<div class="flow-steps mt15">
				                        <ol class="mt10">
				                            <li class="done">申请退款</li>
				                            <li class="done current-prev"><span>品牌商处理退款申请</span></li>
				                            <li class="last-current"><span>退款完成</span></li>
				                        </ol>
				                    </div>
                                </c:if>
                                 <c:if test="${dealerRefund.refundState==10}">
	                                <div class="flow-steps flow-4 mt15">
			                            <ol class="mt10">
			                                <li class="done">申请退款</li>
			                                <li class="done"><span>品牌商处理退款申请</span></li>
			                                <li class="done current-prev"><span>退货给品牌商</span></li>
			                                <li class="last-current"><span>退款完成</span></li>
			                            </ol>
			                    </div>
                                </c:if>
                    <div class="main-grid mb10">
                        <div class="refund_detail">
                            <div class="top clearfix">
                                <div class="left">
                                    <div class="refund_info">
                                        <h3>退款信息  </h3>
                                        <p>
                                            <label for="">退款单号：</label><span class="num">${dealerRefund.refundId}</span>
                                        </p>
                                        <p>
                                            <label for="">退款类型：</label>${dealerRefund.needRefund==true ? "退款退货":"仅退款"}
                                        </p>
                                        <p>
                                            <label for="">退款金额：</label><strong class="price">${dealerRefund.refundAmount}</strong>元
                                        </p>
                                    </div>
                                    <div class="order_info">
                                        <h3>订单信息</h3>
										<p>
										    <label for="">订单号：</label>
										    <a href="${ctx}/dealer/dealerOrder/orderDetail/${dealerOrder.refrenceId}" target="_blank" class="link">
										    	<span class="num">${dealerOrder.orderId}</span>
									    	</a>
										</p>
										<p>
										    <label for="">订货日期：</label>${fns:getTimeFromLong(dealerOrder.createTime,"yyyy-MM-dd HH:mm:ss")}
										</p>
										<p>
										    <label for="">订货数量：</label>${dealerOrder.productCount}
										</p>
										<p>
										    <label for="">订货金额：</label><strong class="price">${dealerOrder.productPrice+dealerOrder.adjustPrice}</strong>元
										</p>
										<p>
										    <label for="">物流费用：</label>${dealerOrder.adjustFreight}元
										</p>
										<p>
                                            <label class="fl">品牌商：</label><span class="fl" style="width: 154px;">${dealerOrder.brandName}</span>
										</p>
									</div>
                                </div>
                                
                                <div id="xx" class="detail_box">
                                <c:if test="${empty dealerRefund}">
                                	<div class="handle_refund pt40" id="wait_result">
	                                        <div><i class="icon i-close-big"></i></div>
	                                        <h3>退款记录不存在,请联系客服核查!</h3>
	                                    </div>
                                </c:if>
                                	<%-- 已要求客服介入,等待客服介入 --%>
                                	<c:if test="${dealerRefund.cusJoin==true&&dealerRefund.serProStatus!=6}">
                                		<div class="main-grid mb10 alerts">
											<div class="alert c-w error">
											   <c:if test="${dealerRefund.serProStatus==1}">
												<i class="iconfont c-w mr5">&#xe626;</i>客服人员正在介入，请耐心等待...
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
										</div>
                                	</c:if>
                                	
									<%-- 申请退款等待处理 --%>
									<c:if test="${dealerRefund.refundState==1}">
										<div class="handle_refund pt40" id="wait_result">
	                                        <div><i class="icon i-clock-big"></i></div>
	                                        <h3>等待品牌商处理退款申请</h3>
	                                        <div class="explain">
	                                            		品牌商还有<span class="c-r time" data-endtime="${endTime}"></span>来处理您的退款申请
	                                        </div>
	                                        <div class="detail">
	                                       		<a href="javascript:void(0);" class="ui-button ui-button-lred abort">撤销退款申请</a>
	                                        </div>
	                                    </div>
									</c:if>
									<c:if test="${dealerRefund.refundState==2}">
									<div style="margin-left: 40px;" class="handle_refund">
                                        <h3>请退货并填写物流信息</h3>
                                        <p class="explain">您还有 <strong class="c-r time" data-endtime="${endTime}"></strong>来退货</p>
                                        <p class="fs14 bb c-h yahei lh2">未经品牌商同意,请勿使用到付</p>
                                        <p class="mt10">退货地址：${dealerRefund.brandRecAddr}</p>
                                        <form:form style="position:relative;left: -170px;" action="/dealer/refund/returnDeliver" class="ui-form mt20" data-widget="validator">
                                        <input type="hidden" name="refrenceId" value="${dealerRefund.refrenceId}" />
                                        <input type="hidden" name="orderNumber" value="${dealerRefund.orderNumber}" />
                                            <div class="ui-form-item">
                                                <span class="ui-form-text bb c-h">请填写物流信息</span>
                                            </div>
                                            <div class="ui-form-item">
                                                <label>物流公司：</label><input required data-display="物流公司" name="logisticsName" maxlength="16" style="width:300px" class="ui-input">
                                            </div>
                                            <div class="ui-form-item">
                                                <label>运 单 号：</label><input required data-display="运单号" name="transNum"  maxlength="25"  style="width:300px" class="ui-input">
                                            </div>
                                            <div class="ui-form-item">
                                                <input type="submit" value="提交退货信息" class="ui-button ui-button-mred">
                                            </div>
                                        </form:form>
                                    </div>
                                    </c:if>
                                    <c:if test="${dealerRefund.refundState==3}">
                                    <div class="handle_refund pt40">
                                        <div><i class="icon i-clock-big"></i></div>
                                        <h3>等待品牌商确认收货</h3>
                                        <p class="explain">品牌商还有<strong class="c-r time" data-endtime="${endTime}"></strong>来确认收货</p>
                                        <div class="explain">
							                                            运送单号：<span class="c-r">${dealerRefund.transNum}</span></br>
							                                            物流公司：<span class="c-r">${dealerRefund.logisticsName}</span>
                                        </div>
                                        <div class="detail">
                                        </div>
                                    </div>
                                    </c:if>
                                    
									<c:if test="${dealerRefund.refundState==4}">
										<div class="handle_refund pt40">
		                                    <div><i class="icon i-right-32 no"></i></div>
		                                    <h3>品牌商拒绝退款</h3>
		                                    <div class="explain">拒绝理由：${dealerRefund.brandMark}</div>
		                                    <div class="detail">
		                                        <c:if test="${dealerRefund.updateNum <3 }">
		                                          	  您可以：<a class="link" href="${ctx}/dealer/refund/choice?orderNumber=${dealerRefund.orderNumber}&needRefund=${dealerRefund.needRefund!=null&&dealerRefund.needRefund==true?'true':'false'}">修改退款申请</a> 
		                                        </c:if>
		                                         <c:if test="${dealerOrder.serProStatus ==null ||dealerOrder.serProStatus==4}">
		                                         	 <a class="link ml10" id="btn-call" href="#" tabindex="-1">要求8637品牌超级代理介入</a>
		                                         </c:if>
		                                    </div>
		                                </div>
									</c:if>
									
									<c:if test="${dealerRefund.refundState==5}">
									<div class="handle_refund pt40">
                                        <div><i class="icon i-right-32 no"></i></div>
                                        <h3>品牌商拒绝退款退货</h3>
                                        <div class="explain"> 拒绝理由：${dealerRefund.brandMark}</div>
                                        <div class="detail">
                                        <c:if test="${dealerRefund.updateNum <3 }">
                                          	  您可以：<a class="link" href="${ctx}/dealer/refund/choice?orderNumber=${dealerRefund.orderNumber}&needRefund=true">修改退款退货申请</a> 
                                        </c:if>	 
                                         <c:if test="${dealerOrder.serProStatus ==null||dealerOrder.serProStatus==4}">
                                          	<a class="link ml10" id="btn-call" href="#" tabindex="-1">要求8637品牌超级代理介入</a>
                                         </c:if>
                                        </div>
                                    </div>
                                    </c:if>
                                     <c:if test="${dealerRefund.refundState==6}">
                                     	<div class="handle_refund pt40">
	                                        <div><i class="icon i-close-big"></i></div>
	                                        ${dealerRefund.needRefund==true ? " <h3>退款退货关闭</h3>":" <h3>退款关闭</h3>"}
	                                        <div class="explain">${dealerRefund.remark}</div>
                                    	</div>
                                     </c:if>
                                    <c:if test="${dealerRefund.refundState==7}">
	                                     <div class="handle_refund pt40">
	                                        <div><i class="icon i-close-big"></i></div>
	                                        ${dealerRefund.needRefund==true ? " <h3>退款退货关闭</h3>":" <h3>退款关闭</h3>"}
	                                        <div class="explain">${dealerRefund.remark}</div>
                                    	</div>
                                     </c:if>
                                     <c:if test="${dealerRefund.refundState==9}">
                                     <div class="handle_refund pt40">
                                        <div><i class="icon i-right-32 yes"></i></div>
                                        <h3>退款成功</h3>
                                        <div class="explain">
                                         	   品牌商同意退款
                                        </div>
                                        <div class="explain">
                                            	退款金额：<span class="c-r">${dealerRefund.refundAmount}</span>元
                                        </div>
                                         <c:if test="${isCredit != null && isCredit}">
                                             <c:if test="${cashNeedRefund != null}">
                                                 <div class="explain" style="font-size: 10px">
                                                     <%--（其中授信退款:<span class="c-r">${dealerRefund.refundAmount-cashNeedRefund}</span>元）--%>
                                                     （其中授信退款:<span class="c-r"><fmt:formatNumber value="${dealerRefund.refundAmount-cashNeedRefund}" pattern="0.00"/></span>元）
                                                 </div>
                                             </c:if>
                                         </c:if>
                                        <div class="detail">
                                            <a href="<%=ZttxConst.PAYAPI_WEBURL_REFUND%>" target="_blank"  class="ui-button ui-button-lred">查看现款退款</a>
                                        </div>
                                    </div>
                                    </c:if>
                                    
                                     <c:if test="${dealerRefund.refundState==10}">
                                     <div class="handle_refund pt40">
                                        <div><i class="icon i-right-32 yes"></i></div>
                                        <h3>退款退货成功</h3>
                                        <div class="explain">品牌商同意退款</div>
                                        <div class="explain">
                                           	 退款金额：<span class="c-r">${dealerRefund.refundAmount}</span>元
                                        </div>
                                         <c:if test="${isCredit != null && isCredit}">
                                             <c:if test="${cashNeedRefund != null}">
                                                 <div class="explain" style="font-size: 10px">
                                                     （其中授信退款:<span
                                                            class="c-r"><fmt:formatNumber
                                                            value="${dealerRefund.refundAmount-cashNeedRefund}"
                                                            pattern="0.00"/></span>元）
                                                 </div>
                                             </c:if>
                                         </c:if>
                                         <div class="detail">
                                             <a href="<%=ZttxConst.PAYAPI_WEBURL_REFUND%>" target="_blank"
                                                class="ui-button ui-button-lred">查看现款退款</a>
                                         </div>
                                    </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                	<jsp:include page="/WEB-INF/view/dealer/refund_refReply.jsp" />
                </div>
            </div>
        </div>
        
    </div>
<jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    <div class="hide">
        <div id="close_refund">
            <div class="ui-head"><h3>撤销退款申请</h3></div>
            <p class="fs14 yahei" style="margin:20px;">撤销申请之后,您将不能再发起退款申请,是否继续? </p>
            <form:form action="${ctx}/dealer/refund/cancel" method="post">
            <input type="hidden" name="refrenceId" value="${dealerRefund.refrenceId}" />
            <input type="hidden" name="orderNumber" value="${dealerRefund.orderNumber}" />
	            <div class="ta-c mb10">
	                <button class="ui-button ui-button-morange" type="submit">确定</button><a class="ui-button ui-button-morange ml20 cancel">取消</a>
	            </div>
            </form:form>
        </div>
    </div>
    
    <div class="hide">
        <div id="zttxjr">
            <div class="ui-head">
                <h3>要求8637品牌超级代理介入</h3>
            </div>
            <form class="ui-form">
                <p class="fs14 yahei" style="margin: 15px;">
                    8637品牌超级代理客服将在${dictValue}个工作日内介入处理，<br />确定要求8637品牌超级代理介入吗?
                </p>
                <div class="ta-c mb10">
                    <a href="${ctx}/dealer/refund/appserjoin?refrenceId=${dealerRefund.refrenceId}&orderNumber=${dealerRefund.orderNumber}"
                        class="ui-button ui-button-morange">确定</a>
                    <a class="ui-button ui-button-morange ml20 cancel">取消</a>
                </div>
            </form>
        </div>
    </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script>
        seajs.use(["$", 'dialog'], function ($, Dialog) {
            var close_dlg = new Dialog({
                trigger: 'div.detail .abort',
                content: "#close_refund",
                width: 360
            });
            $(".cancel").click(function(){
            	close_dlg.hide();
	     	});
            
            var join_dlg = new Dialog({
                trigger: '#btn-call',
                content: "#zttxjr",
                width: 360
            });
            $(".cancel").click(function () {
            	join_dlg.hide();
            });
            
        });
     	
	    $(".time").cutDown(function(dhms){
	        return dhms.d + "天"+ dhms.h+"时"+dhms.m+"分"+dhms.s+"秒";
	    });
    </script>
</body>
</html>
