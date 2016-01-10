<%@ page language="java" contentType="text/html;charset=UTF-8"
		 pageEncoding="UTF-8" import="com.zttx.sdk.consts.ZttxConst" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<c:if test="${null != saleAttributes && saleAttributes.size()>0}">
	<div class="mininfo clearfix">
		<h2 class="title">
			<i class="iconfont">&#xe605;</i>产品销售属性
		</h2>
		<span>（产品详细属性：色彩尺码、材料等）</span>
		<span class="ui-form-required">*</span>
	</div>
	<div class="TitclickHS" id="proSaleAttr">
		<c:forEach items="${saleAttributes}" var="attr">
			<div class="checkall" id="attr_${attr.attributeNo}">
					${attr.attributeName}： <label> <input type="checkbox"
														  class="ui-checkbox" id="attrCheckedAll_${attr.attributeNo}" />全选 </label>
			</div>
			<ul class="coloreditul clearfix" data-color="${attr.isImgAttr}"
				data-name="${attr.attributeName}" id="attrul_${attr.attributeNo}"
				data-attrno="${attr.attributeNo}"
				data-proid="${productInfo.refrenceId}">
				<c:forEach items="${attr.itemList}" var="attrValue"
						   varStatus="status">
					<li
							<c:if test="${!empty attrValue.attributeIcon}"> class="chicunul" </c:if>>
						<input type="hidden" name="salerRefrenceId" id="salerRefrenceId"
							   value="${attrValue.refrenceId}" /> <input type="hidden"
																		 name="salerAtttrIcon" id="salerAtttrIcon"
																		 value="<c:if test='${attrValue.attributeIcon.length()>6}'>${attrValue.attributeIcon}</c:if>" />
						<input data-color="${attr.isImgAttr}"
							   data-icon="${attrValue.attributeIcon}"
							   data-text="${attrValue.attributeItem}" type='checkbox'
							   <c:if test="${attrValue.checked}">checked="checked" <c:if test="${not empty hasValidOrder && hasValidOrder}">disabled</c:if>
						</c:if>
							   class="ui-checkbox colorchk" name="colorcheck"
							   id="attr_chk_${attr.attributeNo}_${attrValue.refrenceId}"
							   data-oldicon="${attrValue.attributeIcon}"
							   data-id="${attrValue.refrenceId}" data-type="${attr.attributeNo}"
							   data-value="${attrValue.attributeItem}" value="${attrValue.refrenceId}">

						<label for="attr_chk_${attr.attributeNo}_${attrValue.refrenceId}">
							<c:if test="${!empty attrValue.attributeIcon}">
								<c:if test="${attrValue.attributeIcon.length() <= 6}">
									<div class="colorpickbox inline-block"
										 style="background:#${attrValue.attributeIcon};"></div>
								</c:if>
								<c:if test="${attrValue.attributeIcon.length() > 6}">
									<div class="colorpickbox inline-block"
										 style="background-image:url(${res}${attrValue.attributeIcon});background-repeat: no-repeat;background-size: 16px 16px;"></div>
								</c:if>
							</c:if> <span <c:if test="${attrValue.checked}">style="display: none;"</c:if>
										  class="colorfont"
										  id="attrshow_${attr.attributeNo}_${attrValue.refrenceId}">${attrValue.attributeItem}</span>
						</label>
						<input type="text" class="getext" name="getext"
							   value="${attrValue.attributeItem}"
							   <c:if test="${attrValue.checked}">style="display: inline-block;"
							   <c:if test="${not empty hasValidOrder && hasValidOrder}">disabled</c:if>
						</c:if>
							   id="attredit_${attr.attributeNo}_${attrValue.refrenceId}" >
						<c:if test="${attrValue.checked}">
							<c:if test="${not empty hasValidOrder && hasValidOrder}">
								<i class="iconfont edit-attr-iconfont">&#xe618;</i>
							</c:if>
						</c:if>
					</li>
				</c:forEach>
			</ul>
		</c:forEach>
		<c:forEach items="${saleAttributes}" var="attr">
			<c:if test="${attr.isImgAttr}">
				<table id="selectchicun" class="TitclickHS-table colortabledata"
					   <c:if test="${!isDisplay}">style="display:none;"</c:if>>
					<colgroup>
						<col width="170">
						<col width="300">
					</colgroup>
					<thead>
					<tr style="display: table-row;*display:block;">
						<th>颜色</th>
						<th>图片（无图片可不填）</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${attr.itemList}" var="attrValue">
						<tr class="colorshow" style="display: table-row;">
							<td class="td-first-Bor"><c:if
									test="${attrValue.attributeIcon.length() <= 6}">
								<div id="ico_fileUp_${attrValue.refrenceId}"
									 class="colorpickbox inline-block"
									 style="background:#${attrValue.attributeIcon};margin-left: 5px;">
								</div>
							</c:if> <c:if test="${attrValue.attributeIcon.length() > 6}">
								<div id="ico_fileUp_${attrValue.refrenceId}"
									 class="colorpickbox inline-block"
									 style="background-image:url(${res}${attrValue.attributeIcon}); no-repeat;margin-left: 5px;">
								</div>
							</c:if> <span class="colorfont"
										  id="attrtext_${attr.attributeNo}_${attrValue.refrenceId}">${attrValue.attributeItem}</span>
								<input type="hidden"
									   id="colorImage_ids_${attrValue.attributeId}_${attrValue.refrenceId}"
									   name="attr_colorImage_ids" value="${attrValue.refrenceId}" /> <input
										type="hidden"
										id="colorImage_urls_${attrValue.attributeId}_${attrValue.refrenceId}"
										name="attr_colorImage_urls" value="${attrValue.attributeIcon}" />
							</td>
							<td>
								<div class="file_wrap inline-block">
									<p class="simple_button">文件上传</p>
									<input type="file" class="input_file"
										   data-id="${attrValue.refrenceId}"
										   data-type="${attrValue.attributeId}" value="文件上传" name="photo"
										   id="fileUp_${attrValue.refrenceId}"
										   onchange="colorImgUpload(this);">
									<div class="data" style="display: none;"
										 id="div_fileUp_${attrValue.refrenceId}">
										<input type="hidden" id="colorImgUrls_${attrValue.refrenceId}"
											   name="colorImgUrls" data-icon="${attrValue.attributeIcon}"
											   value="${attrValue.attributeIcon}" /> <input type="hidden"
																							id="colorImgNames_${attrValue.refrenceId}"
																							name="colorImgNames" value="" />
									</div>
								</div> <a class="simple_button">图库选择</a> <a class="orangefont">删除</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</c:if>
		</c:forEach>
		<div class="tablelong-auto" style="display: none;">
			<div class="m-oprate">
				<ul class="inline-float">
					<li>
						<label>
							<input type="checkbox" class="ui-checkbox" name="isSample" <c:if test="${productInfo.productExtInfo.sample}"> checked="checked"</c:if>>支持拿样
						</label>
					</li>
					<li>
						<input type="text" name="samplePrice" id="samplePrice" value="${samplePrice}" class="ui-input ml5 samplePrice" placeholder="请输入拿样价格">
					</li>
					<li>
						<label>
							<input type="checkbox" class="ui-checkbox ml5" name="credit" id="supportCredit" <c:if test="${productInfo.productExtInfo.credit}"> checked="checked"</c:if> autoComplate="off">支持授信
						</label>
					</li>
				</ul>
			</div>
			<div class="m-oprate">
				<ul class="inline-float">
					<li>
						吊牌价：<input type="text" class="ui-input js-revise-dprice js-price" maxlength="8" />
					</li>
					<li>
						现款价：<label><input type="radio" name="dprice_n" class="ui-radio js-revise-zjradio" checked/> 直接设置</label>
					</li>
					<li>
						<label><input type="radio" name="dprice_n" class="ui-radio" /> 折扣设置</label>
					</li>
					<li>
						<input type="text" class="ui-input js-revise-zprice js-price" maxlength="8" />
					</li>
					<li>
						<span class="js-tigger-sx" <c:if test="!${productInfo.productExtInfo.credit}">style="display: none;"</c:if>>
						授信价：<input type="text" class="ui-input js-revise-cprice js-price" maxlength="8" />
						</span>
					</li>
					<li>
						库存：<input type="text" class="ui-input js-revise-dcount js-number" maxlength="8" />
					</li>
					<li><button type="button" class="simple_button js-revise-set">批量设置</button></li>
				</ul>
			</div>
			<div class="m-oprate">
				<ul class="inline-float">
					<input type="hidden" name="send"/>
					<li>
						<label>
							<input type="checkbox" name="point" class="ui-checkbox" id="support_rebate" <c:if test="${productInfo.productExtInfo.point}">checked=true</c:if> />
							支持返点
						</label>
					</li>
					<li class="js-rebate-display" style="display: none;margin-left: 8px;">
						返点比例：
						<label>
							<input type="radio" name="pointPercent" value="0.1" class="ui-radio js-rebate-pro" data-type="0" <c:if test="${productInfo.productExtInfo.pointPercent==null || productInfo.productExtInfo.pointPercent.doubleValue()==0.10}">checked=true</c:if> />
							10%
						</label>
						<label>
							<input type="radio" name="pointPercent" value="2" class="ui-radio js-rebate-pro" data-type="1"  <c:if test="${ productInfo.productExtInfo.pointPercent!=null && productInfo.productExtInfo.pointPercent.doubleValue()!=0.10 }">checked=true</c:if> />
							其他
						</label>
						<input type="text" class="ui-input js-rebate-other js-price" name="pointPercentOther" style="width: 20px;" value="${productInfo.productExtInfo.pointPercent!=null && productInfo.productExtInfo.pointPercent.doubleValue()!=0.10?(productInfo.productExtInfo.pointPercent.doubleValue()*100):''}"/>%
					</li>
					<li class="js-rebate-display" style="display: none;">
						返点价：
						<select name="" id="select_rebate">
							<option value="">请选择</option>
							<option value="9.00">9.00</option>
							<option value="19.00">19.00</option>
							<option value="29.00">29.00</option>
							<option value="39.00">39.00</option>
							<option value="49.00">49.00</option>
							<option value="59.00">59.00</option>
							<option value="69.00">69.00</option>
						</select>
						<input type="text" class="ui-input js-rebate-setext"/>
						<button type="button" class="simple_button js-rebate-set">设置</button>
					</li>
				</ul>
			</div>
		</div>

		<div class="tablelong-auto"
			 style="display:none;overflow: hidden; margin-top: 10px; margin-bottom: 10px;">
			<style>
				.tablelong-box-item {
					line-height: 30px;
					background: #E3EDF7;
					text-align: center;
					border-right: 1px solid #fff;
					margin-left: -4px;
				}

				.table {
					margin-left: 20px;
				}

				.table th {
					background: #e3edf7;
					font-weight: 400;
					line-height: 30px;
				}

				.setcommon-value {
					padding: 1px 3px;
					border: 1px solid #c1c1c1;
				}

				.setcommon-error {
					border: 1px solid #f00;
					color: #f00;
				}
				.input-error{
					border: 1px solid #f00;
					color: #f00;
				}
			</style>
			<table class="table" style="width: 746px;">
				<thead>
				<tr style="display: table-row;*display:block;">
					<c:forEach items="${saleAttributes}" var="attr">
						<th width="90">${attr.attributeName}</th>
					</c:forEach>
					<th width="90">吊牌价</th>
					<th width="90">现款价</th>
					<th width="90" class="js-tigger-sx" style="display: none;">授信价</th>
					<th width="90" class="js-tigger-fd" style="display: none;">返点价</th>
					<th width="90">库存</th>
					<th width="100">条形码</th>
						<%--<th width="192">批量操作</th>--%>
				</tr>
				</thead>
			</table>
			<div style="max-height:414px;overflow: auto;">
				<table id="selectmix"
					   class="TitclickHS-table TitclickHS-tablelong zuhetable"
					   style="width:746px;display:none; margin-top: 0px;">
					<tbody class="maintbody">

					</tbody>
				</table>
			</div>
		</div>
		<div class="tablelong-auto" style="display:none;">
			<div style="padding: 0 20px;">
				<label>
					<input type="checkbox" name="discount" <c:if test="${productInfo.productExtInfo.discount}">checked </c:if> /> 参加加盟终端商折扣
				</label>
			</div>
			<div class="js-tigger-sx" style="padding: 10px 20px;">
				<label><input type="checkbox" checked/>授信价格的生效方式</label>
				<div style="margin: 10px 0 0 30px;">
					<div><label><input type="radio" class="ui-radio" name="costPush" value="true" />当前终端商的库存生效</label></div>
					<div class="mt5"><label><input type="radio" class="ui-radio" name="costPush" value="false" checked />新订单生效</label></div>
				</div>
			</div>
			<div class="js-tigger-fd" style="padding: 10px 20px;">
				<label><input type="checkbox" checked/>返点价生效时间：</label>
				<input type="text" class="ui-input" name="pointEffTimeStr" id="dateRebate" value="${productInfo.productExtInfo.pointEffTimeStr}" />
			</div>
		</div>
		<div class="notalltip">
			<i class="iconjin"></i>您需要选择所有的销售属性，才能组合成完整的规格信息。
		</div>
	</div>
	<c:if test="${!empty productInfo.tmpAttributes}">
		<div id="init_value" style="display: none;">
				${productInfo.tmpAttributes}</div>
	</c:if>
</c:if>

<c:if test="${null != proAttributes && proAttributes.size()>0}">
	<div class="mininfo2 clearfix">
		<h2 class="title">
			<i class="iconfont">&#xe605;</i>产品属性
		</h2>
		<span></span>
	</div>
	<div class="product-nature" id="proAttribute">
		<c:forEach items="${proAttributes}" var="proAttr" varStatus="status">
			<div class="ui-form-item" id="proAttr">
				<label class="ui-label">${proAttr.attributeName}：</label>
				<c:choose>
					<c:when test="${proAttr.operateCate==1}">
						<div class="inline-block">
							<select class="ui-select js-select"
									data-attrno="${proAttr.attributeNo}"
									data-proid="${productInfo.refrenceId}"
									id="attrsel_${proAttr.attributeNo}"
									style="width:138px; height:22px;" name="${proAttr.refrenceId}">
								<option value="">请选择</option>
								<c:forEach items="${proAttr.itemList}" var="attrValue">
									<option value="${attrValue.refrenceId}"
											<c:if test="${attrValue.checked}">selected</c:if>>${attrValue.attributeItem}</option>
								</c:forEach>
							</select>
						</div>
					</c:when>
					<c:when test="${proAttr.operateCate==2}">
						<div class="inline-block checkboxgroup">
							<c:forEach items="${proAttr.itemList}" var="attrValue">
								<label><input type="checkbox" class="ui-checkbox"
											  <c:if test="${attrValue.checked}">checked="checked" </c:if>
											  value="${attrValue.refrenceId}"
											  data-attrno="${proAttr.attributeNo}"
											  name="${proAttr.refrenceId}">
										${attrValue.attributeItem}</label>
							</c:forEach>
						</div>
					</c:when>
				</c:choose>
			</div>
		</c:forEach>
	</div>
</c:if>

<%-- 其他平台价格 --%>
<div class="mininfo2 clearfix">
	<h2 class="title">
		<i class="iconfont">&#xe605;</i>其他平台价格
	</h2>
	<span></span>
</div>

<div class="product-nature">
	<div class="m-other-flat">
		<ul>
			<c:forEach items="${parityModel}" var="parity" varStatus="status">
				<li>
					<div class="bd">
						<div class="bd-1">
							<label>${parity.name}：</label>
							<input name="parity[${status.index}].parityId" value="${parity.refrenceId}" type="hidden" />
							<input name="parity[${status.index}].price" value="${parity.price}" type="text" class="ui-input"/> 元
						</div>
						<c:if test="${parity.name!='门店参考价'}">
							<div class="bd-2">产品连接：<input type="text" value="${parity.url}" name="parity[${status.index}].url" class="ui-input"/></div>
						</c:if>
					</div>
				</li>
			</c:forEach>

		</ul>
	</div>
</div>

<%--为标记图库位置--%>
<input type="hidden" id="fileInputId">
<%--浮动图库--%>
<jsp:include page="view_img_storage.jsp" />
<div style="display: none" id="saleAttrFormData"></div>
<div style="display: none" id="proAttrFormData"></div>
<div style="display: none" id="proAttrPriceFormData"></div>
<script type="text/javascript">
	function colorImgUpload(fileInput) {
		var id = fileInput.id;
		var valueId = $(fileInput).data("id");
		var type = $(fileInput).data("type");
		seajs.use([ "$", "ajaxFileUpload" ], function($) {
			$.ajaxFileUpload({
				url : "${ctx}/common/showImg?fSize=1",
				secureuri : false,
				fileElementId : id,
				dataType : 'json',
				success : function(data) {
					if (data.code != zttx.SUCCESS) {
						remind("error", data.message);
					} else {
						var url = data.message;
						setColorImgUrlData(id, url);
					}
				}
			})
		})
	}
	function setColorImgUrlData(fileInputId, url, domainName) {
		var $fileInput = $("#" + fileInputId);
		var valueId = $fileInput.data("id");
		var type = $fileInput.data("type");
		if (domainName == undefined) {
			domainName = "";
		}
		var upUrl = url;
		url = window.IMAGE_DOMIAN + url;
		//      $("#colorImgUrls_"+valueId).val(url);
		$fileInput.parent().find("input[name='colorImgUrls']").val(url);
		$fileInput.parent().find("input[name='colorImgNames']").val(domainName);
		$("#ico_" + fileInputId).css({
			"background-image" : "url('" + url + "')",
			"background-repeat" : "no-repeat",
			"background-size" : "16px 16px"
		});
		$("#attr_chk_" + type + "_" + valueId).attr("data-icon", upUrl);
		$("#attr_chk_" + type + "_" + valueId).prev().val(url);
		//图片上传成功后写color ID 和 URL
		$("#colorImage_urls_" + type + "_" + valueId).val(upUrl);
	}
	function delColorImage(adom) {
		var $a = $(adom);

		confirmDialog("是否移除添加的图片", function() {
			$a.parent().find("input[name='colorImgUrls']").val("");
			$a.parent().find("input[name='colorImgNames']").val("");
			var _id = $a.parent().find("input[name='photo']").data("id");
			var _type = $a.parent().find(".input_file").data("type");
			var _color = $("#attr_chk_" + _type + "_" + _id).data("oldicon");//获取之前的颜色值

			$("#ico_fileUp_" + _id).css("background", "#" + _color);
			$('input[name=salerRefrenceId][value="' + _id + '"]').next()
					.val("");

			$("#colorImage_urls_" + _type + "_" + _id).val(_color);
			$("#attr_chk_" + _type + "_" + _id).attr("data-icon", "");
		});
	}
	function setPosition(storageBtn) {
		var fileInputId = $(storageBtn).parents("tr").find(".input_file").attr(
				"id");
		$("#fileInputId").val(fileInputId);
	};
	
</script>