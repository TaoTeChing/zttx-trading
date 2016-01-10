<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-产品管理-上传产品</title>
	<link rel="stylesheet" href="${res}/styles/brand/global.css" />
</head>
<body>
<jsp:include page="/common/menuInfo/mainmenu"/> 
	<div class="main layout">
		 <jsp:include page="/common/menuInfo/sidemenu"/>
<div class="main_con">
    <!-- 内容都放这个地方  -->
    <div class="bread_nav">
        <div class="fl">
            <a class="link" href="${ctx}/brand/center">管理中心</a>
            <span class="arrow">&gt;&gt;</span>
            <a class="link" href="${ctx}/brand/brandes">产品管理</a>
            <span class="arrow">&gt;</span>
            <span class="current">上传产品</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
                <jsp:param value="0" name="isShow"/>
            </jsp:include>
        </div>
    </div>
    <div class="inner">
        <div style="padding: 5px 10px;">
            <div class="mt10" style="color:red;font-size:150%;">提示：上传成功的商品处于下架中状态，请查看后自行上架，仅支持Excel上传</div>
            <div class="mt10" >模板1说明：产品基本信息与颜色尺码分两个页切填写，可设置各颜色尺码所对应的价格和库存。</div>
            <div class="mt10" >模板2说明：产品基本信息与颜色尺码在同一页填写，各颜色尺码的价格和库存均一致。多个颜色尺码需用英文逗号隔开。</div>
            <div class="mt10" >使用模板2上传产品，请先下载安装 <a style="color:blue;" href="${ctx}/uploads/excelPlugins/vba.rar">插件</a></div>
			<div class="mt10" >模板3说明：模板3为模板2的简易模板，方便用户更加快捷方便上传产品。<b>使用该功能前,请先确定运费模板是否已添加</b>。</div>
            <div class="mt10">
                <input id="sourceExcelFile" type="hidden" value=""/>
                <input id="uploadBtn" name="file" type="file"/>
                <input id="importBtn" type="button" value="导入"/>
                <a href="${ctx}/brand/product/upload/getTemplate" >下载模板1</a>
                <a href="${ctx}/brand/product/upload/getTemplate?type=1" >下载模板2</a>
				<a href="${ctx}/brand/product/upload/getTemplate?type=2">下载模板3</a>
                <%--<input id="downloadBtn" type="button" value="下载模板1"/>
                <input id="downloadBtn1" type="button" value="下载模板2"/>
            --%></div>
            <div id="errorPanel">
                下载未导入数据：<a id="errorExcelFile" herf="javascript:void(0);" target="_blank">错误文件.xls</a>
            </div>
            <div id="msgPanel">

            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script>
	
	$(function(){
		initErrorPanel();
		initMsgPanel();
		bindUpload("uploadBtn");
		bindImport("importBtn");
		//bindDownload("downloadBtn");
		//bindDownload("downloadBtn1", 1);
	});

	function bindUpload(uploadId){
		$('#' + uploadId).bind('change', function(){
			seajs.use(["$","ajaxFileUpload"],function($){
				$.ajaxFileUpload({
					url: '${ctx}/common/showFileLocal?fSize=2&requiredFileTypes=.xls',
					secureuri: false,
					fileElementId: uploadId,
					dataType: 'json',
					success: function(data)
					{
						//发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
						bindUpload(uploadId);
						
						if(data.code == zttx.SUCCESS)
						{
							$("#sourceExcelFile").val(data.message);
							remind("success", "上传成功" + data.object);
						}else if(data.code == '121004'){
							remind("error", '文件格式不符合要求');
						}else if(data.code == '126106'){
							remind("error", '超出文件上传大小，请重新选择');
						}else
						{
							remind("error", data.message);
						}
					}
				});
			});
		});
	}
	
	function bindImport(importId)
	{
		$('#' + importId).bind('click', function(){
			var filePath = $("#sourceExcelFile").val();
			if(null == filePath || filePath == "")
			{
				remind("error", "请先导入文件");
				return;
			}
			$('#' + importId).attr("disabled", "disabled");
			$("#sourceExcelFile").val("");
			initErrorPanel();
			initMsgPanel();
			$.post("${ctx}/brand/product/upload/importData", {filePath: filePath}, function(data){
				$('#' + importId).removeAttr("disabled");
				if(data.code == zttx.SUCCESS)
				{
					//remind("success", "导入成功");
				}
				else
				{
					remind("error", data.message);
				}
				initErrorPanel(data.object);
				initMsgPanel(data.rows);
			}, "json");
		});
	}
	
	function bindDownload(downloadId, type)
	{
		var postData = {};
		if(type)
		{
			postData["type"] = type;
		}
		$('#' + downloadId).bind('click', function(){
			$('#' + downloadId).attr("disabled", "disabled");
			$.post("${ctx}/brand/product/upload/getTemplate", postData, function(data){
				$('#' + downloadId).removeAttr("disabled");
				if(data.code == zttx.SUCCESS)
				{
					location.href = "${res}" + data.object;
				}
				else
				{
					remind("error", data.message);
				}
			}, "json");
		});
	}
	
	function initErrorPanel(fileUrl)
	{
		if(null != fileUrl && "" != fileUrl)
		{
			$("#errorExcelFile").attr("href", "${ctx}/brand/product/upload/getError?errorUrl=" + fileUrl);
			$("#errorPanel").show();
		}
		else
		{
			$("#errorExcelFile").attr("href", "javascript:void(0);");
			$("#errorPanel").hide();
		}
	}
	
	function initMsgPanel(msgList)
	{
		$("#msgPanel").html("");
		if(null != msgList)
		{
			for(var i = 0; i < msgList.length; i++)
			{
				$("#msgPanel").append(msgList[i] + "<br/>");
			}
		}
	}
    
</script>
</body>
</html>