<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-品牌管理-品牌资料完善</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css" />
    <style type="text/css">
        .area-ui-select{
        	width:147px;height:25px;
        	vertical-align: middle;
        }
    </style>
</head>
<body>
	<jsp:include page="/common/menuInfo/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="/common/menuInfo/sidemenu"/>
<div class="main_con">
    <div class="bread_nav">
        <div class="fl">
            <a class="link" href="${ctx}/brand/center">管理中心</a>
            <span class="arrow">&gt;&gt;</span>
            <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
            <span class="arrow">&gt;</span>
            <span class="current">品牌资料完善</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
                <jsp:param value="0" name="isShow"/>
            </jsp:include>
        </div>
    </div>
    <div class="inner">
        <!-- 内容都放这个地方  -->
		<form:form action="${ctx }/brand/brandNetwork/list" cssClass="ui-form clearfix" name="brandNetworkForm" id="brandNetworkForm" method="post">
            <jsp:include page="/WEB-INF/view/brand/brands_perfect_menu.jsp">
   				<jsp:param value="3" name="m"/>
   			</jsp:include><!-- 品牌基本资料 end -->
            <div class="tips">
                <i class="v2-icon-explain"></i>
                说明：展示品牌门店形象，提高品牌竞争力
            </div>
            <div class="tab_con_box">
                <!-- 终端网络开始 -->
                <div class="tab_con distribution_net">
                    <div class="ui_tab main_ui_tab" style="margin: 0 20px;">
                        <ul class="ui_tab_items clearfix">
                            <li class="ui_tab_item">
                                <a href="${ctx }/brand/brandNetwork/execute?id=${id}">门店展示添加</a>
                            </li>
                            <li class="ui_tab_item current">
                                <a href="${ctx }/brand/brandNetwork/list?id=${id}">门店展示管理</a>
                            </li>
                        </ul>
                    </div>
                    <div class="inner_tab_con_box">
                        <div class="inner_tab_con" style="display: block;">
                            <div class="search_condition clearfix">
                                    <div class="form_item">
                                        <label for="">终端商等级：</label>
                                        <div class="inline-block">
                                            <select style="width: 116px" class="js_select" name="levelId" id="levelId">
                                                <option value="">不限</option>
                                                <c:forEach items="${brandLevels }" var="brandLevel">
                                                	<option value="${brandLevel.refrenceId}" ${brandLevel.refrenceId==brandNetwork.levelId? 'selected="selected"' :''}>${brandLevel.levelName }</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form_item">
                                        <label for="">终端商区域：</label>
                                        <div class="inline-block">
                                            <jsp:include page="${ctx}/common/regional/searchAllArea">
												<jsp:param value="area-ui-select js-select" name="style"/>
												<jsp:param value="${brandNetwork.province}" name="regProvince"/>
		    									<jsp:param value="${brandNetwork.city}" name="regCity"/>
		    									<jsp:param value="${brandNetwork.county}" name="regCounty"/>
		                                    </jsp:include>
                                        </div>
                                    </div>
                                    <div class="form_item">
                                        <a class="submit_btn" href="javascript:page.goTo(1);">搜&nbsp;索</a>
                                    </div>
                            </div>
                            
                            
                            <div class="search_result">
                                <ul class="inline-float" >
                                	
                                </ul>
                                <div class="mt10" id="pagination"></div>
                            </div>
                        </div>
                        <!-- 终端网络管理结束 -->
                    </div>
                </div>
                <!-- 终端网络结束 -->
            </div>
        </form:form>
    </div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script id="feedback-templage" type="text/html">
{{each rows}}
<li class="item">
<div class="img_contain pr">
	{{if $value.imageName==null}}
	<img src="${res}/images/common/nopic-205x145.gif" alt="{{$value.photoName}}" width="230" height="150"/>
	{{else}}
	<!--<img src="${res}{{$getImageDomainPathFn $value.domainName+$value.imageName 230 150}}" alt="{{$value.photoName}}" width="230" height="150"/>-->
	<img src="${res}{{$value.domainName+$value.imageName}}" alt="{{$value.photoName}}" width="230" height="150"/>
    {{/if}}
    <a style="display: none;" class="edit" href="${ctx}/brand/brandNetwork/execute/{{$value.refrenceId}}"></a>
	<a style="display: none;" class="close" href="javascript:void(0);" data-id="{{$value.refrenceId}}"></a>
</div>
<div class="title">{{$value.dealerName}}<span class="tag">终端商</span></div>
<div class="intro">
	<p>{{$value.provinceName}}&nbsp;&nbsp;{{$value.cityName}}&nbsp;&nbsp;{{$value.areaName}}</p>
	<p>联系人：{{$value.userName}}</p>
	<p>
		{{if $value.telphone==null || $value.telphone==""}}
			<span class="undo">电话：</span>
		{{else}}
			<span>电话：</span>{{$value.telphone}}
		{{/if}}
	</p>
	<p class="clearfix">
		<label><span>地址：</span></label>
		<span class="text">{{$value.address}}</span>
	</p>
</div>
<div class="bottom">
	<div class="fl">
		<input id="showFlag" name="showFlag" class="js_chk" type="checkbox" data-id="{{$value.refrenceId}}" {{if $value.showFlag}}checked{{/if}}/>在展厅中显示
		<input value="{{$value.refrenceId}}" type="hidden"/>
	</div>
	<div class="fr">
		<span class="status status_1">完整度：{{$value.wholePercent}}</span>
	</div>
</div>
</li>
{{/each}}
{{ if rows.length == 0 }}
<li style="margin-left: 300px;">
    暂无数据
</li>
{{ /if }}
</script>
<script src="${src}/brand/brand_manage.js"></script>
<script>
    $(function(){
    	//选择品牌更新内容
		$('#brandsId').change(function (e) {
            var id = $(this).val();
            window.location.href = "${ctx}/brand/brandNetwork/list?id=" + id;
      	});

        $(".search_result").on("mouseenter mouseleave",".img_contain",function(ev){
            var close = $(this).find(".close");
            var edit = $(this).find(".edit");
            if(ev.type == "mouseenter"){
                close.show();
                edit.show();
            }else if(ev.type == "mouseleave"){
                close.hide();
                edit.hide();
            }
        })

        $(".search_result").on("click",".close",function(){
        	var refrenceId = $(this).data("id");
        	confirmDialog("确定是否要删除？",function(){
        		$.post("${ctx}/brand/brandNetwork/list/delete", {refrenceId:refrenceId}, function(data){
					if(data.code == zttx.SUCCESS)
					{
						remind("success", "终端商删除成功");
						page.render();
					}
					else
					{
						remind("error", data.message);
					}
				}, "json");
        	});
        });
    });
    
    
    
</script>
<script>
  var page;
  $(function () {
    seajs.use(["pagination","template"], function (Pagination,template) {
    	
    	template.helper('$getImageDomainPathFn',function (url, width, height){
            return getImageDomainPath(url, width, height);
        });
    	
       	var renderPagination = function(){
       	page = new Pagination({
             url: "${ctx}/brand/brandNetwork/list/data",
             elem: "#pagination",
             form:$("#brandNetworkForm"),
             method:"post",
             handleData: function (json) {
                 var html = template.render("feedback-templage", json);
                 $(".search_result .inline-float").html(html);
                 $(".search_result input:checkbox").on("click",function(){
                 	var isShow = false;
                 	if($(this).is(":checked")){isShow=true};
                 	var refrenceId = $(this).data("id");
                 	$.post("${ctx}/brand/brandNetwork/list/update", {showFlag:isShow,refrenceId:refrenceId}, function(data){
						if(data.code != zttx.SUCCESS)
						{
							remind("error", data.message);
						}
    				}, "json");
                 });
             }
         });
         };
         renderPagination();
       });
     });
</script>
</body>
</html>