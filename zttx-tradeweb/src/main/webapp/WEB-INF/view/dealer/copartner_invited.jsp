<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-加盟管理-邀请中的</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/brandjoin.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
       <jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>

                <div class="main-right">
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> &gt;&gt; <a href="${ctx}/dealer/copartner/brandes" title="">加盟管理</a> &gt; <span class="bb">邀请中的</span>
                            <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="inner">
                        <div class="panel-tabs">
                        	<jsp:include page="/WEB-INF/view/dealer/copartner_header.jsp">
                       			<jsp:param value="3" name="m"/>
                       		</jsp:include>
                            <div class="panel-content">
                                <div class="tab-item">
                                    <div class="panel-table table-favorite clearfix">
                                        <div class="panel-table-content">
                                        	<table class="ui-table ui-table-inbox" id="tempTable">
												<thead>
													<tr>
														<th>品牌LOGO</th>
														<th>品牌商</th>
														<th>公司规模</th>
														<th>年营业额</th>
														<th class="tb20">邀请理由</th>
														<th>邀请时间</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody id="invited_tbl_body">
													
												</tbody>
											</table>
                                            <div class="mt10" id="pagination"></div>
                                        </div>
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
    <script id="invited-templage" type="text/html">
		{{each rows}}
			<tr>
				<td>
					<a target="_blank" href='http://{{$value.domain}}${zttx}'>
                        <div class="js-img-center l-100x50" style="text-align: center;"><img src="${res}{{$getImageDomainPathFn $value.domainName+$value.brandsLogo 100 50}}" /></div><h3>{{$value.brandsName}}</h3>
					</a>				
				</td>
				<td>
                    <p><a target="_blank" href='http://{{$value.domain}}${zttx}'>{{$value.brandName}}</a></p>
                </td>
				<td> {{$value.emploeeNum}} </td>
				<td> {{$value.moneyNum}} </td>
				<td> <span title="{{$value.inviteText}}">{{$trimLongString $value.inviteText 15}} </span></td>
				<td> {{$formatDate $value.inviteTime}} </td>
				<td>
					<a href="javascript:updateInvitedApple('{{$value.brandsId}}')" class="ui-button ui-button-sorange mb10"><i class="iconfont">&#xe620;</i> 接受邀请</a>
						<br />
					<a href="http://{{$value.domain}}${zttx}/recruit" target="_blank" class="c-b">查看招募书</a><!--<a href="${ctx}/market/brandes/certs/{{$value.brandsId}}/{{$value.brandId}}" target="_blank" class="ml10 c-b">查看品牌证书</a>-->
				</td>
			</tr>
		{{/each}}
			{{ if rows.length == 0 }}
				<tr>
					<td colspan="7">暂无数据</td>
				</tr>
			{{ /if }}
	</script>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
	<script type="text/javascript" src="${src}/plugin/jquery-dateFormat.min.js"></script>
	
	<script>
	function doCopartnerSearch() {
    	g_pagination.goTo(1);
    }
	function updateInvitedApple(brandsId){
       	$.post("${ctx}/dealer/invited/accept",{brandsId:brandsId},function(data){
    		if(data.code==126000){
    			remind("success","成功接受邀请!");
    			g_pagination.goTo(1);
    		}else{
    			remind("error",data.message+"，不能授受邀请"); 
    		}
    	},"json");
    }
	</script>
    <script>
	  $(function () {
	  	//表格样式
        $(".panel-table-content tbody tr:odd").css("background-color", "#f9f9f9");

        var trs = $(".panel-table-content tr");
        $(trs).find("td:first,th:first").css("border-left", 0);
        $(trs).find("td:last,th:last").css("border-right", 0);


        var order_model = $(".panel-tabs .panel-head ul li").click(function () {
            order_model.not($(this).addClass("on")).removeClass("on");
            var items = $(".panel-content .tab-item").hide().eq($(order_model).index(this)).show();
        });
	  
	    seajs.use(["pagination","template"], function (Pagination,template) {

			template.helper('$trimLongString', trimLongString);
		    template.helper('$getImageDomainPathFn',function (url, width, height){
		        return getImageDomainPath(url, width, height);
		    });
			template.helper('$formatDate', function (millsec) {
				return $.format.date(new Date(millsec), 'yyyy-MM-dd');
			});

			window['g_pagination'] = new Pagination({
			         url: "${ctx}/dealer/dealerJoinManage/inventing/data",
			         elem: "#pagination",
			         form: $("#copartner_form"),
			         data:{applyState:3},
			         method:"post",
			         handleData: function (data) {
			             var html = template.render("invited-templage", data);
			             $("#invited_tbl_body").empty().append(html);
                         //让图片在一个容器里面垂直居中
                         $(".js_img_center,.js-img-center").each(function () {
                             $("<span></span>").appendTo($(this));
                         });
			         }
			});
        });
      });
	</script>
</body>
</html>
