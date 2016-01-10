<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-账户管理-资料管理</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/account.css" rel="stylesheet" />
    <link rel="stylesheet" href="${res}/styles/common/through.css"/>
</head>
<body>
    <div class="container">
        <jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <jsp:include page="${ctx}/common/menuInfo/sidemenu" />
                <div class="main-right">
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx }/dealer/center">管理中心</a> &gt;&gt; <a href="${ctx}/dealer/dealerInfo/account/infor" title="" class="c-h">账户管理</a> > <span class="bb">资料管理</span>
 							<a class="panel-crumbs-help" href="${ctx}/help/index">
                            <i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="main-grid mb20 clearfix">
                        <div class="success-tips">
                            <i class="icon icon-regist-success fl"></i>
                            <h1 class="title">恭喜您，注册成功！</h1>
                            <span class="tips">您可以继续认证，获得很多品牌商的青睐</span>
                        </div>
                        <form data-widget="validator" class="ui-form revise-form">
                            <div class="ui-form-item">
                            <input type="hidden" value='${dealerInfo.refrenceId}' name="refrenceId">
                                <label class="ui-label"><span class="c-r">*</span> 联 系 人：</label>
                                <input value="${dealerInfo.dealerUser}" type="text" name="dealerUser"  required data-errormessage-required="必须填写联系人" class="ui-input short" data-display="联系人" />
                                 <input type="radio" class="radio" id="lady" name="dealerGender" value="2" ${dealerInfo.dealerGender == null||dealerInfo.dealerGender == 2 ? 'checked="checked"' : '' }/> 
                                 <label for="lady">女士</label>
                                 <input type="radio" class="radio" id="male" name="dealerGender" value="1" ${dealerInfo.dealerGender == 1 ? 'checked="checked"' : '' } /> 
                                 <label for="male">先生</label>
                            </div>
                            <div class="ui-form-item">
                                <label class="ui-label"><span class="c-r">*</span> 手机号码：</label>
                                <input value="${dealerUserm.userMobile}" type="text" class="ui-input" disabled />
                            </div>
                            <div class="ui-form-item" style="height: 30px;">
                                <style>
                                    #test1province{width:130px;margin-right: 5px;}
                                    #test1city{width:94px;margin-right: 5px;}
                                    #test1county{width:100px;margin-right: 5px;}
                                </style>
                                <label class="ui-label"><span class="c-r">*</span> 所 在 地：</label>
                                <input type="hidden" name="provinceName" value="${dealerInfo.provinceName}" >
                                <input type="hidden" name="cityName" value="${dealerInfo.cityName}"  />
                                <input type="hidden" name="areaName" value="${dealerInfo.areaName}"  />
                                <input type="hidden" name="areaNo" value="${dealerInfo.areaNo}" />
                                <jsp:include page="${ctx}/common/regional/searchAllArea">
                                    <jsp:param value="${dealerInfo.province}" name="regProvince" />
                                    <jsp:param value="${dealerInfo.city}" name="regCity" />
                                    <jsp:param value="${dealerInfo.county}" name="regCounty" />
                                    <jsp:param value="test1" name="sale" />
                                    <jsp:param value="ui-select seeking-select-width" name="style" />
                                </jsp:include>
                            </div>
                            <div class="ui-form-item">
                                <input value="${dealerInfo.dealerAddress }" type="text"  name="dealerAddress"  class="ui-input" style="width:322px" placeholder="请输入详细地址"  required  data-display="详细地址"/>
                            </div>
                            <div class="contunie-box" style="display: none;">
                                <div>
                                    <div class="ui-form-item fl">
                                        <label class="ui-label"><span class="c-r">*</span> 身份证正面：</label>
                                        <div class="img-upload">
                                            <div class="file_wrap">
                                                    <a class="upload fs14 mb10 uploadID yahei fs16" id="Imgz">
 															<c:choose> 
																<c:when test="${dealerInfo.legalImgz != null && !empty dealerInfo.legalImgz}">
																	<img alt="" src="${res}${dealerInfo.domainName }${dealerInfo.legalImgz }" width="100%" height="100%"  >
																	<input type="hidden" name="legalImgz" value="${dealerInfo.legalImgz }"  />
																</c:when> 
																<c:otherwise>
																	 上传正面
																</c:otherwise> 
															</c:choose> 
													</a>
                                                     <input type="file" class="input_file"   id="legalImgz" name="photo">
                                                </div>
                                        </div>
                                        <p style="color: #999;">（仅支持JPG、GIF、PNG图片文件，且图片小于2M）</p>
                                    </div>
                                    <%--<div class="ui-form-item fl" style="padding-left: 85px;">
                                        <label class="ui-label"><span class="c-r">*</span> 店铺招牌：</label>
                                        <div class="img-upload">
                                             <div class="file_wrap">
                                                   <a class="upload fs14 mb10 uploadID yahei fs16">
                                                   	 <c:choose>
											<c:when test="${dealerInfo.dealerLogo != null && !empty dealerInfo.dealerLogo }">
												<img alt="" src="${res}${dealerInfo.domainName }${dealerInfo.dealerLogo }" width="100%" height="100%"  >
												<input type="hidden" name="dealerLogo" value="${dealerInfo.dealerLogo }" required data-errormessage-required="店铺LOGO必须上传" />
											</c:when>
											<c:otherwise>
											上传图片
											</c:otherwise>
										</c:choose>
                                                   </a>
                                                   <input class="input_file" type="file" name="photo" id="dealerLogo"/>
                                               </div>
                                        </div>
                                    </div>--%>
                                    <div class="ui-form-item fl imgUploadTip" style="padding-left: 7px;margin-top: 45px; color: #FF5243; display: none;">请上传图片</div>
                                </div>
                                <div class="ui-form-item" style="clear: both;">
                                    <label class="ui-label"><span class="c-r">*</span> 店铺招牌：</label>
                                    <div>
                                        <ul class="inline-float certificate_box">
                                            <c:forEach items="${dealerInfo.dealerImages}" var="image">
                                                <li class="item">
                                                    <div class="img_contain">
                                                        <input type="hidden" name="dealerImagePaths" value="${image.imageName}" />
                                                        <img src="${res}${image.imageName}" alt="" style="width:100%;height:100%;"/><span></span>
                                                        <a href="javascript:;" class="iconfont close" style="display: inline; "></a>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                            <li class="add_certificate" <c:if test="${fn:length(dealerInfo.dealerImages)==5}">style="display: none;" </c:if>>
                                                <div class="file_wrap">
                                                    <i class="iconfont">&#xe611;</i>
                                                    <input class="input_file"  type="file" id="dealerImagePaths" name="photo" />
                                                </div>
                                            </li>
                                        </ul>
                                        <p class="explain" style="padding-top: 10px;color: #999;">（店铺招牌最多上传 5 张图片,仅支持JPG、GIF、PNG图片文件，且图片小于2M）</p>
                                        <div class="imgUploadTip2" style="color: #FF5243; display: none;">*请上传图片</div>
                                    </div>
                                </div>
                                <div class="ui-form-item">
                                    <label class="ui-label"><span class="c-r">*</span> 身份证号：</label>
                                    <input value="${ dealerInfo.cardId}" type="text"  name="cardId"  class="ui-input" style="width:200px"  required  data-display="身份证号"/>
                                </div>
                                <div class="ui-form-item">
                                    <label class="ui-label"><span class="c-r">*</span> 店铺名称：</label>
                                    <input value="${dealerInfo.dealerName }" type="text"  name="dealerName"  class="ui-input" style="width:200px"  required  data-display="店铺名称"/>
                                </div>
                                <div class="ui-form-item">
                                    <label class="ui-label">成立时间：</label>
                                    <input value="${fns:getStringTime(dealerInfo.foundTime,'yyyy-MM-dd') }" type="text"  name="foundtime" class="ui-input fonding-time date" style="width:180px;padding-left: 25px;" />
                                </div>
                                <div class="ui-form-item">
                                    <label class="ui-label">员工数量：</label>
                                    <input value="${dealerInfo.empNum }" type="text"  name="empNum"  class="ui-input" style="width:200px"/>
                                </div>
                                <div class="ui-form-item">
                                    <label class="ui-label">经营品类：</label>
                                    <div>
                                        <ul class="continue-class">
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="ui-form-item">
                                <input class="ui-button ui-button-lorange yahei continue-accreditation" type="button" value="继续认证" />
                                <input class="ui-button ui-button-lorange yahei submitVerify" type="submit" value="提交审核" style="display: none;" />
                                <a id="skip_btn" class="ui-button ui-button-lorange yahei skip-accreditation">跳过认证</a> 
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
<jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp" />
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script>
        var revise = {
            init:function(){
                this.changeImage();//上传图片
                this.continueAccreditation();//继续认证
                this.calendar();//时间选择
                this.validate();//验证
                this.removeImg();//移除图片
            },
            changeImage:function(){
                //上传图片，并把图片放到A标签里面
                $("#legalImgz").bind("change",function(){
                    revise.uploadImage($(this).attr("id"));
                });
                $("#dealerImagePaths").bind("change",function(){
                    revise.uploadImage2($(this).attr("id"));
                });
            },
            showImage: function (uploadId, url) {
                var html = '<img src="${res}' + url + '" style="width:100%;height:100%;"  alt=""/>';
                html += '<input type="hidden" name="' + uploadId + '" value="${res}' + url + '" />';
                $('#' + uploadId).prev().text('').html(html);
            },
            showImage2: function (uploadId, url) {
                var html = '<li class="item">' +
                        '<div class="img_contain">' +
                        '<input type="hidden" name="' + uploadId + '" value="${res}' + url + '" />'+
                        '<img src="${res}'+ url +'" alt="" style="width:100%;height:100%;"/><span></span>' +
                        '<a href="javascript:;" class="iconfont close" style="display: inline; "></a>' +
                        '</div>' +
                        '</li>';
                var _parents = $('#'+uploadId).parents(".add_certificate");
                _parents.before(html);
                this.countImgNum();
            },
            uploadImage: function (uploadId) {
                var me = this;
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
                                    me.uploadImage(uploadId);
                                });

                                if (data.code == 126000) {
                                    me.showImage(uploadId, data.message);
                                }
                                d.destroy();
                            }
                        });
                    });
                });
            },
            uploadImage2: function (uploadId) {
                var me = this;
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
                                    me.uploadImage2(uploadId);
                                });

                                if (data.code == 126000) {
                                    me.showImage2(uploadId, data.message);
                                }
                                d.destroy();
                            }
                        });
                    });
                });
            },
            continueAccreditation:function(){
                $(".revise-form").on("click",".continue-accreditation",function(){
                    var _this = $(this);
                    _this.hide();
                    $(".contunie-box").slideDown();
                    $(".submitVerify").show();
                });
            },
            removeImg:function(){
                var _self = this;
                $(".certificate_box").on("click",".close",function(){
                    var _parents = $(this).parents(".item");
                    _parents.remove();
                    _self.countImgNum();
                });
            },
            countImgNum:function(){
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
            },
            calendar:function(){
                baseCalendar('.fonding-time');
            },
            validate:function(){
                //$(".revise-form").serialize(
                baseFormValidator({
                    selector: ".revise-form",
                    isAjax: true,
                    addElemFn:function(Core,Validator){
                    	Validator.addRule('IDCard', /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|x|X)$)/, '请输入正确的{{display}}格式');
                    	Core.addItem({
                             element: '[name=cardId]',
                             required: true,
                             rule: 'IDCard'
                         }).addItem({
                             element: '[name=county]',
                             required : function (){ 
         		        		var city = $('#test1city').val();
         		        		if(city==''||city=='请选择市'){
         		        			return true;
         		        		}
         		        		if($('#test1county:visible').size()==0){
         		        			return false;
         		        		}
         		        		return true;
         		        	}
                         });
                    },
                    beforeSubmitFn: function(){
                        /*校验图片的方法*/
                        var imgs = $('.file_wrap img');
                        var dealerLogo = parseInt($('.certificate_box .item').length);
                        if(imgs.size()!=1){
                            $('.imgUploadTip').show();
                            return false;
                        }else if(dealerLogo == 0){
                            $(".imgUploadTip2").show();
                        }else{
                            //图片验证通过，可以在后面加入ajax提交
                            $('.imgUploadTip,.imgUploadTip2').hide();
                            $.post("${ctx}/dealer/dealerInfo/account/info/check",$('.revise-form').serialize(),function(data){
                            	if(data.code==126000){
                            		remind('success','保存成功', 8000, function(){
                                        window.location.href="${ctx}/dealer/center";
                                    });
                            	}else{
                            		remind('error',data.message);
                            	}
                            },"json");
                        }
                    }
                });
            }
        }
        $(function(){
            revise.init();
            var _dealList = ${dealList};
            var _dealList = ${dealList};
            var _dealNOs = ${dealNos};
            var nos=[];
            $.each(_dealNOs, function (ind, item){
            	nos.push(parseInt(item.dealNo));
            });
            $.each(_dealList, function (ind, item) {//主分类
            	var checked=jQuery.inArray(parseInt(item.id), nos)!=-1;
                var html = '<li class="item" title="'+item.item+'"><label><input name="dealNos" '+(checked?'checked':'')+' value="'+item.id+'"type="checkbox" class="ui-checkbox"/>'+item.item+'</label></li>';
                $("ul.continue-class").append(html);
            })
            
            $('#skip_btn').click(function(){
            	confirmDialog("确定跳过认证吗?<br/>您可以在[账户管理-资料管理]中继续认证!",function(){
            		window.location.href="${ctx}/dealer/center";
            	});
            });
        });

    </script>
</body>
</html>
