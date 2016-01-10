<!DOCTYPE html><%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-财务管理-银行卡设置</title>
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
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="${ctx }/dealer/finance" title="">财务管理</a> > <span class="bb">银行卡管理</span>
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
                                            		当前可用余额:${empty dealerBalance.accountBalance?0.00: dealerBalance.accountBalance }元 ( 
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
                                    <a href="${ctx}/dealer/finance/drawinghis" class="yahei fs14"><li>提现记录</li></a>
                                    <a href="${ctx}/dealer/finance/bankcard" class="yahei fs14"><li class="on">银行卡设置</li></a>
                                    <a href="${ctx}/dealer/finance/drawing" class="yahei fs14"><li>提现</li></a>
                                </ul>
                            </div>
                            <div class="panel-content">
                                <div class="tab-item">
                                    <div style="color: #666;margin: 15px 0 0px 102px;">
                                        说明：新添加的银行卡需要通过审核才能使用，审核周期一般为1-3个工作日，请耐心等待...
                                    </div>
                                    <div style="padding-top: 15px;" class="panel-finance clearfix myFinancial">
                                           <label style="font-size:13px;" class="charge_label">当前的银行卡：</label>
                                            <!-- 内容start -->
                                           <div class="my_card_list"> 
								                <ul>
								                <c:forEach items="${bankList}" var="item" varStatus="itemStatus">
														<c:if test="${itemStatus.index >2 }">
										                    <li class="item hidedn_item">
									                	</c:if>
									                	<c:if test="${itemStatus.index <=2 }">
										                    <li class="item">
									                	</c:if>
								                        <div class="item_inner">
								                            <div class="js_img_center bank_img" style="width: 130px;">
								                                <img src="${res}${item.bankIcon}" alt="${item.bankName}"/>
								                            </div>
								                            <span class="card_num">${item.lastbankNo}</span>
								                            <c:choose>
								                            	<c:when test="${item.checkState == 0}">
															   		<span class="default" style="width:60px;">未审核</span>
															   	</c:when>
															   	<c:when test="${item.checkState == 1}">
															   		<span class="default" style="width:60px;">审核已通过</span>
															   	</c:when>
															   	<c:when test="${item.checkState == 2}">
															   		<span class="default" style="width:60px;">审核不通过</span>
															   	</c:when>
															   	<c:otherwise>
															   		<span class="default" style="width:60px;"></span>
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
								                       <%--   暂时隐藏，功能已经实现
								                       <c:if test="${item.checkState == 2}">
                                                            <a class="edit" data-uuid="${item.refrenceId}">修改</a> 
                                                      </c:if>
                                                            
                                                            --%> 
								                        </div>
								                    </li>
								                </c:forEach>
								                </ul>
								                <div class="toggle" style="margin-top: 15px;height: 20px;margin-left: 0px;margin-bottom: 15px;">
														<div class="js_show_more show_more" style="margin-top: 10px;">
															<c:if test="${fn:length(bankList)>3}">
																<i class="iconfont" style="vertical-align: -5px;font-size: 20px;">&#xe605;</i>更多
															</c:if>
															<c:if test="${fn:length(bankList)<=3}">
																<i class="iconfont" style="vertical-align: -5px;font-size: 20px;"></i>
															</c:if>
														</div>
													<div class="js_hide_more hide_more" style="display: none;">
                                                        <i class="iconfont" style="vertical-align: -5px;font-size: 20px;">&#xe606;</i>隐藏
													</div>
												</div>
								                <div id="add_card" class="add_card">
                                                    <i class="iconfont" style="vertical-align: -1px;">&#xe61c;</i> 添加新的银行卡
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
								                                     
								               <div id="hidden_pepole" style="display: none;">
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
                                                        <input id="userId_copy" class="js-idcard-view" name="userId" type="hidden" class="ui-input"  value="${dealerInfo.cardId }" />
                                                             <c:choose>
                                                             <c:when test="${not empty dealerInfo.cardId }">
								                            <span class="ui-form-text">${fns:hideAlphaNumber(dealerInfo.cardId)}</span>
                                                             
                                                             </c:when>
                                                             <c:otherwise>
                                                              <span style="color: #FF5243;" class="ui-form-text">请先完善身份信息，再进行提现操作</span>
                                                             </c:otherwise>
                                                             </c:choose>
                                                       
                                                     
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
								                         <label class="ui-label">
								                              	 账户类型：<span class="ui-form-required">*</span>
								                         </label>
								                         <label class='ui-form-text'>
								                              <input id='pepole_bankCate' type='radio' class='ui-form-radio' name="bankCate" value="1"  checked='checked' />
								                                               	个人账户</label>
								                        <!--  <label class='ui-form-text'>
								                         
								                          <input id='copy_bankCate' type='radio' class='ui-form-radio'  name="bankCate" value="2" /> 
								                                              	公司账户</label> -->
								                     </div>
								                     <div id="userInfo_id">
													</div>
													
													<div class="ui-form-item">
								                           <label class="ui-label">
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
								                            <label class="ui-label">
								                                银行卡号：<span class="ui-form-required">*</span>
								                            </label>
								                            <input type="text" class="ui-input" value="" name="bankNo" id="bankNo">
								                            <p class="ui-form-explain" id="view_mark" style="display:none;">输入卡号后会智能识别银行和卡种</p>
								                        </div>
								                        <div class="ui-form-item">
								                            <label class="ui-label">
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
    <%--身份证号未验证 提示框--%>
    <div style="display: none;">
        <div id="id_card">
            <div class="ui-head">
                <h3>提示</h3>
            </div>
            <div style="padding: 15px 0;">
                请先完善身份信息，再进行提现操作。
            </div>
            <div>
                <a href="${ctx}/dealer/account/infor" class="ui-button ui-button-morange js-idcard-go">立即前往</a>
                <a href="javascript:;" class="ui-button ui-button-mwhite js-idcard-cansole">取消</a>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script src="${res}/scripts/dealer/set_dealerBank.js"></script>
    <script type="text/javascript">
	    seajs.use(["tip"],function(Tip){
	        new Tip({
	            trigger: $(".freeze_cash"),
	            width: 250,
	            theme: 'yellow',
	            inViewport: true,
	            arrowPosition: 11
	        }).before('show', function() {
	        	var id_amount = $("#id_amount").val();
	            var content = "您的<span id='id_amount_change'>"+id_amount + "</span>元提现金额申请正在处理中…";
	            this.set('content', content);
	        });
	        $(".panel-table-content tbody tr:odd").css("background-color", "#f9f9f9");
	    });
	    
    	$(".my_card_list").on("mouseenter mouseleave",".item",function(ev){
            if(ev.type == "mouseenter"){
                $(this).addClass("item_hover");
            }else if(ev.type == "mouseleave"){
                $(this).removeClass("item_hover");
            }
        });
        
        var onOff = true;
    	//
    	$(".hidedn_item").hide();
        //显示全部
        $(".toggle").on("click",function(ev){

            if(onOff){
                //显示全部
                $(this).find(".js_show_more").hide();
                $(this).find(".js_hide_more").show();
                $(".hidedn_item").show();
            }else{
                //隐藏部分
                $(this).find(".js_show_more").show();
                $(this).find(".js_hide_more").hide();
                $(".hidedn_item").hide();
            }
            onOff = !onOff;
        });
        
        seajs.use(['dialog',"tip"], function(Dialog,Tip) {
            var d1 = new Dialog({
                //trigger: '#add_card',
                content: "#choose_bank",
                width: 682
            });
            $(".my_card_list").on("click","#add_card",function(){
                d1.show();
                $("#choose_bank input[name=bank_name]").prop("checked",false);
                $("#add_form #bankNo").val("");
                $("#add_form #subBank").val("");
            });
            $(".my_card_list").on("click",".edit",function(){
                d1.show();
                var _id = $(this).data("uuid");
                $("input[name=updateId]").val(_id);
                $("#choose_bank input[value=0403]").prop("checked",true);
                $("#add_form #bankNo").val("XXXX");
                $("#add_form #subBank").val("json");
                /*$.post(url,data,function(json){
                    if(json.code == 0 ){
                        remind("error","数据返回错误");
                    }
                    $("#choose_bank input[value="+json.XXXX+"]").prop("checked",true);
                    $("#add_form #bankNo").val(json.XXXX);
                    $("#add_form #subBank").val(json.XXXX);
                });*/
            });
            d1.after('show', function() {
                $('#choose_bank').show();
            });
            /*
             *20140419 弹出层多了一层，先强制清除[冯唐路]
             * */
            $(document).on("click",".ui-dialog-close",function(){
            	$(".ui-mask").hide();
            	$(".ui-dialog").hide();
            });
            /*结束*/
            var d2 = new Dialog({
                content: "#add_form",
                width: 508
            });
            
            var t = new Tip({
                trigger: $(".support_info strong"),
                content: "目前仅支持：工行、招行、建行、中行、农行、交行、浦发、广发、中信、光大、兴业、民生、平安、杭州银行共18家银行",
                width: 330,
                theme: 'yellow',
                inViewport: true,
                arrowPosition: 11,
                zIndex: 9999
            });

            $(document).on("click","#next_step",function(){
                var num = 0;
                $(".bank_list li").each(function(){
                    var flag = $(this).find("input").prop("checked");
                    if(flag == true){
                        num++;
                    }
                });
                if(num == 0){
//                    alert("请至少选择一个银行");
                }else{
                    d1.hide();
                    d2.show();
                    $("#add_form").show();
                }
            });

        });
        
        $(document).on("click",".bank_list li",function(){
            $(".bank_list li").find("input").prop("checked",false);
            $(".bank_list li").find(".bank_img").removeClass("hasChecked");
            $(this).find("input").prop("checked",true);
            $(this).find(".bank_img").addClass("hasChecked");
        });
    
    </script>
</body>
</html>
