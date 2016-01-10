<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>购物车-订单审核</title>
<link rel="stylesheet" href="${res}/styles/dealer/global.css" />
<link rel="stylesheet" href="${res}/styles/dealer/purchases.css" />

<style type="text/css">
.select_none {
	display: none;
}
.pur-address p{
	word-break: break-all;
}
</style>
</head>
<body>
	<div class="container">
					<div class="main-grid mb10">
						<div class="panel">
							<div class="address_list pb40">
								<h3 class="lh2 yahei fs18">确认收货地址：<a class="add-address link" href="javascript:;" data-addrcode="000000"><i class="iconfont">&#xe615;</i>使用新地址</a></h3>
								<ul id="dealer_address_list">
									<c:forEach items="${dealerAddrs}" var="dealerAddr">
										<li <c:if test="${dealerAddr.dealerDefault}">class="hover"</c:if> id="li_${dealerAddr.refrenceId}">
											<div>
												<span class="fl"> <label class="sendto">寄送至</label>
												<input class="ui-radio" type="radio" <c:if test="${dealerAddr.dealerDefault}">checked='checked'</c:if> autocomplete="off"/> 
												<span class="address">${dealerAddr.provinceName} ${dealerAddr.cityName} ${dealerAddr.areaName} <span title='${dealerAddr.dealerAddress}'>${fns:trimLongString(dealerAddr.dealerAddress, 20)}</span>  ${dealerAddr.dealerName }${empty dealerAddr.dealerTel?'':'/'}${dealerAddr.dealerTel}${empty defaultAddr.postCode?'':'/'}  ${dealerAddr.postCode }</span> </span>
												<span class="fr"> 
												<c:if test="${dealerAddr.dealerDefault}">
													<a class="defadd default" style="cursor: pointer;" data-id="${dealerAddr.refrenceId}">默认地址</a>
												</c:if>
												<c:if test="${dealerAddr.dealerDefault!=true}">
													<a class="defadd" style="cursor: pointer;" data-id="${dealerAddr.refrenceId}">设为默认地址</a> 
												</c:if>
												<a class="edit-address link" style="cursor: pointer;" data-area="${dealerAddr.areaName}" data-city="${dealerAddr.cityName}"
													data-province="${dealerAddr.provinceName}" data-id="${dealerAddr.refrenceId}"
													data-addrcode="${dealerAddr.dealerAddr}" data-address="${dealerAddr.dealerAddress}"
													data-dealername="${dealerAddr.dealerName}" data-dealertel="${dealerAddr.dealerTel}"
													data-dealermobile="${dealerAddr.dealerMobile}" data-postcode="${dealerAddr.postCode}">修改本地址</a> </span>
											</div></li>
									</c:forEach>
								</ul>

							</div>
						</div>
					</div>
					<div class="main-grid mb10 purchases-table">
						<h3 class="lh2 yahei fs18">确认订单信息：</h3>
						<c:set var="maxIndex" value="0" />
						<c:set var="product_total_count" value="0" />
						<c:set var="product_total_money" value="0" />
						<c:forEach var="map" items="${model}" varStatus="sta">
							<div class="ui-table-container">
								<div class="ui-table-title">品牌: ${map.key}</div>
								<table class="ui-table">
									<colgroup>
										<col class="cell-20" />
										<col class="cell-15" />
										<col class="cell-10" />
										<col class="cell-10" />
										<col class="cell-10" />
										<col class="cell-10" />
										<col class="cell-10" />
										<col class="cell-15" />
									</colgroup>
									<thead>
										<tr>
											<th>商品信息</th>
											<th>颜色</th>
											<th>尺码</th>
											<th>单价</th>
											<th>数量</th>
											<th>金额</th>
											<th>数量小计</th>
											<th>金额小计</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach var="product" items="${map.value}" varStatus="sta_">

											<c:if test="${product.buyTotal!=0 }">
												<tr>

													<td align="center" valign="middle" style="padding: 10px;line-height: inherit;border-right: 1px solid #d9d9d9;"><img
														src="${res}${product.domainName}${fns:getFormateURL(product.image)}" width="60px" height="60px" /> <br />
													<a href="#">货号:${product.pNo}</a></td>
													<td colspan="5" style="vertical-align:top;">
														<c:set var="product_count" value="0" /> <c:set var="product_money" value="0" />
														<table class="ui-table ui-table-noborder" >
															<colgroup>
																<col class="cell-30">
															</colgroup>
															<tbody>
																<c:forEach var="att" items="${product.attrList}">
																	<c:set var="product_item_image_url" value="${fns:getFormateURL(att.logo) }" />
																	<tr>
																		<td colspan="5" >
																			<table class="ui-table ui-table-noborder" >
																				<colgroup>
																					<col class="cell-15" />
																					<col class="cell-10" />
																					<col class="cell-10" />
																					<col class="cell-10" />
																					<col class="cell-10" />
																				</colgroup>
																				<tbody>
																					<c:forEach var="att_son" items="${att.listShoperAtt}" varStatus="sta3">
																						<c:if test="${att_son.buyCount!=null&&att_son.buyCount!=0}">
																							<c:set var="product_count" value="${product_count+att_son.buyCount}" />
																							<c:set var="product_money" value="${product_money+att_son.price*att_son.buyCount}" />
																							<tr>
																								<td style="text-align:left">
                                                                                                    <div style="padding-left:40px">
                                                                                                    <img src="${res}${product.domainName}${product_item_image_url}" class="goods-color" style="width:15px;height:15px;margin-left:-20px;vertical-align: -3px"/>
                                                                                                    ${att.attributeValue}
                                                                                                    </div>
                                                                                                </td>
																								<td>${att_son.attributeValue}</td>
																								<td>${att_son.price}</td>
																								<td>${att_son.buyCount}</td>
																								<td>${att_son.price*att_son.buyCount}</td>
																							</tr>
																						</c:if>
																						<%--
																						<c:if test="${att_son.buyCount==null||att_son.buyCount==0}">
																							<c:set var="product_count" value="${product_count+att_son.buyCount}" />
																							<c:set var="product_money" value="${product_money+att_son.price*att_son.buyCount}" />
																							<tr>
																								<td>${att_son.attributeValue}</td>
																								<td>${att_son.price}</td>
																								<td>0</td>
																								<td>${att_son.price*att_son.buyCount}</td>
																							</tr>
																						</c:if>
																						 --%>
																					</c:forEach>
																				</tbody>
																			</table>
                                                                        </td>
																	</tr>
																</c:forEach>
															</tbody>
														</table></td>

													<c:set var="product_total_count" value="${product_count+product_total_count}" />
													<c:set var="product_total_money" value="${product_money+product_total_money}" />

													<td style="border-left: 1px solid #d9d9d9;border-right: 1px solid #d9d9d9;">${product_count }</td>
													<td>${product_money }</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>

							</div>
						</c:forEach>
						<div class="pur-total">
							<div class="pur-address">
								<p id='address_show_p'>寄送至:${defaultAddr.provinceName} ${defaultAddr.cityName} ${defaultAddr.areaName}</p>
								<p id="address_show_d">${defaultAddr.dealerAddress}</p>
								<p id='person_show_p'>收货人:${defaultAddr.dealerName } ${defaultAddr.dealerMobile}</p>
							</div>
							<div class="pur-operate">
							<div style="overflow:hidden;">
                                    <div class="fl">
                                        <span style="vertical-align: top;color: #999;">给品牌商留言：</span>
                                        <textarea style="height: 22px;resize: none;width: 270px;" id="remark_text_id" ></textarea>
                                    </div>
                                    <div class="fr">*您可以提交订单后和品牌商协商运费</div>
                            </div>
                             <div class="bgc-ch ta-r">进货总量:<span class="c-r fs18 yahei bb">${product_total_count }</span> 件 | 总货款(不含运费)：￥<span class="c-r fs18 yahei bb">${product_total_money }</span> <a class="ui-button ui-button-lred yahei fs18"
										id="order_submit">提交订单</a></div>
							</div>
						</div>
					</div>
		<div style="display: none;">
			<form:form id="order_comfirm" action="/dealer/order/confirm">
				<input type="hidden" name="addressId" id="address_select_id" value='${defaultAddr.refrenceId }'>
				<input type="hidden" name="remark" id="remark_edit_id" value=''>
				<c:forEach items="${dealerShorperIds}" var="dealerShorperId">
					<input type="hidden" name="dealerShorperIds" value=${dealerShorperId }>
				</c:forEach>
			</form:form>
			<form:form id="order_payment" action="${ctx}/dealer/order/payment?isClient=true" >
				<input name="orderIds" value="" id="orderIds_id"/>
			</form:form>
		</div>
	</div>
	<div class="hide">
		<div id="Edit_Address">
			<div class="ui-head">
				<h3>修改当前地址</h3>
			</div>
			<form:form class="ui-form pt20" id="address_form_edit"  data-widget="validator">
				<div class="ui-form-item" style="height:40px;">
					<input type="hidden" id="refrenceId" name="refrenceId"> <label for="" class="ui-label">选择所在地： </label>
					<input type="hidden" id=smartAddr value="${country }"> 
					
					<jsp:include page="${ctx}/client/Regional/searchaAll">
						<jsp:param value="${province}" name="regProvince" />
						<jsp:param value="${city}" name="regCity" />
						<jsp:param value="${country }" name="dealerAddr" />
						<jsp:param value="address" name="sale" />
						<jsp:param value="ui-select" name="style" />
					</jsp:include>
					
					
				</div>
				<div class="ui-form-item" style="height:60px;">
					<label class="ui-label">街道地址：</label>
					<textarea class="ui-textarea" name="dealerAddress" id='dealerAddress' style="width:305px;height:50px;" maxlength="120" minlength="5"  data-display="街道地址"  required placeholder="请填写街道地址,最少5个字,最多不能超过120个字,不能全部为数字或字母"></textarea>
				</div>
				<div class="hr-dashed mb20"></div>
				<div class="ui-form-item" style="height:40px;">
					<label class="ui-label">收货人姓名：</label><input class="ui-input" name='dealerName' id='dealerName'
						style="width:160px;"  placeholder="收货人姓名:张三"  required data-display="收货人姓名" maxlength="25" />
				</div>
				<div class="ui-form-item" style="height:40px;">
					<label class="ui-label">电话：</label><input class="ui-input" style="width:160px;" name="dealerTel" id="dealerTel"
						placeholder=""  data-display="电话"/>
				</div>
				<div class="ui-form-item" style="height:40px;">
					<label class="ui-label">手机：</label><input class="ui-input" style="width:160px;" datatype="m" name='dealerMobile'
						id='dealerMobile'  placeholder=""  data-display="手机"  />电话与手机 至少填写一个
				</div>
				<div class="ui-form-item">
					<input id='comfirmbtn' class="ui-button ui-button-mred comfirm" type="submit" value="保存修改" /> <input
						class="ui-button ui-button-mred cancel" type="reset" value="取 消" />
				</div>
			</form:form>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
	<!--[if lte IE 8]>
	<script type="text/javascript" charset="utf-8" src="${res}/scripts/dealer/json2.js" ></script>
	<![endif]-->
	<script src="${res}/scripts/plugin/Validform_v5.3.2_min.js"></script>
	<script>
		var purchasesAddrss = {};

		seajs.use([ '$', 'dialog' ], function($, Dialog) {
			$(".address_list").on("click","li",function() {
						$(".address_list li").removeClass("hover");
						$(".address_list li input.ui-radio").prop('checked',false);
						$(this).addClass("hover");
						$(this).find('input.ui-radio').prop('checked', true);
						//同步显示 低部 地址信息
						purchasesAddrss.setDisplayAddress($(this).find('.edit-address.link'));
					});

			 window['addressDlg'] = new Dialog({content : '#Edit_Address',width : 720});

			$(document).on("click", ".edit-address,.add-address", function() {
				addressDlg.show();
				purchasesAddrss.loadDealerAddr(this);
			});
		});

		/**关闭地址框同时重置表单*/
		purchasesAddrss.closeAddressDlg = function() {
			addressDlg.hide();
			$('#address_form_edit')[0].reset();
		};
		/**收货地址为设为 默认地址*/
		purchasesAddrss.setDealerAddrDefault = function() {
			 var domA=$(this);
			$.post("/dealer/address/dealerDefault", {"uuid" : $(this).data("id")}, function(data) {
				if (data.code == 0) {
					$('a.defadd').removeClass("default").html('设置默认地址');
					domA.addClass("default").html('默认地址');
					remind("success", "设置默认地址成功!");
				} else {
					remind("error", "设置默认地址失败!");
				}
			}, "json");
		};

		/**载入修改地址表单*/
		purchasesAddrss.loadDealerAddr = function(obj) {
			var code_country = $(obj).data('addrcode');
			var code_provice = parseInt(code_country / 10000) * 10000;
			var code_city = parseInt(code_country / 100) * 100;
			$('#Edit_Address div.ui-head h3').html(code_country == '000000' ? '新增收货地址' : '修改当前地址');
			$('#comfirmbtn').val(code_country == '000000' ? '确定新增' : '保存修改');
			if(code_country == '000000'){
				var _code_country = $('#smartAddr').val();
				var _code_provice = parseInt(_code_country / 10000) * 10000;
				var _code_city = parseInt(_code_country / 100) * 100;
				selProCity($("#addressprovince"), $("#addresscity"),$("#addresscounty"), _code_provice, _code_city, code_country);
			}else{
				selProCity($("#addressprovince"), $("#addresscity"),$("#addresscounty"), code_provice, code_city, code_country);
			}
			var text_address = $(obj).data('address');
			var text_dealerName = $(obj).data('dealername');
			var text_dealerTel = $(obj).data('dealertel');
			var text_dealerMobile = $(obj).data('dealermobile');
			var text_id = $(obj).data('id');
			$('#dealerAddress').val(text_address);
			$('#dealerName').val(text_dealerName);
			$('#dealerTel').val(text_dealerTel);
			$('#dealerMobile').val(text_dealerMobile);
			$('#refrenceId').val(text_id);
		};

		/**修改收货地址*/
		purchasesAddrss.modDealerAddr = function() {
			$.post("/dealer/address/dealerDefault", {"uuid" : $(this).data("id")}, function(data) {
				remind("success", "设置成功!");
			}, "json");
		};

		/**新增一个收货地址*/
		purchasesAddrss.addDealerAddr = function() {
			$.post("/dealer/address/dealerDefault", {"uuid" : $(this).data("id")}, function(data) {
				remind("success", "设置成功!");
			}, "json");
		};
		/**确认一个收货地址*/
		purchasesAddrss.comfireDealerAddr = function() {
			var isNew = $('#address_form_edit #refrenceId').val() == ""|| $('#address_form_edit #refrenceId').val() == null;
			$.post("/dealer/address/save",$('#address_form_edit').serialize(),function(data) {
				if (data.code == 0) {
					var html = [];
					html.push('<li id="li_'+ data.object.refrenceId+ '"'+ (data.object.dealerDefault ? 'class="hover"': '') + '>');
					html.push('	<div>');
					html.push('		<span class="fl" style="float: none;">');
					html.push('			<label class="sendto">寄送至</label>');
					html.push('			<input class="ui-radio" type="radio" '+ (data.object.dealerDefault ? 'checked="checked"': '') + '/>');
					var addrDetails = data.object.provinceName+ ' ' + data.object.cityName + ' '+ data.object.areaName + ' '
							+ '<span title="'+data.object.dealerAddress+'">'+(data.object.dealerAddress.length>20?data.object.dealerAddress.substring(0,20)+'...':data.object.dealerAddress) + '</span> ('+ data.object.dealerName + ') '+ data.object.dealerTel;
					html.push('			<span class="address">'+ addrDetails + '</span>');
					html.push('		</span>');
					html.push('		<span class="fr">');
					html.push('			<a class="defadd" style="cursor: pointer;" data-id="'+data.object.refrenceId+'">默认地址</a>');
					html.push('			<a class="edit-address link" style="cursor: pointer;" data-area="'+data.object.areaName+'"  data-city="'+data.object.cityName+'" data-province="'+data.object.provinceName+'" data-id="'+data.object.refrenceId+'" data-addrcode="'+data.object.dealerAddr+'" data-address="'+data.object.dealerAddress+'"  data-dealername="'+data.object.dealerName+'"  data-dealertel="'+data.object.dealerTel+'"  data-dealermobile="'+data.object.dealerMobile+'">修改本地址</a>');
					html.push('		</span>');
					html.push('	 </div>');
					html.push('</li>');
					if (isNew) {
						$('#dealer_address_list').append(html.join(""));
						remind("success", "新增成功!");
					} else {
						$('#li_'+ $('#address_form_edit #refrenceId').val()).replaceWith(html.join(""));
						remind("success", "修改成功!");
					}
					purchasesAddrss.setDisplayAddress($('#li_'+ data.object.refrenceId+ ' .edit-address.link'));
					var uuid=data.object.refrenceId;
					$.post("/dealer/address/dealerDefault", {"uuid" : uuid}, function(data) {
						if (data.code == 0) {
							$(".address_list li").removeClass("hover");
							$(".address_list li input.ui-radio").prop('checked',false);
							$('#li_'+uuid).addClass("hover");
							$('#li_'+uuid).find('input.ui-radio').prop('checked', true);
							$('a.defadd').removeClass("default").html('设置默认地址');
							$('#li_'+uuid+' a.defadd').addClass("default").html('默认地址');
						}
					}, "json");
					purchasesAddrss.closeAddressDlg();
				}
			}, "json");
			return false;
		};
		/**表单验证初始化*/
		purchasesAddrss.initValidform = function() {
			baseFormValidator({
		        selector:'#address_form_edit',isAjax:true,
		        addElemFn:function(Core,Validator){
		        	 Validator.addRule('tel', /^((\+?[0-9]{2,4}\-[0-9]{3,4}\-)|([0-9]{3,4}\-))?([0-9]{7,8})(\-[0-9]+)?$/, '请输入正确的{{display}}格式');
					 Validator.addRule('mobile', /^(1[3-8])\d{9}$/, '请输入正确的{{display}}格式');
					 Validator.addRule('address', function (){  var city = $('input[name="county"]').val();if(city == '' || city == '请选择区'){return false;}return true;}, '地址不能为空!');
					 
		        	Core.addItem({
							element : '[name=dealerTel]',
							required : function() {
								var tel = $('input[name="dealerTel"]').val();
								var mobile = $('input[name="dealerMobile"]').val();
								return tel == '' && mobile == '';
							},
							rule : 'tel'
						}).addItem({
							element : '[name=dealerMobile]',
							required : function() {
								var tel = $('input[name="dealerTel"]').val();
								var mobile = $('input[name="dealerMobile"]').val();
								return tel == '' && mobile == '';
							},
							rule : 'mobile'
						}).addItem({
				        	element : '[name=county]',
				        	rule: 'address',
				        	required :true
				        });
		        },
		        beforeSubmitFn:function(){
		        	purchasesAddrss.comfireDealerAddr();
                }
		        
		    });
		};
		/**设置选中地址栏后,下部地址更新显示*/
		purchasesAddrss.setDisplayAddress = function(address) {
			var addressDetail = "寄送至:" + address.data('province') + " "
					+ address.data('city') + " " + address.data('area');
			var personInfo = "收货人:" + address.data("dealername") + " "
					+ address.data("dealermobile");
			$('#address_show_p').html(addressDetail);
			$('#person_show_p').html(personInfo);
			$("#address_show_d").html(address.data('address'))
			$('#address_select_id').val(address.data('id'));
		};

		/**确认生成采购订单*/
		purchasesAddrss.confirmCreateOrder = function() {
			$.post("${ctx}/dealer/order/confirm",$("#order_comfirm").serialize(),function(data){
				var $this=$("#remark_edit_id").val();
				if($this.length>1000){
				  remind("error","留言最长为1000字！");
				  return false;
				}
				
				  if(data.code==126000){
						$('#orderIds_id').val(data.object.join(","));
						$("#order_payment")[0].submit();
					}else{
						remind("error", data.message);
					}
				},"json");
		};

		$(function() {
			//注册设置默认地址事件
			$('span.fr').on("click", "a.defadd",
					purchasesAddrss.setDealerAddrDefault);
			$('#Edit_Address').on("click",
					"input.ui-button.ui-button-mred.cancel",
					purchasesAddrss.closeAddressDlg);
			$('#order_submit').on("click", purchasesAddrss.confirmCreateOrder);
			purchasesAddrss.initValidform();
			$('#remark_text_id').on("change",function(){
				$('#remark_edit_id').val($(this).val());
			});
			
		});
	</script>

</body>
</html>
