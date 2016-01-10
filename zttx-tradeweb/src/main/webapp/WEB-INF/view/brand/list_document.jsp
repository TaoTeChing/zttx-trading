<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>管理中心-品牌管理-下载资料管理</title>
<link rel="stylesheet" href="${res}/styles/brand/global.css" />
<link rel="stylesheet" href="${res}/styles/brand/brand_manage.css" />
</head>
<body>
<jsp:include page="/common/menuInfo/mainmenu"/>
<div class="main layout">
<jsp:include page="/common/menuInfo/sidemenu"/>
		<div class="main_con">
            <div class="bread_nav">
                <div class="fl">
                    <a class="link" href="${ctx}/brand/center">管理中心</a>
                    <span class="arrow">&gt;&gt;</span>
                    <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
                    <span class="arrow">&gt;</span>
                    <span class="current">下载资料管理</span>
                </div>
                <div class="fr">
                    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
                </div>
            </div>
			<div class="inner">
				<!-- 内容都放这个地方  -->
				<div class="ui_tab main_ui_tab">
					<ul class="js_tab_box ui_tab_items clearfix">
						<li class="ui_tab_item"><a href="${ctx}/brand/brandDocument">资料上传</a>
						</li>
						<li class="ui_tab_item current"><a
							href="${ctx}/brand/document/list">资料管理</a>
						</li>
						<li class="ui_tab_item"><a href="${ctx}/brand/brandDocument/sort">分类管理</a>
						</li>
					</ul>
				</div>
				<div class="js_tab_con_box tab_con_box">
					<!-- 资料管理开始 -->
					<div class="tab_con" style="display: block;">
						<div class="down_list_info">
							<table class="list_table">
							</table>
							<div id="pagination" class="mt10"></div>
						</div>
					</div>
					<!-- 资料管理结束 -->
				</div>

			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
	<script id="feedback-templage" type="text/html">

	<colgroup>
		<col width="200" />
		<col width="150" />
		<col width="100" />
		<col width="260" />
		<col width="142" />
		<col width="124" />
	</colgroup>
	<tr>
		<th>资料名称</th>
		<th>品牌</th>
		<th>下载地址</th>
		<th>描述</th>
		<th>下载次数</th>
		<th class="last">操作</th>
	</tr>
{{each rows}}
	<tr>
		<td>{{$value.docName}}</td>
		<td>{{$value.brandsName}}</td>
		<td>
			<p>
				<a class="link" href="${ctx}/brand/brandDocument/fileDown?id={{$value.refrenceId}}">本地下载</a>
			</p>
			{{if $value.webAddress!=null && $value.webAddress!=""}}
			<p class="mt5">
				<a class="link" href="{{$value.webAddress}}">网盘下载</a>
			</p>
			{{/if}}
		</td>
		<td>
            <span title="{{$value.docMark}}">{{$trimLongString($value.docMark,50)}}</span>
		</td>
		<td>
			<p data-id="123" class="down_num">已下载：<strong>{{$value.downNum}}</strong> 次</p>
		</td>
		<td>
			<p class="operate">
				<a class="link" href="${ctx}/brand/brandDocument/execute/{{$value.refrenceId}}" style="cursor: pointer">
				<i class="iconfont edit_icon">&#xe618;</i>修改
				</a>
			</p>
			<p class="operate mt5">
				<a class="link" data-uuid="{{$value.refrenceId}}"  name="delLink" style="cursor: pointer">
				<i class="iconfont del_icon">&#xe619;</i>删除
				</a>
			</p>
		</td>
	</tr>
{{/each}}
{{ if rows.length == 0 }}
<tr>
    <td class="ta-c" colspan="6">暂无数据</td>
</tr>
{{ /if }}
</script>
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
	<script>
		var page;
		$(function() {
			seajs.use([ "pagination","template" ], function(Pagination,template) {

                template.helper('$trimLongString', function (string,num) {
                    return trimLongString(string,num);
                });

				var renderPagination = function() {
					page = new Pagination({
						url : "${ctx}/brand/brandDocument/data",
						data : {pageSize: 10},
						elem : "#pagination",
						method : "post",
						handleData : function(json) {

                            var html = template.render("feedback-templage",json);
							$(".list_table").html(html);
							
							$("a[name=delLink]").on('click', function() {
								var $this = $(this);
								confirmDialog("是否确认删除？",function(){
									var url = "${ctx}/brand/brandDocument/delete";
									var uuid = $this.data("uuid");
									$.ajax({
										type : "post",
										url : url,
										data : "uuid="+uuid,
										dataType: 'json',
										success : function(data) {
											if(zttx.SUCCESS==data.code){
												remind("success","资料删除成功");
												page.render();
											}
											else{
												remind("error",data.message);
											}
										}
									});
								});
							});
						}
					});
				};
				renderPagination();
			});
			
		});
	</script>
</body>
</html>