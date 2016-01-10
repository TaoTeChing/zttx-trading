<!DOCTYPE html><%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-财务管理-提现</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    
    <link href="${res}/styles/dealer/finance.css" rel="stylesheet" />
    <style>


		.myFinancial label {
		    display: inline;
		    float: left;
		    font: 18px "微软雅黑";
		    margin: 10px 0 0 25px;
		}
		.myFinancial .my_card_list {
		    overflow: hidden;
		}
		
		.ui-dialog .support_info .iconfont {
		    color: #F19149;
		    font-size: 18px;
		    margin-right: 3px;
		    vertical-align: -3px;
		}
		.ui-dialog .support_info strong {
		    color: #0082CC;
		    cursor: pointer;
		    margin: 0 5px;
		}

		.ui-form{
			position: relative;
			left: -42px;
		}
		
		.ui-form .ui-label{
			line-height: 28px;
		}
		
		.ui-form-item-error .ui-form-explain{
			display: inline-block;
			*display: inline;
			*zoom: 1;
		}

        *{
            outline: none;
        }
		
    </style>
</head>
<body>
    <!--完成-->
    <div class="container">
        <jsp:include page="${ctx}/dealer/mainmenu">
        	<jsp:param value="0" name="isShow"/>
        </jsp:include>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->
                
                   <jsp:include page="${ctx}/dealer/dealermenu">
					<jsp:param value="8" name="openId"/>
				</jsp:include>
               
                <!--主体内容-->
                <div class="main-right">
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="${ctx }/dealer/finance" title="">财务管理</a> > <span class="bb">提现</span>
                            <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>

                        </div>
                    </div>
                    <div class="inner">
                        <div class="panel-account clearfix">
                            <dl class="mb10">
                                <dt class="yahei fs16 fl lh2">当前账户余额:</dt>
                                <dd>
                                    <ul>
                                        <li><span class="fs18 bb yahei c-r lh2">${empty dealerBalance.balance?0.00: dealerBalance.balance}元</span></li>
                                        <li class="mb40">
                                            <span class="lh2">
                                            当前可用余额:<span id="id_accountBalance">${empty dealerBalance.accountBalance?0.00: dealerBalance.accountBalance }</span>元 ( 
                                            <span  class="freeze_cash" style="color: #0082CC;">
	                                            冻结金额:<span id="id_frozeMoney">${empty dealerBalance.frozeMoney?0.00: dealerBalance.frozeMoney}</span>元</a> )</span>
	                                       <input type="hidden" id="id_amount" value="${amount}" />
                                            </span>
                                        </li>
                                        <li>
                                           <a href="${ctx}/dealer/finance/charge" target="_blank" class="ui-button ui-button-mred">充 值</a>
                                           <a href="${ctx}/dealer/finance/drawing" class="ui-button ui-button-mred">提 现</a>
                                         </li>
                                    </ul>
                                </dd>
                            </dl>
                        </div>
                        <div class="panel-tabs">
                            <div class="panel-head">
                                  <ul>
                                    <a href="${ctx}/dealer/finance" class="yahei fs14"><li>支出交易 </li></a>
                                   	<a href="${ctx}/dealer/finance/income" class="yahei fs14"><li>收入交易</li></a>
                                    <a href="${ctx}/dealer/finance/history" class="yahei fs14"><li>交易记录</li></a>
                                    <a href="${ctx}/dealer/finance/drawinghis" class="yahei fs14"><li >提现记录</li></a>
                                    <a href="${ctx}/dealer/finance/bankcard" class="yahei fs14"><li>银行卡设置</li></a>
                                    <a href="${ctx}/dealer/finance/drawing" class="yahei fs14"><li class="on">提现</li></a>
                                </ul>
                            </div>
                            <div class="panel-content" id="tab_con_box">
                                <div class="tab-item">
                                    <div style="color: #666;margin: 15px 0 0px 102px;">
                                        说明：申请提现的金额，会在申请提现后的24小时之内打到您指定的账户中。
                                    </div>
                                    <div style="padding-top: 15px;" class="panel-finance clearfix myFinancial">
                                           <label style="font-size:13px;" class="charge_label">当前的银行卡：</label>


                                            <!-- 内容start -->
                                           <div class="my_card_list"> 
								                <ul>
                                                    <c:if test="${bankList.size() == 0}"><li class="item">没有审核通过的银行卡</li></c:if></label>
								                <c:forEach items="${bankList}" var="item" varStatus="itemStatus">

													<c:if test="${itemStatus.index >2 }">
									                    <li class="item hidedn_item">
								                	</c:if>
								                	<c:if test="${itemStatus.index <=2 }">
									                    <li class="item">
								                	</c:if>
								                        <div class="item_inner clearfix">
								                        <input type="radio" style="float: left; height: 42px; line-height: 42px;margin-right:5px" value="${item.refrenceId}" name="dealerBankId"/>
								                            <div class="js_img_center bank_img">
								                            	<img src="${res}${item.bankIcon}" alt="${item.bankName}"/>
								                            </div>
								                            <span class="card_num">${item.lastbankNo}</span>
								                            <%--<c:choose>
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
								                            </c:choose>--%>
								                            
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
								                    </li>
								                </c:forEach>
								                </ul>
								                </br>
								                <div class="toggle">
													<div class="js_show_more show_more" >
															<c:if test="${fn:length(bankList)>3}">
																<i class="iconfont" style="vertical-align: -5px;font-size: 20px;">&#xe605;</i>更多
															</c:if>
															<c:if test="${fn:length(bankList)<=3}">
																<i class="iconfont" style="vertical-align: -5px;font-size: 20px;"></i>
															</c:if>
													</div>
													<div class="js_hide_more hide_more" style="display: none;">
                                                        <i class="iconfont" style="vertical-align: -5px;font-size: 20px;">&#xe606;</i>
														隐藏
													</div>
												</div>
								                <%--<div id="add_card" class="add_card">--%>
                                                <div class="add_card" style="float:left;width: 134px;line-height: 46px;border: #CCC dashed 1px; margin-top: 0;">
                                                    <a href="${ctx}/dealer/finance/bankcard">查看银行卡审核详情</a>
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
								                                    <img src="${res}${card.bankIcon}" alt="${card.bankName}"/>
								                                </div>
								                            </li>
								                        	</c:forEach>
								                        </ul>
								
								                    </div>
								                    <div class="mt10 ta-r">
								                        <a href="javascript:;" class="ui-button ui-button-morange next_step" id='next_step'>下一步</a>
								                    </div>
								                </div>
								                <div id="hidden_copy"  style="display: none;">
								                     <div class="ui-form-item">
								                        <label class="ui-label" for="">
								                           	 公司名称：<span class="ui-form-required">*</span>
								                        </label>
								                        <input id="userName_pepole" name="userName" type="hidden" class="ui-input" value="${dealerInfo.dealerName }" />
								                        <span class="ui-form-text">${dealerInfo.dealerName }</span>
								                    </div>
								                    <div class="ui-form-item">
								                        <label class="ui-label">
								                         	   营业执照：<span class="ui-form-required">*</span>
								                        </label>
								                        <input id="userId_pepole" name="userId" type="hidden" class="ui-input"  value="${dealerInfo.comNo }" />
								                        <span class="ui-form-text">${fns:hideAlphaNumber(dealerInfo.comNo)}</span>
								                    </div>
								               </div>
								                                     
								               <div id="hidden_pepole"  style="display: none;">
								                    <div class="ui-form-item">
								                        <label class="ui-label" for="">
								                           	 真实姓名：<span class="ui-form-required">*</span>
								                        </label>
								                        <input id="userName_copy" name="userName" type="hidden" class="ui-input" value="${dealerInfo.dealerUser }" />
								                        <span class="ui-form-text">${dealerInfo.dealerUser}</span>
								                    </div>
								                    <div class="ui-form-item">
								                        <label class="ui-label">
								                           	 身份证号：<span class="ui-form-required">*</span>
								                        </label>
								                        <input id="userId_copy" name="userId" type="hidden" class="ui-input"  value="${dealerInfo.cardId }" />
								                        <span class="ui-form-text">${fns:hideAlphaNumber(dealerInfo.cardId)}</span>
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
								                    	<input type="hidden" name="updateId" value=""/>
								                    <div class="ui-form-item" id="id_bankCate">
								                         <label class="ui-label" for="">
								                              	 账户类型：<span class="ui-form-required">*</span>
								                         </label>
								                         <label class='ui-form-text' for='gr'>
								                              <input id='pepole_bankCate' type='radio' class='ui-form-radio' name="bankCate" value="1"  checked='checked' />
								                                               	个人账户</label>
								                         <label class='ui-form-text' for='gs'>
								                          <input id='copy_bankCate' type='radio' class='ui-form-radio'  name="bankCate" value="2" /> 
								                                              	公司账户</label>
								                     </div>
								                     <div id="userInfo_id">
													</div>
													
													<div class="ui-form-item">
								                           <label class="ui-label" for="">
								                                开户地区：<span class="ui-form-required">*</span>
								                            </label>
                                                        <span class="ui-form-text" style="margin-top: 0px;">
								                               <jsp:include page="${ctx}/client/Regional/searchaAll">
																	<jsp:param value="area-ui-select" name="style"/>
																	<jsp:param value="" name="regProvince" />
																	<jsp:param value="" name="regCity" />
																	<jsp:param value="" name="regCounty" />
								                        		</jsp:include>
								                        		<input type="hidden" name="provinceName" value="" />
								                                <input type="hidden" name="cityName" value="" />
								                                <input type="hidden" name="areaName" value="" />
                                                            </span>
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
								                            <button id="submitSaveButton" class="ui-button ui-button-morange" type="submit" >同意协议并确定</button>
								                            <p class="mt5">
								                                <a href="${ctx}/common/rules/info/976D2090BEF9486D939B37BEC5DB4E38" class="link" target="_blank" >《8637品牌超级代理服务协议》</a>
								                            </p>
								                        </div>
								                    </form:form>
								                </div>
								            </div>
                                        	<!-- 内容end -->
                                           <c:if test="${bankList.size() != 0}">
                                               <form data-widget="validator" id="widthdraw_form" class="ui-form" action="" autocomplete="off" >
                                                    <div class="ui-form-item" style="height:40px;">
                                                        <label class="ui-label charge_label" style="font-size:12px; margin-top:0;">
                                                            提现金额：
                                                        </label>
                                                        <input autocomplete="off" name="drawAmount" required data-display="提现金额" max="${dealerBalance.accountBalance }" style="width: 160px;padding: 7px 9px;" type="text" class="ui-input">
                                                        元<span class="ml5 explain">（当前最高可提现金额为<strong  style="color: red;">${empty dealerBalance.accountBalance?0.00: dealerBalance.accountBalance }</strong>元）</span>
                                                    </div>
                                                    <div class="ui-form-item" style="height:40px;">
                                                        <label class="ui-label charge_label" style="font-size:12px; margin-top:0;">
                                                            支付密码：
                                                        </label>
                                                        <input type="text" style="display: none;" />
                                                        <input autocomplete="off" name="payPassword" required data-display="支付密码"  style="width: 258px;padding: 7px 9px;" type="password" class="ui-input">
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
                                                        <button class="ui-button ui-button-morange" type="submit" style="margin-left:150px;">确认提交</button>
                                                    </div>
                                                </form>
                                           </c:if>
                
                <!-- 提现返回页面      start -->
    <div class="tab_con_box_s" style="display: none;">
	   <div class="complete pb40" style="margin-top: 10px">
	       <p class="lh2 mb40 mt40 i-right-32">
	           <span class="yahei fs25"><b>您的提现申请已提交，提现金额为 <em class="c-r-on" id="show_drawAmount"></em> 元!</b></span>
	       </p>
	       <p class="lh2">
	           <span class="yahei fs14 ">
	               提现成功后,我们将会通过 <span class="c-r">站内信</span> 和 <span class="c-r">
	                   手机短信
	               </span> 的方式通知您
	           </span>
	       </p>
	       <p class="lh2">
	           <span class="c-h">
	               返回 <a href="${ctx }/dealer/center" class="c-b" title="">我的主页</a> 或进入 <a href="/" class="c-b" title="">8637品牌超级代理首页</a>
	           </span>
	       </p>
	   </div>
    </div>
<!-- 提现返回页面      end -->                                       	
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script src="${res}/scripts/dealer/set_dealerBank.js"></script>
    <script src="${res}/scripts/brand/myFinancial.js"></script>
    <script language="JavaScript" type="text/javascript">
    	myFinancial.init();
        seajs.use(["$", 'widget','validator'], function ($, Widget,Validator) {
			Widget.autoRenderAll();
            Validator.addRule("amountRule",function(options){
                var str = options.element.val();
                return isAmount(str);
            },"金额不能超过小数点2位");
            var Core = Validator.query('#widthdraw_form');
	        Core.set("autoSubmit",false);
            /*
            Core.addItem({
                element: "input[name='drawAmount']",
                rule: "amountRule"
            })
            */
			Core.on('formValidated', function(error) {

			if(!error){
                if($('input[name="dealerBankId"]:checked').val() == null)
                {
                    remind("error","请选择需要提现的银行卡");
                    return ;
                }
			    var dealerBankId = $('input[name="dealerBankId"]:checked').val();
			    var drawAmount = $('input[name="drawAmount"]').val();
			    
			    if(!isAmount(drawAmount)){
			    	remind("error","请输入正确提款金额 只支持2位小数!");
			    	return;
			    }
				var values = $("#widthdraw_form").serialize();
				values=values+'&dealerBankId='+ dealerBankId;
				$.ajax({
                      url: '/dealer/dealerDrawing/save',
                      type: 'post',
                      data: values,
                      dataType: 'json',
                      success: function(data, textStatus)
                      {
                        if(data.code == 116000){
                            $("#show_drawAmount").text(drawAmount);
                            $("#id_frozeMoney").text(data.object.frozeMoney);
                            $("#id_accountBalance").text(data.object.accountBalance);
                            $("#tab_con_box").replaceWith($(".tab_con_box_s").html());
                        }
                        else
                        {
                            remind("error",data.message);
                        }
                      }
		    	});
			}
        });
    });

    seajs.use(["$", 'tip'], function ($,  Tip) {
        new Tip({
            trigger: '.tooltip',
            theme: 'yellow',
            arrowPosition: 11
        }).before('show', function () {
            this.set('content', this.activeTrigger.data().tip);
        });
    });
	//myFinancial.showTip();

        $(document).on("click",".bank_img",function(){
            $(this).prev().click();
        });

    </script>
</body>
</html>
