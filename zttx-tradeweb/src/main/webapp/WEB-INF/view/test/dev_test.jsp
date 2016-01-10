<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dev_test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="${res}/scripts/jquery.min.js"></script>
	<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
	<script src="/scripts/seajs/seajs/2.1.1/sea.js" type="text/javascript"></script>
	<script src="/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
	<script src="/scripts/seajs_config.js" type="text/javascript"></script>
	<script type="text/javascript">
	
	function joinShop(productId) {
        $.post("${ctx}/dealer/shoper/purchase/" + productId+"/default", function (data) {
           alert(data);
        }, "json");
    }
	function setCookie(){
	    seajs.use(["cookie"],function(Cookie){
	        Cookie.set("cart_items","cookies_dev_test_value",{path:'/',domain:document.domain});
	        alert('set success!');
	   });
	}
	
	function cleanCookie1(){
		  $.post("${ctx}/market/clearance/cookie", function (data) {
	           alert(data);
	        }, "json");
	}
	function cleanCookie2(){
		  $.post("${ctx}/market/clearance/cookie",{type:"1"}, function (data) {
	           alert(data);
	        }, "json");
	}
	function cleanCookie3(){
		  $.post("${ctx}/market/clearance/cookie",{type:"2"}, function (data) {
	           alert(data);
	        }, "json");
	}
	function testAsync(){
		  $.post("${ctx}/market/clearance/async", function (data) {
	           alert(data);
	        }, "json");
	}
	</script>
  </head>
  
  <body>
    
	<input type="button" value="设置Cookies" onclick="setCookie()"><br/>
	<input type="button" value="清理Cookies1" onclick="cleanCookie1()">
	<input type="button" value="清理Cookies2" onclick="cleanCookie2()">
	<input type="button" value="清理Cookies3" onclick="cleanCookie3()">
	<input type="button" value="testAsync" onclick="testAsync()"><br/>
  </body>
</html>
