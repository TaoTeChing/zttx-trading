<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>上传图片附件类型管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/myMessage.css"/>
</head>
<a href="${ctx}/temp/manage/uploadAttSize/makeBrands">生成品牌LOGO缩略图</a>
<a href="${ctx}/temp/manage/uploadAttSize/makePhoto">生成品牌形象缩略图</a>
<a href="${ctx}/temp/manage/uploadAttSize/makePro">生成产品缩略图</a>
<a href="${ctx}/temp/manage/uploadAttSize/makeNetImg">生成经销网络缩略图</a>

<body style="padding: 60px;">
	<table border="1">
		<tr>
			<td align="center" style="width: 100px;">资料编号</td>
			<td align="center" style="width: 150px;">类型</td>
			<td align="center" style="width: 150px;">模块Key</td>
			<td align="center" style="width: 150px;">创建时间</td>
			<td align="center" style="width: 150px;">操作</td>
		</tr>
		<c:forEach var="obj" items="${dataList}" varStatus="status">
			<tr>
				<td align="center">${status.index + 1}</td>
				<td align="center">${obj.cateKey}</td>
				<td align="center">${obj.attName}</td>
				<td align="center">${fns:getTimeFromLong(obj.createTime,"yyyy-MM-dd HH:mm:ss")}</td>
				<td align="center">
					<a href="${ctx}/temp/manage/uploadAttSize?attCateId=${obj.refrenceId}">查看</a>&nbsp;
					<a href="javascript:void(0);" onclick="dataUpdateBefore('${obj.refrenceId}', '${obj.cateKey}', '${obj.attName}');">修改</a>&nbsp;
					<a href="javascript:void(0);" onclick="dataDelete('${obj.refrenceId}');">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div style="margin-top: 20px;">
		<form:form id="js-dict">
			<input type="hidden" name="refrenceId" />
			<p>上传图片附件类型：</p>
			<table  border="1">
				<tr>
					<td align="center" style="width: 80px;">类型：</td>
					<td style="width: 250px;"><input style="width: 95%;" type="text" name="cateKey" /></td>
				</tr>
				<tr>
					<td align="center" style="width: 80px;">模块Key：</td>
					<td style="width: 250px;"><input style="width: 95%;" type="text" name="attName" /></td>
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
		$.post("/temp/manage/uploadAttCate/add", $("#js-dict").serialize(), function(data){
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
	
	function dataUpdateBefore(refrenceId, cateKey, attName)
	{
		$(":input[name='refrenceId']").val(refrenceId);
		$(":input[name='cateKey']").val(cateKey);
		$(":input[name='attName']").val(attName);
	}

	function dataUpdate()
	{
		$.post("/temp/manage/uploadAttCate/update", $("#js-dict").serialize(), function(data){
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
		$.post("/temp/manage/uploadAttCate/delete", $("#js-dict").serialize(), function(data){
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