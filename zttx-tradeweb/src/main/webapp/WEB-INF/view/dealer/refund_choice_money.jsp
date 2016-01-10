<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-退款退货</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    
    <link href="${res}/styles/dealer/alreas.css" rel="stylesheet" />
    <link href="${res}/styles/common/validformStyle.css" rel="stylesheet" type="text/css" />
    <style>
    .payrecord {
	    border: 2px solid #FEBB75;
	    border-radius: 3px;
	    margin: 0 15px 10px 50px;
	    padding: 10px;
	}
	.payrecord strong{
		color: #FF0000;
		font-family: "Arial";
	}
	</style>
</head>
<body>
    <!--完成-->
    <div class="container">
        <!-- top(终端商管理中心) -->
		<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->
				 <jsp:include page="${ctx}/common/menuInfo/sidemenu"  >
						<jsp:param value="2" name="openId"/>
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
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="${ctx}/dealer/dealerOrder/order" title="">已进货的订单</a> > <span class="bb">退款申请</span>
                            <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    
					<div class="flow-steps mt15">
                        <ol>
                            <li class="current">申请退款</li>
                            <li><span>品牌商处理退款申请</span></li>
                            <li class="last"><span>退款完成</span></li>
                        </ol>
                    </div>
                    <!---- 退款/退货申请：main 栏目  ---->
                   <div class="main-grid mb10">
		<div class="refund_detail">
    	<div class="top clearfix">
<!---- 退款/退货申请：订单信息栏目  ---->
<div class="left">
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
        <label for="">订货金额：</label><strong
               class="price">${dealerOrder.productPrice + dealerOrder.adjustPrice}</strong>元
	</p>
	<p>
	    <label for="">物流费用：</label>${dealerOrder.adjustFreight}元
	</p>
	<p>
        <label class="fl">品牌商：</label><span class="fl" style="width: 154px;">${dealerOrder.brandName}</span>
	</p>
    </div>
</div> 

<!---- 退款/退货申请：申请退款信息栏目  ---->
<div class="detail_box clearfix">
    <!--选其一-->
    <div class="fl mt20" >
        <form:form id="refundApplyForm" class="ui-form" action="${ctx}/dealer/refund/apply/submit" method="post">
        	<input type="hidden" name="adjustFreight" value="${dealerOrder.adjustFreight}" /><!-- 运费 -->
        	<input type="hidden" name="orderNumber" value="${dealerOrder.orderId}" /><!-- 订单编号  -->
        	<input type="hidden" name="needRefund" value="${dealerRefund.needRefund}" /><!-- 是否退货 -->
        	<c:if test="${dealerRefund.updateNum != 0 && null != dealerRefund.updateNum}">
	            <div class="payrecord">
	        		<p>
	        			您还有 <strong>${3-dealerRefund.updateNum }</strong> 次修改退款申请的机会，请如实提交退款信息，您也可以要求客服介入
	        		</p>
	        	</div>
            </c:if>
            <div class="ui-form-item">
                <label class="ui-label">是否收到货：<span class="ui-form-required">*</span></label>
                <span class="ui-form-text">
                    <input class="ui-radio no-received" type="radio" name="received" data-tip="品牌商已发货，物流公司为【顺丰快递】，运单号【2154213】，联系品牌商"
                        ${true!=dealerRefund.received ? 'checked':''} /> <label>未收到</label>
                    <input class="ui-radio has-received" type="radio" name="received" ${true==dealerRefund.received ? 'checked':''}/> <label>已收到</label>
                </span>
            </div>
            <!--<div> 品牌商已发货，物流公司为【顺丰快递】，运单号【2154213】，联系品牌商(效果未完成) </div>-->
            <div class="ui-form-item" style="height: 35px;">
                <label class="ui-label">退款原因：<span class="ui-form-required">*</span></label>
                <select class="ui-select" style="padding:5px;width:120px;" name="refundReason" dataType="*">
                    <option value="" ${dealerRefund.refundReason=='' ? 'selected':''}>请选择</option>
                    <option value="退运费" ${dealerRefund.refundReason=='退运费' ? 'selected':''}>退运费</option>
                    <option value="收到商品破损" ${dealerRefund.refundReason=='收到商品破损' ? 'selected':''}>收到商品破损</option>
                    <option value="商品错发/漏发" ${dealerRefund.refundReason=='商品错发/漏发' ? 'selected':''}>商品错发/漏发</option>
                    <option value="商品需要维修" ${dealerRefund.refundReason=='商品需要维修' ? 'selected':''}>商品需要维修</option>
                    <option value="发票问题" ${dealerRefund.refundReason=='发票问题' ? 'selected':''}>发票问题</option>
                    <option value="收到商品与描述不符" ${dealerRefund.refundReason=='收到商品与描述不符' ? 'selected':''}>收到商品与描述不符</option>
                    <option value="商品质量问题" ${dealerRefund.refundReason=='商品质量问题' ? 'selected':''}>商品质量问题</option>
                    <option value="未收到货" ${dealerRefund.refundReason=='未收到货' ? 'selected':''}>未收到货</option>
                    <option value="未按约定时间发货" ${dealerRefund.refundReason=='未按约定时间发货' ? 'selected':''}>未按约定时间发货</option>
                    <option value="收到假货" ${dealerRefund.refundReason=='收到假货' ? 'selected':''}>收到假货</option>
                </select>
            </div>
            <div class="ui-form-item">
                <label class="ui-label">退款金额：<span class="ui-form-required">*</span></label>
                <input class="ui-input" style="width:200px" name="refundAmount" ${dealerOrder.orderStatus==2?'readonly':''} value="${dealerOrder.productPrice + dealerOrder.adjustPrice + dealerOrder.adjustFreight}" dataType="n|money"/>
                <span class="ui-form-explain">不超过订货金额和物流费用的总和 </span>
            </div>
            <div class="ui-form-item" style="height: 75px;">
                <label class="ui-label">退款说明：<span class="ui-form-required">*</span></label>
                <textarea class="ui-textarea" style="width:500px;height:60px;resize: none;" placeholder="描述您退款的详细理由" dataType="*"
                	name="refundMark" >${dealerRefund.refundMark}</textarea>
             </div>
             <div class="ui-form-item">
                 <label class="ui-label">上传凭证：</label>
                 <ul id="certificate_list" class="inline-float certificate_box">
                 
                    <li id="dealerRefAttachtImg" class="add_certificate">
                        <div class="file_wrap">
                            <i class="icon-fileupload"></i>
                            <input class="input_file" type="file" name="photo" id="attachtNames"/>
                            <input type="hidden" name="_attachtNames"/>
                        </div>
                    </li>
                </ul>
             </div>
             <div class="ui-form-item">
                 <button id="refundApplyFormSubmit" class="ui-button ui-button-lred">提交申请</button>
             </div>
        </form:form>
     </div>
</div>
        </div>
    </div>
				<!-- 操作记录详情    start -->
                <jsp:include page="/WEB-INF/view/dealer/refund_refReply.jsp" />
                <!-- 操作记录详情    end   -->
</div>

                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
    <div class="hide">
        <div id="close_refund">
            <div class="ui-head">
                <h3>撤销退款申请</h3>
            </div>

            <p class="fs14 yahei" style="margin:20px;">
                确定撤销退款申请吗?
            </p>
            <div class="ta-c mb10">
                <a href="Purchases-refund-2-close.jsp" class="ui-button ui-button-morange">确定</a><a class="ui-button ui-button-morange ml20">取消</a>
            </div>

        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
<script src="${src}/plugin/Validform_v5.3.2_min.js"></script>
<c:set var="SUCCESS" value="<%=com.zttx.web.consts.CommonConst.SUCCESS.code%>"></c:set>
    <script type="text/javascript">
	$(function(){
		$('input[name="photo"]').bind('change', function(){uploadImage($(this).attr('id'));});
	
		$(".certificate_box").on("mouseenter mouseleave", ".item",function(ev) {
			if (ev.type == "mouseenter") {
				$(this).find(".close").show();
			} else if (ev.type == "mouseleave") {
				$(this).find(".close").hide();
			}
		});
		
		$(".certificate_box").on("click", ".close", function(ev) {
			var confirm = window.confirm("确定要删除该照片么？");
			if (!confirm) {
				return;
			}
			$(this).parents(".item").remove();
		});
		
		$("#refundApplyForm").Validform({
			tiptype : 4,
			datatype:{
				// 金额的验证规则
				"money":function(gets,obj,curform,datatype){
					var reg = /^(\d*)(\.\d{1,2})$/;
					if (reg.test(gets)) {
						return true;
					} else {
						return false;
					}
				}
				
			},
			btnSubmit:"#refundApplyFormSubmit",
			postonce : true,
			ajaxPost : true,
			callback : function(json) {
				if(json.code==parseInt('${SUCCESS}')){
					window.location.replace("${ctx}/dealer/refund/details?orderNumber=${dealerOrder.orderId}"); 
				} else {
					remind('error',json.message);
				}
			}
		});
		
		function showImage(uploadId, url){
    		var html = '<li class="item">' + 
                        '<div class="img_contain">' +
                            '<img src="${res}'+ url +'" style="width:100%;height:100%;" alt=""><span></span>' +
                            '<a href="javascript:;" class="iconfont close" style="display: none;">&#xe612;</a>' +
                            '<input type="hidden" name="'+ uploadId +'" value="${res}' + url + '" />' +
                        '</div>' +
                    '</li>';
            $(html).insertBefore("#dealerRefAttachtImg");
            var len = $("#certificate_list li").size();
            if(len > 8){
                $("#dealerRefAttachtImg").hide();
            }
        	$("span.Validform_checktip.Validform_wrong").html('');
    	}
    	
			function uploadImage(uploadId){
				 dialogLoading(function (dialog) {
					 
				
			    seajs.use(["$","ajaxFileUpload"],function($){
			        $.ajaxFileUpload({
			            url: '${ctx}/common/showImg',
			            secureuri: false,
			            fileElementId: uploadId,
			            dataType: 'json',
			            success: function(data)
			            {
			                //发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
			                $('#' + uploadId).bind('change', function(){
			                    uploadImage(uploadId);
			                });
			                if(data.code == 126000)
			                {
			                	 showImage(uploadId,data.message);
			                }
			                dialog.hide();
			            }
			        });
			    });
				 })
			}
        var received = {
            init:function(){
                this.lookReceived();//初始化“退款金额”输入框的readonly状态
                this.clickReceived();//点击单选按钮的时候，改变“退款金额”输入框的readonly状态
            },
            lookReceived:function(){
                if($(".has-received").prop("checked")){
                    $("input[name=refundAmount]").removeAttr("readonly");
                }
                if($(".no-received").prop("checked")){
                    $("input[name=refundAmount]").attr("readonly","readonly");
                }
            },
            clickReceived:function(){
                $("input[name=received]").click(function(){
                    received.lookReceived();
                });
            }
        }
        received.init();
    });

</script>
</body>
</html>
