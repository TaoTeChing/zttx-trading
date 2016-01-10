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
        		<jsp:param name="m" value="4" />
        	</jsp:include>
            <div style="background: #fff; padding: 10px;">
				<jsp:include page="${ctx}/WEB-INF/view/brand/show_brandSchool_bar.jsp">
					<jsp:param name="m" value="4" />
				</jsp:include>

                <div class="main-grid mt10 block">
                    <div class="main-grid pt3 block">
                        <div class="tabpanel bt1">
                            <div class="content">
                                <div class="tabitem">
                                    <table class="panel-table">
                                        <thead>
                                        <tr>
                                            <th class="tb40 tac">标题名称</th>
                                            <th class="tb15 tac">出售价格</th>
                                            <th class="tb15 tac">上传者</th>
                                            <th class="tb15 tac">上传时间</th>
                                            <th class="tb15 tac">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody></tbody>
                                    </table>
                                    <div class="EndBottom"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${res}/scripts/brand/index.js"></script>
<script id="ajax-templage-fav" class="ajax-templage" _url="${ctx}/brand/school/favorite.json?pageSize=22" _page=".EndBottom" _pid=".panel-table tbody" type="text/html">
{{each rows}}
<tr {{if $index % 2 == 0}}class="dan"{{else}}class="ou"{{/if}}>
	<td><a href="${ctx}/school/detail/{{$value.articleId}}">{{$value.articleTitle}}</a></td>
	<td>{{$formatMoney $value.articlePrice}}</td>
	<td>{{$value.userName}}</td>
	<td>{{$formatDate $value.favoriteDate}}</td>
	<td class="c-b"><i class="icon i-delete"></i><a href="javascript:void(0);" class="c-b" onclick="funcCelFav('{{$value.articleId}}');">取消收藏</a></td>
</tr>
{{/each}}
</script>
<script type="text/javascript">
	var pageList = {};
	seajs.use(["pagination", "template", "moment"], function (Pagination, template, moment){
		
		template.helper('$formatMoney', function (num) {
    		num = num == null ? 0 : num;
            return num.toFixed(2);
        });
		template.helper('$formatDate', function (millsec) {
			return moment(millsec).format("YYYY-MM-DD HH:mm");
		});
		
		$(".ajax-templage").each(function(index){
			var $this = $(this);
			var tempId = $this.attr("id");
			if(tempId == null || tempId == ""){
				tempId = "templage" + index;
				$this.attr("id", tempId);
			}
			pageList[tempId] = new Pagination({
				url: $this.attr("_url"),
				elem: $this.attr("_page"),
				method: "post",
				handleData: function (data) {
					var html = template.render(tempId, data);
					$($this.attr("_pid")).html(html);
				}
			});
		});
	});
	
	function funcCelFav(artId){
		$.post("${ctx}/brand/school/favorite/cancel", {artId: artId}, function(data){
			if(data.code == zttx.SUCCESS)
			{
				var page = pageList["ajax-templage-fav"];
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