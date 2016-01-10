<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-品牌管理-品牌资料完善</title>
	<link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css" />
    <style>
        .addBrand-form .brand_logo{
            width: 224px;
        }
        .addBrand-form .brand_logo .input_file{
            width: 224px;
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
			        <form:form id="addBrand-form"  data-widget="validator"  class="ui-form addBrand-form vertical-tip" action="${ctx}/brand/brandRoom/save" method="post">
						<jsp:include page="/WEB-INF/view/brand/brands_perfect_menu.jsp">
				        	<jsp:param value="1" name="m"/>
				        </jsp:include>
				        <!-- 品牌基本资料 end -->
				
				        <div class="js_tab_con_box tab_con_box">
				            <div class="tab_con" style="display: block;">
				                    <div class="ui-form-item">
				                        <label class="ui-label" for="">
				                            展厅名称：<span class="ui-form-required">*</span>
				                        </label>
				                        <input id="roomName" name="roomName" style="width: 464px;" type="text" class="ui-input"
				                         data-display="展厅名称" required minlength="2" maxlength="64" 
				                         value="${brandRoom.roomName}"/>
				                    </div>
				                    <div class="ui-form-item photo-item">
				                        <label class="ui-label" for="">
				                            您的品牌LOGO：<span class="ui-form-required">*</span>
				                        </label>
				                        <input type="hidden" id="logoPhoto" name='logoPhoto' value='${brandRoom.logoPhoto}'/>
				                        <input type="hidden" value="${brandRoom.logoImage}" id="logoImage" name="logoImage" required data-errormessage-required="请上传品牌LOGO" >
				                        <div class="inline-block">
				                            <ul class="inline-float certificate_box">
					                            <div class="file_wrap brand_logo">
				                                	<c:if test="${!empty brandRoom}">
				                                		<p class="replace_text">
				                                			<c:set value="${fileUrl}${brandRoom.logoImage}" var="url"></c:set>
						                                	<img alt="${brandRoom.logoPhoto}" src="${fns:getImageDomainPath(url, 240, 120)}" style="width:200px;height:100px;">
														</p>
				                                	</c:if>
				                                	<c:if test="${empty brandRoom}">
						                                <p class="replace_text">点击上传
						                                </p>
				                                	</c:if>
					                                <input id="imgLogoPhoto" name="photo" class="input_file" type="file" />
					                            </div>
					                        </ul>
				                            <p class="mt5 explain">
				                                （图片需为正方形，尺寸建议大于400*200px，仅支持JPG、GIF、PNG图片文件，且文件小于2M）
				                            </p>
				                        </div>
				                    </div>
				                    <div class="ui-form-item vertical-tip">
					                    <label class="ui-label" for="">
					                        企业产品形象图：<span class="ui-form-required">*</span>
					                    </label>
					                    <div class="inline-block">
					                        <ul class="inline-float certificate_box">
					                        <c:if test="${not empty photoList}">
					                            <c:forEach items="${photoList}" var="brandPhoto">
					                                <li class="item">
					                                    <div class="img_contain">
					                                    	<c:set value="${fileUrl}${brandPhoto.imageName}" var="url"></c:set>
					                                        <img style="width:100px;height:100px;" alt="${brandPhoto.photoName}" src="${fns:getImageDomainPath(url, 220, 220)}"><span></span>
					                                        <a data-display="企业产品形象图" href="javascript:;" class="iconfont close">&#xe612;</a>
					                                    	<input type="hidden" value="${brandPhoto.imageName}" name="photoImgPaths">
					                                    	<input type="hidden" value="${brandPhoto.photoName}" name="photoImgNames">
					                                    </div>
					                                </li>
					                            </c:forEach>
					                        </c:if>
					
					                            <li class="add_certificate">
					                                <div class="file_wrap">
					                                    <i class="iconfont">&#xe611;</i>
					                                    <input class="input_file" id="photoImg" name="photo" type="file"/>
					                                </div>
					                            </li>
					                        </ul>
					                        <p class="mt5 explain">企业产品形象图片需为正方形</p>
					                        <p class="explain">（仅支持JPG、GIF、PNG图片文件，且图片小于2M）</p>
					                    </div>
					                    <input type="hidden" id="photoError" required value="1"/>
				            		</div>
				            		<div class="ui-form-item vertical-tip">
						                <label class="ui-label" for="">推荐图片：</label>
						                <div class="inline-block">
						                    <div class="file_wrap brand_logo">
						                        <c:choose>
						                            <c:when test="${empty brandesInfo.recommendImage}">
						                                <p class="replace_text">点击上传</p>
						                                <input class="input_file" id="recommendImage" name="photo" type="file"/>
						                            </c:when>
						                            <c:otherwise>
						                                <p class="replace_text">
						                                    <c:set value="${fileUrl }${brandesInfo.recommendImage}" var="url"></c:set>
						                                    <img style="width:200px;height:100px;" alt="推荐图片" src="${url}">
						                                    <input type="hidden" name="recommendImagePath" value="${brandesInfo.recommendImage}" />
						                                    <input type="hidden" name="recommendImageName" value="" />
						                                    <input class="input_file" id="recommendImage" name="photo" type="file"/>
						                                </p>
						                            </c:otherwise>
						                        </c:choose>
						                    </div>
						                    <p class="mt5 explain">
						                        （上传图片建议为宽400像素高200像素，仅支持JPG、GIF、PNG图片文件，且文件小于2M）
						                    </p>
						                </div>
						                <input type="hidden" id="recommendImageError" required value="1"/>
						            </div>
				                    <div class="ui-form-item">
				                	<label class="ui-label" for="">
						                    品牌标语：<span class="ui-form-required"></span>
						                </label>
						                <textarea class="ui-textarea" placeholder="品牌标语！" name="brandSubMark" style="width: 640px; height: 120px;">${brandesInfo.brandSubMark}</textarea>
						                <input type="hidden" id="subMarkError" required value="1"/>
						            </div>
				                    <div class="ui-form-item">
				                        <label class="ui-label" for="">
				                            品牌介绍：<span class="ui-form-required">*</span>
				                        </label>
				                        <div class="inline-block">
				                            <textarea id="myEditor2" name="brandMark" data-display="品牌介绍" required minlength="2">${brandRoom.brandMark}</textarea>
				                        </div>
				                    </div>
				                    <div class="ui-form-item">
				                         <button type="submit" class="ui_button ui_button_morange" id='save_brandRoom'>保存修改</button>
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
<script src="${res}/scripts/gallery/editor/1.0.0/expand.js"></script>
<script>
		data_perfect.init();
    	seajs.use(["$", 'widget','validator'], function ($, Widget,Validator) {
	    	Widget.autoRenderAll();
	        Validator.query('#addBrand-form').set("autoSubmit",false);
			Validator.query('#addBrand-form').on('formValidated', function(error) {
			if(error){
				return;
			}
			$("#save_brandRoom").attr("disabled", true);
			$.ajax({
		 		  url: '/brand/brandRoom/save',
		          type: 'post',
		          data: $("#addBrand-form").serialize(),
		          dataType: 'json',
		          success: function(data, textStatus)
		          {
                      $("#save_brandRoom").attr("disabled", false);
		          	if(zttx.SUCCESS==data.code){
						remind("success","品牌基本资料保存成功", function(){
							window.location.reload();
						});
					}else if(zttx.VALIDERR==data.code)
					{
						setErrMsg("#addBrand-form",data.rows);
					}
					else{
						remind("error",data.message);
					}
		          },
		  		  error: function(data)
		          {
                      $("#save_brandRoom").attr("disabled", false);
		  			remind("error",data.message);
		          }
		    	});

			});
			
			//选择品牌更新内容
			$('#brandsId').change(function (e) {
	            var id = $(this).val();
	            window.location.href = "${ctx}/brand/brandRoom/execute?id=" + id;
       		 });
        });
    
    
    $("#select_product").on("mouseenter mouseleave click",".item",function(ev){
        switch (ev.type){
            case "mouseenter":
                $(this).find(".close_icon").show();
                break;
            case "mouseleave":
                $(this).find(".close_icon").hide();
                break;
            case "click":
                var id = $(this).attr("id");
                $($("#"+id)).removeClass("current");
                $("#"+id).attr("clicked","false");
                $(this).remove();
        }
    });
    
     $(function(){
    	var uploadIds = ['imgLogoPhoto'];
    	for(var i = 0; i < uploadIds.length; i++){
    		$('#' + uploadIds[i]).bind('change', function(){uploadImage($(this).attr('id'));});
    	}
    	
    	function showImage(uploadId, url, oldName){
    		var html = '';
   			html+= '<img src="${fileUrl}' + url + '" alt="' + oldName + '" style="width:200px;height:100px;"/>';
			$('#' + uploadId).prev().html(html);
    	}
    	
    	function uploadImage(uploadId){
    		seajs.use(["$","ajaxFileUpload"],function($){
    			$.ajaxFileUpload({
        			url: '${ctx}/common/showImg?fSize=2',
        			secureuri: false,
                    fileElementId: uploadId,
                    dataType: 'json',
                    success: function(data)
                    {
                    	//发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                    	$('#' + uploadId).bind('change', function(){
                			uploadImage(uploadId);
                		});
                    	
                    	if(data.code !== zttx.SUCCESS)
                    	{
                    		remind("error",data.message);
                    	}
                    	else
                    	{
                    	    $('#logoImage').val(data.message);
                    	    $('#logoPhoto').val(data.object);
                    	    $(".photo-item .ui-form-explain").hide();
                    		//存储旧文件名
                    		showImage(uploadId, data.message, data.object);
                    	}
                    }
        		});
    	    });
    	};
    });
     
     $(function(){
     	$('#photoImg').bind('change', function(){uploadImage($(this).attr('id'));});
     	
     	function showImage(uploadId, url, oldName){
     	
     		var html = '';
     		
     		var brandsId = $.trim($("#brandsId").val());

             html+= '<li class="item">'
                     +     '<div class="img_contain">'
                     +         '<img src="${fileUrl}' + url + '" alt="' + oldName + '" style="width:100px;height:100px;"/><span></span>'
                     +         '<a href="javascript:;" class="iconfont close">&#xe612;</a>'
                     +		  '<input type="hidden" name="' + uploadId + 'Paths" value="' + url + '" />'
                     +		  '<input type="hidden" name="' + uploadId + 'Names" value="' + oldName + '" />'
                     +     '</div>'
                     + '</li>';
             $('#' + uploadId).parents('ul').prepend(html);
     
     	}
     	
     	function uploadImage(uploadId){
     		
     		seajs.use(["$","ajaxFileUpload"],function($){
     			$.ajaxFileUpload({
         			url: '${ctx}/common/showImg?fSize=2',
         			secureuri: false,
                     fileElementId: uploadId,
                     dataType: 'json',
                     success: function(data)
                     {
                     	//发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                     	$('#' + uploadId).bind('change', function(){
                 			uploadImage(uploadId);
                 		});
                     	
                     	if(data.code == zttx.SUCCESS)
                     	{
                     		showImage(uploadId, data.message, data.object);
                     	}
                     	else
                     	{
                     		remind("error",data.message);
                     	}
                     }
         		});
     	    });
     	}
     });
     
     $(function(){
     	$('#recommendImage').bind('change', function(){uploadImage($(this).attr('id'));});
     	
     	function showImage(uploadId, url, oldName){
     	
     		var html = '';
     		if($('#' + uploadId).parent().find("[name='"+uploadId+"Path']").length > 0){
				$('#' + uploadId).parent().find("img").attr("src",'${fileUrl}' + url);
				$('#' + uploadId).parent().find("[name='"+uploadId+"Path']").val(url);
				$('#' + uploadId).parent().find("[name='"+uploadId+"Name']").val(oldName);
				return;
			}
			html+= '<img src="${fileUrl}' + url + '" alt="' + oldName + '" style="width:200px;height:100px;"/>'
				+  '<input type="hidden" name="' + uploadId + 'Path" value="' + url + '" />'
				+  '<input type="hidden" name="' + uploadId + 'Name" value="' + oldName + '" />';
			$('#' + uploadId).prev().html(html);
     	}
     	
     	function uploadImage(uploadId){
     		
     		seajs.use(["$","ajaxFileUpload"],function($){
     			$.ajaxFileUpload({
         			url: '${ctx}/common/showImg?fSize=2',
         			secureuri: false,
                     fileElementId: uploadId,
                     dataType: 'json',
                     success: function(data)
                     {
                     	//发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                     	$('#' + uploadId).bind('change', function(){
                 			uploadImage(uploadId);
                 		});
                     	
                     	if(data.code == zttx.SUCCESS)
                     	{
                     		showImage(uploadId, data.message, data.object);
                     	}
                     	else
                     	{
                     		remind("error",data.message);
                     	}
                     }
         		});
     	    });
     	}
     });
</script>
</body>
</html>