<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>上传图片附件类型大小管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/myMessage.css"/>
</head>
<body style="padding: 60px;">
	<a href="/temp/manage/uploadAttCate" style="color: red;">返回</a>
	<table border="1">
		<tr>
			<td align="center" style="width: 100px;">资料编号</td>
			<td align="center" style="width: 150px;">类型Key</td>
			<td align="center" style="width: 150px;">宽度</td>
			<td align="center" style="width: 150px;">高度</td>
			<td align="center" style="width: 150px;">创建时间</td>
			<td align="center" style="width: 150px;">操作</td>
		</tr>
		<c:forEach var="obj" items="${dataList}" varStatus="status">
			<tr>
				<td align="center">${status.index + 1}</td>
				<td align="center">${obj.cateKey}</td>
				<td align="center">${obj.width}</td>
				<td align="center">${obj.height}</td>
				<td align="center">${fns:getTimeFromLong(obj.createTime,"yyyy-MM-dd HH:mm:ss")}</td>
				<td align="center">
					<a href="javascript:void(0);" onclick="dataUpdateBefore('${obj.refrenceId}', '${obj.height}', '${obj.width}');">修改</a>&nbsp;
					<a href="javascript:void(0);" onclick="dataDelete('${obj.refrenceId}');">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div style="margin-top: 20px;">
		<form:form id="js-dictValue">
			<input type="hidden" name="refrenceId" />
			<input type="hidden" name="attCateId" value="${attCateId}" />
			<p>上传图片附件类型大小：</p>
			<table  border="1">
				<tr>
					<td align="center" style="width: 80px;">宽度：</td>
					<td style="width: 250px;"><input style="width: 95%;" type="text" name="width" /></td>
				</tr>
				<tr>
					<td align="center" style="width: 80px;">高度：</td>
					<td style="width: 250px;"><input style="width: 95%;" type="text" name="height" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<a href="javascript:void(0);" onclick="dataAdd();">新增</a>&nbsp;&nbsp;
						<a href="javascript:void(0);" onclick="dataUpdate();">修改</a>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
<script src="${res}/scripts/brand/uglify.js"></script>
<script>
	function dataAdd()
	{
		$.post("/temp/manage/uploadAttSize/add", $("#js-dictValue").serialize(), function(data){
			if(data.code == zttx.SUCCESS)
			{
				remind("success", "保存成功");
				location.href = location.href;
			}
			else
			{
				remind("error", data.message);
			}
		}, "json");
	}
	
	function dataUpdateBefore(refrenceId, height, width)
	{
		$(":input[name='refrenceId']").val(refrenceId);
		$(":input[name='height']").val(height);
		$(":input[name='width']").val(width);
	}
	
	function dataUpdate()
	{
		$.post("/temp/manage/uploadAttSize/update", $("#js-dictValue").serialize(), function(data){
			if(data.code == zttx.SUCCESS)
			{
				remind("success", "保存成功");
				location.href = location.href;
			}
			else
			{
				remind("error", data.message);
			}
		}, "json");
	}
	
	function dataDelete(refrenceId)
	{
		$(":input[name='refrenceId']").val(refrenceId);
		$.post("/temp/manage/uploadAttSize/delete", $("#js-dictValue").serialize(), function(data){
			if(data.code == zttx.SUCCESS)
			{
				remind("success", "保存成功");
				location.href = location.href;
			}
			else
			{
				remind("error", data.message);
			}
		}, "json");
	}
</script>
</body>
</html>