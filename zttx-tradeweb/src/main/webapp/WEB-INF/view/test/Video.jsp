<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
 <jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>视频上传测试页面</title>
</head>
<body>



<div class="file_wrap">
    <p class="replace_text video" id="show_flash_input">点击上传</p>
   	<input type="file" value="文件上传" name="video" id="flash" class="input_file" required  />
</div>
<div id="video">


</div>


<script type="text/javascript">


	$(function (){
		$('input[name="video"]').bind('change', function(){
    			uploadImage($(this).attr('id'), "${ctx}/common/showVideo");
    		});
	});

	//显示视频
   	function show_video(uploadId,url,data){
  		 setVideo(data.video, "video");
  		 
   	}
   	
   	//设置视频值
   	function setVideo(url, id){
   		 seajs.use(["video"],function(Video){
       		var so = new Video({
       			width: 500,
       			height: 400,
       			url : url,
       			element : id,
       			CuPlayerWidth : 600,
       			CuPlayerHeight : 500
       		})
       })
   	}
   	

	function uploadImage(uploadId, url){
    	
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
                			uploadImage(uploadId,url);
                		});
                		
                    	if(data.code  != 126000)
                    	{
        					alert(data.message);
                    	}
                    	else
                    	{
                    		show_video(uploadId,data.message,data.object);
                    	}
                    }
        		})
    	    })
    	}

</script>

</body>
</html>