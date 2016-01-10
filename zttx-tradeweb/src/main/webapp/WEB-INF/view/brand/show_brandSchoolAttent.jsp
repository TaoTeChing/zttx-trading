<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>品牌商管理中心-天下商学院</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/school.css"/>
</head>
<body>
	<jsp:include page="${ctx}/brand/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/brand/brandmenu"/>
    <div class="main_con">
        <div class="inner">
            <!-- 内容都放这个地方  -->
        	<jsp:include page="${ctx}/WEB-INF/view/brand/show_brandSchool_top.jsp">
        		<jsp:param name="m" value="5" />
        	</jsp:include>
        	
            <div style="background: #fff; padding: 10px;">
				<jsp:include page="${ctx}/WEB-INF/view/brand/show_brandSchool_bar.jsp">
					<jsp:param name="m" value="5" />
				</jsp:include>

                <div class="Attention">
                    <ul>
                        <li class="fl main"><a href="javascript:void(0);" _tempId="ajax-templage-myAttent">我的关注</a></li>
                        <li class="fl wd100 br1"><a href="javascript:void(0);" _tempId="ajax-templage-attentMe">关注我的</a></li>
                    </ul>
                </div>

                <div class="Content" style="margin-right: -24px; width: 1024px;"></div>

                <div class="paginationTop">
                    <div class="Dashed"></div>
                </div>
                
                <div class="paginationTop pageParent"></div>

            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${res}/scripts/brand/index.js"></script>
<script id="ajax-templage-myAttent" _url="${ctx}/brand/school/myAttent.json?pageSize=6" _page=".pageParent" _pid=".Content" type="text/html">
{{each rows}}
<div class="AddAttention">
	<div class="fl left">
		<img src="${res}{{$value.attentPhoto}}" width="70" height="70"  alt=""/>
		<ul>
			<li>{{$value.attentName}}</li>
			<li class="another">
				{{if $value.attentFlag}}
					<span class="fl mt5"><img src="${res}/images/dealer/SchoolAttention.jpg" width="16" height="15" border="0"  alt=""/></span>
					<span class="fl mr5 ml5"><em class="c-h">相互关注</em></span>
				{{else}}
					<span class="fl mt5"><img src="${res}/images/dealer/SchoolYes.jpg" width="15" height="12" border="0" alt=""></span>
					<span class="fl mr5 ml5"><em href="javascript:void(0);" class="c-h">已关注</em></span>
				{{/if}}
				<span class="fl">
					<a href="javascript:void(0);" class="c-b" onclick="funcCelAtt('{{$value.attentId}}', {{$value.attentCata}});">取消</a>
				</span>
			</li>
		</ul>
	</div>
	<div class="fl right">
		<div class="fonts">最近上传</div>
		<ul>
			{{each $value.articles}}
			<li><a href="${ctx}/school/detail/{{$value.refrenceId}}">{{$value.articleTitle}}</a></li>
			{{/each}}
		</ul>
	</div>
</div>        
{{/each}}     
</script>
<script id="ajax-templage-attentMe" _url="${ctx}/brand/school/attentMe.json?pageSize=6" _page=".pageParent" _pid=".Content" type="text/html">
{{each rows}}
<div class="AddAttention">
	<div class="fl left">
		<img src="${res}{{$value.userPhoto}}" width="70" height="70"  alt=""/>
		<ul>
			<li>{{$value.userName}}</li>
			<li class="attention">
				<input type="button" class="button" value="+ 关注" onclick="funcAddAtt('{{$value.userId}}', {{$value.userCata}});">
			</li>
		</ul>
	</div>
	<div class="fl right">
		<div class="fonts">最近上传</div>
		<ul>
			{{each $value.articles}}
			<li><a href="${ctx}/school/detail/{{$value.refrenceId}}">{{$value.articleTitle}}</a></li>
			{{/each}}
		</ul>
	</div>
</div>       
{{/each}}     
</script>
<script type="text/javascript">
	var pageTemp = {};
	seajs.use(["pagination", "template"], function (Pagination, template){
		function ajaxTemplate(tempId)
		{
			var $this = $("#" + tempId);
			pageTemp = new Pagination({
				url: $this.attr("_url"),
				elem: $this.attr("_page"),
				method: "post",
				handleData: function (data) {
					for(var i = 0; i < data.rows.length; i++){
						data.rows[i].articles = $.parseJSON(data.rows[i].articleJson);
					}
					var html = template.render(tempId, data);
					$($this.attr("_pid")).html(html);
				}
			});
		}
		
		$(".Attention a").bind("click", function(){
			$(this).parent().removeClass("wd100").removeClass("br1").addClass("main").siblings().removeClass("main").addClass("wd100").addClass("br1");
			var tempId = $(this).attr("_tempId");
			ajaxTemplate(tempId);
			
			//修改面包屑
			$("#js-currentPage").html($(this).html());
		});
		
		$(".Attention a")[0].click();
	});
	
	//取消关注
	function funcCelAtt(attentId, attentCata){
		$.post("${ctx}/brand/school/attent/cancel", {attentId: attentId, attentCata: attentCata}, function(data){
			if(data.code == zttx.SUCCESS)
			{
				remind("success", "取消成功");
				var page = pageTemp;
				page.goTo(page.current);
			}
			else
			{
				remind("error", data.message);
			}
		}, "json");
	}
	
	//增加关注
	function funcAddAtt(attentId, attentCata){
		$.post("${ctx}/brand/school/attent/add", {attentId: attentId, attentCata: attentCata}, function(data){
			if(data.code == zttx.SUCCESS)
			{
				remind("success", "关注成功");
				var page = pageTemp;
				page.goTo(page.current);
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