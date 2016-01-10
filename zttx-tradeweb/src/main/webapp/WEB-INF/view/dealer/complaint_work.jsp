<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-投诉管理-客户介入处理中</title>
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
                
                     <jsp:include page="${ctx}/common/menuInfo/sidemenu">
					<jsp:param value="115" name="openId"/>
				</jsp:include>
                     
               
                <!--主体内容-->
                <div class="main-right">
                    <!--提示框-->
                    <!--<div class="main-grid mb10">
                        <div class="alert c-w warning">
                            <b class="icon i-close">X</b><i class="icon"></i><a href="#">预警消息:您有一笔订单超过15天未付款! 2013-10-16</a>
                        </div>
                    </div>-->
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">

                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="${ctx}/dealer/dealerOrder/order" title="">投诉管理</a> > <span class="bb">客服介入处理中</span>
                            <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>

                        </div>
                    </div>
		    <div class="inner">
                    <div class="main-grid">
                        <div class="flow-steps mt15">
                            <ol class="mt10">
								<li class="done current-prev">等待客服介入</li>
								<li class="current">
								<span>客服介入处理中</span>
								</li>
								<li class="last">
								<span>投诉处理完毕</span>
								</li>
							</ol>
                        </div>
                    </div>
                    <div class="main-grid mb10">
                        <div class="refund_detail">
                            <div class="top clearfix">
                                <div class="left">
                                    <div class="refund_info">
                                        <h3>投诉信息</h3>
                                        <p>
                                            <label for="">投诉单号：</label><span class="num">${dealerComplaint.complaintNumberId }</span>
                                        </p>
                                        <p>
                                            <label for="">投诉理由：</label>
                                              <c:choose>
												<c:when test="${dealerComplaint.complaintCause == 1 }"><span>未按约定时间发货</span></c:when>
												<c:when test="${dealerComplaint.complaintCause == 2 }"><span>未按成交价格进行交易</span></c:when>
												<c:when test="${dealerComplaint.complaintCause == 3 }"><span>承诺的没做到</span></c:when>
												<c:when test="${dealerComplaint.complaintCause == 4 }"><span>违反交易流程</span></c:when>
												<c:otherwise><span>其它原因</span></c:otherwise>
											</c:choose>
                                        </p>
                                    </div>
                                    <div class="order_info">
                                        <h3>订单信息</h3>
                                        <p>
                                          <label for="">订单号：</label><a href="${ctx}/dealer/dealerOrder/orderDetail/${dealerOrder.refrenceId}"><span class="num">${dealerOrder.orderId }</span></a>
                                       </p>
                                        <p>
                                            <label for="">订货日期：</label>${fns:getStringTime(dealerOrder.createTime,"yyyy-MM-dd HH:mm:ss")}
                                        </p>
                                        <p>
                                            <label for="">订货数量：</label>${dealerOrder.productCount }
                                        </p>
                                        <p>
                                            <label for="">订货金额：</label><strong class="price">${dealerOrder.productPrice+dealerOrder.adjustPrice}</strong>元
                                        </p>
                                        <p>
                                            <label for="">物流费用：</label>${dealerOrder.adjustFreight==null?'未设定':dealerOrder.adjustFreight }${dealerOrder.adjustFreight!=null?"元":""}
                                        </p>
                                        <p>
                                            <label for="">品牌商：</label>${dealerOrder.brandName }
                                        </p>
                                    </div>
                                </div>
                                <div class="detail_box">
                                    <div class="handle_refund pt40">
										<div>
											<i class="icon i-clock-big"></i>
										</div>
										<h3>客服介入处理中</h3>
										<p class="explain">客服人员正在处理，请耐心等待...</p>
									</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	</div>
        <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script>
    </script>
   
</body>
</html>
