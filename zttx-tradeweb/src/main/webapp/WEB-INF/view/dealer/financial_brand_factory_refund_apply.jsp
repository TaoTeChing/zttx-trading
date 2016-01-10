<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-申请退款</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/factory.css" rel="stylesheet" />
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
    <div class="em100">
        <div class="main clearfix pr">
            <!--侧边导航-->
            <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
            <!--主体内容-->
            <div class="main-right">
                <!--面包屑-->
                <div class="main-grid mb10">
                    <div class="panel-crumbs">
                        <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a
                            href="${ctx}/dealer/dealerFinancial/financial">品牌财务账</a> > <span class="bb">申请退款</span>
                        <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <div class="inner pt20">
                    <form:form class="ui-form apply-form clearfix" data-widget="validator" onsubmit="return false;">
                    	<input class="ui-input" type="hidden" name="brandId" value="${userInfo.refrenceId }" />
                    	<input type="hidden" name="refrenceId" value="${dealerRefund.refrenceId}" /><!-- 订单编号  -->
                    	<input type="hidden" name="refundId" value="${dealerRefund.refundId}" />
                        <div class="ui-form-item">
                            <label class="ui-label">品牌商名称：&nbsp;</label>
                            <input class="ui-input" type="text" disabled value="${userInfo.brandName}" />
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">联系人名称：&nbsp;</label>
                            <input class="ui-input" type="text" disabled value="${userInfo.connectUserName }" />
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">联系方式：&nbsp;</label>
                            <input class="ui-input" type="text" disabled value="${userInfo.userMobile }" />
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">所在地：&nbsp;</label>
                            <input class="ui-input" type="text" disabled value="${fns:getFullAreaNameByNo(userInfo.areaNo)}" />
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">
                                退款类型：&nbsp;
                            </label>
                            <div class="inline-block mt10">
                                <label class="mr10"><input type="radio" class="ui-checkbox mr5 js-refund-type" name="needRefund"  ${dealerRefund.needRefund ? '':'checked'} value="0"/>仅退款不退货</label>
                                <label><input type="radio" class="ui-checkbox mr5 js-refund-type" name="needRefund"  ${dealerRefund.needRefund ? 'checked':''} value="1"/>退款退货</label>
                            </div>
                        </div>
                        <div class="ui-form-item" id="haveReceived">
                            <label class="ui-label">
                                是否收到货：<span class="c-r">*</span>
                            </label>
                            <div class="inline-block mt10">
                                <label class="mr10"><input type="radio" class="ui-checkbox mr5 js-have-received" name="received" value="0" checked="checked"/>未收到</label>
                                <label><input type="radio" class="ui-checkbox mr5 js-have-received" name="received" value="1"/>已收到</label>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">
                                退款原因：<span class="c-r">*</span>
                            </label>
                            <select name="refundReason" id="" class="ui-select" required data-errormessage="请选择退款原因" style="width: 145px;height: 34px;">
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
                            <label class="ui-label">退款金额：<span class="c-r">*</span></label>
                            <input class="ui-input js-price" type="text" value="${dealerRefund.refundAmount}" required data-display="退款金额" data-rule="max{'max':<fmt:formatNumber value="${maxRefundAmount}" type="currency" pattern="0.00" />} min{'min':0.1}" name='refundAmount'/>
                            退款金额不得超过<strong style="color: #c00;"><fmt:formatNumber value="${maxRefundAmount}" type="currency" pattern="0.00" /></strong>元
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">退款说明：<span class="c-r">*</span></label>
                            <textarea name="refundMark" class="ui-textarea" required data-display="退款说明" style="width: 500px;height: 70px;">${dealerRefund.refundMark}</textarea>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">上传凭证：</label>
                            <div class="inline-block" style="vertical-align: middle;">
                                <ul class="inline-float certificate_box">
                                <c:forEach items="${dealerRefAttachts}" var="dealerRefAttacht">
			                     	<li class="item">
				                        <div class="img_contain">
				                            <img src="${res}${dealerRefAttacht.domainName}${dealerRefAttacht.attachtName}" style="width:100px;height:100px;" alt=""><span></span>
				                        </div> 
				                    </li>
			                 	</c:forEach>
                                    <li class="add_certificate">
                                        <div class="file_wrap">
                                            <i class="iconfont">&#xe611;</i>
                                            <input class="input_file" type="file" name="photo" id="attachtNames"/>
                            				<input type="hidden" name="_attachtNames"/>
                                        </div>
                                    </li>
                                </ul>
                                <p class="explain" style="padding-top: 10px;color: #999;">（上传凭证最多上传 5 张图片,仅支持JPG、GIF、PNG图片文件，且图片小于2M）</p>
                                <div class="imgUploadTip2" style="color: #FF5243; display: none;">*请上传图片</div>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <div class="inline-block">
                                <button type="submit" class="ui-button ui-button-lred">提交申请</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="${ctx}/WEB-INF/view/dealer/import_javascript.jsp" />
<script>
    $(function () {
        //退款类型选中切换状态
        function refundTypeChange(Core, Validator){
            if($(".js-refund-type").eq(0).prop("checked")){
                $("#haveReceived").show();
                Core.addItem({
                    element: '.js-have-received',
                    required: true,
                    errormessage:"请选择是否收到货"
                });
            }else{
                $("#haveReceived").hide();
                Core.removeItem(".js-have-received");
            }
        }

        function showImage(uploadId ,url){
            var html = '<li class="item">' +
                    '<div class="img_contain">' +
                    '<input type="hidden" name="' + uploadId + '" value="' + url + '" />'+
                    '<img src="${res}'+ url +'" alt="" style="width:100%;height:100%;"/><span></span>' +
                    '<a href="javascript:;" class="iconfont close" style="display: inline; "></a>' +
                    '</div>' +
                    '</li>';
            var _parents = $('#'+uploadId).parents(".add_certificate");
            _parents.before(html);
            countImgNum();
        }

        function countImgNum(){
            var _count = $(".certificate_box li").length;
            if(_count == 6){
                $(".add_certificate").hide();
            }
            if(_count < 6){
                $(".add_certificate").show();
            }
            if(_count > 1){
                $(".imgUploadTip2").hide();//隐藏提示
            }
        }

        function uploadImage(uploadId){
            dialogLoading(function (d) {
                seajs.use(["$", "ajaxFileUpload"], function ($) {
                    $.ajaxFileUpload({
                        url: '/common/showImg',
                        secureuri: false,
                        fileElementId: uploadId,
                        dataType: 'json',
                        success: function (data) {
                            //发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                            $('#' + uploadId).bind('change', function () {
                                uploadImage(uploadId);
                            });

                            if (data.code == 126000) {
                                showImage(uploadId, data.message);
                            }
                            d.destroy();
                        }
                    });
                });
            });
        }

        $("#attachtNames").bind("change",function(){
            uploadImage($(this).attr("id"));
        });

        $(".certificate_box").on("click",".close",function(){
            var _parents = $(this).parents(".item");
            _parents.remove();
            countImgNum();
        });


        baseFormValidator({
            selector:".apply-form",
            isAjax:true,
            addElemFn:function(Core, Validator){
                Core.addItem({
                    element: '.js-have-received',
                    required: true,
                    errormessage:"请选择是否收到货"
                });
                //初始化显示状态
                refundTypeChange(Core, Validator);
                $(".js-refund-type").click(function(){
                    refundTypeChange(Core, Validator);
                });
            },
            beforeSubmitFn:function(){
                $.post('${ctx}/dealer/dealerFinancial/factory/refund/submit',$('.apply-form').serialize(),function(data){
                	if(data.code==126000){
    					window.location.href="${ctx}/dealer/dealerFinancial/refund?brandId=${userInfo.refrenceId }"
    				} else {
    					remind('error',data.message);
    				}
                },"json");
                return false;
            }
        });
    });
</script>
</body>
</html>