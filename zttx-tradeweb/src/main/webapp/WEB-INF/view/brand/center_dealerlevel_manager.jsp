<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>管理中心-终端商管理-等级管理</title>
<link rel="stylesheet" href="${res}/styles/brand/global.css" />
<link rel="stylesheet" href="${res}/styles/brand/agencymanag.css" />
<link rel="stylesheet" href="${res}/styles/common/validform.css" />
</head>
<body>
	<jsp:include page="/common/menuInfo/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="/common/menuInfo/sidemenu"/>
		<div class="main_con">
            <!-- 面包导航，并不是每个页面都有 -->
            <div class="bread_nav">
                <div class="fl">
                     <a class="link" href="${ctx}/brand/center">管理中心</a>
		            <span class="arrow">&gt;&gt;</span>
		            <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
		            <span class="arrow">&gt;</span>
		            <span class="current">${title}</span>
                </div>
                
               <div class="fr">
		            <c:choose>
		                <c:when test="${empty info.refrenceId}">
		                    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp"/>
		                </c:when>
		                <c:otherwise>
		                    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
		                        <jsp:param value="0" name="isShow"/>
		                    </jsp:include>
		                </c:otherwise>
		            </c:choose>
      		  </div>
            </div>
            
            
			<div class="inner">
				<div class="agency-grademanage-contant">
					<div class="js_grademanage_menu clearfix">
						<ul class="clearfix">
							<li class="selected"><a href="${ctx}/brand/brandLevel/level" style="display: block">等级管理</a></li>
							<li><a href="${ctx}/brand/brandLevel/setup" style="display: block">设置等级</a></li>
						</ul>
					</div>
					<div class="js_grademanage_con">
						<div>
							<div class="grademanage-add" id="op_info">添加等级</div>
                            <div class="tips">
                                <i class="v2-icon-explain"></i>
                                说明：您可以为不同的品牌设置不同的终端商等级
                            </div>
							<form:form id="grademanage_form_add" cssClass="ui-form ui-grademanage-form">
								<input type="hidden" name="refrenceId" id='refrenceId'>
								<div class="ui-form-item">
									<label for="" class="ui-label">品牌：</label> 
									<div class="inline-block">
										<select class="js_select grademanage-select" name="brandsId" id="brandsId">
                                            <c:if test="${brandesInfos.size() == 0 || brandesInfos.size() > 1}">
											    <option value="">请选择</option>
                                            </c:if>
											<c:forEach items="${brandesInfos}" var="brandesInfo">
												<option value="${brandesInfo.refrenceId}">${brandesInfo.brandsName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="ui-form-item">
									<label for="" class="ui-label">等级名称：</label>
									<div class="inline-block">
										<input class="grademanage-select" style="width:237px;height:29px;" id="levelName" name="levelName">
									</div>
								</div>
								<div class="ui-form-item">
									<label for="" class="ui-label">内容描述：</label>
									<textarea class="ui-textarea grademanage-textarea" name="levelMark" id="levelMark"></textarea>
								</div>
								<div class="ui-form-item">
									<label for="" class="ui-label"></label> 
									<a id="btn_add" href="javascript:void(0);" class="ui_button ui_button_lblue">新增</a>
									<a id="btn_mod" href="javascript:void(0);" class="ui_button ui_button_lblue" style="display:none;">修改</a>
									<a id="btn_reset" href="javascript:void(0);" class="ui_button simple_button" style="display:none;">取消</a>
								</div>
							</form:form>
							<%--<div class="grademanage-add">已有等级</div>--%>
							<form:form id="grademanage_form_list" method="get" action="${ctx}/brand/level">

								<table class="grademanage-add-table">
									<colgroup>
										<col width="130" />
										<col width="140" />
										<col width="345" />
										<col width="130" />
										<col width="130" />
									</colgroup>
									<thead>
										<tr>
											<th>品牌</th>
											<th>终端商等级</th>
											<th>描述</th>
											<th>终端商</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="grademanage_table_body">
									</tbody>
								</table>
								<div class="pageParent"></div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
			
			
			
			
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
	<script src="${src}/brand/agencymanag.js"></script>
<script id="ajax-templage-level" class="ajax-templage" _url="${ctx}/brand/brandLevel/list" _page="div.pageParent" _pid="#grademanage_table_body" type="text/html">
{{each rows}}
<tr id="{{$value.refrenceId}}" data-brandsid="{{$value.brandsId}}">
	<td>{{$value.brandesInfo.brandsName}}</td>
	<td>{{$value.levelName}}</td>
	<td>{{$value.levelMark}}</td>
	<td>
		<a class="bluefont" href="${ctx}/brand/brandLevel/setup?brandsId={{$value.brandsId}}&levelId={{$value.refrenceId}}">管理</a>
	</td>
	<td>
		<a id="js-updateLevel" class="bluefont" href="javascript:void(0);" data-refrenceid="{{$value.refrenceId}}">修改</a> 
		<a id="js-removeLevel" class="bluefont" href="javascript:void(0);" data-refrenceid="{{$value.refrenceId}}">删除</a>
	</td>
</tr>
{{/each}}
</script>
	<script>
		grademanage.init();
		var brandlevel = {};
		
		var pageTemp = {}, validator = {};
		$(function(){
			seajs.use(["pagination", "template", "validator"], function (Pagination, template, Validator){
				function ajaxTemplate()
				{
					var $this = $("#ajax-templage-level");
					var tempId = $this.attr("id");
					pageTemp = new Pagination({
						url: $this.attr("_url"),
						elem: $this.attr("_page"),
						method: "post",
						handleData: function (data) {
							var html = template.render(tempId, data);
							$($this.attr("_pid")).html(html);
						}
					});
				}
				ajaxTemplate();
				
				/**
				 * 刷新
				 */
				brandlevel.refresh = function(){
					var page = pageTemp;
					page.goTo(page.current);
				}
				
				validator = new Validator({
		            element: '#grademanage_form_add',
		            failSilently: true,
		            stopOnError: true,
		            autoSubmit: false
		        }).addItem({
		            element: '#brandsId',
		            required: true,
		            errormessage: '请输入品牌'
		        }).addItem({
		            element: '#levelName',
		            required: true,
		            rule: 'minlength{min:1} maxlength{max:32}',
		            errormessage: '请输入等级名称'
		        }).addItem({
		            element: '#levelMark',
		            required: true,
		            rule: 'minlength{min:1} maxlength{max:1024}',
		            errormessage: '请输入内容描述'
		        });
				
				brandlevel.init();
			});
		});
		
		brandlevel.init = function(){
			$("#brandsId").bind("change", function(){
				brandlevel.cancel();
			});
			
			$("#btn_add").bind("click", function(){
				validator.execute(function(hasError, results, element){
					if(!hasError){
						brandlevel.submit();
					}
				});
			});
			
			$("#btn_reset").bind("click", function(){
				brandlevel.cancel();
			});
			
			$("#btn_mod").bind("click", function(){
				validator.execute(function(hasError, results, element){
					if(!hasError){
						brandlevel.confirmModify();
					}
				});
			});
			
			$(document).on('click','#js-updateLevel',function(){
				var refrenceId = $(this).data("refrenceid");
				brandlevel.modify(refrenceId);
			});
			
			$(document).on('click','#js-removeLevel',function(){
				var refrenceId = $(this).data("refrenceid");
				brandlevel.remove(refrenceId);
			});
		}

		/**
		 * 保存数据
		 */
		brandlevel.submit = function() {
			$.post("${ctx}/brand/brandLevel/add", $('#grademanage_form_add').serialize(), function(data) {
				if(data.code == zttx.SUCCESS)
				{
					remind("success", "保存成功");
					brandlevel.cancel();
					brandlevel.refresh();
				}
				else
				{
					remind("error", data.message);
				}
			}, "json");
		};

		/**
		 * 修改数据
		 */
		brandlevel.modify = function(id) {
			$('#op_info').html("修改等级");
			$('#refrenceId').val(id);
			$('#brandsId').val($('#' + id).data("brandsid"));
			renderSelect('#brandsId');
			$('#levelName').val($('#' + id).find("td:eq(1)").html());
			$('#levelMark').val($('#' + id).find("td:eq(2)").html());
			$("#btn_add").hide();
			$("#btn_mod,#btn_reset").show();
		};

		/**
		 *取消修改
		 */
		brandlevel.cancel = function() {
			$('#op_info').html("添加等级");
			$('#refrenceId').val("");
			$("#levelName").val("");
			$("#levelMark").val("");
			$("#btn_add").show();
			$("#btn_mod, #btn_reset").hide();
		};
		/**
		 * 确认修改
		 */
		brandlevel.confirmModify = function() {
			$.post("${ctx}/brand/brandLevel/modify", $('#grademanage_form_add').serialize(), function(data) {
				if(data.code == zttx.SUCCESS)
				{
					remind("success", "保存成功");
					brandlevel.cancel();
					brandlevel.refresh();
				}
				else
				{
					remind("error", data.message);
				}
			}, "json");
		};
		/**
		 * 删除数据
		 */
		brandlevel.remove = function(id) {
			confirmDialog("确定要删除该数据么？",function(){
				$.post("${ctx}/brand/brandLevel/delete", {refrenceId: id}, function(data){
					if(data.code == zttx.SUCCESS)
					{
						remind("success", "删除成功");
						brandlevel.refresh();
					}
					else
					{
						remind("error", data.message);
					}
				}, "json");
			});
		};
	</script>
	<!--
    另外加载当前页面需要的js路径，或者使用
    seajs.use("./xxx.js")
 -->
</body>
</html>
