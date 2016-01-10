<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-选择产品线</title>
    <link rel="stylesheet" href="/styles/brand/global.css" />
    <link rel="stylesheet" href="/styles/brand/sign.css" />
    <style>
        .list-table tr:hover td{
            cursor: pointer;
            background: #f7f7f7;
        }
    </style>
</head>
<body class="sign-body">
<jsp:include page="${ctx}/brand/mainmenu"/>
<div class="main layout">
    <jsp:include page="${ctx}/brand/brandmenu"/>
    <div class="main_con">
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
                <span class="arrow">&gt;</span>
                <a class="link" href="${ctx}/brand/activity/mylist">报名列表</a>
                <span class="arrow">&gt;</span>
                <span class="current">等待审核</span>
            </div>
            <div class="fr">
                <%@ include file="common_quick_bar.jsp" %>
            </div>
        </div>
        <div class="inner sign-box">
            <div class="v2-steps v2-3steps">
                <span class="text1">选择产品线</span>
                <span class="text2">设置活动属性</span>
                <span class="text3 current">等待审核</span>
                <div class="steps v2-steps-3"></div>
            </div>
            <div class="set-bd">
                <div class="ui_tab">
                    <ul class="ui_tab_items clearfix">
                        <li class="ui_tab_item current">
                            等待审核的产品线
                        </li>
                    </ul>
                </div>
                <%--<h3><span>当前已选择的产品线</span></h3>--%>
                <form:form class="ui-form search-form clearfix" id="activitySearchForm" style="margin:15px 0;">
                    <input type="hidden" name="id" id="id" value="${attributeValue.refrenceId}"/>
                    <input type="hidden" name="lineId" id="lineId"/>
                    <table class="list-table border-table">

                    </table>
                </form:form>
                <div class="btns clearfix">
                    <a href="${ctx}/brand/activity/lines?id=${attributeValue.refrenceId}" class="mr5 simple_button ui_button ui-button_lgray">继续添加产品线</a>
                    <a href="${ctx}/brand/activity/mylist" class="ui_button ui_button_lblue">完成</a>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script id="feedback-templage" type="text/html">
    <colgroup>
        <col width="220">
        <col width="180">
        <col width="150">
        <col width="130">
        <col width="140">
        <col width="150">
    </colgroup>
    <tr>
        <th class="first">产品线名称</th>
        <th>用户类型</th>
        <th>区域</th>
        <th>折扣</th>
        <th>产品数量</th>
        <th>审核失败产品数量</th>
    </tr>
    {{each rows}}
    <tr class="current" data-id="{{$value.refrenceId}}">
        <td class="first">{{$value.lineName}}</td>
        <td>{{$value.lineTypeName}}</td>
        <td>
            {{if $value.areaType==1}}
            {{$value.areaCount}}个人
            {{else}}
            {{$value.areaCount}}个市
            {{/if}}
        </td>
        <td>{{$value.lineAgio}}</td>
        <td>{{$value.productCount}}</td>
        <td class="last-col">{{$value.productFailureCount}}</td>
    </tr>
    {{/each}}
    {{ if rows.length == 0 }}
    <tr>
        <td class="ta-c" colspan="6">暂无数据</td>
    </tr>
    {{ /if }}
</script>
<script id="feedback-templage-product" type="text/html">
    {{each rows}}
    <li data-id="{{$value.id}}">
        <div class="img_contain">
            <img src="${res}{{$value.productImage}}" width="85" height="85" alt=""/>
            <p class="fn-text-overflow">
                货号：{{$value.productNo}} <br/>{{$value.id}}
            </p>
            <i></i>
        </div>
    </li>
    {{/each}}
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
              url: "${ctx}/brand/activity/complete/data",
              elem: "#pagination",
              form:$("#activitySearchForm"),
              method:"post",
              handleData: function (json) {
                  var html = template.render("feedback-templage", json);
                  $(".list-table").html(html);
                  $(".list-table tr.current").on("click",function(){
                  	var lineId = $(this).data("id");
                  	$("#lineId").val(lineId);
                  	$("#activitySearchForm").attr("action","${ctx}/brand/activity/products");
                  	$("#activitySearchForm").submit();
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