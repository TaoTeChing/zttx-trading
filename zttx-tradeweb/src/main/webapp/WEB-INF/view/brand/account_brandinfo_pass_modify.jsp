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
        background: url("/images/common/icon_warn.png") no-repeat scroll 0 -243px rgba(0, 0, 0, 0);
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
                <!-- <div class="flow-steps mt15">
                    <ol>
                        <li class="done current-prev">注册成功</li>
                        <li class="current"><span>填写企业资料</span></li>
                        <li class="last"><span>确认审核</span></li>
                    </ol>
                </div> -->
				<%--<h3 class="mt10 add_companyInfo_title">企业基本信息：</h3>--%>
                <div class="ui_tab">
                    <ul class="ui_tab_items clearfix">
                        <li class="ui_tab_item current">
                            <a href="javascript:;">企业基本信息</a>
                        </li>
                    </ul>
                </div>
                <div class="tips">
                    <i class="v2-icon-explain"></i>
                    说明：企业资料提交后，一旦通过审核，敏感信息将不能再做修改，需修改，请联系客服
                </div>
				<form:form id="addCompanyInfo-form" cssClass="ui-form addCompanyInfo-form" >
					<div class="ui-form-item">
						<label class="ui-label" for=""> 公司名称：<span class="ui-form-required">*</span></label> 
						<input style="width: 214px;" type="text" class="ui-input" value="${empty brandInfo ? userm.comName : brandInfo.comName}" disabled>
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""> 经营模式：<span class="ui-form-required">*</span> </label>
						<c:if test="${companyType != null }">
				            <c:forEach items="${companyType}" var="company">
				            	<c:if test="${brandInfo.dealType == company.dictValue}">
				            		<input style="width: 214px;" type="text" class="ui-input" value="${company.dictValueName}" disabled>
				            	</c:if>
				            </c:forEach>
			            </c:if>
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""> 法人代表：<span class="ui-form-required">*</span> </label> 
						<input style="width: 214px;" type="text" class="ui-input" value="${brandInfo.legalName}" disabled>
					</div>
					 <div class="ui-form-item">
			            <label class="ui-label" for="">公司规模：<span class="ui-form-required">&nbsp;</span></label>
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
			            <label class="ui-label" for="">年营业额：<span class="ui-form-required">&nbsp;</span></label>
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
						<label class="ui-label" for="">公司地址：<span class="ui-form-required">*</span> </label>
						<input style="width: 120px;" type="text" class="ui-input" value="${brandInfo.provinceName}" disabled>
						<input style="width: 120px;" type="text" class="ui-input" value="${brandInfo.cityName}" disabled>
						<input style="width: 120px;" type="text" class="ui-input" value="${brandInfo.areaName}" disabled>
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""><span class="ui-form-required"></span> </label> 
						<input placeholder="详细地址"
							style="width: 362px;" type="text" class="ui-input detail_address" name="comAddress" value="${brandInfo.comAddress}"
							class="txt long" datatype="*1-128" sucmsg="" nullmsg="请输入详细地址!"
							errormsg="详细地址超出最大长度!">
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""> 公司网站：<span class="ui-form-required">&nbsp;</span> </label> 
						<input style="width: 214px;" maxlength="128" name="comWeb"  value="${brandInfo.comWeb}"
							type="text" class="ui-input"  datatype="webUrl" sucmsg="" nullmsg="" ignore="ignore" 
							errormsg="请输入合法的网址，例如：http://www.8637.com">
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""> 注册资本：<span class="ui-form-required">*</span> </label> 
						<input style="width: 214px;" type="text" class="ui-input" value="<fmt:formatNumber pattern="0.00" value="${brandInfo.regMoney}"/>" disabled> 万元
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""> 营业执照编号：<span class="ui-form-required">*</span> </label> 
						<input style="width: 214px;" type="text" class="ui-input" name="comNum" value="${comNum}" disabled>
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""> 主营品类：<span class="ui-form-required">&nbsp;</span> </label>
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
						<button class="ui_button ui_button_lblue" type="submit" id="sub">${brandInfo.checkState==1 ? "保存":"提交审核"}</button>
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

		//初始表单验证
		account_brandinfo.initValidform = function() {
			// 添加区域选择验证
			$("#county").attr("datatype", "countyId").attr("sucmsg", "").attr("nullmsg", "请选择县区!");
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
			$("input[name='areaNo']").val($("#county").val());
			$("#sub").css("display", "none");
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
	
		
		//文档完成入口
		$(function() {
			account_brandinfo.initCategory();
			account_brandinfo.initUMEdit();
			account_brandinfo.initValidform();
		});
	</script>
</body>
</html>