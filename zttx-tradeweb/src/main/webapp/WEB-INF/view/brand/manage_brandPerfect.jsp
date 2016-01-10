<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-品牌管理-品牌资料完善</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css" />
   
	<style>
		.brand_vision_con .video_item .close{
			background: #fff;
			border: #000 solid 1px;
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
		<form:form action="${ctx}/brand/brandVisual/visual/save"  data-widget="validator" class="ui-form addBrand-form vertical-tip" method="post" id="risualForm">
            <input type="hidden" name="opType" id="opType"/>
            <jsp:include page="/WEB-INF/view/brand/brands_perfect_menu.jsp">
        		<jsp:param value="4" name="m"/>
        	</jsp:include>
            <div class="tips">
                <i class="v2-icon-explain"></i>
                说明：上传企业形象图，展示企业实力
            </div>
            <div class="tab_con_box">
                <!-- 品牌视觉开始 -->
                <div class="tab_con brand_vision_con" style="display: block;">
                        <%--<div class="ui-form-item">
                            <label class="ui-label" for="">
                                视频地址：
                            </label>
                            <div class="inline-block">
                            <input maxlength="128" id="vedioFile" name="vedioFile" style="width: 400px;" type="text" class="ui-input" value="${brandVisual.vedioFile}">
                            <input type="hidden" id="video" required value="1"/>
								 <c:set value="${brandVisual.domainName }${brandVisual.vedioFile }" var="vedioFile"></c:set>
                                <ul class="inline-float">
                                    <li class="video_item" style="display: ${ !empty vedioFile  ? 'block' : 'none'};" id="show_video" >
                                    	<div id="show_video_1">
                                    			
                                    	</div>
                                    	<i class="close">×</i>
                                    	<input id="show_video_2" type="hidden" name="vedioFile" value="${brandVisual.vedioFile}"  /> 
										<input id="show_video_3" type="hidden" name="vedioDoc" value="${brandVisual.vedioDoc}" data-display="视频上传"/>
										
										
                                    </li>
                                     <li class="video_item add_video add_video_1" style="display: ${!empty vedioFile ? 'none' : 'block'};" id="show_video_input">
	                                     <div class="file_wrap"  >
		                                         <p class="replace_text video">点击上传</p>
		                                        <input type="file" value="文件上传" name="video" class="input_file" id="video"  />
		                                    </div>
                                     </li> 
                                   
                                </ul>
                                <p class="explain mt5"><u>（仅支持AVI  FLV  WMV格式，且文件小于5M）</u></p> 
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label" for="">3D动画展厅地址：</label> 
                            <div class="inline-block">
                            <input maxlength="128" id="threeFile" name="threeFile" style="width: 400px;" type="text" class="ui-input" value="${brandVisual.threeFile}">
                            <input type="hidden" id="flash" required value="1"/>
                              <c:set value="${brandVisual.domainName }${brandVisual.threeFile }" var="threeFile"></c:set>
                                <ul class="inline-float">
                                    <li class="video_item"  style="display: ${ !empty threeFile  ? 'block' : 'none'};" id="show_flash">
	                                    <div id="show_Flash_1">
                                    			
                                    	</div>
                                    	<i class="close">×</i>
                                    	<input id="show_Flash_2" type="hidden" name="threeFile" value="${brandVisual.threeFile }"  /> 
										<input id="show_Flash_3" type="hidden" name="threeDoc" value="${brandVisual.threeDoc }"  data-display="3D动画展厅"/>
	                                    
		                                    
                                    </li>
                                     <li class="video_item add_video add_video_1"  style="display: ${!empty threeFile  ? 'none' : 'block'};" id="show_flash_input">
                                     		<div class="file_wrap">
		                                        <p class="replace_text video" id="show_flash_input">点击上传</p>
		                                    	<input type="file" value="文件上传" name="flash" id="flash" class="input_file"   />
		                                    </div>
                                     </li>
                                    
                                    
                                   
                                </ul>
                                <p class="explain mt5">
                                    （仅支持SWF格式，且文件小于5M）
                                </p> 
                            </div>
                        </div>
                        --%><div class="ui-form-item">
                            <label class="ui-label" for="">相册上传：<span class="ui-form-required"></span></label>
	                        <div id="upError" class="inline-block">
								<%--特殊依赖，暂时引进来jquery--%>
								<script src="${res}/scripts/jquery.min.js"></script>
	                        	<jsp:include page="/WEB-INF/view/brand/common_teps.jsp">
	                        		<jsp:param value="20" name="imgTotal"/>
	                        		<jsp:param value="5" name="imgRowTotal"/>
	                        	</jsp:include>
	                        	<input type="hidden" name="upError" id="upError" value="1" required/>
	                        </div>
                        </div>
                        <%--<div class="ui-form-item">
                            <label class="ui-label" for="">描述内容：<span class="ui-form-required"></span></label>
                            <div id="brandMark" class="inline-block">
                               
                                <textarea id="myEditor4" name="brandMark" data-display="描述内容">${brandVisual.brandMark}</textarea>
                            </div>
                        </div>
                       --%><div class="ui-form-item">
	                    	<button type="button" class="ui_button ui_button_morange" id='save_brandRoom' onclick="setOpType('0')">保存修改</button>
	                        <a class="ui_button ui_button_mgray" href="javascript:setOpType('1');" id="preview" style="display:none;">预&nbsp;览</a>
	                    </div>

                </div>
                <!-- 品牌视觉结束 -->
            </div>

       </form:form> 	

    </div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/brand_manage.js"></script>
<script>

       data_perfect.init();
    
    
      seajs.use(["$", 'widget','validator'], function ($, Widget,Validator) {
	    	Widget.autoRenderAll();
	       // Validator.query('#risualForm').set("autoSubmit",false);
			Validator.query('#risualForm').on('formValidated', function(error) {
			if(error){
				return;
			}
			var opType = $.trim($("#opType").val());
			if("0"==opType){
				$("#save_brandRoom").attr("disabled", true);
                Validator.query('#risualForm').set("autoSubmit",false);

                $("#risualForm").attr('target','');
				$.ajax({
			 		  url: '/brand/brandVisual/visual/save',
			          type: 'post',
			          data: $("#risualForm").serialize(),
			          dataType: 'json',
			          success: function(data, textStatus)
			          {
                          $("#save_brandRoom").attr("disabled", false);
			          	if(zttx.SUCCESS==data.code){
							remind("success","陈列视觉保存成功", function(){
								window.location.reload();
							});
						}else if(zttx.VALIDERR==data.code)
						{
							setErrMsg("#risualForm",data.rows);
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

			    }else
			    {
			    	Validator.query('#risualForm').set("autoSubmit",true);
			    	$("#risualForm").attr('target','_blank').attr('action','${ctx }/brand/visual/view');
			    }
			}); 
			
			//选择品牌更新内容
			$('#brandsId').change(function (e) {
	            var id = $(this).val();
	            window.location.href = "${ctx}/brand/brandVisual/visual?id=" + id;
       		 });
        });
        
        function setOpType(t){
        	$("#opType").val(t);
        	$("#risualForm").submit();
        }
            
    
    
    
    	var index = 0;	
    	/* $(function (){
    		
    		//下拉选
    		$('#brandsId').bind('change',function(){
    			$('#form1').submit();
    		});
    		
    		//预览
    		$('#preview').bind('click', function (){
    			var op = $('#brandsId option:selected').val();
    			var vedio = $('input[name="vedioFile"]').val();
    			var three = $('input[name="threeFile"]').val();
    			
    			if(op == '' || vedio == '' || three == ''){
    				remind("error","请先填写完必填项才能预览");
    				return ;
    			}
    			var flag = false;
    			var imgArr = $('input[name="images"]').val();
    			$('input[name="images"]').each(function (index, data){
    				if($(this).val() != ''){
    					flag = true;
    					return ;
    				}
    			});
    			if(flag){
    				$('#risualForm').attr('target','_blank').attr('action','${ctx }/brand/visual/view').submit();
    			}else{
    				remind("error","图片至少上传一张");
    			}
    			
    			
    		});
    		
    		 $('#save').bind('click',function (){
    			$('#form2').attr('target','').attr('action','${ctx }/brand/visual/save');
    			$('#form2').submit();
    		});
    	}); */
   
   		/* $(function (){
   			var aLi =  $('#show_img li');
   			var html = '<li class="item"><input type="hidden" name="images" value="" />';
    			html += '<input type="hidden" name="photoName" value="" />';
    			html += '<input type="hidden" name="brandAlbumIds" value="" /></li>';
   			for(var i = aLi.size(); i < 6; i++){
   				$('#show_img').append(html);
   			}
   		});
   
   
   		$(function (){
   			$('#show_img li').each(function (i){
   				if(!$(this).find('img').attr('src')){
   					$(this).find('input[name="images"]').val("");
   					$(this).find('input[name="photoName"]').val("");
   				}
   			});
   		});	 */
	   
	  	$(document).on("click","#show_video .close",function(){
	        $(this).hide();
	        $('#show_video_1').html('');
	        $('#show_video_2').val('');
	        $('#show_video_3').val('');
	        $('#show_video').hide();
	        $('#show_video_input').show();
	        $('#video').prev('.replace_text').text("点击上传");
	    }) 
	    
	    $(document).on("click","#show_flash .close",function(){
	        $(this).hide();
	        $('#show_Flash_2').html('');
	        $('#show_Flash_2').val('');
	        $('#show_Flash_2').val('');
	        $('#show_flash').hide();
	        $('#show_flash_input').show();
	        $('#flash').prev('.replace_text').text("点击上传");
	    }) 
	    
	    
	    $(document).on("mouseenter mouseleave",".video_item",function(ev){
	        var oupdate = $(this).find(".update");
	        if(ev.type == "mouseenter"){
	            oupdate.show();
	        }else if(ev.type == "mouseleave"){
	            oupdate.hide();
	        }
	    })
   

    	
    	/* $(function (){
    		var li = $('#show_img li').size();
    		if(li < 6){
    			var cha = 5 - li;
    			for(var i = 0; i <  cha; i++){
    				$('#show_img').append('<li></li>');
    			}
    			
    		}
    	}); */
   
   
   
   
   
   
    	$(function (){
			upload("video","${ctx}/common/showVideo",show_video);
			upload("flash","${ctx}/common/showFlash", show_flash);
			//upload("photo","${res}/common/showImg", showImg,true); 
		});
    	
    	
    	
    	
    	function upload(name, url, show, vefiy){
    		$('input[name='+name+']').bind('change', function(){
    			uploadImage($(this).attr('id'), url, show, vefiy);
    		});
    	}
    	
    	$(function  (){
    		setTimeout(initVedio, 2000);
    	});
    	
    	
    	
    	//初始化视频
    	function initVedio(){
    		var vedio = '${brandVisual.vedioFile }';
    		var threeFile = '${brandVisual.threeFile }';
    		if( vedio  != ''){
    			setVideo(vedio, "show_video_1");
    		}
    		if( threeFile != ''){
    			setVideo(threeFile, "show_Flash_1");
    		}
    		
    	}
    	
    	
    	//设置视频值
    	function setVideo(url, id){
    		 seajs.use(["video"],function(Video){
	       		var so = new Video({
	       			width: 200,
	       			height: 110,
	       			url : url,
	       			element : id,
	       			CuPlayerWidth : 220,
	       			CuPlayerHeight : 120
	       		})
	       })
    	}
    	
    	//显示视频
    	function show_video(uploadId,url,data){
    		if($('#show_video').is(':hidden')){
    			$('#show_video').show();
    			$('#show_video_input').hide();
    		}
   		   setVideo(data.video, "show_video_1");
   		   $('#show_video i').show();
    		$('#show_video input[name="vedioFile"]').val(data.video);
    		$('#show_video input[name="vedioDoc"]').val(data.videoName);
    	}
    	
    	//现实flash
    	function show_flash(uploadId,url,data){
    		if($('#show_flash').is(':hidden')){
    			$('#show_flash').show();
    			$('#show_flash_input').hide();
    		}
    		
    		setVideo(data.video, "show_Flash_1");
    		$('#show_flash i').show();
    		$('#show_flash input[name="threeFile"]').val(data.video);
    		$('#show_flash input[name="threeDoc"]').val(data.videoName);
    		 
    	}
    	
	
    
    
    	function uploadImage(uploadId, url,show,vefiy){
    	
    		if(vefiy){ 
	    		if(index > 6){
	    			remind("error","本类目下您最多可以上传6张图片");
	    			return ;
	    		}
    		}
    		$('#'+uploadId).prev('.video').text("上传中......");
    		seajs.use(["$","ajaxFileUpload"],function($){
    			$.ajaxFileUpload({
        			url: url,
        			secureuri: false,
                    fileElementId: uploadId,
                    dataType: 'json',
                    success: function(data)
                    {
                    	//发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                    	$('#' + uploadId).bind('change', function(){
                			uploadImage(uploadId,url, show, vefiy);
                		});
                		
                    	if(data.code  != zttx.SUCCESS)
                    	{
        					remind("error",data.message);
        					$('#'+uploadId).prev('.replace_text').text("点击上传");
                    	}
                    	else
                    	{
                    		show(uploadId,data.message,data.object);
                    	}
                    }
        		})
    	    })
    	}
</script>
</body>
</html>