<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-品牌管理-品牌资料完善</title>
	<link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css" />
    <style type="text/css">
    	.uploadimages ul li{
        	overflow: hidden;
        }
        .area-ui-select{
        	width:187px;height:25px;
        	vertical-align: middle;
        }
    </style>
</head>
<body>
	<jsp:include page="/common/menuInfo/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="/common/menuInfo/sidemenu"/>
	<div class="main_con">
  	 <div class="bread_nav">
        <div class="fl">
            <a class="link" href="${ctx}/brand/center">管理中心</a>
            <span class="arrow">&gt;&gt;</span>
            <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
            <span class="arrow">&gt;</span>
            <span class="current">品牌资料完善</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
                <jsp:param value="0" name="isShow"/>
            </jsp:include>
        </div>
    </div>
    
	    <div class="inner">
	        <!-- 内容都放这个地方  -->
	        <form:form id="brandNetworkForm" name="brandNetworkForm" cssClass="ui-form" data-widget="validator" action="${ctx}/brand/brandNetwork/save" method="post">
	        <input type="hidden" name="refrenceId" id="refrenceId" value="${brandNetwork.refrenceId}"/>
			<!-- 品牌基本资料 start -->
	        <jsp:include page="/WEB-INF/view/brand/brands_perfect_menu.jsp">
	   			<jsp:param value="3" name="m"/>
	   		</jsp:include><!-- 品牌基本资料 end -->
			<c:set var="typeName" value="添加"/>
			<c:if test="${!empty brandNetwork.refrenceId}">
			<c:set var="typeName" value="修改"/>
			</c:if>
	        <div class="tips">
	            <i class="v2-icon-explain"></i>
	            说明：展示品牌门店形象，提高品牌竞争力
	        </div>
	        <div class="tab_con_box">
	            <div class="tab_con distribution_net">
	                <div class="ui_tab main_ui_tab" style="margin: 0 20px;">
	                    <ul class="ui_tab_items clearfix">
	                        <li class="ui_tab_item current">
	                            <a href="${ctx}/brand/brandNetwork/execute?id=${id}">门店展示${typeName}</a>
	                        </li>
	                        <li class="ui_tab_item">
	                            <a href="${ctx}/brand/brandNetwork/list?id=${id}">门店展示管理</a>
	                        </li>
						</ul>
	                </div>
	                <div class="inner_tab_con_box">
						<div class="inner_tab_con" style="display: block;">
	                        <div class="toolbar">
	                        <c:choose>
	                        	<c:when test="${empty brandNetwork.refrenceId}">
	                        	<label>
									<input type="radio" name="add" checked="checked" value=""/>  新${typeName}
	                            </label>
	                            <label>
	                                    <input id="choose_from_radio" type="radio" name="add" value="${ctx}/brand/brandNetwork/select?id=${id}"/> 从现有终端商选取
	                            </label>
	                            	<!-- 
	                            	<a class="batch_import_btn" href="javascript:;">批量导入</a>
	                                <a class="link" href="#">下载格式规范</a>
	                            	 -->
	                        	</c:when>
	                        	<c:otherwise>
	                        	<label>
									<input type="radio" name="add" checked="checked" value=""/>  ${typeName}
	                            </label>
	                        	</c:otherwise>
	                        </c:choose>
	                            </div>
	                            <!-- 新添加开始 -->
	                            <div class="new_add">
	                                    <div class="ui-form-item">
	                                        <label class="ui-label" for="dealerName">
	                                            终端商名：<span class="ui-form-required">*</span>
	                                        </label>
	                                        <input required minlength="2" data-errormessage="请输入2-100个字符的终端商名称" maxlength="100" id="dealerName" name="dealerName" style="width: 464px;" type="text" class="ui-input" value="${brandNetwork.dealerName}">
	                                    </div>
	                                    <div class="ui-form-item">
	                                        <label class="ui-label" for="userName">
	                                            联系人：<span class="ui-form-required">*</span>
	                                        </label>
	                                        <input required minlength="2" data-errormessage="请输入2-32个字符的联系人" maxlength="32" id="userName" name="userName" placeholder="姓名" style="width: 220px;" type="text" class="ui-input" value="${brandNetwork.userName}">
	                                    </div>
	                                    <div class="ui-form-item">
	                                        <label class="ui-label" for="code">
	                                            电话：<span class="ui-form-required">*</span>
	                                        </label>
	                                        <input id="code" name="code" style="width: 50px;" type="text" class="ui-input" value="${empty brandNetwork.telphone ? "":fn:split(brandNetwork.telphone, "-")[0]}" />
	                                        <span class="">-</span>
	                                        <input id="phone" name="phone" style="width: 64px;" type="text" class="ui-input" value="${empty brandNetwork.telphone ? "":fn:split(brandNetwork.telphone, "-")[1]}"/>
	                                        <span class="">-</span>
	                                        <input id="extension" name="extension" style="width: 50px;" type="text" class="ui-input" value="${empty brandNetwork.telphone ? "":fn:split(brandNetwork.telphone, "-")[2]}"/>
	                                        <input id="telphone" name="telphone" type="hidden"/>
	                                        <span >手机和电话请至少填写一个，电话的格式：区号-电话号码-分机</span>
	                                    </div>
	                                    <div class="ui-form-item">
	                                        <label class="ui-label" for="mobile">
	                                            手机：<span class="ui-form-required">*</span>
	                                        </label>
	                                        <input id="mobile" name="mobile" style="width: 220px;" type="text" class="ui-input" value="${brandNetwork.mobile}">
	                                    </div>
	                                    <div class="ui-form-item">
	                                        <label class="ui-label" for="">
	                                            公司地址：<span class="ui-form-required">*</span>
	                                        </label>
	                                        <jsp:include page="${ctx}/common/regional/searchAllArea">
	                                            <jsp:param value="${brandNetwork.areaNo}" name="regAreaNo"/>
	                                            <jsp:param value="area-ui-select js-select" name="style"/>
	                                        </jsp:include>
	                                        <div class="mt10">
	                                            <textarea class="ui-textarea" id="address" name="address" required data-errormessage="请输入具体地址" style="height: 70px;">${brandNetwork.address}</textarea>
	                                        </div>
	                                    </div>
	                                    <div class="ui-form-item">
	                                        <label class="ui-label" for="imgCount">
	                                            门店形象：<span class="ui-form-required">*</span>
	                                        </label>
	                                        <div class="inline-block">
	                                            <jsp:include page="/WEB-INF/view/brand/common_teps.jsp">
	                                            	<jsp:param value="1" name="imgType"/>
	                                            </jsp:include>
	                                        </div>
	                                        <input type="hidden" id="upError" name="upError" value="1" required/>
	                                    </div>
	                                    <div class="ui-form-item">
	                                        <button class="ui_button ui_button_morange" type="submit" id="addNetwork">保存提交</button>
	                                    </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	        </div>
			</form:form>
	    </div>
	</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/brand_manage.js"></script>
<script>
    //区号-电话号码-分机公用容器显示错误信息
    function showContainerMessage(message,element){
    	//验证出错会调用此函数，如果前面没有出错，才显示自己的错误信息
    	var codeError = $.trim(this.getExplain(element).html());
		if(!codeError){
			this.getExplain(element).html(message);
			this.getItem(element).addClass(this.get('ui-form-explain'));
			element.parent('div').addClass('ui-form-item-error');
		}
    }
    
    //区号-电话号码-分机公用容器清空消息
    function hideContainerMessage(message,element){
    	//验证通过会调用此函数，如果前面没有出错，才清空信息
		var codeError = $.trim(this.getExplain(element).html());
		if(!codeError){
			this.getExplain(element).html('');
			//this.getItem(element).removeClass(this.get('ui-form-explain'));
		}
    }
    
	seajs.use(["validator","widget"],function(Validator,Widget){

		Validator.addRule('code',/^\d{3,4}$/,'请输入正确的{{display}}');
		Validator.addRule('phone',/^\d{7,8}$/,'请输入正确的{{display}}');
		Validator.addRule('extension',/^\d{2,6}$/,'请输入正确的{{display}}');
		Validator.addRule('telphone',/^[0-9]{3,4}-{1}[0-9]{7,8}(-[0-9]{2,6})?$/,'电话号码格式错误');

        Widget.autoRenderAll();

        var Core = Validator.query("#brandNetworkForm");
        
        Core.set({
            failSilently: true,
            autoSubmit: false
            /*
            onFormValidated:function(error,results,element)
            {
            alert("wef");
            return;
                if(!error){
                    ajaxSubmit();
                } 
                
                $.ajax({
		   			url: '${ctx}/brand/brandNetwork/save',
		   			type: 'post',
		   			data: $("#brandNetworkForm").serialize(),
		   			dataType: 'json',
		   			success: function(data, textStatus)
		   			{
		   				if(zttx.SUCCESS==data.code){
							remind("success","经绡商添加成功", function(){
								window.location.href="${ctx}/brand/brandNetwork/list";
							});
						}else if(zttx.VALIDERR==data.code)
						{
							setErrMsg("#brandNetworkForm",data.rows);
						}
						else{
							remind("error",data.message);
						}
		   			},
		   			error: function(data)
		   			{
		   				remind("error",data.message);
		   			}
		   		});
            }
            */
        });
        
        Core.on('formValidated', function (error) {
        	if (error) {
                return;
            }
            $("#addNetwork").attr("disabled", true);
        	var code = $.trim($('#code').val());
			var phone = $.trim($('#phone').val());
			var extension = $.trim($('#extension').val());
	   		var telphone = "";
	   		if(code && phone){
	   			telphone = code+"-"+phone;
	   		}
	   		if(telphone && extension){
	   			telphone = telphone+"-"+extension;
	   		}
	   		if(telphone){
	   			$('#telphone').val(telphone);
	   		}
        	$.ajax({
		   			url: '${ctx}/brand/brandNetwork/save',
		   			type: 'post',
		   			data: $("#brandNetworkForm").serialize(),
		   			dataType: 'json',
		   			success: function(data, textStatus)
		   			{
		   				if(zttx.SUCCESS==data.code){
							remind("success","终端商添加成功", function(){
								var id = $.trim($("#brandsId").val());
								window.location.href="${ctx}/brand/brandNetwork/list?id="+id;
							});
						}else if(zttx.VALIDERR==data.code)
						{
							setErrMsg("#brandNetworkForm",data.rows);
						}
						else{
							remind("error",data.message);
						}
		   			},
		   			error: function(data)
		   			{
		   				remind("error",data.message);
		   			}
		   		});
		   	$("#addNetwork").attr("disabled", false);
        });
        

        Core.addItem({
            element: '#code',
            rule:'code minlength{min:3}',
            required: function(){
            	var phone = $.trim($("#phone").val());
            	var extension = $.trim($("#extension").val());
            	if(phone || extension){
            		return true;
            	}
            	return false;
            },
            display:'区号',
            errormessageCode:'请输入正确的区号',
            errormessageRequired:'请输入区号'
        }).addItem({
            element: '#phone',
            rule:'phone minlength{min:7}',
            required: function(){
            	var code = $.trim($("#code").val());
            	var extension = $.trim($("#extension").val());
            	if(code || extension){
            		return true;
            	}
            	return false;
            },
            display:'电话号码',
            showMessage:showContainerMessage,
            hideMessage:hideContainerMessage,
            errormessageMinlength:'请输入电话号码',
            errormessageRequired:'请输入电话号码',
            errormessagePhone:'请输入正确的电话号码'
        }).addItem({
            element: '#extension',
            rule:'extension',
            required: false,
            display:'分机号',
            showMessage:showContainerMessage,
            hideMessage:hideContainerMessage
        }).addItem({
            element: '#mobile',
            rule:'mobile minlength{min:11} maxlength{max:11}',
            required: function(){
            	var code = $.trim($("#code").val());
            	var phone = $.trim($("#phone").val());
            	var extension = $.trim($("#extension").val());
            	if(!code && !phone && !extension){
            		return true;
            	}
            	return false;
            },
            display:'手机号',
            errormessageMobile:'请输入正确的手机号',
            errormessageRequired:' 手机或电话至少填写一个'
        });
    });
    $(function(){
	    $(".toolbar input:radio").on("click",function(){
	    	var url = $.trim($(this).val());
	    	if(url!="")
	    	{
	    		window.location.href=url;
	    	}
	    });
    });
</script>
</body>
</html>