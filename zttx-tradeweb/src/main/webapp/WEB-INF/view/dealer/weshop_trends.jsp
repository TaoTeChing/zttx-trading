<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <title>服务管理-约逛管理-约逛仓库</title>
    <link href="/styles/dealer/global.css" rel="stylesheet"/>
    <link href="/styles/dealer/weshop.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/dealer/mainmenu"/>
    <div class="em100">
        <div class="main clearfix pr">
           <jsp:include page="${ctx}/dealer/dealermenu"  />
            <div class="main-right">
                <div class="main-grid mb10">
                    <div class="panel-crumbs">
                        <i class="icon i-setting"></i><a href="/dealer/center">管理中心</a> &gt;&gt; <span class="bb">服务管理</span>
                        <a class="panel-crumbs-help" href="/help/index"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <div class="main-grid mb40 clearfix">
                    <div class="weshop-trends">
                        <h2>约逛动态管理</h2>
                        <jsp:include page="/WEB-INF/view/dealer/weshop_navi.jsp" ><jsp:param value="4" name="tab"/></jsp:include>
                        <div class="trends-items" >
                            <ul class="inline-float" id="table_trends_grid">
                                <li>
                                    <a class="new-trends" href="${ctx}/dealer/weshop/trends/publish?shopId=${shopId}" data-step="1" data-intro="点击这里发布您第一条动态" data-position="right">
                                        <i class="iconfont">&#xe615;</i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div id="pagination" class="pagination mb10"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
<jsp:include page="/WEB-INF/view/dealer/weshop_trends_template.jsp" />
<script>
	seajs.use(["pagination", "template", "moment","masonry"], function (Pagination, template, moment) {
		template.helper('$formatDate', function (millsec) {
		           return moment(millsec).format("YYYY年MM月DD日 ");
		});
		
		window['g_pagination'] = new Pagination({
		    url: '${ctx}/dealer/weshop/trends/grouplist?shopId=${shopId}',
		    elem: '#pagination',
		    handleData: function (data) {
		    	
		    	data['wshop']="${weshop}";
		        var html = template.render('template_trends_grid', data);
		        $('#table_trends_grid li:gt(0)').remove();
		        $('#table_trends_grid').append(html);
               
                /*瀑布流*/
                 $("#table_trends_grid").masonry("destroy").masonry({
                     columnWidth:310,
                     itemSelector:"li",
                     gutter:25
                });
            	
                /*空白动态的引导*/
                if($('#table_trends_grid li').length==1)
                {
                    seajs.use(["introjs"],function(){
                        introJs().setOption("doneLabel","知道了.").start();
                    });
                }
		    }
		});
		
		//删除主题
		$(document).on("click", "i.delete",function(){
			var groupid=$(this).data('groupid');
			$.post('${ctx}/dealer/weshop/trends/delete',{groupId:groupid,shopId:'${shopId}'},function(data){
				if(data.code==126000){
					g_pagination.render();
				}
			},"json");
		});

	});



</script>
</body>
</html>
