<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-选择产品线</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/sign.css" />
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
                <span class="current">选择产品线</span>
            </div>
            <div class="fr">
                <%@ include file="common_quick_bar.jsp" %>
            </div>
        </div>
        <div class="inner sign-box">
            <div class="v2-steps v2-3steps">
                <span class="text1 current">选择产品线</span>
                <span class="text2">设置活动属性</span>
                <span class="text3">等待审核</span>
                <div class="steps v2-steps-1"></div>
            </div>
            <%--<div class="choose-hd">
                <h3>请选择想要参加${brandActivityList.name}活动的产品线：</h3>
            </div>--%>
            <div class="ui_tab">
                <ul class="ui_tab_items clearfix">
                    <li class="ui_tab_item current">
                        请选择想要参加${brandActivityList.name}活动的产品线
                    </li>
                </ul>
            </div>
            <a href="${ctx}/brand/line/execute" class="simple_button mt10"> <i class="iconfont">&#xe611;</i>新增产品线</a>
            <div class="choose-bd">
                <form:form class="ui-form search-form clearfix" id="activitySearchForm" style="margin:10px 0;">
                    <input type="hidden" name="id" id="id" value="${attributeValue.refrenceId}"/>
                    <table class="list-table">
                    </table>
                </form:form>
                <div class="mt20">
                    <button class="ui_button ui_button_lblue" style="width:95px;margin-left:20px;" onclick="activityNext();">下一步</button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />

<script id="feedback-templage" type="text/html">
    <colgroup>
        <col width="30">
        <col width="220">
        <col width="180">
        <col width="180">
        <col width="180">
        <col width="180">
    </colgroup>
    <tr>
        <th></th>
        <th>产品线名称</th>
        <th>用户类型</th>
        <th>区域/人数</th>
        <th>折扣</th>
        <th class="last-col">产品数量</th>
    </tr>
    {{each rows}}
    <tr>
        <td>
            <input name="lineId" class="lineRadio" type="radio" value="{{$value.refrenceId}}"/>
        </td>
        <td>{{$value.lineName}}</td>
        <td>{{$value.lineTypeName}}</td>
        <td>
            {{if $value.areaType==1}}
            {{$value.areaCount}}个人
            {{else}}
            {{$value.areaCount}}个市
            {{/if}}
        </td>
        <td>{{$value.lineAgio}}</td>
        <td class="last-col">{{$value.productCount}}</td>
    </tr>
    {{/each}}
    {{ if rows.length == 0 }}
    <tr>
        <td class="ta-c" colspan="6">暂无数据</td>
    </tr>
    {{ /if }}
</script>
<script>
    function activityNext(){
        <%--bug #4070 新增无产品线的判断--%>
        if ($(".lineRadio").length <= 0) {
            remind('error', '请添加工厂店产品线报名活动');
            return;
        }
		if (!$(".lineRadio").is(":checked")) {
			remind('error', '请至少选择一个产品线');
            return;
        }
		$("#activitySearchForm").attr("action", "/brand/activity/products");
		$("#activitySearchForm").submit();
	}
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
              url: "${ctx}/brand/activity/lines/data",
              elem: "#pagination",
              form:$("#activitySearchForm"),
              method:"post",
              handleData: function (json) {
                  var html = template.render("feedback-templage", json);
                  $(".list-table").html(html);
              }
          });
          };
          renderPagination();
        });
      });
	</script>
</body>
</html>