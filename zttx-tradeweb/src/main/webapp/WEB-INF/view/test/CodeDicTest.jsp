<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>验证码类型管理测试页面</title>
</head>
<body>



所有数据测试页面：http://localhost:8080/Zttx/client/CodeDic/search


<form action="${ctx }/client/CodeDic/execute" method="post">
	验证码编码：<input type="text" name="codeNo" /> 
	验证码名称：<input type="text" name="codeName" /> 
	验证码说明：<input type="text" name="codeMark" /> 
	<input type="submit" value="新增" />
</form>

<form action="${ctx }/client/CodeDic/update" method="post">
	验证码ID：<input type="text" name="refrenceId" /> 
	验证码编码：<input type="text" name="codeNo" /> 
	验证码名称：<input type="text" name="codeName" /> 
	验证码说明：<input type="text" name="codeMark" /> 
	<input type="submit" value="更新" />
</form>
	
<form action="${ctx }/client/CodeDic/delete" method="post">
	验证码ID：<input type="text" name="id" /> 
	<input type="submit" value="删除单个" />
</form>
	
	验证码ID：<input type="text" name="ids" /> 
	验证码ID：<input type="text" name="ids" /> 
	<input type="button" value="批量删除" onclick="dele();" />
	
	
	
<script src="${res}/scripts/jquery-1.11.0.min.js"></script>
<script type="text/javascript">

function dele(){
	var str=new Array();
	var tcheck=$("input[name='ids']").each(function(){
		str.push($(this).val());
	});
	alert(str);
	
	$.post("/Zttx/client/CodeDic/deleteAll",{ids:str},function (){
		alert("删除成功");
	},'json');
	
}


</script>

</body>
</html>