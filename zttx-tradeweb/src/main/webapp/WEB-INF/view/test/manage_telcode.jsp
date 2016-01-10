<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>验证码管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/myMessage.css"/>
</head>
<body style="padding: 60px;">
	<form:form id="telCodeForm" action="" method="post">
	<table border="1">
	<tr >
		<td colspan="8" height="30">
		手机号码：<input type="text" id="userMobile" name="userMobile"/>
		<input type="button" value="搜索" onclick="page.goTo(1);">
		</div>
	</tr>
	<tr>
			<td align="center" style="width: 100px;">手机号码</td>
			<td align="center" style="width: 150px;">验证码</td>
			<td align="center" style="width: 80px;">客户类别</td>
			<td align="center" style="width: 80px;">验证码类型</td>
			<td align="center" style="width: 150px;">创建时间</td>
			<td align="center" style="width: 150px;">有效时间</td>
			<td align="center" style="width: 80px;">使用状态</td>
			<td align="center" style="width: 150px;">使用时间</td>
		</tr>
		<tbody id="telcodeTbody"></tbody>
		<tr>
		<td colspan="8" height="30"><div class="mt10" id="pagination"></div></td>
		</tr>
	</table>
	</form:form>
	
	
	
	
	<div style="margin-top: 20px;float:left;width:100%;">
		<form:form id="brandForm" style="float:left;">
			<p>品牌商：</p>
			<table  border="1">
				<tr>
					<td align="center" style="width: 80px;">品牌商编号：</td>
					<td style="width: 250px;"><input style="width: 95%;" type="text" name="brandId" id="brandId" /></td>
				</tr>
				<tr>
					<td align="center" style="width: 80px;">公司名称：</td>
					<td style="width: 250px;"><input style="width: 95%;" type="text" name="comName" id="comName" /></td>
				</tr>
				<tr>
					<td align="center" style="width: 80px;">审核状态：</td>
					<td style="width: 250px;">
					<select id="checkState" name="checkState">
						<option value="">请选择审核状态</option>
						<option value="0">未审核</option>
						<option value="1">审核通过</option>
						<option value="2">审核不通过</option>
					</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<a href="javascript:void(0);" onclick="updateBrandState();">修改</a>
					</td>
				</tr>
			</table>
		</form:form>
		
		<form id="dealerForm" style="float:left;margin-left: 10px;">
			<p>经绡商：</p>
			<table  border="1">
				<tr>
					<td align="center" style="width: 80px;">经绡商编号：</td>
					<td style="width: 250px;"><input style="width: 95%;" type="text" name="refrenceId" id="refrenceId" /></td>
				</tr>
				<tr>
					<td align="center" style="width: 80px;">公司名称：</td>
					<td style="width: 250px;"><input style="width: 95%;" type="text" name="dealerName" id="dealerName" /></td>
				</tr>
				<tr>
					<td align="center" style="width: 80px;">审核状态：</td> 
					<td style="width: 250px;">
					<select id="checkState" name="checkState">
						<option value="">请选择审核状态</option>
						<option value="0">未审核</option>
						<option value="1">审核通过</option>
						<option value="2">审核不通过</option>
					</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<a href="javascript:void(0);" onclick="updateDealerState();">修改</a>
					</td>
				</tr>
			</table>
		</form>
		
		<form id="brandsForm" style="float:left;margin-left: 10px;">
			<p>品牌：</p>
			<table  border="1">
				<tr>
					<td align="center" style="width: 80px;">品牌编号：</td>
					<td style="width: 250px;"><input style="width: 95%;" type="text" name="refrenceId" id="refrenceId" /></td>
				</tr>
				<tr>
					<td align="center" style="width: 80px;">品牌名称：</td>
					<td style="width: 250px;"><input style="width: 95%;" type="text" name="brandName" id="brandName" /></td>
				</tr>
				<tr>
					<td align="center" style="width: 80px;">审核状态：</td>
					<td style="width: 250px;">
					<select id="brandState" name="brandState">
						<option value="">请选择审核状态</option>
						<option value="0">新增</option>
						<option value="1">已审核</option>
						<option value="2">合作中</option>
						<option value="3">已取消</option>
						<option value="4">已过期</option>
						<option value="5">已失败</option>
					</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<a href="javascript:void(0);" onclick="updateBrandesState();">修改</a>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	<div style="margin-top: 20px;float:left;width:100%;">
	<form id="brandCountForm" style="float:left;">
		<p>品牌商统计：</p>
		<table  border="1">
			<tr>
				<td align="center" style="width: 80px;">品牌商编号：</td>
				<td style="width: 250px;"><input style="width: 95%;" type="text" name="brandId" id="brandId" /></td>
			</tr>
			<tr>
				<td align="center" style="width: 80px;">公司名称：</td>
				<td style="width: 250px;"><input style="width: 95%;" type="text" name="comName" id="comName" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="javascript:void(0);" onclick="updateBrandCount();">统计重置</a>
				</td>
			</tr>
			
		</table>
	</form>
	
	<form id="dealerCountForm" style="float:left;margin-left: 10px;">
		<p>经绡商统计：</p>
		<table  border="1">
			<tr>
				<td align="center" style="width: 80px;">经绡商编号：</td>
				<td style="width: 250px;"><input style="width: 95%;" type="text" name="refrenceId" id="refrenceId" /></td>
			</tr>
			<tr>
				<td align="center" style="width: 80px;">公司名称：</td>
				<td style="width: 250px;"><input style="width: 95%;" type="text" name="dealerName" id="dealerName" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="javascript:void(0);" onclick="updateDealerCount();">统计重置</a>
				</td>
			</tr>
		</table>
	</form>
	
	<form id="brandsCountForm" style="float:left;margin-left: 10px;">
		<p>品牌统计：</p>
		<table  border="1">
			<tr>
				<td align="center" style="width: 80px;">品牌编号：</td>
				<td style="width: 250px;"><input style="width: 95%;" type="text" name="refrenceId" id="refrenceId" /></td>
			</tr>
			<tr>
				<td align="center" style="width: 80px;">品牌名称：</td>
				<td style="width: 250px;"><input style="width: 95%;" type="text" name="brandName" id="brandName" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="javascript:void(0);" onclick="updateBrandsCount();">统计重置</a>
				</td>
			</tr>
			
		</table>
	</form>
	</div>
	<div style="margin-top: 20px;float:left;width:100%;"></div>
	<script id="feedback-templage" type="text/html">
		{{each rows}}
			<tr>
				<td align="center">{{$value.userMobile}}</td>
				<td align="center">{{$value.verifyCode}}</td>
				<td align="center">{{$value.cusType}}</td>
				<td align="center">{{$value.verifyType}}</td>
				<td align="center">{{$formatDate $value.createTime}}</td>
				<td align="center">{{$formatDate $value.createTime+$value.validTime}}</td>
				<td align="center">{{if $value.useState}}已使用{{else}}未使用{{/if}}</td>
				<td align="center">{{if $value.useTime!=null}}{{$formatDate $value.useTime}}{{/if}}</td>
			</tr>
		{{/each}} 
	</script>
<script src="${res}/scripts/brand/uglify.js"></script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
<script>
function updateBrandState(){
	var brandId = $.trim($("#brandForm #brandId").val());
	var comName = $.trim($("#brandForm #comName").val());
	var checkState = $.trim($("#brandForm #checkState").val());
	if(brandId=="" && comName==""){
		remind("error", "品牌商编号或者公司名称必须填写一项！");
		return;
	}
	if(checkState=="")
	{
		remind("error", " 请选择一项审核状态！");
		return;
	}
	$.post("/temp/manage/telcode/updateBrandState", $("#brandForm").serialize(), function(data){
		if(data.code == zttx.SUCCESS)
		{
			remind("success", "审核状态修改成功！");
		}
		else
		{
			remind("error", data.message);
		}
	}, "json");
};
function updateDealerState(){
	var refrenceId = $.trim($("#dealerForm #refrenceId").val());
	var dealerName = $.trim($("#dealerForm #dealerName").val());
	var checkState = $.trim($("#dealerForm #checkState").val());
	if(refrenceId=="" && dealerName==""){
		remind("error", "经绡商编号或者公司名称必须填写一项！");
		return;
	}
	if(checkState=="")
	{
		remind("error", " 请选择一项审核状态！");
		return;
	}
	$.post("/temp/manage/telcode/updateDealerState", $("#dealerForm").serialize(), function(data){
		if(data.code == zttx.SUCCESS)
		{
			remind("success", "审核状态修改成功！");
		}
		else
		{
			remind("error", data.message);
		}
	}, "json");
};
function updateBrandesState(){
	var refrenceId = $.trim($("#brandsForm #refrenceId").val());
	var brandName = $.trim($("#brandsForm #brandName").val());
	var brandState = $.trim($("#brandsForm #brandState").val());
	if(refrenceId=="" && brandName==""){
		remind("error", "品牌编号或者品牌名称必须填写一项！");
		return;
	}
	if(brandState=="")
	{
		remind("error", " 请选择一项审核状态！");
		return;
	}
	$.post("/temp/manage/telcode/updateBrandesState", $("#brandsForm").serialize(), function(data){
		if(data.code == zttx.SUCCESS)
		{
			remind("success", "审核状态修改成功！");
		}
		else
		{
			remind("error", data.message);
		}
	}, "json");
}
function updateBrandCount(){
	var brandId = $.trim($("#brandCountForm #brandId").val());
	var comName = $.trim($("#brandCountForm #comName").val());
	if(brandId=="" && comName==""){
		remind("error", "品牌商编号或者公司名称必须填写一项！");
		return;
	}
	$.post("/temp/manage/telcode/updateBrandCount", $("#brandCountForm").serialize(), function(data){
		if(data.code == zttx.SUCCESS)
		{
			remind("success", "品牌商统计重置完成！");
		}
		else
		{
			remind("error", data.message);
		}
	}, "json");
}
function updateDealerCount(){
	var refrenceId = $.trim($("#dealerCountForm #refrenceId").val());
	var dealerName = $.trim($("#dealerCountForm #dealerName").val());
	if(brandId=="" && comName==""){
		remind("error", "品牌商编号或者公司名称必须填写一项！");
		return;
	}
	$.post("/temp/manage/telcode/updateDealerCount", $("#dealerCountForm").serialize(), function(data){
		if(data.code == zttx.SUCCESS)
		{
			remind("success", "品牌商统计重置完成！");
		}
		else
		{
			remind("error", data.message);
		}
	}, "json");
}
function updateBrandsCount(){
	var refrenceId = $.trim($("#brandsCountForm #refrenceId").val());
	var brandName = $.trim($("#brandsCountForm #brandName").val());
	if(refrenceId=="" && brandName==""){
		remind("error", "品牌编号或者品牌名称必须填写一项！");
		return;
	}
	$.post("/temp/manage/telcode/updateBrandsCount", $("#brandsCountForm").serialize(), function(data){
		if(data.code == zttx.SUCCESS)
		{
			remind("success", "审核状态修改成功！");
		}
		else
		{
			remind("error", data.message);
		}
	}, "json");
}
</script>
<script>
	  var page;
	  $(function () {
	    seajs.use(["pagination","moment","template"], function (Pagination,moment,template) {
	    	template.helper('$formatDate', function (millsec) {
            	return moment(millsec).format("YYYY-MM-DD HH:mm:SS");
        	});
        	var renderPagination = function(){
        	page = new Pagination({
              url: "${ctx}/temp/manage/telcode/data",
              elem: "#pagination",
              form:$("#telCodeForm"),
              method:"post",
              handleData: function (json) {
                  var html = template.render("feedback-templage", json);
                  $("#telcodeTbody").html(html);
              }
          });
          };
          renderPagination();
        });
      });
	</script>
</body>
</html>