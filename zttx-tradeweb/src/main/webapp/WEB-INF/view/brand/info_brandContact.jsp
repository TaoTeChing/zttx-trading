<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-我的账户-账户信息-联系资料</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/myAccount.css" />
    
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
                    <a class="link" href="${ctx}/brand/contact/list">我的账户</a>
                    <span class="arrow">&gt;</span>
                    <a class="link" href="${ctx}/brand/contact/list">账户信息</a>
                    <span class="arrow">&gt;</span>
                    <span class="current">联系资料</span>
                </div>
                <div class="fr">
                    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
                </div>
            </div>
			<div class="inner">
				<div class="contact_info">
					<form:form class="ui-form" action="${ctx }/brand/contact/save" id="addBrand-form" method="post" data-widget="validator" >
					<c:set  value="${brandContact.refrenceId }"  var="refrenceId"></c:set>
						<input type="hidden" name="refrenceId" value="${refrenceId }" /> 
						<div class="top clearfix">
							<div class="photo fl">
								 <c:choose>
                                     <c:when test="${brandContact.userPhoto != null}"><!-- 调用图片地址的转换 -->
                                     	<c:set value="${brandContact.userPhoto}" var="url"></c:set>
                                     	<img alt="" src="${res}${fns:getImageDomainPath(url, 120, 120)}" id="id_photo" style="width:120px; height:120px;">
                                     	<input type="hidden" name="userPhoto" value="${brandContact.userPhoto}"  />
                                     </c:when>
                                     <c:otherwise>
                                     	<img alt="" src="${res}/images/brand/defalut_photo.png" id="id_photo" style="width:120px;height:120px;">
                                     	<input type="hidden" name="userPhoto" value="" />
                                     </c:otherwise>
                                 </c:choose>
								<div class="file_wrap">
									<p class="replace_text">上传头像</p>
									<input type="file" class="input_file" name="photo" id="photo">
								</div>
							</div>
							<div class="basic_info">
								<div class="ui-form-item">
									<label class="ui-label" for=""> 账户名： </label>
									<input class="ui-input" value="${brandContact.accountName }"  type="text" disabled />
								</div>
								<div class="ui-form-item mail">
									<label class="ui-label" for=""> 邮&nbsp;&nbsp;箱： </label>
									<c:choose>
										<c:when test="${brandContact.mailVerify}">
											<input class="ui-input" type="text" value="${brandContact.userMail}" name="userMail" data-rule="email" data-display="邮箱"  disabled/>
											<span>已认证</span>
										</c:when>
										<c:otherwise>
											<input class="ui-input" type="text" value="${brandContact.userMail}" name="userMail" data-rule="email" data-display="邮箱"/>
											<c:if test="${not empty brandContact.userMail}">
												<span class="no_certified">未认证</span>
												<a class="simple_button ml10" href="javascript:sendMail();" id="sendMail">马上认证</a>
											</c:if>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="title_bar">
							<h3>
								<i class="iconfont">&#xe601;</i><span>联系方式</span>
							</h3>
						</div>
						<div class="detail_info">
							<div class="ui-form-item">
								<label class="ui-label" for=""> 联系人：<span
									class="ui-form-required">*</span> </label> 
									<input class="ui-input" type="text" value="${brandContact.userName}" name="userName" required data-display="联系人" />
							</div>
							<div class="ui-form-item">
								<label class="ui-label" for=""> 性别：<span
									class="ui-form-required">*</span> </label>
								<div class="radio_box inline-block">
									 <c:choose>
	                                     <c:when test="${!empty brandContact.userGender}">
	                                     	<input class="ui-radio" ${brandContact.userGender == 1 ? 'checked' : '' } name="userGender" type="radio" value="1"  />先生 
											<input class="ui-radio ml10" ${brandContact.userGender == 2 ? 'checked' : '' } name="userGender" type="radio" value="2" />小姐
											<input class="ui-radio ml10" ${brandContact.userGender == 0 ? 'checked' : '' } name="userGender" type="radio" value="0"  />保密
	                                     </c:when>
	                                     <c:otherwise>
	                                     	<input  class="ui-radio" checked  name="userGender" type="radio" value="1"  />先生 
											<input class="ui-radio ml10"  name="userGender" type="radio" value="2" />女士
											<input class="ui-radio ml10"  name="userGender" type="radio" value="0"  />保密
	                                     </c:otherwise>
	                                 </c:choose>
								</div>
							</div>
							<div class="ui-form-item">
								<label class="ui-label" for=""> 职务： </label> 
								<input class="ui-input" type="text" value="${brandContact.userJob}" name="userJob" data-display="职务" />
							</div>
							<div class="ui-form-item">
								<label class="ui-label" for=""> 手机号码：<span
									class="ui-form-required">*</span> </label> 
									<input class="ui-input" type="text" value="${brandContact.userMobile}"  name="userMobile" id="userMobile" data-rule="mobile" data-display="手机号码" />
							</div>
							<div class="ui-form-item">
								<label class="ui-label" for=""> 公司电话：<span
									class="ui-form-required">*</span> </label> 
									<input class="ui-input" type="text" value="${brandContact.userTelphone}" name="userTelphone" id="userTelphone"  data-display="公司电话"  />
									<span class="ui-form-explain" >手机和电话请至少填写一个，电话的格式：区号-电话号码-分机</span>
							</div>
							<div class="ui-form-item">
								<label class="ui-label" for=""> 传真号码：</label>
									<input class="ui-input" type="text" value="${brandContact.userFax }" name="userFax" id="userFax" data-display="传真号码" />
							</div>
						</div>
                        <div class="title_bar">
                            <h3>

                                <i class="iconfont">&#xe601;</i><span>在线客服</span>

                                <label style="margin-left: 10px;font-size: 12px;">
                                    <input type="checkbox"  name="showed" value="1"  ${userOnlineService.showed == 1 ? 'checked': '' }>
                                                                                                                           是否使用在线客服？
                                </label>
                            </h3>
                        </div>
                        <div class="detail_info js-set-time">
                            <div class="ui-form-item">
                                <label class="ui-label" for=""> 在线时间： </label>
                                <div class="inline-block">
                                    <select style="width: 100px" class="js-select ui-select" name="onlineDateType">
                                        <option <c:if test="${userOnlineService.onlineDateType == 0}">selected</c:if> value="0">周一到周五</option>
                                        <option <c:if test="${userOnlineService.onlineDateType == 1}">selected</c:if> value="1">周一到周六</option>
                                        <option <c:if test="${userOnlineService.onlineDateType == 2}">selected</c:if> value="2">周一到周日</option>
                                    </select>
                                </div>
                                <input id="startTime" name="tmpBeginTime" value='${fns:getTimeFromLong(userOnlineService.onlineBeginTime,"HH:mm:ss")}' style="width: 100px;" class="ui-input" type="text" />
                                -
                                <input id="endTime" name="tmpEndTime" value='${fns:getTimeFromLong(userOnlineService.onlineEndTime,"HH:mm:ss")}' style="width: 100px;" class="ui-input" type="text" />
                            </div>
                            <div id="customer-area" class="ui-form-item">
                                <label class="ui-label" for=""> QQ客服： </label>
                                <c:if test="${empty userOnlineService.onlineDetailList}">
                                	<div class="inline-block module">
                                    	<input class="ui-input" type="text" value="" name="qqs" data-rule="number  maxlength{max:16}"  data-display="业务QQ"/>
                                    	<input class="ui-input ml5" name="names" maxlength="10" style="width: 120px;" type="text" placeholder="客服显示名称" />
                                    	<a class="add-customer ml5" href="javascript:;">添加</a>
                                	</div>
                                </c:if>
                                <c:if test="${not empty userOnlineService.onlineDetailList}">
                                	<c:forEach var="obj" items="${userOnlineService.onlineDetailList}" varStatus="status">
                                		<c:if test="${status.index == 0}">
                                			<div class="inline-block module">
                                    			<input class="ui-input" type="text" value="${obj.qq}" name="qqs" data-rule="number  maxlength{max:16}"  data-display="业务QQ"/>
                                    			<input class="ui-input ml5" value="${obj.name}" name="names" maxlength="10" style="width: 120px;" type="text" placeholder="客服显示名称" />
                                    			<a class="add-customer ml5" href="javascript:;">添加</a>
                                			</div>
                                		</c:if>
                                		<c:if test="${status.index > 0}">
                                			<div class="inline-block mt10 module">
        										<input class="ui-input" type="text" value="${obj.qq}" name="qqs" data-rule="number  maxlength{max:16}"  data-display="业务QQ"/>
        										<input class="ui-input ml5" value="${obj.name}" name="names" maxlength="10" style="width: 120px;" type="text" placeholder="客服显示名称" />
        										<a class="add-customer ml5" href="javascript:;">添加</a>
        										<a class="del-customer ml5" href="javascript:;">删除</a>
    										</div>
                                		</c:if>
									</c:forEach>
                                </c:if>
                            </div>
                        </div>
                        <div class="detail_info">
                            <div class="ui-form-item">
                                <button class="ui_button ui_button_lblue" type="submit">保存</button>
                            </div>
                        </div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<tags:message content="${message}" width="300"></tags:message>
<script id="customer_temp" type="text/html">
    <div class="inline-block mt10 module">
        <input class="ui-input" type="text" name="qqs" data-rule="number  maxlength{max:16}"  data-display="业务QQ"/>
        <input class="ui-input ml5" name="names" maxlength="10" style="width: 120px;" type="text" placeholder="客服显示名称" />
        <a class="add-customer ml5" href="javascript:;">添加</a>
        <a class="del-customer ml5" href="javascript:;">删除</a>
    </div>
</script>
<script type="text/javascript">

     //新增客服
     $("#customer-area").on("click",".add-customer",function(){
         if($("#customer-area .module").length > 2){
             return;
         }
         $($("#customer_temp").html()).appendTo("#customer-area");
     })
     //删除客服
     $("#customer-area").on("click",".del-customer",function(){
         $(this).parent().remove();
     })

     $("#startTime").add("#endTime").addClass("hasDatepicker");
     baseCalendar("#startTime",{dateFmt:'H:00:00'});
     baseCalendar("#endTime",{dateFmt:'H:00:00'});

	 function sendMail(){
    	var dealerMail = $.trim($(".mail input[name='userMail']").val());
      	if(dealerMail == ''){
      		remind("error","邮箱不能为空");
      		return ;
      	}
      	var reg = /^([A-Za-z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
      	if(!reg.exec(dealerMail)){
      		remind("error","邮箱格式不正确");
      		return ;
      	}
      	$.ajax({
				type : "POST",
				url : "${ctx}/common/emailValidate/create",
				data : {userCate: 0, emailAddr: dealerMail },
				dataType: "json",
				success : function(json) {
					if(json.code == zttx.SUCCESS){
						remind("success","认证邮件发送成功！");
						$(".mail .no_certified").html("认证中");
						$("#sendMail").hide();
					}else{
						remind("error",json.message);
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert(errorThrown);
				}
			});


      	//TODO 调用发邮件的接口
      	//remind("success","邮件已经发送，请到指定的邮箱中点击认证");
      	//shoTime($('#sendMail'), 60,"sendMail()", "马上认证");


    }

	function shoTime(o,wait, functionName, mesg)
		{
			if (wait == 0) {
				o.attr("href",  "javascript:"+functionName+";");
				o.text(mesg);
			} else {

				o.attr("href", "javascript:;");
				o.text("重新发送(" + wait + ")");
				wait--;
				setTimeout(function() {
					shoTime(o, wait, functionName, mesg);
				},
				1000);
			}
		}


		seajs.use(["validator","widget"],function(Validator,Widget){
		        Widget.autoRenderAll();
		        // 老电话错误正则/^((\+?[0-9]{2,4}\-[0-9]{3,4}\-)|([0-9]{3,4}\-))?([0-9]{7,8})(\-[0-9]+)?$/
		          Validator.addRule('dianhua', /^((\+?[0-9]{2,4}\-[0-9]{3,4}\-)|([0-9]{3,4}\-))([0-9]{7,8})(\-[0-9]+)?$/, '请输入正确的{{display}}格式');
		          Validator.addRule('zw', /[\u4e00-\u9fa5]|[a-zA-Z]/,  '请输入正确的{{display}}格式');
		          var Core = Validator.query("#addBrand-form").addItem({
		        	 element: '[name=userTelphone]',
		        	 required : function(){
		        	 	var value = $.trim($('#userMobile').val());
                        var thisVal = $.trim($('#userTelphone').val());
                        if(value == "" && thisVal ==""){
                            return true;
                        }
                         if(thisVal!==""){
                             return true;
                         }
                         else{
                             return false;
                         }
		        	 	//return value == "" ? true : false;
		        	 },
		        	 rule : 'dianhua'
		        }).addItem({
		        	element: '[name=userMobile]',
		        	 required : function (){
		        	 	var value = $.trim($('#userTelphone').val());
		        		return value == "" ? true : false;
		        	 }
		        }).addItem({
		        	element : '[name=userFax]',
		        	rule : 'dianhua'
		        }).addItem({
		        	element : '[name=userJob]',
		        	rule : 'zw  maxlength{max:10}'
		        }).addItem({
		        	element : '[name=userName]',
		        	rule : 'zw  maxlength{max:10}',
		        	required : true
		        });
                Core.set("autoSubmit",false);

                Core.on('formValidated', function(error, message, elem) {
					if($("[name='qqs']").val()==''||$("[name='names']").val()==''){
						if($("[name='qqs']").val()!=''||$("[name='names']").val()!=''){
						remind("error","QQ跟客服名称必须同时为空或不为空");
						return;
						}
					}
                    if(!error){

                    	$.post("${ctx}/brand/contact/save", $('#addBrand-form').serialize(), function(data){
    						if(data.code == zttx.SUCCESS)
    						{
    							remind("success", "保存成功", function(){
    								location.href = '${ctx}${0 == brandUserm.userType ? "/brand/contact/list" : "/brand/info/index"}';
    							});
    						}
    						else
    						{
    							remind("error", data.message);
    						}
    					}, "json");
                    }
                });


        });




		$(function (){
			$('input[name="photo"]').bind('change', function(){uploadImage($(this).attr('id'));});
		})


    	function showImage(uploadId, url){
    		$('#id_photo').attr('src', "${res}"+url);
    		$('input[name="userPhoto"]').attr('value',url);
    	}

    	function uploadImage(uploadId){
    		seajs.use(["$","ajaxFileUpload"],function($){
    			$.ajaxFileUpload({
        			url: '${ctx}/common/showImg?fSize=1',
        			secureuri: false,
                    fileElementId: uploadId,
                    dataType: 'json',
                    success: function(data)
                    {
                    	//发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                    	$('#' + uploadId).bind('change', function(){
                			uploadImage(uploadId);
                		});

                    	if(data.code  != zttx.SUCCESS)
                    	{
        					alert(data.message);
                    	}
                    	else
                    	{
                    		showImage(uploadId,data.message);
                    	}
                    }
        		});
    	    });
    	}


    function showed(){
        if($("input[name=showed]").prop("checked")){
            $(".js-set-time").show();
        }else{
            $(".js-set-time").hide();
        }
    }
    showed();
    $("input[name=showed]").click(function(){
        showed();
    });
</script>
</body>
</html>