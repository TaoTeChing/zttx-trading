<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>管理中心-活动报名列表</title>
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
                <li class="ui_tab_item current">
                    <a href="javascript:;">所有活动</a>
                </li>
               <%-- <li class="ui_tab_item">
                    <a href="/brand/activity/mylist">我的报名</a>
                </li>--%>
            </ul>
        </div>
        <div class="tab_con">
            <form:form class="ui-form search-form clearfix" id="activitySearchForm" style="border:0;"></form:form>
            <div class="data-list">
            </div>
        </div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />

	<script id="feedback-templage" type="text/html">
	<table class="list-table">
		<colgroup>
			<col width="450">
			<col width="150">
			<col width="150">
			<col width="200">
		</colgroup>
		{{each rows}}
		<tr>
			<td class="ta-l">
			<div class="clearfix">
			<div class="img_contain">
				<a href="#">
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
			<td>{{if $value.regStateType==0}}报名未开始{{/if}}{{if $value.regStateType==1}}当前可报名{{/if}}{{if $value.regStateType==2}}报名已截止{{/if}}</td>
			<td> 已有<span class="num">{{$value.productCount}}</span>产品报名</td>
			<td class="ta-c">
			{{if $value.regStateType==1}}
                {{if $value.hasJoin}}
                    <a href="/brand/activity/products?id={{$value.refrenceId}}" class="ui_button ui_button_mblue">修改报名</a>
                {{else}}
                    <a href="/brand/activity/products?id={{$value.refrenceId}}" class="ui_button ui_button_mred">我要报名</a>
                {{/if}}
			{{else}}
				<a href="javascript:void(0);" class="ui_button ui_button_mgray">我要报名</a>
			{{/if}}
			</td>
		</tr>
		{{/each}}
		{{ if rows.length == 0 }}
        <tr>
            <td class="ta-c" colspan="4">暂无数据</td>
        </tr>
        {{ /if }}
 	</table>
	</script>
	<script>
	  var page;
	  $(function () {
	    seajs.use(["pagination","moment","template"], function (Pagination,moment,template) {
	    	template.helper('$formatDate', function (millsec) {
            	return moment(millsec).format("YYYY-MM-DD");
        	});
        	var renderPagination = function(){
        	page = new Pagination({
              url: "${ctx}/brand/activity/list/data",
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