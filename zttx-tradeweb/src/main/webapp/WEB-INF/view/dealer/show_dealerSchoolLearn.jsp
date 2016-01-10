<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>终端商管理中心 - 商学院管理 - 我的学习</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    
    <link href="${res}/styles/dealer/index.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/school_index.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/SchoolLearn.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/finance.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<jsp:include page="${ctx}/dealer/mainmenu"/>
		<div class="em100">
			<div class="main clearfix">
				<!--侧边导航-->
				
					<jsp:include page="${ctx}/dealer/dealermenu"/>
               
                <!--主体内容-->
                <div class="main-right">
                    <jsp:include page="${ctx}/WEB-INF/view/dealer/show_dealerSchool_bar.jsp">
						<jsp:param name="m" value="3" />
					</jsp:include>

					<div class="main-grid mt10">
                    	<div class="main-grid pt3">
                        	<div class="tabpanel bt1">
                            	<div class="content ">
                                	<div class="tabitem">
                                    	<table class="panel-table">
                                        	<thead>
                                        		<tr>
                                            		<th class="tb30 tac">标题名称</th>
                                            		<th class="tb20 tac">出售价格</th>
                                            		<th class="tb20 tac">上传者</th>
                                            		<th class="tb20 tac">上传时间</th>
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
        <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
<script class="ajax-templage" _url="${ctx}/dealer/school/learn.json?pageSize=22" _page=".EndBottom" _pid=".panel-table tbody" type="text/html">
{{each rows}}
<tr {{if $index % 2 == 0}}class="dan"{{else}}class="ou"{{/if}}>
	<td><a href="${ctx}/school/detail/{{$value.articleId}}">{{$value.articleTitle}}</a></td>
	<td>{{$formatMoney $value.articlePrice}}</td>
	<td>{{$value.userName}}</td>
	<td>{{$formatDate $value.uploadDate}}</td>
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
</script>
</body>
</html>
