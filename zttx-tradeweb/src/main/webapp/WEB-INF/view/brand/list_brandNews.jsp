<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-品牌管理-资讯管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css" />
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
            <c:choose>
                <c:when test="${param.m=='1'}">
                    <span class="arrow">&gt;&gt;</span>
                    <a class="link" href="${ctx}/brand/dealer/listDealerApply">终端商管理</a>
                    <span class="arrow">&gt;</span>
                    <span class="current">交易会管理</span>
                </c:when>
                <c:otherwise>
                    <span class="arrow">&gt;&gt;</span>
                    <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
                    <span class="arrow">&gt;</span>
                    <span class="current">资讯管理</span>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
        </div>
    </div>
    <div class="inner">
        <!-- 内容都放这个地方  -->
	    <jsp:include page="${ctx}/WEB-INF/view/brand/show_brandNews_bar.jsp">
    		<jsp:param name="m" value="3" />
    	</jsp:include>
        <div class="info_list_con">
            <div class="tool_bar clearfix">
                <div class="add_btn">
                    <a href="${ctx}/brand/brandNews/execute">
                        <i class="iconfont">&#xe611;</i>新增新闻
                    </a>
                </div>
                <div class="status_select inline-block">
                    <label>状态:</label>
                    <!-- 搜索条件表单 -->
                    <div class="inline-block">
                        <form:form id="searchTermForm">
                            <label for="s_all"><input type="radio" name="state" value="" id="s_all" checked/>全部</label>
                            <label for="s_0"><input type="radio" name="state" value="0" id="s_0"/>待发布</label>
                            <label for="s_1"><input type="radio" name="state" value="1" id="s_1"/>已发布</label>
                        </form:form>
                    </div>
                    <!-- end：搜索条件表单 -->
                </div>
            </div>
			<div id="pagination" class="mt10"></div>
        </div>
    </div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />

<script src="${src}/plugin/template-simple.js"></script>
<script id="feedback-templage" type="text/html">
<table class="list_table" id="listContent">
	<colgroup>
		<col width="158" />
		<col width="146" />
		<col width="212" />
		<col width="260" />
		<col width="66" />
		<col width="124" />
	</colgroup>
	<tr>
		<th>发布时间</th>
		<th>品牌</th>
		<th>标题</th>
		<th>内容</th>
		<th>状态</th>
		<th class="last">操作</th>
	</tr>
    {{each rows}}
		<tr id="tr_{{$value.refrenceId}}" class="{{$index%2==0 ? '':'odd'}}">
            <td>{{$fomatDate $value.cronTime}}</td>
			<td>{{$value.brandName}}</td>
			<td style="display:none;">{{$value.cateName}}</td>
			<td>{{$value.newsTitle}}</td>
			<td>{{$trimLongString($value.newsSummary, 25, null)}}</td>
			<td>
				<span class="status status_publish">{{$compare $value.cronTime}}</span>
			</td>
			<td class="last">
				<a class="link" href="${ctx}/market/brand/viewBrandNewsInfo/{{$value.brandsId}}/{{$value.brandId}}/{{$value.refrenceId}}" target="_blank">查看</a>
				<a href="${ctx}/brand/brandNews/execute?brandNewsId={{$value.refrenceId}}" class="link">修改</a>
				<a href="javascript:deleteNews('{{$value.refrenceId}}');" class="link">删除</a>
			</td>
		</tr>
	{{/each}}
	{{ if rows.length == 0 }}
		<tr>
			<td colspan="6" class="last" style="text-align:center;">暂无数据</td>
		</tr>
	{{ /if }}
</table>
</script>
<script type="text/javascript">
	var nowTime = ${nowtime};
	
	/** 删除操作 */
	function deleteNews(brandNewsId){
		$.ajax({
			type : "POST",
			url : "${ctx}/brand/brandNews/delete",
			data : {"brandNewsId":brandNewsId},
			dataType: "json",
			success : function(data) {
				if(data.code == zttx.SUCCESS)
				{
					remind("success", "删除成功",function(){
                        location.href = location.href;
                    });
				}
				else
				{
					remind("error", data.message);
				}
			}
		}); 
	}
	
	var page;

	$(function () {
		seajs.use(["pagination", "moment"], function (Pagination, moment) {
			template.helper('$fomatDate', function(millsec){
				return moment(millsec).format("YYYY-MM-DD HH:mm:00");
			});
			
			template.helper('$compare',function(cronTime){
				if(cronTime>nowTime) {
					return "待发布";
				}else{
					return "已发布";
				}
			});
			
			var renderPagination = function(){
				page = new Pagination({
					/************** 需要修改 *************/
					url: "${ctx}/brand/brandNews/ajaxList",	
					elem: "#pagination",
					form:$("#searchTermForm"),
					method:"post",
					handleData: function (json) {
						var html = template.render("feedback-templage", json);
						$("#listContent").remove();
						$("#pagination").before($(html));
					}
				});
			};
			renderPagination();
		});
        $("#searchTermForm input").change(function(){
            page.goTo(1);
        });
	}); 

	/** 注册 文本内容缩略显示函数 */
    template.helper('$trimLongString', trimLongString);
</script>
</body>
</html>