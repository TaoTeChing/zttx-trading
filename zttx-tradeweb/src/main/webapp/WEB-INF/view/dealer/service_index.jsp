<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-服务管理</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/service.css" rel="stylesheet" />
</head>
<body>
    <!--完成-->
    <div class="container myServices">
		<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->
				<jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
                <div class="main-right">
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <span class="bb">服务管理</span>
                            <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
			<div class="inner">
                    <div class="main-grid mb10">
                        <div class="tab_con">
                            <table class="ui-table">
                                <colgroup>
                                    <col width="300">
                                    <col width="150">
                                    <col width="120">
                                    <col width="100">
                                    <col width="100">
                                    <col width="120">
                                </colgroup>
                                <tbody id="service_items_grid">
                                </tbody>
                            </table>
                             <div class="mt10" id="pagination"></div>
			</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
        <div class="hide">
            <div id="dialogApplyAPP">
                <div class="ui-head"><h3>申请开通</h3></div>
                    <ul class="inline-float">
                        <li>
                            <a class="apply-btn" href="${ctx}/dealer/weshop/choice?type=1">
                                <h4>一步开通约逛及微店</h4>
                                <span class="lock"></span>
                                <p>使用您现在登录的终端商账号开通相同手机号的约逛登录账户，并一步开通激活微店功能，两者密码可相同也可独立自行修改。</p>
                            </a>
                        </li>
                        <li>
                            <a class="apply-btn" href="${ctx}/dealer/weshop/choice?type=2">
                                <h4>已有约逛号激活微店</h4>
                                <span class="notepad"></span>
                                <p>使用您已经在使用的约逛账户进行绑定和激活，绑定后即可开通使用约逛里的微店功能。</p>
                            </a>
                        </li>
                        <li>
                            <a class="apply-btn" href="${ctx}/dealer/weshop/choice?type=3">
                                <h4>使用其他新约逛号</h4>
                                <span class="iphone"></span>
                                <p>还没有约逛账户或想使用其它手机号码作为约逛的登录号请注册新的约逛号</p>
                            </a>
                        </li>
                    </ul>
                <div class="hr mb20"></div>
                <div class="m020 mb10">
                    <a class="ui-button ui-button-lred mr10" href="" target="_blank">前往下载约逛</a>
                    <a class="link" href="" target="_blank">了解约逛</a>
                </div>
            </div>
        </div>

    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script id="service-items-template" type="text/html">
	    <tr>
			<th class="ta-l">服务名称</th>
			<th>类别</th>
			<th>推荐指数</th>
			<th>购买人数</th>
			<th>浏览次数</th>
			<th>操作</th>
		</tr>
	{{each rows}}
		<tr {{$index%2==0?' class="odd"':''}}>
			<td class="ta-l">
				<div class="clearfix">
					<div class="img_contain">
						{{if $value.refrenceId=='S004' || $value.refrenceId=='S005'}}
							<span><img src="${res}{{$value.servicePhoto}}" width="68" height="68" alt=""></span>
							{{/if}}
							{{if $value.refrenceId!='S004' && $value.refrenceId!='S005'}}
							<a href="${ctx}/dealer/webServiceItems/details?refrenceId={{$value.refrenceId}}">
								<img src="${res}{{$value.servicePhoto}}" width="68" height="68" alt="">
							</a>
						{{/if}}


					</div>
					<div class="info_contain">
						<p class="name">
							{{if $value.refrenceId=='S004'||$value.refrenceId=='S005'}}
								<span>{{$value.serviceName}}</span>
							{{/if}}
							{{if $value.refrenceId!='S004'&&$value.refrenceId!='S005'}}
								<a href="${ctx}/dealer/webServiceItems/details?refrenceId={{$value.refrenceId}}">{{$value.serviceName}}</a>
							{{/if}}
						</p>
						<p class="price">
                                    {{if $value.servicePrice==0}}免费{{/if}}
                                    {{if $value.servicePrice!=0 }}{{formatNumber $value.servicePrice}}{{/if}}
						</p>
					</div>
				</div>
			</td>
			<td>{{if $value.servicerCate==1}}系统服务{{ /if}} {{if $value.servicerCate!=1}}扩展服务{{ /if}}</td>
			<td>
				{{$formatStar $value.commentNum}}
			</td>
			<td>{{$value.buyNum}}</td>
			<td>{{$value.viewNum}}  </td>
			<td>
		    {{if $value.refrenceId=='S004'}}
				{{if $value.dealerBuyService==null}}
					<a class="ui-button ui-button-morange applyAPP">开通服务</a>
				{{/if}}
				{{if $value.dealerBuyService!=null}}
					<a class="ui-button ui-button-morange" href="${ctx}/dealer/weshop">管理微店</a>
				{{/if}}
			{{ /if}}
			{{if $value.refrenceId=='S005'}}
				{{if $value.dealerBuyService==null}}
					<a class="ui-button ui-button-morange trail">开通试用</a>
				{{/if}}
				{{if $value.dealerBuyService!=null}}
					<a class="ui-button ui-button-morange" href="">ERP已开通</a>
					<p class="time">{{$formatDate $value.dealerBuyService.endTime}} 到期</p>
				{{/if}}
			{{ /if}}
			{{if $value.refrenceId=='S006'}}
				<a class="ui-button ui-button-morange" href="${ctx}/dealer/webServiceItems/details?refrenceId={{$value.refrenceId}}">购　　买</a>
			{{ /if}}
 			{{if $value.refrenceId!='S004'&&$value.refrenceId!='S005'&&$value.refrenceId!='S006'}}
				{{if $value.dealerBuyService==null}}
					<a class="ui-button ui-button-morange" href="${ctx}/dealer/webServiceItems/details?refrenceId={{$value.refrenceId}}">购　　买</a>
				{{/if}}
				{{if $value.dealerBuyService!=null}}
					{{ if $value.expired==true}}
						<a class="ui-button ui-button-mgreen" href="${ctx}/dealer/webServiceItems/details?refrenceId={{$value.refrenceId}}">续　　费</a>
					{{ /if}}
					{{ if $value.expired==false}}
						<a class="ui-button ui-button-mblue {{if $value.refrenceId=='S001'}}opened{{ /if}}" href="${ctx}/dealer/webServiceItems/details?refrenceId={{$value.refrenceId}}" >查　　看</a>
						<p class="time">{{$formatDate $value.dealerBuyService.endTime}} 到期</p>
					{{ /if}}
				{{/if}}
			{{ /if}}

			</td>
		</tr>
	{{/each}}
    {{ if rows.length == 0 }}
    <tr>
        <td colspan="7" style="text-align:center">暂无数据</td>
    </tr>
    {{ /if }}
	</script>
    <script>
        seajs.use(["pagination", "template", "moment","dialog"], function (Pagination, template, moment,Dialog) {
        	var order_model = $(".panel-tabs .panel-head ul li").click(function () {
                order_model.not($(this).addClass("on")).removeClass("on");
                var items = $(".panel-content .tab-item").hide().eq($(order_model).index(this)).show();
            });
	        template.helper('$formatDate', function (millsec) {
	            return moment(millsec).format("YYYY-MM-DD");
	        });
	        template.helper('$formatStar', function (val) {
	        	var stars=[];
	        	for(var i=0;i<val;i++){
	        		stars.push('<span class="star"></span>');
	        	}
	            return stars.join("");
	        });
	        template.helper("formatNumber",function(price){
                if(isNaN(price)){
                    return price;
                }else{
                    return parseFloat(price).toFixed(2);
                }
	        });
	        window['g_pagination']=  new Pagination({
                url: "${ctx}/dealer/webServiceItems/list?pageSize=10",
                elem: "#pagination",
                handleData: function (data) {
                    var html = template.render("service-items-template", data);
                    $("#service_items_grid").empty().append(html);
                }
            });


            var applyAPP = new Dialog({
                content:"#dialogApplyAPP",
                width:700
            });
            $(document).on("click",".applyAPP",function(){
            	if($("a.opened").length>0){
            		applyAPP.show();
            	}else{
            		 confirmDialog({ title: "提示", content: "请先购买平台服务!" }, function () {},false,null,true);
            	}

            });

            $(document).on("click",".trail",function(){
            	var dom=$(this);
            	if($("a.opened").length>0){
            		dom.removeClass("trail").addClass("ui-button-ldisable");
            		$.post("${ctx}/dealer/webServiceItems/trail",function(data){
            			if(data.code==126000){
                    		remind('success','保存成功',function(){
                    			g_pagination.render();
                            });
                    	}else{
                    		remind('error',data.message);
                            //$(this).addClass("trail").removeClass("trail");
                    	}
            			dom.removeClass("ui-button-ldisable").addClass("trail");
            		},"json");
            	}else{
            		 confirmDialog({ title: "提示", content: "请先购买平台服务!" }, function () {},false,null,true);
            	}
            });
	    });
    </script>
</body>
</html>
