<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
 <jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>BrandCrm生成</title>
</head>
<body>
		<h3>BrandCrm生成</h3>
			
		<select name="" id="select">
			<option value="0" >生成所有</option>
			<option value="1" >BrandUserm</option>
			<option value="2" >createBrandInfo</option>
			<option value="3" >createBrandContact</option>
			<option value="4" >createDealerUserm</option>
			<option value="5" >createDealerInfo</option>
			<option value="6" >createBrandesInfo</option>
		</select>
		<input type="button" value="点击生成" id="btn" />
		
	
		
		
</div>


<script src="${res}/scripts/brand/uglify.js"></script>
		<script type="text/javascript"	>
			$(function (){
				$('#btn').on('click', function (){
					var val = $('#select').val();
					switch (parseInt(val)) {
					case 0:
						sendPost("createAll");
						break;
					case 1:
						sendPost("createBrandUserm");
						break;
					case 2:
						sendPost("createBrandInfo");
						break;
					case 3:
						sendPost("createBrandContact");
						break;
					case 4:
						sendPost("createDealerUserm");
						break;
					case 5:
						sendPost("createDealerInfo");
						break;
					case 6:
						sendPost("createBrandesInfo");
						break;
					default:
						break;
					}
				});
				function sendPost(uri){
					$.post("/temp/manage/" + uri, function(data){
						if(data.code == 126000)
						{
							alert("成功");
						}
						else
						{
							alert("失败");
						}
					}, 'json');
					
				}
			
			})
		
		
		</script>

</body>
</html>