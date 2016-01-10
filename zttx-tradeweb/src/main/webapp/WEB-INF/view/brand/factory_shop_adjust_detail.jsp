<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>管理中心-调价单</title>
  <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
  <link rel="stylesheet" href="${res}/styles/brand/factory.css"/>

</head>
<body>
<jsp:include page="${ctx}/brand/mainmenu"/>
<div class="main layout">
  <jsp:include page="${ctx}/brand/brandmenu"/>
  <div class="main_con">
    <!-- 面包导航，并不是每个页面都有 -->
    <div class="bread_nav">
      <div class="fl">
        <a class="link" href="${ctx}/brand/center">管理中心</a>
        <span class="arrow">&gt;&gt;</span>
        <a class="link" href="${ctx}/brand/order/financial">终端商财务账</a>
        <span class="arrow">&gt;&gt;</span>
        <span class="current">调价单</span>
      </div>
      <div class="fr">
        <%@ include file="common_quick_bar.jsp" %>
      </div>
    </div>
    <!-- 面包屑结束 -->
    <div class="inner">
      <!-- 内容都放这个地方  -->
      <form:form class="ui-form factory-seeking-form clearfix" action="">
        <div class="ui-form-item" style="width: 240px;">
          <label for="" class="ui-label">
            品牌：
          </label>
          <input class="ui-input" type="text"  name = "brandesName" value="" style="width: 155px;"/>
        </div>
        <div class="ui-form-item" style="width: 240px;">
          <label for="" class="ui-label">
            产品名称：
          </label>
          <input class="ui-input" type="text" name="productTitle" value="" style="width: 155px;"/>
        </div>
        <div class="ui-form-item" style="width: 240px;">
          <label for="" class="ui-label">
            产品货号：
          </label>
          <input class="ui-input" type="text"  name = "productNo" value="" style="width: 155px;"/>
        </div>
        <div class="ui-form-item" style="width: 100px;">
          <div class="inline-block">
            <input type="button" value="搜索" class="ui_button_myourself">
          </div>
        </div>
      </form:form>
      <div class="ta-r mr10">
        金额单位：元
      </div>
      <table class="factory-count-table" id="count_detail">
        <colgroup>
          <col width="155">
          <col width="90">
          <col width="205">
          <col width="100">
          <col width="100">
          <col width="100">
          <col width="100">
          <col width="60">
          <col width="100">
        </colgroup>
        <thead>
        <tr>
          <th>时间</th>
          <th>品牌</th>
          <th>产品名称</th>
          <th>产品货号</th>
          <th>颜色/尺码</th>
          <th>原价</th>
          <th>现价</th>
          <th>库存量</th>
          <th>优惠/加价金额</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
      </table>
      <div id="pagination"></div>
      <div class="factory-count-box clearfix"></div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp"/>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script id="feedback-templage" type="text/html">
  {{each rows}}
  <tr>
    <td>{{$getLocalTime $value.time}}</td>
    <td>{{$value.brandesName}}</td>
    <td>{{$value.productTitle}}</td>
    <td>{{$value.productNo}}</td>
    <td>{{$value.productSkuName}}</td>
    <td>{{$formatPrice $value.oldFacSkuDirPrice}}</td>
    <td>{{$formatPrice $value.nowFacSkuDirPrice}}</td>
    <td>{{$value.quantity}}</td>
    <td>{{$formatPrice $value.totalAdjustPrice}}</td>
  </tr>
  {{/each}}
  {{ if !rows || rows.length == 0 }}
  <tr>
    <td class="ta-c" colspan="10">暂无数据</td>
  </tr>
  {{ /if }}
</script>
<script id="countTpl" type="text/html">
  <div class="fl" style="color: #999;">
    说明：负数表示优惠，正数表示加价。
  </div>
  <div class="fr">
    合计金额：<span class="consult" style="color: #f00;">{{$formatPrice object.totalAdjustPrice==null?0:object.totalAdjustPrice}}</span>&nbsp;
    共 <span class="consult" style="color: #f00;">{{if object.totalNum == null }}0{{else}}{{object.totalNum}}{{/if}}</span> 条
  </div>
</script>
<script>

  $(function () {
    seajs.use(["pagination", "template", "moment"], function (Pagination, template, moment) {
      var page;
      template.helper('$getLocalTime', function (millsec) {
        return moment(millsec).format("YYYY-MM-DD HH:mm:00");
      });
      template.helper('$formatPrice', function (price) {
        if (isNaN(price)) {
          return price;
        } else {
          return price.toFixed(2);
        }
      });
      var renderPagination = function () {
        page = new Pagination({
          url: "${ctx}/brand/order/financial/factory/adjustPrice/data",
          elem: "#pagination",
          data: {isFacotryStore:true,dealerId:"${dealerId}", brandAdjustId:"${brandAdjustId}"},
          form: $(".ui-form"),
          method: "post",
          pageSize: 20,
          handleData: function (json) {
            //console.log(json);
            var html1 = template.render("feedback-templage", json);
            var html2 = template.render("countTpl", json);
            $(".factory-count-table tbody").html(html1);
            $(".factory-count-box").html(html2);
          }
        });
      };
      renderPagination();
      $(".ui_button_myourself").click(function () {
        page.goTo(1);
      });
    });
  });
</script>
</body>
</html>