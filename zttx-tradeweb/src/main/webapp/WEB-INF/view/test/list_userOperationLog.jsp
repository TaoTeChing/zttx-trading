<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>用户操作日志</title>
	<link rel="stylesheet" href="${res}/styles/brand/global.css" />
	<link rel="stylesheet" href="${res}/styles/brand/myMessage.css" />
</head>
<body style="padding: 60px;">
	<form id="mainForm" action="" border="1">
		<table style="width: 100%">
			<tr>
				<td>
					操作类型：
					<select name="type" class="ui-select" style="height:30px;">
						<option value="">请选择</option>
						<option value="0">操作押金</option>
						<option value="1">操作订单</option>
						<option value="4">操作产品</option>
						<option value="5">操作活动产品</option>
					</select>
				</td>
				<td>
					对象ID：<input type="text" name="objectId" class="ui-input" />
				</td>
				<td>
					操作人：<input type="text" name="userName" class="ui-input" />
					<button id="search" class="simple_button" type="button">搜索</button>
				</td>
			</tr>
		</table>
	</form>
	<table id="rowPagination" style="width: 100%;margin-top:20px;">
		<thead>
			<tr>
				<th style="width: 15%;">操作人</th>
				<th style="text-align: center; width: 15%;">操作时间</th>
				<th style="width: 70%;">内容</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<div id="pagination" class="pagination mt10"></div>
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
	<script id="dataList" type="text/html">
	{{each rows}}
	<tr data-id="{{$value.objectId}}" loginType="{{$value.loginType}}" loginIP="{{$value.loginIP}}">
		 <td style="width:15%;">{{$value.userName}}</td>
		 <td style="text-align: center;width:15%;">{{$formatDate $value.eventTime}}</td>
		 <td style="width:70%;">{{$value.event}}</td>
	</tr>
	{{/each}}
	</script>
	<script>
		seajs.use([ '$', "pagination", "moment", "template" ], function($, Pagination, moment, Template) {
			template.helper('$formatDate', function(millsec) {
				return moment(millsec).format("YYYY-MM-DD hh:mm:ss");
			});
			var data = $("#data").val();
			var formObj = $("#mainForm");
			var renderPage = new Pagination({
				url : "${ctx}/common/temp/userOperationLogListJson",
				elem : "#pagination",
				form : formObj,
				pageSize:20,
				method : "post",
				handleData : function(data) {
					var html = Template.render('dataList', data);
					$('#rowPagination tbody').html(html);
				}
			});
			$('#search').on('click', function(event) {
				renderPage.goTo(1);
			});
		});
	</script>
</body>
</html>
