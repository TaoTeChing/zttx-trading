<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>数据字典管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/myMessage.css"/>
</head>
<body style="padding: 60px;">
	<table border="1">
		<tr>
			<td align="center" style="width: 100px;">资料编号</td>
			<td align="center" style="width: 150px;">字典编码</td>
			<td align="center" style="width: 150px;">字典名称</td>
			<td align="center" style="width: 150px;">字典描述</td>
			<td align="center" style="width: 150px;">建档时间</td>
			<td align="center" style="width: 150px;">操作</td>
		</tr>
		<c:forEach var="obj" items="${dataList}" varStatus="status">
			<tr>
				<td align="center">${status.index + 1}</td>
				<td align="center">${obj.dictCode}</td>
				<td align="center">${obj.dictName}</td>
				<td align="center">${obj.dictDesc}</td>
				<td align="center">${fns:getTimeFromLong(obj.createTime,"yyyy-MM-dd HH:mm:ss")}</td>
				<td align="center">
					<a href="${ctx}/temp/manage/dataDictValue?dictId=${obj.refrenceId}">查看</a>&nbsp;
					<a href="javascript:void(0);" onclick="dataUpdateBefore('${obj.refrenceId}', '${obj.dictCode}', '${obj.dictName}', '${obj.dictDesc}');">修改</a>&nbsp;
					<a href="javascript:void(0);" onclick="dataDelete('${obj.refrenceId}');">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div style="margin-top: 20px;">
		<form:form id="js-dict">
			<input type="hidden" name="dataDictId" />
			<input type="hidden" name="refrenceId" />
			<p>字典：</p>
			<table  border="1">
				<tr>
					<td align="center" style="width: 80px;">字典编码：</td>
					<td style="width: 250px;"><input style="width: 95%;" type="text" name="dictCode" /></td>
				</tr>
				<tr>
					<td align="center" style="width: 80px;">字典名称：</td>
					<td style="width: 250px;"><input style="width: 95%;" type="text" name="dictName" /></td>
				</tr>
				<tr>
					<td align="center" style="width: 80px;">字典描述：</td>
					<td style="width: 250px;"><textarea style="width: 95%;" name="dictDesc"></textarea></td>
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
		$.post("/temp/manage/dataDict/add", $("#js-dict").serialize(), function(data){
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
	
	function dataUpdateBefore(refrenceId, dictCode, dictName, dictDesc)
	{
		$(":input[name='refrenceId']").val(refrenceId);
		$(":input[name='dictCode']").val(dictCode);
		$(":input[name='dictName']").val(dictName);
		$(":input[name='dictDesc']").val(dictDesc);
	}

	function dataUpdate()
	{
		$.post("/temp/manage/dataDict/update", $("#js-dict").serialize(), function(data){
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
	
	function dataDelete(dictId)
	{
		$(":input[name='dataDictId']").val(dictId);
		$.post("/temp/manage/dataDict/delete", $("#js-dict").serialize(), function(data){
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