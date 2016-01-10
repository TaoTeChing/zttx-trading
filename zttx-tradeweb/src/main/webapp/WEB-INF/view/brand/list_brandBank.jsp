<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-我的财务</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
	<link rel="stylesheet" href="${res}/styles/brand/myFinancial.css" />
</head>
<body>
<jsp:include page="${ctx}/brand/mainmenu"/>
<div class="main myFinancial layout">
<div class="main_con">
<div class="inner">
<!-- 内容都放这个地方  -->

    
    <jsp:include page="${ctx}/WEB-INF/view/brand/show_myFinance_bar.jsp">
    	<jsp:param name="m" value="4" />
    </jsp:include>
    <div class="tab_con_box">
        <!-- 银行卡设置开始 -->
        <div style="display: block;" class="tab_con bank_card_set clearfix">
            <div style="color: #666;margin: 0 0 15px 26px;">
                说明：新添加的银行卡需要通过审核才能使用，审核周期一般为1-3个工作日，请耐心等待...
            </div>
            <label class="charge_label" style="font-size:13px;">当前的银行卡：</label>
<!-- 内容start -->
            <div class="my_card_list"> 
                <ul>
                <c:forEach items="${bankList}" var="item" varStatus="itemStatus">
					<c:if test="${itemStatus.index >3 }">
	                    <li class="item hidedn_item">
                	</c:if>
                	<c:if test="${itemStatus.index <=3 }">
	                    <li class="item">
                	</c:if>
                	
                        <div class="item_inner">
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
                                    <%--<a class="edit" href="javascript:;" data-uuid="${item.refrenceId}">修改</a>--%>
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
                    </li>
                </c:forEach>
                </ul>
                	<c:if test="${not empty bankList && bankList.size() > 4}">
                <div class="toggle" style="margin-top: 15px;height: 40px;margin-left: 0px;margin-bottom: 15px;">
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
                <div id="add_card" class="add_card mt10">
                    <i class="iconfont add_icon">&#xe611;</i>添加新的银行卡
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
                        <input type="hidden" name="updateId" value=""/>
                        <div class="ui-form-item" id="id_bankCate">
                            <label class="ui-label">
                                账户类型：<span class="ui-form-required">*</span>
                            </label>
                            <div class="radio_box inline-block">
                                <label class='js-state'>
                                    <input id='pepole_bankCate' type='radio' class='ui-form-radio ui-radio' name="bankCate" value="1"  checked='checked' />
                                    个人账户
                                </label>
                                <label class='js-state'>
                                    <input id='copy_bankCate' type="radio" class='ui-form-radio ui-radio'  name="bankCate" value="2" />
                                    公司账户
                                </label>
                            </div>
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
                                <%--<p class="ui-form-explain" id="view_mark" style="display:none;">输入卡号后会智能识别银行和卡种</p>
                            --%>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label" >
                                开户支行：<span class="ui-form-required">*</span>
                            </label>
                            <input type="text" class="ui-input" value=""  name="subBank" id="subBank" />
                        </div>
                        <div class="ui-form-item js-fujian">
                            <label class="ui-label" >
                                附件：<span class="ui-form-required">*</span>
                            </label>
                            <a class="file_wrap simple_button" href="javascript:;">
                                <i class="iconfont"></i>
                                上传
                                <input class="input_file" id="fujian" name="photo" type="file">
                            </a>
                            <input type="text" class="ui-input" name="attachment" readonly style="width: 156px;display: none;"/>
                            <input type="text" class="ui-input js-upload-name" disabled style="width: 156px;"/>
                        </div>
                        <div class="ui-form-item">
                            <p style="color:#FF5243;">请上传盖有公司印章的授权证明，仅支持JPG、GIF、PNG图片格式</p>
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
<!-- 内容end  -->
        </div>
        <!-- 银行卡设置结束 -->
    </div>
</div>
</div>
</div>
<div style="display: none;" id="hidden_pepole">
    <div class="ui-form-item js-self-account">
        <label class="ui-label" >
            开户人姓名：<span class="ui-form-required">*</span>
        </label>
        <input type="text" class="ui-input" value=""  name="userName" id="userName_copy" />
        <input type="hidden" name="userId"  value="" />
    </div>
</div>
<div style="display: none;" id="hidden_copy">
    <div class="ui-form-item">
        <label class="ui-label">
            公司名称：<span class="ui-form-required">*</span>
        </label>
        <input id="userName_pepole" name="userName" type="hidden" value="${brandInfo.comName }" />
        <span class="ui-form-text">${brandInfo.comName }</span>
    </div>
    <div class="ui-form-item">
        <label class="ui-label">
            营业执照：<span class="ui-form-required">*</span>
        </label>
        <input id="userId_pepole" name="userId" type="hidden" class="ui-input"  value="${brandInfo.comNum }" />
        <span class="ui-form-text">${brandInfo.comNum}</span>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${res}/scripts/brand/myFinancial.js"></script>
<script src="${res}/scripts/brand/set_brankBank.js"></script>
<script type="text/javascript">
	myFinancial.init();
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
	    
</script>
</body>
</html>
