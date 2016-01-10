<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <title>服务管理-约逛管理-约逛仓库</title>
    <link href="/styles/dealer/global.css" rel="stylesheet" />
    <link href="/styles/dealer/weshop.css" rel="stylesheet" />
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
                    <div class="weshop-leading-in">
                        <h2>我的约逛仓库</h2>
                        <jsp:include page="/WEB-INF/view/dealer/weshop_navi.jsp"><jsp:param value="1" name="tab"/></jsp:include>
                        <form:form id="search-form" action="${ctx}/dealer/weshop/product/store" method="get">
                        <div class="ui-tab">
                            <ul class="ui-tab-items">
                                <li class="ui-tab-item ${productState==null||productState==''?'current':''}"><a href="${ctx }/dealer/weshop/product/store?shopId=${shopId}">我的约逛产品列表</a></li>
                                <li class="ui-tab-item ${productState=='1'?'current':''}"><a href="${ctx }/dealer/weshop/product/store?shopId=${shopId}&productState=1">已下架产品列表</a></li>
                                <li class="ui-tab-item ${productState=='0'?'current':''}"><a href="${ctx }/dealer/weshop/product/store?shopId=${shopId}&productState=0">出售中产品列表</a></li>
                                <li class="ui-tab-item">
                                    <div class="ui-tab-item-search">
                                    	<input name="shopId" value="${shopId}" type="hidden">
                                    	<input name="productState" value="${productState}" type="hidden">
                                        <label>快速搜单:</label><input class="ui-input" name="productNo" value="${productNo }" placeholder="输入关键词快速查找产品."> <button class="ui-button ui-button-mred">搜索</button>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div class="mt10 mb5 clearfix">
                             <div class="fl">
                                <input type="checkbox" class="ui-checkbox" id="checkall"> 全选 <a class="link ml10 mr10 deleleall" href="javascript:;">删除</a>
                            </div>
                            <div class="fr">
                            <%--
                            <%--
                                <a href="javascript:;" data-status="asc" data-type="createTime" class="sort">按发布日期排列<i class="icon i-sanup"></i></a> <a href="javascript:;" class='sort' data-type="createTime">按价格排列<i></i></a>
                            	<input name="field" type="hidden">
                            	<input name="order" type="hidden">
                            	  --%>
                            </div>
                           
                        </div>
                        <div class="ui-table-container">
                            <table class="ui-table ui-table-noborder">
                                <colgroup>
                                    <col width="200px">
                                    <col width="150px">
                                    <col width="150px">
                                    <col width="150px">
                                    <col width="150px">
                                    <col width="100px">
                                    <col width="100px">
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th>商品信息</th>
                                        <th>品牌</th>
                                        <th>颜色尺寸</th>
                                        <th>售价</th>
                                        <th>吊牌原价</th>
                                        <th>目前已下单</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${result.list}" var="item" > 
                                		<%-- 遍历某产品的型号 --%>
                                		<c:forEach items="${item.attr}" var="subitem" varStatus="status">
	                                		<c:if test="${status.index==0 }">
		                                	 <tr>
		                                        <td rowspan="${fn:length(item.attr)}">
		                                            <div>
		                                                <input class="ui-checkbox" type="checkbox" data-productid="${item.refrenceId}">
		                                                <div class="js-img-center">
		                                                    <img src="${weshop}${item.productImage}" />
		                                                </div>
		                                            </div>
		                                        </td>
		                                        <th rowspan="${fn:length(item.attr)}">${item.brandsName}</th>
		                                        <td>
		                                        	<%-- 遍历某产品的型号的属性 --%>
		                                           	 <c:forEach items="${item.attr[status.index].z}" var="prop">
		                                           	 	${prop.v}&nbsp;
		                                           	 </c:forEach> 
		                                        </td>
		                                        <td rowspan="${fn:length(item.attr)}">${item.salePrice}</td>
		                                        <td rowspan="${fn:length(item.attr)}">${item.marketPrice}</td>
		                                        <td rowspan="${fn:length(item.attr)}">${item.orderCount}</td>
		                                         <td rowspan="${fn:length(item.attr)}">
		                                            <a  href="${ctx }/dealer/weshop/product/index?shopId=${shopId}&productId=${item.refrenceId}"class="link block">修改</a>
		                                            <c:if test="${item.productState=='ON_SHELF' }">
		                                            	<a href="javascript:;" class="link block putoff" data-productid="${item.refrenceId}" >下架</a>
		                                            </c:if>
		                                              <c:if test="${item.productState=='OFF_SHELF' }">
		                                            	<a href="javascript:;" class="link block puton" data-productid="${item.refrenceId}" >上架</a>
		                                            </c:if>
		                                             <a href="javascript:;" class="link block delete" data-productid="${item.refrenceId}">移除</a>
		                                        </td>
		                                    </tr>
	                                	 </c:if>
		                                	 <c:if test="${status.index!=0 }">
		                                	    <tr>
			                                        <td>
			                                           <c:forEach items="${item.attr[status.index].z}" var="prop">
		                                           	 	${prop.v}&nbsp;
		                                           	 </c:forEach> 
			                                        </td>
		                                    	</tr>
		                                    </c:if>
	                                    </c:forEach>
                                	</c:forEach>
                                </tbody>
                            </table>
                             <div class="mt10">
                             			<input name="currentPage" type="hidden" value='${result.page.currentPage}'>
                                        <div id="pagination"></div>
                              </div>
                        </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
     <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
<script>
    $(".js-number-plugin").numberPlugin({},function(a,b){
        var amount=$('#service_price').val()*a;
        $('#display_price').html(amount);
    });
    var totalPage = ${result.page.totalPage};
    var currentPage = ${result.page.currentPage};
    seajs.use(["$", "pagination"], function ($, Pagination) {
        new Pagination({
            url: '',
            elem: '#pagination',
            total: totalPage,
            currentPage: currentPage,
            pageClick: function (page) {
            	$("input[name=currentPage]").val(page);
                $('#search-form').val(page).submit();
            }
        });
    });
    $('a.sort').click(function(){
    	 var status=$(this).find("i.i-sanup").size()==1;
    	 $('a.sort i').removeAttr("class") ;
    	 $(this).find("i").addClass("icon").addClass(status?"i-sandown":"i-sanup");
    	 $(this).data('status',status?'desc':'asc');
    	 $('#search-form').append($('<input name="orderstr" type="hidden">').val("order by "+$(this).data('type')+' '+$(this).data('status'))).submit();
    });
    /**下架*/
    $('a.putoff').click(function(){
    	var productid=$(this).data('productid');
		$.post('${ctx}/dealer/weshop/product/putoff',{productId:productid,shopId:'${shopId}'},function(data){
			$('#search-form').submit();
		},"json");
   });
    /**上架*/
    $('a.puton').click(function(){
    	var productid=$(this).data('productid');
		$.post('${ctx}/dealer/weshop/product/puton',{productId:productid,shopId:'${shopId}'},function(data){
			$('#search-form').submit();
		},"json");
   });
    
    $('a.delete').click(function(){
    	var productid=$(this).data('productid');
		$.post('${ctx}/dealer/weshop/product/delete',{productIds:productid,shopId:'${shopId}'},function(data){
			$('#search-form').submit();
		},"json");
   });
    
    $('a.deleleall').click(function(){
    	var productIds=[];
    	$("table input:checked").each(function(){
    		productIds.push($(this).data('productid'))
    	});
		$.post('${ctx}/dealer/weshop/product/delete',{productIds:productIds.join(","),shopId:'${shopId}'},function(data){
			$('#search-form').submit();
		},"json");
   });
    $('#checkall').change(function(){
    	 $("table input[type='checkbox']").prop("checked", $(this).prop("checked"));
   });
    
</script>

</body>
</html>
