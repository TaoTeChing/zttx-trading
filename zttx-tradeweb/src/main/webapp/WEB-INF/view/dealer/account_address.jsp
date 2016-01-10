<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>管理中心-账户管理-收货地址</title>
		<link href="${res}/styles/dealer/global.css" rel="stylesheet" />
		<link href="${res}/styles/dealer/account.css" rel="stylesheet" />
		<link href="${res}/styles/dealer/dealerAddr.css" rel="stylesheet" />
		<link href="${res}/styles/common/validform.css" rel="stylesheet" />
		<style type="text/css">
			.ui-form-explain {
			    padding: 0;
			}
		</style>
		</head>
<body>
	<div class="container">
		<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
		<div class="em100">
			<div class="main clearfix pr">
				<jsp:include page="${ctx}/common/menuInfo/sidemenu">
					<jsp:param name="openId" value="12"/>
				</jsp:include>
				<div class="main-right">
					<jsp:include page="/WEB-INF/view/dealer/agency_header_message.jsp" />
					<div class="main-grid mb10">
						<div class="panel-crumbs">
							<i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> &gt;&gt; <a href="${ctx}/dealer/dealerInfo/account/infor" title="">账户管理</a> &gt; <span
								class="bb">收货地址</span> <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
						</div>
					</div>
                    <div class="inner">
                        <div class="main-grid mb20 clearfix">
                            <div class="address-title mb20">
                                <h3 id="yahei_title" class="yahei fs14">新增收货地址</h3>
                            </div>
                            <style>
                                #addressprovince{width:130px;margin-right: 5px;}
                                #addresscity{width:94px;margin-right: 5px;}
                                #addresscounty{width:100px;margin-right: 5px;}
                            </style>
                            <form:form id="ShippingAddress" name="ShippingAddress" data-widget="validator" class="ui-form">
                                <input id="refrenceId" name="refrenceId" type="hidden" value="" />
                                <input id="provinceName" name="provinceName" type="hidden" />
                                <input id="cityName" name="cityName" type="hidden" />
                                <input id="areaName" name="areaName" type="hidden" />
                                <div class="ui-form-item" style="height:35px;">
                                <label class="ui-label">选择所在地:</label>
                                <jsp:include page="${ctx}/common/regional/searchAllArea">
                                    <jsp:param value="${dealerInfo.province}" name="province" />
                                    <jsp:param value="${dealerInfo.city}" name="city" />
                                    <jsp:param value="${dealerInfo.county}" name="county" />
                                    <jsp:param value="address" name="sale" />
                                    <jsp:param value="ui-select seeking-select-width" name="style" />
                                </jsp:include>
                                </div>
                                <div class="ui-form-item" style="height:35px;">
                                    <label class="ui-label">街道地址:</label>
                                    <input id="dealerAddress" name="dealerAddress" class="ui-input" style="width:450px;" maxlength="120" minlength="5" data-display="街道地址"  required placeholder="请填写街道地址,最少5个字,最多不能超过120个字,不能全部为数字或字母" />
                                </div>
                                <div class="ui-form-item" style="height:35px;">
                                    <label class="ui-label">收货人姓名:</label>
                                    <input id="dealerName" name="dealerName" class="ui-input long" style="width:290px;" placeholder="收货人姓名:张三"  required data-display="收货人姓名" maxlength="25" />
                                </div>
                                <div class="ui-form-item" style="height:35px;">
                                    <label class="ui-label">电话:</label>
                                    <input id="dealerTel" name="dealerTel" class="ui-input long"  style="width:290px;" placeholder=""  data-display="电话"  />
                                </div>
                                <div class="ui-form-item" style="height:35px;">
                                    <label class="ui-label">手机:</label>
                                    <input id="dealerMobile" name="dealerMobile" class="ui-input long" style="width:290px;"  placeholder=""  data-display="手机" /> 电话与手机 至少填写一个
                                </div>
                                <div class="ui-form-item" style="height:35px;">
                                    <label class="ui-label">邮编:</label>
                                    <input id="postCode" name="postCode" class="ui-input long js-number" style="width:290px;"  placeholder="" data-display="邮编" maxlength="6" />
                                </div>

                                <div class="ui-form-item" style="height:35px;">
                                    <label class="ui-label">设为默认:</label>
                                    <span class="ui-form-text">
                                        <label><input id="dealerDefault" class="ui-checkbox" name="dealerDefault" type="checkbox" /> 设为默认收货地址</label>
                                    </span>
                                </div>
                                <div class="ui-form-item">
                                    <input id="dealerSubmit" type="submit" class="ui-button ui-button-mred" value="保 存" />
                                    <input id="dealerReset" type="reset" class="ui-button ui-button-mred"  style="display: none;" value="取 消" />
                                </div>
                            </form:form>
                        </div>
                        <div class="main-grid mb10 clearfix">
                            <form name="listForm" method="post" action="address">
                                <div class="panel-table">
                                    <div class="panel-table-title">
                                        <h3 class="yahei fs14 lh2">已保存的有效地址</h3>
                                    </div>
                                    <div class="panel-table-content">
                                        <table class="ui-table">
                                            <thead>
                                                <tr>
                                                    <th class="cell-1">收货人</th>
                                                    <th class="cell-2">所在地区</th>
                                                    <th class="cell-3">街道地址</th>
                                                    <th class="cell-4">电话/手机</th>
                                                    <th class="cell-5">邮编</th>
                                                    <th class="cell-6"></th>
                                                    <th class="cell-7">操作</th>
                                                </tr>
                                            </thead>
                                            <tbody id="address_datas" name="datas">
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <td colspan="7" id="page_datas" class="pagination">
                                                         <div class="pagination" id="pagination">
                                                        </div>
                                                    </td>
                                                </tr>
                                            </tfoot>
                                        </table>

                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp" />
	</div>
	<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
	<script id="address-templage" type="text/html">
	{{each rows}}
            <tr {{if $value.dealerDefault}}class="on"{{/if}} class="address_tr">
				<td class="cell-1">{{$value.dealerName}}</td>
				<td class="cell-2">{{$value.provinceName}} {{$value.cityName}} {{$value.areaName}} </td>
				<td class="cell-3"><span title="{{$value.dealerAddress}}">{{$trimLongString $value.dealerAddress 15}}</td>
				<td class="cell-4">
				   {{$value.dealerTel}} {{$value.dealerMobile}}
				</td>
				<td class="cell-5">
				   {{$value.postCode}}
				</td>
				<td class="cell-6">
						{{if $value.dealerDefault}}<span>默认地址</span>{{/if}}
						{{if $value.dealerDefault==false}}<a href="javascript:defaultAddr('+ {{$value.refrenceId}}+ ')">设置默认</a>{{/if}}
				</td>
				<td class="cell-7">
               		<a class="template_col6_edit" href="javascript:void(0)" data-id="{{$value.refrenceId}}"><i class="icon i-pencil"></i></a>
                	<a class="template_col6_delete" href="javascript:void(0)" data-id="{{$value.refrenceId}}"><i class="icon i-close-x"></i></a>
                </td>
			</tr>
	{{/each}}
		{{ if rows.length == 0 }}
				<tr>
					<td colspan="7" style="text-align:center;">暂无数据</td>
				</tr>
			{{ /if }}
	</script>
	
	<script>

		 seajs.use(["validator", "widget","template","pagination"], function (Validator, Widget,template,Pagination) {
			 Widget.autoRenderAll();
				
			 Validator.addRule('tel', /^((\+?[0-9]{2,4}\-[0-9]{3,4}\-)|([0-9]{3,4}\-))?([0-9]{7,8})(\-[0-9]+)?$/, '请输入正确的{{display}}格式');
			 Validator.addRule('mobile', /^(1[3-8])\d{9}$/, '请输入正确的{{display}}格式');
			 Validator.addRule('address', function (){ alert($('input[name="county"]').attr('style')); var city = $('input[name="county"]').val();if(city == '' || city == '请选择区'){return false;}return true;}, '地址不能为空!');
							 
			 var Core = Validator.query('#ShippingAddress');
			 
			    Core.addItem({
					element : '[name=dealerTel]',
					required : function() {
						var tel = $('#dealerTel').val();
						var mobile = $('#dealerMobile').val();
						return ((tel == ''||tel=='电话与手机 至少填写一个') &&(mobile == ''||mobile == '电话与手机 至少填写一个'));
					},
					rule : 'tel'
				}).addItem({
					element : '[name=dealerMobile]',
					required : function() {
						var tel = $('#dealerTel').val();
						var mobile = $('#dealerMobile').val();
						return ((tel == ''||tel=='电话与手机 至少填写一个') &&(mobile == ''||mobile == '电话与手机 至少填写一个'));
					},
					rule : 'mobile'
				}).addItem({
		        	element : '[name=county]',
		        	//rule: 'address'
		        	required : function (){ 
		        		//var city = $('#addresscounty').val();
		        		var city = $('#addresscity').val();
		        		if(city==''||city=='请选择市'){
		        			return true;
		        		}
		        		if($('#addresscounty:visible').size()==0){
		        			return false;
		        		}
		        		return true;
		        	}
		        });
		        
			    
			    
		        Core.set("autoSubmit", false);
		        Core.on('formValidated', function (error) {
		            if (!error) {
		            	 formSubmit();
		            }
		        });
		        
		       template.helper('$trimLongString', trimLongString);
		       window['g_address_page']= new Pagination({
                    url: "${ctx}/dealer/dealerAddr/address/list",
                    elem: "#pagination",
                    handleData: function (data) {
                        $("#address_datas").html(template.render("address-templage", data));
                    }
                });
		 });

		function formSubmit() {
			///dealer/address/save
			$.post("${ctx}/dealer/dealerAddr/save", $('#ShippingAddress').serialize(),
				function(data) {
					if (data.code == 126000) {
						$("#ShippingAddress")[0].reset();
						ShippingAddressReset();
						g_address_page.render();
						remind("success", "保存成功！");
					} else {
						remind("error", data.message);
						//$("#error_message").text(data.message);
					}
				}, "json");

		}

		function deleteAddr(uid) {
			$.post("${ctx}/dealer/dealerAddr/address/delete", "uuid=" + uid, function(data) {
				$("#ShippingAddress")[0].reset();
				ShippingAddressReset();
				g_address_page.render();
			}, "json");
		}

		function defaultAddr(uid) {
			$.post("${ctx}/dealer/dealerAddr/setDef", "addrId=" + uid, function(data) {
				$("#ShippingAddress")[0].reset();
				ShippingAddressReset();
				g_address_page.render();
				remind("success", "设置成功！");
			}, "json");
		}

		function loadAddr(uid) {
			$.post("${ctx}/dealer/dealerAddr/address/load", "uuid=" + uid, function(data) {
				ShippingAddressSetValue(data.object);
			}, "json");
		}

		function ShippingAddressSetValue(obj) {
			$("#dealerName").val(obj.dealerName);
			$("#refrenceId").val(obj.refrenceId);
			$("#dealerAddress").val(obj.dealerAddress);
			$("#postCode").val(obj.postCode);
			$("#dealerMobile").val(obj.dealerMobile);
			$("#dealerTel").val(obj.dealerTel);
			$("#provinceName").val(obj.provinceName);
			$("#cityName").val(obj.cityName);
			$("#areaName").val(obj.areaName);
            $("#dealerDefault").prop("checked", obj.dealerDefault);
			var code_country = obj.dealerAddr;
			var code_provice = parseInt(code_country / 10000) * 10000;
			var code_city = parseInt(code_country / 100) * 100;
			selProCity($("#addressprovince"), $("#addresscity"),$("#addresscounty"), code_provice, code_city, code_country);
			$("#yahei_title").text("编辑收货地址");
			$("#dealerReset").show();
		}
		function ShippingAddressReset() {
            $("#ShippingAddress")[0].reset();
            $("#addressprovince").val("");
			$("#yahei_title").text("新增收货地址");
			$("#dealerReset").hide();
		}

		$(function() {
			$("#dealerReset").click(function() {
				ShippingAddressReset();
			});
			
			/**编辑*/
			$(".panel-table-content tbody").on("click",".template_col6_edit",function(){
				loadAddr($(this).data("id"));
			});
			
			/**删除*/
			$(".panel-table-content tbody").on("click",".template_col6_delete",function(){
				var addressid=$(this).data("id");
				confirmDialog("确定删除？", function() {
					deleteAddr(addressid);
				});
			});
			
			$(".panel-table-content tbody").on("mouseenter mouseleave","tr",function(ev){
				if(ev.type == "mouseenter"){
					$(this).addClass("hover");
				}else if(ev.type == "mouseleave"){
					$(this).removeClass("hover");
				}
			});

            $("#dealerTel,#dealerMobile").keyup(function(){
                resetValidatorError("#ShippingAddress");
            });

		});

	</script>
</body>
</html>


