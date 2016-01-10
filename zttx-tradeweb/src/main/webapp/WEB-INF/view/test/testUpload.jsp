<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传请求转发验证</title>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://images.8637.com/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="http://images.8637.com/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script src="/scripts/seajs_config.js"></script>
<script src="http://images.8637.com/scripts/common.js"></script>
<script>

	
	function uploadImage(uploadId){
		//alert("===");
		var csrfToken = $('#_csrf-form').find('input[name="csrfToken"]').val();
		seajs.use(["$","ajaxFileUpload"],function($){
			$.ajaxFileUpload({
    			url: '/test/abc/upload?csrfToken='+csrfToken,
    			secureuri: false,
                fileElementId: uploadId,
                dataType: 'json'
                
    		});
	    });
	}
	</script>
</head>
<body>
<%--
<form:form id="_csrf-form" cssStyle="display: none">
</form:form>
<form:form method="post" action="/test/abc/upload" enctype="multipart/form-data">
	用户名：<input type="text" name="userName"/><br/>
	<input type="file" name="doc"/><br/>
	<input type="submit" value="提交到images.8637.com"/>
</form:form>
--%> 
<form:form id="_csrf-form" cssStyle="display: none">
</form:form>
<form method="post" action="/test/abc/upload" enctype="multipart/form-data">
	用户名称：<input type="text" name="userName"/><br/>
	<input type="file" name="doc" id="doc"/><br/>
	<input id="subform" type="button" onclick="uploadImage($('#doc').attr('id'))" value="提交到images.8637.com"/>
</form>



	
</body>
</html>