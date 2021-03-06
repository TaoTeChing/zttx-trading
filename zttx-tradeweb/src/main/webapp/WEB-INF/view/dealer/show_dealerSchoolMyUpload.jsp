﻿<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>终端商管理中心 - 商学院管理 - 我的上传 - 通过审核</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    
    <link href="${res}/styles/dealer/index.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/school_index.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/alreas.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/SchoolLearn.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/finance.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
        <jsp:include page="${ctx}/dealer/mainmenu"/>
        <div class="em100">
            <div class="main clearfix">
                <!--侧边导航-->
               
                    <jsp:include page="${ctx}/dealer/dealermenu" />
               
                <!--主体内容-->
                <div class="main-right">
                    <jsp:include page="${ctx}/WEB-INF/view/dealer/show_dealerSchool_bar.jsp">
						<jsp:param name="m" value="2" />
					</jsp:include>
					<div class="main-grid mt10">
                    	<div class="main-grid pt3">
                        	<div class="tabpanel">
                            	<div class="tabs clearfix">
                                	<div id="js-myUpload-status" class="fl tac mb10" style="padding: 10px 0;">
                                    	<div class="fl wd80 bdn"><a href="javascript:void(0);" style="color: #0082cc;" id="js-state1" _id="ajax-templage-myUpload1">通过审核</a></div>
                                    	<div class="fl wd80"><a href="javascript:void(0);" style="color: #333;" id="js-state0" _id="ajax-templage-myUpload0">等待审核</a></div>
                                    	<div class="fl wd80"><a href="javascript:void(0);" style="color: #333;" id="js-state2" _id="ajax-templage-myUpload2">未通过审核</a></div>
                                	</div>
                            	</div>
                            	<div class="content ">
                                	<div class="tabitem">
                                    	<table class="panel-table"></table>
                                    	<div class="EndBottom"></div>
                                	</div>
                            	</div>
                        	</div>
                    	</div>
                	</div>



                </div>
            </div>
        </div>
       <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
<script id="ajax-templage-myUpload1" class="ajax-templage" _url="${ctx}/dealer/school/list.json?checkState=1&pageSize=8" _page=".EndBottom" _pid=".panel-table" type="text/html">
<thead>
	<tr>
		<th class="tb15 tac">缩略图</th>
		<th class="tb35 tac">标题名称</th>
		<th class="tb10 tac">出售价格</th>
		<th class="tb10 tac">学习次数</th>
		<th class="tb15 tac">上传时间</th>
		<th class="tb15 tac">操作</th>
	</tr>
</thead>
<tbody>
	{{each rows}}
	<tr {{if $index % 2 == 0}}class="dan"{{else}}class="ou"{{/if}}>
		<td>
			<a href="${ctx}/school/detail/{{$value.refrenceId}}">
				<img src="${res}{{$value.domainName + $value.articleIcon}}" width="100"  height="60" class="pb10pt15"  alt=""/>
			</a>
		</td>
		<td>
			<a href="${ctx}/school/detail/{{$value.refrenceId}}">
				{{$value.articleTitle}}
			</a>
		</td>
		<td>{{$formatMoney $value.articlePrice}}</td>
		<td>{{$value.viewNum}}</td>
		<td>{{$formatDate $value.createTime}}</td>
		<td><i class="iconfont">&#xe618;</i><a href="${ctx}/dealer/school/execute?artId={{$value.refrenceId}}" class="c-b">修改</a> <i class="icon i-delete"></i><a href="javascript:void(0);" class="c-b" onclick="funcDelArt('{{$value.refrenceId}}',{{$value.checkState}});">删除</a></td>
	</tr>
	{{/each}}
</tbody>
</script>
<script id="ajax-templage-myUpload0" class="ajax-templage" _url="${ctx}/dealer/school/list.json?checkState=0&pageSize=8" _page=".EndBottom" _pid=".panel-table" type="text/html">
<thead>
	<tr>
		<th class="tb15 tac">缩略图</th>
		<th class="tb35 tac">标题名称</th>
		<th class="tb10 tac">出售价格</th>
		<th class="tb15 tac">上传时间</th>
		<th class="tb15 tac">操作</th>
	</tr>
</thead>
<tbody>
	{{each rows}}
	<tr {{if $index % 2 == 0}}class="dan"{{else}}class="ou"{{/if}}>
		<td><img src="${res}{{$value.domainName + $value.articleIcon}}" width="100"  height="60" class="pb10pt15"  alt=""/></td>
		<td>{{$value.articleTitle}}</td>
		<td>{{$formatMoney $value.articlePrice}}</td>
		<td>{{$formatDate $value.createTime}}</td>
		<td><i class="iconfont">&#xe618;</i><a href="${ctx}/dealer/school/execute?artId={{$value.refrenceId}}" class="c-b">修改</a> <i class="icon i-delete"></i><a href="javascript:void(0);" class="c-b" onclick="funcDelArt('{{$value.refrenceId}}',{{$value.checkState}});">删除</a></td>
	</tr>
	{{/each}}
</tbody>
</script>
<script id="ajax-templage-myUpload2" class="ajax-templage" _url="${ctx}/dealer/school/list.json?checkState=2&pageSize=8" _page=".EndBottom" _pid=".panel-table" type="text/html">
<thead>
	<tr>
		<th class="tb15 tac">缩略图</th>
		<th class="tb35 tac">标题名称</th>
		<th class="tb10 tac">出售价格</th>
		<th class="tb15 tac">上传时间</th>
		<th class="tb10 tac">未通过原因</th>
		<th class="tb15 tac">操作</th>
	</tr>
</thead>
<tbody>
	{{each rows}}
	<tr {{if $index % 2 == 0}}class="dan"{{else}}class="ou"{{/if}}>
		<td><img src="${res}{{$value.domainName + $value.articleIcon}}" width="100"  height="60" class="pb10pt15"  alt=""/></td>
		<td>{{$value.articleTitle}}</td>
		<td>{{$formatMoney $value.articlePrice}}</td>
		<td>{{$formatDate $value.createTime}}</td>
		<td><a href="#" class="Notby">点击查看</a></td>
		<td><i class="iconfont">&#xe618;</i><a href="${ctx}/dealer/school/execute?artId={{$value.refrenceId}}" class="c-b">修改</a> <i class="icon i-delete"></i><a href="javascript:void(0);" class="c-b" onclick="funcDelArt('{{$value.refrenceId}}',{{$value.checkState}});">删除</a></td>
	</tr>
	<tr style="display:none;"><td colspan="7"></td></tr>
	<tr style="display:none; background-color:#f2f1f1;" class="Notbys">
		<td colspan="7" align="left" style=" padding-left:20px;"><strong>未通过原因:</strong><br/>{{$value.checkResult}}</td>
	</tr>
	{{/each}}
</tbody>
</script>
<script type="text/javascript">
	var pageTemp = null;
	seajs.use(["pagination", "template", "moment"], function (Pagination, template, moment){
		
		template.helper('$formatMoney', function (num) {
    		num = num == null ? 0 : num;
            return num.toFixed(2);
        });
		template.helper('$formatDate', function (millsec) {
			return moment(millsec).format("YYYY-MM-DD HH:mm");
		});
		
		function showMyUpload(tempId){
			var $temp = $("#" + tempId);
			pageTemp = new Pagination({
				url: $temp.attr("_url"),
				elem: $temp.attr("_page"),
				method: "post",
				handleData: function (data) {
					var html = template.render($temp.attr("id"), data);
					$($temp.attr("_pid")).html(html);
					if(tempId == "ajax-templage-myUpload2"){
						bindResult();
					}
				}
			});
		}
		
		function bindResult(){
			$(".Notby").click(function(){
				$(this).parent().parent().next().next().toggle();
				$(this).find(".Notbys").toggle();
			});
		}
		
		//切换审核状态js
		$("#js-myUpload-status a").bind("click", function(){
			//点击最新发布和热点资讯触发的事件
			var $this = $(this);
			$this.css("color", "#0082cc").parent().siblings().children("a").css("color", "#333");
			showMyUpload($this.attr("_id"));
			
			//修改面包屑
			$("#js-currentPage").html($this.html());
		});

		$("#js-state" + "${empty param.checkState ? 1 : param.checkState}").click();
	});
	
	//删除
	function funcDelArt(artId, checkState){
		if(!confirm("确定删除？")){
			return;
		}
		$.post("${ctx}/dealer/school/list/delete", {artId: artId, checkState: checkState}, function(data){
			var page = pageTemp;
			page.goTo(page.current);
		}, "json");
	}
	
	
</script>
</body>
</html>
