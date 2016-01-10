﻿<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-服务管理-服务介绍</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/service.css" rel="stylesheet" />
</head>
<body>
    <div class="container myServices">
		<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->
				<jsp:include page="${ctx}/common/menuInfo/sidemenu"/>

                <div class="main-right">
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href=${ctx}/dealer/webServiceItems/index>服务管理</a> >> <span class="bb">服务介绍</span>
                            <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="inner">
                        <div class="detail_con clearfix">
                <div class="side_bar">
                    <div class="icon_pic common_module js_img_center">
                        <img src="${res}${webServiceItems.servicePhoto}" alt="">
                    <span></span></div>
                    <div class="service_intro common_module mt10">
                        <div class="contact">
                            <div class="item">
                                <label for="">服务商：</label>${webServiceCom.comName }
                            </div>
                            <div class="item">
                                <label for="">资质认证：</label>${webServiceCom.comCert }
                            </div>
                            <div class="item">
                                <label for="">联系电话：</label>${webServiceCom.comTel }
                            </div>
                            <div class="item">
                                <label for="">联系邮箱：</label>${webServiceCom.comEmail }
                            </div>
                        </div>
                        <div class="intro">
                            <h3>服务商简介：</h3>
                            <p>
                            ${webServiceCom.subMark }
                            </p>
                        </div>
                    </div>
                </div>
                <div class="right">
                    <div class="title clearfix">
                        <h2>${webServiceItems.serviceName}</h2>
                        <div class="rating">
	                        	<c:forEach  begin="1" end="${webServiceItems.commentNum}" var="i">
	                        		<span class="star"></span>
	                        	</c:forEach>
                        </div>
                    </div>
                    <div class="intro">
                    	 ${webServiceItems.subMark}
                    </div>
                    <div class="property">
                        <div class="item">
                            <label for="">单价：</label>
                            <strong  class="price"><fmt:formatNumber pattern="0.00" >${webServiceItems.price}</fmt:formatNumber></strong>&nbsp;元
                        </div>
                        <div class="item mt10">
                            <label for="">数量：</label>
                            <span>
                            	<input type="hidden" value="${webServiceItems.price}" id="service_price"/>
                            	<input type="hidden" value="${webServiceItems.refrenceId}" id="service_id"/>
                                <input id="service_buycount" class="js-number-plugin self-ui-input" type="text" data-max="9999" data-min="${webServiceItems.minBuyNum}" value="${webServiceItems.minBuyNum}" style="width:40px;text-align: center;" />
                            </span>
                        </div>
                        <div class="item mt10">
                            <label for="">总价：</label>
                            <strong id="display_price" class="price"><fmt:formatNumber pattern="0.00">${webServiceItems.servicePrice}</fmt:formatNumber></strong>&nbsp;元
                        </div>
                        <div class="operate mt20">
                            <a id="service_buy_button" class="ui-button ui-button-lred js_show_tip" href="javascript:;">立即购买</a>
                            <c:if test="${webServiceItems.refrenceId!='S006'}">
                            	<span class="buy_person">已有${webServiceItems.buyNum}人购买</span>
                            </c:if>
                        </div>
                    </div>
                    <div class="ui_tab main_ui_tab">
                        <ul class="js_tab_box ui_tab_items">
                            <li class="ui_tab_item current">
                                <a href="javascript:;">服务详情</a>
                            </li>
                            <%--<li class="ui_tab_item">
                                <a href="javascript:;">相关评价（30)</a>
                            </li>--%>
                        </ul>
                    </div>
                    <div class="tab_con">
                        <div id="edit_con" class="edit_con">
                            <!-- 这里为编辑器读取的内容 -->
                            <p class="mt15">
                            ${webServiceItems.serviceMark}
                            </p>
                        </div>
                    </div>
                </div>
            </div>
                    </div>
                </div>
            </div>
            <form:form id="service_payment" method="POST" action="${ctx}/dealer/orderPay/pay" >
				<input type="hidden" name="orderIdArr" id="orderIdArr_id" value=''>
				<input type="hidden" name="orderType" value='1'>
			</form:form>
        </div>
         <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
         
         <div class="hide">
	         <div id="table_address_list">
	         <div class="panel">
				<div class="address_list pb40">
					<h3 class="lh2 yahei fs18">确认收货地址：<a class="add-address link" href="javascript:;" data-addrcode="000000" ><i class="icon i-add-blue"></i>使用新地址</a></h3>
					<ul id="dealer_address_list">
                    <c:forEach items="${dealerAddrs}" var="dealerAddr">
                        <li <c:if test="${dealerAddr.dealerDefault}">class="hover"</c:if> id="li_${dealerAddr.refrenceId}">
                            <div>
                                <span class="fl"> <label class="sendto">寄送至</label>
                                <input class="ui-radio" type="radio" <c:if test="${dealerAddr.dealerDefault}">checked='checked'</c:if> autocomplete="off"/>
                                <span class="address">${dealerAddr.provinceName}&nbsp;${dealerAddr.cityName}&nbsp;${dealerAddr.areaName}<span title='${dealerAddr.dealerAddress}'>${fns:trimLongText(dealerAddr.dealerAddress, 20)}</span>&nbsp;(${dealerAddr.dealerName })&nbsp;
                                ${empty dealerAddr.dealerMobile?'':dealerAddr.dealerMobile}${empty dealerAddr.dealerTel?'':' '}${empty dealerAddr.dealerTel?'':dealerAddr.dealerTel}${empty dealerAddr.postCode?'':' '} ${dealerAddr.postCode }</span> </span>
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
                     <input id='comfirmbuybtn' class="ui-button ui-button-mred comfirm" type="submit" value="确 定" />
                     <input class="ui-button ui-button-mred cancel" type="button" value="取 消" onclick="g_tbl_address_dlg.hide()" />
                 </div>
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
					
					<jsp:include page="${ctx}/common/regional/searchAllArea">
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
				<div class="ui-form-item" style="height:40px;">
					<label class="ui-label">邮编：</label><input class="ui-input js-number" style="width:160px;" datatype="m" name='postCode'
						id='postCode'  placeholder="" data-display="邮编" maxlength="6" />
				</div>
				<div class="ui-form-item">
					<input id='comfirmbtn' class="ui-button ui-button-mred comfirm" type="submit" value="保 存" /> <input
						class="ui-button ui-button-mred cancel" type="button" value="取 消" onclick="g_edit_address_dlg.hide()"/>
				</div>
			</form:form>
		</div>
	</div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    
    <script>
        var service_detail = {
            init: function () {
                this.tabSwitch(); //tab切换
               // this.showTip();
            },
            tabSwitch: function () {
                $(".js_tab_box").on("click", "li", function () {
                    var index = $(this).index();
                    $(this).addClass("current").siblings().removeClass("current");
                    if (index == 0) {
                        $("#edit_con").show();
                    } else if (index == 1) {
                        $("#edit_con").hide();
                    }
                })
            },
            showTip: function () {
                seajs.use("tip", function (Tip) {
                    var t = new Tip({
                        trigger: $(".js_show_tip"),
                        width: 230,
                        theme: 'yellow',
                        inViewport: true,
                        arrowPosition: 11
                    });
                    t.before('show', function () {
                        var content = "当前已购买套餐500条，继续购买将会累加";
                        this.set('content', content);
                    });
                });
            }
        };
        service_detail.init();
        
        /**************功能 新增****************/
        /**购买数量变化*/
        $(".js-number-plugin").numberPlugin({},function(a,b){
        	var amount=$('#service_price').val()*a;
            $('#display_price').html(amount.toFixed(1));
        });
        
        /**购买按钮点击*/
        $('#service_buy_button').click(function(){
        	if($('#service_id').val()=='S006'){
        		service_detail.openSelectAddrssWin();
        	}else{
        		service_detail.toPayServiceFee();
        	}
        });
        
        /**支付服务费用*/
        service_detail.toPayServiceFee=function(addressId){
        	jQuery.post("${ctx}/dealer/webServiceItems/buy",{buyNum:$('#service_buycount').val(),serviceId:$('#service_id').val(),chargType:1,addressId:addressId},function(data){
       		 if(data.code==126000){
					$('#orderIdArr_id').val(data.object);
					$("#service_payment")[0].submit();
				}else{
					remind("error", data.message);
				}
       		},"json");
        };
        
        /**打开地址选择对话框*/
        service_detail.openSelectAddrssWin=function(){
        	seajs.use(["dialog"], function (Dialog) {
        		window['g_tbl_address_dlg']= new Dialog({content:"#table_address_list",width:890}).show();
        	});
        };
        
        /**设置默认地址*/
        service_detail.setDealerAddrDefault = function() {
			var domA=$(this);
			$.post("${ctx}/dealer/address/dealerDefault", {"uuid" : domA.data("id")}, function(data) {
				if (data.code == 0) {
					$('a.defadd').removeClass("default").html('设置默认地址');
					domA.addClass("default").html('默认地址');
					remind("success", "设置默认地址成功!");
				} else {
					remind("error", "设置默认地址失败!");
				}
			}, "json");
		};
		
        /**载入地址信息*/
        service_detail.loadDealerAddr = function(obj) {
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
			var text_postCode = $(obj).data('postcode');
			$('#dealerAddress').val(text_address);
			$('#dealerName').val(text_dealerName);
			$('#dealerTel').val(text_dealerTel);
			$('#dealerMobile').val(text_dealerMobile);
			$('#postCode').val(text_postCode);
			$('#refrenceId').val(text_id);
		};
        
		/**初始表单验证*/
		service_detail.initValidform = function() {
			baseFormValidator({
		        selector:'#address_form_edit',isAjax:true,
		        addElemFn:function(Core,Validator){
		        	 Validator.addRule('tel', /^((\+?[0-9]{2,4}\-[0-9]{3,4}\-)|([0-9]{3,4}\-))?([0-9]{7,8})(\-[0-9]+)?$/, '请输入正确的{{display}}格式');
					 Validator.addRule('mobile', /^(1[3-8])\d{9}$/, '请输入正确的{{display}}格式');
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
				        	required : function (){ 
				        		var city = $('#addresscity').val();
				        		if(city==''||city=='请选择市'){
				        			return true;
				        		}
				        		if($('#addresscounty').attr('style')=='display: none;'){
				        			return false;
				        		}
				        		return true;
				        	}
				        });
		        },
		        beforeSubmitFn:function(){
		        	service_detail.comfireDealerAddr();
                }
		    });
		};
		
		/**确认修改与新增*/
		service_detail.comfireDealerAddr = function() {
			var isNew = $('#address_form_edit #refrenceId').val() == ""|| $('#address_form_edit #refrenceId').val() == null;
			$.post("${ctx}/dealer/address/save",$('#address_form_edit').serialize(),function(data) {
				if (data.code == 0) {
					var html = [];
					html.push('<li id="li_'+ data.object.refrenceId+ '"'+ (data.object.dealerDefault ? 'class="hover"': '') + '>');
					html.push('	<div>');
					html.push('		<span class="fl" style="float: none;">');
					html.push('			<label class="sendto">寄送至</label>');
					html.push('			<input class="ui-radio" type="radio" '+ (data.object.dealerDefault ? 'checked="checked"': '') + '/>');
					var addrDetails = data.object.provinceName+ ' ' + data.object.cityName + ' '+ data.object.areaName + ' '
							+ '<span title="'+data.object.dealerAddress+'"> '+(data.object.dealerAddress.length>20?data.object.dealerAddress.substring(0,20)+'...':data.object.dealerAddress) + '</span> ('+ data.object.dealerName + ') ' +data.object.dealerMobile + '&nbsp' + data.object.dealerTel+'&nbsp'+data.object.postCode;
					html.push('			<span class="address">'+ addrDetails + '</span>');
					html.push('		</span>');
					html.push('		<span class="fr">');
					html.push('			<a class="defadd" style="cursor: pointer;" data-id="'+data.object.refrenceId+'">默认地址</a>');
					html.push('			<a class="edit-address link" style="cursor: pointer;" data-area="'+data.object.areaName+'"  data-city="'+data.object.cityName+'" data-province="'+data.object.provinceName+'" data-id="'+data.object.refrenceId+'" data-addrcode="'+data.object.dealerAddr+'" data-address="'+data.object.dealerAddress+'"  data-dealername="'+data.object.dealerName+'"  data-dealertel="'+data.object.dealerTel+'"  data-dealermobile="'+data.object.dealerMobile+'" data-postcode="'+data.object.postCode+'">修改本地址</a>');
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
					var uuid=data.object.refrenceId;
					$.post("${ctx}/dealer/address/dealerDefault", {"uuid" : uuid}, function(data) {
						if (data.code == 0) {
							$(".address_list li").removeClass("hover");
							$(".address_list li input.ui-radio").prop('checked',false);
							$('#li_'+uuid).addClass("hover");
							$('#li_'+uuid).find('input.ui-radio').prop('checked', true);
							$('a.defadd').removeClass("default").html('设置默认地址');
							$('#li_'+uuid+' a.defadd').addClass("default").html('默认地址');
						}
					}, "json");
					g_edit_address_dlg.hide();
				}
			}, "json");
			return false;
		};
		
		/**地址选中效果切换*/
        $(".address_list").on("click","li",function() {
			$(".address_list li").removeClass("hover");
			$(".address_list li input.ui-radio").prop('checked',false);
			$(this).addClass("hover");
			$(this).find('input.ui-radio').prop('checked', true);
		});
        
		/**新增 修改 事件注册*/
		$(document).on("click", ".edit-address,.add-address", function() {
			g_edit_address_dlg.show();
			service_detail.loadDealerAddr(this);
		});
		
		/**选中地址 确认按件 注册*/
		$(document).on("click", "#comfirmbuybtn", function() {
			service_detail.toPayServiceFee($('#dealer_address_list li.hover a.defadd').data('id'));
		});
		
		/**设置默认 事件注册*/
		$(document).on("click", "span.fr a.defadd",service_detail.setDealerAddrDefault);
		
		/**初始地址编辑框*/
	    seajs.use(["dialog"], function (Dialog) {
    		window['g_edit_address_dlg'] = new Dialog({content : '#Edit_Address',width : 720});
   		});
      
	    service_detail.initValidform();
	    
	    
    </script>
</body>
</html>
