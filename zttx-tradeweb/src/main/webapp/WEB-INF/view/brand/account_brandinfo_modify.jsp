<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>管理中心-我的账户-账户信息-企业资料</title>
<link rel="stylesheet" href="${res}/styles/brand/global.css" />
<link rel="stylesheet" href="${res}/styles/brand/myAccount.css" />
<style type="text/css">
    .Validform_wrong{
        color: #FF5243;
        background: url("${res}/images/common/icon_warn.png") no-repeat scroll 0 -243px rgba(0, 0, 0, 0);
        padding-left: 20px;
        display: inline-block;
        height: 28px;
        line-height: 28px;
    }
    .w120{
    	width: 120px !important;
    }
    .flow-steps li{
        width: 320px;
    }
</style>
</head>
<body>
	<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
		<div class="main_con">
            <div class="bread_nav">
                <div class="fl">
                    <i class="icon"></i>
                    <a class="link" href="/brand/center">管理中心</a>
                    <span class="arrow">&gt;&gt;</span>
                    <a class="link" href="/brand/contact/list">我的账户</a>
                    <span class="arrow">&gt;</span>
                    <a class="link" href="${ctx}/brand/contact/list">账户信息</a>
                    <span class="arrow">&gt;</span>
                    <span class="current">企业资料</span>
                </div>
                <div class="fr">
                    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
                </div>
            </div>
			<div class="inner">
                <div class="flow-steps mt15">
                    <ol>
                        <li class="done current-prev">注册成功</li>
                        <li class="current"><span>填写企业资料</span></li>
                        <li class="last"><span>确认审核</span></li>
                    </ol>
                </div>
				<h3 class="mt10 add_companyInfo_title">企业基本信息：</h3>
                <div style="color: #666;margin: 15px 0 0px 90px;">
                    说明：企业资料提交后，一旦通过审核，敏感信息将不能再做修改，需修改，请联系客服
                </div>
				<form:form id="addCompanyInfo-form" cssClass="ui-form addCompanyInfo-form" >
					<div class="ui-form-item">
						<label class="ui-label" for=""> 公司名称：<span class="ui-form-required">*</span></label> 
						<input name='comName' style="width: 214px;" type="text" class="ui-input" value="${empty brandInfo ? userm.comName : brandInfo.comName}" tip="例如:8637品牌超级代理" altercss="gray"
							class="txt long" datatype="*1-50" sucmsg="" nullmsg="请输入公司名称!" errormsg="公司名称超出最大长度!">
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""> 经营模式：<span
							class="ui-form-required">*</span> </label>
						<div class="inline-block">
							<select class="ui-select js_select" name='dealType' id="dealTypeId" datatype="dealTypeId" sucmsg="" nullmsg="请选择经营类型!" errormsg="请选择经营类型!">
								 <option value="0" >请选择</option>
								 <c:if test="${companyType != null }">
				                    <c:forEach items="${companyType }" var="company">
				                    	 <option value="${company.dictValue }" ${brandInfo.dealType == company.dictValue ? 'selected' : '' }>${company.dictValueName }</option>
				                    </c:forEach>
			                    </c:if>
							</select>
						</div>
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""> 法人代表：<span class="ui-form-required">*</span> </label> <input style="width: 214px;" type="text" class="ui-input" tip="例如:张三" altercss="gray" name="legalName" value="${brandInfo.legalName}" class="txt long" datatype="*1-32" sucmsg="" nullmsg="请输入法人代表!" errormsg="法人代表超出最大长度!">
					</div>
						<div class="ui-form-item">
						<label class="ui-label" for=""> 法人身份证号：<span class="ui-form-required">*</span> </label> 
							<input style="width: 214px;" type="text" class="ui-input" name="idNum" value="${brandInfo.idNum}" class="txt long" datatype="*,idCard" sucmsg="" nullmsg="请输入法人身份证号" errormsg="请输入法人正确的身份证号!" />
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""> 身份证正反面：<span class="ui-form-required">*</span> </label>
						<div class="inline-block">
							<ul class="inline-float certificate_box">
							
							<c:choose>
									<c:when test="${brandInfo.userPhoto != null && !empty brandInfo.userPhoto}">
										<li class="item" id="file_user_photo_id">
											<input type="hidden" name="userPhoto" value="${brandInfo.userPhoto}">
					                        <div class="img_contain">
					                        	<c:set value="${brandInfo.domainName}${brandInfo.userPhoto}" var="url"></c:set>
					                            <img alt="" style="width:100px;height:100px;" src="${res}${url}"><span></span>
					                            <a class="iconfont close" href="javascript:;" style="display: none;"></a>
					                        </div>
				                    	</li>
									</c:when>
									<c:otherwise>
										<li class="add_certificate" id="file_user_photo_id">
											<div class="file_wrap">
												<i class="iconfont">&#xe611;</i> <input class="input_file" name="photo" type="file" id="file_user_photo" />
											</div></li>
									</c:otherwise>
								</c:choose>
								
                        		<c:choose>
									<c:when test="${brandInfo.userImage != null && !empty brandInfo.userImage}">
										<li class="item" id="file_user_image_id">
											<input type="hidden" name="userImage" value="${brandInfo.userImage}">
					                        <div class="img_contain">
					                        	<c:set value="${brandInfo.domainName}${brandInfo.userImage}" var="url"></c:set>
					                            <img alt="" style="width:100px;height:100px;" src="${res}${url}"><span></span>
					                            <a class="iconfont close" href="javascript:;" style="display: none;"></a>
					                        </div>
				                    	</li>
									</c:when>
									<c:otherwise>
										<li class="add_certificate" id="file_user_image_id">
											<div class="file_wrap">
												<i class="iconfont">&#xe611;</i> <input class="input_file" name='photo' type="file" id="file_user_image" />
											</div></li>
									</c:otherwise>
								</c:choose>
							</ul>
                            <input  style="display: none;" datatype="idCardPic" datatype="*" sucmsg="" nullmsg="请上传身份证!" errormsg="请上传身份证!">
                        	<p class="explain">（仅支持JPG、GIF、PNG图片文件，且图片小于2M）</p>
						</div>
					</div>
					 <div class="ui-form-item">
			            <label class="ui-label" for="">
			                公司规模：<span class="ui-form-required">&nbsp;</span>
			            </label>
			            <div class="inline-block">
			                <select class="ui-select js_select" id="select_111" name="emploeeNum">
			                
								 <c:if test="${companyScope != null }">
				                    <c:forEach items="${companyScope }" var="scope">
				                    	 <option value="${scope.dictValue }" ${brandInfo.emploeeNum == scope.dictValue ? 'selected' : '' }>${scope.dictValueName }</option>
				                    </c:forEach>
			                    </c:if>
							  </select>
			            </div>
			        </div>
			        <div class="ui-form-item">
			            <label class="ui-label" for="">
			                年营业额：<span class="ui-form-required">&nbsp;</span>
			            </label>
			            <div class="inline-block">
			                <select class="ui-select js_select" id="select_222" name="moneyNum">
			                
			                	<c:if test="${brandTurnover != null }">
				                    <c:forEach items="${brandTurnover }" var="turnover">
				                    	 <option value="${turnover.dictValue }" ${brandInfo.moneyNum == turnover.dictValue ? 'selected' : '' }>${turnover.dictValueName }</option>
				                    </c:forEach>
			                    </c:if>
			                </select>
			            </div>
			        </div>
					<div class="ui-form-item">
						<input type="hidden" name="areaNo" value="" />
						<label class="ui-label" for=""> 公司地址：<span
							class="ui-form-required">*</span> </label>
						<jsp:include page="${ctx}/common/regional/searchAllArea">
							<jsp:param value="${brandInfo.areaNo}" name="regAreaNo"/>
							<jsp:param value="js_select ui-select w120" name="style"/>
						</jsp:include>
						<input type="hidden" id="areaErr" name="areaErr" value=""/>
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""> <span
							class="ui-form-required"></span> </label> <input placeholder="详细地址"
							style="width: 362px;" type="text" class="ui-input detail_address" name="comAddress" value="${brandInfo.comAddress}"
							class="txt long" datatype="*1-128" sucmsg="" nullmsg="请输入详细地址!"
							errormsg="详细地址超出最大长度!">
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""> 公司网站：<span
							class="ui-form-required">&nbsp;</span> </label> <input style="width: 214px;" maxlength="128" name="comWeb"  value="${brandInfo.comWeb}"
							type="text" class="ui-input"  datatype="webUrl" sucmsg="" nullmsg="" ignore="ignore" 
							errormsg="请输入合法的网址，例如：http://www.8637.com">
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""> 注册资本：<span class="ui-form-required">*</span> </label> <input style="width: 214px;" name="regMoney"  value="${brandInfo.regMoney}"
							type="text" class="ui-input" datatype="money" sucmsg=""
							errormsg="请输入正确的金额!"> 万元
					</div>
					
						<div class="ui-form-item">
						<label class="ui-label" for=""> 营业执照编号：<span class="ui-form-required">*</span> </label> 
							<input style="width: 214px;"
							type="text" class="ui-input" tip="例如:1000" altercss="gray" name="comNum" value="${brandInfo.comNum}"
							class="txt long" datatype="*1-20" sucmsg="" nullmsg="请输入执照编号!"
							errormsg="执照编号超出最大长度!">
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""> 营业执照正面：<span
							class="ui-form-required">*</span> </label>
						<div class="inline-block">
							<ul class="inline-float certificate_box">
                        		<c:choose>
									<c:when test="${brandInfo.brandImage != null && !empty brandInfo.brandImage}">
										<li class="item" id="file_cert_image_id">
											<input type="hidden" name="brandImage" value="${brandInfo.brandImage}">
											<input type="hidden" name="brandPhoto" value="${brandInfo.brandPhoto}">
					                        <div class="img_contain">
					                        	<c:set value="${brandInfo.domainName}${brandInfo.brandImage}" var="url"></c:set>
					                            <img alt="" style="width:100px;height:100px;" src="${res}${url}"><span></span>
					                            <a class="iconfont close" href="javascript:;" style="display: none;"></a>
					                        </div>
				                    	</li>
									</c:when>
									<c:otherwise>
										<li class="add_certificate" id="file_cert_image_id">
											<div class="file_wrap">
												<i class="iconfont">&#xe611;</i> <input class="input_file" name='photo' type="file" id="file_cert_image" />
											</div></li>
									</c:otherwise>
								</c:choose>
							</ul>
                            <input style="display: none;" datatype="companyPic" datatype="*" sucmsg="" nullmsg="请选择执照照片!" errormsg="请选择执照照片!">
							<p class="explain">（仅支持JPG、GIF、PNG图片文件，且文件小于5M）</p>
						</div>
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""> 主营品类：<span
							class="ui-form-required">&nbsp;</span> </label>
						<div class="inline-block product_area">
							<ul class="inline-float">
                                <li style="position: relative" id="select_class" class="select_class">
                                    <ul class="select_inner">
                                    </ul>
                                </li>
								<li id="select_product" class="select_product">
									<ul class="select_inner">
									<c:forEach items="${brandCatelogs}" var="item">
										<li data-id="${item.dealNo}" class="item"><span>${item.dealDic.dealName} </span><i style="display: none;" class="iconfont close_icon"></i></li>
									</c:forEach>
									</ul>
								</li>
							</ul>
						</div>
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""> 企业简介：<span class="ui-form-required">*</span> </label>
						<div class="inline-block">
							<textarea id="company_intr" name="comMark">${brandInfo.comMark}</textarea>
                            <input datatype="richtextrule" nullmsg="请输入企业简介!" type="hidden" id="editor_value" value="" />
						</div>
					</div>
					<div class="ui-form-item">
						<button class="ui_button ui_button_morange" type="submit" id="sub">${brandInfo.checkState==1 ? "保存":"提交审核"}</button>
						<label style="color:red;font-size: 12px;display: none;" id="subLbl">正在执行中...</label>
					</div>
				</form:form>

			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
	<script src="${src}/plugin/Validform_v5.3.2_min.js"></script>
	<script>
		//定义 命名空间
		account_brandinfo = {};

		//上传tm图片
		account_brandinfo.uploadUserPhoto=function(){
			seajs.use(["$","ajaxFileUpload"],function($){
    			$.ajaxFileUpload({
        			url: '${ctx}/common/showImg?fSize=2',
        			secureuri: false,
                    fileElementId: 'file_user_photo',
                    dataType: 'json',
                    success: function(data)
                    {
                    	if(data.code == zttx.SUCCESS)
                    	{
                    		account_brandinfo.showCertificateImage('file_user_photo', data.message, data.object);
                    	}
                    	else
                    	{
                    		remind("error", data.message);
                    	}
                    }
        		});
    	    });
		};
		
		account_brandinfo.uploadUserImage=function(){
			seajs.use(["$","ajaxFileUpload"],function($){
    			$.ajaxFileUpload({
        			url: '${ctx}/common/showImg?fSize=2',
        			secureuri: false,
        			fileElementId: 'file_user_image',
                    dataType: 'json',
                    success: function(data)
                    {
                    	if(data.code == zttx.SUCCESS)
                    	{
                    		account_brandinfo.showCertificateImage('file_user_image', data.message, data.object);
                    	}
                    	else
                    	{
                    		remind("error", data.message);
                    	}
                    }
        		});
    	    });
		};

		account_brandinfo.uploadCertImage = function() {
			seajs.use(["$","ajaxFileUpload"],function($){
    			$.ajaxFileUpload({
        			url: '${ctx}/common/showImg?fSize=2',
        			secureuri: false,
                    fileElementId: 'file_cert_image',
                    dataType: 'json',
                    success: function(data)
                    {
                    	if(data.code == zttx.SUCCESS)
                    	{
                    		account_brandinfo.showCertificateImage('file_cert_image', data.message, data.object);
                    	}
                    	else
                    	{
                    		remind("error", data.message);
                    	}
                    }
        		});
    	    });
		};

		//显示证书图片
		account_brandinfo.showCertificateImage = function(itemId,url,oldName) {
    		var html = [];
    		html.push('<li id="'+itemId+'_id" class="item">');
    		html.push('<input type="hidden" name="'+itemId+'Photo" value="'+oldName+'">');
    		html.push('<input type="hidden" name="'+itemId+'Image" value="'+url+'">');
    		html.push(' 	<div class="img_contain">');
    		html.push('			<img src="${res}'+url+'" style="width:100px;height:100px" alt=""/><span></span>');
    		html.push('			<a href="javascript:;" class="iconfont close">&#xe612;</a>');
    		html.push('		</div>');
    		html.push('</li>');
    		$('#'+itemId+"_id").replaceWith($(html.join("")));;
    	};
		//初始业执照图片
		account_brandinfo.initCertificate = function() {
			if ($(".certificate_box").length == 0) {
				return;
			}
			$(".certificate_box").on("mouseenter mouseleave", ".item",
					function(ev) {
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
				var add=[];
				var fildid=$(this).parents(".item").attr('id').replace("_id","");
				add.push('<li id="'+$(this).parents(".item").attr('id')+'" class="add_certificate">');
				add.push('	<div class="file_wrap">');
				add.push('		<i class="iconfont">&#xe611;</i> <input class="input_file" name="photo" type="file" id="'+fildid+'" />');
				add.push('</div></li>');
				$(this).parents(".item").replaceWith($(add.join("")));
			});
		};

		var editor;
		
		//初始编辑器
		account_brandinfo.initUMEdit = function() {
			seajs.use([ "umeditor", "umeditor_config", "umdeitor_style" ],
				function() {

                    editor = UM.getEditor('company_intr', {
						initialFrameWidth : 676,
						initialFrameHeight : 400
					});
				});
		};

		//初始主营产品类别
		account_brandinfo.initCategory = function() {
			var _this = this;
			
			_this._createCategoryItem = function(arr, type) {
				var item_li = [];
				type = !type ? "choose" : type;
				$.each(arr,function(index, obj) {
					if (type == "choose") {
						item_li.push("<li id='item_"+obj.id+"' class='item'><span>"+ obj.item+ "</span><i class='iconfont'>&#xe60f;</i></li>");
					} else if (type == "close") {
						item_li.push("<li data-id="+obj.id+" class='item'><span>"+ obj.item+ "</span><i class='iconfont close_icon'>&#xe602;</i></li>");
					}
				});
				return $(item_li.join(""));
			};
			var class_obj = _this._createCategoryItem(${dealList});
			class_obj.appendTo($("#select_class .select_inner"));
			
			$("#select_class").on("click", ".item", function() {
				//如果已经点击过了一次，不再处理
				
				var isExist=false;
				var outId=$(this).attr("id");
				$("li.select_product ul.select_inner li.item").each(function(){
					if("item_"+$(this).data("id")==outId){
						isExist=true;
					}
				});
				
				if (isExist) {
					return;
				}
				$("#select_class .item").removeClass("current");
				$(this).addClass("current");
				$(this).attr("clicked", "true");
				var product_obj = _this._createCategoryItem([ {
					"id" : $(this).attr("id").replace(/\D/g, function() {
						return "";
					}),
					"item" : $(this).find("span").html()
				} ], "close");
				product_obj.appendTo($("#select_product .select_inner"));
			});
			$("#select_product").on("mouseenter mouseleave click", ".item",
					function(ev) {
						switch (ev.type) {
						case "mouseenter":
							$(this).find(".close_icon").show();
							break;
						case "mouseleave":
							$(this).find(".close_icon").hide();
							break;
						case "click":
							var id = $(this).data("id");
							$($("#item_" + id)).removeClass("current");
							$("#item_" + id).attr("clicked", "false");
							$(this).remove();
						}
					});
		};

		//初始表单验证
		account_brandinfo.initValidform = function() {
			// 添加区域选择验证
			if($("#province").val() !==""){
				$("#areaErr").val("1");
			}
			$("#areaErr").attr("datatype", "countyId").attr("sucmsg", "").attr("nullmsg", "请选择公司地址!");
			$("#province").change(function(){
				var _val = $(this).val();
				if(_val==""){
					$("#areaErr").val("");
				}else{
					$("#areaErr").val("1");
				}
			});
			var f=$("#addCompanyInfo-form").Validform({
				btnSubmit:'.ui_button.ui_button_morange',
				tiptype : 4,
				ajaxpost : true,
				datatype : {
					"dealTypeId" : function(value) {
						return ("0" == value) ? false : true;
					},
					"countyId" : function(value) {
						return ("" == value) ? false : true;
					},
					"companyPic": function(value){
						if($('input[name=brandImage]').size() > 0)
						{
							return true;
						}
						if($('input[name=file_cert_imageImage]').size() > 0)
						{
							return true;
						}
						return false;
					},
					"idCardPic": function(value) {
                        if($('input[name=userPhoto],input[name=userImage]').size() > 1)
						{
							return true;
						}
						if($('input[name=file_user_photoImage],input[name=file_user_imageImage]').size() > 1)
						{
							return true;
						}
						return false;
					},
                    "richtextrule": function(){
                        return editor.hasContents();
                    },
                    "webUrl":/^(http|https):\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?$/,
					"idCard":/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|x|X)$)/,
					"money":/^(([1-9]\d{0,6})|0)(\.\d{1,2})?$/
				},
				
				usePlugin : {
					passwordstrength : {
						minLen : 6,
						maxLen : 18
					}
				},
				callback:account_brandinfo.submit
			});
		};
		
		// 保存修改
		account_brandinfo.submit=function(){
			var category=[];
			$(".select_product .select_inner .item").each(function(){
				category.push($(this).data("id"));
			});
			
			var comMark=UM.getEditor('company_intr').getContent();
			
			var areaNo=$.trim($("#province").val());
			
			var county=$.trim($("#city").val());
			if(county!=""){
				areaNo=county;
			}
			county=$.trim($("#county").val());
			if(county!=""){
				areaNo=county;
			}
			if(areaNo==""){
				remind("error", "请选择公司地址!");
				return false;
			}
			$("input[name='areaNo']").val(areaNo);
			$("#sub").css("display","none");
			$("#subLbl").css("display", "block");
			$.post("${ctx}/brand/info/submit",$(".addCompanyInfo-form").serialize()+"&dealNos="+category.join(","),function(data){
				if(zttx.SUCCESS==data.code){
					remindfn('success',"提交成功!",1000, function(){
						var url = location.href;
						<c:if test="${brandInfo.checkState != 1}">
							url = "${cgx}/brand/info/unchecked";
						</c:if>
						window.location.href=url;
						$("#sub").css("display", "none");
						$("#subLbl").css("display", "block");
					});
				}else
				{
					remind("error", data.message);
					$("#sub").css("display", "block");
					$("#subLbl").css("display", "none");
				}
			},"json");
			return false;
		};
		
		//文档完成入口
		$(function() {
			account_brandinfo.initCertificate();
			account_brandinfo.initCategory();
			account_brandinfo.initUMEdit();
			account_brandinfo.initValidform();
			// 绑定上传营业执照事件
			$(document).on('change', '#file_user_photo', function(){
				if(verifyFileSuffix(this)){
					account_brandinfo.uploadUserPhoto();
				}
			});
			$(document).on('change', '#file_user_image', function(){
				if(verifyFileSuffix(this)){
					account_brandinfo.uploadUserImage();
				}
			});
			$(document).on('change', '#file_cert_image', function(){
				if(verifyFileSuffix(this)){
					account_brandinfo.uploadCertImage();
				}
			});
		});
	</script>
</body>
</html>