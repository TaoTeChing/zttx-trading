<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-我的财务</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
	<link rel="stylesheet" href="${res}/styles/brand/myFinancial.css" />
	<!--link rel="stylesheet" href="${res}/styles/dealer/finance.css" /-->
</head>
<body>
<jsp:include page="${ctx}/brand/mainmenu"/>
<div class="main myFinancial layout">
<div class="main_con">
<div class="inner">
<!-- 内容都放这个地方  -->
    <jsp:include page="${ctx}/WEB-INF/view/brand/show_myFinance_bar.jsp">
    	<jsp:param name="m" value="6" />
    </jsp:include>
    
    <div class="tab_con_box">
        <!-- 银行卡设置开始 -->
        <div style="display: block;" class="tab_con bank_card_set clearfix">
            <div style="color: #666;margin: 0 0 15px 26px;">
                说明：申请提现的金额，会在申请提现后的24小时之内打到您指定的账户中
            </div>
            <label class="charge_label" style="font-size:13px;">当前的银行卡：</label>
            <div class="my_card_list"> 
                <ul>
                <c:choose>
                	<c:when test="${empty bankList}">
                	<li class="item">没有审核通过的银行卡</li>
                	</c:when>
                	<c:otherwise>
	                	<c:forEach items="${bankList}" var="item" varStatus="itemStatus">
						<c:if test="${itemStatus.index >2 }">
		                    <li class="item hidedn_item">
	                	</c:if>
	                	<c:if test="${itemStatus.index <=2 }">
		                    <li class="item">
	                	</c:if>

	                        <div class="item_inner">
	                        	<input type="radio" style="float: left; height: 42px; line-height: 42px;" value="${item.refrenceId}" name="brandBankId"/>
	                            <div class="js_img_center bank_img" style="width: 130px;">
									<c:set value="${item.bankIcon}" var="url"></c:set>
	                                <img src="${res}${url}" alt="${item.bankName}" width="120px" height="30px"/>
	                            </div>
	                            <span class="card_num">${item.lastbankNo}</span>
	                            
	                            <c:choose>
	                            	<c:when test="${item.checkState == 0}">
								   		<span class="default">未审核</span>
								   	</c:when>
								   	<c:when test="${item.checkState == 1}">
								   		<span class="default">审核通过</span>
								   	</c:when>
								   	<c:when test="${item.checkState == 2}">
								   		<span class="default">审核不通过</span>
								   	</c:when>
								   	<c:otherwise>
								   		<span class="default"></span>
								   	</c:otherwise>
	                            </c:choose>
	                            
	                            <c:choose>
								   <c:when test="${item.cardDefault}">
								   		<span class="default">默认银行卡</span>
								   </c:when>
								   <c:otherwise>
								   		<a class="set" data-uuid="${item.refrenceId}">设为默认银行卡</a>
								   </c:otherwise>
								</c:choose>
	                            <a class="del" data-uuid="${item.refrenceId}">删除</a>
	                        </div>
	                	</c:forEach>
                	</c:otherwise>
                </c:choose>
                </ul>
                <div class="clearfix" style="margin: 15px 0 20px 0;">
                	<c:if test="${not empty bankList && bankList.size() > 3}">
                    <div class="toggle">
                        <div class="js_show_more show_more" style="margin-top: 10px;">
                            <i></i>
                            更多
                        </div>
                        <div class="js_hide_more hide_more" style="display: none;">
                            <i></i>
                            隐藏
                        </div>
                    </div>
					</c:if>
                    <div  class="add_card">
                       <a href = "${ctx}/brand/bank/list" >查看银行卡审核详情</a>
                    </div>
                </div>


                <div style="display: none;" id="choose_bank">
                    <div class="ui-head">
                        <h3>选择银行</h3>
                    </div>
                    <div class="area_select">
                        <div class="inline-block">
                        
                        </div>
                       <!--  开户银行 -->
                    </div>
                    <div class="bank_list">
                        <ul class="inline-float">
                        	<c:forEach items="${cardList}" var="card" varStatus="itemStatus">
                        	<li>
                                <input type="radio" name="bank_name" value="${card.bankNo}"/>
                                <div class="bank_img">
									<c:set value="${card.bankIcon}" var="url"></c:set>
                                    <img src="${res}${url}" alt="${card.bankName}" width="120px" height="30px"/>
                                    
                                </div>
                            </li>
                        	</c:forEach>
                        </ul>

                    </div>
                    <div class="mt10 ta-r">
                        <a href="javascript:;" class="ui_button ui_button_morange next_step" id='next_step'>下一步</a>
                    </div>
                </div>
                                     
                <div id="add_form"style="display: none;">
                    <div class="ui-head">
                        <h3>添加银行卡</h3>
                    </div>
                    <p class="support_info">
                    	<i class="iconfont">&#xe62d;</i>目前仅支持工行、农行等<strong>18家银行</strong>的借记卡。
                	</p>
                    <form:form id="cardForm" name="cardForm" class="ui-form">
                    <div class="ui-form-item" id="id_bankCate">
                         <label class="ui-label" for="">
                              	 账户类型：<span class="ui-form-required">*</span>
                         </label>
                         <label class='ui-form-text' for='gs'>
                          <input id='copy_bankCate' type="hidden" class='ui-form-radio'  name="bankCate" value="2" /> 
                                              	公司账户</label>
                     </div>
                     <div class="ui-form-item">
                        <label class="ui-label" for="">
                           	 公司名称：<span class="ui-form-required">*</span>
                        </label>
                        <input id="userName_pepole" name="userName" type="hidden" class="ui-input" value="${brandInfo.comName }" />
                        <span class="ui-form-text">${brandInfo.comName }</span>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                         	   营业执照：<span class="ui-form-required">*</span>
                        </label>
                        <input id="userId_pepole" name="userId" type="hidden" class="ui-input"  value="${brandInfo.comNum }" />
                        <span class="ui-form-text">${brandInfo.comNum}</span>
                    </div>
					<div class="ui-form-item">
                           <label class="ui-label" for="">
                                开户地区：<span class="ui-form-required">*</span>
                            </label>
                               <jsp:include page="${ctx}/client/Regional/searchaAll">
									<jsp:param value="area-ui-select" name="style"/>
									<jsp:param value="" name="regProvince" />
									<jsp:param value="" name="regCity" />
									<jsp:param value="" name="regCounty" />
                        		</jsp:include>
                        		<input type="hidden" name="provinceName" value="" />
                                <input type="hidden" name="cityName" value="" />
                                <input type="hidden" name="areaName" value="" />
                     </div>
                        <div class="ui-form-item">
                            <label class="ui-label" for="">
                                银行卡号：<span class="ui-form-required">*</span>
                            </label>
                            <input type="text" class="ui-input" value="" name="bankNo" id="bankNo">
                            <p class="ui-form-explain" id="view_mark" style="display:none;">输入卡号后会智能识别银行和卡种</p>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label" for="">
                                开户支行：<span class="ui-form-required">*</span>
                            </label>
                            <input type="text" class="ui-input" value=""  name="subBank" id="subBank" />
                        </div>
                        
                        <div class="ui-form-item">
                            <button id="submitSaveButton" class="ui_button ui_button_morange" type="submit" >同意协议并确定</button>
                            <p class="mt5">
                                <a href="${ctx}/common/rules/info/976D2090BEF9486D939B37BEC5DB4E38" class="link" target="_blank" >《8637品牌超级代理服务协议》</a>
                            </p>
                        </div>
                    </form:form>
                </div>
            </div>
            <c:if test="${!empty bankList}">
            <c:choose>
            	<c:when test="${empty brandPayword}">
            	<div class="item mt15">
                 	<button style="margin-left: 118px;" id="setPass_btn" class="simple_button">请先设置支付密码</button>
             	</div>
            	</c:when>
            	<c:otherwise>
            	<form:form style="margin-left: 65px;" data-widget="validator" id="widthdraw_form" class="ui-form" action="">
                    <div class="ui-form-item">
                        <label class="ui-label" style="font-size:12px; margin-top:0;">
                            提现金额：
                        </label>
                        <input autocomplete="off" name="drawAmount" required data-display="提现金额" max="${brandBalance.accountBalance}" style="width: 160px" type="text" class="ui-input">
                        元<span class="ml5 explain">（当前最高可提现金额为<strong style="color: red;font-family: Arial;">${empty brandBalance.accountBalance?0.00: brandBalance.accountBalance }</strong>元）</span>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" style="font-size:12px; margin-top:0;">
                            支付密码：
                        </label>
                        <input type="text" style="display: none;" />
                        <input autocomplete="off" name="payPassword" required data-display="支付密码"  style="width: 258px" type="password" class="ui-input">
                    </div>
                    <!-- 
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            手机号码：
                        </label>
                        <input required data-display="手机号码" data-rule="mobile"  style="width: 160px" type="text" class="ui-input">
                        <button class="code_btn" type="button">获取验证码</button>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            验证码：
                        </label>
                        <input required data-display="验证码" style="width: 82px;" type="text" class="ui-input">
                    </div>
                     -->
                    <div class="ui-form-item">
                        <button class="ui_button ui_button_morange" type="submit">确认提交</button>
                    </div>
                </form:form>
            	</c:otherwise>
            </c:choose>
            </c:if>
        </div>
        <!-- 银行卡设置结束 -->
    </div>

<!-- 提现返回页面      start -->
    <div class="tab_con_box_s" style="display: none;">
        <!-- 提现成功开始 -->
        <div class="tab_con success_box" style="display: block;">

            <div class="status_contain clearfix">
                <i class="iconfont agree_icon">&#xe628;</i>
                <div class="info">
                    <h3>您的提现申请已提交，提现金额为<strong id="show_drawAmount"></strong>元！</h3>
                    <p class="explain mt15">
                        提现成功后，我们将会通过<strong>站内信</strong>和<strong>手机短信</strong>的方式通知您
                    </p>
                </div>
            </div>

        </div>
        <!-- 提现成功结束 -->
    </div>
<!-- 提现返回页面      end -->


</div>
</div>
</div>
<div style="display: none;">
    <div id="setPassword_box" class="setPassword_box">
        <div class="ui-head">
            <h3>设置支付密码</h3>
        </div>
        <form:form data-widget="validator" class="ui-form setPass_Form mt15" id="setPass_Form">
            <div class="ui-form-item">
                <label class="ui-label" for="">支付密码</label>
                <input autocomplete="off" style="width: 200px;" data-display="支付密码" class="ui-input"  id="password" name="payWord" type="password" />
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">重复支付密码</label>
                <input style="width: 200px;" required data-errormessage-required="请再重复输入一遍密码，不能留空" data-rule="confirmation{target: '#password',name:'第二遍'}" data-display="第一遍" class="ui-input" name="repassword" type="password" />
                <div class="ui-form-explain">下次支付需要您输入支付密码，请牢记</div>
            </div>
            <div class="ui-form-item">
                <button class="simple_button confirm_btn" type="submit">确定</button>
                <button class="simple_button cancel_btn ml10" type="button">取消</button>
            </div>
        </form:form>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${res}/scripts/brand/myFinancial.js"></script>
<script src="${res}/scripts/brand/set_brankBank.js"></script>
<script type="text/javascript">
<c:choose>
	<c:when test="${empty brandPayword}">
	$(function() {
		  if($("#setPass_btn").length > 0){

                var d = null;

                seajs.use(["dialog"],function(Dialog){

                    d = new Dialog({
                        trigger: "#setPass_btn",
                        content: "#setPassword_box",
                        width: 376
                    }).before("show",function(){
                        resetValidatorError("#setPass_Form");
                    });

                });

                $("#setPassword_box .cancel_btn").on("click",function(){
                    d.hide();
                });

                baseFormValidator({
                    selector: "#setPass_Form",
                    isAjax: true,
                    addElemFn: function(Core, Validator){
                        Validator.addRule("specialPassword",function(options){
                            var reg = /^(?:\d*|[a-zA-Z]*|[\x00-\x2f\x3a-\x40\x5b-\x60\x7b-\x7f]*)$/;
                            var str = options.element.val();
                            if(reg.test(str)){
                                return false;
                            }else{
                                return true;
                            }
                        },"密码不能全为数字或字母");
                        Core.addItem({
                            element: '#password',
                            required: true,
                            rule: 'minlength{"min":8} maxlength{"max":20} specialPassword',
                            display: '新密码'
                        })
                    },
                    beforeSubmitFn: function(){
                         $.ajax({
				 		  url: '${ctx}/brand/createPayword',
				          type: 'post',
				          data: $("#setPass_Form").serialize(),
				          dataType: 'json',
				          success : function(jsonMessage) {
						if (jsonMessage.code == zttx.SUCCESS) {
								remind("success","设置成功",function(){
								location.href = location.href;
							});
						} else {
								remind("error",jsonMessage.message);
						}
					}
			    	});
                    }
                })

            }
		});
	</c:when>
	<c:otherwise>
	myFinancial.init();
    seajs.use(["$", 'widget','validator'], function ($, Widget,Validator) {
    	if($("#widthdraw_form").length > 0){
			Widget.autoRenderAll();
            var Core = Validator.query('#widthdraw_form');
            Validator.addRule("amountRule",function(options){
                var str = options.element.val();
                var drawAmount = parseFloat(str);
                var drawAmountMax = parseFloat($('input[name="drawAmount"]').attr('max'));
			    if(drawAmount>drawAmountMax){
                    return ;
                }
                if(drawAmount<1){
                    return ;
                }
                return isAmount(str);
            },"金额不能超过小数点2位,且不能小于或等于0");
            Core.set("autoSubmit",false);
            Core.addItem({
                element: "input[name='drawAmount']",
                rule: "amountRule"
            })
			Core.on('formValidated', function(error) {
            if(!error){
                if($('input[name="brandBankId"]:checked').val() == null)
                {
                    remind("error","请选择需要提现的银行卡");
                    return ;
                }
			    var brandBankId = $('input[name="brandBankId"]:checked').val();
			    var drawAmount = $('input[name="drawAmount"]').val();
			    
				var values = $("#widthdraw_form").serialize();
				values=values+'&brandBankId='+ brandBankId;
				$.ajax({
		 		  url: '/brand/brandDrawing/save',
		          type: 'post',
		          data: values,
		          dataType: 'json',
		          success: function(data, textStatus)
		          {
		          	if(data.code == 116000){
		          	var frozeMone = data.object.frozeMoney;
						$("#show_drawAmount").text(drawAmount);
						$("#id_frozeMoney").text(frozeMone);
						$("#id_accountBalance").text(data.object.accountBalance);
						$("#id_amount_change").text(frozeMone);
						$(".tab_con_box").replaceWith($(".tab_con_box_s").html());
		          	}
		          	else
		          	{
		          		remind("error",data.message);
		          	}
		          },
		  		  error: function(data)
		          {
		  			remind("error",data.message);
		          }
		    	});
			}
        });
        }
    });
    
    //城市联动取值 
       $('#province').on('change',function (){
			var province = this.options[this.selectedIndex].text;
			$('input[name="provinceName"]').val(province);
			$('input[name="cityName"]').val('');
			$('input[name="areaName"]').val('');
		});
		
		$('#city').on('change',function (){
			var city = this.options[this.selectedIndex].text;
			$('input[name="cityName"]').val(city);
			$('input[name="areaName"]').val('');
		});
        
        $('#county').on('change', function (){
        	var county = this.options[this.selectedIndex].text;
        	$('input[name="areaName"]').val(county);
        });
	</c:otherwise>
</c:choose>
</script>
</body>
</html>
