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
                        <h2>我要导入产品</h2>
                        <jsp:include page="/WEB-INF/view/dealer/weshop_navi.jsp"><jsp:param value="3" name="tab"/></jsp:include>
                        <div class="ui-tab">
                            <ul class="ui-tab-items">
                                <li class="ui-tab-item current"><a href="${ctx }/dealer/weshop/product/buyed?shopId=${shopId}">全部品牌产品</a></li>
                                <li class="ui-tab-item "><a href="${ctx }/dealer/weshop/product/imported?shopId=${shopId}">已导入产品</a></li>
                               
                                <%--
                                <li class="ui-tab-item"><a>未导入产品</a></li>
                               
                                <li class="ui-tab-item">
                                    <div class="ui-tab-item-search">
                                        <label>快速搜单:</label><input class="ui-input" placeholder="输入关键词快速查找产品."> <button class="ui-button ui-button-mred">搜索</button>
                                    </div>
                                </li>
                                 --%>
                            </ul>
                        </div>
                        <div class="mt10 mb5 clearfix">
                            <div class="fl p020">
                                <%-- <a class="link" href="javascript:;">移除</a> --%>
                            </div>
                            <div class="fr">
                                <a class="ui-button ui-button-sblue import" href="javascript:;">导入</a>
                            <%--
                                <a href="javascript:;">按人气排列<i class="icon i-sanup"></i></a> <a href="javascript:;">按发布日期排列<i class="icon i-sanup"></i></a> <a href="javascript:;">按价格排列<i class="icon i-sandown"></i></a>
                             --%>
                            </div>
                        </div>
                        <div class="ui-table-container">
                            <table class="ui-table ui-table-noborder">
                                <colgroup>
                                    <col width="80px">
                                    <col width="200px">
                                    <col width="200px">
                                    <col width="200px">
                                    <col width="160px">
                                    <col width="160px">
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th><label><input type="checkbox" class="ui-checkbox" id='checkall'> 全选</label></th>
                                        <th>商品信息</th>
                                        <th>品牌</th>
                                        <th data-order="size" data-isorder=true>颜色尺寸</th>
                                        <th data-order="money">售价</th>
                                        <th>吊牌原价 </th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%-- 遍历产品 --%>
                                	<c:forEach items="${result.list}" var="item" > 
                                		<%-- 遍历某产品的型号 --%>
                                		<c:forEach items="${item.attr}" var="subitem" varStatus="status">
	                                		<c:if test="${status.index==0 }">
		                                	 <tr data-productid="${item.refrenceId}">
                                                <td rowspan="${fn:length(item.attr)}">
                                                    <input class="ui-checkbox" type="checkbox" >
                                                </td>
		                                        <td rowspan="${fn:length(item.attr)}">
		                                            <div>

		                                                <div class="js-img-center">
		                                                    <img src="${weshop}${item.productImage}" />
		                                                </div>
		                                            </div>
		                                        </td>
		                                        <th rowspan="${fn:length(item.attr)}">
		                                            	${item.brandsName}
		                                        </th>
		                                        <td>
		                                        	<%-- 遍历某产品的型号的属性 --%>
		                                           	 <c:forEach items="${item.attr[status.index].z}" var="prop">
		                                           	 	${prop.v}&nbsp;
		                                           	 </c:forEach> 
		                                        </td>
		                                        <td rowspan="${fn:length(item.attr)}">
		                                            <input class="ui-input" value="${item.productPrice}" name="price" style="width: 50px;">
		                                        </td>
		                                        <td rowspan="${fn:length(item.attr)}">
		                                            ${item.productPrice}
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
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
//<script src="/scripts/weshop/base-init.js"></script>
<script>

    $(".js-number-plugin").numberPlugin({},function(a,b){
        var amount=$('#service_price').val()*a;
        $('#display_price').html(amount);
    });

    $(".import").click(function(){
    	var productIds=[];
    	var productPrices=[];
    	$("table input:checked").each(function(){
    		productIds.push($(this).parents('tr').data('productid'));
    		productPrices.push($(this).parents('tr').find("input[name=price]").val());
    	});
    	$.post('${ctx}/dealer/weshop/product/importproduct',{productIds:productIds.join(','),productPrices:productPrices.join(','),shopId:'${shopId}'},function(data){
			remind("success","导入完成!");
		},"json");
    });


    checkAll('#checkall',"table tbody input[type='checkbox']");
    /*$('#checkall').change(function(){
   	 	$("table tbody input[type='checkbox']").prop("checked", $(this).prop("checked"));
   });*/


</script>
</body>
</html>
