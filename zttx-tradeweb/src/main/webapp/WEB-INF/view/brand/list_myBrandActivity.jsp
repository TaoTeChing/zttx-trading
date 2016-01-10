<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>管理中心-我的报名</title>
<link rel="stylesheet" href="${res}/styles/brand/global.css" />
<link rel="stylesheet" href="${res}/styles/brand/sign.css" />
</head>
<body>
	<jsp:include page="${ctx}/brand/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/brand/brandmenu"/>
		<div class="main_con">
            <div class="bread_nav">
                <div class="fl">
                    <a class="link" href="${ctx}/brand/center">管理中心</a>
                    <span class="arrow">&gt;&gt;</span>
                    <span>品牌管理</span>
                    <span class="arrow">&gt;</span>
                    <span class="current">报名列表</span>
                </div>
                <div class="fr">
                    <%@ include file="common_quick_bar.jsp" %>
                </div>
            </div>
			<div class="inner">
				<!-- 内容都放这个地方  -->
        <div class="ui_tab main_ui_tab">
            <ul class="ui_tab_items clearfix">
                <li class="ui_tab_item">
                    <a href="/brand/activity/list">所有活动</a>
                </li>
                <li class="ui_tab_item current">
                    <a href="javascript:;">我的报名</a>
                </li>
            </ul>
        </div>
        <div class="tab_con">
        <form:form class="ui-form search-form clearfix" id="activitySearchForm" style="display:none;"></form:form>
            <div class="data-list">
                
            </div>
        </div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />

	<script id="feedback-templage" type="text/html">
	<table class="list-table">
		<colgroup>
			<col width="450">
			<col width="120">
			<col width="120">
			<col width="120">
			<col width="140">
		</colgroup>
		{{each rows}}
		<tr>
			 <td class="ta-l">
				<div class="clearfix">
				<div class="img_contain">
					<a href="/brand/activity/detail?id={{$value.refrenceId}}">
						{{if $value.image!=null}}
							<img width="100" height="100" alt="" src="${res}{{$value.image}}">
						{{else}}
							<img width="100" height="100" alt="" src="${res}/images/common/nopic-100.gif">
						{{/if}}
					</a>
				</div>
				<div class="info_contain">
					<p class="name">
						<a href="/brand/activity/detail?id={{$value.refrenceId}}">{{$value.name}}</a>
					</p>
					{{if $value.chargeType==1}}
					<p class="price toll">
                                       	 收费
                	</p>
					{{else}}
					<p class="price free">
                                        	免费
                	</p>
					{{/if}}
					<p class="time">活动时间：{{$formatDate $value.startTime}} ～{{$formatDate $value.endTime}}</p>
				 </div>
				 </div>
			</td>
			<td>
				<p class="double_row">
                                报名品牌：<br />
                                {{$value.brandSetName}}
				</p>
			</td>
			<td>
				<p class="double_row">
                                报名时间：<br />
                                {{$value.earlyRegTime}}
				</p>
			</td>
			<td>
				<p class="double_row">
                                当前状态：<br />
			  {{if $value.curExamineState==-1}}
					<span class="status_expired">活动已过期</span>
			  {{else}}
			  	{{if $value.curExamineState==0}}
					<span class="status_on">等待审核</span>
				{{/if}}
				{{if $value.curExamineState==1}}
					<span class="status_pass">审核通过</span>
				{{/if}}
				{{if $value.curExamineState==2}}
					<span class="status_nopass js-nopass">审核不通过</span>
				{{/if}}
			  {{/if}}
				</p>
			</td>
			<td class="ta-c">
				{{if $value.curState==3}}
				<div class="mt10">
					<a href="javascript:void(0);" class="ui_button ui_button_mgray">活动已过期</a>
				</div>
				{{else}}
					<div>
						<a class="ui_button ui_button_mblue" href="${ctx}/brand/activity/complete?id={{$value.refrenceId}}">修改报名信息</a>
					</div>
					{{if $value.curState==2}}
					<div class="mt10">
						{{if $value.code=='A00001'}}
						<a href="${ctx}/common/dealer/explosionList" class="ui_button ui_button_mred">进入活动页</a>
						{{else if $value.code=='A00002'}}
						<a href="${ctx}/market/clearance" class="ui_button ui_button_mred">进入活动页</a>
						{{/if}}
					</div>
					{{else}}
					<div class="mt10">
						<a href="javascript:void(0);" class="ui_button ui_button_mgray">活动未开始</a>
					</div>
					{{/if}}
				{{/if}}
				{{if $value.code=='A00002'}}
                <div class="mt10">
                    <a href="${ctx}/brand/activity/statistics" class="ui_button ui_button_morange">活动统计</a>
                </div>
				{{/if}}
			</td>
		</tr>
		{{/each}}
		{{ if rows.length == 0 }}
        <tr>
            <td class="ta-c" colspan="5">暂无数据</td>
        </tr>
        {{ /if }}
	</table>
	</script>
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
	<script>
	  var page;
	  $(function () {
	    seajs.use(["pagination","moment","template"], function (Pagination,moment,template) {
	    	template.helper('$formatDate', function (millsec) {
            	return moment(millsec).format("YYYY-MM-DD");
        	});
        	var renderPagination = function(){
        	page = new Pagination({
              url: "${ctx}/brand/activity/mylist/data",
              elem: "#pagination",
              form:$("#activitySearchForm"),
              method:"post",
              handleData: function (json) {
                  var html = template.render("feedback-templage", json);   
                  $(".data-list").html(html);
              }
          });
          };
          renderPagination();
        });
      });
	</script>
</body>
</html>