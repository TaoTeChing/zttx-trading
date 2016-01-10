<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理中心-加盟管理-申请中的</title>
<link href="${res}/styles/dealer/global.css" rel="stylesheet" />
<link href="${res}/styles/dealer/brandjoin.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
		<div class="em100">
			<div class="main clearfix pr">   
				<!--侧边导航-->
				<jsp:include page="${ctx}/common/menuInfo/sidemenu"/>

				<!--主体内容-->
				<div class="main-right">
					<!--面包屑-->
					<div class="main-grid mb10">
						<div class="panel-crumbs">
							<i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="${ctx}/dealer/copartner/brandes" title="">加盟管理</a> > <span class="bb">申请中的</span>
							<a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
						</div>
					</div>
					<div class="inner">
						<div class="panel-tabs">
							<jsp:include page="/WEB-INF/view/dealer/copartner_header.jsp">
                       			<jsp:param value="2" name="m"/>
                       		</jsp:include> 
							<div class="panel-content">
								<div class="tab-item">
										<div class="panel-table-content">
											<div class="mt10" id="pagination"></div>
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
	<script id="feedback-templage" type="text/html">
	<table id="tempTable" class="ui-table ui-table-inbox">
		<thead>
			<tr>
				<th style="width:150px">品牌LOGO</th>
				<th>品牌商</th>
				<th>公司规模</th>
				<th>年营业额</th>
				<th class="tb20">申请理由</th>
				<th>申请时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>

			{{each rows}}
			<tr>
				<td>
					<a target="_blank" href='http://{{$value.domain}}${zttx}'>
                        <div class="js-img-center l-100x50" style="text-align: center;"><img src="${res}{{$getImageDomainPathFn $value.domainName+$value.brandsLogo 100 50}}" /></div><h3>{{$value.brandsName}}</h3>
					</a>
				</td>
				<td>
                    <p><span>{{$value.brandName}}</span></p>
                </td>
				<td> {{$value.emploeeNum}} </td>
				<td> {{$value.moneyNum}} </td>
				<td> {{$value.inviteText}} </td>
				<td> {{$formatDate $value.inviteTime}} </td>
				<td>
					<a href="javascript:updateApple('{{$value.brandsId}}');" class="ui-button ui-button-sorange mb10">撤销申请</a><br>
                    <span class="c-r">正在审核您的申请</span>
				</td>
			</tr>
			{{/each}}

			{{ if rows.length == 0 }}
				<tr>
					<td colspan="7">暂无数据</td>
				</tr>
			{{ /if }}
		</tbody>
	</table>
</script>
	<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
	<script type="text/javascript" src="${src}/plugin/jquery-dateFormat.min.js"></script>
	<script>
	  	function doCopartnerSearch() {
	    	g_pagination.goTo(1);
	    }
        function updateApple(brandsId){
        	confirmDialog("确定是否要撤销？",function(){
	        	$.post("${ctx}/dealer/joinManage/apply/update",{brandsId:brandsId},function(data){
		    		if(data.code==126000){
		    			doCopartnerSearch();
		    			remind("success","成功撤销申请!");
		    		}else{
		    			remind("error",data.message+"，不能撤销申请"); 
		    		}
		    	},"json");
	    	});
        };
	
	  $(function () {
	    seajs.use(["pagination","template"], function (Pagination,template) {
	    	 window['g_pagination'] = new Pagination({
	              url: "${ctx}/dealer/dealerJoinManage/applying/data",
	              elem: "#pagination",
	              form:$("#copartner_form"),
	              method:"post",
	              handleData: function (data) {
	            	  template.helper('$formatDate', function (millsec) {
	                  	return $.format.date(new Date(millsec), 'yyyy-MM-dd');
	              	 });
	      	    	 template.helper('$getImageDomainPathFn',function (url, width, height){
	      			        return getImageDomainPath(url, width, height);
	      			 });
	                  var html = template.render("feedback-templage", data);
	                  $("#tempTable").remove();
	                  $("#pagination").before($(html));   
	              }
	         });
        });
      });
	</script>

</body>
</html>
